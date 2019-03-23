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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/erpadvcorderstatements/Erpadvcorderstatements_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>订单管理>>结算单维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
	  <input type="hidden" name="selectActParams" id="selectActParams" value=" STATE='审核通过' and DEL_FLAG=0 ">
	  <input type="hidden" id="selChannParam" name="selChannParam" value=" STATE ='启用' and CHANN_ID in(${CHANNS_CHARG}) "/>
       <table width="100%"  border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <tr>
                    <td width="18%" align="right" class="detail_label">结算单编号：</td>
				   	<td width="35%" class="detail_content">
						<input json="ADVC_STATEMENTS_ORDER_NO" name="ADVC_STATEMENTS_ORDER_NO" type="text"
							autocheck="true" label="结算单编号" inputtype="string" mustinput="true"
							maxlength="32"
							<c:if test="${not empty rst.ADVC_STATEMENTS_ORDER_NO}">value="${rst.ADVC_STATEMENTS_ORDER_NO}"</c:if>
							<c:if test="${empty rst.ADVC_STATEMENTS_ORDER_NO}">value="自动生成"</c:if>
							READONLY
							>
					</td>
                   <td width="18%" align="right" class="detail_label">结算日期：</td>
				   <td width="35%" class="detail_content">
                     <input type="text" id="STATEMENTS_DATE" name="STATEMENTS_DATE" READONLY json="STATEMENTS_DATE" onclick="SelectDate();" label="结算日期" mustinput="true" inputtype="string" autocheck="true" size="20" value="${rst.STATEMENTS_DATE}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(STATEMENTS_DATE);">
				   </td>
               </tr>
               <tr>
                   <td width="18%" align="right" class="detail_label">渠道编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="CHANN_ID" id="CHANN_ID" name="CHANN_ID" value="${rst.CHANN_ID}" />
                     <input json="CHANN_NO" name="CHANN_NO"  label="渠道编号" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.CHANN_NO}" /> 
				    <img align="absmiddle" name="selJGXX" style="cursor: pointer;"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selChannParam')"> 
				   
				   </td>
                   <td width="15%" align="right" class="detail_label">渠道名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_NAME" name="CHANN_NAME"  label="渠道名称"  readonly="readonly" type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.CHANN_NAME}"/> 
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
							 onClick="selCommon('BS_149', false, 'MARKETING_ACT_ID', 'MARKETING_ACT_ID', 'forms[0]','MARKETING_ACT_NO,MARKETING_ACT_NAME,COMMISSION_PERCENTAGE', 'MARKETING_ACT_NO,MARKETING_ACT_NAME,COMMISSION_PERCENTAGE', 'selectActParams');">
						
				   </td>
				   <td width="15%" align="right" class="detail_label"> 活动名称： </td>
					<td width="35%" class="detail_content">
					  <input id="MARKETING_ACT_NAME" json="MARKETING_ACT_NAME" name="MARKETING_ACT_NAME" 
					  inputtype="string"   autocheck="true" mustinput="true" READONLY
					  label="活动名称"  value="${rst.MARKETING_ACT_NAME}" />
					</td>
                </tr>
                
               <tr>
                   <td width="18%" align="right" class="detail_label">订金总额：</td>
				   <td width="35%" class="detail_content">
                     <input json="ADVCS_AMOUNT" name="ADVCS_AMOUNT" id="ADVCS_AMOUNT" label="订金总额" 
                      inputtype="float" valuetype="12,2" autocheck="true" mustinput="true" READONLY maxlength="15"
                     type="text" value="${rst.ADVCS_AMOUNT}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label"> 结算总额： </td>
					<td width="35%" class="detail_content">
					  <input id="STATEMENTS_AMOUNT" json="STATEMENTS_AMOUNT" name="STATEMENTS_AMOUNT" 
					  inputtype="float" valuetype="12,2" autocheck="true" mustinput="true" READONLY maxlength="15"
					  label="结算总额"  value="${rst.STATEMENTS_AMOUNT}" />
					</td>
                </tr>
                
                <tr>
                   <td width="18%" align="right" class="detail_label">银行扣点比例：</td>
				   <td width="35%" class="detail_content">
                     <input json="BANK_RATE" name="BANK_RATE" id="BANK_RATE" label="银行扣点比例" maxlength="9"
                       valuetype="4,4" autocheck="true" mustinput="true" inputtype="xiaoshu" onkeyup="changeAmount();"
                     type="text" value="${rst.BANK_RATE}"  /> 
				   </td>
				   <td width="15%" align="right" class="detail_label"> 银行扣点金额： </td>
					<td width="35%" class="detail_content">
					  <input id="BANK_AMOUNT" json="BANK_AMOUNT" name="BANK_AMOUNT" maxlength="15"
					  inputtype="float" valuetype="12,2" autocheck="true" mustinput="true" READONLY
					  label="银行扣点金额"  value="${rst.BANK_AMOUNT}" />
					</td>
                </tr>
                
                 <tr>
                   <td width="18%" align="right" class="detail_label">导购员提成比例：</td>
				   <td width="35%" class="detail_content">
                     <input json="COMMISSION_PERCENTAGE" name="COMMISSION_PERCENTAGE" id="COMMISSION_PERCENTAGE" label="导购员提成比例" 
                     maxlength="9" inputtype="float" valuetype="4,4" autocheck="true" mustinput="true" READONLY
                     type="text" value="${rst.COMMISSION_PERCENTAGE}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label"> 导购员提成金额： </td>
					<td width="35%" class="detail_content">
					  <input id="COMMISSION_AMOUNT" json="COMMISSION_AMOUNT" name="COMMISSION_AMOUNT" 
					  inputtype="float" valuetype="12,2" autocheck="true" mustinput="true" READONLY maxlength="15"
					  label="导购员提成金额"  value="${rst.COMMISSION_AMOUNT}" />
					</td>
                </tr>
                
                <tr>
                   <td width="18%" align="right" class="detail_label">礼品费用：</td>
				   <td width="35%" class="detail_content">
                     <input json="GIFT_AMOUNT" name="GIFT_AMOUNT" id="GIFT_AMOUNT" label="礼品费用" 
                      inputtype="float" valuetype="12,2" autocheck="true"   READONLY maxlength="15"
                     type="text" value="${rst.GIFT_AMOUNT}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label"> 其他费用： </td>
					<td width="35%" class="detail_content">
					  <input id="OTHER_AMOUNT" json="OTHER_AMOUNT" name="OTHER_AMOUNT"  maxlength="15"
					  valuetype="12,2" autocheck="true"   inputtype="xiaoshu" onkeyup="changeAmount();"
					  label="其他费用"  value="${rst.OTHER_AMOUNT}" />
					</td>
                </tr>
                
                <tr>
                   <td width="18%" align="right" class="detail_label">实际结算金额：</td>
				   <td width="35%" class="detail_content">
                     <input json="LAST_STATEMENTS_AMOUNT" name="LAST_STATEMENTS_AMOUNT" id="LAST_STATEMENTS_AMOUNT" label="实际结算金额" 
                      inputtype="zffloat" valuetype="12,2" autocheck="true" mustinput="true" READONLY maxlength="15"
                     type="text" value="${rst.LAST_STATEMENTS_AMOUNT}"/> 
				   </td>
				   <td width="18%" align="right" class="detail_label"> </td>
				   <td width="35%" class="detail_content">  </td>
                </tr>
                <tr>
                  <td width="18%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="4">
				     <textarea  json="REMARK" name="REMARK" id="REMARK"  inputtype="string" autocheck="true" 
				      maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
				   </td>
              </tr>
			</table>
		</td>
	</tr>
	 
</table>
</form>
</body>
<script type="text/javascript">
 //   SelDictShow("BILL_TYPE", "BS_153", '${rst.BILL_TYPE}', "");
</script>
</html>

 