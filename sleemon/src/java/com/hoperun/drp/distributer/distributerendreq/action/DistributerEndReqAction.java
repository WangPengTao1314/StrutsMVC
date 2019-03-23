package com.hoperun.drp.distributer.distributerendreq.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.distributer.distributerendreq.model.DistributerEndReqModel;
import com.hoperun.drp.distributer.distributerendreq.service.DistributerEndReqService;
import com.hoperun.sys.model.UserBean;

/**
 *@module 渠道管理-分销商管理
 *@function   加盟商终止合作申请
 *@version 1.1
 *@author gu_hx
 */
public class DistributerEndReqAction extends BaseAction {

   
	//增删改查
    private static String PVG_BWS="BS0034801";
    private static String PVG_EDIT="BS0034802";
    private static String PVG_DELETE="BS0034803";
	    
	//提交撤销
    private static String PVG_COMMIT_CANCLE="BS0034804";
    //流程跟踪
    private static String PVG_TRACE= "BS0034805";
    //保证金余额
    private static String PVG_DEPOSIT = "BS0034904";
    //账面余额
    private static String PVG_LEFT_ACCT_AMOUNT = "BS0034905";
    //退货金额
    private static String PVG_RETURN_AMOUNT = "BS0034906";
    
    //审核模块                             
    private static String PVG_BWS_AUDIT="BS0034901";
    private static String PVG_AUDIT_AUDIT="BS0034902";
    private static String PVG_TRACE_AUDIT="BS0034903";
	
	//审批流参数
    private static String AUD_TAB_NAME="ERP_DISTRIBUTOR_END_REQ";
    private static String AUD_TAB_KEY="CHANN_END_REQ_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_CHANN_END_REQ_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.drp.distributer.distributerendreq.service.impl.DistributerEndReqFlowInterface";
	
