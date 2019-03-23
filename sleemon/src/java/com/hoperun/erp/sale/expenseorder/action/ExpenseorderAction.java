package com.hoperun.erp.sale.expenseorder.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.expenseorder.model.ExpenseorderChildModel;
import com.hoperun.erp.sale.expenseorder.model.ExpenseorderModel;
import com.hoperun.erp.sale.expenseorder.service.ExpenseorderService;
import com.hoperun.sys.model.UserBean;
/**
 * 费用报销单
 * @author zhang_zhongbin
 *
 */
public class ExpenseorderAction extends BaseAction{
	
	
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0022801";
	private static String PVG_EDIT = "BS0022802";
	private static String PVG_DELETE = "BS0022803";
	
	  //启用,停用
    private static String PVG_START_STOP = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "BS0022804";
	private static String PVG_TRACE = "BS0022805";

	// 审核模块
	private static String PVG_BWS_AUDIT = "BS0023001";
	private static String PVG_AUDIT_AUDIT = "BS0023002";
	private static String PVG_TRACE_AUDIT = "BS0023003";
	// 审批流参数
	private static String AUD_TAB_NAME = "ERP_EXPENSE_ORDER";
	//private static String AUD_TAB_KEY = "u.EXPENSE_ORDER_ID";
	private static String AUD_TAB_KEY = "EXPENSE_ORDER_ID";
	private static String AUD_BUSS_STATE = "STATE";
	private static String AUD_BUSS_TYPE = "ERP_EXPENSE_ORDER_AUDIT";
	private static String AUD_FLOW_INS = "com.hoperun.erp.sale.expenseorder.service.impl.ExpenseorderFlowInterface";
                               
	/**日志**/
	private Logger logger = Logger.getLogger(ExpenseorderAction.class);
	private ExpenseorderService expenseorderService;
	
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
	    ParamUtil.putStr2Map(request, "EXPENSE_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "EXPENSE_TYPE", params);
	    ParamUtil.putStr2Map(request, "BUDGET_ITEM_TYPE", params);
	    ParamUtil.putStr2Map(request, "YEAR", params);
	    ParamUtil.putStr2Map(request, "QUARTER", params);
	    ParamUtil.putStr2Map(request, "MONTH", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params); 
	    ParamUtil.putStr2Map(request, "BUDGET_ITEM_NO", params); 
	    ParamUtil.putStr2Map(request, "BUDGET_ITEM_NAME", params); 
	    ParamUtil.putStr2Map(request, "RELATE_ORDER_NOS", params); 
	    
	    ParamUtil.putStr2Map(request, "EXPENSE_PSON_NAME", params); 
	    ParamUtil.putStr2Map(request, "EXPENSE_DEPT_NO", params); 
	    ParamUtil.putStr2Map(request, "EXPENSE_DEPT_NAME", params); 
	    
	    ParamUtil.putStr2Map(request, "EXPENSE_DATE_BEG", params); 
	    ParamUtil.putStr2Map(request, "EXPENSE_DATE_END", params); 
	    ParamUtil.putStr2Map(request, "DEPT_NAME", params);
	    
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	   
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String STATE  = ParamUtil.get(request, "STATE");
		//权限级别和审批流的封装
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,module,
				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
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
		
		
	    //params.put("QXJBCON", sb.toString());
	    //字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = null;
		if(module.equals("sh")){
 		   page = expenseorderService.pageQuerySH(params, pageNo);
		} else {
	 	   page = expenseorderService.pageQuery(params, pageNo);
	    }
		//IListPage page = expenseorderService.pageQuery(params, pageNo);
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		request.setAttribute("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("search", search);
		return mapping.findForward("list");
	}
	
	// 导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "EXPENSE_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "EXPENSE_TYPE", params);
	    ParamUtil.putStr2Map(request, "BUDGET_ITEM_TYPE", params);
	    ParamUtil.putStr2Map(request, "YEAR", params);
	    ParamUtil.putStr2Map(request, "QUARTER", params);
	    ParamUtil.putStr2Map(request, "MONTH", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params); 
	    ParamUtil.putStr2Map(request, "BUDGET_ITEM_NO", params); 
	    ParamUtil.putStr2Map(request, "BUDGET_ITEM_NAME", params); 
	    ParamUtil.putStr2Map(request, "RELATE_ORDER_NOS", params); 
	    
	    ParamUtil.putStr2Map(request, "EXPENSE_PSON_NAME", params); 
	    ParamUtil.putStr2Map(request, "EXPENSE_DEPT_NO", params); 
	    ParamUtil.putStr2Map(request, "EXPENSE_DEPT_NAME", params); 
	    
	    ParamUtil.putStr2Map(request, "EXPENSE_DATE_BEG", params); 
	    ParamUtil.putStr2Map(request, "EXPENSE_DATE_END", params); 
	    ParamUtil.putStr2Map(request, "DEPT_NAME", params);
	    
	    ParamUtil.putStr2Map(request, "CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "CHANN_NAME", params);
	    
	    //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		//权限级别和审批流的封装
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,module,
				PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));

		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
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
		params.put("QXJBCON", sb.toString());
		List list = null;
		if(module.equals("wh")){
		   list = expenseorderService.downQuery(params);
		} 
		if(module.endsWith("sh")){
		   list = expenseorderService.downQuerySH(params);
		}
		
