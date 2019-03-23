package com.hoperun.erp.sale.saleplan.action;

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
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.saleplan.model.SaleplanModel;
import com.hoperun.erp.sale.saleplan.model.SaleplandtlModel;
import com.hoperun.erp.sale.saleplan.service.SaleplanService;
import com.hoperun.sys.model.UserBean;

public class SaleplanAction extends BaseAction {
	
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS  = "BS0021301";
    private static String PVG_EDIT = "BS0021302";
    private static String PVG_DELETE="BS0021303";

    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0021304";
    //流程跟踪
    private static String PVG_TRACE= "BS0021305";
    //批量编辑
    private static String PVG_BATCH="BS0021306";
    //审核模块                             
    private static String PVG_BWS_AUDIT="BS0021401";
    private static String PVG_AUDIT_AUDIT="BS0021402";
    private static String PVG_TRACE_AUDIT="BS0021403";
    //修改
    private static String PVG_EDIT_AUDIT = "BS0021404";
	
	//审批流参数
    private static String AUD_TAB_NAME="ERP_SALE_PLAN";
    private static String AUD_TAB_KEY="SALE_PLAN_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_SALE_PLAN_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
	
	private SaleplanService  saleplanService;
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
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
         
         Map <String, String> params = new TreeMap <String, String>();
         ParamUtil.putStr2Map(request, "SALE_PLAN_NO", params);
         ParamUtil.putStr2Map(request, "PLAN_YEAR", params);
         ParamUtil.putStr2Map(request, "CHANN_NO", params);
         ParamUtil.putStr2Map(request, "CHANN_NAME", params);
         ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
         ParamUtil.putStr2Map(request, "AREA_NO", params);
         ParamUtil.putStr2Map(request, "AREA_NAME", params);
         ParamUtil.putStr2Map(request, "STATE", params);
         ParamUtil.putStr2Map(request, "pageSize", params);
         
         String PLAN_YEAR = ParamUtil.get(request, "YEAR");
         if(PLAN_YEAR.equals("")){
        	 Date  date   = new Date();
             String year  = String.valueOf(date.getYear()+1900);
             params.put("PLAN_YEAR", year);
             request.setAttribute("year", year);
         } else {
        	 params.put("PLAN_YEAR", PLAN_YEAR);
        	 request.setAttribute("year", PLAN_YEAR);
         }
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
 		 page = saleplanService.pageQuery(params, pageNo);
 		 request.setAttribute("pvg",setPvg(userBean));
 		 request.setAttribute("module", module);
         request.setAttribute("params", params);
         request.setAttribute("page", page);  
         request.setAttribute("CHANNS_CHARG", CHANNS_CHARG);
         request.setAttribute("QUERY_CHANN_ID", CHANNS_CHARG);
         request.setAttribute("QUERY_AREA_ID", userBean.getAREA_CHARG());
         
