<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_Edit_Chld
 * @author glw
 * @time   2013-08-17 17:07:03 
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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/tstoreIn/StroreIn_Edit_Chld.js"></script>
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
	    <form name="childForm" id="childForm">
	    <input type="hidden" name="selectParam" value=" STATE = '启用' and BEL_CHANN_ID='${channId}'">
	    <input type="hidden" name="selectPrdParams" id="selectPrdParams" value=" STATE in ('启用','停用')  and FINAL_NODE_FLAG=1 and (COMM_FLAG=1 or (COMM_FLAG=0 and LEDGER_ID='${LEDGER_ID}'))">
	    <input type="hidden" name="selectTechParams" id="selectTechParams"  value=" DEL_FLAG=0 ">
		<input type="hidden" id="STOREIN_NOTICE_ID" name="STOREIN_NOTICE_ID">
		<input type="hidden" id="LEDGER_ID" name="LEDGER_ID" value="${LEDGER_ID}"/>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">入库库房编号</th>
              <th nowrap align="center">入库库房名称</th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th><%--
              <th nowrap align="center">花号</th>
              --%><th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">折扣率</th>
              <th nowrap align="center">折后单价</th>
              <th nowrap align="center">通知入库数量</th>
              <th nowrap align="center">折后金额</th>
              <th nowrap align="center">备注</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
<input id="delFlag" type="hidden" value="false"/>
<input type="hidden" name="IS_UPDATE_STOREIN" id="IS_UPDATE_STOREIN" json="IS_UPDATE_STOREIN" value="${IS_UPDATE_STOREIN}"/>
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	          '${rst.STOREIN_NOTICE_DTL_ID}',//0
	          '${rst.STOREIN_STORE_ID}',//1
              '${rst.STOREIN_STORE_NO}',//2
              '${rst.STOREIN_STORE_NAME}',//3
              '${rst.PRD_ID}',//4
              '${rst.PRD_NO}',
              '${rst.PRD_NAME}',
              '${rst.PRD_SPEC}',
              '${rst.PRD_COLOR}',
              '${rst.BRAND}',
              '${rst.STD_UNIT}',
              '${rst.PRICE}',
              '${rst.DECT_RATE}',//12
              '${rst.DECT_PRICE}',
              '${rst.NOTICE_NUM}',
              '${rst.DECT_AMOUNT}',
              '${rst.REMARK}',
              '${rst.SPCL_TECH_FLAG}',// 17 特殊工艺标记
              '${rst.SPCL_TECH_ID}'//18 特殊工艺ID
              
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>