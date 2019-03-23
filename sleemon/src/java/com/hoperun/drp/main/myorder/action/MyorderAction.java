package com.hoperun.drp.main.myorder.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.main.myorder.model.MyorderModel;
import com.hoperun.drp.main.myorder.service.MyorderService;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.DictService;

/**
 * 分销首页action
 * 
 * @author zhang_zhongbin
 * 
 */
public class MyorderAction extends BaseAction {

	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0020401";
	private static String PVG_EDIT = "FX0020402";
	private static String PVG_DELETE = "FX0020403";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
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
	private static String AUD_TAB_NAME = "";
	private static String AUD_TAB_KEY = "";
	private static String AUD_BUSS_STATE = "";
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	//是否显示赠品按钮
	private static String PVG_BWS_GIFT = "FX0020406";
	
	private MyorderService myorderService;

	/**
	 * @return the myorderService
	 */
	public MyorderService getMyorderService() {
		return myorderService;
	}

	/**
	 * @param myorderService
	 *            the myorderService to set
	 */
	public void setMyorderService(MyorderService myorderService) {
		this.myorderService = myorderService;
	}

	/**
	 * 用于获取XML文件配置 引用于DicSelectAction类
	 */
	private DictService dictService;

	/**
	 * @return the dictService
	 */
	public DictService getDictService() {
		return dictService;
	}

