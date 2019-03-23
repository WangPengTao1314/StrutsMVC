
<!--  
/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
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
	<title>机构信息详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">小数格式化名称：</td>
					<td width="35%" class="detail_content">${rst.NUMFORMATMC}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">数字类型：</td>
					<td width="35%" class="detail_content">${rst.NUMTYPE}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">小数位：</td>
					<td width="35%" class="detail_content">${rst.DECIMALS }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">进位方式：</td>
					<td width="35%" class="detail_content">${rst.ROUNDTYPE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >创建时间：</td>
					<td width="35%" class="detail_content">${rst.CRETIME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >状态：</td>
					<td width="35%" class="detail_content">${rst.STATE }&nbsp;</td>
				</tr>
				
			</table>
		</td>
	</tr>
</table>
</body>
</html>
