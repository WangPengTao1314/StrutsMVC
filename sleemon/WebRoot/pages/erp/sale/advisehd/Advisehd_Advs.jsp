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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/advisehd/Advisehd_Advs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>建议信息</title>
</head>
<body style="overflow-y: scroll;" class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
			   <input id="XTYHID" type="hidden"  value="${XTYHID}" label="当前登录人ID" />
               <input id="QX" type="hidden"  value="${QX}" label="当前登录人权限级别" />
				<tr>
					<td width="15%" align="right"class="detail_label">建议概述：</td>
					<td width="85%" class="detail_content">${rst.ADVS_SMMRY}&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >建议内容：</td>
					<td width="85%" class="detail_content" colspan="3">
						<textarea rows="4" cols="110" readonly>${rst.CMPL_ADVS_CONTENT}</textarea>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label" >建议回馈信息：</td>
					<td width="85%" class="detail_content" colspan="3">
						<textarea rows="4" cols="110" id="FEEDBACK_INFO" name="FEEDBACK_INFO" maxlength="1000" readonly="readonly">${rst.FEEDBACK_INFO}</textarea>
					</td>
				</tr>
				 
			</table>
		</td>
	</tr>
</table>
</body>
</html>
