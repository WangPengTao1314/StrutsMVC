<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Sjzdwh_Edit_Chld
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
	<script type="text/javascript" src="${ctx}/pages/sys/xmsjzdwh/Sjzdwh_Edit_Chld.js"></script>
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
	    <form>
		<input type="hidden" id="DATA_ID" name="DATA_ID" value="${DATA_ID}">
		<input id='con' type='hidden' name='con' />
		
		
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">数据明细编号</th>
              <th nowrap align="center">数据明细名称</th>
              <th nowrap align="center">上级数据明细编号</th>
              <th nowrap align="center">上级数据明细名称</th>
              <th nowrap align="center">数据明细描述1</th>
              <th nowrap align="center">数据明细描述2</th>
              <th nowrap align="center">备注</th>
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
	           '${rst.DATA_DTL_ID}',
	           '${rst.DATA_DTL_CODE}',
	           '${rst.DATA_DTL_NAME}',
	           '${rst.PAR_DATA_DTL_CODE}',
               '${rst.PAR_DATA_DTL_NAME}',
               '${rst.DATA_DTL_DES_1}',
               '${rst.DATA_DTL_DES_2}',
               '${rst.REMARK}',
               '${rst.PAR_DATA_DTL_ID}'
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>