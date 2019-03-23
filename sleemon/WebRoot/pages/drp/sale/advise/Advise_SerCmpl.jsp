<!--
 * @prjName:喜临门营销平台
 * @fileName:Advise_List
 * @author wdb
 * @time   2013-08-17 10:29:35 
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
	<title>服务投诉回馈信</title>
</head>
<body style="overflow-y: scroll;">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="15%" align="right"class="detail_label">投诉对象：</td>
					<td width="35%" class="detail_content">${rst.CMPL_OBJ}&nbsp;</td>
					<td width="15%" align="right"class="detail_label">投诉的人员：</td>
					<td width="35%" class="detail_content">${rst.CMPL_TO_PSON}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >投诉内容：</td>
					<td width="35%" class="detail_content" colspan="3" >
					<textarea rows="4" cols="110" readonly>${rst.CMPL_ADVS_CONTENT}</textarea>
					</td>
				</tr>
			 
				<tr>
					<td width="15%" align="right" class="detail_label" >投诉回馈信息：</td>
					<td width="35%" class="detail_content" colspan="3" >
					<textarea rows="4" cols="110" readonly>${rst.FEEDBACK_INFO}</textarea>
					</td>
				</tr>
			 
			</table>
		</td>
	</tr>
</table>
</body>
</html>
