<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/advctoorder/Advctoorder_Edit_Chld.js"></script>
	<style type="text/css">
		.readonly{
			width:70px;
		}
	</style>
	<title>预订单转订货处理</title>
</head>
<body class="bodycss1">
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td height="20px" valign="top">
	<h1 align="center">预订单转订货处理</h1>
	</td>
</tr>
<tr>
<td>
	<div class="lst_area">
	    <form id="pageForm" method="post">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
		<input type="hidden" name=selectParams id="selectParams" value="STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1">
		<input type="hidden" name="DECT_RATE" id="DECT_RATE" value="${DECT_RATE}"/>
		<input type="hidden" id="error" value="${error}"/>
		<input type="hidden" id="CHANN_ID" value="${CHANN_ID}"/>
		<input type="hidden" name="ADVC_ORDER_IDS" id="ADVC_ORDER_IDS" value="${ADVC_ORDER_IDS}"/>
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center" >货品编号</th>
              <th nowrap align="center" >货品名称</th>
              <th nowrap align="center">规格型号</th>
<!--              <th nowrap align="center">花号</th>-->
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">折扣率</th>
              <th nowrap align="center">折后单价</th>
              <th nowrap align="center">订货数量</th>
              <th nowrap align="center">已发货数量</th>
              <th nowrap align="center">可用库存</th>
              <th nowrap align="center">实际订货数量</th>
              <th nowrap align="center">抵库数量</th>
              <th nowrap align="center">订货金额</th>
              <th nowrap align="center">交货日期</th>
              <th nowrap align="center">备注</th>
              <th nowrap align="center">货品状态</th>
              <th nowrap align="center">货品类型</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
<table cellSpacing=0 cellPadding=0 border=0 width="100%">
		<tr>
		   <td nowrap align="center">
			   <input id="create" type="button" class="btn" value="加入购物车(C)" title="Alt+C" accesskey="C" >
			   <input id="close" type="button" class="btn" value="关闭(N)" title="Alt+N" accesskey="N" >
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	          '${rst.PRD_ID}',//0
              '${rst.PRD_NO}',//1
              '${rst.PRD_NAME}',//2
              '${rst.PRD_SPEC}',//3
              '${rst.PRD_COLOR}',//4
              '${rst.BRAND}',//5
              '${rst.STD_UNIT}',//6
              '${rst.SPCL_TECH_FLAG}',//7
              $.trim('${rst.BASE_PRICE}'),//8
              $.trim('${rst.DECT_RATE}'),//9
             $.trim('${rst.DECT_PRICE}'),//10
              '${rst.ORDER_NUM}',//11
              '${rst.ADVC_AMOUNT}',//12
              '${rst.REMARK}',//13
              '${rst.ADVC_ORDER_DTL_ID}',//14
              '${rst.NUM}',//15
              '${rst.SPCL_TECH_ID}',//16
              $.trim('${rst.ORDER_RECV_DATE}'),//17
              '${rst.SEND_NUM}',//18
              '${rst.PRDSTATE}',//19
              '${rst.COMM_FLAG}',//20
              '${rst.PRD_TYPE}',//21
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>