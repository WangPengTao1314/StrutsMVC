

<!--  
/**
 * @module 系统管理
 * @func 系统业务一览
 * @version 1.1
 * @author  zcx
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>系统业务表一览</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/table/Table_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;基础信息&gt;&gt;系统业务表一览</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20px" valign="top" >
	   <div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="margin-left:3px;">
				<tr >
				   <td nowrap >
								<c:if test="${pvg.PVG_BWS eq 1 }">
									<input id="query" type="button" class="btn" value="查询(F)"
										title="Alt+F" accesskey="F">
								    <!--  
									<input id="expdata" type="button" class="btn" value="导出(X)" title="Alt+X" accesskey="X">
								    -->
								</c:if>
					</td>
				</tr>
			</table>
	    </div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="left" dbname="TABLE_NAME_EN">系统业务表英文名</th>
					<th nowrap align="left" dbname="TABLE_NAME_ZH">系统业务表中文名</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
                        <td nowrap align="left"><a href="#" onclick="orpenWindow('${rst.TABLE_NAME_EN}');">${rst.TABLE_NAME_EN}</a>&nbsp;</td>
                        <td nowrap align="left"><a href="#" onclick="orpenWindow('${rst.TABLE_NAME_EN}');">${rst.TABLE_NAME_ZH}</a>&nbsp;</td>
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td height="12px" align="center">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" class="lst_toolbar">
			<tr>
				<td>
					<form id="pageForm" action="#" method="post" name="listForm">
					<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
					<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
					<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
					<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
					<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
					<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
					<span id="hidinpDiv" name="hidinpDiv"></span>
					${paramCover.unCoveredForbidInputs}
					</form>
				</td>
				<td align="right">
					 ${page.footerHtml}${page.toolbarHtml}
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<div id="querydiv" class="query_div" >
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<input type="hidden" name="selectChann" value=" STATE in( '启用') and DEL_FLAG='0' ">
	<input id="selcondition" type="hidden" name="selcondition" value=" 1=1 " />
	<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is not null ">
	<input type="hidden" id="selectAreaManager" name="selectAreaManager" value=""/>
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="10%" nowrap align="right" class="detail_label" >系统业务表中文名：</td>
					<td nowrap width="15%" class="detail_content">														
						<input id="TABLE_NAME_ZH" json="TABLE_NAME_ZH"  name="TABLE_NAME_ZH" type="text" inputtype="string"
							  value="${params.TABLE_NAME_ZH}" >	
					</td>
					<td width="10%" nowrap align="right" class="detail_label">系统业务表英文名：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="TABLE_NAME_EN" name="TABLE_NAME_EN" id="TABLE_NAME_EN" value="${params.TABLE_NAME_EN}"/>
					</td>
				</tr>
				<tr>
					<td colspan="10" align="center" valign="middle" >
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<script type="text/javascript">
    function orpenWindow(TABLE_NAME_EN){
		window.showModalDialog("table.shtml?action=toView&TABLE_NAME_EN="+TABLE_NAME_EN,window,"dialogwidth=1000px; dialogheight=800px; status=no");
	 }
</script>
</html>
