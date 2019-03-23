<!-- 
 *@module 分销业务
 *@func 人员信息
 *@version 1.1
 *@author lyg
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>人员信息详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">参数名称：</td>
					<td width="35%" class="detail_content">${rst.DATA_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">参数类型：</td>
					<td width="35%" class="detail_content">${rst.DATA_TYPE}&nbsp;</td>
			 	</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">参数值：</td>
					<td width="35%" class="detail_content">${rst.DATA_VAL}&nbsp;</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
