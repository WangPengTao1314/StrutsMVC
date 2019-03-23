package com.hoperun.base.table.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.base.table.model.TableModel;
import com.hoperun.base.table.service.TableService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.sys.model.UserBean;

public class TableAction  extends BaseAction {
    
	private   TableService   tableService;
	
	private static String PVG_BWS="XT0013501";
    private static String AUD_BUSS_STATE="";
   
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
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
	
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
		 if(Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))){
	    		throw new ServiceException("对不起，您没有权限 !");
	    }
		//设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
	    String module = ParamUtil.get(request,"module");
		if(module.equals("sh")){
			request.setAttribute("module",module);
			return mapping.findForward("framesT");
		}
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
		 
        UserBean userBean = ParamUtil.getUserBean(request);
		
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String> paramsT = new HashMap<String,String>();
        
	    ParamUtil.putStr2Map(request, "TABLE_NAME_ZH", params);
		ParamUtil.putStr2Map(request, "TABLE_NAME_EN", params);
	    
		String search = ParamUtil.get(request,"search");
	    String module = ParamUtil.get(request,"module");
	    String STATE = ParamUtil.get(request,"STATE");
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
 
	    //params.put("QXJBCON",qx.toString());
	    
	    IListPage page = null;
	    request.setAttribute("module",module);
	    //字段排序
  		//ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
	    int pageNo = ParamUtil.getInt(request, "pageNo", 1);
	    //ParamUtil.putStr2Map(request, "pageSize", params);
	    params.put("pageSize", "20");
	    page = tableService.pageQuery(params, pageNo);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("module", module);
        request.setAttribute("params", params);
        request.setAttribute("paramsT", paramsT);
        request.setAttribute("search", search);
        request.setAttribute("page", page);  
        return mapping.findForward("list");
	}
	
	/***
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		 
        UserBean userBean = ParamUtil.getUserBean(request);
		
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String> paramsT = new HashMap<String,String>();
        
	    ParamUtil.putStr2Map(request, "TABLE_NAME_ZH", params);
		ParamUtil.putStr2Map(request, "TABLE_NAME_EN", params);
	    
		String search = ParamUtil.get(request,"search");
	    String module = ParamUtil.get(request,"module");
	    String STATE = ParamUtil.get(request,"STATE");
	    String TABLE_NAME_EN = ParamUtil.get(request, "TABLE_NAME_EN");
	    Map map = tableService.loadByTableNameEn(TABLE_NAME_EN);
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
	    IListPage page = null;
	    request.setAttribute("module",module);
	    int pageNo = 1;
	    params.put("pageSize", "30");
	    List<TableModel> result = tableService.load(TABLE_NAME_EN);
		request.setAttribute("result", result);  
		request.setAttribute("TABLE_NAME_EN", TABLE_NAME_EN);
		request.setAttribute("map", map);
        return mapping.findForward("viewT");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toTableDtl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if (Consts.FUN_CHEK_PVG
				&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
						.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
    	String TABLE_COL_EN = ParamUtil.get(request, "TABLE_COL_EN");
		if (StringUtils.isNotEmpty(TABLE_COL_EN)) {
			List<TableModel> entry   = tableService.loadByColName(TABLE_COL_EN);
			request.setAttribute("result", entry);
		}
    	request.setAttribute("TABLE_COL_EN", TABLE_COL_EN);
        return mapping.findForward("viewTt");
    }

	public TableService getTableService() {
		return tableService;
	}

	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}
	
	public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if (Consts.FUN_CHEK_PVG
				&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
						.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
    	String TABLE_NAME_EN = ParamUtil.get(request, "TABLE_NAME_EN");
    	request.setAttribute("TABLE_NAME_EN", TABLE_NAME_EN);
        return mapping.findForward("viewT");
    }
	
	public ActionForward toQueryTableName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if (Consts.FUN_CHEK_PVG
				&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
						.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
    	String TABLE_NAME_EN = ParamUtil.get(request, "TABLE_NAME_EN");
		if (StringUtils.isNotEmpty(TABLE_NAME_EN)) {
			List<TableModel> entry   = tableService.load(TABLE_NAME_EN);
			request.setAttribute("result", entry);
		}
    	request.setAttribute("TABLE_NAME_EN", TABLE_NAME_EN);
        return mapping.findForward("toQueryTab");
    }
    
	
	 private Map<String,String> setPvg(UserBean userBean)
    {
    	Map<String,String>pvgMap=new HashMap<String,String>();
    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
        pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE) );
		pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
 	    pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
 	    pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
 	    pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
    	return  pvgMap;
    }
}
