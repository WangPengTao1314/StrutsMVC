﻿<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcgoodsapp_Edit_Chld
 * @author lyg
 * @time   2013-11-02 18:55:53 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale//advcgoodsapp/Advcgoodsapp_Edit_Chld.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>明细信息编辑</title>
</head>
<body >
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<table width="99.8%" height="100%" border="0" cellSpacing=0 cellPadding=0>
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
		<input type="hidden" id="ADVC_SEND_REQ_ID" name="ADVC_SEND_REQ_ID">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
		<input type="hidden" name=selectParams id="selectParams" value="">
		<input type="hidden" id="FROM_BILL_DTL_IDS" value="${FROM_BILL_DTL_IDS}"/>
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
              <th nowrap align="center">通知出库数量</th>
              <th nowrap align="center">本次冻结数量</th>
              <th nowrap align="center">交货日期</th>
              <th nowrap align="center">已发货数量</th>
              <th nowrap align="center">订货数量</th>
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
	           '${rst.ADVC_SEND_REQ_DTL_ID}',//1
              '${rst.PRD_NO}',//2
              '${rst.PRD_NAME}',//3
              '${rst.PRD_SPEC}',//4
              '${rst.PRD_COLOR}',//5
              '${rst.BRAND}',//6
              '${rst.STD_UNIT}',//7
              '${rst.NOTICE_NUM}',//8
              '${rst.PRD_ID}',//9
              '${rst.REMARK}',//10
              '${rst.FROM_BILL_DTL_ID}',//11
              '${rst.SPCL_TECH_ID}',//12
              '${rst.SPCL_TECH_FLAG}',//13
              '${rst.PRICE}',//14
              '${rst.DECT_RATE}',//15
              '${rst.DECT_PRICE}',//16
              '${rst.DECT_AMOUNT}',//17
              '${rst.FREEZE_NUM}',//18
              '${rst.ORDER_RECV_DATE}',//19
              '${rst.SEND_NUM}',//20已申请发货数量
              '${rst.ORDER_NUM}',//21订货数量
              '${rst.FREEZE_TO_SEND_NUM}',//22冻结转发货数量
              '${rst.FREEZE}',//23本次冻结数量
              '${rst.ADV_FREEZE_NUM}',//24 预订单明细的冻结数量
              '${rst.CAN_NOTICE_NUM}',//25 可通知出库数量
              '${rst.PRD_TYPE}'
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>