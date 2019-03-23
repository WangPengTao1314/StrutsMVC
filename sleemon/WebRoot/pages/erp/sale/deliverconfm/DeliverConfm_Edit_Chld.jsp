<!--
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 发运确认
 * @author zzb
 * @time   2013-10-12 13:52:19 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/deliverconfm/DeliverConfm_Edit_Chld.js"></script>
    <script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>明细信息编辑</title>
</head>
<body class="bodycss1">
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td height="20px" valign="top"  >
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr>
		   <td nowrap >
			   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >
			   &nbsp;&nbsp;&nbsp;&nbsp;
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
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
			 <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >订单编号</th>
			  <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
              <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
              <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
              <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
              <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
              <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th><%--
              <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
              --%><th  nowrap="nowrap" align="center" dbname="SPCL_TECH_ID" >特殊工艺</th>
              <th  nowrap="nowrap" align="center" dbname="IS_NO_STAND_FLAG" >是否非标</th>
              <th  nowrap="nowrap" align="center" dbname="RECV_ADDR" >收货地址</th>
              <th  nowrap="nowrap" align="center" dbname="ADVC_PLAN_NUM" >预排发运数量</th>
              <th  nowrap="nowrap" align="center" dbname="PLAN_NUM" >计划发运数量</th>
              <th  nowrap="nowrap" align="center" dbname="REAL_SEND_NUM" >实际发运数量</th>
              <th  nowrap="nowrap" align="center" dbname="" >差异数量</th>
              <th  nowrap="nowrap" align="center" dbname="" >剩余货品处理方式</th>
              <th  nowrap="nowrap" align="center" dbname="" >状态</th>
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
	           '${rst.DELIVER_ORDER_DTL_ID}',//0
	           '${rst.SALE_ORDER_NO}',//1
	           '${rst.ORDER_CHANN_NAME}',//2
	           '${rst.PRD_NO}',//3
	           '${rst.PRD_NAME}',//4
	           '${rst.PRD_SPEC}',//5
	           '${rst.PRD_COLOR}',//6
	           '${rst.BRAND}',///7
	           '${rst.STD_UNIT}',//8
               '${rst.SPCL_TECH_ID}',//特殊工艺要求 9
               '${rst.IS_NO_STAND_FLAG}',//是否非标10
               '${rst.RECV_ADDR}',//11
               '${rst.ADVC_PLAN_NUM}',//12
               '${rst.PLAN_NUM}',//13
               '${rst.REAL_SEND_NUM}',//14
               '${rst.PLAN_NUM-rst.REAL_SEND_NUM}',//15
               '${rst.NO_SEND_DEAL_TYPE}',//16
               '${rst.IS_SEND_FIN}'
              
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>