<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:区域折扣
 * @author zzb
 * @time   2013-08-30 15:55:09 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/dsct/Area_Dsct_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left" nowrap>
			<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
			<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick='parent.$("#label_1").click();'>
		</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" name="selectParams" value=" STATE='启用' " />
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
			       <td width="15%" align="right" class="detail_label">区域编号：</td>
				   <td width="35%" class="detail_content">
				      <input type="hidden" name="AREA_ID" id="AREA_ID" json="AREA_ID" value="${rst.AREA_ID}"/>
					  <input json="AREA_NO" name="AREA_NO" autocheck="true" label="区域编号" type="text" inputtype="string"  mustinput="true" READONLY  value="${rst.AREA_NO}"/>
			      	  <img align="absmiddle" name="selAREA" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						 onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[0]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selectParams')"> 								   		
					
				   </td>
				   <td width="15%" align="right" class="detail_label">区域名称：</td>
				   <td width="35%" class="detail_content">
					  <input json="AREA_NAME" name="AREA_NAME" autocheck="true" label="区域名称" type="text" inputtype="string"  mustinput="true" READONLY   value="${rst.AREA_NAME}"/>
				   </td>
			   </tr>
			   <tr>
			       <td width="15%" align="right" class="detail_label">货品编号：</td>
				   <td width="35%" class="detail_content">
				      <input type="hidden" json="PRD_ID" name="PRD_ID" id="PRD_ID" value="${rst.PRD_ID}"/>
					  <input json="PRD_NO" name="PRD_NO" autocheck="true" label="货品编号" type="text" inputtype="string"  mustinput="true" READONLY   value="${rst.PRD_NO}"/>
				      <img align="absmiddle" name="selAREA" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						onClick='selCommon("BS_21", false, "PRD_ID", "PRD_ID","forms[0]","PRD_NO,PRD_NAME,PRD_SPEC", "PRD_NO,PRD_NAME,PRD_SPEC", "selectParams")'> 								   		
					
				   </td>
				   <td width="15%" align="right" class="detail_label">货品名称：</td>
				   <td width="35%" class="detail_content">
					  <input json="PRD_NAME" name="PRD_NAME" autocheck="true" label="货品名称" type="text" inputtype="string"   mustinput="true" READONLY  value="${rst.PRD_NAME}"/>
				   </td>
			   </tr>
			   <tr>
			       <td width="15%" align="right" class="detail_label">规格型号：</td>
				   <td width="35%" class="detail_content">
					  <input json="PRD_SPEC" name="PRD_SPEC" autocheck="true" label="规格型号" type="text" inputtype="string"  mustinput="true" READONLY   value="${rst.PRD_SPEC}"/>
				   </td>
				   <td width="15%" align="right" class="detail_label">折扣类型：</td>
				   <td width="35%" class="detail_content">
					  <select json="DECT_TYPE" id="DECT_TYPE"  name="DECT_TYPE" style="width:155px"  inputtype="string" mustinput="true"  ></select>
				   </td>
			   </tr>
			   <tr>
			   	   <td width="15%" align="right" class="detail_label">基准价格：</td>
				   <td width="35%" class="detail_content">
					  <input json="BASE_PRICE" name="BASE_PRICE" id="BASE_PRICE" autocheck="true" label="基准价格" type="text" mustinput="true" inputtype="float" valueType="8,2" onkeyup="countPrice()"   value="${rst.BASE_PRICE}"/>
				   </td>
			       <td width="15%" align="right" class="detail_label">折扣率：</td>
				   <td width="35%" class="detail_content">
					  <input json="DECT_RATE" name="DECT_RATE" id="DECT_RATE" autocheck="true" label="折扣率" type="text" inputtype="float" valueType="2,2" mustinput="true" onkeyup="countPrice()"  value="${rst.DECT_RATE}"/>
				   </td>
				   
			   </tr>
			      <tr>
			       <td width="15%" align="right" class="detail_label">折扣价：</td>
				   <td width="35%" class="detail_content">
					  <input json="DECT_PRICE" name="DECT_PRICE" id="DECT_PRICE" autocheck="true" label="折扣价" type="text" mustinput="true" inputtype="float" READONLY valueType="8,2"   value="${rst.DECT_PRICE}"/>
				   </td>
				 <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content">
				   </td>
			   </tr>
		    </table>
	   </td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
	SelDictShow("DECT_TYPE","BS_7","${rst.DECT_TYPE}","");
</script>
</html>

 