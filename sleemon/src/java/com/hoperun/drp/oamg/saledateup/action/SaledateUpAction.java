package com.hoperun.drp.oamg.saledateup.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.saledateup.model.SaledateUpChildModel;
import com.hoperun.drp.oamg.saledateup.model.SaledateUpModel;
import com.hoperun.drp.oamg.saledateup.service.SaledateUpService;
import com.hoperun.sys.model.UserBean;

/**
 * 渠道管理-销售数据上报
 * 
 * @author zhang_zhongbin
 * 
 */
public class SaledateUpAction extends BaseAction {

	/** 日志 **/
	private Logger logger = Logger.getLogger(SaledateUpAction.class);

	// 增删改查
	private static String PVG_BWS = "BS0022701";
	private static String PVG_EDIT = "BS0022702";
	private static String PVG_DELETE = "BS0022703";
	private static String PVG_IMPORT = "BS0022706";//导入导出

	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "BS0022704";
	// 维护页面流程跟踪
	private static String PVG_TRACE = "BS0022705";

	// 审核模块
	private static String PVG_BWS_AUDIT = "BS0021701";
	private static String PVG_AUDIT_AUDIT = "BS0021702";
	private static String PVG_TRACE_AUDIT = "BS0021703";
	private static String PVG_RETURN_AUDIT= "BS0021704";

	// 审批流参数
	private static String AUD_TAB_NAME = "ERP_SALE_DATE_UP";
	private static String AUD_TAB_KEY = "SALE_DATE_UP_ID";
	private static String AUD_BUSS_STATE = "STATE";
	private static String AUD_BUSS_TYPE = "DRP_SALE_DATE_UP_AUDIT";
	private static String AUD_FLOW_INS = "com.hoperun.drp.oamg.saledateup.service.impl.SaledateUpFlowInterfaceImpl";

	private SaledateUpService saledateUpService;

