<!--
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
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
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
                    <th  nowrap="nowrap" align="center" dbname="BILL_NO" >单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ACTION" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="ACTOR" >操作人</th>
                    <th  nowrap="nowrap" align="center" dbname="ACT_TIME" >操作时间</th>
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >状态</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  >
                     <td nowrap="nowrap" align="center" >${rst.BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.ACTION}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.ACTOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.ACT_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.REMARK}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="6" align="center" class="lst_empty">
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
	<input type="hidden" id="ADVC_ORDER_DTL_IDS" name="ADVC_ORDER_DTL_IDS" value=""/>
</form>
</body>
<script type="text/javascript">
	
</script>
</html>