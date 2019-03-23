package com.hoperun.erp.oamg.monthVisit.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.oamg.monthVisit.model.MonthvisitModel;
import com.hoperun.erp.oamg.monthVisit.model.MonthvisitdtlModel;
import com.hoperun.erp.oamg.monthVisit.service.MonthVisitService;
import com.hoperun.sys.model.UserBean;

public class MonthVisitAction extends BaseAction {

	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS  = "BS0033401";
    private static String PVG_EDIT = "BS0033402";
    private static String PVG_DELETE="BS0033403";

    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0033404";
    //流程跟踪
    private static String PVG_TRACE= "BS0033405";
    //审核模块                             
    private static String PVG_BWS_AUDIT="BS0033501";
    private static String PVG_AUDIT_AUDIT="BS0033502";
    private static String PVG_TRACE_AUDIT="BS0033503";
	
	//审批流参数
    private static String AUD_TAB_NAME="ERP_MONTH_VISIT_PLAN";
    private static String AUD_TAB_KEY="MONTH_VISIT_PLAN_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_MONTH_VISIT_PLAN_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	
	private MonthVisitService  monthvisitService;
	
	public MonthVisitService getMonthvisitService() {
		return monthvisitService;
	}

	public void setMonthvisitService(MonthVisitService monthvisitService) {
		this.monthvisitService = monthvisitService;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		request.setAttribute("module",  ParamUtil.get(request,"module"));
		return mapping.findForward("frames");
	}
	
