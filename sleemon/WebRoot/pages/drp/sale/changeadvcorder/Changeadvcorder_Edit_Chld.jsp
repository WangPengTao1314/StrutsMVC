<!--
 * @prjName:喜临门营销平台
 * @fileName:Changeadvcorder_Frame
 * @author ghx
 * @time   2014-05-20 15:14:52 
 * @version 1.1
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/changeadvcorder/Changeadvcorder_Edit_Chld.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>预订单变更明细编辑</title>
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
		<input type="hidden" id="ADVC_ORDER_ID" name="ADVC_ORDER_ID">
		<input type="hidden" id="errorMsg" name="errorMsg">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<input type="hidden" name=selectParams id="selectParams" value="">
			<input type="hidden" id="CHANN_ID" value="${CHANN_ID}"/>
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
<!--              <th nowrap align="center">花号</th>-->
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">折扣率</th>
              <th nowrap align="center">折后单价</th>
              <th nowrap align="center">订货数量</th>
              <th nowrap align="center">应收金额</th>
              <th nowrap align="center">交货日期</th>
              <th nowrap align="center">冻结数量</th>
              <th nowrap align="center">变更原因</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
</body>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	           '${rst.ADVC_ORDER_DTL_ID}',
              '${rst.PRD_ID}',
              '${rst.PRD_NO}',
              '${rst.PRD_NAME}',
              '${rst.PRD_SPEC}',
              '${rst.PRD_COLOR}',
              '${rst.BRAND}',
              '${rst.STD_UNIT}',
              '${rst.PRICE}',
              '${rst.DECT_RATE}',
              '${rst.DECT_PRICE}',
              '${rst.ORDER_NUM}',
              '${rst.PAYABLE_AMOUNT}',
              '${rst.REMARK}',
              '${rst.FROM_BILL_DTL_ID}',
              '${rst.SPCL_TECH_ID}',
              '${rst.SPCL_TECH_FLAG}',
              '${rst.ADVC_ORDER_CHANGE_DTL_ID}',
              '${rst.ADVC_ORDER_CHANGE_ID}',
              '${rst.ORDER_RECV_DATE}',
              '${rst.FREEZE_NUM}',
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>