<!-- 
/**
 *@module 基础信息
 *@func 系统日志
 *@version 1.1
 *@author  guhongxiu
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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>系统日志详情</title>
</head>
<body STYLE='OVERFLOW-Y:SCROLL;' class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">日志编号：</td>
					<td width="35%" class="detail_content">${rst.SYSLOG_ID }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">模块名称：</td>
					<td width="35%" class="detail_content">${rst.UC_NAME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">操作名称：</td>
					<td width="35%" class="detail_content">${rst.ACT_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">调用类型：</td>
					<td width="35%" class="detail_content">${rst.OPRATOR }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">操作时间：</td>
					<td width="35%" class="detail_content">${rst.ACT_TIME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >服务码+操作码：</td>
					<td width="35%" class="detail_content">${rst.OPRCODE }&nbsp;</td>
				</tr>				
				<tr>
					<td width="15%" align="right" class="detail_label" >uid：</td>
					<td width="35%" class="detail_content">${rst.APPID }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">appcode：</td>
					<td width="35%" class="detail_content">${rst.APPCODE }</td> 
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.STATE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.REMARK }&nbsp;</td>
				</tr>
				
			</table>
		</td>
	</tr>
</table>
</body>
</html>
