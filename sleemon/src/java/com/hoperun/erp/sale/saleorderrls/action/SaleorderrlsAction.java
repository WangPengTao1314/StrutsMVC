/**
 * prjName:喜临门营销平台
 * ucName:标准订单
 * fileName:SaleorderAction
*/
package com.hoperun.erp.sale.saleorderrls.action;
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
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.saleorderrls.service.SaleorderrlsService;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModel;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * * 销售订单审核
 * *@func 
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-10-12 13:52:19
 */
public class SaleorderrlsAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(SaleorderrlsAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0010901";
    private static String PVG_EDIT="?";
    private static String PVG_DELETE="?";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    //转标准订单
    private static String PVG_CONVERT="BS0010804";
    //取消预订
    private static String PVG_CANCEL="BS0010805";
    //恢复预定
    private static String PVG_RECOVER="BS0010806";
    
    
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="BS0010902";
    private static String PVG_AUDIT_AUDIT="BS0010902";
    private static String PVG_TRACE_AUDIT="BS0010902";
    //审批流参数
    private static String AUD_TAB_NAME="ERP_SALE_ORDER";
    private static String AUD_TAB_KEY="SALE_ORDER_ID";
	private static String AUD_BUSS_STATE="u.STATE";
    private static String AUD_BUSS_TYPE="ERP_SALE_ORDER_AUDIT";
	private static String AUD_FLOW_INS="com.hoperun.sys.service.impl.SaleorderrlsFlowInterface";
    /**审批 end**/
    /** 业务service*/
	private SaleorderrlsService saleorderrlsService;
    
	 
	
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
		String SALE_ORDER_ID = ParamUtil.get(request,"selRowId");
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
	    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
	    ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
	    
	    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "ORDER_DATE_BEGIN", params);
	    ParamUtil.putStr2Map(request, "ORDER_DATE_END", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
 
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "IS_CANCELED_FLAG", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		params.put("XTYHID",userBean.getXTYHID());
		
		if(StringUtil.isEmpty(search) && !StringUtil.isEmpty(SALE_ORDER_ID) && -1 != SALE_ORDER_ID.indexOf("_")){
			params.put("SALE_ORDER_ID",SALE_ORDER_ID);//订货订单生命跟踪 跳转
		} 
		
		StringBuffer strQx = new StringBuffer("");
		strQx.append(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		
		String PROVS=request.getParameter("PROV");
		if(!StringUtil.isEmpty(PROVS)){
			sb.append(" and u.ORDER_CHANN_ID in ( select CHANN_ID from BASE_CHANN where 1=1 ");
			String sql = StringUtil.creCon("PROV", PROVS, ",");
			sb.append(sql).append(")");
		}
		
		params.put("QXJBCON", sb.toString());
	    
	    //渠道分管sql  by zzb 2014-6-17
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		
		//字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_DATE", "ASC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = saleorderrlsService.pageQuery(params, pageNo);
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
        if(!StringUtil.isEmpty(SALE_ORDER_ID))
        {
        	 List <SaleorderspModelChld> result = saleorderrlsService.childQuery(SALE_ORDER_ID);
        	 if(null != result){
        		 request.setAttribute("resultSize", result.size()); 
        	 }
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
		String SALE_ORDER_ID = ParamUtil.get(request, "selRowId");
		if(!StringUtil.isEmpty(SALE_ORDER_ID)){
			Map<String,String> entry = saleorderrlsService.load(SALE_ORDER_ID);
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
        	 List <SaleorderspModelChld> result = saleorderrlsService.childQuery(SALE_ORDER_ID);
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
          List <SaleorderspModelChld> list = saleorderrlsService.loadChilds(params);
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
            SaleorderspModel model = new Gson().fromJson(parentJsonData, new TypeToken <SaleorderspModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <SaleorderspModelChld> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
                chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <SaleorderspModelChld>>() {
                }.getType());
            }
            saleorderrlsService.txEdit(SALE_ORDER_ID, model, chldModelList, userBean);
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
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <SaleorderspModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <SaleorderspModelChld>>() {
                }.getType());
                saleorderrlsService.txChildEdit(SALE_ORDER_ID, modelList);
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
            saleorderrlsService.txDelete(params);
            saleorderrlsService.clearCaches(params);
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
            saleorderrlsService.txBatch4DeleteChild(SALE_ORDER_DTL_IDs);
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
			Map<String,String> entry = saleorderrlsService.load(SALE_ORDER_ID);
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
            List <SaleorderspModelChld> list = saleorderrlsService.childQuery(SALE_ORDER_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
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
     * 转标准订单
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
        	
        	this.saleorderrlsService.txConvertTechOrder(SALE_ORDER_ID,SALE_ORDER_DTL_IDS,isAll,userBean);
        	jsonResult = jsonResult(true,"转标准订单成功");
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"转标准订单失败");
        	e.printStackTrace();
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
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try{
        	String SALE_ORDER_DTL_ID = ParamUtil.get(request, "SALE_ORDER_DTL_ID");
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	this.saleorderrlsService.recoverOrder(SALE_ORDER_ID,SALE_ORDER_DTL_ID);
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
     * 取消预定
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void queryTechSate(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try{
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String sate = this.saleorderrlsService.queryTechState(SALE_ORDER_ID);
        	jsonResult = jsonResult(true,sate);
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
        String SALE_ORDER_ID = request.getParameter("SALE_ORDER_ID");
        String BILL_TYPE = ParamUtil.utf8Decoder(request, "BILL_TYPE");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(SALE_ORDER_DTL_IDs)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDs);
          List <SaleorderspModelChld> list = saleorderrlsService.loadChilds(params);
          request.setAttribute("rst", list);
          request.setAttribute("SALE_ORDER_ID", SALE_ORDER_ID);
          request.setAttribute("BILL_TYPE", BILL_TYPE);
          
        }
        return mapping.findForward("toCanceledit");
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
			String jsonDate = ParamUtil.get(request, "JsonData");
			List<SaleorderspModelChld> chldModelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				chldModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<SaleorderspModelChld>>() {
						}.getType());
			}
        	
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String SALE_ORDER_DTL_IDS = ParamUtil.get(request, "SALE_ORDER_DTL_IDS");
        	String FROM_BILL_DTL_IDS = ParamUtil.get(request, "FROM_BILL_DTL_IDS");
        	String BILL_TYPE = ParamUtil.get(request, "BILL_TYPE");
        	if(StringUtil.isEmpty(SALE_ORDER_DTL_IDS)){
        		throw new ServerException("明细ID为空");
        	}
        	this.saleorderrlsService.txCalcelSaleOrder(SALE_ORDER_ID,BILL_TYPE,SALE_ORDER_DTL_IDS,FROM_BILL_DTL_IDS,chldModelList,userBean);
        	jsonResult = jsonResult(true,"操作成功");
        }catch (Exception e) {
        	jsonResult = jsonResult(false,"操作失败");
        	logger.info(e);
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
	 
		public SaleorderrlsService getSaleorderrlsService() {
			return saleorderrlsService;
		}
		public void setSaleorderrlsService(SaleorderrlsService saleorderrlsService) {
			this.saleorderrlsService = saleorderrlsService;
		}
		/**
	     * * 子表 新增/修改数据
	     * @param mapping the mapping
	     * @param form the form
	     * @param request the request
	     * @param response the response
	     */
	    public void childSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        try {
	            String jsonDate = request.getParameter("childJsonData");
	            if (!StringUtil.isEmpty(jsonDate)) {
	                List <SaleorderspModelChld> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <SaleorderspModelChld>>() {
	                }.getType());
	                saleorderrlsService.txChildSave(modelList);
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
	 
	 
	    public void toExpData(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean =  ParamUtil.getUserBean(request);
	        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
	    	{
	    		throw new ServiceException("对不起，您没有权限 !");
	    	}
			Map<String,String> params = new HashMap<String,String>();
			String SALE_ORDER_ID = ParamUtil.get(request,"selRowId");
			 ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
		    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
		    ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		    
		    ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);
		    ParamUtil.putStr2Map(request, "STATE", params);
		    ParamUtil.putStr2Map(request, "AREA_NO", params);
		    ParamUtil.putStr2Map(request, "AREA_NAME", params);
		    ParamUtil.putStr2Map(request, "ORDER_DATE_BEGIN", params);
		    ParamUtil.putStr2Map(request, "ORDER_DATE_END", params);
		    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	 
		    ParamUtil.putStr2Map(request, "PRD_NO", params);
		    ParamUtil.putStr2Map(request, "PRD_NAME", params);
		    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
		    
	       //只查询0的记录。1为删除。0为正常
	        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	        String search = ParamUtil.get(request,"search");
			String module = ParamUtil.get(request,"module");
			params.put("XTYHID",userBean.getXTYHID());
			
			if(StringUtil.isEmpty(search) && !StringUtil.isEmpty(SALE_ORDER_ID) && -1 != SALE_ORDER_ID.indexOf("_")){
				params.put("SALE_ORDER_ID",SALE_ORDER_ID);//订货订单生命跟踪 跳转
			} 
			
			StringBuffer strQx = new StringBuffer("");
			strQx.append(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			
			//权限级别和审批流的封装
			StringBuffer sb = new StringBuffer();
			sb.append(StringUtil.getStrQX("u",strQx.toString()));
			params.put("QXJBCON", sb.toString());
		    
		    //渠道分管sql  by zzb 2014-6-17
			String CHANNS_CHARG = userBean.getCHANNS_CHARG();
			params.put("CHANNS_CHARG_EXP", CHANNS_CHARG);
			
			List list = saleorderrlsService.qrySaleOrderExp(params);
			
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
				ExcelUtil.doExport(response, list, "销售订单明细", tmpContent, tmpContentCn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
}