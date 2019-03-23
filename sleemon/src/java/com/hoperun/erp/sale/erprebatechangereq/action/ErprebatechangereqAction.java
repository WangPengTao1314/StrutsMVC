/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：TempCreditReqAction.java
 */
package com.hoperun.erp.sale.erprebatechangereq.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
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
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.erprebatechangereq.model.ErprebatechangereqModel;
import com.hoperun.erp.sale.erprebatechangereq.service.ErprebatechangereqService;
import com.hoperun.sys.model.UserBean;

public class ErprebatechangereqAction extends BaseAction {
	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(ErprebatechangereqAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0023301";
    private static String PVG_EDIT="BS0023302";
    private static String PVG_DELETE="BS0023303";
    private static String PVG_EXPORT="BS0023306";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="STATE";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0023304";
    private static String PVG_TRACE="BS0023305";
    private static String PVG_BATCHCOMMIT_CANCLE="BS0023307";
    private static String PVG_COPY="BS0023308";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0023401";
    private static String PVG_AUDIT_AUDIT="BS0023402";
    private static String PVG_TRACE_AUDIT="BS0023403";
    private static String PVG_BATCHAUDIT_AUDIT="BS0023404";
    private static String PVG_CANCEL_AUDIT="BS0023405";
    private static String PVG_BATCHCANCEL_AUDIT="BS0023406";
    
    //关闭
    private static String  PVG_CLOSE = "";
    
    //审批流参数
    private static String AUD_TAB_NAME="ERP_REBATE_CHANGE_REQ";//表名
    private static String AUD_TAB_KEY="REBATE_CHANGE_REQ_ID";//主键
    private static String AUD_BUSS_TYPE="ERP_REBATE_CHANGE_REQ_AUDIT";
    private static String AUD_FLOW_INS="com.hoperun.erp.sale.erprebatechangereq.service.impl.ErprebatechangereqFlowInterface";
	private ErprebatechangereqService ErprebatechangereqService;
	

	public ErprebatechangereqService getErprebatechangereqService() {
		return ErprebatechangereqService;
	}

	public void setErprebatechangereqService(
			ErprebatechangereqService erprebatechangereqService) {
		ErprebatechangereqService = erprebatechangereqService;
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
    	
	    ParamUtil.putStr2Map(request, "REBATE_CHANGE_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "REQ_PSON_NAME", params);
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		if("true".equals(search)){
			String STATE = ParamUtil.get(request,"STATE");
			if(!"".equals(STATE)){
				params.put("STATE", "'"+STATE+"'");
			}
		}else{
			if("sh".equals(module)){
				params.put("STATE", "'提交'");
			}else{
				params.put("STATE", "'未提交','撤销','否决'");
			}
		}
		//权限级别和审批流的封装以及状态的封装
		if("sh".equals(module)){
			params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_BWS_AUDIT,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			
		}else{
			params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			
		}
    	// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	params.put("module", module);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = ErprebatechangereqService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
    	request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
    	request.setAttribute("search", search);
        request.setAttribute("page", page);
        return mapping.findForward("list");
    }
    
    /**批量提交页
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toBatchList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map<String, String> params = new HashMap<String, String>();
    	ParamUtil.putStr2Map(request, "CHANN_NO", params);
 	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
 	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
 	    ParamUtil.putStr2Map(request, "REBATE_TYPE", params);
    	String module = "wh";
    	params.put("module", module);
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon("",module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    	// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = ErprebatechangereqService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
    	request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
        request.setAttribute("page", page);
        return mapping.findForward("toBachlist");
    }   
    
    
    /**批量弃审页
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toBatchCancelAuditList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map<String, String> params = new HashMap<String, String>();
    	ParamUtil.putStr2Map(request, "CHANN_NO", params);
 	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
 	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
 	    ParamUtil.putStr2Map(request, "REBATE_TYPE", params);    	
    	String module = "sh";
    	params.put("module", module);
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("STATE", "'审核通过'");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon("","",PVG_BWS_AUDIT,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    	// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = ErprebatechangereqService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
    	request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
        request.setAttribute("page", page);
        return mapping.findForward("toBachCancelAuditlist");
    } 
    
    
    /**提交
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void rebateCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String REBATE_CHANGE_REQ_ID = ParamUtil.get(request, "REBATE_CHANGE_REQ_ID");
        try {
            if(StringUtil.isEmpty(REBATE_CHANGE_REQ_ID)){
                 throw new Exception("请选择一条明细!");
            }
    		ErprebatechangereqService.txBatchCommit(request,AUD_FLOW_INS,AUD_BUSS_TYPE,REBATE_CHANGE_REQ_ID);
        } catch (Exception e) {
            jsonResult = jsonResult(false, e.getMessage());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }	
    }
    
    /**复制记录
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void copyRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String REBATE_CHANGE_REQ_ID = ParamUtil.get(request, "REBATE_CHANGE_REQ_ID");
        try {
            if(StringUtil.isEmpty(REBATE_CHANGE_REQ_ID)){
                 throw new Exception("请选择一条明细!");
            }
    		String  REBATE_CHANGE_REQ_NO = ErprebatechangereqService.txCopyRecord(request,REBATE_CHANGE_REQ_ID);
    		 jsonResult = jsonResult(true, "复制成功,单号:"+REBATE_CHANGE_REQ_NO);
        } catch (Exception e) {
            jsonResult = jsonResult(false, e.getMessage());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }	
    }
    
    
    
    
    /**审核
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void rebateAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String REBATE_CHANGE_REQ_ID = ParamUtil.get(request, "REBATE_CHANGE_REQ_ID");
        try {
            if(StringUtil.isEmpty(REBATE_CHANGE_REQ_ID)){
                 throw new Exception("请选择一条明细!");
            }
    		ErprebatechangereqService.txRebateAudit(request,AUD_FLOW_INS,REBATE_CHANGE_REQ_ID);
        } catch (Exception e) {
            jsonResult = jsonResult(false, e.getMessage());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }	
    }
    
    /**弃审
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void cancelAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String REBATE_CHANGE_REQ_ID = ParamUtil.get(request, "REBATE_CHANGE_REQ_ID");
        try {
            if(StringUtil.isEmpty(REBATE_CHANGE_REQ_ID)){
                 throw new Exception("请选择一条明细!");
            }
    		ErprebatechangereqService.txCancelAudit(request,REBATE_CHANGE_REQ_ID);
        } catch (Exception e) {
            jsonResult = jsonResult(false, e.getMessage());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }	
    }
    
    
        
    
    
    /**撤销
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void rebateRevoke(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String REBATE_CHANGE_REQ_ID = ParamUtil.get(request, "REBATE_CHANGE_REQ_ID");
        try {
            if(StringUtil.isEmpty(REBATE_CHANGE_REQ_ID)){
                 throw new Exception("请选择一条明细!");
            }
    		ErprebatechangereqService.txRebateRevoke(request,REBATE_CHANGE_REQ_ID);
        } catch (Exception e) {
            jsonResult = jsonResult(false, e.getMessage());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }	
    }
    
    
    
    /**批量提交返利变更单
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void batchCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String REBATE_CHANGE_REQ_IDS = ParamUtil.get(request, "REBATE_CHANGE_REQ_IDS");
        try {
            if(StringUtil.isEmpty(REBATE_CHANGE_REQ_IDS)){
                 throw new Exception("请选择一条明细!");
            }
    		ErprebatechangereqService.txBatchCommit(request,AUD_FLOW_INS,AUD_BUSS_TYPE,REBATE_CHANGE_REQ_IDS);
        } catch (Exception e) {
            jsonResult = jsonResult(false, e.getMessage());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    

    /**批量审核返利变更单
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void batchAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String REBATE_CHANGE_REQ_IDS = ParamUtil.get(request, "REBATE_CHANGE_REQ_IDS");
        try {
            if(StringUtil.isEmpty(REBATE_CHANGE_REQ_IDS)){
                 throw new Exception("请选择一条明细!");
            }
    		ErprebatechangereqService.txBatchAudit(request,AUD_FLOW_INS,AUD_BUSS_TYPE,REBATE_CHANGE_REQ_IDS);
        } catch (Exception e) {
            jsonResult = jsonResult(false, e.getMessage());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**批量审核返利变更单
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void batchCancelAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String REBATE_CHANGE_REQ_IDS = ParamUtil.get(request, "REBATE_CHANGE_REQ_IDS");
        try {
            if(StringUtil.isEmpty(REBATE_CHANGE_REQ_IDS)){
                 throw new Exception("请选择一条明细!");
            }
    		ErprebatechangereqService.txCancelAudit(request,REBATE_CHANGE_REQ_IDS);
        } catch (Exception e) {
            jsonResult = jsonResult(false, e.getMessage());
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    /**批量审核页
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward toBatchAuditList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map<String, String> params = new HashMap<String, String>();
    	ParamUtil.putStr2Map(request, "CHANN_NO", params);
 	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
 	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
 	    ParamUtil.putStr2Map(request, "REBATE_TYPE", params);   
    	String module = "sh";
    	params.put("module", module);
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("STATE", "'提交'");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon("","",PVG_BWS_AUDIT,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    	// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = ErprebatechangereqService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
    	request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
        request.setAttribute("page", page);
        return mapping.findForward("toBachAuditlist");
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
        String REBATE_CHANGE_REQ_ID = ParamUtil.get(request, "REBATE_CHANGE_REQ_ID");
        Map<String,String> entry = ErprebatechangereqService.load(REBATE_CHANGE_REQ_ID);
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
        String REBATE_CHANGE_REQ_ID = ParamUtil.get(request, "REBATE_CHANGE_REQ_ID");
        String IS_COPY = ParamUtil.get(request, "IS_COPY");
        Map<String,String> entry=new HashMap<String,String>();
        if("TRUE".equals(IS_COPY)){
        	entry= ErprebatechangereqService.load(REBATE_CHANGE_REQ_ID);
        	entry.remove("REBATE_CHANGE_REQ_ID");
        	entry.remove("REBATE_CHANGE_REQ_NO");
        	request.setAttribute("IS_COPY", "TRUE");
        }else if (StringUtils.isNotEmpty(REBATE_CHANGE_REQ_ID)) {
        	entry= ErprebatechangereqService.load(REBATE_CHANGE_REQ_ID);
        }else{
        	entry.put("REQ_PSON_ID", userBean.getRYXXID());
        	entry.put("REQ_PSON_NAME", userBean.getXM());
        }
        request.setAttribute("rst", entry);
        request.setAttribute("ZTXXID", userBean.getCHANN_ID());
        request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
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
        ErprebatechangereqModel model = new ErprebatechangereqModel();
        String REBATE_CHANGE_REQ_ID = ParamUtil.get(request, "REBATE_CHANGE_REQ_ID");
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <ErprebatechangereqModel>() {
            }.getType());
        }
    	try {
    		ErprebatechangereqService.txEdit(REBATE_CHANGE_REQ_ID, model, userBean);
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
     * 查询当前返利
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void getRebate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	logger.info("编辑开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
    	try {
    		Map<String,String> map=ErprebatechangereqService.getRebate(CHANN_ID);
    		jsonResult = new Gson().toJson(new Result(true, map, ""));
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "查询失败");
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
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String REBATE_CHANGE_REQ_ID = ParamUtil.get(request, "REBATE_CHANGE_REQ_ID");
        if (StringUtils.isNotEmpty(REBATE_CHANGE_REQ_ID)) {
            try {
                Map <String, String> params = new HashMap <String, String>();
                params.put("REBATE_CHANGE_REQ_ID", REBATE_CHANGE_REQ_ID);
                params.put("UPDATOR", userBean.getXTYHID());
    		    params.put("UPD_NAME", userBean.getYHM());
    		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
    		    ErprebatechangereqService.txDelete(REBATE_CHANGE_REQ_ID, userBean);
    		    ErprebatechangereqService.clearCaches(REBATE_CHANGE_REQ_ID);
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
    
  //导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,String> params = new HashMap<String,String>();
		ParamUtil.putStr2Map(request, "REBATE_CHANGE_REQ_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "REQ_PSON_NAME", params);
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    	// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	params.put("module", module);
    	ParamUtil.putStr2Map(request, "pageSize", params);
        List list=ErprebatechangereqService.downQuery(params);
        //excel数据上列显示的列明
        String tmpContentCn="返利额度变更单编号,渠道编号,渠道名称,渠道类型,单据类型,返利类别,申请人,变更额度,变更前返利,变更后返利,制单人,制单时间,制单部门,状态 ";
        String tmpContent="REBATE_CHANGE_REQ_NO,CHANN_NO,CHANN_NAME,CHANN_TYPE,BILL_TYPE,REBATE_TYPE,REQ_PSON_NAME,CHANGE_REBATE,CHANGE_REBATE_OLD,CHANGE_REBATE_NEW,CRE_NAME,CRE_TIME,DEPT_NAME,STATE";
        try {
			ExcelUtil.doExport(response, list, "返利额度变更", tmpContent, tmpContentCn);
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
	    	pvgMap.put("PVG_CLOSE",QXUtil.checkPvg(userBean, PVG_CLOSE) );
	    	pvgMap.put("PVG_EXPORT",QXUtil.checkPvg(userBean, PVG_EXPORT) );
	    	pvgMap.put("PVG_BATCHCOMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_BATCHCOMMIT_CANCLE) );
	    	pvgMap.put("PVG_BATCHAUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_BATCHAUDIT_AUDIT) );
	    	pvgMap.put("PVG_CANCEL_AUDIT",QXUtil.checkPvg(userBean, PVG_CANCEL_AUDIT) );
	    	pvgMap.put("PVG_BATCHCANCEL_AUDIT",QXUtil.checkPvg(userBean, PVG_BATCHCANCEL_AUDIT) );
	    	pvgMap.put("PVG_COPY",QXUtil.checkPvg(userBean, PVG_COPY) );
	    	
	    	
	    	
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
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_AUDIT_AUDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        logger.info("按钮修改为审核通过单条记录开始");
	        try {
	        	Map<String,String> map=new HashMap<String,String>();
	        	map.put("REBATE_CHANGE_REQ_ID", ParamUtil.utf8Decoder(request, "REBATE_CHANGE_REQ_ID"));
	        	map.put("TEMP_CREDIT_VALDT", ParamUtil.utf8Decoder(request, "TEMP_CREDIT_VALDT"));
	        	ErprebatechangereqService.upTEMP_CREDIT_VALDT(map);
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
	        String REBATE_CHANGE_REQ_ID = ParamUtil.get(request, "REBATE_CHANGE_REQ_ID");
	    	try {
	    		ErprebatechangereqService.txClose(REBATE_CHANGE_REQ_ID, userBean);
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
