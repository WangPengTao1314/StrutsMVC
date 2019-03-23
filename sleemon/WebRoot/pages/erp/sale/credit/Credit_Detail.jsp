<!-- 
/**
 * @module 销售管理
 * @func 信用额度设定
 * @version 1.1
 * @author 郭利伟
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
	<title>信用额度设定</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">渠道编号：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NO }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">渠道名称：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NAME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">渠道简称：</td>
					<td width="35%" class="detail_content">${rst.CHANN_ABBR }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">初始信用额度：</td>
					<td width="35%" class="detail_content">${rst.INI_CREDIT }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">渠道类别：</td>
					<td width="35%" class="detail_content">${rst.CHANN_TYPE}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">渠道级别：</td>
					<td width="35%" class="detail_content">${rst.CHAA_LVL }&nbsp;</td>
				</tr>
<!--				<tr>-->
<!--					<td width="15%" align="right" class="detail_label">当前信用额度：</td>-->
<!--					<td width="35%" class="detail_content">${rst.CREDIT_CURRT}&nbsp;</td>-->
<!--					<td width="15%" align="right" class="detail_label"></td>-->
<!--					<td width="35%" class="detail_content"></td>-->
<!--				</tr>				-->
				<tr>
					<td width="15%" align="right" class="detail_label">区域编号：</td>
					<td width="35%" class="detail_content">${rst.AREA_NO }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">区域名称：</td>
					<td width="35%" class="detail_content">${rst.AREA_NAME }</td> 
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >信用修改人：</td>
					<td width="35%" class="detail_content">${rst.CREDIT_CRE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >信用修改时间：</td>
					<td width="35%" class="detail_content">${rst.CREDIT_CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >账套名称：</td>
					<td width="35%" class="detail_content">${rst.LEDGER_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" ></td>
					<td width="35%" class="detail_content"></td>
				</tr>
				
			</table>
		</td>
	</tr>
</table>
</body>
</html>
