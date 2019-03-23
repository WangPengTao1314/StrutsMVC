<!-- 
 *@module 基础资料
 *@func 维护编码规则
 *@version 1.1
 *@author 孙炜
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<title>编码规则维护详情</title>
	</head>
	<body class="bodycss1">	
	<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
		<tr>
			<td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
					<tr>
					    <td width="15%" align="right" class="detail_label">编码编号：</td>
						<td width="35%" class="detail_content">${rst.BMBH}&nbsp;</td>
						<td width="15%" align="right" class="detail_label">编码对象：</td>
						<td width="35%" class="detail_content">${rst.BMDX}&nbsp;</td>						
					</tr>
					<tr>
						<td width="15%" align="right" class="detail_label">规则名称：</td>
						<td width="35%" class="detail_content">${rst.GZMC}&nbsp;</td>
						<td width="15%" align="right" class="detail_label">总长度：</td>
						<td width="35%" class="detail_content">${rst.ZCD}&nbsp;</td>
					</tr>
					<tr>
						<td width="15%" align="right"class="detail_label">创建人：</td>
						<td width="35%" class="detail_content">${rst.CRENAME}&nbsp;</td>
						<td width="15%" align="right" class="detail_label">创建时间：</td>
						<td width="35%" class="detail_content">${rst.CRETIME }&nbsp;</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="detail_label">状态：</td>
						<td width="35%" class="detail_content" id="detail_state${rst.BMGZID}">${rst.STATE}</td>
						<td width="15%" align="right" class="detail_label">备注：</td>
						<td width="35%" class="detail_content" style="word-break:break-all">${rst.REMARK}</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</body>
</html>
