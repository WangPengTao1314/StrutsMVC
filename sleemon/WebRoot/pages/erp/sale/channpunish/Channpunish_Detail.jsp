
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
	
	<title>渠道信息详情</title>
</head>
<body >	
<div class="lst_area">
<table width="100%"  border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">渠道编号：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">渠道名称：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">渠道简称：</td>
					<td width="35%" class="detail_content">${rst.CHANN_ABBR }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">是否惩罚：</td>
					<td width="35%" class="detail_content">
						<c:if test="${rst.PUNISH_FLAG eq 1}">
							是
						</c:if>
						<c:if test="${rst.PUNISH_FLAG eq 0}">
							否
						</c:if>
					&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">渠道类型：</td>
					<td width="35%" class="detail_content">${rst.CHANN_TYPE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">渠道级别：</td>
					<td width="35%" class="detail_content">${rst.CHAA_LVL }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">区域编号：</td>
					<td width="35%" class="detail_content">${rst.AREA_NO }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">区域名称：</td>
					<td width="35%" class="detail_content">${rst.AREA_NAME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">惩罚说明：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.PUNISH_REMARK }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">处理人：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.DEAL_PSON_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">处理时间：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.DEAL_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">帐套名称：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.LEDGER_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content" style="word-break:break-all">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
</body>
<script type=text/javascript >
	displayDownFile('BUSS_LIC_ATT','SAMPLE_DIR',true,false);
	displayDownFile('ORG_CERT_ATT','SAMPLE_DIR',true,false);
	displayDownFile('TAX_BURDEN_ATT','SAMPLE_DIR',true,false);
</script>
</html>
