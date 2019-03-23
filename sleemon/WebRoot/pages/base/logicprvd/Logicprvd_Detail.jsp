<!--  
/**
 * @module 系统管理
 * @func 物流供应商
 * @version 1.0
 * @author 王栋斌
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
	<title>详细信息</title>
</head>
<body style="overflow-y: scroll;" class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">供应商编号：</td>
					<td width="35%" class="detail_content">${rst.PRVD_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">供应商名称：</td>
					<td width="35%" class="detail_content">${rst.PRVD_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">供应商类别：</td>
					<td width="35%" class="detail_content">${rst.PRVD_TYPE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">供应商级别：</td>
					<td width="35%" class="detail_content">${rst.PRVD_LVL }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >供应商性质：</td>
					<td width="35%" class="detail_content">${rst.PRVD_NATRUE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >国家：</td>
					<td width="35%" class="detail_content">${rst.NATION }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">省份：</td>
					<td width="35%" class="detail_content">${rst.PROV }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">城市：</td>
					<td width="35%" class="detail_content">${rst.CITY }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">区县：</td>
					<td width="35%" class="detail_content">${rst.COUNTY }&nbsp;</td>
			<!--  		<td width="15%" align="right" class="detail_label">供货周期：</td>
					<td width="35%" class="detail_content">${rst.PRVD_CY }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">供货能力：</td>
					<td width="35%" class="detail_content">${rst.PRVD_CAP}&nbsp;</td>-->
					<td width="15%" align="right" class="detail_label">业务员：</td>
					<td width="35%" class="detail_content">${rst.PERSON_BUSS}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">联系人：</td>
					<td width="35%" class="detail_content">${rst.PERSON_CON}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">邮编：</td>
					<td width="35%" class="detail_content">${rst.POST }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">电话：</td>
					<td width="35%" class="detail_content">${rst.TEL}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">手机：</td>
					<td width="35%" class="detail_content">${rst.MOBILE}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">传真：</td>
					<td width="35%" class="detail_content">${rst.TAX}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">Email：</td>
					<td width="35%" class="detail_content">${rst.EMAIL}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">Web：</td>
					<td width="35%" class="detail_content">${rst.WEB}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">法人代表：</td>
					<td width="35%" class="detail_content">${rst.LEGAL_REPRE}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">营业执照号：</td>
					<td width="35%" class="detail_content">${rst.BUSS_LIC}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">发票抬头：</td>
					<td width="35%" class="detail_content">${rst.INVOICE_TI }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">发票地址：</td>
					<td width="35%" class="detail_content">${rst.INVOICE_ADDR}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">开户行：</td>
					<td width="35%" class="detail_content">${rst.OPENING_BANK }&nbsp;</td>
				</tr>				
				<tr>
					<td width="15%" align="right" class="detail_label">银行账号：</td>
					<td width="35%" class="detail_content">${rst.BANK_ACCT}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">财务对照码：</td>
					<td width="35%" class="detail_content">${rst.FI_CMP_NO}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单人：</td>
					<td width="35%" class="detail_content">${rst.CRE_NAME}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content">${rst.CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">${rst.UPD_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">${rst.UPD_TIME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">${rst.ORG_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">${rst.DEPT_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">账套名称：</td>
					<td width="35%" class="detail_content">${rst.LEDGER_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.STATE}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >详细地址：</td>
					<td width="35%" class="detail_content" colspan="3" >${rst.ADDRESS}&nbsp;</td>
					 
				</tr>
				 
				<tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3" >${rst.REMARK}&nbsp;</td>
				 
				</tr>
				 
			</table>
		</td>
	</tr>
</table>
</body>
</html>
