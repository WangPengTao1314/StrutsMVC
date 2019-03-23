<!--
/*
  *@module 系统管理
  *@fuc 系统角色明细列表编辑
  *@version 1.1
  *@author 唐赟
  */
 -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
    <script type="text/javascript" src="${ctx}/pages/sys/xtjs/xtjs_Mx_Edit.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>系统角色明细编辑</title>
</head>
<body>
<table width="99.8%"  height="100%" border="0" cellSpacing=0 cellPadding=0>
	<tr id="btntr">
		<td height="20px" valign="top">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0>
				<tr>
				   <td nowrap>
					   <input id="add" type="button" class="btn" value="新增(I)" title="Alt+I" accesskey="I" >
					   <input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G" >
					   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >
					   <input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="goback();">
					</td>
				</tr>
			</table>
		</td>
	</tr>
<tr>
	<td>
	<div class="lst_area">
	    <form>
		<table id="jsontb" width="100%"border="0" cellpadding="1" cellspacing="1" class="lst">
		<input id='selcondition' type='hidden' name='selcondition' value="yhzt='启用'" />
			<tr class="fixedRow">
				<th nowrap align="center">
				<input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
				<th nowrap align="center">用户编号</th>
				<th nowrap align="center">用户名称</th>	
			</tr>
		</table>
		<input type="hidden" id="chistate" value="${chistate}" />
		</form>
		</div>
	</td>
</tr>
</table>
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rest" varStatus="row">
	var arrs = ['${rest.XTYHJSID}',
	            '${rest.YHBH}',
				'${rest.YHM}'];
	addRow(arrs);
	</c:forEach>
</script>
 

