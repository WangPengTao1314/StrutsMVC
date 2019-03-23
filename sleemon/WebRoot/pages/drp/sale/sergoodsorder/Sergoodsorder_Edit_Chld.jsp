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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/sergoodsorder/Sergoodsorder_Edit_Chld.js"></script>
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
		   <c:if test="${pvg.PVG_CAL_MX eq 1 }">
		     <input id="bussClose" type="button" class="btn" value="取消(D)" title="Alt+D" accesskey="D" >
		   </c:if>
		   </td>
		</tr>
	</table>
	</td>
</tr>
<tr>
<td>
	<div class="lst_area">
	    <form>
		<input type="hidden" id="GOODS_ORDER_ID" name="GOODS_ORDER_ID">
		<input type="hidden" id="resultSize" name="resultSize" value="${resultSize}">
		<input type="hidden" id="CHANN_ID" name="CHANN_ID" value="${CHANN_ID}">
		
		<input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用'">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center">
              <input type="checkbox" style="height:12px;valign:middle" id="allChecked">
              </th>
              <th nowrap align="center">非标</th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">花号</th>
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">折后单价</th>
              <th nowrap align="center">原价格</th>
              <th nowrap align="center">订货数量</th>
              <th nowrap align="center">金额</th>
              
            
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
	           '${rst.GOODS_ORDER_DTL_ID}',
	           '${rst.PRD_ID}',
	           '',//非标
	           '${rst.PRD_NO}',//3
	           '${rst.PRD_NAME}',
	           '${rst.PRD_SPEC}',
	           '${rst.PRD_COLOR}',
	           '${rst.BRAND}',
	           '${rst.STD_UNIT}',
               '${rst.SPCL_TECH_ID}',//特殊工艺要求 9
               '${rst.PRICE}',//10
               '${rst.OLD_PRICE}',//11
               '${rst.DECT_RATE}',//12
               '${rst.DECT_PRICE}',//13折后单价
               '${rst.ORDER_NUM}',
               '${rst.ORDER_AMOUNT}',
               '${rst.VOLUME}',
               '${rst.SPCL_TECH_FLAG}'
               
              
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>