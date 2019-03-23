<!--  
/**
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author  刘曰刚
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
	
	<title>返利额度变更单详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">返利额度变更单编号：</td>
					<td width="35%" class="detail_content">${rst.REBATE_CHANGE_REQ_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">单据类型：</td>
					<td width="35%" class="detail_content">${rst.BILL_TYPE}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">渠道编号：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NO}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">渠道名称：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NAME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">渠道类型：</td>
					<td width="35%" class="detail_content">${rst.CHANN_TYPE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >申请人：</td>
					<td width="35%" class="detail_content">${rst.REQ_PSON_NAME}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">变更前额度：</td>
					<td width="35%" class="detail_content">${rst.CHANGE_REBATE_OLD }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >变更后额度：</td>
					<td width="35%" class="detail_content">${rst.CHANGE_REBATE_NEW}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >变更额度：</td>
					<td width="35%" class="detail_content">${rst.CHANGE_REBATE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">${rst.STATE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >返利类别：</td>
					<td width="35%" class="detail_content">${rst.REBATE_TYPE }&nbsp;</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单人：</td>
					<td width="35%" class="detail_content">${rst.CRE_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content">${rst.CRE_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">${rst.UPD_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">${rst.UPD_TIME }&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">审核人：</td>
					<td width="35%" class="detail_content">${rst.AUDIT_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">审核时间：</td>
					<td width="35%" class="detail_content">${rst.AUDIT_TIME}&nbsp;</td>
					
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">${rst.DEPT_NAME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">${rst.ORG_NAME}&nbsp;</td>
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
</script>
</html>
