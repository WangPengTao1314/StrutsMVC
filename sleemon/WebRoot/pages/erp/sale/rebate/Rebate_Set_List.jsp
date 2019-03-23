<!--
 * @prjName:喜临门营销平台
 * @fileName:REBATESET
 * @author chenj
 * @time   2013-09-15 14:59:50 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/rebate/Rebate_Set_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<form>
 <input type="hidden" id="CHANN_NOS" name="CHANN_NOS"  json="CHANN_NOS" value="${CHANN_NOS}"/>
  <input type="hidden" id="DESCON" name="DESCON"  json="DESCON" value="${DESCON}"/>
 
		<div align="center">
			<h3>
				调整返利额
			</h3>
		</div>
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td align="center">调整的返利金额:</td>
		<td align="center"><input id="SET_REBATE" json="SET_REBATE" name="SET_REBATE" type="text" 
										label="调整的返利金额" inputtype="float"   valueType="8,2"  mustinput="true"  
										maxlength="10" autocheck="true" value=""></td>
</tr>
<tr>
	<td align="center" colspan="2">
					<input id="q_add" type="button" class="btn" value="确定(A)"
										title="Alt+A" accesskey="A">
				 	<input id="q_close" type="button" class="btn" value="关闭(X)"
										title="Alt+X" accesskey="X">
				</td>
			</tr>
</table>
</form>
</body>
</html>