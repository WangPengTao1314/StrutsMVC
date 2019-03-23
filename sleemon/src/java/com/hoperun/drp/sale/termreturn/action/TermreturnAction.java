/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:TermreturnAction
*/
package com.hoperun.drp.sale.termreturn.action;
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
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.termreturn.model.TermreturnModel;
import com.hoperun.drp.sale.termreturn.model.TermreturnModelChld;
import com.hoperun.drp.sale.termreturn.service.TermreturnService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class TermreturnAction extends BaseAction {
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="FX0021001";
    private static String PVG_EDIT="FX0021002";
    private static String PVG_DELETE="FX0021003";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="FX0021004";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="DRP_ADVC_ORDER";
    private static String AUD_TAB_KEY="ADVC_ORDER_ID";
    private static String AUD_BUSS_STATE="STATE";	
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private TermreturnService termreturnService;
    /**
	 * Sets the Termreturn service.
	 * 
	 * @param EmployeeService the new Termreturn service
	 */
	public void setTermreturnService(TermreturnService termreturnService) {
		this.termreturnService = termreturnService;
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
	    ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "TEL", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        ParamUtil.putStr2Map(request, "RETURN_STATEMENT_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "RETURN_STATEMENT_DATE_END", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,"",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    params.put("BILL_TYPE", "终端退货");
	    //查询时状态为查询选择状态，不然状态为未提交和退回状态
	    if(StringUtil.isEmpty(search)){
	    	 params.put("searchSTATE","'未提交','退回'");
	    }else
		{
	    	ParamUtil.putStr2Map(request, "STATE", params);
		}
	    if(StringUtil.isEmpty(params.get("STATE"))&&StringUtil.isEmpty(params.get("searchSTATE"))){
	    	params.put("STATES", "'未提交','提交','待退货','已入库','已结算','已退货','退回'");
	    }
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = termreturnService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("search", search);
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
    public ActionForward childList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String ADVC_ORDER_ID =ParamUtil.get(request, "ADVC_ORDER_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_ID))
        {
        	 List <TermreturnModelChld> result = termreturnService.childQuery(ADVC_ORDER_ID,userBean.getCHANN_TYPE());
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String ADVC_ORDER_ID = ParamUtil.get(request, "selRowId");
		Map<String,String> entry=new HashMap<String,String>();
		if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
			entry= termreturnService.load(ADVC_ORDER_ID);
		}
		// 按登陆人所属部门id查询终端信息
		Map<String, String> map = termreturnService.getTerminalInfoById(userBean.getBMXXID());
		entry.put("LEDGER_ID", userBean.getLoginZTXXID());//预订单通用选取查询条件
		if(null!=map){
			request.setAttribute("tempTERM_ID", map.get("TERM_ID"));
			request.setAttribute("tempTERM_NO", map.get("TERM_NO"));
			request.setAttribute("tempTERM_NAME", map.get("TERM_NAME"));
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
        String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
        if(!StringUtil.isEmpty(ADVC_ORDER_ID))
        {
        //  0156867--start--刘曰刚--2014-06-19--//
        	//修改终端退货录入明细通用选取不能选择重复数据，根据来源单据id过滤
        	StringBuffer QUERYID=new StringBuffer();
        	 List <TermreturnModelChld> result = termreturnService.childQuery(ADVC_ORDER_ID,userBean.getCHANN_TYPE());
        	 QUERYID.append("select wmsys.wm_concat( '''' || a.FROM_BILL_DTL_ID  || '''' ) QUERYDTLID from DRP_ADVC_ORDER_DTL a where a.ADVC_ORDER_ID='").
 			append(ADVC_ORDER_ID).
 			append("' and DEL_FLAG=0 ");
        	 String dtlId= termreturnService.qeuryId(QUERYID.toString());
             request.setAttribute("rst", result);
             request.setAttribute("FROM_BILL_DTL_IDS", dtlId);
         //  0156867--End--刘曰刚--2014-06-19--//
        }
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        request.setAttribute("CHANN_TYPE", userBean.getCHANN_TYPE());
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
        String ADVC_ORDER_DTL_IDs = request.getParameter("ADVC_ORDER_DTL_IDS");
        //  0156867--start--刘曰刚--2014-06-19--//
    	//修改终端退货录入明细通用选取不能选择重复数据，根据来源单据id过滤
        String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
    	StringBuffer QUERYID=new StringBuffer();
    	if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
        	QUERYID.append("select wmsys.wm_concat( '''' || a.FROM_BILL_DTL_ID  || '''' ) QUERYDTLID from DRP_ADVC_ORDER_DTL a where a.ADVC_ORDER_ID='").
			append(ADVC_ORDER_ID).append("' and a.DEL_FLAG=0 ");
        }
    	//没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(ADVC_ORDER_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("ADVC_ORDER_DTL_IDS",ADVC_ORDER_DTL_IDs);
		  params.put("CHANN_TYPE", userBean.getCHANN_TYPE());
          List <TermreturnModelChld> list = termreturnService.loadChilds(params);
          request.setAttribute("rst", list);
          QUERYID.append(" and ADVC_ORDER_DTL_ID not in (").append(ADVC_ORDER_DTL_IDs).append(")");
        }
        String dtlId= termreturnService.qeuryId(QUERYID.toString());
        request.setAttribute("FROM_BILL_DTL_IDS", dtlId);
        request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
        request.setAttribute("CHANN_TYPE", userBean.getCHANN_TYPE());
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
            String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            TermreturnModel model = new Gson().fromJson(parentJsonData, new TypeToken <TermreturnModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <TermreturnModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <TermreturnModelChld>>() {
                }.getType());
            }
            if(!StringUtil.isEmpty(model.getRETURN_STATEMENT_DATE())){
            	boolean AccFlag=LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),model.getRETURN_STATEMENT_DATE());
                if(AccFlag){
                	String message="退货结算日期:"+model.getRETURN_STATEMENT_DATE()+"已月结不能保存";
                	throw new ServiceException(message);
                }
            }
            termreturnService.txEdit(ADVC_ORDER_ID, model, chldModelList, userBean);
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
            String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <TermreturnModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <TermreturnModelChld>>() {
                }.getType());
                termreturnService.txChildEdit(ADVC_ORDER_ID, modelList,"list",userBean.getCHANN_TYPE());
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
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			termreturnService.txDelete(params);
			termreturnService.clearCaches(params);
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
            String ADVC_ORDER_DTL_IDs = request.getParameter("ADVC_ORDER_DTL_IDS");
            String ADVC_ORDER_ID=request.getParameter("ADVC_ORDER_ID");
            //批量删除，多个Id之间用逗号隔开
            termreturnService.txBatch4DeleteChild(ADVC_ORDER_DTL_IDs,ADVC_ORDER_ID);
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
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if(!StringUtil.isEmpty(ADVC_ORDER_ID)){
			Map<String,String> entry = termreturnService.load(ADVC_ORDER_ID);
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
            String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
            List <TermreturnModelChld> list = termreturnService.childQuery(ADVC_ORDER_ID,userBean.getCHANN_TYPE());
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }else{
            	Map<String,String> map=new HashMap<String,String>();
            	map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
            	map.put("STATE", BusinessConsts.COMMIT);
            	map.put("UPDATOR", userBean.getRYXXID());
            	map.put("UPD_NAME", userBean.getXM());
            	map.put("UPD_TIME", "数据库时间");
            	termreturnService.txCommitById(map);
            }
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
    
    
	
	// 导出
	@SuppressWarnings("unchecked")
	public void expertExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
	    ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "TERM_NO", params);
	    ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
	    ParamUtil.putStr2Map(request, "TEL", params);
	    ParamUtil.putStr2Map(request, "CUST_NAME", params);
	    ParamUtil.putStr2Map(request, "TERM_NAME", params);
	    ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        ParamUtil.putStr2Map(request, "RETURN_STATEMENT_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "RETURN_STATEMENT_DATE_END", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		//权限级别和审批流的封装
	    StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,"",PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		params.put("QXJBCON", sb.toString());
		
	    params.put("BILL_TYPE", "终端退货");
	    //查询时状态为查询选择状态，不然状态为未提交和退回状态
	    if(StringUtil.isEmpty(search)){
	    	 params.put("searchSTATE","'未提交','退回'");
	    }else {
	    	ParamUtil.putStr2Map(request, "STATE", params);
		}
	    if(StringUtil.isEmpty(params.get("STATE"))&&StringUtil.isEmpty(params.get("searchSTATE"))){
	    	params.put("STATES", "'未提交','提交','待退货','已入库','已结算','已退货','退回'");
	    }
	    
		// 设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
	 
		List list = termreturnService.expertExcelQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="终端退货单编号,预订单编号,终端编号,终端名称,合同编号,客户姓名,退货总金额,退货扣款金额,业务员,销售日期," +
				"货品编号,货品名称,规格型号,品牌,特殊规格说明," +
				"单价,折扣率,退货单价,退货数量,应退金额," +
				"退回原因";
		String tmpContent = "ADVC_ORDER_NO,FROM_BILL_NO,TERM_NO,TERM_NAME,CONTRACT_NO,CUST_NAME,F_PAYABLE_AMOUNT,RETURN_DEDUCT_AMOUNT,SALE_PSON_NAME,SALE_DATE," +
				"PRD_NO,PRD_NAME,PRD_SPEC,BRAND,SPCL_DTL_REMARK," +
				"PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,PAYABLE_AMOUNT," +
				"REMARK";
		String colType= "string,string,string,string,string,string,number,number,string,string," +
					   "string,string,string,string,string," +
					   "number,number,number,number,number," +
					   "string";
		try {
			ExcelUtil
					.doExport(response, list, "终端退货", tmpContent, tmpContentCn,colType);
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