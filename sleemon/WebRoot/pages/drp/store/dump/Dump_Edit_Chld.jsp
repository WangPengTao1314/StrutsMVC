<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:转储单明细
 * @author zzb
 * @time   2013-09-05 14:00:34 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/dump/Dump_Edit_Chld.js"></script>
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
		<input type="hidden" id="DUMP_ID" name="DUMP_ID">
		<input type="hidden" id="ZTXXID" name="ZTXXID" value="${ZTXXID}">
		<input type="hidden" id="selParam" name="selParam" value=" STATE='启用' and DEL_FLAG=0 ">
		<input type="hidden" name="selectOutStoreParams" id="selectOutStoreParams" value=""/>
		<input type="hidden" name="selectInStoreParams" id="selectInStoreParams" value=""/>
		
		
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">花号</th>
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">特殊工艺</th>
<!--              <th nowrap align="center" name="OUT_STORG_NO">转出库位编号</th>-->
<!--              <th nowrap align="center" name="OUT_STORG_NAME">转出库位名称</th>-->
<!--              <th nowrap align="center" name="IN_STORG_NO">转入库位编号</th>-->
<!--              <th nowrap align="center" name="IN_STORG_NAME">转入库位名称</th>-->
              <th nowrap align="center">转储数量</th>
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
	           '${rst.DUMP_DTL_ID}',
              '${rst.PRD_ID}',
              '${rst.PRD_NO}',
              '${rst.PRD_NAME}',
              '${rst.PRD_SPEC}',
              '${rst.PRD_COLOR}',
              '${rst.BRAND}',
              '${rst.STD_UNIT}',
              '${rst.DUMP_OUT_STORG_ID}',
              '${rst.DUMP_OUT_STORG_NO}',
              '${rst.DUMP_OUT_STORG_NAME}',
              '${rst.DUMP_IN_STORG_ID}',
              '${rst.DUMP_IN_STORG_NO}',
              '${rst.DUMP_IN_STORG_NAME}',
              '${rst.DUMP_NUM}',
              '${rst.REMARK}',
              '${rst.SPCL_TECH_ID}',
              '${rst.SPCL_TECH_FLAG}',
              '${rst.SAFE_NUM}',// 18 的库存量（实时库存-冻结库存）
              '${rst.RELT_BILL_DTL_ID}'
    
            ];
	addRow(arrs);
	</c:forEach>
</script>
</html>