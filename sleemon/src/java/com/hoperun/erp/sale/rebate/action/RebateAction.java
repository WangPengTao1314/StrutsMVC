package com.hoperun.erp.sale.rebate.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.base.chann.action.ChannAction;
import com.hoperun.base.chann.model.ChannModel;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.rebate.service.RebateService;
import com.hoperun.sys.model.UserBean;

public class RebateAction extends BaseAction {

	
	private Logger logger = Logger.getLogger(ChannAction.class);
	
	/** 权限对象**/
    /** 维护*/
    //维护界面
    //增删改查
    private static String PVG_BWS = "BS0020201";
    private static String PVG_EDIT = "BS0020202";
    private static String PVG_DELETE = "";
    
    private static String PVG_RESET = "BS0020203";//清空返利
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /**end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="";
    private static String PVG_TRACE="";
    
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    
    //审批流参数
    private static String AUD_TAB_NAME = "";//表名
    private static String AUD_TAB_KEY = ""; //主键
	private static String AUD_BUSS_STATE = "";
    private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
    /**审批 end**/
	
	
	private RebateService rebateService;
	
	/**
	 * * query List data
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		
	
		Map<String,String> params = new HashMap<String,String>();
		ParamUtil.putStr2Map(request, "CHECK_IN", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    params.put("STATE",BusinessConsts.JC_STATE_ENABLE);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
		StringBuffer sql = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		String CHECK_IN = ParamUtil.get(request, "CHECK_IN");
		if(null != CHECK_IN){

			//已登记
			if(BusinessConsts.CHECK_IN.equals(CHECK_IN)){
				sql.append(" and c.REBATE_TOTAL>0 ");
			}

			//未登记
			if(BusinessConsts.UN_CHECK_IN.equals(CHECK_IN)){
				sql.append(" and  NVL(c.REBATE_TOTAL,0)=0 ");
			}
		}
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		if(!StringUtil.isEmpty(CHANN_NO)){
			sql.append(" and c.CHANN_NO ='").append(CHANN_NO).append("' ");
		}
		
	    params.put("QXJBCON", sql.toString());
		
	    //字段排序
		ParamUtil.setOrderField(request, params, "c.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = rebateService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	public ActionForward toSetRebate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		  String CHANN_NOS = ParamUtil.get(request,"CHANN_NOS");
		  String DESCON = ParamUtil.get(request,"DESCON");
		request.setAttribute("CHANN_NOS", CHANN_NOS);
		request.setAttribute("DESCON", DESCON);
		return mapping.findForward("setRebatePage");
	}
	
	public void setRebateAmount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
		      Map<String,String> params = new HashMap<String,String>();
			  String DESCON = ParamUtil.get(request,"DESCON");
			  if(DESCON==null||DESCON.trim().length()==0){
				  jsonResult = jsonResult(false, "保存失败");
				  return;
			  }
			  String SET_REBATE = ParamUtil.get(request,"SET_REBATE");
			  if(SET_REBATE==null||SET_REBATE.trim().length()==0){
				  jsonResult = jsonResult(false, "保存失败");
				  return;
			  }
			  params.put("LEDGER_ID", userBean.getLoginZTXXID());
			  ParamUtil.putStr2Map(request, "CHANN_NOS", params);
			  ParamUtil.putStr2Map(request, "DESCON", params);
			  ParamUtil.putStr2Map(request, "SET_REBATE", params);
		      boolean flg = rebateService.txUpdateRebateByIds(params);
		      if(flg){
		    	  jsonResult = jsonResult(true, "成功");
		      }else{
		    	  jsonResult = jsonResult(false, "保存失败");
		      }		

      } catch (Exception e) {
          jsonResult = jsonResult(false, "保存失败");
          e.printStackTrace();
      }
      if (null != writer) {
          writer.write(jsonResult);
          writer.close();
      }
	}

	
	/**
	 * * 主表 新增/修改数据
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
          
            String jsonData = ParamUtil.get(request, "jsonData");
            List<ChannModel> modelList = null;
            if(!StringUtil.isEmpty(jsonData)){
            	modelList = new Gson().fromJson(jsonData,
						new TypeToken<List<ChannModel>>() {
						}.getType());
            }
            
            if(null != modelList){
            	rebateService.txEdit(modelList, userBean);
            	jsonResult = jsonResult(true, "保存成功");
            }
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
	 * * 清空返利
	 * 1、先查看是否有冻结的返利，有就提示
	 * 2、如果全部没有冻结  就插入返利历史记录表 ERP_REBATE_HIS
	 * 3、更新BASE_CHANN表
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void rest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_RESET))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
          
			//先查看是否有被冻结的返利
			List<ChannModel> list =  rebateService.isHaveRebateList();
			
			if(null != list && list.size()>0){
				StringBuffer sb = new StringBuffer();
				sb.append("[");
				int size = list.size();
				for(int i=0;i<size;i++){
					sb.append(list.get(i).getCHANN_NAME());
					if((i+1)<size){
						sb.append(",");
					}
					
				}
				sb.append("]");
				jsonResult = jsonResult(false, sb.toString()+" 当前还有返利被单据引用，不能被清空！");
			}else{
				rebateService.txRest(userBean);
				jsonResult = jsonResult(true, "清除成功");
			}
			
           
            
        } catch (Exception e) {
            jsonResult = jsonResult(false, "清除失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	
	 //导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,String> params = new HashMap<String,String>();
		ParamUtil.putStr2Map(request, "CHECK_IN", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    params.put("STATE",BusinessConsts.JC_STATE_ENABLE);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装
		StringBuffer sql = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		String CHECK_IN = ParamUtil.get(request, "CHECK_IN");
		if(null != CHECK_IN){

			//已登记
			if(BusinessConsts.CHECK_IN.equals(CHECK_IN)){
				sql.append(" and c.REBATE_TOTAL>0 ");
			}

			//未登记
			if(BusinessConsts.UN_CHECK_IN.equals(CHECK_IN)){
				sql.append(" and  NVL(c.REBATE_TOTAL,0)=0 ");
			}
		}
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		if(!StringUtil.isEmpty(CHANN_NO)){
			sql.append(" and c.CHANN_NO ='").append(CHANN_NO).append("' ");
		}
		
	    params.put("QXJBCON", sql.toString());
	  //字段排序
		ParamUtil.setOrderField(request, params, "c.CRE_TIME", "DESC");
        List list=rebateService.downQuery(params);
        //excel数据上列显示的列明
        String tmpContentCn="渠道编号,渠道名称,渠道类型,区域编号,区域名称,返利总额,当前返利,已用返利";
        String tmpContent="CHANN_NO,CHANN_NAME,CHANN_TYPE,AREA_NO,AREA_NAME,REBATE_TOTAL,REBATE_CURRT,REBATE_USED";
        try {
			ExcelUtil.doExport(response, list, "返利登记", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
	/**
	 * * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String,String> setPvg(UserBean userBean)
	    {
	    	Map<String,String>pvgMap=new HashMap<String,String>();
	    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
	    	pvgMap.put("PVG_EDIT",QXUtil.checkPvg(userBean, PVG_EDIT));
	    	pvgMap.put("PVG_DELETE",QXUtil.checkPvg(userBean, PVG_DELETE)  );
	    	pvgMap.put("PVG_START_STOP",QXUtil.checkPvg(userBean, PVG_START_STOP) );
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE)  );
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_FINISH_CANCLE",QXUtil.checkPvg(userBean, PVG_FINISH_CANCLE) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
			pvgMap.put("PVG_RESET",PVG_RESET);
	    	return  pvgMap;
	   }
	 

	public RebateService getRebateService() {
		return rebateService;
	}

	public void setRebateService(RebateService rebateService) {
		this.rebateService = rebateService;
	}
	

	 

	
	
	
}
