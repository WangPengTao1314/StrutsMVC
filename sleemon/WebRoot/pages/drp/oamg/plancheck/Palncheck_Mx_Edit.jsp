<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Termrefinecheck_Edit_Chld
 * @author lyg
 * @time   2014-01-26 14:46:31 
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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/plancheck/Palncheck_Mx_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>明细信息编辑</title>
</head>
<body class="bodycss1">
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
	    <form name="mainForm">
		<input type="hidden" id="CHANN_CHECK_PLAN_ID" name="CHANN_CHECK_PLAN_ID" value="${CHANN_CHECK_PLAN_ID}">
		<input type="hidden" name="selectData" value=" DATA_DTL_ID not in (select PAR_DATA_DTL_ID from PROJECT_DATA_DICTIONARY_DTL where PAR_DATA_DTL_ID is not null) "/>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">检查项目类型</th>
              <th nowrap align="center">检查项目编号</th>
              <th nowrap align="center">检查项目名称</th>
              <th nowrap align="center">项目分值</th>
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
	var arrs = [
	          '${rst.CHANN_CHECK_PLAN_DTL_ID}',
              '${rst.CHANN_CHECK_PLAN_ID}',
              '${rst.PRJ_TYPE}',
              '${rst.PRJ_CODE}',
              '${rst.PRJ_NAME}',
              '${rst.PRJ_SCORE}'
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>