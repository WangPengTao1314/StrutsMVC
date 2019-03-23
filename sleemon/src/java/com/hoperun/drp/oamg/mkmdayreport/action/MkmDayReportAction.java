package com.hoperun.drp.oamg.mkmdayreport.action;

import java.io.PrintWriter;
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
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.mkmdayreport.model.ChannVisitDayModel;
import com.hoperun.drp.oamg.mkmdayreport.model.CooperativeVisitModel;
import com.hoperun.drp.oamg.mkmdayreport.model.DistributorVisitModel;
import com.hoperun.drp.oamg.mkmdayreport.model.MkmDayReportModel;
import com.hoperun.drp.oamg.mkmdayreport.model.PromotionActvModel;
import com.hoperun.drp.oamg.mkmdayreport.model.ShoppGuideTranModel;
import com.hoperun.drp.oamg.mkmdayreport.model.TerminalVisitModel;
import com.hoperun.drp.oamg.mkmdayreport.service.MkmDayReportService;
import com.hoperun.sys.model.UserBean;

public class MkmDayReportAction extends BaseAction {
   
	    private  MkmDayReportService  mkmDayReportService;
	    //增删改查
	    private static String PVG_BWS="BS0034601";
	    private static String PVG_EDIT="BS0034602";
	    private static String PVG_DELETE="BS0034603";
	    //提交撤销
	    private static String PVG_COMMIT_CANCLE="BS0034604";
	    //流程跟踪
	    private static String PVG_TRACE= "BS0034605";
	    //审核模块                             
	    private static String PVG_BWS_AUDIT="BS0034701";
	    private static String PVG_AUDIT_AUDIT="BS0034702";
	    private static String PVG_TRACE_AUDIT="BS0034703";
		//审批流参数
	    private static String AUD_TAB_NAME="DRP_MKM_DAY_REPORT";
	    private static String AUD_TAB_KEY="MKM_DAY_RPT_ID";
		private static String AUD_BUSS_STATE="STATE";
	    private static String AUD_BUSS_TYPE="DRP_MKM_DAY_REPORT_AUDIT";
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
	         ParamUtil.putStr2Map(request, "MKM_DAY_RPT_NO", params);
	         ParamUtil.putStr2Map(request, "WAREA_NAME", params);
	         ParamUtil.putStr2Map(request, "MKM_NAME", params);
	         String MKM_NAME=ParamUtil.utf8Decoder(request, "MKM_NAME");
	         ParamUtil.putStr2Map(request, "STATE", params);
	         ParamUtil.putStr2Map(request, "REPORT_DATE_BEG", params);
	         ParamUtil.putStr2Map(request, "REPORT_DATE_END", params);
	         ParamUtil.putStr2Map(request, "VST_DATE_BEG", params);
	         ParamUtil.putStr2Map(request, "VST_DATE_END", params);
	         ParamUtil.putStr2Map(request, "pageSize", params);
	         params.put("MKM_NAME_SQL", StringUtil.creCon("m.MKM_NAME", MKM_NAME, ","));
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
			 }
			 if(module.equals("sh")){
				if(!StringUtil.isEmpty(search) ){
					if(!StringUtil.isEmpty(STATE)){
						params.put("STATE", STATE);
					}else{
						qx.append(" and STATE in('提交','撤销','否决','审核通过') ");
					}
				}else{
					qx.append(" and STATE='提交' ");
				}
			 }
		     params.put("QXJBCON",qx.toString());
		     IListPage page = null;
		     request.setAttribute("module",module);
		     //渠道分管
		     String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		     params.put("CHANNS_CHARG", CHANNS_CHARG);
		     params.put("AREA_CHARG", userBean.getAREA_CHARG());
				
		     int pageNo = ParamUtil.getInt(request, "pageNo", 1);
	 		 page = mkmDayReportService.pageQuery(params, pageNo);
		     
	 		 request.setAttribute("pvg",setPvg(userBean));
	 		 request.setAttribute("module", module);
	 		 request.setAttribute("search", search);
	         request.setAttribute("params", params);
	         request.setAttribute("page", page);  
	         return mapping.findForward("list");
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
			if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			String EDIT_FLAG = ParamUtil.get(request, "EDIT_FLAG");
			request.setAttribute("EDIT_FLAG", EDIT_FLAG);
			// 设置跳转时需要的一些公用属性
			ParamUtil.setCommAttributeEx(request);
			return mapping.findForward("editFrame");
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
	    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}	
			String MKM_DAY_RPT_ID = ParamUtil.get(request, "selRowId");
			Map<String,String> entry = new HashMap<String,String>();
			Map<String,String> entrycv = new HashMap<String,String>();
			Map<String,String> entryact= new HashMap<String,String>();
			Map<String,String> entrydis= new HashMap<String,String>();
			Map<String,String> entrycoo= new HashMap<String,String>();
			Map<String,String> entryshop = new HashMap<String,String>();
			Map<String,String> entryterm = new HashMap<String,String>();
			
			if(!StringUtil.isEmpty(MKM_DAY_RPT_ID)){
				entry   = mkmDayReportService.load(MKM_DAY_RPT_ID);
				entrycv = mkmDayReportService.loadByChannVist(MKM_DAY_RPT_ID);
				entryact= mkmDayReportService.loadByProActv(MKM_DAY_RPT_ID);
				entrydis= mkmDayReportService.loadByDisVisit(MKM_DAY_RPT_ID);
				entrycoo= mkmDayReportService.loadByCooVisit(MKM_DAY_RPT_ID);
				entryshop = mkmDayReportService.loadByShopTran(MKM_DAY_RPT_ID);
				entryterm = mkmDayReportService.loadByByTerm(MKM_DAY_RPT_ID);
			} else{
				entry = new HashMap<String,String>();
				entry.put("STATE", BusinessConsts.UNCOMMIT);
				entry.put("MKM_ID", userBean.getRYXXID());
				entry.put("MKM_NAME", userBean.getXM());
				entry.put("REPORT_DATE", DateUtil.format(new Date(), "yyyy-MM-dd"));
				Map<String,String> chann = mkmDayReportService.loadByAMId(userBean.getRYXXID());
				if(null != chann){
					entry.put("WAREA_ID",   chann.get("BMXXID"));
					entry.put("WAREA_NO",   chann.get("BMBH"));
	            	entry.put("WAREA_NAME", chann.get("BMMC"));
	        	}
			}
			request.setAttribute("userName", userBean.getXM());
			request.setAttribute("rst", entry);
			request.setAttribute("rstcv", entrycv);
			request.setAttribute("rstact", entryact);
			request.setAttribute("rstdis", entrydis);
			request.setAttribute("rstcoo", entrycoo);
			request.setAttribute("rstshop",entryshop);
			request.setAttribute("rsterm", entryterm);
			request.setAttribute("pvg",setPvg(userBean));
			request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
			return mapping.findForward("toedit");
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
	    	String MKM_DAY_RPT_ID = ParamUtil.get(request, "MKM_DAY_RPT_ID");
			if (StringUtils.isNotEmpty(MKM_DAY_RPT_ID)) {
				Map entry   = mkmDayReportService.load(MKM_DAY_RPT_ID);
				Map entrycv = mkmDayReportService.loadByChannVist(MKM_DAY_RPT_ID);
				Map entryact= mkmDayReportService.loadByProActv(MKM_DAY_RPT_ID);
				Map entrydis= mkmDayReportService.loadByDisVisit(MKM_DAY_RPT_ID);
				Map entrycoo= mkmDayReportService.loadByCooVisit(MKM_DAY_RPT_ID);
				Map entryshop = mkmDayReportService.loadByShopTran(MKM_DAY_RPT_ID);
				Map entryterm = mkmDayReportService.loadByByTerm(MKM_DAY_RPT_ID);
				
				request.setAttribute("rst", entry);
				request.setAttribute("rstcv", entrycv);
				request.setAttribute("rstact",entryact);
				request.setAttribute("rstdis", entrydis);
				request.setAttribute("rstcoo", entrycoo);
				request.setAttribute("rstshop",entryshop);
				request.setAttribute("rsterm", entryterm);
				List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(MKM_DAY_RPT_ID);
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
			String MKM_DAY_RPT_ID = ParamUtil.get(request, "MKM_DAY_RPT_ID");
	
			MkmDayReportModel     model     = new MkmDayReportModel();  //营销经理日报
			ChannVisitDayModel    cvModel   = new ChannVisitDayModel(); //加盟商拜访
			PromotionActvModel    actModel  = new PromotionActvModel(); //推广活动
			DistributorVisitModel disModel  = new DistributorVisitModel(); //分销商
			CooperativeVisitModel cooModel  = new CooperativeVisitModel(); //合作商
			ShoppGuideTranModel  shopModel  = new ShoppGuideTranModel();   //导购员
			TerminalVisitModel   termModel  = new TerminalVisitModel();    //终端

			if (StringUtils.isNotEmpty(jsonData)) {
				model = new Gson().fromJson(jsonData,
						new TypeToken<MkmDayReportModel>() {
						}.getType());
			    cvModel = new Gson().fromJson(jsonData,
							new TypeToken<ChannVisitDayModel>() {
					}.getType());
			    actModel = new Gson().fromJson(jsonData,
						new TypeToken<PromotionActvModel>() {
				}.getType());
			    disModel = new Gson().fromJson(jsonData,
						new TypeToken<DistributorVisitModel>() {
				}.getType()); 
			    cooModel = new Gson().fromJson(jsonData,
						new TypeToken<CooperativeVisitModel>() {
				}.getType());
			    
			    shopModel= new Gson().fromJson(jsonData,
						new TypeToken<ShoppGuideTranModel>() {
				}.getType());
			    termModel= new Gson().fromJson(jsonData,
						new TypeToken<TerminalVisitModel>() {
				}.getType());
			}
			try {
			  boolean flag = mkmDayReportService.txEdit(MKM_DAY_RPT_ID, model,cvModel,actModel,disModel,cooModel,shopModel,termModel,userBean);
			  if(!flag){
				  jsonResult = jsonResult(false, "您提交的拜访日期已存在");
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
	     * 营销经理日报删除 Description :.
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
	        String MKM_DAY_RPT_ID = ParamUtil.get(request, "MKM_DAY_RPT_ID");

	        if (StringUtils.isNotEmpty(MKM_DAY_RPT_ID)) {
	            try {
	            	mkmDayReportService.txDelete(MKM_DAY_RPT_ID, userBean);
	            	mkmDayReportService.clearCaches(MKM_DAY_RPT_ID);
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

		public MkmDayReportService getMkmDayReportService() {
			return mkmDayReportService;
		}

		public void setMkmDayReportService(MkmDayReportService mkmDayReportService) {
			this.mkmDayReportService = mkmDayReportService;
		}
}
