package com.hoperun.erp.sale.erpadvcorder.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hoperun.erp.sale.erpadvcorder.model.ErpAdvcGiftModel;
import com.hoperun.erp.sale.erpadvcorder.model.ErpAdvcorderChldModel;
import com.hoperun.erp.sale.erpadvcorder.model.ErpAdvcorderModel;
import com.hoperun.erp.sale.erpadvcorder.model.ErpPaymentDtlModel;
import com.hoperun.erp.sale.erpadvcorder.service.ErpAdvcorderService;
import com.hoperun.sys.model.UserBean;
/**
 * 总部-预订单
 * @author zhang_zhongbin
 *
 */
public class ErpAdvcorderAction extends BaseAction{

	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0023101";
	private static String PVG_EDIT = "BS0023102";
	private static String PVG_DELETE = "BS0023103";
	
	  //启用,停用
    private static String PVG_START_STOP = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "BS0023104";
	private static String PVG_TRACE = "BS0023105";

	// 审核模块
	private static String PVG_BWS_AUDIT = "BS0023201";
	private static String PVG_AUDIT_AUDIT = "BS0023202";
	private static String PVG_TRACE_AUDIT = "BS0023203";
	// 审批流参数
	private static String AUD_TAB_NAME = "ERP_ADVC_ORDER";
	private static String AUD_TAB_KEY = "ADVC_ORDER_ID";
	private static String AUD_BUSS_STATE = "STATE";
	private static String AUD_BUSS_TYPE = "ERP_ADVC_ORDER_AUDIT";
	private static String AUD_FLOW_INS = "com.hoperun.sys.service.PublicFlowInterface";
	
	
	private ErpAdvcorderService erpAdvcorderService;

	
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl",paramUrl );
	 
