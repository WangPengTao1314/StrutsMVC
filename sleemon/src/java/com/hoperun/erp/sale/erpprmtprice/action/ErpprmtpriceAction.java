/**

 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ChannAction.java
 */
package com.hoperun.erp.sale.erpprmtprice.action;

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
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.erpprmtprice.model.ErpprmtpriceModel;
import com.hoperun.erp.sale.erpprmtprice.service.ErpprmtpriceService;
import com.hoperun.sys.model.UserBean;

/**
 * The Class ChannAction.
 * 
 * @module 系统管理
 * @func 渠道惩罚设定
 * @version 1.0
 * @author 刘曰刚
 */
public class ErpprmtpriceAction extends BaseAction {
	/** The chann Service*/
	private ErpprmtpriceService erpprmtpriceService;
	
	public ErpprmtpriceService getErpprmtpriceService() {
		return erpprmtpriceService;
	}
	public void setErpprmtpriceService(ErpprmtpriceService erpprmtpriceService) {
		this.erpprmtpriceService = erpprmtpriceService;
	}

	/** 权限对象**/
	/** 维护*/
    //增删改查
    private static String PVG_BWS="BS0013901";
    private static String PVG_EDIT="";
    private static String PVG_DELETE="";
    //惩罚
    private static String PVG_PUNISH="";
    //取消惩罚
    private static String PVG_CANCEL="";
    //启用,停用
    private static String PVG_START_STOP="";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="";
    /** end*/
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
    
	/**
	 * 渠道信息框架
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
		
	}
	/**
     * 查询结果列表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("PRMT_PLAN_NO", ParamUtil.utf8Decoder(request, "PRMT_PLAN_NO"));
    	params.put("PRMT_PLAN_NAME", ParamUtil.utf8Decoder(request, "PRMT_PLAN_NAME"));
    	params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));
    	params.put("PAR_PRD_NAME", StringUtil.creCon("PAR_PRD_NAME", ParamUtil.utf8Decoder(request, "PAR_PRD_NAME"), ","));
    	params.put("PRD_NO", StringUtil.creCon("PRD_NO", ParamUtil.utf8Decoder(request, "PRD_NO"), ","));
    	params.put("PRD_NAME", StringUtil.creCon("PRD_NAME", ParamUtil.utf8Decoder(request, "PRD_NAME"), ","));
    	params.put("PRD_GROUP_NAME", StringUtil.creCon("a.PRD_GROUP_NAME", ParamUtil.utf8Decoder(request, "PRD_GROUP_NAME"), ","));
    	String PRMT_PLAN_ID=ParamUtil.utf8Decoder(request, "PRMT_PLAN_ID");
    	String set=ParamUtil.utf8Decoder(request, "set");
    	String STATE=ParamUtil.utf8Decoder(request, "STATE");
    	
    	if(StringUtil.isEmpty(PRMT_PLAN_ID)){
    		params.put("seachFlag", "1<>1");
    	}
    	if("已设置".equals(set)){
    		params.put("priceFlag", " 1=1 ");
    		params.put("prdFlag", " 1<>1 ");
		}else if("未设置".equals(set)){
			params.put("priceFlag", " 1<>1 ");
    		params.put("prdFlag", " 1=1 ");
		}
    	if("启用".equals(STATE)){
    		params.put("prdFlag", " 1<>1 ");
    	}
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("COMM_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		int pageSize=ParamUtil.getInt(request, "pageSize",0);
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
    	// 字段排序
		ParamUtil.setOrderField(request, params, "PRD_NO", "DESC");
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = erpprmtpriceService.pageQuery(params, pageNo);
    	String html=getHtml(params, pageNo,pageSize);
		request.setAttribute("html", html);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("page", page);
        request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
        return mapping.findForward("list");
    }
    /**
     * 保存促销价格
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		UserBean userBean =  ParamUtil.getUserBean(request);
		try {
			String jsonDate = ParamUtil.get(request, "jsonData");
			String PRMT_PLAN_ID=ParamUtil.get(request, "PRMT_PLAN_ID");
			List<ErpprmtpriceModel> ModelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				ModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<ErpprmtpriceModel>>() {
						}.getType());
				erpprmtpriceService.txInsertPrice(ModelList,PRMT_PLAN_ID,userBean);
			}
			
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
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
	 * 启用/停用
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void updateState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		UserBean userBean =  ParamUtil.getUserBean(request);
		try {
			String PRMT_PRICE_IDS = ParamUtil.get(request, "PRMT_PRICE_IDS");
			String STATE=ParamUtil.get(request, "STATE");
			erpprmtpriceService.txUpdateState(PRMT_PRICE_IDS,STATE,userBean);
		} catch (ServiceException s) {
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
	 * * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String,String> setPvg(UserBean userBean)
	    {
	    	Map<String,String>pvgMap=new HashMap<String,String>();
	    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
	    	pvgMap.put("PVG_EDIT",QXUtil.checkPvg(userBean, PVG_EDIT));
	    	pvgMap.put("PVG_DELETE",QXUtil.checkPvg(userBean, PVG_DELETE)  );
	    	pvgMap.put("PVG_START_STOP",QXUtil.checkPvg(userBean, PVG_START_STOP) );
	    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
	    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE)  );
	    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
	    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
	    	pvgMap.put("PVG_FINISH_CANCLE",QXUtil.checkPvg(userBean, PVG_FINISH_CANCLE) );
	    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
	    	pvgMap.put("PVG_PUNISH",QXUtil.checkPvg(userBean, PVG_PUNISH) );
	    	pvgMap.put("PVG_CANCEL",QXUtil.checkPvg(userBean, PVG_CANCEL) );
	    	pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
	    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
	    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
	    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
	    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
	    	return  pvgMap;
	   }
	 public String getHtml(Map<String,String> map,int pageNo,int pageSize){
	    	long totalCount=erpprmtpriceService.getCount(map);
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
				.append("parent.showConfirm('页面有货品未保存，如不保存则所选货品记录会丢失,确定跳转吗？','bottomcontent.retrue()','N');")
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
