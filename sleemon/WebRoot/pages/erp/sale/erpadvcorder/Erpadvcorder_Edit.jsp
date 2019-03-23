<!--
/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/erpadvcorder/Erpadvcorder_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>订单管理>>预订单录入</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
     <input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用' and DEL_FLAG=0 ">
	 <input type="hidden" name="selectActParams" id="selectActParams" value=" END_DATE>=sysdate and STATE='审核通过' and DEL_FLAG=0 ">
	 <input type="hidden" name="selectRYXXParams" id="selectRYXXParams" value=" RYZT='启用' and DELFLAG=0 ">
	 <input type="hidden" name="selectReqParams" id="selectReqParams" value=" STATE='审核通过' and DEL_FLAG=0 ">
	 <input type="hidden" name="selTermParam" id="selTermParam" value=" STATE='启用' and DEL_FLAG=0 ">
	 <input type="hidden" name="selCardParam" id="selCardParam" value="">
	 <input type="hidden" name="selectMemberInfoParams" id="selectMemberInfoParams" value="">
	 
       <table width="100%"  border="0" cellpadding="4" cellspacing="4" class="detail">
        <tr>
      
        </tr>
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <tr>
                    <td width="18%" align="right" class="detail_label">预订单编号：</td>
				   	<td width="35%" class="detail_content">
						<input json="ADVC_ORDER_NO" name="ADVC_ORDER_NO" type="text"
							autocheck="true" label="预订单编号" inputtype="string" mustinput="true"
							maxlength="32"
							<c:if test="${not empty rst.ADVC_ORDER_NO}">value="${rst.ADVC_ORDER_NO}"</c:if>
							<c:if test="${empty rst.ADVC_ORDER_NO}">value="自动生成"</c:if>
							READONLY
							>
					</td>
                   <td width="15%" align="right" class="detail_label">单据类型：</td>
				   <td width="35%" class="detail_content" >
                     <select json="BILL_TYPE" name="BILL_TYPE" id="BILL_TYPE"   style="width:155px;" label="单据类型"  > </select>
                      <SPAN class="validate">&nbsp;*</SPAN> 
				   </td>
               </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">卡券编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="MARKETING_CARD_ID" id="MARKETING_CARD_ID" name="MARKETING_CARD_ID" value="${rst.MARKETING_CARD_ID}" />
                     <input json="MARKETING_CARD_NO" name="MARKETING_CARD_NO"  label="卡券编号" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.MARKETING_CARD_NO}" /> 
				    <img align="absmiddle" name="selJGXX" style="cursor: pointer;"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selectCard();"> 
				   
				   </td>
                   <td width="18%" align="right" class="detail_label">业务员：</td>
				   <td width="35%" class="detail_content">
				    <input  type="hidden"  name="SALE_PSON_ID" id="SALE_PSON_ID" json="SALE_PSON_ID" value="${rst.SALE_PSON_ID}"/>
                     <input json="SALE_PSON_NAME" name="SALE_PSON_NAME" id="SALE_PSON_NAME" label="业务员" mustinput="true" inputtype="string" autocheck="true"  type="text" value="${rst.SALE_PSON_NAME}"/> 
                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('0', false, 'SALE_PSON_ID', 'RYXXID', 'forms[0]','SALE_PSON_NAME', 'XM', 'selectRYXXParams')">
								
				   </td>
                </tr>
                <tr>
				   <td width="18%" align="right" class="detail_label">销售日期：</td>
				   <td width="35%" class="detail_content">
                     <input type="text" id="SALE_DATE" name="SALE_DATE" READONLY json="SALE_DATE" onclick="SelectDate();" label="销售日期" mustinput="true" inputtype="string" autocheck="true" size="20" value="${rst.SALE_DATE}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE);">
				   </td>
				    <td width="18%" align="right" class="detail_label">要求到货日期：</td>
				    <td width="35%" class="detail_content">
                     <input type="text" id="ORDER_RECV_DATE" name="ORDER_RECV_DATE" READONLY json="ORDER_RECV_DATE" onclick="SelectDate();" label="要求到货日期" mustinput="true" inputtype="string" autocheck="true" size="20" value="${rst.ORDER_RECV_DATE}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_RECV_DATE);">
				    </td>
                </tr>
           
                <tr>
                   <td width="15%" align="right" class="detail_label">客户名称：</td>
				   <td width="35%" class="detail_content" >
				      <input type="hidden" name="MEMBER_ID" id="MEMBER_ID" />
				      <input json="CUST_NAME" name="CUST_NAME" id="CUST_NAME" label="客户名称" 
				      mustinput="true" inputtype="string" autocheck="true"  type="text" value="${rst.CUST_NAME}"/> 
				        <img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('BS_164', false, 'MEMBER_ID', 'MEMBER_ID', 'forms[0]','MEMBER_ID,CUST_NAME,TEL', 'MEMBER_ID,MEMBER_NAME,MOBILE_PHONE', 'selectMemberInfoParams')">
				      
				   </td>
                   <td width="15%" align="right" class="detail_label">电话：</td>
				   <td width="35%" class="detail_content" >
				      <input json="TEL" name="TEL" id="TEL" label="电话" 
				      mustinput="true" inputtype="string" autocheck="true"  type="text" value="${rst.TEL}"/> 
				   </td>
                </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">活动编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" id="MARKETING_ACT_ID" name="MARKETING_ACT_ID" json="MARKETING_ACT_ID" value="${rst.MARKETING_ACT_ID}"/>
                     <input json="MARKETING_ACT_NO" name="MARKETING_ACT_NO" id="MARKETING_ACT_NO" label="活动编号" 
                      inputtype="string"  autocheck="true" mustinput="true" READONLY
                      type="text" value="${rst.MARKETING_ACT_NO}"/> 
                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('BS_149', false, 'MARKETING_ACT_ID', 'MARKETING_ACT_ID', 'forms[0]','MARKETING_ACT_NO,MARKETING_ACT_NAME', 'MARKETING_ACT_NO,MARKETING_ACT_NAME', 'selectActParams');changeCardParams();">
				   </td>
				   <td width="15%" align="right" class="detail_label"> 活动名称： </td>
					<td width="35%" class="detail_content">
					  <input id="MARKETING_ACT_NAME" json="MARKETING_ACT_NAME" name="MARKETING_ACT_NAME" 
					  inputtype="string"   autocheck="true" mustinput="true" READONLY
					  label="活动名称"  value="${rst.MARKETING_ACT_NAME}" />
					</td>
                </tr>
                 <tr>
                   <td width="18%" align="right" class="detail_label">终端编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="TERM_ID" id="TERM_ID" name="TERM_ID" value="${rst.TERM_ID}" />
                     <input json="TERM_NO" name="TERM_NO"  label="终端编号" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.TERM_NO}" /> 
				    <img align="absmiddle" name="selJGXX" style="cursor: pointer;"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[0]','TERM_NO,TERM_NAME', 'TERM_NO,TERM_NAME', 'selTermParam')"> 
				   
				   </td>
                   <td width="15%" align="right" class="detail_label">终端名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NAME" name="TERM_NAME"  label="终端名称"  readonly="readonly" type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.TERM_NAME}"/> 
				   </td>
                </tr>
               <tr>
                   <td width="18%" align="right" class="detail_label">订金金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADVC_AMOUNT" name="ADVC_AMOUNT" id="ADVC_AMOUNT" label="订金金额" 
                      inputtype="float" valuetype="12,2" autocheck="true" mustinput="true"
                     type="text" value="${rst.ADVC_AMOUNT}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label"> 应收总额： </td>
					<td width="35%" class="detail_content">
					  <input id="PAYABLE_AMOUNT" json="PAYABLE_AMOUNT" name="PAYABLE_AMOUNT" 
					  inputtype="float" valuetype="12,2" autocheck="true" mustinput="true" 
					  label="应收总额"  value="${rst.PAYABLE_AMOUNT}" />
					</td>
                </tr>
                
               
                
                
                <tr>
                   <td width="15%" align="right" class="detail_label">收货地址：</td>
				   <td width="35%" class="detail_content" colspan="4">
				     <textarea  json="RECV_ADDR" name="RECV_ADDR" id="RECV_ADDR"  inputtype="string" autocheck="true" 
				      maxlength="250"   label="收货地址"    rows="4" cols="80%" >${rst.RECV_ADDR}</textarea>
				   </td>
                </tr>
                <tr>
                  <td width="18%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="4">
				     <textarea  json="REMARK" name="REMARK" id="REMARK"  inputtype="string" autocheck="true" 
				      maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
				   </td>
              </tr>
              <tr>
                <td></td>
               <td>
               <span style="margin-left: 200px;">
                  <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
                  <input id="back" type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();" style="margin-left: 20px;" >
               </span>
               </td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
    SelDictShow("BILL_TYPE", "BS_153", '${rst.BILL_TYPE}', "");
</script>
</html>

 