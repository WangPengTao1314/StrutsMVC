package com.hoperun.sys.action;


import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.MessTimerTask;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.MessageModel;
import com.hoperun.sys.model.NoticeModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FirstPageService;

// TODO: Auto-generated Javadoc
/**
 * The Class FirstPageAction.
 */
public class FirstPageAction extends BaseAction {

	/** The logger. */
	private Logger logger = Logger.getLogger(FirstPageAction.class);

	/** The first page service. */
	private FirstPageService firstPageService;

	/**
	 * Gets the first page service.
	 * 
	 * @return the first page service
	 */
	public FirstPageService getFirstPageService() {
		return firstPageService;
	}

	/**
	 * Sets the first page service.
	 * 
	 * @param firstPage
	 *            the new first page service
	 */
	public void setFirstPageService(FirstPageService firstPage) {
		this.firstPageService = firstPage;
	}

	/**
	 * 短消息.
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
	 * @return the new short info
	 */

	public ActionForward getNewShortInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		if (userBean != null) {
			List<MessageModel> oldModelList = MessTimerTask.getReturnList();
			List<MessageModel> rntModel = new ArrayList<MessageModel>();

			if (null != oldModelList && oldModelList.size() > 0) {
				int size = oldModelList.size();
				String yhid = userBean.getXTYHID();
				for (int i = 0; i < size; i++) {
					MessageModel tmpModel = new MessageModel();
					MessageModel oldModel = oldModelList.get(i);
					String RECEIVEID = oldModel.getRECEIVEID();
					if (yhid.equals(RECEIVEID)) {
						tmpModel.setMESSAGEID(oldModel.getMESSAGEID());
						tmpModel.setCKZT(oldModel.getCKZT());
						tmpModel.setMSGINFO(oldModel.getMSGINFO());
						tmpModel.setSENDID(oldModel.getSENDID());
						tmpModel.setSENDER(oldModel.getSENDER());
						tmpModel.setSENDTIME(oldModel.getSENDTIME());
						tmpModel.setRECEIVEID(RECEIVEID);
						tmpModel.setRECEIVER(oldModel.getRECEIVER());
						rntModel.add(tmpModel);
					}
					if (rntModel.size() == 500) {// 每个人只能看前500条消息
						break;
					}
				}
				request.setAttribute("modelList", rntModel);
			} else {
				request.setAttribute("modelList", null);
			}
			request.setAttribute("XTYHZTXXID", userBean.getLoginZTXXID());
		}
		// 1分销
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		return mapping.findForward("message");
	}

	/**
	 * 根据短消息ID把消息插入已查看表中.
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
	public void txInsertCkztByDxxid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("txUpdateCkztByDxxid方法调用开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();

		String dxxid = request.getParameter("dxxid");
		Map<String, String> map = new HashMap();
		map.put("MSGTRACEID", StringUtil.uuid32len());
		map.put("MESSAGEID", dxxid);
		if ("1".equals(firstPageService.txInsertCkztByDxxid(map))) {
			jsonResult = jsonResult(true, "SUCCESS");
		} else {
			jsonResult = jsonResult(false, "查看数据出错！");
		}
		// 修改对象中消息的查看状态
		for (int i = 0; i < MessTimerTask.getReturnList().size(); i++) {
			if (dxxid.equals(MessTimerTask.getReturnList().get(i)
					.getMESSAGEID())) {
				MessTimerTask.getReturnList().get(i).setCKZT("1");
			}
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("txUpdateCkztByDxxid方法调用结束");
	}

	/**
	 * 将短消息插入表中.
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
	public void txInsertMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("txInsertMessage方法调用开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();

		String xtyhids = request.getParameter("xtyhids");
		String bmxxids = request.getParameter("bmxxids");
		String jgxxids = request.getParameter("jgxxids");
		String fsnr = request.getParameter("fsnr");
		xtyhids = "'" + xtyhids.replaceAll(",", "','") + "'";
		bmxxids = "'" + bmxxids.replaceAll(",", "','") + "'";
		jgxxids = "'" + jgxxids.replaceAll(",", "','") + "'";
		Map<String, String> map = new TreeMap();
		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");

		map.put("YHBH", userBean.getYHBH());
		map.put("SENDID", userBean.getXTYHID());
		map.put("SENDER", userBean.getYHM());
		map.put("MSGINFO", fsnr);
		map.put("XTYHID", xtyhids);
		map.put("BMXXID", bmxxids);
		map.put("JGXXID", jgxxids);

		if (firstPageService.txInsertMessage(map)) {
			jsonResult = jsonResult(true, "消息发送成功");
		} else {
			jsonResult = jsonResult(false, "消息发送失败！");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("txInsertMessage方法调用结束");
	}

	/**
	 * 将短消息插入表中 调用 var _xtyhids=""; var _bmxxids="000001"; var _jgxxids=""; var
	 * _jsxxids=""; var _fsnr="方法调用开始，测试审核发送短消息是否成功！"; $.ajax({ url:
	 * ctxPath+"/firstPage.shtml?action=txInsertMessageByAudit", type:"POST",
	 * dataType:"text",
	 * data:{xtyhids:_xtyhids,bmxxids:_bmxxids,jgxxids:_jgxxids,
	 * fsnr:_fsnr,jsxxids:_jsxxids}, complete: function(xhr){
	 * eval("json = "+xhr.responseText); if(json.success==true){
	 * parent.showErrorMsg(utf8Decode(json.messages)); }else{
	 * parent.showErrorMsg(utf8Decode(json.messages)); } } });
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
	public void txInsertMessageByAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("txInsertMessageByAudit方法调用开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();

		String xtyhids = request.getParameter("xtyhids");
		String bmxxids = request.getParameter("bmxxids");
		String jgxxids = request.getParameter("jgxxids");
		String jsxxids = request.getParameter("jsxxids");
		String fsnr = request.getParameter("fsnr");
		xtyhids = "'" + xtyhids.replaceAll(",", "','") + "'";
		bmxxids = "'" + bmxxids.replaceAll(",", "','") + "'";
		jgxxids = "'" + jgxxids.replaceAll(",", "','") + "'";
		jsxxids = "'" + jsxxids.replaceAll(",", "','") + "'";
		Map<String, String> map = new TreeMap();
		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");

		map.put("YHBH", userBean.getYHBH());
		map.put("SENDID", userBean.getXTYHID());
		map.put("SENDER", userBean.getYHM());
		map.put("MSGINFO", fsnr);
		map.put("XTYHID", xtyhids);
		map.put("BMXXID", bmxxids);
		map.put("JGXXID", jgxxids);
		map.put("XTJSID", jsxxids);

		if (firstPageService.txInsertMessageByAudit(map)) {
			jsonResult = jsonResult(true, "消息发送成功");
		} else {
			jsonResult = jsonResult(false, "消息发送失败！");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("txInsertMessageByAudit方法调用结束");
	}

	/**
	 * 首页.
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
	public ActionForward toFirst(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		String firstPage = null;
		if (!StringUtil.isEmpty(userBean.getPORTAL_URL())) {
			firstPage = userBean.getPORTAL_URL();
		} else {
			if ("1".equals(userBean.getIS_DRP_LEDGER())) {
				// 分销
				firstPage = "toDrpFirst";
			} else {
				// 总部
				firstPage = "toErpFirst";
			}
		}
		this.queryNotice(userBean, request);
		this.queryStore(userBean, request);
		
		
		
		// 待办事项，如确定不用去掉
		if (userBean != null) {
			List<Map<String, String>> resList= LogicUtil.getAgencyWorkSql( userBean);
			/**
			String sql = QXUtil.getAudList(userBean);
			List<Map<String, String>> list = firstPageService
					.queryDbsyByUser(sql);
			List<Map<String, String>> resList = new ArrayList();
			// 进行数据过滤 add by zhuxw
			int Num = list.size();
			String IDS = "'1<>1'";
			for (int i = 0; i < Num; i++) {

				Map<String, String> temMap = (Map) list.get(i);
				if (i != Num - 1) {
					Map<String, String> nextMap = (Map) list.get(i + 1);
					String curCORRELATION = temMap.get("CORRELATION")
							.toString();
					String CORRELATION = nextMap.get("CORRELATION").toString();
					if (!curCORRELATION.equals(CORRELATION)) {
						IDS = IDS + ",'"
								+ temMap.get("CORRELATIONID").toString() + "'";
						StringBuffer tempsql = new StringBuffer();
						tempsql.append(" select count(1) NUM from ");
						tempsql.append(curCORRELATION);
						tempsql.append(" u  where ");
						tempsql.append(temMap.get("PRIMARYKEY"));
						tempsql.append(" in (");
						tempsql.append(IDS);
						tempsql.append(")");
						if (temMap.get("MENU_QXCODE") != null) {
							tempsql.append(" and "
									+ QXUtil.getQXTJ(userBean, temMap.get(
											"MENU_QXCODE").toString()));
						}
						Map resMap = firstPageService.getFilterData(tempsql
								.toString());
						String tempNUM = resMap.get("NUM").toString();
						temMap.put("NUM", tempNUM);
						if (!"0".equals(tempNUM))
							resList.add(temMap);
						IDS = "'1<>1'";
					} else {
						IDS = IDS + ",'"
								+ temMap.get("CORRELATIONID").toString() + "'";

					}
				} else {
					String CORRELATION = temMap.get("CORRELATION").toString();
					IDS = IDS + ",'" + temMap.get("CORRELATIONID").toString()
							+ "'";
					String prefixcondition = "";
					StringBuffer tempsql = new StringBuffer();
					tempsql.append(" select count(1) NUM from ");
					tempsql.append(CORRELATION);
					tempsql.append(" where ");
					tempsql.append(temMap.get("PRIMARYKEY"));
					tempsql.append(" in (");
					tempsql.append(IDS);
					tempsql.append(")");
					if (temMap.get("MENU_QXCODE") != null) {
						prefixcondition = QXUtil.getQXTJ(userBean, temMap.get(
								"MENU_QXCODE").toString());
						tempsql.append(" and "
								+ prefixcondition.replaceAll("u.", ""));
					}
					Map resMap = firstPageService.getFilterData(tempsql
							.toString());
					String tempNUM = resMap.get("NUM").toString();
					temMap.put("NUM", tempNUM);
					if (!"0".equals(tempNUM))
						resList.add(temMap);
				}
			}
			**/

			request.setAttribute("list", resList);
		}
		return mapping.findForward(firstPage);
	}

	/**
	 * 查询公告
	 * 
	 * @param userBean
	 * @param request
	 */
	public void queryNotice(UserBean userBean, HttpServletRequest request) {
		String RYXXID = userBean.getRYXXID();  //获取当前登录的人员信息ID
	    List<NoticeModel> noticers  = this.firstPageService.queryNoticers(RYXXID);
	    int count = noticers.size();
	    List<NoticeModel> noticeList = null;
	    if(count == 0){
	            noticeList = this.firstPageService.queryNoticeList(
				userBean, 7);
	    }else{
	    	    noticeList = this.firstPageService.queryAllNotices(userBean, 7);
	    }
		if (null != noticeList && noticeList.size() > 0) {
			request.setAttribute("firstNotice", noticeList.get(0));
		}
		request.setAttribute("noticeList", noticeList);
	}

	/**
	 * 查询库存预警
	 * 
	 * @param userBean
	 * @param request
	 */
	public void queryStore(UserBean userBean, HttpServletRequest request) {
		List storeList = this.firstPageService.queryStore(userBean);
		request.setAttribute("storeList", storeList);
	}

	/**
	 * 查询用户未查看的消息.
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
	public void queryNotLookMess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("queryNotLookMess方法调用开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();

		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		boolean lookFlag = false;
		List<MessageModel> model = MessTimerTask.getReturnList();

		if (model != null && !model.isEmpty()) {
			int total = model.size();
			int haveSize = 0;
			if (total > 0) {
				String yhid = userBean.getXTYHID();
				for (int i = 0; i < total; i++) {
					if (yhid.equals(model.get(i).getRECEIVEID())
							&& "0".equals(model.get(i).getCKZT())) {
						haveSize++;
						lookFlag = true;
						// break;
					}
				}
				if (lookFlag) {
					// jsonResult = jsonResult(true, "有未查看消息");
					// modify by zzb 2014-7-14 添加未读消息数目
					jsonResult = jsonResult(true, "" + haveSize);
				} else {
					jsonResult = jsonResult(false, "没有未查看消息");
				}
			}
		} else {
			jsonResult = jsonResult(false, "没有消息");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("queryNotLookMess方法调用结束");
	}

	/**
	 * 首页报表：总部出库查询(按月份)
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
	public void queryBaseSaleAmountBymonth(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		Date date = new Date();

		response.setDateHeader("Expires", date.getTime());

		logger.info("queryBaseSaleAmountBymonth方法调用开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		String rptMonths = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		rptMonths = StringUtil.joinConIn(rptMonths, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("MONTH", rptMonths);
		try {
			List<Map> rst = firstPageService.qrySaleAmountByMonth(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("queryBaseSaleAmountBymonth方法调用结束");
	}

	public void columnChartDefAccordingToCategoryHeadquatersShippingAmount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("columnChartDefAccordingToCategoryHeadquatersShippingAmount方法调用开始");

		Date date = new Date();

		response.setDateHeader("Expires", date.getTime());

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		String rptMonths = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		rptMonths = StringUtil.joinConIn(rptMonths, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("MONTH", rptMonths);
		try {
			List<Map> rst = firstPageService.columnChartDefAccordingToCategoryHeadquatersShippingAmount(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("columnChartDefAccordingToCategoryHeadquatersShippingAmount方法调用结束");
	}

	// 销售出库 按战区 维度月份
	public void qrySaleAmountByWarZoneMonth(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("qrySaleAmountByWarZoneMonth方法调用开始");

		Date date = new Date();

		response.setDateHeader("Expires", date.getTime());

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		String rptMonths = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		rptMonths = StringUtil.joinConIn(rptMonths, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("MONTH", rptMonths);
		try {
			List<Map> rst = firstPageService.qrySaleAmountByWarZoneMonth(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("qrySaleAmountByWarZoneMonth方法调用结束");
	}

	/** 
	 * 线下加盟商出库  按战区 维度季度
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void qrySaleAmountByWarZoneQuarter(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("qrySaleAmountByWarZoneQuarter方法调用开始");
		Date date = new Date();
		response.setDateHeader("Expires", date.getTime());
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request.getParameter("RPT_YEAR"));
		String rptQuarter = StringUtil.nullToSring(request .getParameter("RPT_QUARTER"));
		if(rptQuarter.indexOf("0")>-1){
			rptQuarter = "1";
		}
		 
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptQuarter)) {
			jsonResult = jsonResult(false, "对不起！请选择季节！");
		}
		rptQuarter = StringUtil.joinConIn(rptQuarter, ",");
		String args[] = rptQuarter.split(",");
		StringBuffer columns = new StringBuffer("");
		for(int i=0;i<args.length;i++){
			if(columns.length()>0){
				columns.append("+");
			}
			if("1".equals(args[i]) || "'1'".equals(args[i])){
				columns.append("NVL(FIRST_QUARTER_AMOUNT,0)");//一季度
			}
			if("2".equals(args[i]) || "'2'".equals(args[i])){
				columns.append("NVL(SECOND_QUARTER_AMOUNT,0)");//季度
			}
			if("3".equals(args[i]) || "'3'".equals(args[i])){
				columns.append("NVL(THIRD_QUARTER_AMOUNT,0)");//三季度
			}
			if("4".equals(args[i]) || "'4'".equals(args[i])){
				columns.append("NVL(FOURTH_QUARTER_AMOUNT,0)");//四季度
			}
		}
		if(columns.length() == 0){
			columns.append("NVL(FIRST_QUARTER_AMOUNT,0)");
		}
		String sumColumns = "sum("+columns.toString()+")";
		Map<String, String> map = new HashMap<String, String>();
		map.put("YEAR", rptYear);
		map.put("RPT_QUARTER", rptQuarter);
		map.put("sumColumns",sumColumns);
		
		try {
			List<Map> rst = firstPageService.qrySaleAmountByWarZoneQuarter(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("qrySaleAmountByWarZoneQuarter方法调用结束");
	}

	// 渠道部（当前有效门店数） 有效门店发展汇总 按总部
	public void effectiveStoreChannelDeptsSummarization(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("effectiveStoreChannelDeptsSummarization方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		// String rptMonths =
		// StringUtil.nullToSring(request.getParameter("RPT_MONTH"));
	
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}

		
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		try {
			List<Map> rst = firstPageService
					.effectiveStoreChannelDeptsSummarization(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("effectiveStoreChannelDeptsSummarization方法调用结束");
	}

	// 渠道部（当前有效门店数） 各战区门店发展汇总 按战区
	public void effectiveStoreChannelDeptsSummarizationByWarZone(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("effectiveStoreChannelDeptsSummarizationByWarZone方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		
		
		String rptMonth = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptMonth)) {
			jsonResult = jsonResult(false, "对不起！请选择月份！");
		}
		
		
		String rpt_year_month = rptYear+rptMonth;
		
		rptMonth = StringUtil.joinConIn(rptMonth, ",");
		
		Map<String, String> map = new HashMap();
		
		map.put("YEAR", rptYear);
		
		map.put("RPT_MONTH", rptMonth);
		
		map.put("RPT_YEAR_MONTH",rpt_year_month);
		
		
		try {
			List<Map> rst = firstPageService
					.effectiveStoreChannelDeptsSummarizationByWarZone(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("effectiveStoreChannelDeptsSummarizationByWarZone方法调用结束");
	}

	// 总部订货金额（货品分类） 总部维度
	public void headquartersOrderAmountClassificationOfGoods(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("headquartersOrderAmountClassificationOfGoods方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		// String rptMonths =
		// StringUtil.nullToSring(request.getParameter("RPT_MONTH"));
		String rptQuarter = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptQuarter)) {
			jsonResult = jsonResult(false, "对不起！请选择季节！");
		}
		rptQuarter = StringUtil.joinConIn(rptQuarter, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("RPT_MONTH", rptQuarter);
		try {
			List<Map> rst = firstPageService
					.headquartersOrderAmountClassificationOfGoods(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("headquartersOrderAmountClassificationOfGoods方法调用结束");
	}

	// 总部订货金额（货品分类） 战区维度 按季度
	public void headquartersOrderAmountByWarDimensionByQuarter(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("headquartersOrderAmountByWarDimensionByQuarter方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		// String rptMonths =
		// StringUtil.nullToSring(request.getParameter("RPT_MONTH"));
		String rptQuarter = StringUtil.nullToSring(request
				.getParameter("RPT_QUARTER"));
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptQuarter)) {
			jsonResult = jsonResult(false, "对不起！请选择季节！");
		}
		rptQuarter = StringUtil.joinConIn(rptQuarter, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("RPT_QUARTER", rptQuarter);
		try {
			List<Map> rst = firstPageService
					.headquartersOrderAmountByWarDimensionByQuarter(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("headquartersOrderAmountByWarDimensionByQuarter方法调用结束");
	}

	// 总部订货金额（货品分类） 战区维度
	public void headquartersOrderAmountByWarDimension(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("headquartersOrderAmountByWarDimension方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		// String rptMonths =
		// StringUtil.nullToSring(request.getParameter("RPT_MONTH"));
		String rptQuarter = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptQuarter)) {
			jsonResult = jsonResult(false, "对不起！请选择季节！");
		}
		rptQuarter = StringUtil.joinConIn(rptQuarter, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("RPT_MONTH", rptQuarter);
		try {
			List<Map> rst = firstPageService
					.headquartersOrderAmountByWarDimension(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("headquartersOrderAmountByWarDimension方法调用结束");
	}

	// 总部订货金额 总部维度
	public void headquartersOrderAmountByHeaderQuaterDimension(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("headquartersOrderAmountByHeaderQuaterDimension方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		// String rptMonths =
		// StringUtil.nullToSring(request.getParameter("RPT_MONTH"));
		String rptQuarter = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptQuarter)) {
			jsonResult = jsonResult(false, "对不起！请选择季节！");
		}
		rptQuarter = StringUtil.joinConIn(rptQuarter, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("RPT_MONTH", rptQuarter);
		try {
			List<Map> rst = firstPageService
					.headquartersOrderAmountByHeaderQuaterDimension(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("headquartersOrderAmountByHeaderQuaterDimension方法调用结束");
	}

	// 直营销售出库金额汇总
	public void directsalestoreTheSalesAmountOfSummary(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("directsalestoreTheSalesAmountOfSummary方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		// String rptMonths =
		// StringUtil.nullToSring(request.getParameter("RPT_MONTH"));
		String rptQuarter = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptQuarter)) {
			jsonResult = jsonResult(false, "对不起！请选择季节！");
		}
		rptQuarter = StringUtil.joinConIn(rptQuarter, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("RPT_MONTH", rptQuarter);
		
		
		
		try {
			List<Map> rst = firstPageService
					.directsalestoreTheSalesAmountOfSummary(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("directsalestoreTheSalesAmountOfSummary方法调用结束");
	}

	// 渠道（含直营办） 杭州直营办销售出库金额
	public void channelAndDirectSaleStoreSalesAmountOfOutbound(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("channelAndDirectSaleStoreSalesAmountOfOutbound方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		// String rptMonths =
		// StringUtil.nullToSring(request.getParameter("RPT_MONTH"));
		String rptMonths = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		
		String paramrowNum = StringUtil.nullToSring(request
				.getParameter("PARAMROWNUM"));
		
		
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptMonths)) {
			jsonResult = jsonResult(false, "对不起！请选择月份！");
		}
		
		if (StringUtil.isEmpty(paramrowNum)) {
			jsonResult = jsonResult(false, "对不起！请选择paramrowNum！");
		}
		
		
		
		rptMonths = StringUtil.joinConIn(rptMonths, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("RPT_MONTH", rptMonths);
		map.put("PARAMROWNUM", paramrowNum);
		
		

		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		if (userBean != null) {
			map.put("LEDGER_ID", userBean.getLoginZTXXID());
		}

		try {
			List<Map> rst = firstPageService
					.channelAndDirectSaleStoreSalesAmountOfOutbound(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("channelAndDirectSaleStoreSalesAmountOfOutbound方法调用结束");
	}

	// 渠道（含直营办） 杭州直营办销售出库TOP5 根据门店
	public void channelAndDirectSaleStoreSalesOutboundTop5ByStore(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("channelAndDirectSaleStoreSalesOutboundTop5ByStore方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		 String rptMonths =
		 StringUtil.nullToSring(request.getParameter("RPT_MONTH"));
		
		String paramrowNum = StringUtil.nullToSring(request
				.getParameter("PARAMROWNUM"));
		
		
		
		
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptMonths)) {
			jsonResult = jsonResult(false, "对不起！请选择月份！");
		}
		
		if (StringUtil.isEmpty(paramrowNum)) {
			jsonResult = jsonResult(false, "对不起！请选择paramrowNum！");
		}
		
		rptMonths = StringUtil.joinConIn(rptMonths, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("RPT_MONTH", rptMonths);
		map.put("PARAMROWNUM", paramrowNum);

		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		if (userBean != null) {
			map.put("LEDGER_ID", userBean.getLoginZTXXID());
		}

		try {
			List<Map> rst = firstPageService
					.channelAndDirectSaleStoreSalesOutboundTop5ByStore(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("channelAndDirectSaleStoreSalesOutboundTop5ByStore方法调用结束");
	}

	// 渠道（含直营办） 杭州直营办销售出库TOP5 根据货品
	public void channelAndDirectSaleStoreSalesOutboundTop5ByGoods(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("channelAndDirectSaleStoreSalesOutboundTop5ByGoods方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		// String rptMonths =
		// StringUtil.nullToSring(request.getParameter("RPT_MONTH"));
		String rptMonths = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		
		String paramrowNum = StringUtil.nullToSring(request
				.getParameter("PARAMROWNUM"));
		
		
		
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptMonths)) {
			jsonResult = jsonResult(false, "对不起！请选择月份！");
		}
		
		
		if (StringUtil.isEmpty(paramrowNum)) {
			jsonResult = jsonResult(false, "对不起！请选择paramrowNum！");
		}
		
		
		rptMonths = StringUtil.joinConIn(rptMonths, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("RPT_MONTH", rptMonths);
		map.put("PARAMROWNUM", paramrowNum);

		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		if (userBean != null) {
			map.put("LEDGER_ID", userBean.getLoginZTXXID());
		}

		try {
			List<Map> rst = firstPageService
					.channelAndDirectSaleStoreSalesOutboundTop5ByGoods(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("channelAndDirectSaleStoreSalesOutboundTop5ByGoods方法调用结束");
	}
	
	
	
	// 
	public void directsaleStoreDevelopmentTheSalesAmountOfSummary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("directsaleStoreDevelopmentTheSalesAmountOfSummary方法调用开始");

		Date date = new Date();

		response.setDateHeader("Expires", date.getTime());

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		String rptMonths = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		rptMonths = StringUtil.joinConIn(rptMonths, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("MONTH", rptMonths);
		try {
			List<Map> rst = firstPageService.directsaleStoreDevelopmentTheSalesAmountOfSummary(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("directsaleStoreDevelopmentTheSalesAmountOfSummary方法调用结束");
	}

	// 渠道（含直营办） 杭州直营办销售出库TOP5 根据 业务员 导购
	public void channelAndDirectSaleStoreSalesOutboundTop5BySalesman(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		logger
				.info("channelAndDirectSaleStoreSalesOutboundTop5BySalesman方法调用开始");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String rptYear = StringUtil.nullToSring(request
				.getParameter("RPT_YEAR"));
		// String rptMonths =
		// StringUtil.nullToSring(request.getParameter("RPT_MONTH"));
		String rptQuarter = StringUtil.nullToSring(request
				.getParameter("RPT_MONTH"));
		
		String paramrowNum = StringUtil.nullToSring(request
				.getParameter("PARAMROWNUM"));
		
		if (StringUtil.isEmpty(rptYear)) {
			jsonResult = jsonResult(false, "对不起！请选择年份！");
		}
		if (StringUtil.isEmpty(rptQuarter)) {
			jsonResult = jsonResult(false, "对不起！请选择季节！");
		}
		
		
		
		if (StringUtil.isEmpty(paramrowNum)) {
			jsonResult = jsonResult(false, "对不起！请选择paramrowNum！");
		}

		rptQuarter = StringUtil.joinConIn(rptQuarter, ",");
		Map<String, String> map = new HashMap();
		map.put("YEAR", rptYear);
		map.put("RPT_MONTH", rptQuarter);
		map.put("PARAMROWNUM", paramrowNum);

		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		if (userBean != null) {
			map.put("LEDGER_ID", userBean.getLoginZTXXID());
		}

		try {
			List<Map> rst = firstPageService
					.channelAndDirectSaleStoreSalesOutboundTop5BySalesman(map);
			if (rst == null) {
				rst = new ArrayList<Map>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger
				.info("channelAndDirectSaleStoreSalesOutboundTop5BySalesman方法调用结束");
	}
	
	/**
	 * 获得当前年份，月份
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void currentYearAndMonth(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request,HttpServletResponse response) {
		int currentYear = 0;
		Calendar c = Calendar.getInstance();// 获得系统当前日期
		currentYear = c.get(Calendar.YEAR);
		//currentMonth = c.get(Calendar.MONTH) + 1;// 系统日期从0开始算起
		String currentMonth = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		java.util.Date dd  = Calendar.getInstance().getTime();
		currentMonth = sdf.format(dd); 
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("currentYear",currentYear);//当前年
		param.put("theYearBeforeLast",currentYear-1);//前一年
		param.put("threeYearsAgo",currentYear-2);
		param.put("currentMonth",currentMonth);
		String jsonResult = jsonResult();
		
		jsonResult = new Gson().toJson(new Result(true, param, "保存成功"));
		PrintWriter writer = getWriter(response);
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		
	}
 
	
	
	/**
	 * 总部订货金额前30 
	 * 区域维度 按季度
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void queryAreaTopByQuarter( ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("queryAreaTopByQuarter方法调用开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String rptYear = StringUtil.nullToSring(request.getParameter("RPT_YEAR"));
			String rptQuarter = StringUtil.nullToSring(request.getParameter("RPT_QUARTER"));//季度
			String RPT_RAKING = StringUtil.nullToSring(request.getParameter("RPT_RAKING"));//排名
			String RPT_MONTH = StringUtil.nullToSring(request.getParameter("RPT_MONTH"));//月份
			String RPT_FLAG = StringUtil.nullToSring(request.getParameter("RPT_FLAG"));//月份标志
			if(StringUtil.isEmpty(RPT_RAKING)){
				RPT_RAKING = "10";
			}
			if(StringUtil.isEmpty(rptYear)) {
				throw new ServiceException("对不起！请选择年份！");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			if("0".equals(RPT_FLAG)){
				if(StringUtil.isEmpty(RPT_MONTH)) {
					throw new ServiceException("对不起！请选择月份！");
				}
				RPT_MONTH = StringUtil.joinConIn(RPT_MONTH, ",");
				map.put("COLUMN","temp.S_MM");
				map.put("RPT_QUARTER", RPT_MONTH);
				map.put("G_CLUMN", "S_MM");
			}else{
				if(StringUtil.isEmpty(rptQuarter)) {
					throw new ServiceException("对不起！请选择季节！");
				}
				rptQuarter = StringUtil.joinConIn(rptQuarter, ",");
				map.put("RPT_QUARTER", rptQuarter);
				map.put("COLUMN","temp.S_QUARTER");
				map.put("G_CLUMN", "S_QUARTER");
			}
			map.put("YEAR", rptYear);
			map.put("RAKING", RPT_RAKING);
			List<Map<String,String>> rst = firstPageService.queryAreaTopByQuarter(map);
			if (rst == null) {
				rst = new ArrayList<Map<String,String>>();
			}
			jsonResult = jsonResult(true, new Gson().toJson(rst));
			System.out.println(jsonResult);
		}catch(ServiceException e){
			jsonResult = jsonResult(false, e.getMessage());
		}catch (Exception e) {
			jsonResult = jsonResult(false, "出错!" + e.getMessage());
			e.printStackTrace();
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("queryAreaTopByQuarter方法调用结束");
	}
	

}


