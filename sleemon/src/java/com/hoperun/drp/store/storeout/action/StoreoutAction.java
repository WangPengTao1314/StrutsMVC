/**
 * prjName:喜临门营销平台
 * ucName:出库单处理
 * fileName:StoreoutAction
 */
package com.hoperun.drp.store.storeout.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storeout.model.StoreoutModel;
import com.hoperun.drp.store.storeout.model.StoreoutModelChld;
import com.hoperun.drp.store.storeout.model.StoreoutModelGrandChld;
import com.hoperun.drp.store.storeout.service.StoreoutService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func *@version 1.1 *@author chenj *@createdate 2013-09-15
 * 14:59:50
 */
public class StoreoutAction extends BaseAction {
	/** 日志 **/
	private Logger logger = Logger.getLogger(StoreoutAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查  销售出库
	private static String PVG_SBWS = "FX0030101";
	private static String PVG_SEDIT = "FX0030102";
	private static String PVG_SCOMMIT_CANCLE = "FX0030103";
	private static String PVG_CLOSE="FX0030104";//关闭销售出库确认
	private static String PVG_SRETURN = "FX0030105";
	
	
	// 增删改查 退货出库
	private static String PVG_TBWS = "FX0031301";
	private static String PVG_TEDIT = "FX0031302";
	private static String PVG_TCOMMIT_CANCLE = "FX0031303";
	private static String PVG_TRETURN = "FX0031304";
	
	// 增删改查分发出库
	private static String PVG_FBWS = "FX0030201";
	private static String PVG_FEDIT = "FX0030202";
	private static String PVG_FCOMMIT_CANCLE = "FX0030203";
	
	// 增删改查调拨出库
	private static String PVG_DBWS = "FX0031401";
	private static String PVG_DEDIT = "FX0031402";
	private static String PVG_DCOMMIT_CANCLE = "FX0031403";
	
	// 增删改查返修出库
	private static String PVG_XBWS = "FX0031601";
	private static String PVG_XEDIT = "FX0031602";
	private static String PVG_XCOMMIT_CANCLE = "FX0031603";
	
	// 增删改查区域服务中心销售出库处理
	private static String PVG_XXBWS = "FX0031801";
	private static String PVG_XXEDIT = "FX0031802";
	private static String PVG_XXCOMMIT_CANCLE = "FX0031803";
	
	//零星领料单出库
	private static String PVG_LXEDIT = "FX0032202";//编辑
	private static String PVG_LXBWS = "FX0032201";//浏览
	private static String PVG_LXCOMMIT_CANCLE = "FX0032203";//提交撤销
	private static String PVG_LRETURN = "FX0032205";
	
	/** end */
	/** 审批维护必须维护字段 **/
	
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";
	private static String AUD_TAB_KEY = "";
	private static String AUD_BUSS_STATE = "STATE";
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	
	//单据类型
	/**退货出库**/
	private static String T_BILL_TYPE = "退货出库";
	/**销售出库**/
	private static String S_BILL_TYPE = "销售出库";
	/**分发出库**/
	private static String F_BILL_TYPE = "分发出库";
	/**调拨出库**/
	private static String D_BILL_TYPE = "调拨出库";
	/**返修出库**/
	private static String X_BILL_TYPE = "返修出库";
	/**下级销售出库**/
	private static String XX_BILL_TYPE= "下级销售出库";
	/**零星领料单出库**/
	private static String LX_BILL_TYPE= "零星出库";

	/** 审批 end **/
	/** 业务service */
	private StoreoutService storeoutService;

	/**
	 * Sets the Storeout service.
	 * 
	 * @param StoreoutService
	 *            the new Storeout service
	 */
	public void setStoreoutService(StoreoutService storeoutService) {
		this.storeoutService = storeoutService;
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
//		String CHANN_TYPE=storeoutService.getCHANN_TYPE(userBean.getCHANN_ID());
		String billType = null;
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		String [] paraArr = paramUrl.split("andflag");
		for(int i=0;i<paraArr.length;i++){
			String param = paraArr[i];
			if(param.contains("BILL_TYPE")){
				String[] arr = param.split("equalsflag");
				billType = arr[1];
			}
		}
//		if("区域服务中心".equals(CHANN_TYPE)&&"XSCK".equals(billType)&& !"true".equals(Consts.IS_OLD_FLOW)){
//			billType="XXSCK";
//		}
		String strPVG_BWS = getBWSQXByType(billType);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, strPVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		String search = ParamUtil.get(request, "search");
		request.setAttribute("search", search);
		request.setAttribute("paramUrl", paramUrl);
		request.setAttribute("BILL_TYPE", billType);
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
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		UserBean userBean = ParamUtil.getUserBean(request);
		String billType =ParamUtil.get(request, "BILL_TYPE");
//		String CHANN_TYPE=storeoutService.getCHANN_TYPE(userBean.getCHANN_ID());
//		if("区域服务中心".equals(CHANN_TYPE)&&"XSCK".equals(billType)){
//			billType="XXSCK";
//		}
		String strPVG_BWS = getBWSQXByType(billType);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, strPVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		billType = convBillType(billType);
		Map<String, String> params = new HashMap<String, String>();
		//
		setQueryData(request,billType, params,userBean);
		
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装
		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module, strPVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		
		if(StringUtil.isEmpty(search)){
			sb.append(" and u.STATE = '");
			sb.append(BusinessConsts.UNDONE);
			sb.append("' ");
		}
		params.put("QXJBCON",sb.toString());
		
		//销售出库 处理的 金额和数量
		if("销售出库".equals(billType)){
			String DEAL_TIME_BEG = ParamUtil.get(request, "DEAL_TIME_BEG");//处理时间
			String DEAL_TIME_END = ParamUtil.get(request, "DEAL_TIME_END");//处理时间
			Map<String,String> timeMap = new HashMap<String,String>();
			timeMap.put("DATE_TIME_END", DEAL_TIME_END);
			timeMap.put("DATE_TIME_BEG", DEAL_TIME_BEG);
			timeMap.put("ZTXXID", userBean.getLoginZTXXID());
			Map<String,Object> result = storeoutService.quereyDealAmount(timeMap);
			result.put("DATE_TIME_BEG", DEAL_TIME_BEG);
			request.setAttribute("delMap", result);
		}
		
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.STOREOUT_NO ASC,u.CRE_TIME", "DESC");
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = storeoutService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("search", search);
		request.setAttribute("pvg", setPvg(userBean,billType));
		request.setAttribute("page", page);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("AREA_SER_ID", userBean.getAREA_SER_ID());
		request.setAttribute("BASE_CHANN_ID", userBean.getBASE_CHANN_ID());
		return mapping.findForward("list");
		
	}

	private void setQueryData(HttpServletRequest request,String billType,
			Map<String, String> params,UserBean userBean) {
		ParamUtil.putStr2Map(request, "STOREOUT_ID", params);
		ParamUtil.putStr2Map(request, "STOREOUT_NO", params);
		params.put("BILL_TYPE", billType);
		ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_ID", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "STOREOUT_STORE_ID", params);
		ParamUtil.putStr2Map(request, "STOREOUT_STORE_NO", params);
		ParamUtil.putStr2Map(request, "STOREOUT_STORE_NAME", params);
		ParamUtil.putStr2Map(request, "STORAGE_FLAG", params);
		ParamUtil.putStr2Map(request, "TERM_NAME", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
		ParamUtil.putStr2Map(request, "CUST_NAME", params);
		ParamUtil.putStr2Map(request, "TEL", params);
		ParamUtil.putStr2Map(request, "CRE_NAME", params);
	    ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
        ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);
        ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);
        ParamUtil.putStr2Map(request, "CONTRACT_NO", params);//合同编号
        ParamUtil.putStr2Map(request, "CUST_NAME", params);//客户名称
        ParamUtil.putStr2Map(request, "TEL", params);//电话
        ParamUtil.putStr2Map(request, "PRD_NO", params);
		ParamUtil.putStr2Map(request, "PRD_NAME", params);
		ParamUtil.putStr2Map(request, "DEAL_TIME_BEG", params);
		ParamUtil.putStr2Map(request, "DEAL_TIME_END", params);
		String PRD_NO=ParamUtil.utf8Decoder(request, "PRD_NO");
		params.put("PRD_NOQuery", StringUtil.creCon("a.PRD_NO", PRD_NO, ","));
		String PRD_NAME=ParamUtil.utf8Decoder(request, "PRD_NAME");
		params.put("PRD_NAMEQuery", StringUtil.creCon("a.PRD_NAME", PRD_NAME, ","));
        
		ParamUtil.putStr2Map(request, "DEPT_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		//库房分管
		params.put("STOREOUT_STORE_CHARG",userBean.getSTORE_CHARG());
		
		
		
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
		String billType =ParamUtil.get(request, "BILL_TYPE");
		String strPVG_BWS = getBWSQXByType(billType);
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, strPVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		if (!StringUtil.isEmpty(STOREOUT_ID)) {
			List<StoreoutModelChld> result = storeoutService
					.childQuery(STOREOUT_ID);
			request.setAttribute("rst", result);
			request.setAttribute("STOREOUT_ID", STOREOUT_ID);	
		}
		request.setAttribute("pvg", setPvg(userBean,billType));
		return mapping.findForward("childlist");
	}

	/**
	 * * 库位明细列表
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
	public ActionForward storgChildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		String billType =ParamUtil.get(request, "BILL_TYPE");
		String strPVG_BWS = getBWSQXByType(billType);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, strPVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
	    String STOREOUT_DTL_IDs = request.getParameter("STOREOUT_DTL_IDS");
	    if (!StringUtil.isEmpty(STOREOUT_ID)) {
	    	 Map<String,String> params = new HashMap<String,String>();
	    	 
	    	 params.put("STOREOUT_DTL_IDS",STOREOUT_DTL_IDs);
	    	 params.put("STOREOUT_ID",STOREOUT_ID);
	    	 List<StoreoutModelGrandChld> result = storeoutService
					.storgChildQuery(params);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean,billType));
//		request.setAttribute("STOREOUT_DTL_ID",STOREOUT_DTL_ID);
		return mapping.findForward("grandchildlist");
	}
	
	
	/**
	 * * 修改库位明细列表
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
	public void storgChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String storeoutId = ParamUtil.get(request, "STOREOUT_ID");
			String storeoutDtlId = ParamUtil.get(request, "STOREOUT_DTL_ID");
			String jsonData = ParamUtil.get(request, "childJsonData");
			List<StoreoutModelGrandChld> chldModelList = null;
			if (!StringUtil.isEmpty(jsonData)) {
				chldModelList = new Gson().fromJson(jsonData,
						new TypeToken<List<StoreoutModelGrandChld>>() {
						}.getType());
			}
			storeoutService.txBatchStorgEdit(storeoutId,storeoutDtlId, chldModelList, userBean);
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
	 * *  to修改库位明细列表
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
	public ActionForward toStorgChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String billType =ParamUtil.get(request, "BILL_TYPE");
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, getEditQXByType(billType))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		String storeId = ParamUtil.get(request, "STOREOUT_STORE_ID");
		String STOREOUT_DTL_ID = ParamUtil.get(request, "STOREOUT_DTL_ID");
		String STOREOUT_DTL_IDs = "'"+STOREOUT_DTL_ID+"'";
		if (!StringUtil.isEmpty(STOREOUT_ID)) {
	    	 Map<String,String> params = new HashMap<String,String>();
	    	 params.put("STOREOUT_DTL_IDS",STOREOUT_DTL_IDs);
	    	 params.put("STOREOUT_ID",STOREOUT_ID);
			List<StoreoutModelGrandChld> result = storeoutService
					.storgChildQuery(params);
			request.setAttribute("rst", result);
		}
		List<StoreoutModelChld> dtlResult = storeoutService.childQueryById(STOREOUT_DTL_IDs);
		request.setAttribute("dtlRst", dtlResult);
		request.setAttribute("pvg", setPvg(userBean,billType));
		request.setAttribute("STOREOUT_ID",STOREOUT_ID);
		request.setAttribute("STOREOUT_DTL_ID",STOREOUT_DTL_ID);
		request.setAttribute("STOREOUT_STORE_ID",storeId);
		request.setAttribute("BILL_TYPE", billType);
		return mapping.findForward("grandchildedit");
	}


	

	/**
	 * * 编辑框架页面加载子页面
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
	public ActionForward modifyToChildEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String billType =ParamUtil.get(request, "BILL_TYPE");
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, getEditQXByType(billType))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		if (!StringUtil.isEmpty(STOREOUT_ID)) {
			List<StoreoutModelChld> result = storeoutService
					.childQuery(STOREOUT_ID);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("childedit");
	}

	/**
	 * * to 直接编辑明细页面
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
	public ActionForward toChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String billType =ParamUtil.get(request, "BILL_TYPE");
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, getEditQXByType(billType))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		// 多个Id批量查询，用逗号隔开
		String STOREOUT_DTL_IDs = request.getParameter("STOREOUT_DTL_IDS");
		// 没有零星领料Id可以直接跳转。
		if (!StringUtil.isEmpty(STOREOUT_DTL_IDs)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("STOREOUT_DTL_IDS", STOREOUT_DTL_IDs);
			List<StoreoutModelChld> list = storeoutService.loadChilds(params);
			request.setAttribute("rst", list);
		}
		return mapping.findForward("childedit");
	}
	
	/**
	 * to 货品扫码页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toEditScanDtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		if (Consts.FUN_CHEK_PVG ) {
//			throw new ServiceException("对不起，您没有权限 !");
//		}
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		String billType =ParamUtil.get(request, "BILL_TYPE");
		if (!StringUtil.isEmpty(STOREOUT_ID)) {
			List<StoreoutModelChld> result = storeoutService
					.childQuery(STOREOUT_ID);
			request.setAttribute("rst", result);
		}
	    request.setAttribute("BILL_TYPE", billType);
	    request.setAttribute("STOREOUT_ID", STOREOUT_ID);
		return mapping.findForward("scan");
	}
	
	/**跟据序列号查出库货品信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	 
	@SuppressWarnings("unchecked")
	public void editScanList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try{
			
//			String storeOutId =ParamUtil.get(request, "STOREOUT_ID");
//			String billType =ParamUtil.get(request, "BILL_TYPE");
			String pdtSN = ParamUtil.get(request, "SN");
			//先检查是否已经扫过该序列号
//			boolean isHave = this.storeoutService.getDrpStoreOutDtlBySN(pdtSN);
			
//			if(!isHave){
				Map<String,Object> erpmap = this.storeoutService.getErpStoreOutDtl(pdtSN);
			    if(erpmap!=null){
			    	String pdtId = (String)erpmap.get("PRD_ID");
			    	pdtId = pdtId.trim();
				    Map<String,Object> drpMap = this.storeoutService.getDrpStoreOutDtl(pdtId);
					if(drpMap!=null){
						 jsonResult = new Gson().toJson(new Result(true, drpMap, ""));
					}else{
						jsonResult = jsonResult(false, "该货品不是此次要发的货品");	
					}		    	
			    }else{
			    	jsonResult = jsonResult(false, "没有匹配到货品信息,请检查");	
			    }
//			}else{
//				jsonResult = jsonResult(false, "已扫过该序列号");	
//			}
			
		}catch(Exception e){
			logger.info(e);
			jsonResult = jsonResult(false, "描码货品失败");	
		}
		if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
	}
		
	

	/**
	 * * 主表 新增/修改数据
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
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String billType =ParamUtil.get(request, "BILL_TYPE");
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, getEditQXByType(billType))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
			String parentJsonData = ParamUtil.get(request, "parentJsonData");
			StoreoutModel model = new Gson().fromJson(parentJsonData,
					new TypeToken<StoreoutModel>() {
					}.getType());
			String jsonDate = ParamUtil.get(request, "childJsonData");
			List<StoreoutModelChld> chldModelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				chldModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<StoreoutModelChld>>() {
						}.getType());
			}
			storeoutService.txEdit(STOREOUT_ID, model, chldModelList, userBean);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * * 子表 新增/修改数据
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
	public void childEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String billType =ParamUtil.get(request, "BILL_TYPE");
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, getEditQXByType(billType))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String STOREOUT_ID = request.getParameter("STOREOUT_ID");
			String jsonDate = request.getParameter("childJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<StoreoutModelChld> modelList = new Gson().fromJson(
						jsonDate, new TypeToken<List<StoreoutModelChld>>() {
						}.getType());
				storeoutService.txChildEdit(STOREOUT_ID, modelList,userBean);
			}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}


	public void scanChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String billType =ParamUtil.get(request, "BILL_TYPE");
		String STOREOUT_ID =ParamUtil.get(request, "STOREOUT_ID");
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, getEditQXByType(billType))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String jsonDate = request.getParameter("childJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<StoreoutModelChld> modelList = new Gson().fromJson(
						jsonDate, new TypeToken<List<StoreoutModelChld>>() {
						}.getType());
				storeoutService.txScanChildEdit(STOREOUT_ID, modelList,userBean);
			}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}



	
	/**
	 * 库存信息批量删除 软删除 Description :.
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
	public void storegChildDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String billType =ParamUtil.get(request, "BILL_TYPE");
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, getEditQXByType(billType))) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String STOREOUT_STORG_DTL_IDS = request.getParameter("STOREOUT_STORG_DTL_IDS");
			storeoutService.txBatch4DeleteChild(STOREOUT_STORG_DTL_IDS);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "删除失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * 关闭销售出库确认
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
	public void closeDocument(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_CLOSE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String STOREOUT_ID = request.getParameter("STOREOUT_ID");
			String BILL_TYPE = request.getParameter("BILL_TYPE");
			if(LX_BILL_TYPE.equals(BILL_TYPE)){//零星出库
				storeoutService.txCloseSporadicStoreout(STOREOUT_ID,userBean);
			}else{
				//验证是否存在退货单，如果存在退货单，不能关闭
				boolean flag=storeoutService.checkReturnAdvc(STOREOUT_ID);
				if(!flag){
					throw new ServiceException("对不起，您所选的出库单存在终端退货单，不能关闭!");
				}
				storeoutService.closeDocument(STOREOUT_ID,userBean);
			}
			jsonResult = jsonResult(true, "关闭成功");
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "关闭失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
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
		String billType =ParamUtil.get(request, "BILL_TYPE");
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, getEditQXByType(billType))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String STOREOUT_ID = ParamUtil.get(request, "STOREOUT_ID");
		if (!StringUtil.isEmpty(STOREOUT_ID)) {
			Map<String, String> entry = storeoutService.load(STOREOUT_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("detail");
	}

	/**
	 * * 提交时，校验是否有明细.
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
		String billType =ParamUtil.get(request, "BILL_TYPE");
		billType = convBillType(billType);
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean,getCommitQXByType(billType))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String errorId = "";
		try {
			String storeOutId = request.getParameter("STOREOUT_ID");
			Map<String,String> model = this.storeoutService.load(storeOutId);
			if("已处理".equals(model.get("STATE"))){
				throw new ServiceException("该单据已被["+model.get("UPD_NAME")+"]处理过了!");
			}
			if("1".equals(String.valueOf(model.get("DEL_FLAG")))){
				throw new ServiceException("该单据已被删除，不能出库，请刷新页面!");
			}
			List chldlist = storeoutService.childQuery(storeOutId);
			if (chldlist.size() == 0) {
				errorId = "0";
				throw new Exception("没有明细");
			}
			
			if(!S_BILL_TYPE.equals(billType)){
				for(int i=0 ;i<chldlist.size();i++){
					HashMap chldMap = (HashMap)chldlist.get(i);
					BigDecimal NOTICE_NUM = (BigDecimal)chldMap.get("NOTICE_NUM");
					BigDecimal REAL_NUM = (BigDecimal)chldMap.get("REAL_NUM");
					if(!NOTICE_NUM.toString().equals(REAL_NUM.toString())){
						errorId = "1";
						throw new Exception("还有未扫码的货品,请进行扫码!");
					}
				}
//				for(StoreoutModelChld child : chldlist){
//					if(!child.getNOTICE_NUM().equals(child.getREAL_NUM())){
//						errorId = "1";
//						throw new Exception("还有未扫码的货品,请进行扫码!");
//					}
//				}
			}
			
			if(S_BILL_TYPE.equals(billType)){
				//出库数量不能大于预订单订货数量 (出库数量<=预订单订货数量-已发货数量)
				Map<String,String> map = storeoutService.txCheckRealNumAndAdvcOrderNum(storeOutId);
				if(null != map){
					errorId = "2";
					jsonResult = new Gson().toJson(new Result(false, map, ""));
					throw new Exception("出库数量不能大于订货数量!");
				}
				//验证是否所有明细都未扫码
				Integer count=storeoutService.countSnNum(storeOutId);
				if(count<=0){
					errorId = "2";
					throw new Exception("该单据未扫码，不能提交!");
				}
			}
			
			boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),DateUtil.newDataTime());
			if(isMonthAcc){
				errorId="2";
				throw new Exception("处于月结阶段,不能做出库!");
			}
			boolean storeFreezeFlag=LogicUtil.checkFreezeStore(model.get("STOREOUT_STORE_ID"));
			if(!storeFreezeFlag){
				errorId="2";
				throw new Exception("当前出库库房已被冻结,不能做出库!");
			}
			if (!StringUtil.isEmpty(storeOutId)) {
				if(S_BILL_TYPE.equals(billType)){//销售出库
					List<StoreoutModel> list = storeoutService.mainQuery(storeOutId);
					//加载 退货单信息包括收货方的渠道信息
//					HashMap<String,String> storeOutMap = storeoutService.load(storeOutId);
//			        Map<String,String> chann =  storeoutService.loadChann(userBean.getCHANN_ID());
//			        String AREA_SER_ID = chann.get("AREA_SER_ID");
					storeoutService.txSlCommit(list, userBean,storeOutId,chldlist);
				}else if(T_BILL_TYPE.equals(billType)){//退货出库
					List<StoreoutModel> list = storeoutService.mainQuery(storeOutId);
					//加载 退货单信息包括收货方的渠道信息
					HashMap<String,String> storeOutMap = storeoutService.load(storeOutId);
			        Map<String,String> chann =  storeoutService.loadChann(userBean.getCHANN_ID());
			        String AREA_SER_ID = chann.get("AREA_SER_ID");
					List<StoreoutModelChld> storeOutDtlList = storeoutService.childQuery(storeOutId);
					//有区域服务中心
					if(!StringUtil.isEmpty(AREA_SER_ID) && !"true".equals(Consts.IS_OLD_FLOW)){
						MsgInfo mess  = storeoutService.
						txAreaSerStoreOutDone(storeOutMap, storeOutDtlList, userBean, storeOutId);
				    	jsonResult = jsonResult(mess.isFLAG(), mess.getMESS());
					}else{
						storeoutService.txThCommit(list,storeOutDtlList, userBean,storeOutId);
					}
				}else if(D_BILL_TYPE.equals(billType)){//调拨出库
					List<StoreoutModel> list = storeoutService.mainQuery(storeOutId);
					//加载 退货单信息包括收货方的渠道信息
					HashMap<String,String> storeOutMap = storeoutService.load(storeOutId);
			        Map<String,String> chann =  storeoutService.loadChann(userBean.getCHANN_ID());
			        String AREA_SER_ID = chann.get("AREA_SER_ID");
			    	List<StoreoutModelChld> dtllist = storeoutService.childQuery(storeOutId);
			    	MsgInfo mess =  storeoutService.txDbCommit(list,dtllist, userBean,storeOutId);
			    	jsonResult = jsonResult(mess.isFLAG(), mess.getMESS());
				}else if(F_BILL_TYPE.equals(billType)){//分发出库
					List<StoreoutModel> list = storeoutService.mainQuery(storeOutId);
					//加载 退货单信息包括收货方的渠道信息
					HashMap<String,String> storeOutMap = storeoutService.load(storeOutId);
			        Map<String,String> chann =  storeoutService.loadChann(userBean.getCHANN_ID());
			        String AREA_SER_ID = chann.get("AREA_SER_ID");
					List<StoreoutModelChld> dtllist = storeoutService
					.childQuery(storeOutId);
					storeoutService.txFFCommit(list,dtllist, userBean,storeOutId);
				}else if(X_BILL_TYPE.equals(billType)){//返修出库
					List<StoreoutModel> list = storeoutService.mainQuery(storeOutId);
					//加载 退货单信息包括收货方的渠道信息
					HashMap<String,String> storeOutMap = storeoutService.load(storeOutId);
			        Map<String,String> chann =  storeoutService.loadChann(userBean.getCHANN_ID());
			        String AREA_SER_ID = chann.get("AREA_SER_ID");
					List<StoreoutModelChld> dtllist = storeoutService
					.childQuery(storeOutId);
					storeoutService.txFXCommit(list,dtllist, userBean,storeOutId);
				}else if(XX_BILL_TYPE.equals(billType)){//下级销售出库
					//获得出库单和销售出库通知的信息  edit by zhuxw 2014-06-07
					//加载 退货单信息包括收货方的渠道信息
					List<StoreoutModel> saleToLowerList = storeoutService.mainSaleToLowerQuery(storeOutId);
					List<Map<String,String>> dtlGroupList = storeoutService.childQueryGroup(storeOutId);
					List<StoreoutModelChld> dtllist = storeoutService.childQuerySaleOutDtl(storeOutId);
					storeoutService.txXXCommit(saleToLowerList,dtllist,dtlGroupList, userBean,storeOutId);
				}else if(LX_BILL_TYPE.equals(billType)){//零星出库
					List<StoreoutModel> list = storeoutService.mainQuery(storeOutId);
					List result = storeoutService.childQuery(storeOutId);
					for(int i=0;i<result.size();i++){
						HashMap chldMap = (HashMap)result.get(i);
						String PRD_NAME = (String)chldMap.get("PRD_NAME");
						BigDecimal REAL_NUM = (BigDecimal)chldMap.get("REAL_NUM");
						if(REAL_NUM==null||"0".equals(REAL_NUM.toString())){
							errorId="2";
							throw new Exception(PRD_NAME+":无实际出库数量,不允许出库!");
						}
					}
					MsgInfo mess =  storeoutService.txLxCommit(list, userBean,storeOutId);
			    	jsonResult = jsonResult(mess.isFLAG(), mess.getMESS());
				}
			}
		}catch (ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		}catch (Exception e) {
			if ("0".equals(errorId)) {
				jsonResult = jsonResult(false, "没有明细，不能提交!");
			}else if("1".equals(errorId)){
				jsonResult = jsonResult(false, "还有未扫码的货品,请进行扫码!");
			}else if("2".equals(errorId)){
				jsonResult = jsonResult(false, e.getMessage());
			}else{
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
	 * * 设置权限Map
	 * 
	 * @param UserBean
	 *            the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean,String billType) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, getBWSQXByType(billType)));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, getEditQXByType(billType)));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_SCOMMIT_CANCLE));//销售出库
		pvgMap.put("PVG_TCOMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_TCOMMIT_CANCLE));//退货出库
		pvgMap.put("PVG_FCOMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_FCOMMIT_CANCLE));//分发出库
		pvgMap.put("PVG_DCOMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_FCOMMIT_CANCLE));//调拨出库
		pvgMap.put("PVG_XCOMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_FCOMMIT_CANCLE));//返修出库
		pvgMap.put("PVG_XXCOMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_XXCOMMIT_CANCLE));//区域服务中心销售出库
		pvgMap.put("PVG_CLOSE", QXUtil.checkPvg(userBean,PVG_CLOSE));//区域服务中心销售出库
		pvgMap.put("PVG_SRETURN", QXUtil.checkPvg(userBean,PVG_SRETURN));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_TRETURN", QXUtil.checkPvg(userBean, PVG_TRETURN));
		pvgMap.put("PVG_LRETURN", QXUtil.checkPvg(userBean, PVG_LRETURN));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}
	
	
	private static String convBillType(String billType){
		if("XSCK".equals(billType)){
			return S_BILL_TYPE;
		}else if("THCK".equals(billType)){
			return T_BILL_TYPE;
		}else if("FFCK".equals(billType)){
			return F_BILL_TYPE;
		}else if("DBCK".equals(billType)){
			return D_BILL_TYPE;
		}else if("FXCK".equals(billType)){
			return X_BILL_TYPE;
		}else if("XXSCK".equals(billType)){
			return XX_BILL_TYPE;
		}else if("LXLLCK".equals(billType)){
			return  LX_BILL_TYPE;//零星领料单出库
		}
		return billType;
	}
	
	/**跟据单据类型取查询权限CODE
	 * @param billType
	 * @return
	 */
	private static String getBWSQXByType(String billType){
		billType = convBillType(billType);
		if(S_BILL_TYPE.equals(billType)){
			return PVG_SBWS;
		}else if(T_BILL_TYPE.equals(billType)){
			return PVG_TBWS;
		}else if(D_BILL_TYPE.equals(billType)){
			return PVG_DBWS;
		}else if(F_BILL_TYPE.equals(billType)){
			return PVG_FBWS;
		}else if(X_BILL_TYPE.equals(billType)){
			return PVG_XBWS;
		}else if(XX_BILL_TYPE.equals(billType)){
			return PVG_XXBWS;
		}else if(LX_BILL_TYPE.equals(billType)){
			return  PVG_LXBWS;//零星领料单出库
		}
		return null;
	}
	
	/**跟据单据类型取修改权限CODE
	 * @param billType
	 * @return
	 */
	private static String getEditQXByType(String billType){
		billType = convBillType(billType);
		if(S_BILL_TYPE.equals(billType)){
			return PVG_SEDIT;
		}else if(T_BILL_TYPE.equals(billType)){
			return PVG_TEDIT;
		}else if(D_BILL_TYPE.equals(billType)){
			return PVG_DEDIT;
		}else if(F_BILL_TYPE.equals(billType)){
			return PVG_FEDIT;
		}else if(X_BILL_TYPE.equals(billType)){
			return PVG_XEDIT;
		}else if(XX_BILL_TYPE.equals(billType)){
			return PVG_XXEDIT;
		}else if(LX_BILL_TYPE.equals(billType)){
			return PVG_LXEDIT;//零星领料单出库
		}
		
		return null;
	}
	
	/**跟据单据类型取处理权限CODE
	 * @param billType
	 * @return
	 */
	private static String getCommitQXByType(String billType){
		billType = convBillType(billType);
		if(S_BILL_TYPE.equals(billType)){
			return PVG_SCOMMIT_CANCLE;
		}else if(T_BILL_TYPE.equals(billType)){
			return PVG_TCOMMIT_CANCLE;
		}else if(D_BILL_TYPE.equals(billType)){
			return PVG_DCOMMIT_CANCLE;
		}else if(F_BILL_TYPE.equals(billType)){
			return PVG_FCOMMIT_CANCLE;
		}else if(X_BILL_TYPE.equals(billType)){
			return PVG_XCOMMIT_CANCLE;
		}else if(XX_BILL_TYPE.equals(billType)){
			return PVG_XXCOMMIT_CANCLE;
		}else if(LX_BILL_TYPE.equals(billType)){
			return PVG_LXCOMMIT_CANCLE;//零星领料单出库
		}
		return null;
	}
	/**
	 * 零星出库打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward sporadicPrintInfo(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	UserBean userBean = ParamUtil.getUserBean(request);
		 	 String STOREOUT_ID = request.getParameter("STOREOUT_ID");
		 	//记录打印次数
//		 	Map<String,String> printMap=new HashMap<String, String>();
//		 	printMap.put("TABLE_NAME", "DRP_STOREOUT");
//		 	printMap.put("BUSS_ID", STOREOUT_ID);
//		 	if(!LogicUtil.txUpdatePrintNum(printMap)){
//		 		throw new ServiceException("对不起，记录打印次数有误，请联系管理员 !");
//		 	}
		 	 StringBuffer paramSql=new StringBuffer();
		 	Map<String,String> map=storeoutService.getSellInfo(STOREOUT_ID);
		 	double gift_amount=storeoutService.getDtlGiftAmount(STOREOUT_ID);
			Object obj=map.get("PAYABLE_AMOUNT");
			if(null==obj){
				 obj="0";
			 }
		 	paramSql.append("sporadicStoreOutPrint.raq&STOREOUT_ID=").append(STOREOUT_ID)
		 			.append("&arg1=").append(obj.toString())
				 	.append("&arg2=").append(userBean.getXM());
		 	obj=map.get("PAYED_TOTAL_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 paramSql.append("&arg3=").append(obj.toString()).append("&arg4=").append(gift_amount);
		 	 /**
			 StringBuffer storeOutSql = new StringBuffer();
			 String TEL=storeoutService.getChannTel(userBean.getCHANN_ID());
			 double gift_amount=storeoutService.getDtlGiftAmount(STOREOUT_ID);
			 storeOutSql.append("rptSql=select ")
			 	   .append("a.STOREOUT_STORE_NAME,")
			 	   .append("to_char(a.CRE_TIME,'yyyy-mm-dd') CRE_TIME,")
			 	   .append("a.STOREOUT_NO,")
			 	   .append("b.MOBILE,")
			 	   .append("b.ADDRESS,")
			 	   .append("a.REMARK,")
			 	   .append("a.CRE_NAME,")
			 	   .append("a.CUST_NAME,")
			 	   .append("'").append(TEL).append("' CHANNTEL, ")
			 	   .append("a.TEL,")
			 	   .append("NVL(a.STOREOUT_AMOUNT,0)STOREOUT_AMOUNT,")
			 	   .append("a.RECV_ADDR, ")
			 	   .append("q.CONTRACT_NO,")
			 	   .append("advc.ADVC_ORDER_NO,")
			 	   .append("advc.TERM_NAME,")
			 	   .append("advc.SALE_PSON_NAME,")
			 	   .append("advc.RECEIPT_NO,advc.PROMOTE_NAME,")
			 	   .append("to_char(advc.SALE_DATE,'yyyy-mm-dd') SALE_DATE,")
			 	   .append("(select sum(d.DECT_AMOUNT) from DRP_STOREOUT_DTL d where a.STOREOUT_ID=d.STOREOUT_ID and d.DEL_FLAG=0)DECT_AMOUNT ")
			 	   .append(" from DRP_STOREOUT a")
			 	   .append(" left join BASE_CHANN b on a.SEND_CHANN_ID=b.CHANN_ID")
			 	   .append(" left join DRP_ADVC_SEND_REQ q on a.FROM_BILL_ID=q.ADVC_SEND_REQ_ID ")
			 	   .append(" left join DRP_ADVC_ORDER advc on q.FROM_BILL_ID=advc.ADVC_ORDER_ID ")
			 	   .append(" where a.STOREOUT_ID='").append(STOREOUT_ID).append("';");
			 int dtlCount=storeoutService.getDtlCount(STOREOUT_ID);
			 double page=0;
			 if(dtlCount%6!=0){
				 page=Math.ceil((double)dtlCount/6);
			 }
			 double newCount=page*6;
			 StringBuffer dtlSql=new StringBuffer();
			 dtlSql.append("dtlSql=select temp.*,rownum from (select ")
			 	   .append("a.PRD_NO,")
			 	   .append("a.PRD_NAME,")
			 	   .append("a.PRD_SPEC,")
			 	   .append("a.STD_UNIT,")
			 	   .append("sum(a.NOTICE_NUM)NOTICE_NUM,")
			 	   .append("(case when a.REMARK is not null then b.SPCL_DTL_REMARK || '(' || a.REMARK ||')' else b.SPCL_DTL_REMARK end) SPCLREMARK,")
			 	   .append("a.DECT_PRICE,")
			 	   .append("sum(a.DECT_AMOUNT)DECT_AMOUNT,")
			 	   .append("a.REMARK,a.PRD_TYPE ")
			 	   .append(" from DRP_STOREOUT_DTL a ")
			 	   .append(" left join DRP_SPCL_TECH b on a.SPCL_TECH_ID=b.SPCL_TECH_ID and b.USE_FLAG=")
			 	   .append(BusinessConsts.YJLBJ_FLAG_TRUE)
			 	   .append(" where a.STOREOUT_ID='")
			 	   .append(STOREOUT_ID)
			 	   .append("' and a.DEL_FLAG=0  ")
			 	   .append(" group by ")
			 	   .append("a.PRD_NO,")
			 	   .append("a.PRD_NAME,")
			 	   .append("a.PRD_SPEC,")
			 	   .append("a.STD_UNIT,")
			 	   .append("a.DECT_PRICE,")
			 	   .append("b.SPCL_DTL_REMARK,")
			 	   .append("a.REMARK,a.DECT_PRICE,a.DECT_AMOUNT,a.PRD_TYPE ")
			 	   .append(" order by PRD_TYPE )temp ");
			  for (int i = 0; i < (newCount-dtlCount); i++) {
					 dtlSql.append(" union all select NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,").append(dtlCount+i+1).append(" from dual ");
				}
			  dtlSql.append(" ;");
			 Map<String,String> map=storeoutService.getSellInfo(STOREOUT_ID);
			 StringBuffer arg=new StringBuffer();
			 Object obj=map.get("PAYABLE_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 arg.append(" arg1=").append(obj.toString()).append(";")
			 	.append(" arg2=").append(userBean.getXM()).append(" ;");
			 obj=map.get("PAYED_TOTAL_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 arg.append(" arg3=").append(obj.toString()).append("; arg4=").append(gift_amount).append(" ;");
			 StringBuffer sql=new StringBuffer();
			 sql.append(storeOutSql.toString()).append(dtlSql.toString()).append(arg.toString());
			 System.out.println(sql.toString());
			 request.setAttribute("reportFileName",
			 "sporadicStoreOutPrint.raq");
			 **/
			 // 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			 // 报表路径名称
			 
			 return mapping.findForward("flashReportPrint");
	}
	/**
	 * 打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	 public ActionForward printInfo(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	UserBean userBean = ParamUtil.getUserBean(request);
		 	 String STOREOUT_ID = request.getParameter("STOREOUT_ID");
		 	//记录打印次数
		 	Map<String,String> printMap=new HashMap<String, String>();
		 	printMap.put("TABLE_NAME", "DRP_STOREOUT");
		 	printMap.put("BUSS_ID", STOREOUT_ID);
		 	if(!LogicUtil.txUpdatePrintNum(printMap)){
		 		throw new ServiceException("对不起，记录打印次数有误，请联系管理员 !");
		 	}
			 String TEL=storeoutService.getChannTel(userBean.getCHANN_ID());
			 double gift_amount=storeoutService.getDtlGiftAmount(STOREOUT_ID);
			 StringBuffer paramSql=new StringBuffer();
			 paramSql.append("storeOutPrint.raq&STOREOUT_ID=").append(STOREOUT_ID).append("&TEL=").append(TEL);
			 Map<String,String> map=storeoutService.getSellInfo(STOREOUT_ID);
			 Object obj=map.get("PAYABLE_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 paramSql.append("&arg1=").append(obj.toString())
			 	.append("&arg2=").append(userBean.getXM());
			 obj=map.get("PAYED_TOTAL_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 paramSql.append("&arg3=").append(obj.toString()).append("&arg4=").append(gift_amount);
			 /**
			 storeOutSql.append("rptSql=select ")
			 	   .append("a.STOREOUT_STORE_NAME,")
			 	   .append("to_char(a.DEAL_TIME,'yyyy-mm-dd') CRE_TIME,")
			 	   .append("a.STOREOUT_NO,")
			 	   .append("b.MOBILE,")
			 	   .append("b.ADDRESS,")
			 	   .append("a.REMARK,")
			 	   .append("advc.CRE_NAME,")
			 	   .append("a.CUST_NAME,")
			 	   .append("'").append(TEL).append("' CHANNTEL, ")
			 	   .append("a.TEL,")
			 	   .append("a.STOREOUT_AMOUNT,")
			 	   .append("a.RECV_ADDR, ")
			 	   .append("q.CONTRACT_NO,")
			 	   .append("advc.ADVC_ORDER_NO,")
			 	   .append("advc.TERM_NAME,")
			 	   .append("advc.SALE_PSON_NAME,")
			 	   .append("advc.RECEIPT_NO,advc.PROMOTE_NAME,")
			 	   .append("to_char(advc.SALE_DATE,'yyyy-mm-dd') SALE_DATE,")
			 	   .append("(select sum(d.DECT_AMOUNT) from DRP_STOREOUT_DTL d where a.STOREOUT_ID=d.STOREOUT_ID and d.DEL_FLAG=0)DECT_AMOUNT ")
			 	   .append(" from DRP_STOREOUT a")
			 	   .append(" left join BASE_CHANN b on a.SEND_CHANN_ID=b.CHANN_ID")
			 	   .append(" left join DRP_ADVC_SEND_REQ q on a.FROM_BILL_ID=q.ADVC_SEND_REQ_ID ")
			 	   .append(" left join DRP_ADVC_ORDER advc on q.FROM_BILL_ID=advc.ADVC_ORDER_ID ")
			 	   .append(" where a.STOREOUT_ID='").append(STOREOUT_ID).append("';");
			 int dtlCount=storeoutService.getDtlCount(STOREOUT_ID);
			 double page=0;
			 if(dtlCount%5!=0){
				 page=Math.ceil((double)dtlCount/5);
			 }
			 double newCount=page*5;
			 StringBuffer dtlSql=new StringBuffer();
			 dtlSql.append("dtlSql=select temp.*,rownum from (select ")
			 	   .append("a.PRD_NO,")
			 	   .append("a.PRD_NAME,")
			 	   .append("a.PRD_SPEC,")
			 	   .append("a.STD_UNIT,")
			 	   .append("sum(a.NOTICE_NUM)NOTICE_NUM,")
			 	   .append("(case when a.REMARK is not null then b.SPCL_DTL_REMARK || '(' || a.REMARK ||')' else b.SPCL_DTL_REMARK end) SPCLREMARK,")
			 	   .append("a.DECT_PRICE,")
			 	   .append("sum(a.DECT_AMOUNT)DECT_AMOUNT,")
			 	   .append("a.REMARK,a.PRD_TYPE ")
			 	   .append(" from DRP_STOREOUT_DTL a ")
			 	   .append(" left join DRP_SPCL_TECH b on a.SPCL_TECH_ID=b.SPCL_TECH_ID and b.USE_FLAG=")
			 	   .append(BusinessConsts.YJLBJ_FLAG_TRUE)
			 	   .append(" where a.STOREOUT_ID='")
			 	   .append(STOREOUT_ID)
			 	   .append("' and a.DEL_FLAG=0  ")
			 	   .append(" group by ")
			 	   .append("a.PRD_NO,")
			 	   .append("a.PRD_NAME,")
			 	   .append("a.PRD_SPEC,")
			 	   .append("a.STD_UNIT,")
			 	   .append("a.DECT_PRICE,")
			 	   .append("b.SPCL_DTL_REMARK,")
			 	   .append("a.REMARK,a.DECT_PRICE,a.DECT_AMOUNT,a.PRD_TYPE ")
			 	   .append(" order by PRD_TYPE )temp ");
			  for (int i = 0; i < (newCount-dtlCount); i++) {
					 dtlSql.append(" union all select NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,").append(dtlCount+i+1).append(" from dual ");
				}
			  dtlSql.append(" ;");
			 Map<String,String> map=storeoutService.getSellInfo(STOREOUT_ID);
			 StringBuffer arg=new StringBuffer();
			 Object obj=map.get("PAYABLE_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 arg.append(" arg1=").append(obj.toString()).append(";")
			 	.append(" arg2=").append(userBean.getXM()).append(" ;");
			 obj=map.get("PAYED_TOTAL_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 arg.append(" arg3=").append(obj.toString()).append("; arg4=").append(gift_amount).append(" ;");
			 StringBuffer sql=new StringBuffer();
			 sql.append(storeOutSql.toString()).append(dtlSql.toString()).append(arg.toString());
			 System.out.println(sql.toString());
			 // 报表路径名称
			 request.setAttribute("reportFileName",
			 "storeOutPrint.raq");
			 **/
			 // 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			 
			 return mapping.findForward("flashReportPrint");
	}
	 
	 /***
	  * 退货出库处理打印
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  * @return
	  */
	 public ActionForward reStoreOutPrint(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	UserBean userBean = ParamUtil.getUserBean(request);
		 	 String STOREOUT_ID = request.getParameter("STOREOUT_ID");
		 	//记录打印次数
		 	Map<String,String> printMap=new HashMap<String, String>();
		 	printMap.put("TABLE_NAME", "DRP_STOREOUT");
		 	printMap.put("BUSS_ID", STOREOUT_ID);
		 	if(!LogicUtil.txUpdatePrintNum(printMap)){
		 		throw new ServiceException("对不起，记录打印次数有误，请联系管理员 !");
		 	}
			 
			 String TEL=storeoutService.getChannTel(userBean.getCHANN_ID());
			 StringBuffer paramSql=new StringBuffer();
			 paramSql.append("reStoreOutPrint.raq&STOREOUT_ID=").append(STOREOUT_ID).append("&TEL=").append(TEL);
			 /**
			 StringBuffer storeOutSql = new StringBuffer();
			 double gift_amount=storeoutService.getDtlGiftAmount(STOREOUT_ID);
			 storeOutSql.append("rptSql=select ")
			 	   .append("  a.STOREOUT_ID,")
			 	   .append("  a.STOREOUT_NO,")
			 	   .append("  a.STOREOUT_STORE_NAME,")
			 	   .append("  a.SEND_CHANN_NAME,")
			 	   .append("  a.BILL_TYPE,")
			 	   .append("  a.CUST_NAME,")
			 	   .append("  a.STOREOUT_AMOUNT,")
			 	   .append("  a.TEL,")
			 	   .append("  a.RECV_ADDR,")
			 	   .append("  to_char(a.SALE_DATE,'yyyy-MM-DD') SALE_DATE,")
			 	   .append("  a.REMARK,")
			 	   .append("  a.FROM_BILL_NO,")
			 	   .append("'").append(TEL).append("' CHANNTEL ")
			 	   .append("  from DRP_STOREOUT a  ")
			 	   .append(" where a.STOREOUT_ID='").append(STOREOUT_ID).append("';");
			 int dtlCount=storeoutService.getDtlCount(STOREOUT_ID);
			 double page=0;
			 if(dtlCount%5!=0){
				 page=Math.ceil((double)dtlCount/5);
			 }
			 double newCount=page*5;
			 StringBuffer dtlSql=new StringBuffer();
			 dtlSql.append("dtlSql=select temp.*,rownum from (select ")
			 	   .append("a.PRD_NO,")
			 	   .append("a.PRD_NAME,")
			 	   .append("a.PRD_SPEC,")
			 	   .append("a.STD_UNIT,")
			 	   .append("a.BRAND,")
			 	   .append("a.PRD_COLOR,")
			 	   .append("b.PRD_MATL,")
			 	   .append("SUM(NVL(a.NOTICE_NUM, 0)) NOTICE_NUM,")
			 	   .append("SUM(NVL(a.REAL_NUM, 0)) REAL_NUM,")
			 	   .append("NVL((SUM(NVL(a.REAL_NUM, 0)) * a.PRICE),0) TOTAL_AMOUNT,")
			 	   .append("(case when a.REMARK is not null then b.SPCL_DTL_REMARK || '(' || a.REMARK ||')' else b.SPCL_DTL_REMARK end) SPCLREMARK,")
			 	   .append("a.DECT_PRICE,")
			 	   .append("sum(a.DECT_AMOUNT) DECT_AMOUNT,")
			 	   .append(" a.REMARK,a.PRD_TYPE, ")
			 	   .append("b.SPCL_SPEC_REMARK ")
			 	   .append(" from DRP_STOREOUT_DTL a ")
			 	   .append(" left join DRP_SPCL_TECH b on a.SPCL_TECH_ID=b.SPCL_TECH_ID and b.USE_FLAG=")
			 	   .append(BusinessConsts.YJLBJ_FLAG_TRUE)
			 	   .append(" where a.STOREOUT_ID='")
			 	   .append(STOREOUT_ID)
			 	   .append("' and a.DEL_FLAG=0  ")
			 	   .append(" group by ")
			 	   .append("a.PRD_NO,")
			 	   .append("a.PRD_COLOR,")
			 	   .append("a.PRD_NAME,")
			 	   .append("a.BRAND,")
			 	   .append("a.PRD_SPEC,")
			 	   .append("a.STD_UNIT,")
			 	   .append("a.DECT_PRICE,")
			 	   .append("b.SPCL_DTL_REMARK,")
			 	   .append("b.SPCL_SPEC_REMARK, ")
			 	   .append("a.REMARK,a.DECT_PRICE,a.DECT_AMOUNT,a.PRD_TYPE,b.PRD_MATL,a.PRICE")
			 	   .append(" order by PRD_TYPE )temp ");
//			  for (int i = 0; i < (newCount-dtlCount); i++) {
//					 dtlSql.append(" union all select NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,").append(dtlCount+i+1).append(" from dual ");
//				}
			  dtlSql.append(" ;");
			 Map<String,String> map=storeoutService.getSellInfo(STOREOUT_ID);
			 StringBuffer arg=new StringBuffer();
			 Object obj=map.get("PAYABLE_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 arg.append(" arg1=").append(obj.toString()).append(";")
			 	.append(" arg2=").append(userBean.getXM()).append(" ;");
			 obj=map.get("PAYED_TOTAL_AMOUNT");
			 if(null==obj){
				 obj="0";
			 }
			 arg.append(" arg3=").append(obj.toString()).append("; arg4=").append(gift_amount).append(" ;");
			 StringBuffer sql=new StringBuffer();
			 sql.append(storeOutSql.toString()).append(dtlSql.toString()).append(arg.toString());
			 System.out.println(sql.toString());
			 // 报表路径名称
			 request.setAttribute("reportFileName",
			 "reStoreOutPrint.raq");
			 **/
			 // 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			 
			 return mapping.findForward("flashReportPrint");
	}
	 
		public void cancleCommit(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			PrintWriter writer = getWriter(response);
			String jsonResult = jsonResult();
			try {
				String storeOutId = request.getParameter("STOREOUT_ID");
				boolean flg =  storeoutService.txCancelStoreOut(storeOutId);
			}catch (ServiceException s){
				jsonResult = jsonResult(false, s.getMessage());
			}catch (Exception e) {
				jsonResult = jsonResult(false, "提交失败");
			}
			if (null != writer) {
				writer.write(jsonResult);
				writer.close();
			}
		}
		
		
		/**
		 * 反出库处理
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 */
		public void returnStore(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			PrintWriter writer = getWriter(response);
			String jsonResult = jsonResult();
			try {
				Map<String,String> params = new HashMap<String,String>();
				params.put("STOREOUT_ID", ParamUtil.get(request, "STOREOUT_ID"));
				Map<String,Object> storeInfo=storeoutService.getStoreoutInfo(params.get("STOREOUT_ID"));
				if(1==StringUtil.getInteger(storeInfo.get("RECV_FLAG"))){
					throw new ServiceException("对不起，已出库确认的单据不能反出库");
				}
				boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),StringUtil.nullToSring(storeInfo.get("DEAL_TIME")));
				if(isMonthAcc){
					throw new ServiceException("出库处理日期:"+storeInfo.get("DEAL_TIME")+"已月结不能反出库");
				}
				//验证是否存在退货单，如果存在退货单，不能关闭
				boolean flag=storeoutService.checkReturnAdvc(params.get("STOREOUT_ID"));
				if(!flag){
					throw new ServiceException("对不起，您所选的出库单存在终端退货单，不能关闭!");
				}
				params.put("UPDATOR", userBean.getRYXXID());
				params.put("UPD_NAME", userBean.getXM());
				params.put("UPD_TIME", "sysdate");
				params.put("STATE", BusinessConsts.UNDONE);
				storeoutService.txReturnStore(params,userBean);
				jsonResult = jsonResult(true, "操作成功");
			}catch(ServiceException s){
				jsonResult = jsonResult(false, s.getMessage());
			} catch (Exception e) {
				jsonResult = jsonResult(false, "操作失败");
				e.printStackTrace();
			}
			if (null != writer) {
				writer.write(jsonResult);
				writer.close();
			}
		}
		
		/**
		 *修改备注
		 */	
		public void modifyRemark(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			PrintWriter writer = getWriter(response);
			String jsonResult = jsonResult();
			try {
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("STOREOUT_ID", ParamUtil.get(request, "STOREOUT_ID"));
				params.put("REMARK", ParamUtil.get(request, "REMARK"));
				params.put("ORDER_RECV_DATE", ParamUtil.get(request, "ORDER_RECV_DATE"));
//				params.put("STOREOUT_TIME", ParamUtil.get(request, "STOREOUT_TIME"));
				storeoutService.updateRemark(params);
				jsonResult = jsonResult(true, "操作成功");
			} catch (Exception e) {
				jsonResult = jsonResult(false, "操作失败");
				e.printStackTrace();
			}
			if (null != writer) {
				writer.write(jsonResult);
				writer.close();
			}
		}
		// 导出
		public void ExcelOutput(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			UserBean userBean = ParamUtil.getUserBean(request);
			Map<String, String> params = new HashMap<String, String>();
			// 只查询0的记录。1为删除。0为正常
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			String search = ParamUtil.get(request, "search");
			String module = ParamUtil.get(request, "module");
			String billType =ParamUtil.get(request, "BILL_TYPE");
//			String CHANN_TYPE=storeoutService.getCHANN_TYPE(userBean.getCHANN_ID());
//			if("区域服务中心".equals(CHANN_TYPE)&&"XSCK".equals(billType)){
//				billType="XXSCK";
//			}
			String strPVG_BWS = getBWSQXByType(billType);
			if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, strPVG_BWS)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			billType = convBillType(billType);
			setQueryData(request,billType, params,userBean);
			// 权限级别和审批流的封装
			StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module, strPVG_BWS,
					PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
					AUD_BUSS_STATE, userBean));
			StringBuffer sb = new StringBuffer();
			sb.append(StringUtil.getStrQX("u",strQx.toString()));
			
			if(StringUtil.isEmpty(search)){
				sb.append(" and u.STATE = '");
				sb.append(BusinessConsts.UNDONE);
				sb.append("' ");
			}
			params.put("QXJBCON",sb.toString());
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
			List list = storeoutService.downQuery(params);
			// excel数据上列显示的列明
			String tmpContentCn ="出库单编号,来源单据号,合同编号,发货方名称,出库库房名称,终端名称,要求到货日期,客户名称,电话,货品名称," +
								"规格型号,货品类型,花号,品牌,标准单位,特殊规格说明,通知出库数量,实际出库数量";
			String tmpContent = "STOREOUT_NO,FROM_BILL_NO,CONTRACT_NO,SEND_CHANN_NAME,STOREOUT_STORE_NAME,TERM_NAME,ORDER_RECV_DATE,CUST_NAME,TEL,PRD_NAME," +
								"PRD_SPEC,PRD_TYPE,PRD_COLOR,BRAND,STD_UNIT,SPCL_DTL_REMARK,NOTICE_NUM,REAL_NUM";
			String colType= "string,string,string,string,string,string,string,string,string,string," +
						   "string,string,string,string,string,string,number,number" ;
			try {
				ExcelUtil
						.doExport(response, list, "销售出库", tmpContent, tmpContentCn,colType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
}