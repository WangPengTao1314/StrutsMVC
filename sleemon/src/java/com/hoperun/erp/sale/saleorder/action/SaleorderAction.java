/**
 * prjName:喜临门营销平台
 * ucName:标准订单
 * fileName:SaleorderAction
*/
package com.hoperun.erp.sale.saleorder.action;
import java.io.PrintWriter;
import java.rmi.ServerException;
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
import com.hoperun.drp.sale.saleorderview.model.SaleorderviewChldModel;
import com.hoperun.erp.sale.saleorder.model.SaleorderModel;
import com.hoperun.erp.sale.saleorder.model.SaleorderModelChld;
import com.hoperun.erp.sale.saleorder.service.SaleorderService;
import com.hoperun.sys.model.UserBean;
/**
 * *@func 标准订单维护
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-10-12 13:52:19
 */
public class SaleorderAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(SaleorderAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0010701";
    private static String PVG_EDIT="BS0010702";
    private static String PVG_DELETE="BS0010707";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    //转非标订单
    private static String PVG_CONVERT="BS0010704";
    //取消预订
    private static String PVG_CANCEL="BS0010705";
    //恢复预定
    private static String PVG_RECOVER="BS0010706";
    
    
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="BS0010703";
    private static String PVG_TRACE="BS0010703";
    //审核模块
    private static String PVG_BWS_AUDIT="?";
    private static String PVG_AUDIT_AUDIT="?";
    private static String PVG_TRACE_AUDIT="?";
    //审批流参数
    private static String AUD_TAB_NAME="ERP_SALE_ORDER";
    private static String AUD_TAB_KEY="SALE_ORDER_ID";
	private static String AUD_BUSS_STATE="";
    private static String AUD_BUSS_TYPE="ERP_SALE_ORDER_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.PublicFlowInterface";
    /**审批 end**/
    /** 业务service*/
	private SaleorderService saleorderService;
    /**
	 * Sets the Saleorder service.
	 * 
	 * @param SaleorderService the new Saleorder service
	 */
	public void setSaleorderService(SaleorderService saleorderService) {
		this.saleorderService = saleorderService;
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String,String> params = new HashMap<String,String>();
	    
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);//订货订单编号
	    
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    params.put("BASE_ADD_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String BILL_TYPE = ParamUtil.get(request,"BILL_TYPE");
		
		//module = all [销售订单查]
		if("all".equals(module)){
			 //查看非标和标准的订单
		}else{
			//params.put("BILL_TYPE",BusinessConsts.STANDARD);//标准
		}
		 
		params.put("XTYHID",userBean.getXTYHID());
		StringBuffer strQx = new StringBuffer("");
		strQx.append(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		if(StringUtil.isEmpty(search) || StringUtil.isEmpty(BILL_TYPE)){
			strQx.append(" and u.BILL_TYPE in ('标准','赠品订货','返利订货') ");
		}else if(!StringUtil.isEmpty(BILL_TYPE)){
			params.put("BILL_TYPE",BILL_TYPE); 
		} 
		//只查询审核通过状态的数据
		if(StringUtil.isEmpty(search)){
			strQx.append(" and u.STATE='"+BusinessConsts.UNCOMMIT+"' ");
		}else{
			strQx.append(" and u.STATE in ('未提交','已取消','审核通过','已发货','待发货','已完成','退回') ");
		}
		
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		params.put("QXJBCON", sb.toString());
	    
	    //渠道分管sql  by zzb 2014-6-17
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = saleorderService.pageQuery(params, pageNo);
		params.put("module", module);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String SALE_ORDER_ID =ParamUtil.get(request, "SALE_ORDER_ID");
        String module = ParamUtil.get(request,"module");
        if(!StringUtil.isEmpty(SALE_ORDER_ID))
        {
        	 List <SaleorderModelChld> result = saleorderService.childQuery(SALE_ORDER_ID);
        	 if(null != result){
        		 request.setAttribute("resultSize", result.size()); 
        	 }
             request.setAttribute("rst", result);
            
        }
        request.setAttribute("module",module);
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
		String SALE_ORDER_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(SALE_ORDER_ID)){
			Map<String,String> entry = saleorderService.load(SALE_ORDER_ID);
			request.setAttribute("rst", entry);
		}
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
        String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        if(!StringUtil.isEmpty(SALE_ORDER_ID))
        {
        	 List <SaleorderModelChld> result = saleorderService.childQuery(SALE_ORDER_ID);
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
    public ActionForward toChildEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String SALE_ORDER_DTL_IDs = request.getParameter("SALE_ORDER_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(SALE_ORDER_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDs);
          List <SaleorderModelChld> list = saleorderService.loadChilds(params);
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
            String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            SaleorderModel model = new Gson().fromJson(parentJsonData, new TypeToken <SaleorderModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <SaleorderModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <SaleorderModelChld>>() {
                }.getType());
            }
            saleorderService.txEdit(SALE_ORDER_ID, model, chldModelList, userBean);
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
            String SALE_ORDER_ID = request.getParameter("SALE_ORDER_ID");
            String BILL_TYPE=request.getParameter("BILL_TYPE");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <SaleorderModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <SaleorderModelChld>>() {
                }.getType());
                saleorderService.txChildEdit(SALE_ORDER_ID, modelList,"list",BILL_TYPE);
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
			String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
			Map <String, String> params = new HashMap <String, String>();
            params.put("SALE_ORDER_ID", SALE_ORDER_ID);
            params.put("UPD_NAME", userBean.getYHM());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			saleorderService.txDelete(params);
			saleorderService.clearCaches(params);
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
            String SALE_ORDER_DTL_IDs = request.getParameter("SALE_ORDER_DTL_IDS");
            //批量删除，多个Id之间用逗号隔开
            saleorderService.txBatch4DeleteChild(SALE_ORDER_DTL_IDs);
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
		if(!StringUtil.isEmpty(SALE_ORDER_ID)){
			Map<String,String> entry = saleorderService.load(SALE_ORDER_ID);
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
            String SALE_ORDER_ID = request.getParameter("SALE_ORDER_ID");
            String SALE_ORDER_NO = request.getParameter("SALE_ORDER_NO");
            List <SaleorderModelChld> list = saleorderService.childQuery(SALE_ORDER_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }else{
            	saleorderService.txToCommit(SALE_ORDER_ID,SALE_ORDER_NO,userBean);
            	jsonResult = jsonResult(true, "提交成功");
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
    
    /**
     * 转非标订单
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void convert(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try{
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String SALE_ORDER_DTL_IDS = ParamUtil.get(request, "SALE_ORDER_DTL_IDS");
        	boolean isAll = ParamUtil.getBoolean(request, "isAll");
        	this.saleorderService.txConvertTechOrder(SALE_ORDER_ID,SALE_ORDER_DTL_IDS,isAll,userBean);
        	jsonResult = jsonResult(true,"转非标订单成功");
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"转非标订单失败");
        	e.printStackTrace();
		}
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        
    }
    
    /**
     * * to 取消预订编辑页面
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toCancelOrderEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String SALE_ORDER_DTL_IDs = request.getParameter("SALE_ORDER_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(SALE_ORDER_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDs);
          List <SaleorderModelChld> list = saleorderService.loadChilds(params);
          request.setAttribute("rst", list);
        }
        return mapping.findForward("canceledit");
    }
    /**
     * 取消预定
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void cancelOrder(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        try{
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String SALE_ORDER_DTL_IDS = ParamUtil.get(request, "SALE_ORDER_DTL_IDS");
        	String FROM_BILL_DTL_IDS = ParamUtil.get(request, "FROM_BILL_DTL_IDS");
        	if(StringUtil.isEmpty(SALE_ORDER_DTL_IDS)){
        		throw new ServerException("明细ID为空");
        	}
        	this.saleorderService.txCalcelSaleOrder(SALE_ORDER_ID,SALE_ORDER_DTL_IDS,FROM_BILL_DTL_IDS,userBean);
        	jsonResult = jsonResult(true,"操作成功");
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"操作失败");
        	logger.info(e);
		}
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        
    }
    
    
    /**
     * 恢复预定
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void recoverOrder(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        try{
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String SALE_ORDER_DTL_IDS = ParamUtil.get(request, "SALE_ORDER_DTL_IDS");
        	String FROM_BILL_DTL_IDS = ParamUtil.get(request, "FROM_BILL_DTL_IDS");
        	this.saleorderService.txRecoverOrder(SALE_ORDER_ID,SALE_ORDER_DTL_IDS,FROM_BILL_DTL_IDS,userBean);
        	jsonResult = jsonResult(true,"操作成功");
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"操作失败");
        	e.printStackTrace();
		}
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        
    }
    
    /**
     * 查询销售订单的生命周期
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward toTrace(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
		if(!StringUtil.isEmpty(SALE_ORDER_ID)){
			List<Map<String,String>> result = saleorderService.queryTrace(SALE_ORDER_ID);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("toTrace");
	}
	
	
	/**
	 * * to 订货订单框架页面
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toGoodsFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		String search = ParamUtil.get(request, "search");
		String SALE_ORDER_ID=ParamUtil.get(request, "SALE_ORDER_ID");
		request.setAttribute("paramUrl", paramUrl);
		request.setAttribute("search", search);
		request.setAttribute("SALE_ORDER_ID", SALE_ORDER_ID);
		return mapping.findForward("goodsFrames");
	}
	
	
	/**
	 * * query List data
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toGoodsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,String> params=new HashMap<String, String>();
		params.put("SALE_ORDER_ID", ParamUtil.get(request, "SALE_ORDER_ID"));
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_RECV_DATE", "ASC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = saleorderService.goodsPageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("goodsList");
	}
	/**
	 * * 明细列表
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward goodsChildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
		if (!StringUtil.isEmpty(GOODS_ORDER_ID)) {
			List<SaleorderviewChldModel> result = saleorderService.goodsChildQuery(GOODS_ORDER_ID);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("goodsChildlist");
	}
	
	
	
	 /**
     * 查询收货方赠品折扣
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void getChannDECT_RATE(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try{
        	String CHANN_ID=request.getParameter("CHANN_ID");
        	String BILL_TYPE=request.getParameter("BILL_TYPE");
        	String DECT_RATE;
        	if(BILL_TYPE.equals("赠品订货")){
        		DECT_RATE= saleorderService.getDECT_RATE(CHANN_ID);
        	}else{
        		DECT_RATE=LogicUtil.getChannDsct(CHANN_ID);
        	}
        	if(StringUtil.isEmpty(DECT_RATE)){
        		throw new ServiceException("总部未设置所选订货方的折扣，请设置后进行操作！");
        	}
        	Map<String,String> map=new HashMap<String, String>();
        	map.put("DECT_RATE", DECT_RATE);
        	jsonResult = new Gson().toJson(new Result(true, map, ""));
        }catch(ServiceException s){
        	jsonResult = jsonResult(false,s.getMessage());
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"获取渠道赠品折扣失败");
        	e.printStackTrace();
		}
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        
    }
    
    
    public void toExpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        Map<String, String> params = new HashMap<String, String>();
        ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
	    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);//订货订单编号
	    
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    params.put("BASE_ADD_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		String BILL_TYPE = ParamUtil.get(request,"BILL_TYPE");
		StringBuffer strQx = new StringBuffer("");
		strQx.append(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		//只查询审核通过状态的数据
		if(StringUtil.isEmpty(params.get("STATE"))&&params.isEmpty()){
			strQx.append(" and u.STATE='"+BusinessConsts.UNCOMMIT+"' ");
		}else{
			strQx.append(" and u.STATE in ('未提交','已取消','审核通过','待发货','已发货','已完成','退回') ");
		}
		 
		params.put("XTYHID",userBean.getXTYHID());
		if(StringUtil.isEmpty(BILL_TYPE)){
			strQx.append(" and u.BILL_TYPE in ('标准','赠品订货','返利订货') ");
		}else{
			params.put("BILL_TYPE",BILL_TYPE); 
		} 
		
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		params.put("QXJBCON", sb.toString());
	    
	    //渠道分管sql  by zzb 2014-6-17
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		
		//字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
	    
		
		List list = saleorderService.qrySaleOrderExp(params);
		
        //excel数据上列显示的列明
        String tmpContentCn="销售订单编号,单据类型,订货方编号,发货方名称,是否使用返利,收货方编号,收货方名称,联系人,电话,生产基地名称,收货地址编号," +
        		"货品编号,货品名称,规格型号,品牌,标准单位,是否是备货,是否非标,特殊规格说明,是否赠品标记,供货价,折扣率,折后单价,订货数量,订货金额,体积," +
        		"明细备注,区域名称,制单人名称,组织名称,收货地址,区域经理名称,区域经理电话,区域服务中心编号,区域服务中心名称,状态,主表备注";
        //
        String tmpContent="SALE_ORDER_NO,BILL_TYPE,ORDER_CHANN_NO,ORDER_CHANN_NAME,IS_USE_REBATE,RECV_CHANN_NO,RECV_CHANN_NAME," +
        		"PERSON_CON,TEL,SHIP_POINT_NAME,RECV_ADDR_NO,PRD_NO,PRD_NAME,PRD_SPEC,BRAND,STD_UNIT,IS_BACKUP_FLAG," +
        		"IS_NO_STAND_FLAG,SPCL_SPEC_REMARK,IS_FREE_FLAG,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,ORDER_AMOUNT,VOLUME,REMARKDTL," +
        		"AREA_NAME,CRE_NAME,LEDGER_NAME,RECV_ADDR,AREA_MANAGE_NAME,AREA_MANAGE_TEL,AREA_SER_NO,AREA_SER_NAME,STATE,REMARK";
        try {
			ExcelUtil.doExport(response, list, "标准订单", tmpContent, tmpContentCn);
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
	    	pvgMap.put("PVG_CONVERT",QXUtil.checkPvg(userBean, PVG_CONVERT) );
	    	pvgMap.put("PVG_CANCEL",QXUtil.checkPvg(userBean, PVG_CANCEL));
	    	pvgMap.put("PVG_RECOVER",QXUtil.checkPvg(userBean, PVG_RECOVER));
	    	
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
}