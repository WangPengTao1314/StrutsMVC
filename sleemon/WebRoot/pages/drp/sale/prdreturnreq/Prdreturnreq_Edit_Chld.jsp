<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:prdreturnreq_Edit_Chld
 * @author wzg
 * @time   2013-08-15 10:17:13 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/prdreturnreq/Prdreturnreq_Edit_Chld.js"></script>
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
		<input type="hidden" id="PRD_RETURN_REQ_ID" name="PRD_RETURN_REQ_ID">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<input type="hidden" name=selectParams id="selectParams" value="STATE='启用' and DEL_FLAG=0 and FINAL_NODE_FLAG=1 and COMM_FLAG=1 ">
			<input type="hidden" name="selectPrd" id="selectPrd" value=""/>
			<input type="hidden" name="selShip" value=" STATE ='启用' and DEL_FLAG=0 "/>
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <%--
              <th nowrap align="center">来源单据编号</th>
              --%>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
<!--              <th nowrap align="center">花号</th>-->
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">退货单价</th>
              <th nowrap align="center">退货数量</th>
              <th nowrap align="center">退货金额</th>
              <th nowrap align="center">原因归类</th>
              <th nowrap align="center">退货原因</th>
              <th nowrap align="center">使用时间</th>
              <th nowrap align="center">生产基地</th>
              <th nowrap align="center">生产日期</th>
              
              
              <th nowrap align="center">退货附件</th>
            </tr>
		</table>
		<input id="LEDGER_ID" name="LEDGER_ID" type="hidden" value="${LEDGER_ID}"/>
		<input id="selectCondition" name="selectCondition" type="hidden" value=""/>
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
	           '${rst.PRD_RETURN_DTL_REQ_ID}',//0
	           '${rst.FROM_BILL_ID}',//1
              '${rst.FROM_BILL_NO}',//2
              '${rst.PRD_ID}',//3
              '${rst.PRD_NO}',//4
              '${rst.PRD_NAME}',//5
              '${rst.PRD_SPEC}',//6
              '${rst.PRD_COLOR}',//7
              '${rst.BRAND}',//8
              '${rst.STD_UNIT}',//9
              '${rst.RETURN_PRICE}',//10
              '${rst.RETURN_NUM}',//11
              '${rst.RETURN_AMOUNT}',//12
              '${rst.RETURN_RSON_TYPE}',//13
              '${rst.RETURN_RSON}',//14
              '${rst.RETURN_ATT}',//15
              '${rst.SPCL_TECH_ID}',//16
              '${rst.SPCL_TECH_FLAG}',//17
              '${rst.MAX_RETURN_NUM}',// 18 最大退货数量
              '${rst.USE_TIME}',//19
              '${rst.SHIP_POINT_ID}',//20
              '${rst.SHIP_POINT_NO}',//21
              '${rst.SHIP_POINT_NAME}',//22
              '${rst.PRDC_DATE}',//23
              '',//24CHECKONLY
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>