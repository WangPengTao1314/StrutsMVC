<!-- 
 *@module 系统管理
 *@func 部门信息
 *@version 1.1
 *@author 吴亚林
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
	<title>生产工厂详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">生产工厂编号：</td>
					<td width="35%" class="detail_content">${rst.FACTORY_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">生产工厂名称：</td>
					<td width="35%" class="detail_content">${rst.FACTORY_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">联系人：</td>
					<td width="35%" class="detail_content">${rst.PERSON_CON }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">手机：</td>
					<td width="35%" class="detail_content">${rst.MOBILE_NO}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">电话：</td>
					<td width="35%" class="detail_content">${rst.TEL }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">邮政编码：</td>
					<td width="35%" class="detail_content">${rst.POST}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >传真：</td>
					<td width="35%" class="detail_content">${rst.TAX }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.STATE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单人：</td>
					<td width="35%" class="detail_content">${rst.CRE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content">${rst.CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">${rst.DEPT_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">${rst.ORG_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">${rst.UPD_NAME }&nbsp;</td>
					<td width="15%" align="right"class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">${rst.UPD_TIME }&nbsp;</td>
				</tr>
				
			    <tr>
					<td width="15%" align="right" class="detail_label">帐套名称：</td>
					<td width="35%" class="detail_content">${rst.LEDGER_NAME}&nbsp;</td>
					<td width="15%" align="right"class="detail_label"></td>
					<td width="35%" class="detail_content">&nbsp;</td>
				</tr>
				<tr rowspan="2">
					<td width="15%" align="right" class="detail_label" rowspan="2">详细地址：</td>
					<td width="50%" class="detail_content" colspan="3" rowspan="2">${rst.ADDRESS }&nbsp;</td>
					<td width="35%" class="detail_content">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