	/**
	 * @param dictService
	 *            the dictService to set
	 */
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}

	/**
	 * 
	 * 下帧显示货品信息页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("brand", ParamUtil.utf8Decoder(request, "brand"));
		map.put("prmt", ParamUtil.utf8Decoder(request, "prmt"));
		map.put("spec", ParamUtil.utf8Decoder(request, "spec"));
		map.put("type", ParamUtil.utf8Decoder(request, "type"));
		map.put("prd_Info", ParamUtil.utf8Decoder(request, "prd_Info"));
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		map.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		map.put("STATE", BusinessConsts.JC_STATE_ENABLE);
		map.put("CHANN_ID", userBean.getCHANN_ID());
		// -- 0156143--Start--刘曰刚--2014-06-16//
		// 获取渠道折扣率
		// String
		// DECT_RATE=myorderService.getChannDiscount(userBean.getCHANN_ID());
		// 修改渠道折扣率获取方式
		String DECT_RATE = LogicUtil.getChannDsct(userBean.getCHANN_ID());
		if (StringUtil.isEmpty(DECT_RATE)) {
			return mapping.findForward("error");
		}
		map.put("DECT_RATE", DECT_RATE);
		request.setAttribute("DECT_RATE", DECT_RATE);
		// -- 0156143--End--刘曰刚--2014-06-16//
		// --0156379-- Start--刘曰刚--2014-06-17//
		// 查询货品时增加渠道货品过滤
		StringBuffer sql = new StringBuffer();
		sql.append(" and  a.COMM_FLAG=").append(BusinessConsts.YJLBJ_FLAG_TRUE);
		String lassQuery = ParamUtil.utf8Decoder(request, "lassQuery");
		if ("true".equals(lassQuery)) {
			sql.append(" and IS_CAN_FREE_FLAG=").append(BusinessConsts.YJLBJ_FLAG_TRUE);
		}
		map.put("sql", sql.toString());
		String rebateQuery = ParamUtil.utf8Decoder(request, "rebateQuery");//返利标记
//		StringBuffer REBATE_PRD_NOS = new StringBuffer();
		if ("true".equals(rebateQuery)) {
//			String[] REBATE_PRD_NUMBER = REBATE_PRD_NUMBERS.split(",");
//			for (int i = 0; i < REBATE_PRD_NUMBER.length; i++) {
//				REBATE_PRD_NOS.append("'").append(REBATE_PRD_NUMBER[i]).append(
//						"',");
//			}
			map.put("REBATE_PRD_NO", " a.IS_REBATE_FLAG=1 ");
		}
//		String REBATE_PRD_NO = REBATE_PRD_NOS.toString();
//		if (REBATE_PRD_NO.length() > 0) {
//			REBATE_PRD_NO = REBATE_PRD_NO.substring(0,
//					REBATE_PRD_NO.length() - 1);
//		}
//		map.put("REBATE_PRD_NO", REBATE_PRD_NO);
		int pageSize = ParamUtil.getInt(request, "pageSize", 0);
		ParamUtil.setOrderField(request, map, "a.PRD_NO", "ASC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", map);
		// 每页显示条数列表
		map.put("pageSizeList", "7,20");
		IListPage page = myorderService.pageQuery(map, pageNo);

		// 图片服务器地址
		String picture_url = LogicUtil.getPicServerURL(userBean
				.getCURRT_PIC_URL(), "");
		String html = getHtml(map, pageNo, pageSize);
		request.setAttribute("picture_url", picture_url);
		request.setAttribute("html", html);
		request.setAttribute("params", map);
		request.setAttribute("page", page);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
		return mapping.findForward("list");

	}

	/**
	 * 上帧显示查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toSelPrd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("STATE", BusinessConsts.JC_STATE_ENABLE);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 数据库查询品牌表获得所有启用并且为未删除状态的品牌名称
		List<String> BRANDS = myorderService.findBRANDAll(map);
		// 数据查询货品表里的所有货品分类
		List<String> PRD_TYPES = myorderService.findType();
		// 数据库查找当前时间的所有促销信息
		map.put("STATE", BusinessConsts.ISSUANCE);// 已发布

		map.put("CHANN_ID", userBean.getCHANN_ID());
		List<String> PRMT_PLANS = myorderService.findPRMT_PLANAll(map);

		// 获取常量类里的货品标准规格型号数组转换为list-------货品标准规格型号获取方式改变，从货品表里取值
		// List<String> PRD_SPECS=Arrays.asList(BusinessConsts.PRODUCT_SPEC);
		// 设置要获取的XML配置文件的id查找货品分类
		// List<String> PRD_TYPES=new ArrayList<String>();
		// String dictId = "172";
		// String selCommHead = "System"; // 默认为物流的配置文件
		// if (dictId.indexOf("_") != -1) {
		// selCommHead = dictId.substring(0, dictId.indexOf("_"));
		// } else {
		// dictId = selCommHead + "_" + dictId;
		// }
		// String fileName = this.servlet.getServletContext().getRealPath("/") +
		// File.separator + "pages" + File.separator + "sys" + File.separator +
		// "configfiles"
		// + File.separator + "dictionary" + File.separator + "dictConfig_" +
		// selCommHead + ".xml";
		// List <?> dicList = ContextServlet.getDicList(fileName +
		// dictId.substring(dictId.indexOf("_")));
		// if (null == dicList) {
		// dicList = dictService.getDictList(fileName,
		// dictId.substring(selCommHead.length() + 1),ParamUtil.get(request,
		// "condition"));
		// }
		// try {
		// int listCount = dicList.size();
		// for (int i = 0; i < listCount; i++) {
		// DictInfoBean dictBean = (DictInfoBean) dicList.get(i);
		// PRD_TYPES.add(dictBean.getDiccnname());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		request.setAttribute("BRANDS", BRANDS);
		request.setAttribute("PRMT_PLANS", PRMT_PLANS);
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("typeList", PRD_TYPES);
		// request.setAttribute("PRD_TYPES", PRD_TYPES);

		// add by zzb 2014-7-26
		String AREA_SER_ID = userBean.getAREA_SER_ID(); // 区域服务中心ID
		if (!StringUtil.isEmpty(AREA_SER_ID)
				&& !"true".equals(Consts.IS_OLD_FLOW)) {
			// 走区域服务中心
			request.setAttribute("havaAreaSerId", 0);
			request.setAttribute("AREA_SER_ID", AREA_SER_ID);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("selPrd");
	}

	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String jsonDate = ParamUtil.get(request, "jsonData");
			List<MyorderModel> ModelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				ModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<MyorderModel>>() {
						}.getType());
			}
			myorderService.txEdit(ModelList, userBean);
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
		pvgMap.put("PVG_BWS_GIFT", QXUtil.checkPvg(userBean, PVG_BWS_GIFT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}

	// 点击图片弹出窗口显示大图片
	public ActionForward toPicture(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("fileName");
		request.setAttribute("fileName", fileName);
		return mapping.findForward("picture");
	}

	/**
	 * 页脚
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public String getHtml(Map<String, String> map, int pageNo, int pageSize) {
		String pageSizeList = map.get("pageSizeList");
		long totalCount = myorderService.getCount(map);
		if (pageSize == 0) {
			pageSize = Consts.PAGE_SIZE;
		}
		int start = (pageNo - 1) * pageSize;
		long curPageNo = start / pageSize + 1;
		long totalPageCount = 0;
		if (totalPageCount == 0) {
			if (totalCount % pageSize == 0)
				totalPageCount = totalCount / pageSize;
			else
				totalPageCount = totalCount / pageSize + 1;
		}
		String html = getToolbarHtml(curPageNo, totalPageCount, totalCount,
				pageSize,pageSizeList);
		return html;
	}

	/**
	 * 翻页控制.
	 * 
	 * @return the toolbar html
	 */
	public String getToolbarHtml(long curPageNo, long totalPage,
			long totalCount, int pageSize,String pageSizeList) {
		StringBuffer html = new StringBuffer();
		html.append("&nbsp;&nbsp;共").append(totalCount).append(
				"条记录&nbsp;&nbsp;");
		// 总页数大于1才显示上一页
		if (totalPage > 1) {
			html
					.append("&nbsp;<span class='otherPage' onclick='_gotopageahead()'>&nbsp;上一页&nbsp;</span>&nbsp;");
		}
		if (curPageNo < 6) {
			for (int i = 0; i < totalPage; i++) {
				if (i > 5) {
					if (totalPage > 6) {
						if (totalPage > 7) {
							html.append("<b>.&nbsp;.&nbsp;.</b>");
						}
						html
								.append("<span class='otherPage' onclick='javascript:_gotopagecho("
										+ totalPage
										+ ")' >&nbsp;&nbsp;"
										+ totalPage
										+ "&nbsp;&nbsp;</span>&nbsp;");
					}
					break;
				} else {
					int j = i + 1;
					if (curPageNo == j) {
						html.append("<span class='curPage' >&nbsp;&nbsp;" + j
								+ "&nbsp;&nbsp;</span>&nbsp;");
					} else {
						html
								.append("<span class='otherPage' onclick='javascript:_gotopagecho("
										+ j
										+ ")' >&nbsp;&nbsp;"
										+ j
										+ "&nbsp;&nbsp;</span>&nbsp;");
					}
				}
			}
		} else {
			html
					.append("<span class='otherPage' onclick='javascript:_gotopagecho("
							+ 1
							+ ")' >&nbsp;&nbsp;"
							+ 1
							+ "&nbsp;&nbsp;</span>&nbsp;");
			if (totalPage <= curPageNo + 3) {
				if (totalPage != 6 && totalPage != 7) {
					html.append("<b>.&nbsp;.&nbsp;.</b>");
				}
				for (long i = (totalPage - 6 == 0 ? 1 : totalPage - 6); i < totalPage; i++) {
					long j = i + 1;
					if (curPageNo == j) {
						html
								.append("<span aligh='center' class='curPage' >&nbsp;&nbsp;"
										+ j + "&nbsp;&nbsp;</span>&nbsp;");
					} else {
						html
								.append("<span class='otherPage' onclick='javascript:_gotopagecho("
										+ j
										+ ")' >&nbsp;&nbsp;"
										+ j
										+ "&nbsp;&nbsp;</span>&nbsp;");
					}
				}
			} else {
				html.append("<b>.&nbsp;.&nbsp;.</b>");
				for (long i = (curPageNo - 3), t = curPageNo + 2; i < t; i++) {
					long j = i + 1;
					if (curPageNo == j) {
						html
								.append("<span aligh='center' class='curPage' >&nbsp;&nbsp;"
										+ j + "&nbsp;&nbsp;</span>&nbsp;");
					} else {
						html
								.append("<span class='otherPage' onclick='javascript:_gotopagecho("
										+ j
										+ ")' >&nbsp;&nbsp;"
										+ j
										+ "&nbsp;&nbsp;</span>&nbsp;");
					}
				}
				html.append("<b>.&nbsp;.&nbsp;.</b>");
				html
						.append("<span class='otherPage' onclick='javascript:_gotopagecho("
								+ totalPage
								+ ")' >&nbsp;&nbsp;"
								+ totalPage
								+ "&nbsp;&nbsp;</span>&nbsp;");
			}
		}

		long tempNextPage = curPageNo + 1;
		if (totalPage > 1) {
			html
					.append("&nbsp;<span class='otherPage' onclick='_gotopagenext()'>&nbsp;下一页&nbsp;</span>");
		}

		html
				.append("&nbsp;&nbsp;每页&nbsp;<select onChange='_gotoPage3()' style='font-size:12px;'  id='_gotoPageSize' class='page_no' maxlength='5'>");
		// xingkefa 2012-01-19 每页显示条数列表！ start
		//String pageSizeList = Properties.getStrList("PAGE_SIZE_LIST");
		String[] pagesizes = pageSizeList.split(",");
		String pagesize = pageSize + "";
		for (int i = 0; i < pagesizes.length; i++) {
			if (pagesize.equals(pagesizes[i])) {
				html.append(
						"<option selected='selected' value='" + pagesizes[i]
								+ "'>").append(pagesizes[i])
						.append("</option>");
			} else {
				html.append("<option value='" + pagesizes[i] + "'>").append(
						pagesizes[i]).append("</option>");
			}
		}
		html.append("</select>&nbsp;条");
		// end
		html
				.append("&nbsp;&nbsp;到第&nbsp;<input id='_gotoPageNo' class='page_no' maxlength='5' value="
						+ curPageNo + ">&nbsp;页");
		html
				.append("&nbsp;<input type='button' id='_gotoPageNobt'  class='btn'  onclick='_gotoPage2()' value='确定'>");

		html.append("<script type='text/javascript'>");
		// 刘曰刚-2014-06-17 //
		// 修改当有选中货品时跳转页面的提示框，改为确定提示框，如果确定则跳转，取消则不变
		// 选择确定的页面后的跳转
		html.append("function _gotopagecho(page){");
		html.append("listForm").append(".pageNo.value = page;");
		html.append("showWaitPanel('');");
		html.append("checkPrdId();");
		html.append("}");

		// 点击下一页后的页面跳转
		// modify by zhuxw parent 有可能为null
		html
				.append("function _gotopagenext(){if("
						+ tempNextPage
						+ ">"
						+ totalPage
						+ "){ if(parent.showErrorMsg){parent.showErrorMsg('已经是最后一页!');return;}else{showErrorMsg('已经是最后一页!');return;}}");
		html.append("listForm").append(".pageNo.value = " + tempNextPage + ";");
		html.append("showWaitPanel('');");
		html.append("checkPrdId();");
		html.append("}");

		long tempAheadPage = curPageNo - 1;
		// 点击上一页后的页面跳转
		html
				.append("function _gotopageahead(){if("
						+ tempAheadPage
						+ "<"
						+ 1
						+ "){if(parent.showErrorMsg){parent.showErrorMsg('已经是第一页!');return;}else{showErrorMsg('已经是第一页!');return;}}");
		html.append("listForm")
				.append(".pageNo.value = " + tempAheadPage + ";");
		html.append("showWaitPanel('');");
		html.append("checkPrdId();");
		html.append("}");

		// 点击确定后的跳转
		html.append("function _gotoPage2(){");
		html.append("var inpt = g('_gotoPageNo');");
		html.append("var pageNo = inpt.value*1;");
		// 頁碼等於當前頁碼時，不飜頁
		html.append("if(").append(curPageNo).append(" == pageNo ){return;");
		// 頁碼是否超出範圍
		html.append("}else if (").append(totalPage).append(" < pageNo){");
		html.append("parent.showErrorMsg('页码超出范围!');return;");
		html.append("}else if ( 1 > pageNo){");
		html.append("parent.showErrorMsg('页码超出范围!');return;");
		html.append("}");
		// 頁碼跳轉
		html.append("listForm").append(".pageNo.value = pageNo;");
		html.append("showWaitPanel('');");
		html.append("checkPrdId();");

		html.append("}");
		// pagesizelist 改变时跳转！
		html.append("function _gotoPage3(){");
		html.append("var inpt = g('_gotoPageSize');");
		html.append("var pagesize = inpt.value;");
		// pagesize跳轉
		html.append("listForm").append(".pageSize.value = pagesize;");
		html.append("showWaitPanel('');");
		html.append("checkPrdId();");
		html.append("}");
		html
				.append("function checkPrdId(){")
				.append("var checkBox = $('#jsontb tr:gt(0) input:checked');")
				.append("if(!checkBox.length<1){")
				.append(
						"parent.showConfirm('页面有货品未下单，如不下单则所选货品记录会丢失,确定跳转吗？','bottomcontent.retrue()','N');")
				.append("closeWindow();").append("}else{").append(
						"setTimeout('").append("listForm").append(
						".submit()',100);").append("}").append("}");
		html.append("function retrue(){").append("setTimeout('").append(
				"listForm").append(".submit()',100);").append("}");
		html.append("</script>");
		return html.toString();
	}
}
