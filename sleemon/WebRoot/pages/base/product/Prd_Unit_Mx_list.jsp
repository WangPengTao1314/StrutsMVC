
<!--  
/**
 * @module 系统管理
 * @func 货品计量单位
 * @version 1.1
 * @author 刘曰刚
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
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/product/Prd_Unit_Mx_list.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
			   		<c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
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
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="MEAS_UNIT_NO" >计量单位编号</th>
                    <th  nowrap="nowrap" align="center" dbname="MEAS_UNIT_NAME" >计量单位名称</th>
                    <th  nowrap="nowrap" align="center" dbname="MEAS_UNIT_TYPE" >单位类型</th>
                    <th  nowrap="nowrap" align="center" dbname="RATIO" >换算关系</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="setDtlId('${rst.PRD_UNIT_ID}');selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.PRD_UNIT_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="left" >${rst.MEAS_UNIT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.MEAS_UNIT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.MEAS_UNIT_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.RATIO}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="10" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="PRD_UNIT_IDS" name="PRD_UNIT_IDS" value=""/>
</form>
</body>
</html>