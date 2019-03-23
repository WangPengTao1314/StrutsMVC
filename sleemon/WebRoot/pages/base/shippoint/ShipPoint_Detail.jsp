<!-- 
 *@module 系统管理
 *@func 生产基地维护
 *@version 1.0
 *@author 王志格
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
	<title>生产基地详情</title>
</head>
<body  class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">生产基地编号：</td>
					<td width="35%" class="detail_content">${rst.SHIP_POINT_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">生产基地名称：</td>
					<td width="35%" class="detail_content">${rst.SHIP_POINT_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单人：</td>
					<td width="35%" class="detail_content">${rst.CRE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content">${rst.CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">${rst.UPD_NAME }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">${rst.UPD_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">${rst.ORG_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">${rst.DEPT_NAME }&nbsp;</td>
				</tr>
				
				
			    <tr>
					<td width="15%" align="right" class="detail_label">帐套名称：</td>
					<td width="35%" class="detail_content">${rst.LEDGER_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.STATE }&nbsp;</td>
				</tr>
				<tr >
					<td width="15%" align="right" class="detail_label">详细地址：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.ADDRESS }&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
