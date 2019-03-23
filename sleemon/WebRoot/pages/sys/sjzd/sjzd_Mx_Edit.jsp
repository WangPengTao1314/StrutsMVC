<!--  
/**
  *@module 系统管理
  *@fuc 数据字典明细编辑
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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
    <script type="text/javascript" src="${ctx}/pages/sys/sjzd/sjzd_Mx_Edit.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>数据字典维护</title>
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
			<tr class="fixedRow">
				<th nowrap align="center">
				<input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
				<th nowrap align="center">数据项名称</th>
				<th nowrap align="center">数据项值</th>
				<th nowrap align="center">数据项代码</th>
				<!--th nowrap align="center">上级数据项名称</th-->
				<th nowrap align="center">数据归类</th>
				<th nowrap align="center">其他说明</th>
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
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = ['${rst.SJZDMXID}',
				'${rst.SJXMC}',
				'${rst.SJXZ}',
				//'${rst.SJSJZDMXID}',
				'${rst.SJGL}',
				'${rst.REMARK}',
				'${rst.SJZDID}',
				'${rst.SJSJZDMXMC}',
				'${rst.KEYCODE}'];
	addRow(arrs);
	</c:forEach>
</script>
 

