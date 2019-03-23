package com.hoperun.erp.report.salereport.action;

import java.util.HashMap;
import java.util.Map;

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
import com.hoperun.erp.report.salereport.service.SaleReportService;
import com.hoperun.sys.model.UserBean;

public class SaleReportAction extends BaseAction{
	//销售报表查询
	private static String PVG_BWS = "BS0050101";
	//库存报表查询
	private static String PVG_STORE_BWS = "BS0050201";
	//财务报表查询
	private static String PVG_FAD_BWS = "BS0050301";
	//分类销量统计
	private static String PVG_COSS="BS0050122";
	//生产查询
	private static String PVG_BWS_PRODUCE="BS0050102";
	//退货单跟踪查询
	private static String PVG_BWS_RETURN="BS0050103";
	//销售订单出货统计明细表
	private static String PVG_BWS_ADVC_STATISTICAL="BS0050104";
	//订货订单信息
	private static String PVG_BWS_ADVC="BS0050105";
	//出货统计汇总表
	private static String PVG_BWS_OUT_STORE="BS0050106";
	//库存生产状态查询
	private static String PVG_BWS_STORE="BS0050107";
	//销售订单的生产状态查询
	private static String PVG_BWS_ADVC_STORE="BS0050108";
	//发货及时率的报表
	private static String PVG_BWS_DELIVER_GOODS="BS0050109";
	//装箱率月统计
	private static String PVG_BWS_PACK="BS0050110";
	//销售订单生产组织对照表
	private static String PVG_BWS_ADVC_CONTRAST="BS0050111";
	//月份、发货统计
	private static String PVG_BWS_MONTH_STOREOUT="BS0050112";
	//每月售后投诉报表
	private static String PVG_BWS_COMPLAIN="BS0050113";
	//撤店退货统计
	private static String PVG_BWS_RETURN_STATISTICS="BS0050114";
	//返修费用统计
	private static String PVG_BWS_RETURN_FIX_STATISTICS="BS0050115";
	//退货跟踪查询
	private static String PVG_BWS_RETURN_TAIL="BS0050116";
	//库存报表
	private static String PVG_BWS_STOCK="BS0050202";
	//应收余额查询
	private static String PVG_BWS_FAD="BS0050302";
	//发货情况统计表
	private static String PVG_BWS_DELIVER="BS0050117";
	private static String PVG_BWS_INVOC_NUM="BS0050307";
	//信用
	private static String PVG_CREDIT_BWS = "BS0050303";
	//订单查询
	private static String PVG_BWS_GOODS="BS0050118";
	//TOP10产品
	private static String CLASSIFY_SALE = "BS0050120";
	//销量统计比例
	private static String SALE_PERCEN = "BS0050121";
	//合同报表执行情况查询
	private static String PVG_BWS_CONTRACT_IMPLEMENT="BS0050304";
	//收款情况查询
	private static String PVG_BWS_CLAUSE="BS0050305";
	//返款情况查询
	private static String PVG_BWS_REBATES="BS0050308";
	//返利抵扣查询
	private static String PVG_BWS_REBATE="BS0050119";
	//渠道财务进销存
	private static String PVG_BWS_INVOC_ACCT="BS0050306";
	//信用流水账
	private static String CREDIT_ACCT_OUT = "BS0050307";
	//订单冻结信用查询
	private static String CREDIT_FREEZE = "BS0050309";
	//返利订单扣除信用查询
	private static String REBATE_FREEZE = "BS0050310";
	//原发运单查询
	private static String PVG_OLD_DELIVERY = "BS0050123";
	//渠道库存实时查询
	private static String PVG_ERP_STORE_BWS="BS0050203";
	//总部对账单查询
    private static String PVG_ACCOUNTS_BWS="BS0050311";
    //
    private static String PVG_MONTH_WORK_PALN = "BS0050125";
    
