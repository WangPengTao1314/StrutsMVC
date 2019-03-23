<!--
 * @prjName:喜临门营销平台
 * @fileName: 
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
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/marketcardsale/MarketcardSale_Child_List.js"></script>
    <script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
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
				   	    <c:if test="${pvg.PVG_OPEN_CLOSE eq 1 }">
				   		<input id="open" type="button" class="btn" value="开启(O)" title="Alt+O" accesskey="O">
				   		<input id="close" type="button" class="btn" value="关闭(C)" title="Alt+C" accesskey="C">
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
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="MARKETING_CARD_NO" >卡券编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CARD_TYPE" >卡券类型</th>
                    <th  nowrap="nowrap" align="center" dbname="CARD_VALUE" >卡券面值</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >发放日期</th>
                    <th  nowrap="nowrap" align="center" dbname="CUST_NAME" >客户名称</th>
                    <th  nowrap="nowrap" align="center" dbname="MOBILE_PHONE" >手机</th>
                    <th  nowrap="nowrap" align="center" dbname="PAYABLE_AMOUNT" >收款额</th>
                    <th  nowrap="nowrap" align="center" dbname="SEX" >性别</th> 
                    <th  nowrap="nowrap" align="center" dbname="ADDRESS" >住址</th>
                    <%--
                    <th  nowrap="nowrap" align="center" dbname="REGIST_FLAG" >签到状态</th>
                    --%>
                    <th  nowrap="nowrap" align="center" dbname="REGIST_STATE" >签到状态</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >操作状态</th> 
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >客户需求（意向购买）</th>
                    <th  nowrap="nowrap" align="center" dbname="BIRTHDAY" >生日</th>
                    <th  nowrap="nowrap" align="center" dbname="HOBBY" >爱好</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr{rr}" onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox" name="mx_checkbox"  style="valign:middle"  id="eid${rr}"  value="${rst.CARD_SALE_ORDER_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
					 <td nowrap="nowrap" align="center" >${rst.MARKETING_CARD_NO} </td>
                     <td nowrap="nowrap" align="center" >${rst.CARD_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CARD_VALUE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.CUST_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.MOBILE_PHONE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.PAYABLE_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.SEX}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.ADDRESS}&nbsp;</td><%--
                     <td nowrap="nowrap" align="center" >
                      <c:if test="${empty rst.REGIST_FLAG or 0 eq rst.REGIST_FLAG}"> 未签到</c:if>
                      <c:if test="${1 eq rst.REGIST_FLAG}">     ${rst.REGIST_STATE}&nbsp; </c:if>
                     </td>
                     --%>
                     <td nowrap="nowrap" align="center" >${rst.REGIST_STATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STATE}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.REMARK}&nbsp;</td>
                      <td nowrap="nowrap" align="center" >${rst.BIRTHDAY}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.HOBBY}&nbsp;</td>
                     <input type="hidden" name="STATE" value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="17" align="center" class="lst_empty">
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
	<input type="hidden" id="CARD_SALE_ORDER_DTL_IDS" name="CARD_SALE_ORDER_DTL_IDS" value=""/>
	<input type="hidden" id="CARD_SALE_ORDER_ID" name="CARD_SALE_ORDER_ID" value=""/>
</form>
</body>
</html>