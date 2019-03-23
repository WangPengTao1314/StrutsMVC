package com.hoperun.drp.sale.saleorderview.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.hoperun.drp.sale.advcorder.action.AdvcorderAction;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.drp.sale.saleorderview.model.SaleorderviewChldModel;
import com.hoperun.drp.sale.saleorderview.service.SaleorderviewService;
import com.hoperun.sys.model.UserBean;

public class SaleorderviewAction extends BaseAction {
	/** 日志 **/
	private Logger logger = Logger.getLogger(AdvcorderAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0022301";
	private static String PVG_EDIT = "";
	private static String PVG_DELETE = "";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "";
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";// 表名
	private static String AUD_TAB_KEY = "";// 主键
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	/** 审批 end **/
	/** 业务service */
	private SaleorderviewService saleorderviewService;
	/**
	 * @return the saleorderviewService
	 */
	public SaleorderviewService getSaleorderviewService() {
		return saleorderviewService;
	}
	/**
	 * @param saleorderviewService the saleorderviewService to set
	 */
	public void setSaleorderviewService(SaleorderviewService saleorderviewService) {
		this.saleorderviewService = saleorderviewService;
	}
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
	 * 
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		String search = ParamUtil.get(request, "search");
		request.setAttribute("paramUrl", paramUrl);
		request.setAttribute("search", search);
		return mapping.findForward("frames");
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
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG	&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "ORDER_DATE_BEGIN", params);
		ParamUtil.putStr2Map(request, "ORDER_DATE_END", params);
		ParamUtil.putStr2Map(request, "IS_CANCELED_FLAG", params);
		
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));

		params.put("ORDER_CHANN_ID", userBean.getLoginZTXXID());
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		ParamUtil.setOrderField(request, params, "a.ORDER_DATE DESC,a.SALE_ORDER_NO", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = saleorderviewService.pageQuery(params, pageNo);
		
		Map<String,Object> entry = new HashMap<String,Object>();
		//用户所在渠道信息
		Map<String,Object> chann = saleorderviewService.loadChann(userBean.getCHANN_ID());
		entry.put("INI_CREDIT", chann.get("INI_CREDIT"));//初始信用
		entry.put("REBATE_TOTAL", chann.get("REBATE_TOTAL"));//返利总额
		entry.put("REBATE_CURRT", chann.get("REBATE_CURRT"));//当前返利
//		entry.put("CREDIT_TOTAL", chann.get("CREDIT_TOTAL"));//信用总额
		entry.put("TEMP_CREDIT_REQ", chann.get("TEMP_CREDIT_REQ"));//临时信用
		entry.put("CREDIT_CURRT", chann.get("CREDIT_CURRT"));//当前信用
		entry.put("FREEZE_CREDIT", chann.get("FREEZE_CREDIT"));//冻结信用
		entry.put("PAYMENT_CREDIT", chann.get("PAYMENT_CREDIT"));//付款凭证信用
		entry.put("CHANN_NO", chann.get("CHANN_NO")); 
		entry.put("CHANN_NAME", chann.get("CHANN_NAME")); 
		
		//获取返利折扣
		Map<String,Object> result = LogicUtil.getChannRebateDsct(userBean.getCHANN_ID());
		if(null != result){
			request.setAttribute("REBATEDSCT", result.get("DECT_RATE"));//返利折扣
			request.setAttribute("REBATE_TOTAL", result.get("REBATE_TOTAL"));//返利总金额
			request.setAttribute("REBATE_CURRT", result.get("REBATE_CURRT"));//当前返利
			request.setAttribute("REBATE_FREEZE", result.get("REBATE_FREEZE"));//冻结的返利
		}
		
		String AREA_SER_ID =  userBean.getAREA_SER_ID(); //区域服务中心ID
		if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
			//走区域服务中心
			request.setAttribute("havaAreaSerId", 0);
			request.setAttribute("AREA_SER_ID", AREA_SER_ID);
		}
		//查询可用信用  add by zzb 2014-09-23
		if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){
			String userCredit = LogicUtil.getU9CurrCredit(userBean.getCHANN_NO());
			request.setAttribute("userCredit", userCredit);
		}
		
		request.setAttribute("rst", entry);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
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
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
		if (!StringUtil.isEmpty(SALE_ORDER_ID)) {
			List<SaleorderviewChldModel> result = saleorderviewService.childQuery(SALE_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childlist");
	}
	
	/**
	 * * to 详细信息
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
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
		if (!StringUtil.isEmpty(SALE_ORDER_ID)) {
			Map<String, String> entry = saleorderviewService.load(SALE_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
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
		IListPage page = saleorderviewService.goodsPageQuery(params, pageNo);
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
		String GOODS_ORDER_IDS = ParamUtil.get(request, "GOODS_ORDER_IDS");
		if (!StringUtil.isEmpty(GOODS_ORDER_IDS)) {
			List<SaleorderviewChldModel> result = saleorderviewService.goodsChildQuery(GOODS_ORDER_IDS);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("goodsChildlist");
	}
	
	
	 /**
     * * 生命周期
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward traceList(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String SALE_ORDER_ID =ParamUtil.get(request, "SALE_ORDER_ID");
        if(!StringUtil.isEmpty(SALE_ORDER_ID))
        {
        	 List <GoodsorderhdModelTrace> result = saleorderviewService.traceQuery(SALE_ORDER_ID);
             request.setAttribute("rst", result);
        }
        request.setAttribute("pvg",setPvg(userBean));
        return mapping.findForward("traceList");
    }
    
    
    public void toExpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "ORDER_DATE_BEGIN", params);
		ParamUtil.putStr2Map(request, "ORDER_DATE_END", params);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));

		params.put("ORDER_CHANN_ID", userBean.getLoginZTXXID());
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
	    
		
		List list = saleorderviewService.qrySaleOrderExp(params);
		
        //excel数据上列显示的列明
        String tmpContentCn="销售订单编号,单据类型,订货方编号,发货方名称,是否使用返利,收货方编号,收货方名称,联系人,电话,生产基地名称,收货地址编号,状态,主表备注," +
        		"货品编号,货品名称,规格型号,品牌,标准单位,是否是备货,是否非标,特殊规格说明,是否赠品标记,供货价,折扣率,折后单价,订货数量,订货金额,体积," +
        		"已发数量,明细状态,明细备注,区域名称,制单人名称,组织名称,收货地址,区域经理名称,区域经理电话,区域服务中心编号,区域服务中心名称";
        //
        String tmpContent="SALE_ORDER_NO,BILL_TYPE,ORDER_CHANN_NO,ORDER_CHANN_NAME,IS_USE_REBATE,RECV_CHANN_NO,RECV_CHANN_NAME," +
        		"PERSON_CON,TEL,SHIP_POINT_NAME,RECV_ADDR_NO,STATE,REMARK,PRD_NO,PRD_NAME,PRD_SPEC,BRAND,STD_UNIT,IS_BACKUP_FLAG," +
        		"IS_NO_STAND_FLAG,SPCL_SPEC_REMARK,IS_FREE_FLAG,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,ORDER_AMOUNT,VOLUME,SENDED_NUM,STATEDTL,REMARKDTL," +
        		"AREA_NAME,CRE_NAME,LEDGER_NAME,RECV_ADDR,AREA_MANAGE_NAME,AREA_MANAGE_TEL,AREA_SER_NO,AREA_SER_NAME";
        try {
			ExcelUtil.doExport(response, list, "我的总部订单明细", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,
				PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil
				.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_FINISH_CANCLE", QXUtil.checkPvg(userBean,
				PVG_FINISH_CANCLE));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil
				.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}
}
