<%--
/**
 * @module 消息管理
 * @fuc 公告生效部门
 * @version 1.1
 * @author 张忠斌
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
    <script type="text/javascript" src="${ctx}/pages/sys/notice/Notice_Dept_Edit.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>公告生效部门</title>
</head>
<body>
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td height="20px" valign="top">
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
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
<tr>
<td>
	<div class="lst_area">
	    <form> 
	    <input type="hidden" name="selectContion" value=" BMZT='启用' and IS_DRP_LEDGER=0">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
				<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
				<th nowrap align="center">部门名称</th> 
			</tr>
		</table>
		</form>
		</div>
	</td>
</tr>
</table>
</body>

<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = ['${rst.NOTC_DEPT_ID}',
	            '${rst.DEPT_ID}',
				'${rst.DEPT_NAME}'
				];
	 addRow(arrs);
	</c:forEach>
</script>


