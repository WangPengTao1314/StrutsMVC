<!-- 
 *@module 基础资料
 *@func 维护编码规则
 *@version 1.1
 *@author 孙炜
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
    <script type="text/javascript" src="${ctx}/pages/sys/bmgzwh/bmgz_Mx_Edit.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<title>编码规则维护</title>
</head>
<body>
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
	<tr id="btntr">
		<td height="20px" valign="top">
 <!--<div class="buttonlayer" id="floatDiv"> 
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0 width="100%"> -->
      <table id="qxBtnTb"  cellSpacing=0 cellPadding=0 border=0>
		<tr>
		   <td nowrap>
		     <div style="position: absolute;top: 1px;left: 2px">
			   <input id="add" type="button" class="btn" value="新增(I)" title="Alt+I" accesskey="I" >
			   <input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G" >
			   <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S" >
			   <input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="goback();">
			</div>
		</td>
		</tr>
	</table>
<!--</div>  -->
</td>
	</tr>
<!--浮动按钮层结束-->
<!--占位用table，以免显示数据被浮动层挡住
<table width="100%" height="25px">
    <tr>
        <td>&nbsp;</td>
    </tr>
</table>-->
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<!--<table width="100%" border="0" cellSpacing="0" cellPadding="0">-->
<tr>
<td>
	<div class="lst_area">
	    <form>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
				<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
				<th style="display:none"></th>
				<th nowrap align="center">段类型</th>
				<th nowrap align="center">段长度</th>
				<th nowrap align="center">段头</th>
				<th nowrap align="center">年样式</th>
				<th nowrap align="center">步长</th>		
				<th nowrap align="center">初始植</th>
			</tr>
		</table>
		</form>
		<!--<p id="clickAddRow" style="width:100%;height:20px; vertical-align:top;background-color:#efe">&nbsp;</p>  -->
	</div>
	</td>
</tr>
</table>
</body>
<script type="text/javascript">
	<c:forEach items="${rst}" var="rst" varStatus="row">
	var arrs = ['${rst.BMGZMXID}',
	            '${rst.DLX}',
				'${rst.DCD}',
				'${rst.DT}',
				'${rst.NYS}',
				'${rst.BC}',
				'${rst.CSZ}',
				'${rst.SXH}'];
	addRow(arrs);
	</c:forEach>
</script>
 