    //年度返利汇总
    private static String PVG_ANNUALREBATE_BWS="BS0050312";
	
	
	//=======================营销报表=========================
    private static String PVG_MARKET_BWS="BS0050901";
	//总部销售报表
	private static String PVG_ERP_SALE = "BS0050902";
	//终端门店零售报表（分销销售报表）
	private static String PVG_DRP_SALE = "BS0050903";
	//销售计划达成率报表
	private static String PVG_SALE_PLAN_PENC = "BS0050904";
	//月度拜访工作计划达成率表
	private static String PVG_MONTH_PLAN_PENC = "BS0050905";
	//各战区季度有效门店达成数
	private static String WAREA_QUARTEROPEN_PVG = "BS0050906";
	//门店精致化检查分数
	private static String TERM_REFINE_CHECK_PVG = "BS0050907";
	//终端门店报表
	private static String TERMINAL_OPEN_PVG = "BS0050908";
	//拜访达成考核目标
	private static String VISIT_TASK_ASSESS_PVG = "BS0050907";
	//活动销售报表
	private static String MARKET_SALE_PVG = "BS0050908";
	//活动提成查询报表
	private static String MARKET_COMM_PVG = "BS0050909";
	//加盟商推广费用使用明细
	private static String PRMT_COST_REQ_PVG = "BS0050910";
	//战区推广费
	private static String PRMT_WAREA_FREE = "BS0050911";
	//加盟商推广费报表
	private static String PVG_PRMT_COST_REQ = "BS0050912";
	//区域推广费
	private static String PRMT_AREA_FREE = "BS0050913";
	//卡券销售清单
	private static String PVG_CARD_LIST = "BS0050914";
	//直营办零售数据跟踪
	private static String PVG_CHILDCOMP_TRACK = "BS0050915";
	//每月售后投诉报表
	private static String PVG_ADVISE_STATIS = "BS0050916";
	//床垫季/年度售后投诉报表
	private static String PVG_CD_ADVISE_STATIS = "BS0050917";
	//软床季/年度售后投诉报表
	private static String PVG_RC_ADVISE_STATIS = "BS0050918";
	//床头柜季/年度售后投诉报表
	private static String PVG_CTG_ADVISE_STATIS = "BS0050919";
	//营销经理日报表
	private static String PVG_MKM_DAY_STATUS    = "BS0050920";
	//加盟商营销经理评分表
	private static String PVG_CHANN_MKM_STATUS  = "BS0050921";
	
	//国内销售民用产品预估表
	private static String PVG_RPT_JOB  = "BS0050922";
	//偏差率
	private static String PVG_RPT_DIFF  = "BS0050923";
	
	private SaleReportService saleReportService;
 

