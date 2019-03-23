<!--
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_Detial
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>信息详情</title>
</head>
<body style="overflow: auto;" class="bodycss1">
<table width="100%" scrolling="AUTO" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
			        <td width="15%" align="right" class="detail_label">盈亏单编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.PROFIT_LOSS_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">盘点单编号:</td>
					<td width="35%" class="detail_content">${rst.PRD_INV_NO}</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">库房编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.STORE_NO}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">库房名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.STORE_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">监盘人编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.INV_PSON_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">监盘人名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.INV_PSON_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">单据类型:</td>
					<td width="35%" class="detail_content">
                        ${rst.BILL_TYPE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">制单时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单人:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">更新时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">更新人:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">制单机构:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORG_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单部门:</td>
					<td width="35%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">备注:</td>
					<td width="85%" class="detail_content" colspan="3">
                        ${rst.REMARK}&nbsp;
					</td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	displayDownFile('STATENEBTS_ATT','SAMPLE_DIR',true,false);
</script>
</html>