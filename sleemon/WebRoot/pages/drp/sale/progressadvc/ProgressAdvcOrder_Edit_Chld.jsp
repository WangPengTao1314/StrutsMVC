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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/progressadvc/ProgressAdvcOrder_Edit_Chld.js"></script>
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
		   		<c:if test="${updateFlag ne 1}">
			   <input id="add" type="button" class="btn" value="新增(I)" title="Alt+I" accesskey="I" >
			   <input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G" >
			   </c:if>
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
			<input type="hidden" name=selectParams id="selectParams" value="STATE in ('启用','停用') and DEL_FLAG=0 and FINAL_NODE_FLAG=1 and (COMM_FLAG=1 or (COMM_FLAG=0 and LEDGER_ID='${LEDGER_ID}'))   and IS_NO_STAND_FLAG=0">
			<input type="hidden" name="selectPro" value="  ZTXXID='${LEDGER_ID}' and (STATE='启用' or STORENUM>0) "/>
			<input type="hidden" name=selectParam  value="	STATE='启用' and DEL_FLAG=0 and (BEL_CHANN_ID in ('${LEDGER_ID}','${BMXXID}') ">
			<input type="hidden" id="TERM_DECT_FROM" value="${TERM_DECT_FROM}"/>
			<input type="hidden" id="CHANN_ID" value="${CHANN_ID}" />
			<input type="hidden" id="LEDGER_ID" value="${LEDGER_ID}" />
			<input type="hidden" id="BMXXID" value="${BMXXID}" />
			<input type="hidden" id="MAX_FREEZE_DATE" value="${MAX_FREZZ_DAYS}" />
			<input type="hidden" id="updateFlag" value="${updateFlag}" />
			
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">货品类型</th>
              <th nowrap align="center">规格型号</th>
<!--              <th nowrap align="center">花号</th>-->
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">折扣率</th>
              <th nowrap align="center">折后单价</th>
              <th nowrap align="center">订货数量</th>
              <th nowrap align="center">交货日期</th>
<!--              <th nowrap align="center">是否冻结</th>-->
<!--              <th nowrap align="center">冻结数量</th>-->
<!--              <th nowrap align="center">冻结库房</th>-->
              <th nowrap align="center">应收金额</th>
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
	          '${rst.ADVC_ORDER_DTL_ID}',//1
              '${rst.PRD_NO}',//2
              '${rst.PRD_ID}',//3
              '${rst.PRD_NAME}',//4
              '${rst.PRD_SPEC}',//5
              '${rst.PRD_COLOR}',//6
              '${rst.BRAND}',//7
              '${rst.STD_UNIT}',//8
              '${rst.PRICE}',//9
              '${rst.DECT_RATE}',//10
              '${rst.DECT_PRICE}',//11
              '${rst.ORDER_NUM}',//12
              '${rst.PAYABLE_AMOUNT}',//13
              '${rst.REMARK}',//14
              '${rst.SESSION_ID}',//15
              '${rst.SPCL_TECH_ID}',//16
              '${rst.ADVC_ORDER_ID}',//17
              '${rst.PRD_TYPE}',//18
              '${rst.IS_FREEZE_FLAG}',//19
              '${rst.FREEZE_STORE_ID}',//20
              '${rst.FREEZE_STORE_NO}',//21
              '${rst.FREEZE_STORE_NAME}',//22
              '${rst.FREEZE_NUM}',//23
              '${rst.ORDER_RECV_DATE}',//24
              '${rst.SPCL_TECH_FLAG}',//25
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>