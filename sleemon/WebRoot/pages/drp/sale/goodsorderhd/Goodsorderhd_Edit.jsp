<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:分销-订货订单维护
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/sale/goodsorderhd/Goodsorderhd_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>我的订货订单>>订货订单维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input type="hidden" name=selectParams value=" DEL_FLAG=0 and STATE='启用'  ">
       <input type="hidden" name="selectStore" id="selectStore" value=""/>
       <input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
       <input type="hidden" id="selectAddrParams" name=selectAddrParams value=" DEL_FLAG=0 and STATE='启用' and CHANN_ID='${rst.RECV_CHANN_ID}' ">
       <input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE ='启用' and (CHANN_ID='${ZTXXID}' or AREA_SER_ID='${ZTXXID}') "/>
       <input type="hidden" id="selSendChannParam" name="selSendChannParam" value=" STATE ='启用' and CHANN_ID in('${BASE_CHANN_ID}','${AREA_SER_ID}') "/>
       
       <input id="REBATEDSCT" name="REBATEDSCT" label="返利折扣" type="hidden" value="${REBATEDSCT}">
       <input id="REBATE_CURRT" name="REBATE_CURRT" label="当前返利" type="hidden" value="${rst.REBATE_CURRT-rst.REBATE_FREEZE}">
 
       
       <table width="100%"  border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
			    <td colspan="4">
			    <input  type="hidden" json="ORDER_CHANN_ID"  id="ORDER_CHANN_ID" name="ORDER_CHANN_ID" label="" value="${rst.ORDER_CHANN_ID}" />
			    <input  type="hidden" json="ORDER_CHANN_NO"  id="ORDER_CHANN_NO" name="ORDER_CHANN_NO" label="" value="${rst.ORDER_CHANN_NO}" />
			    <input  type="hidden" json="ORDER_CHANN_NAME"  id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME" label="" value="${rst.ORDER_CHANN_NAME}" />
			    <input  type="hidden" json="AREA_ID"  id="AREA_ID" name="AREA_ID" label="" value="${rst.AREA_ID}" />
			    <input  type="hidden" json="AREA_NO"  id="AREA_NO" name="AREA_NO" label="" value="${rst.AREA_NO}" />
			    <input  type="hidden" json="AREA_NAME" id="AREA_NAME" name="AREA_NAME" label="" value="${rst.AREA_NAME}" />
			    <input  type="hidden" json="ORDER_RECV_DATE" id="ORDER_RECV_DATE" name="ORDER_RECV_DATE" label="要求到货日期" value="${rst.ORDER_RECV_DATE}" />
			    <input  type="hidden"   id="BILL_TYPE" name="BILL_TYPE" label="单据类型" value="${rst.BILL_TYPE}" />
			    
			    <c:if test="${havaAreaSerId ne 0}"><!-- 有区域服务中心的加盟商不使用返利 -->
			    <div style ="border:solid 1px #A0A0A0;">
                                               订货单位：${rst.ORDER_CHANN_NAME}　
                   <!-- 等于 当前信用+sum的临时信用-冻结信用 -->
                                               可用信用：<span style="color: red;text-decoration:underline;">
                             <%if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){%>
                              ${rst.INI_CREDIT+userCredit+rst.TEMP_CREDIT_REQ-rst.FREEZE_CREDIT}
                             <%}else{%>
                               ${rst.CREDIT_CURRT+rst.TEMP_CREDIT_REQ+rst.REBATE_CURRT-rst.FREEZE_CREDIT}
                             <%} %>
                               
                           </span>&nbsp;(元)　　
                                               付款凭证信用：<span style="color: red;text-decoration:underline;">
                               ${rst.PAYMENT_CREDIT}
                              </span>（元）　
                                               返利额：<span style="color: red;text-decoration:underline;">
                               <c:if test="${empty rst.REBATE_CURRT}">0</c:if>
                              <c:if test="${!empty rst.REBATE_CURRT}">${rst.REBATE_CURRT-rst.REBATE_FREEZE}</c:if>
                         </span>（元）
                                          订货总额：<span style="color: red;text-decoration:underline;">
                              <c:if test="${empty rst.ORDER_AMOUNT}">0</c:if>
                              <c:if test="${!empty rst.ORDER_AMOUNT}">${rst.ORDER_AMOUNT}</c:if>
                        </span>（元）
                        
                       <input type="checkbox" id="user_rebate" name="user_rebate" 
                       <c:if test="${rst.IS_USE_REBATE==1}">checked</c:if>   
                       <c:if test="${ not empty rst.GOODS_ORDER_ID}" > disabled="disabled"</c:if>
                        onclick="setUser_rebate();"/> 
                                                       是否使用返利
                 </div>
                 
                 </c:if>
			    </td>
			   </tr>
			    <tr>
                   <td width="18%" align="right" class="detail_label">收货方编号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" json="IS_USE_REBATE"  id="IS_USE_REBATE" name="IS_USE_REBATE" label="" value="${rst.IS_USE_REBATE}"/>
				    <input type="hidden" json="RECV_CHANN_ID_OLD"  id="RECV_CHANN_ID_OLD" name="RECV_CHANN_ID_OLD" value="${rst.RECV_CHANN_ID}" />
				    <input type="hidden" json="RECV_CHANN_ID"  id="RECV_CHANN_ID" name="RECV_CHANN_ID" value="${rst.RECV_CHANN_ID}" />
                    <input json="RECV_CHANN_NO" name="RECV_CHANN_NO"   label="收货方编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.RECV_CHANN_NO}"/> 
		            <img align="absmiddle" name="selJGXX" style="cursor: hand"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[0]','RECV_CHANN_NO,RECV_CHANN_NAME,PERSON_CON,TEL', 'CHANN_NO,CHANN_NAME,PERSON_CON,TEL', 'selOrderChannParam');changeAddr();"> 
				   </td>
                   <td width="15%" align="right" class="detail_label">收货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  label="收货方名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.RECV_CHANN_NAME}"/> 
				   </td>
               </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">联系人：</td>
				   <td width="35%" class="detail_content">
                     <input json="PERSON_CON" name="PERSON_CON" id="PERSON_CON" label="联系人" mustinput="true" inputtype="string"   type="text" value="${rst.PERSON_CON}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">联系人方式：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" id="TEL"  label="联系人方式"  type="text" mustinput="true" inputtype="string"   autocheck="true"   maxlength="30"  value="${rst.TEL}"/> 
				   </td>
                </tr>
                <tr>
                  <td width="18%" align="right" class="detail_label">详细地址：</td>
				   <td width="35%" class="detail_content" >
				      <input type="hidden" id="DELIVER_ADDR_ID" json="DELIVER_ADDR_ID" name="DELIVER_ADDR_ID"  value=""/>
				      <input type="hidden" id="RECV_ADDR_NO" json="RECV_ADDR_NO" name="RECV_ADDR_NO"  value="${rst.RECV_ADDR_NO}"/>
                      <input type="text"  id="RECV_ADDR" json="RECV_ADDR" name="RECV_ADDR"  autocheck="true" inputtype="string" mustinput="true" size="250" style="width: 340px;" maxlength="250" readonly="readonly"  label="详细地址"  value="${rst.RECV_ADDR}" />
                       <img align="absmiddle" name="selAddr" style="cursor: hand"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selCommon('BS_76', false, 'DELIVER_ADDR_ID', 'DELIVER_ADDR_ID', 'forms[0]','DELIVER_ADDR_ID,RECV_ADDR_NO,RECV_ADDR', 'DELIVER_ADDR_ID,DELIVER_ADDR_NO,DELIVER_DTL_ADDR', 'selectAddrParams')"> 
				   </td>
              
                   <td width="18%" align="right" class="detail_label">入库库房：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" name="STORE_ID" id="STORE_ID" json="STORE_ID" value="${rst.STORE_ID}">
				    <input type="hidden" name="STORE_NO" id="STORE_NO" json="STORE_NO" value="${rst.STORE_NO}">
				    <input json="STORE_NAME" name="STORE_NAME" id="STORE_NAME"  label="入库库房"  type="text" inputtype="string"  autocheck="true"   readonly value="${rst.STORE_NAME}"/>  
				     <img align="absmiddle" name="selJGXX" style="cursor: hand"
					 src="${ctx}/styles/${theme}/images/plus/select.gif"       
					  onClick="selStore()"> 
				   </td>
				   </tr>
                   <%--<td width="15%" align="right" class="detail_label">车型：</td>
				   <td width="35%" class="detail_content">
                     <select style="width:155" name="carType" id="carType" onchange="">
		   				</select>
				   </td>
                --%> <%--
                <tr>
                   <td width="18%" align="right" class="detail_label">包含：</td>
				   <td width="35%" class="detail_content" colspan="3">
                      <input type="checkbox"/>
                                                              总部未排车
				   </td>
               </tr>
                
                --%>
                <tr>
                   <td width="18%" align="right" class="detail_label">发货方编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="SEND_CHANN_ID" id="SEND_CHANN_ID" name="SEND_CHANN_ID" value="${rst.SEND_CHANN_ID}" />
                     <input json="SEND_CHANN_NO" name="SEND_CHANN_NO"  label="发货方编号" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.SEND_CHANN_NO}" /> 
				    <img align="absmiddle" name="selJGXX" style="cursor: hand"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selCommon('BS_19', false, 'SEND_CHANN_ID', 'CHANN_ID', 'forms[0]','SEND_CHANN_NO,SEND_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selSendChannParam')"> 
				   
				   </td>
                   <td width="15%" align="right" class="detail_label">发货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  label="发货方名称"  readonly="readonly" type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.SEND_CHANN_NAME}"/> 
				   </td>
                </tr>
                <tr>
                  <td width="18%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="4">
				     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
				   </td>
              </tr>
			</table>
		</td>
	</tr>
 
</table>
</form>
<input type="hidden" id="addFlag" value="${rst.GOODS_ORDER_ID}"/>
<input type="hidden" id="havaAreaSerId" value="${havaAreaSerId}"/>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
    SelDictShow("carType","185","${params.carType}","");
     //初始化 审批流程
     spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
</html>

 