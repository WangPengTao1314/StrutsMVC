﻿<!--
 * @prjName:喜临门营销平台
 * @fileName:Drpreturnstorein_Detial
 * @author lyg
 * @time   2014-10-27 14:51:37 
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
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
			        <td width="15%" align="right" class="detail_label">处理人:</td>
					<td width="35%" class="detail_content">
                        ${rst.DEAL_PSON_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">年份月份:</td>
					<td width="35%" class="detail_content">
                        ${rst.YEAR_MONTH}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">结算日期:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATEMENUT_DATE}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">处理时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.DEAL_TIME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">入库单编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.STOREIN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">单据类型:</td>
					<td width="35%" class="detail_content">
                        ${rst.BILL_TYPE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">来源单据编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.FROM_BILL_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">订货方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORDER_CHANN_NO}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">订货方:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORDER_CHANN_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">负责人:</td>
					<td width="35%" class="detail_content">
                        ${rst.RSP_NAME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">入库库房编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.STOREIN_STORE_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">入库库房:</td>
					<td width="35%" class="detail_content">
                        ${rst.STOREIN_STORE_NAME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">制单人:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">更新人:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">更新时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">制单部门:</td>
					<td width="35%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单机构:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORG_NAME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">帐套名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.LEDGER_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
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