         List list  = new  ArrayList();
         for(int j=2010;j<2031;j++){
       	  list.add(j);
         }
         request.setAttribute("list", list);
//         Date  date   = new Date();
//         String year  = String.valueOf(date.getYear()+1900);
//         request.setAttribute("year", year);
         request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
         request.setAttribute("JGXXID", userBean.getJGXXID());
         return mapping.findForward("list");
    }
    
    
    public ActionForward toListT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        //权限级别
        UserBean userBean = ParamUtil.getUserBean(request);
        
        Map <String, String> params = new TreeMap <String, String>();
        ParamUtil.putStr2Map(request, "SALE_PLAN_NO", params);
        ParamUtil.putStr2Map(request, "PLAN_YEAR", params);
        ParamUtil.putStr2Map(request, "CHANN_NO", params);
        ParamUtil.putStr2Map(request, "AREA_MANAGE_NAME", params);
        ParamUtil.putStr2Map(request, "AREA_NO", params);
        ParamUtil.putStr2Map(request, "STATE", params);
        ParamUtil.putStr2Map(request, "pageSize", params);
        
        String PLAN_YEAR = ParamUtil.get(request, "PLAN_YEAR");
        if(PLAN_YEAR.equals("")){
       	 Date  date   = new Date();
            String year  = String.valueOf(date.getYear()+1900);
            params.put("PLAN_YEAR", year);
            request.setAttribute("year", year);
        } else {
       	    params.put("PLAN_YEAR", PLAN_YEAR);
       	    request.setAttribute("year", PLAN_YEAR);
        }
        
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
		 page = saleplanService.pageQuery(params, pageNo);
		 request.setAttribute("pvg",setPvg(userBean));
		 request.setAttribute("module", module);
        request.setAttribute("params", params);
        request.setAttribute("page", page);  
        request.setAttribute("CHANNS_CHARG", CHANNS_CHARG);
        request.setAttribute("QUERY_CHANN_ID", CHANNS_CHARG);
        request.setAttribute("QUERY_AREA_ID", userBean.getAREA_CHARG());
        
        List list  = new  ArrayList();
        for(int j=2010;j<2031;j++){
      	  list.add(j);
        }
        request.setAttribute("list", list);
        request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
        request.setAttribute("JGXXID", userBean.getJGXXID());
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String SALE_PLAN_ID = ParamUtil.get(request, "SALE_PLAN_ID");
		if (!StringUtil.isEmpty(SALE_PLAN_ID)) {
			List<SaleplandtlModel> result = saleplanService.childQuery(SALE_PLAN_ID);
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
		String SALE_PLAN_ID = ParamUtil.get(request, "SALE_PLAN_ID");
		if (!StringUtil.isEmpty(SALE_PLAN_ID)) {
			Map<String, String> entry = saleplanService
					.load(SALE_PLAN_ID);
			request.setAttribute("rst", entry);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(SALE_PLAN_ID);
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

        String SALE_PLAN_ID = ParamUtil.get(request, "SALE_PLAN_ID");
    	if(!SALE_PLAN_ID.equals("")){
    	 request.setAttribute("SALE_PLAN_ID", SALE_PLAN_ID);
    	 return mapping.findForward("editFrame_T");
    	} else {
         return mapping.findForward("editFrame");
    	}
     }
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward  toParentEditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	 UserBean userBean = ParamUtil.getUserBean(request);
    	 String SALE_PLAN_ID = ParamUtil.get(request, "SALE_PLAN_ID");
    	 if (StringUtils.isNotEmpty(SALE_PLAN_ID)) {
 			Map entry = saleplanService.load(SALE_PLAN_ID);
 			request.setAttribute("rst", entry);
 		 }
    	 request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
    	 request.setAttribute("SALE_PLAN_ID",SALE_PLAN_ID);
    	 List list  = new  ArrayList();
         for(int j=2010;j<2031;j++){
       	  list.add(j);
         }
         request.setAttribute("list", list);
    	 return mapping.findForward("toeditT");
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		 String SALE_PLAN_ID = StringUtil.utf8Decoder(ParamUtil.get(request, "SALE_PLAN_ID"));
		 List list = new ArrayList();
		 for(int j=2010;j<2031;j++){
	       	  list.add(j);
	     }
	     request.setAttribute("list", list);
         // 为空则是新增，否则是修改
         if (StringUtils.isNotEmpty(SALE_PLAN_ID)) {
             Map <String, String> entry = saleplanService.load(SALE_PLAN_ID);
             request.setAttribute("rst", entry);
             return mapping.findForward("toeditT");
         }    
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl", paramUrl);
		
        Date  date   = new Date();
        String year  = String.valueOf(date.getYear()+1900);
        request.setAttribute("year", year);
		return mapping.findForward("toedit");
	}
	
	public ActionForward toEdit1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		 String SALE_PLAN_ID = StringUtil.utf8Decoder(ParamUtil.get(request, "SALE_PLAN_ID"));
         // 为空则是新增，否则是修改
         if (StringUtils.isNotEmpty(SALE_PLAN_ID)) {
             Map <String, String> entry = saleplanService.load(SALE_PLAN_ID);
             request.setAttribute("rst", entry);
             request.setAttribute("isNew", false);
         }   
    	 List list = new  ArrayList();
         for(int j=2010;j<2031;j++){
       	  list.add(j);
         }
         request.setAttribute("list", list);
         return mapping.findForward("toedit");
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String DELIVER_ORDER_ID = ParamUtil.get(request, "selRowId");
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			Map<String, String> result = saleplanService
					.load(DELIVER_ORDER_ID);
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
    	 String currentNo = "ZXSQ"+System.currentTimeMillis();     //自动申请装修申请单号
         String selRowId = StringUtil.utf8Decoder(ParamUtil.get(request, "selRowId"));//ParamUtil.utf8Decoder(request, "BMGZID");//;
         String SALE_PLAN_ID = StringUtil.utf8Decoder(ParamUtil.get(request, "SALE_PLAN_ID"));
         // 为空则是新增，否则是修改
         if (StringUtils.isNotEmpty(SALE_PLAN_ID)) {
             Map <String, String> entry = saleplanService.load(SALE_PLAN_ID);
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
    	 List list = new  ArrayList();
         for(int j=2010;j<2031;j++){
       	  list.add(j);
         }
         request.setAttribute("list", list);
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
	    	
	    	String SALE_PLAN_ID = ParamUtil.utf8Decoder(request, "SALE_PLAN_ID");//ParamUtil.get(request, "BMGZID");
	        if (StringUtils.isNotEmpty(SALE_PLAN_ID)) {
	            List<SaleplandtlModel> result = saleplanService.childQuery(SALE_PLAN_ID);
	            request.setAttribute("rst", result);
	        }
	        request.setAttribute("SALE_PLAN_ID",SALE_PLAN_ID);
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
	    	
	    	String SALE_PLAN_ID = ParamUtil.utf8Decoder(request, "SALE_PLAN_ID");//ParamUtil.get(request, "BMGZID");
	        if (StringUtils.isNotEmpty(SALE_PLAN_ID)) {
	            List<SaleplandtlModel> result = saleplanService.childQuery(SALE_PLAN_ID);
	            request.setAttribute("rst", result);
	        }
	        request.setAttribute("SALE_PLAN_ID",SALE_PLAN_ID);
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
	        
	        String SALE_PLAN_DTL_IDS = request.getParameter("SALE_PLAN_DTL_IDS");
	        String PLAN_YEAR         = request.getParameter("PLAN_YEAR");
	        // 没有编码规则明细Id可以直接跳转。
	        if (StringUtils.isNotEmpty(SALE_PLAN_DTL_IDS)) {
	            List <SaleplandtlModel> list = saleplanService.loadByIds(SALE_PLAN_DTL_IDS);
	            request.setAttribute("rst", list);
	        }
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
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        int errorFlg = 0;
	        try {                                                          
	        	String SALE_PLAN_ID = ParamUtil.utf8Decoder(request, "SALE_PLAN_ID"); 
	            String jsonDate = request.getParameter("childJsonData");
	            if (StringUtils.isNotEmpty(jsonDate)) {
	                List <SaleplandtlModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List<SaleplandtlModel>>() {
	                }.getType());
	                for(int i=0;i<modelList.size();i++){
	                	String SALE_PLAN_DTL_ID = modelList.get(i).getSALE_PLAN_DTL_ID().toString();
	                	String year       = modelList.get(i).getPLAN_YEAR().toString();
	                	String month      = modelList.get(i).getPLAN_MONTH().toString();
	                	String planAmount = modelList.get(i).getPLAN_SALE_AMOUNT().toString();
	                	String chanAmount = modelList.get(i).getCHANN_SALE_AMOUNT().toString();
	                	String str = "";
	                	if(SALE_PLAN_DTL_ID.equals("")){
	                	    saleplanService.insertChild(SALE_PLAN_ID,year,month, planAmount, chanAmount);
	                	}else{
	                	    saleplanService.updateChild(SALE_PLAN_DTL_ID,SALE_PLAN_ID,year, month, planAmount, chanAmount);
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
	        String jsonResult = jsonResult();
	        try {                                                                
	        	String SALE_PLAN_DTL_IDs = ParamUtil.get(request, "SALE_PLAN_DTL_IDs");
	            //批量删除，多个Id之间用逗号隔开
	            saleplanService.txBatch4DeleteChild(SALE_PLAN_DTL_IDs, userBean);
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
	    @SuppressWarnings("unchecked")
		public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        
	         PrintWriter writer = getWriter(response);
	         String jsonResult = jsonResult();
	         String parentJsonData = ParamUtil.get(request, "jsonData");
	         
	         UserBean userBean = ParamUtil.getUserBean(request);
	         String SALE_PLAN_ID = ParamUtil.utf8Decoder(request, "SALE_PLAN_ID");  
	         SaleplanModel model = new SaleplanModel();
	         if (StringUtils.isNotEmpty(parentJsonData)) {
	             model = new Gson().fromJson(parentJsonData, new TypeToken <SaleplanModel>() {
	             }.getType());
	         }
	         String jsonData = ParamUtil.get(request, "childJsonData");
	         List <SaleplandtlModel>  mxList = null;
	         if (StringUtils.isNotEmpty(jsonData)) {
	             mxList = new Gson().fromJson(jsonData, new TypeToken <List<SaleplandtlModel>>() {
	             }.getType());
	         }
	         Map<String,String> params = new HashMap<String,String>();
	         params.put("PLAN_YEAR", model.getPLAN_YEAR());
	         params.put("CHANN_ID", model.getCHANN_ID());
	         if("".equals(SALE_PLAN_ID)){
		         List<SaleplanModel>  list = saleplanService.queryChannAndYear(params);
		         if(list.size()==0){
		           saleplanService.txEdit(SALE_PLAN_ID, model, userBean,mxList);
		         } else{
		        	 jsonResult = jsonResult(false, "年份和渠道信息已经存在!");
		         }
	         }else{
	        	 saleplanService.txEdit(SALE_PLAN_ID, model, userBean,mxList);
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
	    @SuppressWarnings("unchecked")
		public void batchUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        
	         PrintWriter writer = getWriter(response);
	         String jsonResult = jsonResult();
	         String parentJsonData = ParamUtil.get(request, "jsonData");
	         
	         UserBean userBean = ParamUtil.getUserBean(request);
	         String SALE_PLAN_IDs = ParamUtil.utf8Decoder(request, "SALE_PLAN_IDs");
	         String YEAR_PLAN_AMOUNTs = ParamUtil.utf8Decoder(request, "YEAR_PLAN_AMOUNTs");
	         String JAN_AMOUNTs = ParamUtil.utf8Decoder(request, "JAN_AMOUNTs");
	         String FEB_AMOUNTs = ParamUtil.utf8Decoder(request, "FEB_AMOUNTs");
	         String MAR_AMOUNTs = ParamUtil.utf8Decoder(request, "MAR_AMOUNTs");
	         String FIRST_QUARTER_AMOUNTs = ParamUtil.utf8Decoder(request, "FIRST_QUARTER_AMOUNTs");
	         String APR_AMOUNTs = ParamUtil.utf8Decoder(request, "APR_AMOUNTs");
	         String MAY_AMOUNTs = ParamUtil.utf8Decoder(request, "MAY_AMOUNTs");
	         String JUN_AMOUNTs = ParamUtil.utf8Decoder(request, "JUN_AMOUNTs");
	         String SECOND_QUARTER_AMOUNTs = ParamUtil.utf8Decoder(request, "SECOND_QUARTER_AMOUNTs");
	         String JUL_AMOUNTs = ParamUtil.utf8Decoder(request, "JUL_AMOUNTs");
	         String AUG_AMOUNTs = ParamUtil.utf8Decoder(request, "AUG_AMOUNTs");
	         String SEP_AMOUNTs = ParamUtil.utf8Decoder(request, "SEP_AMOUNTs");
	         String THIRD_QUARTER_AMOUNTs = ParamUtil.utf8Decoder(request, "THIRD_QUARTER_AMOUNTs");
	         String OCT_AMOUNTs = ParamUtil.utf8Decoder(request, "OCT_AMOUNTs");
	         String NOV_AMOUNTs = ParamUtil.utf8Decoder(request, "NOV_AMOUNTs");
	         String DEC_AMOUNTs = ParamUtil.utf8Decoder(request, "DEC_AMOUNTs");
	         String FOURTH_QUARTER_AMOUNTs = ParamUtil.utf8Decoder(request, "FOURTH_QUARTER_AMOUNTs");
	         String AREA_MANAGE_NAMEs   = ParamUtil.utf8Decoder(request, "AREA_MANAGE_NAMEs");
	         String AREA_MANAGE_IDs     = ParamUtil.utf8Decoder(request, "AREA_MANAGE_IDs");
	         
	         String[] STR_SALE_PLAN_ID  = SALE_PLAN_IDs.split(",");
	         String[] STR_YEAR_PLAN_AMOUNT = YEAR_PLAN_AMOUNTs.split(",");
	         String[] STR_JAN_AMOUNT = JAN_AMOUNTs.split(",");
	         String[] STR_FEB_AMOUNT = FEB_AMOUNTs.split(",");
	         String[] STR_MAR_AMOUNT = MAR_AMOUNTs.split(",");
	         String[] STR_FIRST_QUARTER_AMOUNT = FIRST_QUARTER_AMOUNTs.split(",");
	         String[] STR_APR_AMOUNT = APR_AMOUNTs.split(",");
	         String[] STR_MAY_AMOUNT = MAY_AMOUNTs.split(",");
	         String[] STR_JUN_AMOUNT = JUN_AMOUNTs.split(",");
	         String[] STR_SECOND_QUARTER_AMOUNT = SECOND_QUARTER_AMOUNTs.split(",");
	         String[] STR_JUL_AMOUNT = JUL_AMOUNTs.split(",");
	         String[] STR_AUG_AMOUNT = AUG_AMOUNTs.split(",");
	         String[] STR_SEP_AMOUNT = SEP_AMOUNTs.split(",");
	         String[] STR_THIRD_QUARTER_AMOUNT = THIRD_QUARTER_AMOUNTs.split(",");
	         String[] STR_OCT_AMOUNTT = OCT_AMOUNTs.split(",");
	         String[] STR_NOV_AMOUNT  = NOV_AMOUNTs.split(",");
	         String[] STR_DEC_AMOUNT  = DEC_AMOUNTs.split(",");
	         String[] STR_FOURTH_QUARTER_AMOUNT = FOURTH_QUARTER_AMOUNTs.split(",");
	         String[] AREA_MANAGE_NAME= AREA_MANAGE_NAMEs.split(",");
	         String[] AREA_MANAGE_ID  = AREA_MANAGE_IDs.split(",");
	         for(int i=0;i<STR_YEAR_PLAN_AMOUNT.length;i++){
	        	 SaleplanModel  salePlan = new SaleplanModel();
	        	 salePlan.setSALE_PLAN_ID(STR_SALE_PLAN_ID[i]);
	        	 salePlan.setYEAR_PLAN_AMOUNT(STR_YEAR_PLAN_AMOUNT[i]);
	        	 salePlan.setJAN_AMOUNT(STR_JAN_AMOUNT[i]);
	        	 salePlan.setFEB_AMOUNT(STR_FEB_AMOUNT[i]);
	        	 salePlan.setMAR_AMOUNT(STR_MAR_AMOUNT[i]);
	        	 salePlan.setFIRST_QUARTER_AMOUNT(STR_FIRST_QUARTER_AMOUNT[i]);
	        	 salePlan.setAPR_AMOUNT(STR_APR_AMOUNT[i]);
	        	 salePlan.setMAY_AMOUNT(STR_MAY_AMOUNT[i]);
	        	 salePlan.setJUN_AMOUNT(STR_JUN_AMOUNT[i]);
	        	 salePlan.setSECOND_QUARTER_AMOUNT(STR_SECOND_QUARTER_AMOUNT[i]);
	        	 salePlan.setJUL_AMOUNT(STR_JUL_AMOUNT[i]);
	        	 salePlan.setAUG_AMOUNT(STR_AUG_AMOUNT[i]);
	        	 salePlan.setSEP_AMOUNT(STR_SEP_AMOUNT[i]);
	        	 salePlan.setTHIRD_QUARTER_AMOUNT(STR_THIRD_QUARTER_AMOUNT[i]);
	        	 salePlan.setOCT_AMOUNT(STR_OCT_AMOUNTT[i]);
	        	 salePlan.setNOV_AMOUNT(STR_NOV_AMOUNT[i]);
	        	 salePlan.setDEC_AMOUNT(STR_DEC_AMOUNT[i]);
	        	 salePlan.setFOURTH_QUARTER_AMOUNT(STR_FOURTH_QUARTER_AMOUNT[i]);
	        	 salePlan.setAREA_MANAGE_NAME(AREA_MANAGE_NAME[i]);
	        	 salePlan.setAREA_MANAGE_ID(AREA_MANAGE_ID[i]);
	        	 saleplanService.batchUpdate(salePlan, userBean);
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
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        String SALE_PLAN_ID = ParamUtil.get(request, "SALE_PLAN_ID");

	        if (StringUtils.isNotEmpty(SALE_PLAN_ID)) {
	            try {
	                boolean flag = saleplanService.txDelete(SALE_PLAN_ID, userBean);
	                if(flag==true){
	                	saleplanService.txDeleteChild(SALE_PLAN_ID, userBean);
	                }
	                saleplanService.clearCaches(SALE_PLAN_ID);
	            } catch (RuntimeException e) {
	                jsonResult = jsonResult(false, "删除失败");
	            }
	        }
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	    }
	    
		public void loadModel(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
	         
			UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        try{
	             String PLAN_YEAR = ParamUtil.get(request, "PLAN_YEAR");
	             List<SaleplanModel>  result = saleplanService.queryJudgeModel(PLAN_YEAR);
	             jsonResult = new Gson().toJson(new Result(true, result, ""));
	        }catch (Exception e) {
	        	jsonResult = jsonResult(false, "保存失败");
			}
	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
		}
		
	    public ActionForward toBatchList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

	         //权限级别
	         UserBean userBean = ParamUtil.getUserBean(request);
	         
	         Map <String, String> params = new TreeMap <String, String>();
	         ParamUtil.putStr2Map(request, "PLAN_YEAR", params);
	         ParamUtil.putStr2Map(request, "AREA_NO", params);
	         ParamUtil.putStr2Map(request, "AREA_NAME", params);
	         ParamUtil.putStr2Map(request, "CHANN_NO", params);
	         ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	         ParamUtil.putStr2Map(request, "pageSize", params);
	         
	         String PLAN_YEAR = ParamUtil.get(request, "PLAN_YEAR");
	         if(PLAN_YEAR.equals("")){
	        	 Date  date   = new Date();
	             String year  = String.valueOf(date.getYear()+1900);
	             params.put("PLAN_YEAR", year);
	         } else {
	        	 params.put("PLAN_YEAR", PLAN_YEAR);
	         }
	         
	        params.put("CHANN_NO", ParamUtil.utf8Decoder(request, "CHANN_NO"));
	     	String CHANN_NO=ParamUtil.utf8Decoder(request, "CHANN_NO");
	     	params.put("CHANN_NOQuery", StringUtil.creCon("t.CHANN_NO", CHANN_NO, ","));
	     	params.put("CHANN_NAME", ParamUtil.utf8Decoder(request, "CHANN_NAME"));
	     	String CHANN_NAME=ParamUtil.utf8Decoder(request, "CHANN_NAME");
	     	params.put("CHANN_NAMEQuery", StringUtil.creCon("t.CHANN_NAME", CHANN_NAME, ","));
	     	
	     	params.put("AREA_NO", ParamUtil.utf8Decoder(request, "AREA_NO"));
	     	String AREA_NO=ParamUtil.utf8Decoder(request, "AREA_NO");
	     	params.put("AREA_NOQuery", StringUtil.creCon("s.AREA_NO", AREA_NO, ","));
	     	params.put("AREA_NAME", ParamUtil.utf8Decoder(request, "AREA_NAME"));
	     	String AREA_NAME=ParamUtil.utf8Decoder(request, "AREA_NAME");
	     	params.put("AREA_NAMEQuery", StringUtil.creCon("s.AREA_NAME", AREA_NAME, ","));
	         
	         String search = ParamUtil.get(request,"search");
	 	     String module = ParamUtil.get(request,"module");
	 	     String STATE = ParamUtil.get(request,"STATE");
			 StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		     //权限级别和审批流的封装
		     params.put("QXJBCON",qx.toString());
		     IListPage page = null;
		     request.setAttribute("module",module);
		     //渠道分管
		     String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		     params.put("CHANNS_CHARG", CHANNS_CHARG);
		     int pageNo = ParamUtil.getInt(request, "pageNo", 1);
	 		 page = saleplanService.pageBatch(params, pageNo);
	 		 request.setAttribute("pvg",setPvg(userBean));
	 		 request.setAttribute("module", module);
	         request.setAttribute("params", params);
	         request.setAttribute("page", page);  
	         request.setAttribute("CHANNS_CHARG", CHANNS_CHARG);
	         request.setAttribute("QUERY_CHANN_ID", CHANNS_CHARG);
	         request.setAttribute("QUERY_AREA_ID", userBean.getAREA_CHARG());
	         
	         List list  = new  ArrayList();
	         for(int j=2010;j<2031;j++){
	       	  list.add(j);
	         }
	         request.setAttribute("list", list);
	         Date  date   = new Date();
	         String year  = String.valueOf(date.getYear()+1900);
	         request.setAttribute("year", year);
	         request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
	         return mapping.findForward("batchList");
	    }
		
	    public ActionForward toBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

	         //权限级别
	         UserBean userBean = ParamUtil.getUserBean(request);
	         
	         Map <String, String> params = new TreeMap <String, String>();
	         ParamUtil.putStr2Map(request, "PLAN_YEAR", params);
	         ParamUtil.putStr2Map(request, "AREA_NO", params);
	         ParamUtil.putStr2Map(request, "AREA_NAME", params);
	         ParamUtil.putStr2Map(request, "CHANN_NO", params);
	         ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	         ParamUtil.putStr2Map(request, "pageSize", params);
	         
	         String PLAN_YEAR = ParamUtil.get(request, "PLAN_YEAR");
	         if(PLAN_YEAR.equals("")){
	        	 Date  date   = new Date();
	             String year  = String.valueOf(date.getYear()+1900);
	             params.put("PLAN_YEAR", year);
	         } else {
	        	 params.put("PLAN_YEAR", PLAN_YEAR);
	         }
	         
	         String search = ParamUtil.get(request,"search");
	 	     String module = ParamUtil.get(request,"module");
	 	     String STATE = ParamUtil.get(request,"STATE");
			 StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		     //权限级别和审批流的封装
		     params.put("QXJBCON",qx.toString());
		     IListPage page = null;
		     request.setAttribute("module",module);
		     //渠道分管
		     String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		     params.put("CHANNS_CHARG", CHANNS_CHARG);
		     int pageNo = ParamUtil.getInt(request, "pageNo", 1);
	 		 page = saleplanService.pageBatch(params, pageNo);
	 		 request.setAttribute("pvg",setPvg(userBean));
	 		 request.setAttribute("module", module);
	         request.setAttribute("params", params);
	         request.setAttribute("page", page);  
	         request.setAttribute("CHANNS_CHARG", CHANNS_CHARG);
	         request.setAttribute("QUERY_CHANN_ID", CHANNS_CHARG);
	         request.setAttribute("QUERY_AREA_ID", userBean.getAREA_CHARG());
	         
	         List list  = new  ArrayList();
	         for(int j=2010;j<2031;j++){
	       	  list.add(j);
	         }
	         request.setAttribute("list", list);
	         Date  date   = new Date();
	         String year  = String.valueOf(date.getYear()+1900);
	         request.setAttribute("year", year);
	         request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
	         return mapping.findForward("batch");
	    }
    
		/**
		 * * 设置权限MapS
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
	        pvgMap.put("PVG_BATCH", QXUtil.checkPvg(userBean, PVG_BATCH));
	        pvgMap.put("PVG_EDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_EDIT_AUDIT));
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
		    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
		    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
		    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
       
	   /**
	    * @return
	    */
	   public SaleplanService getSaleplanService() {
			return saleplanService;
	   }
	
	   /**
	    * @param saleplanService
	    */
	   public void setSaleplanService(SaleplanService saleplanService) {
			this.saleplanService = saleplanService;
	   }
}
