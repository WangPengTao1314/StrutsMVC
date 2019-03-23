/**
 * prjName:喜临门营销平台
 * ucName:推广费用申请单维护
 * fileName:PromoexpenAction
*/
package com.hoperun.drp.oamg.promoexpen.action;
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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.promoexpen.model.PromoexpenModel;
import com.hoperun.drp.oamg.promoexpen.service.PromoexpenService;
import com.hoperun.erp.sale.budgetquota.model.BudgetquotaModel;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-24 10:59:55
 */
public class PromoexpenAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(PromoexpenAction.class);
	/** 权限对象**/
	/** 维护*/
	 //增删改查
    private static String PVG_BWS="BS0020901";
    private static String PVG_EDIT="BS0020902";
    private static String PVG_DELETE="BS0020903";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0020904";
    private static String PVG_TRACE="BS0020905";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0020601";
    private static String PVG_AUDIT_AUDIT="BS0020602";
    private static String PVG_TRACE_AUDIT="BS0020603";
    private static String PVG_EDIT_AUDIT ="BS0020605";
    //审批流参数
    private static String AUD_TAB_NAME="ERP_PRMT_COST_REQ";
    private static String AUD_TAB_KEY="PRMT_COST_REQ_ID";
	private static String AUD_BUSS_STATE="STATE";
    private static String AUD_BUSS_TYPE="ERP_PRMT_COST_REQ_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.drp.oamg.promoexpen.service.impl.PromoexpenFlowInterface";
    /**审批 end**/
    /** 业务service*/
	private PromoexpenService promoexpenService;
    /**
	 * Sets the Promoexpen service.
	 * @param PromoexpenService the new Promoexpen service
	 */
	public void setPromoexpenService(PromoexpenService promoexpenService) {
		this.promoexpenService = promoexpenService;
	}
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		request.setAttribute("audit", request.getParameter("audit"));
		
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
	   UserBean userBean =  ParamUtil.getUserBean(request);
       if(Consts.FUN_CHEK_PVG&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
       {
    		throw new ServiceException("对不起，您没有权限 !");
       }
	   Map<String,String> params = new HashMap<String,String>();
	   ParamUtil.putStr2Map(request, "PRMT_COST_REQ_NO", params);
	   ParamUtil.putStr2Map(request, "STATE", params);
	   ParamUtil.putStr2Map(request, "REQ_NAME", params);
	   ParamUtil.putStr2Map(request, "AREA_NAME", params);
	   ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	   ParamUtil.putStr2Map(request, "REQ_DATE", params);
       //只查询0的记录。1为删除。0为正常
       params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
       String search = ParamUtil.get(request,"search");
	   String module = ParamUtil.get(request,"module");
	   String STATE = ParamUtil.get(request,"STATE");
 
		//权限级别和审批流的封装和状态过滤
       StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
	   StringBuffer sb = new StringBuffer();
	   sb.append(StringUtil.getStrQX("u",strQx.toString()));
	   //params.put("QXJBCON", sb.toString());
	   //字段排序
	   ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
	   int pageNo = ParamUtil.getInt(request, "pageNo", 1);
	   ParamUtil.putStr2Map(request, "pageSize", params);
	   if("1".equals(userBean.getIS_DRP_LEDGER())){
		    //维护
			if("wh".equals(module)){
				params.put("CHANN_ID", userBean.getLoginZTXXID());
			}else {
				params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());//添加渠道分管
			}
	   }else{
		   params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());//添加渠道分管 
	   }
	   
	   if(module.equals("wh") || module.equals("")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}
			}else{
				strQx.append(" and STATE in('未提交','撤销','否决') ");
			}
			params.put("QXJBCON",strQx.toString());
		 }
		 if(module.equals("sh")){
			String strSql = strQx.toString().substring(strQx.toString().indexOf("(")+1,strQx.toString().lastIndexOf(")"));
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
	    if(module.equals("sh")){
 		   page =  promoexpenService.pageQuerySH(params, pageNo);
		} else {
			page = promoexpenService.pageQuery(params, pageNo);
	    }
		//IListPage page = promoexpenService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("audit",request.getParameter("audit"));
		request.setAttribute("module", module);
		request.setAttribute("search", search);
		return mapping.findForward("list");
	}
	
	
	public ActionForward toListByParam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	   UserBean userBean =  ParamUtil.getUserBean(request);
       if(Consts.FUN_CHEK_PVG&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
       {
    		throw new ServiceException("对不起，您没有权限 !");
       }
	   Map<String,String> params = new HashMap<String,String>();
	   ParamUtil.putStr2Map(request, "PRMT_COST_REQ_NO", params);
	   ParamUtil.putStr2Map(request, "STATE", params);
	   ParamUtil.putStr2Map(request, "REQ_NAME", params);
	   ParamUtil.putStr2Map(request, "AREA_NAME", params);
	   ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	   ParamUtil.putStr2Map(request, "REQ_DATE", params);
       //只查询0的记录。1为删除。0为正常
       params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
       String search = ParamUtil.get(request,"search");
	   String module = ParamUtil.get(request,"module");
	   String PRMT_COST_REQ_NO_T = ParamUtil.get(request, "PRMT_COST_REQ_NO");
	   params.put("PRMT_COST_REQ_NO", PRMT_COST_REQ_NO_T);
 
	   //权限级别和审批流的封装和状态过滤
	    StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
	    //params.put("QXJBCON", sb.toString());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
	    if("1".equals(userBean.getIS_DRP_LEDGER())){
		    //维护
			if("wh".equals(module) || "".equals(module)){
				params.put("CHANN_ID", userBean.getLoginZTXXID());
			}else {
				params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());//添加渠道分管
			}
	    }else{
		   params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());//添加渠道分管 
	    }
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
	    //params.put("CHANNS_CHARG", CHANNS_CHARG);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		if(PRMT_COST_REQ_NO_T.equals("")){
	      params.put("QXJBCON",strQx.toString());
		}
		IListPage page = promoexpenService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("audit",request.getParameter("audit"));
		request.setAttribute("module", module);
		request.setAttribute("search", search);
		return mapping.findForward("list");
	}
	
	// 导出
	@SuppressWarnings("unchecked")
	public void expertExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "PRMT_COST_REQ_NO", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "REQ_NAME", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "REQ_DATE", params);
		
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
        String module = ParamUtil.get(request, "module");
        String STATE  = ParamUtil.get(request, "state");
        
      //权限级别和审批流的封装和状态过滤
        StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module,
 				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
 				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
 	   StringBuffer sb = new StringBuffer();
 	   sb.append(StringUtil.getStrQX("u",strQx.toString()));
 	   //params.put("QXJBCON", sb.toString());
 	   //字段排序
