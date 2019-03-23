<!-- 
  *@module 系统模块 
  *@func 系统模块 
  *@version 1.1
  *@author zhuxw  
 -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<script type=text/javascript src="${ctx}/scripts/core/jquery-1.4.1.min.js"></script>
	<script type=text/javascript src="${ctx}/scripts/core/jquery.ztree-2.6.min.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/zTreeStyle.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<title>calendar</title>
	<script type=text/javascript >
	</script>
</head>

<body>
<table width="100%" height="98%" border="0" cellpadding="0" cellspacing="0">
<tr height="100%">
	<td nowrap="nowrap" height="500" valign="top">
	<p><p>
		<li>1.引入js文件    src="$&ctx&/scripts/util/My97DatePicker/WdatePicker.js"(将&&符号换成{})</li><p>
		<li>2.需要填写日期的input增加onclick事件 onclick="SelectDate();"</li><p>
		<li>3.后面的图标增加onclick事件 onclick="SelectDate(inputname);" 其中参数inputname为需要反填的input的name属性名</li><p>
		<p><p>
		<li>示例：<br>制单日期：
			<input json="LLRQ" name="LLRQ" type="text" autocheck="true" label="领料日期" inputtype="date" onclick="SelectDate();" mustinput="true" >
			<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(LLRQ);"></li>
	</td>
</tr>
</table>
</body>

