<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/areaser/senddirectnotice/Senddirectnotice_Edit_Chld.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>销售订单明细编辑</title>
</head>
<body class="bodycss1">
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
<td>
	<div class="" style="width: 100%;overflow-x: auto;  height: 100%;background-color: #fff;">
	    <form>
		<input type="hidden" id="REAL_SEND_NUM" value="${REAL_SEND_NUM}">
		<input type="hidden" id="BASE_DELIVER_NOTICE_ID" value="${params.BASE_DELIVER_NOTICE_ID}"/>
		<input type="hidden" id="SPCL_TECH_ID" value="${params.SPCL_TECH_ID}"/>
		<input type="hidden" id="sessionId" value="${sessionId}"/>
		<input type="hidden" id="PRD_ID" value="${params.PRD_ID}"/>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">销售订单编号</th>
              <th nowrap align="center">订货方名称</th>
              <th nowrap align="center">订货日期</th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">订货数量</th>
              <th nowrap align="center">已发货数量</th>
              <th nowrap align="center">分配数量</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
<tr>
	<td align="center">
		<input type="button" class="btn" id="allot"  value="确定分配"  />
		<input type="button" class="btn" onclick="window.close()" value="关闭"/>
		<span style="margin-left: 20px;">剩余分配数量</span><span id="surplusNum">${REAL_SEND_NUM}</span>
		<input type="hidden" id="allotNum"/>
	</td>
</tr>
</table>
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
			'${rst.SALE_ORDER_ID}',//0
			'${rst.SALE_ORDER_NO}',//1
			'${rst.ORDER_DATE}',//2
			'${rst.ORDER_CHANN_NAME}',//3
			'${rst.PRD_NO}',//4
			'${rst.PRD_NAME}',//5
			'${rst.PRD_SPEC}',//6
			'${rst.BRAND}',//7
			'${rst.SPCL_TECH_ID}',//8
			'${rst.SPCL_TECH_FLAG}',//9
			'${rst.ORDER_NUM}',//10
			'${rst.SENDED_NUM}',//11
			'${rst.SALE_ORDER_DTL_ID}',//12
			'${rst.ORDER_CHANN_ID}',//13
			'${rst.PRD_ID}',//14
			'${rst.PRD_COLOR}',//15
			'${rst.STD_UNIT}',//16
			'${rst.ORDER_CHANN_NO}',//17
			'${rst.BASE_DELIVER_NOTICE_DTL_ID}',//18
			'${rst.ALLOT_NUM}',//19
			'${rst.BASE_DELIVER_NOTICE_ID}',//20
			'${rst.RECV_CHANN_ID}',//21
			'${rst.RECV_CHANN_NO}',//22
			'${rst.RECV_CHANN_NAME}',//23
			'${rst.RECV_ADDR_NO}',//24
			'${rst.RECV_ADDR}',//25
			'${rst.DECT_PRICE}',//26
			'${rst.PRICE}',//27
			'${rst.DECT_RATE}',//28
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>