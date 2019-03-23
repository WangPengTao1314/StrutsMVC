<!--
 * @prjName:喜临门营销平台
 * @fileName:Drpreturna_Detial
 * @author lyg
 * @time   2014-10-26 15:41:36 
 * @version 1.1
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
	<title>信息详情</title>
</head>
<body>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
			        <td width="15%" align="right" class="detail_label">退货单编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.PRD_RETURN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">单据类型：</td>
					<td width="35%" class="detail_content">
                        ${rst.BILL_TYPE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">来源单据编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.FROM_BILL_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">退货方编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.RETURN_CHANN_NO}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">退货方名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.RETURN_CHANN_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">负责人</td>
					<td width="35%" class="detail_content">
						${rst.RSP_NAME}&nbsp;
					</td>
<!--			        <td width="15%" align="right" class="detail_label">预计退货日期：</td>-->
<!--					<td width="35%" class="detail_content">-->
<!--                        ${rst.EXPECT_RETURNDATE}&nbsp;-->
<!--					</td>-->
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">审核人：</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">审核时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_TIME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">年份月份：</td>
					<td width="35%" class="detail_content">
                        ${rst.YEAR_MONTH}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">
                        ${rst.ORG_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">帐套名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.LEDGER_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">
					</td>
               </tr>
               <tr>
					<td width="15%" align="right" class="detail_label">退回原因：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.RETURN_RSON }&nbsp;</td>
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
</html>