package com.hoperun.drp.main.shopcar.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.main.shopcar.model.ShopcarChannInfoModel;
import com.hoperun.drp.main.shopcar.model.ShopcarModel;
import com.hoperun.drp.main.shopcar.service.ShopcarService;
import com.hoperun.sys.model.UserBean;

/**
 * 分销首页action
 * 
 * @author zhang_zhongbin
 * 
 */
public class ShopcarAction extends BaseAction {

	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS="FX0020401";
    private static String PVG_EDIT="FX0020402";
    private static String PVG_DELETE="FX0020403";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";
	private static String PVG_TRACE = "";
	
	//是否显示赠品按钮
	private static String PVG_BWS_GIFT = "FX0020406";
	
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "DRP_GOODS_ORDER";
	private static String AUD_TAB_KEY = "GOODS_ORDER_ID";
	private static String AUD_BUSS_STATE = "STATE";
	private static String AUD_BUSS_TYPE = "DRP_GOODS_ORDER_AUDIT";
	private static String AUD_FLOW_INS = "com.hoperun.sys.service.impl.GoodsOrderFlowInterfaceImpl";

	private ShopcarService shopcarService;

	/**
	 * @return the shopcarService
	 */
	public ShopcarService getShopcarService() {
		return shopcarService;
	}

	/**
	 * @param shopcarService
	 *            the shopcarService to set
	 */
	public void setShopcarService(ShopcarService shopcarService) {
		this.shopcarService = shopcarService;
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		Map<String, String> map = new HashMap<String, String>();
		map.put("LEDGER_ID", userBean.getLoginZTXXID());
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		map.put("DEAL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		map.put("CHANN_ID", userBean.getCHANN_ID());
		ParamUtil.putStr2Map(request, "SHOP_CART_TYPE", map);//购物行类型
		
		String REBATEFLAG=request.getParameter("REBATEFLAG");//是否使用返利标记
		
		String LARGESSFLAG=request.getParameter("LARGESSFLAG");//是否使用赠品标记
		if("1".equals(REBATEFLAG)){
//			String[] REBATE_PRD_NUMBER=REBATE_PRD_NUMBERS.split(",");
//			StringBuffer PRD_NOS=new StringBuffer();
//			for (int i = 0; i < REBATE_PRD_NUMBER.length; i++) {
//				PRD_NOS.append("'").append(REBATE_PRD_NUMBER[i]).append("',");
//			}
//			String PRDNOS=PRD_NOS.toString();
//			if(PRDNOS.length()>0){
//				PRDNOS = PRDNOS.substring(0,PRDNOS.length() - 1);
//				map.put("PRDNOS", PRDNOS);
//			}
			map.put("PRDNOS", " b.IS_REBATE_FLAG=1 and c.SPCL_TECH_ID is null ");
		}
		StringBuffer sql=new StringBuffer();
		if("1".equals(LARGESSFLAG)){
			sql.append(" b.IS_CAN_FREE_FLAG=").append(BusinessConsts.YJLBJ_FLAG_TRUE);
			sql.append(" and c.SPCL_TECH_ID is null");
		}else{
			sql.append(" 1=1");
		}
		map.put("sql", sql.toString());
		//图片服务器地址
		String picture_url = LogicUtil.getPicServerURL(userBean.getCURRT_PIC_URL(),"");
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		// 字段排序
		ParamUtil.setOrderField(request, map, "u.PRD_NO", "DESC");
		ParamUtil.putStr2Map(request, "pageSize", map);
		String DECT_RATE=LogicUtil.getChannDsct(userBean.getCHANN_ID());
        if(StringUtil.isEmpty(DECT_RATE)){
        	return mapping.findForward("error");
        }
        map.put("DECT_RATE", DECT_RATE);
		IListPage page = shopcarService.pageQuery(map, pageNo);
		int pageSize=ParamUtil.getInt(request, "pageSize",0);
		String sta;
		String AREA_SER_ID =  userBean.getAREA_SER_ID(); //区域服务中心ID
		if(StringUtil.isEmpty(AREA_SER_ID)&& !"true".equals(Consts.IS_OLD_FLOW)){
			sta = "show";
		}else{
			sta = "hidden";
		}
		//获取返利折扣
		Map<String,Object> result = LogicUtil.getChannRebateDsct(userBean.getCHANN_ID());
		if(null!=result){
			request.setAttribute("REBATEDSCT", result.get("DECT_RATE"));//返利折扣
			request.setAttribute("REBATE_TOTAL", result.get("REBATE_TOTAL"));//返利总金额
			request.setAttribute("REBATE_CURRT", result.get("REBATE_CURRT"));//当前返利
			request.setAttribute("REBATE_FREEZE", result.get("REBATE_FREEZE"));//冻结的返利
		}
		//获取赠品折扣
		String largessDect=StringUtil.nullToSring(shopcarService.getLargessDect(userBean.getCHANN_ID()));
		if(0==largessDect.indexOf('.')){
			largessDect="0"+largessDect;
		}
		//查询可用信用  add by zzb 2014-09-23
		if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){
			String userCredit = LogicUtil.getU9CurrCredit(userBean.getCHANN_NO());
			request.setAttribute("userCredit", userCredit);
		}
		
		request.setAttribute("sta", sta);
		String html=getHtml(map, pageNo,pageSize);
		request.setAttribute("html", html);
		request.setAttribute("picture_url", picture_url);
		request.setAttribute("params", map);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
		request.setAttribute("LARGESSDSCT", largessDect);
		request.setAttribute("REBATEFLAG", REBATEFLAG);
		request.setAttribute("LARGESSFLAG", LARGESSFLAG);
		return mapping.findForward("list");
	}

