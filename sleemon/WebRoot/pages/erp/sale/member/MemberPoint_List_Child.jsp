<!--
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time   2014-12-03 10:35:10  
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
	<title>卡券</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/member/MemberPoint_List_Child.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		.opendiv{
			display: none;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
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
                    <th  nowrap="nowrap" align="center" dbname="BUS_TYPE" >业务类型</th>
                    <th  nowrap="nowrap" align="center" dbname="BUS_ORDER_NO" >业务编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CURR_POINT" >变更积分</th>
                    <th  nowrap="nowrap" align="center" dbname="NEW_POINT" >变更后积分</th>
                    <th  nowrap="nowrap" align="center" dbname="POINT_EDIT_TIME" >积分变更时间</th> 
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
                    <tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr{rr}" onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">					 <td width="1%"align='center' >
						<input type="checkbox"  style="valign:middle"  id="eid${rr}" value="${rst.MEMBER_POINT_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.BUS_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BUS_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CURR_POINT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.NEW_POINT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.POINT_EDIT_TIME}&nbsp;</td>
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
	<input type="hidden" id="MEMBER_POINT_DTL_IDS" name="MEMBER_POINT_DTL_IDS" value=""/>
</form>
</body>
</html>
 