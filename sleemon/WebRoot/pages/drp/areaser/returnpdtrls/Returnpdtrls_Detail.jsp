<!--
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_Detial
 * @author wzg
 * @time   2013-08-15 10:17:13 
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
<body class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
			        <td width="15%" align="right" class="detail_label">退货申请单编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.PRD_RETURN_REQ_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">单据类型:</td>
					<td width="35%" class="detail_content">
                        ${rst.BILL_TYPE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">退货方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.RETURN_CHANN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">退货方名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.RETURN_CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">收货方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.RECV_CHANN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">收货方名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.RECV_CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">退货出库库房编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.RETURN_STORE_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">退货出库库房名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.RETURN_STORE_NAME}&nbsp;
					</td>
               </tr>
                <tr>
			        <td width="15%" align="right" class="detail_label">区域服务中心编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_SER_NO}&nbsp;
					</td>
			         <td width="15%" align="right" class="detail_label">区域服务中心名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_SER_NAME}&nbsp;
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
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">备注:</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.REMARK}&nbsp;
					</td>
                    
               </tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>