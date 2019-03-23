<!--
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List
 * @author lyg
 * @time   2013-08-11 17:17:29 
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/sale/advctoorder/Advctoorder_List_shift.js"></script>
</head>
<body >
<table width="99.8%" height="95%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<div align="center"><h2 align="center">预订单选取</h2></div>
	<td valign="top">
	<div>
		<form id="queryForm" method="post" action="advctoorder.shtml?action=toListShift">
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
		               <tr>
		               		<td width="10%" nowrap align="right" class="detail_label">销售日期从:</td>
							<td width="20%" class="detail_content">
			   					<input id="SALE_DATE_BEG" name="SALE_DATE_BEG" readonly="readonly"  onclick="SelectDate();"  style="width:155" value="${params.SALE_DATE_BEG }">
			   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_BEG);"  >
							</td>
							<td width="10%" nowrap align="right" class="detail_label">销售日期到:</td>
							<td width="20%" class="detail_content">
			   					<input id="SALE_DATE_END" name="SALE_DATE_END" readonly="readonly"  onclick="SelectDate();"   style="width:155" value="${params.SALE_DATE_END }">
			   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_END);" >
							</td>
							<td width="10%" nowrap align="right" class="detail_label">要求到货日期从:</td>
							<td width="20%" class="detail_content">
			   					<input id="ORDER_RECV_DATE_BEG" name="ORDER_RECV_DATE_BEG" readonly="readonly"  onclick="SelectDate();"  style="width:155" value="${params.ORDER_RECV_DATE_BEG }">
			   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE_BEG);"  >
							</td>
							<td width="10%" nowrap align="right" class="detail_label">要求到货日期到:</td>
							<td width="20%" class="detail_content">
			   					<input id="ORDER_RECV_DATE_END" name="ORDER_RECV_DATE_END" readonly="readonly"  onclick="SelectDate();"   style="width:155" value="${params.ORDER_RECV_DATE_END }">
			   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE_END);" >
							</td>
		               </tr>
		               <tr>
		               		<td width="10%" nowrap align="right" class="detail_label">制单日期从:</td>
							<td width="20%" class="detail_content">
			   					<input id="CRE_TIME_BEG" name="CRE_TIME_BEG" readonly="readonly"  onclick="SelectDate();"  style="width:155" value="${params.CRE_TIME_BEG }">
			   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CRE_TIME_BEG);"  >
							</td>
							<td width="10%" nowrap align="right" class="detail_label">制单日期到:</td>
							<td width="20%" class="detail_content">
			   					<input id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"  onclick="SelectDate();"   style="width:155" value="${params.CRE_TIME_END }">
			   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CRE_TIME_END);" >
							</td>
							<td width="10%" nowrap align="right" class="detail_label">预订单编号：</td>
							<td width="20%" class="detail_content">
			   					<input id="ADVC_ORDER_NO" name="ADVC_ORDER_NO"   style="width:155" value="${params.ADVC_ORDER_NO }">
							</td>
							<td width="10%" nowrap align="right" class="detail_label"></td>
							<td width="20%" class="detail_content">
							</td>
		               </tr>
		               <tr>
		               		<td align="right" width="100%" class="detail_content" colspan="8">
								<input id="search" type="submit" class="btn" value="查询(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
								<input id="reset"  type="button" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
							</td>
		               </tr>
					</table>
				</td>
			</tr>
		</table>
		</form>
		</div>
		<div class="lst_area" style="height: 80%">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_ORDER_NO" >预订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NO" >终端编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >终端名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CUST_NAME" >客户姓名</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_AMOUNT" >订金金额</th>
                    <th  nowrap="nowrap" align="center" dbname="PAYABLE_AMOUNT" >应收总额</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_PSON_NAME" >业务员</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_DATE" >销售日期</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_RECV_DATE" >要求到货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_DATE" >制单日期</th>
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));" >
					 <td width="1%"align='center' >
						<input type="checkbox" style="height:12px;valign:middle" json="ADVC_ORDER_DTL_ID" id="eid${rr}" name="ADVC_ORDER_DTL_ID" value="${rst.ADVC_ORDER_ID}" onclick="setEidChecked(this);">
					 </td>
                     <td nowrap="nowrap" align="center">${rst.ADVC_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TERM_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.TERM_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CUST_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.ADVC_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.PAYABLE_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.SALE_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SALE_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_RECV_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.REMARK}</td>
                    <input id="state${rst.ADVC_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page}">
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
<tr>
					<td colspan="10" align="center" height="15%">
						<input id="nextStep" type="button" class="btn" value="下一步(S)" title="Alt+S" accesskey="S">&nbsp;&nbsp;
						<input id="close" type="button" class="btn" value="关闭(C)" title="Alt+C" accesskey="C">&nbsp;&nbsp;
					</td>
				</tr>
</table>

</body>
</html>
