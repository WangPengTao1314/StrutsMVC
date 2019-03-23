﻿<!--  
/**
 * @module 系统管理
 * @func 货品信息-特殊工艺维护-部件新增
 * @version 1.1
 * @author 黄如
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	 
	<title>处理信息填写</title>
</head>
<body class="bodycss1">
	<form>
		<input type="hidden" id="CMPL_ADVS_ID" json="CMPL_ADVS_ID" name="CMPL_ADVS_ID" value="${CMPL_ADVS_ID}"/>
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<%--<th width="1%"></th>--%>
					<th  nowrap="nowrap" align="center" dbname="DEAL_PSON_NAME" >处理人</th>
					<th  nowrap="nowrap" align="center" dbname="DEAL_TIME" >处理时间</th>
					<th  nowrap="nowrap" align="center" dbname="DEAL_TIME" >处理结果</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${list}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="">
					 <%--<td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.DRP_CMPL_ADVS_TRACE_ID}"/>
						<input type="hidden" id="FEEDBACK_INFO${rst.CMPL_ADVS_ID}" name="FEEDBACK_INFO" value="${rst.FEEDBACK_INFO}">
					 </td>
					 --%><td nowrap="nowrap" align="left">${rst.DEAL_PSON_NAME}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.DEAL_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FEEDBACK_INFO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     
				    </tr>
				</c:forEach>
				<c:if test="${empty list}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</form>
</body>
<script type="text/javascript">
 

</script>
</html>
