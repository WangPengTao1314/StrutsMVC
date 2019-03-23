package com.hoperun.drp.visit.channvisit.action;

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
import com.hoperun.drp.visit.channvisit.model.ChannVisitDtlModel;
import com.hoperun.drp.visit.channvisit.model.ChannVisitModel;
import com.hoperun.drp.visit.channvisit.service.ChannVisitService;
import com.hoperun.sys.model.UserBean;

public class ChannVisitAction  extends BaseAction {

	    private  ChannVisitService  channvisitService;
	    //增删改查
	    private static String PVG_BWS="BS0032901";
	    private static String PVG_EDIT="BS0032902";
	    private static String PVG_DELETE="BS0032903";
	    //提交撤销
	    private static String PVG_COMMIT_CANCLE="BS0032904";
	    //流程跟踪
	    private static String PVG_TRACE= "BS0032905";
	    //审核模块                             
	    private static String PVG_BWS_AUDIT="BS0033201";
	    private static String PVG_AUDIT_AUDIT="BS0033202";
	    private static String PVG_TRACE_AUDIT="BS0033203";
		//审批流参数
	    private static String AUD_TAB_NAME="ERP_CHANN_VISIT";
	    private static String AUD_TAB_KEY="CHANN_VISIT_ID";
		private static String AUD_BUSS_STATE="STATE";
	    private static String AUD_BUSS_TYPE="ERP_CHANN_VISIT_AUDIT";
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
	     * 加盟商拜访维护列表 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

