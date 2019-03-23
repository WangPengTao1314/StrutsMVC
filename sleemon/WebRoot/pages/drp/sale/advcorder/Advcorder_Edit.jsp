<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit
 * @author lyg
 * @time   2013-08-11 17:17:29 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale//advcorder/Advcorder_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;终端销售&gt;预订单录入编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
   		
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
                    <input type="hidden" name=selectParams value="STATE='启用' and DEL_FLAG=0 and CHANN_ID_P='${rst.ZTXXID}'">
                    <input type="hidden" id="DATE" value="${rst.DATE}"/>
                    <input type="hidden" id="updateFlag" value="${updateFlag}"/>
                    <input type="hidden" name="selectMoteParams" value=" DEL_FLAG='0' and STATE='启用' and LEDGER_ID = '${LEDGER_ID}'">
                    
               <tr>
                   <td width="19%" align="right" class="detail_label">预订单编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADVC_ORDER_NO" name="ADVC_ORDER_NO" autocheck="true" label="预订单编号"  type="text"   inputtype="string"   readonly    mustinput="true"  
                     	<c:if test="${rst.ADVC_ORDER_NO==null}"> value="自动生成"</c:if>
                     	<c:if test="${rst.ADVC_ORDER_NO!=null}">value="${rst.ADVC_ORDER_NO}"</c:if>
                     	/> 
				   </td>
                    <td width="15%" align="right" class="detail_label">终端编号：</td>
					<td width="35%" class="detail_content">
                       <input json="TERM_ID" name="TERM_ID" autocheck="true" label="终端信息ID" type="hidden" inputtype="string"   value="${rst.TERM_ID}"/>
                       <input json="TERM_NO" name="TERM_NO" autocheck="true" label="终端编号"  type="text"  inputtype="string"  mustinput="true"  readonly   value="${rst.TERM_NO}"/>
                       <c:if test="${rst.TERMFLAG ne 1 && updateFlag ne 1}">
                       <img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[0]','TERM_ID,TERM_NO,TERM_NAME', 'TERM_ID,TERM_NO,TERM_NAME', 'selectParams')">
						</c:if> 
					</td>
               </tr>
               <tr>
                   <td width="19%" align="right" class="detail_label">活动编号：</td>
				   <td width="35%" class="detail_content">
				     <input id="PROMOTE_ID" json="PROMOTE_ID" name="PROMOTE_ID" type="hidden" value="${rst.PROMOTE_ID}">
                     <input json="PROMOTE_NO" name="PROMOTE_NO" autocheck="true" label="活动编号"  type="text"   inputtype="string"   readonly value="${rst.PROMOTE_NO}"/>
                     <c:if test="${updateFlag ne 1}">
	                     <img align="absmiddle" style="cursor： hand" src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30" 
	                     	onClick="selCommon('BS_112', false, 'PROMOTE_ID', 'PROMOTE_ID', 'forms[0]','PROMOTE_ID,PROMOTE_NO,PROMOTE_NAME','PROMOTE_ID,PROMOTE_NO,PROMOTE_NAME','selectMoteParams')">
                     </c:if> 
				   </td>
                    <td width="15%" align="right" class="detail_label">活动名称：<br></td>
					<td width="35%" class="detail_content">
						<input json="PROMOTE_NAME" name="PROMOTE_NAME" autocheck="true" label="活动名称" type="text" inputtype="string" readonly  value="${rst.PROMOTE_NAME}"/>
					</td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">终端名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NAME" name="TERM_NAME" autocheck="true" label="终端名称"  type="text"   inputtype="string"   readonly    mustinput="true"   value="${rst.TERM_NAME}"/> 
				   </td>
                    <td width="15%" align="right" class="detail_label">销售日期：</td>
					<td width="35%" class="detail_content">
						<input type="text" json="SALE_DATE" id="SALE_DATE" name="SALE_DATE" autocheck="true" inputtype="string" label="销售日期"  value="${rst.SALE_DATE}"
						 <c:if test="${updateFlag ne 1}">
						onclick="SelectDate();"  
						</c:if>
						mustinput="true"  readonly />
						<c:if test="${updateFlag ne 1}">
							<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE);">
						</c:if>
						<input type="hidden" id="oldSaleDate" value="${rst.SALE_DATE}"/>
					</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">业务员：</td>
					<td width="35%" class="detail_content">
                       <input json="SALE_PSON_NAME" name="SALE_PSON_NAME"  autocheck="true" label="业务员"  type="text"  inputtype="string"   readonly  mustinput="true"   value="${rst.SALE_PSON_NAME}"/>
                        <input json="SALE_PSON_ID" name="SALE_PSON_ID" autocheck="true" label="业务员ID" type="hidden" inputtype="string"   value="${rst.SALE_PSON_ID}"/>  
					</td>
                   <td width="15%" align="right" class="detail_label">客户姓名：</td>
				   <td width="35%" class="detail_content">
                     <input json="CUST_NAME" name="CUST_NAME" autocheck="true" label="客户姓名"  type="text"   inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.CUST_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" id="TEL" autocheck="true" label="电话"  type="text"   inputtype="string"     mustinput="true"     maxlength="30"  value="${rst.TEL}"/> 
				   </td>
