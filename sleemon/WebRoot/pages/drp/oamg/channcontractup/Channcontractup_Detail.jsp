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
	<title>渠道合同上传详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">渠道合同编号：</td>
					<td width="35%" class="detail_content">${rst.CHANN_CONTRACT_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">渠道编号：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NO}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">渠道名称：</td>
					<td width="35%" class="detail_content">${rst.CHANN_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">合同附件：</td>
					<td width="35%" class="detail_content">
						<input type="hidden" id ="CONTRACT_ATT" value='${rst.CONTRACT_ATT}' />
					&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">年度指标：</td>
					<td width="35%" class="detail_content">${rst.YEAR_PLAN_AMOUNT }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >一季度指标：</td>
					<td width="35%" class="detail_content">${rst.FIRST_QUARTER_AMOUNT}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">二季度指标：</td>
					<td width="35%" class="detail_content">${rst.SECOND_QUARTER_AMOUNT }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >三季度指标：</td>
					<td width="35%" class="detail_content">${rst.THIRD_QUARTER_AMOUNT}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">四季度指标：</td>
					<td width="35%" class="detail_content">${rst.FOURTH_QUARTER_AMOUNT }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" ></td>
					<td width="35%" class="detail_content">&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">年份：</td>
					<td width="35%" class="detail_content">${rst.YEAR }&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >状态：</td>
					<td width="35%" class="detail_content">${rst.STATE}&nbsp;</td>
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
displayDownFile('CONTRACT_ATT','SAMPLE_DIR',false,false,true);
</script>
</html>
