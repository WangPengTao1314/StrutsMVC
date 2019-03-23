<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Saleorder_Edit
 * @author zzb
 * @time   2013-10-12 13:52:19 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/saleordermge/Saleordermge_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>订单中心>>销售订单维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
					<input type="hidden" id="selSendChannParam" name="selSendChannParam" value=" STATE ='启用' and CHANN_ID in('${BASE_CHANN_ID}','${AREA_SER_ID}') "/>
					<input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE ='启用' and DEL_FLAG=0 and IS_BASE_FLAG=0 "/>
					<input type="hidden" id="selectAddrParams" name=selectAddrParams value=" DEL_FLAG=0 and STATE ='启用'  ">
					<input type="hidden" name="selectStore" id="selectStore" value=""/>
					<input type="hidden" name="selectShipParams" id="selectShipParams" value=" DEL_FLAG=0 and STATE='启用' "/>
					<input type="hidden" name="SALE_ORDER_ID" id="SALE_ORDER_ID" value="${rst.SALE_ORDER_ID}"/>
					<tr>
					  <td width="18%" align="right" class="detail_label">销售订单编号：</td>
					  <td width="35%" class="detail_content"><input type="text" value="${rst.SALE_ORDER_NO}" class="readonly" readonly/></td>
					  <td width="18%" align="right" class="detail_label">订货订单编号：</td>
					  <td width="35%" class="detail_content"><input type="text" value="${rst.FROM_BILL_NO}" class="readonly" readonly/></td>
					</tr>
					<tr>
					   <td width="18%" align="right" class="detail_label">订货方编号：</td>
					   <td width="35%" class="detail_content">
					    <input type="hidden" json="ORDER_CHANN_ID"  id="ORDER_CHANN_ID" name="ORDER_CHANN_ID" value="${rst.ORDER_CHANN_ID}" />
	                    <input json="ORDER_CHANN_NO" name="ORDER_CHANN_NO"   label="订货方编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.ORDER_CHANN_NO}"/><%--
			            <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selOrderChann();"> 
					   --%></td>
	                   <td width="15%" align="right" class="detail_label">订货方名称：</td>
					   <td width="35%" class="detail_content">
	                     <input json="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  label="订货方名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.ORDER_CHANN_NAME}"/>
	                     <input json="AREA_ID" name="AREA_ID" id="AREA_ID" type="hidden" value="${rst.AREA_ID}"/>
	                     <input json="AREA_NO" name="AREA_NO" id="AREA_NO" type="hidden" value="${rst.AREA_NO}"/>
	                     <input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" type="hidden" value="${rst.AREA_NAME}"/>
					   </td>
					</tr>
					<tr>
                   <td width="18%" align="right" class="detail_label">收货方编号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" json="IS_USE_REBATE"  id="IS_USE_REBATE" name="IS_USE_REBATE" label="" value="${rst.IS_USE_REBATE}"/>
				    <input type="hidden" json="RECV_CHANN_ID"  id="RECV_CHANN_ID" name="RECV_CHANN_ID" value="${rst.RECV_CHANN_ID}" />
                    <input json="RECV_CHANN_NO" name="RECV_CHANN_NO"   label="收货方编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.RECV_CHANN_NO}"/>
		            <%--<img align="absmiddle" name="selJGXX" style="cursor: hand"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selRecvChann();"> 
				   --%></td>
                   <td width="15%" align="right" class="detail_label">收货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  label="收货方名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.RECV_CHANN_NAME}"/>
				   </td>
               </tr>
               <tr>
               		<td width="18%" align="right" class="detail_label">单据类型：</td>
				   <td width="35%" class="detail_content">
                     <select label="单据类型" name="BILL_TYPE"
						 inputtype="string"
						style="width: 153" id="BILL_TYPE" json="BILL_TYPE"  onchange="delDtl()" 
						<c:if test="${rst.SALE_ORDER_ID!=null}"> disabled="disabled" </c:if>
						value="${rst.BILL_TYPE}" mustinput="true">
					</select>
				   </td>
                   <td width="15%" align="right" class="detail_label">发货基地：</td>
				   <td width="35%" class="detail_content">
				      <input type="hidden" id="SHIP_POINT_ID" json="SHIP_POINT_ID" name="SHIP_POINT_ID"  value="${rst.SHIP_POINT_ID}"/>
                      <input type="text"  id="SHIP_POINT_NAME" json="SHIP_POINT_NAME" name="SHIP_POINT_NAME"  autocheck="true" inputtype="string" mustinput="true" maxlength="50" readonly="readonly"  label="发货基地"  value="${rst.SHIP_POINT_NAME}" />
                       <img align="absmiddle" name="" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selCommon('BS_120', false, 'SHIP_POINT_ID', 'SHIP_POINT_ID', 'forms[0]','SHIP_POINT_NAME', 'SHIP_POINT_NAME', '');"> 
				   </td>
               </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">联系人：</td>
				   <td width="35%" class="detail_content">
                     <input json="PERSON_CON" name="PERSON_CON" id="PERSON_CON" label="联系人" mustinput="true" inputtype="string"  readonly="readonly"  type="text" value="${rst.PERSON_CON}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">联系人方式：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" id="TEL"  label="联系人方式"  type="text" mustinput="true" inputtype="string"   autocheck="true"   maxlength="30" readonly="readonly" value="${rst.TEL}"/> 
				   </td>
                </tr>
                <tr>
                  <td width="18%" align="right" class="detail_label">详细地址：</td>
				   <td width="35%" class="detail_content" >
				      <input type="hidden" id="RECV_ADDR_NO" json="RECV_ADDR_NO" name="RECV_ADDR_NO"  value="${rst.RECV_ADDR_NO}"/>
                      <input type="text"  id="RECV_ADDR" json="RECV_ADDR" name="RECV_ADDR"  autocheck="true" inputtype="string" mustinput="true" size="250" style="width: 340px;" maxlength="250" readonly="readonly"  label="详细地址"  value="${rst.RECV_ADDR}" />
                       <%--<img align="absmiddle" name="selAddr" style="cursor: hand"
					src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selAddrInfo();"> 
				   --%></td>
              
                   <td width="18%" align="right" class="detail_label">入库库房：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" name="STORE_ID" id="STORE_ID" json="STORE_ID" value="${rst.STORE_ID}">
				    <input type="hidden" name="STORE_NO" id="STORE_NO" json="STORE_NO" value="${rst.STORE_NO}">
				    <input json="STORE_NAME" name="STORE_NAME" id="STORE_NAME"  label="入库库房"  type="text" inputtype="string"  autocheck="true"   readonly value="${rst.STORE_NAME}"/><%--  
				     <img align="absmiddle" name="selJGXX" style="cursor: hand"
					 src="${ctx}/styles/${theme}/images/plus/select.gif"       
					  onClick="selStore()"> 
				   --%></td>
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
<!--                <tr>-->
<!--                   <td width="18%" align="right" class="detail_label">发货方编号：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--				     <input type="hidden" json="SEND_CHANN_ID" id="SEND_CHANN_ID" name="SEND_CHANN_ID" value="${rst.SEND_CHANN_ID}" />-->
<!--                     <input json="SEND_CHANN_NO" name="SEND_CHANN_NO"  label="发货方编号" readonly="readonly"  autocheck="true" inputtype="string"    mustinput="true"  type="text" value="${rst.SEND_CHANN_NO}" /> -->
<!--				    <img align="absmiddle" name="selJGXX" style="cursor: hand"-->
<!--					src="${ctx}/styles/${theme}/images/plus/select.gif"       -->
<!--					onClick="selCommon('BS_19', false, 'SEND_CHANN_ID', 'CHANN_ID', 'forms[0]','SEND_CHANN_NO,SEND_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selSendChannParam')"> -->
<!--				   -->
<!--				   </td>-->
<!--                   <td width="15%" align="right" class="detail_label">发货方名称：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--                     <input json="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  label="发货方名称"  readonly="readonly" type="text"  autocheck="true"  inputtype="string"     mustinput="true"     maxlength="100"  value="${rst.SEND_CHANN_NAME}"/> -->
<!--				   </td>-->
<!--                </tr>-->
	                <tr>
	                  <td width="18%" align="right" class="detail_label">备注：</td>
					   <td width="35%" class="detail_content" colspan="4">
				        <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
				      </td>
                 </tr> 
			     <tr>
                  <td colspan="4" align="center" class="detail_content">
				   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >
				   <input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="history.go(-1);">
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
	SelDictShow("BILL_TYPE", "BS_129", '${rst.BILL_TYPE}', "");
</script>
 