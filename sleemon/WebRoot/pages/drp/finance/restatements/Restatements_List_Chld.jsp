<!--
 * @prjName:喜临门营销平台
 * @fileName:Dstrorder_List
 * @author glw
 * @time   2013-08-16 10:31:37 
 * @version 1.1
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/dstrorder/Restatements_List_Chld.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th  nowrap="nowrap" align="center" >入库单编号</th>
                    <th  nowrap="nowrap" align="center" >货品编号</th>
                    <th  nowrap="nowrap" align="center" >货品名称</th>
                    <th  nowrap="nowrap" align="center" >规格型号</th>
                    <th  nowrap="nowrap" align="center" >花号</th>
                    <th  nowrap="nowrap" align="center" >品牌</th>
                    <th  nowrap="nowrap" align="center" >标准单位</th>
                    <th  nowrap="nowrap" align="center" >退货数量</th>
                    <th  nowrap="nowrap" align="center" >单价</th>
                    <th  nowrap="nowrap" align="center" >折扣</th>
                    <th  nowrap="nowrap" align="center" >折扣单价</th>
                    <th  nowrap="nowrap" align="center" >折后金额</th>
                    <th  nowrap="nowrap" align="center" >备注</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
					 <td nowrap="nowrap" align="center" >${rst.STOREOUTIN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.RETURN_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.DECT_RATE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.DECT_PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.DECT_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.REMARK}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
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
	<input type="hidden" id="DSTR_ORDER_DTL_IDS" name="DSTR_ORDER_DTL_IDS" value=""/>
</form>
</body>
</html>