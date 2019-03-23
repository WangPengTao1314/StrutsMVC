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
	<script type="text/javascript" src="${ctx}/pages/drp/areaser/storeoutnotice/Storeoutnotice_Edit_Chld.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>预订单明细编辑</title>
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
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<input type="hidden" name=selectParams id="selectParams" value="">
			<input type="hidden" name=selectParam  value="	STATE='启用' and DEL_FLAG=0 and BEL_CHANN_ID='${LEDGER_ID}' ">
			<input type="hidden" id="TERM_DECT_FROM" value="${TERM_DECT_FROM}"/>
			<input type="hidden" id="CHANN_ID" value="${CHANN_ID}" />
			<input type="hidden" id="MAX_FREEZE_DATE" value="<%=Consts.MAX_FREEZE_DATE%>" />
			
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
<!--              <th nowrap align="center">颜色</th>-->
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">通知出库数量</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">折扣率</th>
              <th nowrap align="center">折后单价</th>
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
	          '${rst.STOREOUT_NOTICE_DTL_ID}',//1销售出库通知单ID
              '${rst.PRD_NO}',//2货品编号
              '${rst.PRD_ID}',//3货品ID
              '${rst.PRD_NAME}',//4货品名称
              '${rst.PRD_SPEC}',//5规格型号
              '${rst.PRD_COLOR}',//6颜色
              '${rst.BRAND}',//7品牌
              '${rst.STD_UNIT}',//8标准单位
              '${rst.PRICE}',//9单价
              '${rst.DECT_RATE}',//10折扣率
              '${rst.DECT_PRICE}',//11折扣价
              '${rst.NOTICE_NUM}',//12通知出库数量
              '${rst.DECT_AMOUNT}',//13折后金额
              '${rst.REMARK}',//14备注
              '${rst.REAL_NUM}',//15实际出库数量
              '${rst.SPCL_TECH_ID}',//16订单特殊工艺ID
              '${rst.FROM_BILL_DTL_ID}',//17来源单据明细ID
              '${rst.STOREOUT_NOTICE_ID}',//18销售出库通知单ID
              '${rst.SPCL_TECH_FLAG}',//19特殊工艺标记
              '${rst.STORE_NUM}'//库存量
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>