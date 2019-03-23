<%--
/**
 * @module 
 * @fuc 货品组信息
 * @version 1.1
 * @author 
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/prdgroup/Prdgroup_Edit.js"></script>
	<title>货品组信息编辑</title>
</head>
<body class="bodycss1">	
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;基础信息&gt;&gt;货品组信息编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
<form method="POST" action="#" id="mainForm" >
<input type="hidden" name="tiaojian" value=" DELFLAG=0 and STATE='启用'">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table id="jsontb"  width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
				    <input type="hidden" id="PRD_GROUP_ID" value="${rst.PRD_GROUP_ID}"/>
					<td width="15%" align="right" class="detail_label">货品组编号：</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="PRD_GROUP_NO" name="PRD_GROUP_NO" autocheck="true" 
                         inputtype="string" label="货品组编号" maxlength="30" mustinput='true' valueType="chinese:false" value="${rst.PRD_GROUP_NO}"/>&nbsp;
					</td>
					
					<td width="15%" align="right" class="detail_label">货品组名称：</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="PRD_GROUP_NAME" name="PRD_GROUP_NAME" autocheck="true" 
                        seltarget="selMXXX"  inputtype="string" label="货品组名称"
                         maxlength="50" value="${rst.PRD_GROUP_NAME}" mustinput='true' />
					</td>
				</tr>
				 
				<tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td colspan="3" class="detail_content">
						<textarea cols="60" json="REMARK" name="REMARK" autocheck="true" 
						inputtype="string" label="备注" rows="2" maxlength="250">${rst.REMARK}</textarea>
				    </td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>

