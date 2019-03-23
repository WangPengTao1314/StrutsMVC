<!--
/**
 * @prjName:供应链_贵人
 * @fileName:Dststoreout_Edit_Chld
 * @author zsl
 * @time   2016-01-11 15:05:08 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store//dststoreout/Dststoreout_Edit_Chld.js"></script>
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
		<input type="hidden" id="STOREOUT_ID" name="STOREOUT_ID">
		<input type="hidden" id="FROM_BILL_DTL_IDS" value="${FROM_BILL_DTL_IDS}"/>
		<input type="hidden" name=selectParams id="selectParams" value="">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">货品类型</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">折扣率</th>
              <th nowrap align="center">折扣价</th>
              <th nowrap align="center">订货数量</th>
              <th nowrap align="center">已发数量</th>
              <th nowrap align="center">出库数量</th>
              <th nowrap align="center">折后金额</th>
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
	           '${rst.STOREOUT_DTL_ID}',
              '${rst.STD_UNIT}',
              '${rst.BRAND}',
              '${rst.PRD_ID}',
              '${rst.DECT_PRICE}',
              '${rst.REMARK}',
              '${rst.DECT_RATE}',
              '${rst.RECV_NUM}',
              '${rst.PRD_TYPE}',
              '${rst.PRD_NAME}',
              '${rst.REAL_NUM}',
              '${rst.PRICE}',
              '${rst.PRD_SPEC}',
              '${rst.SPCL_TECH_ID}',
              '${rst.PRD_NO}',
              '${rst.DECT_AMOUNT}',
              '${rst.SPCL_TECH_FLAG}',
              '${rst.PRD_COLOR}',
              '${rst.FROM_BILL_DTL_ID}',
              '${rst.ORDER_NUM}',
              '${rst.SEND_NUM}'
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>
