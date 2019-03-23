<%@ page import="java.util.*" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
</head>
<body >
<div class="lst_area">
		<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
			<tr class="fixedRow">
				<th nowrap align="center">序号</th>
				<th nowrap align="center" >操作者</th>
				<th nowrap align="center" >操作</th>
				<th nowrap align="center" >时间</th>
				<th nowrap align="center" >下一操作者</th>
				<th nowrap align="center" >操作者类型</th>
				<th nowrap align="center" >意见</th>
			</tr>
			<c:forEach items="${resList}" var="rst" varStatus="row">
			<c:set var="r" value="${row.count % 2}"></c:set>
			<c:set var="rr" value="${row.count}"></c:set>
			<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
				<td nowrap align="left">${rr}&nbsp;</td>
				<td nowrap align="left">${rst.OPERATORNAME}&nbsp;</td>
				<td nowrap align="left">${rst.OPERATION}&nbsp;</td>
				<td nowrap align="center">${rst.OPERATETIME}&nbsp;</td>
				<td nowrap align="left">${rst.NEXTOPERATORNAME}&nbsp;</td>
				<td nowrap align="left">${rst.OPERATORTYPE}&nbsp;</td>
				<td nowrap align="left">${rst.REMARK}&nbsp;</td>
			</tr>
			</c:forEach>
			<c:if test="${empty resList}">
			<tr>
				<td height="25" colspan="13" align="center" class="lst_empty">&nbsp;草拟，尚未进入审核流程!&nbsp;</td>
			</tr>
			</c:if>
		</table>
		</div>
</body>
</html>
