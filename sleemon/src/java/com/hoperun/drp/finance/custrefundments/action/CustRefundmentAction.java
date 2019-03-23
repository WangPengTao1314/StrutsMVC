/**
 * prjName:喜临门营销平台
 * ucName:用户预付款
 * fileName:AdvpaymentAction
*/
package com.hoperun.drp.finance.custrefundments.action;
import java.io.PrintWriter;
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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.finance.advpayment.model.AdvpaymentModel;
import com.hoperun.drp.finance.advpayment.model.AdvpaymentModelChld;
import com.hoperun.drp.finance.custrefundments.service.CustRefundmentService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func 客户退款单
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-20 18:57:47
 */
public class CustRefundmentAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(CustRefundmentAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0050101";
    private static String PVG_EDIT="FX0050102";
    private static String PVG_DELETE="FX0050104";
    //启用,停用
    private static String PVG_START_STOP="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0050103";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="FX0050301";
    private static String PVG_AUDIT_AUDIT="FX0050302";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
    private static String AUD_BUSS_STATE="STATE";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private CustRefundmentService custRefundmentService;
	
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
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
	 * 
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    ParamUtil.putStr2Map(request, "STATEMENTS_NO", params);
	    ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        params.put("LEDGER_ID", userBean.getLoginZTXXID());
//        params.put("BILL_TYPES", "'"+BusinessConsts.BILL_TYPE_RETURN_OTHER+"','"
//        		+BusinessConsts.BILL_TYPE_RETURN_TERM+"','销售退款'");
        
        params.put("BILL_TYPE", "销售退款");
        
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,"",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		if("wh".equals(module)&&StringUtil.isEmpty(search)){
			qx.append(" and u.STATE = '");
			qx.append(BusinessConsts.UNCOMMIT);
			qx.append("' ");
		}else if("sh".equals(module)&&StringUtil.isEmpty(search)){
			qx.append(" and u.STATE = '待结算'");
		}
		//权限级别和审批流的封装
	    params.put("QXJBCON", qx.toString());
	    
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = custRefundmentService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("module", module);
		request.setAttribute("page", page);
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
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STATEMENTS_ID =ParamUtil.get(request, "STATEMENTS_ID");
        if(!StringUtil.isEmpty(STATEMENTS_ID))
        {
        	 List <AdvpaymentModelChld> result = custRefundmentService.childQuery(STATEMENTS_ID);
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
    public ActionForward toEditFrame(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
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
	 * 
	 * @return the action forward
	 */
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,String> entry = new HashMap<String,String>();
		
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String STATEMENTS_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(STATEMENTS_ID)){
			entry = custRefundmentService.load(STATEMENTS_ID);
		}else{
//			entry.put("BILL_TYPE",BusinessConsts.BILL_TYPE_RETURN_OTHER);
			entry.put("LEDGER_ID", userBean.getLoginZTXXID());
			entry.put("STATEMENTS_NO", BusinessConsts.ZDSC);
		}
		
		request.setAttribute("rst", entry);
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
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
        if(!StringUtil.isEmpty(STATEMENTS_ID))
        {
        	 List <AdvpaymentModelChld> result = custRefundmentService.childQuery(STATEMENTS_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
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
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String STATEMENTS_PAYMENT_DTL_IDs = request.getParameter("STATEMENTS_PAYMENT_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(STATEMENTS_PAYMENT_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("STATEMENTS_PAYMENT_DTL_IDS",STATEMENTS_PAYMENT_DTL_IDs);
          List <AdvpaymentModelChld> list = custRefundmentService.loadChilds(params);
          request.setAttribute("rst", list);
        }
        request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            AdvpaymentModel model = new Gson().fromJson(parentJsonData, new TypeToken <AdvpaymentModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <AdvpaymentModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <AdvpaymentModelChld>>() {
                }.getType());
            }
            custRefundmentService.txEdit(STATEMENTS_ID, model, chldModelList, userBean);
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
    public void childEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String STATEMENTS_ID = request.getParameter("STATEMENTS_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <AdvpaymentModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <AdvpaymentModelChld>>() {
                }.getType());
                custRefundmentService.txChildEdit(STATEMENTS_ID, modelList,true);
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
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("STATEMENTS_ID", STATEMENTS_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            custRefundmentService.txDelete(params);
            custRefundmentService.clearCaches(params);
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
    public void childDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String STATEMENTS_PAYMENT_DTL_IDs = request.getParameter("STATEMENTS_PAYMENT_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            custRefundmentService.txBatch4DeleteChild(STATEMENTS_PAYMENT_DTL_IDs);
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
     * 退回
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void toReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_AUDIT_AUDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String STATEMENTS_ID = request.getParameter("STATEMENTS_ID");
            String RETURN_RSON = request.getParameter("RETURN_RSON");
            custRefundmentService.txToReturn(STATEMENTS_ID,userBean,RETURN_RSON);
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
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
		if(!StringUtil.isEmpty(STATEMENTS_ID)){
			Map<String,String> entry = custRefundmentService.load(STATEMENTS_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
    /**
     * * 提交时，校验是否有明细.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
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
            String STATEMENTS_ID = request.getParameter("STATEMENTS_ID");
            String STATEMENTS_AMOUNT=request.getParameter("STATEMENTS_AMOUNT");
            String PAYABLE_AMOUNT=request.getParameter("PAYABLE_AMOUNT");
            List <AdvpaymentModelChld> list = custRefundmentService.childQuery(STATEMENTS_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
            Map<String,String> checkMap=custRefundmentService.load(STATEMENTS_ID);
            boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),checkMap.get("STATEMENT_DATE"));
			if(isMonthAcc){
				throw new ServiceException("结算日期:"+checkMap.get("STATEMENT_DATE")+"已月结不能提交");
			}
            Map<String,String> map=new HashMap<String, String>();
            map.put("PAYABLE_AMOUNT", PAYABLE_AMOUNT);//应收总额
            map.put("STATEMENTS_AMOUNT", STATEMENTS_AMOUNT);//本次退款金额
            map.put("STATEMENTS_ID", STATEMENTS_ID);
            boolean flag=custRefundmentService.checkRefund(map);
            if(!flag){
            	jsonResult = jsonResult(false, "");
            }else{
            	jsonResult = jsonResult(true, "");
            }
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
        } catch (Exception e) {
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            } else {
                jsonResult = jsonResult(false, "提交失败");
            }
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
	public void opSub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
			Map<String,String> map=custRefundmentService.load(STATEMENTS_ID);
            boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),map.get("STATEMENT_DATE"));
			if(isMonthAcc){
				throw new ServiceException("结算日期:"+map.get("STATEMENT_DATE")+"已月结不能提交");
			}
			String op=ParamUtil.get(request, "op");
			String IS_RETURN_SALE_AUD_FLAG=custRefundmentService.getIS_RETURN_SALE_AUD_FLAG(userBean.getCHANN_ID());
			Map <String, String> params = new HashMap <String, String>();
            params.put("STATEMENTS_ID", STATEMENTS_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
            if("1".equals(IS_RETURN_SALE_AUD_FLAG)&&"commit".equals(op)){
            	params.put("STATE", "待结算");
            }else{
            	params.put("STATE", BusinessConsts.STATE_IS_PAY);
            }
            custRefundmentService.txSub(params,userBean);
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
		    e.printStackTrace();
			jsonResult = jsonResult(false, "提交失败!");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	
	/**
     * 撤销
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void toRevoke(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
       
        try {
            String STATEMENTS_ID = request.getParameter("STATEMENTS_ID");
            Map<String,String> map=custRefundmentService.load(STATEMENTS_ID);
            boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),map.get("STATEMENT_DATE"));
			if(isMonthAcc){
				throw new ServiceException("结算日期:"+map.get("STATEMENT_DATE")+"已月结不能提交");
			}
            custRefundmentService.txRevoke(STATEMENTS_ID,userBean);
            jsonResult = jsonResult(true, "操作成功");
        }catch(ServiceException e){
        	jsonResult = jsonResult(false, e.getMessage());
        }catch (Exception e) {
            jsonResult = jsonResult(false, "操作失败");
            e.printStackTrace();
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    public ActionForward printInfo(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	 String STATEMENTS_ID = request.getParameter("STATEMENTS_ID");
		 	StringBuffer paramSql=new StringBuffer();
		 	 paramSql.append("CustrefundmentsPrint.raq&STATEMENTS_ID=").append(STATEMENTS_ID);
		 	 /**
			 StringBuffer advSql = new StringBuffer();
			 advSql.append("rptSql=select ")
			 		.append("u.STATEMENTS_NO,u.SALE_PSON_NAME,to_char(u.CRE_TIME,'yyyy-mm-dd')CRE_TIME, ")
			 		.append("u.ADVC_ORDER_NO,")
			 		.append("u.BILL_TYPE,")
			 		.append("u.TERM_NAME,")
			 		.append("u.STATEMENTS_AMOUNT,")
			 		.append("u.DEDUCT_AMOUNT,")
			 		.append("to_char(u.SALE_DATE, 'yyyy-MM-DD') SALE_DATE,")
			 		.append("u.CUST_NAME,")
			 		.append(" u.TEL, ")
			 		.append("to_char(u.STATEMENT_DATE, 'yyyy-MM-DD') STATEMENT_DATE ")
			 		.append(" from DRP_STATEMENTS u ")
			 		.append(" where u.DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
			 		.append(" and u.STATEMENTS_ID ='").append(STATEMENTS_ID).append("'; ");
			 StringBuffer dtlSql=new StringBuffer();
			 dtlSql.append("dtlSql=select a.PAY_TYPE, ")
			 	   .append("a.PAY_AMONT,")
			 	   .append("a.PAY_INFO")
			 	   .append(" from DRP_STATEMENTS_PAYMENT_DTL a ")
			 	   .append(" where a.DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
			 	   .append(" and a.STATEMENTS_ID ='").append(STATEMENTS_ID).append("' ")
			 	   .append(" order by PAY_TYPE asc ;");
			 StringBuffer paymentSql=new StringBuffer();
			 StringBuffer sql=new StringBuffer();
			 sql.append(advSql.toString()).append(dtlSql.toString()).append(paymentSql.toString());
			 System.out.println(sql.toString());
			 // 要传递的宏参数！！！
			 request.setAttribute("params", sql.toString());
			 // 报表路径名称
			 request.setAttribute("reportFileName", "CustrefundmentsPrint.raq");
			 **/
		 	 request.setAttribute("params", paramSql.toString());
			 return mapping.findForward("flashReportPrint");
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
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
		public void setCustRefundmentService(CustRefundmentService custRefundmentService) {
			this.custRefundmentService = custRefundmentService;
		}
}