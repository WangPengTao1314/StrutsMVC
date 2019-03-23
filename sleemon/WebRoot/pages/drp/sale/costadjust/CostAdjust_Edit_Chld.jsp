<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_Edit_Chld
 * @author lyg
 * @time   2013-08-19 14:35:52 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/costadjust/CostAdjust_Edit_Chld.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>终端退货录入明细编辑</title>
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
	<div class="" style="width: 100%;overflow-x: auto;  height: 100%;background-color: #fff;">
	    <form>
		<input type="hidden" id="COST_ADJUST_ID" name="COST_ADJUST_ID">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<input type="hidden" name=selectParams id="selectParams" value="">
			<input type="hidden" id="CHANN_ID" value="${CHANN_ID}"/>
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺编号</th>
              <th nowrap align="center">特殊工艺说明</th>
              <th nowrap align="center">调整后成本单价</th>
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
	           '${rst.COST_ADJUST_DTL_ID}',//0
              '${rst.PRD_ID}',//1
              '${rst.PRD_NO}',//2
              '${rst.PRD_NAME}',//3
              '${rst.PRD_SPEC}',//4
              '${rst.STD_UNIT}',//5
              '${rst.ADJUST_AMOUNT}',//6
              '${rst.REMARK}',//7
              '${rst.SPCL_TECH_ID}',//8
              '${rst.SPCL_TECH_FLAG}',//9
              '${rst.CUR_COST_PRICE}',//10
              '${rst.NEW_COST_PRICE}',//11
              '${rst.CHECKONLY}',//12
              '${rst.DM_SPCL_TECH_NO}',//13
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>