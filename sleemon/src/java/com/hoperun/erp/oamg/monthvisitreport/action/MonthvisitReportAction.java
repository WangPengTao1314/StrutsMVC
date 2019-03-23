package com.hoperun.erp.oamg.monthvisitreport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.oamg.monthvisitreport.service.MonthvisitReqService;
import com.hoperun.sys.model.UserBean;

public class MonthvisitReportAction  extends BaseAction {
	
	private static String PVG_BWS="BS0050901";
	
	private   MonthvisitReqService   monthvisitreqService;

	public MonthvisitReqService getMonthvisitreqService() {
		return monthvisitreqService;
	}

	public void setMonthvisitreqService(MonthvisitReqService monthvisitreqService) {
		this.monthvisitreqService = monthvisitreqService;
	}

	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("frames");
	}
	
	public ActionForward toFADList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	    
		UserBean userBean = ParamUtil.getUserBean(request);
		// 设置跳转时需要的一些公用属性
		String rptConFile = request.getParameter("rptConFile");
		request.setAttribute("rptConFile", rptConFile);
		String sjcd = request.getParameter("sjcd");
		request.setAttribute("sjcd", sjcd);
		String backpath = request.getParameter("backPath");
		request.setAttribute("backPath", backpath);
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("FADList");
	}
	
	/**
	 * 月度拜访工作计划达成率报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	public ActionForward toConDition(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 设置跳转时需要的一些公用属性
		UserBean userBean =  ParamUtil.getUserBean(request);
		String rptConFile = request.getParameter("rptConFile");
		request.setAttribute("rptConFile", rptConFile);
		String sjcd = request.getParameter("sjcd");
		request.setAttribute("sjcd", sjcd);
		String backPath = request.getParameter("backPath");
		request.setAttribute("backPath", backPath);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("LEDGER_NAME", userBean.getLoginZTMC());
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		request.setAttribute("IS_DRP_LEDGER", IS_DRP_LEDGER);
		request.setAttribute("CHANNS_CHARG",userBean.getCHANNS_CHARG());
		request.setAttribute("TERM_CHARGE", userBean.getTERM_CHARGE());
		String IS_NO_DELIVER_PLAN_FLAG=Properties.getString("IS_NO_DELIVER_PLAN_FLAG");
		request.setAttribute("IS_NO_DELIVER_PLAN_FLAG", IS_NO_DELIVER_PLAN_FLAG);
		String CHANN_ID=userBean.getCHANN_ID();
		//Map channInfo=monthvisitreqService.queryMonthVisitreq();
		//request.setAttribute("channInfo", channInfo);
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		return mapping.findForward("list");
	}

	
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer conSql = new StringBuffer();
		 
		String WAREA_NO   = ParamUtil.get(request, "WAREA_NO"); 
		String RYBH       = ParamUtil.get(request, "RYBH"); 
		String PLAN_YEAR  = ParamUtil.get(request, "PLAN_YEAR"); 
		String PLAN_MONTH = ParamUtil.get(request, "PLAN_MONTH"); 
		 
		if(!StringUtil.isEmpty(WAREA_NO)){
			 String tempSql = StringUtil.creCon("t.WAREA_NO", WAREA_NO, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(RYBH)){
			String tempSql = StringUtil.creCon("t.RYBH", RYBH, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(PLAN_YEAR)){
			String tempSql = StringUtil.creCon("t.PLAN_YEAR", PLAN_YEAR, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(PLAN_MONTH)){
			String tempSql = StringUtil.creCon("t.PLAN_MONTH", PLAN_MONTH, ",");
			conSql.append(tempSql);
		}
		 
		StringBuffer head_sql  = new StringBuffer("select ");
		StringBuffer group_by  = new StringBuffer(" group by  ");
		StringBuffer group_sql = new StringBuffer("");
		String count_sql = "a.AREA_ID,a.AREA_ID_P,a.AREA_NO_P,a.AREA_NAME_P,c.RYXXID,c.RYBH,c.XM RYMC,p.PLAN_YEAR,p.PLAN_MONTH,p.Plan_Visit_Num_Total from base_area a  left join base_chann b on a.area_id=b.area_id left join t_sys_ryxx c on b.chann_id=c.jgxxid left join ERP_MONTH_VISIT_PLAN p on p.ryxxid = c.ryxxid";
  
		//润乾报表列sql
		StringBuffer sqlColumn = new StringBuffer("");
	    //group by 字段
        //月份
        String MONTH_SHOW = ParamUtil.get(request, "PLAN_MONTH"); 
        boolean MONTH_FLAG = true;
        if(!StringUtil.isEmpty(MONTH_SHOW)){
        	head_sql.append("t.MONTH,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("t.MONTH");
        	MONTH_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";MONTH_SQL=ds1.MONTH");
        	}else{
        		sqlColumn.append(";MONTH_SQL=ds1.select(MONTH)");
        	}
        }
        //年份
        String YEAR_SHOW = ParamUtil.get(request, "PLAN_YEAR"); 
        boolean YEAR_FLAG = true;
        if(!StringUtil.isEmpty(YEAR_SHOW)){
        	head_sql.append("t.YEAR,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("t.YEAR");
        	YEAR_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";YEAR_SQL=ds1.YEAR");
        	}else{
        		sqlColumn.append(";YEAR_SQL=ds1.select(YEAR)");
        	}
        }
        
        
		/********** 查询条件拼接 start **********/
//		String sql = this.reportService.getErpSaleReportSql(conSql.toString());
        String sql = head_sql.toString()+ count_sql.toString()+ conSql.toString();
        if(!StringUtil.isEmpty(group_sql.toString())){
        	sql = sql + group_by.toString() + group_sql.toString();
        }
        /*
        String params = "rptSql=" + sql
        +";WAREA_FLAG="+WAREA_FLAG
        +";PROV_FLAG="+PROV_FLAG
        +";CITY_FLAG="+CITY_FLAG
        +";AREA_FLAG="+AREA_FLAG
        +";CHANN_FLAG="+CHANN_FLAG
        +";BRAND_FLAG="+BRAND_FLAG
        +";MONTH_FLAG="+MONTH_FLAG
        +";YEAR_FLAG="+YEAR_FLAG
        +";PAR_PRD_NAME_FLAG="+PAR_PRD_NAME_FLAG;
        */
        boolean IS_FIRST_SHOW = true;
        if(sqlColumn.toString().length()<1){
        	IS_FIRST_SHOW = false;
        	 
        } 
    	/*
        params = params 
    	+sqlColumn.toString()
    	+";IS_FIRST_SHOW="+IS_FIRST_SHOW;
        */
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		//request.setAttribute("conDition", params);
		request.setAttribute("rptModel", "drpsalereport.raq");
		request.setAttribute("printModel", "drpsalereport.raq");
		return mapping.findForward("pageResult");
	}
	
	
	
}
