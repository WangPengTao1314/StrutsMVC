package com.hoperun.erp.sale.storeout.action;

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
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.pdtdeliver.action.PdtdeliverAction;
import com.hoperun.erp.sale.storeout.model.ErpStoreoutChildPrdModel;
import com.hoperun.erp.sale.storeout.service.ErpStoreoutService;
import com.hoperun.sys.model.UserBean;
/**
 * 总部-出库单
 * @author zhang_zhongbin
 *
 */
public class ErpStoreoutAction extends BaseAction{
	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(PdtdeliverAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0013601";
	private static String PVG_DRP_BWS = "FX0022701";
	private static String PVG_EDIT = "";
	private static String PVG_DELETE = "";
	
	//行关闭
	private static String PVG_ROW_CLOSE = "";
	//整单关闭
	private static String PVG_ORDER_CLOSE = "BS0013602";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "";
	/** end */
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
	
	private ErpStoreoutService erpStoreoutService;

	
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG &&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_DRP_BWS))) {
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
		if (Consts.FUN_CHEK_PVG &&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_DRP_BWS))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("");
		Map<String, String> params = new HashMap<String, String>();

		ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);//预计发货日期从
		ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);//预计发货日期到
		
		ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
//		ParamUtil.putStr2Map(request, "AREA_SER_NO", params);
//		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);//整车发运
//		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);//销售发运
		ParamUtil.putStr2Map(request, "CRE_NAME", params);//制单人
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);//制单时间从
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);//制单时间到
		ParamUtil.putStr2Map(request, "STOREOUT_TIME_BEG", params);//出库日期从
		ParamUtil.putStr2Map(request, "STOREOUT_TIME_END", params);//出库日期到
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);//收货方编号
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);//收货方名称
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);//规格型号
	    ParamUtil.putStr2Map(request, "PRD_NO", params);//货品编号
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);//货品名称
	    ParamUtil.putStr2Map(request, "SHIP_POINT_ID", params);//货品名称编号
		ParamUtil.putStr2Map(request, "SHIP_POINT_NAME", params);//生产基地名称
		ParamUtil.putStr2Map(request, "STATE", params);//状态
		ParamUtil.putStr2MaptoUpperCase(request, "DELIVER_ORDER_NO", params);//发运单号
		ParamUtil.putStr2MaptoUpperCase(request, "STOREOUT_NO", params);//出库单
		ParamUtil.putStr2MaptoUpperCase(request, "FROM_BILL_NO", params); //来源发运单编号 转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "JOIN_DELIVER_ORDER_NO", params); //出货计划号  转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params); //销售订单编号  转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "U9_SM_NO", params); //销售订单编号  转换为大写
		
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		
		String PROV = ParamUtil.get(request, "PROV");
		String PROV_SQL = StringUtil.creCon("PROV", PROV, ",");
		params.put("PROV_SQL", PROV_SQL); 
		params.put("PROV", PROV);
		String PVG="";
		//如果有总部出库单权限，则是区域分管查询条件
		if("0".equals(userBean.getIS_DRP_LEDGER())){
			params.put("CHANNS_CHARG",userBean.getCHANNS_CHARG());
			request.setAttribute("DRP_FLAG", "1");//总部
			PVG=PVG_BWS;
		}else{
			params.put("CHANN_ID",userBean.getCHANN_ID());
			params.put("RECV_CHANN_NO", userBean.getCHANN_NO());
			params.put("RECV_CHANN_NAME", userBean.getCHANN_NAME());
			request.setAttribute("DRP_FLAG", "0");//渠道
			PVG=PVG_DRP_BWS;
		}
