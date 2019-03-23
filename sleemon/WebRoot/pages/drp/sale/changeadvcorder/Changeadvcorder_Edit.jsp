<!--
 * @prjName:喜临门营销平台
 * @fileName:Changeadvcorder_Frame
 * @author ghx
 * @time   2014-05-20 15:14:52 
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/sale/changeadvcorder/Changeadvcorder_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>预订单变更申请明细</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;终端销售&gt;&gt;预订单变更申请编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
                    <input type="hidden" name=selectParams value="STATE ='审核通过' and DEL_FLAG=0 and ${rst.CONDISION}">
               <tr>
                    <td width="20%" align="right" class="detail_label">预订单变更编号：</td>
					<td width="35%" class="detail_content">
                       <input json="ADVC_ORDER_CHANGE_NO" name="ADVC_ORDER_CHANGE_NO" autocheck="true" label="预订单变更编号" mustinput="true"  type="text"  inputtype="string"  
                        readonly    maxlength="30" 
                        <c:if test="${rst.ADVC_ORDER_CHANGE_NO==null}"> value="自动生成"</c:if>
                     	<c:if test="${rst.ADVC_ORDER_CHANGE_NO!=null}">value="${rst.ADVC_ORDER_CHANGE_NO}"</c:if>
                        /> 
					</td>
                    <td width="20%" align="right" class="detail_label">来源单据编号：</td>
					<td width="35%" class="detail_content">						
						<input json="ADVC_ORDER_ID" id="FROM_BILL_ID" name="FROM_BILL_ID" autocheck="true" label="来源单据ID" type="hidden" inputtype="string"   value="${rst.ADVC_ORDER_ID}"/> 
                        <input json="ADVC_ORDER_NO" name="FROM_BILL_NO" id="FROM_BILL_NO" autocheck="true" label="来源单据编号"  type="text" mustinput="true" inputtype="string"   readonly    maxlength="30"  value="${rst.ADVC_ORDER_NO}"/>
                        <img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selAdvcorder()"> 
						<input json="CRE_TIME" id="CRE_TIME" name="CRE_TIME" autocheck="true" label="制单时间" type="hidden" inputtype="string"   value="${rst.CRE_TIME}"/>
						<input json="OLD_CUST_NAME" id="OLD_CUST_NAME" name="OLD_CUST_NAME" autocheck="true" label="原客户名称" type="hidden" inputtype="string"   value="${rst.OLD_CUST_NAME}"/>
						<input json="OLD_TEL" id="OLD_TEL" name="OLD_TEL" autocheck="true" label="原电话" type="hidden" inputtype="string"   value="${rst.OLD_TEL}"/>
						<input json="OLD_RECV_ADDR" id="OLD_RECV_ADDR" name="OLD_RECV_ADDR" autocheck="true" label="原收货地址" type="hidden" inputtype="string"   value="${rst.OLD_RECV_ADDR}"/>
					</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">终端编号：</td>
					<td width="35%" class="detail_content">
						<input json="TERM_ID" name="TERM_ID" id="TERM_ID" autocheck="true" label="终端信息ID" type="hidden" inputtype="string"   value="${rst.TERM_ID}"/>
                        <input json="TERM_NO" name="TERM_NO" id="TERM_NO" autocheck="true" label="终端编号"  type="text" mustinput="true"  inputtype="string"   readonly    maxlength="30"  value="${rst.TERM_NO}"/> 
					</td>
                   <td width="15%" align="right" class="detail_label">终端名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME" autocheck="true" label="终端名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.TERM_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">客户姓名：</td>
				   <td width="35%" class="detail_content">
                     <input json="CUST_NAME" name="CUST_NAME" id="CUST_NAME" autocheck="true" label="客户姓名"  type="text"   inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.CUST_NAME}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" autocheck="true" id="TEL" label="电话"  type="text"   inputtype="string"     mustinput="true"     maxlength="30"  value="${rst.TEL}"/> 
				   </td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">业务员：</td>
					<td width="35%" class="detail_content">
						<input json="SALE_PSON_ID" name="SALE_PSON_ID" id="SALE_PSON_ID" autocheck="true" label="业务员ID" type="hidden" inputtype="string"   value="${rst.SALE_PSON_ID}"/> 
                        <input json="SALE_PSON_NAME" name="SALE_PSON_NAME" id="SALE_PSON_NAME" autocheck="true" label="业务员"  type="text" mustinput="true" inputtype="string"   readonly    maxlength="30"  value="${rst.SALE_PSON_NAME}"/> 
					</td>
                   <td width="15%" align="right" class="detail_label">销售日期</td>
				   <td width="35%" class="detail_content">
				   		<input type="text" json="SALE_DATE" id="SALE_DATE" name="SALE_DATE" autocheck="true" inputtype="string" label="销售日期"  value="${rst.SALE_DATE}"  mustinput="true"  readonly />
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">变更差异金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="DIFF_AMOUNT" name="DIFF_AMOUNT" id="DIFF_AMOUNT" autocheck="true" label="变更差异金额"  type="text"   inputtype="string"   readonly value="${rst.DIFF_AMOUNT}"/>
                     <input id="oldAmount" type="hidden" value="${rst.DIFF_AMOUNT}"/>
				   </td>
                   <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content">
				   </td>
               </tr>
<!--               <tr>-->
<!--               		<td width="15%" align="right" class="detail_label">总金额：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--                     <input json="TOTAL_AMOUNT" name="TOTAL_AMOUNT" id="TOTAL_AMOUNT" autocheck="true" label="总金额"  type="text"   inputtype="float"     valueType="12,2" readonly value="${rst.TOTAL_AMOUNT}"/>-->
<!--				   </td>-->
<!--                   <td width="15%" align="right" class="detail_label">优惠金额：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--                     <input json="DISCOUNT_AMOUNT" id="DISCOUNT_AMOUNT" name="DISCOUNT_AMOUNT" autocheck="true" label="优惠金额"   type="text"   inputtype="float"      readonly valueType="12,2"  value="${rst.DISCOUNT_AMOUNT}"/>-->
<!--				   </td>-->
<!--               </tr>-->
<!--               <tr>-->
<!--                   <td width="15%" align="right" class="detail_label">应收总额：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--                     <input json="PAYABLE_AMOUNT" name="PAYABLE_AMOUNT" id="PAYABLE_AMOUNT" autocheck="true" label="应收总额"  type="text"   inputtype="float"     readonly  valueType="12,2" readonly value="${rst.PAYABLE_AMOUNT}"/>-->
<!--				   </td>-->
<!--				   <td width="15%" align="right" class="detail_label">订金金额：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--                     <input json="ADVC_AMOUNT" id="ADVC_AMOUNT" name="ADVC_AMOUNT" autocheck="true" label="订金金额"  type="text"   inputtype="float"     readonly  valueType="12,2"  value="${rst.ADVC_AMOUNT}"/>-->
<!--				   </td>-->
<!--               </tr>-->
               <tr>
                    <td width="15%" align="right" class="detail_label">收货地址：</td>
					<td width="35%" class="detail_content" colspan="3">
                         <textarea  json="RECV_ADDR" name="RECV_ADDR"  id="RECV_ADDR" autocheck="true" inputtype="string"   maxlength="250"   label="收货地址"   mustinput="true"   rows="4" cols="80%" >${rst.RECV_ADDR}</textarea>
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
 