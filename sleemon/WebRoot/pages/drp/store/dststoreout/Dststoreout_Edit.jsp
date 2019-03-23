<!--
/**
 * @prjName:供应链_贵人
 * @fileName:Dststoreout_Edit
 * @author zsl
 * @time   2016-01-11 15:05:08 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store//dststoreout/Dststoreout_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：库存管理&gt;&gt;出库管理&gt;&gt;预订单出库维护编辑</td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
       <input id="selectSTORECondition" name="selectSTORECondition" type="hidden" value=" "/>
       <input id="selectAdvc" name="selectAdvc" type="hidden" value=" TERM_ID='${BMXXID}' or LEDGER_ID='${BMXXID}' "/>
       <input id="BMXXID" value="${BMXXID}" type="hidden"/>
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
               		<td width="15%" align="right" class="detail_label">出库单编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="STOREOUT_NO" name="STOREOUT_NO" autocheck="true" label="出库单编号"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="30"
                     <c:if test="${rst.STOREOUT_NO==null}"> value="自动生成"</c:if>
                     	<c:if test="${rst.STOREOUT_NO!=null}">value="${rst.STOREOUT_NO}"</c:if>  
                     />
				   </td>
                   <td width="15%" align="right" class="detail_label">来源单据编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="FROM_BILL_NO" id="FROM_BILL_NO" name="FROM_BILL_NO" autocheck="true" label="来源单据NO"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="30"  value="${rst.FROM_BILL_NO}"/>
                     <input json="FROM_BILL_ID" name="FROM_BILL_ID" id="FROM_BILL_ID" autocheck="true" label="来源单据ID" type="hidden" inputtype="string"   value="${rst.FROM_BILL_ID}"/>
                     <img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selAdvcorder()">  
				   </td>
               </tr> 
               <tr>
               		<td width="15%" align="right" class="detail_label">终端编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NO" name="TERM_NO" autocheck="true" label="终端编号"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="30"  value="${rst.TERM_NO}"/>
                     <input json="TERM_ID" name="TERM_ID" autocheck="true" label="终端信息ID" type="hidden" inputtype="string"   value="${rst.TERM_ID}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">终端名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NAME" name="TERM_NAME" autocheck="true" label="终端名称"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="100"  value="${rst.TERM_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" autocheck="true" label="电话"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="30"  value="${rst.TEL}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">要求到货日期：</td>
				   <td width="35%" class="detail_content">
                     <input json="ORDER_RECV_DATE" name="ORDER_RECV_DATE" autocheck="true" label="要求到货日期"  type="text"   inputtype="string"   readonly     mustinput="true"   value="${rst.ORDER_RECV_DATE}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">业务员名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="SALE_PSON_NAME" name="SALE_PSON_NAME" autocheck="true" label="业务员名称"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="30"  value="${rst.SALE_PSON_NAME}"/>
                     <input json="SALE_PSON_ID" name="SALE_PSON_ID" autocheck="true" label="业务员ID"  type="hidden"   inputtype="string"         maxlength="32"  value="${rst.SALE_PSON_ID}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">销售日期：</td>
				   <td width="35%" class="detail_content">
                     <input json="SALE_DATE" name="SALE_DATE" autocheck="true" label="销售日期"  type="text"   inputtype="string"   readonly     mustinput="true"      value="${rst.SALE_DATE}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">出库总金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="STOREOUT_AMOUNT" name="STOREOUT_AMOUNT" autocheck="true" label="出库总金额"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="22"  value="${rst.STOREOUT_AMOUNT}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">客户名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CUST_NAME" name="CUST_NAME" autocheck="true" label="客户名称"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="100"  value="${rst.CUST_NAME}"/> 
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">发货方编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="SEND_CHANN_NO" name="SEND_CHANN_NO" autocheck="true" label="发货方编号"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="30"  value="${rst.SEND_CHANN_NO}"/>
                     <input json="SEND_CHANN_ID" id="SEND_CHANN_ID" name="SEND_CHANN_ID" autocheck="true" label="发货方ID" type="hidden" inputtype="string"   value="${rst.SEND_CHANN_ID}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">发货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="SEND_CHANN_NAME" name="SEND_CHANN_NAME" autocheck="true" label="发货方名称"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="100"  value="${rst.SEND_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">出库库房编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="STOREOUT_STORE_NO" name="STOREOUT_STORE_NO" autocheck="true" label="出库库房编号"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="30"  value="${rst.STOREOUT_STORE_NO}"/>
                     <input json="STOREOUT_STORE_ID" name="STOREOUT_STORE_ID" id="STOREOUT_STORE_ID" autocheck="true" label="出库库房ID" type="hidden" inputtype="string"   value="${rst.STOREOUT_STORE_ID}"/>
                     <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				         onclick="selStoreout()"/>  
				   </td>
				   <td width="15%" align="right" class="detail_label">出库库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME" autocheck="true" label="出库库房名称"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="100"  value="${rst.STOREOUT_STORE_NAME}"/> 
				   </td>
               </tr>
				<tr>
                    <td width="15%" align="right" class="detail_label">收货地址：</td>
					<td width="35%" class="detail_content" colspan="3">
                         <textarea  json="RECV_ADDR" name="RECV_ADDR" id="RECV_ADDR" autocheck="true"  inputtype="string" disabled="disabled"  maxlength="250"    label="收货地址"     rows="4" cols="80%" >${rst.RECV_ADDR}</textarea>
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="3">
                     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
				   </td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
</script>
 