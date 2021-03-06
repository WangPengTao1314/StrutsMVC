﻿<!--
 * @prjName:喜临门营销平台
 * @fileName: 
 * @time   2014-12-03 10:35:10 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/expenseorder/Expenseorder_Child_List.js"></script>
    <script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel" style="margin-top: -15px;">
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                	<td nowrap>
					   <c:if test="${pvg.PVG_EDIT eq 1 }">
				   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
				   	   </c:if>
				   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
				   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
				   	   </c:if>
					   &nbsp;
					</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>
					<th  nowrap="nowrap" align="center" dbname="EXPENSE_TYPE" >报销类型</th>
                    <th  nowrap="nowrap" align="center" dbname="EXPENSE_REMARK" >报销事由</th>
                    <th  nowrap="nowrap" align="center" dbname="BUSS_DATE" >发生日期</th>
                    <th  nowrap="nowrap" align="center" dbname="EXPENSE_AMOUNT" >报销金额</th>
                    <th  nowrap="nowrap" align="center" dbname="OTHER_REMARK" >其它说明</th>
                    <th  nowrap="nowrap" align="center" dbname="" >附件</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr{rr}" onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="valign:middle"  id="eid${rr}" value="${rst.EXPENSE_ORDER_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
					 <td nowrap="nowrap" align="center" >${rst.EXPENSE_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.EXPENSE_REMARK}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BUSS_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.EXPENSE_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.OTHER_REMARK}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                      <input type="hidden" id="EXPENSE_ATT${rr}" name="" value="${rst.EXPENSE_ATT}" />
                      <script type="text/javascript">
				        displayDownFile('EXPENSE_ATT${rr}','SAMPLE_DIR',true,false);
				      </script>
                     </td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="7" align="center" class="lst_empty">
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
	<input type="hidden" id="EXPENSE_ORDER_DTL_IDS" name="EXPENSE_ORDER_DTL_IDS" value=""/>
</form>
</body>
</html>