	private   DistributerEndReqService   distributerEndReqService;
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	    }
		//设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
	    return mapping.findForward("frames");
	}
	
	/**
	 * * query List data
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		  //权限级别
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	    }
		Map<String, String> params = new HashMap<String, String>();
				
		ParamUtil.putStr2Map(request, "DISTRIBUTOR_REQ_ID", params);
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_REQ_NO", params);
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_NAME", params);
    	ParamUtil.putStr2Map(request, "DISTRIBUTOR_TYPE", params);
    	ParamUtil.putStr2Map(request, "AREA_NAME_P", params);
    	ParamUtil.putStr2Map(request, "CHANN_NO", params);
    	ParamUtil.putStr2Map(request, "CHANN_NAME", params);
    	ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
    	ParamUtil.putStr2Map(request, "PROV", params);
    	ParamUtil.putStr2Map(request, "CITY", params);
    	ParamUtil.putStr2Map(request, "COUNTY", params);
    	ParamUtil.putStr2Map(request, "PERSON_CON", params);
    	ParamUtil.putStr2Map(request, "STATE", params);
    	
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//渠道分管
	    String CHANNS_CHARG = userBean.getCHANNS_CHARG();
	    params.put("CHANNS_CHARG", CHANNS_CHARG);
		// 字段排序
		ParamUtil.setOrderField(request, params, "c.CRE_TIME", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = distributerEndReqService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("page", page);
        ParamUtil.setCommAttributeEx(request);
        return mapping.findForward("list");
	}
	
	/**
	 * 编辑框架页面.
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toEditFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
    		throw new ServiceException("对不起，您没有权限 !");
        }
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("pvg",setPvg(userBean));
		return mapping.findForward("editFrame");
	}
	
	/**
	 * * to 编辑页面初始化
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
    		throw new ServiceException("对不起，您没有权限 !");
        }	
		String DISTRIBUTOR_REQ_ID = ParamUtil.get(request, "selRowId");
		String module = ParamUtil.get(request, "module");
		Map<String,String> entry = new HashMap<String,String>();
		if(!StringUtil.isEmpty(DISTRIBUTOR_REQ_ID)){
			entry = distributerEndReqService.load(DISTRIBUTOR_REQ_ID);
		}
				
		request.setAttribute("userName", userBean.getXM());
		request.setAttribute("rst", entry);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		
		String toPage = "toedit";
		if(!StringUtil.isEmpty(module) && "sh".equals(module)){
			toPage="toeditCf";
		}
		
		return mapping.findForward(toPage);
	}
	 
	/**
	 * 编辑.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request  the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);        
        String jsonResult = jsonResult();
        try{
        	String jsonData = ParamUtil.get(request, "jsonData");
        	DistributerEndReqModel model = new DistributerEndReqModel();
            if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, 
                		new TypeToken <DistributerEndReqModel>() {}.getType());
            }
            distributerEndReqService.txEdit(model,userBean);
            jsonResult = jsonResult(true, "保存成功");
            
        }catch(Exception e){
        	jsonResult = jsonResult(false, "保存失败");
        	e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
	 * 编辑框架页面.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toDetailFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		return mapping.findForward("detailFrame");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG
					&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
							.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			String DISTRIBUTOR_REQ_ID = ParamUtil.get(request, "selRowId");
			
			Map<String,String> entry = distributerEndReqService.load(DISTRIBUTOR_REQ_ID);
			request.setAttribute("rst", entry);			
			//request.setAttribute("nFlag", checkDistributerType(entry));
			
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(DISTRIBUTOR_REQ_ID);
		    request.setAttribute("flowInfoList", flowInfoList);
		    
			return mapping.findForward("detail");
	}
	
	 /**
     * 删除.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	
    	UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String CHANN_END_REQ_ID = ParamUtil.get(request, "CHANN_END_REQ_ID");

        if (StringUtils.isNotEmpty(CHANN_END_REQ_ID)) {
            try {
            	distributerEndReqService.txDelete(CHANN_END_REQ_ID, userBean);            	
            } catch (RuntimeException e) {
                jsonResult = jsonResult(false, "删除失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
	 * 审核前的校验.
	 * @param mapping the mapping
	 * @param form the form
	 * @param request  the request
	 * @param response the response
	 */
	public void toAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_AUDIT_AUDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);        
        String jsonResult = jsonResult();
        String CHANN_END_REQ_ID = ParamUtil.get(request, "CHANN_END_REQ_ID");

        try {
        	if (StringUtils.isNotEmpty(CHANN_END_REQ_ID)) {
        		Map<String,String> entry = distributerEndReqService.load(CHANN_END_REQ_ID);  
        		String DEPOSIT_CONFIRM = entry.get("DEPOSIT_CONFIRM") == null ? null :String.valueOf(entry.get("DEPOSIT_CONFIRM"));
        		String LEFT_ACCT_AMOUNT_CONFIRM = entry.get("LEFT_ACCT_AMOUNT_CONFIRM") == null ? null :String.valueOf(entry.get("LEFT_ACCT_AMOUNT_CONFIRM"));
        		String RETURN_AMOUNT_CONFIRM = entry.get("RETURN_AMOUNT_CONFIRM") == null ? null :String.valueOf(entry.get("RETURN_AMOUNT_CONFIRM"));
        		
        		List<String>   li = distributerEndReqService.queryGZZXXID();
        		String RYXXID = userBean.getRYXXID().toString();
        		int count = 0;
        		if(li.size() !=0){
        		for(int i=0;i<li.size();i++){
        			count  = distributerEndReqService.queryZW(RYXXID,String.valueOf(li.get(i)));
        		}
        		if(count !=0){
	        		if(StringUtil.isEmpty(DEPOSIT_CONFIRM)||
	            			StringUtil.isEmpty(LEFT_ACCT_AMOUNT_CONFIRM)||
	            			StringUtil.isEmpty(RETURN_AMOUNT_CONFIRM)){
	            		jsonResult = jsonResult(false, "请财务确认实际金额!");
	            	}
        		 }
        		}
            }
        	
        } catch (RuntimeException e) {
            jsonResult = jsonResult(false, "查询失败");
        }finally{
        	if (null != writer) {
                writer.write(jsonResult);
                writer.close();
            }
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
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	        pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
	        
	        pvgMap.put("PVG_DEPOSIT", QXUtil.checkPvg(userBean, PVG_DEPOSIT));
	        pvgMap.put("PVG_LEFT_ACCT_AMOUNT", QXUtil.checkPvg(userBean, PVG_LEFT_ACCT_AMOUNT));
	        pvgMap.put("PVG_RETURN_AMOUNT", QXUtil.checkPvg(userBean, PVG_RETURN_AMOUNT));
	        
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	 
	
	
	public DistributerEndReqService getDistributerEndReqService() {
		return distributerEndReqService;
	}

	public void setDistributerEndReqService(
			DistributerEndReqService distributerEndReqService) {
		this.distributerEndReqService = distributerEndReqService;
	}
}
