
<!--  
/**
 * @module 系统管理
 * @func 分销渠道信息
 * @version 1.1
 * @author  gu_hongxiu
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
	
	<title>详细信息</title>
</head>
<body class="bodycss1">	
<table width="100%"  border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">营销经理：</td>
					<td width="35%" class="detail_content">${rst.AREA_MANAGE_NAME}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">省份：</td>
					<td width="35%" class="detail_content">${rst.PROV }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">城市：</td>
					<td width="35%" class="detail_content">${rst.CITY }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">区县：</td>
					<td width="35%" class="detail_content">${rst.COUNTY}&nbsp;</td>					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">联系人：</td>
					<td width="35%" class="detail_content">${rst.PERSON_CON}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">联系人电话：</td>
					<td width="35%" class="detail_content">${rst.MOBILE}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">传真：</td>
					<td width="35%" class="detail_content">${rst.TAX }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">邮箱：</td>
					<td width="35%" class="detail_content">${rst.EMAIL}&nbsp;</td>					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >商场名称：</td>
					<td width="35%" class="detail_content">${rst.SALE_STORE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">商场地址：</td>
					<td width="35%" class="detail_content">${rst.SALE_STORE_LOCAL }&nbsp;</td>
				</tr>
				
				<tr>
					<td width="15%" align="right" class="detail_label">经营品牌：</td>
					<td width="35%" class="detail_content">${rst.BUSS_BRAND }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">主营品类：</td>
					<td width="35%" class="detail_content">${rst.BUSS_CLASS }&nbsp;</td>
				</tr>
				
				<tr>
					<td width="15%" align="right" class="detail_label">合作时间：</td>
					<td width="35%" class="detail_content">${rst.COOPER_DATE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">&nbsp;</td>
				</tr>
				
				<tr>
					<td width="15%" align="right" class="detail_label">制单人：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.CRE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">更新人：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.UPD_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.UPD_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.ORG_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.DEPT_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">帐套名称：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.LEDGER_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content" style="word-break:break-all">${rst.STATE }&nbsp;</td>
				</tr>								
				<tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.REMARK }&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
<script type=text/javascript >
	displayDownFile('BUSS_LIC_ATT','SAMPLE_DIR',true,false);
	displayDownFile('ORG_CERT_ATT','SAMPLE_DIR',true,false);
	displayDownFile('TAX_BURDEN_ATT','SAMPLE_DIR',true,false);
</script>
</html>
