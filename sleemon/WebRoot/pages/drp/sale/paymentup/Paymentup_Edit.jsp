﻿<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Paymentup_Edit
 * @author lyg
 * @time   2013-08-23 10:25:58 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale//paymentup/Paymentup_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<div class="buttonlayer" id="floatDiv">
      <table cellSpacing=0 cellPadding=0 border=0 width="100%">
		<tr>
		   <td align="left" nowrap>
		   	<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
			<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick='parent.$("#label_1").click();'>
			</td>
		</tr>
	</table>
</div>
<!--浮动按钮层结束-->
<!--占位用table，以免显示数据被浮动层挡住-->
<table width="100%" height="25px">
    <tr>
        <td>&nbsp;</td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td>
		<form name="form" id="mainForm">
		<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
                     <input type="hidden" name=selectParam value=" RYZT in( '启用') and DELFLAG='0' and jgxxid in (select jgxxid from T_SYS_JGXX where ZTXXID='${rst.LEDGER_ID}')">
                     <input type="hidden" name=selectParams value="DEL_FLAG=0 and STATE='启用' ">
                   
                    <input json="PAYMENT_UPLOAD_ID" name="PAYMENT_UPLOAD_ID" autocheck="true" label="付款凭证上传ID" type="hidden" inputtype="string"   value="${rst.PAYMENT_UPLOAD_ID}"/> 
               <tr>
                   <td width="18%" align="right" class="detail_label">付款凭证编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="PAYMENT_UPLOAD_NO" name="PAYMENT_UPLOAD_NO"  label="付款凭证编号"  type="text"   readonly    mustinput="true"  
                     	<c:if test="${rst.PAYMENT_UPLOAD_NO==null}"> value="自动生成"</c:if>
                     	<c:if test="${rst.PAYMENT_UPLOAD_NO!=null}">value="${rst.PAYMENT_UPLOAD_NO}"</c:if>
                      /> 
				   </td>
                   <td width="15%" align="right" class="detail_label">凭证号：</td>
				   <td width="35%" class="detail_content">
                     <input json="PAYMENT_NO" name="PAYMENT_NO" autocheck="true" label="凭证号"  type="text"   inputtype="string"     mustinput="true"     maxlength="30"  value="${rst.PAYMENT_NO}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">金额：</td>
				   <td width="35%" class="detail_content">
				   
                     <input json="PAYMENT_AMOUNT" name="PAYMENT_AMOUNT" autocheck="true" label="金额"  type="text"   inputtype="float"     mustinput="true"   valueType="12,2"  value="${rst.PAYMENT_AMOUNT}"/>
				   </td>
				   <td width="18%" align="right" class="detail_label">付款凭证路径：</td>
				   <td width="35%" class="detail_content">
						<input json="PAYMENT_PATH" id="PAYMENT_PATH" name="PAYMENT_PATH" mustinput="true" type="text"  label="付款凭证路径" inputtype="string" value="${rst.PAYMENT_PATH }">
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">申请人：</td>
					<td width="35%" class="detail_content">
						<input json="REQ_PSON_ID" name="REQ_PSON_ID" autocheck="true" label="申请人ID" type="hidden" inputtype="string"   value="${rst.REQ_PSON_ID}"/> 
                       <input json="REQ_PSON_NAME" name="REQ_PSON_NAME" autocheck="true" label="申请人"  type="text"  inputtype="string" mustinput="true"  readonly    maxlength="30"  value="${rst.REQ_PSON_NAME}"/>
                        <img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
                        	onClick="selCommon('System_0', false, 'REQ_PSON_ID', 'RYXXID', 'forms[0]','REQ_PSON_ID,REQ_PSON_NAME', 'RYXXID,XM','selectParam')">
					</td>
                   <td width="15%" align="right" class="detail_label">联系方式：</td>
				   <td width="35%" class="detail_content">
                     <input json="TEL" name="TEL" autocheck="true" id="TEL" label="联系方式"  type="text"   inputtype="string"     mustinput="true"     maxlength="30"  value="${rst.TEL}"/> 
				   </td>
               </tr>
               <tr>
               	   <td width="15%" align="right" class="detail_label">渠道编号：</td>
				   <td width="35%" class="detail_content">
				 	  <input json="CHANN_ID" name="CHANN_ID" autocheck="true" label="渠道ID" type="hidden" inputtype="string"   value="${rst.CHANN_ID}"/>
                     <input json="CHANN_NO" name="CHANN_NO" autocheck="true" label="渠道编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.CHANN_NO}"/>
<!--                     <img align="absmiddle" name="selJGXX" style="cursor: hand"-->
<!--							src="${ctx}/styles/${theme}/images/plus/select.gif"       -->
<!--							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_ID,CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME', 'selectParams')"> -->
				   </td>
                   <td width="15%" align="right" class="detail_label">渠道名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_NAME" name="CHANN_NAME" autocheck="true" label="所属渠道"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">区域编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="AREA_ID" name="AREA_ID" autocheck="true" label="区域ID" type="hidden" inputtype="string"   value="${rst.AREA_ID}"/> 
                     <input json="AREA_NO" name="AREA_NO" autocheck="true" label="区域编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.AREA_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">区域名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="AREA_NAME" name="AREA_NAME" autocheck="true" label="区域名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="${rst.AREA_NAME}"/> 
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
	</td>
	</tr>
</table>
</body>
</html>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
	   uploadFile('PAYMENT_PATH', 'SAMPLE_DIR', true);
</script>
 