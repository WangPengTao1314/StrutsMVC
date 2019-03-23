package com.hoperun.report.reportcomm.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.InterUtil;
import com.hoperun.commons.util.InterfaceInvokeUtil;
import com.hoperun.commons.util.JesonImplUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.SpringContextUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.mkmdayreport.model.MkmDayReportModel;
import com.hoperun.drp.store.inventory.service.InventoryService;
import com.hoperun.report.allocatecount.service.AllocatecountService;
import com.hoperun.report.goodsorder.service.GoodsorderService;
import com.hoperun.report.reportcomm.service.ReportService;
import com.hoperun.report.returncount.service.ReturncountService;
import com.hoperun.report.saleordersendout.service.SaleorderSendoutService;
import com.hoperun.report.store.service.StoreRepertoryService;
import com.hoperun.sys.model.UserBean;

/**
 * 报表管理
 * 
 * @author zhuxw
 * 
 */
public class ReportAction extends BaseAction {
	private Logger logger = Logger.getLogger(ReportAction.class);

	private ReportService reportService;

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toYWBB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 设置跳转时需要的一些公用属性
		String toList = request.getParameter("toList");
		return mapping.findForward(toList);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 设置跳转时需要的一些公用属性
		String rptConFile = request.getParameter("rptConFile");
		request.setAttribute("rptConFile", rptConFile);
		String sjcd = request.getParameter("sjcd");
		request.setAttribute("sjcd", sjcd);
		String backpath = request.getParameter("backPath");
		request.setAttribute("backPath", backpath);
		//是否成本报表标记
		request.setAttribute("COST_FLAG", request.getParameter("COST_FLAG"));
		return mapping.findForward("frames");
	}

	/**
	 * 
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
		request.setAttribute("COST_FLAG", request.getParameter("COST_FLAG"));
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("CHANN_NO", userBean.getCHANN_NO());
		request.setAttribute("LEDGER_NAME", userBean.getLoginZTMC());
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		request.setAttribute("IS_DRP_LEDGER", IS_DRP_LEDGER);
		request.setAttribute("CHANNS_CHARG",userBean.getCHANNS_CHARG());
		request.setAttribute("TERM_CHARGE", userBean.getTERM_CHARGE());
		String IS_NO_DELIVER_PLAN_FLAG=Properties.getString("IS_NO_DELIVER_PLAN_FLAG");
		request.setAttribute("IS_NO_DELIVER_PLAN_FLAG", IS_NO_DELIVER_PLAN_FLAG);
		String CHANN_ID=userBean.getCHANN_ID();
		Map channInfo=reportService.queryChannInfo(CHANN_ID);
		request.setAttribute("channInfo", channInfo);
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		
//		List list  = new  ArrayList();
//        for(int i=2010;i<2031;i++){
//       	  list.add(i);
//        }
//        request.setAttribute("list", list);
//        
//        List list1  = new  ArrayList();
//        for(int j=1;j<13;j++){
//       	  list1.add(j);
//        }
//        request.setAttribute("list1", list1);
        
        Date  date   = new Date();
        String year  = String.valueOf(date.getYear()+1900);
		request.setAttribute("year", year);
		return mapping.findForward(rptConFile);
	}

	/**
	 * 列表 Description :
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	/**
	 * public ActionForward toPageResult(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) { //查询条件
	 * //拼接好条件的sql String conDition = ParamUtil.get(request, "conDition");
	 * //报表模板路径名/文件名 String rptModel=ParamUtil.get(request, "rptModel"); String
	 * printModel=ParamUtil.get(request, "printModel"); String cutSql =
	 * ParamUtil.get(request, "cutSql"); Map params = new HashMap();
	 * System.err.println("cutSql===="+cutSql); params.put("SelSQL",cutSql); Map
	 * resMap = reportService.selecTotalCount(params);
	 * request.setAttribute("conDition", conDition);
	 * request.setAttribute("rptModel", rptModel);
	 * request.setAttribute("printModel", printModel);
	 * request.setAttribute("totalCount", resMap.get("NUM")); return
	 * mapping.findForward("rptPageResult"); }
	 **/

	/**
	 * TEST报表例子(真分页) Description :
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toRealTestPageResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		// 组织SQL
		StringBuffer sql = new StringBuffer(
				" select YHM YHMC,YHIP,DLSJ from T_SYS_XTYH a,T_SYS_DLRZ b where a.XTYHID=b.XTYHID ");
		StringBuffer coutsql = new StringBuffer(
				" select count(1) NUM from T_SYS_XTYH a,T_SYS_DLRZ b where a.XTYHID=b.XTYHID ");
		String YHMC = ParamUtil.get(request, "YHMC");
		String YHIP = ParamUtil.get(request, "YHIP");
		String KSRQ = ParamUtil.get(request, "KSRQ");
		String JZRQ = ParamUtil.get(request, "JZRQ");
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer("");
		if (!StringUtil.isEmpty(YHMC)) {
			condition.append(" and a.YHM='" + YHMC + "'");
		}
		if (!StringUtil.isEmpty(YHIP)) {
			condition.append(" and b.YHIP='" + YHIP + "'");
		}
		if (!StringUtil.isEmpty(KSRQ)) {
			condition.append(" and to_char(b.DLSJ,'YYYY-MM-DD')>='" + KSRQ
					+ "'");
		}
		if (!StringUtil.isEmpty(JZRQ)) {
			condition.append(" and to_char(b.DLSJ,'YYYY-MM-DD')>='" + JZRQ
					+ "'");
		}
		String ordersql = "order by b.DLSJ asc";
		Map params = new HashMap();
		params.put("SelSQL", coutsql.append(condition.toString()).toString());
		Map resMap = reportService.selecTotalCount(params);
		request.setAttribute("conDition", "rptSql="
				+ sql.append(condition.toString()).append(ordersql));
		request.setAttribute("rptModel", "login_real.raq");
		request.setAttribute("printModel", "login_real.raq");
		request.setAttribute("totalCount", resMap.get("NUM"));
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("realPageResult");
	}

	/**
	 * TEST报表例子(假分页) Description :
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toBastTestPageResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		// 组织SQL
		StringBuffer sql = new StringBuffer(
				" select YHM YHMC,YHIP,DLSJ from T_SYS_XTYH a,T_SYS_DLRZ b where a.XTYHID=b.XTYHID ");
		String YHMC = ParamUtil.get(request, "YHMC");
		String YHIP = ParamUtil.get(request, "YHIP");
		String KSRQ = ParamUtil.get(request, "KSRQ");
		String JZRQ = ParamUtil.get(request, "JZRQ");
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer("");
		if (!StringUtil.isEmpty(YHMC)) {
			condition.append(" and a.YHM='" + YHMC + "'");
		}
		if (!StringUtil.isEmpty(YHIP)) {
			condition.append(" and b.YHIP='" + YHIP + "'");
		}
		if (!StringUtil.isEmpty(KSRQ)) {
			condition.append(" and to_char(b.DLSJ,'YYYY-MM-DD')>='" + KSRQ
					+ "'");
		}
		if (!StringUtil.isEmpty(JZRQ)) {
			condition.append(" and to_char(b.DLSJ,'YYYY-MM-DD')>='" + JZRQ
					+ "'");
		}
		String ordersql = "order by b.DLSJ asc";
		request.setAttribute("conDition", "rptSql="
				+ sql.append(condition.toString()).append(ordersql));
		request.setAttribute("rptModel", "login_bast.raq");
		request.setAttribute("printModel", "login_bast.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("bastPageResult");
	}
	
	
	/**
	 * 销售订单出货明细 查询结果报表 Description :
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toSaleOrderSendResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		String AREA_NO = ParamUtil.get(request, "AREA_NO");
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME");
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String ORDER_DATE_BEG = ParamUtil.get(request, "ORDER_DATE_BEG");
		String ORDER_DATE_END = ParamUtil.get(request, "ORDER_DATE_END");
//		String PRD_TYPE_NAME = ParamUtil.get(request, "PRD_TYPE_NAME");
		String PRD_TYPE_NO = ParamUtil.get(request, "PRD_TYPE_NO");
        String PRD_GROUP_NAME = ParamUtil.get(request, "PRD_GROUP_NAME");
		String KSRQ = ParamUtil.get(request, "KSRQ");
		String JZRQ = ParamUtil.get(request, "JZRQ");
		String STATE = ParamUtil.get(request, "STATE");
		String SHIP_POINT_NAME = ParamUtil.get(request, "SHIP_POINT_NAME");
		String SHIP_POINT_NO = ParamUtil.get(request, "SHIP_POINT_NO");
		String SALE_ORDER_NO=ParamUtil.get(request, "SALE_ORDER_NO");
		String PROV = ParamUtil.get(request, "PROV");//省份
		String ISAllCHANN = ParamUtil.get(request, "ISAllCHANN");//是否显示全部 
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		String IS_NO_DELIVER_PLAN_FLAG=Properties.getString("IS_NO_DELIVER_PLAN_FLAG");
		String DELIVER_TYPE = ParamUtil.get(request, "DELIVER_TYPE"); 
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" where a.DEL_FLAG=0 ");
		if (!StringUtil.isEmpty(AREA_NO)) {
			//拼装 like sql
			String sql = StringUtil.creCon("a.WAREA_NO", AREA_NO, ",");
			condition.append(sql);
			 
		}
		if (!StringUtil.isEmpty(AREA_NAME)) {
			//拼装 like sql
			String sql = StringUtil.creCon("a.WAREA_NAME", AREA_NAME, ",");
			condition.append(sql);
		}
		if (!StringUtil.isEmpty(CHANN_NO)) {
			//拼装 like sql
			String sql = StringUtil.creCon("a.ORDER_CHANN_NO", CHANN_NO, ",");
			condition.append(sql);
			
		}
		if (!StringUtil.isEmpty(CHANN_NAME)) {
			//拼装 like sql
			String sql = StringUtil.creCon("a.ORDER_CHANN_NAME", CHANN_NAME, ",");
			condition.append(sql);
		}
	 
		
		if (!StringUtil.isEmpty(PRD_NO)) {
			//拼装 like sql
			String sql = StringUtil.creCon("e.PRD_NO", PRD_NO, ",");
			condition.append(sql);
		}
		if (!StringUtil.isEmpty(PRD_NAME)) {
			//拼装 like sql
			String sql = StringUtil.creCon("e.PRD_NAME", PRD_NAME, ",");
			condition.append(sql);
		}
		
		if (!StringUtil.isEmpty(KSRQ)) {
			condition.append(" and to_char(a.CRE_TIME,'YYYY-MM-DD')>='" + KSRQ
					+ "'");
		}
		if (!StringUtil.isEmpty(JZRQ)) {
			condition.append(" and to_char(a.CRE_TIME,'YYYY-MM-DD')<='" + JZRQ
					+ "'");
		}

		if (!StringUtil.isEmpty(ORDER_DATE_BEG)) {
			condition.append(" and to_char(a.ORDER_DATE,'YYYY-MM-DD')>='"
					+ ORDER_DATE_BEG + "'");
		}
		if (!StringUtil.isEmpty(ORDER_DATE_END)) {
			condition.append(" and to_char(a.ORDER_DATE,'YYYY-MM-DD')<='"
					+ ORDER_DATE_END + "'");
		}

//		if (!StringUtil.isEmpty(PRD_TYPE_NAME)) {
//			//拼装 like sql
//			String sql = StringUtil.creCon("f.PRD_NAME", PRD_TYPE_NAME, ",");
//			condition.append(sql);
//		}
		if (!StringUtil.isEmpty(PRD_TYPE_NO)) {
			//拼装 like sql
			String sql = StringUtil.creEqualCon("f.PRD_NO", PRD_TYPE_NO, ",");
			condition.append(sql);
		}
		if(!StringUtil.isEmpty(DELIVER_TYPE)){
			condition.append("");
		}

		if (!StringUtil.isEmpty(STATE)) {
			STATE = STATE.replaceAll(",", "','");
			condition.append(" and a.STATE in ('" + STATE + "')");
		}
		if(!StringUtil.isEmpty(SALE_ORDER_NO)){
			SALE_ORDER_NO = SALE_ORDER_NO.replaceAll(",", "','");
			condition.append(" and a.SALE_ORDER_NO in ('" + SALE_ORDER_NO + "')");
//			condition.append(" and a.SALE_ORDER_NO like '%").append(SALE_ORDER_NO).append("%' ");
		}
		if(!StringUtil.isEmpty(PROV)){
			condition.append(StringUtil.creCon("g.PROV", PROV, ","));
		}
		if (!StringUtil.isEmpty(PRD_GROUP_NAME)) {
			//拼装 like sql
			String sql = StringUtil.creCon("gg.PRD_GROUP_NAME", PRD_GROUP_NAME, ",");
			condition.append(" and e.PRD_ID in (select  distinct gd.PRD_ID   from BASE_PRD_GROUP gg, BASE_PRD_GROUP_DTL gd where gd.PRD_GROUP_ID = gg.PRD_GROUP_ID ");
			condition.append(sql);
			condition.append(" ) ");
		}
		if (!StringUtil.isEmpty(SHIP_POINT_NAME)) {
			//拼装 like sql
			String sql = StringUtil.creCon("a.SHIP_POINT_NAME", SHIP_POINT_NAME, ",");
			condition.append(sql);
		}
		
		if (!StringUtil.isEmpty(SHIP_POINT_NO)) {
			//拼装 like sql
			String sql = StringUtil.creEqualCon("a.SHIP_POINT_ID", SHIP_POINT_NO, ",");
			condition.append(sql);
		}
		//是否显示全部渠道
		if(!BusinessConsts.INTEGER_1.equals(ISAllCHANN)){
			if(!BusinessConsts.INTEGER_1.equals(IS_DRP_LEDGER)){
				condition.append(" and a.ORDER_CHANN_ID in ").append(userBean.getCHANNS_CHARG());
			}
		}
		SaleorderSendoutService saleorderSendoutService = (SaleorderSendoutService) SpringContextUtil.getBean("saleorderSendoutService");
		String rptSql="";
		if("1".equals(IS_NO_DELIVER_PLAN_FLAG)){
			rptSql = saleorderSendoutService.createNewSql(condition.toString());
			request.setAttribute("rptModel", "SaleorderSendoutNewMX.raq");
			request.setAttribute("printModel", "SaleorderSendoutNewMX.raq");
		}else{
			rptSql = saleorderSendoutService.createSql(condition.toString());
			request.setAttribute("rptModel", "SaleorderSendoutMX.raq");
			request.setAttribute("printModel", "SaleorderSendoutMX.raq");
		}
		   
		request.setAttribute("conDition", rptSql);
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		System.out
				.println("*************end*****goin SaleOrderSend*****************");
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 订货订单 查询结果报表 Description :
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toGoodOrderResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		String AREA_NO = ParamUtil.get(request, "AREA_NO");
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME");
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String KSRQ = ParamUtil.get(request, "KSRQ");
		String JZRQ = ParamUtil.get(request, "JZRQ");
		String STATE = ParamUtil.get(request, "STATE");
		String PAR_PRD_NAME = ParamUtil.get(request, "PAR_PRD_NAME");

		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" where a.DEL_FLAG=0 ");
		if (!StringUtil.isEmpty(AREA_NO)) {
			//拼装 like sql
			String sql = StringUtil.creCon("b.AREA_NO,area.AREA_NO_P", AREA_NO, ",");
			condition.append(sql);
			
//			condition.append(" and (b.AREA_NO like '%" + AREA_NO + "%' or  area.AREA_NO_P like '%"+AREA_NO+"%') ");
		}
		if (!StringUtil.isEmpty(AREA_NAME)) {
			//拼装 like sql
			String sql = StringUtil.creCon("b.AREA_NAME,area.AREA_NAME_P", AREA_NAME, ",");
			condition.append(sql);
//			condition.append(" and (b.AREA_NAME like '%" + AREA_NAME + "%' or  area.AREA_NAME_P like '%"+AREA_NAME+"%') ");
		}
		if (!StringUtil.isEmpty(CHANN_NO)) {
			//拼装 like sql
			String sql = StringUtil.creCon("b.CHANN_NO", CHANN_NO, ",");
			condition.append(sql);
		}
		condition.append(" and  b.CHANN_ID in ").append(userBean.getCHANNS_CHARG());
		if (!StringUtil.isEmpty(CHANN_NAME)) {
			//拼装 like sql
			String sql = StringUtil.creCon("b.CHANN_NAME", CHANN_NAME, ",");
			condition.append(sql);
		}
		if (!StringUtil.isEmpty(PRD_NO)) {
			//拼装 like sql
			String sql = StringUtil.creCon("c.PRD_NO", PRD_NO, ",");
			condition.append(sql);
		}
		if (!StringUtil.isEmpty(PRD_NAME)) {
			//拼装 like sql
			String sql = StringUtil.creCon("c.PRD_NAME", PRD_NAME, ",");
			condition.append(sql);
		}
		if (!StringUtil.isEmpty(KSRQ)) {
			condition.append(" and to_char(a.CRE_TIME,'YYYY-MM-DD')>='" + KSRQ
					+ "'");
		}
		if (!StringUtil.isEmpty(JZRQ)) {
			condition.append(" and to_char(a.CRE_TIME,'YYYY-MM-DD')<='" + JZRQ
					+ "'");
		}
		if (!StringUtil.isEmpty(STATE)) {
			condition.append(" and a.STATE='" + STATE + "'");
		}

		if (!StringUtil.isEmpty(PAR_PRD_NAME)) {
			//拼装 like sql
			String sql = StringUtil.creCon("d.PAR_PRD_NAME", PAR_PRD_NAME, ",");
			condition.append(sql);
		}
		GoodsorderService goodsorderService = (GoodsorderService) SpringContextUtil
				.getBean("rGoodsorderService");
		String rptSql = goodsorderService.createSql(condition.toString());

		request.setAttribute("conDition", "rptSql=" + rptSql);
		request.setAttribute("rptModel", "goodsorder.raq");
		request.setAttribute("printModel", "goodsorder.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		return mapping.findForward("pageResult");
	}
	
	

	/**
	 * 撤店退货统计 查询结果报表 Description : toGoodOrderResult
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toReturnCounResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		String SALE_DATE = ParamUtil.get(request, "SALE_DATE");
		String PRD_TYPE = ParamUtil.get(request, "PRD_TYPE");
		String TERM_NO = ParamUtil.get(request, "TERM_NO");
		String ADVC_ORDER_NO = ParamUtil.get(request, "ADVC_ORDER_NO");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String RT_DATE = ParamUtil.get(request, "RT_DATE");
		String PAR_PRD_NO= ParamUtil.get(request, "PAR_PRD_NO");
		String PAR_PRD_NAME= ParamUtil.get(request, "PAR_PRD_NAME");
		String CUST_NAME = ParamUtil.get(request, "CUST_NAME");
		String TEL = ParamUtil.get(request, "TEL");
		
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer("");

		if (!StringUtil.isEmpty(TERM_NO)) {
			condition.append(" and t.TERM_NO='" + TERM_NO + "'");
		}
		
		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
			condition.append(StringUtil.creCon("prd.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(PAR_PRD_NO)){
			condition.append(StringUtil.creCon("prd.PAR_PRD_NO", PAR_PRD_NO, ","));
		}
		
		if (!StringUtil.isEmpty(CUST_NAME)) {
			condition.append(" and t.CUST_NAME like '%" + CUST_NAME + "%'");
		}
		if (!StringUtil.isEmpty(TEL)) {
			condition.append(" and t.TEL like '%" + TEL + "%'");
		}
		
		if (!StringUtil.isEmpty(ADVC_ORDER_NO)) {
			condition.append(" and t.ADVC_ORDER_NO='" + ADVC_ORDER_NO + "'");
		}
		if (!StringUtil.isEmpty(SALE_DATE)) {
			condition.append(" and to_char(t.SALE_DATE,'YYYY-MM-DD')='"
					+ SALE_DATE + "'");
		}
		if (!StringUtil.isEmpty(RT_DATE)) {
			condition.append(" and to_char(t.CRE_TIME,'YYYY-MM-DD')='"
					+ RT_DATE + "'");
		}
		if (!StringUtil.isEmpty(PRD_TYPE)) {
			condition.append(" and t.PRD_TYPE='" + PRD_TYPE + "'");
		}
		if (!StringUtil.isEmpty(PRD_NO)) {
			condition.append(" and d.PRD_NO='" + PRD_NO + "'");
		}
		ReturncountService returncountService = (ReturncountService) SpringContextUtil
				.getBean("rReturncountService");
		String rptOneSql = returncountService
				.createOneSql(condition.toString());

		request.setAttribute("conDition", "rptSql=" + rptOneSql);
		request.setAttribute("rptModel", "returnCount.raq");
		request.setAttribute("printModel", "returnCount.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	}

	/**
	 * 门店销售报表 Description :
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toReportPageResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		String START_SALE_DATE = ParamUtil.get(request, "START_SALE_DATE");
		String END_SALE_DATE = ParamUtil.get(request, "END_SALE_DATE");
		String DEAL_TIME_BEGIN = ParamUtil.get(request, "ORDER_RECV_DATE_BEGIN");
		String DEAL_TIME_END = ParamUtil.get(request, "ORDER_RECV_DATE_END");
		String ADVC_ORDER_NO = ParamUtil.get(request, "ADVC_ORDER_NO");
		String PROMOTE_NO = ParamUtil.get(request, "PROMOTE_NO");
		String PROMOTE_NAME = ParamUtil.get(request, "PROMOTE_NAME");
		String PRD_NO=ParamUtil.get(request, "PRD_NO");
		String PRD_NAME=ParamUtil.get(request, "PRD_NAME");
		String PAR_PRD_NAME=ParamUtil.get(request, "PAR_PRD_NAME");
		String CRE_NAME=ParamUtil.get(request, "CRE_NAME");
		String TERM_NAME = ParamUtil.get(request, "TERM_NAME");
		String TERM_NO = ParamUtil.get(request, "TERM_NO");
		String CUST_NAME = ParamUtil.get(request, "CUST_NAME");
		String CONTRACT_NO = ParamUtil.get(request, "CONTRACT_NO");
		String SEND_STATE= ParamUtil.get(request, "SEND_STATE");
		String TERM_CLASS= ParamUtil.get(request, "TERM_CLASS");
		String COST_FLAG =ParamUtil.get(request, "COST_FLAG");//成本类型  1是供货价，0是月成本
		String QUERYFLAG=ParamUtil.get(request, "QUERYFLAG");//1货品 2导购 3品类 4门店
		if(StringUtil.isEmpty(QUERYFLAG)){
			QUERYFLAG="1";
		}
		//为1时来自财务报表里的链接，否则为门店报表里的连接
		String FDflag=ParamUtil.get(request, "FDflag");
		StringBuffer sql = new StringBuffer();
		sql
		   .append(" SELECT  t.ADVC_ORDER_DTL_ID,t.TERM_NO, t.PROMOTE_NO, t.PROMOTE_NAME,t.ADVC_ORDER_ID,t.COST_AMOUNT,")
		   .append(" t.COST_PRICE,t.TERM_NAME, t.SPCL_DTL_REMARK,t.CRE_NAME,t.SALE_DATE, t.ORDER_RECV_DATE, t.CUST_NAME,")
		   .append(" t.CONTRACT_NO, t.ADVC_ORDER_NO, t.SEND_AMOUNT,t.TOTAL_AMOUNT,t.PAYABLE_AMOUNT,t.SEND_NUM,t.ADVC_AMOUNT,")
		   .append(" t.STATEMENTS_AMOUNT, t.DISCOUNT_AMOUNT, t.BILL_TYPE, t.PRD_NO, t.PRD_NAME,t.PRD_SPEC, t.PAR_PRD_NAME,t.ORDER_NUM,")
		   .append(" t.REMARK,t.DECT_PRICE,t.TEL,t.RECV_ADDR,t.DTL_PAYABLE_AMOUNT,t.DEAL_TIME,t.NO_SEND_NUM,t.NO_SEND_AMOUNT,t.PAR_PRD_NO,")
		   .append(" SUM(t.PAYABLE_AMOUNT) over() TOTAL_PAYABLE_AMOUNT,SUM(t.ADVC_AMOUNT) over() TOTAL_ADVC_AMOUNT,SUM(t.PAYABLE_AMOUNT-t.ADVC_AMOUNT) over() TOTAL_PAYABLE_ADVC_AMOUNT,")
		   .append(" SUM(t.statements_amount) over() TOTAL_STATEMENTS_AMOUNT,SUM(t.order_num) over() TOTAL_ORDER_NUM,SUM(t.SEND_NUM) over() TOTAL_SEND_NUM,SUM(t.DECT_PRICE*t.SEND_NUM) over() TOTAL_DECT_SEND_NUM,")
		   .append(" SUM(t.COST_PRICE) over() TOTAL_COST_PRICE, SUM(t.COST_AMOUNT) over() TOTAL_COST_AMOUNT,  SUM(t.NO_SEND_NUM) over() TOTAL_NO_SEND_NUM, SUM(t.NO_SEND_AMOUNT) over() TOTAL_NO_SEND_AMOUNT ")
		   .append(" FROM  (")
		   .append(" select  d.ADVC_ORDER_DTL_ID,")
		   .append("t.TERM_NO,")
		   .append("t.PROMOTE_NO,")
		   .append("t.PROMOTE_NAME,")
		   .append("t.ADVC_ORDER_ID,")
		   .append("(round(NVL(u.COST_PRICE, 0),2) *decode(t.BILL_TYPE,'终端销售',NVL(i.REAL_NUM, 0),NVL(x.REAL_NUM * -1, 0))) COST_AMOUNT,")
		   .append("round(NVL(u.COST_PRICE, 0), 2) COST_PRICE,")
		   .append("t.TERM_NAME,")
		   .append("spcl.SPCL_DTL_REMARK,")
		   .append("t.SALE_PSON_NAME CRE_NAME,")
		   .append("decode(t.BILL_TYPE,'终端销售', to_char(t.SALE_DATE, 'yyyy-MM-DD'), to_char(t.return_statement_date, 'yyyy-MM-DD')) SALE_DATE,")
		   .append("d.ORDER_RECV_DATE,")
		   .append("t.CUST_NAME,")
		   .append("t.CONTRACT_NO,")
		   .append("t.ADVC_ORDER_NO,")
		   .append("(NVL(d.SEND_NUM,0)*( case when d.PRD_TYPE='赠品' then 0 else NVL(d.DECT_PRICE, 0) end))SEND_AMOUNT,")
		   .append("decode(t.BILL_TYPE,'终端销售',NVL(t.TOTAL_AMOUNT, 0),NVL(t.TOTAL_AMOUNT * -1, 0)) TOTAL_AMOUNT,")
		   .append("decode(t.BILL_TYPE,'终端销售',NVL(t.PAYABLE_AMOUNT, 0),NVL(t.PAYABLE_AMOUNT * -1, 0)) PAYABLE_AMOUNT,")
		   .append("decode(t.BILL_TYPE,'终端销售',NVL(i.REAL_NUM, 0),NVL(x.REAL_NUM * -1, 0)) SEND_NUM,")
		   .append(" pm.ADVC_AMOUNT,")
		   .append("stm.STATEMENTS_AMOUNT,")
		   .append("nvl(t.DISCOUNT_AMOUNT, 0) DISCOUNT_AMOUNT,t.BILL_TYPE,d.PRD_NO,d.PRD_NAME,d.PRD_SPEC,e.PAR_PRD_NAME,")
		   .append("decode(t.BILL_TYPE,'终端销售', NVL(d.ORDER_NUM, 0),NVL(d.ORDER_NUM * -1, 0)) ORDER_NUM,")
		   .append("t.REMARK,( case when d.PRD_TYPE='赠品' then 0 else NVL(d.DECT_PRICE, 0) end)DECT_PRICE,t.TEL,t.RECV_ADDR,")
		   .append("(case when d.PRD_TYPE = '赠品' then 0 else NVL(d.PAYABLE_AMOUNT, 0) end) DTL_PAYABLE_AMOUNT,")
		   .append("decode(t.BILL_TYPE,'终端销售',to_char(h.DEAL_TIME, 'yyyy-mm-dd'),to_char(k.DEAL_TIME, 'yyyy-mm-dd')) DEAL_TIME,")
		   .append("NVL((NVL(d.ORDER_NUM,0)-NVL(d.SEND_NUM,0)),0)NO_SEND_NUM,NVL((NVL((NVL(d.ORDER_NUM,0)-NVL(d.SEND_NUM,0)),0)*NVL(d.DECT_PRICE,0)),0)NO_SEND_AMOUNT,e.PAR_PRD_NO ")
		   .append(" from DRP_ADVC_ORDER t ")
		   .append(" left join DRP_ADVC_ORDER_DTL d on t.ADVC_ORDER_ID = d.ADVC_ORDER_ID and d.DEL_FLAG = 0 ")
		   .append(" left join BASE_PRODUCT e on e.PRD_ID = d.PRD_ID ")
		   .append(" left join DRP_ADVC_SEND_REQ_DTL g  on g.FROM_BILL_DTL_ID=d.ADVC_ORDER_DTL_ID and g.DEL_FLAG=0 ")
		   .append(" left join DRP_ADVC_SEND_REQ f on t.ADVC_ORDER_ID=f.FROM_BILL_ID and g.ADVC_SEND_REQ_ID=f.ADVC_SEND_REQ_ID and f.STATE  not in ('未提交','提交') and f.DEL_FLAG=0 ")
		   .append(" left join DRP_STOREOUT h on h.FROM_BILL_ID=f.ADVC_SEND_REQ_ID and h.STATE='已处理' and h.DEL_FLAG=0 and h.BILL_TYPE='销售出库' ")
		   .append(" left join ")
		   .append(" (select sum(NVL(REAL_NUM,0)) REAL_NUM,STOREOUT_ID,FROM_BILL_DTL_ID  from  DRP_STOREOUT_DTL where DEL_FLAG = 0 group by STOREOUT_ID,FROM_BILL_DTL_ID ) i ")
		   .append(" on i.STOREOUT_ID = h.STOREOUT_ID  and i.FROM_BILL_DTL_ID = g.ADVC_SEND_REQ_DTL_ID ")
		   .append(" left join DRP_STOREIN k on t.ADVC_ORDER_ID = k.FROM_BILL_ID and k.DEAL_FLAG = 1 and K.DEL_FLAG = 0")
		   .append(" left join DRP_STOREIN_DTL x on k.STOREIN_ID = x.STOREIN_ID and d.ADVC_ORDER_DTL_ID = x.FROM_BILL_DTL_ID and x.DEL_FLAG = 0");
		   if("1".equals(COST_FLAG)){
			   sql.append(" left join  V_PRODUCT_PRVD_PRICE u on  d.PRD_ID = u.PRD_ID and NVL(d.SPCL_TECH_ID, 'NONE') = NVL(u.SPCL_TECH_ID, 'NONE') ");
		   }else{
			   sql.append(" left join DRP_COST_PRICE u on t.LEDGER_ID = u.LEDGER_ID and d.PRD_ID = u.PRD_ID and NVL(d.SPCL_TECH_ID, 'NONE') = NVL(u.SPCL_TECH_ID, 'NONE')")
			   .append(" and u.year = decode(t.BILL_TYPE,'终端销售',to_char(h.DEAL_TIME, 'yyyy'),to_char(k.DEAL_TIME, 'yyyy')) ")
			   .append(" and u.month = decode(t.BILL_TYPE, '终端销售',to_char(h.DEAL_TIME, 'mm'),to_char(k.DEAL_TIME, 'mm'))");
		   }
		   sql.append(" left join DRP_SPCL_TECH spcl on spcl.SPCL_TECH_ID=d.SPCL_TECH_ID and spcl.USE_FLAG=1 ")
		   .append(" left join BASE_TERMINAL l on l.TERM_ID=t.TERM_ID and l.DEL_FLAG=0 ")
		   .append(" left join (select sum(decode(p.BILL_TYPE,'销售退款',NVL(p.STATEMENTS_AMOUNT * -1, 0),NVL(p.STATEMENTS_AMOUNT, 0))) ADVC_AMOUNT,p.ADVC_ORDER_ID from DRP_STATEMENTS p where p.DEL_FLAG = 0 and p.BILL_TYPE in ('正常收款', '订金') and p.STATE in ('已结算', '已核销') group by p.ADVC_ORDER_ID) pm on pm.ADVC_ORDER_ID = t.ADVC_ORDER_ID ")
		   .append(" left join (select sum(NVL(st.STATEMENTS_AMOUNT, 0)) STATEMENTS_AMOUNT,st.ADVC_ORDER_ID from DRP_STATEMENTS st where st.DEL_FLAG = 0 and st.STATE = '已结算' and st.BILL_TYPE = '销售退款' group by st.ADVC_ORDER_ID) stm on stm.ADVC_ORDER_ID = t.ADVC_ORDER_ID ")
		   .append(" where t.DEL_FLAG = 0 and d.STATE='正常'  and t.STATE in ('审核通过', '待确认', '已发货', '待退货', '已退货') ");
					
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer("");

		if (!StringUtil.isEmpty(START_SALE_DATE)) {
			condition.append(" and t.SALE_DATE >=to_date('" + START_SALE_DATE + "','yyyy-mm-dd')");
		}
		if (!StringUtil.isEmpty(END_SALE_DATE)) {
			condition.append(" and t.SALE_DATE<=to_date('" + END_SALE_DATE + "','yyyy-mm-dd')");
		}
		
		if (!StringUtil.isEmpty(DEAL_TIME_BEGIN)&&!StringUtil.isEmpty(DEAL_TIME_END)) {
			condition.append(" and ((h.DEAL_TIME >=to_date('" + DEAL_TIME_BEGIN + "','yyyy-mm-dd') and h.DEAL_TIME<=to_date('" + DEAL_TIME_END+"','yyyy-mm-dd')+1 )")
			  .append(" or (k.DEAL_TIME >=to_date('" + DEAL_TIME_BEGIN + "','yyyy-mm-dd') and k.DEAL_TIME<=to_date('" + DEAL_TIME_END+"','yyyy-mm-dd')+1 ))");
			
		}else if(StringUtil.isEmpty(DEAL_TIME_BEGIN)&&!StringUtil.isEmpty(DEAL_TIME_END)){
			condition.append(" and (h.DEAL_TIME<=to_date('" + DEAL_TIME_END+"','yyyy-mm-dd')+1 or k.DEAL_TIME<=to_date('" + DEAL_TIME_END+"','yyyy-mm-dd')+1");
		}else if(!StringUtil.isEmpty(DEAL_TIME_BEGIN)&&StringUtil.isEmpty(DEAL_TIME_END)){
			condition.append(" and (h.DEAL_TIME >=to_date('" + DEAL_TIME_BEGIN + "','yyyy-mm-dd') or k.DEAL_TIME >=to_date('" + DEAL_TIME_BEGIN + "','yyyy-mm-dd'))");
		}
		if (!StringUtil.isEmpty(ADVC_ORDER_NO)) {
			condition.append(" and t.ADVC_ORDER_NO like '%" + ADVC_ORDER_NO + "%'");
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			condition.append(StringUtil.creCon("d.PRD_NO", PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			condition.append(StringUtil.creCon("d.PRD_NAME", PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
			condition.append(StringUtil.creCon("e.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(CRE_NAME)){
			condition.append(StringUtil.creCon("t.CRE_NAME", CRE_NAME, ","));
		}
		if(!StringUtil.isEmpty(TERM_NAME)){
			condition.append(StringUtil.creCon("t.TERM_NAME", TERM_NAME, ","));
		}
		if(!StringUtil.isEmpty(TERM_NO)){
			condition.append(StringUtil.creCon("t.TERM_NO", TERM_NO, ","));
		}
		if(!StringUtil.isEmpty(PROMOTE_NO)){
			condition.append(StringUtil.creCon("t.PROMOTE_NO", PROMOTE_NO, ","));
		}
		if(!StringUtil.isEmpty(PROMOTE_NAME)){
			condition.append(StringUtil.creCon("t.PROMOTE_NAME", PROMOTE_NAME, ","));
		}
		if(!StringUtil.isEmpty(CONTRACT_NO)){
			condition.append(StringUtil.creCon("t.CONTRACT_NO", CONTRACT_NO, ","));
		}
		if(!StringUtil.isEmpty(CUST_NAME)){
			condition.append(StringUtil.creCon("t.CUST_NAME", CUST_NAME, ","));
		}
		if(!"全部".equals(SEND_STATE)&&!StringUtil.isEmpty(SEND_STATE)){
			StringBuffer str=new StringBuffer();
			str.append(" select m.ADVC_ORDER_ID from DRP_ADVC_ORDER m ")
			   .append(" left join DRP_ADVC_ORDER_DTL n on m.ADVC_ORDER_ID = n.ADVC_ORDER_ID and n.DEL_FLAG = 0 ")
			   .append(" where m.DEL_FLAG = 0 group by m.ADVC_ORDER_ID ");
			if("已发货".equals(SEND_STATE)){
				str.append(" having sum(NVL(n.ORDER_NUM, 0)) = sum(NVL(n.SEND_NUM, 0)) ");
			}else if("未发货".equals(SEND_STATE)){
					str.append(" having sum(NVL(n.ORDER_NUM, 0)) > sum(NVL(n.SEND_NUM, 0)) ");
			}
			condition.append(" and t.ADVC_ORDER_ID in (").append(str.toString()).append(") ");
		}
		if(!StringUtil.isEmpty(TERM_CLASS)){
			condition.append(" and l.TERM_CLASS='").append(TERM_CLASS).append("' ");
		}
	    String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
	    if(BusinessConsts.INTEGER_1.equals(IS_DRP_LEDGER)){
	    	condition.append(" and t.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("'");
	    }
		condition.append(" and t.TERM_ID in ").append(userBean.getTERM_CHARGE());
		condition.append(" order by t.TERM_NO,t.ADVC_ORDER_NO ) t");
		sql = sql.append(condition);
		StringBuffer rowNam=new StringBuffer();
		if("1".equals(QUERYFLAG)){
			rowNam.append("SALE_DATE=ds1.SALE_DATE;")
				  .append("ORDER_RECV_DATE=ds1.ORDER_RECV_DATE;")
				  .append("ADVC_ORDER_NO=ds1.ADVC_ORDER_NO;")
				  .append("BILL_TYPE=ds1.BILL_TYPE;")
				  .append("PROMOTE_NAME=ds1.PROMOTE_NAME;")
				  .append("CUST_NAME=ds1.CUST_NAME;")
				  .append("CONTRACT_NO=ds1.CONTRACT_NO;")
				  .append("PRD_NO=ds1.PRD_NO;")
				  .append("PRD_NAME=ds1.PRD_NAME;")
				  .append("PRD_SPEC=ds1.PRD_SPEC;")
				  .append("PAR_PRD_NAME=ds1.PAR_PRD_NAME;")
				  .append("SPCL_DTL_REMARK=ds1.SPCL_DTL_REMARK;")
				  .append("DECT_PRICE=ds1.DECT_PRICE;")
				  .append("DEAL_TIME=ds1.DEAL_TIME;")
				  .append("REMARK=ds1.REMARK;")
				  .append("TEL=ds1.TEL;")
				  .append("RECV_ADDR=ds1.RECV_ADDR;")
				  .append("CRE_NAME=ds1.CRE_NAME;");
		}else if("2".equals(QUERYFLAG)){
			rowNam.append("CRE_NAME=ds1.CRE_NAME;");
		}else if("3".equals(QUERYFLAG)){
			rowNam.append("PAR_PRD_NAME=ds1.PAR_PRD_NAME;");
		}
		StringBuffer str=new StringBuffer();
		if(!StringUtil.isEmpty(QUERYFLAG)&&!"1".equals(QUERYFLAG)){
			str.append(" select temp.TERM_NAME,")
			.append("sum(NVL(temp.DTL_PAYABLE_AMOUNT,0))PAYABLE_AMOUNT,")
			.append("sum(NVL(temp.ADVC_AMOUNT,0))ADVC_AMOUNT,")
			.append("sum(NVL(temp.STATEMENTS_AMOUNT,0))STATEMENTS_AMOUNT,")
			.append("sum(NVL(temp.ORDER_NUM,0))ORDER_NUM,")
			.append("sum(NVL(temp.SEND_NUM,0))SEND_NUM,")
			.append("sum(NVL(temp.SEND_AMOUNT,0))SEND_AMOUNT ");
			if("2".equals(QUERYFLAG)){
				str.append(",temp.CRE_NAME ");
			}else if("3".equals(QUERYFLAG)){
				str.append(",temp.PAR_PRD_NAME,temp.PAR_PRD_NO ");
			}
			str.append(" from (");
		}
		str.append(sql.toString());
		if(!StringUtil.isEmpty(QUERYFLAG)&&!"1".equals(QUERYFLAG)){
			str.append(" ) temp group by temp.TERM_NAME");
			if("2".equals(QUERYFLAG)){
				str.append(",temp.CRE_NAME ");
			}else if("3".equals(QUERYFLAG)){
				str.append(",temp.PAR_PRD_NAME,temp.PAR_PRD_NO ");
			}
		}
		str.append(";").append(rowNam.toString()).append("arg1=").append(QUERYFLAG);
		request.setAttribute("conDition", "rptSql=" + str);
		if("1".equals(FDflag)){
			request.setAttribute("rptModel", "FDsaleCountNew.raq");
			request.setAttribute("printModel", "FDsaleCountNew.raq");
		}else{
			request.setAttribute("rptModel", "saleCountNew.raq");
			request.setAttribute("printModel", "saleCountNew.raq");
		}
		request.setAttribute("IS_DRP_LEDGER",IS_DRP_LEDGER);
		return mapping.findForward("pageResult");
  }
  
	  /**
		 * 门店库存报表
		 * Description :
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
	public ActionForward toStoreStockPageResult(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response){
			String LEDGER_ID = ParamUtil.get(request, "ztxxid");
			String PRD_ID = ParamUtil.get(request, "PRD_ID");
			UserBean userBean =  ParamUtil.getUserBean(request);
			StringBuffer sql = new StringBuffer();
			sql.append(" select rownum,s.store_no,s.store_name,p.prd_no,p.prd_name,p.prd_spec,nvl(a.STORE_NUM,0) STORE_NUM,nvl(a.freeze_num,0) freeze_num,sh.spcl_spec_remark ")
			   .append(" from DRP_STORE_ACCT a  ")
			   .append(" left join BASE_PRODUCT p on a.prd_id = p.prd_id ")
			   .append(" left join drp_spcl_tech sh on sh.prd_no = p.prd_no and NVL(sh.spcl_tech_id,'NULL') = NVL(a.store_acct_id,'null') ")
			   .append(" left join DRP_STORE s on s.store_id = a.store_id ")
			   .append(" WHERE s.LEDGER_ID = '").append(LEDGER_ID).append("'")
			   .append(" and s.TERM_STROE_FLAG=1");
			/********** 查询条件拼接 start **********/
			if(!StringUtil.isEmpty(PRD_ID)){
				sql.append(" and a.PRD_ID = '").append(PRD_ID).append("'");
			}
			request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
			request.setAttribute("conDition", "rptSql=" + sql.toString());
			request.setAttribute("rptModel", "storeStock.raq");
			request.setAttribute("printModel", "storeStock.raq");
			return mapping.findForward("pageResult");
	}
	
	/**
	 * 退货统计报表
	 * Description :
	}

	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
  public ActionForward toReturnPageResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
	  	UserBean userBean =  ParamUtil.getUserBean(request);
	  	String TERM_NO = ParamUtil.get(request, "TERM_NO");
		String TERM_NAME = ParamUtil.get(request, "TERM_NAME");
		String ADVC_ORDER_NO = ParamUtil.get(request, "ADVC_ORDER_NO");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String START_SALE_DATE = ParamUtil.get(request, "START_SALE_DATE");
		String END_SALE_DATE = ParamUtil.get(request, "END_SALE_DATE");
		String START_RT_DATE = ParamUtil.get(request, "START_RT_DATE");
		String END_RT_DATE = ParamUtil.get(request, "END_RT_DATE");
		String CUST_NAME = ParamUtil.get(request, "CUST_NAME");
		String TEL = ParamUtil.get(request, "TEL");
		String PAR_PRD_NO= ParamUtil.get(request, "PAR_PRD_NO");
		String PAR_PRD_NAME= ParamUtil.get(request, "PAR_PRD_NAME");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select rownum,a.ADVC_ORDER_NO,to_char(a.CRE_TIME,'yyyy-MM-DD')CRE_TIME,a.CUST_NAME,a.TEL,a.TERM_NAME,b.PRD_NAME,b.PRD_SPEC,b.ORDER_NUM,to_char(a.SALE_DATE,'yyyy-MM-DD')SALE_DATE,c.ADVC_ORDER_NO SALE_NO,c.TOTAL_AMOUNT  SALE_AMOUNT,a.TOTAL_AMOUNT,a.CUST_NAME,a.RETURN_RSON,a.REMARK ")
		   .append(" from DRP_ADVC_ORDER a  ")
		   .append(" left join DRP_ADVC_ORDER_DTL b on a.advc_order_id = b.advc_order_id ")
		   .append(" left join DRP_ADVC_ORDER c on a.from_bill_id = c.advc_order_id ")
		   .append(" left join BASE_PRODUCT prd on prd.PRD_ID=b.PRD_ID and prd.DEL_FLAG=0 ")
		   .append(" WHERE a.BILL_TYPE = '终端退货' ");
		
		/********** 查询条件拼接 start **********/
		if(!StringUtil.isEmpty(TERM_NO)){
			sql.append(" and a.TERM_NO = '").append(TERM_NO).append("'");
		}
		
		if(!StringUtil.isEmpty(CUST_NAME)){
			sql.append(" and a.CUST_NAME like '%").append(CUST_NAME).append("%'");
		}
		if(!StringUtil.isEmpty(TEL)){
			sql.append(" and a.TEL like '%").append(TEL).append("%'");
		}
		if (!StringUtil.isEmpty(PAR_PRD_NO)) {
			sql.append(StringUtil.creCon("prd.PAR_PRD_NO", PAR_PRD_NO, ","));
		}
		if (!StringUtil.isEmpty(PAR_PRD_NAME)) {
			sql.append(StringUtil.creCon("prd.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(TERM_NAME)){
			sql.append(" and a.TERM_NAME like '%").append(TERM_NAME).append("%'");
		}
		if(!StringUtil.isEmpty(ADVC_ORDER_NO)){
			sql.append(" and a.ADVC_ORDER_NO = '").append(ADVC_ORDER_NO).append("'");
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			sql.append(" and b.PRD_NO = '").append(PRD_NO).append("'");
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			sql.append(" and b.PRD_NAME = '%").append(PRD_NAME).append("%'");
		}
		if(!StringUtil.isEmpty(START_SALE_DATE)){
			sql.append(" and a.SALE_DATE >= to_date('").append(START_SALE_DATE).append("','yyyy-mm-dd')");
		}
		if(!StringUtil.isEmpty(END_SALE_DATE)){
			sql.append(" and a.SALE_DATE <= to_date('").append(END_SALE_DATE).append("','yyyy-mm-dd')");
		}
		if(!StringUtil.isEmpty(START_RT_DATE)){
			sql.append(" and a.CRE_TIME >= to_date('").append(START_RT_DATE).append("','yyyy-mm-dd')");
		}
		if(!StringUtil.isEmpty(END_RT_DATE)){
			sql.append(" and a.CRE_TIME <= to_date('").append(END_RT_DATE).append("','yyyy-mm-dd')");
		}
		sql.append(" and a.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("'");
		request.setAttribute("conDition", "rptSql=" + sql.toString());
		request.setAttribute("rptModel", "returnCount.raq");
		request.setAttribute("printModel", "returnCount.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	
	}

	/**
	 * 报表——调拨表统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAllocatecountResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		UserBean userBean = ParamUtil.getUserBean(request);
		String LEDGER_ID = userBean.getLoginZTXXID();
		
	  	String ALLO_DATE = ParamUtil.get(request, "ALLO_DATE");
		String ALLOC_OUT_STORE_NO = ParamUtil.get(request, "ALLOC_OUT_STORE_NO");
		String ALLOC_OUT_STORE_NAME = ParamUtil.get(request, "ALLOC_OUT_STORE_NAME");		
		String DEF_STORE_NO = ParamUtil.get(request, "DEF_STORE_NO");
		String DEF_STORE_NAME = ParamUtil.get(request, "DEF_STORE_NAME");		
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");		
		
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" where t.state = '审核通过' and t.del_flag = 0 ");
		if (!StringUtil.isEmpty(LEDGER_ID)) {
			condition.append(" and t.LEDGER_ID='" + LEDGER_ID + "'");
		}
		if (!StringUtil.isEmpty(ALLO_DATE)) {
			condition.append(" and to_char(t.CRE_TIME,'YYYY-MM-DD')='" + ALLO_DATE
					+ "'");
		}		
		if (!StringUtil.isEmpty(ALLOC_OUT_STORE_NO)) {
			condition.append(" and t.ALLOC_OUT_STORE_NO='" + ALLOC_OUT_STORE_NO + "'");
		}		
		if (!StringUtil.isEmpty(ALLOC_OUT_STORE_NAME)) {
			condition.append(" and t.ALLOC_OUT_STORE_NAME like '%" + ALLOC_OUT_STORE_NAME + "%'");
		}
		if (!StringUtil.isEmpty(DEF_STORE_NO)) {
			condition.append(" and n.DEF_STORE_NO='" + DEF_STORE_NO + "'");
		}
		if (!StringUtil.isEmpty(DEF_STORE_NAME)) {
			condition.append(" and n.def_store_name like '%" + DEF_STORE_NAME + "%'");
		}
		if (!StringUtil.isEmpty(PRD_NO)) {
			condition.append(" and d.prd_no='" + PRD_NO + "'");
		}
		if (!StringUtil.isEmpty(PRD_NAME)) {
			condition.append(" and d.prd_name like '%" + PRD_NAME + "%'");
		}
		
		AllocatecountService allocatecountService =  (AllocatecountService) SpringContextUtil.
		getBean("rAllocatecountService");

		String rptSql = allocatecountService.createSql(condition.toString());

		request.setAttribute("conDition", "rptSql=" + rptSql);
		request.setAttribute("rptModel", "allocatecount.raq");
		request.setAttribute("printModel", "allocatecount.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	}
	
	/**
	 * 冻结库存明细报表
	 * Description :
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toFreezeStockDtlResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		String START_SALE_DATE = ParamUtil.get(request, "START_SALE_DATE");
		String END_SALE_DATE = ParamUtil.get(request, "END_SALE_DATE"); 
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME"); 
		String PRD_NO = ParamUtil.get(request, "PRD_NO"); 
		String PAR_PRD_NO= ParamUtil.get(request, "PAR_PRD_NO"); 
		String PAR_PRD_NAME= ParamUtil.get(request, "PAR_PRD_NAME"); 
		StringBuffer sql = new StringBuffer();
//		sql.append(" select rownum, a.TERM_NAME,a.ADVC_ORDER_NO,a.SALE_PSON_NAME,a.SALE_DATE,b.Prd_NO,b.PRD_NAME,b.PRD_SPEC,b.FREEZE_NUM ")
//		   .append(" from DRP_ADVC_ORDER a ")
//		   .append(" left join DRP_ADVC_ORDER_DTL b  on a.ADVC_ORDER_ID = b.ADVC_ORDER_ID  ")
//		   .append(" where NVL(b.FREEZE_NUM, 0) > 0")
//		   .append(" and b.IS_FREEZE_FLAG = 1")
//		   .append(" and a.DEL_FLAG = 0")
//		   .append(" and a.STATE <> '未提交'")
//		   .append(" and b.ADVC_ORDER_DTL_ID not in")
//           .append(" (select dt.ADVC_SEND_REQ_DTL_ID  from DRP_ADVC_SEND_REQ_DTL dt  where NVL(dt.FREEZE_NUM, 0) > 0 and dt.DEL_FLAG = 0)")
//           .append(" and a.LEDGER_ID = '")
//           .append(LEDGER_ID).append("' ");
//		
//		/********** 查询条件拼接 start **********/
//		if(!StringUtil.isEmpty(START_SALE_DATE)){
//			sql.append(" and a.SALE_DATE >= to_date('").append(START_SALE_DATE).append("','yyyy-mm-dd')");
//		}
//		if(!StringUtil.isEmpty(END_SALE_DATE)){
//			sql.append(" and a.SALE_DATE <= to_date('").append(END_SALE_DATE).append("','yyyy-mm-dd')");
//		}
//		if(!StringUtil.isEmpty(PRD_NO)){
//			sql.append(" and b.PRD_NO like '%").append(PRD_NO).append("%'");
//		}
//		if(!StringUtil.isEmpty(PRD_NAME)){
//			sql.append(" and b.PRD_NAME like '%").append(PRD_NAME).append("%'");
//		}
//		
//		sql.append(" union all ")
		sql.append(" select rownum, tt.TERM_NAME,tt.STOREOUT_NO ADVC_ORDER_NO,tt.SALE_PSON_NAME,tt.SALE_DATE,dt.PRD_NO,dt.PRD_NAME,dt.PRD_SPEC,dt.FREEZE_NUM")
		.append(" from DRP_STOREOUT tt, DRP_STOREOUT_DTL dt,BASE_PRODUCT prd  ")
		.append(" where tt.storeout_id = dt.storeout_id and dt.PRD_ID=prd.PRD_ID ")
		.append(" and tt.DEL_FLAG = 0 ")
		.append(" and prd.DEL_FLAG=0 ")
		.append(" and dt.DEL_FLAG = 0 ");
		if(!StringUtil.isEmpty(START_SALE_DATE)){
			sql.append(" and tt.SALE_DATE >= to_date('").append(START_SALE_DATE).append("','yyyy-mm-dd')");
		}
		if(!StringUtil.isEmpty(END_SALE_DATE)){
			sql.append(" and tt.SALE_DATE <= to_date('").append(END_SALE_DATE).append("','yyyy-mm-dd')");
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			sql.append(" and dt.PRD_NO like '%").append(PRD_NO).append("%'");
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			sql.append(" and dt.PRD_NAME like '%").append(PRD_NAME).append("%'");
		}
		
		if (!StringUtil.isEmpty(PAR_PRD_NO)) {
			sql.append(StringUtil.creCon("prd.PAR_PRD_NO", PAR_PRD_NO, ","));
		}
		if (!StringUtil.isEmpty(PAR_PRD_NAME)) {
			sql.append(StringUtil.creCon("prd.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		
		sql.append(" and tt.STATE = '未处理'")
		.append(" and tt.LEDGER_ID = '").append(userBean.getLoginZTXXID()).append("' ");
		
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql.toString());
		request.setAttribute("rptModel", "freezeStockDtl.raq");
		request.setAttribute("printModel", "freezeStockDtl.raq");
		return mapping.findForward("pageResult");
}
	
	/**
	 * 信用报表
	 * Description :
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toCreditPageResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer sql = new StringBuffer();
		
		String CHANNID = ParamUtil.get(request, "CHANNID");//取分销的渠道
		String CHANN_NO  = ParamUtil.get(request, "CHANN_NO");//取分销的渠道
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");//取分销的渠道
		String ZTXXID = userBean.getLoginZTXXID();
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		CHANNID = ZTXXID;
		if(!"1".equals(IS_DRP_LEDGER)){//总部
			sql.append(" select b.chann_no,b.chann_name,nvl(b.INI_CREDIT,0) INI_CREDIT,(nvl(b.CREDIT_CURRT,0)-nvl(b.INI_CREDIT,0)) CREDIT_CURRT,nvl(b.FREEZE_CREDIT,0) FREEZE_CREDIT,nvl(b.REBATE_CURRT,0) REBATE_CURRT,nvl(b.PAYMENT_CREDIT,0) PAYMENT_CREDIT,nvl(temp.TEMP_CREDITE,0) TEMP_CREDITE, ")
			   .append(" (nvl(b.CREDIT_CURRT,0) + nvl(temp.TEMP_CREDITE,0) + nvl(b.PAYMENT_CREDIT,0) + nvl(b.REBATE_CURRT,0) - nvl(b.FREEZE_CREDIT,0)) book_credite,  ")
			   .append(" (nvl(b.CREDIT_CURRT,0) + nvl(temp.TEMP_CREDITE,0) + nvl(b.REBATE_CURRT,0) - nvl(b.FREEZE_CREDIT,0)) send_credite ")
			   .append(" from base_chann b ")
			   .append(" left join (select sum(TEMP_CREDIT_REQ) TEMP_CREDITE ,CHANN_ID")
			   .append(" 		from ERP_TEMP_CREDIT_REQ req ")
			   .append("     	where req.TEMP_CREDIT_VALDT >= to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') and req.STATE = '审核通过' and req.DEL_FLAG = 0 group by req.CHANN_ID ) ").append(" temp on b.chann_id = temp.CHANN_ID ")
			   .append("  where b.del_flag = 0 and b.state = '启用' ");
			
			String CHANN_ID = ParamUtil.get(request, "CHANN_ID");//取总部查询条件
			if(!StringUtil.isEmpty(CHANN_ID)){
				sql.append(" and b.CHANN_ID = '").append(CHANN_ID).append("'");
			}else{
				if(!StringUtil.isEmpty(CHANN_NO))
				{
					sql.append(" and b.CHANN_NO  like '%").append(CHANN_NO).append("%'");
				}
				if(!StringUtil.isEmpty(CHANN_NAME))
				{
					sql.append(" and b.CHANN_NAME  like '%").append(CHANN_NAME).append("%'");
				}
				
				sql.append(" and b.CHANN_ID in ").append("(select CHANN_ID from BASE_USER_CHANN_CHRG where USER_ID = '").append(userBean.getXTYHID()).append("')  ");
			
			}
			
		}else{//分销
			sql.append(" select b.chann_no,b.chann_name,nvl(b.INI_CREDIT,0) INI_CREDIT,(nvl(b.CREDIT_CURRT,0)-nvl(b.INI_CREDIT,0)) CREDIT_CURRT,nvl(b.FREEZE_CREDIT,0) FREEZE_CREDIT,nvl(b.REBATE_CURRT,0) REBATE_CURRT,nvl(b.PAYMENT_CREDIT,0) PAYMENT_CREDIT,nvl(temp.TEMP_CREDITE,0) TEMP_CREDITE, ")
			   .append(" (nvl(b.CREDIT_CURRT,0) + nvl(temp.TEMP_CREDITE,0) + nvl(b.PAYMENT_CREDIT,0) + nvl(b.REBATE_CURRT,0) - nvl(b.FREEZE_CREDIT,0)) book_credite,  ")
			   .append(" (nvl(b.CREDIT_CURRT,0) + nvl(temp.TEMP_CREDITE,0) + nvl(b.REBATE_CURRT,0) - nvl(b.FREEZE_CREDIT,0)) send_credite ")
			   .append(" from base_chann b ")
			   .append(" join (select sum(TEMP_CREDIT_REQ) TEMP_CREDITE ")
			   .append(" 		from ERP_TEMP_CREDIT_REQ req ")
			   .append("     	where req.TEMP_CREDIT_VALDT >= to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') and req.STATE = '审核通过' and req.DEL_FLAG = 0 and req.CHANN_ID = '").append(CHANNID).append("') temp on 1=1 ")
			   .append("  where b.del_flag = 0 and b.state = '启用' and CHANN_ID = '").append(CHANNID).append("' ");
		}
		
		System.out.println(sql.toString());
		   
		/********** 查询条件拼接 start **********/
		
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql.toString());
		request.setAttribute("rptModel", "credit.raq");
		request.setAttribute("printModel", "credit.raq");
		return mapping.findForward("pageResult");
	}
	
	
	 /**
	  * 发货情况统计表
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toQueryDeliver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		String DELIVER_ORDER_NO = ParamUtil.get(request, "DELIVER_ORDER_NO");//发运单编号
		String ADVC_SEND_DATE_BENG=ParamUtil.get(request, "ADVC_SEND_DATE_BENG");//发货日期从
		String ADVC_SEND_DATE_END=ParamUtil.get(request, "ADVC_SEND_DATE_END");//发货日期到
		String DELIVER_TYPE=ParamUtil.get(request, "DELIVER_TYPE");//发货方式
		String ORDER_CHANN_NO=ParamUtil.get(request, "ORDER_CHANN_NO");//客户编号
		String ORDER_CHANN_NAME=ParamUtil.get(request, "ORDER_CHANN_NAME");//客户名称
		String RECV_ADDR_NO=ParamUtil.get(request, "RECV_ADDR_NO");//收货地址编号
		String SALE_ORDER_NOS=ParamUtil.get(request, "SALE_ORDER_NO");//订单编号
		String JOIN_DELIVER_ORDER_NO=ParamUtil.get(request, "JOIN_DELIVER_ORDER_NO");//出货计划号
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME");//战区
		String RECV_CHANN_NO = ParamUtil.get(request, "RECV_CHANN_NO");//收货方编号
		String RECV_CHANN_NAME = ParamUtil.get(request, "RECV_CHANN_NAME");//收货方名称
		String PROV=ParamUtil.get(request, "PROV");//省份
		String SHIP_POINT_NAME=ParamUtil.get(request, "SHIP_POINT_NAME");//所属生产基地
		String PRD_GROUP_NAME = ParamUtil.get(request, "PRD_GROUP_NAME");//货品组
		String AREA_MANAGE_NAME=ParamUtil.get(request, "AREA_MANAGE_NAME");//业务员
		String PRD_TYPE_NAME=ParamUtil.get(request, "PRD_TYPE_NAME");//货品分类
		String PRD_NO=ParamUtil.get(request, "PRD_NO");//货品编号
		String PRD_NAME=ParamUtil.get(request, "PRD_NAME");//货品名称
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.DELIVER_ORDER_NO, ")
		   .append("d.SALE_ORDER_NO,")
		   .append("b.U9_SALE_ORDER_NO,")
		   .append("a.JOIN_DELIVER_ORDER_NO,")
		   .append("a.ORDER_CHANN_NO,")
		   .append("a.ORDER_CHANN_NAME,")
		   .append("a.RECV_CHANN_NO,")
		   .append("a.RECV_CHANN_NAME,")
		   .append("to_char(a.CRE_TIME, 'yyyy-MM-DD HH24:MI:SS') CRE_TIME,")
		   .append("to_char(a.ADVC_SEND_DATE, 'yyyy-mm-dd') ADVC_SEND_DATE,")
		   .append(" b.PRD_NO,")
		   .append("b.PRD_NAME,")
		   .append(" b.PRD_SPEC,")
		   .append("b.STD_UNIT,")
		   .append("e.SPCL_SPEC_REMARK,")
		   .append("b.PLAN_NUM,")
		   .append("b.REAL_SEND_NUM,")
		   .append("(b.REAL_SEND_NUM*b.VOLUME)TOT_VOLUME,")
		   .append("(b.REAL_SEND_NUM*b.DECT_PRICE)TOT_AMOUNT,")
		   .append("b.RECV_ADDR_NO,")
		   .append("b.DECT_PRICE,")
		   .append("b.NO_SEND_NUM,")
		   .append("b.VOLUME,")
		   .append("b.RECV_ADDR,")
		   .append("to_char(b.STORE_OUT_TIME,'yyyy-MM-DD HH24:MI:SS') STORE_OUT_TIME,")
		   .append("a.STATE,")
		   .append("g.AREA_NAME_P")
		   .append(" from V_DD u,ERP_DELIVER_ORDER a ")
		   .append(" left join  ERP_DELIVER_ORDER_DTL b on a.DELIVER_ORDER_ID = b.DELIVER_ORDER_ID   and b.DEL_FLAG = 0 ")
		   .append(" left join ERP_SALE_ORDER_DTL c on b.SALE_ORDER_DTL_ID=c.SALE_ORDER_DTL_ID   and c.DEL_FLAG = 0  ")
		   .append(" left join ERP_SALE_ORDER d on c.SALE_ORDER_ID=d.SALE_ORDER_ID and d.DEL_FLAG = 0 ")
		   .append(" left join DRP_SPCL_TECH e on b.SPCL_TECH_ID=e.SPCL_TECH_ID  and e.USE_FLAG=1   ")
		   .append(" left join BASE_CHANN f on f.CHANN_ID=a.ORDER_CHANN_ID and f.DEL_FLAG = 0 ")
		   .append(" left join BASE_AREA g on f.AREA_ID=g.AREA_ID and g.DEL_FLAG = 0 ")
		   .append(" left join BASE_PRODUCT h on h.PRD_ID=b.PRD_ID and h.DEL_FLAG=0")
		   .append(" where a.DEL_FLAG = 0 and u.SJZDBH='ERP_DELIVER_ORDER_STATE'   and u.SJXMC=a.STATE  and u.SJXZ>'3' ");
		/********** 查询条件拼接 start **********/
		if(!StringUtil.isEmpty(DELIVER_ORDER_NO)){
			sql.append(" and a.DELIVER_ORDER_NO like '%").append(DELIVER_ORDER_NO).append("%' ");
		}
		if(!StringUtil.isEmpty(DELIVER_TYPE)){
			sql.append(" and a.DELIVER_TYPE ='").append(DELIVER_TYPE).append("' ");
		}
		if(!StringUtil.isEmpty(ORDER_CHANN_NO)){
			sql.append(StringUtil.creCon("a.ORDER_CHANN_NO", ORDER_CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(ORDER_CHANN_NAME)){
			sql.append(StringUtil.creCon("a.ORDER_CHANN_NAME", ORDER_CHANN_NAME, ","));
		}
		if (!StringUtil.isEmpty(AREA_NAME)) {
			sql.append(StringUtil.creCon("g.AREA_NAME_P", AREA_NAME, ","));
		}
		if (!StringUtil.isEmpty(PRD_GROUP_NAME)) {
			//拼装 like sql
			String sqlStr = StringUtil.creCon("gg.PRD_GROUP_NAME", PRD_GROUP_NAME, ",");
			sql.append(" and b.PRD_ID in (select  distinct gd.PRD_ID   from BASE_PRD_GROUP gg, BASE_PRD_GROUP_DTL gd where gd.PRD_GROUP_ID = gg.PRD_GROUP_ID ");
			sql.append(sqlStr);
			sql.append(" ) ");
		}
		if(!StringUtil.isEmpty(RECV_CHANN_NO)){
			sql.append(StringUtil.creCon("a.RECV_CHANN_NO", RECV_CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(RECV_CHANN_NAME)){
			sql.append(StringUtil.creCon("a.RECV_CHANN_NAME", RECV_CHANN_NAME, ","));
		}
		if(!StringUtil.isEmpty(PROV)){
			sql.append(StringUtil.creCon("f.PROV", PROV, ","));
		}
		if(!StringUtil.isEmpty(AREA_MANAGE_NAME)){
			sql.append(" and f.AREA_MANAGE_NAME like '%").append(AREA_MANAGE_NAME).append("%'");
		}
		if(!StringUtil.isEmpty(SHIP_POINT_NAME)){
			sql.append(" and f.SHIP_POINT_NAME like '%").append(SHIP_POINT_NAME).append("%'");
		}
		
		if(!StringUtil.isEmpty(RECV_ADDR_NO)){
			sql.append(" and a.RECV_ADDR_NO like '%").append(RECV_ADDR_NO).append("%' ");
		}
		if(!StringUtil.isEmpty(ADVC_SEND_DATE_BENG)){
			sql.append(" and a.ADVC_SEND_DATE >=to_date('").append(ADVC_SEND_DATE_BENG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(ADVC_SEND_DATE_END)){
			sql.append(" and a.ADVC_SEND_DATE <=to_date('").append(ADVC_SEND_DATE_END).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(SALE_ORDER_NOS)){
			sql.append(StringUtil.creCon("d.SALE_ORDER_NO", SALE_ORDER_NOS, ","));
		}
		if(!StringUtil.isEmpty(JOIN_DELIVER_ORDER_NO)){
			sql.append(" and a.JOIN_DELIVER_ORDER_NO like '%").append(JOIN_DELIVER_ORDER_NO).append("%' ");
		}
		if(!StringUtil.isEmpty(PRD_TYPE_NAME)){
			sql.append(StringUtil.creEqualCon("h.PAR_PRD_NAME", PRD_TYPE_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			sql.append(StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			sql.append(StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
		}
		System.out.println(sql.toString());
		request.setAttribute("conDition", "rptSql=" + sql.toString());
		request.setAttribute("rptModel", "deliver.raq");
		request.setAttribute("printModel", "deliver.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
}    
	
	
	/**
	  * 新发货情况统计表
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toQueryNewDeliver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME");//战区
		String PRDC_POINT_NAME=ParamUtil.get(request, "PRDC_POINT_NAME");//生产基地
		String SHIP_POINT_NAME=ParamUtil.get(request, "SHIP_POINT_NAME");//发货基地
		String STOREOUT_TIME_BENG=ParamUtil.get(request, "STOREOUT_TIME_BENG");//出货时间从
		String STOREOUT_TIME_END=ParamUtil.get(request, "STOREOUT_TIME_END");//出货时间到
		String PRD_TYPE_NAME=ParamUtil.get(request, "PRD_TYPE_NAME");//货品分类
		String PRD_GROUP_NAME = ParamUtil.get(request, "PRD_GROUP_NAME");//货品组
		String PRD_NO=ParamUtil.get(request, "PRD_NO");//货品编号
		String PRD_NAME=ParamUtil.get(request, "PRD_NAME");//货品名称
		String DELIVER_TYPE=ParamUtil.get(request, "DELIVER_TYPE");//发货方式
		String SALE_ORDER_NOS=ParamUtil.get(request, "SALE_ORDER_NO");//订单编号
		String RECV_ADDR_NO=ParamUtil.get(request, "RECV_ADDR_NO");//收货地址编号
		String ORDER_CHANN_NO=ParamUtil.get(request, "ORDER_CHANN_NO");//客户编号
		String ORDER_CHANN_NAME=ParamUtil.get(request, "ORDER_CHANN_NAME");//客户名称
		String RECV_CHANN_NO = ParamUtil.get(request, "RECV_CHANN_NO");//收货方编号
		String RECV_CHANN_NAME = ParamUtil.get(request, "RECV_CHANN_NAME");//收货方名称
		String PROV = ParamUtil.get(request, "PROV");
		String BILL_TYPE = ParamUtil.get(request, "BILL_TYPE");
		
		StringBuffer sql = new StringBuffer();//查询SQL
		StringBuffer commSql=new StringBuffer();//表连接SQL
		StringBuffer sumSql=new StringBuffer();//合计SQL
		StringBuffer querySql=new StringBuffer();//查询条件
		sql.append("rptSql= select row_number() over (order by a.STOREOUT_TIME ASC) ROW_INDEX,a.STOREOUT_NO, ")
		   .append("a.DELIVER_ORDER_NO,")
		   .append("d.SALE_ORDER_NO,")
		   .append("c.U9_SALE_ORDER_NO,")
		   .append("d.SHIP_POINT_NAME,")
		   .append("c.PRDC_POINT_NAME,")
//		   .append("g.AREA_NAME_P,")
		   .append("d.ORDER_CHANN_NO,")
		   .append("d.ORDER_CHANN_NAME,")
//		   .append("a.RECV_CHANN_NO,")
//		   .append("a.RECV_CHANN_NAME,")
		   .append("d.BILL_TYPE,")
		   .append("d.RECV_CHANN_NO,")
		   .append("d.RECV_CHANN_NAME,")
		   .append("to_char(a.STOREOUT_TIME, 'yyyy-MM-DD HH24:MI:SS') STOREOUT_TIME,")
		   .append("b.PRD_NO,")
		   .append(" b.PRD_NAME,")
		   .append("h.PAR_PRD_NAME,")
		   .append("b.PRD_SPEC,")
		   .append("b.STD_UNIT,")
		   .append("e.SPCL_DTL_REMARK SPCL_SPEC_REMARK,")
		   .append("b.STOREOUT_NUM,")
		   .append(" b.DECT_PRICE,")
		   .append("decode(c.IS_FREE_FLAG, 1, 0, b.DECT_AMOUNT) DECT_AMOUNT,")
		   .append("b.VOLUMES,")
		   .append("(b.VOLUMES/b.STOREOUT_NUM) VOLUME,")
		   .append("d.RECV_ADDR_NO,")
		   .append("d.RECV_ADDR,")
		   .append("d.WAREA_NAME AREA_NAME_P,")
		   .append("f.PROV,f.AREA_MANAGE_NAME,d.CRE_NAME ");
	sumSql.append("sumSql=select sum(b.STOREOUT_NUM) ALL_STOREOUT_NUM,")
	      .append("sum(decode(c.IS_FREE_FLAG, 1, 0, b.DECT_AMOUNT)) ALL_DECT_AMOUNT,")
	      .append("sum(b.VOLUMES) ALL_VOLUMES ");
	commSql.append(" from ERP_STOREOUT a ")
		   .append(" left join ERP_STOREOUT_PRD_DTL b on a.STOREOUT_ID = b.STOREOUT_ID ")
		   .append(" left join ERP_SALE_ORDER_DTL c on b.SALE_ORDER_DTL_ID = c.SALE_ORDER_DTL_ID ")
		   .append(" left join ERP_SALE_ORDER d on c.SALE_ORDER_ID = d.SALE_ORDER_ID ")
		   .append(" left join DRP_SPCL_TECH e on c.SPCL_TECH_ID = e.SPCL_TECH_ID ")
		   .append(" left join BASE_CHANN f on f.CHANN_ID = d.ORDER_CHANN_ID and f.DEL_FLAG=0 ")//订货客户
		   .append(" left join BASE_CHANN j on  j.CHANN_ID=d.RECV_CHANN_ID and j.DEL_FLAG=0 ")//收货客户
//		   .append(" left join BASE_AREA g on f.AREA_ID = g.AREA_ID and g.DEL_FLAG = 0 ")
		   .append(" left join BASE_PRODUCT h on h.PRD_ID = b.PRD_ID and h.DEL_FLAG = 0 ")
		   .append(" left join ERP_DELIVER_ORDER i on i.DELIVER_ORDER_ID=a.DELIVER_ORDER_ID ")
		   .append(" where 1=1 and a.STATE!='关闭' and a.DEL_FLAG = 0 and b.DEL_FLAG = 0 ");
		
		/********** 查询条件拼接 start **********/
		if(BusinessConsts.YJLBJ_FLAG_FALSE.equals(userBean.getIS_DRP_LEDGER())){
			querySql.append("and f.CHANN_ID in ").append(userBean.getCHANNS_CHARG());
		}
		if(!StringUtil.isEmpty(DELIVER_TYPE)){
			querySql.append(" and i.DELIVER_TYPE ='").append(DELIVER_TYPE).append("' ");
		}
		if(!StringUtil.isEmpty(ORDER_CHANN_NO)){
			querySql.append(StringUtil.creCon("d.ORDER_CHANN_NO,f.CHANN_NO", ORDER_CHANN_NO, ","));
			
		}
		if(!StringUtil.isEmpty(ORDER_CHANN_NAME)){
			querySql.append(StringUtil.creCon("d.ORDER_CHANN_NAME,f.CHANN_NAME", ORDER_CHANN_NAME, ","));
		}
		if (!StringUtil.isEmpty(AREA_NAME)) {
			querySql.append(StringUtil.creCon("d.WAREA_NAME", AREA_NAME, ","));
		}
		if (!StringUtil.isEmpty(PRD_GROUP_NAME)) {
			//拼装 like sql
			String sqlStr = StringUtil.creCon("gg.PRD_GROUP_NAME", PRD_GROUP_NAME, ",");
			querySql.append(" and b.PRD_ID in (select  distinct gd.PRD_ID   from BASE_PRD_GROUP gg, BASE_PRD_GROUP_DTL gd where gd.PRD_GROUP_ID = gg.PRD_GROUP_ID ");
			querySql.append(sqlStr);
			querySql.append(" ) ");
		}
		if(!StringUtil.isEmpty(RECV_CHANN_NO)){
			querySql.append(StringUtil.creCon("d.RECV_CHANN_NO,j.CHANN_NO", RECV_CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(RECV_CHANN_NAME)){
			querySql.append(StringUtil.creCon("d.RECV_CHANN_NAME,j.CHANN_NAME", RECV_CHANN_NAME, ","));
		}
		
		if(!StringUtil.isEmpty(PRDC_POINT_NAME)){
			querySql.append(" and c.PRDC_POINT_NAME like '%").append(PRDC_POINT_NAME).append("%'");
		}
		
		if(!StringUtil.isEmpty(SHIP_POINT_NAME)){
			querySql.append(" and d.SHIP_POINT_NAME like '%").append(SHIP_POINT_NAME).append("%'");
		}
		
		if(!StringUtil.isEmpty(RECV_ADDR_NO)){
			querySql.append(" and d.RECV_ADDR_NO like '%").append(RECV_ADDR_NO).append("%' ");
		}
		if(!StringUtil.isEmpty(STOREOUT_TIME_BENG)){
//			sql.append(" and a.STOREOUT_TIME >=to_date('").append(STOREOUT_TIME_BENG).append("','yyyy-MM-DD  HH24:MI:SS') ");
			querySql.append(" and to_date(to_char(a.STOREOUT_TIME,'yyyy-MM-DD'),'yyyy-MM-DD') >=to_date('").append(STOREOUT_TIME_BENG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(STOREOUT_TIME_END)){
			querySql.append(" and to_date(to_char(a.STOREOUT_TIME,'yyyy-MM-DD'),'yyyy-MM-DD') <=to_date('").append(STOREOUT_TIME_END).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(SALE_ORDER_NOS)){
			querySql.append(StringUtil.creCon("d.SALE_ORDER_NO", SALE_ORDER_NOS, ","));
		}
		if(!StringUtil.isEmpty(PRD_TYPE_NAME)){
			querySql.append(StringUtil.creEqualCon("h.PAR_PRD_NAME", PRD_TYPE_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			querySql.append(StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			querySql.append(StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PROV)){
			 String tempSql = StringUtil.creCon("f.PROV", PROV, ",");
			 querySql.append(tempSql);
		}
		
		if(!StringUtil.isEmpty(BILL_TYPE)){
			querySql.append(" and d.BILL_TYPE='"+BILL_TYPE+"'");
		}
		sql.append(commSql).append(querySql).append(" order by a.STOREOUT_TIME ASC; ").append(sumSql).append(commSql).append(querySql);
		request.setAttribute("conDition", sql.toString());
		request.setAttribute("rptModel", "newDeliver.raq");
		request.setAttribute("printModel", "newDeliver.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
}
	
	

	/**
	 * 查询结果报表 Description :
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toStoreRepertory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String STORE_NO = ParamUtil.get(request, "STORE_NO");
		String STORE_NAME = ParamUtil.get(request, "STORE_NAME");
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String PRD_TYPE_NAME = ParamUtil.get(request, "PRD_TYPE_NAME");
		String PAR_PRD_NO = ParamUtil.get(request, "PAR_PRD_NO");
		String PAR_PRD_NAME = ParamUtil.get(request, "PAR_PRD_NAME");
		String COST_FLAG=ParamUtil.get(request, "COST_FLAG");
		UserBean userBean = ParamUtil.getUserBean(request);
		
		
		boolean STORE_NO_FLAG=true;
		boolean STORE_NAME_FLAG=true;
		boolean PRD_NO_FLAG=true;
		boolean PRD_NAME_FLAG=true;
		boolean PAR_PRD_NAME_FLAG=true;
		boolean PRD_SPEC_FLAG=true;
		boolean DM_SPCL_TECH_NO_FLAG=true;
		boolean SPCL_DTL_REMARK_FLAG=true;
		StringBuffer repoetShowStr=new StringBuffer();
		//显示字段
		StringBuffer showStr=new StringBuffer();
		
		String STORE_NO_SHOW=ParamUtil.get(request, "STORE_NO_SHOW");
		if(!StringUtil.isEmpty(STORE_NO_SHOW)){
			showStr.append("s.STORE_NO,");
			repoetShowStr.append("STORE_NO=ds1.select(STORE_NO);");
			STORE_NO_FLAG=false;
		}
		String STORE_NAME_SHOW=ParamUtil.get(request, "STORE_NAME_SHOW");
		if(!StringUtil.isEmpty(STORE_NAME_SHOW)){
			showStr.append("s.STORE_NAME,");
			repoetShowStr.append("STORE_NAME=ds1.select(STORE_NAME);");
			STORE_NAME_FLAG=false;
		}
		String PRD_NO_SHOW=ParamUtil.get(request, "PRD_NO_SHOW");
		if(!StringUtil.isEmpty(PRD_NO_SHOW)){
			showStr.append("p.PRD_NO,");
			repoetShowStr.append("PRD_NO=ds1.select(PRD_NO);");
			PRD_NO_FLAG=false;
		}
		String PRD_NAME_SHOW=ParamUtil.get(request, "PRD_NAME_SHOW");
		if(!StringUtil.isEmpty(PRD_NAME_SHOW)){
			showStr.append("p.PRD_NAME,");
			repoetShowStr.append("PRD_NAME=ds1.select(PRD_NAME);");
			PRD_NAME_FLAG=false;
		}
		String PRD_SPEC_SHOW=ParamUtil.get(request, "PRD_SPEC_SHOW");
		if(!StringUtil.isEmpty(PRD_SPEC_SHOW)){
			showStr.append("p.PRD_SPEC,");
			repoetShowStr.append("PRD_SPEC=ds1.select(PRD_SPEC);");
			PRD_SPEC_FLAG=false;
		}
		String PAR_PRD_NAME_SHOW=ParamUtil.get(request, "PAR_PRD_NAME_SHOW");
		if(!StringUtil.isEmpty(PAR_PRD_NAME_SHOW)){
			showStr.append("p.PAR_PRD_NAME,");
			repoetShowStr.append("PAR_PRD_NAME=ds1.select(PAR_PRD_NAME);");
			PAR_PRD_NAME_FLAG=false;
		}
		String DM_SPCL_TECH_NO_SHOW=ParamUtil.get(request, "DM_SPCL_TECH_NO_SHOW");
		if(!StringUtil.isEmpty(DM_SPCL_TECH_NO_SHOW)){
			showStr.append("b.DM_SPCL_TECH_NO,");
			repoetShowStr.append("DM_SPCL_TECH_NO=ds1.select(DM_SPCL_TECH_NO);");
			DM_SPCL_TECH_NO_FLAG=false;
		}
		String SPCL_DTL_REMARK_SHOW=ParamUtil.get(request, "SPCL_DTL_REMARK_SHOW");
		if(!StringUtil.isEmpty(SPCL_DTL_REMARK_SHOW)){
			showStr.append("b.SPCL_DTL_REMARK,");
			repoetShowStr.append("SPCL_DTL_REMARK=ds1.select(SPCL_DTL_REMARK);");
			SPCL_DTL_REMARK_FLAG=false;
		}
		repoetShowStr.append("STORE_NO_FLAG=").append(STORE_NO_FLAG).append(";")
					 .append("STORE_NAME_FLAG=").append(STORE_NAME_FLAG).append(";")
					 .append("PRD_NO_FLAG=").append(PRD_NO_FLAG).append(";")
					 .append("PRD_NAME_FLAG=").append(PRD_NAME_FLAG).append(";")
					 .append("PAR_PRD_NAME_FLAG=").append(PAR_PRD_NAME_FLAG).append(";")
					 .append("PRD_SPEC_FLAG=").append(PRD_SPEC_FLAG).append(";")
					 .append("DM_SPCL_TECH_NO_FLAG=").append(DM_SPCL_TECH_NO_FLAG).append(";")
					 .append("SPCL_DTL_REMARK_FLAG=").append(SPCL_DTL_REMARK_FLAG).append(";");
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" where s.DEL_FLAG=0 ");
 
//		condition.append(" and s.LEDGER_ID='" + ZTXXID + "'");
//		condition.append(" and s.TERM_STROE_FLAG=0 ");
//		if (!StringUtil.isEmpty(STORE_ID)) {
//			condition.append(StringUtil.creCon("s.STORE_ID", STORE_ID, ","));
//		}
		
		if (!StringUtil.isEmpty(STORE_NO)) {
			condition.append(StringUtil.creCon("s.STORE_NO", STORE_NO, ","));
		}
		if (!StringUtil.isEmpty(STORE_NAME)) {
			condition.append(StringUtil.creCon("s.STORE_NAME", STORE_NAME, ","));
		}
		if (!StringUtil.isEmpty(PAR_PRD_NO)) {
			condition.append(StringUtil.creEqualCon("p.PAR_PRD_NO", PAR_PRD_NO, ","));
		}
		if (!StringUtil.isEmpty(PAR_PRD_NAME)) {
			condition.append(StringUtil.creEqualCon("p.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		if (!StringUtil.isEmpty(CHANN_NO)) {
			condition.append(StringUtil.creEqualCon("c.CHANN_NO", CHANN_NO, ","));
		}
		if (!StringUtil.isEmpty(CHANN_NAME)) {
			condition.append(StringUtil.creEqualCon("c.CHANN_NAME", CHANN_NAME, ","));
		}
		if (!StringUtil.isEmpty(PRD_NO)) {
			condition.append(StringUtil.creCon("p.PRD_NO", PRD_NO, ","));
		}
		if (!StringUtil.isEmpty(PRD_NAME)) {
			condition.append(StringUtil.creCon("p.PRD_NAME", PRD_NAME, ","));
		}
		
		if (!StringUtil.isEmpty(PRD_TYPE_NAME)) {
			condition.append(" and f.PRD_NAME like '%" + PRD_TYPE_NAME + "%'");
		}
		
		
		
		StoreRepertoryService storeRepertoryService = (StoreRepertoryService) SpringContextUtil
		.getBean("storeRepertoryService");
		if(!StringUtil.isEmpty(showStr.toString())){
			showStr=InterUtil.replaceUpSql(showStr);
		}
		String rptSql = "";
		if("1".equals(COST_FLAG)){
			rptSql=storeRepertoryService.createSql(condition.toString());
			request.setAttribute("rptModel", "StoreRepertoryCost.raq");
			request.setAttribute("printModel", "StoreRepertoryCost.raq");
		}else{
			rptSql=storeRepertoryService.createSql(condition.toString(),showStr);
			request.setAttribute("rptModel", "StoreRepertory.raq");
			request.setAttribute("printModel", "StoreRepertory.raq");
		}
		String aa=rptSql+";"+repoetShowStr.toString();
		System.out.println(aa);
		request.setAttribute("conDition", aa);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	}
	
	

	
	 /**
	  * 订单查询
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toQueryGoods(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");//渠道编号
		String CHANN_NAME=ParamUtil.get(request, "CHANN_NAME");//渠道名称
		String SAEL_DATE_BENG=ParamUtil.get(request, "SAEL_DATE_BENG");//销售日期从
		String SAEL_DATE_END=ParamUtil.get(request, "SAEL_DATE_END");//销售日期到
		
		
		String GOODS_ORDER_NO = ParamUtil.get(request, "GOODS_ORDER_NO");//订货订单编号
		String STATE = ParamUtil.get(request, "STATE");//状态
		//查询SQL
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select ")
		   .append(" GOODS_ORDER_NO,STATE,SAEL_DATE,ORDER_CHANN_NO,ORDER_CHANN_NAME,RECV_CHANN_NO,RECV_CHANN_NAME,PRD_NO,PRD_NAME,PRD_SPEC,")
		   .append(" PAR_PRD_NAME ,DECT_PRICE,ORDER_NUM,ORDER_AMOUNT,TOTAL_VOLUME,CREDIT_FREEZE,ADVC_NUM, PALNED_SEND_NUM,  SENDED_NUM,")
		   .append(" DELIVER_ORDER_NO,JOIN_DELIVER_ORDER_NO, FROM_BILL_NO, DELIVER_TYPE, to_char(ADVC_SEND_DATE,'YYYY-MM-DD') ADVC_SEND_DATE,SHIP_POINT_NAME")
		   .append(" from ( ") 
		   .append(" select ")
		   .append(" 1 XH,c.GOODS_ORDER_NO,'未审核' STATE,to_char(c.CRE_TIME,'YYYY-MM-DD')SAEL_DATE,c.ORDER_CHANN_NO,")
		   .append(" c.ORDER_CHANN_NAME,c.RECV_CHANN_NO, c.RECV_CHANN_NAME,d.PRD_NO,e.PRD_NAME,e.PRD_SPEC,e.PAR_PRD_NAME ,")
		   .append(" d.DECT_PRICE,d.ORDER_NUM, d.ORDER_AMOUNT,d.TOTAL_VOLUME, (NVL(d.CREDIT_FREEZE_PRICE,0)*NVL(d.ORDER_NUM,0)) CREDIT_FREEZE,  ")
		   .append(" 0 ADVC_NUM,0 PALNED_SEND_NUM, 0 SENDED_NUM,' ' DELIVER_ORDER_NO,'' JOIN_DELIVER_ORDER_NO,'' FROM_BILL_NO,")
		   .append(" '' DELIVER_TYPE,null ADVC_SEND_DATE,' 'SHIP_POINT_NAME")
		   .append(" from  ")
		   .append("  DRP_GOODS_ORDER c ,DRP_GOODS_ORDER_DTL d left join BASE_PRODUCT e on d.PRD_ID=e.PRD_ID ")
		   .append(" where  c.GOODS_ORDER_ID=d.GOODS_ORDER_ID ")
		   .append(" and c.STATE='未处理'")
		   .append(" union all ")
		   .append(" select ")
		   .append("  2 XH,a.SALE_ORDER_NO,'已审核' STATE,to_char(a.ORDER_DATE,'YYYY-MM-DD'),a.ORDER_CHANN_NO,a.ORDER_CHANN_NAME,a.RECV_CHANN_NO,")
		   .append(" a.RECV_CHANN_NAME,b.PRD_NO,e.PRD_NAME,e.PRD_SPEC,e.PAR_PRD_NAME ,b.DECT_PRICE,b.ORDER_NUM, ")
		   .append(" b.ORDER_AMOUNT,NVL(b.VOLUME,0)*NVL(b.ORDER_NUM,0) TOTAL_VOLUME, (NVL(b.CREDIT_FREEZE_PRICE,0)*NVL(b.ORDER_NUM,0)) CREDIT_FREEZE, ")
		   .append(" b.PLANED_NUM ADVC_NUM, tmp.PLAN_NUM  PALNED_SEND_NUM,tmp.REAL_SEND_NUM SENDED_NUM,tmp.DELIVER_ORDER_NO,tmp.JOIN_DELIVER_ORDER_NO,tmp.FROM_BILL_NO, ")
		   .append(" tmp.DELIVER_TYPE,tmp.ADVC_SEND_DATE,tmp.SHIP_POINT_NAME ")
		   .append(" from  ERP_SALE_ORDER  a ,ERP_SALE_ORDER_DTL b left join BASE_PRODUCT e on b.PRD_ID=e.PRD_ID ")
		   .append(" left join ")
		   .append(" ( ")
		   .append(" select ")
		   .append(" n.SALE_ORDER_DTL_ID,m.DELIVER_ORDER_NO,m.JOIN_DELIVER_ORDER_NO,m.FROM_BILL_NO,m.DELIVER_TYPE,m.ADVC_SEND_DATE,m.SHIP_POINT_NAME, ")
		   .append(" sum((case when m.STATE in ('已提交库房', '已发货', '已收货','待收货', '部分发货') then NVL(PLAN_NUM,0) else 0 end) )PLAN_NUM, ")
		   .append(" sum((case when m.STATE in ('已提交库房', '已发货', '已收货','待收货', '部分发货') then NVL(REAL_SEND_NUM,0) else 0 end) )REAL_SEND_NUM ")
		   .append(" from ERP_DELIVER_ORDER m ,ERP_DELIVER_ORDER_DTL n")
		   .append(" where  m.DELIVER_ORDER_ID =n.DELIVER_ORDER_ID ")
		   .append(" and m.DEL_FLAG=0  and n.DEL_FLAG=0")
		   .append(" group by  n.SALE_ORDER_DTL_ID,m.DELIVER_ORDER_NO,m.JOIN_DELIVER_ORDER_NO,m.FROM_BILL_NO,m.DELIVER_TYPE,m.ADVC_SEND_DATE,m.SHIP_POINT_NAME    ")
		   .append(" ) tmp on b.SALE_ORDER_DTL_ID =tmp.SALE_ORDER_DTL_ID ")
		   .append(" where a.SALE_ORDER_ID=b.SALE_ORDER_ID ")
		   .append(" and a.DEL_FLAG=0  and b.DEL_FLAG=0 )all_date  ");
		sql.append(" where 1=1 ");
		
		if(!StringUtil.isEmpty(CHANN_NO)){
			sql.append(" and ORDER_CHANN_NO like '%").append(CHANN_NO).append("%' ");
		}
		if(!StringUtil.isEmpty(CHANN_NAME)){
			sql.append(" and ORDER_CHANN_NAME like '%").append(CHANN_NAME).append("%' ");
		}
		if(!StringUtil.isEmpty(SAEL_DATE_BENG)){
			sql.append(" and to_date(SAEL_DATE,'yyyy-MM-DD') >=to_date('").append(SAEL_DATE_BENG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(SAEL_DATE_END)){
			sql.append(" and to_date(SAEL_DATE,'yyyy-MM-DD') <=to_date('").append(SAEL_DATE_END).append("','yyyy-MM-DD') ");
		}
		
		if(!StringUtil.isEmpty(GOODS_ORDER_NO)){
			String sqlq = StringUtil.creCon("GOODS_ORDER_NO", GOODS_ORDER_NO, ",");
			sql.append(sqlq);
		}
		if(!StringUtil.isEmpty(STATE)){
			sql.append(" and STATE ='"+STATE+"' ");
		}
		
		System.out.println(sql.toString());
		request.setAttribute("conDition", "rptSql=" + sql.toString());
		request.setAttribute("rptModel", "goods.raq");
		request.setAttribute("printModel", "goods.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
		return mapping.findForward("pageResult");
}
	 /**
	  * 合同执行情况表
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toQueryContract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		String LEDGER_IDS = ParamUtil.get(request, "CHANN_ID");//帐套名称
		String TERM_IDS=ParamUtil.get(request, "TERM_ID");//终端名称
		String SAEL_DATE_BENG=ParamUtil.get(request, "SAEL_DATE_BENG");//销售日期从
		String SAEL_DATE_END=ParamUtil.get(request, "SAEL_DATE_END");//销售日期到
		
		String PROMOTE_NAME = ParamUtil.get(request, "PROMOTE_NAME");//活动名称
		String CUST_NAME = ParamUtil.get(request, "CUST_NAME");//顾客
		String TEL = ParamUtil.get(request, "TEL");//电话
		
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT   rownum RNO,t1.LEDGER_ID, t1.LEDGER_NAME, t1.SALE_DATE, t1.SEND_STATE, t1.TERM_ID,t1.TERM_NAME,t1.ADVC_ORDER_NO, t1.BILL_TYPE,t1.PAYABLE_AMOUNT,t1.CUST_NAME,")
		   .append(" t1.TEL,t1.CONTRACT_NO, t1.PROMOTE_NAME,t1.ORDER_RECV_DATE,t1.PAR_PAYMENT_AMOUNT, t1.CUR_PAYMENT_AMOUNT, t1.ALL_PAYMENT_AMOUNT,")
		   .append(" t1.PAR_STOREOUT_AMOUNT, t1.CUR_STOREOUT_AMOUNT, t1.STOREOUT_AMOUNT, t1.NO_SEND_AMOUNT,t1.NO_PAYEDMENT,")
		   .append(" t1.LEFT_PAYEDMENT,t1.TOTAL_PAR_PAYMENT_AMOUNT, t1.TOTAL_CUR_PAYMENT_AMOUNT, t1.TOTAL_ALL_PAYMENT_AMOUNT,t1.TOTAL_PAR_STOREOUT_AMOUNT,")
		   .append(" t1.TOTAL_CUR_STOREOUT_AMOUNT,t1.TOTAL_STOREOUT_AMOUNT,t1.TOTAL_NO_SEND_AMOUNT, t1.TOTAL_NO_PAYEDMENT,t1.TOTAL_LEFT_PAYEDMENT")
		   .append(" FROM ")
		   .append(" (SELECT t.LEDGER_ID,t.LEDGER_NAME, t.SALE_DATE,  t.SEND_STATE,t.TERM_ID,t.TERM_NAME,t.ADVC_ORDER_NO, t.BILL_TYPE,t.PAYABLE_AMOUNT,t.CUST_NAME,t.TEL,t.CONTRACT_NO, t.PROMOTE_NAME,")
		   .append("  t.ORDER_RECV_DATE, t.PAR_PAYMENT_AMOUNT,t.CUR_PAYMENT_AMOUNT, t.ALL_PAYMENT_AMOUNT,t.PAR_STOREOUT_AMOUNT,t.CUR_STOREOUT_AMOUNT,t. STOREOUT_AMOUNT,")
		   .append("  t.NO_SEND_AMOUNT,t.NO_PAYEDMENT,t.LEFT_PAYEDMENT,SUM(t.PAR_PAYMENT_AMOUNT) over ()  TOTAL_PAR_PAYMENT_AMOUNT,SUM(t.CUR_PAYMENT_AMOUNT) over ()  TOTAL_CUR_PAYMENT_AMOUNT,")
		   .append("  SUM(t.ALL_PAYMENT_AMOUNT) over () TOTAL_ALL_PAYMENT_AMOUNT,SUM(t.PAR_STOREOUT_AMOUNT)over () TOTAL_PAR_STOREOUT_AMOUNT,SUM(t.CUR_STOREOUT_AMOUNT) over () TOTAL_CUR_STOREOUT_AMOUNT,")
		   .append("  SUM(t.STOREOUT_AMOUNT) over () TOTAL_STOREOUT_AMOUNT, SUM(t.NO_SEND_AMOUNT) over () TOTAL_NO_SEND_AMOUNT,SUM(t.NO_PAYEDMENT) over () TOTAL_NO_PAYEDMENT,SUM(t.LEFT_PAYEDMENT) over () TOTAL_LEFT_PAYEDMENT")
		   .append(" FROM (")
		   .append("select rownum RNO, ")
		   .append("a.LEDGER_ID," )
		   .append("a.LEDGER_NAME,")
		   .append("decode(a.BILL_TYPE,'终端销售', to_char(a.SALE_DATE, 'yyyy-MM-DD') ,to_char(a.return_statement_date, 'yyyy-MM-DD')) SALE_DATE,")
		   .append("a.TERM_ID,")
		   .append("a.TERM_NAME,")
		   .append("a.ADVC_ORDER_NO,")
		   .append("a.BILL_TYPE,")
		   .append("(a.PAYABLE_AMOUNT)* decode(a.BILL_TYPE, '终端销售', 1, -1) PAYABLE_AMOUNT,")
		   .append("a.CUST_NAME,")
		   .append("a.TEL,")
		   .append("a.CONTRACT_NO,")
		   .append("a.PROMOTE_NAME,")
		   .append("to_char(a.ORDER_RECV_DATE, 'yyyy-MM-DD') ORDER_RECV_DATE,")
		   .append("NVL(b.PAR_PAYMENT_AMOUNT, 0) * decode(a.BILL_TYPE, '终端销售', 1, -1) PAR_PAYMENT_AMOUNT,")
		   .append("NVL(b.CUR_PAYMENT_AMOUNT, 0) * decode(a.BILL_TYPE, '终端销售', 1, -1) CUR_PAYMENT_AMOUNT,")
		   .append("(NVL(b.PAR_PAYMENT_AMOUNT, 0) + NVL(b.CUR_PAYMENT_AMOUNT, 0)) *decode(a.BILL_TYPE, '终端销售', 1, -1) ALL_PAYMENT_AMOUNT,")
		   .append("decode(a.BILL_TYPE,'终端销售',NVL(k.PAR_STOREOUT_AMOUNT, 0),NVL(u.PAR_RETURN_AMOUNT, 0) * -1) PAR_STOREOUT_AMOUNT,")
		   .append("decode(a.BILL_TYPE,'终端销售',NVL(k.CUR_STOREOUT_AMOUNT, 0),NVL(u.CUR_RETURN_AMOUNT, 0) * -1) CUR_STOREOUT_AMOUNT,")
		   .append("decode(a.BILL_TYPE,'终端销售',NVL(k.STOREOUT_AMOUNT, 0),NVL(u.RETURN_AMOUNT, 0)*-1) STOREOUT_AMOUNT,")
		   .append("(case ")
		   .append(" when decode(a.BILL_TYPE,'终端销售',NVL(k.STOREOUT_AMOUNT, 0),NVL(u.RETURN_AMOUNT, 0)) = 0  and NVL(a.PAYABLE_AMOUNT, 0)>0 then ")
		   .append(" decode(a.BILL_TYPE,'终端销售', '未发货', '未退货') ")
		   .append(" when decode(a.BILL_TYPE,'终端销售',NVL(k.STOREOUT_AMOUNT, 0),NVL(u.RETURN_AMOUNT, 0)) >= NVL(a.PAYABLE_AMOUNT, 0) then ")
		   .append(" decode(a.BILL_TYPE,'终端销售', '已发货', '已退货') ")
		   .append(" else ")
		   .append(" decode(a.BILL_TYPE,'终端销售', '部分发货', '部分退货') ")
		   .append(" end) SEND_STATE, ")
		   .append(" (case ")
		   .append(" when decode(a.BILL_TYPE,'终端销售',NVL(k.STOREOUT_AMOUNT, 0),NVL(u.RETURN_AMOUNT, 0)) < ")
		   .append(" NVL(b.PAR_PAYMENT_AMOUNT, 0) + NVL(b.CUR_PAYMENT_AMOUNT, 0) then ")
		   .append(" NVL(b.PAR_PAYMENT_AMOUNT, 0) + NVL(b.CUR_PAYMENT_AMOUNT, 0) - ")
		   .append(" decode(a.BILL_TYPE,'终端销售',NVL(k.STOREOUT_AMOUNT, 0),NVL(u.RETURN_AMOUNT, 0)) ")
		   .append(" else ")
		   .append(" 0 ")
		   .append(" end) NO_SEND_AMOUNT, ")
		   .append(" (case ")
		   .append(" when decode(a.BILL_TYPE,'终端销售',NVL(k.STOREOUT_AMOUNT, 0),NVL(u.RETURN_AMOUNT, 0)) > ")
		   .append(" NVL(b.PAR_PAYMENT_AMOUNT, 0) + NVL(b.CUR_PAYMENT_AMOUNT, 0) then ")
		   .append(" decode(a.BILL_TYPE,'终端销售',NVL(k.STOREOUT_AMOUNT, 0),NVL(u.RETURN_AMOUNT, 0)) - NVL(b.PAR_PAYMENT_AMOUNT, 0) - ")
		   .append(" NVL(b.CUR_PAYMENT_AMOUNT, 0) ")
		   .append(" else ")
		   .append(" 0 ")
		   .append(" end)* decode(a.BILL_TYPE, '终端销售', 1, -1)  NO_PAYEDMENT, ")
		   .append(" (NVL(a.PAYABLE_AMOUNT, 0) - NVL(b.PAR_PAYMENT_AMOUNT, 0) -NVL(b.CUR_PAYMENT_AMOUNT, 0))* decode(a.BILL_TYPE, '终端销售', 1, -1)   LEFT_PAYEDMENT ")
		   .append(" from DRP_ADVC_ORDER a ")
		   .append(" left join (select sum(case ")
		   .append(" when to_char(m.STATEMENT_DATE, 'yyyy-MM-DD') < '").append(SAEL_DATE_BENG).append("' then ")
		   .append(" decode(BILL_TYPE,'销售退款',NVL(m.STATEMENTS_AMOUNT, 0) * -1,NVL(m.STATEMENTS_AMOUNT, 0)) else 0 end) PAR_PAYMENT_AMOUNT,")
		   .append(" sum(case when to_char(m.STATEMENT_DATE, 'yyyy-MM-DD') >= '").append(SAEL_DATE_BENG).append("' then ")
		   .append(" decode(BILL_TYPE,'销售退款',NVL(m.STATEMENTS_AMOUNT, 0) * -1,NVL(m.STATEMENTS_AMOUNT, 0)) else 0 end) CUR_PAYMENT_AMOUNT,")
		   .append(" m.ADVC_ORDER_ID, ")
		   .append(" sum(case when to_char(m.STATEMENT_DATE, 'yyyy-mm-dd') >='").append(SAEL_DATE_BENG).append("' and ")
		   .append(" to_char(m.STATEMENT_DATE, 'yyyy-mm-dd') <='").append(SAEL_DATE_END).append("' then 1 else 0 end) BUSS_FLAG ")
		   .append(" from DRP_STATEMENTS m ")
		   .append(" where m.STATE = '已结算' ")
		   .append(" and m.DEL_FLAG = 0 ")
		   .append(" and to_char(m.STATEMENT_DATE, 'yyyy-mm-dd') <= '").append(SAEL_DATE_END).append("' ")
		   .append(" group by m.ADVC_ORDER_ID) b on a.ADVC_ORDER_ID = b.ADVC_ORDER_ID ")
		   .append(" left join (select ")
		   .append(" sum(((case when to_char(a.DEAL_TIME, 'yyyy-mm-dd') < '"+SAEL_DATE_BENG+"' then b.real_num else 0 end) * decode(d.prd_type, '赠品', 0, b.dect_price))) PAR_STOREOUT_AMOUNT,")
		   .append(" sum(((case when to_char(a.DEAL_TIME, 'yyyy-mm-dd') >= '").append(SAEL_DATE_BENG).append("' and to_char(a.DEAL_TIME, 'yyyy-mm-dd') <= '").append(SAEL_DATE_END).append("' then b.real_num else 0 end) * decode(d.prd_type, '赠品', 0, b.dect_price))) CUR_STOREOUT_AMOUNT,")
		   .append("sum(((case when  to_char(a.DEAL_TIME, 'yyyy-mm-dd') <= '"+SAEL_DATE_END+"' then  b.real_num else  0 end ) * decode(d.prd_type, '赠品', 0, b.dect_price))) STOREOUT_AMOUNT,")
		   .append(" c.from_bill_id, ")
		   .append(" sum(case ")
		   .append(" when to_char(a.DEAL_TIME, 'yyyy-mm-dd') >= '").append(SAEL_DATE_BENG).append("' and ")
		   .append(" to_char(a.DEAL_TIME, 'yyyy-mm-dd') <= '").append(SAEL_DATE_END).append("' then ")
		   .append(" 1 else 0 end) BUSS_FLAG ")
		   .append(" from DRP_STOREOUT a,")
		   .append(" DRP_STOREOUT_DTL b,")
		   .append(" DRP_ADVC_SEND_REQ c,")
		   .append(" DRP_ADVC_SEND_REQ_DTL d ")
		   .append(" where a.STOREOUT_ID = b.STOREOUT_ID ")
		   .append(" and c.ADVC_SEND_REQ_ID = d.ADVC_SEND_REQ_ID ")
		   .append(" and b.FROM_BILL_DTL_ID = d.ADVC_SEND_REQ_DTL_ID ")
		   .append(" and a.DEL_FLAG = 0 ")
		   .append(" and b.DEL_FLAG = 0 ")
		   .append(" and c.DEL_FLAG = 0 ")
		   .append(" and d.DEL_FLAG = 0 ")
		   .append(" and a.DEAL_FLAG = 1 ")
		   .append(" and a.BILL_TYPE = '销售出库' ")
		   .append(" group by c.FROM_BILL_ID) k ")
		   .append(" on a.ADVC_ORDER_ID = k.FROM_BILL_ID ")
		   .append(" left join ")
		   .append(" ( ")
		   .append(" select  a.FROM_BILL_ID,  ")
		   .append(" sum((case when to_char(a.DEAL_TIME, 'yyyy-mm-dd') < '").append(SAEL_DATE_BENG).append("' then b.DECT_AMOUNT else 0 end)) PAR_RETURN_AMOUNT,")
		   .append(" sum((case when to_char(a.DEAL_TIME, 'yyyy-mm-dd') >= '").append(SAEL_DATE_BENG).append("' and to_char(a.DEAL_TIME, 'yyyy-mm-dd') <= '").append(SAEL_DATE_END).append("' then  b.DECT_AMOUNT  else  0 end)) CUR_RETURN_AMOUNT,")
           .append(" sum((case when to_char(a.DEAL_TIME, 'yyyy-mm-dd') <= '").append(SAEL_DATE_END).append("' then b.DECT_AMOUNT else 0 end)) RETURN_AMOUNT,")
		   
           .append(" sum(case when to_char(a.DEAL_TIME, 'yyyy-mm-dd') >='").append(SAEL_DATE_BENG).append("' and ")
		   .append(" to_char(a.DEAL_TIME, 'yyyy-mm-dd') <='").append(SAEL_DATE_END).append("' then 1 else 0 end) BUSS_FLAG ")
           
		   .append(" from ")
		   .append(" DRP_STOREIN a ,DRP_STOREIN_DTL b ")
		   .append(" where a.STOREIN_ID=b.STOREIN_ID ")
		   .append(" and a.BILL_TYPE='终端退货' ")
		   .append(" and a.Del_Flag=0 and b.Del_Flag=0 and a.Deal_Flag=1 ")
		   .append(" and to_char(a.DEAL_TIME, 'yyyy-mm-dd') <= '").append(SAEL_DATE_END).append("'")
		   .append(" group by a.FROM_BILL_ID ")
		   .append(" ) u on a.ADVC_ORDER_ID = u.FROM_BILL_ID ")
		   .append(" where ")
		   .append(" a.STATE in ('审核通过', '待确认', '已发货','待退货','已退货','关闭') ")
		   .append(" and a.DEL_FLAG = 0 ")
		   .append(" and ((a.SALE_DATE >= to_date('").append(SAEL_DATE_BENG).append("', 'yyyy-MM-DD') or ")
		   .append(" NVL(a.PAYABLE_AMOUNT, 0) - NVL(b.PAR_PAYMENT_AMOUNT, 0) - ")
		   .append(" NVL(b.CUR_PAYMENT_AMOUNT, 0) > 0 or decode(a.BILL_TYPE, '终端销售',NVL(k.STOREOUT_AMOUNT, 0), NVL(u.RETURN_AMOUNT, 0)) < NVL(a.PAYABLE_AMOUNT, 0) ) and ")
		   .append(" a.SALE_DATE <= to_date('").append(SAEL_DATE_END).append("', 'yyyy-MM-DD') or ")
		   .append(" b.BUSS_FLAG > 0 or k.BUSS_FLAG > 0 or u.BUSS_FLAG > 0 ")
		   .append(" or (( a.RETURN_STATEMENT_DATE>= to_date('").append(SAEL_DATE_BENG).append("', 'yyyy-MM-DD') or  NVL(a.PAYABLE_AMOUNT, 0) - NVL(b.PAR_PAYMENT_AMOUNT, 0) -  NVL(b.CUR_PAYMENT_AMOUNT, 0) > 0 or a.STATE = '审核通过') ")
		   .append(" and a.RETURN_STATEMENT_DATE<= to_date('").append(SAEL_DATE_END).append("', 'yyyy-MM-DD') ");
			
		   sql.append(" ))) t  GROUP BY t.LEDGER_ID, t.LEDGER_NAME, t.SALE_DATE,  t.SEND_STATE, t.TERM_ID,t.TERM_NAME,t.ADVC_ORDER_NO, t.BILL_TYPE,")
		   .append(" t.PAYABLE_AMOUNT,t.CUST_NAME,t.TEL,t.CONTRACT_NO, t.PROMOTE_NAME,t.ORDER_RECV_DATE,")
		   .append(" t.PAR_PAYMENT_AMOUNT,t.CUR_PAYMENT_AMOUNT,t.ALL_PAYMENT_AMOUNT,t.PAR_STOREOUT_AMOUNT,")
		   .append(" t.CUR_STOREOUT_AMOUNT,t. STOREOUT_AMOUNT,t.NO_SEND_AMOUNT,t.NO_PAYEDMENT,")
		   .append(" t.LEFT_PAYEDMENT  HAVING 1=1");
		   
		    //条件SQL
	 		if(!StringUtil.isEmpty(LEDGER_IDS)){
				sql.append(" and t.LEDGER_ID LIKE '%"+LEDGER_IDS+"%'");
			}
			
			if(!StringUtil.isEmpty(TERM_IDS)){
				sql.append(" and t.TERM_ID LIKE '%"+TERM_IDS+"%'");
			}
			if(!StringUtil.isEmpty(PROMOTE_NAME)){
				sql.append(" and t.PROMOTE_NAME like '%"+PROMOTE_NAME+"%'");
			}
			if(!StringUtil.isEmpty(CUST_NAME)){
				sql.append(" and t.CUST_NAME like '%"+CUST_NAME+"%'");
			}
			if(!StringUtil.isEmpty(TEL)){
				sql.append(" and t.TEL like '%"+TEL+"%'");
			}
		   sql.append(" ) t1 where 1=1 ");
		
		StringBuffer arg=new StringBuffer();
		arg.append(";arg3=");
		boolean flag=true;
		if(!StringUtil.isEmpty(SAEL_DATE_BENG)){
			arg.append(SAEL_DATE_BENG);
			flag=false;
		}
		if(!StringUtil.isEmpty(SAEL_DATE_END)){
			if(!flag){
				arg.append("至");
			}
			arg.append(SAEL_DATE_END);
		}
		
		//查询SQL
		sql.append(arg.toString());
		 
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "Contract.raq");
		request.setAttribute("printModel", "Contract.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
		return mapping.findForward("pageResult");
	}
	
	
	 /**
	  * 收款情况查询
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toQueryClause(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		String LEDGER_IDS = ParamUtil.get(request, "CHANN_ID");//帐套名称
		String TERM_IDS=ParamUtil.get(request, "TERM_ID");//终端名称
		String SAEL_DATE_BENG=ParamUtil.get(request, "SAEL_DATE_BENG");//销售日期从
		String SAEL_DATE_END=ParamUtil.get(request, "SAEL_DATE_END");//销售日期到
		
		String STATEMENT_DATE_BENG= ParamUtil.get(request, "STATEMENT_DATE_BENG");//收款日期从
		String STATEMENT_DATE_END = ParamUtil.get(request, "STATEMENT_DATE_END");//收款日期到
		
		String PROMOTE_NAME = ParamUtil.get(request, "PROMOTE_NAME");//活动名称
		String CUST_NAME = ParamUtil.get(request, "CUST_NAME");//顾客
		String TEL = ParamUtil.get(request, "TEL");//电话
		//条件SQL
		StringBuffer condition=new StringBuffer();
		
		if(!StringUtil.isEmpty(LEDGER_IDS)){
			condition.append(StringUtil.creCon("a.LEDGER_ID", LEDGER_IDS, ","));
		}
		
		if(!StringUtil.isEmpty(TERM_IDS)){
			condition.append(StringUtil.creCon("a.TERM_ID", TERM_IDS, ","));
		}
		if(!StringUtil.isEmpty(PROMOTE_NAME)){
			condition.append(" and a.PROMOTE_NAME like '%"+PROMOTE_NAME+"%'");
		}
		if(!StringUtil.isEmpty(CUST_NAME)){
			condition.append(" and a.CUST_NAME like '%"+CUST_NAME+"%'");
		}
		if(!StringUtil.isEmpty(TEL)){
			condition.append(" and a.TEL like '%"+TEL+"%'");
		}
		
		if(!StringUtil.isEmpty(SAEL_DATE_BENG)){
			condition.append(" and a.SALE_DATE >=to_date('").append(SAEL_DATE_BENG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(SAEL_DATE_END)){
			condition.append(" and a.SALE_DATE <=to_date('").append(SAEL_DATE_END).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(STATEMENT_DATE_BENG)){
			condition.append(" and b.STATEMENT_DATE >=to_date('").append(STATEMENT_DATE_BENG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(STATEMENT_DATE_END)){
			condition.append(" and b.STATEMENT_DATE <=to_date('").append(STATEMENT_DATE_END).append("','yyyy-MM-DD') ");
		}
		
		 
		
		String sql=reportService.getImportSql(condition.toString());
		String countSql=reportService.getImportCountSql(condition.toString());
		Map<String,String> params=new HashMap<String, String>();
		params.put("SelSQL", countSql);
		Map resMap = reportService.selecTotalCount(params);
		int count=StringUtil.getInteger(resMap.get("NUM"));
		System.out.println(sql.toString());
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "importClause.raq");
		request.setAttribute("printModel", "importClause.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("totalCount", count+1);
		return mapping.findForward("realPageResult");
	}
	
	/**
	  * 返款情况查询
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toQueryRebates(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		String LEDGER_IDS = ParamUtil.get(request, "CHANN_ID");//帐套名称
		String TERM_IDS=ParamUtil.get(request, "TERM_ID");//终端名称
		String SAEL_DATE_BENG=ParamUtil.get(request, "SAEL_DATE_BENG");//销售日期从
		String SAEL_DATE_END=ParamUtil.get(request, "SAEL_DATE_END");//销售日期到
		String WRITE_OFF_PSON_TIME_BENG=ParamUtil.get(request, "WRITE_OFF_PSON_TIME_BENG");//核销日期从
		String WRITE_OFF_PSON_TIME_END=ParamUtil.get(request, "WRITE_OFF_PSON_TIME_END");//核销日期到
		//条件SQL
		StringBuffer condition=new StringBuffer();
		//查询SQL
		StringBuffer sql=new StringBuffer();
		
		sql.append(" select a.LEDGER_NAME, ")
		.append(" to_char(a.SALE_DATE,'yyyy-mm-dd')SALE_DATE,")
		.append(" a.TERM_NO,")
		.append(" a.TERM_NAME,")
		.append(" a.ADVC_ORDER_NO,")
		.append(" a.PAYABLE_AMOUNT,")
		.append(" c.PAY_TYPE,")
		.append(" c.PAY_AMONT,")
		.append(" a.CONTRACT_NO")
		.append(" from    ")
		.append(" DRP_ADVC_ORDER a , DRP_STATEMENTS b ,DRP_STATEMENTS_PAYMENT_DTL c ")
		.append(" where ")
		.append(" a.ADVC_ORDER_ID = b.ADVC_ORDER_ID ")
		.append("  and b.STATEMENTS_ID = c.STATEMENTS_ID ")
		.append("  and a.STATE in ('审核通过', '已结算', '待结算', '待确认', '已发货') ")
		.append(" and a.DEL_FLAG = 0 ")
		.append(" and b.DEL_FLAG = 0 ")
		.append(" and c.DEL_FLAG = 0 ")
		.append(" and c.WRITE_OFF_FLAG=1 ")
		.append(" and c.PAY_TYPE in (select DATA_VAL from DRP_DATA_CONF where DATA_TYPE='付款方式' and IS_DEAL_FLAG=1  ");
		if("1".equals(userBean.getIS_DRP_LEDGER())){
			sql.append("and LEDGER_ID='").append(userBean.getLoginZTXXID()).append("' ) ");
		}else{
			sql.append(" and LEDGER_ID in ").append(userBean.getCHANNS_CHARG()).append(" ) ");
		}
		
		if(!StringUtil.isEmpty(LEDGER_IDS)){
			condition.append(StringUtil.creCon("a.LEDGER_ID", LEDGER_IDS, ","));
		}
		if(!StringUtil.isEmpty(TERM_IDS)){
			condition.append(StringUtil.creCon("a.TERM_ID", TERM_IDS, ","));
		}
		if(!StringUtil.isEmpty(SAEL_DATE_BENG)){
			condition.append(" and a.SALE_DATE >=to_date('").append(SAEL_DATE_BENG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(SAEL_DATE_END)){
			condition.append(" and a.SALE_DATE <=to_date('").append(SAEL_DATE_END).append("','yyyy-MM-DD') ");
		}
		
		if(!StringUtil.isEmpty(WRITE_OFF_PSON_TIME_BENG)){
			condition.append(" and to_char(c.WRITE_OFF_PSON_TIME,'yyyy-MM-DD') <='").append(WRITE_OFF_PSON_TIME_BENG).append("' ");
		}
		if(!StringUtil.isEmpty(WRITE_OFF_PSON_TIME_END)){
			condition.append(" and to_char(c.WRITE_OFF_PSON_TIME,'yyyy-MM-DD') <='").append(WRITE_OFF_PSON_TIME_END).append("' ");
		}
		
		
		System.out.println(sql.toString());
		request.setAttribute("conDition", "rptSql=" + sql.append(condition.toString()));
		request.setAttribute("rptModel", "rebatesClause.raq");
		request.setAttribute("printModel", "rebatesClause.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	}
	
	
	
	
	
	 /**
	  * 日销售商品明细表
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toQueryAdvcOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		String TERM_IDS=ParamUtil.get(request, "TERM_ID");//终端名称
		String SAEL_DATE_BENG=ParamUtil.get(request, "SAEL_DATE_BENG");//销售日期从
		String SAEL_DATE_END=ParamUtil.get(request, "SAEL_DATE_END");//销售日期到
		//查询SQL
		StringBuffer sql = new StringBuffer();
		//条件SQL
		StringBuffer conSql=new StringBuffer();
		if(!StringUtil.isEmpty(TERM_IDS)){
			conSql.append(StringUtil.creCon("a.TERM_ID", TERM_IDS, ","));
		}
		if(!StringUtil.isEmpty(SAEL_DATE_BENG)){
			conSql.append(" and a.SALE_DATE >=to_date('").append(SAEL_DATE_BENG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(SAEL_DATE_END)){
			conSql.append(" and a.SALE_DATE <=to_date('").append(SAEL_DATE_END).append("','yyyy-MM-DD') ");
		}
		
		
		sql.append(" select ")
		   .append(" rownum ROU,")
		   .append("a.ADVC_ORDER_NO,")
		   .append("a.CONTRACT_NO,")
		   .append("to_char(a.SALE_DATE,'yyyy-mm-dd')SALE_DATE, ")
		   .append("a.TERM_NAME,")
		   .append("a.CRE_NAME,")
		   .append("to_char(a.ORDER_RECV_DATE,'yyyy-mm-dd') ORDER_RECV_DATE,")
		   .append("a.CUST_NAME,")
		   .append("a.TEL,")
		   .append("a.STATE,")
		   .append("cm.PAYABLE_AMOUNT, ")
		   .append(" a.PAYED_TOTAL_AMOUNT, ")
		   .append(" (NVL(cm.PAYABLE_AMOUNT, ")
		   .append(" 0) - NVL(a.PAYED_TOTAL_AMOUNT, 0) - ")
		   .append(" NVL(a.DEDUCTED_TOTAL_AMOUNT, 0)) RESIDUE_AMOUNT,")
		   .append(" a.RECV_ADDR,")
		   .append("b.PRD_NO,")
		   .append(" b.PRD_NAME,")
		   .append("b.ORDER_NUM,")
		   .append("b.DECT_PRICE,")
		   .append("b.PRD_SPEC,")
		   .append("b.REMARK")
		   .append(" from V_DD u,DRP_ADVC_ORDER a ")
		   .append("left join DRP_ADVC_ORDER_DTL b on a.ADVC_ORDER_ID = b.ADVC_ORDER_ID and b.DEL_FLAG=0 ")
		   .append(" left join (select sum(c.PAYABLE_AMOUNT)PAYABLE_AMOUNT,c.ADVC_ORDER_ID ")
		   .append(" from DRP_ADVC_ORDER_DTL c ")
		   .append(" where c.DEL_FLAG = 0 ")
		   .append(" and c.PRD_TYPE = '销售货品' group by c.ADVC_ORDER_ID) cm on cm.ADVC_ORDER_ID=a.ADVC_ORDER_ID  ")
		   .append(" where a.DEL_FLAG=0 and u.SJZDBH='DRP_ADVC_ORDER_STATE' and u.SJXMC=a.STATE  and a.BILL_TYPE='终端销售'  and a.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("' ")
		   .append(" and a.STATE<>'未提交' ");
		//.append(" and u.SJXZ > 3 ")报错
		
		sql.append(conSql.toString());
		
		sql.append(" union all select ")
		   .append("null,'合计',null,null,null,null,null,null,null,null,null,null,null,null,null,null,")
		   .append("sum(NVL(b.ORDER_NUM,0))ORDER_NUM, sum(NVL(b.DECT_PRICE,0))DECT_PRICE,")
		   .append("null,null ")
		   .append(" from V_DD u,DRP_ADVC_ORDER a ")
		   .append("left join DRP_ADVC_ORDER_DTL b on a.ADVC_ORDER_ID = b.ADVC_ORDER_ID and b.DEL_FLAG=0 ")
		   .append(" where a.DEL_FLAG=0 and u.SJZDBH='DRP_ADVC_ORDER_STATE' and u.SJXMC=a.STATE  and a.BILL_TYPE='终端销售'  and a.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("' ")
		   .append(" and a.STATE<>'未提交' ");
		
		sql.append(conSql.toString());
		
		
		
		System.out.println(sql.toString());
		request.setAttribute("conDition", "rptSql=" + sql.toString());
		request.setAttribute("rptModel", "AdvcOrder.raq");
		request.setAttribute("printModel", "AdvcOrder.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 渠道财务进销存表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toInvocAcct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		StringBuffer sql=new StringBuffer();
		String LEDGER_ID = ParamUtil.get(request, "CHANN_ID");//帐套名称
		String StartDate=ParamUtil.get(request, "StartDate");//开始时间
		String EndDate=ParamUtil.get(request, "EndDate");//结束时间
		String getherFlag=ParamUtil.get(request, "getherFlag");//汇总标记
		String STORE_NO=ParamUtil.get(request, "STORE_NO");//库房编号
		String MONTH=ParamUtil.get(request, "MONTH");//月份
		String YEAR=ParamUtil.get(request, "YEAR");//年份
		String STORE_NAME=ParamUtil.get(request, "STORE_NAME");//库房名称
		String PRD_NO=ParamUtil.get(request, "PRD_NO");//货品编号
		String PRD_NAME=ParamUtil.get(request, "PRD_NAME");//货品名称
		String PAR_PRD_NAME=ParamUtil.get(request, "PAR_PRD_NAME");//货品分类
		if(!"3".contains(getherFlag)){
			
			sql.append(" SELECT t.PER_LEFT_NUM,t.PER_LEFT_PRICE,t.PER_LEFT_AMOUNT,t.CUR_END_NUM, t.CUR_END_PRICE,")
			   .append(" t.CUR_END_AMOUNT,t.CUR_PUR_NUM,t.CUR_PUR_PRICE, t.CUR_PUR_AMOUNT, t.CUR_TERM_SALE_NUM,")
			   .append(" t.CUR_TERM_SALE_PRICE,t.CUR_TERM_SALE_AMOUNT,t.CUR_OTHER_SALE_NUM,t.CUR_OTHER_SALE_PRICE,")
			   .append(" t.CUR_OTHER_SALE_AMOUNT,t.CUR_DUMP_OUT_NUM,t.CUR_DUMP_OUT_PRICE,t.CUR_DUMP_OUT_AMOUNT,")
			   .append(" t.CUR_DUMP_IN_NUM,t.CUR_DUMP_IN_PRICE,t.CUR_DUMP_IN_AMOUNT, t.CUR_RETUN_NUM,")
			   .append(" t.CUR_RETUN_PRICE,t.CUR_RETUN_AMOUNT,t.CUR_REPAIR_NUM,t.CUR_REPAIR_PRICE,")
			   .append(" t.CUR_REPAIR_AMOUNT,t.CUR_CUST_RETURN_NUM,t.CUR_CUST_RETURN_PRICE, t.CUR_CUST_RETURN_AMOUNT,")
			   .append(" t.CUR_INV_ADD_NUM,t.CUR_INV_ADD_PRICE,t.CUR_INV_ADD_AMOUNT,")
			   .append(" t.REMARK,t.PRD_NO,t.PRD_NAME,t.PRD_SPEC, t.STD_UNIT,t.STORE_NO,t.STORE_NAME,t.CUR_QUO_AMOUNT,")
			   .append(" t.CUR_QUO_PRICE,t.CUR_QUO_NUM,t.CUR_REAL_RETUN_AMOUNT,t.CUR_INV_REDUCE_AMOUNT,t.CUR_INV_REDUCE_PRICE,")
			   .append(" t.CUR_INV_REDUCE_NUM,t.CUR_REAL_REPAIR_AMOUNT,t.CUR_OTHER_REAL_SALE_AMOUNT,t.CUR_TERM_REAL_SALE_AMOUNT,")
			   .append(" t.CUR_REPAIR_BACK_AMOUNT,t.CUR_REPAIR_BACK_PRICE,t.CUR_REPAIR_BACK_NUM,t.CUR_OTHER_RETURN_AMOUNT,")
			   .append(" t.CUR_OTHER_RETURN_PRICE,t.CUR_OTHER_RETURN_NUM,t.CUR_FEW_STOREOUT_NUM,t.CUR_FEW_STOREOUT_PRICE,")
			   .append(" t.CUR_FEW_STOREOUT_AMOUNT,t.TO_SALE_COST,t.SPCL_DTL_REMARK,t.PAR_PRD_NAME,t.CUR_COST_ADJUST_AMOUNT,")
			   .append(" SUM(t.PER_LEFT_NUM) over() TOTAL_PER_LEFT_NUM, SUM(t.PER_LEFT_AMOUNT) over() TOTAL_PER_LEFT_AMOUNT,")
			   .append(" SUM(t.COLLECTNUM) over() TOTAL_COLLECTNUM,SUM(t.COLLECTAMOUNT) over() TOTAL_COLLECTAMOUNT,")
			   .append(" SUM(t.DISPATCHNUM) over() TOTAL_DISPATCHNUM,SUM(t.DISPATCHAMOUNT) over() TOTAL_DISPATCHAMOUNT,")
			   .append(" SUM(t.CUR_END_NUM) over() TOTAL_CUR_END_NUM, SUM(t.CUR_END_AMOUNT) over() TOTAL_CUR_END_AMOUNT,")
			   .append("  t.COLLECTNUM,t.COLLECTAMOUNT, t.DISPATCHNUM, t.DISPATCHAMOUNT")
			   .append(" FROM ( ")
			   .append("select ")
			   .append("a.PER_LEFT_NUM,")
			   .append("a.PER_LEFT_PRICE,")
			   .append("a.PER_LEFT_AMOUNT,")
			   .append("a.CUR_END_NUM,")//存数量
			   .append("a.CUR_END_PRICE,")
			   .append("a.CUR_END_AMOUNT,")//存金额
			   .append("a.CUR_PUR_NUM,")
			   .append("a.CUR_PUR_PRICE,")
			   .append("a.CUR_PUR_AMOUNT,")
			   .append("a.CUR_TERM_SALE_NUM,")
			   .append("a.CUR_TERM_SALE_PRICE,")
			   .append("a.CUR_TERM_SALE_AMOUNT,")
			   .append("a.CUR_OTHER_SALE_NUM,")
			   .append("a.CUR_OTHER_SALE_PRICE,")
			   .append("a.CUR_OTHER_SALE_AMOUNT,")
			   .append("a.CUR_DUMP_OUT_NUM,")
			   .append("a.CUR_DUMP_OUT_PRICE,")
			   .append("a.CUR_DUMP_OUT_AMOUNT,")
			   .append("a.CUR_DUMP_IN_NUM,")
			   .append("a.CUR_DUMP_IN_PRICE,")
			   .append("a.CUR_DUMP_IN_AMOUNT,")
			   .append("a.CUR_RETUN_NUM,")
			   .append("a.CUR_RETUN_PRICE,")
			   .append("a.CUR_RETUN_AMOUNT,")
			   .append("a.CUR_REPAIR_NUM,")
			   .append("a.CUR_REPAIR_PRICE,")
			   .append("a.CUR_REPAIR_AMOUNT,")
			   .append("a.CUR_CUST_RETURN_NUM,")
			   .append("a.CUR_CUST_RETURN_PRICE,")
			   .append("a.CUR_CUST_RETURN_AMOUNT,")
			   .append("a.CUR_INV_ADD_NUM,")
			   .append("a.CUR_INV_ADD_PRICE,")
			   .append("a.CUR_INV_ADD_AMOUNT,")
			   //.append("a.CUR_INV_REDUCE_NUM,")
			   .append("a.REMARK,")
			   .append("b.PRD_NO,")
			   .append("b.PRD_NAME,")
			   .append("b.PRD_SPEC,")
			   .append("b.STD_UNIT,")
			   .append("c.STORE_NO,")
			   .append("c.STORE_NAME,")
			   .append("a.CUR_QUO_AMOUNT,")
			   .append("a.CUR_QUO_PRICE,")
			   .append("a.CUR_QUO_NUM,")
			   .append("a.CUR_REAL_RETUN_AMOUNT,")
			   .append("a.CUR_INV_REDUCE_AMOUNT,")
			   .append("a.CUR_INV_REDUCE_PRICE,")
			   .append("a.CUR_INV_REDUCE_NUM,")
			   .append("a.CUR_REAL_REPAIR_AMOUNT,")
			   .append("a.CUR_OTHER_REAL_SALE_AMOUNT,")
			   .append("a.CUR_TERM_REAL_SALE_AMOUNT,")
			   .append("a.CUR_REPAIR_BACK_AMOUNT,")
			   .append("a.CUR_REPAIR_BACK_PRICE,")
			   .append("a.CUR_REPAIR_BACK_NUM,")
			   .append("a.CUR_OTHER_RETURN_AMOUNT,")
			   .append("a.CUR_OTHER_RETURN_PRICE,")
			   .append("a.CUR_OTHER_RETURN_NUM,")
			   .append("a.CUR_FEW_STOREOUT_NUM, ")//零星出库数量
			   .append("a.CUR_FEW_STOREOUT_PRICE,")
			   .append("a.CUR_FEW_STOREOUT_AMOUNT,")
			   .append("NVL(a.TO_SALE_COST,0)TO_SALE_COST,")
			   .append("d.SPCL_DTL_REMARK, ")
			   .append("b.PAR_PRD_NAME,")
			   .append("a.CUR_COST_ADJUST_AMOUNT,")
			   .append("NVL(NVL(a.CUR_PUR_NUM,0)+NVL(a.CUR_CUST_RETURN_NUM,0)+NVL(a.CUR_OTHER_RETURN_NUM,0)+NVL(a.CUR_REPAIR_BACK_NUM,0)+NVL(a.CUR_DUMP_IN_NUM,0)+NVL(a.CUR_INV_ADD_NUM,0),0)COLLECTNUM,")//收数量
			   .append("NVL(NVL(a.CUR_PUR_AMOUNT,0)+NVL(a.CUR_CUST_RETURN_AMOUNT,0)+NVL(a.CUR_OTHER_RETURN_AMOUNT,0)+NVL(a.CUR_REPAIR_BACK_AMOUNT,0)+NVL(a.CUR_DUMP_IN_AMOUNT,0)+NVL(a.CUR_INV_ADD_AMOUNT,0),0)COLLECTAMOUNT,")//收金额
			   .append("NVL(NVL(a.CUR_TERM_SALE_NUM,0)+NVL(a.CUR_OTHER_SALE_NUM,0)+NVL(a.CUR_RETUN_NUM,0)+NVL(a.CUR_REPAIR_NUM,0)+NVL(a.CUR_DUMP_OUT_NUM,0)+NVL(a.CUR_INV_REDUCE_NUM,0)+ NVL(a.CUR_FEW_STOREOUT_NUM, 0),0)DISPATCHNUM,")//发数量
			   .append("NVL(NVL(a.CUR_TERM_SALE_AMOUNT,0)+NVL(a.CUR_OTHER_SALE_AMOUNT,0)+NVL(a.CUR_RETUN_AMOUNT,0)+NVL(a.CUR_REPAIR_AMOUNT,0)+NVL(a.CUR_DUMP_OUT_AMOUNT,0)+NVL(a.CUR_INV_REDUCE_AMOUNT,0)+ NVL(a.CUR_COST_ADJUST_AMOUNT, 0)+ NVL(a.CUR_FEW_STOREOUT_AMOUNT, 0)+ NVL(a.TO_SALE_COST, 0),0)DISPATCHAMOUNT")//发金额
			   .append(" from DRP_INVOC_ACCT a ")
			   .append(" left join BASE_PRODUCT b on a.PRD_ID = b.PRD_ID ")
			   .append(" left join DRP_STORE c on c.STORE_ID = a.STORE_ID ")
			   .append(" left join DRP_SPCL_TECH d on d.SPCL_TECH_ID = a.SPCL_TECH_ID  and d.USE_FLAG = 1 ");
		}else{
			sql.append("select a.IN_OUT_TYPE,")
			   .append("a.BILL_NO,")
			   .append("a.STORE_NO,")
			   .append("a.STORE_NAME,")
			   .append("d.SPCL_DTL_REMARK,")
			   .append("a.PRD_NO,")
			   .append("a.PRD_NAME,")
			   .append("a.PRD_SPEC,")
			   .append("a.BRAND,")
			   .append("a.STD_UNIT,")
			   .append("DECODE(a.DIRECTION,1,-1,0,1)*a.IN_OUT_NUM IN_OUT_NUM,")
			   .append("NVL(e.COST_PRICE, 0) * NVL(DECODE(a.DIRECTION,1,-1,0,1)*a.IN_OUT_NUM, 0) IN_OUT_AMOUNT,")
			   .append("a.DEAL_TIME,")
			   .append("a.DEAL_DATE ")
			   .append(" from DRP_JOURNAL_ACCT a ")
			   .append(" left join DRP_SPCL_TECH d  on d.SPCL_TECH_ID = a.SPCL_TECH_ID and d.USE_FLAG = 1 ")
			   .append(" left join BASE_PRODUCT b on b.PRD_ID=a.PRD_ID and b.DEL_FLAG=0 ")
			   .append(" left join DRP_STORE c on c.STORE_ID=a.STORE_ID and c.DEL_FLAG=0 ")
			   .append(" left join DRP_COST_PRICE e on a.Prd_Id=e.PRD_ID and NVL(a.SPCL_TECH_ID,'NONE')=NVL(e.SPCL_TECH_ID,'NONE') ")
			   .append(" and a.YEAR_MONTH=(e.YEAR||'/'||e.MONTH) and a.LEDGER_ID=e.LEDGER_ID ");
		}
		   sql.append(" where 1=1 ");
		   if(!StringUtil.isEmpty(LEDGER_ID)){
			   sql.append(" and a.LEDGER_ID='").append(LEDGER_ID).append("' ");
		   }
		   if (!StringUtil.isEmpty(StartDate)) {
				if("3".equals(getherFlag)){
					sql.append(" and a.YEAR_MONTH >=substr('").append(StartDate).append("',0,4) || '/' || substr('").append(StartDate).append("',6,2)");
				}else{
					sql.append(" and to_date(a.YEAR || '-' || a.MONTH ,'yyyy-mm') >=to_date('").append(StartDate).append("','yyyy-mm')");
				}
			}
			if (!StringUtil.isEmpty(EndDate)) {
				if("3".equals(getherFlag)){
					sql.append(" and a.YEAR_MONTH <=substr('").append(EndDate).append("',0,4) || '/' || substr('").append(EndDate).append("',6,2)");
				}else{
					sql.append(" and to_date(a.YEAR || '-' || a.MONTH ,'yyyy-mm') <=to_date('").append(EndDate).append("','yyyy-mm')");
				}
			}
			if (!StringUtil.isEmpty(YEAR)) {
				if("3".equals(getherFlag)){
					sql.append(" and substr(a.YEAR_MONTH,0,4) ='").append(YEAR).append("'");
				}else{
					sql.append(" and a.YEAR ='").append(YEAR).append("'");
				}
			}
			if (!StringUtil.isEmpty(MONTH)) {
				if("3".equals(getherFlag)){
					sql.append(" and substr(a.YEAR_MONTH,6)='").append(MONTH).append("'");
				}else{
					sql.append(" and a.MONTH='").append(MONTH).append("'");
				}
			}
		   if(!StringUtil.isEmpty(STORE_NO)){
				sql.append(StringUtil.creCon("c.STORE_NO", STORE_NO, ","));
			}
		   if(!StringUtil.isEmpty(STORE_NAME)){
				sql.append(StringUtil.creCon("c.STORE_NAME", STORE_NAME, ","));
			}
		   
		   if(!StringUtil.isEmpty(PRD_NO)){
				sql.append(StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
			}
		   if(!StringUtil.isEmpty(PRD_NAME)){
				sql.append(StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
			}
		   if(!StringUtil.isEmpty(PAR_PRD_NAME)){
				sql.append(StringUtil.creCon("b.PAR_PRD_NAME", PAR_PRD_NAME, ","));
			}
		   sql.append("order by b.PRD_NO  ");
		if(!"3".equals(getherFlag)){
		   sql.append("  ) t");
		}
		request.setAttribute("conDition", "rptSql=" + sql);
		if("1".equals(getherFlag)){
			request.setAttribute("rptModel", "InvocGatherAcct.raq");
			request.setAttribute("printModel", "InvocGatherAcct.raq");
		}else if("2".equals(getherFlag)){
			request.setAttribute("rptModel", "InvocAcct.raq");
			request.setAttribute("printModel", "InvocAcct.raq");
		}else if("3".equals(getherFlag)){
			request.setAttribute("rptModel", "JournalAcct.raq");
			request.setAttribute("printModel", "JournalAcct.raq");
		}
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	}
	
	

	/**
	 * 信用流水账 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toCreditAcctResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String KSRQ = ParamUtil.get(request, "KSRQ");
		String JZRQ = ParamUtil.get(request, "JZRQ");
		String STATE = ParamUtil.get(request, "STATE");
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" and 1=1 ");
		 
		 
		if(BusinessConsts.INTEGER_1.equals(IS_DRP_LEDGER)){
			if (!StringUtil.isEmpty(CHANN_NO)) {
				//拼装 like sql
				String sql = StringUtil.creCon("t.ORDER_CHANN_NO", CHANN_NO, ",");
				condition.append(sql);
				
			}
			if (!StringUtil.isEmpty(CHANN_NAME)) {
				//拼装 like sql
				String sql = StringUtil.creCon("t.ORDER_CHANN_NAME", CHANN_NAME, ",");
				condition.append(sql);
			}
			if (!StringUtil.isEmpty(KSRQ)) {
				condition.append(" and to_char(t.CRE_TIME,'YYYY-MM-DD')>='" + KSRQ + "'");
			}
			if (!StringUtil.isEmpty(JZRQ)) {
				condition.append(" and to_char(t.CRE_TIME,'YYYY-MM-DD')<='" + JZRQ + "'");
			}
		}else{
			if (!StringUtil.isEmpty(CHANN_NO)) {
				//拼装 like sql
				String sql = StringUtil.creCon("t.CHANN_NO", CHANN_NO, ",");
				condition.append(sql);
				
			}
			if (!StringUtil.isEmpty(CHANN_NAME)) {
				//拼装 like sql
				String sql = StringUtil.creCon("t.CHANN_NAME", CHANN_NAME, ",");
				condition.append(sql);
			}
			if (!StringUtil.isEmpty(KSRQ)) {
				condition.append(" and substr(t.deal_time,0,10)>='" + KSRQ + "'");
			}
			if (!StringUtil.isEmpty(JZRQ)) {
				condition.append(" and substr(t.deal_time,0,10)<='" + JZRQ + "'");
			}
		}
	 
		if (!StringUtil.isEmpty(STATE)) {
			STATE = STATE.replaceAll(",", "','");
			condition.append(" and a.STATE in ('" + STATE + "')");
		}
 
		 
		String rptSql = "";
		if(BusinessConsts.INTEGER_1.equals(IS_DRP_LEDGER)){
			rptSql = reportService.getCreditAcctFXSQL(userBean,condition.toString());
		}else{
			rptSql = reportService.getCreditAcctSQL(userBean,condition.toString());
		}
		
		   
		request.setAttribute("conDition", "rptSql=" + rptSql);
		request.setAttribute("rptModel", "credit_acct.raq");
		request.setAttribute("printModel", "credit_acct.raq");
		request.setAttribute("IS_DRP_LEDGER", IS_DRP_LEDGER);
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
	 
		System.out
				.println("*************end*****goin SaleOrderSend*****************");
		return mapping.findForward("pageResult");
	}
 
	
	/**
	 * 信用流水账  对外
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toCreditAcctOutResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String KSRQ = ParamUtil.get(request, "KSRQ");
		String JZRQ = ParamUtil.get(request, "JZRQ");
		String DIRECTION = ParamUtil.get(request, "DIRECTION");
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" and 1=1 ");
	 
		if(BusinessConsts.INTEGER_1.equals(IS_DRP_LEDGER)){
			CHANN_NO = userBean.getCHANN_NO();
			CHANN_NAME = userBean.getCHANN_NAME();
		}
		if (!StringUtil.isEmpty(CHANN_NO)) {
			//拼装 like sql
			String sql = StringUtil.creCon("t.CHANN_NO", CHANN_NO, ",");
			condition.append(sql);
			
		}
		if (!StringUtil.isEmpty(CHANN_NAME)) {
			//拼装 like sql
			String sql = StringUtil.creCon("t.CHANN_NAME", CHANN_NAME, ",");
			condition.append(sql);
		}
		if (!StringUtil.isEmpty(KSRQ)) {
			condition.append(" and substr(t.deal_time,0,10)>='" + KSRQ + "'");
		}
		if (!StringUtil.isEmpty(JZRQ)) {
			condition.append(" and substr(t.deal_time,0,10)<='" + JZRQ + "'");
		}
		if (!StringUtil.isEmpty(DIRECTION)) {
			condition.append(" and t.DIRECTION =" + DIRECTION + "");
		}
		 
		String rptSql  = reportService.getCreditAcctSQL(userBean,condition.toString());
		List<Map<String,Object>> list = reportService.queryCreditTolal(condition.toString());
		Double a = 0.0;
		Double b = 0.0;
		Double c = 0.0;
		Double a1 = 0.0;
		Double b1 = 0.0;
		Double c1 = 0.0;
		for(Map<String,Object> map : list){
			String DIRECTION_ = StringUtil.nullToSring(map.get("DIRECTION"));
			if(BusinessConsts.INTEGER_0.equals(DIRECTION_)){
				a = StringUtil.getDouble(StringUtil.nullToSring(map.get("CREDIT_FREEZE_AMOUNT")));
				a1 = StringUtil.getDouble(StringUtil.nullToSring(map.get("PRD_AMOUNT")));
			}else{
				b = StringUtil.getDouble(StringUtil.nullToSring(map.get("CREDIT_FREEZE_AMOUNT")));
				b1 = StringUtil.getDouble(StringUtil.nullToSring(map.get("PRD_AMOUNT")));
			}
		}
	    c = a-b;
	    c1 = a1-b1;
		request.setAttribute("conDition", "rptSql=" + rptSql+";CREDIT_FREEZE_TOTAL="+String.format("%.2f", c)+";PRD_AMOUNT_TOTAL="+String.format("%.2f", c1));
		request.setAttribute("rptModel", "credit_acct_out.raq");
		request.setAttribute("printModel", "credit_acct_out.raq");
		request.setAttribute("IS_DRP_LEDGER", IS_DRP_LEDGER);
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
	 
		System.out
				.println("*************end*****goin SaleOrderSend*****************");
		return mapping.findForward("pageResult");
	}
	
	
	
	
	/**
	 * 仓库进销存报表 :
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toInvocNum(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		StringBuffer sql=new StringBuffer();
		int MONTH=StringUtil.getInteger(ParamUtil.get(request, "MONTH"));//月份
		String monthStr="";
		String monthStrSmall="";
		int YEAR=StringUtil.getInteger(ParamUtil.get(request, "YEAR"));//年份
		String yearStr=""+YEAR;
		String yearStrSmall=""+YEAR;
		String DEAL_TIME=ParamUtil.get(request, "DEAL_TIME");//业务日期
		String CHANN_ID=ParamUtil.get(request, "CHANN_ID");//渠道ID
		String STORE_NAME = ParamUtil.get(request, "STORE_NAME");//库房
		String PRD_NO = ParamUtil.get(request, "PRD_NO");//货品编号
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");//货品名称
		String PRD_TYPE_NAME = ParamUtil.get(request, "PRD_TYPE_NAME");//货品分类
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		InventoryService inventoryService = (InventoryService) SpringContextUtil.getBean("inventoryService");
		String channMonth=inventoryService.getChannMonth(CHANN_ID);
		Date dt1;
		try {
			if(!StringUtil.isEmpty(channMonth)){
				dt1 = df.parse(YEAR+"-"+MONTH+"-01");
				Date dt2 = df.parse(channMonth);
		        if (dt1.getTime() < dt2.getTime()) {
		        	throw new ServiceException();
		        }
			}
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("error", "对不起，请联系管理员 !");
			return mapping.findForward("errorPage");
		}catch (ServiceException s){
			request.setAttribute("error", "对不起，您选得年份月份在所选渠道的初始化年份月份之前 !");
			return mapping.findForward("errorPage");
		}
		
		if(MONTH>1&&MONTH<=10){
			monthStrSmall="0"+(MONTH-1);
		}else if(MONTH==1){
			monthStrSmall="12";
			YEAR=YEAR-1;
			yearStrSmall=""+YEAR;
		}else{
			monthStrSmall=(MONTH-1)+"";
		}
		if(MONTH<10){
			monthStr="0"+MONTH;
		}else{
			monthStr=""+MONTH;
		}
		StringBuffer query=new StringBuffer();
		if(!StringUtil.isEmpty(STORE_NAME)){
			query.append(StringUtil.creCon("v.STORE_NAME", STORE_NAME, ","));
		}
		
		if(!StringUtil.isEmpty(PRD_NO)){
			query.append(StringUtil.creCon("u.PRD_NO", PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			query.append(StringUtil.creCon("u.PRD_NAME", PRD_NAME, ","));
		}
//		if(!StringUtil.isEmpty(PRD_TYPE_NO)){
//			query.append(StringUtil.creCon("u.PAR_PRD_NO", PRD_TYPE_NO, ","));
//		}
		if(!StringUtil.isEmpty(PRD_TYPE_NAME)){
			query.append(StringUtil.creEqualCon("u.PAR_PRD_NAME", PRD_TYPE_NAME, ","));
		}
		
		sql.append("select ")
		   .append(" v.STORE_NO,")
		   .append(" v.STORE_NAME,")
		   .append(" u.PRD_NO,")
		   .append(" u.PRD_NAME,")
		   .append(" u.PRD_SPEC,")
		   .append(" u.BRAND,")
		   .append(" k.SPCL_DTL_REMARK,")
		   .append(" u.PAR_PRD_NAME,")
		   .append(" NVL(b.CUR_END_NUM,0) PER_LEFT_NUM,")
		   .append(" NVL(c.CUR_PUR_NUM,0) CUR_PUR_NUM,")
		   .append(" NVL(c.CUR_CUST_RETURN_NUM,0)CUR_CUST_RETURN_NUM,")
		   .append(" NVL(c.CUR_OTHER_RETURN_NUM,0)CUR_OTHER_RETURN_NUM,")
		   .append(" NVL(c.CUR_REPAIR_BACK_NUM,0)CUR_REPAIR_BACK_NUM,")
		   .append(" NVL(c.CUR_DUMP_IN_NUM,0)CUR_DUMP_IN_NUM,")
		   .append(" NVL(c.CUR_TERM_SALE_NUM,0)CUR_TERM_SALE_NUM,")
		   .append(" NVL(c.CUR_OTHER_SALE_NUM,0)CUR_OTHER_SALE_NUM,")
		   .append(" NVL(c.CUR_RETUN_NUM,0)CUR_RETUN_NUM,")
		   .append(" NVL(c.CUR_REPAIR_NUM,0)CUR_REPAIR_NUM,")
		   .append(" NVL(c.CUR_DUMP_OUT_NUM,0)CUR_DUMP_OUT_NUM,")
		   .append(" NVL(c.CUR_INV_ADD_NUM,0)CUR_INV_ADD_NUM,")
		   .append(" NVL(c.CUR_INV_REDUCE_NUM,0)CUR_INV_REDUCE_NUM,")
		   .append(" NVL(b.CUR_END_NUM,0)+ NVL(c.CUR_PUR_NUM,0)+ NVL(c.CUR_CUST_RETURN_NUM,0)+ NVL(c.CUR_OTHER_RETURN_NUM,0)+ NVL(c.CUR_REPAIR_BACK_NUM,0)")
		   .append(" +NVL(c.CUR_DUMP_IN_NUM,0)- NVL(c.CUR_TERM_SALE_NUM,0)-NVL(c.CUR_RETUN_NUM,0)-NVL(c.CUR_OTHER_SALE_NUM,0)- NVL(c.CUR_REPAIR_NUM,0)-")
		   .append(" NVL(c.CUR_DUMP_OUT_NUM,0)+ NVL(c.CUR_INV_ADD_NUM,0)-NVL(c.CUR_INV_REDUCE_NUM,0)- NVL(c.CUR_FEW_STOREOUT_NUM, 0) CUR_END_NUM,")
		   .append(" NVL(c.CUR_FEW_STOREOUT_NUM,0)CUR_FEW_STOREOUT_NUM ")
		   .append(" from ")
		   .append(" DRP_STORE v , BASE_PRODUCT u, DRP_STORE_ACCT a ")
		   .append(" left join ")
		   .append("  DRP_INVOC_ACCT b  ")
		   .append(" on a.STORE_ID=b.STORE_ID and a.PRD_ID=b.PRD_ID and NVL(a.SPCL_TECH_ID,'NONE') = NVL(b.SPCL_TECH_ID,'NONE') and b.YEAR='").append(yearStrSmall).append("' and b.MONTH='").append(monthStrSmall).append("'")
		   .append("  left join (")
		   .append(" select ")
		   .append(" u.STORE_ID, ")
		   .append(" u.PRD_ID, ")
		   .append(" NVL(u.SPCL_TECH_ID,'NONE')SPCL_TECH_ID,")
		   .append(" u.LEDGER_ID,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'手工新增入库',NVL(u.IN_OUT_NUM,0),'订货入库',NVL(u.IN_OUT_NUM,0),0)) CUR_PUR_NUM, ")
		   .append(" sum(decode(u.IN_OUT_TYPE,'终端退货入库',NVL(u.IN_OUT_NUM,0),0))  CUR_CUST_RETURN_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'下级退货入库',NVL(u.IN_OUT_NUM,0),0))  CUR_OTHER_RETURN_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'返修入库',NVL(u.IN_OUT_NUM,0),0))  CUR_REPAIR_BACK_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'转储入库',NVL(u.IN_OUT_NUM,0),0))  CUR_DUMP_IN_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'终端销售出库',NVL(u.IN_OUT_NUM,0),0))  CUR_TERM_SALE_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'退货出库',NVL(u.IN_OUT_NUM,0),0))  CUR_RETUN_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'下级销售出库',NVL(u.IN_OUT_NUM,0),0))  CUR_OTHER_SALE_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'转储出库',NVL(u.IN_OUT_NUM,0),0))  CUR_DUMP_OUT_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'返修出库',NVL(u.IN_OUT_NUM,0),'下级退货入库',NVL(u.IN_OUT_NUM,0),0))  CUR_REPAIR_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'盘盈',NVL(u.IN_OUT_NUM,0),0))  CUR_INV_ADD_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'盘亏',NVL(u.IN_OUT_NUM,0),0))  CUR_INV_REDUCE_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'零星出库',NVL(u.IN_OUT_NUM, 0),0)) CUR_FEW_STOREOUT_NUM ")
		   .append(" from DRP_JOURNAL_ACCT u ")
		   .append(" where  u.IN_OUT_TYPE in('手工新增入库','订货入库','终端退货入库','下级退货入库','转储入库','返修入库','盘盈','盘亏','终端销售出库','下级销售出库','退货出库','返修出库','转储出库','零星出库')")
		   .append(" and substr(u.DEAL_TIME,1,10)<='").append(DEAL_TIME).append("'")
		   .append(" and u.YEAR_MONTH='").append(yearStr).append("/").append(monthStr).append("'")
		   .append(" and u.LEDGER_ID='").append(CHANN_ID).append("'")
		   .append("group by ")
		   .append(" u.PRD_ID,NVL(u.SPCL_TECH_ID,'NONE') ,u.STORE_ID,u.LEDGER_ID ")
		   .append(" )c  on a.STORE_ID=c.STORE_ID and a.PRD_ID=c.PRD_ID and NVL(a.SPCL_TECH_ID,'NONE') = c.SPCL_TECH_ID ")
		   .append(" left join DRP_SPCL_TECH k on a.SPCL_TECH_ID=k.SPCL_TECH_ID ")
		   .append(" where ")
		   .append(" a.LEDGER_ID='").append(CHANN_ID).append("'")
		   .append(" and a.PRD_ID=u.PRD_ID ")
		   .append(" and v.STORE_ID=a.STORE_ID ")
		   .append(" and NVL(b.CUR_END_NUM,0)+ NVL(c.CUR_PUR_NUM,0)+ NVL(c.CUR_CUST_RETURN_NUM,0)+ NVL(c.CUR_OTHER_RETURN_NUM,0)+")
		   .append("NVL(c.CUR_REPAIR_BACK_NUM,0)+NVL(c.CUR_DUMP_IN_NUM,0)+ NVL(c.CUR_TERM_SALE_NUM,0)+NVL(c.CUR_RETUN_NUM,0)+")
		   .append("NVL(c.CUR_OTHER_SALE_NUM,0)+ NVL(c.CUR_REPAIR_NUM,0)+ NVL(c.CUR_DUMP_OUT_NUM,0)+ NVL(c.CUR_INV_ADD_NUM,0)+")
		   .append("NVL(c.CUR_INV_REDUCE_NUM,0)+ NVL(c.CUR_FEW_STOREOUT_NUM, 0)>0 ").append(query.toString())
		   .append(" union all ")
		   .append("select ")
		   .append(" '',")
		   .append(" '',")
		   .append(" '',")
		   .append(" '',")
		   .append(" '',")
		   .append(" '',")
		   .append(" '',")
		   .append(" '合计',")
		   .append(" sum(NVL(b.CUR_END_NUM,0)) PER_LEFT_NUM,")
		   .append(" sum(NVL(c.CUR_PUR_NUM,0)) CUR_PUR_NUM,")
		   .append(" sum(NVL(c.CUR_CUST_RETURN_NUM,0))CUR_CUST_RETURN_NUM,")
		   .append(" sum(NVL(c.CUR_OTHER_RETURN_NUM,0))CUR_OTHER_RETURN_NUM,")
		   .append(" sum(NVL(c.CUR_REPAIR_BACK_NUM,0))CUR_REPAIR_BACK_NUM,")
		   .append(" sum(NVL(c.CUR_DUMP_IN_NUM,0))CUR_DUMP_IN_NUM,")
		   .append(" sum(NVL(c.CUR_TERM_SALE_NUM,0))CUR_TERM_SALE_NUM,")
		   .append(" sum(NVL(c.CUR_OTHER_SALE_NUM,0))CUR_OTHER_SALE_NUM,")
		   .append(" sum(NVL(c.CUR_RETUN_NUM,0))CUR_RETUN_NUM,")
		   .append(" sum(NVL(c.CUR_REPAIR_NUM,0))CUR_REPAIR_NUM,")
		   .append(" sum(NVL(c.CUR_DUMP_OUT_NUM,0))CUR_DUMP_OUT_NUM,")
		   .append(" sum(NVL(c.CUR_INV_ADD_NUM,0))CUR_INV_ADD_NUM,")
		   .append(" sum(NVL(c.CUR_INV_REDUCE_NUM,0))CUR_INV_REDUCE_NUM,")
		   .append(" sum(NVL(b.CUR_END_NUM,0)+ NVL(c.CUR_PUR_NUM,0)+ NVL(c.CUR_CUST_RETURN_NUM,0)+ NVL(c.CUR_OTHER_RETURN_NUM,0)+ NVL(c.CUR_REPAIR_BACK_NUM,0)")
		   .append(" +NVL(c.CUR_DUMP_IN_NUM,0)- NVL(c.CUR_TERM_SALE_NUM,0)-NVL(c.CUR_RETUN_NUM,0)-NVL(c.CUR_OTHER_SALE_NUM,0)- NVL(c.CUR_REPAIR_NUM,0)-")
		   .append(" NVL(c.CUR_DUMP_OUT_NUM,0)+ NVL(c.CUR_INV_ADD_NUM,0)-NVL(c.CUR_INV_REDUCE_NUM,0)- NVL(c.CUR_FEW_STOREOUT_NUM, 0)) CUR_END_NUM,")
		   .append(" sum(NVL(c.CUR_FEW_STOREOUT_NUM,0))CUR_FEW_STOREOUT_NUM ")
		   .append(" from ")
		   .append(" DRP_STORE v , BASE_PRODUCT u, DRP_STORE_ACCT a ")
		   .append(" left join ")
		   .append("  DRP_INVOC_ACCT b  ")
		   .append(" on a.STORE_ID=b.STORE_ID and a.PRD_ID=b.PRD_ID and NVL(a.SPCL_TECH_ID,'NONE') = NVL(b.SPCL_TECH_ID,'NONE') and b.YEAR='").append(yearStrSmall).append("' and b.MONTH='").append(monthStrSmall).append("'")
		   .append("  left join (")
		   .append(" select ")
		   .append(" u.STORE_ID, ")
		   .append(" u.PRD_ID, ")
		   .append(" NVL(u.SPCL_TECH_ID,'NONE')SPCL_TECH_ID,")
		   .append(" u.LEDGER_ID,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'手工新增入库',NVL(u.IN_OUT_NUM,0),'订货入库',NVL(u.IN_OUT_NUM,0),0)) CUR_PUR_NUM, ")
		   .append(" sum(decode(u.IN_OUT_TYPE,'终端退货入库',NVL(u.IN_OUT_NUM,0),0))  CUR_CUST_RETURN_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'下级退货入库',NVL(u.IN_OUT_NUM,0),0))  CUR_OTHER_RETURN_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'返修入库',NVL(u.IN_OUT_NUM,0),0))  CUR_REPAIR_BACK_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'转储入库',NVL(u.IN_OUT_NUM,0),0))  CUR_DUMP_IN_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'终端销售出库',NVL(u.IN_OUT_NUM,0),0))  CUR_TERM_SALE_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'退货出库',NVL(u.IN_OUT_NUM,0),0))  CUR_RETUN_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'下级销售出库',NVL(u.IN_OUT_NUM,0),0))  CUR_OTHER_SALE_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'转储出库',NVL(u.IN_OUT_NUM,0),0))  CUR_DUMP_OUT_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'返修出库',NVL(u.IN_OUT_NUM,0),'下级退货入库',NVL(u.IN_OUT_NUM,0),0))  CUR_REPAIR_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'盘盈',NVL(u.IN_OUT_NUM,0),0))  CUR_INV_ADD_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'盘亏',NVL(u.IN_OUT_NUM,0),0))  CUR_INV_REDUCE_NUM,")
		   .append(" sum(decode(u.IN_OUT_TYPE,'零星出库',NVL(u.IN_OUT_NUM, 0),0)) CUR_FEW_STOREOUT_NUM ")
		   .append(" from DRP_JOURNAL_ACCT u ")
		   .append(" where  u.IN_OUT_TYPE in('手工新增入库','订货入库','终端退货入库','下级退货入库','转储入库','返修入库','盘盈','盘亏','终端销售出库','下级销售出库','退货出库','返修出库','转储出库','零星出库')")
		   .append(" and substr(u.DEAL_TIME,1,10)<='").append(DEAL_TIME).append("'")
		   .append(" and u.YEAR_MONTH='").append(yearStr).append("/").append(monthStr).append("'")
		   .append(" and u.LEDGER_ID='").append(CHANN_ID).append("'")
		   .append("group by ")
		   .append(" u.PRD_ID,NVL(u.SPCL_TECH_ID,'NONE') ,u.STORE_ID,u.LEDGER_ID ")
		   .append(" )c  on a.STORE_ID=c.STORE_ID and a.PRD_ID=c.PRD_ID and NVL(a.SPCL_TECH_ID,'NONE') = c.SPCL_TECH_ID ")
		   .append(" left join DRP_SPCL_TECH k on a.SPCL_TECH_ID=k.SPCL_TECH_ID ")
		   .append(" where ")
		   .append(" a.LEDGER_ID='").append(CHANN_ID).append("'")
		   .append(" and a.PRD_ID=u.PRD_ID ")
		   .append(" and v.STORE_ID=a.STORE_ID ")
		   .append(" and NVL(b.CUR_END_NUM,0)+ NVL(c.CUR_PUR_NUM,0)+ NVL(c.CUR_CUST_RETURN_NUM,0)+ NVL(c.CUR_OTHER_RETURN_NUM,0)+")
		   .append("NVL(c.CUR_REPAIR_BACK_NUM,0)+NVL(c.CUR_DUMP_IN_NUM,0)+ NVL(c.CUR_TERM_SALE_NUM,0)+NVL(c.CUR_RETUN_NUM,0)+")
		   .append("NVL(c.CUR_OTHER_SALE_NUM,0)+ NVL(c.CUR_REPAIR_NUM,0)+ NVL(c.CUR_DUMP_OUT_NUM,0)+ NVL(c.CUR_INV_ADD_NUM,0)+")
		   .append("NVL(c.CUR_INV_REDUCE_NUM,0)+ NVL(c.CUR_FEW_STOREOUT_NUM, 0)>0 ").append(query.toString());
		
		
		
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "InvocNum.raq");
		request.setAttribute("printModel", "InvocNum.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward  toQueryMonthvisitResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	}
	
	/**
	 * 返利抵扣查询 :
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toRebateDeducted(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		String AREA_NAME_P=ParamUtil.get(request, "AREA_NAME_P");//战区
		String CHANN_NO=ParamUtil.get(request, "CHANN_NO");//渠道编号
		String CHANN_NAME=ParamUtil.get(request, "CHANN_NAME");//渠道名称
		String SHIP_POINT_NAME=ParamUtil.get(request, "SHIP_POINT_NAME");//所属生产基地
		String CRE_TIME_BEGIN=ParamUtil.get(request, "CRE_TIME_BEGIN");//订货日期从
		String CRE_TIME_END=ParamUtil.get(request, "CRE_TIME_END");//订货日期到
		StringBuffer sql=new StringBuffer();//查询SQL
		StringBuffer commSql=new StringBuffer();//通用SQL
		StringBuffer sumSql=new StringBuffer();//合计SQL
		StringBuffer querySql=new StringBuffer();//查询条件
		sql.append("rptSql=select ")
		   .append(" c.AREA_NAME_P,")
		   .append("a.ORDER_CHANN_NO,")
		   .append("a.ORDER_CHANN_NAME,")
		   .append(" b.SHIP_POINT_NAME,")
		   .append("a.GOODS_ORDER_NO,")
		   .append("to_char(a.CRE_TIME, 'yyyy-MM-DD') CRE_TIME,")
		   .append("d.PRD_NO,")
		   .append("d.PRD_NAME,")
		   .append("d.PRD_SPEC,")
		   .append("d.BRAND,")
		   .append("d.ORDER_NUM,")
		   .append("d.REBATE_PRICE,")
		   .append("nvl(d.REBATE_PRICE,0)*nvl(d.ORDER_NUM,0) REBATE_AMOUNT,")
//		   .append("d.REBATE_AMOUNT,")
		   .append("f.PAR_PRD_NAME ");
		sumSql.append("sumSql=select sum(d.ORDER_NUM)ALL_ORDER_NUM,")
			  .append("sum(d.REBATE_PRICE)ALL_REBATE_PRICE,")
			  .append("sum(nvl(d.REBATE_PRICE, 0) * nvl(d.ORDER_NUM, 0)) ALL_REBATE_AMOUNT ");
		commSql.append(" from DRP_GOODS_ORDER_DTL d ")
		   .append(" left join DRP_GOODS_ORDER a on a.GOODS_ORDER_ID = d.GOODS_ORDER_ID and d.DEL_FLAG = 0 ")
		   .append(" left join BASE_CHANN b on a.ORDER_CHANN_ID = b.CHANN_ID and b.DEL_FLAG = 0 ")
		   .append("  left join BASE_AREA c on c.AREA_ID = b.AREA_ID and c.DEL_FLAG = 0 ")
		   .append(" left join BASE_PRODUCT f on f.PRD_ID = d.PRD_ID and f.DEL_FLAG = 0 ")
		   .append(" where a.IS_USE_REBATE = 1 and a.STATE in ('未处理','已处理')");
		if(!StringUtil.isEmpty(AREA_NAME_P)){
			querySql.append(" and c.AREA_NAME_P like '%").append(AREA_NAME_P).append("%' ");
		}
		if(!StringUtil.isEmpty(CHANN_NO)){
			querySql.append(StringUtil.creCon("a.ORDER_CHANN_NO", CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(CHANN_NAME)){
			querySql.append(StringUtil.creCon("a.ORDER_CHANN_NAME", CHANN_NAME, ","));
		}
		if(!StringUtil.isEmpty(SHIP_POINT_NAME)){
			querySql.append(" and b.SHIP_POINT_NAME like '%").append(SHIP_POINT_NAME).append("%' ");
		}
//		if(!StringUtil.isEmpty(CRE_TIME_BEGIN)){
//			querySql.append(" and a.CRE_TIME >=to_date('").append(CRE_TIME_BEGIN).append("','yyyy-MM-DD HH24:MI:SS') ");
//		}
//		if(!StringUtil.isEmpty(CRE_TIME_END)){
//			querySql.append(" and a.CRE_TIME <=to_date('").append(CRE_TIME_END).append("','yyyy-MM-DD HH24:MI:SS') ");
//		}
		
		if (!StringUtil.isEmpty(CRE_TIME_BEGIN)) {
			querySql.append(" and to_char(a.CRE_TIME,'YYYY-MM-DD')>='" + CRE_TIME_BEGIN
					+ "'");
		}
		if (!StringUtil.isEmpty(CRE_TIME_END)) {
			querySql.append(" and to_char(a.CRE_TIME,'YYYY-MM-DD')<='" + CRE_TIME_END
					+ "'");
		}
		if(!"1".equals(userBean.getIS_DRP_LEDGER())){
			querySql.append(" and a.ORDER_CHANN_ID in ").append(userBean.getCHANNS_CHARG());
		}
		sql.append(commSql).append(querySql).append(" order by a.ORDER_CHANN_NO ;").append(sumSql).append(commSql).append(querySql);
		request.setAttribute("conDition", sql);
		request.setAttribute("rptModel", "RebateDeducted.raq");
		request.setAttribute("printModel", "RebateDeducted.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	}
	
	
	
	
	/**
	 * 库存预订单对照备货查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAdvcStoreAcct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		StringBuffer sql=new StringBuffer();
		String PRD_NO = ParamUtil.get(request, "PRD_NO");//货品编号
		String PRD_NAME=ParamUtil.get(request, "PRD_NAME");//货品名称
		String PAR_PRD_NO = ParamUtil.get(request, "PAR_PRD_NO");//上级货品编号
		String PAR_PRD_NAME=ParamUtil.get(request, "PAR_PRD_NAME");//上级货品名称
		sql.append("select  u.PRD_ID,u.PRD_NO,u.PRD_NAME,u.PRD_SPEC,t.SPCL_DTL_REMARK,k.STORE_SALE_NUM_BASE,k.STORE_SALE_NUM_TREM,t.SPCL_TECH_ID,")
		   .append("k.STORE_NOSALE_NUM_BASE,k.STORE_NOSALE_NUM_TREM,w.NO_SEND_NUM,y.ON_WAY_NUM,")
//		   .append("(case when NVL(NVL(k.STORE_SALE_NUM_BASE,0)+NVL(k.STORE_SALE_NUM_TREM,0)-NVL(w.NO_SEND_NUM,0),0)>0 then NVL(NVL(k.STORE_SALE_NUM_BASE,0)+NVL(k.STORE_SALE_NUM_TREM,0)-NVL(w.NO_SEND_NUM,0),0) else 0 end )DIFFNUM")
		   .append(" NVL(NVL(k.STORE_SALE_NUM_BASE,0)+NVL(k.STORE_SALE_NUM_TREM,0)-NVL(w.NO_SEND_NUM,0),0)DIFFNUM")
		   .append(" from ")
		   .append(" V_ALL_PRODUCT u ")
		   .append(" left join ")
		   .append(" ( select ")
		   .append(" a.PRD_ID,")
		   .append(" a.SPCL_TECH_ID,")
		   .append(" sum((case when b.STORE_TYPE in ('正品仓','二次销售仓') and b.TERM_STROE_FLAG=0  then NVL(a.STORE_NUM,0) else 0 end )) STORE_SALE_NUM_BASE, ")
		   .append(" sum((case when b.STORE_TYPE in ('正品仓','二次销售仓') and b.TERM_STROE_FLAG=1  then NVL(a.STORE_NUM,0) else 0 end )) STORE_SALE_NUM_TREM,")
		   .append(" sum((case when b.STORE_TYPE in ('次品仓库') and b.TERM_STROE_FLAG=0  then NVL(a.STORE_NUM,0) else 0 end )) STORE_NOSALE_NUM_BASE,")
		   .append(" sum( (case when b.STORE_TYPE in ('次品仓库') and b.TERM_STROE_FLAG=1  then NVL(a.STORE_NUM,0) else 0 end)) STORE_NOSALE_NUM_TREM")
		   .append(" from ")
		   .append(" DRP_STORE_ACCT a ,DRP_STORE b ")
		   .append("  where ")
		   .append(" a.STORE_ID=b.STORE_ID ")
		   .append(" and a.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("'")
//		   .append(" and a.LEDGER_ID in (select d. YHM  from (select b.xtyhjsid, b.xtjsid, b.xtyhid from T_SYS_XTYHjs b where b.xtjsid = 'DRP000'  and b.delflag = 0) c,  T_SYS_XTYH d where c.xtyhid = d.xtyhid and yhkl='e99a18c428cb38d5f260853678922e03')")
		   .append(" group by a.PRD_ID, a.SPCL_TECH_ID ")
		   .append(" ) k on u.PRD_ID=k.PRD_ID and NVL(u.SPCL_TECH_ID,'NONE')=NVL(k.SPCL_TECH_ID,'NONE')")
		   .append(" left join  ")
		   .append(" ( ")
		   .append(" select b.PRD_ID ,b.SPCL_TECH_ID, sum(NVL(ORDER_NUM,0)-NVL(SEND_NUM,0)) NO_SEND_NUM ")
		   .append(" from DRP_ADVC_ORDER a,DRP_ADVC_ORDER_DTL b,V_DD c ")
		   .append(" where  ")
		   .append(" a.ADVC_ORDER_ID=b.ADVC_ORDER_ID  ")
		   .append(" and a.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("'")
//		   .append(" and a.LEDGER_ID in (select d. YHM  from (select b.xtyhjsid, b.xtjsid, b.xtyhid from T_SYS_XTYHjs b where b.xtjsid = 'DRP000'  and b.delflag = 0) c,  T_SYS_XTYH d where c.xtyhid = d.xtyhid and yhkl='e99a18c428cb38d5f260853678922e03')")
		   .append(" and c.SJZDBH='DRP_ADVC_ORDER_STATE'  ")
		   .append(" and a.STATE=c.SJXMC ")
		   .append("  and a.DEL_FLAG=0    ")
		   .append(" and b.DEL_FLAG=0  ")
		   .append(" and c.SJXZ>='4' ")
		   .append(" and NVL(b.ORDER_NUM,0)>NVL(b.SEND_NUM,0) ")
		   .append(" group by b.PRD_ID ,b.SPCL_TECH_ID ")
		   .append(" ) w on u.PRD_ID=w.PRD_ID and NVL(u.SPCL_TECH_ID,'NONE')=NVL(w.SPCL_TECH_ID,'NONE') ")
		   .append(" left join   ")
		   .append(" ( select b.PRD_ID ,b.SPCL_TECH_ID, sum(NVL(ORDER_NUM,0)-NVL(SENDED_NUM,0)) ON_WAY_NUM ")
		   .append(" from  ERP_SALE_ORDER a ,ERP_SALE_ORDER_DTL b,V_DD c ")
		   .append(" where  ")
		   .append(" a.SALE_ORDER_ID=b.SALE_ORDER_ID ")
		   .append(" and a.ORDER_CHANN_ID='").append(userBean.getLoginZTXXID()).append("'")
		   .append("  and c.SJZDBH='ERP_SALE_SATE' ")
		   .append(" and a.STATE=c.SJXMC ")
		   .append(" and a.DEL_FLAG=0  ")
		   .append(" and b.DEL_FLAG=0   ")
		   .append(" and c.SJXZ>'1'")
		   .append(" and NVL(b.ORDER_NUM,0)>NVL(b.SENDED_NUM,0)")
		   .append(" group by b.PRD_ID ,b.SPCL_TECH_ID  ")
		   .append(" ) y on u.PRD_ID=y.PRD_ID and NVL(u.SPCL_TECH_ID,'NONE')=NVL(y.SPCL_TECH_ID,'NONE')")
		   .append(" left join DRP_SPCL_TECH t on  u.SPCL_TECH_ID=t.SPCL_TECH_ID ")
		   .append("   left join BASE_PRODUCT prd on prd.PRD_ID=u.PRD_ID and prd.DEL_FLAG=0 ")
		   .append(" where ( k.STORE_SALE_NUM_BASE>0   or w.NO_SEND_NUM >0) ");
		if(!StringUtil.isEmpty(PRD_NO)){
			sql.append(StringUtil.creCon("u.PRD_NO", PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			sql.append(StringUtil.creCon("u.PRD_NAME", PRD_NAME, ","));
		}
		
		if(!StringUtil.isEmpty(PAR_PRD_NO)){
			sql.append(StringUtil.creCon("prd.PAR_PRD_NO", PAR_PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
			sql.append(StringUtil.creCon("prd.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		
		sql.append(" order by DIFFNUM,PRD_NO");
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "AdvcStoreAcct.raq");
		request.setAttribute("printModel", "AdvcStoreAcct.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
	}
	
 
	
	/**
	 * 门店发货统计表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toTermSaleSendcount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		String CRE_TIME_BEGIN = ParamUtil.get(request, "CRE_TIME_BEGIN");//订货日期从
		String CRE_TIME_END = ParamUtil.get(request, "CRE_TIME_END");//订货日期到
		String CRE_NAME= ParamUtil.get(request, "CRE_NAME");//导购员
		String TERM_NAME = ParamUtil.get(request, "TERM_NAME");//终端名称
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String PAR_PRD_NAME = ParamUtil.get(request, "PAR_PRD_NAME");
		String SALE_PSON_NAME = ParamUtil.get(request, "SALE_PSON_NAME");
		String ADVC_ORDER_NO = ParamUtil.get(request, "ADVC_ORDER_NO");
		String CUST_NAME = ParamUtil.get(request, "CUST_NAME");
		String TEL = ParamUtil.get(request, "TEL");
		String RECV_ADDR = ParamUtil.get(request, "RECV_ADDR");
		String GROUP_TYPE = ParamUtil.get(request, "GROUP_TYPE");
		String PROMOTE_NO=ParamUtil.utf8Decoder(request, "PROMOTE_NO");
		String PROMOTE_NAME=ParamUtil.utf8Decoder(request, "PROMOTE_NAME");
		String COST_FLAG=ParamUtil.utf8Decoder(request, "COST_FLAG");
		StringBuffer condition = new StringBuffer(" and 1=1 ");
		StringBuffer conSqloT = new StringBuffer(" and 1=1 ");
		 
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		if(BusinessConsts.INTEGER_1.equals(IS_DRP_LEDGER)){
			condition.append("  and a.LEDGER_ID= '"+userBean.getLoginZTXXID()+"' ");
			conSqloT.append("  and a.LEDGER_ID= '"+userBean.getLoginZTXXID()+"' ");
		}else{
			condition.append("  and a.LEDGER_ID in "+userBean.getCHANNS_CHARG()+" ");
			conSqloT.append("  and a.LEDGER_ID in "+userBean.getCHANNS_CHARG()+" ");
		}
		if(!StringUtil.isEmpty(TERM_NAME)){
			condition.append(StringUtil.creCon("a.TERM_NAME", TERM_NAME, ","));
			conSqloT.append(StringUtil.creCon("e.TERM_NAME", TERM_NAME, ","));
			
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			condition.append(StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
			conSqloT.append(StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			condition.append(StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
			conSqloT.append(StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
			condition.append(StringUtil.creCon("p.PAR_PRD_NAME", PAR_PRD_NAME, ","));
			conSqloT.append(StringUtil.creCon("p.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(SALE_PSON_NAME)){
			condition.append(StringUtil.creCon("a.SALE_PSON_NAME", SALE_PSON_NAME, ","));
			conSqloT.append(StringUtil.creCon("c.SALE_PSON_NAME", SALE_PSON_NAME, ","));
		}
		if(!StringUtil.isEmpty(ADVC_ORDER_NO)){
			condition.append(StringUtil.creCon("e.ADVC_ORDER_NO", ADVC_ORDER_NO, ","));
			conSqloT.append(StringUtil.creCon("e.ADVC_ORDER_NO", ADVC_ORDER_NO, ","));
		}
		if(!StringUtil.isEmpty(CRE_NAME)){
			condition.append(StringUtil.creCon("e.CRE_NAME", CRE_NAME, ","));
			conSqloT.append(StringUtil.creCon("e.CRE_NAME", CRE_NAME, ","));
		}
		if(!StringUtil.isEmpty(CRE_TIME_BEGIN)){
			condition.append(" and a.DEAL_TIME >=to_date('").append(CRE_TIME_BEGIN).append("','yyyy-MM-DD') ");
			conSqloT.append(" and a.DEAL_TIME >=to_date('").append(CRE_TIME_BEGIN).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(CRE_TIME_END)){
			condition.append(" and a.DEAL_TIME <=to_date('").append(CRE_TIME_END).append("','yyyy-MM-DD')+1 ");
			conSqloT.append(" and a.DEAL_TIME <=to_date('").append(CRE_TIME_END).append("','yyyy-MM-DD')+1 ");
		}
		if(!StringUtil.isEmpty(CUST_NAME)){
			condition.append(" and e.CUST_NAME like '%").append(CUST_NAME).append("%' ");
			conSqloT.append(" and e.CUST_NAME like '%").append(CUST_NAME).append("%' ");
		}
		
		if(!StringUtil.isEmpty(TEL)){
			condition.append(" and e.TEL like '%").append(TEL).append("%' ");
			conSqloT.append(" and e.TEL like '%").append(TEL).append("%' ");
		}
		if(!StringUtil.isEmpty(PROMOTE_NO)){
			condition.append(StringUtil.creCon("e.PROMOTE_NO", PROMOTE_NO, ","));
			conSqloT.append(StringUtil.creCon("e.PROMOTE_NO", PROMOTE_NO, ","));
		}
		if(!StringUtil.isEmpty(PROMOTE_NAME)){
			condition.append(StringUtil.creCon("e.PROMOTE_NAME", PROMOTE_NAME, ","));
			conSqloT.append(StringUtil.creCon("e.PROMOTE_NAME", PROMOTE_NAME, ","));
		}
		if(!StringUtil.isEmpty(RECV_ADDR)){
			condition.append(" and e.RECV_ADDR like '%").append(RECV_ADDR).append("%' ");
			conSqloT.append(" and e.RECV_ADDR like '%").append(RECV_ADDR).append("%' ");
		} 
		if("按产品类别".equals(GROUP_TYPE)){
			String sql = reportService.getTermSaleSendByPrdParentSql(condition.toString(),conSqloT.toString());
			request.setAttribute("conDition", "rptSql=" + sql);
			if("1".equals(COST_FLAG)){
				request.setAttribute("rptModel", "termSaleSendByParentcountCost.raq");
				request.setAttribute("printModel", "termSaleSendByParentcountCost.raq");
			}else{
				request.setAttribute("rptModel", "termSaleSendByParentcount.raq");
				request.setAttribute("printModel", "termSaleSendByParentcount.raq");
			}
		}else if("导购员".equals(GROUP_TYPE)){
			String sql = reportService.getTermSaleSendBySalePsonNameSql(condition.toString(),conSqloT.toString());
			request.setAttribute("conDition", "rptSql=" + sql);
			request.setAttribute("rptModel", "termStoreByPsonName.raq");
			request.setAttribute("printModel", "termStoreByPsonName.raq");
		}else{
			String sql = reportService.getTermSaleSendSql(userBean,condition.toString(),conSqloT.toString());
			request.setAttribute("conDition", "rptSql=" + sql);
			if("1".equals(COST_FLAG)){
				request.setAttribute("rptModel", "termSaleSendcountCost.raq");
				request.setAttribute("printModel", "termSaleSendcountCost.raq");
			}else{
				request.setAttribute("rptModel", "termSaleSendcount.raq");
				request.setAttribute("printModel", "termSaleSendcount.raq");
			}
		}
		request.setAttribute("IS_DRP_LEDGER", IS_DRP_LEDGER);
		return mapping.findForward("pageResult");
	}
	
	
	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	
	
	/**
	 * 根据货品id，特殊工艺id，渠道id查询预订单信息，用于库存预订单对照备货查询报表弹出页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAdvcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		String PRD_ID=ParamUtil.get(request, "PRD_ID");
		String SPCL_TECH_ID=ParamUtil.get(request, "SPCL_TECH_ID");
		Map<String,String> params=new HashMap<String, String>();
		params.put("PRD_ID", PRD_ID);
		params.put("SPCL_TECH_ID", SPCL_TECH_ID);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		List list=reportService.qeuryAdvcStoreAcct(params);
		request.setAttribute("page", list);
		return mapping.findForward("advcList");
	}
	
	
	
	/**
	 * 新的信用报表
	 * Description :
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toNewCreditPageResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer sql = new StringBuffer();
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		 
		String CHANN_NO = "";
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		 
		if("1".equals(IS_DRP_LEDGER)){
			CHANN_NO = userBean.getCHANN_NO();
		}else{
			CHANN_NO = ParamUtil.get(request, "CHANN_NO");//取总部查询条件
		}
		String[] CHANN_NOS = CHANN_NO.split(",");
		List datas = new ArrayList();
		for(int i=0;i<CHANN_NOS.length;i++){
			String userCredit = "0";
			try {
				userCredit = LogicUtil.getU9CurrCredit(CHANN_NOS[i]);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				userCredit = "0";
			}
			
			// 更新语句
			StringBuffer updateSql = new StringBuffer();
			updateSql.append("update BASE_CHANN set CREDIT_CURRT=");
			updateSql.append(userCredit);
			updateSql.append(" where CHANN_NO='"+CHANN_NOS[i]+"'");
			Map<String,String> params = new HashMap<String,String> ();
			params.put("UpdSQL", updateSql.toString());
			datas.add(params);
			 
		}
		//先把查询到的信用更新到渠道表的CREDIT_CURRT字段
		reportService.batch4Update(datas);
		
		CHANN_NO = "'"+CHANN_NO.replace(",", "','")+"'";
		sql.append(" select b.CHANN_NO,b.CHANN_NAME,NVL(b.CREDIT_CURRT,0)CREDIT_CURRT,NVL(b.INI_CREDIT,0) INI_CREDIT,(NVL(b.CREDIT_CURRT,0)-NVL(b.INI_CREDIT,0)) CREDIT_CURRT,NVL(b.FREEZE_CREDIT,0) FREEZE_CREDIT,NVL(b.REBATE_CURRT,0) REBATE_CURRT,NVL(b.PAYMENT_CREDIT,0) PAYMENT_CREDIT,NVL(temp.TEMP_CREDITE,0) TEMP_CREDITE, ")
		   .append(" (NVL(b.CREDIT_CURRT,0) + NVL(temp.TEMP_CREDITE,0) + NVL(b.PAYMENT_CREDIT,0) - NVL(b.FREEZE_CREDIT,0)) BOOK_CREDITE,  ")
		   .append(" (NVL(b.CREDIT_CURRT,0) + NVL(temp.TEMP_CREDITE,0) - NVL(b.FREEZE_CREDIT,0)) SEND_CREDITE ")
		   .append(" from BASE_CHANN b ")
		   .append(" left join (select sum(TEMP_CREDIT_REQ) TEMP_CREDITE ,CHANN_ID")
		   .append(" 		from ERP_TEMP_CREDIT_REQ req ")
		   .append("     	where req.TEMP_CREDIT_VALDT >= to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') and req.STATE = '审核通过' and req.DEL_FLAG = 0 group by req.CHANN_ID ) ")
		   .append(" temp on b.CHANN_ID = temp.CHANN_ID ")
		   .append("  where b.DEL_FLAG = 0 and b.STATE = '启用' ");
		
		    sql.append(" and b.CHANN_NO in (").append(CHANN_NO).append(") ");
		 
		  if(!"1".equals(IS_DRP_LEDGER)){
			sql.append(" and b.CHANN_ID in ").append("(select CHANN_ID from BASE_USER_CHANN_CHRG where USER_ID = '").append(userBean.getXTYHID()).append("')  ");
		  }
		    
		System.out.println(sql.toString());
		   
		/********** 查询条件拼接 start **********/
		
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql.toString());
		request.setAttribute("rptModel", "new_credit.raq");
		request.setAttribute("printModel", "new_credit.raq");
		return mapping.findForward("pageResult");
	}
	

	
	 /**
	  * 发货情况统计表
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toQueryUncomm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		String ADVC_ORDER_NO=ParamUtil.utf8Decoder(request, "ADVC_ORDER_NO");
		String TERM_NO=ParamUtil.utf8Decoder(request, "TERM_NO");
		String TERM_NAME=ParamUtil.utf8Decoder(request, "TERM_NAME");
		String SALE_DATE_BEG=ParamUtil.utf8Decoder(request, "SALE_DATE_BEG");
		String SALE_DATE_END=ParamUtil.utf8Decoder(request, "SALE_DATE_END");
		String SALE_PSON_NAME=ParamUtil.utf8Decoder(request, "SALE_PSON_NAME");
		String CUST_NAME=ParamUtil.utf8Decoder(request, "CUST_NAME");
		String BEGIN_CRE_TIME=ParamUtil.utf8Decoder(request, "BEGIN_CRE_TIME");
		String END_CRE_TIME=ParamUtil.utf8Decoder(request, "END_CRE_TIME");
		String CONTRACT_NO=ParamUtil.utf8Decoder(request, "CONTRACT_NO");
		String PROMOTE_NO=ParamUtil.utf8Decoder(request, "PROMOTE_NO");
		String PROMOTE_NAME=ParamUtil.utf8Decoder(request, "PROMOTE_NAME");
		String TEL=ParamUtil.utf8Decoder(request, "TEL");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.ADVC_ORDER_NO, ")
		   .append("a.TERM_NO,")
		   .append("a.TERM_NAME,")
		   .append("a.SALE_PSON_NAME,")
		   .append("to_char(a.CRE_TIME, 'yyyy-MM-DD HH24:MI:SS') CRE_TIME,")
		   .append("a.CUST_NAME,")
		   .append("a.TEL,")
		   .append("A.CONTRACT_NO,")
		   .append("a.ADVC_AMOUNT ")
		   .append(" from DRP_ADVC_ORDER a")
		   .append(" where a.DEL_FLAG = 0 and a.STATE='待确认' and a.LEDGER_ID='"+userBean.getLoginZTXXID()+ "' ");
		/********** 查询条件拼接 start **********/
		if(!StringUtil.isEmpty(ADVC_ORDER_NO)){
			sql.append(" and a.ADVC_ORDER_NO like '%").append(ADVC_ORDER_NO).append("%' ");
		}
		if(!StringUtil.isEmpty(TERM_NO)){
			sql.append(StringUtil.creCon("a.TERM_NO", TERM_NO, ","));
		}
		if(!StringUtil.isEmpty(TERM_NAME)){
			sql.append(StringUtil.creCon("a.TERM_NAME", TERM_NAME, ","));
		}
		if(!StringUtil.isEmpty(SALE_DATE_BEG)){
			sql.append(" and a.SALE_DATE >=to_date('").append(SALE_DATE_BEG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(SALE_DATE_END)){
			sql.append(" and a.SALE_DATE >=to_date('").append(SALE_DATE_END).append("','yyyy-MM-DD') ");
		}
		
		if(!StringUtil.isEmpty(BEGIN_CRE_TIME)){
			sql.append(" and a.CRE_TIME >=to_date('").append(BEGIN_CRE_TIME).append("','yyyy-MM-DD HH24:MI:SS') ");
		}
		if(!StringUtil.isEmpty(END_CRE_TIME)){
			sql.append(" and a.CRE_TIME <=to_date('").append(END_CRE_TIME).append("','yyyy-MM-DD HH24:MI:SS') ");
		}
		
		if(!StringUtil.isEmpty(SALE_PSON_NAME)){
			sql.append(StringUtil.creCon("a.SALE_PSON_NAME", SALE_PSON_NAME, ","));
		}
		if(!StringUtil.isEmpty(CUST_NAME)){
			sql.append(" and a.CUST_NAME like '%").append(CUST_NAME).append("%' ");
		}
		if(!StringUtil.isEmpty(CONTRACT_NO)){
			sql.append(" and a.CONTRACT_NO like '%").append(CONTRACT_NO).append("%' ");
		}
		if(!StringUtil.isEmpty(PROMOTE_NO)){
			sql.append(StringUtil.creCon("a.PROMOTE_NO", PROMOTE_NO, ","));
		}
		if(!StringUtil.isEmpty(PROMOTE_NAME)){
			sql.append(StringUtil.creCon("a.PROMOTE_NAME", PROMOTE_NAME, ","));
		}
		if(!StringUtil.isEmpty(TEL)){
			sql.append(" and a.TEL like '%").append(TEL).append("%' ");
		}
		System.out.println(sql.toString());
		request.setAttribute("conDition", "rptSql=" + sql.toString());
		request.setAttribute("rptModel", "confirmedAdvc.raq");
		request.setAttribute("printModel", "confirmedAdvc.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
}
	/**
	 * 未处理的订货订单冻结信用
	 * Description :
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toCreditFreeze(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer conSql = new StringBuffer();
		
		String ZTXXID = userBean.getLoginZTXXID();
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		String CHANN_ID = ParamUtil.get(request, "CHANN_ID"); 
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO"); 
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME"); 
		if(!StringUtil.isEmpty(CHANN_ID)){
			conSql.append(" and t.ORDER_CHANN_ID='").append(CHANN_ID).append("'");
		}else{
			if(!StringUtil.isEmpty(CHANN_NO)){
				conSql.append(" and t.ORDER_CHANN_NO='").append(CHANN_NO).append("'");
			}else if(!StringUtil.isEmpty(CHANN_NAME)){
				conSql.append(" and t.ORDER_CHANN_NAME='").append(CHANN_NAME).append("'");
			}
		}
		   
		/********** 查询条件拼接 start **********/
		String sql = this.reportService.getCreditFreezeSql(conSql.toString());
		
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "CreditFreeze.raq");
		request.setAttribute("printModel", "CreditFreeze.raq");
		return mapping.findForward("pageResult");
	}
	
	 
	/**
	 * 返利扣除信用
	 * Description :
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toRebateFreeze(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer conSql = new StringBuffer();
		
		String ZTXXID = userBean.getLoginZTXXID();
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		String CHANN_ID = ParamUtil.get(request, "CHANN_ID");//取总部查询条件
		
		   
		/********** 查询条件拼接 start **********/
		String sql = this.reportService.getRebateFreezeSql(CHANN_ID);
		
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "RebateFreeze.raq");
		request.setAttribute("printModel", "RebateFreeze.raq");
		return mapping.findForward("pageResult");
	}
	
	/**
	 * TOP10产品
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toTop10Product(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer conSql = new StringBuffer();
		
		String ZTXXID = userBean.getLoginZTXXID();
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		String ORDER_DATE_BEG = ParamUtil.get(request, "ORDER_DATE_BEG"); 
		String ORDER_DATE_END = ParamUtil.get(request, "ORDER_DATE_END"); 
		String PAR_PRD_ID = ParamUtil.get(request, "PAR_PRD_ID"); 
		String PAR_PRD_NAME = ParamUtil.get(request, "PAR_PRD_NAME"); 
		String RANKING = ParamUtil.get(request, "RANKING"); 
		
		
		if(!StringUtil.isEmpty(ORDER_DATE_BEG)){
			conSql.append(" and to_char(t.ORDER_DATE,'YYYY-MM-DD') >='").append(ORDER_DATE_BEG).append("' ");
		}
		
		if(!StringUtil.isEmpty(ORDER_DATE_END)){
			conSql.append(" and to_char(t.ORDER_DATE,'YYYY-MM-DD') <='").append(ORDER_DATE_END).append("' ");
		}
//		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
//			conSql.append(" and  p.PAR_PRD_NAME like '%").append(PAR_PRD_NAME).append("%' ");
//		}
		if(!StringUtil.isEmpty(PAR_PRD_ID)){
			conSql.append(" and  p.PAR_PRD_ID in ('").append(PAR_PRD_ID).append("') ");
		}
		if(StringUtil.isEmpty(RANKING) || (0 == StringUtil.getInteger(RANKING))){
			RANKING = "10";
		}
		/********** 查询条件拼接 start **********/
		String sql = this.reportService.getTop10ProductSql(conSql.toString(),RANKING);
		
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "ToTop10Product.raq");
		request.setAttribute("printModel", "ToTop10Product.raq");
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 销量统计比例
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toSalePercentage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer conSql = new StringBuffer();
		 
		String PRD_TYPE_ID = ParamUtil.get(request, "PRD_TYPE_ID"); 
		String PRD_TYPE_NO = ParamUtil.get(request, "PRD_TYPE_NO"); 
		String PRD_TYPE_NAME = ParamUtil.get(request, "PRD_TYPE_NAME"); 
		if(!StringUtil.isEmpty(PRD_TYPE_ID)){
			conSql.append(" and p.PAR_PRD_ID ='").append(PRD_TYPE_ID).append("' ");
		}else{
			if(!StringUtil.isEmpty(PRD_TYPE_NO)){
				conSql.append(" and p.PAR_PRD_NO ='").append(PRD_TYPE_NO).append("' ");
			}else if(!StringUtil.isEmpty(PRD_TYPE_NAME)){
				conSql.append(" and p.PAR_PRD_NAME ='").append(PRD_TYPE_NAME).append("' ");
			}
		}
		   
		/********** 查询条件拼接 start **********/
		String sql = this.reportService.getSaleStatisticsSql(conSql.toString());
		
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "SalePercentage.raq");
		request.setAttribute("printModel", "SalePercentage.raq");
		return mapping.findForward("pageResult");
	}
	
	 /**
	  * 分类销量统计
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toCOSS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		String PAR_PRD_NO=ParamUtil.utf8Decoder(request, "PAR_PRD_NO");
		String PAR_PRD_NAME=ParamUtil.utf8Decoder(request, "PAR_PRD_NAME");
		String ORDER_DATE_BEGIN=ParamUtil.utf8Decoder(request, "ORDER_DATE_BEGIN");
		String ORDER_DATE_END=ParamUtil.utf8Decoder(request, "ORDER_DATE_END");
		StringBuffer sql = new StringBuffer();
		sql.append(" select to_number(to_char(a.ORDER_DATE, 'MM'))||'月' MONTH, ")
		   .append("sum(b.ORDER_NUM) ORDER_NUM,")
		   .append("sum(b.ORDER_AMOUNT) ORDER_AMOUNT,")
		   .append("c.PAR_PRD_NO,")
		   .append("c.PAR_PRD_NAME ")
		   .append(" from ERP_SALE_ORDER a ")
		   .append(" left join ERP_SALE_ORDER_DTL b on a.SALE_ORDER_ID = b.SALE_ORDER_ID and b.DEL_FLAG = 0 ")
		   .append(" left join BASE_PRODUCT c on b.PRD_ID = c.PRD_ID ")
		   .append(" where a.DEL_FLAG=0 ");
		/********** 查询条件拼接 start **********/
		if(!StringUtil.isEmpty(PAR_PRD_NO)){
			sql.append(StringUtil.creEqualCon("c.PAR_PRD_NO", PAR_PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
			sql.append(StringUtil.creEqualCon("c.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(ORDER_DATE_BEGIN)){
			sql.append(" and a.ORDER_DATE >=to_date('").append(ORDER_DATE_BEGIN).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(ORDER_DATE_END)){
			sql.append(" and a.ORDER_DATE <=to_date('").append(ORDER_DATE_END).append("','yyyy-MM-DD') ");
		}
		sql.append(" group by to_char(ORDER_DATE, 'MM'), c.PAR_PRD_NO, c.PAR_PRD_NAME");
		System.out.println(sql.toString());
		request.setAttribute("conDition", "rptSql=" + sql.toString());
		request.setAttribute("rptModel", "COSS.raq");
		request.setAttribute("printModel", "COSS.raq");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("pageResult");
}
	/**
	  * 原发运单查询
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	public ActionForward toQueryOldDelivery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			UserBean userBean =  ParamUtil.getUserBean(request);
			String DELIVER_ORDER_NO=ParamUtil.utf8Decoder(request, "DELIVER_ORDER_NO");
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.DELIVER_ORDER_NO, ")
			   .append(" a.ORDER_CHANN_NAME,")
			   .append("b.PRD_NO,")
			   .append("b.PRD_NAME,")
			   .append(" b.PRD_SPEC,")
			   .append("b.PLAN_NUM,")
			   .append("b.U9_SALE_ORDER_NO,")
			   .append("b.U9_SALE_ORDER_DTL_NO ")
			   .append(" from ERP_DELIVER_ORDER_TEMP a, ERP_DELIVER_ORDER_DTL_TEMP b ")
			   .append(" where a.DELIVER_ORDER_ID = b.DELIVER_ORDER_ID ")
			   .append(" and DELIVER_ORDER_NO in ")
			   .append(" (select DELIVER_ORDER_NO ")
			   .append("  from ERP_DELIVER_ORDER_TEMP c ")
			   .append(" start with DELIVER_ORDER_NO = '").append(DELIVER_ORDER_NO).append("' ")
			   .append(" connect by PRIOR FROM_BILL_ID = DELIVER_ORDER_NO ")
			   .append("  union all ")
			   .append(" select DELIVER_ORDER_NO ")
			   .append(" from ERP_DELIVER_ORDER_TEMP c ")
			   .append(" start with DELIVER_ORDER_NO = '").append(DELIVER_ORDER_NO).append("' ")
			   .append(" connect by PRIOR DELIVER_ORDER_NO = FROM_BILL_ID) ");
			System.out.println(sql.toString());
			request.setAttribute("conDition", "rptSql=" + sql.toString());
			request.setAttribute("rptModel", "queryOldDelivery.raq");
			request.setAttribute("printModel", "queryOldDelivery.raq");
			request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
			return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 销量统计比例
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toUncommAdvc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer conSql = new StringBuffer();
		 
		String ADVC_ORDER_NO = ParamUtil.get(request, "ADVC_ORDER_NO"); 
		String TERM_NO = ParamUtil.get(request, "TERM_NO"); 
		String TERM_NAME = ParamUtil.get(request, "TERM_NAME"); 
		String SALE_DATE_BEG = ParamUtil.get(request, "SALE_DATE_BEG"); 
		String SALE_DATE_END = ParamUtil.get(request, "SALE_DATE_END"); 
		
		String ORDER_RECV_DATE_BEG = ParamUtil.get(request, "ORDER_RECV_DATE_BEG"); 
		String ORDER_RECV_DATE_END = ParamUtil.get(request, "ORDER_RECV_DATE_END"); 
		
		String CREATE_DATE_BEG = ParamUtil.get(request, "CREATE_DATE_BEG"); 
		String CREATE_DATE_END = ParamUtil.get(request, "CREATE_DATE_END"); 
		
		String CUST_NAME = ParamUtil.get(request, "CUST_NAME"); 
		String CONTRACT_NO = ParamUtil.get(request, "CONTRACT_NO"); 
		String TEL = ParamUtil.get(request, "TEL"); 
		
		
		if(!StringUtil.isEmpty(ADVC_ORDER_NO)){
			conSql.append(" and u.ADVC_ORDER_NO ='").append(ADVC_ORDER_NO).append("' ");
		}
		if(!StringUtil.isEmpty(TERM_NO)){
			conSql.append(" and u.TERM_NO ='").append(TERM_NO).append("' ");
		}
		if(!StringUtil.isEmpty(TERM_NAME)){
			conSql.append(" and u.TERM_NAME ='").append(TERM_NAME).append("' ");
		} 
		if(!StringUtil.isEmpty(CUST_NAME)){
			conSql.append(" and u.CUST_NAME  like '%").append(CUST_NAME).append("%' ");
		}
		if(!StringUtil.isEmpty(TEL)){
			conSql.append(" and u.TEL  like '%").append(TEL).append("%' ");
		}
		if(!StringUtil.isEmpty(CONTRACT_NO)){
			conSql.append(" and u.CONTRACT_NO like '%").append(CONTRACT_NO).append("%' ");
		}
		//销售日期
		if(!StringUtil.isEmpty(SALE_DATE_BEG)){
			conSql.append(" and u.SALE_DATE >=to_date('").append(SALE_DATE_BEG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(SALE_DATE_END)){
			conSql.append(" and u.SALE_DATE <=to_date('").append(SALE_DATE_END).append("','yyyy-MM-DD') ");
		}
		//发货日期
		if(!StringUtil.isEmpty(ORDER_RECV_DATE_BEG)){
			conSql.append(" and u.ORDER_RECV_DATE >=to_date('").append(ORDER_RECV_DATE_BEG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(ORDER_RECV_DATE_END)){
			conSql.append(" and u.ORDER_RECV_DATE <=to_date('").append(ORDER_RECV_DATE_END).append("','yyyy-MM-DD') ");
		}
		//制单
		if(!StringUtil.isEmpty(CREATE_DATE_BEG)){
//			conSql.append(" and u.CRE_TIME >=to_date('").append(CREATE_DATE_BEG).append("','yyyy-MM-DD') ");
			conSql.append(" and to_char(u.CRE_TIME,'yyyy-MM-DD')>='" + CREATE_DATE_BEG + "'");
		}
		if(!StringUtil.isEmpty(CREATE_DATE_END)){
//			conSql.append(" and u.CRE_TIME <=to_date('").append(CREATE_DATE_END).append("','yyyy-MM-DD') ");
			conSql.append(" and to_char(u.CRE_TIME,'yyyy-MM-DD')<='" + CREATE_DATE_END + "'");
		}
		
		   
		/********** 查询条件拼接 start **********/
		String sql = this.reportService.getUncommAdvcSql(conSql.toString());
		
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "uncommAdvc.raq");
		request.setAttribute("printModel", "uncommAdvc.raq");
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 总部销售报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toErpSaleReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer conSql = new StringBuffer();
		 
		String WAREA_NO = ParamUtil.get(request, "WAREA_NO"); 
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME"); 
		String CHANN_ID = ParamUtil.get(request, "CHANN_ID"); 
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO"); 
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME"); 
		String PAR_PRD_NAME = ParamUtil.get(request, "PAR_PRD_NAME "); 
		String PAR_PRD_ID = ParamUtil.get(request, "PAR_PRD_ID"); 
		String PRD_TYPE_NO = ParamUtil.get(request, "PRD_TYPE_NO");
		String AREA_NO = ParamUtil.get(request, "AREA_NO");
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME");
		String ORDER_DATE_BEG = ParamUtil.get(request, "ORDER_DATE_BEG"); 
		String ORDER_DATE_END = ParamUtil.get(request, "ORDER_DATE_END"); 
		
		String SHIP_POINT_NO = ParamUtil.get(request, "SHIP_POINT_NO"); 
		String SHIP_POINT_NAME = ParamUtil.get(request, "SHIP_POINT_NAME"); 
		String PROV = ParamUtil.get(request, "PROV"); 
		if(!StringUtil.isEmpty(WAREA_NO)){
			 String tempSql = StringUtil.creCon("a.AREA_NO_P", WAREA_NO, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(WAREA_NAME)){
			String tempSql = StringUtil.creCon("a.AREA_NAME_P", WAREA_NAME, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(AREA_NO)){
			String tempSql = StringUtil.creCon("t.AREA_NO", AREA_NO, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(AREA_NAME)){
			String tempSql = StringUtil.creCon("t.AREA_NAME", AREA_NAME, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(PROV)){
			 String tempSql = StringUtil.creCon("b.PROV", PROV, ",");
			 conSql.append(tempSql);
		}
	 
		if(!StringUtil.isEmpty(CHANN_NO)){
			String tempSql = StringUtil.creCon("b.CHANN_NO", CHANN_NO, ",");
			conSql.append(tempSql);
		}
		
		if(!StringUtil.isEmpty(CHANN_NAME)){
			String tempSql = StringUtil.creCon("b.CHANN_NAME", CHANN_NAME, ",");
			conSql.append(tempSql);
		}
		 
		
		
		if(!StringUtil.isEmpty(PAR_PRD_ID)){
			String tempSql = StringUtil.creEqualCon("p.PAR_PRD_ID", PAR_PRD_ID, ",");
			conSql.append(tempSql);
		}else{
			if(!StringUtil.isEmpty(PRD_TYPE_NO)){
				String tempSql = StringUtil.creEqualCon("p.PAR_PRD_NO", PRD_TYPE_NO, ",");
				conSql.append(tempSql);
			}
//			if(!StringUtil.isEmpty(PAR_PRD_NAME)){
//				conSql.append(" and p.PAR_PRD_NAME  like '%").append(PAR_PRD_NAME).append("%' ");
//			}
		}
		
		if(!StringUtil.isEmpty(SHIP_POINT_NO)){
			String tempSql = StringUtil.creEqualCon("t.SHIP_POINT_ID", SHIP_POINT_NO, ",");
			conSql.append(tempSql);
			 
		}else{
			if(!StringUtil.isEmpty(SHIP_POINT_NAME)){
				String tempSql = StringUtil.creCon("t.SHIP_POINT_NAME", SHIP_POINT_NAME, ",");
				conSql.append(tempSql);
			}
		}
		
		//销售日期
		if(!StringUtil.isEmpty(ORDER_DATE_BEG)){
			conSql.append(" and t.ORDER_DATE >=to_date('").append(ORDER_DATE_BEG).append("','yyyy-MM-DD') ");
		}
		if(!StringUtil.isEmpty(ORDER_DATE_END)){
			conSql.append(" and t.ORDER_DATE <=to_date('").append(ORDER_DATE_END).append("','yyyy-MM-DD') ");
		}
		StringBuffer head_sql = new StringBuffer("select ");
		StringBuffer group_by = new StringBuffer(" group by  ");
		StringBuffer group_sql = new StringBuffer("");
		String count_sql = " SUM(NVL(d.ORDER_NUM, 0)) ORDER_NUM,"
       +"SUM(NVL(d.DECT_PRICE, 0) * NVL(d.ORDER_NUM, 0)) ORDER_AMOUNT,"
       +"sum(NVL(P.RET_PRICE_MIN, 0) * NVL(d.ORDER_NUM, 0)) RET_ORDER_AMOUNT"
       +" from ERP_SALE_ORDER t left join ERP_SALE_ORDER_DTL d on t.SALE_ORDER_ID = d.SALE_ORDER_ID"
       +" left join BASE_CHANN b on t.ORDER_CHANN_ID = b.CHANN_ID"
       +" left join BASE_AREA a on b.AREA_ID = a.AREA_ID"
       +" left join BASE_PRODUCT p on p.PRD_ID = d.PRD_ID"
       +" where d.DEL_FLAG = 0 and t.DEL_FLAG = 0 ";
  
		//润乾报表列sql
		StringBuffer sqlColumn = new StringBuffer("");
	    //group by 字段
		//战区
		String WAREA_ID_SHOW = ParamUtil.get(request, "WAREA_ID_SHOW"); 
		//是否显示战区这列 true->不显示
		boolean WAREA_FLAG = true;
        if(!StringUtil.isEmpty(WAREA_ID_SHOW)){
        	head_sql.append("a.AREA_NAME_P,");
        	group_sql.append("a.AREA_NAME_P");
        	WAREA_FLAG = false; 
        	sqlColumn.append(";WAREA_SQL=ds1.select(AREA_NAME_P)");
        }
		
        //省份
        String PROV_SHOW = ParamUtil.get(request, "PROV_SHOW"); 
        boolean PROV_FLAG = true;
        if(!StringUtil.isEmpty(PROV_SHOW)){
        	head_sql.append("b.PROV,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("b.PROV");
        	PROV_FLAG = false; 
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";PROV_SQL=ds1.PROV");
        	}else{
        		sqlColumn.append(";PROV_SQL=ds1.select(PROV)");
        	}
        }
        //区域
        String AREA_ID_SHOW = ParamUtil.get(request, "AREA_ID_SHOW"); 
        boolean AREA_FLAG = true;
        if(!StringUtil.isEmpty(AREA_ID_SHOW)){
        	head_sql.append("b.AREA_NAME,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("b.AREA_NAME");
        	AREA_FLAG = false; 
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";AREA_NAME_SQL=ds1.AREA_NAME");
        	}else{
        		sqlColumn.append(";AREA_NAME_SQL=ds1.select(AREA_NAME)");
        	}
        }
        //渠道
        String CHANN_ID_SHOW = ParamUtil.get(request, "CHANN_ID_SHOW"); 
        boolean CHANN_FLAG = true;
        if(!StringUtil.isEmpty(CHANN_ID_SHOW)){
        	head_sql.append("t.ORDER_CHANN_NO,t.ORDER_CHANN_NAME,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("t.ORDER_CHANN_NO,t.ORDER_CHANN_NAME");
        	CHANN_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";CHANN_NO_SQL=ds1.ORDER_CHANN_NO");
        	}else{
        		sqlColumn.append(";CHANN_NO_SQL=ds1.select(ORDER_CHANN_NO)");
        	}
        	
        	sqlColumn.append(";CHANN_NAME_SQL=ds1.ORDER_CHANN_NAME");
        }
        //品牌
        String BRAND_SHOW = ParamUtil.get(request, "BRAND_SHOW"); 
        boolean BRAND_FLAG = true;
        if(!StringUtil.isEmpty(BRAND_SHOW)){
        	head_sql.append("d.BRAND,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("d.BRAND");
        	BRAND_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";BRAND_SQL=ds1.BRAND");
        	}else{
        		sqlColumn.append(";BRAND_SQL=ds1.select(BRAND)");
        	}
        }
		  
        //货品分类
        String PAR_PRD_NAME_SHOW = ParamUtil.get(request, "PAR_PRD_NAME_SHOW"); 
        boolean PAR_PRD_NAME_FLAG = true;
        if(!StringUtil.isEmpty(PAR_PRD_NAME_SHOW)){
        	head_sql.append("p.PAR_PRD_NAME,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("p.PAR_PRD_NAME");
        	PAR_PRD_NAME_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";PAR_PRD_NAME_SQL=ds1.PAR_PRD_NAME");
        	}else{
        		sqlColumn.append(";PAR_PRD_NAME_SQL=ds1.select(PAR_PRD_NAME)");
        	}
        }
       //货品编号
        String PRD_NO_SHOW = ParamUtil.get(request, "PRD_NO_SHOW"); 
        boolean PRD_NO_FLAG = true;
        if(!StringUtil.isEmpty(PRD_NO_SHOW)){
        	head_sql.append("d.PRD_NO,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("d.PRD_NO");
        	PRD_NO_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";PRD_NO_SQL=ds1.PRD_NO");
        	}else{
        		sqlColumn.append(";PRD_NO_SQL=ds1.select(PRD_NO)");
        	}
        }
        
       //货品名称
        String PRD_NAME_SHOW = ParamUtil.get(request, "PRD_NAME_SHOW"); 
        boolean PRD_NAME_FLAG = true;
        if(!StringUtil.isEmpty(PRD_NAME_SHOW)){
        	head_sql.append("d.PRD_NAME,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("d.PRD_NAME");
        	PRD_NAME_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";PRD_NAME_SQL=ds1.PRD_NAME");
        	}else{
        		sqlColumn.append(";PRD_NAME_SQL=ds1.select(PRD_NAME)");
        	}
        }
        //规格型号
        String PRD_SPEC_SHOW = ParamUtil.get(request, "PRD_SPEC_SHOW"); 
        boolean PRD_SPEC_FLAG = true;
        if(!StringUtil.isEmpty(PRD_SPEC_SHOW)){
        	head_sql.append("d.PRD_SPEC,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("d.PRD_SPEC");
        	PRD_SPEC_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";PRD_SPEC_SQL=ds1.PRD_SPEC");
        	}else{
        		sqlColumn.append(";PRD_SPEC_SQL=ds1.select(PRD_SPEC)");
        	}
        }
        
        //生产基地
        String SHIP_POINT_ID_SHOW = ParamUtil.get(request, "SHIP_POINT_ID_SHOW"); 
        boolean SHIP_POINT_FLAG = true;
        if(!StringUtil.isEmpty(SHIP_POINT_ID_SHOW)){
        	head_sql.append("t.SHIP_POINT_NAME,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("t.SHIP_POINT_NAME");
        	SHIP_POINT_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";SHIP_POINT_SQL=ds1.SHIP_POINT_NAME");
        	}else{
        		sqlColumn.append(";SHIP_POINT_SQL=ds1.select(SHIP_POINT_NAME)");
        	}
        }
        
		/********** 查询条件拼接 start **********/
//		String sql = this.reportService.getErpSaleReportSql(conSql.toString());
        String sql = head_sql.toString()+ count_sql.toString()+ conSql.toString();
        if(!StringUtil.isEmpty(group_sql.toString())){
        	sql = sql + group_by.toString() + group_sql.toString();
        }
        String params = "rptSql=" + sql
        +";WAREA_FLAG="+WAREA_FLAG
        +";PROV_FLAG="+PROV_FLAG
        +";AREA_FLAG="+AREA_FLAG
        +";CHANN_FLAG="+CHANN_FLAG
        +";BRAND_FLAG="+BRAND_FLAG
        +";PAR_PRD_NAME_FLAG="+PAR_PRD_NAME_FLAG
        +";PRD_NO_FLAG="+PRD_NO_FLAG
        +";PRD_NAME_FLAG="+PRD_NAME_FLAG
        +";PRD_SPEC_FLAG="+PRD_SPEC_FLAG
        +";SHIP_POINT_FLAG="+SHIP_POINT_FLAG
        +";WAREA_ID_SHOW="+WAREA_ID_SHOW;
        boolean IS_FIRST_SHOW = true;
        if(sqlColumn.toString().length()<1 || sqlColumn.toString().indexOf("SHIP_POINT_SQL")>-1){
        	IS_FIRST_SHOW = false;
        	 
        } 
    	params = params 
    	+sqlColumn.toString()
    	+";IS_FIRST_SHOW="+IS_FIRST_SHOW;
        
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", params);
		request.setAttribute("rptModel", "erpsalereport.raq");
		request.setAttribute("printModel", "erpsalereport.raq");
		return mapping.findForward("pageResult");
	}
	/**
	 * 分销销售报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toDrpSaleReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer conSql = new StringBuffer();
		 
		String WAREA_NO = ParamUtil.get(request, "WAREA_NO"); 
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME"); 
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO"); 
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME"); 
		String PAR_PRD_ID = ParamUtil.get(request, "PAR_PRD_ID"); 
		String PRD_TYPE_NO = ParamUtil.get(request, "PRD_TYPE_NO");
		String AREA_NO = ParamUtil.get(request, "AREA_NO");
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME");
		String BRAND = ParamUtil.get(request, "BRAND"); 
		String YEAR = ParamUtil.get(request, "YEAR"); 
		String MONTH = ParamUtil.get(request, "MONTH"); 
		String PROV = ParamUtil.get(request, "PROV"); 
		String CITY = ParamUtil.get(request, "CITY"); 
		String productSql;
		if(!StringUtil.isEmpty(WAREA_NO)){
			 String tempSql = StringUtil.creCon("area.area_no_p", WAREA_NO, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(WAREA_NAME)){
			String tempSql = StringUtil.creCon("area.area_name_p", WAREA_NAME, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(AREA_NO)){
			String tempSql = StringUtil.creCon("area.AREA_NO", AREA_NO, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(AREA_NAME)){
			String tempSql = StringUtil.creCon("area.AREA_NAME", AREA_NAME, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(PROV)){
			 String tempSql = StringUtil.creCon("t.PROV", PROV, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(CITY)){
			 String tempSql = StringUtil.creCon("t.CITY", CITY, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(CHANN_NO)){
			String tempSql = StringUtil.creCon("t.CHANN_NO_P", CHANN_NO, ",");
			conSql.append(tempSql);
		}
		
		if(!StringUtil.isEmpty(CHANN_NAME)){
			String tempSql = StringUtil.creCon("t.CHANN_NAME_P", CHANN_NAME, ",");
			conSql.append(tempSql);
		}
		
		if(!StringUtil.isEmpty(PAR_PRD_ID)){
			 productSql = StringUtil.creEqualCon("p.PAR_PRD_ID", PAR_PRD_ID, ",");
		}else{
			if(!StringUtil.isEmpty(PRD_TYPE_NO)){
				productSql = StringUtil.creEqualCon("p.PAR_PRD_NO", PRD_TYPE_NO, ",");
			}else{
				productSql="and 1=1";
			}
//			if(!StringUtil.isEmpty(PAR_PRD_NAME)){
//				conSql.append(" and p.PAR_PRD_NAME  like '%").append(PAR_PRD_NAME).append("%' ");
//			}
		}
		
		if(!StringUtil.isEmpty(BRAND)){
			String tempSql = StringUtil.creEqualCon("t.BUSS_SCOPE", BRAND, ",");
			conSql.append(tempSql);
		} 
		if(!StringUtil.isEmpty(YEAR)){
			String tempSql = StringUtil.creEqualCon("temp.YEAR", YEAR, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(MONTH)){
			String tempSql = StringUtil.creEqualCon("temp.MONTH", MONTH, ",");
			conSql.append(tempSql);
		}
		 
	    StringBuffer top_sql  = new StringBuffer("SELECT p.TERM_NO,p.TERM_NAME,p.BUSS_STATE,p.TERM_TYPE, p.ORDER_NUM,p.ORDER_AMOUNT,   SUM(p.ORDER_NUM) over() TOTAL_ORDER_NUM,SUM(p.ORDER_AMOUNT) over() TOTAL_ORDER_AMOUNT ");
	    //top_sql.append("temp.ORDER_NUM,temp.ORDER_AMOUNT,   SUM(temp.ORDER_NUM) over() TOTAL_ORDER_NUM,SUM(temp.ORDER_AMOUNT) over() TOTAL_ORDER_AMOUNT");
		StringBuffer head_sql = new StringBuffer("  select ");
		StringBuffer group_by = new StringBuffer(" group by  ");
		StringBuffer group_sql = new StringBuffer("  t.term_no,t.term_name,t.term_type,t.buss_state");
//		String count_sql = " SUM(NVL(d.TOTAL_NUM, 0)) ORDER_NUM,"
//       +" SUM(NVL(d.TOTAL_AMOUNT, 0)) ORDER_AMOUNT"
//       +" from ERP_SALE_DATE_UP t "
//       +" left join  ERP_SALE_DATE_UP_DTL d on t.SALE_DATE_UP_ID=d.SALE_DATE_UP_ID"
//       +" left join BASE_CHANN c on c.CHANN_ID=t.CHANN_ID"
//       +" left join (select PRD_ID,PRD_NO, PRD_NAME,BRAND from BASE_PRODUCT where STATE ='启用' and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ) p on p.PRD_NAME=d.PRD_TYPE"
//       +" where t.STATE='审核通过' and  d.DEL_FLAG = 0 and t.DEL_FLAG = 0 ";
		String count_sql = "t.term_no TERM_NO,t.term_name TERM_NAME,t.BUSS_STATE,t.term_type TERM_TYPE, SUM(NVL(temp.order_num, 0)) ORDER_NUM,"
         +" sum(NVL(temp.TOTAL_AMOUNT, 0)) ORDER_AMOUNT"
         +" from base_terminal t left join (select a.TERM_NO,a.term_name,to_char(e.DEAL_TIME, 'yyyy') YEAR,to_char(e.DEAL_TIME, 'mm') MONTH,SUM(nvl(c.REAL_NUM, 0)) order_num,sum(decode(c.prd_type,'赠品',0,NVL(c.REAL_NUM, 0) * NVL(c.dect_price, 0))) total_amount " +
         		" from drp_advc_order a left join drp_advc_order_dtl ad on a.advc_order_id = ad.advc_order_id and ad.del_flag = 0  left join drp_advc_send_req f on f.from_bill_id = a.advc_order_id and f.del_flag = 0" +
         		" left join drp_advc_send_req_dtl b on b.from_bill_dtl_id = ad.advc_order_dtl_id and f.advc_send_req_id = b.advc_send_req_id  and b.del_flag = 0" +
         		" left join drp_storeout e  on e.from_bill_id = f.advc_send_req_id and e.del_flag = 0 left join drp_storeout_dtl c on e.storeout_id = c.storeout_id and c.from_bill_dtl_id = b.advc_send_req_dtl_id and c.del_flag = 0 left join BASE_PRODUCT p on ad.PRD_ID = p.PRD_ID  and p.DEL_FLAG = 0  "+productSql+" " +
         				"where a.del_flag = 0 and a.BILL_TYPE='终端销售' and a.state in ('待确认', '待核价', '提交', '已发货', '审核通过')"
         +" group by a.TERM_NO, a.term_name,to_char(e.DEAL_TIME,'yyyy'),to_char(e.DEAL_TIME, 'mm')) temp on t.term_no = temp.term_no left join base_area area on area.area_id = t.area_id "
         +" where t.state = '启用' and t.del_flag = 0 and t.CHANN_ID_P in ('4108', '4109', '4110', '4111')";
         //+"and t.CHANN_ID_P in"+CHANN_NO+"" 
        // +"group by t.term_no, t.term_name, t.term_type,t.chann_no_p, t.chann_name_p, t.BUSS_SCOPE,t.prov,t.CITY,temp.YEAR,temp.MONTH,area.area_no_p,area.area_name, area.area_name_p";
        String count_sqlt ="t.term_no TERM_NO,t.term_name TERM_NAME,t.BUSS_STATE,t.term_type TERM_TYPE, "
         +" SUM(NVL(temp.TOTAL_NUM, 0)) ORDER_NUM,sum(NVL(temp.TOTAL_AMOUNT, 0)) ORDER_AMOUNT from base_terminal t"
         +" left join (select t.term_no,t.term_name,t.YEAR,t.MONTH,SUM(NVL(d.TOTAL_NUM, 0)) TOTAL_NUM, SUM(NVL(d.TOTAL_AMOUNT, 0)) TOTAL_AMOUNT  from ERP_SALE_DATE_UP t left join ERP_SALE_DATE_UP_DTL d"
         +" on t.SALE_DATE_UP_ID = d.SALE_DATE_UP_ID left join BASE_CHANN c on c.CHANN_ID = t.CHANN_ID "
         +" left join (select PRD_ID, PRD_NO, PRD_NAME, BRAND from BASE_PRODUCT where STATE = '启用' and DEL_FLAG = '0' and FINAL_NODE_FLAG = 0) p on p.PRD_NAME = d.PRD_TYPE"
         +" where t.STATE = '审核通过' and d.DEL_FLAG = 0 and t.DEL_FLAG = 0 group by t.term_no, t.term_name,t.WAREA_NO,t.WAREA_NAME, t.YEAR,t.MONTH) temp  on t.term_no = temp.term_no left join base_area area on area.area_id = t.area_id "
         +" where t.state = '启用' and t.del_flag = 0  and t.CHANN_ID_P not in ('4108', '4109', '4110', '4111')";
         //+" and t.CHANN_ID_P not in"+CHANN_NO+""
         //+ "group by t.term_no, t.term_name, t.term_type,t.chann_no_p, t.chann_name_p, t.BUSS_SCOPE,t.prov,t.CITY,temp.YEAR,area.area_no_p,area.area_name";
  
		//润乾报表列sql
		StringBuffer sqlColumn = new StringBuffer("");
	    //group by 字段
		//战区
		String WAREA_ID_SHOW = ParamUtil.get(request, "WAREA_ID_SHOW"); 
		//是否显示战区这列 true->不显示
		boolean WAREA_FLAG = true;
        if(!StringUtil.isEmpty(WAREA_ID_SHOW)){
        	top_sql.append(",p.AREA_NAME_P");
        	head_sql.append("area.AREA_NAME_P,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("area.AREA_NAME_P");
        	WAREA_FLAG = false; 
        	sqlColumn.append(";WAREA_SQL=ds1.select(AREA_NAME_P)");
        }
		
        //省份
        String PROV_SHOW = ParamUtil.get(request, "PROV_SHOW"); 
        boolean PROV_FLAG = true;
        if(!StringUtil.isEmpty(PROV_SHOW)){
        	top_sql.append(",p.PROV");
        	head_sql.append("t.PROV,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("t.PROV");
        	PROV_FLAG = false; 
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";PROV_SQL=ds1.PROV");
        	}else{
        		sqlColumn.append(";PROV_SQL=ds1.select(PROV)");
        	}
        }
        String CITY_SHOW = ParamUtil.get(request, "CITY_SHOW"); 
        boolean CITY_FLAG = true;
        if(!StringUtil.isEmpty(CITY_SHOW)){
        	top_sql.append(",p.CITY");
        	head_sql.append("t.CITY,");
        	if(group_sql.indexOf(".")>-1){
        	   group_sql.append(",");
        	}
        	group_sql.append("t.CITY");
        	CITY_FLAG = false; 
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";CITY_SQL=ds1.CITY");
        	}else{
        		sqlColumn.append(";CITY_SQL=ds1.select(CITY)");
        	}
        }
        
        //区域
        String AREA_ID_SHOW = ParamUtil.get(request, "AREA_ID_SHOW"); 
        boolean AREA_FLAG = true;
        if(!StringUtil.isEmpty(AREA_ID_SHOW)){
        	top_sql.append(",p.AREA_NAME");
        	head_sql.append("area.AREA_NAME,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
            group_sql.append("area.AREA_NAME");
        	AREA_FLAG = false; 
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";AREA_NAME_SQL=ds1.AREA_NAME");
        	}else{
        		sqlColumn.append(";AREA_NAME_SQL=ds1.select(AREA_NAME)");
        	}
        }
        //渠道
        String CHANN_ID_SHOW = ParamUtil.get(request, "CHANN_ID_SHOW"); 
        boolean CHANN_FLAG = true;
        if(!StringUtil.isEmpty(CHANN_ID_SHOW)){
        	top_sql.append(",p.CHANN_NO_P,p.CHANN_NAME_P");
        	head_sql.append("t.CHANN_NO_P,t.CHANN_NAME_P,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("t.CHANN_NO_P,t.CHANN_NAME_P");
        	CHANN_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";CHANN_NO_SQL=ds1.CHANN_NO_P");
        	}else{
        		sqlColumn.append(";CHANN_NO_SQL=ds1.select(CHANN_NO_P)");
        	}
        	
        	sqlColumn.append(";CHANN_NAME_SQL=ds1.CHANN_NAME_P");
        }
        //品牌
        String BRAND_SHOW = ParamUtil.get(request, "BRAND_SHOW"); 
        boolean BRAND_FLAG = true;
        if(!StringUtil.isEmpty(BRAND_SHOW)){
        	top_sql.append(",p.BUSS_SCOPE");
        	head_sql.append("t.BUSS_SCOPE,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("t.BUSS_SCOPE");
        	BRAND_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";BRAND_SQL=ds1.BUSS_SCOPE");
        	}else{
        		sqlColumn.append(";BRAND_SQL=ds1.select(BUSS_SCOPE)");
        	}
        }
		  
        //货品分类
        String PAR_PRD_NAME_SHOW = ParamUtil.get(request, "PAR_PRD_NAME_SHOW"); 
        boolean PAR_PRD_NAME_FLAG = true;
        if(!StringUtil.isEmpty(PAR_PRD_NAME_SHOW)){
        	top_sql.append(",p.PRD_TYPE");
        	head_sql.append("d.PRD_TYPE,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("d.PRD_TYPE");
        	PAR_PRD_NAME_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";PAR_PRD_NAME_SQL=ds1.PRD_TYPE");
        	}else{
        		sqlColumn.append(";PAR_PRD_NAME_SQL=ds1.select(PRD_TYPE)");
        	}
        }
        
        //月份
        String MONTH_SHOW = ParamUtil.get(request, "MONTH_SHOW"); 
        boolean MONTH_FLAG = true;
        if(!StringUtil.isEmpty(MONTH_SHOW)){
        	top_sql.append(",p.MONTH");
        	head_sql.append("temp.MONTH,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	group_sql.append("temp.MONTH");
        	MONTH_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";MONTH_SQL=ds1.MONTH");
        	}else{
        		sqlColumn.append(";MONTH_SQL=ds1.select(MONTH)");
        	}
        }
        //年份
        String YEAR_SHOW = ParamUtil.get(request, "YEAR_SHOW"); 
        boolean YEAR_FLAG = true;
        if(!StringUtil.isEmpty(YEAR_SHOW)){
        	top_sql.append(",p.YEAR");
        	head_sql.append("temp.YEAR,");
        	if(group_sql.indexOf(".")>-1){
        	   group_sql.append(",");
        	}
        	group_sql.append("temp.YEAR");
        	YEAR_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";YEAR_SQL=ds1.YEAR");
        	}else{
        		sqlColumn.append(";YEAR_SQL=ds1.select(YEAR)");
        	}
        }
        
        
		/********** 查询条件拼接 start **********/
//		String sql = this.reportService.getErpSaleReportSql(conSql.toString());
        String sql = top_sql.toString()+" FROM ( "+head_sql.toString()+ count_sql.toString()+conSql.toString()+group_by.toString() + group_sql.toString()+" union all "+ head_sql.toString()+ count_sqlt.toString()+conSql.toString();
        if(!StringUtil.isEmpty(group_sql.toString())){
        	sql = sql + group_by.toString() + group_sql.toString();
        }
        sql += ") p";
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
       
        boolean IS_FIRST_SHOW = true;
        if(sqlColumn.toString().length()<1){
        	IS_FIRST_SHOW = false;
        	 
        } 
    	params = params 
    	+sqlColumn.toString()
    	+";IS_FIRST_SHOW="+IS_FIRST_SHOW;
        
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", params);
		request.setAttribute("rptModel", "drpsalereport.raq");
		request.setAttribute("printModel", "drpsalereport.raq");
		return mapping.findForward("pageResult");
	}
	
	/**
	 * 销售计划达成率报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toSalePlanReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		StringBuffer conSql = new StringBuffer(" where 1=1 and  c.STATE = '启用' and c.DEL_FLAG = 0 and area.AREA_ID_P  is not null ");
		String WAREA_NO = ParamUtil.get(request, "WAREA_NO"); 
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME"); 
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO"); 
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME"); 
		String PAR_PRD_ID = ParamUtil.get(request, "PAR_PRD_ID"); 
		String PRD_TYPE_NO = ParamUtil.get(request, "PRD_TYPE_NO");
		String AREA_NO = ParamUtil.get(request, "AREA_NO");
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME");
		String BRAND = ParamUtil.get(request, "BRAND"); 
		String YEAR = ParamUtil.get(request, "YEAR"); 
		String MONTH = ParamUtil.get(request, "MONTH"); 
		String PROV = ParamUtil.get(request, "PROV"); 
		String CITY = ParamUtil.get(request, "CITY"); 
	    String END_DATE = ParamUtil.get(request, "END_DATE"); 
	    String START_DATE = ParamUtil.get(request, "START_DATE"); 
		if(!StringUtil.isEmpty(WAREA_NAME)){
			String tempSql = StringUtil.creCon("area.AREA_NAME_P", WAREA_NAME, ",");
			conSql.append(tempSql);
		} 
		if(!StringUtil.isEmpty(AREA_NAME)){
			String tempSql = StringUtil.creCon("area.AREA_NAME", AREA_NAME, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(PROV)){
			 String tempSql = StringUtil.creCon("c.PROV", PROV, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(CITY)){
			 String tempSql = StringUtil.creCon("c.CITY", CITY, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(CHANN_NO)){
			String tempSql = StringUtil.creCon("c.CHANN_NO", CHANN_NO, ",");
			conSql.append(tempSql);
		}
		
		if(!StringUtil.isEmpty(CHANN_NAME)){
			String tempSql = StringUtil.creCon("c.CHANN_NAME", CHANN_NAME, ",");
			conSql.append(tempSql);
		}
		
		if(!StringUtil.isEmpty(PAR_PRD_ID)){
			String tempSql = StringUtil.creEqualCon("p.PAR_PRD_ID", PAR_PRD_ID, ",");
			conSql.append(tempSql);
		}else{
			if(!StringUtil.isEmpty(PRD_TYPE_NO)){
				String tempSql = StringUtil.creEqualCon("p.PAR_PRD_NO", PRD_TYPE_NO, ",");
				conSql.append(tempSql);
			}
//			if(!StringUtil.isEmpty(PAR_PRD_NAME)){
//				conSql.append(" and p.PAR_PRD_NAME  like '%").append(PAR_PRD_NAME).append("%' ");
//			}
		}
		
		if(!StringUtil.isEmpty(BRAND)){
			String tempSql = StringUtil.creEqualCon("a.BRAND", BRAND, ",");
			conSql.append(tempSql);
		} 
		StringBuffer conSql_temp = new StringBuffer();
		StringBuffer conSql_temp_old = new StringBuffer();
		if(!StringUtil.isEmpty(START_DATE)){
			conSql_temp.append(" and to_char(a.STOREOUT_TIME,'yyyy-MM-DD')>='"+START_DATE+"' ");
			conSql_temp_old.append(" and to_char(a.STOREOUT_TIME,'yyyy-MM-DD')>=to_char(add_months(to_date('"+START_DATE+"', 'yyyy-MM-DD'), -12),  'yyyy-MM-DD')");
		} 
		if(!StringUtil.isEmpty(END_DATE)){
			conSql_temp.append(" and to_char(a.STOREOUT_TIME,'yyyy-MM-DD')<='"+END_DATE+"' ");
			conSql_temp_old.append(" and to_char(a.STOREOUT_TIME,'yyyy-MM-DD')<=to_char(add_months(to_date('"+END_DATE+"', 'yyyy-MM-DD'), -12),  'yyyy-MM-DD')");
		} 
		
		 
		StringBuffer order_sql = new StringBuffer(" order  by 1 ");
		StringBuffer plan_con = new StringBuffer();
		StringBuffer temp_sql = new StringBuffer();
		StringBuffer plan_con_old = new StringBuffer(); 
		if(!StringUtil.isEmpty(YEAR)){
			plan_con.append(" and ym.YEAR="+YEAR);
			plan_con_old.append(" and ym.YEAR = "+(StringUtil.getInteger(YEAR)-1));//上一年
			temp_sql.append(StringUtil.creEqualCon("temp.PLAN_YEAR", YEAR, "$"));//
		}
		if(!StringUtil.isEmpty(MONTH)){
			plan_con.append(StringUtil.creEqualCon("ym.MONTH", MONTH, "$"));
			temp_sql.append(StringUtil.creEqualCon("temp.PLAN_MONTH", MONTH, "$"));//
		}
		
		
		/** **/
		StringBuffer top_sql = new StringBuffer(" ");
		StringBuffer head_sql = new StringBuffer(" select ");
		StringBuffer group_by = new StringBuffer(" group by ");
		StringBuffer group_sql = new StringBuffer("");
		 //月份
        String MONTH_SHOW = ParamUtil.get(request, "MONTH_SHOW");
        String group_month = "";
//        String store_group_month = "";
        if(!StringUtil.isEmpty(MONTH_SHOW) || !StringUtil.isEmpty(MONTH)){
        	group_month = ",h.PLAN_MONTH ";
//        	store_group_month = " and ym.MONTH=to_char(a.STOREOUT_TIME,'mm')";
        }
        //右边 销售计划单独进行分组
        StringBuffer right_top_sql = new StringBuffer(" select ");
        StringBuffer right_group_sql = new StringBuffer();
        StringBuffer right_sql ;
           
	
        //没有点击 按渠道分组的话 就按照区域销售指标，只要点击了渠道，就按照渠道销售指标
        String CHANN_ID_SHOW = ParamUtil.get(request, "CHANN_ID_SHOW"); 
        if(StringUtil.isEmpty(CHANN_ID_SHOW)){
        	right_sql = new StringBuffer(
     				" from (select f.AREA_ID,f.AREA_NO,h.PLAN_YEAR,sum(NVL(h.PLAN_SALE_AMOUNT,0))PLAN_SALE_AMOUNT "
            		+group_month
     				+" from ERP_AREA_SALE_PLAN f, ERP_AREA_SALE_PLAN_DTL h where f.AREA_SALE_PLAN_ID=h.AREA_SALE_PLAN_ID and f.DEL_FLAG=0 and h.DEL_FLAG=0  "
     				//+" and f.STATE='审核通过'"SALE_PLAN_ID
     			    +" group by f.AREA_ID,f.AREA_NO,h.PLAN_YEAR "+group_month+")temp ")
                    .append(" left join BASE_AREA area"
                     +" on area.AREA_ID = temp.AREA_ID"
                     +" where area.AREA_ID_P is not null and area.DEL_FLAG=0 and  area.STATE='启用' ")
                     .append(temp_sql);		
        }else{
        	right_sql = new StringBuffer(
     				" from (select f.CHANN_ID,f.CHANN_NO,h.PLAN_YEAR,sum(NVL(h.CHANN_SALE_AMOUNT,0))CHANN_SALE_AMOUNT,sum(NVL(h.PLAN_SALE_AMOUNT,0))PLAN_SALE_AMOUNT "
            		+group_month
     				+" from ERP_SALE_PLAN f, ERP_SALE_PLAN_DTL h where f.SALE_PLAN_ID=h.SALE_PLAN_ID and f.DEL_FLAG=0 and h.DEL_FLAG=0  "
     				//+" and f.STATE='审核通过'"
     			    +" group by f.CHANN_ID,f.CHANN_NO,h.PLAN_YEAR "+group_month+")temp ")
                    .append(" left join BASE_CHANN c"
                     +" on temp.CHANN_NO = c.CHANN_NO"
                     +" left join BASE_AREA area"
                     +" on area.AREA_ID = c.AREA_ID"
                     +" where area.AREA_ID_P is not null ");
        }
        
        StringBuffer left_sql = new StringBuffer( " sum((NVL(a.STOREOUT_NUM, 0) * NVL(a.DECT_PRICE, 0))) STOREOUT_AMOUNT "
               // +" NVL(temp.PLAN_SALE_AMOUNT, 0) PLAN_SALE_AMOUNT, (round(case when sum(NVL(temp.PLAN_SALE_AMOUNT, 0)) = 0 then 1 else "
              //  +" sum((NVL(a.STOREOUT_NUM, 0) * NVL(a.DECT_PRICE, 0))) / NVL(temp.PLAN_SALE_AMOUNT, 0) end * 100,2)) || '%' PLAN_PRIENCE,"
             //   +" sum((NVL(o.STOREOUT_NUM, 0) * NVL(o.DECT_PRICE, 0))) OLD_STOREOUT_AMOUNT,"
              //  +" (NVL(sum((NVL(a.STOREOUT_NUM, 0) * NVL(a.DECT_PRICE, 0))), 0) - nvl(sum((NVL(o.STOREOUT_NUM, 0) * NVL(o.DECT_PRICE, 0))), 0)) DIFF_AMOUT,"
              //  +" case when nvl(sum((NVL(o.STOREOUT_NUM, 0) * NVL(o.DECT_PRICE, 0))), 0) = 0 then '' "
             //   +" ELSE trunc(((NVL(sum((NVL(a.STOREOUT_NUM, 0) * NVL(a.DECT_PRICE, 0))),0) / nvl(sum((NVL(o.STOREOUT_NUM, 0) * NVL(o.DECT_PRICE, 0))),0)) - 1),2) || '%'end DIFF_PRIENCE " 
				+" from  BASE_CHANN c  left join BASE_AREA area  on area.AREA_ID = c.AREA_ID "
				+" left join ERP_YEAR_MONTH ym on 1=1 "+plan_con
				+" left join (select a.ORDER_CHANN_ID,a.ORDER_CHANN_NO,a.STOREOUT_TIME,a.ORDER_CHANN_NAME,b.PRD_ID,b.BRAND,b.STOREOUT_NUM,b.DECT_PRICE,b.DECT_AMOUNT from ERP_STOREOUT a  " 
				+" left join ERP_STOREOUT_PRD_DTL b  on a.STOREOUT_ID = b.STOREOUT_ID"
	            +" where 1 = 1 and a.DEL_FLAG = 0 and b.DEL_FLAG = 0 "+conSql_temp+") a on c.CHANN_ID = a.ORDER_CHANN_ID"
				+" and  ym.YEAR=to_char(a.STOREOUT_TIME,'yyyy') " 
//				+store_group_month
				+" and  ym.month= to_char(a.STOREOUT_TIME, 'MM') "
				+" left join BASE_PRODUCT p on p.PRD_ID=a.PRD_ID" );
        //去年数据
        
        StringBuffer left_sql_old = new StringBuffer( " sum((NVL(a.STOREOUT_NUM, 0) * NVL(a.DECT_PRICE, 0))) OLD_STOREOUT_AMOUNT "
 				+"  from  BASE_CHANN c  left join BASE_AREA area  on area.AREA_ID = c.AREA_ID "
 				+" left join ERP_YEAR_MONTH ym on 1=1 "+plan_con_old
 				+" left join (select a.ORDER_CHANN_ID,a.ORDER_CHANN_NO,a.STOREOUT_TIME,a.ORDER_CHANN_NAME,b.PRD_ID,b.BRAND,b.STOREOUT_NUM,b.DECT_PRICE,b.DECT_AMOUNT from ERP_STOREOUT a  " 
 				+" left join ERP_STOREOUT_PRD_DTL b  on a.STOREOUT_ID = b.STOREOUT_ID"
 	            +" where 1 = 1 and a.DEL_FLAG = 0 and b.DEL_FLAG = 0 "+conSql_temp_old+") a on c.CHANN_ID = a.ORDER_CHANN_ID"
 	            +"  and to_char(a.STOREOUT_TIME, 'yyyy') = ym.YEAR " //to_char(add_months(to_date(ym.YEAR, 'yyyy'), -12), 'yyyy')
// 				+store_group_month
 	            + "  and  ym.month= to_char(a.STOREOUT_TIME, 'MM') "
 				+" left join BASE_PRODUCT p on p.PRD_ID=a.PRD_ID" );
        StringBuffer join_on_o  = new StringBuffer(""); 
        StringBuffer join_on  = new StringBuffer(""); 
		//润乾报表列sql
		StringBuffer sqlColumn = new StringBuffer("");
	    //group by 字段
		//战区
		String WAREA_ID_SHOW = ParamUtil.get(request, "WAREA_ID_SHOW"); 
		//是否显示战区这列 true->不显示
		boolean WAREA_FLAG = true;
        if(!StringUtil.isEmpty(WAREA_ID_SHOW)){
        	top_sql.append("u.AREA_NAME_P,");
        	head_sql.append("area.AREA_NAME_P,");
        	group_sql.append("area.AREA_NAME_P");
        	right_top_sql.append("area.AREA_NAME_P,");
        	right_group_sql.append("area.AREA_NAME_P");
        	join_on_o.append(" and u.AREA_NAME_P=o.AREA_NAME_P");
        	join_on.append(" and u.AREA_NAME_P=t2.AREA_NAME_P");
        	WAREA_FLAG = false; 
        	sqlColumn.append(";WAREA_SQL=ds1.select(AREA_NAME_P)");
        	order_sql.append(",u.AREA_NAME_P");
        }
		
        //省份
        String PROV_SHOW = ParamUtil.get(request, "PROV_SHOW"); 
        boolean PROV_FLAG = true;
        if(!StringUtil.isEmpty(PROV_SHOW)){
        	top_sql.append("u.PROV,");
        	head_sql.append("c.PROV,");
        	right_top_sql.append("c.PROV,");
        	join_on_o.append(" and u.PROV=o.PROV");
        	join_on.append(" and u.PROV=t2.PROV");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        		right_group_sql.append(",");
        	}
        	group_sql.append("c.PROV");
        	right_group_sql.append("c.PROV");
        	PROV_FLAG = false; 
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";PROV_SQL=ds1.PROV");
        	}else{
        		sqlColumn.append(";PROV_SQL=ds1.select(PROV)");
        	}
        }
        String CITY_SHOW = ParamUtil.get(request, "CITY_SHOW"); 
        boolean CITY_FLAG = true;
        if(!StringUtil.isEmpty(CITY_SHOW)){
        	top_sql.append("u.CITY,");
        	head_sql.append("c.CITY,");
        	join_on_o.append(" and u.CITY=o.CITY ");
        	join_on.append(" and u.CITY=t2.CITY ");
        	right_top_sql.append("c.CITY,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        		right_group_sql.append(",");
        	}
        	group_sql.append("c.CITY");
        	right_group_sql.append("c.CITY");
        	CITY_FLAG = false; 
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";CITY_SQL=ds1.CITY");
        	}else{
        		sqlColumn.append(";CITY_SQL=ds1.select(CITY)");
        	}
        }
        
        //区域
        String AREA_ID_SHOW = ParamUtil.get(request, "AREA_ID_SHOW"); 
        boolean AREA_FLAG = true;
        if(!StringUtil.isEmpty(AREA_ID_SHOW)){
        	top_sql.append("u.AREA_NAME,");
        	head_sql.append("area.AREA_NAME,");
        	right_top_sql.append("area.AREA_NAME,");
        	join_on_o.append(" and u.AREA_NAME=o.AREA_NAME ");
        	join_on.append(" and u.AREA_NAME=t2.AREA_NAME ");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        		right_group_sql.append(",");
        	}
        	group_sql.append("area.AREA_NAME");
        	right_group_sql.append("area.AREA_NAME");
        	AREA_FLAG = false; 
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";AREA_NAME_SQL=ds1.AREA_NAME");
        	}else{
        		sqlColumn.append(";AREA_NAME_SQL=ds1.select(AREA_NAME)");
        	}
        }
        //渠道
        boolean CHANN_FLAG = true;
        if(!StringUtil.isEmpty(CHANN_ID_SHOW)){
        	top_sql.append("u.CHANN_NO,u.CHANN_NAME,");
        	head_sql.append("c.CHANN_NO,c.CHANN_NAME,");
        	right_top_sql.append("c.CHANN_NO,c.CHANN_NAME,");
        	join_on_o.append(" and u.CHANN_NO=o.CHANN_NO ");
        	join_on.append(" and u.CHANN_NO=t2.CHANN_NO ");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        		right_group_sql.append(",");
        	}
        	group_sql.append("c.CHANN_NO,c.CHANN_NAME");
        	right_group_sql.append("c.CHANN_NO,c.CHANN_NAME");
        	CHANN_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";CHANN_NO_SQL=ds1.CHANN_NO");
        	}else{
        		sqlColumn.append(";CHANN_NO_SQL=ds1.select(CHANN_NO)");
        	}
        	
        	sqlColumn.append(";CHANN_NAME_SQL=ds1.CHANN_NAME");
        }
        //品牌
        String BRAND_SHOW = ParamUtil.get(request, "BRAND_SHOW"); 
        boolean BRAND_FLAG = true;
        if(!StringUtil.isEmpty(BRAND_SHOW)){
        	top_sql.append("u.BRAND,");
        	head_sql.append("a.BRAND,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	join_on_o.append(" and u.BRAND=o.BRAND ");
        	group_sql.append("a.BRAND");
        	if(join_on_o.indexOf("and") == -1){
            	join_on.append(" and u.CHANN_NO=t2.CHANN_NO ");
        	}
        	
        	BRAND_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";BRAND_SQL=ds1.BRAND");
        	}else{
        		sqlColumn.append(";BRAND_SQL=ds1.select(BRAND)");
        	}
        }
		  
        //货品分类
        String PAR_PRD_NAME_SHOW = ParamUtil.get(request, "PAR_PRD_NAME_SHOW"); 
        boolean PAR_PRD_NAME_FLAG = true;
        if(!StringUtil.isEmpty(PAR_PRD_NAME_SHOW)){
        	top_sql.append("u.PAR_PRD_NAME,");
        	head_sql.append("p.PAR_PRD_NAME,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	join_on_o.append(" and u.PAR_PRD_NAME=o.PAR_PRD_NAME ");
        	if(join_on_o.indexOf("and") == -1){
            	join_on.append(" and u.CHANN_NO=t2.CHANN_NO ");
        	}
        	group_sql.append("p.PAR_PRD_NAME");
        	PAR_PRD_NAME_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";PAR_PRD_NAME_SQL=ds1.PAR_PRD_NAME");
        	}else{
        		sqlColumn.append(";PAR_PRD_NAME_SQL=ds1.select(PAR_PRD_NAME)");
        	}
        }
        
        //年份
        String YEAR_SHOW = ParamUtil.get(request, "YEAR_SHOW"); 
        boolean YEAR_FLAG = true;
        if(!StringUtil.isEmpty(YEAR_SHOW) || !StringUtil.isEmpty(MONTH_SHOW)){
        	top_sql.append("u.YEAR,");
        	head_sql.append("ym.YEAR,");
        	right_top_sql.append("temp.PLAN_YEAR YEAR,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	
        	if(right_group_sql.indexOf(".")>-1){
        		right_group_sql.append(",");
        	}
        	right_group_sql.append("temp.PLAN_YEAR");
//        	if(join_on_o.indexOf("and") == -1){
//            	join_on.append(" and u.CHANN_NO=t2.CHANN_NO ");
//        	}
        	join_on_o.append(" and u.YEAR=o.YEAR ");
        	join_on.append(" and u.YEAR=t2.YEAR ");
        	group_sql.append("ym.YEAR");
        	YEAR_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";YEAR_SQL=ds1.YEAR");
        	}else{
        		sqlColumn.append(";YEAR_SQL=ds1.select(YEAR)");
        	}
        }
        
        //月份
//        String MONTH_SHOW = ParamUtil.get(request, "MONTH_SHOW"); 
        boolean MONTH_FLAG = true;
        if(!StringUtil.isEmpty(MONTH_SHOW)){
        	top_sql.append("u.MONTH,");
        	head_sql.append("ym.MONTH,");
        	right_top_sql.append("temp.PLAN_MONTH MONTH,");
        	if(group_sql.indexOf(".")>-1){
        		group_sql.append(",");
        	}
        	if(right_group_sql.indexOf(".")>-1){
        		right_group_sql.append(",");
        	}
        	right_group_sql.append("temp.PLAN_MONTH");
//        	if(join_on_o.indexOf("and") == -1){
//            	join_on.append(" and u.CHANN_NO=t2.CHANN_NO ");
//        	}
        	join_on_o.append("and u.MONTH=o.MONTH ");
        	join_on.append(" and u.MONTH=t2.MONTH ");
        	group_sql.append("ym.MONTH");
        	YEAR_FLAG = false;//显示该列
        	MONTH_FLAG = false;//显示该列
        	if(sqlColumn.toString().length()>1){
        		sqlColumn.append(";MONTH_SQL=ds1.MONTH");
        	}else{
        		sqlColumn.append(";MONTH_SQL=ds1.MONTH");
        	}
        	order_sql.append(",u.MONTH");
        }
        
        
//        top_sql.append(" sum((NVL(a.STOREOUT_NUM, 0) * NVL(a.DECT_PRICE, 0))) STOREOUT_AMOUNT, sum((NVL(o.STOREOUT_NUM, 0) * NVL(o.DECT_PRICE, 0))) OLD_STOREOUT_AMOUNT," 
//        		+" (NVL(sum((NVL(a.STOREOUT_NUM, 0) * NVL(a.DECT_PRICE, 0))),0) - nvl(sum((NVL(o.STOREOUT_NUM, 0) * NVL(o.DECT_PRICE, 0))),0)) DIFF_AMOUT,"
//        		+" case when nvl(sum((NVL(o.STOREOUT_NUM, 0) * NVL(o.DECT_PRICE, 0))),0) = 0 then '' "
//        		+" ELSE  trunc(((NVL(sum((NVL(a.STOREOUT_NUM, 0) * NVL(a.DECT_PRICE, 0))),0) / nvl(sum((NVL(o.STOREOUT_NUM, 0) * NVL(o.DECT_PRICE, 0))),0))-1),2)||'%' end "
//        		 );
        
        
		/********** 查询条件拼接 start **********/
 
        right_top_sql.append("sum(NVL(temp.PLAN_SALE_AMOUNT, 0)) PLAN_SALE_AMOUNT");
        String t2 = right_top_sql.toString()+ right_sql.toString();
        if(right_group_sql.length()>3){
        	t2 = t2+" group by "+right_group_sql.toString();
        }
        System.out.println(t2);
        String u = head_sql.toString()+ left_sql.toString()+conSql.toString();
        String o = head_sql.toString()+ left_sql_old.toString()+conSql.toString();
        if(group_sql.length()>3){
        	u = u + " group by "+group_sql.toString();
        	o = o + " group by "+group_sql.toString();
        }  
        System.out.println(u);
        top_sql.append("round( NVL(u.STOREOUT_AMOUNT,0) /10000, 2) STOREOUT_AMOUNT,")
        .append("round( NVL(o.OLD_STOREOUT_AMOUNT,0) /10000, 2) OLD_STOREOUT_AMOUNT,")
        .append("t2.PLAN_SALE_AMOUNT,")
        //top_sql.append(" u.STOREOUT_AMOUNT,o.OLD_STOREOUT_AMOUNT,t2.PLAN_SALE_AMOUNT, ")
        .append("case when NVL(t2.PLAN_SALE_AMOUNT, 0) = 0 then 1  else round(u.STOREOUT_AMOUNT/(t2.PLAN_SALE_AMOUNT*10000),4) end  PLAN_PRIENCE,")
        .append("round((NVL(u.STOREOUT_AMOUNT,0)-NVL(o.OLD_STOREOUT_AMOUNT,0)) /10000, 2) DIFF_AMOUT,")
        .append(" case when NVL(o.OLD_STOREOUT_AMOUNT, 0) = 0 then  0 ")
        .append(" ELSE round(((NVL(u.STOREOUT_AMOUNT,  0) /  nvl(o.OLD_STOREOUT_AMOUNT,  0) ) - 1), 2)")
        .append(" end DIFF_PRIENCE ");
        
        String sql = "select "+top_sql.toString()+"from ("+u+") u "
        +" left join ("+o+") o on 1=1 "+join_on_o.toString()
        +" left join ("+t2+")t2 on 1=1 "+join_on.toString();
        System.out.println(sql);
        
        //String rptSql = " select "+top_sql.toString()+"  from ("+sql+")u group by " + top_group;
        String rptSql =  sql+" where  u.STOREOUT_AMOUNT >0  "+order_sql ;

        String params = "rptSql=" + rptSql
        +";WAREA_FLAG="+WAREA_FLAG
        +";PROV_FLAG="+PROV_FLAG
        +";CITY_FLAG="+CITY_FLAG
        +";AREA_FLAG="+AREA_FLAG
        +";CHANN_FLAG="+CHANN_FLAG
        +";BRAND_FLAG="+BRAND_FLAG
        +";MONTH_FLAG="+MONTH_FLAG
        +";YEAR_FLAG="+YEAR_FLAG
        +";PAR_PRD_NAME_FLAG="+PAR_PRD_NAME_FLAG;
       
        boolean IS_FIRST_SHOW = true;
        if(sqlColumn.toString().length()<1){
        	IS_FIRST_SHOW = false;
        } 
    	params = params 
    	+sqlColumn.toString()
    	+";IS_FIRST_SHOW="+IS_FIRST_SHOW;
        
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", params);
		request.setAttribute("rptModel", "salePlanReport.raq");
		request.setAttribute("printModel", "salePlanReport.raq");
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 月度拜访工作计划达成率
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toMonthvisitReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		String YEAR     = ParamUtil.get(request, "PLAN_YEAR"); 
		String MONTH    = ParamUtil.get(request, "PLAN_MONTH"); 
		String RYMC     = ParamUtil.get(request, "RYMC");
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME");
		StringBuffer sql = new StringBuffer();
//		sql.append("  select en.BMMC,en.XM,en.A1,en.A2,round(en.a2/en.a1,2) A3,en.B1,en.B2,round(en.b2/en.b1,2) B3,(en.a1+en.b1) B4,(en.a2+en.b2) B5,round ((en.a2+en.b2)/(en.a1+en.b1),2) B6,en.CC,en.C1,en.C2,round(en.c2/en.c1,2) C3,round((en.a2+en.b2+en.c2)/(en.a1+en.b1+en.c1),2)C4")
//			.append(" from (" +
					sql.append("select tep_ps.bmmc,tep_ps.xm,tep_ps.YEAR,tep_ps.MONTH,sum(qdsd_jihua.qdsdmb) a1,sum(qd_sd.qdsd) a2,sum(qddh_jihua.qddhmu) b1,sum(qd_dh.qddh) b2,sum(md_zs.storetotal) cc,sum(md_jihua.storemb) c1,sum(md_sj.storesj) c2 ")
			.append(" from (select b.YEAR, b.MONTH, c.rybh, c.xm, c.BMMC from ERP_YEAR_MONTH b " )
			.append(" left join " )
			.append(" ( select f.creator rybh,f.cre_name xm, f.dept_id BMXXID,f.dept_name BMMC   from erp_chann_visit f   where  f.del_flag = 0 group by f.creator,f.cre_name,f.dept_id,f.dept_name)c on 1 = 1  ")
			.append(" where 1=1   ");
		if(!YEAR.isEmpty()){
			sql.append(" and  b.YEAR ='"+YEAR+"'");
		}
		if(!MONTH.isEmpty()){
			sql.append(" and  b.Month in ("+MONTH+")");
		}
		sql.append(" ) tep_ps   left join (select lf.visit_people, count(lf.store_visit_id) storesj from erp_store_visit lf where lf.state = '审核通过' and lf.del_flag = 0");
		if(!YEAR.isEmpty()){
			sql.append(" and to_char(lf.APPLY_DATE, 'YYYY') ='"+YEAR+"' ");
		}
		if(!MONTH.isEmpty()){
			sql.append(" and to_char(lf.APPLY_DATE, 'MM') in ("+MONTH+") ");
		}
		 
	    sql.append("  group by lf.visit_people) md_sj on md_sj.visit_people = tep_ps.xm left join (select qd.visit_people, count(qd.chann_visit_id) qdsd from erp_chann_visit qd where qd.del_flag = 0 and qd.state = '审核通过' and qd.visit_type = '实地拜访'");
		if(!YEAR.isEmpty())	{
			sql.append(" and to_char(qd.APPLY_DATE, 'YYYY') = '"+YEAR+"'");
		}
		if(!MONTH.isEmpty()){
			sql.append(" and to_char(qd.APPLY_DATE, 'MM') in ("+MONTH+")");
		}
	    sql.append(" group by qd.visit_people) qd_sd on qd_sd.visit_people = tep_ps.xm left join (select qd.visit_people, count(qd.chann_visit_id) qddh from erp_chann_visit qd  where qd.del_flag = 0 and qd.state = '审核通过' and qd.visit_type = '电话拜访'");
	    if(!YEAR.isEmpty())	{
			sql.append(" and to_char(qd.APPLY_DATE, 'YYYY') = '"+YEAR+"'");
		}
		if(!MONTH.isEmpty()){
			sql.append(" and to_char(qd.APPLY_DATE, 'MM') in ("+MONTH+")");
		}	
	    sql.append("  group by qd.visit_people) qd_dh on qd_dh.visit_people = tep_ps.xm left join (select zb.rymc, count(mxb.month_visit_plan_dtl_id) qdsdmb from erp_month_visit_plan_dtl mxb left join erp_month_visit_plan zb")
			.append(" on zb.month_visit_plan_id = mxb.month_visit_plan_id where mxb.visit_type = '实地拜访' and mxb.visit_obj_type = '加盟商' and zb.del_flag = 0");
		    if(!YEAR.isEmpty())	{
				sql.append(" and to_char(zb.PLAN_YEAR, '9999') = '"+YEAR+"'");
			}
			if(!MONTH.isEmpty()){
				sql.append(" and to_char(zb.PLAN_MONTH, '09') in ("+MONTH+")");
			}		
	    sql.append("  group by zb.rymc) qdsd_jihua on qdsd_jihua.rymc = tep_ps.xm left join (select zb.rymc, count(mxb.month_visit_plan_dtl_id) qddhmu  from erp_month_visit_plan_dtl mxb left join erp_month_visit_plan zb ")
	       .append("  on zb.month_visit_plan_id = mxb.month_visit_plan_id where mxb.visit_type = '电话拜访' and mxb.visit_obj_type = '加盟商' and zb.del_flag = 0");	       
	       if(!YEAR.isEmpty())	{
				
	    	   sql.append(" and to_char(zb.PLAN_YEAR, '9999') = '"+YEAR+"'");
			}
			if(!MONTH.isEmpty()){
				sql.append(" and to_char(zb.PLAN_MONTH, '09') in ("+MONTH+")");
			}	
	    	sql.append("  group by zb.rymc) qddh_jihua on qddh_jihua.rymc = tep_ps.xm    left join (select zb.rymc,count(mxb.month_visit_plan_dtl_id) storemb")	
	    	   .append("  from erp_month_visit_plan_dtl mxb left join erp_month_visit_plan zb")
			.append(" on zb.month_visit_plan_id = mxb.month_visit_plan_id where mxb.visit_obj_type = '门店' and zb.del_flag = 0");
		    if(!YEAR.isEmpty())	{
				sql.append(" and to_char(zb.PLAN_YEAR, '9999') = '"+YEAR+"'");
			}
			if(!MONTH.isEmpty()){
				sql.append(" and to_char(zb.PLAN_MONTH, '09') in ("+MONTH+")");
			}	
	       sql.append(" group by zb.rymc) md_jihua on md_jihua.rymc = tep_ps.xm left join (select qudao.area_manage_name, count(zd.term_id) storetotal from base_terminal zd left join base_chann qudao")
			.append(" on zd.chann_id_p = qudao.chann_id where zd.del_flag = 0 and qudao.del_flag = 0" +
					" group by qudao.area_manage_name) md_zs on md_zs.area_manage_name = tep_ps.xm  where 1=1 " );
					
	    if(!RYMC.isEmpty()){
//	       sql.append(" where tep_ps.xm = '"+RYMC+"'");
	       sql.append(StringUtil.creCon("tep_ps.xm", RYMC, ","));
	    }
	    if(!WAREA_NAME.isEmpty()){
//	       sql.append(" and tep_ps.bmmc ='"+WAREA_NAME+"'");
	       sql.append(StringUtil.creCon("tep_ps.bmmc", WAREA_NAME, ","));
	    }
        sql.append(" group by tep_ps.xm, tep_ps.bmmc,tep_ps.YEAR,tep_ps.MONTH ");
	    sql.append("  having tep_ps.bmmc like '%战区%'or tep_ps.bmmc like '%直营%'");
//	  是否显示战区这列 true->不显示
	  //是否显示战区这列 true->不显示
		boolean WAREA_FLAG = false;
		boolean RYXX_FLAG  = false;
        boolean YEAR_FLAG  = false;
        boolean MONTH_FLAG = false;
        
        String params = "rptSql=" + sql
        +";WAREA_FLAG="+WAREA_FLAG
        +";RYXX_FLAG="+RYXX_FLAG
        +";YEAR_FLAG="+YEAR_FLAG
        +";MONTH_FLAG="+MONTH_FLAG;
      
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", params);
		request.setAttribute("rptModel",   "MonthvisitreportT.raq");
		request.setAttribute("printModel", "MonthvisitreportT.raq");
		return mapping.findForward("pageResult");
	}
	
	/**
	 * 门店精致化检查分数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toQuerycheckReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		 
		String TERM_NO         = ParamUtil.get(request, "TERM_NO"); 
		String CHECK_DATE_BEG  = ParamUtil.get(request, "CHECK_DATE_BEG"); 
		String CHECK_DATE_END  = ParamUtil.get(request, "CHECK_DATE_END"); 
		
		StringBuffer sql = new StringBuffer();
		sql.append("  select t.term_no,t.Area_Name,t.AREA_NAME_P,t.AREA_MANAGE_NAME,t.CHANN_NAME,t.SALE_STORE_NAME,t.ADDRESS,t.term_refine_check_no,t.Check_Date,")
		   .append("  sum(t.pro) PRO,sum(t.pro1) PRO1,sum(pro2) PRO2, (sum(t.pro)+sum(t.pro1)+sum(t.pro2)) PRO3,t.MYSTIC_CMNR,t.MAIN_DEDUCT_SCORE_REMARK from (")
		   .append("  select  distinct b.term_no,t.AREA_NAME,a.AREA_NAME_P,t.AREA_MANAGE_NAME,t.CHANN_NAME,b.SALE_STORE_NAME,b.ADDRESS,t.TERM_REFINE_CHECK_NO,t.CHECK_DATE,")
		   .append(" (case when t1.prj_name like '%硬装%' then sum(t1.prj_score) else 0 end) pro,")
		   .append(" (case when t1.prj_name like '%软装%' then sum(t1.prj_score) else 0 end) pro1,")
		   .append(" (case when t1.prj_name like '%导购%' then sum(t1.prj_score) else 0 end) pro2,")
		   .append("  t.MYSTIC_CMNR,t.MAIN_DEDUCT_SCORE_REMARK")
		   .append("  from TERM_REFINE_CHECK t LEFT JOIN BASE_AREA a on a.area_id = t.area_id LEFT JOIN BASE_TERMINAL b on b.term_id = t.term_id left join TERM_REFINE_CHECK_DTL t1 on t1.term_refine_check_id = t.term_refine_check_id")
		   .append("  group by t1.prj_name,b.term_no,t.AREA_NAME,a.AREA_NAME_P,t.AREA_MANAGE_NAME,t.CHANN_NAME,b.SALE_STORE_NAME,b.ADDRESS, t.TERM_REFINE_CHECK_NO,t.CHECK_DATE,t.MYSTIC_CMNR,t.MAIN_DEDUCT_SCORE_REMARK )t")
		   .append("   group by  t.term_no,t.Area_Name,t.AREA_NAME_P,t.AREA_MANAGE_NAME,t.CHANN_NAME,t.SALE_STORE_NAME,t.ADDRESS,t.term_refine_check_no,t.Check_Date,t.MYSTIC_CMNR,t.MAIN_DEDUCT_SCORE_REMARK")
		   .append("  having 1=1 ");
		 
		/********** 查询条件拼接 start **********/
		if(!StringUtil.isEmpty(TERM_NO)){
			sql.append(" and t.TERM_NO = '").append(TERM_NO).append("'");
		}
		if(!StringUtil.isEmpty(CHECK_DATE_BEG)){
			sql.append(" and t.CHECK_DATE >=to_date('").append(CHECK_DATE_BEG).append("','yyyy-MM-DD HH24:MI:SS') ");
		}
		if(!StringUtil.isEmpty(CHECK_DATE_END)){
			sql.append(" and t.CHECK_DATE <=to_date('").append(CHECK_DATE_END).append("','yyyy-MM-DD HH24:MI:SS') ");
		}
		//是否显示战区这列 true->不显示
		boolean WAREA_FLAG = false;
		boolean RYXX_FLAG  = false;
        boolean YEAR_FLAG  = false;
        boolean MONTH_FLAG = false;
        
        String params = "rptSql=" + sql
        +";WAREA_FLAG="+WAREA_FLAG
        +";RYXX_FLAG="+RYXX_FLAG
        +";YEAR_FLAG="+YEAR_FLAG
        +";MONTH_FLAG="+MONTH_FLAG;
      
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", params);
		 
	    request.setAttribute("rptModel",   "Termrefinecheck.raq");
		request.setAttribute("printModel", "Termrefinecheck.raq");
		 
		return mapping.findForward("pageResult");
	}
	
	/**
	 * 各战区季度有效门店达成数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toWareaQuarterOpenNumReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		String WAREA_NO = ParamUtil.get(request, "WAREA_NO"); 
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME"); 
		String AREA_NO = ParamUtil.get(request, "AREA_NO"); 
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME"); 
		String MONTH=ParamUtil.get(request, "MONTH"); 
		String YEAR = ParamUtil.get(request, "YEAR"); 
		String PROV = ParamUtil.get(request, "PROV"); 
		StringBuffer sb=new StringBuffer();
		sb.append("select a.AREA_NAME_P,")
		  .append("b.PROV,")
		  .append("a.AREA_NAME,")
		  .append("b.AREA_MANAGE_NAME,")
		  .append("sum(NVL(tq.QUOTA_NUM, 0)) QUOTA_NUM,")//门店目标
		  .append("max(NVL(QUOTA_NUM_M, 0)) QUOTA_NUM_M,")//月门店目标
		  .append("max(NVL(term.TERM_NUM, 0))TERM_NUM ,")//有效门店达成数
		  .append("max(NVL(newterm.NEWTERM_NUM, 0)) NEWTERM_NUM,")//新开门店数量
		  .append("max(NVL(cd.CEDIAN_NUM, 0))CEDIAN_NUM ,")//撤店数
		  .append("max(NVL(fx.ZFX_NUM, 0)) ZFX_NUM ")//转分销数
		  .append(" from BASE_AREA a ")
		  .append(" left join BASE_CHANN b on a.AREA_ID = b.AREA_ID ")
		  .append(" and b.PROV is not null ")
		  .append(" left join ERP_YEAR_MONTH y on 1 = 1 and y.MONTH = '").append(MONTH).append("' ")
		  .append(" left join ERP_CHANN_TERMINAL_QUOTA tq on tq.WAREA_ID = a.AREA_ID_P and tq.YEAR = y.YEAR and tq.DEL_FLAG = 0 ")
		  .append(" left join (select tq.WAREA_ID, ")
		  .append("tq.YEAR,")
		  .append("tq.MONTH,")
		  .append("sum(NVL(tq.QUOTA_NUM, 0)) QUOTA_NUM_M ")
		  .append(" from ERP_CHANN_TERMINAL_QUOTA tq ")
		  .append(" where tq.DEL_FLAG = 0 ")
		  .append(" group by tq.WAREA_ID, tq.YEAR, tq.MONTH) temp on temp.WAREA_ID = a.AREA_ID_P and temp.YEAR = y.YEAR and temp.MONTH = y.MONTH ")
		  .append(" left join (select t.AREA_ID, count(t.TERM_NO) TERM_NUM ")
		  .append(" from BASE_TERMINAL t  where t.DEL_FLAG = 0 and t.STATE = '启用' ")
		  .append(" group by t.AREA_ID) term on term.AREA_ID = a.AREA_ID ")
		  .append(" left join (select t.AREA_ID, count(t.TERM_NO) NEWTERM_NUM ")
		  .append(" from BASE_TERMINAL t ")
		  .append(" where t.DEL_FLAG = 0  and t.STATE = '启用' and to_char(t.BEG_SBUSS_DATE, 'yyyy') = '").append(YEAR).append("' ")
		  .append("and to_char(t.BEG_SBUSS_DATE, 'mm') = '").append(MONTH).append("' ")
		  .append(" group by t.AREA_ID) newterm on newterm.AREA_ID = a.AREA_ID ")
		  .append(" left join (select count(r.PCL_STORE_CC_REQ_NO) CEDIAN_NUM, ")
		  .append("b.AREA_ID ")
		  .append(" from DRP_SPCL_STORE_CC_REQ r ")
		  .append(" left join BASE_CHANN b on b.CHANN_ID = r.CHANN_ID ")
		  .append(" where r.DEL_FLAG = 0 and r.STATE = '审核通过' ")
		  .append(" and to_char(r.SPCL_STORE_CANCEL_DATE, 'yyyy') = '").append(YEAR).append("' ")
		  .append(" and to_char(r.SPCL_STORE_CANCEL_DATE, 'mm') = '").append(MONTH).append("' ")
		  .append(" group by b.AREA_ID) cd on cd.AREA_ID = a.AREA_ID ")
		  .append(" left join (select count(q.SPCL_STORE_TO_RETAIL_REQ_NO) ZFX_NUM,")
		  .append(" b.AREA_ID  ")
		  .append(" from DRP_SPCL_STORE_TO_RETAIL_REQ q ")
		  .append(" left join BASE_CHANN b on b.CHANN_ID = q.CHANN_ID ")
		  .append(" where q.DEL_FLAG = 0 and q.STATE = '审核通过' ")
		  .append(" and to_char(q.SPCL_STORE_TO_RETAIL_DATE, 'yyyy') = '").append(YEAR).append("' ")
		  .append(" and to_char(q.SPCL_STORE_TO_RETAIL_DATE, 'mm') = '").append(MONTH).append("' ")
		  .append(" group by b.AREA_ID) fx on cd.AREA_ID = a.AREA_ID ")
		  .append("  where a.STATE = '启用'  and a.DEL_FLAG = 0 and a.AREA_NAME_P is not null ");
		  
		StringBuffer consql = new StringBuffer();
		 
		/********** 查询条件拼接 start **********/
		 
		if(!StringUtil.isEmpty(PROV)){
			String tempSql = StringUtil.creCon("b.PROV", PROV, ",");
			consql.append(tempSql);
		}
		
		if(!StringUtil.isEmpty(WAREA_NO)){
			String tempSql = StringUtil.creCon("a.AREA_NO_P", WAREA_NO, ",");
			consql.append(tempSql);
		}
		if(!StringUtil.isEmpty(WAREA_NAME)){
			String tempSql = StringUtil.creCon("a.AREA_NAME_P", WAREA_NAME, ",");
			consql.append(tempSql);
		}
		
		if(!StringUtil.isEmpty(AREA_NO)){
			String tempSql = StringUtil.creCon("a.AREA_NO", AREA_NO, ",");
			consql.append(tempSql);
		}
		if(!StringUtil.isEmpty(AREA_NAME)){
			String tempSql = StringUtil.creCon("a.AREA_NAME", AREA_NAME, ",");
			consql.append(tempSql);
		}
		
		if(!StringUtil.isEmpty(YEAR)){
			  consql.append(" and y.YEAR="+YEAR);
		}
		if(!StringUtil.isEmpty(MONTH)){
			  consql.append(" and y.MONTH="+MONTH);
		}
		sb.append(consql.toString())
		  .append(" group by a.AREA_NAME_P, b.PROV, a.AREA_NAME, b.AREA_MANAGE_NAME ");
//		String sql = this.reportService.getWareaQuarterOpenNumSql(consql.toString());
        String params = "rptSql=" + sb.toString()+";arg1="+YEAR+";arg2="+MONTH;
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", params);
	    request.setAttribute("rptModel",   "WareaQuarterOpenNumReport_NEW.raq");
		request.setAttribute("printModel", "WareaQuarterOpenNumReport_NEW.raq");
		return mapping.findForward("pageResult");
	}
	
	/**
	 * 终端门店报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toTerminalOpenReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		String PLAN_YEAR       = ParamUtil.get(request, "PLAN_YEAR"); 
		StringBuffer sql = new StringBuffer();
		sql.append("  select AREA_NAME,OPEN_1,OPEN_2,OPEN_3,OPEN_4,CHANGE1,CHANGE2,CHANGE3,CHANGE4,sum(OPEN_1+OPEN_2+OPEN_3+OPEN_4+CHANGE1+CHANGE2+CHANGE3+CHANGE4) total,")
		   .append("  NO1,NO2,IS_BRANCH_COMPANY_FLAG from (select 1 as NO1,1 as NO2,IS_BRANCH_COMPANY_FLAG,AREA_NAME,OPEN_1,OPEN_2,OPEN_3,OPEN_4,CHANGE1,CHANGE2,")
		   .append("  CHANGE3,CHANGE4 from V_TREM_REPORT where CUR_YEAR='"+PLAN_YEAR+"'  union all select 2 as NO1,1 as NO2,IS_BRANCH_COMPANY_FLAG,'小计' AREA_NAME,")
		   .append("  sum(OPEN_1)OPEN_1,sum(OPEN_2)OPEN_2,sum(OPEN_3)OPEN_3,sum(OPEN_4)OPEN_4,sum(CHANGE1)CHANGE1,sum(CHANGE2)CHANGE2,sum(CHANGE3)CHANGE3,sum(CHANGE4)CHANGE4")
		   .append("  from V_TREM_REPORT where CUR_YEAR='"+PLAN_YEAR+"' group by IS_BRANCH_COMPANY_FLAG union all select 1 as NO1,2 as NO2,0 IS_BRANCH_COMPANY_FLAG,'合计'")
		   .append("  AREA_NAME,sum(OPEN_1)OPEN_1,sum(OPEN_2)OPEN_2,sum(OPEN_3)OPEN_3,sum(OPEN_4)OPEN_4,sum(CHANGE1)CHANGE1,sum(CHANGE2)CHANGE2,sum(CHANGE3)CHANGE3,sum(CHANGE4)CHANGE4")
		   .append("  from V_TREM_REPORT where CUR_YEAR='"+PLAN_YEAR+"') all_data")
		   .append("  group by AREA_NAME,OPEN_1,OPEN_2,OPEN_3,OPEN_4,CHANGE1,CHANGE2,CHANGE3,CHANGE4,NO1,NO2,IS_BRANCH_COMPANY_FLAG")
		   .append("  order by NO2 ASC,IS_BRANCH_COMPANY_FLAG ASC,NO1 ASC");
		
		/********** 查询条件拼接 start **********/
		//是否显示战区这列 true->不显示
		boolean WAREA_FLAG = false;
		boolean RYXX_FLAG  = false;
        boolean YEAR_FLAG  = false;
        boolean MONTH_FLAG = false;
        
        String params = "rptSql=" + sql
        +";WAREA_FLAG="+WAREA_FLAG
        +";RYXX_FLAG="+RYXX_FLAG
        +";YEAR_FLAG="+YEAR_FLAG
        +";MONTH_FLAG="+MONTH_FLAG;
      
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", params);
		 
	    request.setAttribute("rptModel",   "Terminalreq.raq");
		request.setAttribute("printModel", "Terminalreq.raq");
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 拜访达成考核目标
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toVisitTaskAssessReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		
		String WAREA_NO = ParamUtil.get(request, "WAREA_NO"); 
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME"); 
		String AREA_MANAGE_NO = ParamUtil.get(request, "AREA_MANAGE_NO");
		String AREA_MANAGE_NAME = ParamUtil.get(request, "AREA_MANAGE_NAME");
		String PROV = ParamUtil.get(request, "PROV"); 
		String VISIT_OBJ_TYPE = ParamUtil.get(request, "VISIT_OBJ_TYPE"); 
		String YEAR = ParamUtil.get(request, "YEAR"); 
		
		StringBuffer conSql = new StringBuffer();
		
		if(!StringUtil.isEmpty(WAREA_NO)){
			 String tempSql = StringUtil.creCon("b.AREA_NO_P", WAREA_NO, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(WAREA_NAME)){
			String tempSql = StringUtil.creCon("b.AREA_NAME_P", WAREA_NAME, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(AREA_MANAGE_NO)){
			conSql.append(" and b.AREA_MANAGE_NO like '%"+AREA_MANAGE_NO+"%' ");
		}
		if(!StringUtil.isEmpty(AREA_MANAGE_NAME)){
			conSql.append(" and b.AREA_MANAGE_NAME like '%"+AREA_MANAGE_NAME+"%' ");
		}
		if(!StringUtil.isEmpty(PROV)){
			 String tempSql = StringUtil.creCon("b.PROV", PROV, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(VISIT_OBJ_TYPE)){
			conSql.append(" and temp.VISIT_OBJ_TYPE = '"+VISIT_OBJ_TYPE+"' ");
		}
		if(!StringUtil.isEmpty(YEAR)){
			conSql.append(" and temp.PLAN_YEAR = '"+YEAR+"' ");
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("select b.AREA_NAME_P,  b.PROV,  b.AREA_MANAGE_NAME, temp.PLAN_YEAR, temp.VISIT_OBJ_TYPE,  temp.VISIT_TYPE, temp.PLAN_VISIT_NUM,  temp.REAL_VISIT_NUM,")
		.append("(round((temp.REAL_VISIT_NUM / temp.PLAN_VISIT_NUM) * 100, 2)) || '%' PERCENTAGE ")
		.append(" from (select t1.RYXXID,")
        .append("'加盟商拜访' VISIT_OBJ_TYPE,")
        .append("t1.VISIT_TYPE,t1.PLAN_YEAR,")
        .append("sum(t1.PLAN_VISIT_NUM) PLAN_VISIT_NUM,")
        .append("count(v.CHANN_VISIT_ID) REAL_VISIT_NUM ")
        .append(" from (select t.RYXXID, d.VISIT_TYPE, t.PLAN_YEAR,d.PLAN_VISIT_NUM  from ERP_MONTH_VISIT_PLAN t, ERP_MONTH_VISIT_PLAN_DTL d")
        .append(" where t.MONTH_VISIT_PLAN_ID = d.MONTH_VISIT_PLAN_ID")
        .append(" and t.DEL_FLAG = 0")
        .append(" and d.DEL_FLAG = 0")
        .append(" and t.state in ('审核通过', '提交')")
        .append(" and d.VISIT_OBJ_TYPE in ('加盟商')) t1")
        .append(" left join ERP_CHANN_VISIT v")
        .append(" on t1.RYXXID = v.VISIT_PEOPLE_ID")
        .append(" and v.DEL_FLAG = 0")
        .append(" and v.state in ('审核通过')")
        .append(" group by t1.RYXXID, t1.VISIT_TYPE,t1.PLAN_YEAR ")
        .append(" union all ")
        .append(" select t1.RYXXID,")
        .append("'门店拜访' VISIT_OBJ_TYPE,")
        .append("'拜访',t1.PLAN_YEAR,")
        .append("sum(t1.PLAN_VISIT_NUM) PLAN_VISIT_NUM,")
        .append("count(s.STORE_VISIT_ID) REAL_VISIT_NUM ")
		.append(" from (select t.RYXXID,t.PLAN_YEAR, d.VISIT_TYPE, d.PLAN_VISIT_NUM")
		.append(" from ERP_MONTH_VISIT_PLAN t, ERP_MONTH_VISIT_PLAN_DTL d")
		.append(" where t.MONTH_VISIT_PLAN_ID = d.MONTH_VISIT_PLAN_ID")
		.append(" and t.DEL_FLAG = 0")
		.append(" and d.DEL_FLAG = 0")
		.append(" and t.state in ('审核通过', '提交')")
		.append(" and d.VISIT_OBJ_TYPE in ('门店')) t1")
		.append(" left join ERP_STORE_VISIT s")
		.append(" on t1.RYXXID = s.VISIT_PEOPLE_ID")
		.append(" and s.DEL_FLAG = 0")
		.append(" and s.state in ('审核通过')")
		.append(" group by t1.RYXXID,t1.PLAN_YEAR) temp")
		.append(" left join (select c.AREA_MANAGE_NAME,  c.AREA_MANAGE_ID,   c.PROV,a.AREA_NO_P,  a.AREA_NAME_P")
		.append(" from BASE_CHANN c")
		.append(" left join BASE_AREA a")
		.append(" on c.AREA_ID = a.AREA_ID where c.STATE = '启用'  and c.DEL_FLAG = 0 and a.DEL_FLAG = 0 group by c.AREA_MANAGE_NAME,")
		.append(" c.AREA_MANAGE_ID,  c.PROV,a.AREA_NO_P, a.AREA_NAME_P) b")
		.append(" on b.AREA_MANAGE_ID = temp.RYXXID ")
		.append(" where b.AREA_NAME_P is not null ")
		.append(conSql.toString());
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql);
		 
	    request.setAttribute("rptModel",   "visitTaskAssessReport.raq");
		request.setAttribute("printModel", "visitTaskAssessReport.raq");
		return mapping.findForward("pageResult");
	}
	
	
	

	/**
	 * 活动销售报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toMarketSaleReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		
		String MARKETING_ACT_NO = ParamUtil.get(request, "MARKETING_ACT_NO"); 
		String MARKETING_ACT_NAME = ParamUtil.get(request, "MARKETING_ACT_NAME"); 
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String BRAND = ParamUtil.get(request, "BRAND"); 
		String PRD_TYPE = ParamUtil.get(request, "PRD_TYPE"); 
		String SALE_DATE_BEG = ParamUtil.get(request, "SALE_DATE_BEG"); 
		String SALE_DATE_END = ParamUtil.get(request, "SALE_DATE_END"); 
		
		StringBuffer conSql = new StringBuffer();
		
		if(!StringUtil.isEmpty(MARKETING_ACT_NO)){
			 String tempSql = StringUtil.creCon("v.MARKETING_ACT_NO", MARKETING_ACT_NO, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(MARKETING_ACT_NAME)){
			String tempSql = StringUtil.creCon("v.MARKETING_ACT_NAME", MARKETING_ACT_NAME, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(CHANN_NO)){
			conSql.append(StringUtil.creCon("v.CHANN_NO", CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(CHANN_NAME)){
			conSql.append(StringUtil.creCon("v.CHANN_NAME", CHANN_NAME, ","));
		}
		if(!StringUtil.isEmpty(BRAND)){
			String tempSql = StringUtil.creEqualCon("v.BRAND", BRAND, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(PRD_TYPE)){
			String tempSql = StringUtil.creEqualCon("v.PRD_TYPE", PRD_TYPE, ",");
			conSql.append(tempSql);
		}
		
		if(!StringUtil.isEmpty(SALE_DATE_BEG)){
			conSql.append(" and v.SALE_DATE >= '").append(SALE_DATE_BEG).append("' ");
		}
		if(!StringUtil.isEmpty(SALE_DATE_END)){
			conSql.append(" and v.SALE_DATE <= '").append(SALE_DATE_END).append("' ");
		}
		
		 
		StringBuffer sql = new StringBuffer(" select * from  v_market_sale_report v where 1=1 ");
		sql.append(conSql.toString());
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql);
		 
	    request.setAttribute("rptModel",   "MarketSaleReport.raq");
		request.setAttribute("printModel", "MarketSaleReport.raq");
		return mapping.findForward("pageResult");
	}
	
	

	/**
	 * 活动提成查询报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toMarketCommReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		
		String MARKETING_ACT_NO = ParamUtil.get(request, "MARKETING_ACT_NO"); 
		String MARKETING_ACT_NAME = ParamUtil.get(request, "MARKETING_ACT_NAME"); 
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String SALE_PSON_NAME = ParamUtil.get(request, "SALE_PSON_NAME"); 
 
		
		StringBuffer conSql = new StringBuffer();
		
		if(!StringUtil.isEmpty(MARKETING_ACT_NO)){
			 String tempSql = StringUtil.creCon("v.MARKETING_ACT_NO", MARKETING_ACT_NO, ",");
			 conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(MARKETING_ACT_NAME)){
			String tempSql = StringUtil.creCon("v.MARKETING_ACT_NAME", MARKETING_ACT_NAME, ",");
			conSql.append(tempSql);
		}
		if(!StringUtil.isEmpty(CHANN_NO)){
			conSql.append(StringUtil.creCon("v.CHANN_NO", CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(CHANN_NAME)){
			conSql.append(StringUtil.creCon("v.CHANN_NAME", CHANN_NAME, ","));
		}
		if(!StringUtil.isEmpty(SALE_PSON_NAME)){
			conSql.append(" and v.SALE_PSON_NAME like '%"+SALE_PSON_NAME+"%' ");
		}
		 
		StringBuffer sql = new StringBuffer(" select * from  V_MARKET_COMM_REPORT v where 1=1 ");
		sql.append(conSql.toString());
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql);
		 
	    request.setAttribute("rptModel",   "MarketActCommissionReport.raq");
		request.setAttribute("printModel", "MarketActCommissionReport.raq");
		return mapping.findForward("pageResult");
	}
	
	
	public ActionForward toSaleRanking(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		String START_SALE_DATE = ParamUtil.get(request, "START_SALE_DATE");
		String END_SALE_DATE = ParamUtil.get(request, "END_SALE_DATE");
		String ADVC_ORDER_NO = ParamUtil.get(request, "ADVC_ORDER_NO");
		String PROMOTE_NO = ParamUtil.get(request, "PROMOTE_NO");
		String PROMOTE_NAME = ParamUtil.get(request, "PROMOTE_NAME");
		String PRD_NO=ParamUtil.get(request, "PRD_NO");
		String PRD_NAME=ParamUtil.get(request, "PRD_NAME");
		String PAR_PRD_NAME=ParamUtil.get(request, "PAR_PRD_NAME");
		String CRE_NAME=ParamUtil.get(request, "CRE_NAME");
		String  TERM_NAME = ParamUtil.get(request, "TERM_NAME");
		String  CUST_NAME = ParamUtil.get(request, "CUST_NAME");
		String  CONTRACT_NO = ParamUtil.get(request, "CONTRACT_NO");
		String QUERYFLAG=ParamUtil.get(request, "QUERYFLAG");//1货品 2导购 3品类 4门店
		String topNum=ParamUtil.get(request, "topNum");
		if(StringUtil.isEmpty(QUERYFLAG)){
			QUERYFLAG="1";
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select t.TERM_NO,")
		   .append("t.SALE_PSON_NAME,")
		   .append("t.TERM_NAME,")
		   .append("d.PRD_NO,")
		   .append("d.PRD_NAME,")
		   .append("e.PAR_PRD_NO,")
		   .append("e.PAR_PRD_NAME,")
		   .append("sum(NVL(d.SEND_NUM, 0)) SEND_NUM,")
		   .append("sum(NVL(d.SEND_NUM, 0) * decode(d.PRD_TYPE, '赠品', 0, NVL(d.DECT_PRICE, 0))) SEND_AMOUNT,")
		   .append("sum(NVL(d.SEND_NUM, 0) * NVL(u.COST_PRICE, 0)) COST_AMOUNT,spcl.SPCL_DTL_REMARK ")
		   .append(" from DRP_ADVC_ORDER t ")
		   .append(" left join DRP_ADVC_ORDER_DTL d on t.ADVC_ORDER_ID = d.ADVC_ORDER_ID and d.DEL_FLAG = 0 ")
		   .append(" left join BASE_PRODUCT e on e.PRD_ID = d.PRD_ID ")
		   .append(" left join V_PRODUCT_PRVD_PRICE u on d.PRD_ID = u.PRD_ID and NVL(d.SPCL_TECH_ID, 'NONE') = NVL(u.SPCL_TECH_ID, 'NONE')")
		   .append(" left join DRP_SPCL_TECH spcl on spcl.SPCL_TECH_ID = d.SPCL_TECH_ID  and spcl.USE_FLAG = 1")
		   .append(" where t.DEL_FLAG = 0 and d.STATE = '正常' and t.STATE in ('审核通过', '待确认', '已发货', '待退货', '已退货') ")
		   .append(" and t.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("' ");
		   
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer("");

		if (!StringUtil.isEmpty(START_SALE_DATE)) {
			condition.append(" and t.SALE_DATE >=to_date('" + START_SALE_DATE + "','yyyy-mm-dd')");
		}
		if (!StringUtil.isEmpty(END_SALE_DATE)) {
			condition.append(" and t.SALE_DATE<=to_date('" + END_SALE_DATE + "','yyyy-mm-dd')");
		}
		if (!StringUtil.isEmpty(ADVC_ORDER_NO)) {
			condition.append(" and t.ADVC_ORDER_NO like '%" + ADVC_ORDER_NO + "%'");
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			condition.append(StringUtil.creCon("d.PRD_NO", PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			condition.append(StringUtil.creCon("d.PRD_NAME", PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
			condition.append(StringUtil.creCon("e.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(CRE_NAME)){
			condition.append(StringUtil.creCon("t.CRE_NAME", CRE_NAME, ","));
		}
		if(!StringUtil.isEmpty(TERM_NAME)){
			condition.append(StringUtil.creCon("t.TERM_NAME", TERM_NAME, ","));
		}
		if(!StringUtil.isEmpty(PROMOTE_NO)){
			condition.append(StringUtil.creCon("t.PROMOTE_NO", PROMOTE_NO, ","));
		}
		if(!StringUtil.isEmpty(PROMOTE_NAME)){
			condition.append(StringUtil.creCon("t.PROMOTE_NAME", PROMOTE_NAME, ","));
		}
		if(!StringUtil.isEmpty(CONTRACT_NO)){
			condition.append(StringUtil.creCon("t.CONTRACT_NO", CONTRACT_NO, ","));
		}
		if(!StringUtil.isEmpty(CUST_NAME)){
			condition.append(StringUtil.creCon("t.CUST_NAME", CUST_NAME, ","));
		}
		condition.append(" and t.TERM_ID in ").append(userBean.getTERM_CHARGE());
		sql.append(condition.toString())
		   .append(" group by t.TERM_NO, ")
		   .append("t.SALE_PSON_NAME,")
		   .append("t.TERM_NAME,")
		   .append("d.PRD_NO,")
		   .append("d.PRD_NAME,")
		   .append("e.PAR_PRD_NO,")
		   .append("e.PAR_PRD_NAME, ")
		   .append("spcl.SPCL_DTL_REMARK ")
		   .append("  order by sum(NVL(d.send_num, 0) * decode(d.prd_type, '赠品', 0, NVL(d.dect_price, 0))) desc ");
		StringBuffer str=new StringBuffer();
		StringBuffer rowName=new StringBuffer();
		if(!"1".equals(QUERYFLAG)){//通用/门店
			str.append(" select temp.TERM_NO,")
			   .append("temp.TERM_NAME,")
			   .append("sum(NVL(temp.SEND_NUM,0))SEND_NUM,")
			   .append("sum(NVL(temp.SEND_AMOUNT,0))SEND_AMOUNT,")
			   .append("sum(NVL(temp.COST_AMOUNT))COST_AMOUNT ");
			if("2".equals(QUERYFLAG)){//导购
				str.append(",temp.SALE_PSON_NAME ");
				rowName.append("SALE_PSON_NAME=ds1.SALE_PSON_NAME;");
			}else if("3".equals(QUERYFLAG)){//货品分类
				str.append(",temp.PAR_PRD_NO,temp.PAR_PRD_NAME ");
				rowName.append("PAR_PRD_NAME=ds1.PAR_PRD_NAME;");
			}
			str.append(" from (");
		}else{
			rowName.append("SALE_PSON_NAME=ds1.SALE_PSON_NAME;PRD_NO=ds1.PRD_NO;PRD_NAME=ds1.PRD_NAME;SPCL_DTL_REMARK=ds1.SPCL_DTL_REMARK;PAR_PRD_NAME=ds1.PAR_PRD_NAME;");
		}
		str.append(sql.toString());
		if(!"1".equals(QUERYFLAG)){
			str.append(" )temp group by temp.TERM_NO,temp.TERM_NAME ");
			if("2".equals(QUERYFLAG)){
				str.append(",temp.SALE_PSON_NAME ");
			}else if("3".equals(QUERYFLAG)){//货品分类
				str.append(",temp.PAR_PRD_NO,temp.PAR_PRD_NAME ");
			}
			str.append(" order by sum(NVL(temp.SEND_AMOUNT,0)) desc ");
		}
		if(!StringUtil.isEmpty(topNum)){
			request.setAttribute("conDition", "rptSql=select * from ("+sql+") where rownum<="+topNum+";"+rowName+"arg1="+QUERYFLAG );
		}else{
			request.setAttribute("conDition", "rptSql=" + sql+";"+rowName+"arg1="+QUERYFLAG);
		}
		
		 
	    request.setAttribute("rptModel",   "saleRanking.raq");
		request.setAttribute("printModel", "saleRanking.raq");
		return mapping.findForward("pageResult");
	}
	
	/***
	 * 总部对账单查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAccountReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String SHIP_POINT_NO = ParamUtil.get(request, "SHIP_POINT_NO");
		String YEAR = ParamUtil.get(request, "YEAR");
		String MONTH = ParamUtil.get(request, "MONTH");
		String SAEL_DATE_BENG = ParamUtil.get(request, "SAEL_DATE_BENG");
		String SAEL_DATE_END = ParamUtil.get(request, "SAEL_DATE_END");
		if(StringUtil.isEmpty(SHIP_POINT_NO)){
			SHIP_POINT_NO = " ";//组织为空 传递一个空格
		}
		String MonthStartDate = ParamUtil.get(request, "MonthStartDate")+"-01";
		MonthStartDate = DateUtil.format(DateUtil.parseDate(MonthStartDate),"yyyy-MM-dd HH:mm:ss");
//		String MonthStartDate = DateUtil.format(DateUtil.parseDate("2014-05-01"),"yyyy-MM-dd");
		Map<String,String>params = new HashMap<String,String>();
	    params.put("CustCode", CHANN_NO);
	    params.put("MonthStartDate", MonthStartDate);
	    params.put("OrgCode", SHIP_POINT_NO);
	    String strJsonData =  txQueryPayMentDetails(params);//入参数
	    String jsonStr = null;//出参数
		try {
			jsonStr = InterfaceInvokeUtil.queryPayMentDetails(strJsonData);
			LogicUtil.txUpdatePayMentDetail(CHANN_NO,MonthStartDate,jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select CHANN_NO, ")
		   .append("CHANN_NAME,")
		   .append("LEDGER_NO,")
		   .append("LEDGER_NAME,")
		   .append("ACCT_DATE,")
		   .append("ACCT_TIME,")
		   .append("DOC_NO,")
		   .append("MEMO,")
		   .append("BILL_TYPE,")
		   .append("BILL_NO,")
		   .append("CUR_PAYMENT,")
		   .append("CUR_REV_PAYMENT,")
		   .append("CUR_LEFT_PAYMENT,CUST_NAME ")
		   .append(" from ERP_PAYMENT_STATEMENT where 1=1 ");
		if(!StringUtil.isEmpty(CHANN_NO)){
			sql.append(" and CHANN_NO like '%").append(CHANN_NO).append("%' ");
		}
//		if(!StringUtil.isEmpty(CHANN_NAME)){
//			sql.append(" and CHANN_NAME like '%").append(CHANN_NAME).append("%' ");
//		}
		if(!StringUtil.isEmpty(YEAR)){
			sql.append(" and substr(ACCT_DATE,0,4) = '").append(YEAR).append("' ");
		}
		if(!StringUtil.isEmpty(MONTH)){
			sql.append(" and substr(ACCT_DATE,6,2) = '").append(MONTH).append("' ");
		}
		if (!StringUtil.isEmpty(SAEL_DATE_BENG)) {
			sql.append(" and to_date(ACCT_DATE,'yyyy-mm') >=to_date('" + SAEL_DATE_BENG + "','yyyy-mm-dd')");
		}
		if (!StringUtil.isEmpty(SAEL_DATE_END)) {
			sql.append(" and to_date(ACCT_DATE,'yyyy-mm')<=to_date('" + SAEL_DATE_END + "','yyyy-mm-dd')");
		}
		
//		if(!StringUtil.isEmpty(MonthStartDate)){
//			sql.append(" and to_date(ACCT_DATE,'yyyy-mm')<=to_date('" + MonthStartDate + "','yyyy-mm-dd')");
//		}
		
		sql.append(" order by ACCT_DATE ");
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "AccountsReport.raq");
		request.setAttribute("printModel", "AccountsReport.raq");
		return mapping.findForward("pageResult");
	}
	
	

 
	
	public String txQueryPayMentDetails(Map<String,String> map) {
		
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", "TR0200040");
		headMap.put("ServiceCode", "ReportQuery");
		headMap.put("Operation","queryPayMentDetails");
		headMap.put("AppCode","DM");
		headMap.put("DestCode","UA");
		headMap.put("TransType", "0");
		headMap.put("UId", StringUtil.uuid32len());
		
		LinkedHashMap bodyMap = new LinkedHashMap();
		bodyMap.putAll(map);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}

	
	
	/***
	 * 直营办--销售情况统计表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toSaleaOrderReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		String ORDER_DATE_BENG = ParamUtil.get(request, "ORDER_DATE_BENG");
		String ORDER_DATE_END = ParamUtil.get(request, "ORDER_DATE_END");
		String PAR_PRD_NO = ParamUtil.get(request, "PAR_PRD_NO");
		String PAR_PRD_NAME = ParamUtil.get(request, "PAR_PRD_NAME");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String ORDER_CHANN_NO=ParamUtil.get(request, "ORDER_CHANN_NO");
		String ORDER_CHANN_NAME=ParamUtil.get(request, "ORDER_CHANN_NAME");
		String SALE_ORDER_NO=ParamUtil.get(request, "SALE_ORDER_NO");
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.SALE_ORDER_NO, ")
		   .append("a.ORDER_CHANN_NO,")
		   .append("a.RSP_NAME ORDER_CHANN_NAME,")
		   .append("to_char(a.ORDER_DATE,'yyyy-mm-dd')ORDER_DATE,")
		   .append("b.PRD_NO,")
		   .append("b.PRD_NAME,")
		   .append("b.PRD_SPEC,")
		   .append("b.BRAND,")
		   .append("b.ORDER_NUM,")
		   .append("b.ORDER_AMOUNT,")
		   .append("b.SENDED_NUM,")
		   .append("b.PRICE,")
		   .append("c.SPCL_DTL_REMARK,d.PAR_PRD_NAME ")
		   .append(" from DRP_SALE_ORDER_A a  ")
		   .append(" left join DRP_SALE_ORDER_DTL_A b on a.SALE_ORDER_ID=b.SALE_ORDER_ID and b.DEL_FLAG=0 ")
		   .append(" left join DRP_SPCL_TECH c on c.SPCL_TECH_ID=b.SPCL_TECH_ID and c.USE_FLAG=1 ")
		   .append(" left join BASE_PRODUCT d on d.PRD_ID=b.PRD_ID and b.DEL_FLAG=0 where a.DEL_FLAG=0 and a.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("' ");
		if (!StringUtil.isEmpty(ORDER_DATE_BENG)) {
			sql.append(" and a.ORDER_DATE >=to_date('" + ORDER_DATE_BENG + "','yyyy-mm-dd')");
		}
		if (!StringUtil.isEmpty(ORDER_DATE_END)) {
			sql.append(" and a.ORDER_DATE<=to_date('" + ORDER_DATE_END + "','yyyy-mm-dd')");
		}
		if(!StringUtil.isEmpty(PAR_PRD_NO)){
			sql.append(StringUtil.creCon("d.PAR_PRD_NO", PAR_PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
			sql.append(StringUtil.creCon("d.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			sql.append(StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			sql.append(StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(ORDER_CHANN_NO)){
			sql.append(StringUtil.creCon("a.ORDER_CHANN_NO", ORDER_CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(ORDER_CHANN_NAME)){
			sql.append(StringUtil.creCon("a.RSP_NAME", ORDER_CHANN_NAME, ","));
		}
		if(!StringUtil.isEmpty(SALE_ORDER_NO)){
			sql.append(StringUtil.creCon("a.SALE_ORDER_NO", SALE_ORDER_NO, ","));
		}
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "saleaOrder.raq");
		request.setAttribute("printModel", "saleaOrder.raq");
		return mapping.findForward("pageResult");
	}
	
	/***
	 * 直营办--发货情况统计表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toStoreoutaReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		String DEAL_TIME_BENG = ParamUtil.get(request, "DEAL_TIME_BENG");
		String DEAL_TIME_END = ParamUtil.get(request, "DEAL_TIME_END");
		String PAR_PRD_NO = ParamUtil.get(request, "PAR_PRD_NO");
		String PAR_PRD_NAME = ParamUtil.get(request, "PAR_PRD_NAME");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String RECV_CHANN_NO=ParamUtil.get(request, "RECV_CHANN_NO");
		String RECV_CHANN_NAME=ParamUtil.get(request, "RECV_CHANN_NAME");
		String STOREOUT_NO=ParamUtil.get(request, "STOREOUT_NO");
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.STOREOUT_NO, ")
		   .append("a.RECV_CHANN_NO,")
		   .append("a.RSP_NAME RECV_CHANN_NAME,")
		   .append("to_char(a.DEAL_TIME,'yyyy-mm-dd')DEAL_TIME,")
		   .append("b.PRD_NO,")
		   .append("b.PRD_NAME,")
		   .append("b.PRD_SPEC,")
		   .append("b.BRAND,")
		   .append("b.NOTICE_NUM,")
		   .append("b.DECT_AMOUNT,")
		   .append("b.PRICE,")
		   .append("c.SPCL_DTL_REMARK,d.PAR_PRD_NAME ")
		   .append(" from DRP_STOREOUT_A a  ")
		   .append(" left join DRP_STOREOUT_DTL_A b on a.STOREOUT_ID=b.STOREOUT_ID and b.DEL_FLAG=0 ")
		   .append(" left join DRP_SPCL_TECH c on c.SPCL_TECH_ID=b.SPCL_TECH_ID and c.USE_FLAG=1 ")
		   .append(" left join BASE_PRODUCT d on d.PRD_ID=b.PRD_ID and b.DEL_FLAG=0 where a.DEL_FLAG=0 and a.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("' ");
		if (!StringUtil.isEmpty(DEAL_TIME_BENG)) {
			sql.append(" and a.DEAL_TIME >=to_date('" + DEAL_TIME_BENG + "','yyyy-mm-dd')");
		}
		if (!StringUtil.isEmpty(DEAL_TIME_END)) {
			sql.append(" and a.DEAL_TIME<=to_date('" + DEAL_TIME_END + "','yyyy-mm-dd')+1");
		}
		if(!StringUtil.isEmpty(PAR_PRD_NO)){
			sql.append(StringUtil.creCon("d.PAR_PRD_NO", PAR_PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
			sql.append(StringUtil.creCon("d.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			sql.append(StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			sql.append(StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(RECV_CHANN_NO)){
			sql.append(StringUtil.creCon("a.RECV_CHANN_NO", RECV_CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(RECV_CHANN_NAME)){
			sql.append(StringUtil.creCon("a.RSP_NAME", RECV_CHANN_NAME, ","));
		}
		if(!StringUtil.isEmpty(STOREOUT_NO)){
			sql.append(StringUtil.creCon("a.STOREOUT_NO", STOREOUT_NO, ","));
		}
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "storeouta.raq");
		request.setAttribute("printModel", "storeouta.raq");
		return mapping.findForward("pageResult");
	}
	
	/***
	 * 直营办--退货情况统计表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toReturnaReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		String CRE_TIME_BENG = ParamUtil.get(request, "CRE_TIME_BENG");
		String CRE_TIME_END = ParamUtil.get(request, "CRE_TIME_END");
		String PAR_PRD_NO = ParamUtil.get(request, "PAR_PRD_NO");
		String PAR_PRD_NAME = ParamUtil.get(request, "PAR_PRD_NAME");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String RETURN_CHANN_NO=ParamUtil.get(request, "RETURN_CHANN_NO");
		String RETURN_CHANN_NAME=ParamUtil.get(request, "RETURN_CHANN_NAME");
		String PRD_RETURN_NO=ParamUtil.get(request, "PRD_RETURN_NO");
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.PRD_RETURN_NO, ")
		   .append("a.RETURN_CHANN_NO,")
		   .append("a.RSP_NAME RETURN_CHANN_NAME,")
		   .append("to_char(a.CRE_TIME,'yyyy-mm-dd')CRE_TIME,")
		   .append("b.PRD_NO,")
		   .append("b.PRD_NAME,")
		   .append("b.PRD_SPEC,")
		   .append("b.BRAND,")
		   .append("c.SPCL_DTL_REMARK, ")
		   .append("b.RETURN_NUM,")
		   .append("b.RETURN_PRICE,")
		   .append("b.RETURN_AMOUNT,d.PAR_PRD_NAME ")
		   .append(" from DRP_RETURN_A a  ")
		   .append(" left join DRP_RETURN_DTL_A b on a.PRD_RETURN_ID=b.PRD_RETURN_ID and b.DEL_FLAG=0 ")
		   .append(" left join DRP_SPCL_TECH c on c.SPCL_TECH_ID=b.SPCL_TECH_ID and c.USE_FLAG=1 ")
		   .append(" left join BASE_PRODUCT d on d.PRD_ID=b.PRD_ID and b.DEL_FLAG=0 where a.DEL_FLAG=0 and a.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("' ");
		if (!StringUtil.isEmpty(CRE_TIME_BENG)) {
			sql.append(" and a.CRE_TIME >=to_date('" + CRE_TIME_BENG + "','yyyy-mm-dd')");
		}
		if (!StringUtil.isEmpty(CRE_TIME_END)) {
			sql.append(" and a.CRE_TIME<=to_date('" + CRE_TIME_END + "','yyyy-mm-dd')+1");
		}
		if(!StringUtil.isEmpty(PAR_PRD_NO)){
			sql.append(StringUtil.creCon("d.PAR_PRD_NO", PAR_PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
			sql.append(StringUtil.creCon("d.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			sql.append(StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			sql.append(StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(RETURN_CHANN_NO)){
			sql.append(StringUtil.creCon("a.RETURN_CHANN_NO", RETURN_CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(RETURN_CHANN_NAME)){
			sql.append(StringUtil.creCon("a.RSP_NAME", RETURN_CHANN_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_RETURN_NO)){
			sql.append(StringUtil.creCon("a.PRD_RETURN_NO", PRD_RETURN_NO, ","));
		}
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "returna.raq");
		request.setAttribute("printModel", "returna.raq");
		return mapping.findForward("pageResult");
	}
	
	/***
	 * 直营办--退货情况统计表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toInvocAcctaReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		String YEAR = ParamUtil.get(request, "YEAR");
		String MONTH = ParamUtil.get(request, "MONTH");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String STORE_NO = ParamUtil.get(request, "STORE_NO");
		String STORE_NAME = ParamUtil.get(request, "STORE_NAME");
		String PRD_TYPE_NAME=ParamUtil.get(request, "PRD_TYPE_NAME");
		String getherFlag=ParamUtil.get(request, "getherFlag");//汇总标记
		StringBuffer query=new StringBuffer();//条件
		
		query.append(" where a.LEDGER_ID='").append(userBean.getLoginZTXXID()).append("' ");
		if (!StringUtil.isEmpty(YEAR)) {
			if("3".equals(getherFlag)){
				query.append(" and substr(a.YEAR_MONTH,0,4) ='").append(YEAR).append("'");
			}else{
				query.append(" and a.YEAR ='").append(YEAR).append("'");
			}
		}
		if (!StringUtil.isEmpty(MONTH)) {
			if("3".equals(getherFlag)){
				query.append(" and substr(a.YEAR_MONTH,6)='").append(MONTH).append("'");
			}else{
				query.append(" and a.MONTH='").append(MONTH).append("'");
			}
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			query.append(StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			query.append(StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
		}
		if(!StringUtil.isEmpty(STORE_NO)){
			query.append(StringUtil.creCon("d.STORE_NO", STORE_NO, ","));
		}
		if(!StringUtil.isEmpty(STORE_NAME)){
			query.append(StringUtil.creCon("d.STORE_NAME", STORE_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_TYPE_NAME)){
			query.append(StringUtil.creCon("b.PAR_PRD_NAME", PRD_TYPE_NAME, ","));
		}
		
		StringBuffer sql=new StringBuffer();
		
		if(!"3".equals(getherFlag)){
			sql.append("select ")
			   .append("a.PER_LEFT_NUM,")
			   .append("a.PER_LEFT_PRICE,")
			   .append("a.PER_LEFT_AMOUNT,")
			   .append("a.CUR_END_NUM,")//存数量
			   .append("a.CUR_END_PRICE,")
			   .append("a.CUR_END_AMOUNT,")//存金额
			   .append("a.CUR_PUR_NUM,")
			   .append("a.CUR_PUR_PRICE,")
			   .append("a.CUR_PUR_AMOUNT,")
			   .append("a.CUR_TERM_SALE_NUM,")
			   .append("a.CUR_TERM_SALE_PRICE,")
			   .append("a.CUR_TERM_SALE_AMOUNT,")
			   .append("a.CUR_OTHER_SALE_NUM,")
			   .append("a.CUR_OTHER_SALE_PRICE,")
			   .append("a.CUR_OTHER_SALE_AMOUNT,")
			   .append("a.CUR_DUMP_OUT_NUM,")
			   .append("a.CUR_DUMP_OUT_PRICE,")
			   .append("a.CUR_DUMP_OUT_AMOUNT,")
			   .append("a.CUR_DUMP_IN_NUM,")
			   .append("a.CUR_DUMP_IN_PRICE,")
			   .append("a.CUR_DUMP_IN_AMOUNT,")
			   .append("a.CUR_RETUN_NUM,")
			   .append("a.CUR_RETUN_PRICE,")
			   .append("a.CUR_RETUN_AMOUNT,")
			   .append("a.CUR_REPAIR_NUM,")
			   .append("a.CUR_REPAIR_PRICE,")
			   .append("a.CUR_REPAIR_AMOUNT,")
			   .append("a.CUR_CUST_RETURN_NUM,")
			   .append("a.CUR_CUST_RETURN_PRICE,")
			   .append("a.CUR_CUST_RETURN_AMOUNT,")
			   .append("a.CUR_INV_ADD_NUM,")
			   .append("a.CUR_INV_ADD_PRICE,")
			   .append("a.CUR_INV_ADD_AMOUNT,")
			   .append("a.CUR_INV_REDUCE_NUM,")
			   .append("a.REMARK,")
			   .append("b.PRD_NO,")
			   .append("b.PRD_NAME,")
			   .append("b.PRD_SPEC,")
			   .append("b.STD_UNIT,")
			   .append("d.STORE_NO,")
			   .append("d.STORE_NAME,")
			   .append("a.CUR_QUO_AMOUNT,")
			   .append("a.CUR_QUO_PRICE,")
			   .append("a.CUR_QUO_NUM,")
			   .append("a.CUR_REAL_RETUN_AMOUNT,")
			   .append("a.CUR_INV_REDUCE_AMOUNT,")
			   .append("a.CUR_INV_REDUCE_PRICE,")
			   .append("a.CUR_INV_REDUCE_NUM,")
			   .append("a.CUR_REAL_REPAIR_AMOUNT,")
			   .append("a.CUR_OTHER_REAL_SALE_AMOUNT,")
			   .append("a.CUR_TERM_REAL_SALE_AMOUNT,")
			   .append("a.CUR_REPAIR_BACK_AMOUNT,")
			   .append("a.CUR_REPAIR_BACK_PRICE,")
			   .append("a.CUR_REPAIR_BACK_NUM,")
			   .append("a.CUR_OTHER_RETURN_AMOUNT,")
			   .append("a.CUR_OTHER_RETURN_PRICE,")
			   .append("a.CUR_OTHER_RETURN_NUM,")
			   .append("a.CUR_FEW_STOREOUT_NUM, ")//零星出库数量
			   .append("a.CUR_FEW_STOREOUT_PRICE,")
			   .append("a.CUR_FEW_STOREOUT_AMOUNT,")
			   .append("NVL(a.TO_SALE_COST,0)TO_SALE_COST,")
			   .append("c.SPCL_DTL_REMARK, ")
			   .append("b.PAR_PRD_NAME,")
			   .append("a.CUR_COST_ADJUST_AMOUNT,")
			   .append("NVL(NVL(a.CUR_PUR_NUM,0)+NVL(a.CUR_CUST_RETURN_NUM,0)+NVL(a.CUR_OTHER_RETURN_NUM,0)+NVL(a.CUR_REPAIR_BACK_NUM,0)+NVL(a.CUR_DUMP_IN_NUM,0)+NVL(a.CUR_INV_ADD_NUM,0),0)COLLECTNUM,")//收数量
			   .append("NVL(NVL(a.CUR_PUR_AMOUNT,0)+NVL(a.CUR_CUST_RETURN_AMOUNT,0)+NVL(a.CUR_OTHER_RETURN_AMOUNT,0)+NVL(a.CUR_REPAIR_BACK_AMOUNT,0)+NVL(a.CUR_DUMP_IN_AMOUNT,0)+NVL(a.CUR_INV_ADD_AMOUNT,0),0)COLLECTAMOUNT,")//收金额
			   .append("NVL(NVL(a.CUR_TERM_SALE_NUM,0)+NVL(a.CUR_OTHER_SALE_NUM,0)+NVL(a.CUR_RETUN_NUM,0)+NVL(a.CUR_REPAIR_NUM,0)+NVL(a.CUR_DUMP_OUT_NUM,0)+NVL(a.CUR_INV_REDUCE_NUM,0)+ NVL(a.CUR_FEW_STOREOUT_NUM, 0),0)DISPATCHNUM,")//发数量
			   .append("NVL(NVL(a.CUR_TERM_SALE_AMOUNT,0)+NVL(a.CUR_OTHER_SALE_AMOUNT,0)+NVL(a.CUR_RETUN_AMOUNT,0)+NVL(a.CUR_REPAIR_AMOUNT,0)+NVL(a.CUR_DUMP_OUT_AMOUNT,0)+NVL(a.CUR_INV_REDUCE_AMOUNT,0)+ NVL(a.CUR_COST_ADJUST_AMOUNT, 0)+ NVL(a.CUR_FEW_STOREOUT_AMOUNT, 0)+ NVL(a.TO_SALE_COST, 0),0)DISPATCHAMOUNT")//发金额
			   .append(" from DRP_INVOC_ACCT a ")
			   .append(" left join BASE_PRODUCT b on a.PRD_ID = b.PRD_ID ")
			   .append(" left join DRP_SPCL_TECH c on c.SPCL_TECH_ID = a.SPCL_TECH_ID  and c.USE_FLAG = 1 ")
		       .append(" left join DRP_STORE d on d.STORE_ID = a.STORE_ID and d.DEL_FLAG = 0 ")
		       .append(query.toString())
		       .append(" union all ")
		       .append("select SUM(NVL(a.PER_LEFT_NUM,0))PER_LEFT_NUM,")
		       .append("SUM(NVL(a.PER_LEFT_PRICE,0))PER_LEFT_PRICE,")
		       .append("SUM(NVL(a.PER_LEFT_AMOUNT,0))PER_LEFT_AMOUNT,")
		       .append("SUM(NVL(a.CUR_END_NUM,0))CUR_END_NUM,")
		       .append("SUM(NVL(a.CUR_END_PRICE,0))CUR_END_PRICE,")
		       .append("SUM(NVL(a.CUR_END_AMOUNT,0))CUR_END_AMOUNT,")
		       .append("SUM(NVL(a.CUR_PUR_NUM,0))CUR_PUR_NUM,")
		       .append("SUM(NVL(a.CUR_PUR_PRICE,0))CUR_PUR_PRICE,")
		       .append("SUM(NVL(a.CUR_PUR_AMOUNT,0))CUR_PUR_AMOUNT,")
		       .append("SUM(NVL(a.CUR_TERM_SALE_NUM,0))CUR_TERM_SALE_NUM,")
		       .append(" SUM(NVL(a.CUR_TERM_SALE_PRICE,0))CUR_TERM_SALE_PRICE,")
		       .append("SUM(NVL(a.CUR_TERM_SALE_AMOUNT,0))CUR_TERM_SALE_AMOUNT,")
		       .append("SUM(NVL(a.CUR_OTHER_SALE_NUM,0))CUR_OTHER_SALE_NUM,")
		       .append("SUM(NVL(a.CUR_OTHER_SALE_PRICE,0))CUR_OTHER_SALE_PRICE,")
		       .append("SUM(NVL(a.CUR_OTHER_SALE_AMOUNT,0))CUR_OTHER_SALE_AMOUNT,")
		       .append("SUM(NVL(a.CUR_DUMP_OUT_NUM,0))CUR_DUMP_OUT_NUM,")
		       .append("SUM(NVL(a.CUR_DUMP_OUT_PRICE,0))CUR_DUMP_OUT_PRICE,")
		       .append("SUM(NVL(a.CUR_DUMP_OUT_AMOUNT,0))CUR_DUMP_OUT_AMOUNT,")
		       .append("SUM(NVL(a.CUR_DUMP_IN_NUM,0))CUR_DUMP_IN_NUM,")
		       .append("SUM(NVL(a.CUR_DUMP_IN_PRICE,0))CUR_DUMP_IN_PRICE,")
		       .append("SUM(NVL(a.CUR_DUMP_IN_AMOUNT,0))CUR_DUMP_IN_AMOUNT,")
		       .append("SUM(NVL(a.CUR_RETUN_NUM,0))CUR_RETUN_NUM,")
		       .append("SUM(NVL(a.CUR_RETUN_PRICE,0))CUR_RETUN_PRICE,")
		       .append("SUM(NVL(a.CUR_RETUN_AMOUNT,0))CUR_RETUN_AMOUNT,")
		       .append("SUM(NVL(a.CUR_REPAIR_NUM,0))CUR_REPAIR_NUM,")
		       .append("SUM(NVL(a.CUR_REPAIR_PRICE,0))CUR_REPAIR_PRICE,")
		       .append("SUM(NVL(a.CUR_REPAIR_AMOUNT,0))CUR_REPAIR_AMOUNT,")
		       .append("SUM(NVL(a.CUR_CUST_RETURN_NUM,0))CUR_CUST_RETURN_NUM,")
		       .append("SUM(NVL(a.CUR_CUST_RETURN_PRICE,0))CUR_CUST_RETURN_PRICE,")
		       .append("SUM(NVL(a.CUR_CUST_RETURN_AMOUNT,0))CUR_CUST_RETURN_AMOUNT,")
		       .append("SUM(NVL(a.CUR_INV_ADD_NUM,0))CUR_INV_ADD_NUM,")
				.append("SUM(NVL(a.CUR_INV_ADD_PRICE,0))CUR_INV_ADD_PRICE,")
				.append("SUM(NVL(a.CUR_INV_ADD_AMOUNT,0))CUR_INV_ADD_AMOUNT,")
				.append("SUM(NVL(a.CUR_INV_REDUCE_NUM,0))CUR_INV_REDUCE_NUM,")
				.append("'',")
				.append("'',")
				.append("'',")
				.append("'',")
				.append("'',")
				.append("'',")
				.append("'',")
				.append("SUM(NVL(a.CUR_QUO_AMOUNT,0))CUR_QUO_AMOUNT,")
				.append("SUM(NVL(a.CUR_QUO_PRICE,0))CUR_QUO_PRICE,")
				.append("SUM(NVL(a.CUR_QUO_NUM,0))CUR_QUO_NUM,")
				.append("SUM(NVL(a.CUR_REAL_RETUN_AMOUNT,0))CUR_REAL_RETUN_AMOUNT,")
				.append("SUM(NVL(a.CUR_INV_REDUCE_AMOUNT,0))CUR_INV_REDUCE_AMOUNT,")
				.append("SUM(NVL(a.CUR_INV_REDUCE_PRICE,0))CUR_INV_REDUCE_PRICE,")
				.append("SUM(NVL(a.CUR_INV_REDUCE_NUM,0))CUR_INV_REDUCE_NUM,")
				.append("SUM(NVL(a.CUR_REAL_REPAIR_AMOUNT,0))CUR_REAL_REPAIR_AMOUNT,")
				.append("SUM(NVL(a.CUR_OTHER_REAL_SALE_AMOUNT,0))CUR_OTHER_REAL_SALE_AMOUNT,")
				.append("SUM(NVL(a.CUR_TERM_REAL_SALE_AMOUNT,0))CUR_TERM_REAL_SALE_AMOUNT,")
				.append("SUM(NVL(a.CUR_REPAIR_BACK_AMOUNT,0))CUR_REPAIR_BACK_AMOUNT,")
				.append("SUM(NVL(a.CUR_REPAIR_BACK_PRICE,0))CUR_REPAIR_BACK_PRICE,")
				.append("SUM(NVL(a.CUR_REPAIR_BACK_NUM,0))CUR_REPAIR_BACK_NUM,")
				.append("SUM(NVL(a.CUR_OTHER_RETURN_AMOUNT,0))CUR_OTHER_RETURN_AMOUNT,")
				.append("SUM(NVL(a.CUR_OTHER_RETURN_PRICE,0))CUR_OTHER_RETURN_PRICE,")
				.append("SUM(NVL(a.CUR_OTHER_RETURN_NUM,0))CUR_OTHER_RETURN_NUM,")
				.append("SUM(NVL(a.CUR_FEW_STOREOUT_NUM,0))CUR_FEW_STOREOUT_NUM,")
				.append("SUM(NVL(a.CUR_FEW_STOREOUT_PRICE,0))CUR_FEW_STOREOUT_PRICE,")
				.append("SUM(NVL(a.CUR_FEW_STOREOUT_AMOUNT,0))CUR_FEW_STOREOUT_AMOUNT,")
				.append("null,")
				.append("'',")
				.append("'合计：',")
				.append("SUM(NVL(a.CUR_COST_ADJUST_AMOUNT,0))CUR_COST_ADJUST_AMOUNT,")
				.append("SUM(NVL(NVL(a.CUR_PUR_NUM,0)+NVL(a.CUR_CUST_RETURN_NUM,0)+")
				.append("NVL(a.CUR_OTHER_RETURN_NUM,0)+NVL(a.CUR_REPAIR_BACK_NUM,0)+")
				.append("NVL(a.CUR_DUMP_IN_NUM,0)+NVL(a.CUR_INV_ADD_NUM,0),")
				.append("0))COLLECTNUM,")
				.append("SUM(NVL(NVL(a.CUR_PUR_AMOUNT,0)+NVL(a.CUR_CUST_RETURN_AMOUNT,0)+")
				.append("NVL(a.CUR_OTHER_RETURN_AMOUNT,0)+")
				.append("NVL(a.CUR_REPAIR_BACK_AMOUNT,0)+NVL(a.CUR_DUMP_IN_AMOUNT,0)+")
				.append("NVL(a.CUR_INV_ADD_AMOUNT,0),")
				.append("0))COLLECTAMOUNT,")
				.append("SUM(NVL(NVL(a.CUR_TERM_SALE_NUM,0)+NVL(a.CUR_OTHER_SALE_NUM,0)+")
				.append("NVL(a.CUR_RETUN_NUM,0)+NVL(a.CUR_REPAIR_NUM,0)+")
				.append("NVL(a.CUR_DUMP_OUT_NUM,0)+NVL(a.CUR_INV_REDUCE_NUM,0)+")
				.append("NVL(a.CUR_FEW_STOREOUT_NUM,0),")
				.append("0))DISPATCHNUM,")
				.append("SUM(NVL(NVL(a.CUR_TERM_SALE_AMOUNT,0)+NVL(a.CUR_OTHER_SALE_AMOUNT,0)+")
				.append("NVL(a.CUR_RETUN_AMOUNT,0)+NVL(a.CUR_REPAIR_AMOUNT,0)+")
				.append("NVL(a.CUR_DUMP_OUT_AMOUNT,0)+NVL(a.CUR_INV_REDUCE_AMOUNT,0)+")
				.append("NVL(a.CUR_COST_ADJUST_AMOUNT,0)+")
				.append("NVL(a.CUR_FEW_STOREOUT_AMOUNT,0)+NVL(a.TO_SALE_COST,0),")
				.append("0))DISPATCHAMOUNT ")
				.append(" from DRP_INVOC_ACCT a ")
				.append(" left join BASE_PRODUCT b ")
				.append(" on a.PRD_ID=b.PRD_ID ")
				.append(" left join DRP_SPCL_TECH c ")
				.append(" on c.SPCL_TECH_ID=a.SPCL_TECH_ID ")
				.append(" and c.USE_FLAG=1 ")
				.append(" left join DRP_STORE d ")
				.append(" on d.STORE_ID=a.STORE_ID ")
				.append(" and d.DEL_FLAG=0 ")
				.append(query.toString());
		}else{
			sql.append("select a.IN_OUT_TYPE,")
			   .append("a.BILL_NO,")
			   .append("a.STORE_NO,")
			   .append("a.STORE_NAME,")
			   .append("c.SPCL_DTL_REMARK,")
			   .append("a.PRD_NO,")
			   .append("a.PRD_NAME,")
			   .append("a.PRD_SPEC,")
			   .append("a.BRAND,")
			   .append("a.STD_UNIT,")
			   .append("a.IN_OUT_NUM,")
			   .append("a.IN_OUT_AMOUNT,")
			   .append("a.DEAL_TIME,")
			   .append("a.DEAL_DATE ")
			   .append(" from DRP_JOURNAL_ACCT a ")
			   .append(" left join DRP_SPCL_TECH c  on c.SPCL_TECH_ID = a.SPCL_TECH_ID and c.USE_FLAG = 1 ")
			   .append(" left join BASE_PRODUCT b on b.PRD_ID=a.PRD_ID and b.DEL_FLAG=0 ")
			   .append(" left join DRP_STORE d on d.STORE_ID=a.STORE_ID and d.DEL_FLAG=0 ")
			   .append(query.toString())
			   .append(" union all ")
			   .append("select '',")
			   .append("'',")
			   .append("'',")
			   .append("'',")
			   .append("'',")
			   .append("'',")
			   .append("'',")
			   .append("'',")
			   .append("'',")
			   .append("'合计：',")
			   .append("sum(NVL(a.IN_OUT_NUM,0))IN_OUT_NUM,")
			   .append("sum(NVL(a.IN_OUT_AMOUNT,0))IN_OUT_AMOUNT,")
			   .append("null,")
			   .append("null ")
			   .append(" from DRP_JOURNAL_ACCT a ")
			   .append(" left join DRP_SPCL_TECH c  on c.SPCL_TECH_ID = a.SPCL_TECH_ID and c.USE_FLAG = 1 ")
			   .append(" left join BASE_PRODUCT b on b.PRD_ID=a.PRD_ID and b.DEL_FLAG=0 ")
			   .append(" left join DRP_STORE d on d.STORE_ID=a.STORE_ID and d.DEL_FLAG=0 ")
			   .append(query.toString());
		}
		
		request.setAttribute("conDition", "rptSql=" + sql);
		if("1".equals(getherFlag)){
			request.setAttribute("rptModel", "InvocGatherAccta.raq");
			request.setAttribute("printModel", "InvocGatherAccta.raq");
		}else if("2".equals(getherFlag)){
			request.setAttribute("rptModel", "InvocAccta.raq");
			request.setAttribute("printModel", "InvocAccta.raq");
		}else if("3".equals(getherFlag)){
			request.setAttribute("rptModel", "JournalAccta.raq");
			request.setAttribute("printModel", "JournalAccta.raq");
		}
		return mapping.findForward("pageResult");
	}
	 	
    /**
     * 月工作计划提交情况
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward  toMonthWorkPalnReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		String PLAN_YEAR  = ParamUtil.get(request, "PLAN_YEAR"); 
		String PLAN_MONTH = ParamUtil.get(request, "PLAN_MONTH");
		StringBuffer advSql = new StringBuffer();
		advSql.append("select distinct  t1.WAREA_NAME,t1.RYMC,sum(NVL(t1.TOTAL_UP_REPORT_NUM,0)) TOTAL_UP_REPORT_NUM, sum(NVL(s.TOTAL_UP_REPORT_NUM,0)) REAL_VISIT_NUM,")
		   .append("  (round((sum(NVL(s.TOTAL_UP_REPORT_NUM,0)) / sum(NVL(t1.TOTAL_UP_REPORT_NUM,0))) * 100, 2)) || '%' PERCENTAGE")
		   .append("  from (select  t.WORK_PLAN_ID, t.WAREA_NAME, d.RYMC,t.TOTAL_UP_REPORT_NUM ")
		   .append("  from ERP_WORK_PLAN t, ERP_WORK_PLAN_DTL d where t.WORK_PLAN_ID = d.WORK_PLAN_ID")
		   .append("  and t.DEL_FLAG = 0 and d.DEL_FLAG = 0 and t.WAREA_NAME like '%战区%'"); //and t.PLAN_YEAR='"+PLAN_YEAR+"'and t.PLAN_MONTH='"+PLAN_MONTH+"') t1")
		
		if(!StringUtil.isEmpty(PLAN_YEAR)){
			advSql.append(" and t.PLAN_YEAR='"+PLAN_YEAR+"'");
		}
		if(!StringUtil.isEmpty(PLAN_MONTH)){
			advSql.append(" and t.PLAN_MONTH='"+PLAN_MONTH+"'");
		}
		advSql.append(" ) t1 left join ERP_WORK_PLAN s on t1.WORK_PLAN_ID = s.WORK_PLAN_ID and s.DEL_FLAG=0 and s.state in ('审核通过','提交')")
		   .append(" group by t1.WAREA_NAME,t1.RYMC");
		
		
		 StringBuffer dtlSql=new StringBuffer();
		 dtlSql.append("select distinct  t1.WAREA_NAME,t1.RYMC,sum(NVL(t1.TOTAL_UP_REPORT_NUM,0)) TOTAL_UP_REPORT_NUM, sum(NVL(s.TOTAL_UP_REPORT_NUM,0)) REAL_VISIT_NUM,")
		   .append("  (round((sum(NVL(s.TOTAL_UP_REPORT_NUM,0)) / sum(NVL(t1.TOTAL_UP_REPORT_NUM,0))) * 100, 2)) || '%' PERCENTAGE")
		   .append("  from (select  t.WORK_PLAN_ID, t.WAREA_NAME, d.RYMC,t.TOTAL_UP_REPORT_NUM ")
		   .append("  from ERP_WORK_PLAN t, ERP_WORK_PLAN_DTL d where t.WORK_PLAN_ID = d.WORK_PLAN_ID")
		   .append("  and t.DEL_FLAG = 0 and d.DEL_FLAG = 0 and t.WAREA_NAME like '%直营%'"); //and t.PLAN_YEAR='"+PLAN_YEAR+"'and t.PLAN_MONTH='"+PLAN_MONTH+"') t1")
		
		if(!StringUtil.isEmpty(PLAN_YEAR)){
			dtlSql.append(" and t.PLAN_YEAR='"+PLAN_YEAR+"'");
		}
		if(!StringUtil.isEmpty(PLAN_MONTH)){
			dtlSql.append(" and t.PLAN_MONTH='"+PLAN_MONTH+"'");
		}
		dtlSql.append(" ) t1 left join ERP_WORK_PLAN s on t1.WORK_PLAN_ID = s.WORK_PLAN_ID and s.DEL_FLAG=0 and s.state in ('审核通过','提交')")
		   .append(" group by t1.WAREA_NAME,t1.RYMC");
		
//		/********** 查询条件拼接 start **********/
//		//是否显示战区这列 true->不显示
		/********** 查询条件拼接 start **********/
		//是否显示战区这列 true->不显示
		boolean WAREA_FLAG = false;
		boolean RYXX_FLAG  = false;
        boolean YEAR_FLAG  = false;
        boolean MONTH_FLAG = false;
        
        String params = "rptSql=" + advSql+";dtlSql="+dtlSql
        +";WAREA_FLAG="+WAREA_FLAG
        +";RYXX_FLAG="+RYXX_FLAG
        +";YEAR_FLAG="+YEAR_FLAG
        +";MONTH_FLAG="+MONTH_FLAG;
      
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", params);
		
	    request.setAttribute("rptModel",   "monthWorkPlanReport.raq");
		request.setAttribute("printModel", "monthWorkPlanReport.raq");
		return mapping.findForward("pageResult");
	}
    
	/**
	 * 加盟商推广费使用明细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toPrmtCostReqReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		String PRMT_COST_REQ_NO = ParamUtil.get(request, "PRMT_COST_REQ_NO");
		String REQ_DATE_BEG = ParamUtil.get(request, "REQ_DATE_BEG");
		String REQ_DATE_END = ParamUtil.get(request, "REQ_DATE_END");
		String AREA_MANAGE_NAME = ParamUtil.get(request, "AREA_MANAGE_NAME");
		String WAREA_NAME   = ParamUtil.get(request, "WAREA_NAME");
		StringBuffer sql=new StringBuffer();
		sql.append("select  ")
		   .append("  u.PRMT_COST_REQ_NO, ")
		   .append("  u.REQ_MAKE,")
	       .append("  u.AREA_NO, ")
	       .append("  u.AREA_NAME, ")
	       .append("  u.AREA_MANAGE_ID,")
	       .append("  u.AREA_MANAGE_NAME, u.CHANN_NAME, ")
	       .append("  u.COST_TYPE, ")
	       .append("  to_char(u.REQ_DATE,'yyyy-MM-DD') REQ_DATE, ")
	       .append("  u.BUDGET_AMOUNT,")
	       .append("  to_char(u.AUDIT_TIME,'yyyy-MM-DD') REQ_FIN_DATE, ")
	       .append("  to_char(b.EXPENSE_DATE,'yyyy-MM-DD')EXPENSE_DATE,b.EXPENSE_ORDER_NO,b.EXPENSE_AMOUNT,")
	       .append("  to_char(b.AUDIT_TIME,'yyyy-MM-DD') EXP_FIN_DATE ")
	       .append("  from ERP_PRMT_COST_REQ  u left join  ERP_EXPENSE_ORDER b on")
	       .append("  u.PRMT_COST_REQ_NO=b.RELATE_ORDER_NOS and b.STATE in ('提交','审核通过') and b.DEL_FLAG=0")
	       .append("  where  u.STATE in ('提交','审核通过') and u.DEL_FLAG=0  ");
		
		if (!StringUtil.isEmpty(PRMT_COST_REQ_NO)) {
			sql.append(" and u.PRMT_COST_REQ_NO like '%").append(PRMT_COST_REQ_NO).append("%'");
		}
		if (!StringUtil.isEmpty(REQ_DATE_BEG)) {
			sql.append(" and u.REQ_DATE>=to_date('"+REQ_DATE_BEG+"','yyyy-MM-DD')");
		}
		if (!StringUtil.isEmpty(REQ_DATE_END)) {
			sql.append(" and u.REQ_DATE<=to_date('"+REQ_DATE_END+"','yyyy-MM-DD')");
		}
	    if(!StringUtil.isEmpty(AREA_MANAGE_NAME)){
	    	AREA_MANAGE_NAME = AREA_MANAGE_NAME.replaceAll(",", "','");
	    	sql.append(" and u.AREA_MANAGE_NAME IN ('").append(AREA_MANAGE_NAME).append("') ");
	    }
	    if(!StringUtil.isEmpty(WAREA_NAME)){
	    	WAREA_NAME =  WAREA_NAME.replace(",", "','");
	    	sql.append(" and u.AREA_NAME IN ('"+WAREA_NAME+"')");
	    }
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel",   "prmtCostReq.raq");
		request.setAttribute("printModel", "prmtCostReq.raq");
		return mapping.findForward("pageResult");
	}
	
	/**
	 * 战区推广费
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toWareExtensionFeeReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		String WAREA_NO = ParamUtil.get(request, "WAREA_NO"); 
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME"); 
	    String QUARTER = ParamUtil.get(request, "QUARTER"); 
	    String YEAR = ParamUtil.get(request, "YEAR"); 
		StringBuffer conSql = new StringBuffer("");
		if(!StringUtil.isEmpty(WAREA_NAME)){
			conSql.append(StringUtil.creCon("ext.area_name_p", WAREA_NAME, ","));
		}
		if(!StringUtil.isEmpty(WAREA_NO)){
			conSql.append(StringUtil.creCon("ext.area_no_p", WAREA_NO, ","));
		}
		
		String sql = this.reportService.getWareExtensionFeeSql(conSql.toString(),YEAR,QUARTER);
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel",   "wareExtensionFee.raq");
		request.setAttribute("printModel", "wareExtensionFee.raq");
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 区域推广费
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAreaExtensionFeeReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		String WAREA_NO = ParamUtil.get(request, "WAREA_NO"); 
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME"); 
		String AREA_NO = ParamUtil.get(request, "AREA_NO"); 
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME"); 
		
	    String QUARTER = ParamUtil.get(request, "QUARTER"); 
	    String YEAR = ParamUtil.get(request, "YEAR"); 
		StringBuffer conSql = new StringBuffer("");
		if(!StringUtil.isEmpty(WAREA_NAME)){
			conSql.append(StringUtil.creCon("ext.area_name_p", WAREA_NAME, ","));
		}
		if(!StringUtil.isEmpty(WAREA_NO)){
			conSql.append(StringUtil.creCon("ext.area_no_p", WAREA_NO, ","));
		}
		if(!StringUtil.isEmpty(AREA_NO)){
			conSql.append(StringUtil.creCon("ext.area_no", AREA_NO, ","));
		}
		if(!StringUtil.isEmpty(AREA_NAME)){
			conSql.append(StringUtil.creCon("ext.area_name", AREA_NAME, ","));
		}
		
		String sql = this.reportService.getAreaExtensionFeeSql(conSql.toString(),YEAR,QUARTER);
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel",   "areaExtensionFee.raq");
		request.setAttribute("printModel", "areaExtensionFee.raq");
		return mapping.findForward("pageResult");
	}
	
 
	/**
	 * 加盟商推广费报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toPrmtCostReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME");
		String AREA_MANAGE_NAME = ParamUtil.get(request, "AREA_MANAGE_NAME");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String YEAR    = ParamUtil.get(request, "YEAR");
		String QUARTER = ParamUtil.get(request, "QUARTER");
		StringBuffer sql=new StringBuffer();
		
		int intQUARTER = StringUtil.getStrQuarterToInt(QUARTER);
		int last_year = 0;
		int last_q = 0;
		if (intQUARTER == 1) {
			last_year = StringUtil.getInteger(YEAR) - 1;
			last_q = 4;
		} else {
			last_year = StringUtil.getInteger(YEAR);
			last_q = intQUARTER - 1;
		}
		
		sql.append("select  ")
		   .append("  ext.area_name_p WAREA_NAME,ext.chann_name CHANN_NAME, ext.area_manage_name AREA_MANAGE_NAME,")
		   .append("  round((ext.storeoutAmount_s + ext.quarter_amount)/10000,2) SALEYUSUAN, round(ext.storeoutAmount/10000,2) STOREOUT,")
	       .append("  round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.3)/1000,2) WAREAYUSUAN,round(ext.BUDGET_AMOUNT1/10000,2) WAREAYUPI,")
	       .append("  round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.3 - ext.BUDGET_AMOUNT1)/10000,2) WAREAYUSUANENABLE, ")
	       .append("  round((ext.storeoutAmount * 0.3)/10000,2) WAREAJITI,round(EXPENSE_AMOUNT1/10000,2) WAREABOXIAO, round((ext.storeoutAmount * 0.3 - EXPENSE_AMOUNT1)/10000,2) WAREAJITIENABLE,")
	       .append("  round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.2)/10000 ,2) TOTALYUSUAN,ext.BUDGET_AMOUNT2 TOTALYUPI, ")
	       .append("  round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.2 - ext.BUDGET_AMOUNT2)/10000 ,2) TOTALYUSUANENABLE, ")
	       .append("  round((ext.storeoutAmount * 0.2)/10000 ,2) TOTALJITI,round( EXPENSE_AMOUNT2/10000 ,2) TOTALBAOXIAO,round((ext.storeoutAmount * 0.2 - EXPENSE_AMOUNT2)/10000,2) TOTAOLJITIENABLE")
	       .append("  from (select a.area_name_p, b.chann_name, b.area_manage_name,max(f1.storeoutAmount_s) storeoutAmount_s,  ")
	       .append("  sum(NVL(p.first_quarter_amount, 0)) quarter_amount, max(f2.storeoutAmount) storeoutAmount,  ")
	       .append("  max(NVL(c1.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT1, max(NVL(c2.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT2, ")
	       .append("  max(NVL(x1.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT1,  max(NVL(x2.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT2  ")
	       .append("  from base_chann b left join base_area a on a.area_id = b.area_id and a.state in ('启用')")
	       .append("  and a.del_flag = 0 left join (select b.area_id, b.area_manage_id, b.chann_id,")
	       .append("  sum(NVL(temp.storeoutAmount_s, 0)) storeoutAmount_s from (select s.order_chann_id,")
	       .append("  sum(NVL(sd.dect_price, 0) * NVL(sd.storeout_num, 0)) storeoutAmount_s ")
	       .append("  from erp_storeout s, erp_storeout_prd_dtl sd where s.storeout_id = sd.storeout_id");
		if (!StringUtil.isEmpty(QUARTER)) {
			sql.append(" and TO_CHAR(s.storeout_time, 'Q') = '"+last_q+"'");
		}
		if (!StringUtil.isEmpty(YEAR)) {
			sql.append(" and to_char(s.storeout_time, 'yyyy') = '"+last_year+"'");
		}
		sql.append("  and s.del_flag = 0 and sd.del_flag = 0 group by s.order_chann_id) temp  left join base_chann b")
		   .append("  on temp.order_chann_id = b.chann_id group by b.area_id, b.area_manage_id, b.chann_id) f1")
		   .append("  on f1.area_id = b.area_id and NVL(f1.area_manage_id, 'NONL') =  NVL(b.area_manage_id, 'NONL')")
		   .append("  and f1.chann_id = b.chann_id  left join ERP_AREA_SALE_PLAN p  ");
		
		sql.append("  on p.area_id = b.area_id and p.del_flag = 0 ");
		if (!StringUtil.isEmpty(YEAR)) {
			sql.append("   and p.PLAN_YEAR = '"+intQUARTER+"' ");
		}
		sql.append("  left join (select b.area_id, b.area_manage_id, b.chann_id,")
		   .append("  sum(NVL(temp.storeoutAmount, 0)) storeoutAmount from (select s.order_chann_id,")
		   .append("  sum(NVL(sd.dect_price, 0) * NVL(sd.storeout_num, 0)) storeoutAmount")
		   .append("  from erp_storeout s, erp_storeout_prd_dtl sd where s.storeout_id = sd.storeout_id");
		if (!StringUtil.isEmpty(QUARTER)) {
			sql.append(" and TO_CHAR(s.storeout_time, 'Q') = '"+intQUARTER+"'");
		}
		if (!StringUtil.isEmpty(YEAR)) {
			sql.append(" and to_char(s.storeout_time, 'yyyy') = '"+YEAR+"'");
		}
		sql.append(" and s.del_flag = 0 and sd.del_flag = 0 group by s.order_chann_id) temp")
		   .append(" left join base_chann b on temp.order_chann_id = b.chann_id group by b.area_id, b.area_manage_id, b.chann_id) f2")
		   .append(" on f2.area_id = b.area_id and NVL(f2.area_manage_id, 'NONL') = NVL(b.area_manage_id, 'NONL') and f2.chann_id = b.chann_id")
		   .append(" left join (select b.area_id, b.area_manage_id, r.chann_id, SUM(NVL(r.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT")
		   .append(" from ERP_PRMT_COST_REQ r left join base_chann b on r.chann_id = b.chann_id where r.state in ('审核通过', '提交') and r.del_flag = 0");
		if (!StringUtil.isEmpty(YEAR)) {
			sql.append("   and to_char(r.REQ_DATE, 'yyyy') = '"+YEAR+"'");
		}
		if (!StringUtil.isEmpty(QUARTER)) {
			sql.append("   and TO_CHAR(r.REQ_DATE, 'Q') = '"+intQUARTER+"'");
		}   
		sql.append("  and r.cost_type = '战区费用' group by b.area_id, b.area_manage_id, r.chann_id) c1 on c1.area_id = b.area_id")
		.append("   and NVL(c1.area_manage_id, 'NONL') = NVL(b.area_manage_id, 'NONL') and c1.chann_id = b.chann_id")
		.append("  left join (select b.area_id,b.area_manage_id,r.chann_id,SUM(NVL(r.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT")
		.append("  from ERP_PRMT_COST_REQ r left join base_chann b on r.chann_id = b.chann_id where r.state in ('审核通过', '提交') and r.del_flag = 0");
		
		if (!StringUtil.isEmpty(YEAR)) {
			sql.append("   and to_char(r.REQ_DATE, 'yyyy') = '"+YEAR+"'");
		}
		if (!StringUtil.isEmpty(QUARTER)) {
			sql.append("   and TO_CHAR(r.REQ_DATE, 'Q') = '"+intQUARTER+"'");
		}   
		sql.append("   and r.cost_type = '总部费用' group by b.area_id, b.area_manage_id, r.chann_id) c2 on c2.area_id = a.area_id")
		.append("  and NVL(c2.area_manage_id, 'NONL') = NVL(b.area_manage_id, 'NONL') and c2.chann_id = b.chann_id left join (select b.area_id,")
		.append(" b.area_manage_id, r.chann_id,SUM(NVL(e.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT from ERP_EXPENSE_ORDER e left join ERP_PRMT_COST_REQ r")
		.append("  on r.prmt_cost_req_no = SUBSTR(e.relate_order_nos||',',0, instr(e.relate_order_nos||',',',')-1)")
		.append("  left join base_chann b on r.chann_id = b.chann_id where e.state in ('审核通过', '提交') and e.relate_order_nos is not null and e.del_flag = 0")
		.append("   and r.cost_type = '战区费用'");
		
		if (!StringUtil.isEmpty(YEAR)) {
			sql.append("    and e.YEAR = '"+YEAR+"'");
		}
		if (!StringUtil.isEmpty(QUARTER)) {
			sql.append("    and e.quarter = '"+intQUARTER+"'");
		}   
		sql.append("   group by b.area_id, b.area_manage_id, r.chann_id) x1 on x1.area_id = b.area_id")
		.append("   and NVL(x1.area_manage_id, 'NONL') =NVL(b.area_manage_id, 'NONL') and x1.chann_id = b.chann_id left join (select b.area_id,b.area_manage_id, r.chann_id,")
		.append("  SUM(NVL(e.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT from ERP_EXPENSE_ORDER e")
		.append("  left join ERP_PRMT_COST_REQ r on r.prmt_cost_req_no = SUBSTR(e.relate_order_nos||',',0, instr(e.relate_order_nos||',',',')-1)")
		.append("  left join base_chann b on r.chann_id = b.chann_id  where e.state in ('审核通过', '提交') and e.relate_order_nos is not null")
		.append("  and e.del_flag = 0 and r.cost_type = '总部费用'");
		if (!StringUtil.isEmpty(YEAR)) {
			sql.append("    and e.YEAR = '"+YEAR+"'");
		}
		if (!StringUtil.isEmpty(QUARTER)) {
			sql.append("    and e.quarter = '"+intQUARTER+"'");
		}   
		sql.append(" group by b.area_id, b.area_manage_id, r.chann_id) x2 on x2.area_id = a.area_id and NVL(x2.area_manage_id, 'NONL') = NVL(b.area_manage_id, 'NONL')")
		   .append("  and x2.chann_id = b.chann_id  where a.AREA_ID_P is not null and b.state in ('启用') and b.del_flag = 0 group by a.area_name_p,")
		   .append("   b.chann_name, b.area_manage_name) ext WHERE 1=1 ");
		if(!StringUtil.isEmpty(WAREA_NAME)){
			WAREA_NAME = WAREA_NAME.replaceAll(",", "','");
			sql.append(" AND ext.area_name_p IN( '"+WAREA_NAME+"')");
		}
		if(!StringUtil.isEmpty(AREA_MANAGE_NAME)){
			AREA_MANAGE_NAME = AREA_MANAGE_NAME.replaceAll(",", "','");
			sql.append(" AND ext.AREA_MANAGE_NAME IN( '"+AREA_MANAGE_NAME+"')");
		}
		if(!StringUtil.isEmpty(CHANN_NAME)){
			sql.append(" AND ext.CHANN_NAME= '"+CHANN_NAME+"'");
		}
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel",   "prmtCostReport.raq");
		request.setAttribute("printModel", "prmtCostReport.raq");
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 卡券销售清单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toCardListReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		String SALE_PSON_NAME = ParamUtil.get(request, "SALE_PSON_NAME"); 
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO"); 
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME"); 
		String TERM_NO = ParamUtil.get(request, "TERM_NO"); 
	    String TERM_NAME = ParamUtil.get(request, "TERM_NAME"); 
	    String CUST_NAME = ParamUtil.get(request, "CUST_NAME"); 
	    String SALE_DATE = ParamUtil.get(request, "SALE_DATE"); 
	    String MARKETING_CARD_NO = ParamUtil.get(request, "MARKETING_CARD_NO"); 
	    
	    
		StringBuffer conSql = new StringBuffer("");
		if(!StringUtil.isEmpty(CHANN_NO)){
			conSql.append(StringUtil.creCon("v.CHANN_NO", CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(CHANN_NAME)){
			conSql.append(StringUtil.creCon("v.CHANN_NAME", CHANN_NAME, ","));
		}
		if(!StringUtil.isEmpty(TERM_NO)){
			conSql.append(StringUtil.creCon("v.TERM_NO", TERM_NO, ","));
		}
		if(!StringUtil.isEmpty(TERM_NAME)){
			conSql.append(StringUtil.creCon("v.TERM_NAME", TERM_NAME, ","));
		}
		
		if(!StringUtil.isEmpty(SALE_PSON_NAME)){
			conSql.append(StringUtil.creCon("v.SALE_PSON_NAME", SALE_PSON_NAME, ","));
		}
		if(!StringUtil.isEmpty(CUST_NAME)){
			conSql.append(StringUtil.creCon("v.CUST_NAME", CUST_NAME, ","));
		}
		if(!StringUtil.isEmpty(SALE_DATE)){
			conSql.append(StringUtil.creCon("v.SALE_DATE", SALE_DATE, ","));
		}
		
		if(!StringUtil.isEmpty(MARKETING_CARD_NO)){
			conSql.append(StringUtil.creCon("v.MARKETING_CARD_NO", MARKETING_CARD_NO, ","));
		}
		
		String sql = "select v.* from V_CARD_LIST_VIEW v where 1=1 "+conSql.toString();
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel",   "MarketCardReport.raq");
		request.setAttribute("printModel", "MarketCardReport.raq");
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 年度返利汇总报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAnnualRebate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO"); 
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME"); 
		String YEAR = ParamUtil.get(request, "YEAR"); 
	    StringBuffer sql=new StringBuffer();
	    sql.append("select b.CHANN_NO,")
	       .append("b.CHANN_NAME,")
	       .append("b.CHANN_ID,")
	       .append("(case when ac.BEG_BALANCE is null then aco.END_BALANCE else ac.BEG_BALANCE end)BEG_BALANCE,")
	       .append("temp.REBATE_TYPE,")
	       .append("NVL(temp.CHANGE_REBATE, 0) CHANGE_REBATE,")
	       .append("NVL(journal.USER_REBATE, 0) USER_REBATE ")
	       .append(" from BASE_CHANN b ")
	       .append(" left join BASE_REBATE_YEAR_ACCT ac on b.CHANN_ID = ac.CHANN_ID and TO_CHAR(to_date('").append(YEAR).append("', 'yyyy'), 'YYYY') = ac.YEAR ")
	       .append(" left join BASE_REBATE_YEAR_ACCT aco on b.CHANN_ID=aco.CHANN_ID and TO_CHAR(ADD_MONTHS(to_date('").append(YEAR).append("', 'yyyy'), -12), 'YYYY') = aco.YEAR and ac.CHANN_ID is null")
	       .append(" left join (select aa.CHANN_ID, ")
	       .append(" SUM(DECODE(aa.DIRECTION, 1, 1, 0, -1) * NVL(aa.prd_amount, 0)) USER_REBATE")
	       .append(" from BASE_JOURNAL_REBATE_ACCT aa ")
	       .append(" where aa.BUSS_TYPE in ('订货订单', '销售订单') and TO_CHAR(to_date(aa.DEAL_TIME, 'yyyy-mm-dd HH24:mi:ss'),  'yyyy') = '").append(YEAR).append("'")
	       .append(" group by aa.CHANN_ID) journal on journal.CHANN_ID = b.CHANN_ID ")
	       .append(" left join (select aa.CHANN_ID, decode(aa.REBATE_TYPE, null,aa.BUSS_TYPE,aa.REBATE_TYPE) REBATE_TYPE, ")
	       .append(" SUM(decode(aa.DIRECTION, 0, 1, 1, -1) * NVL(aa.PRD_AMOUNT, 0)) CHANGE_REBATE ")
	       .append(" from BASE_JOURNAL_REBATE_ACCT aa ")
	       .append(" where aa.BUSS_TYPE in ('增加返利', '减少返利') and TO_CHAR(to_date(aa.DEAL_TIME, 'yyyy-mm-dd HH24:mi:ss'), 'yyyy') = '").append(YEAR).append("' ")
	       .append(" group by aa.CHANN_ID,decode(aa.REBATE_TYPE,null,aa.BUSS_TYPE, aa.REBATE_TYPE)) temp ")
	       .append(" on temp.CHANN_ID = b.CHANN_ID ")
	       .append(" where b.DEL_FLAG = 0 ")
	       .append("  and b.STATE in ('启用','停用') ")
	       .append(" and b.IS_BASE_FLAG = 0 ");
	    StringBuffer countSql=new StringBuffer();
	    countSql.append("select count(1) cnt ")
	       .append(" from BASE_CHANN b ")
	       .append(" left join BASE_REBATE_YEAR_ACCT ac on b.CHANN_ID = ac.CHANN_ID and TO_CHAR(ADD_MONTHS(to_date('").append(YEAR).append("', 'yyyy'), -12), 'YYYY') = ac.year ")
	       .append(" left join (select aa.CHANN_ID ")
	       .append(" from BASE_JOURNAL_REBATE_ACCT aa ")
	       .append(" where aa.BUSS_TYPE in ('订货订单', '销售订单') and TO_CHAR(to_date(aa.DEAL_TIME, 'yyyy-mm-dd HH24:mi:ss'),  'yyyy') = '").append(YEAR).append("'")
	       .append(" group by aa.CHANN_ID) journal on journal.CHANN_ID = b.CHANN_ID ")
	       .append(" left join (select aa.CHANN_ID ")
	       .append(" from BASE_JOURNAL_REBATE_ACCT aa ")
	       .append(" where aa.BUSS_TYPE in ('增加返利', '减少返利') and TO_CHAR(to_date(aa.DEAL_TIME, 'yyyy-mm-dd HH24:mi:ss'), 'yyyy') = '").append(YEAR).append("' ")
	       .append(" group by aa.CHANN_ID) temp ")
	       .append(" on temp.CHANN_ID = b.CHANN_ID ")
	       .append(" where b.DEL_FLAG = 0 ")
	       .append("  and b.STATE in ('启用','停用') ")
	       .append(" and b.IS_BASE_FLAG = 0 ");
	       if(!"1".equals(userBean.getIS_DRP_LEDGER())){
	    	   sql.append("and b.CHANN_ID in (").append(userBean.getCHANNS_CHARG()).append(")");
	    	   countSql.append("and b.CHANN_ID in (").append(userBean.getCHANNS_CHARG()).append(")");
	       }
	       if(!StringUtil.isEmpty(CHANN_NO)){
				sql.append(StringUtil.creCon("b.CHANN_NO", CHANN_NO, ","));
				countSql.append(StringUtil.creCon("b.CHANN_NO", CHANN_NO, ","));
			}
			if(!StringUtil.isEmpty(CHANN_NAME)){
				sql.append(StringUtil.creCon("b.CHANN_NAME", CHANN_NAME, ","));
				countSql.append(StringUtil.creCon("b.CHANN_NAME", CHANN_NAME, ","));
			}
		Map<String,String> params=new HashMap<String, String>();
		params.put("SelSQL", countSql.toString());
		Map resMap = reportService.selecTotalCount(params);
		int count=StringUtil.getInteger(resMap.get("CNT"));
	    sql.append(" order by b.CHANN_NO ");
		request.setAttribute("conDition", "rptSql=" + sql+";arg1="+YEAR);
		request.setAttribute("rptModel",   "AnnualRebateReport.raq");
		request.setAttribute("printModel", "AnnualRebateReport.raq");
		request.setAttribute("totalCount", count);
		return mapping.findForward("realPageResult");
//		return mapping.findForward("pageResult");
	}
	
	/**
	 * 年度返利汇总明细报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAnnualRebateDtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String CHANN_ID = ParamUtil.get(request, "CHANN_ID"); 
		String YEAR = ParamUtil.get(request, "YEAR"); 
	    StringBuffer sql=new StringBuffer();
	    sql.append("select * from (")
	       .append("select a.CHANN_NAME,")
	       .append("a.DEAL_TIME,")
	       .append("DECODE(NVL(a.REBATE_TYPE,'NONL'),'NONL',a.BUSS_TYPE,a.REBATE_TYPE) BUSS_TYPE,")
	       .append("a.BILL_NO,")
	       .append("DECODE(a.DIRECTION,1,a.PRD_AMOUNT,'0') DEBIT_AMOUNT,")
	       .append("DECODE(a.DIRECTION,0,a.PRD_AMOUNT,'0') CREDIT_AMOUNT, ")
	       .append(" b.REMARK ")
	       .append(" from BASE_JOURNAL_REBATE_ACCT a ")
	       .append(" left join ERP_REBATE_CHANGE_REQ b on a.BILL_ID=b.REBATE_CHANGE_REQ_ID ")
	       .append("where a.CHANN_ID='").append(CHANN_ID).append("' and substr(a.DEAL_TIME,0,4)='").append(YEAR).append("'")
	       .append(" union all ")
	       .append(" select '合计',null,'','',sum(DECODE(a.DIRECTION, 1, a.PRD_AMOUNT, '0'))DEBIT_AMOUNT,sum(DECODE(a.DIRECTION, 0, a.PRD_AMOUNT, '0'))CREDIT_AMOUNT,'' ")
	       .append(" from BASE_JOURNAL_REBATE_ACCT a ")
	       .append(" left join ERP_REBATE_CHANGE_REQ b on a.BILL_ID=b.REBATE_CHANGE_REQ_ID ")
	       .append("where a.CHANN_ID='").append(CHANN_ID).append("' and substr(a.DEAL_TIME,0,4)='").append(YEAR).append("'")
	       .append(") order by DEAL_TIME");
//		request.setAttribute("conDition", "CHANN_ID="+CHANN_ID+";YEAR="+YEAR);
		request.setAttribute("conDition", "rptSql=" + sql+";arg1="+YEAR);
		request.setAttribute("rptModel",   "AnnualRebateDtlReport.raq");
		request.setAttribute("printModel", "AnnualRebateDtlReport.raq");
		return mapping.findForward("pageResult");
	}
	
	/**
	 * 直营办零售数据跟踪
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toChildCompSaleTraking(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO"); 
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String YEAR = ParamUtil.get(request, "YEAR"); 
		StringBuffer sql = new StringBuffer("select v.* from v_childcomp_sale_track_view v where 1=1  ");
		if(!StringUtil.isEmpty(YEAR)){
			sql.append(" and v.YEAR='").append(YEAR).append("' ");
		}
		if(!StringUtil.isEmpty(CHANN_NO)){
			sql.append(StringUtil.creCon("v.CHANN_NO", CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(CHANN_NAME)){
			sql.append(StringUtil.creCon("v.CHANN_NAME", CHANN_NAME, ","));
		}
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel",   "childcompSaleTracking.raq");
		request.setAttribute("printModel", "childcompSaleTracking.raq");
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 每月售后投诉报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAdviseMothStatis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String YEAR_MONTH = ParamUtil.get(request, "YEAR_MONTH"); 
		Date curr = null;
		Calendar c = Calendar.getInstance();  
		if(!StringUtil.isEmpty(YEAR_MONTH)){
			curr = DateUtil.parseDate(YEAR_MONTH, "yyyy-MM");
			c.setTime(curr);
		}else{
			YEAR_MONTH = DateUtil.format(new Date(), "yyyy-MM");
		}
		c.add(Calendar.MONTH, -1);  
		String lastDate = DateUtil.format(c.getTime(), "yyyy-MM");
	    String sql = reportService.getAdviseMothStatisSql(YEAR_MONTH,lastDate);
	    
		request.setAttribute("conDition", "rptSql="+sql+";YEAR_MONTH='"+YEAR_MONTH+"';");
		request.setAttribute("rptModel",   "AdviseMonthStaticReport.raq");
		request.setAttribute("printModel", "AdviseMonthStaticReport.raq");
		return mapping.findForward("pageResult");
	}
	
	
	/**
	 * 床垫季/年度售后投诉报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAdviseCDMothStatis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String CUUR_YEAR_MONTH_BEG = ParamUtil.get(request, "CUUR_YEAR_MONTH_BEG"); 
		String CUUR_YEAR_MONTH_END = ParamUtil.get(request, "CUUR_YEAR_MONTH_END"); 
		Date curr = null;
		//上年日期开始
		Calendar c = Calendar.getInstance();  
	    curr = DateUtil.parseDate(CUUR_YEAR_MONTH_BEG, "yyyy-MM");
		c.setTime(curr);
		String CURR_YEAR_MONTH = c.get(Calendar.YEAR)+"年0"+(c.get(Calendar.MONTH)+1)+"月";
		c.add(Calendar.YEAR, -1);  
		String LAST_YEAR_MONTH_BEG = DateUtil.format(c.getTime(), "yyyy-MM");
		String LAST_YEAR_MONTH = c.get(Calendar.YEAR)+"年0"+(c.get(Calendar.MONTH)+1)+"月";
			
		//上年日期结束 
		c = Calendar.getInstance();  
	    curr = DateUtil.parseDate(CUUR_YEAR_MONTH_END, "yyyy-MM");
		c.setTime(curr);
		CURR_YEAR_MONTH = CURR_YEAR_MONTH+"-0"+(c.get(Calendar.MONTH)+1)+"月";
		c.add(Calendar.YEAR, -1);  
		LAST_YEAR_MONTH = LAST_YEAR_MONTH+"-0"+(c.get(Calendar.MONTH)+1)+"月";
		String LAST_YEAR_MONTH_END = DateUtil.format(c.getTime(), "yyyy-MM");
	    String sql = reportService
	    .getCDAdviseMothStatisSql(CUUR_YEAR_MONTH_BEG,CUUR_YEAR_MONTH_END,LAST_YEAR_MONTH_BEG,LAST_YEAR_MONTH_END);
	    
	    String conDition = "rptSql="+sql+";LAST_YEAR_MONTH='"+LAST_YEAR_MONTH+"';CURR_YEAR_MONTH='"+CURR_YEAR_MONTH+"';TITLE='床垫'";
		request.setAttribute("conDition", conDition);
		request.setAttribute("rptModel",   "AdviseCDMothStatisReport.raq");
		request.setAttribute("printModel", "AdviseCDMothStatisReport.raq");
		return mapping.findForward("pageResult");
	}
	
	
	
	/**
	 * 软床季/年度售后投诉报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAdviseRCMothStatis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		String PRD_TYPE = "软床";
		String CUUR_YEAR_MONTH_BEG = ParamUtil.get(request, "CUUR_YEAR_MONTH_BEG"); 
		String CUUR_YEAR_MONTH_END = ParamUtil.get(request, "CUUR_YEAR_MONTH_END"); 
		Date curr = null;
		//上年日期开始
		Calendar c = Calendar.getInstance();  
	    curr = DateUtil.parseDate(CUUR_YEAR_MONTH_BEG, "yyyy-MM");
		c.setTime(curr);
		String CURR_YEAR_MONTH = c.get(Calendar.YEAR)+"年0"+(c.get(Calendar.MONTH)+1)+"月";
		c.add(Calendar.YEAR, -1);  
		String LAST_YEAR_MONTH_BEG = DateUtil.format(c.getTime(), "yyyy-MM");
		String LAST_YEAR_MONTH = c.get(Calendar.YEAR)+"年0"+(c.get(Calendar.MONTH)+1)+"月";
			
		//上年日期结束 
		c = Calendar.getInstance();  
	    curr = DateUtil.parseDate(CUUR_YEAR_MONTH_END, "yyyy-MM");
		c.setTime(curr);
		CURR_YEAR_MONTH = CURR_YEAR_MONTH+"-0"+(c.get(Calendar.MONTH)+1)+"月";
		c.add(Calendar.YEAR, -1);  
		LAST_YEAR_MONTH = LAST_YEAR_MONTH+"-0"+(c.get(Calendar.MONTH)+1)+"月";
		String LAST_YEAR_MONTH_END = DateUtil.format(c.getTime(), "yyyy-MM");
	    String sql = reportService
	    .getAdviseMothStatisSql(CUUR_YEAR_MONTH_BEG,CUUR_YEAR_MONTH_END,LAST_YEAR_MONTH_BEG,LAST_YEAR_MONTH_END,PRD_TYPE);
	    String conDition = "rptSql="+sql+";LAST_YEAR_MONTH='"+LAST_YEAR_MONTH+"';CURR_YEAR_MONTH='"+CURR_YEAR_MONTH+"';TITLE='"+PRD_TYPE+"'";
		request.setAttribute("conDition", conDition);
		request.setAttribute("rptModel",   "AdviseCDMothStatisReport.raq");
		request.setAttribute("printModel", "AdviseCDMothStatisReport.raq");
		return mapping.findForward("pageResult");
	}
	
	/**
	 * 软床季/年度售后投诉报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAdviseCTGMothStatis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		String PRD_TYPE = "床头柜";
		String CUUR_YEAR_MONTH_BEG = ParamUtil.get(request, "CUUR_YEAR_MONTH_BEG"); 
		String CUUR_YEAR_MONTH_END = ParamUtil.get(request, "CUUR_YEAR_MONTH_END"); 
		Date curr = null;
		//上年日期开始
		Calendar c = Calendar.getInstance();  
	    curr = DateUtil.parseDate(CUUR_YEAR_MONTH_BEG, "yyyy-MM");
		c.setTime(curr);
		String CURR_YEAR_MONTH = c.get(Calendar.YEAR)+"年0"+(c.get(Calendar.MONTH)+1)+"月";
		c.add(Calendar.YEAR, -1);  
		String LAST_YEAR_MONTH_BEG = DateUtil.format(c.getTime(), "yyyy-MM");
		String LAST_YEAR_MONTH = c.get(Calendar.YEAR)+"年0"+(c.get(Calendar.MONTH)+1)+"月";
			
		//上年日期结束 
		c = Calendar.getInstance();  
	    curr = DateUtil.parseDate(CUUR_YEAR_MONTH_END, "yyyy-MM");
		c.setTime(curr);
		CURR_YEAR_MONTH = CURR_YEAR_MONTH+"-0"+(c.get(Calendar.MONTH)+1)+"月";
		c.add(Calendar.YEAR, -1);  
		LAST_YEAR_MONTH = LAST_YEAR_MONTH+"-0"+(c.get(Calendar.MONTH)+1)+"月";
		String LAST_YEAR_MONTH_END = DateUtil.format(c.getTime(), "yyyy-MM");
	    String sql = reportService
	    .getAdviseMothStatisSql(CUUR_YEAR_MONTH_BEG,CUUR_YEAR_MONTH_END,LAST_YEAR_MONTH_BEG,LAST_YEAR_MONTH_END,PRD_TYPE);
	    String conDition = "rptSql="+sql+";LAST_YEAR_MONTH='"+LAST_YEAR_MONTH+"';CURR_YEAR_MONTH='"+CURR_YEAR_MONTH+"';TITLE='"+PRD_TYPE+"'";
		request.setAttribute("conDition", conDition);
		request.setAttribute("rptModel",   "AdviseCDMothStatisReport.raq");
		request.setAttribute("printModel", "AdviseCDMothStatisReport.raq");
		return mapping.findForward("pageResult");
	}
 
	
	
	/**
	 * XXXX年XX月国内销售民用产品预估表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toForecastAdvcMonthPrd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String YEAR = ParamUtil.get(request, "YEAR"); 
		String MONTH = ParamUtil.get(request, "MONTH");  
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");  
		StringBuffer asql = new StringBuffer();
		if(!StringUtil.isEmpty(YEAR)){
			asql.append(" and a.YEAR='").append(YEAR).append("' ");
		}
		if(!StringUtil.isEmpty(MONTH)){
			asql.append(" and a.MONTH='").append(MONTH).append("' ");
		}
		if(!StringUtil.isEmpty(PRD_NAME)){
			asql.append(" and a.PRD_NAME like '%").append(PRD_NAME).append("%' ");
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select temp.PRD_ID,temp.PRD_NO,temp.PRD_NAME,temp.PRD_SPEC,temp.PRVD_PRICE,temp.PRD_TYPE_NUM,temp.PRD_TYPE_NAME,temp.PAR_PRD_NAME,temp.ROWFLAG ")
	    .append(" from (select rst.PRD_ID, rst.PRD_NO,rst.PRD_NAME,rst.PRD_SPEC,rst.PRVD_PRICE,rst.PRD_TYPE_NUM,rst.PRD_TYPE_NAME,prd.PAR_PRD_NAME,1 ROWFLAG ")
	    .append(" from (select a.PRD_ID,a.PRD_NO,a.PRD_NAME,a.PRD_SPEC,a.PRVD_PRICE,sum(NVL(a.ADVC_RPT_NUM, 0)) PRD_TYPE_NUM,area.AREA_NAME_P PRD_TYPE_NAME ")
	    .append(" from ERP_ADVC_RPT_CHANN_DTL a  left join ERP_RPT_JOB_CHANN b  on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID left join BASE_CHANN c on c.CHANN_ID = b.CHANN_ID left join BASE_AREA area on c.AREA_ID = area.AREA_ID")
	    .append(" where b.STATE in ('提交', '审核通过') and a.PRD_ID is not null ")
	    .append(asql)
	    .append(" group by a.PRD_ID,a.PRD_NO,a.PRD_NAME,a.PRD_SPEC,a.PRVD_PRICE,area.AREA_NAME_P ")
	    .append(" union all ")
	    .append("select a.PRD_ID,a.PRD_NO,a.PRD_NAME,a.PRD_SPEC,a.PRVD_PRICE,sum(NVL(a.ADVC_RPT_NUM, 0)) PRD_TYPE_NO,decode(a.SHIP_POINT_ID,'00','总部','78','成都','90','北方','喜临门绍兴家具有限公司','绍兴','') PRD_TYPE_NAME ")
	    .append(" from ERP_ADVC_RPT_CHANN_DTL a left join ERP_RPT_JOB_CHANN b on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID where b.state in ('提交', '审核通过') and a.PRD_ID is not null")
	    .append(asql)
	    .append(" group by a.PRD_ID,a.PRD_NO,a.PRD_NAME,a.SHIP_POINT_ID,a.SHIP_POINT_NAME,a.PRD_SPEC,a.PRVD_PRICE) rst left join BASE_PRODUCT prd on rst.PRD_ID = prd.PRD_ID")
	    .append(" union all ")
	    .append(" select '' PRD_ID,rst.PAR_PRD_NAME || '合计：' as PRD_NO,'' PRD_NAME,'' PRD_SPEC,null PRVD_PRICE,rst.PRD_TYPE_NUM,rst.PRD_TYPE_NAME,rst.PAR_PRD_NAME,2 ROWFLAG")
	    .append(" from (select sum(NVL(a.ADVC_RPT_NUM, 0)) PRD_TYPE_NUM,area.AREA_NAME_P PRD_TYPE_NAME,prd.PAR_PRD_NAME from ERP_ADVC_RPT_CHANN_DTL a")
	    .append(" left join ERP_RPT_JOB_CHANN b on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID left join BASE_CHANN c on c.CHANN_ID = b.CHANN_ID left join BASE_AREA area on c.AREA_ID = area.AREA_ID left join BASE_PRODUCT prd on a.PRD_ID = prd.PRD_ID ")
	    .append(" where b.state in ('提交', '审核通过') and a.PRD_ID is not null ")
	    .append(asql)
	    .append(" group by prd.PAR_PRD_NAME, area.AREA_NAME_P ")
	    .append(" union all ")
	    .append(" select sum(NVL(a.ADVC_RPT_NUM, 0)) PRD_TYPE_NO,decode(a.SHIP_POINT_ID,'00','总部','78','成都','90','北方','喜临门绍兴家具有限公司','绍兴','') PRD_TYPE_NAME,prd.PAR_PRD_NAME ")
	    .append(" from ERP_ADVC_RPT_CHANN_DTL a left join ERP_RPT_JOB_CHANN b on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID left join BASE_PRODUCT prd on a.PRD_ID = prd.PRD_ID ")
	    .append(" where b.state in ('提交', '审核通过') and a.PRD_ID is not null ")
	    .append(asql)
	    .append(" group by prd.PAR_PRD_NAME, a.SHIP_POINT_ID, a.SHIP_POINT_NAME) rst ")
	    .append(" union all ")
	    .append(" select '' PRD_ID,'总合计:' PRD_NO,'' PRD_NAME,'' PRD_SPEC,null,rst.PRD_TYPE_NUM,rst.PRD_TYPE_NAME,'',3 ROWFLAG ")
	    .append(" from (select sum(NVL(a.ADVC_RPT_NUM, 0)) PRD_TYPE_NUM,area.AREA_NAME_P PRD_TYPE_NAME ")
        .append(" from ERP_ADVC_RPT_CHANN_DTL a left join ERP_RPT_JOB_CHANN b on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID left join BASE_CHANN c on c.CHANN_ID = b.CHANN_ID left join BASE_AREA area on c.AREA_ID = area.AREA_ID")
        .append(" where b.STATE in ('提交', '审核通过')  and a.PRD_ID is not null ")
        .append(asql)
        .append("  group by area.AREA_NAME_P ")
        .append(" union all ")
        .append(" select sum(NVL(a.ADVC_RPT_NUM, 0)) PRD_TYPE_NO,decode(a.SHIP_POINT_ID,'00','总部','78','成都','90','北方','喜临门绍兴家具有限公司','绍兴','') PRD_TYPE_NAME ")
        .append(" from ERP_ADVC_RPT_CHANN_DTL a left join ERP_RPT_JOB_CHANN b on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID  where b.STATE in ('提交', '审核通过') and a.PRD_ID is not null ")
        .append(asql)
        .append(" group by a.SHIP_POINT_NAME, a.SHIP_POINT_ID) rst) temp order by temp.PAR_PRD_NAME, ROWFLAG, PRD_TYPE_NAME ");
		
		StringBuffer countStr=new StringBuffer();
		countStr.append("select count(1) cnt from (")
			 .append(" select PRD_ID from ( ")
			 .append(" select a.PRD_ID from ERP_ADVC_RPT_CHANN_DTL a left join ERP_RPT_JOB_CHANN b on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID left join BASE_CHANN c ")
			 .append(" on c.CHANN_ID = b.CHANN_ID left join BASE_AREA area on c.AREA_ID = area.AREA_ID where b.STATE in ('提交', '审核通过') and a.PRD_ID is not null")
             .append(asql)
			 .append(" group by a.PRD_ID")
			 .append(" union all ")
			 .append(" select a.PRD_ID from ERP_ADVC_RPT_CHANN_DTL a left join ERP_RPT_JOB_CHANN b on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID ")
			 .append(" where b.state in ('提交', '审核通过') and a.PRD_ID is not null ").append(asql).append(" group by a.PRD_ID ) group by PRD_ID")
			 .append(" union all ")
			 .append(" select PAR_PRD_NAME from ( ")
			 .append(" select prd.PAR_PRD_NAME from ERP_ADVC_RPT_CHANN_DTL a left join ERP_RPT_JOB_CHANN b on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID left join BASE_CHANN c ")
			 .append(" on c.CHANN_ID = b.CHANN_ID left join BASE_AREA area on c.AREA_ID = area.AREA_ID left join BASE_PRODUCT prd on a.PRD_ID = prd.PRD_ID ")
             .append(" where b.state in ('提交', '审核通过') and a.PRD_ID is not null ").append(asql).append(" group by prd.PAR_PRD_NAME ")
             .append(" union all ")
             .append(" select prd.PAR_PRD_NAME from ERP_ADVC_RPT_CHANN_DTL a left join ERP_RPT_JOB_CHANN b on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID left join BASE_PRODUCT prd on a.PRD_ID = prd.PRD_ID ")
             .append(" where b.state in ('提交', '审核通过') and a.PRD_ID is not null ").append(asql).append(" group by prd.PAR_PRD_NAME ) rst group by PAR_PRD_NAME")
			 .append(" union all ")
			 .append(" select '总计1条' from dual) ");
		
		Map params = new HashMap();
		params.put("SelSQL", countStr.toString());
		Map resMap = reportService.selecTotalCount(params);
		int count=StringUtil.getInteger(resMap.get("CNT"));
		request.setAttribute("totalCount", count);
		request.setAttribute("conDition", "rptSql="+sql+";YEAR='"+YEAR+"';MONTH='"+MONTH+"';");
		request.setAttribute("rptModel",   "ForecastAdvcMonthPrd.raq");
		request.setAttribute("printModel", "ForecastAdvcMonthPrd.raq");
		return mapping.findForward("realPageResult");
//		return mapping.findForward("pageResult");
	}

	/**
	 * 加盟商-货品维度: 偏差率
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toRptChannPrdDiff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		int SHOW_TYPE = ParamUtil.getInt(request, "SHOW_TYPE",0); 
		//渠道-货品维度
		if(0 == SHOW_TYPE){
			channWDRptDiff(request);
		}
		//渠道-货品分类维度
		if(1 == SHOW_TYPE){
			toRptChannPrdTypeDiff(request);
		}
		//货品分类维度
		if(2 == SHOW_TYPE){
			toRptPrdTypeDiff(request);
		}
		return mapping.findForward("pageResult");
	}
	//渠道-货品维度
	private void channWDRptDiff(HttpServletRequest request){
		String YEAR = ParamUtil.get(request, "YEAR"); 
		String MONTH = ParamUtil.get(request, "MONTH");  
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");  
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");  
		String PRD_TYPE = ParamUtil.get(request, "PRD_TYPE");  
		StringBuffer asql = new StringBuffer();
		if(!StringUtil.isEmpty(YEAR)){
			asql.append(" and zq.YEAR='").append(YEAR).append("' ");
		}
		if(!StringUtil.isEmpty(MONTH)){
			asql.append(" and zq.MONTH='").append(MONTH).append("' ");
		}
		if(!StringUtil.isEmpty(CHANN_NO)){
			asql.append(StringUtil.creCon("zq.CHANN_NO", CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(CHANN_NAME)){
			asql.append(StringUtil.creCon("zq.CHANN_NAME", CHANN_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_TYPE)){
			asql.append(StringUtil.creCon("zq.PAR_PRD_NAME", PRD_TYPE, ","));
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select zq.CHANN_NO, zq.CHANN_NAME, zq.PRD_ID, zq.PRD_NO, zq.PRD_NAME, zq.PRD_SPEC,  zq.ADVC_RPT_NUM,  zq.PAR_PRD_NO,")
	    .append("zq.YEAR,zq.MONTH,zq.PAR_PRD_NAME,  NVL(jd.ORDER_NUM, 0) ORDER_NUM,")
	    .append("case when NVL(jd.ORDER_NUM, 0)=0 or NVL(zq.ADVC_RPT_NUM,0)=0")
	    .append(" then 0")
	    .append(" else  round(abs(zq.ADVC_RPT_NUM-jd.ORDER_NUM)/zq.ADVC_RPT_NUM*100/100,2) ")
	    .append(" end DIFF")
	    .append(" from (select a.PRD_ID,  a.PRD_NO, a.PRD_NAME,  a.PRD_SPEC,  a.PRVD_PRICE,a.YEAR,a.MONTH,")
	    .append(" b.CHANN_NO,  b.CHANN_NAME, sum(NVL(a.ADVC_RPT_NUM, 0)) ADVC_RPT_NUM,")
	    .append(" p.PAR_PRD_NO, p.PAR_PRD_NAME")
	    .append(" from ERP_ADVC_RPT_CHANN_DTL a")
	    .append(" left join ERP_RPT_JOB_CHANN b")
	    .append(" on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID")
	    .append(" left join BASE_PRODUCT p")
	    .append(" on p.PRD_ID = a.PRD_ID")
	    .append(" where b.STATE in ('提交', '审核通过')")
	    .append(" group by a.PRD_ID,a.PRD_NO,a.PRD_NAME,a.PRD_SPEC,")
	    .append(" a.PRVD_PRICE,a.YEAR,a.MONTH,b.CHANN_NO,b.CHANN_NAME,")
	    .append(" p.PAR_PRD_NO,p.PAR_PRD_NAME) zq")
	    .append(" left join (select t.ORDER_CHANN_NO,t.ORDER_CHANN_NAME,to_char(t.ORDER_DATE, 'yyyy')YEAR,to_char(t.ORDER_DATE, 'mm')MONTH,d.PRD_ID,d.PRD_NO,")
	    .append(" d.PRD_NAME,p.PAR_PRD_NO,p.PAR_PRD_NAME,")
	    .append(" sum(NVL(d.ORDER_NUM, 0)) ORDER_NUM")
	    .append(" from ERP_SALE_ORDER_DTL d")
	    .append(" left join ERP_SALE_ORDER t")
	    .append(" on t.SALE_ORDER_ID = d.SALE_ORDER_ID")
	    .append(" left join BASE_PRODUCT p")
	    .append(" on p.PRD_ID = d.PRD_ID")
	    .append(" where t.state = '审核通过'")
	    .append(" and d.DEL_FLAG = 0")
	    .append(" and t.DEL_FLAG = 0")
	    .append(" group by d.PRD_ID,  d.PRD_NO,d.PRD_NAME,p.PAR_PRD_NO,p.PAR_PRD_NAME,t.ORDER_CHANN_NO, t.ORDER_CHANN_NAME,t.ORDER_DATE")
	    .append(" ) jd")
	    .append(" on zq.PRD_ID = jd.PRD_ID")
	    .append(" and zq.CHANN_NAME = jd.ORDER_CHANN_NO")
	    .append(" and zq.YEAR=jd.YEAR and zq.MONTH=jd.MONTH ")
	    .append(" where 1=1 ")
	    .append(asql)
	    .append(" order by zq.CHANN_NO");
	    
		request.setAttribute("conDition", "rptSql="+sql);
		request.setAttribute("rptModel",   "RptChannPrdDiffReport.raq");
		request.setAttribute("printModel", "RptChannPrdDiffReport.raq");
	}
	
	
	/**
	 * 加盟商-货品分类维度: 偏差率
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void toRptChannPrdTypeDiff(HttpServletRequest request){
		String YEAR = ParamUtil.get(request, "YEAR"); 
		String MONTH = ParamUtil.get(request, "MONTH");  
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");  
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");  
		String PRD_TYPE = ParamUtil.get(request, "PRD_TYPE");  
		StringBuffer asql = new StringBuffer();
		if(!StringUtil.isEmpty(YEAR)){
			asql.append(" and zq.YEAR='").append(YEAR).append("' ");
		}
		if(!StringUtil.isEmpty(MONTH)){
			asql.append(" and zq.MONTH='").append(MONTH).append("' ");
		}
		if(!StringUtil.isEmpty(CHANN_NO)){
			asql.append(StringUtil.creCon("zq.CHANN_NO", CHANN_NO, ","));
		}
		if(!StringUtil.isEmpty(CHANN_NAME)){
			asql.append(StringUtil.creCon("zq.CHANN_NAME", CHANN_NAME, ","));
		}
		if(!StringUtil.isEmpty(PRD_TYPE)){
			asql.append(StringUtil.creCon("zq.PAR_PRD_NAME", PRD_TYPE, ","));
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select zq.CHANN_NO, zq.CHANN_NAME,zq.ADVC_RPT_NUM, zq.PAR_PRD_ID,zq.PAR_PRD_NO,")
	    .append("zq.YEAR,zq.MONTH,zq.PAR_PRD_NAME,  NVL(jd.ORDER_NUM, 0) ORDER_NUM,")
	    .append("case when NVL(jd.ORDER_NUM, 0)=0 or NVL(zq.ADVC_RPT_NUM,0)=0")
	    .append(" then 0")
	    .append(" else  round(abs(zq.ADVC_RPT_NUM -jd.ORDER_NUM)/zq.ADVC_RPT_NUM*100/100,2)")
	    .append(" end DIFF")
	    .append(" from (select a.YEAR,a.MONTH,")
	    .append(" b.CHANN_NO,  b.CHANN_NAME, sum(NVL(a.ADVC_RPT_NUM, 0)) ADVC_RPT_NUM,")
	    .append(" p.PAR_PRD_ID,p.PAR_PRD_NO, p.PAR_PRD_NAME")
	    .append(" from ERP_ADVC_RPT_CHANN_DTL a")
	    .append(" left join ERP_RPT_JOB_CHANN b")
	    .append(" on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID")
	    .append(" left join BASE_PRODUCT p")
	    .append(" on p.PRD_ID = a.PRD_ID")
	    .append(" where b.STATE in ('提交', '审核通过')")
	    .append(" group by ")
	    .append(" a.YEAR,a.MONTH,b.CHANN_NO,b.CHANN_NAME,")
	    .append(" p.PAR_PRD_ID, p.PAR_PRD_NO,p.PAR_PRD_NAME) zq")
	    .append(" left join (select t.ORDER_CHANN_NO,t.ORDER_CHANN_NAME,to_char(t.ORDER_DATE, 'yyyy')YEAR,to_char(t.ORDER_DATE, 'mm')MONTH,")
	    .append(" p.PAR_PRD_ID,p.PAR_PRD_NO,p.PAR_PRD_NAME,")
	    .append(" sum(NVL(d.ORDER_NUM, 0)) ORDER_NUM")
	    .append(" from ERP_SALE_ORDER_DTL d")
	    .append(" left join ERP_SALE_ORDER t")
	    .append(" on t.SALE_ORDER_ID = d.SALE_ORDER_ID")
	    .append(" left join BASE_PRODUCT p")
	    .append(" on p.PRD_ID = d.PRD_ID")
	    .append(" where t.state = '审核通过'")
	    .append(" and d.DEL_FLAG = 0")
	    .append(" and t.DEL_FLAG = 0")
	    .append(" group by p.PAR_PRD_ID,p.PAR_PRD_NO,p.PAR_PRD_NAME,t.ORDER_CHANN_NO, t.ORDER_CHANN_NAME,t.ORDER_DATE")
	    .append(" ) jd")
	    .append(" on zq.PAR_PRD_ID = jd.PAR_PRD_ID")
	    .append(" and zq.CHANN_NAME = jd.ORDER_CHANN_NO")
	    .append(" and zq.YEAR=jd.YEAR and zq.MONTH=jd.MONTH ")
	    .append(" where 1=1 and (zq.PAR_PRD_ID is not null) ")
	    .append(asql)
	    .append(" order by zq.CHANN_NO");
	    
		request.setAttribute("conDition", "rptSql="+sql);
		request.setAttribute("rptModel",   "RptChannPrdTypeDiffReport.raq");
		request.setAttribute("printModel", "RptChannPrdTypeDiffReport.raq");
		
	}
	
	
	/**
	 * 货品分类维度: 偏差率
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void toRptPrdTypeDiff(HttpServletRequest request){
		String YEAR = ParamUtil.get(request, "YEAR"); 
		String MONTH = ParamUtil.get(request, "MONTH");  
		String PRD_TYPE = ParamUtil.get(request, "PRD_TYPE");  
		StringBuffer asql = new StringBuffer();
		if(!StringUtil.isEmpty(YEAR)){
			asql.append(" and zq.YEAR='").append(YEAR).append("' ");
		}
		if(!StringUtil.isEmpty(MONTH)){
			asql.append(" and zq.MONTH='").append(MONTH).append("' ");
		}
		if(!StringUtil.isEmpty(PRD_TYPE)){
			asql.append(StringUtil.creCon("zq.PAR_PRD_NAME", PRD_TYPE, ","));
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select zq.ADVC_RPT_NUM, zq.PAR_PRD_ID,zq.PAR_PRD_NO,")
	    .append("zq.YEAR,zq.MONTH,zq.PAR_PRD_NAME,  NVL(jd.ORDER_NUM, 0) ORDER_NUM,")
	    .append("case when NVL(jd.ORDER_NUM, 0)=0 or NVL(zq.ADVC_RPT_NUM,0)=0")
	    .append(" then 0")
	    .append(" else  round(abs(zq.ADVC_RPT_NUM-jd.ORDER_NUM)/zq.ADVC_RPT_NUM*100/100,2)")
	    .append(" end DIFF")
	    .append(" from (select a.YEAR,a.MONTH,")
	    .append(" sum(NVL(a.ADVC_RPT_NUM, 0)) ADVC_RPT_NUM,")
	    .append(" p.PAR_PRD_ID,p.PAR_PRD_NO, p.PAR_PRD_NAME")
	    .append(" from ERP_ADVC_RPT_CHANN_DTL a")
	    .append(" left join ERP_RPT_JOB_CHANN b")
	    .append(" on a.RPT_JOB_CHANN_ID = b.RPT_JOB_CHANN_ID")
	    .append(" left join BASE_PRODUCT p")
	    .append(" on p.PRD_ID = a.PRD_ID")
	    .append(" where b.STATE in ('提交', '审核通过')")
	    .append(" group by ")
	    .append(" a.YEAR,a.MONTH,")
	    .append(" p.PAR_PRD_ID, p.PAR_PRD_NO,p.PAR_PRD_NAME) zq")
	    .append(" left join (select to_char(t.ORDER_DATE, 'yyyy')YEAR,to_char(t.ORDER_DATE, 'mm')MONTH,")
	    .append(" p.PAR_PRD_ID,p.PAR_PRD_NO,p.PAR_PRD_NAME,")
	    .append(" sum(NVL(d.ORDER_NUM, 0)) ORDER_NUM")
	    .append(" from ERP_SALE_ORDER_DTL d")
	    .append(" left join ERP_SALE_ORDER t")
	    .append(" on t.SALE_ORDER_ID = d.SALE_ORDER_ID")
	    .append(" left join BASE_PRODUCT p")
	    .append(" on p.PRD_ID = d.PRD_ID")
	    .append(" where t.state = '审核通过'")
	    .append(" and d.DEL_FLAG = 0")
	    .append(" and t.DEL_FLAG = 0")
	    .append(" group by p.PAR_PRD_ID,p.PAR_PRD_NO,p.PAR_PRD_NAME,t.ORDER_DATE")
	    .append(" ) jd")
	    .append(" on zq.PAR_PRD_ID = jd.PAR_PRD_ID")
	    .append(" and zq.YEAR=jd.YEAR and zq.MONTH=jd.MONTH ")
	    .append(" where 1=1 and (zq.PAR_PRD_ID is not null) ")
	    .append(asql);
	    
		request.setAttribute("conDition", "rptSql="+sql);
		request.setAttribute("rptModel",   "RptPrdTypeDiffReport.raq");
		request.setAttribute("printModel", "RptPrdTypeDiffReport.raq");
	}
 
	
	/**
	 * 营销经理日报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toMkmDayReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String,String> param = new HashMap<String,String>();
		String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME"); 
		String MKM_NAME   = ParamUtil.get(request, "MKM_NAME"); 
		String REPORT_DATE_BEG = ParamUtil.get(request, "REPORT_DATE_BEG");
		String REPORT_DATE_END = ParamUtil.get(request, "REPORT_DATE_END");
		String VST_DATE_BEG    = ParamUtil.get(request, "VST_DATE_BEG");
		String VST_DATE_END    = ParamUtil.get(request, "VST_DATE_END");
		String STATE  = ParamUtil.get(request, "STATE");
		String QUERY_STYLE = ParamUtil.get(request, "QUERY_STYLE");
		
		param.put("WAREA_NAME", WAREA_NAME);
		param.put("MKM_NAME_SQL", StringUtil.creCon("m.MKM_NAME", MKM_NAME, ","));
		param.put("REPORT_DATE_BEG", REPORT_DATE_BEG);
		param.put("REPORT_DATE_END", REPORT_DATE_END);
		param.put("VST_DATE_BEG", VST_DATE_BEG);
		param.put("VST_DATE_END", VST_DATE_END);
		param.put("STATE", STATE);
		
		StringBuffer conSql = new StringBuffer(" ");
		
        StringBuffer top_sql  = new StringBuffer("  SELECT m.MKM_DAY_RPT_NO,m.WAREA_NAME,to_char(m.REPORT_DATE,'yyyy-MM-DD') REPORT_DATE ,m.MKM_NAME,to_char(m.VST_DATE,'yyyy-MM-DD') VST_DATE ,m.ON_TRIP,m.CORP_BUSS_DEAL,m.WAKE_OFF "); 
        StringBuffer count_sql= new StringBuffer("  SELECT COUNT(*) ");
        StringBuffer left_sql = new StringBuffer( " FROM  DRP_MKM_DAY_REPORT m ");
 
 
		//润乾报表列sql
		StringBuffer sqlColumn = new StringBuffer("");
	    //group by 字段
		//战区
		String CHANN_VISIT_SHOW = ParamUtil.get(request, "CHANN_VISIT_SHOW"); 
		//是否显示战区这列 true->不显示
		boolean CHANN_VISIT_FLAG = true;
        if(!StringUtil.isEmpty(CHANN_VISIT_SHOW)){
        	top_sql.append(",c.CHANN_NO,c.CHANN_NAME,c.MATT_AMOUNT,c.SOFT_BED_AMOUNT,c.OTHER_AMOUNT,c.CHANN_PROBLEM,c.IMPRV_PLAN,c.COMPET_PRODUCT,c.SUPPORT_REQ");
            left_sql.append(" LEFT JOIN DRP_CHANN_VISIT_DAY c on c.MKM_DAY_RPT_ID = m.MKM_DAY_RPT_ID  ");
            CHANN_VISIT_FLAG = false; 
            
            //sqlColumn.append(";CHANN_VISIT_SQL=ds1.select(CHANN_NO),ds1.CHANN_NAME,ds1.MATT_AMOUNT,ds1.SOFT_BED_AMOUNT,ds1.OTHER_AMOUNT,ds1.CHANN_PROBLEM,ds1.IMPRV_PLAN,ds1.COMPET_PRODUCT,ds1.SUPPORT_REQ ");

            sqlColumn.append(";CHANN_NO_SQL=ds1.select(CHANN_NO) ");
        	sqlColumn.append(";CHANN_NAME_SQL=ds1.CHANN_NAME");
        	sqlColumn.append(";MATT_AMOUNT_SQL=ds1.MATT_AMOUNT");
        	sqlColumn.append(";SOFT_BED_AMOUNT_SQL=ds1.SOFT_BED_AMOUNT");
        	sqlColumn.append(";OTHER_AMOUNT_SQL=ds1.OTHER_AMOUNT");
        	sqlColumn.append(";CHANN_PROBLEM_SQL=ds1.CHANN_PROBLEM");
        	sqlColumn.append(";IMPRV_PLAN_SQL=ds1.IMPRV_PLAN");
        	sqlColumn.append(";COMPET_PRODUCT_SQL=ds1.COMPET_PRODUCT");
        	sqlColumn.append(";SUPPORT_REQ_SQL=ds1.SUPPORT_REQ");
        	sqlColumn.append(";TOTAL_AMOUNT_SQL=ds1.MATT_AMOUNT+ds1.SOFT_BED_AMOUNT+ds1.OTHER_AMOUNT");
        }
		
       //推广活动推进
       String PROMOTION_ACTV_SHOW = ParamUtil.get(request, "PROMOTION_ACTV_SHOW"); 
       boolean PROMOTION_ACTV_FLAG = true;
       if(!StringUtil.isEmpty(PROMOTION_ACTV_SHOW)){
      	top_sql.append(",p.BEG_DATE,p.ACTV_STYLE,p.ACTV_TITLE,p.AGREE_DATE,p.AGREE_ADDR,p.ACTV_PRMT_COST,p.ADVC_DEAL_BILL_NUM,p.ADVC_DEAL_BILL_AMOUNT,")
      	       .append(" p.FORWORD_DATE,p.SALE_CARD_NUM,p.OTHER_REMARK,p.SALE_CARD_NUM_END,p.CHECK_CARD_NUM,p.DEAL_BILL_NUM,p.ADVC_DEAL_AMOUNT,p.COST_AMOUNT");

      	left_sql.append(" LEFT JOIN DRP_PROMOTION_ACTV  p on p.MKM_DAY_RPT_ID = m.MKM_DAY_RPT_ID ");
      	PROMOTION_ACTV_FLAG = false;
      	sqlColumn.append(";BEG_DATE_SQL=ds1.BEG_DATE");
      	sqlColumn.append(";ACTV_STYLE_SQL=ds1.ACTV_STYLE");
      	sqlColumn.append(";ACTV_TITLE_SQL=ds1.ACTV_TITLE");
      	sqlColumn.append(";AGREE_DATE_SQL=ds1.AGREE_DATE");
      	sqlColumn.append(";AGREE_ADDR_SQL=ds1.AGREE_ADDR");
      	sqlColumn.append(";ACTV_PRMT_COST_SQL=ds1.ACTV_PRMT_COST");
      	sqlColumn.append(";ADVC_DEAL_BILL_NUM_SQL=ds1.ADVC_DEAL_BILL_NUM");
      	sqlColumn.append(";ADVC_DEAL_BILL_AMOUNT_SQL=ds1.ADVC_DEAL_BILL_AMOUNT");
      	sqlColumn.append(";FORWORD_DATE_SQL=ds1.FORWORD_DATE");
      	sqlColumn.append(";SALE_CARD_NUM_SQL=ds1.SALE_CARD_NUM");
      	sqlColumn.append(";OTHER_REMARK_SQL =ds1.OTHER_REMARK");
      	sqlColumn.append(";SALE_CARD_NUM_END_SQL=ds1.SALE_CARD_NUM_END");
      	sqlColumn.append(";CHECK_CARD_NUM_SQL =ds1.CHECK_CARD_NUM");
      	sqlColumn.append(";DEAL_BILL_NUM_SQL = ds1.DEAL_BILL_NUM");
      	sqlColumn.append(";ADVC_DEAL_AMOUNT_SQL = ds1.ADVC_DEAL_AMOUNT");
      	sqlColumn.append(";COST_AMOUNT_SQL = ds1.COST_AMOUNT");
      	sqlColumn.append(";RATE_AMOUNT_SQL = round(ds1.ADVC_DEAL_AMOUNT/ds1.COST_AMOUNT,2)");
      }
        
        //分销商开发与拜访
        String DISTRIBUTE_SHOW = ParamUtil.get(request, "DISTRIBUTE_SHOW"); 
        boolean DISTRIBUTE_FLAG = true;
        if(!StringUtil.isEmpty(DISTRIBUTE_SHOW)){
        	top_sql.append(",d.DISTRIBUTOR_NO,d.DISTRIBUTOR_NAME,d.MAIN_PROBLEM,d.SOLUTION,d.WILL_DISTRIBUTOR_TYPE,d.WILL_SALE_STORE_NAME,d.WILL_DISTRIBUTOR_NAME")
        	.append(",d.WILL_DISTRIBUTOR_TEL,d.WILL_DISTRIBUTOR_ADDRESS,WILL_DISTRIBUTOR_BUSS_BRAND,d.WILL_DISTRIBUTOR_BUSS_CLASS,d.WILL_DEGREE");
        	left_sql.append(" LEFT JOIN DRP_DISTRIBUTOR_VISIT d on d.MKM_DAY_RPT_ID = m.MKM_DAY_RPT_ID ");
        	DISTRIBUTE_FLAG = false;
        	sqlColumn.append(";DISTRIBUTOR_NO_SQL  =ds1.DISTRIBUTOR_NO");
        	sqlColumn.append(";DISTRIBUTOR_NAME_SQL=ds1.DISTRIBUTOR_NAME");
        	sqlColumn.append(";MAIN_PROBLEM_SQL=ds1.MAIN_PROBLEM");
        	sqlColumn.append(";SOLUTION_SQL=ds1.SOLUTION");
        	sqlColumn.append(";WILL_DISTRIBUTOR_TYPE_SQL=ds1.WILL_DISTRIBUTOR_TYPE");
        	sqlColumn.append(";WILL_SALE_STORE_NAME_SQL=ds1.WILL_SALE_STORE_NAME");
        	sqlColumn.append(";WILL_DISTRIBUTOR_NAME_SQL=ds1.WILL_DISTRIBUTOR_NAME");
        	sqlColumn.append(";WILL_DISTRIBUTOR_TEL_SQL=ds1.WILL_DISTRIBUTOR_TEL");
        	sqlColumn.append(";WILL_DISTRIBUTOR_ADDRESS_SQL=ds1.WILL_DISTRIBUTOR_ADDRESS");
        	sqlColumn.append(";WILL_DISTRIBUTOR_BUSS_BRAND_SQL=ds1.WILL_DISTRIBUTOR_BUSS_BRAND"); 
        	sqlColumn.append(";WILL_DISTRIBUTOR_BUSS_CLASS_SQL=ds1.WILL_DISTRIBUTOR_BUSS_CLASS");
        	sqlColumn.append(";WILL_DEGREE_SQL=ds1.WILL_DEGREE");
        }
        
        //"1+N"合作商开发与拜访
        String COOP_VISIT_SHOW = ParamUtil.get(request, "COOP_VISIT_SHOW"); 
        boolean COOP_VISIT_FLAG = true;
        if(!StringUtil.isEmpty(COOP_VISIT_SHOW)){
        	top_sql.append(",coo.DISTRIBUTOR_NO,coo.DISTRIBUTOR_NAME,coo.MAIN_PROBLEM,coo.SOLUTION,coo.MARKET_TYPE,coo.WILL_SALE_STORE_NAME,coo.CUST_NAME, ")
        	.append("coo.CUST_TEL,coo.MARKET_ADDRESS,coo.MARKET_BUSS_BRAND,coo.MARKET_BUSS_CLASS,coo.WILL_DEGREE,coo.NO_WILL_RESON");
        	left_sql.append(" LEFT JOIN DRP_COOPERATIVE_VISIT coo on coo.MKM_DAY_RPT_ID = m.MKM_DAY_RPT_ID ");
        	COOP_VISIT_FLAG = false;
        	sqlColumn.append(";NDISTRIBUTOR_NO_SQL    =ds1.DISTRIBUTOR_NO");
        	sqlColumn.append(";NDISTRIBUTOR_NAME_SQL  =ds1.DISTRIBUTOR_NAME");
        	sqlColumn.append(";NMAIN_PROBLEM_SQL  =ds1.MAIN_PROBLEM");
        	sqlColumn.append(";NSOLUTION_SQL      =ds1.SOLUTION");
        	sqlColumn.append(";NMARKET_TYPE_SQL   =ds1.MARKET_TYPE");
        	sqlColumn.append(";NWILL_SALE_STORE_NAME_SQL  =ds1.WILL_SALE_STORE_NAME");
        	sqlColumn.append(";NCUST_NAME_SQL   =ds1.CUST_NAME");
        	sqlColumn.append(";NCUST_TEL_SQL    =ds1.CUST_TEL");
        	sqlColumn.append(";NMARKET_ADDRESS_SQL  = ds1.MARKET_ADDRESS");
        	sqlColumn.append(";NMARKET_BUSS_BRAND_SQL  =ds1.MARKET_BUSS_BRAND");
        	sqlColumn.append(";NMARKET_BUSS_CLASS_SQL  =ds1.MARKET_BUSS_CLASS");
        	sqlColumn.append(";NWILL_DEGREE_SQL    =ds1.WILL_DEGREE");
        }
        
        //导购员培训
        String SHOP_GUIDE_SHOW = ParamUtil.get(request, "SHOP_GUIDE_SHOW"); 
        boolean SHOP_GUIDE_FLAG = true;
        if(!StringUtil.isEmpty(SHOP_GUIDE_SHOW)){
        	top_sql.append(",s.TRAN_NUM,s.TRAN_TITLE,s.TRAN_TYPE,to_char(s.TRAN_DATE,'yyyy-MM-DD')TRAN_DATE ");
        	left_sql.append("LEFT JOIN DRP_SHOPP_GUIDE_TRAN s  on s.MKM_DAY_RPT_ID  = m.MKM_DAY_RPT_ID ");
        	SHOP_GUIDE_FLAG = false;
        	sqlColumn.append(";TRAN_NUM_SQL     =ds1.TRAN_NUM");
        	sqlColumn.append(";TRAN_TITLE_SQL   =ds1.TRAN_TITLE");
        	sqlColumn.append(";TRAN_TYPE_SQL    =ds1.TRAN_TYPE");
        	sqlColumn.append(";TRAN_DATE_SQL   =ds1.TRAN_DATE");
        }
        
        //门店拜访
        String STORE_VISIT_SHOW = ParamUtil.get(request, "STORE_VISIT_SHOW");
        boolean STORE_VISIT_FLAG = true;
        if(!StringUtil.isEmpty(STORE_VISIT_SHOW)){
        	top_sql.append(",t.TERM_NO,t.TERM_NAME,t.CHANN_NO,t.CHANN_NAME,t.HARD_DECORATE,t.SOFT_DECORATE,")
        	       .append(" t.SHOPP_GUIDE,t.MAIN_PROBLEM,t.SOLUTION ");
        	left_sql.append(" LEFT JOIN DRP_TERMINAL_VISIT t on t.MKM_DAY_RPT_ID  = m.MKM_DAY_RPT_ID ");
        	STORE_VISIT_FLAG = false;
        	sqlColumn.append(";TERM_NO_SQL          =ds1.TERM_NO");
        	sqlColumn.append(";TERM_NAME_SQL        =ds1.TERM_NAME");
        	sqlColumn.append(";SCHANN_NAME_SQL      =ds1.CHANN_NAME");
        	sqlColumn.append(";SCHANN_NO_SQL        =ds1.CHANN_NO");
        	sqlColumn.append(";HARD_DECORATE_SQL    =ds1.HARD_DECORATE");
        	sqlColumn.append(";SOFT_DECORATE_SQL    =ds1.SOFT_DECORATE");
        	sqlColumn.append(";SHOPP_GUIDE_SQL      =ds1.SHOPP_GUIDE");
        	sqlColumn.append(";SMAIN_PROBLEM_SQL    =ds1.MAIN_PROBLEM");
        	sqlColumn.append(";SSOLUTION_SQL        =ds1.SOLUTION");
        }
        StringBuffer bottom_sql = new StringBuffer();
        bottom_sql.append("WHERE m.DEL_FLAG=0 ");
        if(!StringUtil.isEmpty(WAREA_NAME)){
        	String WAREA_NAME_SQL = StringUtil.creCon("m.WAREA_NAME", WAREA_NAME, ",");
        	bottom_sql.append(WAREA_NAME_SQL);
		}  
        if(!StringUtil.isEmpty(MKM_NAME)){
        	
        	String MKM_NAME_SQL = StringUtil.creCon("m.MKM_NAME", MKM_NAME, ",");
        	bottom_sql.append(MKM_NAME_SQL);
		}  
        
        if(!StringUtil.isEmpty(REPORT_DATE_BEG)){
        	bottom_sql.append(" and to_char(m.REPORT_DATE,'yyyy-MM-DD')>='"+REPORT_DATE_BEG+"' ");
		} 
		if(!StringUtil.isEmpty(REPORT_DATE_END)){
			bottom_sql.append(" and to_char(m.REPORT_DATE,'yyyy-MM-DD')<='"+REPORT_DATE_END+"' ");
		} 
		
		if(!StringUtil.isEmpty(VST_DATE_BEG)){
			bottom_sql.append(" and to_char(m.VST_DATE,'yyyy-MM-DD')>='"+VST_DATE_BEG+"' ");
		} 
		if(!StringUtil.isEmpty(VST_DATE_END)){
			bottom_sql.append(" and to_char(m.VST_DATE,'yyyy-MM-DD')<='"+VST_DATE_END+"' ");
		} 
		if(!StringUtil.isEmpty(STATE)){
			bottom_sql.append(" and m.STATE = '"+STATE+"'");
		}
        
        
        String rptSql =  top_sql.toString()+left_sql.toString()+bottom_sql.toString();

        String params = "rptSql=" + rptSql
        +";CHANN_VISIT_FLAG="+CHANN_VISIT_FLAG
        +";PROMOTION_ACTV_FLAG="+PROMOTION_ACTV_FLAG
        +";DISTRIBUTE_FLAG="+DISTRIBUTE_FLAG
        +";COOP_VISIT_FLAG="+COOP_VISIT_FLAG
        +";SHOP_GUIDE_FLAG="+SHOP_GUIDE_FLAG
        +";STORE_VISIT_FLAG="+STORE_VISIT_FLAG;
        
        boolean IS_FIRST_SHOW = true;
        if(sqlColumn.toString().length()<1){
        	IS_FIRST_SHOW = false;
        } 
    	params = params 
    	+sqlColumn.toString()
    	+";IS_FIRST_SHOW="+IS_FIRST_SHOW;
    	
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("conDition", params);
		if(QUERY_STYLE.equals("报表形式")){
			request.setAttribute("rptModel",   "MkmDayReport.raq");
			request.setAttribute("printModel", "MkmDayReport.raq");
			return mapping.findForward("pageResult");
		}else{
			String MKM_NAMES = "";
			String VST_DATES = "";
			String MKMS = "";
			Map<String, String> map = new HashMap<String, String>() ;  
			List li = new ArrayList();
			int count = reportService.getCountMkm(param);
			request.setAttribute("count", count);
			MkmDayReportModel model = new MkmDayReportModel();
			List<MkmDayReportModel> list = reportService.queryMkmdayreport(param);
			for(int i=0;i<list.size();i++){
				  li.add(list.get(i).getMKM_NAME());
				  li.add(list.get(i).getVST_DATE());
			}
		   for(int i=0;i<li.size();i++){
			   if(i%2==0){
				   MKM_NAMES += li.get(i)+",";
			   }else{
				   VST_DATES += li.get(i)+",";
			   }
		   }
		    String Names = "";
		    String Dates = "";
		    List<String>  arr = new ArrayList<String>();
		    if(!"".equals(MKM_NAMES)){
		        MKM_NAMES   = MKM_NAMES.substring(0,MKM_NAMES.length()-1);
		        VST_DATES   = VST_DATES.substring(0,VST_DATES.length()-1);
			    String[] nameArray = MKM_NAMES.split(",");
				String[] valueArray = VST_DATES.split(",");
				Map<String,List> resultMap = new HashMap<String,List>();
				Set<String> nameSet = new HashSet<String>();
				
				for(int i = 0; i < nameArray.length; i++){
					if(!nameSet.contains(nameArray[i])){
						List tempList = new ArrayList();
						tempList.add(valueArray[i]);
						nameSet.add(nameArray[i]);
						
						resultMap.put(nameArray[i], tempList);
					}else{
						resultMap.get(nameArray[i]).add(valueArray[i]);
					}
				}
				
				for (String key : resultMap.keySet()) {
					System.out.println("key= "+ key + " and value= " + resultMap.get(key));
					Names += key+"|";
					Dates += String.valueOf(resultMap.get(key)).replace("[","").replace("]","")+"|";
				}
				Names = Names.substring(0,Names.length()-1);
				Dates = Dates.substring(0,Dates.length()-1);
			    request.setAttribute("Names", Names);
			    request.setAttribute("Dates", Dates);
		    }
			return mapping.findForward("pageCalendar");
		}
	}
	
	 public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	UserBean userBean = ParamUtil.getUserBean(request);
	    	String VST_DATE  = ""; 
	    	String MKM_NAME  = "";
	    	try {
	    		VST_DATE = new String(request.getParameter("VST_DATE").getBytes("ISO-8859-1"),"UTF-8");
	    	    MKM_NAME    = new String(request.getParameter("MKM_NAME").getBytes("ISO-8859-1"),"UTF-8"); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (StringUtils.isNotEmpty(VST_DATE)) {
				String MKM_DAY_RPT_ID = reportService.queryReportInfo(VST_DATE, MKM_NAME);
				Map entry   = reportService.load(MKM_DAY_RPT_ID);
				Map entrycv = reportService.loadByChannVist(MKM_DAY_RPT_ID);
				Map entryact= reportService.loadByProActv(MKM_DAY_RPT_ID);
				Map entrydis= reportService.loadByDisVisit(MKM_DAY_RPT_ID);
				Map entrycoo= reportService.loadByCooVisit(MKM_DAY_RPT_ID);
				Map entryshop = reportService.loadByShopTran(MKM_DAY_RPT_ID);
				Map entryterm = reportService.loadByByTerm(MKM_DAY_RPT_ID);
				
				request.setAttribute("rst", entry);
				request.setAttribute("rstcv", entrycv);
				request.setAttribute("rstact",entryact);
				request.setAttribute("rstdis", entrydis);
				request.setAttribute("rstcoo", entrycoo);
				request.setAttribute("rstshop",entryshop);
				request.setAttribute("rsterm", entryterm);
				List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(MKM_DAY_RPT_ID);
			    request.setAttribute("flowInfoList", flowInfoList);
			}
	        return mapping.findForward("toDetail");
	    }
 
	 /***
	  * 加盟商营销经理评分表
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	 public ActionForward  toChannMkmReport(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response){
			
			UserBean userBean = ParamUtil.getUserBean(request);
			Map<String,String> param = new HashMap<String,String>();
			String WAREA_NAME = ParamUtil.get(request, "WAREA_NAME"); 
			String MKM_NAME   = ParamUtil.get(request, "MKM_NAME"); 
			String SCORE_DATE_BEG = ParamUtil.get(request, "SCORE_DATE_BEG");
			String SCORE_DATE_END = ParamUtil.get(request, "SCORE_DATE_END");
			String STATE  = ParamUtil.get(request, "STATE");
			
			param.put("WAREA_NAME", WAREA_NAME);
			param.put("MKM_NAME_SQL", StringUtil.creCon("m.MKM_NAME", MKM_NAME, ","));
			param.put("SCORE_DATE_BEG", SCORE_DATE_BEG);
			param.put("SCORE_DATE_END", SCORE_DATE_END);
			param.put("STATE", STATE);
			
			StringBuffer conSql = new StringBuffer(" ");
			
	        StringBuffer top_sql  = new StringBuffer("  SELECT DISTINCT m.CHANN_SCORE_MKM_NO,(SELECT DISTINCT a.AREA_NAME_P FROM BASE_AREA a LEFT JOIN BASE_CHANN c on a.AREA_ID = c.AREA_ID WHERE c.AREA_MANAGE_NAME = m.MKM_NAME) WAREA_NAME, m.MKM_NAME "); 
	        StringBuffer left_sql = new StringBuffer("  FROM DRP_CHANN_SCORE_MKM m  LEFT JOIN DRP_CHANN_SCORE_MKM_DTL d on m.CHANN_SCORE_MKM_ID = d.CHANN_SCORE_MKM_ID ");
	        StringBuffer bottom_sql = new StringBuffer();
	        bottom_sql.append("WHERE m.DEL_FLAG=0 ");
	        //润乾报表列sql
			StringBuffer sqlColumn = new StringBuffer("");
		    //group by 字段
			//战区
			String WORK_VISIT_SHOW = ParamUtil.get(request, "WORK_VISIT_SHOW"); 
			//是否显示工作拜访频率这列 true->不显示
			boolean WORK_VISIT_FLAG = true;
	        if(!StringUtil.isEmpty(WORK_VISIT_SHOW)){
	            top_sql.append(",( SELECT DISTINCT d.SCORE_DESC  FROM DRP_SCORE_STAND s  ")
	            .append("  LEFT JOIN DRP_CHANN_SCORE_MKM_DTL d  on s.SCORE_STAND_ID = d.SCORE_STAND_ID")
	            .append("  LEFT JOIN DRP_CHANN_SCORE_MKM m1 on m1.chann_score_mkm_id = d.CHANN_SCORE_MKM_ID")
	            .append("  WHERE m1.MKM_NAME = m.MKM_NAME AND m1.CHANN_SCORE_MKM_ID = m.CHANN_SCORE_MKM_ID ")
	            .append("  and s.SCORE_OPTION LIKE '%工作拜访频率%' ) SCORE_DESC1 ");
	            WORK_VISIT_FLAG = false; 
	            sqlColumn.append(";WORK_VISIT_SQL=ds1.SCORE_DESC1 ");
	        }
			
	       //公司政策传达
	       String  OFFICE_SHOW = ParamUtil.get(request, "OFFICE_SHOW"); 
	       //是否显示公司政策传达这列
	       @SuppressWarnings("unused")
		   boolean OFFICE_VISIT_FLAG = true;
	       if(!StringUtil.isEmpty(OFFICE_SHOW)){
    	    top_sql.append(",(  SELECT DISTINCT d.SCORE_DESC  FROM DRP_SCORE_STAND s ")
    	    .append("  LEFT JOIN DRP_CHANN_SCORE_MKM_DTL d  on s.SCORE_STAND_ID = d.SCORE_STAND_ID ")
    	    .append("  LEFT JOIN DRP_CHANN_SCORE_MKM m1  on m1.chann_score_mkm_id = d.CHANN_SCORE_MKM_ID")
    	    .append("  WHERE m1.MKM_NAME = m.MKM_NAME AND m1.CHANN_SCORE_MKM_ID = m.CHANN_SCORE_MKM_ID ")
    	    .append("  and s.SCORE_OPTION LIKE '%公司政策传达%') SCORE_DESC2");
            OFFICE_VISIT_FLAG = false;
	      	sqlColumn.append(";OFFICE_SQL=ds1.SCORE_DESC2");
	      }
	        
	        //门店精致化
	        String TERM_CHECK_SHOW = ParamUtil.get(request, "TERM_CHECK_SHOW"); 
	        @SuppressWarnings("unused")
			boolean TERM_CHECK_VISIT_FLAG= true;
	        if(!StringUtil.isEmpty(TERM_CHECK_SHOW)){
	        	top_sql.append(",(  SELECT DISTINCT d.SCORE_DESC  FROM DRP_SCORE_STAND s ")
	        	.append("  LEFT JOIN DRP_CHANN_SCORE_MKM_DTL d  on s.SCORE_STAND_ID = d.SCORE_STAND_ID ")
	    	    .append("  LEFT JOIN DRP_CHANN_SCORE_MKM m1  on m1.chann_score_mkm_id = d.CHANN_SCORE_MKM_ID")
	    	    .append("  WHERE m1.MKM_NAME = m.MKM_NAME AND m1.CHANN_SCORE_MKM_ID = m.CHANN_SCORE_MKM_ID ")
	    	    .append("  and s.SCORE_OPTION LIKE '%门店精致化%') SCORE_DESC3");
	            TERM_CHECK_VISIT_FLAG = false;
	        	sqlColumn.append(";TERM_CHECK_SQL  =ds1.SCORE_DESC3");
	        }
	        
	        //导购员培训
	        String SHOP_GUIDE_SHOW = ParamUtil.get(request, "SHOP_GUIDE_SHOW"); 
	        @SuppressWarnings("unused")
			boolean SHOP_GUIDE_FLAG = true;
	        if(!StringUtil.isEmpty(SHOP_GUIDE_SHOW)){
	        	top_sql.append(",(  SELECT DISTINCT d.SCORE_DESC  FROM DRP_SCORE_STAND s ")
	        	.append("  LEFT JOIN DRP_CHANN_SCORE_MKM_DTL d  on s.SCORE_STAND_ID = d.SCORE_STAND_ID ")
	    	    .append("  LEFT JOIN DRP_CHANN_SCORE_MKM m1  on m1.chann_score_mkm_id = d.CHANN_SCORE_MKM_ID")
	    	    .append("  WHERE m1.MKM_NAME = m.MKM_NAME AND m1.CHANN_SCORE_MKM_ID = m.CHANN_SCORE_MKM_ID ")
	    	    .append("  and s.SCORE_OPTION LIKE '%导购员培训%') SCORE_DESC4");
	        	SHOP_GUIDE_FLAG = false;
	        	sqlColumn.append(";SHOP_GUIDE_SQL    =ds1.SCORE_DESC4");
	        }
	        
	        //店外推广执行
	        String PRMIT_SHOW = ParamUtil.get(request, "PRMIT_SHOW"); 
	        @SuppressWarnings("unused")
			boolean PRMIT_FLAG = true;
	        if(!StringUtil.isEmpty(PRMIT_SHOW)){
	        	top_sql.append(",(  SELECT DISTINCT d.SCORE_DESC  FROM DRP_SCORE_STAND s ")
	        	.append("  LEFT JOIN DRP_CHANN_SCORE_MKM_DTL d  on s.SCORE_STAND_ID = d.SCORE_STAND_ID ")
	    	    .append("  LEFT JOIN DRP_CHANN_SCORE_MKM m1  on m1.chann_score_mkm_id = d.CHANN_SCORE_MKM_ID")
	    	    .append("  WHERE m1.MKM_NAME = m.MKM_NAME AND m1.CHANN_SCORE_MKM_ID = m.CHANN_SCORE_MKM_ID ")
	    	    .append("  and s.SCORE_OPTION LIKE '%店外推广执行%') SCORE_DESC5");
	        	PRMIT_FLAG = false;
	        	sqlColumn.append(";PRMIT_SQL     =ds1.SCORE_DESC5");
	        }
	        
	        //分销商开发与维护
	        String DETRIBUTE_VISIT_SHOW = ParamUtil.get(request, "DETRIBUTE_VISIT_SHOW");
	        @SuppressWarnings("unused")
			boolean DETRIBUTE_VISIT_FLAG = true;
	        if(!StringUtil.isEmpty(DETRIBUTE_VISIT_SHOW)){
	        	top_sql.append(",(  SELECT DISTINCT d.SCORE_DESC  FROM DRP_SCORE_STAND s ")
	        	.append("  LEFT JOIN DRP_CHANN_SCORE_MKM_DTL d  on s.SCORE_STAND_ID = d.SCORE_STAND_ID ")
	    	    .append("  LEFT JOIN DRP_CHANN_SCORE_MKM m1  on m1.chann_score_mkm_id = d.CHANN_SCORE_MKM_ID")
	    	    .append("  WHERE m1.MKM_NAME = m.MKM_NAME AND m1.CHANN_SCORE_MKM_ID = m.CHANN_SCORE_MKM_ID ")
	    	    .append("  and s.SCORE_OPTION LIKE '%分销商开发与维护%') SCORE_DESC6");
	        	DETRIBUTE_VISIT_FLAG = false;
	        	sqlColumn.append(";DETRIBUTE_VISIT_SQL =ds1.SCORE_DESC6 ");
	        }
	        
	        //问题分析解决
	        String QUESTION_VISIT_SHOW = ParamUtil.get(request, "QUESTION_VISIT_SHOW");
	        @SuppressWarnings("unused")
			boolean QUESTION_VISIT_FLAG = true;
	        if(!StringUtil.isEmpty(QUESTION_VISIT_SHOW)){
	        	top_sql.append(",(  SELECT DISTINCT d.SCORE_DESC  FROM DRP_SCORE_STAND s ")
	        	.append("  LEFT JOIN DRP_CHANN_SCORE_MKM_DTL d  on s.SCORE_STAND_ID = d.SCORE_STAND_ID ")
	    	    .append("  LEFT JOIN DRP_CHANN_SCORE_MKM m1  on m1.chann_score_mkm_id = d.CHANN_SCORE_MKM_ID")
	    	    .append("  WHERE m1.MKM_NAME = m.MKM_NAME AND m1.CHANN_SCORE_MKM_ID = m.CHANN_SCORE_MKM_ID ")
	    	    .append("  and s.SCORE_OPTION LIKE '%问题分析解决%') SCORE_DESC7");
	            QUESTION_VISIT_FLAG = false;
	            sqlColumn.append(";QUESTION_VISIT_SQL =ds1.SCORE_DESC7 ");
	        }
	        
	        //是否显示分数
	        String SHOW_SCORE_SHOW = ParamUtil.get(request, "SHOW_SCORE_SHOW");
	        @SuppressWarnings("unused")
			boolean SHOW_SCORE_FLAG = true;
	        if(!StringUtil.isEmpty(SHOW_SCORE_SHOW)){
	        	top_sql.append(",m.SCORE_TOTAL ");
	            SHOW_SCORE_FLAG = false;
	            sqlColumn.append(";SHOW_SCORE_SQL =ds1.SCORE_TOTAL ");
	        }
	        
	        if(!StringUtil.isEmpty(WAREA_NAME)){
	        	//bottom_sql.append(" and m.WAREA_NAME = '"+WAREA_NAME+"'");
			}  
	        if(!StringUtil.isEmpty(MKM_NAME)){
	        	
	        	String MKM_NAME_SQL = StringUtil.creCon("m.MKM_NAME", MKM_NAME, ",");
	        	bottom_sql.append(MKM_NAME_SQL);
			}  
	        
	        if(!StringUtil.isEmpty(SCORE_DATE_BEG)){
	        	bottom_sql.append(" and to_char(m.SCORE_DATE,'yyyy-MM-DD')>='"+SCORE_DATE_BEG+"' ");
			} 
			if(!StringUtil.isEmpty(SCORE_DATE_END)){
				bottom_sql.append(" and to_char(m.SCORE_DATE,'yyyy-MM-DD')<='"+SCORE_DATE_END+"' ");
			} 
			if(!StringUtil.isEmpty(STATE)){
				bottom_sql.append(" and m.STATE = '"+STATE+"'");
			}
	        
	        String rptSql =  top_sql.toString()+left_sql.toString()+bottom_sql.toString();

	        String params = "rptSql=" + rptSql
	        +";WORK_VISIT_FLAG="+WORK_VISIT_FLAG
	        +";OFFICE_VISIT_FLAG="+OFFICE_VISIT_FLAG
	        +";TERM_CHECK_VISIT_FLAG="+TERM_CHECK_VISIT_FLAG
	        +";SHOP_GUIDE_FLAG="+SHOP_GUIDE_FLAG
	        +";PRMIT_FLAG="+PRMIT_FLAG
	        +";DETRIBUTE_VISIT_FLAG="+DETRIBUTE_VISIT_FLAG
	        +";QUESTION_VISIT_FLAG="+QUESTION_VISIT_FLAG
	        +";SHOW_SCORE_FLAG="+SHOW_SCORE_FLAG;
	        
//	        boolean IS_FIRST_SHOW = true;
//	        if(sqlColumn.toString().length()<1){
//	        	IS_FIRST_SHOW = false;
//	        } 
	    	params = params 
	    	+sqlColumn.toString();
	    	//+";IS_FIRST_SHOW="+IS_FIRST_SHOW;
	    	
			request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
			request.setAttribute("conDition", params);
			request.setAttribute("rptModel",   "ChannMkmReport.raq");
			request.setAttribute("printModel", "ChannMkmReport.raq");
			return mapping.findForward("pageResult");
	 }
	 
	 /***
	  * 分销商购货月报
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return 
	  */
	 public ActionForward  toDistributorSaleRpt(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response){
			
			UserBean userBean = ParamUtil.getUserBean(request);
			
			String AREA_NAME_P = ParamUtil.get(request, "WAREA_NAME"); 
			String AREA_MANAGE_NAME   = ParamUtil.get(request, "MKM_NAME"); 
			String DISTRIBUTOR_NO   = ParamUtil.get(request, "DISTRIBUTOR_NO"); 
			String DISTRIBUTOR_NAME   = ParamUtil.get(request, "DISTRIBUTOR_NAME");
			String CRE_DATE_BEG = ParamUtil.get(request, "CRE_DATE_BEG");
			String CRE_DATE_END = ParamUtil.get(request, "CRE_DATE_END");
			String YEAR = ParamUtil.get(request, "YEAR");
			String MONTH = ParamUtil.get(request, "MONTH");
			String DISTRIBUTOR_TYPE = ParamUtil.get(request, "DISTRIBUTOR_TYPE");
			String PRD_NO = ParamUtil.get(request, "PRD_NO");
			String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
						
			String conSql = new String(" select a.AREA_NAME_P,d.AREA_MANAGE_NAME, d.CHANN_ID,d.CHANN_NO, d.CHANN_NAME,c.YEAR || '年' || c.MONTH || '月' RPT_DATE," +
					"to_char(c.CRE_TIME, 'yyyy-MM-DD HH24:MI:SS') CRE_TIME,u.DISTRIBUTOR_NO, u.DISTRIBUTOR_NAME,u.DISTRIBUTOR_TYPE,u.PRD_NO,u.PRD_NAME,u.PRD_SPEC,u.STOREOUT_NUM,u.STOREOUT_AMOUNT " +
					" from ERP_DISTRIBUTOR_SALE_RPT_DTL u   " +
					" left join ERP_DISTRIBUTOR_SALE_RPT c on c.DISTRIBUTOR_SALE_RPT_ID = u.DISTRIBUTOR_SALE_RPT_ID " +
					" left join BASE_DISTRIBUTOR d on d.DISTRIBUTOR_ID = u.DISTRIBUTOR_ID   " +
					" left join BASE_AREA a on a.AREA_ID = d.AREA_ID");
				        
	        StringBuffer whereSql = new StringBuffer(" WHERE u.del_flag = 0 and c.del_flag = 0 ");
	        		   
	        if(!StringUtil.isEmpty(AREA_NAME_P)){	        	
	        	String AREA_NAME_P_SQL = StringUtil.creCon("a.AREA_NAME_P", AREA_NAME_P, ",");
	        	whereSql.append(AREA_NAME_P_SQL);
			}	        
	        if(!StringUtil.isEmpty(AREA_MANAGE_NAME)){	        	
	        	String AREA_MANAGE_NAME_SQL = StringUtil.creCon("d.AREA_MANAGE_NAME", AREA_MANAGE_NAME, ",");
	        	whereSql.append(AREA_MANAGE_NAME_SQL);
			}	        
	        if(!StringUtil.isEmpty(DISTRIBUTOR_NO)){	        	
	        	String DISTRIBUTOR_NO_SQL = StringUtil.creCon("u.DISTRIBUTOR_NO", DISTRIBUTOR_NO, ",");
	        	whereSql.append(DISTRIBUTOR_NO_SQL);
			}	        
	        if(!StringUtil.isEmpty(DISTRIBUTOR_NAME)){	        	
	        	String DISTRIBUTOR_NAME_SQL = StringUtil.creCon("u.DISTRIBUTOR_NAME", DISTRIBUTOR_NAME, ",");
	        	whereSql.append(DISTRIBUTOR_NAME_SQL);
			}	        
	        if(!StringUtil.isEmpty(CRE_DATE_BEG)){
	        	whereSql.append(" and to_char(c.CRE_TIME,'yyyy-MM-DD')>='"+CRE_DATE_BEG+"' ");
			} 
			if(!StringUtil.isEmpty(CRE_DATE_END)){
				whereSql.append(" and to_char(c.CRE_TIME,'yyyy-MM-DD')<='"+CRE_DATE_END+"' ");
			} 
			if(!StringUtil.isEmpty(YEAR)){
				whereSql.append(" and c.YEAR = '"+YEAR+"'");
			}
			if(!StringUtil.isEmpty(MONTH)){
				whereSql.append(" and c.MONTH = '"+MONTH+"'");
			}
			if(!StringUtil.isEmpty(DISTRIBUTOR_TYPE)){
				whereSql.append(" and u.DISTRIBUTOR_TYPE = '"+DISTRIBUTOR_TYPE+"'");
			}
			if(!StringUtil.isEmpty(PRD_NO)){	        	
	        	String PRD_NO_SQL = StringUtil.creCon("PRD_NO", PRD_NO, ",");
	        	whereSql.append(PRD_NO_SQL);
			}
			if(!StringUtil.isEmpty(PRD_NAME)){	        	
	        	String PRD_NAME_SQL = StringUtil.creCon("PRD_NAME", PRD_NAME, ",");
	        	whereSql.append(PRD_NAME_SQL);
			}
	        
	        String rptSql =  conSql + whereSql.toString();

	        String params = "rptSql=" + rptSql;
	        	    	
			request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
			request.setAttribute("conDition", params);
			request.setAttribute("rptModel",   "DistributorSaleRpt.raq");
			request.setAttribute("printModel", "DistributorSaleRpt.raq");
			return mapping.findForward("pageResult");
	 }
}
