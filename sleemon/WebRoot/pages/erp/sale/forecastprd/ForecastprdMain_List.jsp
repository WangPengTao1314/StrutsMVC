<!--  
/**
 * @module 预计量上报管理
 * @func 预计量上报货品设置
 * @version 1.1
 * @author 张忠斌
 */ 
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>预计量货品设置</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/forecastprd/ForecastprdMain_List.js"></script>
	<style type="text/css">
	  .query_div_1{
		display:none;
		width:100%;
		background-color:#e0edf6;
		filter:alpha(opacity=90);
		position:absolute;
		left:0;
		top:20;
		right:100%;
		bottom:100%;
	}
	
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0">
		<tr> 
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;预计量上报管理&gt;&gt;上报货品设置</td>
		</tr>
	  </table>  
	</td>
</tr>
 
</table>

<div id="querydiv" class="query_div_1" >
<form id="mainForm" method="post" action="#">
  <input type="hidden" name="selectParams" value=" STATE='启用' and FINAL_NODE_FLAG=0 and DEL_FLAG=0 ">
 
 <input type="hidden" name="search" id="search" value="true" />
 <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2" valign="top">
			<table id="querytb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">货品编号：</td>
					<td width="15%" class="detail_content">
	   					 <input id="PRD_NO" json="PRD_NO"  name="PRD_NO" type="text"  value="${params.PRD_NO}" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">货品名称：</td>
					<td width="15%" class="detail_content">
					 <input id="PRD_NAME" json="PRD_NAME"  name="PRD_NAME" type="text"  value="${params.PRD_NAME}" >
					</td>
				    <td width="8%" nowrap align="right" class="detail_label" >规格：</td>
					<td nowrap width="15%" class="detail_content">
						 <input id="PRD_SPEC" json="PRD_SPEC"  name="PRD_SPEC" type="text"  value="${params.PRD_SPEC}" >
					</td>
				</tr>
				<tr>
					<td width="8%" nowrap align="right" class="detail_label" >货品分类编号：</td>
					<td nowrap width="15%" class="detail_content">
						<input id="PAR_PRD_ID" json="PAR_PRD_ID" name="PAR_PRD_ID" type="hidden" inputtype="string" value="${params.PAR_PRD_ID}">
						<input id="PAR_PRD_NO" json="PAR_PRD_NO"  name="PAR_PRD_NO" type="text" inputtype="string"  value="${params.PAR_PRD_NO}" >
						<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_21', true, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]','PAR_PRD_ID,PAR_PRD_NO,PAR_PRD_NAME', 'PRD_ID,PRD_NO,PRD_NAME', 'selectParams')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">货品分类名称：</td>
					<td width="15%" class="detail_content">
						<input name="PAR_PRD_NAME" type="text" value="${params.PAR_PRD_NAME}" id="PAR_PRD_NAME" json="PAR_PRD_NAME">
					</td>
					<td width="8%" nowrap align="right" class="detail_label" > </td>
					<td nowrap width="15%" class="detail_content"> </td>
				</tr>
				<tr > 
				    <td width="8%" nowrap align="right" class="detail_label">上报结果显示：</td>
					<td width="15%" class="detail_content" colspan="7">
					   <input type="radio" name="notcharg" id="notcharg_0"  checked="checked" /> 显示全部
					   <input type="radio" name="notcharg" id="notcharg_1"   /> 显示未上报
					   <input type="radio" name="notcharg" id="notcharg_2"   /> 显示已上报
					</td>
				</tr>
				
				
				<tr>
					<td colspan="10" align="center" valign="middle" style="background-color:rgb(255, 255, 255);">
					   <c:if test="${pvg.PVG_BWS eq 1 }">
						<input id="query" type="button" class="btn" value="查询(Q)" title="Alt+Q" accesskey="Q">&nbsp;&nbsp;
						</c:if>
						<c:if test="${pvg.PVG_EDIT eq 1 }">
						 <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">&nbsp;&nbsp;
						</c:if>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>

</body>
</html>
