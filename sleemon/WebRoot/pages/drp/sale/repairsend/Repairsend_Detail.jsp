<!--
 * @prjName:喜临门营销平台
 * @fileName:Repairsend_Detial
 * @author chenj
 * @time   2013-11-03 16:25:12 
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
			        <td width="15%" align="right" class="detail_label">返修单编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.ERP_REPAIR_ORDER_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">预计返修日期:</td>
					<td width="35%" class="detail_content">
                        ${rst.REQ_FINISH_DATE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">返修方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.REPAIR_CHANN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">返修方名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.REPAIR_CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">返修方收货地址编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.DELIVER_ADDR_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">详细地址:</td>
					<td width="35%" class="detail_content">
                        ${rst.DELIVER_DTL_ADDR}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">区域编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">区域名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">制单人名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">更新人名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">更新时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">制单部门名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单机构名称:</td>
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
			        <td width="15%" align="right" class="detail_label">备注:</td>
					<td width="35%" class="detail_content">
                        ${rst.REMARK}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">
                        &nbsp;
					</td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>