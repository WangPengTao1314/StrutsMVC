<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorder_Edit_Chld
 * @author zzb
 * @time   2013-08-31 11:40:47 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/deliverconfm/DeliverConfm_U9_Test.js"></script>
    <script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>发运单测试U9</title>
</head>
<body class="bodycss1">
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td height="20px" valign="top">
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr>
		   <td nowrap>
		     <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
		   </td>
		</tr>
	</table>
	</td>
</tr>
<tr>
<td>
	<div class="lst_area">
	    <form>
	          发运单编号
		<input type="text" id="DELIVER_ORDER_NO" name="DELIVER_ORDER_NO">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center">
              <input type="checkbox" style="height:12px;valign:middle" id="allChecked">
              </th>
              <th nowrap align="center">销售订单编号</th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">实发数量</th>
              <th nowrap align="center">货品扫码</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
</body>
<script type="text/javascript">
	for(var i=0;i<3;i++){
		addRow();
	}
</script>
</html>