	/**
	 * * to 框架页面
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
						.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("frames");
	}

	/**
	 * * 查询
	 * 
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
						.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();

		// 装修报销申请单编号
		ParamUtil.putStr2Map(request, "SALE_DATE_UP_NO", params);
//		ParamUtil.putStr2Map(request, "CHANN_NO", params);
//		ParamUtil.putStr2Map(request, "CHANN_NAME", params);
//		ParamUtil.putStr2Map(request, "WAREA_NO", params);
//		ParamUtil.putStr2Map(request, "WAREA_NAME", params);
//		ParamUtil.putStr2Map(request, "AREA_NO", params);
//		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "YEAR", params);
		ParamUtil.putStr2Map(request, "MONTH_BEG", params);
		ParamUtil.putStr2Map(request, "MONTH_END", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NAME", params);
		
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");

		String channChrg = userBean.getCHANNS_CHARG();
		params.put("channChrg", channChrg);
		
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String WAREA_NO = ParamUtil.get(request, "WAREA_NO");
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME");
		String AREA_NO = ParamUtil.get(request, "AREA_NO");
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME");
		
		StringBuffer sb = new StringBuffer(" 1=1 ");
		if(!StringUtil.isEmpty(CHANN_NO)){
			String CHANN_NO_SQL = StringUtil.creCon("u.CHANN_NO", CHANN_NO, ",");
			sb.append(CHANN_NO_SQL);
			params.put("CHANN_NO", CHANN_NO);
		}
		if(!StringUtil.isEmpty(CHANN_NAME)){
			String CHANN_NAME_SQL = StringUtil.creCon("u.CHANN_NAME", CHANN_NAME, ",");
			sb.append(CHANN_NAME_SQL);
			params.put("CHANN_NAME", CHANN_NAME);
		}
		if(!StringUtil.isEmpty(WAREA_NO)){
			String WAREA_NO_SQL = StringUtil.creCon("u.WAREA_NO", WAREA_NO, ",");
			sb.append(WAREA_NO_SQL);
			params.put("WAREA_NO", WAREA_NO);
		}
		if(!StringUtil.isEmpty(WAREA_NAME)){
			String WAREA_NAME_SQL = StringUtil.creCon("u.WAREA_NAME", WAREA_NAME, ",");
			sb.append(WAREA_NAME_SQL);
			params.put("WAREA_NAME", WAREA_NAME);
		}
		if(!StringUtil.isEmpty(AREA_NO)){
			String AREA_NO_SQL = StringUtil.creCon("u.AREA_NO", AREA_NO, ",");
			sb.append(AREA_NO_SQL);
			params.put("AREA_NO", AREA_NO);
		}
		if(!StringUtil.isEmpty(AREA_NAME)){
			String AREA_NAME_SQL = StringUtil.creCon("u.AREA_NAME", AREA_NAME, ",");
			sb.append(AREA_NAME_SQL);
			params.put("AREA_NAME", AREA_NAME);
		}
		
		// 权限级别和审批流的封装和状态过滤
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search,
				module, PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		sb.append(" and ");
		sb.append(StringUtil.getStrQX("u", strQx.toString()));
		params.put("QXJBCON", sb.toString());
		
		String ZTXXID = userBean.getLoginZTXXID();
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		if("1".equals(IS_DRP_LEDGER)){
			params.put("ZTXXID", ZTXXID);
			Map<String,String> chann = saledateUpService.loadChannById(userBean.getCHANN_ID());
			request.setAttribute("AREA_ID", chann.get("AREA_ID"));
			request.setAttribute("WAREA_ID", chann.get("AREA_ID_P"));
		}
		
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = saledateUpService.pageQuery(params, pageNo);
		
		request.setAttribute("IS_DRP_LEDGER", IS_DRP_LEDGER);
		request.setAttribute("ZTXXID", ZTXXID);
		request.setAttribute("params", params);
		request.setAttribute("module", module);
		request.setAttribute("page", page);
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("list");
	}

	/**
	 * 新增.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)){
			throw new ServiceException("对不起，您没有权限 !");
		}
		String SALE_DATE_UP_ID = ParamUtil.get(request, "SALE_DATE_UP_ID");
		Map<String, Object> entry = null;
		if (StringUtils.isNotEmpty(SALE_DATE_UP_ID)) {
			entry = saledateUpService.loadById(SALE_DATE_UP_ID);
		}  
		request.setAttribute("rst", entry);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		return mapping.findForward("toedit");
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
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String SALE_DATE_UP_ID =ParamUtil.get(request, "SALE_DATE_UP_ID");
        if(!StringUtil.isEmpty(SALE_DATE_UP_ID)){
        	 List <SaledateUpChildModel> result = saledateUpService.childQuery(SALE_DATE_UP_ID);
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
		String SALE_DATE_UP_ID = ParamUtil.get(request, "selRowId");
		Map<String,Object> entry = new HashMap<String,Object>();
        if(!StringUtil.isEmpty(SALE_DATE_UP_ID)){
        	entry = saledateUpService.loadById(SALE_DATE_UP_ID);
        }else{
        	Map<String,String> chann = saledateUpService.loadChannById(userBean.getCHANN_ID());
        	if(null != chann){
        		entry.put("AREA_ID", chann.get("AREA_ID"));
            	entry.put("AREA_NO", chann.get("AREA_NO"));
            	entry.put("AREA_NAME", chann.get("AREA_NAME"));
            	entry.put("WAREA_ID", chann.get("AREA_ID_P"));
            	entry.put("WAREA_NO", chann.get("AREA_NO_P"));
            	entry.put("WAREA_NAME", chann.get("AREA_NAME_P"));
            	
            	entry.put("CHANN_ID", userBean.getCHANN_ID());
            	entry.put("CHANN_NO", userBean.getCHANN_NO());
            	entry.put("CHANN_NAME", userBean.getCHANN_NAME());
        	}
        }
	 
		request.setAttribute("rst", entry);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("pvg",setPvg(userBean));
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String SALE_DATE_UP_ID = ParamUtil.get(request, "SALE_DATE_UP_ID");
        if(!StringUtil.isEmpty(SALE_DATE_UP_ID)) {
        	 List <SaledateUpChildModel> result = saledateUpService.childQuery(SALE_DATE_UP_ID);
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
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT)){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//多个Id批量查询，用逗号隔开
        String SALE_DATE_UP_DTL_IDS = request.getParameter("SALE_DATE_UP_DTL_IDS");
        //没有零星领料Id可以直接跳转。
        if (!StringUtil.isEmpty(SALE_DATE_UP_DTL_IDS)) {
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("SALE_DATE_UP_DTL_IDS",SALE_DATE_UP_DTL_IDS);
          List <SaledateUpChildModel> list = saledateUpService.loadChilds(params);
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
            String SALE_DATE_UP_ID = ParamUtil.get(request, "SALE_DATE_UP_ID");
            String parentJsonData = ParamUtil.get(request, "parentJsonData");
            SaledateUpModel model = new Gson().fromJson(parentJsonData, new TypeToken <SaledateUpModel>() {}.getType());
            String jsonDate = ParamUtil.get(request, "childJsonData");
            List <SaledateUpChildModel> chldModelList = null;
            if (!StringUtil.isEmpty(jsonDate)) {
            	chldModelList = new Gson().fromJson(jsonDate, new TypeToken <List <SaledateUpChildModel>>() {
                }.getType());
            }
            saledateUpService.txEdit(SALE_DATE_UP_ID, model, chldModelList, userBean);
            Map<String,String> p = new HashMap<String,String>();
            p.put("SALE_DATE_UP_ID", SALE_DATE_UP_ID);
            jsonResult = new Gson().toJson(new Result(true, p, "保存成功"));
            
        }catch(ServiceException e){ 
        	jsonResult = jsonResult(false, e.getMessage());
        }catch (Exception e) {
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
            String SALE_DATE_UP_ID = request.getParameter("SALE_DATE_UP_ID");
            String jsonDate = request.getParameter("childJsonData");
            if (!StringUtil.isEmpty(jsonDate)) {
                List <SaledateUpChildModel> modelList = new Gson().fromJson(jsonDate, new TypeToken <List <SaledateUpChildModel>>() {
                }.getType());
                 
                saledateUpService.txChildEdit(SALE_DATE_UP_ID, modelList);
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
	 * 详情页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
						.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String SALE_DATE_UP_ID = ParamUtil.get(request, "SALE_DATE_UP_ID");
		if (StringUtils.isNotEmpty(SALE_DATE_UP_ID)) {
			Map<String, Object> entry = saledateUpService.loadById(SALE_DATE_UP_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}

	/**
	 * 删除.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String SALE_DATE_UP_ID = ParamUtil.get(request, "SALE_DATE_UP_ID");

		if (StringUtils.isNotEmpty(SALE_DATE_UP_ID)) {
			try {
				saledateUpService.txDelete(SALE_DATE_UP_ID, userBean);
				jsonResult = jsonResult(true, "删除成功");
			} catch (Exception e) {
				jsonResult = jsonResult(false, "删除失败");
			}
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
   	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE)){
   		throw new ServiceException("对不起，您没有权限 !");
   	}
       PrintWriter writer = getWriter(response);
       String jsonResult = jsonResult();
       try {
           String SALE_DATE_UP_DTL_IDS = request.getParameter("SALE_DATE_UP_DTL_IDS");
           //批量删除，多个Id之间用逗号隔开
           saledateUpService.txBatch4DeleteChild(SALE_DATE_UP_DTL_IDS);
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
	 * * 提交时
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public void toCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String errorId = "";
		try {
			String RNVTN_REIT_REQ_ID = ParamUtil.get(request, "CHANN_RNVTN_ID");

		} catch (Exception e) {
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
    public void toReAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		 UserBean userBean = ParamUtil.getUserBean(request);
	     PrintWriter writer = getWriter(response);
	     String jsonResult = jsonResult();
		 String SALE_DATE_UP_ID    = ParamUtil.get(request, "SALE_DATE_UP_ID");
		 if(!SALE_DATE_UP_ID.isEmpty()) {
			 saledateUpService.upSaledataup(SALE_DATE_UP_ID,userBean);
		 }
	     if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	}
    
 // 导出
	public void export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG	&& !QXUtil.checkMKQX(userBean, PVG_IMPORT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		String MONTH    = ParamUtil.get(request, "MONTH");
		String YEAR    = ParamUtil.get(request, "YEAR");
		Map<String,String> params=new HashMap<String, String>();
		params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
    	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	params.put("CHANN_IDS", CHANNS_CHARG);
    	params.put("MONTH", MONTH);
    	params.put("YEAR", YEAR);
		List list = saledateUpService.getChargTerminal(params);
		// excel数据上列显示的列明
		String tmpContentCn ="销售数据上报编号,战区编号,战区名称,区域编号,区域名称,渠道编号,渠道名称,终端编号,终端名称,年份,月份,床垫金额(数值),软床金额(数值),其他金额(数值)";
		String tmpContent = "SALE_DATE_UP_NO,AREA_NO_P,AREA_NAME_P,AREA_NO,AREA_NAME,CHANN_NO_P,CHANN_NAME_P,TERM_NO,TERM_NAME,YEAR,MONTH,MATTRESS_AMOUNT,HAMMOCK_AMOUNT,OTHER_AMOUNT";
		String colType= "string,string,string,string,string,string,string,string,string,string,string,number,number,number" ;
		try {
			doExport(response, list, "销售数据上报维护", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	//Excel文件导出成文件流
	//dataList 需要导出的数据列表
	//execlName 导出后默认的文件名
	//tmpContent:数据库字段名，多字段以逗号分割
	//tmpContentCnexcel:文件名字段名，多字段以逗号分割
	/**
	 * Do export.
	 * 
	 * @param response the response
	 * @param dataList the data list
	 * @param execlName the execl name
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * 
	 * @throws Exception the exception
	 */
	public  void  doExport(HttpServletResponse response,List dataList,String execlName,String tmpContent,String tmpContentCn,String colType) throws Exception{
		//生成excel
		HSSFWorkbook workbook =new HSSFWorkbook();
		workbook = printExcel(tmpContent,tmpContentCn,dataList,colType);
        //导出excel
        writeExecl(response,workbook,execlName);
	}
	/**
	 * Prints the excel.
	 * 
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * @param dataList the data list
	 * 
	 * @return the hSSF workbook
	 */
	private  HSSFWorkbook printExcel(String tmpContent,String tmpContentCn,List dataList,String colType){

	     HSSFWorkbook workbook = null;
	     String[] titles_CN = tmpContentCn.split(",");
	     String[] titles_EN = tmpContent.split(",");
	     String[] colTypeArr = colType.split(",");
	     try{
	          //创建工作簿实例 
	           workbook = new HSSFWorkbook();
	          //创建工作表实例 
	         HSSFSheet sheet = workbook.createSheet("Sheet1"); 
	          //设置列宽 
	          setSheetColumnWidth(titles_CN,sheet);
	        //获取样式 
	          HSSFCellStyle style = createTitleStyle(workbook); 
	          if(dataList != null){
	               //创建第一行标题 
	                HSSFRow row = sheet.createRow((short)0);// 建立新行
	                row.setHeight((short) 0);
	                createCell(row, 0, style, HSSFCell.CELL_TYPE_STRING, "hoperun");
	                HSSFRow rows = sheet.createRow((short)1);// 建立新行
	                for(int i=0;i<titles_CN.length;i++){
	                createCell(rows, i, null, HSSFCell.CELL_TYPE_STRING, 
	                       titles_CN[i]);
	                }
	                //给excel填充数据 
	               for(int i=1;i<=dataList.size();i++){ 
	                        // 将dataList里面的数据取出来 
	                        Map<String,String> map = (HashMap)(dataList.get(i-1));
	                         HSSFRow row1 = sheet.createRow((short) (i + 1));// 建立新行
	                        boolean isOverTime = false;
	                         for(int j=0;j<titles_EN.length;j++){
	                        	 if("number".equals(colTypeArr[j]))
		  	                	   {
		  	                		   createCell(row1, j, style, HSSFCell.CELL_TYPE_NUMERIC, map.get(titles_EN[j])); 
		  	                	   }else
		  	                	   {
		                            createCell(row1, j, style, HSSFCell.CELL_TYPE_STRING, map.get(titles_EN[j]));
		  	                	   }
	                                  }               
	                      }
	       }else{
	                createCell(sheet.createRow(0), 0, style,HSSFCell.CELL_TYPE_STRING, "查无资料");
	       }
	  }catch(Exception e){
	              e.printStackTrace();
	  }
	 return workbook;
	}
	/**
	 * Write execl.
	 * 
	 * @param response the response
	 * @param workbook the workbook
	 * @param execlName the execl name
	 */
	public  void writeExecl(HttpServletResponse response,HSSFWorkbook workbook, String execlName) {
		if (null == workbook)
		{
			workbook = new HSSFWorkbook();
		}
		
		if (0 == workbook.getNumberOfSheets()) {
			HSSFSheet sheet = workbook.createSheet("无数据");
			sheet.createRow(3).createCell(3).setCellValue("未查询到数据!");
		}
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel;charset=UTF-8");
		ServletOutputStream outputStream = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + "_" + DateFormatUtils.format(new Date(), "MM-dd") + ".xls");
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}
	}
	//设置excel的title样式  
	/**
	 * Creates the title style.
	 * 
	 * @param wb the wb
	 * 
	 * @return the hSSF cell style
	 */
	private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) { 
	  HSSFFont boldFont = wb.createFont(); 
	  boldFont.setFontHeight((short) 200); 
	  HSSFCellStyle style = wb.createCellStyle(); 
	  style.setFont(boldFont); 
	  style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
	  //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	  //style.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
	  return style;  
	}
	//设置列宽
	/**
	 * Sets the sheet column width.
	 * 
	 * @param titles_CN the titles_ cn
	 * @param sheet the sheet
	 */
	private  void setSheetColumnWidth(String[] titles_CN,HSSFSheet sheet){ 
	   // 根据你数据里面的记录有多少列，就设置多少列
	  for(int i=0;i<titles_CN.length;i++){
		  sheet.setColumnWidth((short)i, (short) 6000);
	          
	  }
	}
	//创建Excel单元格  
	/**
	 * Creates the cell.
	 * 
	 * @param row the row
	 * @param column the column
	 * @param style the style
	 * @param cellType the cell type
	 * @param value the value
	 */
	private void createCell(HSSFRow row, int column, HSSFCellStyle style,int cellType,Object value) { 
	  HSSFCell cell = row.createCell( column);  
	  if (style != null) { 
	       cell.setCellStyle(style); 
	  }   
	  String res = (value==null?"":value).toString();
	  switch(cellType){ 
	       case HSSFCell.CELL_TYPE_BLANK: {} break; 
	       case HSSFCell.CELL_TYPE_STRING: {cell.setCellValue(res+"");
	       } break; 
	       case HSSFCell.CELL_TYPE_NUMERIC: {
	    	   cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
	    	   if(null==value){
	    		   cell.setCellValue("");
	    	   }else{
	    		   cell.setCellValue(Double.parseDouble(value.toString()));
	    	   }
	       }
	       break; 
	       default: break; 
		 }  
		
		} 
	
	/**
	  * 导入
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  */
	 public void toImportData(ActionMapping mapping, ActionForm form, 
			 HttpServletRequest request, HttpServletResponse response) {
		 PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        UserBean userBean = ParamUtil.getUserBean(request);
	        if (Consts.FUN_CHEK_PVG	&& !QXUtil.checkMKQX(userBean, PVG_IMPORT)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
	        try {
	        	String serverDir = Properties.getString("UPLOADFILE_DIR");
	        	String fileName = "sample"+ParamUtil.utf8Decoder(request, "fileName");
	        	String secPath = Properties.getString("SAMPLE_DIR");
	        	String path = serverDir+ File.separatorChar + secPath+ File.separatorChar + fileName;
	        	List<Map<String,String>> alist = new ArrayList<Map<String,String>>();
	        	Map<String,String> map = new HashMap<String,String>();
        	    map.put("SALE_DATE_UP_NO", "0");//
        		map.put("AREA_NO", "3");//区域编号
            	map.put("AREA_NAME", "4");//区域名称
            	map.put("CHANN_NO_P", "5");//渠道编号
            	map.put("CHANN_NAME_P", "6");//渠道名称
            	map.put("TERM_NO", "7");//终端编号
            	map.put("TERM_NAME", "8");//终端名称
            	map.put("YEAR", "9");//年份
            	map.put("MONTH", "10");//月份
            	map.put("MATTRESS_AMOUNT", "11");//床垫金额
            	map.put("HAMMOCK_AMOUNT", "12");//软床金额
            	map.put("OTHER_AMOUNT", "13");//其他金额
	        	String[] a = new String[]{"1"};
	        	alist.add(map);
	            List list = ExcelUtil.readExcelbyModel(fileName, path, 1, alist, a);
	            saledateUpService.txImportExcel(list, userBean);
	            jsonResult = jsonResult(true, "上传成功");//
	        } catch (ServiceException e) {
	            jsonResult = jsonResult(false, e.getMessage());
	        } catch (Exception e) {
	            logger.error(e);
	            e.printStackTrace();
	            jsonResult = jsonResult(false, "Execl解析失败." + e.getMessage());
	        }

	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	 }
	 
	public SaledateUpService getSaledateUpService() {
		return saledateUpService;
	}

	public void setSaledateUpService(SaledateUpService saledateUpService) {
		this.saledateUpService = saledateUpService;
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
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil
				.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil
				.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,
				PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
        pvgMap.put("PVG_RETURN_AUDIT", QXUtil.checkPvg(userBean, PVG_RETURN_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("PVG_IMPORT", QXUtil.checkPvg(userBean, PVG_IMPORT));
		
		return pvgMap;
	}
}
