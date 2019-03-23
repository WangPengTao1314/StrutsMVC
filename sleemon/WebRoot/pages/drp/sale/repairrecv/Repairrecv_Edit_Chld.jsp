<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Repairapp_Edit_Chld
 * @author chenj
 * @time   2013-11-03 16:25:12 
 * @version 1.1
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/sale/repairrecv/Repairrecv_Edit_Chld.js"></script>
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
		<input type="hidden" id="ERP_REPAIR_ORDER_ID" name="ERP_REPAIR_ORDER_ID">
		<input type="hidden" name="selectContion" value=" STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1 and COMM_FLAG=1 ">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">花号</th>
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">返修单价</th>
              <th nowrap align="center">返修数量</th>
              <th nowrap align="center">返修原因</th>
              <th nowrap align="center">返修附件</th>
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
	           '${rst.REPAIR_ORDER_DTL_ID}',//0
	          '${rst.PRD_ID}',//1
              '${rst.PRD_NO}',//2
              '${rst.PRD_NAME}',//3
              '${rst.PRD_SPEC}',//4
              '${rst.PRD_COLOR}',//5
              '${rst.BRAND}',//6
              '${rst.STD_UNIT}',//7
              '${rst.REPAIR_NUM}',//8
              '${rst.REPAIR_RESON}',//9
              '${rst.REPAIR_ATT}',//10
              '${rst.VOLUME}',//11
              '${rst.REPAIR_PRICE}',//12
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>
