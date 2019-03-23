/**
 * prjName:喜临门营销平台
 * ucName:标准订单
 * fileName:SaleorderAction
*/
package com.hoperun.erp.sale.saleordermge.action;
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
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.InterfaceInvokeUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.saleordermge.service.SaleordermgeService;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModel;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * * 销售订单处理
 * *@func 
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-10-12 13:52:19
 */
public class SaleordermgeAction extends BaseAction {
	/**日志**/
	private Logger logger = Logger.getLogger(SaleordermgeAction.class);
	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS = "BS0013701";
    private static String PVG_EDIT = "BS0013702";
    private static String PVG_DELETE="?";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    //转标准订单
    private static String PVG_CONVERT="";
    //取消预订
    private static String PVG_CANCEL="";
    //恢复预定
    private static String PVG_RECOVER="";
    
    //反审核
    private static String PVG_OPPOSEAUDIT = "BS0013703";
    //提交生产
    private static String PVG_COMMIT = "BS0013704";
    //撤销生产
    private static String PVG_CANCLE = "BS0013705";
    //行关闭
    private static String PVG_ROW_CLOSE = "BS0013706";
    //强制关闭
    private static String PVG_ROW_MUST_CLOSE = "BS0013707";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
	private static String AUD_BUSS_STATE="";
    private static String AUD_BUSS_TYPE="";
	private static String AUD_FLOW_INS="";
    /**审批 end**/
    /** 业务service*/
	private SaleordermgeService saleordermgeService;
    
	 
	
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
	    ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params);
	    
	    ParamUtil.putStr2MaptoUpperCase(request, "GOODS_ORDER_NO", params);
	    ParamUtil.putStr2Map(request, "STATE", params);
	    ParamUtil.putStr2Map(request, "AREA_NO", params);
	    ParamUtil.putStr2Map(request, "AREA_NAME", params);
	    ParamUtil.putStr2Map(request, "ORDER_DATE_BEGIN", params);
	    ParamUtil.putStr2Map(request, "ORDER_DATE_END", params);
	    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
	    ParamUtil.putStr2Map(request, "CRE_NAME", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "ROWSTATE", params);
	    ParamUtil.putStr2Map(request, "IS_CANCELED_FLAG", params);
       //只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		params.put("XTYHID",userBean.getXTYHID());
		String ROWSTATE=request.getParameter("ROWSTATE");
		StringBuffer STATEsql=new StringBuffer();
		 if("正常".equals(ROWSTATE)){
			 STATEsql.append("  NVL(c.ORDER_NUM, 0) > NVL(c.SENDED_NUM, 0) ");
	    }else if("已发货".equals(ROWSTATE)){
	    	STATEsql.append("  NVL(c.ORDER_NUM, 0) = NVL(c.SENDED_NUM, 0) ");
	    }else if("关闭".equals(ROWSTATE)){
	    	STATEsql.append("  c.STATE='关闭' ");
	    }else{
	    	STATEsql.append("  1=1 ");
	    }
		 params.put("STATESQL", STATEsql.toString());
		StringBuffer strQx = new StringBuffer("");
		strQx.append(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
		//权限级别和审批流的封装
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		if(StringUtil.isEmpty(search)){
			sb.append(" and u.STATE = '审核通过' ");
		} 
		String PROVS=request.getParameter("PROV");
		if(!StringUtil.isEmpty(PROVS)){
			sb.append(" and u.ORDER_CHANN_ID in ( select CHANN_ID from BASE_CHANN where 1=1 ");
			String sql = StringUtil.creCon("PROV", PROVS, ",");
			sb.append(sql).append(")");
		}
		ParamUtil.putStr2Map(request, "PROV", params);
		params.put("QXJBCON", sb.toString());
	    
	    //渠道分管sql  by zzb 2014-6-17
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		//字段排序
		ParamUtil.setOrderField(request, params, "u.STATE DESC,u.ORDER_DATE", "ASC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = saleordermgeService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("IS_NO_ADVC_DATE_FLAG", Consts.IS_NO_ADVC_DATE_FLAG);
		return mapping.findForward("list");
	}
	/**
	 * 根据销售订单id获取明细默认交期
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toChldAdvcData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_COMMIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String SALE_ORDER_ID = request.getParameter("SALE_ORDER_ID");
		if (!StringUtil.isEmpty(SALE_ORDER_ID)) {
			List list = saleordermgeService.getChldAdvcDtat(SALE_ORDER_ID);
			request.setAttribute("rst", list);
		}
		request.setAttribute("SALE_ORDER_ID", SALE_ORDER_ID);
		request.setAttribute("SALE_ORDER_NO", request.getParameter("SALE_ORDER_NO"));
		return mapping.findForward("editChldAdvcData");
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
        String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        Map<String, String> params = new HashMap<String, String>();
		params.put("SALE_ORDER_ID", SALE_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ROW_NO", "ASC");
		 
        if(!StringUtil.isEmpty(SALE_ORDER_ID)) {
        	 List <SaleorderspModelChld> result = saleordermgeService.childQuery(params);
        	 if(null != result){
        		 request.setAttribute("resultSize", result.size()); 
        	 }
             request.setAttribute("rst", result);
        }
        request.setAttribute("IS_NO_ADVC_DATE_FLAG", Consts.IS_NO_ADVC_DATE_FLAG);
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
			Map<String,String> entry = saleordermgeService.load(SALE_ORDER_ID);
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
        	 List <Map<String,Object>> result = saleordermgeService.childMapQuery(SALE_ORDER_ID);
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
          List <SaleorderspModelChld> list = saleordermgeService.loadChilds(params);
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
            saleordermgeService.txEdit(SALE_ORDER_ID, model, chldModelList, userBean);
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
                saleordermgeService.txChildEdit(SALE_ORDER_ID, modelList);
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
            saleordermgeService.txDelete(params);
            saleordermgeService.clearCaches(params);
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
            saleordermgeService.txBatch4DeleteChild(SALE_ORDER_DTL_IDs);
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
			Map<String,String> entry = saleordermgeService.load(SALE_ORDER_ID);
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_COMMIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String errorId = "";
        try {
            String SALE_ORDER_ID = request.getParameter("SALE_ORDER_ID");
            Map<String,String> model = saleordermgeService.load(SALE_ORDER_ID);
            if(null != model){
            	//控制状态只能是’审核通过‘和‘已核价’
            	if(!BusinessConsts.PASS.equals(model.get("STATE")) && !"已核价".equals(model.get("STATE"))){
            		errorId = "4";
                    throw new Exception();
            	}
            	//判断生产基地是否填写
            	if(StringUtil.isEmpty(model.get("SHIP_POINT_ID"))){
            		errorId = "3";
                    throw new Exception();
            	}
            }
            List <SaleorderspModelChld> list = saleordermgeService.childModelQuery(SALE_ORDER_ID);
            if (list.size() == 0) {
                errorId = "0";
                throw new Exception();
            }
            for(SaleorderspModelChld child : list){
            	//判断交期
            	if(StringUtil.isEmpty(child.getADVC_SEND_DATE())){
            		errorId = "1";
                    throw new Exception();
            	}
            	if(StringUtil.isEmpty(child.getU_CODE())){
            		errorId = "5";
                    throw new Exception();
            	}
            	//判断新花号新规格是否填写
//            	if("1".equals(child.getSPCL_TECH_FLAG())){
//            		if(StringUtil.isEmpty(child.getNEW_COLOR()) || StringUtil.isEmpty(child.getNEW_SPEC())){
//            			errorId = "2";
//                        throw new Exception();
//            		}
//            		
//            	}
            }
            jsonResult = jsonResult(true, "");
        } catch (Exception e) {
            if ("0".equals(errorId)) {
                jsonResult = jsonResult(false, "没有明细，不能提交!");
            }else if("1".equals(errorId)){
            	jsonResult = jsonResult(false, "明细的'预计发货日期'未填写");
            }else if("2".equals(errorId)){
            	jsonResult = jsonResult(false, "明细的'新花号'或者'新规格'未填写");
            }else if("3".equals(errorId)){
            	jsonResult = jsonResult(false, "单据的发货基地未填写");
            }else if("4".equals(errorId)){
            	jsonResult = jsonResult(false, "当前单据状态下不能提交生产");
            }else if("5".equals(errorId)){
            	jsonResult = jsonResult(false, "明细的'U_CODE'未填写,请先反审核，重新做单");
            }else {
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
     * 反审核
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void opposeAudit(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_OPPOSEAUDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        
        try{
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String FROM_BILL_ID = ParamUtil.get(request, "FROM_BILL_ID");
        	String BILL_TYPE = ParamUtil.get(request, "BILL_TYPE");
        	this.saleordermgeService.txOpposeAudit(SALE_ORDER_ID,BILL_TYPE,FROM_BILL_ID,userBean);
        	jsonResult = jsonResult(true,"操作成功");
        }catch (ServiceException e) {
        	jsonResult = jsonResult(false,e.getMessage());
        	e.printStackTrace();
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
     * 提交生产
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void commitProduction(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_COMMIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
		String ServiceCode = "TC0300010";
		String AppCode = "DM";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode + ":" + "createSO";
		String strResult ="";
		String strJsonData ="";
    	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
    	String SALE_ORDER_NO = ParamUtil.get(request, "SALE_ORDER_NO");
    	String jsonDate = ParamUtil.get(request, "childData");

        try{
        	if(StringUtil.isEmpty(jsonDate)&&Consts.IS_NO_ADVC_DATE_FLAG.equals("1")){
				throw new ServiceException("对不起，您所选的销售订单没有明细 !");
			}
			List<SaleorderspModelChld> modelList = new Gson().fromJson(
					jsonDate,
					new TypeToken<List<SaleorderspModelChld>>() {
					}.getType());
			 //修改交期
	 		if(Consts.IS_NO_ADVC_DATE_FLAG.equals("1")){
	 			this.saleordermgeService.upChldAdvcData(modelList);
			}
    		strJsonData = InterfaceInvokeUtil.getStrCreateSO(SALE_ORDER_ID,null,UId);
    		LogicUtil.actLog("销售订单管理", "开始调入生成销售订单接口", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE,SALE_ORDER_NO);
    		strResult = this.saleordermgeService.txCommitProduction(SALE_ORDER_ID,SALE_ORDER_NO,userBean,strJsonData, AppCode, UId, OPRCODE,modelList);
            if("操作成功".equals(strResult)){
            	LogicUtil.actLog("销售订单管理", "生成销售订单成功", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE,SALE_ORDER_NO);
            	jsonResult = jsonResult(true,strResult);
            }else{
            	LogicUtil.actLog("销售订单管理", "生成销售订单失败", "U9系统", "失败","["+strResult+"]"+strJsonData,AppCode,UId,OPRCODE,SALE_ORDER_NO);
            	jsonResult = jsonResult(false,strResult);
            }
        }catch (ServiceException e) {
           	LogicUtil.actLog("销售订单管理", "生成销售订单失败", "U9系统", "失败","["+e.getMessage()+"]"+strJsonData,AppCode,UId,OPRCODE,SALE_ORDER_NO);
        	jsonResult = jsonResult(false,e.getMessage());
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
     * 撤销生产
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void toRevoke(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	UserBean userBean = ParamUtil.getUserBean(request);
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_CANCLE)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        try{
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String SALE_ORDER_NO = ParamUtil.get(request, "SALE_ORDER_NO");
        	List<Map<String,Object>> list = saleordermgeService.childMapQuery(SALE_ORDER_ID);
        	if(null != list && !list.isEmpty()){
        		for(Map<String,Object> child:list){
        			double SENDED_NUM = StringUtil.getDouble(child.get("SENDED_NUM"));
        			if(SENDED_NUM>0){
        				throw new ServiceException("该单据已有货品发货，不能撤销");
        			}
        		}
        	}
        	this.saleordermgeService.txToRevoke(SALE_ORDER_ID,SALE_ORDER_NO,userBean);
        	jsonResult = jsonResult(true,"操作成功");
        }catch (ServiceException e) {
        	jsonResult = jsonResult(false,e.getMessage());
        	e.printStackTrace();
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
     * 生产状态查询
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward queryProStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		//获取参数
		Map<String,String> params = new HashMap<String,String>();
    	params.put("DeliverPlanNo", ParamUtil.utf8Decoder(request, "SALE_ORDER_NO"));//销售订单号
		Map<String,Object> page = new HashMap<String,Object>();
		
		//调用服务
		List rst = saleordermgeService.queryProStatus(params);		
		page.put("result", rst);
		
		//检查服务调用结果
		if(rst != null && rst.size() == 1){
			Map temp = (Map) rst.get(0);
			String status =  (String) temp.get("Status");
			String showMsg = (String) temp.get("Desc");
			
			if(!StringUtil.isEmpty(status) && "false".equalsIgnoreCase(status)){
				if("No Results for Query!".equals(showMsg)){
					page.put("errorMsg", "U9未查找到数据");
				}else{
					page.put("errorMsg", showMsg);
				}
			}
		}
		
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		return mapping.findForward("toQueryStatus");
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
        	this.saleordermgeService.recoverOrder(SALE_ORDER_ID,SALE_ORDER_DTL_ID);
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
        	String sate = this.saleordermgeService.queryTechState(SALE_ORDER_ID);
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
          List <SaleorderspModelChld> list = saleordermgeService.loadChilds(params);
          request.setAttribute("rst", list);
          request.setAttribute("SALE_ORDER_ID", SALE_ORDER_ID);
          request.setAttribute("BILL_TYPE", BILL_TYPE);
        }
        return mapping.findForward("toCanceledit");
    }
    
    
    
    /**
     * 行关闭
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void closeOrder(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        try{
			String SALE_ORDER_DTL_IDS = ParamUtil.get(request, "SALE_ORDER_DTL_IDS");
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String BILL_TYPE = ParamUtil.get(request, "BILL_TYPE");
         	if(StringUtil.isEmpty(SALE_ORDER_DTL_IDS)){
        		throw new ServiceException("明细ID为空");
        	}
        	this.saleordermgeService.txCloseOrder(SALE_ORDER_ID,BILL_TYPE,SALE_ORDER_DTL_IDS,userBean);
        	jsonResult = jsonResult(true,"操作成功");
        }catch (Exception e) {
        	jsonResult = jsonResult(false,e.getMessage());
        	logger.error(e);
		}
        
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
        
    }
    
    
    
    /**
     * 强制关闭
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void forceCloseOrder(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response){
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        UserBean userBean = ParamUtil.getUserBean(request);
        try{
			String SALE_ORDER_DTL_IDS = ParamUtil.get(request, "SALE_ORDER_DTL_IDS");
        	String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        	String BILL_TYPE = ParamUtil.get(request, "BILL_TYPE");
        	
         	if(StringUtil.isEmpty(SALE_ORDER_DTL_IDS)){
        		throw new ServiceException("明细ID为空");
        	}
        	this.saleordermgeService.txforceCloseOrder(SALE_ORDER_ID,BILL_TYPE,SALE_ORDER_DTL_IDS,userBean);
        	jsonResult = jsonResult(true,"操作成功");
        }catch (Exception e) {
        	jsonResult = jsonResult(false,e.getMessage());
        	logger.error(e);
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
        		throw new ServiceException("明细ID为空");
        	}
        	this.saleordermgeService.txCalcelSaleOrder(SALE_ORDER_ID,BILL_TYPE,SALE_ORDER_DTL_IDS,FROM_BILL_DTL_IDS,chldModelList,userBean);
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
     * * 出库明细列表
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward storeOutlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
        Map<String, String> params = new HashMap<String, String>();
		params.put("SALE_ORDER_ID", SALE_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 
        if(!StringUtil.isEmpty(SALE_ORDER_ID)) {
        	List<Map<String,Object>>  result = saleordermgeService.queryStorePrdDtl(params);
        	 if(null != result){
        		 request.setAttribute("resultSize", result.size()); 
        	 }
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("storeOutlist");
    }
    
    
    
	/**
	 * 查询可用信用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void queryCredit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
             
			String CHANN_ID = ParamUtil.get(request,"CHANN_ID");
			String CHANN_NO = ParamUtil.get(request,"CHANN_NO");
			Map<String,Object> entry = new HashMap<String,Object>();
			//用户所在渠道信息
			Map<String,Object> chann = saleordermgeService.loadChann(CHANN_ID);
			entry.put("INI_CREDIT", chann.get("INI_CREDIT"));//初始信用
			entry.put("REBATE_TOTAL", chann.get("REBATE_TOTAL"));//返利总额
			entry.put("REBATE_CURRT", chann.get("REBATE_CURRT"));//当前返利
//			entry.put("CREDIT_TOTAL", chann.get("CREDIT_TOTAL"));//信用总额
			entry.put("TEMP_CREDIT_REQ", chann.get("TEMP_CREDIT_REQ"));//临时信用
			entry.put("CREDIT_CURRT", chann.get("CREDIT_CURRT"));//当前信用
			entry.put("FREEZE_CREDIT", chann.get("FREEZE_CREDIT"));//冻结信用
			entry.put("PAYMENT_CREDIT", chann.get("PAYMENT_CREDIT"));//付款凭证信用
			entry.put("CHANN_NO", chann.get("CHANN_NO")); 
			entry.put("CHANN_NAME", chann.get("CHANN_NAME")); 
			String userCredit = LogicUtil.getU9CurrCredit(CHANN_NO);
			entry.put("USER_CREDIT", userCredit); 
			jsonResult = new Gson().toJson(new Result(true, entry, ""));
        } catch (Exception e) {
            jsonResult = jsonResult(false, "");
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
	    	
	        
	        pvgMap.put("PVG_OPPOSEAUDIT",QXUtil.checkPvg(userBean, PVG_OPPOSEAUDIT));
	        pvgMap.put("PVG_COMMIT",QXUtil.checkPvg(userBean, PVG_COMMIT));
	        pvgMap.put("PVG_CANCLE",QXUtil.checkPvg(userBean, PVG_CANCLE));
	    	pvgMap.put("PVG_ROW_CLOSE",QXUtil.checkPvg(userBean, PVG_ROW_CLOSE));
	    	pvgMap.put("PVG_ROW_MUST_CLOSE",QXUtil.checkPvg(userBean, PVG_ROW_MUST_CLOSE));
	    	
			pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
			pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
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
	                saleordermgeService.txChildSave(modelList);
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
	  * 失效！！！！！！！！！！！！！！！1
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  */
	    @SuppressWarnings("unchecked")
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
			String PROVS=request.getParameter("PROV");
			if(!StringUtil.isEmpty(PROVS)){
				sb.append(" and u.ORDER_CHANN_ID in ( select CHANN_ID from BASE_CHANN where 1=1 ");
				String sql = StringUtil.creCon("PROV", PROVS, ",");
				sb.append(sql).append(")");
			}
			params.put("QXJBCON", sb.toString());
		    
		    //渠道分管sql  by zzb 2014-6-17
			String CHANNS_CHARG = userBean.getCHANNS_CHARG();
			params.put("CHANNS_CHARG_EXP", CHANNS_CHARG);
			
			List list = saleordermgeService.qrySaleOrderExp(params);
			
	        //excel数据上列显示的列明
	        String tmpContentCn="销售订单编号,单据类型,订货方编号,发货方名称,是否使用返利,收货方编号,收货方名称,联系人,电话,生产基地名称," +
				        		"收货地址编号,明细行号,货品编号,货品名称,规格型号,库存分类名称,品牌,标准单位,交期,是否是备货,是否非标,特殊规格说明,是否赠品标记," +
				        		"供货价,折扣率,折后单价,订货数量,实发数量,取消数量,行状态,订货金额,单位体积,明细备注,区域名称,制单人,组织名称," +
				        		"收货地址,区域经理名称,区域经理电话,区域服务中心编号,区域服务中心名称,主表备注";
	        //
	        String tmpContent="SALE_ORDER_NO,BILL_TYPE,ORDER_CHANN_NO,ORDER_CHANN_NAME,IS_USE_REBATE,RECV_CHANN_NO,RECV_CHANN_NAME,PERSON_CON,TEL,SHIP_POINT_NAME," +
				        	  "RECV_ADDR_NO,ROW_NO,PRD_NO,PRD_NAME,PRD_SPEC,PAR_PRD_NAME,BRAND,STD_UNIT,ADVC_SEND_DATE,IS_BACKUP_FLAG,IS_NO_STAND_FLAG,SPCL_SPEC_REMARK,IS_FREE_FLAG," +
				        	  "PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,SENDED_NUM,CANCEL_NUM,STATE,ORDER_AMOUNT,VOLUME,REMARKDTL,AREA_NAME,CRE_NAME,LEDGER_NAME," +
				        	  "RECV_ADDR,AREA_MANAGE_NAME,AREA_MANAGE_TEL,AREA_SER_NO,AREA_SER_NAME,REMARK";
//	        String colType="string,string,string,string,string,string,string,string,string,string," +
//	        			   "string,string,string,string,string,string,string,string,string,string,string," +
//	        			   "number,number,number,number,number,number,string,string,string,string," +
//	        			   "string,string,string,string,string,string,string";
	        try {
				ExcelUtil.doExport(response, list, "销售订单明细", tmpContent, tmpContentCn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    
	 // 导出未出货订单
		@SuppressWarnings("unchecked")
		public void noSendExport(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			Map<String, String> params = new HashMap<String, String>();

			ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
		    ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
		    ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params);
		    
		    ParamUtil.putStr2MaptoUpperCase(request, "GOODS_ORDER_NO", params);
		    ParamUtil.putStr2Map(request, "STATE", params);
		    ParamUtil.putStr2Map(request, "AREA_NO", params);
		    ParamUtil.putStr2Map(request, "AREA_NAME", params);
		    ParamUtil.putStr2Map(request, "ORDER_DATE_BEGIN", params);
		    ParamUtil.putStr2Map(request, "ORDER_DATE_END", params);
		    ParamUtil.putStr2Map(request, "BILL_TYPE", params);
		    ParamUtil.putStr2Map(request, "CRE_NAME", params);
		    ParamUtil.putStr2Map(request, "STATE", params);
		    ParamUtil.putStr2Map(request, "PRD_NO", params);
		    ParamUtil.putStr2Map(request, "PRD_NAME", params);
		    ParamUtil.putStr2Map(request, "PRD_SPEC", params);
		    ParamUtil.putStr2Map(request, "ROWSTATE", params);
		    ParamUtil.putStr2Map(request, "IS_CANCELED_FLAG", params);
	       //只查询0的记录。1为删除。0为正常
	        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	        String search = ParamUtil.get(request,"search");
			String module = ParamUtil.get(request,"module");
			params.put("XTYHID",userBean.getXTYHID());
			
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
			ParamUtil.putStr2Map(request, "PROV", params);
			params.put("QXJBCON", sb.toString());
			String ROWSTATE=request.getParameter("ROWSTATE");
			StringBuffer sql=new StringBuffer();
			 if("正常".equals(ROWSTATE)){
		    	sql.append("  NVL(c.ORDER_NUM, 0) > NVL(c.SENDED_NUM, 0) ");
		    }else if("已发货".equals(ROWSTATE)){
		    	sql.append("  NVL(c.ORDER_NUM, 0) = NVL(c.SENDED_NUM, 0) ");
		    }else if("关闭".equals(ROWSTATE)){
		    	sql.append("  c.STATE='关闭' ");
		    }else{
		    	sql.append("  1=1 ");
		    }
			 params.put("STATESQLDTL", sql.toString());
		    //渠道分管sql  by zzb 2014-6-17
			String CHANNS_CHARG = userBean.getCHANNS_CHARG();
			params.put("CHANNS_CHARG", CHANNS_CHARG);
			List list = saleordermgeService.noSendExport(params);
			// excel数据上列显示的列明
			String tmpContentCn ="销售订单号,U9订单编号,U9订单行编号,订货日期,交货日期,订货方编号,订货方名称,收货方编号,收货方名称,收货地址编号," +
								 "收货地址,生产基地,货品编号,货品名称,规格型号,特殊规格说明,抵库,备注," +
								 "订货数量,未发数量,单价,计划发运金额,单位体积,汇总行总体积,货品分类,状态,明细备注";
			String tmpContent = "SALE_ORDER_NO,U9_SALE_ORDER_NO,U9_SALE_ORDER_DTL_NO,ORDER_DATE,ADVC_SEND_DATE,ORDER_CHANN_NO,ORDER_CHANN_NAME,RECV_CHANN_NO,RECV_CHANN_NAME,RECV_ADDR_NO," +
								"RECV_ADDR,SHIP_POINT_NAME,PRD_NO,PRD_NAME,PRD_SPEC,SPCL_DTL_REMARK,IS_BACKUP_FLAG,PRIMARYREMARK," +
								"ORDER_NUM,NO_SEND_NUM,DECT_PRICE,ALLPRICE,VOLUME,ALLVOLDTL,PAR_PRD_NAME,CHILD_STATE,DTLREMARK";
			String colType= "string,string,string,string,string,string,string,string,string,string," +
						   "string,string,string,string,string,string,string,string," +
						   "number,number,number,number,number,number,string,string,string";
			try {
				ExcelUtil
						.doExport(response, list, "销售订单明细", tmpContent, tmpContentCn,colType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    
	    
		public SaleordermgeService getSaleordermgeService() {
			return saleordermgeService;
		}
		public void setSaleordermgeService(SaleordermgeService saleordermgeService) {
			this.saleordermgeService = saleordermgeService;
		}
	 
}