	//销售报表
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		//当前报表URL
		String RUNQIAN_REPORT_URL = userBean.getCURRT_RPT_URL();
		String SLEEMON_REPORT_URL = Consts.SLEEMON_REPORT_URL;
		String IS_NO_DELIVER_PLAN_FLAG=Properties.getString("IS_NO_DELIVER_PLAN_FLAG");
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("IS_NO_DELIVER_PLAN_FLAG", IS_NO_DELIVER_PLAN_FLAG);
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
	}
	
	//库存查询
	public ActionForward toStoreList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_STORE_BWS) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		//当前报表URL
		String RUNQIAN_REPORT_URL = userBean.getCURRT_RPT_URL();
		String SLEEMON_REPORT_URL=Consts.SLEEMON_REPORT_URL;
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("storeList");
	}
	
	
	//财务报表查询
	public ActionForward toFADList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_FAD_BWS) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		//当前报表URL
		String RUNQIAN_REPORT_URL = userBean.getCURRT_RPT_URL();
		String SLEEMON_REPORT_URL=Consts.SLEEMON_REPORT_URL;
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("FADList");
	}
	
	
	/**
	 * 二期
	 * 营销报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toMarketingList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_MARKET_BWS) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		//当前报表URL
		String RUNQIAN_REPORT_URL = userBean.getCURRT_RPT_URL();
		String SLEEMON_REPORT_URL = Consts.SLEEMON_REPORT_URL;
		String IS_NO_DELIVER_PLAN_FLAG=Properties.getString("IS_NO_DELIVER_PLAN_FLAG");
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("IS_NO_DELIVER_PLAN_FLAG", IS_NO_DELIVER_PLAN_FLAG);
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("marketingList");
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
		pvgMap.put("PVG_BWS_RETURN_TAIL", QXUtil.checkPvg(userBean, PVG_BWS_RETURN_TAIL));
		pvgMap.put("PVG_BWS_RETURN_FIX_STATISTICS", QXUtil.checkPvg(userBean, PVG_BWS_RETURN_FIX_STATISTICS));
		pvgMap.put("PVG_BWS_RETURN_STATISTICS", QXUtil.checkPvg(userBean, PVG_BWS_RETURN_STATISTICS));
		pvgMap.put("PVG_BWS_COMPLAIN", QXUtil.checkPvg(userBean, PVG_BWS_COMPLAIN));
		pvgMap.put("PVG_BWS_MONTH_STOREOUT", QXUtil.checkPvg(userBean, PVG_BWS_MONTH_STOREOUT));
		pvgMap.put("PVG_BWS_ADVC_CONTRAST", QXUtil.checkPvg(userBean, PVG_BWS_ADVC_CONTRAST));
		pvgMap.put("PVG_BWS_PACK", QXUtil.checkPvg(userBean, PVG_BWS_PACK));
		pvgMap.put("PVG_BWS_DELIVER_GOODS", QXUtil.checkPvg(userBean, PVG_BWS_DELIVER_GOODS));
		pvgMap.put("PVG_BWS_ADVC_STORE", QXUtil.checkPvg(userBean, PVG_BWS_ADVC_STORE));
		pvgMap.put("PVG_BWS_STORE", QXUtil.checkPvg(userBean, PVG_BWS_STORE));
		pvgMap.put("PVG_BWS_OUT_STORE", QXUtil.checkPvg(userBean, PVG_BWS_OUT_STORE));
		pvgMap.put("PVG_BWS_ADVC", QXUtil.checkPvg(userBean, PVG_BWS_ADVC));
		pvgMap.put("PVG_BWS_ADVC_STATISTICAL", QXUtil.checkPvg(userBean, PVG_BWS_ADVC_STATISTICAL));
		pvgMap.put("PVG_BWS_RETURN", QXUtil.checkPvg(userBean, PVG_BWS_RETURN));
		pvgMap.put("PVG_BWS_PRODUCE", QXUtil.checkPvg(userBean, PVG_BWS_PRODUCE));
		pvgMap.put("PVG_STORE_BWS", QXUtil.checkPvg(userBean, PVG_STORE_BWS));
		pvgMap.put("PVG_BWS_STOCK", QXUtil.checkPvg(userBean, PVG_BWS_STOCK));
		pvgMap.put("PVG_BWS_FAD", QXUtil.checkPvg(userBean, PVG_BWS_FAD));
		pvgMap.put("PVG_FAD_BWS", QXUtil.checkPvg(userBean, PVG_FAD_BWS));
		pvgMap.put("PVG_BWS_DELIVER", QXUtil.checkPvg(userBean, PVG_BWS_DELIVER));
		pvgMap.put("PVG_BWS_CONTRACT_IMPLEMENT", QXUtil.checkPvg(userBean, PVG_BWS_CONTRACT_IMPLEMENT));
		pvgMap.put("PVG_BWS_GOODS", QXUtil.checkPvg(userBean, PVG_BWS_GOODS));
		pvgMap.put("PVG_CREDIT_BWS", QXUtil.checkPvg(userBean, PVG_CREDIT_BWS));
		pvgMap.put("PVG_BWS_CLAUSE", QXUtil.checkPvg(userBean, PVG_BWS_CLAUSE));
		pvgMap.put("PVG_BWS_INVOC_ACCT", QXUtil.checkPvg(userBean, PVG_BWS_INVOC_ACCT));
		pvgMap.put("PVG_BWS_INVOC_NUM", QXUtil.checkPvg(userBean, PVG_BWS_INVOC_NUM));
		pvgMap.put("PVG_BWS_REBATE", QXUtil.checkPvg(userBean, PVG_BWS_REBATE));
		pvgMap.put("CREDIT_ACCT_OUT", QXUtil.checkPvg(userBean, CREDIT_ACCT_OUT));
		pvgMap.put("PVG_BWS_REBATES", QXUtil.checkPvg(userBean, PVG_BWS_REBATES));
		pvgMap.put("CREDIT_FREEZE", QXUtil.checkPvg(userBean, CREDIT_FREEZE));
		pvgMap.put("REBATE_FREEZE", QXUtil.checkPvg(userBean, REBATE_FREEZE));
		pvgMap.put("CLASSIFY_SALE", QXUtil.checkPvg(userBean, CLASSIFY_SALE));
		pvgMap.put("SALE_PERCEN", QXUtil.checkPvg(userBean, SALE_PERCEN));
		pvgMap.put("PVG_COSS", QXUtil.checkPvg(userBean, PVG_COSS));
		pvgMap.put("PVG_OLD_DELIVERY", QXUtil.checkPvg(userBean, PVG_OLD_DELIVERY));
		pvgMap.put("PVG_ERP_SALE", QXUtil.checkPvg(userBean, PVG_ERP_SALE));
		pvgMap.put("PVG_DRP_SALE", QXUtil.checkPvg(userBean, PVG_DRP_SALE));
		pvgMap.put("PVG_SALE_PLAN_PENC", QXUtil.checkPvg(userBean, PVG_SALE_PLAN_PENC));
		pvgMap.put("PVG_MONTH_PLAN_PENC", QXUtil.checkPvg(userBean, PVG_MONTH_PLAN_PENC));
		pvgMap.put("WAREA_QUARTEROPEN_PVG", QXUtil.checkPvg(userBean, WAREA_QUARTEROPEN_PVG));
		pvgMap.put("TERM_REFINE_CHECK_PVG", QXUtil.checkPvg(userBean, TERM_REFINE_CHECK_PVG));
		pvgMap.put("PVG_ERP_STORE_BWS", QXUtil.checkPvg(userBean, PVG_ERP_STORE_BWS));
		pvgMap.put("VISIT_TASK_ASSESS_PVG", QXUtil.checkPvg(userBean, VISIT_TASK_ASSESS_PVG));
		pvgMap.put("MARKET_SALE_PVG", QXUtil.checkPvg(userBean, MARKET_SALE_PVG));
		pvgMap.put("MARKET_COMM_PVG", QXUtil.checkPvg(userBean, MARKET_COMM_PVG));
		pvgMap.put("PVG_ACCOUNTS_BWS", QXUtil.checkPvg(userBean, PVG_ACCOUNTS_BWS));
		pvgMap.put("PVG_MONTH_WORK_PALN", QXUtil.checkPvg(userBean, PVG_MONTH_WORK_PALN));
		pvgMap.put("PRMT_COST_REQ_PVG", QXUtil.checkPvg(userBean, PRMT_COST_REQ_PVG));
		pvgMap.put("PRMT_WAREA_FREE", QXUtil.checkPvg(userBean, PRMT_WAREA_FREE));
		pvgMap.put("PVG_PRMT_COST_REQ", QXUtil.checkPvg(userBean, PVG_PRMT_COST_REQ));
		pvgMap.put("PRMT_AREA_FREE", QXUtil.checkPvg(userBean, PRMT_AREA_FREE));
		pvgMap.put("PVG_CARD_LIST", QXUtil.checkPvg(userBean, PVG_CARD_LIST));
		pvgMap.put("PVG_ANNUALREBATE_BWS", QXUtil.checkPvg(userBean, PVG_ANNUALREBATE_BWS));
		pvgMap.put("PVG_MARKET_BWS", QXUtil.checkPvg(userBean, PVG_MARKET_BWS));
		pvgMap.put("TERMINAL_OPEN_PVG", QXUtil.checkPvg(userBean, TERMINAL_OPEN_PVG));
		pvgMap.put("PVG_CHILDCOMP_TRACK", QXUtil.checkPvg(userBean, PVG_CHILDCOMP_TRACK));
		pvgMap.put("PVG_ADVISE_STATIS", QXUtil.checkPvg(userBean, PVG_ADVISE_STATIS));
		pvgMap.put("PVG_CD_ADVISE_STATIS", QXUtil.checkPvg(userBean, PVG_CD_ADVISE_STATIS));
		pvgMap.put("PVG_RC_ADVISE_STATIS", QXUtil.checkPvg(userBean, PVG_RC_ADVISE_STATIS));
		pvgMap.put("PVG_CTG_ADVISE_STATIS", QXUtil.checkPvg(userBean, PVG_CTG_ADVISE_STATIS));
		pvgMap.put("PVG_MKM_DAY_STATUS", QXUtil.checkPvg(userBean, PVG_MKM_DAY_STATUS));
		pvgMap.put("PVG_CHANN_MKM_STATUS", QXUtil.checkPvg(userBean, PVG_CHANN_MKM_STATUS));
		pvgMap.put("PVG_RPT_JOB", QXUtil.checkPvg(userBean, PVG_RPT_JOB));
		pvgMap.put("PVG_RPT_DIFF", QXUtil.checkPvg(userBean, PVG_RPT_DIFF));
		
		return pvgMap;
	}
	
	

	public SaleReportService getSaleReportService() {
		return saleReportService;
	}

	public void setSaleReportService(SaleReportService saleReportService) {
		this.saleReportService = saleReportService;
	}
	
}
