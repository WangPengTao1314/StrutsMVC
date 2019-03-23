<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Storediff_Edit
 * @author wzg
 * @time   2013-08-30 14:03:21 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/storediffaff/Storediffaff_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：库存管理&lt;&lt;入库管理&lt;&lt;入库差异确认编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="15%" align="right" class="detail_label">入库方处理时间：</td>
				   <td width="35%" class="detail_content">
                     <input json="RECV_DEAL_TIME" name="RECV_DEAL_TIME" autocheck="true" label="入库方处理时间"  type="text"   inputtype="string"        maxlength="11"  value="${rst.RECV_DEAL_TIME}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">入库方处理人ID：</td>
				   <td width="35%" class="detail_content">
                     <input json="RECV_DEAL_PSON_ID" name="RECV_DEAL_PSON_ID" autocheck="true" label="入库方处理人ID"  type="text"   inputtype="string"        maxlength="32"  value="${rst.RECV_DEAL_PSON_ID}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">入库方处理人名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="REVC_DEAL_PSON_NAME" name="REVC_DEAL_PSON_NAME" autocheck="true" label="入库方处理人名称"  type="text"   inputtype="string"        maxlength="30"  value="${rst.REVC_DEAL_PSON_NAME}"/> 
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
 