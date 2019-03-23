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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/marketcardsale/MarketcardSale_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>销售管理>>卡券销售</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
      <input type="hidden" name="selectActParams" id="selectActParams" value="   END_DATE>=sysdate and STATE='审核通过' and DEL_FLAG=0 ">
	  <input type="hidden" id="selChannParam" name="selChannParam" value=" STATE ='启用' and CHANN_ID in(${CHANNS_CHARG}) "/>
	  <input type="hidden" name="selectRYXXParams" id="selectRYXXParams" value=" RYZT='启用' and DELFLAG=0 ">
	  <input type="hidden" id="selTermParam" name="selTermParam" value=" CHANN_ID_P='${rst.CHANN_ID}' and DEL_FLAG=0 and STATE='启用' ""/>
        <table width="100%"  border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <tr>
                    <td width="18%" align="right" class="detail_label">卡券销售单编号：</td>
				   	<td width="35%" class="detail_content">
						<input json="CARD_SALE_ORDER_NO" name="CARD_SALE_ORDER_NO" type="text"
							autocheck="true" label="卡券销售单编号" inputtype="string" mustinput="true"
							maxlength="32"
							<c:if test="${not empty rst.CARD_SALE_ORDER_NO}">value="${rst.CARD_SALE_ORDER_NO}"</c:if>
							<c:if test="${empty rst.CARD_SALE_ORDER_NO}">value="自动生成"</c:if>
							READONLY
							>
					</td>
					<td width="18%" align="right" class="detail_label">销售日期：</td>
				    <td width="35%" class="detail_content">
                     <input type="text" id="SALE_DATE" name="SALE_DATE" READONLY json="SALE_DATE" onclick="SelectDate();" label="销售日期" mustinput="true" inputtype="string" autocheck="true" size="20" value="${rst.SALE_DATE}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE);">
				   </td>
				 </tr>
				 <tr>
                   <td width="18%" align="right" class="detail_label">渠道编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="CHANN_ID" id="CHANN_ID" name="CHANN_ID" value="${rst.CHANN_ID}" />
                     <input json="CHANN_NO" name="CHANN_NO"  label="渠道编号" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.CHANN_NO}" /> 
				    <img align="absmiddle" name="selJGXX" style="cursor: pointer;"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selChannParam');changeTerm();"> 
				   
				   </td>
                   <td width="15%" align="right" class="detail_label">渠道名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_NAME" name="CHANN_NAME"  label="渠道名称"  readonly="readonly" type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.CHANN_NAME}"/> 
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
                   <td width="18%" align="right" class="detail_label">活动编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" id="MARKETING_ACT_ID" name="MARKETING_ACT_ID" json="MARKETING_ACT_ID" value="${rst.MARKETING_ACT_ID}"/>
                     <input json="MARKETING_ACT_NO" name="MARKETING_ACT_NO" id="MARKETING_ACT_NO" label="活动编号" 
                      inputtype="string"  autocheck="true" mustinput="true" READONLY
                      type="text" value="${rst.MARKETING_ACT_NO}"/> 
                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('BS_149', false, 'MARKETING_ACT_ID', 'MARKETING_ACT_ID', 'forms[0]','MARKETING_ACT_NO,MARKETING_ACT_NAME', 'MARKETING_ACT_NO,MARKETING_ACT_NAME', 'selectActParams');">
						
				   </td>
				   <td width="15%" align="right" class="detail_label"> 活动名称： </td>
					<td width="35%" class="detail_content">
					  <input id="MARKETING_ACT_NAME" json="MARKETING_ACT_NAME" name="MARKETING_ACT_NAME" 
					  inputtype="string"   autocheck="true" mustinput="true" READONLY
					  label="活动名称"  value="${rst.MARKETING_ACT_NAME}" />
					</td>
                </tr>
                <tr>
                   
                   <td width="18%" align="right" class="detail_label">业务员：</td>
				   <td width="35%" class="detail_content">
				    <input  type="hidden"  name="SALE_PSON_ID" id="SALE_PSON_ID" json="SALE_PSON_ID" value="${rst.SALE_PSON_ID}"/>
                     <input json="SALE_PSON_NAME" name="SALE_PSON_NAME" id="SALE_PSON_NAME" label="业务员" mustinput="true" inputtype="string" autocheck="true"  type="text" value="${rst.SALE_PSON_NAME}"/> 
                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('0', false, 'SALE_PSON_ID', 'RYXXID', 'forms[0]','SALE_PSON_NAME', 'XM', 'selectRYXXParams')">
								
				   </td>
                   <td width="18%" align="right" class="detail_label">销售卡券总额：</td>
				   <td width="35%" class="detail_content">
                     <input type="text" id="SALE_CARD_AMOUNT" name="SALE_CARD_AMOUNT" json="SALE_CARD_AMOUNT"  label="销售卡券总额" mustinput="true" inputtype="string" autocheck="true" size="20" value="${rst.SALE_CARD_AMOUNT}" >
				   </td>
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
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
    SelDictShow("carType","185","${params.carType}","");
     //初始化 审批流程
     spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
</html>

 