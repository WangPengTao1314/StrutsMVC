<!-- 
 *@module 销售报表
 *@func 退货单查询
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
	<title>退货单列表</title>
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
					<th nowrap align="center" dbname="RETURN_NO" >退货单号</th>
					<th nowrap align="center" dbname="CUST_NO" >客户编号</th>
					<th nowrap align="center" dbname="CUST_NAME" >客户名称</th>
					<th nowrap align="center" dbname="PRD_SN" >商品序列号</th>
					<th nowrap align="center" dbname="PRD_NO" >商品编号</th>
                    <th nowrap align="center" dbname="PRD_NAME" >商品名称</th>
					<th nowrap align="center" dbname="PRD_SPEC" >规格型号</th>
					<th nowrap align="center" dbname="STORE_IN_NUM" >入库数量</th>
					<th nowrap align="center" dbname="RETURN_PRICE" >退货单价</th>
					<th nowrap align="center" dbname="RETURN_AMOUNT" >退货金额</th>
					<th nowrap align="center" dbname="STORE_IN_TIME" >入库时间</th>
					<th nowrap align="center" dbname="STORE_IN_USERNAME" >入库处理人姓名</th>
					<th nowrap align="center" dbname="FNC_AUDIT_TIME" >财务审核时间</th>
					<th nowrap align="center" dbname="FNC_AUDIT_USERNAME" >财务审核人姓名</th>		
				</tr>
				
				<c:forEach items="${page.result}" var="rst" varStatus="row">
				
					<c:if test="${empty page.errorMsg}">					
				
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}" >
						<td align="center">${rst.RETURN_NO}&nbsp;</td>
						<td align="center">${rst.CUST_NO}&nbsp;</td>
						<td align="center">${rst.CUST_NAME}&nbsp;</td>
						<td align="center">${rst.PRD_SN}&nbsp;</td>
						<td align="center">${rst.PRD_NO}&nbsp;</td>
						<td align="center">${rst.PRD_NAME}&nbsp;</td>
						<td align="center">${rst.PRD_SPEC}&nbsp;</td>
						<td align="center">${rst.STORE_IN_NUM}&nbsp;</td>
						<td align="center">${rst.RETURN_PRICE}&nbsp;</td>
						<td align="center">${rst.RETURN_AMOUNT}&nbsp;</td>
						<td align="center">${rst.STORE_IN_TIME}&nbsp;</td>
						<td align="center">${rst.STORE_IN_USERNAME}&nbsp;</td>
						<td align="center">${rst.FNC_AUDIT_TIME}&nbsp;</td>
						<td align="center">${rst.FNC_AUDIT_USERNAME}&nbsp;</td>							
					</tr>
					</c:if>
				</c:forEach>
				
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="14" align="center" class="lst_empty">							
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
				</c:if>
				
				<c:if test="${not empty page.errorMsg}">
					<tr>
						<td nowrap height="25" colspan="14" align="center" class="lst_empty">							
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