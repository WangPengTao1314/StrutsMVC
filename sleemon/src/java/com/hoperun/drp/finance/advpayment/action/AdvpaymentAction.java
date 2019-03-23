/**
 * prjName:喜临门营销平台
 * ucName:用户预付款
 * fileName:AdvpaymentAction
*/
package com.hoperun.drp.finance.advpayment.action;
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
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.finance.advpayment.model.AdvpaymentModel;
import com.hoperun.drp.finance.advpayment.model.AdvpaymentModelChld;
import com.hoperun.drp.finance.advpayment.service.AdvpaymentService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-20 18:57:47
 */
public class AdvpaymentAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(AdvpaymentAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0050101";
    private static String PVG_EDIT="FX0050102";
    private static String PVG_DELETE="FX0050104";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0050103";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
    private static String AUD_BUSS_STATE="STATE";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private AdvpaymentService advpaymentService;
    /**
	 * Sets the Advpayment service.
	 * 
	 * @param PreorderpaymentService the new Advpayment service
	 */
	public void setAdvpaymentService(AdvpaymentService advpaymentService) {
		this.advpaymentService = advpaymentService;
	}
	
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
        String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
        String isNoEdit = ParamUtil.utf8Decoder(request, "isNoEdit");
        
        request.setAttribute("paramUrl", paramUrl);
        request.setAttribute("isNoEdit", isNoEdit);
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
	    ParamUtil.putStr2Map(request, "TEL", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
	    
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        params.put("LEDGER_ID", userBean.getLoginZTXXID());
       
        
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//是否可编辑标记 从其它地方点击 弹出页面
		String isNoEdit = ParamUtil.get(request, "isNoEdit");
		if(StringUtil.isEmpty(isNoEdit)){
			 params.put("BILL_TYPES", "'"+BusinessConsts.BILL_TYPE_DEPOSIT+"','"+BusinessConsts.BILL_TYPE_ADVPAY+"','预付款'");
		}
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		if(StringUtil.isEmpty(search)){
			qx.append(" and u.STATE = '");
			qx.append(BusinessConsts.UNCOMMIT);
			qx.append("' ");
		}
		//权限级别和审批流的封装
	    params.put("QXJBCON", qx.toString());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advpaymentService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("isNoEdit", isNoEdit);
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
        if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STATEMENTS_ID =ParamUtil.get(request, "STATEMENTS_ID");
        if(!StringUtil.isEmpty(STATEMENTS_ID)){
        	 List <AdvpaymentModelChld> result = advpaymentService.childQuery(STATEMENTS_ID);
             request.setAttribute("rst", result);
        }
        String JSBHS = userBean.getJSBHS();
        request.setAttribute("JSBHS", JSBHS);
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
			entry = advpaymentService.load(STATEMENTS_ID);
		}else{
			entry.put("BILL_TYPE",BusinessConsts.BILL_TYPE_ADVPAY);
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
    public ActionForward modifyToChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String STATEMENTS_ID = ParamUtil.get(request, "STATEMENTS_ID");
        if(!StringUtil.isEmpty(STATEMENTS_ID))
        {
        	 List <AdvpaymentModelChld> result = advpaymentService.childQuery(STATEMENTS_ID);
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
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
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
          List <AdvpaymentModelChld> list = advpaymentService.loadChilds(params);
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
            advpaymentService.txEdit(STATEMENTS_ID, model, chldModelList, userBean);
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
            String STATEMENTS_ID = request.getParameter("STATEMENTS_ID");
            String jsonDate = request.getParameter("childJsonData");
            String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
            //是否修改预订单的付款方式
            boolean isUpdateAdvcPayDtl = ParamUtil.getBoolean(request, "isUpdateAdvcPayDtl");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <AdvpaymentModelChld> modelList = new Gson().fromJson(jsonDate, 
                		new TypeToken <List <AdvpaymentModelChld>>() {
                }.getType());
                advpaymentService.txChildEdit(STATEMENTS_ID, modelList,true,ADVC_ORDER_ID,isUpdateAdvcPayDtl);
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
			advpaymentService.txDelete(params);
			advpaymentService.clearCaches(params);
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
            advpaymentService.txBatch4DeleteChild(STATEMENTS_PAYMENT_DTL_IDs);
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
			Map<String,String> entry = advpaymentService.load(STATEMENTS_ID);
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
            List <AdvpaymentModelChld> list = advpaymentService.childQuery(STATEMENTS_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
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
			String STATEMENTS_ID = ParamUtil.get(request, "id");
			Map<String,String> map=advpaymentService.load(STATEMENTS_ID);
            boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),map.get("STATEMENT_DATE"));
			if(isMonthAcc){
				throw new ServiceException("结算日期:"+map.get("STATEMENT_DATE")+"已月结不能提交");
			}
			List <AdvpaymentModelChld> list = advpaymentService.childQuery(STATEMENTS_ID);
            if (list!=null && list.size() == 0) {
            	jsonResult = jsonResult(false, "没有明细，不能提交!");
            }else{
            	Map <String, String> params = new HashMap <String, String>();
                params.put("STATEMENTS_ID", STATEMENTS_ID);
                params.put("UPD_NAME", userBean.getYHM());
                params.put("UPDATOR", userBean.getXTYHID());
                params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
    			String PAYED_TOTAL_AMOUNT=advpaymentService.txSub(params,userBean);
    			jsonResult = jsonResult(true, PAYED_TOTAL_AMOUNT);
            }
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
            Map<String,String> map=advpaymentService.load(STATEMENTS_ID);
            boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),map.get("STATEMENT_DATE"));
			if(isMonthAcc){
				throw new ServiceException("结算日期:"+map.get("STATEMENT_DATE")+"已月结不能提交");
			}
            String type = request.getParameter("type");
            int rstCount = advpaymentService.queryHXChildsCount(STATEMENTS_ID);
            if(rstCount>0){
            	throw new ServiceException("该单据开始核销不能撤销!");
            }else{
            	 advpaymentService.txRevoke(STATEMENTS_ID,userBean,type);
                 jsonResult = jsonResult(true, "操作成功");
            }
        }catch(ServiceException s){
        	jsonResult = jsonResult(false, s.getMessage());
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
		 	 paramSql.append("StatementsPrint.raq&STATEMENTS_ID=").append(STATEMENTS_ID);
		 	 System.out.println(paramSql.toString());
		 	 /**
			 StringBuffer advSql = new StringBuffer();
			 advSql.append("rptSql=select ")
			 		.append("u.STATEMENTS_NO,")
			 		.append("u.ADVC_ORDER_NO,")
			 		.append("u.BILL_TYPE,")
			 		.append("u.TERM_NAME,")
			 		.append("u.STATEMENTS_AMOUNT,")
			 		.append("u.DEDUCT_AMOUNT,")
			 		.append("NVL((select PAYABLE_AMOUNT -")
			 		.append(" NVL((select sum((case ")
			 		.append(" when BILL_TYPE = '销售退款' then ")
			 		.append(" STATEMENTS_AMOUNT * -1 else  STATEMENTS_AMOUNT  end))")
			 		.append(" from DRP_STATEMENTS n ")
			 		.append(" where BILL_TYPE in ('订金', '正常收款', '预付款', '销售退款') ")
			 		.append(" and n.ADVC_ORDER_ID = m.ADVC_ORDER_ID ")
			 		.append(" and n.STATE = '已结算' ")
			 		.append(" and n.Del_Flag = 0), 0) ")
			 		.append(" from DRP_ADVC_ORDER m ")
			 		.append(" where m.ADVC_ORDER_ID = u.ADVC_ORDER_ID), 0) FUND_OF_END, ")
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
			 // 报表路径名称
			 request.setAttribute("reportFileName", "StatementsPrint.raq");
			 **/
		 	// 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			 return mapping.findForward("flashReportPrint");
	}
    
	
	/**导出EXCEL
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void PaymentToExcel(ActionMapping mapping, ActionForm form,
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
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        params.put("LEDGER_ID", userBean.getLoginZTXXID());
       
        
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//是否可编辑标记 从其它地方点击 弹出页面
		String isNoEdit = ParamUtil.get(request, "isNoEdit");
		if(StringUtil.isEmpty(isNoEdit)){
			 params.put("BILL_TYPES", "'"+BusinessConsts.BILL_TYPE_DEPOSIT+"','"+BusinessConsts.BILL_TYPE_ADVPAY+"','预付款'");
		}
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//权限级别和审批流的封装
	    params.put("QXJBCON", qx.toString());
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		List list = advpaymentService.PaymentToExcel(params);
		// excel数据上列显示的列明
		String tmpContentCn ="收款单编号,预订单编号,收款类型,终端编号, 终端名称,本次收款金额,本次扣款金额 ,尾款,销售日期,业务员名称,客户名称,合同编号,要求到货日期, 结算日期, 状态 ";
		String tmpContent = "STATEMENTS_NO,ADVC_ORDER_NO,BILL_TYPE,TERM_NO,TERM_NAME,STATEMENTS_AMOUNT,DEDUCT_AMOUNT,FUND_OF_END,SALE_DATE," +
							"SALE_PSON_NAME,CUST_NAME,CONTRACT_NO,ORDER_RECV_DATE,STATEMENT_DATE,STATE";
		String colType= "string,string,string,string,string,number,number,number,string," +
					   "string,string,string,string,string,string";
		try {
			ExcelUtil
					.doExport(response, list, "客户收款单", tmpContent, tmpContentCn,colType);
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