//		if(!QXUtil.checkMKQX(userBean, PVG_BWS)){
//			//区域分管
//			
//		}else if(QXUtil.checkMKQX(userBean, PVG_DRP_BWS)){//如果是渠道出库单权限，则只能查询自己的渠道
//			
//		}
		
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		params.put("QXJBCON",sb.toString());
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = erpStoreoutService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
	}
	
	/**
	 * * 明细列表
	 * @return the action forward
	 */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		
		UserBean userBean = ParamUtil.getUserBean(request);
			if (Consts.FUN_CHEK_PVG &&(!QXUtil.checkMKQX(userBean, PVG_BWS)
					&&!QXUtil.checkMKQX(userBean, PVG_DRP_BWS))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		if (!StringUtil.isEmpty(STOREOUT_ID)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("STOREOUT_ID", STOREOUT_ID);
			// 只查询0的记录。1为删除。0为正常
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			// 字段排序
			ParamUtil.setOrderField(request, params, "u.STOREOUT_ID", "desc");
			List<ErpStoreoutChildPrdModel> result = erpStoreoutService.childQuery(params);
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
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG &&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_DRP_BWS)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}	
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		if(!StringUtil.isEmpty(STOREOUT_ID)){
			Map<String,String> entry = erpStoreoutService.load(STOREOUT_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
    
	/**
	 * 关闭
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
//		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
//			throw new ServiceException("对不起，您没有权限 !");
//		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		String U9_SM_NO = ParamUtil.get(request, "U9_SM_NO");
		
		try {
			 this.erpStoreoutService.txClose(STOREOUT_ID, DELIVER_ORDER_ID, U9_SM_NO, userBean);
			 jsonResult = jsonResult(true, "关闭成功");
			
		}catch (ServiceException e){
			jsonResult = jsonResult(false, e.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "关闭失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	 //导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);//预计发货日期从
		ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);//预计发货日期到
		
		ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
//		ParamUtil.putStr2Map(request, "AREA_SER_NO", params);
//		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);//整车发运
//		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);//销售发运
		ParamUtil.putStr2Map(request, "CRE_NAME", params);//制单人
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);//制单时间从
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);//制单时间到
		ParamUtil.putStr2Map(request, "STOREOUT_TIME_BEG", params);//出库日期从
		ParamUtil.putStr2Map(request, "STOREOUT_TIME_END", params);//出库日期到
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);//收货方编号
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);//收货方名称
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);//规格型号
	    ParamUtil.putStr2Map(request, "PRD_NO", params);//货品编号
	    ParamUtil.putStr2Map(request, "PRD_NAME", params);//货品名称
	    ParamUtil.putStr2Map(request, "SHIP_POINT_ID", params);//货品名称编号
		ParamUtil.putStr2Map(request, "SHIP_POINT_NAME", params);//生产基地名称
		ParamUtil.putStr2Map(request, "STATE", params);//状态
		ParamUtil.putStr2MaptoUpperCase(request, "DELIVER_ORDER_NO", params);//发运单号
		ParamUtil.putStr2MaptoUpperCase(request, "STOREOUT_NO", params);//出库单
		ParamUtil.putStr2MaptoUpperCase(request, "FROM_BILL_NO", params); //来源发运单编号 转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "JOIN_DELIVER_ORDER_NO", params); //出货计划号  转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params); //销售订单编号  转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "U9_SM_NO", params); //销售订单编号  转换为大写
		
		String DRP_FLAG=ParamUtil.get(request, "DRP_FLAG");
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		
		String PROV = ParamUtil.get(request, "PROV");
		String PROV_SQL = StringUtil.creCon("PROV", PROV, ",");
		params.put("PROV_SQL", PROV_SQL); 
		params.put("PROV", PROV);
		String PVG="";
		if(QXUtil.checkMKQX(userBean, PVG_BWS)){
			//区域分管
			params.put("CHANNS_CHARG",userBean.getCHANNS_CHARG());
			request.setAttribute("DRP_FLAG", "1");//总部
			PVG=PVG_BWS;
		}else if(QXUtil.checkMKQX(userBean, PVG_DRP_BWS)){//如果是渠道出库单权限，则只能查询自己的渠道
			params.put("CHANN_ID",userBean.getCHANN_ID());
			params.put("RECV_CHANN_NO", userBean.getCHANN_NO());
			params.put("RECV_CHANN_NAME", userBean.getCHANN_NAME());
			request.setAttribute("DRP_FLAG", "0");//渠道
			PVG=PVG_DRP_BWS;
		}
		
		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		StringBuffer QXJB=new StringBuffer();
		QXJB.append(StringUtil.getStrQX("u",sb.toString()));
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		params.put("QXJBCON",QXJB.toString());
		
        List list=erpStoreoutService.downQuery(params);
        //excel数据上列显示的列明
        String tmpContentCn;
        String tmpContent;
        if("0".equals(DRP_FLAG)){
        	 tmpContentCn="出库时间,出货单编号,单据类型,客户名称,所属战区,业务员,状态,货品名称,规格型号,特殊工艺描述,实际出库数量," +
			"单价,总金额,总体积,销售订单编号,车牌号,收货地址";
        	 tmpContent="STOREOUT_TIME,STOREOUT_NO,BILL_TYPE,ORDER_CHANN_NAME,AREA_NAME_P,AREA_MANAGE_NAME,STATE,PRD_NAME,PRD_SPEC,SPCL_DTL_REMARK,STOREOUT_NUM," +
			"DECT_PRICE,DECT_AMOUNT,VOLUMES,SALE_ORDER_NO,TRUCK_NO,RECV_ADDR";
        }else{
        	 tmpContentCn="出库时间,出货单编号,单据类型,客户名称,所属战区,业务员,状态,货品名称,规格型号,特殊工艺描述,实际出库数量," +
				"单价,总金额,总体积,销售订单编号,车牌号";
			 tmpContent="STOREOUT_TIME,STOREOUT_NO,BILL_TYPE,ORDER_CHANN_NAME,AREA_NAME_P,AREA_MANAGE_NAME,STATE,PRD_NAME,PRD_SPEC,SPCL_DTL_REMARK,STOREOUT_NUM," +
				"DECT_PRICE,DECT_AMOUNT,VOLUMES,SALE_ORDER_NO,TRUCK_NO";
        }
        try {
			ExcelUtil.doExport(response, list, "出货单明细", tmpContent, tmpContentCn);
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
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_FINISH_CANCLE", QXUtil.checkPvg(userBean,PVG_FINISH_CANCLE));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_DRP_BWS", QXUtil.checkPvg(userBean, PVG_DRP_BWS));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("PVG_ROW_CLOSE", QXUtil.checkPvg(userBean, PVG_ROW_CLOSE));
		pvgMap.put("PVG_ORDER_CLOSE", QXUtil.checkPvg(userBean, PVG_ORDER_CLOSE));
		return pvgMap;
	}
	
	
	
	
	public ErpStoreoutService getErpStoreoutService() {
		return erpStoreoutService;
	}

	public void setErpStoreoutService(ErpStoreoutService erpStoreoutService) {
		this.erpStoreoutService = erpStoreoutService;
	}
	
	
	
}
