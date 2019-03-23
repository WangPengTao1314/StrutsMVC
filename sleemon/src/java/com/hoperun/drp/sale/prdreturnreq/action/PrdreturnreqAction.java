/**
 * prjName:喜临门营销平台
 * ucName:退货申请单维护
 * fileName:PrdreturnAction
*/
package com.hoperun.drp.sale.prdreturnreq.action;
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
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.prdreturnreq.model.PrdreturnreqModel;
import com.hoperun.drp.sale.prdreturnreq.model.PrdreturnreqModelChld;
import com.hoperun.drp.sale.prdreturnreq.service.PrdreturnreqService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func 退货申请单
 * *@version 1.1 
 * *@author wzg
 * *@createdate 2013-08-15 10:17:13
 */
public class PrdreturnreqAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(PrdreturnreqAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0020701";
    private static String PVG_EDIT="FX0020702";
    private static String PVG_DELETE="FX0020703";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0020704";
    private static String PVG_TRACE="FX0020705";
    //审核模块
    
    private static String PVG_BWS_AUDIT0="BS0012001";
    private static String PVG_BWS_AUDIT="BS0012101";
    private static String PVG_AUDIT_AUDIT="BS0012102";
    private static String PVG_TRACE_AUDIT="BS0012103";
    //审批流参数
    private  String AUD_TAB_NAME = "DRP_PRD_RETURN_REQ";
    private  String AUD_TAB_KEY = "u.PRD_RETURN_REQ_ID";
	private  String AUD_BUSS_STATE = "STATE";
    private  String AUD_BUSS_TYPE = "DRP_PRD_RETURN_REQ_AUDIT";
	private  String AUD_FLOW_INS = "com.hoperun.drp.sale.prdreturnreq.service.impl.PrdreturnreqFlowImpl";

	/**审批 end**/
    /** 业务service*/
	private PrdreturnreqService prdreturnreqService;
    /**
	 * Sets the Prdreturn service.
	 * 
	 * @param PrdreturnreqService the new Prdreturn service
	 */
	public void setPrdreturnreqService(PrdreturnreqService prdreturnreqService) {
		this.prdreturnreqService = prdreturnreqService;
	}
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT0))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		//初审状态
		String firstAudit = ParamUtil.get(request,"firstAudit");
		request.setAttribute("firstAudit", firstAudit);
		
		// [STATE][search]add by zzb  2014-6-21 分销首页 [待入库]url用到 该参数
		String STATE = ParamUtil.utf8Decoder(request, "STATE");
		String search = ParamUtil.utf8Decoder(request, "search");
		request.setAttribute("STATE", STATE);
		request.setAttribute("search", search);
		
		return mapping.findForward("frames");
	}
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)
				&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT0)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "PRD_RETURN_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RETURN_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);//订货订单编号
	    
	    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String firstAudit = ParamUtil.get(request, "firstAudit");
		String strPvgBwsAudit = null;
		String moduleName = "退货申请单维护";
		
		
		//modify zzb   2014-6-21 分销首页 [待入库]url用到 该参数
		
		String STATE = ParamUtil.get(request,"STATE");
		if("isFirst".equals(search)){
			 STATE = ParamUtil.utf8Decoder(request, "STATE");
		} 
		if(!StringUtil.isEmpty(STATE)){
			params.put("STATE", STATE);
		}
		
		String checkSql="";
		String empSql="";
		//维护
		if("wh".equals(module)){
			moduleName = "退货申请单维护";
			empSql="JGXXID='"+userBean.getJGXXID()+"'";//人员查询
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
			params.put("addCheck", " LEDGER_ID='"+userBean.getLoginZTXXID()+"' ");
			
		}else {
			 params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());//添加渠道分管
		}
		//审核
		if("sh".equals(module)&&!"fsh".equals(firstAudit)){
			moduleName = "退货申请单审核";
			strPvgBwsAudit = PVG_BWS_AUDIT;
//			checkSql=" and BILL_TYPE in('售后退货') ";
			empSql="1=1";
		}
		//初审
		if("fsh".equals(firstAudit)&&"sh".equals(module)){
			moduleName = "退货申请单初审";
			params.put("RETURN_CHANN_ID", userBean.getCHANNS_CHARG());
			strPvgBwsAudit = PVG_BWS_AUDIT0;
//			checkSql=" and BILL_TYPE ='关店退货' ";
			empSql="1=1";
		}		
		params.put("empSql", empSql);
		Map<String,String> chann = prdreturnreqService.getChann(userBean.getCHANN_ID());
		String AREA_SER_ID = chann.get("AREA_SER_ID");
		
		// add by zzb 
		String RECV_CHANN_ID = userBean.getBASE_CHANN_ID();
		//添加是否走原来流程的开关
		if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
		     AUD_TAB_NAME = "DRP_PRD_RETURN_REQ";
		     AUD_TAB_KEY = "PRD_RETURN_REQ_ID";
			 AUD_BUSS_STATE = "STATE";
		     AUD_BUSS_TYPE = "DRP_SER_PRD_RETURN_REQ_AUDIT";
		     RECV_CHANN_ID = AREA_SER_ID;
		}else{
	        AUD_TAB_NAME = "DRP_PRD_RETURN_REQ";
	        AUD_TAB_KEY = "PRD_RETURN_REQ_ID";
		    AUD_BUSS_STATE = "STATE";
	        AUD_BUSS_TYPE = "DRP_PRD_RETURN_REQ_AUDIT";
		}
		
		
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,strPvgBwsAudit,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		sb.append(checkSql);
		request.setAttribute("moduleName", moduleName);
		if(StringUtil.isEmpty(params.get("addCheck"))){
			params.put("addCheck", " 1=1 ");
		}
		
		
		params.put("QXJBCON", sb.toString());
	    params.put("addQXJB", ParamUtil.getPvgCon("true",module,PVG_BWS,strPvgBwsAudit,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean)+checkSql);
	   
		
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = prdreturnreqService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("search", search);
		request.setAttribute("page", page);
		if("wh".equals(module)){
			request.setAttribute("RECV_CHANN_ID", RECV_CHANN_ID);
		}
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
	}
	
	 /**
     * * 明细列表
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT0)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String PRD_RETURN_REQ_ID =ParamUtil.get(request, "PRD_RETURN_REQ_ID");
        if(!StringUtil.isEmpty(PRD_RETURN_REQ_ID))
        {
        	 List <PrdreturnreqModelChld> result = prdreturnreqService.childQuery(PRD_RETURN_REQ_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("childlist");
    }
	
	 /**
     * *
     * * to 编辑框架页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        //设置跳转时需要的一些公用属性
        ParamUtil.setCommAttributeEx(request);
        request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        return mapping.findForward("editFrame");
    }
	
	/**
	 * * to 主表编辑页面初始化
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PRD_RETURN_REQ_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry = null;
		if(!StringUtil.isEmpty(PRD_RETURN_REQ_ID)){
			entry = prdreturnreqService.load(PRD_RETURN_REQ_ID);
			request.setAttribute("rst", entry);
		}else{
			 entry = new HashMap<String,String>();
			 Map<String,String> chann =  prdreturnreqService.loadChann(userBean.getCHANN_ID());
			//单据类型
			entry.put("BILL_TYPE", "销售退货");
			//退货方默认值(自己)
			entry.put("RETURN_CHANN_ID", userBean.getCHANN_ID());
			entry.put("RETURN_CHANN_NO", userBean.getCHANN_NO());
			entry.put("RETURN_CHANN_NAME", userBean.getCHANN_NAME());
			entry.put("CHAA_LVL", chann.get("CHAA_LVL"));//渠道级别
			String AREA_SER_ID = chann.get("AREA_SER_ID");
			//走区域服务中心
			if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
				//收货方是区域服务中心
				entry.put("RECV_CHANN_ID", AREA_SER_ID);
				entry.put("RECV_CHANN_NO", chann.get("AREA_SER_NO"));
				entry.put("RECV_CHANN_NAME", chann.get("AREA_SER_NAME"));
			}else{
				//收货方默认值(总部)
				entry.put("RECV_CHANN_ID", userBean.getBASE_CHANN_ID());
				entry.put("RECV_CHANN_NO", userBean.getBASE_CHANN_NO());
				entry.put("RECV_CHANN_NAME", userBean.getBASE_CHANN_NAME ());
			}
			request.setAttribute("rst", entry);
		}
		request.setAttribute("AREA_SER_ID", userBean.getAREA_SER_ID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("toedit");
	}
	
	/**
     * * 编辑框架页面加载子页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
    	request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
    	
        String PRD_RETURN_REQ_ID = ParamUtil.get(request, "PRD_RETURN_REQ_ID");
        
        if(!StringUtil.isEmpty(PRD_RETURN_REQ_ID))
        {
        	 List <PrdreturnreqModelChld> result = prdreturnreqService.childQuery(PRD_RETURN_REQ_ID);
             request.setAttribute("rst", result);
        }
        return mapping.findForward("childedit");
    }
	
	 /**
     * * to 直接编辑明细页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String PRD_RETURN_DTL_REQ_IDs = request.getParameter("PRD_RETURN_DTL_REQ_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(PRD_RETURN_DTL_REQ_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PRD_RETURN_DTL_REQ_IDS",PRD_RETURN_DTL_REQ_IDs);
          List <PrdreturnreqModelChld> list = prdreturnreqService.loadChilds(params);
          request.setAttribute("rst", list);
        }
        return mapping.findForward("childedit");
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
            String PRD_RETURN_REQ_ID = ParamUtil.get(request, "PRD_RETURN_REQ_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            PrdreturnreqModel model = new Gson().fromJson(parentJsonData, new TypeToken <PrdreturnreqModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <PrdreturnreqModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrdreturnreqModelChld>>() {
                }.getType());
            }
            prdreturnreqService.txEdit(PRD_RETURN_REQ_ID, model, chldModelList, userBean);
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        }catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
     * * 子表 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRD_RETURN_REQ_ID = request.getParameter("PRD_RETURN_REQ_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <PrdreturnreqModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <PrdreturnreqModelChld>>() {
                }.getType());
                prdreturnreqService.txChildEdit(PRD_RETURN_REQ_ID, modelList);
            }
        }catch(ServiceException s) {
        	jsonResult = jsonResult(false, s.getMessage());
        }catch (Exception e) {
            jsonResult = jsonResult(false, "保存失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
    /**
	 * * 主表 删除
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String PRD_RETURN_REQ_ID = ParamUtil.get(request, "PRD_RETURN_REQ_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PRD_RETURN_REQ_ID", PRD_RETURN_REQ_ID);
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			prdreturnreqService.txDelete(params);
			prdreturnreqService.clearCaches(params);
		} catch (Exception e) {
		    e.printStackTrace();
			jsonResult = jsonResult(false, "删除失败!");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	 /**
     * * 明细批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRD_RETURN_DTL_REQ_IDs = request.getParameter("PRD_RETURN_DTL_REQ_IDS");
            //批量删除，多个Id之间用逗号隔开
            prdreturnreqService.txBatch4DeleteChild(PRD_RETURN_DTL_REQ_IDs);
		} catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
	/**
	 * * to 详细信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)
				&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT0)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PRD_RETURN_REQ_ID = ParamUtil.get(request, "PRD_RETURN_REQ_ID");
		if(!StringUtil.isEmpty(PRD_RETURN_REQ_ID)){
			Map<String,String> entry = prdreturnreqService.load(PRD_RETURN_REQ_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    /**
     * * 提交时，校验是否有明细.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String PRD_RETURN_REQ_ID = request.getParameter("PRD_RETURN_REQ_ID");
            String AREA_SER_ID = request.getParameter("AREA_SER_ID");
            String flag = request.getParameter("flag");
            List <PrdreturnreqModelChld> list = prdreturnreqService.childQuery(PRD_RETURN_REQ_ID);
            if(null == list || list.size() == 0) {
            	jsonResult = jsonResult(false, "没有明细，不能提交!");
            }else if("commit".equals(flag)){//有区域服务中心的走下面的提交
            	prdreturnreqService.txCommit(PRD_RETURN_REQ_ID, AREA_SER_ID, userBean);
            	jsonResult = jsonResult(true, "提交成功");
            }
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    
    
    
	// 导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,String> params = new HashMap<String,String>();
		ParamUtil.putStr2Map(request, "PRD_RETURN_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "RETURN_STORE_NO", params);
	    ParamUtil.putStr2Map(request, "RETURN_STORE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);//订货订单编号
	    
	    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String firstAudit = ParamUtil.get(request, "firstAudit");
		String strPvgBwsAudit = null;
		String moduleName = "退货申请单维护";
		
		
		//modify zzb   2014-6-21 分销首页 [待入库]url用到 该参数
		
		String STATE = ParamUtil.get(request,"STATE");
		if("isFirst".equals(search)){
			 STATE = ParamUtil.utf8Decoder(request, "STATE");
		} 
		if(!StringUtil.isEmpty(STATE)){
			params.put("STATE", STATE);
		}
		
		String checkSql="";
		String empSql="";
		//维护
		if("wh".equals(module)){
			moduleName = "退货申请单维护";
			empSql="JGXXID='"+userBean.getJGXXID()+"'";//人员查询
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
			params.put("addCheck", " LEDGER_ID='"+userBean.getLoginZTXXID()+"' ");
			
		}else {
			 params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());//添加渠道分管
		}
		//审核
		if("sh".equals(module)&&!"fsh".equals(firstAudit)){
			moduleName = "退货申请单审核";
			strPvgBwsAudit = PVG_BWS_AUDIT;
//			checkSql=" and BILL_TYPE in('售后退货') ";
			empSql="1=1";
		}
		//初审
		if("fsh".equals(firstAudit)&&"sh".equals(module)){
			moduleName = "退货申请单初审";
			params.put("RETURN_CHANN_ID", userBean.getCHANNS_CHARG());
			strPvgBwsAudit = PVG_BWS_AUDIT0;
//			checkSql=" and BILL_TYPE ='关店退货' ";
			empSql="1=1";
		}		
		params.put("empSql", empSql);
		Map<String,String> chann = prdreturnreqService.getChann(userBean.getCHANN_ID());
		String AREA_SER_ID = chann.get("AREA_SER_ID");
		
		// add by zzb 
		String RECV_CHANN_ID = userBean.getBASE_CHANN_ID();
		//添加是否走原来流程的开关
		if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
		     AUD_TAB_NAME = "DRP_PRD_RETURN_REQ";
		     AUD_TAB_KEY = "u.PRD_RETURN_REQ_ID";
			 AUD_BUSS_STATE = "STATE";
		     AUD_BUSS_TYPE = "DRP_SER_PRD_RETURN_REQ_AUDIT";
		     RECV_CHANN_ID = AREA_SER_ID;
		}else{
	        AUD_TAB_NAME = "DRP_PRD_RETURN_REQ";
	        AUD_TAB_KEY = "u.PRD_RETURN_REQ_ID";
		    AUD_BUSS_STATE = "STATE";
	        AUD_BUSS_TYPE = "DRP_PRD_RETURN_REQ_AUDIT";
		}
		
		
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,strPvgBwsAudit,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		sb.append(checkSql);
		request.setAttribute("moduleName", moduleName);
		if(StringUtil.isEmpty(params.get("addCheck"))){
			params.put("addCheck", " 1=1 ");
		}
		
		
		params.put("QXJBCON", sb.toString());
	    params.put("addQXJB", ParamUtil.getPvgCon("true",module,PVG_BWS,strPvgBwsAudit,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean)+checkSql);
	   
		
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		List list = prdreturnreqService.downQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn = "退货申请单编号,单据类型,退货方编号,退货方名称,收货方编号,收货方名称," +
							  "制单人,制单时间,备注,状态,审核人,审核时间," +
							  "货品编号,货品名称,规格型号,品牌,标准单位,特殊工艺说明," +
							  "退货单价,退货数量,退货金额,原因归类,退货原因,使用时间(月),发货点";
		String tmpContent = "PRD_RETURN_REQ_NO,BILL_TYPE,RETURN_CHANN_NO,RETURN_CHANN_NAME,RECV_CHANN_NO,RECV_CHANN_NAME," +
							"CRE_NAME,CRE_TIME,REMARK,STATE,AUDIT_NAME,AUDIT_TIME," +
							"PRD_NO,PRD_NAME,PRD_SPEC,BRAND,STD_UNIT,SPCL_DTL_REMARK," +
							"RETURN_NUM,RETURN_PRICE,RETURN_AMOUNT,RETURN_RSON_TYPE,RETURN_RSON,USE_TIME,SHIP_POINT_NAME";
		String colType = "string,string,string,string,string,string," +
					     "string,string,string,string,string,string," +
					     "string,string,string,string,string,string," +
					     "number,number,number,string,string,string,string";
		try {
			ExcelUtil .doExport(response, list, "退货申请单明细", tmpContent, tmpContentCn,colType);
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
	    	pvgMap.put("PVG_BWS_AUDIT0",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT0) );
	    	pvgMap.put("PVG_FINISH_CANCLE",QXUtil.checkPvg(userBean, PVG_FINISH_CANCLE) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}