	/**
	 * 删除购物车
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String SHOP_CART_IDS = request.getParameter("SHOP_CART_IDS");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("SHOP_CART_IDS", SHOP_CART_IDS);
			params.put("UPD_NAME", userBean.getYHM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			shopcarService.txDelete(params);
			shopcarService.clearCaches(params);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "删除失败!");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
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
	public ActionForward toInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		String CHANN_ID = userBean.getCHANN_ID();
		Map<String, String> chann = shopcarService.getChannInfo(CHANN_ID);
		
		String sta;
		String AREA_SER_ID =  userBean.getAREA_SER_ID(); //区域服务中心ID
		if(StringUtil.isEmpty(AREA_SER_ID)&& !"true".equals(Consts.IS_OLD_FLOW)){
			sta = "show";
		}else{
			sta = "hidden";
		}
		//查询可用信用  add by zzb 2014-09-23
		if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){
			String userCredit = LogicUtil.getU9CurrCredit(userBean.getCHANN_NO());
			request.setAttribute("userCredit", userCredit);
		}
		request.setAttribute("rst", chann);
		request.setAttribute("params", chann);
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("sta", sta);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
		return mapping.findForward("info");
	}

	// 点击图片弹出窗口显示大图片
	public ActionForward toPicture(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("fileName");
		request.setAttribute("fileName", fileName);
		return mapping.findForward("picture");
	}

	// 保存购物车/转订货订单
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			Map<String,String> shopParams=new HashMap<String, String>();
			String jsonDate = ParamUtil.get(request, "jsonData");
			String tabData = ParamUtil.get(request, "tabData");
			shopParams.put("rebate", request.getParameter("rebate"));
			shopParams.put("LARGESSDSCT", request.getParameter("LARGESSDSCT"));
			shopParams.put("LARGESSFLAG", request.getParameter("LARGESSFLAG"));
			if(StringUtil.isEmpty(shopParams.get("rebate"))){
				shopParams.put("rebate", "0");
			}
			if(StringUtil.isEmpty(shopParams.get("LARGESSFLAG"))){
				shopParams.put("LARGESSFLAG", "0");
			}
			shopParams.put("type", request.getParameter("type"));
			shopParams.put("SHOP_CART_IDS", request.getParameter("SHOP_CART_IDS"));
			String ORDER_RECV_DATE = request.getParameter("ORDER_RECV_DATE");
			shopParams.put("REBATEDSCT", request.getParameter("REBATEDSCT"));
			List<ShopcarModel> ModelList = null;
			ShopcarChannInfoModel channModel = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				ModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<ShopcarModel>>() {
						}.getType());
			}
			if (!StringUtil.isEmpty(tabData)) {
				channModel = new Gson().fromJson(tabData,
						new TypeToken<ShopcarChannInfoModel>() {
						}.getType());
				if(null != channModel){
					channModel.setORDER_RECV_DATE(ORDER_RECV_DATE);//交期
				}
				
			}
			boolean flag=shopcarService.txSaveCommit(shopParams,ModelList,userBean,channModel);
			if(flag){
				jsonResult = jsonResult(true, "操作成功");
			}else{
				jsonResult = jsonResult(false, "操作失败");
			}
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			String regEx = "[\\u4e00-\\u9fa5]"; 
			Pattern p = Pattern.compile(regEx);
			String str=e.getMessage();
			Matcher m = p.matcher(str);  
			if(m.find()){
				jsonResult = jsonResult(false, str);
			}else{
				jsonResult = jsonResult(false, "操作失败");
				e.printStackTrace();
			}
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 修改特殊工艺后修改该条的价格
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void upPrice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("PRICE", ParamUtil.get(request, "PRICE"));
			map.put("DECT_RATE", ParamUtil.get(request, "DECT_RATE"));
			map.put("DECT_PRICE", ParamUtil.get(request, "DECT_PRICE"));
			map.put("ORDER_NUM", ParamUtil.get(request, "ORDER_NUM"));
			map.put("ORDER_AMOUNT", ParamUtil.get(request, "ORDER_AMOUNT"));
			map.put("SHOP_CART_ID", ParamUtil.get(request, "SHOP_CART_ID"));
			shopcarService.upPrice(map);
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
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_BWS_GIFT", QXUtil.checkPvg(userBean, PVG_BWS_GIFT));
		
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		
		return pvgMap;
	}
	 public String getHtml(Map<String,String> map,int pageNo,int pageSize){
	    	long totalCount=shopcarService.getCount(map);
	    	if(pageSize==0){
	    		pageSize =  Consts.PAGE_SIZE;
	    	}
			int start = (pageNo - 1) * pageSize;
			long curPageNo=start / pageSize + 1;
			long totalPageCount=0;
			if (totalPageCount == 0) {
				if (totalCount % pageSize == 0)
					totalPageCount = totalCount / pageSize;
				else
					totalPageCount = totalCount / pageSize + 1;
			}
			String html=getToolbarHtml(curPageNo, totalPageCount, totalCount,pageSize);
	    	return html;
	    }
	    
	    /**
		 * 翻页控制.
		 * 
		 * @return the toolbar html
		 */
	 public String getToolbarHtml(long curPageNo,long totalPage,long totalCount,int pageSize) {
			StringBuffer html = new StringBuffer();
			html.append("&nbsp;&nbsp;共").append(totalCount).append("条记录&nbsp;&nbsp;");
			//总页数大于1才显示上一页
			if(totalPage>1){
				html.append("&nbsp;<span class='otherPage' onclick='_gotopageahead()'>&nbsp;上一页&nbsp;</span>&nbsp;");
			}
			if(curPageNo < 6){
				for(int i=0 ;i<totalPage; i++){
					if(i>5){
						if(totalPage > 6){
							if(totalPage > 7){
								html.append("<b>.&nbsp;.&nbsp;.</b>");
							}
							html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+totalPage+")' >&nbsp;&nbsp;"+totalPage+"&nbsp;&nbsp;</span>&nbsp;");
						}
						break;
					}else{
						int j = i+1;
						if(curPageNo == j){
							html.append("<span class='curPage' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}else{
							html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+j+")' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}
					}
				}
			}else{
				html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+1+")' >&nbsp;&nbsp;"+1+"&nbsp;&nbsp;</span>&nbsp;");
				if(totalPage <= curPageNo+3){
					if(totalPage!=6&& totalPage!=7){
						html.append("<b>.&nbsp;.&nbsp;.</b>");
					}
					for(long i=(totalPage-6==0?1:totalPage-6) ;i<totalPage; i++){
						long j = i+1;
						if(curPageNo == j){
							html.append("<span aligh='center' class='curPage' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}else{
							html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+j+")' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}
					}
				}else{
					html.append("<b>.&nbsp;.&nbsp;.</b>");
					for(long i=(curPageNo-3),t=curPageNo+2;i<t ; i++){
						long j = i+1;
						if(curPageNo == j){
							html.append("<span aligh='center' class='curPage' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}else{
							html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+j+")' >&nbsp;&nbsp;"+j+"&nbsp;&nbsp;</span>&nbsp;");
						}
					}
					html.append("<b>.&nbsp;.&nbsp;.</b>");
					html.append("<span class='otherPage' onclick='javascript:_gotopagecho("+totalPage+")' >&nbsp;&nbsp;"+totalPage+"&nbsp;&nbsp;</span>&nbsp;");
				}
			}
			
			long tempNextPage = curPageNo+1;
			if(totalPage>1){
				html.append("&nbsp;<span class='otherPage' onclick='_gotopagenext()'>&nbsp;下一页&nbsp;</span>");
			}
			
			html.append("&nbsp;&nbsp;每页&nbsp;<select onChange='_gotoPage3()' style='font-size:12px;' id='_gotoPageSize' class='page_no' maxlength='5'>");
			//xingkefa 2012-01-19 每页显示条数列表！  start
			String pageSizeList=Properties.getStrList("PAGE_SIZE_LIST");
			String[] pagesizes = pageSizeList.split(",");
			String pagesize = pageSize + "";
			for(int i=0;i<pagesizes.length;i++){
				if(pagesize.equals(pagesizes[i])){
					html.append("<option selected='selected' value='"+pagesizes[i]+"'>").append(pagesizes[i]).append("</option>");
				}else{
					html.append("<option value='"+pagesizes[i]+"'>").append(pagesizes[i]).append("</option>");
				}
			}
			html.append("</select>&nbsp;条");
			//end 
			html.append("&nbsp;&nbsp;到第&nbsp;<input id='_gotoPageNo' class='page_no' maxlength='5' value="+curPageNo+">&nbsp;页");
			html.append("&nbsp;<input type='button' id='_gotoPageNobt'  class='btn'  onclick='_gotoPage2()' value='确定'>");
			
			html.append("<script type='text/javascript'>");
			//刘曰刚-2014-06-17 //
			//修改当有选中货品时跳转页面的提示框，改为确定提示框，如果确定则跳转，取消则不变
	        //选择确定的页面后的跳转
			html.append("function _gotopagecho(page){");
			html.append("listForm").append(".pageNo.value = page;");
			html.append("showWaitPanel('');");
			html.append("checkPrdId();");
			html.append("}");
			
			//点击下一页后的页面跳转
			//modify by zhuxw parent 有可能为null
			html.append("function _gotopagenext(){if("+tempNextPage+">"+totalPage+"){ if(parent.showErrorMsg){parent.showErrorMsg('已经是最后一页!');return;}else{showErrorMsg('已经是最后一页!');return;}}");
			html.append("listForm").append(".pageNo.value = "+tempNextPage+";");
			html.append("showWaitPanel('');");
			html.append("checkPrdId();");
			html.append("}");
			
			long tempAheadPage = curPageNo-1;
			//点击上一页后的页面跳转
			html.append("function _gotopageahead(){if("+tempAheadPage+"<"+1+"){if(parent.showErrorMsg){parent.showErrorMsg('已经是第一页!');return;}else{showErrorMsg('已经是第一页!');return;}}");
			html.append("listForm").append(".pageNo.value = "+tempAheadPage+";");
			html.append("showWaitPanel('');");
			html.append("checkPrdId();");
			html.append("}");
			
			//点击确定后的跳转
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
			
			//pagesizelist 改变时跳转！
			html.append("function _gotoPage3(){");
			html.append("var inpt = g('_gotoPageSize');");
			html.append("var pagesize = inpt.value;");
			// pagesize跳轉
			html.append("listForm").append(".pageSize.value = pagesize;");
			html.append("showWaitPanel('');");
			html.append("checkPrdId();");
			html.append("}");
			html.append("function checkPrdId(){")
				.append("var checkBox = $('#jsontb tr:gt(0) input:checked');")
				.append("if(!checkBox.length<1){")
				.append("parent.showConfirm('页面有货品未下单，如不下单则所选货品记录会丢失,确定跳转吗？','bottomcontent.retrue()','N');")
				.append("closeWindow();")
				.append("}else{")
				.append("setTimeout('").append("listForm").append(".submit()',100);")
				.append("}")
				.append("}");
			html.append("function retrue(){")
				.append("setTimeout('").append("listForm").append(".submit()',100);")
				.append("}");
			html.append("</script>");
			return html.toString();
		}
}
