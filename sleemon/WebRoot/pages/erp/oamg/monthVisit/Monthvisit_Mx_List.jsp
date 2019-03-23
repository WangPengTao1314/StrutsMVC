<!-- 
 *@module 渠道管理-拜访管理
 *@func   月度拜访计划维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>月度拜访计划维护</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
    <script type="text/javascript" src="${ctx}/pages/erp/oamg/monthVisit/Monthvisit_Mx_List.js"></script>
</head>
<BODY>
<table width="99.9%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td height="100%">
		<div class="tablayer">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
				   <td nowrap>
			   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
			   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>

<tr>
	<td>
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center" width="8%"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th> 
					<th nowrap align="center" width="23%">拜访对象类型</th>
					<th nowrap align="center" width="23%">拜访形式</th>
					<th nowrap align="center" width="23%">拜访次数</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
						<td nowrap align='center' >
							<input type="checkbox" style="height:12px;valign:middle" id="eid${rr}" value="${rst.MONTH_VISIT_PLAN_DTL_ID}" onclick="setEidChecked(this);">
						</td>
						<td nowrap align="center">${rst.VISIT_OBJ_TYPE}&nbsp;</td>
						<td nowrap align="center">${rst.VISIT_TYPE}&nbsp;</td>
						<td nowrap align="center">${rst.PLAN_VISIT_NUM}&nbsp;</td>
					</tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">&nbsp;无相关信息&nbsp;</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="MONTH_VISIT_PLAN_DTL_IDS" name="MONTH_VISIT_PLAN_DTL_IDS" value=""/>
	<input type="hidden" id="MONTH_VISIT_PLAN_ID"  name="MONTH_VISIT_PLAN_ID" value=""/>
</form>
</body>
 

						