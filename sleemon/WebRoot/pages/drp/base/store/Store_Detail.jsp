<!--
 * @prjName:喜临门营销平台
 * @fileName:Store_Detial
 * @author wdb
 * @time   2013-08-13 14:01:22 
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
<body class="bodycss1" >
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
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
               		<td width="15%" align="right" class="detail_label">上级库房编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.PAR_STORE_NO}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">上级库房名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.PAR_STORE_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">库房类别:</td>
					<td width="35%" class="detail_content">
                        ${rst.STORE_TYPE}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">是否终端库房:</td>
					<td width="35%" class="detail_content">
                        <c:if test="${rst.TERM_STROE_FLAG eq 0}">否&nbsp;</c:if> 
                        <c:if test="${rst.TERM_STROE_FLAG eq 1}">是&nbsp;</c:if> 
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">所属单位编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.BEL_CHANN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">所属单位名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.BEL_CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">库位管理标记:</td>
					<td width="35%" class="detail_content">
						<c:if test="${rst.STORAGE_FLAG eq 0}">否&nbsp;</c:if>
                        <c:if test="${rst.STORAGE_FLAG eq 1}">是&nbsp;</c:if>
					</td>
					<td width="15%" align="right" class="detail_label">库房总容积:</td>
					<td width="35%" class="detail_content">
					  ${rst.TOTAL_VOLUME}&nbsp;
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
              		<td width="15%" align="right" class="detail_label">账套名称:</td>
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
               <tr>
               		<td width="15%" align="right" class="detail_label">详细地址:</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.DTL_ADDR}&nbsp;
					</td>
               </tr>
                
			</table>
		</td>
	</tr>
</table>
</body>
</html>