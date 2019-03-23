<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_Edit
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/finance/custrefundorder/Custrefundorder_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" class="panel">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：财务管理>>财务结算>>退货退款单</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail" id="formtb">
       		<input id="ZTXX_ID" name="ZTXX_ID" json="ZTXX_ID" type='hidden' value="${rst.LEDGER_ID}">
			
			<input id="ADVC_PAYMENT_ID" json="ADVC_PAYMENT_ID" name="ADVC_PAYMENT_ID" type="hidden" value="${rst.ADVC_PAYMENT_ID}">
       		<input id="SALE_DATE_HIDDEN" json="SALE_DATE_HIDDEN" name="SALE_DATE_HIDDEN" type="hidden" value="${rst.SALE_DATE}">
			<input id="ORDER_RECV_DATE_HIDDEN" json="ORDER_RECV_DATE_HIDDEN" name="ORDER_RECV_DATE_HIDDEN" type="hidden" value="${rst.ORDER_RECV_DATE}">
			<input id="CRE_TIME" json="CRE_TIME" name="CRE_TIME" type="hidden" value="${rst.CRE_TIME}">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
	               <tr>
	                   <td width="15%" align="right" class="detail_label">退款单编号：</td>
					   <td width="35%" class="detail_content">
	                     <input json="STATEMENTS_NO" name="STATEMENTS_NO" readonly autocheck="true" label="收款单编号"  type="text"   inputtype="string"     mustinput="true"     maxlength="30"  value="${rst.STATEMENTS_NO}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label"></td>
					   <td width="35%" class="detail_content"></td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">退款类型：</td>
					   <td width="35%" class="detail_content">
					   	 <%--<select json="BILL_TYPE" id="BILL_TYPE"   name="BILL_TYPE" <c:if test="${not empty rst.STATEMENTS_ID}"> disabled="disabled" </c:if>
					   	  style="width:155px" label="退款类型" inputtype="string"   mustinput="true" onchange="changeBill(this);"></select>
					   --%>
					   <input type="text" id="BILL_TYPE" name="BILL_TYPE"  json="BILL_TYPE" value="终端退货退款" readonly="readonly"/>
					   </td>
					   <td width="15%" align="right" class="detail_label">来源单据编号：</td>
					   <td width="35%" class="detail_content">
					   	<input id="ADVC_ORDER_ID" json="ADVC_ORDER_ID" name="ADVC_ORDER_ID" type="hidden" value="${rst.ADVC_ORDER_ID}">
	                     <input id="ADVC_ORDER_NO" json="ADVC_ORDER_NO" name="ADVC_ORDER_NO" readonly autocheck="true" label="来源单据编号"  type="text"   inputtype="string"     mustinput="true"     maxlength="30"  value="${rst.ADVC_ORDER_NO}"/>
	                     <input id="con" json="con" name="con" type="hidden">
	                      <img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30" onClick="queryOrderNo();"> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">终端编号：</td>
					   <td width="35%" class="detail_content">
					   	 <input id="TERM_ID" json="TERM_ID" name="TERM_ID" type="hidden" value="${rst.TERM_ID}">
	                     <input id="TERM_NO" json="TERM_NO" name="TERM_NO" autocheck="true" readonly label="终端编号"  type="text"   inputtype="string"     mustinput="true"     maxlength="30"  value="${rst.TERM_NO}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">终端名称：</td>
					   <td width="35%" class="detail_content">
	                     <input id="TERM_NAME" json="TERM_NAME" name="TERM_NAME" autocheck="true" readonly label="终端名称"  type="text"   inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.TERM_NAME}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">销售日期：</td>
					   <td width="35%" class="detail_content">
	                     <input id="SALE_DATE" json="SALE_DATE" name="SALE_DATE" readonly autocheck="true" label="销售日期"  type="text"   inputtype="string"       value="${rst.SALE_DATE}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">业务员名称：</td>
					   <td width="35%" class="detail_content">
					   	  <input id="SALE_PSON_ID" json="SALE_PSON_ID" name="SALE_PSON_ID" type="hidden" value="${rst.SALE_PSON_ID}">
	                     <input id="SALE_PSON_NAME" json="SALE_PSON_NAME" name="SALE_PSON_NAME" readonly autocheck="true" label="业务员名称"  type="text"   inputtype="string"       maxlength="30"  value="${rst.SALE_PSON_NAME}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">客户名称：</td>
					   <td width="35%" class="detail_content">
	                     <input id="CUST_NAME" json="CUST_NAME" name="CUST_NAME" autocheck="true" readonly label="客户名称"  type="text"   inputtype="string"        maxlength="100"  value="${rst.CUST_NAME}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">电话：</td>
					   <td width="35%" class="detail_content">
	                     <input id="TEL" json="TEL" name="TEL" autocheck="true" label="电话" readonly type="text"   inputtype="string"        maxlength="30"  value="${rst.TEL}"/> 
					   </td>
	               </tr>
	               <tr>
	               		<!-- 要求到货日期： -->
	                   <td width="15%" align="right" class="detail_label"></td>
					   <td width="35%" class="detail_content">
	                     <input id="ORDER_RECV_DATE" json="ORDER_RECV_DATE" readonly name="ORDER_RECV_DATE" autocheck="true" label="要求到货日期"  type="hidden"   inputtype="string"       value="${rst.ORDER_RECV_DATE}"/> 
					   </td>
						<!--订金：-->
	                   <td width="15%" align="right" class="detail_label"></td>
					   <td width="35%" class="detail_content">
	                     <input id="ADVC_AMOUNT" json="ADVC_AMOUNT" name="ADVC_AMOUNT" autocheck="true" readonly label="订金"  type="hidden"   inputtype="string"        maxlength="22"  value="${rst.ADVC_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">已退款金额：</td>
					   <td width="35%" class="detail_content">
					     <input id="PAYED_TOTAL_AMOUNT_TEMP" json="PAYED_TOTAL_AMOUNT_TEMP" name="PAYED_TOTAL_AMOUNT_TEMP" autocheck="true" label="已退款金额" readonly  type="text"   inputtype="string"      value="${rst.PAYED_TOTAL_AMOUNT_TEMP}"/>
	                     <input id="PAYED_TOTAL_AMOUNT" json="PAYED_TOTAL_AMOUNT" name="PAYED_TOTAL_AMOUNT" autocheck="true" label="已退款金额"   type="hidden"   inputtype="string"     value="${rst.PAYED_TOTAL_AMOUNT}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label">应退款总额：</td>
					   <td width="35%" class="detail_content">
	                     <input id="PAYABLE_AMOUNT" json="PAYABLE_AMOUNT" name="PAYABLE_AMOUNT" autocheck="true" readonly label="应退款总额"  type="text"   inputtype="string"     mustinput="true"     maxlength="22"  value="${rst.PAYABLE_AMOUNT}"/> 
					   </td>
	               </tr>
	               <tr>
	               	   <td width="15%" align="right" class="detail_label">本次退款金额：</td>
					   <td width="35%" class="detail_content">
	                     <input id="STATEMENTS_AMOUNT" json="STATEMENTS_AMOUNT" name="STATEMENTS_AMOUNT" autocheck="true" label="本次退款金额"  type="text"   inputtype="string"     mustinput="true"   value="${rst.STATEMENTS_AMOUNT}"/> 
					   </td>
	                   <td width="18%" align="right" class="detail_label">结算日期：</td>
					   <td width="35%" class="detail_content">
	                     <input id="STATEMENT_DATE" json="STATEMENT_DATE" readonly name="STATEMENT_DATE" autocheck="true" label="结算日期"  type="text"   inputtype="string"   onclick="SelectDate();"  value="${rst.STATEMENT_DATE}"/> 
	                     <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(STATEMENT_DATE);"/>
					   </td>
	               </tr>
	               <tr>
	               	   <td width="15%" align="right" class="detail_label">附件上传：</td>
					   <td width="35%" class="detail_content">
	                     <input id="STATENEBTS_ATT" json="STATENEBTS_ATT" name="STATENEBTS_ATT" autocheck="true" type="text"   value="${rst.STATENEBTS_ATT}"/> 
					   </td>
	                   <td width="15%" align="right" class="detail_label"></td>
					   <td width="35%" class="detail_content"></td>
	               </tr>
	               <tr>
	                   <td width="15%" align="right" class="detail_label">备注：</td>
					   <td width="35%" class="detail_content" colspan="3">
					   	 <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="5" cols="80%" >${rst.REMARK}</textarea>
					   </td>
	               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
	//SelDictShow("BILL_TYPE","BS_49","${rst.BILL_TYPE}","");
	uploadFile('STATENEBTS_ATT', 'SAMPLE_DIR', true);
</script>
 