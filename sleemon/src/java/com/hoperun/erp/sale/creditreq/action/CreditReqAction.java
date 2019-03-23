package com.hoperun.erp.sale.creditreq.action;

import java.io.PrintWriter;
import java.util.HashMap;
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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.creditreq.model.CreditReqModel;
import com.hoperun.erp.sale.creditreq.service.CreditReqService;
import com.hoperun.sample.grant.action.GrantAction;
import com.hoperun.sys.model.UserBean;

public class CreditReqAction extends BaseAction {
	
	/**日志**/
	private Logger logger = Logger.getLogger(GrantAction.class);
	/** 权限对象**/
    /** 维护*/
    //维护界面
    //增删改查
    private static String PVG_BWS="FX0021201";
    private static String PVG_EDIT="FX0021202";
    private static String PVG_DELETE="FX0021203";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /**end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0021204";
    private static String PVG_TRACE="FX0021205";
    
    //审核模块
    private static String PVG_BWS_AUDIT="BS0012701";
    private static String PVG_AUDIT_AUDIT="BS0012702";
    private static String PVG_TRACE_AUDIT="BS0012703";
    
    //审批流参数
    private static String AUD_TAB_NAME = "ERP_CREDIT_REQ";//表名
    private static String AUD_TAB_KEY = "CREDIT_REQ_ID"; //主键
	private static String AUD_BUSS_STATE = "STATE";
    private static String AUD_BUSS_TYPE = "ERP_CREDIT_REQ_AUDIT";
	private static String AUD_FLOW_INS = "com.hoperun.sys.service.impl.CreditReqFlowInterfaceImpl";
    /**审批 end**/
	
	private CreditReqService creditReqService;

	
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
		
		logger.info("toFrame方法调用开始");
		
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		String d  = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl", d);
		
		logger.info("toFrame方法调用结束");
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "CREDIT_REQ_ID", params);
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
        ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	   
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		if("wh".equals(module)){
			params.put("LEDGER_ID", userBean.getCHANN_ID());
		}
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//params.put("QXJBCON", getQXString(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = creditReqService.pageQuery(params, pageNo);
		params.put("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	/**
	 * * to 编辑页面初始化
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
    	
		String creditReqID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry=new HashMap<String,String>();
		if(!StringUtil.isEmpty(creditReqID)){
			entry = creditReqService.load(creditReqID);
			request.setAttribute("isNew", false);
		}else{
			request.setAttribute("isNew", true);
			entry.put("REQ_PSON_NAME", userBean.getXM());
            entry.put("REQ_PSON_ID", userBean.getRYXXID());
            entry.put("CHANN_ID", userBean.getCHANN_ID());
            entry.put("CHANN_NO", userBean.getCHANN_NO());
            entry.put("CHANN_NAME", userBean.getCHANN_NAME());
            Map<String,String> chann = creditReqService.loadChann(userBean.getCHANN_ID());
            entry.put("AREA_ID", chann.get("AREA_ID"));
            entry.put("AREA_NO", chann.get("AREA_NO"));
            entry.put("AREA_NAME", chann.get("AREA_NAME"));
            Object OLD_CREDIT_TOTAL=chann.get("INI_CREDIT");
            if(null==OLD_CREDIT_TOTAL){
            	OLD_CREDIT_TOTAL="0";
            }
            entry.put("OLD_CREDIT_TOTAL",OLD_CREDIT_TOTAL.toString());
		}
		
		request.setAttribute("rst", entry);
		request.setAttribute("ZTXXID", userBean.getCHANN_ID());
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("toedit");
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
          
			String creditReqID = ParamUtil.get(request, "creditReqID");
            String jsonData = ParamUtil.get(request, "jsonData");
            CreditReqModel model = new Gson().fromJson(jsonData, new TypeToken <CreditReqModel>() {}.getType());
            
            if(null != model){
            	 creditReqService.txEdit(creditReqID,model, userBean);
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
	 *  查看详细信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String creditReqID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(creditReqID))
        {
			Map<String, String> entry = creditReqService.load(creditReqID);
			request.setAttribute("rst", entry);
        }
		return mapping.findForward("view");
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
			String creditReqID = ParamUtil.get(request, "creditReqID");
			Map <String, String> params = new HashMap <String, String>();
            
            creditReqService.txDelete(creditReqID,userBean);
            creditReqService.clearCaches(params);
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
	    	return  pvgMap;
	   }

	 
	 
	public CreditReqService getCreditReqService() {
		return creditReqService;
	}


	public void setCreditReqService(CreditReqService creditReqService) {
		this.creditReqService = creditReqService;
	}
	

}
