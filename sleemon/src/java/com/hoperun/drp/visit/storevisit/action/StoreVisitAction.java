package com.hoperun.drp.visit.storevisit.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.visit.storevisit.model.StoreVisitDtlModel;
import com.hoperun.drp.visit.storevisit.model.StoreVisitModel;
import com.hoperun.drp.visit.storevisit.service.StoreVisitService;
import com.hoperun.sys.model.UserBean;

public class StoreVisitAction extends BaseAction {
    
	    private   StoreVisitService   storevisitService;

		//增删改查
	    private static String PVG_BWS="BS0033001";
	    private static String PVG_EDIT="BS0033002";
	    private static String PVG_DELETE="BS0033003";
	    
	    //提交撤销
	    private static String PVG_COMMIT_CANCLE="BS0033004";
	    //流程跟踪
	    private static String PVG_TRACE= "BS0033005";
	    //审核模块                             
	    private static String PVG_BWS_AUDIT="BS0033301";
	    private static String PVG_AUDIT_AUDIT="BS0033302";
	    private static String PVG_TRACE_AUDIT="BS0033303";
		
		//审批流参数
	    private static String AUD_TAB_NAME="ERP_STORE_VISIT";
	    private static String AUD_TAB_KEY="STORE_VISIT_ID";
		private static String AUD_BUSS_STATE="STATE";
	    private static String AUD_BUSS_TYPE="ERP_STORE_VISIT_AUDIT";
		private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
		
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
			request.setAttribute("module",  ParamUtil.get(request,"module"));
			return mapping.findForward("frames");
		}
		
		/**
	     * 拓展拜访维护列表 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

	         //权限级别
	         UserBean userBean =  ParamUtil.getUserBean(request);
			 if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
		    		throw new ServiceException("对不起，您没有权限 !");
		     }
	         Map <String, String> params = new TreeMap <String, String>();
	         ParamUtil.putStr2Map(request, "STORE_VISIT_NO", params);
	         ParamUtil.putStr2Map(request, "TERM_NO", params);
	         ParamUtil.putStr2Map(request, "TERM_NAME", params);
	         ParamUtil.putStr2Map(request, "AREA_NAME", params);
	         ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	         ParamUtil.putStr2Map(request, "VISIT_PEOPLE", params);
	         ParamUtil.putStr2Map(request, "STATE", params);
	         ParamUtil.putStr2Map(request, "SVISIT_DATE", params);
	         ParamUtil.putStr2Map(request, "EVISIT_DATE", params);
	         ParamUtil.putStr2Map(request, "pageSize", params);
	         
	         String search = ParamUtil.get(request,"search");
	 	     String module = ParamUtil.get(request,"module");
	 	     String STATE = ParamUtil.get(request,"STATE");
			 StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		     //权限级别和审批流的封装
			 if(module.equals("wh") || module.equals("")){
				if(!StringUtil.isEmpty(search) ){
					if(!StringUtil.isEmpty(STATE)){
						params.put("STATE", STATE);
					}
				}else{
				   qx.append(" and STATE in('未提交','撤销','否决') ");
				}
				 params.put("QXJBCON",qx.toString());
			 }
			 if(module.equals("sh")){
				String strSql = qx.toString().substring(qx.toString().indexOf("(")+1,qx.toString().lastIndexOf(")"));
				String newStr = "("+strSql.replaceAll("u.CREATOR", "'"+userBean.getRYXXID()+"'")+") temp ";
				StringBuffer buffer = new StringBuffer();
				buffer.append(newStr);
				buffer.append("WHERE u.DEL_FLAG = 0 ");
				if(!StringUtil.isEmpty(search) ){
					if(!StringUtil.isEmpty(STATE)){
						//params.put("STATE", "("+STATE+")");
						buffer.append("and STATE = '"+STATE+"'");
					}else{
						//params.put("STATE"," ('提交','撤销','否决','审核通过') ");
						buffer.append("and STATE IN ('提交','撤销','否决','审核通过') ");
					}
				}else{
					//params.put("STATE"," ('提交') ");
					  buffer.append("and STATE = '提交'");
				}
				params.put("QXJBCON",buffer.toString());
			 }
 		     IListPage page = null;
		     request.setAttribute("module",module);
		     
		     //渠道分管
		     String CHANNS_CHARG = userBean.getCHANNS_CHARG();
			 params.put("CHANNS_CHARG", CHANNS_CHARG);
	 	     
			 int pageNo = ParamUtil.getInt(request, "pageNo", 1);
			 if(module.equals("sh")){
	 		   page = storevisitService.pageQuery(params, pageNo);
			 } else{
			   page = storevisitService.pageQueryWH(params, pageNo);
			 }
	 		 request.setAttribute("pvg",setPvg(userBean));
	 		 request.setAttribute("search", search);
	 		 request.setAttribute("module", module);
	         request.setAttribute("params", params);
	         request.setAttribute("page", page);  
	         return mapping.findForward("list");
	    }
	    
	    /**
	     * 拓展拜访表明细列表 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	 UserBean userBean =  ParamUtil.getUserBean(request);
			 if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
		    		throw new ServiceException("对不起，您没有权限 !");
		     }
	    	 String STORE_VISIT_ID = ParamUtil.get(request, "STORE_VISIT_ID");
	         if (StringUtils.isNotEmpty(STORE_VISIT_ID)) {
	             List<StoreVisitDtlModel> result = storevisitService.childQueryT(STORE_VISIT_ID);
	             for(int i=0;i<result.size();i++){
	            	 StoreVisitDtlModel  model = (StoreVisitDtlModel)result.get(i);
	            	 model.setSTORE_VISIT_DTL_ID(model.getSTORE_VISIT_DTL_ID());
	            	 String  PRO_NAME = storevisitService.queryPro(model.getPRO_NAME());
	            	 model.setPRO_NAME(PRO_NAME);
	            	 String  MAIN_TYPE= storevisitService.queryPro(model.getMAIN_TYPE());
	            	 model.setMAIN_TYPE(MAIN_TYPE);
	            	 model.setSIT_ANALYSIS(model.getSIT_ANALYSIS());
	            	 model.setACTION_PLAN(model.getACTION_PLAN());
	            	 model.setCOMPLETE_TIME(model.getCOMPLETE_TIME());
	            	 model.setOTHER_INFO(model.getOTHER_INFO());
	             }
	             request.setAttribute("rst", result);
	             System.out.println(request.getAttribute("rst"));
	         }
	         //为空直接跳转显示页面，而不是错误提示。
	        return mapping.findForward("childlist");
	    }
	    
	    public ActionForward childListSH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	   UserBean userBean =  ParamUtil.getUserBean(request);
		   if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	       }
	   	   String STORE_VISIT_ID = ParamUtil.get(request, "STORE_VISIT_ID");
	        if (StringUtils.isNotEmpty(STORE_VISIT_ID)) {
	            List<StoreVisitDtlModel> result = storevisitService.childQueryT(STORE_VISIT_ID);
	            for(int i=0;i<result.size();i++){
	            	 StoreVisitDtlModel  model = (StoreVisitDtlModel)result.get(i);
	            	 model.setSTORE_VISIT_DTL_ID(model.getSTORE_VISIT_DTL_ID());
	            	 String  PRO_NAME = storevisitService.queryPro(model.getPRO_NAME());
	            	 model.setPRO_NAME(PRO_NAME);
	            	 String  MAIN_TYPE= storevisitService.queryPro(model.getMAIN_TYPE());
	            	 model.setMAIN_TYPE(MAIN_TYPE);
	            	 model.setSIT_ANALYSIS(model.getSIT_ANALYSIS());
	            	 model.setACTION_PLAN(model.getACTION_PLAN());
	            	 model.setCOMPLETE_TIME(model.getCOMPLETE_TIME());
	            	 model.setOTHER_INFO(model.getOTHER_INFO());
	             }
	            request.setAttribute("rst", result);
	            System.out.println(request.getAttribute("rst"));
	        }
	        //为空直接跳转显示页面，而不是错误提示。
	       return mapping.findForward("childlistSH");
	   }
	    
	    /**
	     * 拓展拜访表编辑框架页面.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	String STORE_VISIT_ID = ParamUtil.get(request, "STORE_VISIT_ID");
	        request.setAttribute("STORE_VISIT_ID", STORE_VISIT_ID);
	        String module  = ParamUtil.get(request, "module");
	        request.setAttribute("module", module);
	    	if(!STORE_VISIT_ID.equals("")){
	    	 return mapping.findForward("editFrame_T");
	    	} else {
    		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	     String time = sdf.format(new Date());
    	     request.setAttribute("APPLY_DATE",time);
    	     request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
	         return mapping.findForward("toedit");
	    	}
	    }
	    
	    
	    /**
		 * 新增.
		 * @param mapping the mapping
		 * @param form the form
		 * @param request the request
		 * @param response the response
		 * @return the action forward
		 */
		public ActionForward toEdit(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

			UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
			PrintWriter writer = getWriter(response);
		    String jsonResult = jsonResult();
		    String currentTime = "ZXSQ"+System.currentTimeMillis();     //自动申请装修申请单号
		    String userName  = userBean.getXM();                        //申请人
		    String STORE_VISIT_ID    = ParamUtil.get(request, "STORE_VISIT_ID");
		    if (StringUtils.isNotEmpty(STORE_VISIT_ID)) {
				Map entry = storevisitService.loadByConfId(STORE_VISIT_ID);
				request.setAttribute("rst", entry);
				request.setAttribute("STORE_VISIT_ID", STORE_VISIT_ID);
			}
		    Map<String,String> params = new HashMap<String,String>();
		    String search = ParamUtil.get(request,"search");
			String module = ParamUtil.get(request,"module");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	    String time = sdf.format(new Date());
    	    request.setAttribute("APPLY_DATE",time);
		    request.setAttribute("userName",userName);
		    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		    request.setAttribute("date", time);
		    request.setAttribute("state", "未提交");
		    // 获取当前登录人员姓名id
		    Map<String,String> entry ;
	  	    entry=new HashMap<String, String>();
	  	    entry.put("APPLY_PERSON", userBean.getXM());
	  	    entry.put("APPLY_ID",     userBean.getRYXXID());
	  	    request.setAttribute("rst", entry);
			request.setAttribute("LOGIN_NAME", userBean.getXM());
			request.setAttribute("BM_NAME",userBean.getBMMC());
			request.setAttribute("LOGIN_ID", userBean.getRYXXID());
		    
			return mapping.findForward("toedit");
		}
		
		/**
		 * 新增.
		 * @param mapping the mapping
		 * @param form the form
		 * @param request the request
		 * @param response the response
		 * @return the action forward
		 */
		public ActionForward toEdit1(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

			UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
			PrintWriter writer = getWriter(response);
		    String jsonResult = jsonResult();
		    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		    String userName  = userBean.getXM();  //申请人
		    Map<String,String> params = new HashMap<String,String>();
		    Map<String,String> paramsT = new HashMap<String,String>();
		    String STORE_VISIT_ID    = ParamUtil.get(request, "STORE_VISIT_ID");

		    String search = ParamUtil.get(request,"search");
		    String module    = ParamUtil.get(request, "module");
		    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		    IListPage page   = null;
		    IListPage pageT  = null;
		    if (StringUtils.isNotEmpty(STORE_VISIT_ID)) {
				Map entry = storevisitService.loadByConfId(STORE_VISIT_ID);
				request.setAttribute("rst", entry);
			}
			//权限级别和审批流的封装和状态过滤
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		    String time = sdf.format(new Date());
		    request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		    request.setAttribute("userName",userName);
		    request.setAttribute("date", time);
		    request.setAttribute("state", "未提交");
		    request.setAttribute("page", page);
		    request.setAttribute("pageT", pageT);
		    request.setAttribute("pvg",setPvg(userBean));
			return mapping.findForward("toeditT");
		}
	    
	    /**
	     * 拓展拜访表维护修改页面跳转 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward toParentEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	 
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	String currentNo = "ZXSQ"+System.currentTimeMillis();     //自动申请装修验收单号
	    	String userName  = userBean.getXM();                      //申请人
	    	request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
	    	request.setAttribute("CHANN_ID",userBean.getCHANNS_CHARG());
	        return mapping.findForward("toedit");
	    }
	    
	    public ActionForward toParentEditT(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
			String STORE_VISIT_ID = ParamUtil.get(request, "STORE_VISIT_ID");
			if (StringUtils.isNotEmpty(STORE_VISIT_ID)) {
				Map entry =  storevisitService.loadByConfIdT(STORE_VISIT_ID);
				request.setAttribute("rst", entry);
			}
			request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
			request.setAttribute("STORE_VISIT_ID", STORE_VISIT_ID);
			return mapping.findForward("toeditT");
		}
	    
	    /**
	     * 拓展拜访表详细信息 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG
					&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
							.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
				throw new ServiceException("对不起，您没有权限 !");
			}
	    	String STORE_VISIT_ID = ParamUtil.get(request, "STORE_VISIT_ID");
			if (StringUtils.isNotEmpty(STORE_VISIT_ID)) {
				Map entry = storevisitService.loadByConfId(STORE_VISIT_ID);
				request.setAttribute("rst", entry);
				List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(STORE_VISIT_ID);
			    request.setAttribute("flowInfoList", flowInfoList);
			}
	        return mapping.findForward("todetail");
	    }
	    
	    
	    /**
	     * 拓展拜访表维护编辑//新增或修改。 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    @SuppressWarnings("unchecked")
		public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        String jsonData       = ParamUtil.get(request, "jsonData");
	        String STORE_VISIT_ID = ParamUtil.get(request, "STORE_VISIT_ID");
	        
	        StoreVisitModel model = new StoreVisitModel();
	        if (StringUtils.isNotEmpty(jsonData)) {
	            model = new Gson().fromJson(jsonData, new TypeToken <StoreVisitModel>() {
	            }.getType());
	        }
	        try {
	        	storevisitService.txEdit(STORE_VISIT_ID, model, userBean);
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
	     * 拓展拜访表明细编辑跳转页面 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	// 多个Id批量查询，用逗号隔开
	        String STORE_VISIT_DTL_ID = request.getParameter("STORE_VISIT_DTL_ID");
	        // 没有编码规则明细Id可以直接跳转。
	        if (StringUtils.isNotEmpty(STORE_VISIT_DTL_ID)) {
	        	
	        	List <StoreVisitDtlModel> list = storevisitService.loadChilds(STORE_VISIT_DTL_ID);
	            for (int i = 0; i < list.size(); i++) {
	            	  StoreVisitDtlModel modle = (StoreVisitDtlModel) list.get(i);
	                    modle.setSTORE_VISIT_ID(modle.getSTORE_VISIT_ID());  
	                    modle.setPRO_NAME(modle.getPRO_NAME());
	                    modle.setMAIN_TYPE(modle.getMAIN_TYPE());
	                    modle.setSIT_ANALYSIS(modle.getSIT_ANALYSIS());
	                    modle.setACTION_PLAN(modle.getACTION_PLAN());
	                    modle.setCOMPLETE_TIME(modle.getCOMPLETE_TIME());
	                    modle.setOTHER_INFO(modle.getOTHER_INFO());
	            }
	            request.setAttribute("rst", list); 
	        }
	        return mapping.findForward("childedit");
	    	
	    }
	    
	    
	    /**
	     * 拓展拜访表维护编辑页面加载子页面 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	String STORE_VISIT_ID = ParamUtil.utf8Decoder(request, "STORE_VISIT_ID");
	        if (StringUtils.isNotEmpty(STORE_VISIT_ID)) {
	            List<StoreVisitDtlModel> result = storevisitService.childQuery(STORE_VISIT_ID);
	            request.setAttribute("rst", result);
	        }
	        request.setAttribute("STORE_VISIT_ID",STORE_VISIT_ID);
	        return mapping.findForward("childedit");
	    }
	    
	    
	    
	    /**
	     * 拓展拜访表明细编辑
	     * Description :.
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
	        int errorFlg = 0;
	        try {
	        	String STORE_VISIT_ID = ParamUtil.utf8Decoder(request, "STORE_VISIT_ID");//ParamUtil.get(request, "SJZDID");
	            String jsonDate = request.getParameter("childJsonData");
	            if (StringUtils.isNotEmpty(jsonDate)) {
	                List <StoreVisitDtlModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List<StoreVisitDtlModel>>() {
	                }.getType());
	                for(int i=0;i<modelList.size();i++){
	                	StoreVisitDtlModel model = (StoreVisitDtlModel)modelList.get(i);
	                	String STORE_VISIT_DTL_ID = model.getSTORE_VISIT_DTL_ID();
	                	
	                	//查询项目类型,项目名称在验收明细表中是否存在
	                    String str = "";
	        			if(StringUtils.isEmpty(STORE_VISIT_DTL_ID)){
	         	              storevisitService.insertChild(STORE_VISIT_ID,model);
	        			} else{
	                  	      storevisitService.updateChild(STORE_VISIT_DTL_ID,model);
	        			}
	                } 
	            }
	        } catch (Exception e) {
	            jsonResult = jsonResult(false, "保存失败");
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    }
	    
	    

	    /**
	     * 拓展拜访表删除 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	      
	    	UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        String STORE_VISIT_ID = ParamUtil.get(request, "STORE_VISIT_ID");
	        if (StringUtils.isNotEmpty(STORE_VISIT_ID)) {
	            try {
	            	storevisitService.txDelete(STORE_VISIT_ID, userBean);
	                storevisitService.clearCaches(STORE_VISIT_ID);
	            } catch (RuntimeException e) {
	                jsonResult = jsonResult(false, "删除失败");
	            }
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    }
	    
	    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        
	    	  UserBean userBean = ParamUtil.getUserBean(request);
			  if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
				throw new ServiceException("对不起，您没有权限 !");
			  }
	          PrintWriter writer = getWriter(response);
	          String jsonResult = jsonResult();
	          try {
	              String STORE_VISIT_ID = ParamUtil.utf8Decoder(request, "STORE_VISIT_ID");//request.getParameter("BMGZID");
	              String STORE_VISIT_DTL_IDS = request.getParameter("STORE_VISIT_DTL_IDs");
	              // 批量删除，多个Id之间用逗号隔开
	              storevisitService.txBatch4DeleteChild(STORE_VISIT_DTL_IDS, STORE_VISIT_ID, userBean);
	          } catch (Exception e) {
	              jsonResult = jsonResult(false, "删除失败");
	          }
	          if (null != writer) {
	              writer.write(jsonResult);
	              writer.close();
	          }
	    }
	    
	   
	    // 导出
		@SuppressWarnings("unchecked")
		public void expertExcel(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			Map<String, String> params = new HashMap<String, String>();
			ParamUtil.putStr2Map(request, "STORE_VISIT_NO", params);
			ParamUtil.putStr2Map(request, "CHANN_NAME", params);
			ParamUtil.putStr2Map(request, "VISIT_PEOPLE", params);
			ParamUtil.putStr2Map(request, "STATE", params);
			ParamUtil.putStr2Map(request, "SVISIT_DATE", params);
			ParamUtil.putStr2Map(request, "EVISIT_DATE", params);
	       //只查询0的记录。1为删除。0为正常
	        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
			//权限级别和审批流的封装
	        String search = ParamUtil.get(request,"search");
		    String module = ParamUtil.get(request,"module");
		    String STATE = ParamUtil.get(request,"STATE");
			StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		    //权限级别和审批流的封装
			 if(module.equals("wh") || module.equals("")){
				if(!StringUtil.isEmpty(search) ){
					if(!StringUtil.isEmpty(STATE)){
						params.put("STATE", STATE);
					}
				}else{
				   qx.append(" and STATE in('未提交','撤销','否决') ");
				}
				 params.put("QXJBCON",qx.toString());
			 }
			 if(module.equals("sh")){
				String strSql = qx.toString().substring(qx.toString().indexOf("(")+1,qx.toString().lastIndexOf(")"));
				String newStr = "("+strSql.replaceAll("u.CREATOR", "'"+userBean.getRYXXID()+"'")+") temp ";
				StringBuffer buffer = new StringBuffer();
				buffer.append(newStr);
				buffer.append("WHERE u.DEL_FLAG = 0 ");
				if(!StringUtil.isEmpty(search) ){
					if(!StringUtil.isEmpty(STATE)){
						buffer.append("and STATE = '"+STATE+"'");
					}else{
						buffer.append("and STATE IN ('提交','撤销','否决','审核通过') ");
					}
				}else{
					  buffer.append("and STATE = '提交'");
				}
				params.put("QXJBCON",buffer.toString());
			 }
		     IListPage page = null;
		     request.setAttribute("module",module);
		     
		     //渠道分管
		     String CHANNS_CHARG = userBean.getCHANNS_CHARG();
			 params.put("CHANNS_CHARG", CHANNS_CHARG);
			
		    //字段排序
	  		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		 
			List list = new ArrayList();
			if(module.equals("sh")){
				list = storevisitService.expertExcelQuerySH(params);
			}else{
				list = storevisitService.expertExcelQuery(params);
			}
			// excel数据上列显示的列明
			String tmpContentCn = "流程编号,终端编号,终端名称,渠道名称,申请人,城市,拜访人,拜访日期,店招,店内灯箱,地面,产品," +
			        "道具,软饰,电视,灯光,物料,导购形象,导购态度,导购技能,饮水机,块毯,执行活动主题,执行活动时间,执行活动地点,活动目标,"+
					"实际达成,达成率,活动总结,本月计划,实际达成,达成率,本季目标,实际达成,达成率,月度坪效,活动计划主题,活动计划时间,活动计划地点,"+
					"预计投入,预计达成目标,竞品信息,支持需求";
			
			String tmpContent = "STORE_VISIT_NO,TERM_NO,TERM_NAME,CHANN_NAME,APPLY_PERSON,CITY,VISIT_PEOPLE,VISIT_DATE,STORE_STROKES,LIGHT_BOX,GROUND,PRODUCTS," +
			        "PROPERTIES,SOFT_DECORATION,TELEVISION,LIGHT_LAMP,MATERIALS,FIGURE,MANNER,TECHNICAL,WATER_MACHINE,BLANKETS,EXECUTE_ACTION_TOPIC,EXECUTE_ACTION_DATE,EXECUTE_ACTION_ADDR,ACTION_PLAN,"+
			        "ACTION_REALITY_RATE,ACTION_RATE,ACTION_RIGHT,MONTH_ORDER_NUM,MONTH_REALITY_RATE,MONTH_RATE,SEASON_ORDER_NUM,SEASON_REALITY_RATE,SEASON_RATE,EVALUATION_MONTH,ACTION_PLAN_TOPIC,ACTION_PLAN_DATE,ACTION_PLAN_ADDR,"+
			        "FORECAST_INVESTMENT,EXPECTED_GOAL,COMPETITION_INFO,SUPPORT_DEMAND";
			
			String colType= "string,string,string,string,string,string,string,string,string,string,string,string,string,string,"+
			                "string,string,string,string,string,string,string,string,string,string,string,string,string,string," +
						    "string,string,string,string,string,string,string,string,string,string,string,string,string,string,"+
						    "string,string,string,string,string,string,string,string,string,string,string,string,string,string,"+
						    "string,string,string,string,string,string,string,string,string,string,string,string,string,string";
			try {
				ExcelUtil.doExport(response, list, "门店拜访申请单", tmpContent, tmpContentCn,colType);
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
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	        pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
		    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
		    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
		    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	   
		public StoreVisitService getStorevisitService() {
			return storevisitService;
		}
	
		public void setStorevisitService(StoreVisitService storevisitService) {
			this.storevisitService = storevisitService;
		}
}
