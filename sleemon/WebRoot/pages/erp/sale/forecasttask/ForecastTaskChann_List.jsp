<!-- 
 *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *  
 -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>渠道列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center" dbname="CHANN_NO" >渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME" >渠道名称</th>
					<th nowrap align="center" dbname="AREA_NO" >区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME" >区域名称</th>
					<th nowrap align="center" dbname="SENDER_NAME" >发布人</th>
					<th nowrap align="center" dbname="SENDER_TIME" >发布时间</th>
					<th nowrap align="center" dbname="STATE" >状态</th>		
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
						<td align="center">${rst.CHANN_NO}&nbsp;</td>
						<td align="center">${rst.CHANN_NAME}&nbsp;</td>
						<td align="center">${rst.AREA_NO}&nbsp;</td>
						<td align="center">${rst.AREA_NAME}&nbsp;</td>
						<td align="left">${rst.SENDER_NAME}&nbsp;</td>
						<td align="center">${rst.SENDER_TIME}&nbsp;</td>
						<td align="center">${rst.STATE}&nbsp;</td>
					</tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="7" colspan="13" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
</table>
</body>
</html>