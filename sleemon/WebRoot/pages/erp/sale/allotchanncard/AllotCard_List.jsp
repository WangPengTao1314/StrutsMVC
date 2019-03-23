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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/allotchanncard/AllotCard_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	 
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area" >
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
				    <th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="MARKETING_CARD_NO" >营销卡券编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >分配的加盟商编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >分配的加盟商名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CARD_TYPE" >卡券类型</th>
                    <th  nowrap="nowrap" align="center" dbname="CARD_VALUE" >卡券面值</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >创建时间</th>
                    <th  nowrap="nowrap" align="center" dbname="CARD_SEQ_NO" >卡券序号</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th> 
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					 <tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="valign:middle" name='eid' id="eid${rr}" value="${rst.MARKETING_CARD_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.MARKETING_CARD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CARD_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CARD_VALUE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CARD_SEQ_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STATE}&nbsp;</td>
                      <input type="hidden" id="" name="CHANN_ID" value="${rst.CHANN_ID}"/>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
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
<tr>
	<td height="12px" align="center">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" class="lst_toolbar">
			<tr>
				<td>
					<form id="pageForm" action="#" method="post" name="listForm">
					<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
						<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
						<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
						<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
						<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
						<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
						<span id="hidinpDiv" name="hidinpDiv"></span>
						${paramCover.unCoveredForbidInputs }
					</form>
				</td>
				<td align="right">
					 ${page.footerHtml}${page.toolbarHtml}
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<form id="queryForm" action="#" method="post" >
	<input type="hidden" id="CHANN_ID" name="CHANN_ID" value=""/>
	<input type="hidden" id="CHANN_NO" name="CHANN_NO" value=""/>
	<input type="hidden" id="CHANN_NAME" name="CHANN_NAME" value=""/>
	<input type="hidden" id="MARKETING_CARD_NO" name="MARKETING_CARD_NO" value=""/>
	<input type="hidden" id="CARD_SEQ_NO_BEGIN" name="CARD_SEQ_NO_BEGIN" value=""/>
	<input type="hidden" id="CARD_SEQ_NO_END" name="CARD_SEQ_NO_END" value=""/>
	<input type="hidden" id="MARKETING_ACT_NO" name="MARKETING_ACT_NO" value=""/>
	<input type="hidden" id="MARKETING_ACT_NAME" name="MARKETING_ACT_NAME" value=""/>
	<input type="hidden" id="MARKETING_ACT_ID" name="MARKETING_ACT_ID" value=""/>
	<input type="hidden" id="notallot" name="notallot" value=""/>
	 
</form>
</body>
</html>
 