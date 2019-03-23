

<!--  
/**
 * @module 系统管理
 * @func 渠道信息
 * @version 1.1
 * @author  刘曰刚
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
	<title>渠道信息列表</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/chann/Chann_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th nowrap align="center" dbname="CHANN_NO">渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME">渠道名称</th>
					<th nowrap align="center" dbname="CHANN_TYPE">渠道类型</th>
					<th nowrap align="center" dbname="CHAA_LVL">渠道级别</th>
					<th nowrap align="center" dbname="CHANN_NAME_P">上级渠道</th>
					<th nowrap align="center" dbname="AREA_NO">区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME">区域名称</th>
					<th nowrap align="center" dbname="CRE_NAME">制单人</th>
					<th nowrap align="center" dbname="CRE_TIME">制单时间</th>
					<th nowrap align="center" dbname="DEPT_NAME">制单部门</th>
					<th nowrap align="center" dbname="STATE">状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%"align='center' >
							<input type="checkbox" style="valign:middle" name="eid" id="eid${rr}" value="${rst.CHANN_ID}"/>
						</td>
                        <td nowrap align="left">${rst.CHANN_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.CHANN_NAME}&nbsp;</td>
                        <td nowrap align="center" >${rst.CHANN_TYPE}&nbsp;</td>
                        <td nowrap align="center" >${rst.CHAA_LVL}&nbsp;</td>
                        <td nowrap align="left">${rst.CHANN_NAME_P}&nbsp;</td>
                        <td nowrap align="left">${rst.AREA_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.AREA_NAME}&nbsp;</td>
                        <td nowrap align="left">${rst.CRE_NAME}&nbsp;</td>
                        <td nowrap align="center">${rst.CRE_TIME}&nbsp;</td>
                        <td nowrap align="left">${rst.DEPT_NAME}&nbsp;</td>
                        <td nowrap json='STATE' align="center">${rst.STATE}&nbsp;</td>
                        <input type="hidden" id="state${rst.CHANN_ID}" value="${rst.STATE}"/>
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
</body>
</html>
