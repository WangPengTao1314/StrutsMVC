<!-- 
/**
  *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *
 */
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
	<title>上报任务详情</title>
</head>
<body class="bodycss1">	

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
				<tr>
					<td width="15%" align="right"class="detail_label">上报任务编号：</td>
					<td width="35%" class="detail_content">${rst.ADVC_RPT_JOB_NO}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">年份：</td>
						<td width="35%" class="detail_content">${rst.YEAR}&nbsp;</td>
					</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">发布人：</td>
					<td width="35%" class="detail_content">${rst.SENDER_NAME}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">月份：</td>
					<td width="35%" class="detail_content">${rst.MONTH}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right"class="detail_label">发布时间：</td>
					<td width="35%" class="detail_content">${rst.SENDER_TIME}&nbsp;</td>
					<td width="15%" align="right" class="detail_label" >状态：</td>
					<td width="35%" class="detail_content">${rst.STATE }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">制单人：</td>
					<td width="35%" class="detail_content">${rst.CRE_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content">${rst.CRE_TIME}&nbsp;</td>
				</tr>
				<tr>
					
					<td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">${rst.DEPT_NAME }&nbsp;</td>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">${rst.ORG_NAME }&nbsp;</td>
				</tr>				
				<tr>
					
					<td width="15%" align="right"class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">${rst.UPD_NAME }</td> 
					<td width="15%" align="right" class="detail_label" >更新时间：</td>
					<td width="35%" class="detail_content">${rst.UPD_TIME }&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all"> ${rst.REMARK} </td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
