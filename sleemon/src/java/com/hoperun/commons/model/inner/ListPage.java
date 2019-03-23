/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model.inner;

import java.util.ArrayList;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class ListPage.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 * 
 * @author uke
 */
public class ListPage implements IListPage {

	/** The DEFAUL t_ form. */
	private static String DEFAULT_FORM = "listForm";
	
	/** The page size. */
	private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数
	
	/** The page size list. */
	private String pageSizeList = DEFAULT_PAGE_SIZE_LIST; // 每页显示记录数列表

	/** The start. */
	private long start; // 当前页第一条数据在List中的位置,从0开始

	/** The data. */
	private Object data; // 当前页中存放的记录,类型一般为List

	/** The total count. */
	private long totalCount; // 总记录数
	
	/** The total page count. */
	private long totalPageCount;// 總頁數

	/** The form. */
	private String form = DEFAULT_FORM; // 页面Form名称
	
	/** The user style. */
	public String userStyle="";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getForm()
	 */
	public String getForm() {
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#setForm(java.lang.String)
	 */
	public void setForm(String form) {
		this.form = form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getStart()
	 */
	public long getStart() {
		return start;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#setStart(long)
	 */
	public void setStart(long start) {
		this.start = start;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#setPageSize(int)
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#setTotalCount(long)
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getPageSize()
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getTotalCount()
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 构造方法，只构造空页.
	 */
	public ListPage() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	/**
	 * 默认构造方法.
	 * 
	 * @param start 本页数据在数据库中的起始位置
	 * @param totalSize 数据库中总记录条数
	 * @param pageSize 本页容量
	 * @param data 本页包含的数据
	 */
	public ListPage(long start, long totalSize, int pageSize, Object data) {
		this.start = start;
		this.totalCount = totalSize;
		this.pageSize = pageSize;
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getTotalPageCount()
	 */
	public long getTotalPageCount() {
		if (totalPageCount == 0) {
			if (totalCount % pageSize == 0)
				totalPageCount = totalCount / pageSize;
			else
				totalPageCount = totalCount / pageSize + 1;
		}
		return totalPageCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getResult()
	 */
	public Object getResult() {
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getCurrentPageNo()
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#hasNextPage()
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#hasPreviousPage()
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 * 
	 * @param pageNo the page no
	 * 
	 * @return the start of page
	 */
	public static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 * 
	 * @param pageNo 从1开始的页号
	 * @param pageSize 每页记录条数
	 * 
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	/*
	 * 列表页面提示
	 * 
	 * @see com.yitong.commons.model.IPage#getFooterHtml()
	 */
	/* (non-Javadoc)
	 * @see com.hoperun.commons.model.IListPage#getFooterHtml()
	 */
	public String getFooterHtml() {
		//long curPageNo = getCurrentPageNo();
		//long totalPage = getTotalPageCount();
		StringBuffer html = new StringBuffer();
		//html.append("【第").append(curPageNo).append("页】");
		/*html.append("【共").append(totalPage).append("页】");
		html.append("【共").append(getTotalCount()).append("条】");
*/
		/*if (totalPage > 2) {
			//add by zy 2011/08/16
			for(int i=0 ;i<totalPage ; i++){
				html.append("<a href='javascript:_gotopagecho("+(i+1)+")'>"+(i+1)+"</a>");
				if(i>5){
					html.append("...");
					break;
				}	
			}
			long tempNextPage = curPageNo+1;
			html.append("&nbsp;<a href='javascript:_gotopagenext()'>下一页</a>");
			html.append("&nbsp;【共").append(totalPage).append("页】");
			html.append("&nbsp;到第<input id='_gotoPageNo' class='page_no' maxlength='5'>页");
			html.append("&nbsp;<input type='button' id='_gotoPageNobt' class='page_no' onclick='_gotoPage2()' value='确定'>");
			html.append("<script type='text/javascript'>");
			//选择确定的页面后的跳转
			html.append("function _gotopagecho(page){");
			html.append(getForm()).append(".pageNo.value = page;");
			html.append("showWaitPanel('');");
			html.append("setTimeout('").append(getForm()).append(".submit()',100);");
			html.append("}");
			
			//点击下一页后的页面跳转
			html.append("function _gotopagenext(){");
			html.append(getForm()).append(".pageNo.value = "+tempNextPage+";");
			html.append("showWaitPanel('');");
			html.append("setTimeout('").append(getForm()).append(".submit()',100);");
			html.append("}");
			
			//点击确定后的跳转
			html.append("function _gotoPage2(){");
			html.append("var inpt = g('_gotoPageNo');");
			html.append("var pageNo = inpt.value*1;");
			// 頁碼等於當前頁碼時，不飜頁
			html.append("if(").append(curPageNo).append(" == pageNo ){return;");
			// 頁碼是否超出範圍
			html.append("}else if (").append(totalPage).append(" < pageNo){");
			html.append("showErrorMsg('页码超出范围!');return;");
			html.append("}else if ( 1 > pageNo){");
			html.append("showErrorMsg('页码超出范围!');return;");
			html.append("}");
			// 頁碼跳轉
			html.append(getForm()).append(".pageNo.value = pageNo;");
			html.append("showWaitPanel('');");
			html.append("setTimeout('").append(getForm()).append(".submit()',100);");
			html.append("}");
			html.append("</script>");
			html.append("页码：<input id='_gotoPageNo' class='page_no' maxlength='5'>");
			html.append("<a href='javascript:_gotoPage2();'><span class='goPage'></span></a>&nbsp;"); 
			html.append("<script type='text/javascript'>");
			html.append("function _gotoPage2(){");
			html.append("var inpt = g('_gotoPageNo');");
			html.append("var pageNo = inpt.value*1;");
			// 頁碼等於當前頁碼時，不飜頁
			html.append("if(").append(curPageNo).append(" == pageNo ){return;");
			// 頁碼是否超出範圍
			html.append("}else if (").append(totalPage).append(" < pageNo){");
			html.append("showErrorMsg('页码超出范围!');return;");
			html.append("}else if ( 1 > pageNo){");
			html.append("showErrorMsg('页码超出范围!');return;");
			html.append("}");
			// 頁碼跳轉
			html.append(getForm()).append(".pageNo.value = pageNo;");
			html.append("showWaitPanel('');");
			html.append("setTimeout('").append(getForm()).append(".submit()',100);");
			html.append("}");
			html.append("</script>");
		}*/
		return html.toString();
	}

	/**
	 * 翻页控制.
	 * 
	 * @return the toolbar html
	 */
	public String getToolbarHtml() {
		StringBuffer html = new StringBuffer();
		long curPageNo = getCurrentPageNo();
		long totalPage = getTotalPageCount();
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
		
        //选择确定的页面后的跳转
		html.append("function _gotopagecho(page){");
		html.append(getForm()).append(".pageNo.value = page;");
		html.append("showWaitPanel('');");
		html.append("setTimeout('").append(getForm()).append(".submit()',100);");
		html.append("}");
		
		//点击下一页后的页面跳转
		//modify by zhuxw parent 有可能为null
		html.append("function _gotopagenext(){if("+tempNextPage+">"+totalPage+"){ if(parent.showErrorMsg){parent.showErrorMsg('已经是最后一页!');return;}else{showErrorMsg('已经是最后一页!');return;}}");
		html.append(getForm()).append(".pageNo.value = "+tempNextPage+";");
		html.append("showWaitPanel('');");
		html.append("setTimeout('").append(getForm()).append(".submit()',100);");
		html.append("}");
		
		long tempAheadPage = curPageNo-1;
		//点击上一页后的页面跳转
		html.append("function _gotopageahead(){if("+tempAheadPage+"<"+1+"){if(parent.showErrorMsg){parent.showErrorMsg('已经是第一页!');return;}else{showErrorMsg('已经是第一页!');return;}}");
		html.append(getForm()).append(".pageNo.value = "+tempAheadPage+";");
		html.append("showWaitPanel('');");
		html.append("setTimeout('").append(getForm()).append(".submit()',100);");
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
		html.append(getForm()).append(".pageNo.value = pageNo;");
		html.append("showWaitPanel('');");
		html.append("setTimeout('").append(getForm()).append(".submit()',100);");
		html.append("}");
		
		//pagesizelist 改变时跳转！
		html.append("function _gotoPage3(){");
		html.append("var inpt = g('_gotoPageSize');");
		html.append("var pagesize = inpt.value;");
		// pagesize跳轉
		html.append(getForm()).append(".pageSize.value = pagesize;");
		html.append("showWaitPanel('');");
		html.append("setTimeout('").append(getForm()).append(".submit()',100);");
		html.append("}");
		
		html.append("</script>");
		return html.toString();
	}
}