<!--  
/**
  *@module 系统管理
  *@fuc 数据字典编辑
  *@version 1.1
  *@author 张羽
*/
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script type=text/javascript src="${ctx}/pages/sys/sjzd/sjzd_Edit.js"></script>
	<title>新增或修改</title>
</head>
<body>
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：系统管理&gt;&gt;基础信息&gt;&gt;数据字典信息编辑</td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="15%" align="right" class="detail_label">数据字典编号：</td>
					<td width="35%" class="detail_content">
						<input json="SJZDBH" name="SJZDBH" id="SJZDBH" type="text" autocheck="true" valueType="chinese:false"  label="数据字典编号" inputtype="string" mustinput="true" maxlength="30" value="${rst.SJZDBH}" />
					</td>
					<td width="15%" align="right" class="detail_label">数据字典名称：</td>
					<td width="35%" class="detail_content">
						<input json="SJZDMC" name="SJZDMC" type="text" seltarget="selLL" autocheck="true" label="数据字典名称" inputtype="string"  mustinput="true"  maxlength="50"  value="${rst.SJZDMC}">
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">其他说明：</td>
					<td width="35%" class="detail_content" colspan="4">
       					<textarea  json="REMARK" name="REMARK" inputtype="string" maxlength="250" rows="3" cols="80%" ><c:out value="${rst.REMARK}"></c:out></textarea>
       				</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
 

