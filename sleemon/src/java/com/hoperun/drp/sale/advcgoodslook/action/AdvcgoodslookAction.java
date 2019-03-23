package com.hoperun.drp.sale.advcgoodslook.action;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advcgoodslook.service.AdvcgoodslookService;
import com.hoperun.drp.sale.advcorder.action.AdvcorderAction;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.sys.model.UserBean;

public class AdvcgoodslookAction  extends BaseAction{
	/** 日志 **/
	private Logger logger = Logger.getLogger(AdvcorderAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0020901";
	private static String PVG_EDIT = "FX0020902";
	private static String PVG_DELETE = "FX0020903";
	// 启用,停用
	private static String PVG_START_STOP = "";
	 
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "FX0020904";
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
	private AdvcgoodslookService advcgoodslookService;


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
		if (Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl", paramUrl);
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NAME", params);
		ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
		ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
		ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);
		ParamUtil.putStr2Map(request, "CUST_NAME", params);
		ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
		ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
		ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
		ParamUtil.putStr2Map(request, "PRD_NO", params);
		ParamUtil.putStr2Map(request, "PRD_NAME", params);
		ParamUtil.putStr2Map(request, "PAR_PRD_NO", params);
		ParamUtil.putStr2Map(request, "PAR_PRD_NAME", params);
		String PRD_NO=ParamUtil.get(request, "PRD_NO");
		String PRD_NAME=ParamUtil.get(request, "PRD_NAME");
		String PAR_PRD_NO=ParamUtil.get(request, "PAR_PRD_NO");
		String PAR_PRD_NAME=ParamUtil.get(request, "PAR_PRD_NAME");
		params.put("PRD_NOQuery", StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
		params.put("PRD_NAMEQuery", StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
		params.put("PAR_PRD_NOQuery", StringUtil.creEqualCon("b.PAR_PRD_NO", PAR_PRD_NO, ","));
		params.put("PAR_PRD_NAMEQuery", StringUtil.creEqualCon("b.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		 
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				null, userBean));
	 
		strQx.append(" and  u.STATE ='审核通过' ");
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		
		// 权限级别和审批流的封装
		params.put("QXJBCON", sb.toString());
		
		// 设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		// 设置终端销售
		params.put("BILL_TYPE", "终端销售");
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_RECV_DATE,u.ADVC_ORDER_NO", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = advcgoodslookService.pageQuery(params, pageNo);
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelChld> result = advcgoodslookService
					.childQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childlist");
	}

	 
	/**
	 * * to 详细信息
	 * 
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean,PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			Map<String, String> entry = advcgoodslookService.load(ADVC_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	/**
	 * 导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void expertExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean,PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NAME", params);
		ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
		ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
		ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);
		ParamUtil.putStr2Map(request, "CUST_NAME", params);
		ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
		ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
		ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
		ParamUtil.putStr2Map(request, "PRD_NO", params);
		ParamUtil.putStr2Map(request, "PRD_NAME", params);
		ParamUtil.putStr2Map(request, "PAR_PRD_NO", params);
		ParamUtil.putStr2Map(request, "PAR_PRD_NAME", params);
		String PRD_NO=ParamUtil.get(request, "PRD_NO");
		String PRD_NAME=ParamUtil.get(request, "PRD_NAME");
		String PAR_PRD_NO=ParamUtil.get(request, "PAR_PRD_NO");
		String PAR_PRD_NAME=ParamUtil.get(request, "PAR_PRD_NAME");
		params.put("PRD_NOQuery", StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
		params.put("PRD_NAMEQuery", StringUtil.creCon("b.PRD_NAME", PRD_NAME, ","));
		params.put("PAR_PRD_NOQuery", StringUtil.creEqualCon("b.PAR_PRD_NO", PAR_PRD_NO, ","));
		params.put("PAR_PRD_NAMEQuery", StringUtil.creEqualCon("b.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		 
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				null, userBean));
	 
		strQx.append(" and  u.STATE ='审核通过' ");
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		
		// 权限级别和审批流的封装
		params.put("QXJBCON", sb.toString());
		
		// 设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		// 设置终端销售
		params.put("BILL_TYPE", "终端销售");
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_RECV_DATE,u.ADVC_ORDER_NO", "DESC");
		List list = advcgoodslookService.expertExcelQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="预订单编号,终端编号,终端名称,客户姓名,订金金额,应收总额,业务员,销售日期,主表备注,制单人," +
							"制单时间,制单部门,货品编号 - 特殊工艺编号,货品名称,货品类型,规格型号,品牌,标准单位,特殊规格说明,单价," +
							"折扣率,折后单价,订货数量,已发货数量,未发数量,未发金额,交货日期,应收金额,明细备注";
		String tmpContent = "ADVC_ORDER_NO,TERM_NO,TERM_NAME,CUST_NAME,ADVC_AMOUNT,PAYABLE_AMOUNT,SALE_PSON_NAME,SALE_DATE,MAINREMARK,CRE_NAME," +
							"CRE_TIME,DEPT_NAME,PRD_NO,PRD_NAME,PRD_TYPE,PRD_SPEC,BRAND,STD_UNIT,SPCL_DTL_REMARK,PRICE," +
							"DECT_RATE,DECT_PRICE,ORDER_NUM,SEND_NUM,NOSEND_NUM,NOSEND_AMOUNT,ORDER_RECV_DATE,PAYABLE_AMOUNT,REMARK";
		String colType= "string,string,string,string,number,number,string,string,string,string," +
						"string,string,string,string,string,string,string,string,string,number," +
						"number,number,number,number,number,number,string,number,string";
		try {
			ExcelUtil
					.doExport(response, list, "待发货预订单", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 

	public AdvcgoodslookService getAdvcgoodslookService() {
		return advcgoodslookService;
	}

	public void setAdvcgoodslookService(AdvcgoodslookService advcgoodslookService) {
		this.advcgoodslookService = advcgoodslookService;
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
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		 
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}
	 
	 
	 
	 

	 
	 
	 

}
