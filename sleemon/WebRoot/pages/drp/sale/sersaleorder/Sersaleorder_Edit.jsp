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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/sersaleorder/Sersaleorder_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：区域服务中心管理>>订单中心>>销售订单维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <tr>
                   <td width="18%" align="right" class="detail_label">销售订单编号：</td>
				   <td width="35%" class="detail_content" colspan="3">
                    <input json="SALE_ORDER_NO" name="SALE_ORDER_NO"   label="销售订单编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.SALE_ORDER_NO}"/> 
		           </td>
		           
                   
               </tr>
                <tr>
                  <td width="18%" align="right" class="detail_label">订货方编号：</td>
				   <td width="35%" class="detail_content">
                    <input json="ORDER_CHANN_NO" name="RECV_CHANN_NO"   label="收货方编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.ORDER_CHANN_NO}"/> 
		           </td>
                   <td width="15%" align="right" class="detail_label">订货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  label="收货方名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.ORDER_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
                  <td width="18%" align="right" class="detail_label">发货方编号：</td>
				   <td width="35%" class="detail_content">
                    <input json="SEND_CHANN_NO" name="SEND_CHANN_NO"   label="发货方编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.SEND_CHANN_NO}"/> 
		           </td>
                   <td width="15%" align="right" class="detail_label">发货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  label="发货方名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.SEND_CHANN_NAME}"/> 
				   </td>
                </tr>
                <tr>
                  <td width="18%" align="right" class="detail_label">收货方编号：</td>
				   <td width="35%" class="detail_content">
                    <input json="RECV_CHANN_NO" name="RECV_CHANN_NO"   label="收货方编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.RECV_CHANN_NO}"/> 
		           </td>
                   <td width="15%" align="right" class="detail_label">收货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  label="收货方名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.RECV_CHANN_NAME}"/> 
				   </td>
                </tr>
                
                <tr>
                   <td width="18%" align="right" class="detail_label">联系人：</td>
				   <td width="35%" class="detail_content">
                     <input json="PERSON_CON" name="PERSON_CON" id="PERSON_CON" label="联系人" readonly="readonly"  type="text" value="${rst.PERSON_CON}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">电话：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" id="TEL"  label="电话"  type="text"   inputtype="string"  readonly="readonly"    maxlength="30"  value="${rst.TEL}"/> 
				   </td>
               </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">收货地址：</td>
				   <td width="35%" class="detail_content" colspan="3">
				   <textarea  json="RECV_ADDR" name="RECV_ADDR" id="RECV_ADDR" autocheck="true" inputtype="string"   maxlength="250" disabled="disabled"   label="收货地址"     rows="4" cols="80%" >${rst.RECV_ADDR}</textarea>
				   </td>
               </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="3">
				    <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"  label="备注"     rows="4" cols="80%" >${rst.REMARK}</textarea>
				   </td>
                   
                </tr>
			</table>
		</td>
		<td>
		 
		</td>
	</tr>
</table>
</form>
</body>
</html>

 