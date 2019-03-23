<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Restatements_Edit
 * @author chenj
 * @time   2013-10-12 15:21:43 
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
	<script type="text/javascript" src="${ctx}/pages/drp/finance/restatements/Restatements_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<div class="buttonlayer" id="floatDiv">
      <table cellSpacing=0 cellPadding=0 border=0 width="100%">
		<tr>
		   <td align="left" nowrap>
		   	<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
			<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick='parent.$("#label_1").click();'>
			</td>
		</tr>
	</table>
</div>
<!--浮动按钮层结束-->
<!--占位用table，以免显示数据被浮动层挡住-->
<table width="100%" height="25px">
    <tr>
        <td>&nbsp;</td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td>
		<form name="form" id="mainForm">
		<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<input id="ZTXX_ID" name="ZTXX_ID" json="ZTXX_ID" type='hidden' value="${rst.LEDGER_ID}">
			<input id="TERM_ID" json="TERM_ID" name="TERM_ID" type="hidden" value="${rst.TERM_ID}">
			<input id="ADVC_ORDER_ID" json="ADVC_ORDER_ID" name="ADVC_ORDER_ID" type="hidden" value="${rst.ADVC_ORDER_ID}">
			<input id="STATEMENTS_ID" json="STATEMENTS_ID" name="STATEMENTS_ID" type="hidden" value="${rst.STATEMENTS_ID}">
			<input id="STOREIN_ID" json="STOREIN_ID" name="STOREIN_ID" type="hidden" value="${rst.STOREIN_ID}">
			
			<input id="SALE_DATE_HIDDEN" json="SALE_DATE_HIDDEN" name="SALE_DATE_HIDDEN" type="hidden" value="${rst.SALE_DATE}">
			<input id="ADD_ADVC_AMOUNT_HIDDEN" json="ADD_ADVC_AMOUNT_HIDDEN" name="ADD_ADVC_AMOUNT_HIDDEN" type="hidden" />
			<input id="LAST_PAYABLE_AMOUNT_HIDDEN" json="LAST_PAYABLE_AMOUNT_HIDDEN" name="LAST_PAYABLE_AMOUNT_HIDDEN" type="hidden" />
		<tr>
			<td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="15%" align="right" class="detail_label">客户退货结算编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="STATEMENTS_NO" name="STATEMENTS_NO" autocheck="true" label="客户退货结算编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.STATEMENTS_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">终端退货编号：</td>
				   <td width="35%" class="detail_content">
                      <input json="ADVC_ORDER_NO" name="ADVC_ORDER_NO" autocheck="true" label="终端退货编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.ADVC_ORDER_NO}"/>
                      <input id="con" json="con" name="con" type="hidden">
	                      <img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30" onClick="queryOrderNo();"> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">终端编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NO" name="TERM_NO" autocheck="true" label="终端编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.TERM_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">终端名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NAME" name="TERM_NAME" autocheck="true" label="终端名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.TERM_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">销售日期：</td>
				   <td width="35%" class="detail_content">
                     <input id="SALE_DATE" json="SALE_DATE" name="SALE_DATE" autocheck="true" label="销售日期"  type="text"   inputtype="string"   readonly    mustinput="true"     value="${rst.SALE_DATE}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">业务员名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="SALE_PSON_NAME" name="SALE_PSON_NAME" autocheck="true" label="业务员名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.SALE_PSON_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">客户名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CUST_NAME" name="CUST_NAME" autocheck="true" label="客户名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.CUST_NAME}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" autocheck="true" label="电话"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.TEL}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">订金金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADVC_AMOUNT" name="ADVC_AMOUNT" autocheck="true" label="订金金额"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="22"  value="${rst.ADVC_AMOUNT}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">已收剩余金额：</td>
				   <td width="35%" class="detail_content">
                     <input id="RECV_LAST_AMOUNT" json="RECV_LAST_AMOUNT" name="RECV_LAST_AMOUNT" autocheck="true" label="已收剩余金额"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="22"  value="${rst.RECV_LAST_AMOUNT}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">退货金额：</td>
				   <td width="35%" class="detail_content">
                     <input id="STATEMENTS_AMOUNT" json="STATEMENTS_AMOUNT" name="STATEMENTS_AMOUNT" autocheck="true" label="退货金额"  type="text"   inputtype="string"     mustinput="true"     maxlength="22"  value="${rst.STATEMENTS_AMOUNT}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content"></td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="85%" class="detail_content" colspan="3">
				   	 <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="5" cols="120%" >${rst.REMARK}</textarea>
				   </td>
               </tr>
			</table>
			</td>
		</tr>
		</table>
		</form>
	</td>
	</tr>
</table>
</body>
</html>
<script type="text/javascript">
</script>
 