// 	   ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
//		//权限级别和审批流的封装
//	    StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,"",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
//		StringBuffer sb = new StringBuffer();
//		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		//params.put("QXJBCON", sb.toString());
		
		if("1".equals(userBean.getIS_DRP_LEDGER())){
		    //维护
			if("wh".equals(module)){
				params.put("CHANN_ID", userBean.getLoginZTXXID());
			}else {
				params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());//添加渠道分管
			}
	   }else{
		   params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());//添加渠道分管 
	   }
	   
	   if(module.equals("wh") || module.equals("")){
			if(!StringUtil.isEmpty(search) ){
				if(!StringUtil.isEmpty(STATE)){
					params.put("STATE", STATE);
				}
			}else{
				strQx.append(" and STATE in('未提交','撤销','否决') ");
			}
			params.put("QXJBCON",strQx.toString());
		 }
		 if(module.equals("sh")){
			String strSql = strQx.toString().substring(strQx.toString().indexOf("(")+1,strQx.toString().lastIndexOf(")"));
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
	    
//		 IListPage page = null;
//		 if(module.equals("sh")){
//	 		   page =  promoexpenService.pageQuerySH(params, pageNo);
//		  } else {
//				page = promoexpenService.pageQuery(params, pageNo);
//		  }
//		 
		// 设置帐套id
		//params.put("LEDGER_ID", userBean.getLoginZTXXID());
	    
		List list = null;
		if(module.equals("sh")){
			list = promoexpenService.expertExcelQuery(params);
		}else{
			list = promoexpenService.expertExcelQueryWH(params);
		}
		// excel数据上列显示的列明
		String tmpContentCn ="推广费用申请单编号,加盟商名称,加盟商联系人,加盟商联系电话,所属战区,区域经理,费用类别,申请人姓名," +
				"申请时间,城市名称,城市类型,申请原因,申请编码,预算科目,促销方案, 此次费用共计,预批金额,费用类别,计划零售额,计划开单数,加盟商季度累计实际收入,加盟商季度累计预批费用,加盟商季度累计费效比," +
				"区域季度累计预算费用,区域季度累计预批费用,区域季度累计预算可用,战区季度累计预算费用,战区季度累计预批费用,战区季度累计预算可用";
		String tmpContent = "PRMT_COST_REQ_NO,CHANN_NAME,CHANN_PERSON_CON,CHANN_TEL,AREA_NAME,AREA_MANAGE_NAME,COST_TYPE,REQ_NAME," +
				"REQ_DATE,CITY_NAME,CITY_LVL,REQ_REMARK,REQ_MAKE,BUDGET_ITEM_NAME,PRMT_PLAN_NAME,TOTAL_PRMT_COST,BUDGET_AMOUNT,COST_TYPE,RETAIL_AMOUNT,BILL_AMOUNT,TOTAL_REAL_AMOUNT,TOTAL_ADVC_AMOUNT,TOTAL_RATE," +
				"AREA_TOTAL_BUDGET_AMOUNT,AREA_TOTAL_PRE_AMOUNT,AREA_TOTAL_AVALIABLE_AMOUNT,WARE_TOTAL_BUDGET_AMOUNT,WARE_TOTAL_PRE_AMOUNT,WARE_TOTAL_AVALIABLE_AMOUNT";
		String colType= "string,string,string,string,string,string,string,string,"+
		                "string,string,string,string,string,string,string,string,string,string,string,string,string,string,string," +
					    "string,string,string,string,string,string";
		try {
			ExcelUtil
					.doExport(response, list, "推广费用申请单", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * * to 编辑初始化页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,String> entry = new HashMap<String,String>();
		
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT) && !QXUtil.checkMKQX(userBean, PVG_EDIT_AUDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PRMT_COST_REQ_ID = ParamUtil.get(request, "PRMT_COST_REQ_ID");
		String module  =  ParamUtil.get(request, "module");
		if(!StringUtil.isEmpty(PRMT_COST_REQ_ID)){
			entry = promoexpenService.load(PRMT_COST_REQ_ID);
			String path = promoexpenService.loadFiles(PRMT_COST_REQ_ID);
			entry.put("STATENEBTS_ATT", path);
		}else{
			entry.put("LEDGER_ID", userBean.getLoginZTXXID());
			entry.put("REQ_ID", userBean.getRYXXID());
			entry.put("REQ_NAME", userBean.getXM());
			entry.put("REQ_DATE", StringUtil.getCurrentDate());
			entry.put("STATE", BusinessConsts.UNCOMMIT);
			entry.put("PRMT_COST_REQ_NO", BusinessConsts.ZDSC);
			entry.put("LEDGER_NAME", userBean.getLoginZTMC());
		}
		request.setAttribute("rst", entry);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		request.setAttribute("BMBH", userBean.getBMBH());
		request.setAttribute("BMMC", userBean.getBMMC());
		request.setAttribute("module", module);
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("LEDGER_NAME", userBean.getLoginZTMC());
		request.setAttribute("DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		if(module.equals("sh")){
			return mapping.findForward("toeditT");
		}
		return mapping.findForward("toedit");
	}
	
	
	public void loadModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
             String CHANN_ID = ParamUtil.get(request, "CHANN_ID");
             String QUARTER  = ParamUtil.get(request, "QUARTER");
             String YEAR     = ParamUtil.get(request, "YEAR");
             String RELATE_AREA_ID = ParamUtil.get(request, "RELATE_AREA_ID");
             String WAREA_ID = ParamUtil.get(request, "WAREA_ID");
             
             Map<String,Object>  result = promoexpenService.queryJudgeModel(CHANN_ID,QUARTER,YEAR);
             String  YUPI = promoexpenService.queryJudgeModel1(CHANN_ID,QUARTER,YEAR);
             String  AREAYUSUAN = promoexpenService.queryJudgeModel2(RELATE_AREA_ID,QUARTER,YEAR);
             String  AREAYUPI   = promoexpenService.queryJudgeModel3(RELATE_AREA_ID,QUARTER,YEAR);
             String  WAREYUSUAN = promoexpenService.queryJudgeModel4(WAREA_ID,QUARTER,YEAR);
             String  WAREYUPI   = promoexpenService.queryJudgeModel5(WAREA_ID,QUARTER,YEAR);
             result.put("YUPI", YUPI);
             result.put("AREAYUSUAN", AREAYUSUAN);
             result.put("AREAYUPI", AREAYUPI);
             result.put("WAREYUSUAN", WAREYUSUAN);
             result.put("WAREYUPI", WAREYUPI);
             jsonResult = new Gson().toJson(new Result(true, result, ""));
        }catch (Exception e) {
        	logger.error(e);
        	jsonResult = jsonResult(false, "保存失败");
		}
       
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
	
	/**
	 * * 新增/修改数据
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		UserBean userBean = ParamUtil.getUserBean(request);
//    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
//    	{
//    		throw new ServiceException("对不起，您没有权限 !");
//    	}	
//		PrintWriter writer = getWriter(response);
//		String jsonResult = jsonResult();
//		try {
//			String jsonData = ParamUtil.get(request, "jsonData");
//			PromoexpenModel aModel = null;
//			if(!StringUtil.isEmpty(jsonData)){
//				aModel = new Gson().fromJson(jsonData, new TypeToken<PromoexpenModel>(){}.getType());
//			}
//			String PRMT_COST_REQ_ID = ParamUtil.get(request, "PRMT_COST_REQ_ID");
//			promoexpenService.txEdit(PRMT_COST_REQ_ID,aModel,userBean);
//		} catch (Exception e){
//			e.printStackTrace();
//			jsonResult = jsonResult(false, "保存失败!");
//		}
		
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String PRMT_COST_REQ_ID = ParamUtil.get(request, "PRMT_COST_REQ_ID");
        String jsonResult = jsonResult();
        try{
        	String jsonData = ParamUtil.get(request, "jsonData").trim();
        	PromoexpenModel model = new PromoexpenModel();
            if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, 
                		new TypeToken <PromoexpenModel>() {}.getType());
            }
            promoexpenService.txEdit(PRMT_COST_REQ_ID, model,userBean);
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
	 * check预算额度
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void isCheckMoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
         
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,Object>  result = new HashMap<String,Object>();
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String PRMT_COST_REQ_ID = ParamUtil.get(request, "PRMT_COST_REQ_ID");
			BudgetquotaModel  quota =  promoexpenService.qyeryQuotaAmount(PRMT_COST_REQ_ID);
		    String BUDGET_QUOTA = "";
		    String FREEZE_BUDGET_QUOTA = "";
		    String USE_BUDGET_QUOTA = "";
		    String BUDGET_AMOUNT =  quota.getBUDGET_AMOUNT().toString();
		    if(quota.getBUDGET_QUOTA() !=null){
		    	BUDGET_QUOTA = quota.getBUDGET_QUOTA().toString();
		    }else{
		    	BUDGET_QUOTA = "0";
		    }
		    if(quota.getFREEZE_BUDGET_QUOTA() !=null){
		    	FREEZE_BUDGET_QUOTA = quota.getFREEZE_BUDGET_QUOTA().toString();
		    }else{
		    	FREEZE_BUDGET_QUOTA = "0";
		    }
		    if(quota.getUSE_BUDGET_QUOTA()!=null){
		    	USE_BUDGET_QUOTA = quota.getUSE_BUDGET_QUOTA().toString();
		    }else{
		    	USE_BUDGET_QUOTA = "0";
		    }
		    result.put("BUDGET_QUOTA", BUDGET_QUOTA);
		    result.put("FREEZE_BUDGET_QUOTA", FREEZE_BUDGET_QUOTA);
		    result.put("USE_BUDGET_QUOTA", USE_BUDGET_QUOTA);
		    result.put("BUDGET_AMOUNT", BUDGET_AMOUNT);
			jsonResult = new Gson().toJson(new Result(true, result, ""));
		}catch (Exception e) {
			jsonResult = jsonResult(false, e.getMessage());
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
    /**
	 * * 删除
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
			String PRMT_COST_REQ_ID = ParamUtil.get(request, "PRMT_COST_REQ_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("PRMT_COST_REQ_ID", PRMT_COST_REQ_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			promoexpenService.txDelete(params);
			promoexpenService.clearCaches(params);
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
	 * * to 详细信息
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String PRMT_COST_REQ_ID = ParamUtil.get(request, "PRMT_COST_REQ_ID");
		if(!StringUtil.isEmpty(PRMT_COST_REQ_ID)){
			Map<String,String> entry = promoexpenService.load(PRMT_COST_REQ_ID);
			String path = promoexpenService.loadFiles(PRMT_COST_REQ_ID);
			entry.put("STATENEBTS_ATT", path);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(PRMT_COST_REQ_ID);
			request.setAttribute("flowInfoList", flowInfoList);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	/**
	 *  启用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void changeStateStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为启用单条记录开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			logger.warn("取得对应记录的状态");
			String PRMT_COST_REQ_ID = request.getParameter("PRMT_COST_REQ_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRMT_COST_REQ_ID", PRMT_COST_REQ_ID);
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
            params.put("STATE",BusinessConsts.JC_STATE_ENABLE);	
			promoexpenService.txStartById(params);
			jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
        	e.printStackTrace();
			jsonResult = jsonResult(false, "状态修改失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
    /**
	 * 停用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
    public void changeStateStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        logger.info("按钮修改为停用单条记录开始");
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            logger.warn("取得对应记录的状态");
            String PRMT_COST_REQ_ID = request.getParameter("PRMT_COST_REQ_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("PRMT_COST_REQ_ID", PRMT_COST_REQ_ID);
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
            params.put("STATE",BusinessConsts.JC_STATE_DISENABLE);
            promoexpenService.txStopById(params);
            jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
		    e.printStackTrace();
            jsonResult = jsonResult(false, "状态修改失败!");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
	/**
	 * 打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward printInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	 	 String PRMT_COST_REQ_ID = request.getParameter("PRMT_COST_REQ_ID");
	 	StringBuffer paramSql=new StringBuffer();
	 	 paramSql.append("Promoexpenprint.raq&PRMT_COST_REQ_ID=").append(PRMT_COST_REQ_ID);
	 	 /**
		 StringBuffer advSql = new StringBuffer();
		 advSql.append("rptSql=select ")
		 		.append(" p.PRMT_COST_REQ_NO,")
		 		.append(" p.REQ_NAME,")
		 		.append(" to_char(p.REQ_DATE,'yyyy-MM-dd') REQ_DATE,")
		 		.append(" p.AREA_NAME,")
		 		.append(" p.CHANN_NAME,")
		 		.append(" p.CITY_NAME,")
		 		.append(" p.CITY_LVL,")
		 		.append(" p.REQ_REMARK, ")
		 		.append(" p.BUDGET_ITEM_NAME,")
		 		.append(" p.COST_TYPE,")
		 		.append(" p.RETAIL_AMOUNT,")
		 		.append(" p.TOTAL_PRMT_COST,")
		 		.append(" p.BUDGET_AMOUNT, ")
		 		.append(" p.BILL_AMOUNT,")
		 		.append(" p.TOTAL_REAL_AMOUNT,")
		 		.append(" p.TOTAL_ADVC_AMOUNT, ")
		 		.append(" p.TOTAL_RATE, ")
		 		.append(" p.AREA_TOTAL_BUDGET_AMOUNT,")
		 		.append(" p.AREA_TOTAL_PRE_AMOUNT,")
		 		.append(" p.AREA_TOTAL_AVALIABLE_AMOUNT, ")
		 		.append(" p.WARE_TOTAL_BUDGET_AMOUNT,")
		 		.append(" p.WARE_TOTAL_PRE_AMOUNT,")
		 		.append(" p.WARE_TOTAL_AVALIABLE_AMOUNT ")
		 		.append(" from  ERP_PRMT_COST_REQ p where p.DEL_FLAG =").append(BusinessConsts.DEL_FLAG_COMMON)
		 		.append(" and PRMT_COST_REQ_ID ='").append(PRMT_COST_REQ_ID.trim()).append("'; ");
		 
			 StringBuffer dtlSql=new StringBuffer();
			 dtlSql.append(" dtlSql=   SELECT t1.NEXTOPERATORNAME, t1.REMARK, t1.OPERATETIME,t1.OPERATORNAME FROM ( SELECT a.NEXTOPERATORNAME, ")
		 	   .append("  lead(a.remark) over(order by a.operatetime) REMARK,")
		 	   .append("  lead(a.operatorname) over(order by a.operatetime) OPERATORNAME,")
		 	   .append("  lead(to_char(a.operatetime, 'yyyy-MM-DD HH24:MI:SS')) over(order by a.operatetime) OPERATETIME ")
		 	   .append("  from T_SYS_FLOWTRACE a ")
		 	   .append("  where a.BUSINESSID = '").append(PRMT_COST_REQ_ID.trim()).append("'")
		 	   .append("  ORDER BY a.OPERATETIME ) t1 where t1.NEXTOPERATORNAME is not null ")
		 	   .append("  and t1.OPERATORNAME IS NOT NULL ");
			 
//			 dtlSql.append(" dtlSql= SELECT t2.NEXTOPERATORNAME, t2.REMARK, t2.OPERATETIME,t2.OPERATORNAME  FROM (     ")
//		 	   .append("  SELECT t1.NEXTOPERATORNAME, t1.REMARK, t1.OPERATETIME,t1.OPERATORNAME,")
//		 	   .append("  rownum as num  FROM ( SELECT ")
//		 	   .append("  a.NEXTOPERATORNAME, ")
//		 	   .append("  lead(a.operatorname) over(order by a.operatetime) OPERATORNAME, ")
//		 	   .append("  (LEAD(a.remark) OVER(order by a.operatetime)) || ' \' || (LEAD(a.operatorname) OVER(order by a.operatetime)) REMARK, ")
//		 	   .append("  LEAD(to_char(a.operatetime, 'yyyy-MM-DD HH24:MI:SS')) OVER(order by a.operatetime) OPERATETIME ")
//		 	   .append("  FROM T_SYS_FLOWTRACE a, DRP_PRD_RETURN_REQ t  where a.BUSINESSID = t.prd_return_req_id ")
//		 	   .append("  and t.prd_return_req_no = 'TQ201504190002'  ORDER BY a.OPERATETIME)t1  where t1.NEXTOPERATORNAME is not null ")
//		 	   .append("  and t1.OPERATORNAME IS NOT NULL  ) t2  where num>1;");
			 
			 StringBuffer paymentSql=new StringBuffer();
			 StringBuffer sql=new StringBuffer();
			 sql.append(advSql.toString()).append(dtlSql.toString()).append(paymentSql.toString());
			 // 报表路径名称
			 request.setAttribute("reportFileName", "Promoexpenprint.raq");
			 System.out.println(sql.toString());
			 **/
			 // 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			 
			 return mapping.findForward("flashReportPrint");
	}
	
	/**
	 * 提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            String PRMT_COST_REQ_ID = request.getParameter("PRMT_COST_REQ_ID");
            List <PromoexpenModel> list = promoexpenService.childQuery(PRMT_COST_REQ_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
            jsonResult = jsonResult(true, "提交成功");
        } catch (Exception e) {
           jsonResult = jsonResult(false, "提交失败");
           e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
	
	/**
	 * 审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toAuditT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            String PRMT_COST_REQ_ID = request.getParameter("PRMT_COST_REQ_ID");
            List <PromoexpenModel> list = promoexpenService.childQuery(PRMT_COST_REQ_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
            jsonResult = jsonResult(true, "审核成功");
        } catch (Exception e) {
           jsonResult = jsonResult(false, "审核失败");
           e.printStackTrace();
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
	    	pvgMap.put("PVG_EDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_EDIT_AUDIT));
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
}