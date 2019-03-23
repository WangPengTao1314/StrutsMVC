<!-- 
 *@module渠道管理-拜访管理
 *@func  月度拜访计划维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
    <script type="text/javascript" src="${ctx}/pages/erp/oamg/monthVisit/Monthvisit_Mx_Edit.js"></script>
	<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>月度拜访计划维护</title>
</head>
<body onload="load()">
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
	 <tr id="btntr">
		<td height="20px" valign="top">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0>
				<tr>
				   <td nowrap>
				   	   <input id="add" type="button" class="btn" value="新增(I)" title="Alt+I" accesskey="I" >
					   <input class="btn"  type="button"  value="保存(S)"  title="Alt+S"  accesskey="S"  id="save" />
					   <input class="btn"  type="button"  value="返回(B)"  title="Alt+B"  accesskey="B"  onclick="gobacknew();" />
		           </td>
	          </tr>
</table>
</td>
</tr>
<!--浮动按钮层结束-->
<!--占位用table，以免显示数据被浮动层挡住
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<tr>
<td>
	<div class="lst_area">
	    <form id="mainForm" name="mainForm">
	    <input type="hidden" name="selectParamsChann"	value="STATE in( '启用','停用') and DEL_FLAG=0 "	>
	    <input type="hidden" name="selectParams"        value="CHANN_ID in ${CHANN_ID}" />
	    <input type="hidden" id="zoneConditionCity" name="zoneConditionCity" value="" />
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
				<th nowrap align="center" width="23%">拜访对象类型</th>
				<th nowrap align="center" width="23%">拜访形式</th>
				<th nowrap align="center" width="23%">拜访次数</th>
			</tr>
		</table>
		</form>
	</div>
	</td>
</tr>
</table>
<input id="delFlag" type="hidden" value="false" />
<input id="Tflag" type="hidden" name="Tflag" value="${flag}" />
<input id="MONTH_VISIT_PLAN_ID" name="MONTH_VISIT_PLAN_ID" json="MONTH_VISIT_PLAN_ID" type="hidden" value="${MONTH_VISIT_PLAN_ID}"/> 
</body>
 <script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = [
	            '${rst.MONTH_VISIT_PLAN_DTL_ID}',
	            '${rst.VISIT_OBJ_TYPE}',
				'${rst.PLAN_VISIT_NUM}',
				'${rst.VISIT_TYPE}'
				];
	addRow(arrs);
	</c:forEach>
</script>
