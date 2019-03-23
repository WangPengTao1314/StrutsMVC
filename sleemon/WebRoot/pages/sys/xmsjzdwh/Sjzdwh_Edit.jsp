<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Sjzdwh_Edit
 * @author chenj
 * @time   2014-01-30 10:18:20 
 * @version 1.1
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/sys/xmsjzdwh/Sjzdwh_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="28" align="center"></td>
		<td>当前位置：基础数据＞＞项目数据字典维护编辑</td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="15%" align="right" class="detail_label">数据编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="DATA_CODE" name="DATA_CODE" autocheck="true" label="数据编号"  type="text"   inputtype="string"        maxlength="30"  value="${rst.DATA_CODE}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">数据名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="DATA_NAME" name="DATA_NAME" autocheck="true" label="数据名称"  type="text"   inputtype="string"        maxlength="30"  value="${rst.DATA_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content">
                     <input json="DATA_REMARK" name="DATA_REMARK" autocheck="true" label="备注"  type="text"   inputtype="string"        maxlength="300"  value="${rst.DATA_REMARK}"/> 
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
</form>
</body>
</html>
<script type="text/javascript">
</script>
 