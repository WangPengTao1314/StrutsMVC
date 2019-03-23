<!--  
/**
  *@module 系统管理
  *@fuc 送货地址信息
  *@version 1.1
  *@author 张忠斌
*/
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>渠道分管信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
</head>
<BODY>
<table width="99.9%" height="100%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td>
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
					<th nowrap align="center">人员编号</th>
					<th nowrap align="center">人员名称</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));setBtStates();">
						<td nowrap align='center' >
							<input type="checkbox" name="eid" style="height:12px;valign:middle" id="eid${rr}" value="" onclick="setEidChecked(this);">
						</td>
						<td nowrap align="left">${rst.RYBH}&nbsp;</td>
						<td nowrap align="left">${rst.XM}&nbsp;</td>
					</tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="3" align="center" class="lst_empty">&nbsp;无相关信息&nbsp;</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
</table>
</body>
 

