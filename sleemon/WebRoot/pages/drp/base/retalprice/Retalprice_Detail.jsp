
<!--  
/**
 * @module 系统管理
 * @func 渠道信息
 * @version 1.1
 * @author 刘曰刚
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	
	<title>货品零售价设置详情</title>
</head>
<body class="bodycss1">	
<table width="100%"  border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">货品编号：</td>
					<td width="35%" class="detail_content">${rst.PRD_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">货品名称：</td>
					<td width="35%" class="detail_content">${rst.PRD_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">规格型号：</td>
					<td width="35%" class="detail_content">${rst.PRD_SPEC }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">品牌：</td>
					<td width="35%" class="detail_content">${rst.BRAND }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">出厂价：</td>
					<td width="35%" class="detail_content">${rst.FACT_PRICE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >制单人：</td>
					<td width="35%" class="detail_content">${rst.CRE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content">${rst.CRE_TIME }&nbsp;</td>
				</tr>
<!--				  //-- 0156117--Start--刘曰刚--2014-06-16// -->
				<tr>
					<td width="15%" align="right" class="detail_label" >更新人：</td>
					<td width="35%" class="detail_content">${rst.UPD_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">${rst.UPD_TIME }&nbsp;</td>
				</tr>
<!--				 //-- 0156117--End--刘曰刚--2014-06-16// -->
				<tr>
					<td width="15%" align="right" class="detail_label">帐套名称：</td>
					<td width="35%" class="detail_content">${rst.LEDGER_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
<script type=text/javascript >
</script>
</html>
