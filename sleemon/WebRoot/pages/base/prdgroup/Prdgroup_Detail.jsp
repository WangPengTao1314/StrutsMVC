<%--
/**
 * @module 
 * @fuc 货品组
 * @version 1.1
 * @author zhuxw
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>货品组信息详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right" class="detail_label">货品组编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.PRD_GROUP_NO}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">货品组名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.PRD_GROUP_NAME}&nbsp;
					</td>
				</tr>
				
				<tr>
					<td width="15%" align="right" class="detail_label">制单人：</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
					
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
					
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">
                        ${rst.ORG_NAME}&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.STATE}&nbsp;
					</td>
				</tr>
				 
				<tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td colspan="3" class="detail_content">
                        ${rst.REMARK}&nbsp;
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>

