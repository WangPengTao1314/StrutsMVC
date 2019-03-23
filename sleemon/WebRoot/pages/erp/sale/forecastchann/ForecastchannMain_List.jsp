<!--  
/**
 * @module 预计量上报管理
 * @func 预计量上报渠道设置
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
	<title>预计量渠道设置</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/forecastchann/ForecastchannMain_List.js"></script>
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
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;预计量上报管理&gt;&gt;上报渠道设置</td>
		</tr>
	  </table>  
	</td>
</tr>
 
</table>

<div id="querydiv" class="query_div_1" >
<form id="mainForm" method="post" action="#"> 
 <input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
 <input type="hidden" name="selectParamsT" value=" STATE in( '启用')">
 <input type="hidden" name="search" id="search" value="true" />
 <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2" valign="top">
			<table id="querytb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">渠道编号：</td>
					<td width="15%" class="detail_content">
	   					 <input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text"  value="${params.CHANN_NO}" >
	   					 <img align="absmiddle" name="selCHANN_NO_P" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'CHANN_NO', 'CHANN_NO', 'forms[0]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selectParamsT')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content">
					 <!--  -->
					 <input id="CHANN_NAME" json="CHANN_NAME"  name="CHANN_NAME" type="text"  value="${params.CHANN_NAME}" >
 					</td>
				    <td width="8%" nowrap align="right" class="detail_label" >类型：</td>
					<td nowrap width="15%" class="detail_content">
						 <select name="CHANN_TYPE" id="CHANN_TYPE" json="CHANN_TYPE" style="width: 155"></select>
 					</td>
				</tr>
				<tr>
				    <td width="10%" nowrap align="right" class="detail_label">战区编号：</td>
					<td width="15%" class="detail_content">
						<input name="AREA_NO_P" id="AREA_NO_P" json="AREA_NO_P" type="text" value="${params.AREA_NO_P}">
						<input name="AREA_ID_P" id="AREA_ID_P" type="hidden"/>
						<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_26', true, 'AREA_ID_P', 'AREA_ID', 'forms[0]',
										'AREA_NO_P,AREA_NAME_P', 
										'AREA_NO,AREA_NAME', 
										'selectParamsArea');">
					</td>
					<td width="10%" nowrap align="right" class="detail_label">战区名称：</td>
					<td width="15%" class="detail_content">
						<input name="AREA_NAME_P" id="AREA_NAME_P" json="AREA_NAME_P" type="text" value="${params.AREA_NAME_P}">
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
<<script type="text/javascript">
<!--
SelDictShow("CHANN_TYPE", "171", '${params.CHANN_TYPE}', "");
//-->
</script>
