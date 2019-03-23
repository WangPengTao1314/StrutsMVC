<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcgoodsapp_Edit
 * @author lyg
 * @time   2013-11-02 18:55:53 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale//advcgoodsapp/Advcgoodsapp_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;终端销售&gt;&gt;预订单发货申请</td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<input  name="selectParams" type="hidden" value=" STATE = '启用' and  DEL_FLAG=0  "/>
				<input  name="selectTERM" type="hidden" value=" STATE ='启用' and  DEL_FLAG=0 and (TERM_ID='${BEN_DETP_ID}' or CHANN_ID_P='${BEN_DETP_ID}')"/>
				<input  name="selectADC" id="selectADC" type="hidden" value=" TERM_ID in ${TERM_CHARGE } "/>
				<input id="TERM_CHARGE" type="hidden" lable="终端分管" value="${TERM_CHARGE}"/>
                <input id="selectSTORECondition" name="selectSTORECondition" type="hidden" value=" "/>
                <input id="channId" type="hidden" value="${channInfo.SEND_CHANN_ID}"/>
                <input id="channNo" type="hidden" value="${channInfo.SEND_CHANN_NO}"/>
                <input id="channName" type="hidden" value="${channInfo.SEND_CHANN_NAME}"/>
               <tr>
                   <td width="15%" align="right" class="detail_label">发货申请单编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADVC_SEND_REQ_NO" name="ADVC_SEND_REQ_NO" autocheck="true" label="发货申请单编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"
                     	<c:if test="${rst.ADVC_SEND_REQ_NO eq null}">
                     		value="系统自动生成"
                     	</c:if> 
                     	<c:if test="${rst.ADVC_SEND_REQ_NO != null}">
                     		value="${rst.ADVC_SEND_REQ_NO}"
                     	</c:if> 
                      /> 
				   </td>
                   <td width="15%" align="right" class="detail_label">发货方类型：</td>
				   <td width="35%" class="detail_content">
                   	<select id="SEND_TYPE"   <c:if test="${rst.ADVC_SEND_REQ_NO != null}">disabled=true</c:if>   json="SEND_TYPE" onchange="showSendChann();" name="SEND_TYPE" style="width: 154px;" mustinput="true" autocheck="true" label="发货方类型" inputtype="string" ></select>
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">发货方编号：</td>
				   <td width="35%" class="detail_content">
				   	<input json="SEND_CHANN_ID" id="SEND_CHANN_ID" name="SEND_CHANN_ID" autocheck="true" label="发货方id"  type="hidden"   inputtype="string"     value="${rst.SEND_CHANN_ID}"/>
                     <input json="SEND_CHANN_NO" id="SEND_CHANN_NO" name="SEND_CHANN_NO" autocheck="true" label="发货方编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.SEND_CHANN_NO}"/>
                     <img align="absmiddle" name="" style="cursor: hand" id="imgSend"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selComm()"> 
				   </td>
                   <td width="15%" align="right" class="detail_label">发货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  id="SEND_CHANN_NAME" autocheck="true" label="发货方名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.SEND_CHANN_NAME}"/>
                      
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">来源单据编号：</td>
				   <td width="35%" class="detail_content">
				     <input json="CONTRACT_NO" id="CONTRACT_NO" name="CONTRACT_NO"   label="合同编号" type="hidden"    value="${rst.CONTRACT_NO}"/>
				     <input json="FROM_BILL_ID" id="FROM_BILL_ID" name="FROM_BILL_ID" autocheck="true" label="来源单据ID" type="hidden" inputtype="string"   value="${rst.FROM_BILL_ID}"/>
                     <input json="FROM_BILL_NO" name="FROM_BILL_NO" id="FROM_BILL_NO" autocheck="true" label="来源单据编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.FROM_BILL_NO}"/>
                      <img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selAdvcorder()"> 
				   </td>
                   <td width="15%" align="right" class="detail_label">销售日期：</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="SALE_DATE" id="SALE_DATE"name="SALE_DATE" autocheck="true" inputtype="string" label="销售日期"  value="${rst.SALE_DATE}"   readonly="readonly" />
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">终端编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="TERM_ID" name="TERM_ID" autocheck="true" label="终端id"  type="hidden"   inputtype="string"  value="${rst.TERM_ID}"/>
                     <input json="TERM_NO" name="TERM_NO" autocheck="true" label="终端编号"  type="text"   inputtype="string"   readonly       maxlength="30"  value="${rst.TERM_NO}"/>
				   </td>
                   <td width="15%" align="right" class="detail_label">终端名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NAME" name="TERM_NAME" autocheck="true" label="终端名称"  type="text"   inputtype="string"   readonly       maxlength="100"  value="${rst.TERM_NAME}"/> 
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">业务员：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="SALE_PSON_ID" name="SALE_PSON_ID" autocheck="true" label="业务员ID"  type="hidden"   inputtype="string"        maxlength="32"  value="${rst.SALE_PSON_ID}"/>
                     <input json="SALE_PSON_NAME" name="SALE_PSON_NAME" autocheck="true" label="业务员"  type="text"   inputtype="string"   readonly       maxlength="30"  value="${rst.SALE_PSON_NAME}"/>
				   </td>
				   <td width="15%" align="right" class="detail_label">客户名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CUST_NAME" name="CUST_NAME" autocheck="true" label="客户名称"  type="text"   inputtype="string"   readonly     maxlength="100"  value="${rst.CUST_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" autocheck="true" label="电话"  type="text"   inputtype="string"   readonly     maxlength="30"  value="${rst.TEL}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">要求到货日期：</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="ORDER_RECV_DATE" id="ORDER_RECV_DATE" name="ORDER_RECV_DATE" autocheck="true" inputtype="string" label="要求到货日期"  value="${rst.ORDER_RECV_DATE}" onclick="SelectDate();"  mustinput="true"  readonly />
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE);">
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">应收总额：</td>
				   <td width="35%" class="detail_content">
                     <input json="PAYABLE_AMOUNT" name="PAYABLE_AMOUNT" autocheck="true" label="应收总额"  type="text" readonly  inputtype="string"        maxlength="30"  value="${rst.PAYABLE_AMOUNT}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">订金金额：</td>
					<td width="35%" class="detail_content">
						<input json="ADVC_AMOUNT" name="ADVC_AMOUNT" autocheck="true" label="订金金额"    mustinput="true"  type="text"  readonly inputtype="string"        maxlength="30"  value="${rst.ADVC_AMOUNT}"/>
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">已付款：</td>
					<td width="35%" class="detail_content">
						<input json="PAYED_TOTAL_AMOUNT" name="PAYED_TOTAL_AMOUNT" autocheck="true" label="已付款"  type="text" readonly  inputtype="string"        maxlength="30"  value="${rst.PAYED_TOTAL_AMOUNT}"/>
					</td>
                   <td width="15%" align="right" class="detail_label">出库库房编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="STOREOUT_STORE_ID" name="STOREOUT_STORE_ID" id="STOREOUT_STORE_ID" autocheck="true" label="出库库房id"  type="hidden"    value="${rst.STOREOUT_STORE_ID}"/>
                     <input json="STOREOUT_STORE_NO" id="STOREOUT_STORE_NO" name="STOREOUT_STORE_NO" autocheck="true" label="出库库房编号"  type="text"   inputtype="string"   readonly      mustinput="true"     maxlength="30"  value="${rst.STOREOUT_STORE_NO}"/>
                     <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				         onclick="selStoreout()"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">出库库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME" id="STOREOUT_STORE_NAME" autocheck="true" label="出库库房名称"  type="text"    mustinput="true"   inputtype="string"   readonly       maxlength="100"  value="${rst.STOREOUT_STORE_NAME}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content">
				</td>
               </tr>
              <tr>
                    <td width="15%" align="right" class="detail_label">收货地址：</td>
					<td width="35%" class="detail_content" colspan="3">
                         <textarea  json="RECV_ADDR" name="RECV_ADDR" id="RECV_ADDR" autocheck="true" inputtype="string"   maxlength="250"    label="收货地址"     rows="4" cols="80%" >${rst.RECV_ADDR}</textarea>
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
	SelDictShow("SEND_TYPE","BS_55","${rst.SEND_TYPE}","");
	showSendChann('${rst.SEND_TYPE}');
</script>
 