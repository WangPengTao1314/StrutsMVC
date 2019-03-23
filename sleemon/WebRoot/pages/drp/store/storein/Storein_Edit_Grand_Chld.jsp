<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Storeingrand_Edit_Chld
 * @author glw
 * @time   2013-08-22 16:42:15 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/storein/Storein_Edit_Grand_Chld.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>入库单库位明细编辑</title>
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
		   	   <input id="add" type="button" class="btn" value="新增(I)" title="Alt+I" accesskey="I" />
		   	   <input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D" />
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
	    <form name="grendChild" id="grendChild">
		<input  type="hidden" id="STOREIN_DTL_ID" name="STOREIN_DTL_ID" value="${STOREIN_DTL_ID}">
		<input type="hidden" name="selectParam" id="selectParam" value="">
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th nowrap align="center">货品编号</th>
              <th nowrap align="center">货品名称</th>
              <th nowrap align="center">规格型号</th>
              <th nowrap align="center">花号</th>
              <th nowrap align="center">品牌</th>
              <th nowrap align="center">标准单位</th>
              <th nowrap align="center">库位编号</th>
              <th nowrap align="center">库位名称</th>
              <th nowrap align="center">实际入库数量</th>
            </tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
</body>
<script type="text/javascript">
	 <c:set var="arrsTemp" value=""/>;
	 <c:set var="noticeNum" value=""/>;
	 <c:forEach items="${child}" var="child" varStatus="row">
		arrsTemp = [
	              '${child.PRD_ID}',
	              '${child.PRD_NO}',
	              '${child.PRD_NAME}',
	              '${child.PRD_SPEC}',
	              '${child.PRD_COLOR}',
	              '${child.BRAND}',
	              '${child.STD_UNIT}',
	              '${child.STOREIN_DTL_ID}',
	              '${child.STOREIN_ID}'
	            ];
         noticeNum = ${child.NOTICE_NUM};
         $("#selectParam").val(" STATE in( '启用') and STORE_ID = '${child.STOREIN_STORE_ID}'");
	</c:forEach>;
	<c:forEach items="${rst}" var="rst" varStatus="row">
	 arrs = [
			  '${rst.STOREIN_STORG_DTL_ID}',
			  '${rst.STORG_ID}',
              '${rst.STORG_NO}',
              '${rst.STORG_NAME}',
              '${rst.REAL_NUM}'
            ];
     var tempArr = arrs.concat(arrsTemp);  
	 addRow(tempArr);
	</c:forEach>
</script>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="STOREIN_ID" name="STOREIN_ID" value=""/>
</form>
</html>