package com.hoperun.drp.sale.delivershow.action;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.delivershow.service.DelivershowService;
import com.hoperun.erp.sale.turnoverhd.action.TurnoverhdAction;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.sys.model.UserBean;


/**
 * 分销 查看发运单
 * @author zhang_zhongbin
 *
 */
public class DelivershowAction extends BaseAction{
	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(DelivershowAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0020401";
	private static String PVG_EDIT = "";
	private static String PVG_SHIP_EDIT = "";
	
	//行关闭
	private static String PVG_ROW_CLOSE = "";
	
	private static String PVG_DELETE = "";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";//撤销
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
	
	private DelivershowService delivershowService;
	
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request,"paramUrl");
		request.setAttribute("paramUrl", paramUrl);
		return mapping.findForward("frames");
	}
 
	/**
	 * 一览页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("");
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "TRUCK_TYPE", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);
		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);
//		ParamUtil.putStr2Map(request, "DELIVER_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_NAME", params);
		ParamUtil.putStr2Map(request, "SHIP_POINT_NAME", params);
//		ParamUtil.putStr2Map(request, "PROV", params);
//		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);
	    ParamUtil.putStr2Map(request, "PRD_NO", params);
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);
	    ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_BEG", params);
	    ParamUtil.putStr2Map(request, "ADVC_SEND_DATE_END", params);
	    ParamUtil.putStr2MaptoUpperCase(request, "DELIVER_ORDER_NO", params); //发运单号 转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "FROM_BILL_NO", params); //来源发运单编号 转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "JOIN_DELIVER_ORDER_NO", params); //出货计划号  转换为大写
	    ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params); //销售订单编号  转换为大写
	    
		
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);

		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		String ZTXXID = userBean.getLoginZTXXID();
		StringBuffer sb = new StringBuffer(" 1=1 ");
		sb.append(" and u.ORDER_CHANN_ID='"+ZTXXID+"'");
		// 权限级别和审批流的封装
		params.put("QXJBCON", sb.toString());
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ADVC_SEND_DATE", "ASC");
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = delivershowService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("ZTXXID", ZTXXID);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}

	/**
	 * * 明细列表
	 * 
	 * @return the action forward
	 */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			List<TurnoverplanChildModel> result = delivershowService
					.childQuery(DELIVER_ORDER_ID);
			if (null != result) {
				request.setAttribute("resultSize", result.size());
			}
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childlist");
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
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		if(!StringUtil.isEmpty(DELIVER_ORDER_ID)){
			Map<String,String> entry = delivershowService.load(DELIVER_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
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
		pvgMap.put("PVG_ROW_CLOSE", QXUtil.checkPvg(userBean, PVG_ROW_CLOSE));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("PVG_SHIP_EDIT", QXUtil.checkPvg(userBean, PVG_SHIP_EDIT));
		return pvgMap;
	}

	public DelivershowService getDelivershowService() {
		return delivershowService;
	}

	public void setDelivershowService(DelivershowService delivershowService) {
		this.delivershowService = delivershowService;
	}
	
	
	

}