		String tmpContentCn ="费用报销单编号,费用报销类型,预算科目编号,预算科目名称,预算科目类型,年份,季度,月份,报销人姓名," +
		 "报销金额,报销部门编号,报销部门名称,报销日期,关联单据编号,渠道编号,渠道名称,申请金额,制单人," +
		 "制单时间,状态,更新人,更新时间,审核人,审核时间,制单部门,制单机构,帐套名称,备注,报销类型,报销事由,发生日期,报销金额,其他说明";
		
		String tmpContent = "EXPENSE_ORDER_NO,EXPENSE_TYPE,BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_ITEM_TYPE,YEAR,QUARTER,MONTH,EXPENSE_PSON_NAME," +
		"EXPENSE_AMOUNT,EXPENSE_DEPT_NO,EXPENSE_DEPT_NAME,EXPENSE_DATE,RELATE_ORDER_NOS,CHANN_NO,CHANN_NAME,RELATE_AMOUNT_REQ,CRE_NAME," +
		"CRE_TIME,STATE,UPD_NAME,UPD_TIME,AUDIT_NAME,AUDIT_TIME,DEPT_NAME,ORG_NAME,LEDGER_NAME,REMARK,EXPENSE_TYPE,EXPENSE_REMARK,BUSS_DATE,EXPENSE_AMOUNT,OTHER_REMARK";
		
		String colType= "string,string,string,string,string,string,string,string,string," +
		   "number,string,string,string,string,string,string,string,string," +
		   "string,string,string,string,string,string,string,string,string,string,string,string,string,number,string";
		