	/**
     * 月度拜访维护列表 Description :.
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

         //权限级别
     	 UserBean userBean = ParamUtil.getUserBean(request);
		 if (Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		 }
         Map <String, String> params = new TreeMap <String, String>();
         ParamUtil.putStr2Map(request, "MONTH_VISIT_PLAN_NO", params);
         ParamUtil.putStr2Map(request, "RYBH", params);
         ParamUtil.putStr2Map(request, "RYMC", params);
         ParamUtil.putStr2Map(request, "PLAN_YEAR", params);
         ParamUtil.putStr2Map(request, "PLAN_MONTH", params);
         ParamUtil.putStr2Map(request, "STATE", params);
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
			
	     int pageNo = ParamUtil.getInt(request, "pageNo", 1);
 		 page = monthvisitService.pageQuery(params, pageNo);
 		 request.setAttribute("pvg",setPvg(userBean));
 		 request.setAttribute("module", module);
         request.setAttribute("params", params);
         request.setAttribute("page", page);  
         request.setAttribute("CHANNS_CHARG", CHANNS_CHARG);
         request.setAttribute("QUERY_CHANN_ID", CHANNS_CHARG);
         request.setAttribute("QUERY_AREA_ID", userBean.getAREA_CHARG());
         return mapping.findForward("list");
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String MONTH_VISIT_PLAN_ID = ParamUtil.get(request, "MONTH_VISIT_PLAN_ID");
		if (!StringUtil.isEmpty(MONTH_VISIT_PLAN_ID)) {
			List<MonthvisitdtlModel> result =  monthvisitService.childQuery(MONTH_VISIT_PLAN_ID);
			if (null != result) {
				request.setAttribute("resultSize", result.size());
			}
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		return mapping.findForward("childlist");
	}
    
	/**
	 * * to 详细信息
	 * @param mapping  the mapping
	 * @param form the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String MONTH_VISIT_PLAN_ID = ParamUtil.get(request, "MONTH_VISIT_PLAN_ID");
		if (!StringUtil.isEmpty(MONTH_VISIT_PLAN_ID)) {
			Map<String, String> entry = monthvisitService
					.load(MONTH_VISIT_PLAN_ID);
			request.setAttribute("rst", entry);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(MONTH_VISIT_PLAN_ID);
		    request.setAttribute("flowInfoList", flowInfoList);
		}
		return mapping.findForward("detail");
	}
	
	
	 /**
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,"paramUrl"));
	        return mapping.findForward("editFrame");
    }
   
   /**
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean,PVG_EDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl", paramUrl);
		return mapping.findForward("edit");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean,PVG_EDIT))){
    		throw new ServiceException("对不起，您没有权限!");
    	}
		String MONTH_VISIT_PLAN_ID = ParamUtil.get(request, "selRowId");
		if (!StringUtil.isEmpty(MONTH_VISIT_PLAN_ID)) {
			Map<String, String> result = monthvisitService.load(MONTH_VISIT_PLAN_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("update");
	}
	
	 /**
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		 
	 UserBean userBean = ParamUtil.getUserBean(request);
	 if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	 {
		throw new ServiceException("对不起，您没有权限 !");
	 }
   	 String currentNo = "ZXSQ"+System.currentTimeMillis();     //自动申请装修申请单号
        String selRowId = StringUtil.utf8Decoder(ParamUtil.get(request, "selRowId"));//ParamUtil.utf8Decoder(request, "BMGZID");//;
        // 为空则是新增，否则是修改
        if (StringUtils.isNotEmpty(selRowId)) {
            Map <String, String> entry = monthvisitService.load(selRowId);
            request.setAttribute("rst", entry);
            request.setAttribute("isNew", false);
        }  else {
       	 String userName  = userBean.getXM();                      //申请人
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
     	     String time = sdf.format(new Date());                     //制单日期
        	 request.setAttribute("RNVTN_SUBST_STD_NO",currentNo);
        	 request.setAttribute("CRE_NAME",userName);
        	 request.setAttribute("CRE_TIME", time);
        }
        return mapping.findForward("toedit");
        }

	    /**
	     * 销售计划维护编辑页面加载子页面 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     * @return the action forward
	     */
	    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	String MONTH_VISIT_PLAN_ID = ParamUtil.utf8Decoder(request, "MONTH_VISIT_PLAN_ID");
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        if (StringUtils.isNotEmpty(MONTH_VISIT_PLAN_ID)) {
	            List<MonthvisitdtlModel> result =  monthvisitService.childQuery(MONTH_VISIT_PLAN_ID);
	            request.setAttribute("rst", result);
	        }
	        request.setAttribute("MONTH_VISIT_PLAN_ID",MONTH_VISIT_PLAN_ID);
	        request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
	        return mapping.findForward("childedit");
	    }

	    /**
	     * @param mapping
	     * @param form
	     * @param request
	     * @param response
	     * @return
	     */
       public ActionForward modifyToChildEditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	
    	    UserBean userBean = ParamUtil.getUserBean(request);
	       	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	       	{
	       		throw new ServiceException("对不起，您没有权限 !");
	       	}
	    	String MONTH_VISIT_PLAN_ID = ParamUtil.utf8Decoder(request, "MONTH_VISIT_PLAN_ID");//ParamUtil.get(request, "BMGZID");
	        if (StringUtils.isNotEmpty(MONTH_VISIT_PLAN_ID)) {
	            List<MonthvisitdtlModel> result = monthvisitService.childQuery(MONTH_VISIT_PLAN_ID);
	            request.setAttribute("rst", result);
	        }
	        request.setAttribute("MONTH_VISIT_PLAN_ID",MONTH_VISIT_PLAN_ID);
	        request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
	        return mapping.findForward("childeditT");
	    }
	    
	    /**
	     * 销售计划维护编辑跳转页面 Description :.
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
	        String MONTH_VISIT_PLAN_DTL_IDS = request.getParameter("MONTH_VISIT_PLAN_DTL_IDS");
	        String MONTH_VISIT_PLAN_ID      = request.getParameter("MONTH_VISIT_PLAN_ID");
	        String flag  =  request.getParameter("Tflag");
	        // 没有编码规则明细Id可以直接跳转。
	        if (StringUtils.isNotEmpty(MONTH_VISIT_PLAN_DTL_IDS)) {
	            List <MonthvisitdtlModel> list =  monthvisitService.loadByIds(MONTH_VISIT_PLAN_DTL_IDS);
	            request.setAttribute("rst", list);
	        }
	        request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
	        request.setAttribute("MONTH_VISIT_PLAN_ID", MONTH_VISIT_PLAN_ID);
	        request.setAttribute("flag",flag);
	        return mapping.findForward("childedit");
	    }
	    
          public ActionForward toChildEditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        String MONTH_VISIT_PLAN_DTL_IDS = request.getParameter("MONTH_VISIT_PLAN_DTL_IDS");
	        String MONTH_VISIT_PLAN_ID      = request.getParameter("MONTH_VISIT_PLAN_ID");
	        String flag  =  request.getParameter("Tflag");
	        // 没有编码规则明细Id可以直接跳转。
	        if (StringUtils.isNotEmpty(MONTH_VISIT_PLAN_DTL_IDS)) {
	            List <MonthvisitdtlModel> list =  monthvisitService.loadByIds(MONTH_VISIT_PLAN_DTL_IDS);
	            request.setAttribute("rst", list);
	        }
	        request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
	        request.setAttribute("MONTH_VISIT_PLAN_ID", MONTH_VISIT_PLAN_ID);
	        request.setAttribute("flag",flag);
	        return mapping.findForward("childeditT");
	    }
	    
	    /**
	     * 销售计划维护明细编辑
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
	        try {                                                          
	        	String MONTH_VISIT_PLAN_ID = ParamUtil.utf8Decoder(request, "MONTH_VISIT_PLAN_ID"); 
	            String jsonDate = request.getParameter("childJsonData");
	            if (StringUtils.isNotEmpty(jsonDate)) {
	                List <MonthvisitdtlModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List<MonthvisitdtlModel>>() {
	                }.getType());
	                for(int i=0;i<modelList.size();i++){
	                	String MONTH_VISIT_PLAN_DTL_ID  = modelList.get(i).getMONTH_VISIT_PLAN_DTL_ID().toString();
	                	String VISIT_OBJ_TYPE       = modelList.get(i).getVISIT_OBJ_TYPE().toString();
	                	String PLAN_VISIT_NUM       = modelList.get(i).getPLAN_VISIT_NUM().toString();
	                	String VISIT_TYPE=modelList.get(i).getVISIT_TYPE().toString();
	                	if(MONTH_VISIT_PLAN_DTL_ID.equals("")){
	                	    monthvisitService.txInsertChild(MONTH_VISIT_PLAN_ID,VISIT_OBJ_TYPE,PLAN_VISIT_NUM,VISIT_TYPE);
	                	}else{
	                	    monthvisitService.txUpdateChild(MONTH_VISIT_PLAN_DTL_ID,MONTH_VISIT_PLAN_ID,VISIT_OBJ_TYPE,PLAN_VISIT_NUM,VISIT_TYPE);
	                	}
	                }
	            }
	        }catch(ServiceException s){
	        	jsonResult = jsonResult(false, s.getMessage());
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
	     * 数据字典明细批量删除
	     * Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

	        PrintWriter writer = getWriter(response);
	     	UserBean userBean = ParamUtil.getUserBean(request);
	       	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE)){
	       		throw new ServiceException("对不起，您没有权限 !");
	       	}
	        String jsonResult = jsonResult();
	        try {                                                                
	        	String MONTH_VISIT_PLAN_DTL_IDs = ParamUtil.get(request, "MONTH_VISIT_PLAN_DTL_IDs");
	        	String MONTH_VISIT_PLAN_ID      = ParamUtil.get(request, "MONTH_VISIT_PLAN_ID");
	            //批量删除，多个Id之间用逗号隔开
	            String PLAN_VISIT_NUM_TOTAL = monthvisitService.txBatch4DeleteChild(MONTH_VISIT_PLAN_DTL_IDs, userBean,MONTH_VISIT_PLAN_ID);
	            jsonResult = jsonResult(true, PLAN_VISIT_NUM_TOTAL);
	        } catch (Exception e) {
	            jsonResult = jsonResult(false, "删除失败");
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    }
	    
	    /**
	     * 销售计划维护编辑//新增或修改。 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
		public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        
	         PrintWriter writer = getWriter(response);
	         String jsonResult = jsonResult();
	         String parentJsonData = ParamUtil.get(request, "parentJsonData");
	         UserBean userBean = ParamUtil.getUserBean(request);
	         if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean,PVG_EDIT))){
	     		throw new ServiceException("对不起，您没有权限 !");
	     	 }
	         String MONTH_VISIT_PLAN_ID = ParamUtil.utf8Decoder(request, "MONTH_VISIT_PLAN_ID");  
	         MonthvisitModel model = new  MonthvisitModel();
	         if (StringUtils.isNotEmpty(parentJsonData)) {
	             model = new Gson().fromJson(parentJsonData, new TypeToken <MonthvisitModel>() {
	             }.getType());
	         }
	         String jsonData = ParamUtil.get(request, "childJsonData");
	         List <MonthvisitdtlModel>  mxList = null;
	         if (StringUtils.isNotEmpty(jsonData)) {
	             mxList = new Gson().fromJson(jsonData, new TypeToken <List<MonthvisitdtlModel>>() {
	             }.getType());
	         }
	         try {
	        	 monthvisitService.txEdit(MONTH_VISIT_PLAN_ID, model, userBean,mxList);
			}catch(ServiceException s){
				jsonResult = jsonResult(false, s.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult = jsonResult(false, "保存失败");
			}
	         
	         if (null != writer) {
	             writer.write(jsonResult);
	             writer.close();
	         }
	    }    
	    
	    /**
	     * 销售计划维护删除 Description :.
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	      
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE)){
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        String MONTH_VISIT_PLAN_ID = ParamUtil.get(request, "MONTH_VISIT_PLAN_ID");

	        if (StringUtils.isNotEmpty(MONTH_VISIT_PLAN_ID)) {
	            try {
	                boolean flag = monthvisitService.txDelete(MONTH_VISIT_PLAN_ID, userBean);
	                if(flag==true){
	                	monthvisitService.txDeleteChild(MONTH_VISIT_PLAN_ID, userBean);
	                }
	                monthvisitService.clearCaches(MONTH_VISIT_PLAN_ID);
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
   
}