	         //权限级别
	         UserBean userBean = ParamUtil.getUserBean(request);
	     	 if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	         }
	         Map <String, String> params = new TreeMap <String, String>();
	         ParamUtil.putStr2Map(request, "CHANN_VISIT_NO", params);
	         ParamUtil.putStr2Map(request, "CHANN_NO", params);
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
						buffer.append("and STATE = '"+STATE+"'");
					}else{
						buffer.append("and STATE IN ('提交','撤销','否决','审核通过') ");
					}
				}else{
					  buffer.append("and STATE = '提交'");
				}
				if(userBean.getRYXXID().equals("14031691")){
					   buffer.append(" and DEPT_NAME = '华北战区'");
				}
				params.put("QXJBCON",buffer.toString());
			 }
		     IListPage page = null;
		     request.setAttribute("module",module);
		     //渠道分管
		     String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		     params.put("CHANNS_CHARG", CHANNS_CHARG);
		     params.put("AREA_CHARG", userBean.getAREA_CHARG());
				
		     int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		     if(module.equals("sh")) {
	 		    page = channvisitService.pageQuery(params, pageNo);
		     } else{
		        page = channvisitService.pageQueryWH(params, pageNo);
		     }
	 		 request.setAttribute("pvg",setPvg(userBean));
	 		 request.setAttribute("module", module);
	 		 request.setAttribute("search", search);
	         request.setAttribute("params", params);
	         request.setAttribute("page", page);  
	         return mapping.findForward("list");
	    }
	    
	    /**
	     * 加盟商拜访表明细列表 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	 
	    	 UserBean userBean = ParamUtil.getUserBean(request);
	    	 if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	         }
	    	 String CHANN_VISIT_ID = ParamUtil.get(request, "CHANN_VISIT_ID");
	         if (StringUtils.isNotEmpty(CHANN_VISIT_ID)) {
	             List<ChannVisitDtlModel> result = channvisitService.childQueryT(CHANN_VISIT_ID);
	             for(int i=0;i<result.size();i++){
	            	 ChannVisitDtlModel   model = (ChannVisitDtlModel)result.get(i);
	            	 model.setCHANN_VISIT_DTL_ID(model.getCHANN_VISIT_DTL_ID());
	            	 String PRO_NAME = channvisitService.queryPro(model.getPRO_NAME());
	            	 model.setPRO_NAME(PRO_NAME);
	            	 String MAIN_TYPE= channvisitService.queryPro(model.getMAIN_TYPE());
	            	 model.setMAIN_TYPE(MAIN_TYPE);
	            	 String SUB_TYPE = channvisitService.queryPro(model.getSUB_TYPE());
	            	 model.setSUB_TYPE(SUB_TYPE);
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
	        UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	         }
	   	    String CHANN_VISIT_ID = ParamUtil.get(request, "CHANN_VISIT_ID");
	        if (StringUtils.isNotEmpty(CHANN_VISIT_ID)) {
	            List<ChannVisitDtlModel> result = channvisitService.childQueryT(CHANN_VISIT_ID);
	            for(int i=0;i<result.size();i++){
	            	 ChannVisitDtlModel   model = (ChannVisitDtlModel)result.get(i);
	            	 model.setCHANN_VISIT_DTL_ID(model.getCHANN_VISIT_DTL_ID());
	            	 String PRO_NAME = channvisitService.queryPro(model.getPRO_NAME());
	            	 model.setPRO_NAME(PRO_NAME);
	            	 String MAIN_TYPE= channvisitService.queryPro(model.getMAIN_TYPE());
	            	 model.setMAIN_TYPE(MAIN_TYPE);
	            	 String SUB_TYPE = channvisitService.queryPro(model.getSUB_TYPE());
	            	 model.setSUB_TYPE(SUB_TYPE);
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
	     * 加盟商拜访表编辑框架页面.
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
	        String CHANN_VISIT_ID = ParamUtil.get(request, "CHANN_VISIT_ID");
	        request.setAttribute("CHANN_VISIT_ID", CHANN_VISIT_ID);
	        String module  = ParamUtil.get(request, "module");
	        request.setAttribute("module", module);
	    	if(!CHANN_VISIT_ID.equals("")){
	    	 return mapping.findForward("editFrame_T");
	    	} else {
	         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	     String time = sdf.format(new Date());
    	     request.setAttribute("APPLY_DATE",time);
    	     request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
    	     request.setAttribute("XTYH_ID", userBean.getXTYHID());
    	     
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
	    }
	    
	    /**
	     * 加盟商拜访表维护修改页面跳转 Description :.
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
			String CHANN_VISIT_ID = ParamUtil.get(request, "CHANN_VISIT_ID");
			if (StringUtils.isNotEmpty(CHANN_VISIT_ID)) {
				Map entry =  channvisitService.loadByConfId(CHANN_VISIT_ID);
				request.setAttribute("rst", entry);
			}
			request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
			request.setAttribute("CHANN_VISIT_ID", CHANN_VISIT_ID);
			return mapping.findForward("toeditT");
		}
	    
	    /**
	     * 加盟商拜访表详细信息 Description :.
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
	    	String CHANN_VISIT_ID = ParamUtil.get(request, "CHANN_VISIT_ID");
			if (StringUtils.isNotEmpty(CHANN_VISIT_ID)) {
				Map entry = channvisitService.loadByConfId(CHANN_VISIT_ID);
				request.setAttribute("rst", entry);
				List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(CHANN_VISIT_ID);
			    request.setAttribute("flowInfoList", flowInfoList);
			}
	        return mapping.findForward("todetail");
	    }
	    
	    /**
	     * 加盟商拜访表维护编辑//新增或修改。 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    @SuppressWarnings("unchecked")
		public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        
		    UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			PrintWriter writer = getWriter(response);
			String jsonResult = jsonResult();
			String jsonData = ParamUtil.get(request, "jsonData");
			String CHANN_VISIT_ID = ParamUtil.get(request, "CHANN_VISIT_ID");
	
			ChannVisitModel model = new ChannVisitModel();
			if (StringUtils.isNotEmpty(jsonData)) {
				model = new Gson().fromJson(jsonData,
						new TypeToken<ChannVisitModel>() {
						}.getType());
			}
			try {
				channvisitService.txEdit(CHANN_VISIT_ID, model, userBean);
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
		 * 加盟商拜访表维护编辑//新增或修改。 Description :.
		 * 
		 * @param mapping
		 *            the mapping
		 * @param form
		 *            the form
		 * @param request
		 *            the request
		 * @param response
		 *            the response
		 */
	    @SuppressWarnings("unchecked")
		public void editT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        String jsonData       = ParamUtil.get(request, "jsonData");
	        String CHANN_VISIT_ID = ParamUtil.get(request, "CHANN_VISIT_ID");
	        String EME_DEGREE     = ParamUtil.get(request, "EME_DEGREE");
	        
	        ChannVisitModel model = new ChannVisitModel();
	        if (StringUtils.isNotEmpty(jsonData)) {
	            model = new Gson().fromJson(jsonData, new TypeToken <ChannVisitModel>() {
	            }.getType());
	        }
	         channvisitService.txEditT(CHANN_VISIT_ID, model, userBean,EME_DEGREE);
	 
	         if (null != writer) {
	             writer.write(jsonResult);
	             writer.close();
	         }
	    }
	    
	    public ActionForward toEdit1(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
			PrintWriter writer = getWriter(response);
		    String jsonResult = jsonResult();
		    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		    String userName  = userBean.getXM();  //申请人
		    Map<String,String> params = new HashMap<String,String>();
		    Map<String,String> paramsT = new HashMap<String,String>();
		    String CHANN_VISIT_ID    = ParamUtil.get(request, "CHANN_VISIT_ID");

		    String search = ParamUtil.get(request,"search");
		    String module    = ParamUtil.get(request, "module");
		    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		    IListPage page   = null;
		    IListPage pageT  = null;
		    if (StringUtils.isNotEmpty(CHANN_VISIT_ID)) {
				Map entry = channvisitService.loadByConfId(CHANN_VISIT_ID);
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
		    request.setAttribute("XTYH_ID", userBean.getXTYHID());
		    
			return mapping.findForward("toeditT");
		}
	    
	    /**
	     * 加盟商拜访表明细编辑跳转页面 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * 
	     * @return the action forward
	     */
	    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	// 多个Id批量查询，用逗号隔开
	        String CHANN_VISIT_DTL_ID = request.getParameter("CHANN_VISIT_DTL_ID");
	        // 没有编码规则明细Id可以直接跳转。
	        if (StringUtils.isNotEmpty(CHANN_VISIT_DTL_ID)) {
	            List <ChannVisitDtlModel> list =  channvisitService.loadChilds(CHANN_VISIT_DTL_ID);
	            for (int i = 0; i < list.size(); i++) {
	            	  ChannVisitDtlModel modle = (ChannVisitDtlModel) list.get(i);
	                    modle.setPRO_NAME(modle.getPRO_NAME());   
	                    modle.setMAIN_TYPE(modle.getMAIN_TYPE());   
	                    modle.setSUB_TYPE(modle.getSUB_TYPE());    
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
	     * 加盟商拜访表维护编辑页面加载子页面 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	    	String CHANN_VISIT_ID = ParamUtil.utf8Decoder(request, "CHANN_VISIT_ID");
	        if (StringUtils.isNotEmpty(CHANN_VISIT_ID)) {
	            List<ChannVisitDtlModel> result = channvisitService.childQuery(CHANN_VISIT_ID);
	            request.setAttribute("rst", result);
	        }
	        request.setAttribute("CHANN_VISIT_ID",CHANN_VISIT_ID);
	        return mapping.findForward("childedit");
	    }
	    
	    /**
	     * 加盟商拜访表明细编辑
	     * Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        int errorFlg = 0;
	        try {
	        	String CHANN_VISIT_ID = ParamUtil.utf8Decoder(request, "CHANN_VISIT_ID");//ParamUtil.get(request, "SJZDID");
	            String jsonDate = request.getParameter("childJsonData");
	            if (StringUtils.isNotEmpty(jsonDate)) {
	                List <ChannVisitDtlModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List<ChannVisitDtlModel>>() {
	                }.getType());
	                for(int i=0;i<modelList.size();i++){
	                	ChannVisitDtlModel model = (ChannVisitDtlModel)modelList.get(i);
	                	String CHANN_VISIT_DTL_ID = model.getCHANN_VISIT_DTL_ID().toString();
	                	//查询项目类型,项目名称在验收明细表中是否存在
	                    String str = "";
	        			if(StringUtils.isEmpty(CHANN_VISIT_DTL_ID)){
	         	           channvisitService.insertChild( CHANN_VISIT_ID,model);
	        			} else{
	                  	   channvisitService.updateChild(CHANN_VISIT_DTL_ID,model);
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
	     * 加盟商拜访表删除 Description :.
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
	        String CHANN_VISIT_ID = ParamUtil.get(request, "CHANN_VISIT_ID");

	        if (StringUtils.isNotEmpty(CHANN_VISIT_ID)) {
	            try {
	            	channvisitService.txDelete(CHANN_VISIT_ID, userBean);
	                channvisitService.clearCaches(CHANN_VISIT_ID);
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
	              String CHANN_VISIT_ID = ParamUtil.utf8Decoder(request, "CHANN_VISIT_ID");//request.getParameter("BMGZID");
	              String CHANN_VISIT_DTL_IDS = request.getParameter("CHANN_VISIT_DTL_IDs");
	              // 批量删除，多个Id之间用逗号隔开
	              channvisitService.txBatch4DeleteChild(CHANN_VISIT_DTL_IDS, CHANN_VISIT_ID, userBean);
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
			ParamUtil.putStr2Map(request, "CHANN_VISIT_NO", params);
			ParamUtil.putStr2Map(request, "CHANN_NAME", params);
			ParamUtil.putStr2Map(request, "VISIT_PEOPLE", params);
			ParamUtil.putStr2Map(request, "SVISIT_DATE", params);
			ParamUtil.putStr2Map(request, "EVISIT_DATE", params);
			ParamUtil.putStr2Map(request, "STATE", params);
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
			   list = channvisitService.expertExcelQuerySH(params);
			} else{
			   list = channvisitService.expertExcelQuery(params);
			}
			// excel数据上列显示的列明
			String tmpContentCn = "流程编号,客户名称,拜访人,拜访日期,申请部门,拜访形式,床垫,软床,床头柜,床品,本月销售目标,本季销售目标," +
			        "销售改善计划,本月实际销售,本季实际销售,月销售达成率,季销售达成率,季度目标,目前达成,达成率,季度改善计划,加盟商问题,"+
					"主要行动,竞品信息,支持需求";
			String tmpContent = "CHANN_VISIT_NO,CHANN_NAME,VISIT_PEOPLE,VISIT_DATE,APPLY_DEP,VISIT_TYPE,MATTRESS_STOCK,BED_STOCK,BEDSIDE_STOCK,BEDDING_STOCK,MONTH_ORDER_NUM,SEASON_ORDER_NUM," +
			        "SALES_IMP_PLAN,MONTH_ORDER_REALITY_RATE,SEASON_ORDER_REALITY_RATE,MONTH_ORDER_RATE,SEASON_ORDER_RATE,SEASON_GOALS,CURRENT_REALITY_RATE,CURRENT_RATE,SEASON_IMPROVE_PLAN,CHANN_QUESTION,"+
			        "MAIN_ACTION,COMPETITION_INFO,SUPPORT_DEMAND";
			
			String colType= "string,string,string,string,string,string,string,string,string,string,string,string,string,string,"+
			                "string,string,string,string,string,string,string,string,string,string,string,string,string,string," +
						    "string,string,string,string,string,string,string,string,string,string,string,string,string,string";
			try {
				ExcelUtil.doExport(response, list, "加盟商拜访申请单", tmpContent, tmpContentCn,colType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	   public static void main(String[] args){
//		   System.out.print("123..........");
//		   StringBuffer sbf = new StringBuffer();
//		   sbf.append(",,c,,");
//		   sbf.toString().indexOf("\\n",1);
//		   sbf.append("f,g,h,i,j,k");
//		   System.out.print(sbf.toString());
		   
		   String str = "\b2\b\n456\n789\n012"; 
		   //String str2 = getStr(str, 2);//截取第二次，如果是12次，就改成12 
		   System.out.println(str); 

	   }
	   
	   private static String getStr(String str, int n) { 
		   int i = 0; 
		   int s = 0; 
		   while (i++ < n) { 
		   s = str.indexOf("\n", s + 1); 
		   if (s == -1) { 
		   return str; 
		   } 
		   } 
		   return str.substring(0, s); 
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
	   

	   public ChannVisitService getChannvisitService() {
		return channvisitService;
	   }

	   public void setChannvisitService(ChannVisitService channvisitService) {
		this.channvisitService = channvisitService;
	   }
}
