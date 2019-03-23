<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorderhd_Edit_Chld
 * @author zzb
 * @time   2013-09-15 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/goodsorderhd/Goodsorderhd_Edit_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="/sleemon/scripts/core/keyboard_ctrl.js"></script>
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
		<input type="hidden" id="GOODS_ORDER_ID" name="GOODS_ORDER_ID">
		<input type="hidden" id="PRDNOS" name="PRDNOS" value="${PRDNOS}">
	    <input type="hidden" id="selectParams" name="selectParams" value="STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1 ">
	    <input type="hidden" id="DECT_RATE" value="${DECT_RATE}">
		<input type="hidden" id="rate" name="rate">
		<input type="hidden" id="CHANN_ID" value="${CHANN_ID}"/>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">行号</th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
<!--              <th nowrap align="center">花号</th>-->
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center" id="delId" name="hideTdByBillType">特殊工艺</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">折扣率</th>
              <th nowrap align="center">折后单价</th>
              <th nowrap align="center">订货数量</th>
              <th nowrap align="center">要求到货日期</th>
              <th nowrap align="center">货品体积</th>
              <th nowrap align="center">总体积</th>
              <th nowrap align="center">总金额</th>
<!--              <th nowrap align="center">原价格</th>-->
             <th nowrap align="center">备注</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
<input id="flag" value="1" type="hidden"> 
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	           '${rst.GOODS_ORDER_DTL_ID}',
              '${rst.PRD_ID}',
              '${rst.PRD_NO}',
              '${rst.PRD_NAME}',
              '${rst.PRD_SPEC}',
              '${rst.PRD_COLOR}',
              '${rst.BRAND}',
              '${rst.STD_UNIT}',
              '${rst.SPCL_TECH_FLAG}',//8特殊工艺标记
              '${rst.PRICE}',
              '${rst.OLD_PRICE}',
              '${rst.DECT_RATE}',
              '${rst.DECT_PRICE}',
              '${rst.ORDER_NUM}',
              '${rst.ORDER_AMOUNT}',
              '${rst.VOLUME}',
              '${rst.TOTAL_VOLUME}',
              '${rst.SPCL_TECH_ID}',
              '${rst.CREDIT_FREEZE_PRICE}',// 18 信用冻结单价
              '${rst.IS_NO_STAND_FLAG}',  //是否非标
              '${rst.ORDER_RECV_DATE}',
              '${rst.TECH_MULT}',// 21 特殊工艺加价倍数
              '${rst.TECH_AMOUNT}', //特殊工艺加价 金额
              '${rst.REMARK}',
              '${rst.PRD_SUIT_FLAG}',//套件标记
              '${rst.ROW_NO}'//行号
              
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>