<!--                    <td width="18%" align="right" class="detail_label">要求到货日期：</td>-->
<!--					<td width="35%" class="detail_content">-->
<!--                        <input type="text" json="ORDER_RECV_DATE" id="ORDER_RECV_DATE" name="ORDER_RECV_DATE" autocheck="true" inputtype="string" label="要求到货日期"  value="${rst.ORDER_RECV_DATE}"  mustinput="true"   onclick="SelectDate();" readonly="readonly" />-->
<!--						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE);">-->
<!--					</td>-->
					  <td width="15%" align="right" class="detail_label">合同编号：</td>
					   <td width="35%" class="detail_content">
					   		<input json="CONTRACT_NO" name="CONTRACT_NO" autocheck="true" label="合同编号"  type="text"   inputtype="string"  <c:if test="${updateFlag eq 1}">readonly="readonly"</c:if>   maxlength="30"   value="${rst.CONTRACT_NO}"/>
					   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">订金金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADVC_AMOUNT" id="ADVC_AMOUNT" name="ADVC_AMOUNT" autocheck="true" label="订金金额"  type="text"  <c:if test="${updateFlag eq 1}">readonly="readonly"</c:if> inputtype="float" mustinput="true"  valueType="12,2"  value="${rst.ADVC_AMOUNT}"/>
				   </td>
                   <td width="15%" align="right" class="detail_label">优惠金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="DISCOUNT_AMOUNT" id="DISCOUNT_AMOUNT" name="DISCOUNT_AMOUNT" autocheck="true" label="优惠金额" readonly  onkeyup="countAMOUNT()" type="text"   textType="float"    valueType="12,2"  value="${rst.DISCOUNT_AMOUNT}"/>
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">总金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="TOTAL_AMOUNT" name="TOTAL_AMOUNT" id="TOTAL_AMOUNT" autocheck="true" label="总金额"  type="text"   inputtype="float"  readonly   valueType="12,2" value="${rst.TOTAL_AMOUNT}"/>
				   </td>
                   <td width="15%" align="right" class="detail_label">应收总额：</td>
				   <td width="35%" class="detail_content">
                     <input json="PAYABLE_AMOUNT" name="PAYABLE_AMOUNT" id="PAYABLE_AMOUNT" autocheck="true" label="应收总额"  type="text"   inputtype="string"  readonly   value="${rst.PAYABLE_AMOUNT}"/>
				   </td>
				   
               </tr>
               <tr>
               	  <td width="15%" align="right" class="detail_label">消费者年龄：</td>
				   <td width="35%" class="detail_content">
                     <input json="CUSTOMER_AGE" name="CUSTOMER_AGE" id="CUSTOMER_AGE" autocheck="true" label="消费者年龄"  type="text"   inputtype="string"   maxlength="5"  value="${rst.CUSTOMER_AGE}"/>
				   </td>
				   <td width="15%" align="right" class="detail_label">客户来源：</td>
				   <td width="35%" class="detail_content">
                     <input json="CUSTOMER_SOURCE" name="CUSTOMER_SOURCE" id="CUSTOMER_SOURCE" autocheck="true" label="客户来源"  type="text"   inputtype="string"  maxlength="100"   value="${rst.CUSTOMER_SOURCE}"/>
				   </td>
               </tr>
               <tr>
               	 <td width="15%" align="right" class="detail_label">客户需求：</td>
  				 <td width="35%" class="detail_content" colspan="3">
                        <textarea  json="CUSTOMER_DEMAND" name="CUSTOMER_DEMAND"  id="CUSTOMER_DEMAND" autocheck="true" inputtype="string"   maxlength="250"   label="客户需求"   rows="4" cols="80%" >${rst.CUSTOMER_DEMAND}</textarea>
				</td>             	 
               </tr>
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
<script type="text/javascript">
</script>
 