		return mapping.findForward("frames");
	}
	
	
	/**
	 * *list页面
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
	    ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "MARKETING_ACT_NO", params);
	    ParamUtil.putStr2Map(request, "MARKETING_ACT_NAME", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params); 
	    ParamUtil.putStr2Map(request, "TEL", params); 
	    ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);  
	    ParamUtil.putStr2Map(request, "SALE_DATE_END", params);  
	    ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);  
	    
	    
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	   
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		//权限级别和审批流的封装
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,module,
				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		
		sb.append(" and u.LEDGER_ID ='").append(userBean.getLoginZTXXID()).append("'");
		if("sh".equals(module)){
		}
		if("wh".equals(module)){
			 
		}
		 
	    params.put("QXJBCON", sb.toString());
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = erpAdvcorderService.pageQuery(params, pageNo);
		
		request.setAttribute("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
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
   public ActionForward childList(ActionMapping mapping, ActionForm form, 
   		HttpServletRequest request, HttpServletResponse response) {
   	
	   UserBean userBean = ParamUtil.getUserBean(request);
       if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
    		   &&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
	   		throw new ServiceException("对不起，您没有权限 !");
	   }
       String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
       if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
       	 List <ErpAdvcorderChldModel> result = erpAdvcorderService.childQuery(ADVC_ORDER_ID);
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
   public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, 
   		HttpServletRequest request, HttpServletResponse response) {
   	UserBean userBean = ParamUtil.getUserBean(request);
   	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
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
	 * @return the action forward
	 */
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
	   	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
	   		throw new ServiceException("对不起，您没有权限 !");
	   	}	
		String ADVC_ORDER_ID = ParamUtil.get(request, "selRowId");
		Map<String,Object> entry = new HashMap<String,Object>();
		if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
			entry = erpAdvcorderService.load(ADVC_ORDER_ID);
		} 
		request.setAttribute("rst", entry);
		request.setAttribute("pvg",setPvg(userBean));
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
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
        	 List <ErpAdvcorderChldModel> result = erpAdvcorderService.childQuery(ADVC_ORDER_ID);
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
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
    	//多个Id批量查询，用逗号隔开
        String ADVC_ORDER_DTL_IDS = request.getParameter("ADVC_ORDER_DTL_IDS");
        if (!StringUtil.isEmpty(ADVC_ORDER_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("ADVC_ORDER_DTL_IDS",ADVC_ORDER_DTL_IDS);
		  params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
          List <ErpAdvcorderChldModel> list = erpAdvcorderService.loadChilds(params);
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
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean,PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            ErpAdvcorderModel model = new Gson().fromJson(parentJsonData, 
            		new TypeToken <ErpAdvcorderModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            
            List <ErpAdvcorderChldModel> chldList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
            	chldList = new Gson().fromJson(jsonDate, 
            			new TypeToken <List <ErpAdvcorderChldModel>>(){}.getType());
            }
            erpAdvcorderService.txEdit(ADVC_ORDER_ID, model, chldList, userBean);
            jsonResult = jsonResult(true, "保存成功");
            
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
     * * 子表 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void childEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <ErpAdvcorderChldModel> modelList = new Gson().fromJson(
                		jsonDate, new TypeToken <List <ErpAdvcorderChldModel>>(){}.getType());
                erpAdvcorderService.txChildEdit(ADVC_ORDER_ID, modelList,userBean);
                jsonResult = jsonResult(true, "保存成功");
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
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            erpAdvcorderService.txDelete(params);
            erpAdvcorderService.clearCaches(params);
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
    public void childDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
            String ADVC_ORDER_DTL_IDS = request.getParameter("ADVC_ORDER_DTL_IDS");
            Map <String, String> params = new HashMap <String, String>();
            params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
            params.put("ADVC_ORDER_DTL_IDS", ADVC_ORDER_DTL_IDS);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            erpAdvcorderService.txBatch4DeleteChild(params);
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
        if(Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS)
        		&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
			Map<String,Object> entry = erpAdvcorderService.load(ADVC_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		 
		return mapping.findForward("todetail");
	}
	
	
	 
	 /**
     * *  提交
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toCommit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
            if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
            	List <ErpAdvcorderChldModel> list = erpAdvcorderService.childQuery(ADVC_ORDER_ID);
            	if(null == list || list.isEmpty()){
            		throw new ServiceException("预订单明细未填写！");
            	}
            	Map<String,Object>enty = erpAdvcorderService.load(ADVC_ORDER_ID);
            	double ADVC_AMOUNT = StringUtil.getDouble(enty.get("ADVC_AMOUNT"));
            	Map<String,String>paramMap = new HashMap<String,String>();
            	paramMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
            	double PAY_AMONT = erpAdvcorderService.querySumPaymentDel(paramMap);
            	if(0 == PAY_AMONT){
            		throw new ServiceException("付款明细未填写！");
            	}
            	if(ADVC_AMOUNT != PAY_AMONT){
            		throw new ServiceException("付款明细与订金金额不等！");
            	}
            	
            }
            jsonResult = jsonResult(true, "提交成功");
		}catch (ServiceException e) {
            jsonResult = jsonResult(false, e.getMessage());
        } catch (Exception e) {
            jsonResult = jsonResult(false, "提交失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    //============================================付款明细======================================
    /**
     * * 付款明细列表
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward paymentList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	
 	   UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
     		   &&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
 	   		throw new ServiceException("对不起，您没有权限 !");
 	   }
        String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
        	 List <ErpPaymentDtlModel> result = erpAdvcorderService.paymentQuery(ADVC_ORDER_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("paymentlist");
    }
    

    
    /**
     * * to 直接编辑付款明细页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toPaymentEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String PAYMENT_DTL_IDS = request.getParameter("PAYMENT_DTL_IDS");
        if (!StringUtil.isEmpty(PAYMENT_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("PAYMENT_DTL_IDS",PAYMENT_DTL_IDS);
		  params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
          List <ErpPaymentDtlModel> list = erpAdvcorderService.loadPayments(params);
          request.setAttribute("rst", list);
        }
        return mapping.findForward("paymentedit");
    }
    
  
    /**
     * * 付款明细子表 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void paymentEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <ErpPaymentDtlModel> modelList = new Gson().fromJson(
                		jsonDate, new TypeToken <List <ErpPaymentDtlModel>>(){}.getType());
                erpAdvcorderService.txPaymentEdit(ADVC_ORDER_ID, modelList,userBean);
                jsonResult = jsonResult(true, "保存成功");
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
     * * 付款明细批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void paymentDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
            String PAYMENT_DTL_IDS = request.getParameter("PAYMENT_DTL_IDS");
            Map <String, String> params = new HashMap <String, String>();
            params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
            params.put("PAYMENT_DTL_IDS", PAYMENT_DTL_IDS);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            erpAdvcorderService.txBatch4DeletePayment(params);
		} catch (Exception e) {
            jsonResult = jsonResult(false, "删除失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    //============================================赠品======================================
    
    /**
     * * 付款明细列表
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward giftList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	
 	   UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)
     		   &&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
 	   		throw new ServiceException("对不起，您没有权限 !");
 	   }
        String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
        	 List <ErpAdvcGiftModel> result = erpAdvcorderService.giftQuery(ADVC_ORDER_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("giftlist");
    }
    

    
    /**
     * * to 直接编辑付款明细页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toGiftEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
    	//多个Id批量查询，用逗号隔开
        String ERP_ADVC_GIFT_DTL_IDS = request.getParameter("ERP_ADVC_GIFT_DTL_IDS");
        if (!StringUtil.isEmpty(ERP_ADVC_GIFT_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("ERP_ADVC_GIFT_DTL_IDS",ERP_ADVC_GIFT_DTL_IDS);
		  params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
          List <ErpAdvcGiftModel> list = erpAdvcorderService.loadGifts(params);
          request.setAttribute("rst", list);
        }
        return mapping.findForward("giftedit");
    }
    
    
    /**
     * * 付款明细子表 新增/修改数据
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void giftEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <ErpAdvcGiftModel> modelList = new Gson().fromJson(
                		jsonDate, new TypeToken <List <ErpAdvcGiftModel>>(){}.getType());
                erpAdvcorderService.txGiftEdit(ADVC_ORDER_ID, modelList,userBean);
                jsonResult = jsonResult(true, "保存成功");
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
     * * 付款明细批量删除
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void giftDelete(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String ERP_ADVC_GIFT_DTL_IDS = request.getParameter("ERP_ADVC_GIFT_DTL_IDS");
            Map <String, String> params = new HashMap <String, String>();
            params.put("ERP_ADVC_GIFT_DTL_IDS", ERP_ADVC_GIFT_DTL_IDS);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            erpAdvcorderService.txBatch4DeleteGift(params);
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
	 * * 设置权限Map
	 * 
	 * @param UserBean
	 *            the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
	 
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}
	
	public ErpAdvcorderService getErpAdvcorderService() {
		return erpAdvcorderService;
	}


	public void setErpAdvcorderService(ErpAdvcorderService erpAdvcorderService) {
		this.erpAdvcorderService = erpAdvcorderService;
	}
	
	
	
	
	
	
	

}
