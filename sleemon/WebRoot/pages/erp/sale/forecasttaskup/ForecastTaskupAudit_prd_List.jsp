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
	<title>货品列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/forecasttaskup/ForecastTaskupAudit_prd_List.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="97%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                	<td nowrap>
					   <c:if test="${pvg.PVG_EDIT eq 1 }">
				   		<input id="save" type="button" class="btn" value="保存E)" title="Alt+E" accesskey="E">
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
		<form action="" id="ordertbForm" name="ordertbForm">
	  	   <input type="hidden" id="selShipParam" name="selShipParam" value=" DEL_FLAG=0 and STATE='启用' "/>
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center" dbname="PRD_NO" >货品编号</th>
					<th nowrap align="center" dbname="PRD_NAME" >货品名称</th>
					<th nowrap align="center" dbname="PRD_SPEC" >规格</th>
					<th nowrap align="center" dbname="" >生产基地</th>
					<th nowrap align="center" dbname="" >本月预计订货量</th>
					<th nowrap align="center" dbname="" >供货价</th>
					<th nowrap align="center" dbname="" >预计订货金额</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
						<td align="center">${rst.PRD_NO}</td>
						<td align="center">${rst.PRD_NAME}</td>
						<td align="center">${rst.PRD_SPEC}</td>
						<td align="left">
						<c:if test=""></c:if>
						
						 <input type="hidden" name="SHIP_POINT_ID" json="SHIP_POINT_ID" id="SHIP_POINT_ID${rr}" value="${rst.SHIP_POINT_ID}"/>
						 <input type="text" name="SHIP_POINT_NAME" json="SHIP_POINT_NAME" id="SHIP_POINT_NAME${rr}" readonly style="width:200px;" class="readonly" value="${rst.SHIP_POINT_NAME}"/>
						  <img align="absmiddle" name="selJGXX" style="cursor: hand" id="selImg${rr}"
					       src="${ctx}/styles/${theme}/images/plus/select.gif"
					       onClick="selCommon('BS_22', false, 'SHIP_POINT_ID${rr}', 'SHIP_POINT_ID', 'ordertbForm','SHIP_POINT_NAME${rr}', 'SHIP_POINT_NAME', 'selShipParam')">
					       
					       
						</td>
						<td align ="center">${rst.ADVC_RPT_NUM}</td>
						<td align="center">${rst.PRVD_PRICE}</td>
						<td align="center" >${rst.ADVC_RPT_AMOUNT} </td>
						<input type="hidden" id="" name="" json="ADVC_RPT_CHANN_DTL_ID" value="${rst.ADVC_RPT_CHANN_DTL_ID}"/>
					</tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="12" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
				</c:if>
				<tr>
				   <td colspan="4" align="center">
				        合计
				   </td>
				   <td align="center">
				       ${rstT.TOTAL_ADVC_RPT_NUM}
				   </td>
				   <td>&nbsp;
				   </td>
				   <td align="center">
				       ${rstT.TOTAL_ADVC_RPT_AMOUNT}
<!--				       预防拼接JSON为空-->
				        <input type="hidden" name="SHIP_POINT_ID" json="SHIP_POINT_ID"  />
						 <input type="hidden" name="SHIP_POINT_NAME" json="SHIP_POINT_NAME" />
				   </td>
				</tr>
			</table>
			</form>
		</div>
	</td>
</tr>
 </table>
<script language="JavaScript">  
</script>
</body>
</html>