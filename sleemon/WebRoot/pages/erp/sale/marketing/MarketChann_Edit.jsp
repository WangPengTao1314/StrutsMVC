<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time   2014-12-03 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/marketing/MarketChann_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="/sleemon/scripts/core/keyboard_ctrl.js"></script>
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
			   <input id="back" type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">
		</td>
		</tr>
	</table>
	</td>
</tr>
<tr>
<td>
	<div class="lst_area">
	    <form>
	    <input type="hidden" id="selectParams" name="selectParams" value=""/>
	    <input type="hidden" id="CHANN_IDS" name="CHANN_IDS" value="${CHANN_IDS}"/>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
               <th nowrap align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>
               <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >渠道编号</th>
               <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
               <th  nowrap="nowrap" align="center" dbname="CHANN_TYPE" >渠道类型</th>
               <th  nowrap="nowrap" align="center" dbname="WAREA_NO" >战区编号</th>
               <th  nowrap="nowrap" align="center" dbname="WAREA_NAME" >战区名称</th>
               <th  nowrap="nowrap" align="center" dbname="AREA_MANAGE_NAME" >区域经理名称</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
<input id="flag" value="1" type="hidden"> 
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	           '${rst.MARKETING_CHANN_ID}',
              '${rst.CHANN_ID}',
              '${rst.CHANN_NO}',
              '${rst.CHANN_NAME}',
              '${rst.CHANN_TYPE}',
              '${rst.WAREA_ID}',
              '${rst.WAREA_NO}',
              '${rst.WAREA_NAME}',
              '${rst.AREA_MANAGE_ID}', 
              '${rst.AREA_MANAGE_NAME}'
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>