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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/cusregist/CusRegist_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>销售管理>>顾客现场签到</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
    
	    <input type="hidden" id="selTermParam" name="selTermParam" value=""/>
        <table width="100%"  border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <input type="hidden" id="CHANN_ID" name="CHANN_ID" json="CHANN_ID" value="" />
			    <input type="hidden" id="CHANN_NO" name="CHANN_NO" json="CHANN_NO" value="" />
			    <input type="hidden" id="CHANN_NAME" name="CHANN_NAME" json="CHANN_NAME" value="" />
			    <input type="hidden" id="PAYABLE_AMOUNT" name="PAYABLE_AMOUNT" json="PAYABLE_AMOUNT" value="" />
			    <input type="hidden" id="CRE_TIME" name="CRE_TIME" json="CRE_TIME" value="" />
			    <input type="hidden" id="STATE" name="STATE" json="STATE" value="" />
			    
			    <input type="hidden" id="CARD_SALE_ORDER_DTL_ID" name="CARD_SALE_ORDER_DTL_ID" json="CARD_SALE_ORDER_DTL_ID" value="${rst.CARD_SALE_ORDER_DTL_ID}" />
			    
			    <tr>
                    <td width="18%" align="right" class="detail_label">卡券编号：</td>
				   	<td width="35%" class="detail_content">
						<input json="MARKETING_CARD_NO" name="MARKETING_CARD_NO" id="MARKETING_CARD_NO" type="text"
							autocheck="true" label="卡券编号" inputtype="string" mustinput="true" onblur="loadCard();"
							<c:if test="${not empty rst.CARD_SALE_ORDER_DTL_ID}"> readonly </c:if>
							maxlength="32" value="${rst.MARKETING_CARD_NO}"  >
					</td>
					<td width="18%" align="right" class="detail_label">卡券类型：</td>
				    <td width="35%" class="detail_content">
				      <input json="CARD_TYPE" name="CARD_TYPE" id="CARD_TYPE"  type="text"
							autocheck="true" label="卡券类型" inputtype="string" mustinput="true"
							maxlength="32" value="${rst.CARD_TYPE}"  >
				   </td>
				   <td width="18%" align="right" class="detail_label">卡券面值：</td>
				   <td width="35%" class="detail_content">
                     <input json="CARD_VALUE" name="CARD_VALUE" id="CARD_VALUE"  label="卡券面值" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.CARD_VALUE}" /> 
				   </td>
				 </tr>
				 <tr>
                   <td width="15%" align="right" class="detail_label">顾客名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CUST_NAME" name="CUST_NAME" id="CUST_NAME"   label="顾客名称"   type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.CUST_NAME}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">顾客手机：</td>
				   <td width="35%" class="detail_content">
                     <input json="MOBILE_PHONE" name="MOBILE_PHONE" id="MOBILE_PHONE"   label="顾客手机"   type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="11"  value="${rst.MOBILE_PHONE}"/> 
				   </td>
				   <%--<td width="15%" align="right" class="detail_label">生日：</td>
				   <td width="35%" class="detail_content">
                     <input type="text" id="BIRTHDAY" name="BIRTHDAY" READONLY json="BIRTHDAY" onclick="SelectDate();" label="生日" inputtype="string" autocheck="true" size="20" value="${rst.BIRTHDAY}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(BIRTHDAY);">
				   </td>
                --%>
                <td width="18%" align="right" class="detail_label">销售日期：</td>
				    <td width="35%" class="detail_content">
                     <input type="text" id="SALE_DATE" name="SALE_DATE" READONLY json="SALE_DATE" onclick="SelectDate();" label="销售日期" mustinput="true" inputtype="string" autocheck="true" size="20" value="${rst.SALE_DATE}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE);">
				   </td>
                </tr>
                 <tr>
                 <%--
                   <td width="15%" align="right" class="detail_label">爱好：</td>
				   <td width="35%" class="detail_content">
                     <input json="HOBBY" name="HOBBY" id="HOBBY"  label="爱好"   type="text"  autocheck="true"  inputtype="string"   maxlength="300"  value="${rst.HOBBY}"/> 
				   </td>
				   --%>
				   <td width="15%" align="right" class="detail_label">住址：</td>
				   <td width="35%" class="detail_content" colspan="5">
                     <input json="ADDRESS" name="ADDRESS"  id="ADDRESS"  label="住址"   type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="250"  value="${rst.ADDRESS}" style="width: 513px;"/> 
				   </td>
                </tr>
                
                <tr>
                   
                   <td width="18%" align="right" class="detail_label">终端编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="TERM_ID" id="TERM_ID" name="TERM_ID" value="${rst.TERM_ID}" />
                     <input json="TERM_NO" name="TERM_NO" id="TERM_NO" label="终端编号" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.TERM_NO}" /> 
				    <img align="absmiddle" name="selJGXX" style="cursor: pointer;"
					  src="${ctx}/styles/${theme}/images/plus/select.gif"  onClick="selTerm();" /> 
				   </td>
                   <td width="15%" align="right" class="detail_label">终端名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME" label="终端名称"  readonly="readonly" type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.TERM_NAME}"/> 
				   </td>
				    <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content"> </td>
                </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">客户需求：</td>
				   <td width="35%" class="detail_content" colspan="6">
				     <textarea  json="REMARK" name="REMARK" id="REMARK"  inputtype="string" autocheck="true" 
				      maxlength="250"   label="客户需求"    rows="4" cols="80%" >${rst.REMARK}</textarea>
				   </td>
              </tr>
               <tr>
               
               <td colspan="6" align="center">
               <span  >
                  <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
                  <input id="back" type="button" class="btn" style="margin-left: 40px;" value="返回(B)" title="Alt+B" accesskey="B" onclick="window.close();">
               </span>
               </td>
              
               </tr>
			</table>
		</td>
	</tr>

</table>
</form>
</body>
</html>

 