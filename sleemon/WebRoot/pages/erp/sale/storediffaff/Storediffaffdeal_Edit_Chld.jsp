<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Storediffdeal_Edit_Chld
 * @author wzg
 * @time   2013-08-30 14:17:25 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/storediffaff/Storediffaffdeal_Edit_Chld.js"></script>
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
		<input type="hidden" id="STOREIN_DIFF_ID" name="STOREIN_DIFF_ID">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">花号</th>
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">单价</th>
              <th nowrap align="center">折扣率</th>
              <th nowrap align="center">折后单价</th>
              <th nowrap align="center">差异数量</th>
              <th nowrap align="center">差异原因</th>
              <th nowrap align="center">处理方式</th>
              <th nowrap align="center">差异金额</th>
              <th nowrap align="center">差异附件</th>
              <th nowrap align="center">备注</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
<input id="PRD_ID" name="PRD_ID"  type="hidden" value="${prd.PRD_ID}"/>
<input id="PRD_NO" name="PRD_NO" type="hidden" value="${prd.PRD_NO}"/>
<input id="PRD_NAME" name="PRD_NAME" type="hidden" value="${prd.PRD_NAME}"/>
<input id="PRD_SPEC" name="PRD_SPEC" type="hidden" value="${prd.PRD_SPEC}"/>
<input id="PRD_COLOR" name="PRD_COLOR" type="hidden" value="${prd.PRD_COLOR}"/>
<input id="BRAND" name="BRAND" type="hidden" value="${prd.BRAND}"/>
<input id="STD_UNIT" name="STD_UNIT" type="hidden" value="${prd.STD_UNIT}"/>
<input id="PRICE" name="PRICE" type="hidden" value="${prd.PRICE}"/>
<input id="DECT_RATE" name="DECT_RATE" type="hidden" value="${prd.DECT_RATE}"/>
<input id="DECT_PRICE" name="DECT_PRICE" type="hidden" value="${prd.DECT_PRICE}"/>
</body>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	           '${rst.DIFF_DEAL_DTL_ID}',
	           '${rst.PRD_ID}',
	           '${rst.PRD_NO}',
	           '${rst.PRD_NAME}',
	           '${rst.PRD_SPEC}',
	           '${rst.PRD_COLOR}',
	           '${rst.BRAND}',
	           '${rst.STD_UNIT}',
	           '${rst.PRICE}',
	           '${rst.DECT_RATE}',
	           '${rst.DECT_PRICE}',
               '${rst.DIFF_NUM}',
               '${rst.DIFF_RSON}',
               '${rst.DEAL_WAY}',
               '${rst.DIFF_AMOUNT}',
               '${rst.DIFF_ATT}',
               '${rst.REMARK}',
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>