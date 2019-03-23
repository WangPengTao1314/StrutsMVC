<!--  
/**
 * @module 系统管理
 * @func 货品信息详细信息页面
 * @version 1.1
 * @author 刘曰刚
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
	<title>货品详情</title>
</head>
<body class="bodycss1">	
<div class="lst_area">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">货品分类编号：</td>
					<td width="35%" class="detail_content">${rst.PRD_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">货品分类名称：</td>
					<td width="35%" class="detail_content">${rst.PRD_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">上级货品分类编号：</td>
					<td width="35%" class="detail_content">${rst.PAR_PRD_NO }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">上级货品分类名称：</td>
					<td width="35%" class="detail_content">${rst.PAR_PRD_NAME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">品牌：</td>
					<td width="35%" class="detail_content">${rst.BRAND }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">默认交期：</td>
					<td width="35%" class="detail_content">${rst.DEAFULT_ADVC_SEND_DATE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >制单人：</td>
					<td width="35%" class="detail_content">${rst.CRE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >制单时间：</td>
					<td width="35%" class="detail_content">${rst.CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">${rst.ORG_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">${rst.DEPT_NAME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">${rst.UPD_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">${rst.UPD_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.STATE}&nbsp;</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.REMARK }&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
</body>
</html>
