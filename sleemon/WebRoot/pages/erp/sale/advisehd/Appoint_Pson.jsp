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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/advisehd/Appoint_Pson.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>指派处理人</title>
</head>
<body style="overflow-y: scroll;">
<form>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="15%" align="right"class="detail_label">处理人：</td>
					<td width="35%" class="detail_content">
						<input type="hidden" id="APPOINT_PSON_ID" json="APPOINT_PSON_ID" name="APPOINT_PSON_ID"/>
						<input type="text" id="APPOINT_PSON_NAME" name="APPOINT_PSON_NAME" readonly class="readonly"/>
						<img align="absmiddle" name="selAREA" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
						onClick="selCommon('BS_7',false,'APPOINT_PSON_ID','XTYHID','forms[0]','APPOINT_PSON_NAME,BM,ZW','XM,BMMC,ZW','')">
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">所属部门：</td>
					<td width="35%" class="detail_content">
						<input type="text" id="BM" name="BM" readonly class="readonly"/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">职务：</td>
					<td width="35%" class="detail_content">
						<input type="text" id="ZW" name="ZW" readonly class="readonly"/>
					</td>
				</tr>
			</table>
			<input type="hidden" id="CMPL_ADVS_ID" name="CMPL_ADVS_ID" value="${CMPL_ADVS_ID}">
		</td>
	</tr>
	<tr>
		<td align="right">
			<input type="button" id="cancel" name="cancel" value="退出" class="btn" onclick="window.close()">&nbsp;
			<input type="button" id="save" name="save" value="确认" class="btn">
		</td>
	</tr>
</table>
</form>
</body>
</html>
