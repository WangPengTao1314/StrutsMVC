/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：TempCreditReqAction.java
 */
package com.hoperun.erp.sale.tempcreditreq.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.erp.sale.tempcreditreq.model.TempCreditReqModel;
import com.hoperun.erp.sale.tempcreditreq.service.TempCreditReqService;
import com.hoperun.sys.model.UserBean;
/**
 * The Class TempCreditReqAction.
 * 
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */
public class TempCreditReqAction extends BaseAction {
	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(TempCreditReqAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0021401";
    private static String PVG_EDIT="FX0021402";
    private static String PVG_DELETE="FX0021403";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="STATE";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0021404";
    private static String PVG_TRACE="FX0021405";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0013101";
    private static String PVG_AUDIT_AUDIT="BS0013102";
    private static String PVG_TRACE_AUDIT="BS0013103";
    private static String PVG_TEMP_CREDIT_EDIT="BS0013105";
    //关闭
    private static String  PVG_CLOSE = "BS0013104";
    
    //审批流参数
    private static String AUD_TAB_NAME="ERP_TEMP_CREDIT_REQ";//表名
    private static String AUD_TAB_KEY="TEMP_CREDIT_REQ_ID";//主键
    private static String AUD_BUSS_TYPE="ERP_TEMP_CREDIT_REQ_AUDIT";
    private static String AUD_FLOW_INS="com.hoperun.erp.sale.tempcreditreq.service.impl.TempCreditReqFlowInterface";
	
	private TempCreditReqService tempCreditReqService;
	
	/**
	 * @return the tempCreditReqService
	 */
	public TempCreditReqService getTempCreditReqService() {
		return tempCreditReqService;
	}

	/**
	 * @param tempCreditReqService the tempCreditReqService to set
	 */
	public void setTempCreditReqService(TempCreditReqService tempCreditReqService) {
		this.tempCreditReqService = tempCreditReqService;
	}

	/**
	 * 临时额度调整申请框架
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
		
	}
	
	/**
     * 查询结果列表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map<String, String> params = new HashMap<String, String>();
    	
	    ParamUtil.putStr2Map(request, "TEMP_CREDIT_REQ_ID", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
	    
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		if(!"1".equals(userBean.getIS_DRP_LEDGER())){
			//区域分管
			params.put("CHANNS_CHARG",userBean.getCHANNS_CHARG());
		}else{
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
		}
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		//张大姐要看上一个审核人，查角色是总部_客服部的用户
		String JSBHS = userBean.getJSBHS();
		String JSBH = "XTJS0002";//总部_客服部
		//总部_客服总监 zhangdajie审签
		if("'XTJS0022'".indexOf(JSBHS)>-1){
			params.put("JSBH", JSBH);//总部_客服部
		}
		
		//财务副总裁 chengbin总审签
		if("'XTJS0015'".indexOf(JSBHS)>-1){
			JSBH = "XTJS0022";
		}
		params.put("JSBH", JSBH);
		  
		
    	// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	params.put("module", module);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = tempCreditReqService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("page", page);
        return mapping.findForward("list");
    }
    
    /**
     * 查看详细信息.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String TEMP_CREDIT_REQ_ID = ParamUtil.get(request, "TEMP_CREDIT_REQ_ID");
        Map<String,String> entry = tempCreditReqService.load(TEMP_CREDIT_REQ_ID);
        request.setAttribute("rst", entry);
        return mapping.findForward("view");
    }
    
    /**
     * 点击新增或修改按钮跳转到编辑页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String TEMP_CREDIT_REQ_ID = ParamUtil.get(request, "TEMP_CREDIT_REQ_ID");
        Map<String,String> entry=new HashMap<String,String>();
        if (StringUtils.isNotEmpty(TEMP_CREDIT_REQ_ID)) {
        	entry= tempCreditReqService.load(TEMP_CREDIT_REQ_ID);
            request.setAttribute("rst", entry);
        }else{
        	entry.put("REQ_PSON_NAME", userBean.getXM());
            entry.put("REQ_PSON_ID", userBean.getRYXXID());
            entry.put("CHANN_ID", userBean.getCHANN_ID());
            entry.put("CHANN_NO", userBean.getCHANN_NO());
            entry.put("CHANN_NAME", userBean.getCHANN_NAME());
            Map<String,String> chann = this.tempCreditReqService.loadChann(userBean.getCHANN_ID());
            entry.put("AREA_ID", chann.get("AREA_ID"));
            entry.put("AREA_NO", chann.get("AREA_NO"));
            entry.put("AREA_NAME", chann.get("AREA_NAME"));
            request.setAttribute("rst", entry);
        }
        request.setAttribute("ZTXXID", userBean.getCHANN_ID());
        return mapping.findForward("toedit");
    }
    /**
     * 编辑.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String jsonData = ParamUtil.get(request, "jsonData");
        TempCreditReqModel model = new TempCreditReqModel();
        String TEMP_CREDIT_REQ_ID = ParamUtil.get(request, "TEMP_CREDIT_REQ_ID");
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <TempCreditReqModel>() {
            }.getType());
        }
    	try {
    		tempCreditReqService.txEdit(TEMP_CREDIT_REQ_ID, model, userBean);
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "保存失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    
    /**
     * 删除
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void delete(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response){
    	// TODO Auto-generated method stub
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String TEMP_CREDIT_REQ_ID = ParamUtil.get(request, "TEMP_CREDIT_REQ_ID");
        if (StringUtils.isNotEmpty(TEMP_CREDIT_REQ_ID)) {
            try {
                Map <String, String> params = new HashMap <String, String>();
                params.put("TEMP_CREDIT_REQ_ID", TEMP_CREDIT_REQ_ID);
                params.put("UPDATOR", userBean.getXTYHID());
    		    params.put("UPD_NAME", userBean.getYHM());
    		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
    		    tempCreditReqService.txDelete(TEMP_CREDIT_REQ_ID, userBean);
    		    tempCreditReqService.clearCaches(TEMP_CREDIT_REQ_ID);
            } catch (Exception ex) {
            	ex.printStackTrace();
                jsonResult = jsonResult(false, "删除失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
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
	    	pvgMap.put("PVG_CLOSE",QXUtil.checkPvg(userBean, PVG_CLOSE) );
	    	pvgMap.put("PVG_TEMP_CREDIT_EDIT",QXUtil.checkPvg(userBean, PVG_TEMP_CREDIT_EDIT) );
	    	
	    	pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
	    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	 /**
	  * 审核通过
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  */
	 public void toAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_TEMP_CREDIT_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        logger.info("按钮修改为审核通过单条记录开始");
	        try {
	        	Map<String,String> map=new HashMap<String,String>();
	        	map.put("TEMP_CREDIT_REQ_ID", ParamUtil.utf8Decoder(request, "TEMP_CREDIT_REQ_ID"));
	        	map.put("TEMP_CREDIT_VALDT", ParamUtil.utf8Decoder(request, "TEMP_CREDIT_VALDT"));
	        	tempCreditReqService.upTEMP_CREDIT_VALDT(map);
	        	jsonResult = jsonResult(true,"");
	        }catch(ServiceException s){
	        	jsonResult = jsonResult(false, s.getMessage());
	        	s.printStackTrace();
	        } catch (Exception e) {
	           jsonResult = jsonResult(false, "审核失败");
	           logger.info(e);
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    }
	 
	 
	 
	 /**
	     * 关闭
	     * 
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void toClose(ActionMapping mapping, ActionForm form, 
	    		HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean =  ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean,PVG_CLOSE)){
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	logger.info("编辑开始");
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        String TEMP_CREDIT_REQ_ID = ParamUtil.get(request, "TEMP_CREDIT_REQ_ID");
	    	try {
	    		tempCreditReqService.txClose(TEMP_CREDIT_REQ_ID, userBean);
	    		jsonResult = jsonResult(true, "操作成功");
	        } catch (RuntimeException e) {
	        	e.printStackTrace();
	            jsonResult = jsonResult(false, "操作失败");
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    }
	    
}
