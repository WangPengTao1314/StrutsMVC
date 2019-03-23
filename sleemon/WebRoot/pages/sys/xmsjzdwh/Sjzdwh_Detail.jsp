<!--
 * @prjName:喜临门营销平台
 * @fileName:Sjzdwh_Detial
 * @author chenj
 * @time   2014-01-30 10:18:20 
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
			        <td width="15%" align="right" class="detail_label">数据编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.DATA_CODE}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">数据名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.DATA_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">备注:</td>
					<td width="35%" class="detail_content">
                        ${rst.DATA_REMARK}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>