		try {
			ExcelUtil.doExport(response, list, "推广费报销单", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
       String EXPENSE_ORDER_ID = ParamUtil.get(request, "EXPENSE_ORDER_ID");
       if(!StringUtil.isEmpty(EXPENSE_ORDER_ID)){
       	 List <ExpenseorderChildModel> result = expenseorderService.childQuery(EXPENSE_ORDER_ID);
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
		String EXPENSE_ORDER_ID = ParamUtil.get(request, "selRowId");
		Map<String,Object> entry = new HashMap<String,Object>();
		if(!StringUtil.isEmpty(EXPENSE_ORDER_ID)){
			entry = expenseorderService.load(EXPENSE_ORDER_ID);
		} else{
			if("1".equals(userBean.getIS_DRP_LEDGER())){
				entry.put("EXPENSE_PSON_NAME", userBean.getLoginZTMC());
			}else{
				//entry.put("EXPENSE_PSON_NAME", userBean.getXM());
			}
		}
		request.setAttribute("rst", entry);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
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
        String EXPENSE_ORDER_ID = ParamUtil.get(request, "EXPENSE_ORDER_ID");
        if(!StringUtil.isEmpty(EXPENSE_ORDER_ID)){
        	 List <ExpenseorderChildModel> result = expenseorderService.childQuery(EXPENSE_ORDER_ID);
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
    	String EXPENSE_ORDER_ID = ParamUtil.get(request, "EXPENSE_ORDER_ID");
    	//多个Id批量查询，用逗号隔开
        String EXPENSE_ORDER_DTL_IDS = request.getParameter("EXPENSE_ORDER_DTL_IDS");
        if (!StringUtil.isEmpty(EXPENSE_ORDER_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("EXPENSE_ORDER_DTL_IDS",EXPENSE_ORDER_DTL_IDS);
          List <ExpenseorderChildModel> list = expenseorderService.loadChilds(params);
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
            String EXPENSE_ORDER_ID = ParamUtil.get(request, "EXPENSE_ORDER_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            ExpenseorderModel model = new Gson().fromJson(parentJsonData, 
            		new TypeToken <ExpenseorderModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            
            List <ExpenseorderChildModel> chldList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
            	chldList = new Gson().fromJson(jsonDate, 
            			new TypeToken <List <ExpenseorderChildModel>>(){}.getType());
            }
            expenseorderService.txEdit(EXPENSE_ORDER_ID, model, chldList, userBean);
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
            String EXPENSE_ORDER_ID = request.getParameter("EXPENSE_ORDER_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <ExpenseorderChildModel> modelList = new Gson().fromJson(
                		jsonDate, new TypeToken <List <ExpenseorderChildModel>>(){}.getType());
                expenseorderService.txChildEdit(EXPENSE_ORDER_ID, modelList,userBean);
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
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void loadModelT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
             String CHANN_ID = ParamUtil.get(request, "CHANN_ID").toString();
             Date  date = new Date();
             String YEAR  = String.valueOf(date.getYear()+1900);
             //统计本年度渠道出货金额
             Map<String,Object>  result = expenseorderService.queryJudgeModelT(CHANN_ID, YEAR);
             //本年度渠道已申报推广   
             String  EXPENSE_AMOUNT = expenseorderService.queryChannExpenseAmount(CHANN_ID,YEAR);
             if(EXPENSE_AMOUNT != null){
                result.put("EXPENSE_AMOUNT", EXPENSE_AMOUNT);
             } else {
            	result.put("EXPENSE_AMOUNT", "0");
             }
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
	 * * 主表 删除
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
			String EXPENSE_ORDER_ID = ParamUtil.get(request, "EXPENSE_ORDER_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("EXPENSE_ORDER_ID", EXPENSE_ORDER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            expenseorderService.txDelete(params);
            expenseorderService.clearCaches(params);
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
        	String EXPENSE_ORDER_ID = ParamUtil.get(request, "EXPENSE_ORDER_ID");
            String EXPENSE_ORDER_DTL_IDS = request.getParameter("EXPENSE_ORDER_DTL_IDS");
            Map <String, String> params = new HashMap <String, String>();
            params.put("EXPENSE_ORDER_ID", EXPENSE_ORDER_ID);
            params.put("EXPENSE_ORDER_DTL_IDS", EXPENSE_ORDER_DTL_IDS);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            expenseorderService.txBatch4DeleteChild(params);
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
		String EXPENSE_ORDER_ID = ParamUtil.get(request, "EXPENSE_ORDER_ID");
		if(!StringUtil.isEmpty(EXPENSE_ORDER_ID)){
			Map<String,Object> entry = expenseorderService.load(EXPENSE_ORDER_ID);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(EXPENSE_ORDER_ID);
			request.setAttribute("flowInfoList", flowInfoList);
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
            String EXPENSE_ORDER_ID = request.getParameter("EXPENSE_ORDER_ID");
            if(!StringUtil.isEmpty(EXPENSE_ORDER_ID)){
            	List <ExpenseorderChildModel> list = expenseorderService.childQuery(EXPENSE_ORDER_ID);
            	if(null == list || list.isEmpty()){
            		throw new ServiceException("报销单明细未填写！");
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
    
    /**
	 * 提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toCommitT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            String EXPENSE_ORDER_ID = request.getParameter("EXPENSE_ORDER_ID");
            List <ExpenseorderModel> list = expenseorderService.childQueryT(EXPENSE_ORDER_ID);
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
            String EXPENSE_ORDER_ID = request.getParameter("EXPENSE_ORDER_ID");
            List <ExpenseorderModel> list = expenseorderService.childQueryT(EXPENSE_ORDER_ID);
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
    
    public void queryRelateOrder(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	Map<String,Object>  result =  new  HashMap<String,Object>();
            String RELATE_ORDER_NOS =  ParamUtil.get(request, "RELATE_ORDER_NOS");
            String  relateOrders = expenseorderService.queryRelateOrder(RELATE_ORDER_NOS);
            result.put("relateOrders", relateOrders);
//            if(!StringUtil.isEmpty(EXPENSE_ORDER_ID)){
//            	List <ExpenseorderChildModel> list = expenseorderService.childQuery(EXPENSE_ORDER_ID);
//            	if(null == list || list.isEmpty()){
//            		throw new ServiceException("报销单明细未填写！");
//            	}
//            }
//            jsonResult = jsonResult(true, "提交成功");
              jsonResult = new Gson().toJson(new Result(true, result, ""));
		}catch (ServiceException e) {
            jsonResult = jsonResult(false, e.getMessage());
        }  
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    
    public ActionForward toLookPromoexpen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	 
	    Map<String,String> params = new HashMap<String,String>();
        //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//报销单页面查看推广费
		String RELATE_ORDER_NOS = ParamUtil.utf8Decoder(request, "RELATE_ORDER_NOS");
		if(!StringUtil.isEmpty(RELATE_ORDER_NOS)){
			RELATE_ORDER_NOS = "'"+RELATE_ORDER_NOS.replace(",", "','")+"'";
			params.put("RELATE_ORDER_NOS",RELATE_ORDER_NOS);
			//字段排序
			ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
			int pageNo = ParamUtil.getInt(request, "pageNo", 1);
			ParamUtil.putStr2Map(request, "pageSize", params);
			IListPage page = expenseorderService.pagePromoexpenQuery(params, pageNo);
			//报销单页面查看推广费
			request.setAttribute("page", page);
		}
		return mapping.findForward("toLookPromoexpen");
	}
	
    
    public ActionForward toPromoexpen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	    Map<String,String> params = new HashMap<String,String>();
        //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//报销单页面查看推广费
		String PRMT_COST_REQ_NO = ParamUtil.utf8Decoder(request, "PRMT_COST_REQ_NO");
		request.setAttribute("PRMT_COST_REQ_NO", PRMT_COST_REQ_NO);
		return mapping.findForward("toPromoexpen");
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
	 	 String EXPENSE_ORDER_ID = request.getParameter("EXPENSE_ORDER_ID");
	 	StringBuffer paramSql=new StringBuffer();
	 	paramSql.append("Expenseorderprint.raq&EXPENSE_ORDER_ID=").append(EXPENSE_ORDER_ID);
	 	/**
		 StringBuffer advSql = new StringBuffer();
		 advSql.append("rptSql=select ")
		 		.append(" p.EXPENSE_ORDER_NO,")
		 		.append(" p.EXPENSE_TYPE,")
		 		.append(" p.BUDGET_ITEM_NO,")
		 		.append(" p.BUDGET_ITEM_NAME,")
		 		.append(" p.BUDGET_ITEM_TYPE,")
		 		.append(" to_char(p.EXPENSE_DATE,'yyyy-MM-dd') EXPENSE_DATE,")
		 		.append(" p.YEAR,")
		 		.append(" p.QUARTER, ")
		 		.append(" p.EXPENSE_PSON_NAME,")
		 		.append(" p.MONTH,")
		 		.append(" p.EXPENSE_AMOUNT,")
		 		.append(" p.EXPENSE_DEPT_NO,")
		 		.append(" p.EXPENSE_DEPT_NAME, ")
		 		.append(" p.REMARK, ")
		 		.append(" p.RELATE_ORDER_NOS, ")
		 		.append(" p.RELATE_AMOUNT_REQ, ")
		 		.append(" p1.EXPENSE_TYPE,")
		 		.append(" p1.EXPENSE_REMARK,")
		 		.append(" to_char(p1.BUSS_DATE,'yyyy-MM-dd') BUSS_DATE, ")
		 		.append(" p1.EXPENSE_AMOUNT, ")
		 		.append(" p1.OTHER_REMARK,")
		 		.append(" p.CHANN_NAME")
		 		.append(" FROM ERP_EXPENSE_ORDER p ")
		 		.append(" LEFT JOIN ERP_EXPENSE_ORDER_DTL p1  ")
		 		.append(" on P.EXPENSE_ORDER_ID = p1.EXPENSE_ORDER_ID and p1.DEL_FLAG='").append(BusinessConsts.DEL_FLAG_COMMON).append("'")
		 		.append(" LEFT JOIN ERP_PRMT_COST_REQ r on r.PRMT_COST_REQ_NO = p.RELATE_ORDER_NOS ")
		 		.append(" where p.DEL_FLAG =").append(BusinessConsts.DEL_FLAG_COMMON)
		 		.append(" and p.EXPENSE_ORDER_ID ='").append(EXPENSE_ORDER_ID.trim()).append("'; ");
		 
			 StringBuffer dtlSql=new StringBuffer();
			 dtlSql.append(" dtlSql=   SELECT t1.NEXTOPERATORNAME, t1.REMARK, t1.OPERATETIME,t1.OPERATORNAME FROM ( SELECT a.NEXTOPERATORNAME, ")
		 	   .append("  lead(a.remark) over(order by a.operatetime) REMARK,")
		 	   .append("  lead(a.operatorname) over(order by a.operatetime) OPERATORNAME,")
		 	   .append("  lead(to_char(a.operatetime, 'yyyy-MM-DD HH24:MI:SS')) over(order by a.operatetime) OPERATETIME ")
		 	   .append("  from T_SYS_FLOWTRACE a ")
		 	   .append("  where a.BUSINESSID = '").append(EXPENSE_ORDER_ID.trim()).append("'")
		 	   .append("  ORDER BY a.OPERATETIME ) t1 where t1.NEXTOPERATORNAME is not null ")
		 	   .append("  and t1.OPERATORNAME IS NOT NULL ");
			 
			 StringBuffer paymentSql=new StringBuffer();
			 StringBuffer sql=new StringBuffer();
			 sql.append(advSql.toString()).append(dtlSql.toString()).append(paymentSql.toString());
			 System.out.println(sql.toString());
			 // 报表路径名称
			 request.setAttribute("reportFileName", "Expenseorderprint.raq");
			 **/
			 // 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			 
			 return mapping.findForward("flashReportPrint");
	}   
    
	/**
	 * 撤销释放金额
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toRevoke(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String EXPENSE_ORDER_ID = request.getParameter("EXPENSE_ORDER_ID");
            if(!StringUtil.isEmpty(EXPENSE_ORDER_ID)){
//            	ExpenseorderModel  expenseOrder = expenseorderService.expenseOrderQuery(EXPENSE_ORDER_ID);
//            	String RELATE_AMOUNT_REQ = expenseOrder.getRELATE_AMOUNT_REQ().toString();  //根据报销单ID查询出所申请金额
//            	String RELATE_ORDER_NOS  = expenseOrder.getRELATE_ORDER_NOS().toString();   //来源申请单编号
//            	String EXPENSE_AMOUNT    = expenseOrder.getEXPENSE_AMOUNT().toString();     //报销金额
//            	String  relateOrders = expenseorderService.queryRelateOrder(RELATE_ORDER_NOS);
//            	Float  resiDueAmount = Float.parseFloat(relateOrders)- Float.parseFloat(RELATE_AMOUNT_REQ);
            	
            	Map<String,String> params = new HashMap<String,String>();
            	params.put("UPD_NAME",userBean.getXM());
     		    params.put("UPDATOR",userBean.getXTYHID());
     		    params.put("UPD_TIME","sysdate");
     			params.put("EXPENSE_ORDER_ID", EXPENSE_ORDER_ID);
     			params.put("EXPENSE_AMOUNT",String.valueOf("0"));
     			expenseorderService.txUpdateById(params);
            }
		}catch (Exception e) {
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

	public ExpenseorderService getExpenseorderService() {
		return expenseorderService;
	}

	public void setExpenseorderService(ExpenseorderService expenseorderService) {
		this.expenseorderService = expenseorderService;
	}
}
