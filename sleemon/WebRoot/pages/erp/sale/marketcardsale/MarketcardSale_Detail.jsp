﻿<!--
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10 
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
<body style="overflow: auto;" class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="" scrolling="AUTO">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
			   <tr>
			        <td width="15%" align="right" class="detail_label">卡券销售编号:</td>
					<td width="35%" class="detail_content"> ${rst.CARD_SALE_ORDER_NO}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label"> </td>
					<td width="35%" class="detail_content"> &nbsp;</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">渠道编号:</td>
					<td width="35%" class="detail_content"> ${rst.CHANN_NO}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">渠道名称:</td>
					<td width="35%" class="detail_content">  ${rst.CHANN_NAME}&nbsp;</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">终端编号:</td>
					<td width="35%" class="detail_content"> ${rst.TERM_NO}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">终端名称:</td>
					<td width="35%" class="detail_content">  ${rst.TERM_NAME}&nbsp;</td>
               </tr>
               
               <tr>
			        <td width="15%" align="right" class="detail_label">营销活动编号:</td>
					<td width="35%" class="detail_content"> ${rst.MARKETING_ACT_NO}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">营销活动名称:</td>
					<td width="35%" class="detail_content">  ${rst.MARKETING_ACT_NAME}&nbsp;</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">业务员名称:</td>
					<td width="35%" class="detail_content"> ${rst.SALE_PSON_NAME}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">销售日期:</td>
					<td width="35%" class="detail_content">  ${rst.SALE_DATE}&nbsp;</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">销售卡券总额:</td>
					<td width="35%" class="detail_content">  ${rst.SALE_CARD_AMOUNT}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">  ${rst.STATE}&nbsp; </td>
               </tr>
              
               <tr>
			        <td width="15%" align="right" class="detail_label">备注:</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.REMARK}&nbsp;
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
                    <td width="15%" align="right" class="detail_label">审核人:</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">审核时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_TIME}&nbsp;
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
			       <td width="15%" align="right" class="detail_label"></td>
				 <td width="35%" class="detail_content"> </td>
               </tr> 
			</table>
		</td>
	</tr>
</table>
</body>
</html>