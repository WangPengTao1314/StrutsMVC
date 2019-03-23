<!-- 
 *@module 渠道管理-装修管理
 *@func  装修补贴标准维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
    <script type="text/javascript" src="${ctx}/pages/drp/oamg/decorationallo/Decorationallo_Mx_Edit.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>装修补贴标准维护</title>
</head>
<body>
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
	<tr id="btntr">
		<td height="20px" valign="top">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0>
				<tr>
				   <td nowrap>
					   <input id="add" type="button" class="btn" value="新增(I)" title="Alt+I" accesskey="I" >
					   <input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G" >
					   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >
					   <input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">
		           </td>
		</tr>
	</table>
</td>
</tr>
<!--浮动按钮层结束-->
<!--占位用table，以免显示数据被浮动层挡住
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<tr>
<td>
	<div class="lst_area">
	    <form id="mainForm" name="mainForm">
	    <input type="hidden" name="selectData" id="selectData"/>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
				<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
				<th style="display:none"></th>
				<th nowrap align="center">商场位置类型</th>
				<th nowrap align="center">补贴金额(元/平米)</th>
				<th nowrap align="center">备注</th>
			</tr>
		</table>
		</form>
	</div>
	</td>
</tr>
</table>
<input id="delFlag" type="hidden" value="false"/>
<input id="RNVTN_SUBST_STD_ID" name="RNVTN_SUBST_STD_ID" type="hidden" value="${RNVTN_SUBST_STD_ID}"/> 
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	            '${rst.RNVTN_SUBST_STD_DTL_ID}',
	            '${rst.LOCAL_TYPE}',
	            '${rst.SUBST_AMOUNT}',
				'${rst.REMARK}'
				 ];
	addRow(arrs);
	</c:forEach>
</script>
 
