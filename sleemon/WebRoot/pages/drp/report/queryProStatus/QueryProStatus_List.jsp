<!-- 
 *@module 销售报表
 *@func 生产情况查询
 *@version 1.1
 *@author ghx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>生产情况信息列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>	
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">

<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center" dbname="deliver_order_no" >发运单号</th>
					<th nowrap align="center" dbname="SaleOrderNo" >销售订单编号</th>
					<th nowrap align="center" dbname="CustNo" >客户编号</th>
					<th nowrap align="center" dbname="CustName" >客户名称</th>					
					<th nowrap align="center" dbname="PrdNo" >商品编号</th>
                    <th nowrap align="center" dbname="PrdName" >商品名称</th>
					<th nowrap align="center" dbname="PrdSpec" >规格型号</th>
					<th nowrap align="center" dbname="PLAN_NUM" >排车数量</th>
					<th nowrap align="center" dbname="StoreNum" >抵库数量</th>	
					<th nowrap align="center" dbname="StroeInNum" >已入库数量</th>					
					<th nowrap align="center" dbname="WorkNum" >生产中</th>	
					<!--<th nowrap align="center" dbname="WorkNum" >组织</th>-->				
				</tr>
				
				<c:forEach items="${page.result}" var="rst" varStatus="row">
				
					<c:if test="${empty page.errorMsg}">					
				
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}" >
						<td align="center">${rst.DeliverOrderNo}&nbsp;</td>
						<td align="center">${rst.SaleOrderNo}&nbsp;</td>
						<td align="center">${rst.CustNo}&nbsp;</td>
						<td align="center">${rst.CustName}&nbsp;</td>
						<td align="center">${rst.PrdNo}&nbsp;</td>
						<td align="center">${rst.PrdName}&nbsp;</td>
						<td align="center">${rst.PrdSpec}&nbsp;</td>
						<td align="center">${rst.PlanNum}&nbsp;</td>
						<td align="center">${rst.StoreNum}&nbsp;</td>						
						<td align="center">${rst.StroeInNum}&nbsp;</td>
						<td align="center">${rst.WorkNum}&nbsp;</td>
						<!--<td align="center">${rst.LEDGER_NAME}&nbsp;</td>-->
															
					</tr>
					</c:if>
				</c:forEach>
				
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="15" align="center" class="lst_empty">							
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
				</c:if>
				
				<c:if test="${not empty page.errorMsg}">
					<tr>
						<td nowrap height="25" colspan="15" align="center" class="lst_empty">							
							&nbsp;${page.errorMsg}&nbsp;
						</td>
					</tr>
				</c:if>
					
			</table>
		</div>
	</td>
</tr>
<tr>
	<td height="12px" align="center">
		
	</td>
</tr>
</table>

</body>

<script type="text/javascript">
$(function(){	
$("#btn_query",parent.document).removeAttr("disabled");
});
</script>

</html>