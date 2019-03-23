﻿<!--
 * @prjName:喜临门营销平台
 * @fileName:Store_List
 * @author wdb
 * @time   2013-08-13 14:01:22 
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
	<script type="text/javascript" src="${ctx}/pages/drp/base/store/Store_List_Chld.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_START_STOP eq 1 }">
			   	    <input id="start" type="button" class="btn" value="启用(S)" title="Alt+S" accesskey="S">
					<input id="stop" type="button" class="btn" value="停用(W)" title="Alt+W" accesskey="W">
			   	   </c:if>
				   &nbsp;
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="STORG_NO" >库位编号</th>
                    <th  nowrap="nowrap" align="center" dbname="STORG_NAME" >库位名称</th>
                    <th  nowrap="nowrap" align="center" dbname="FLOOR" >楼层</th>
                    <th  nowrap="nowrap" align="center" dbname="LAY_ADDR" >存放地点</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));btnStateChange('${rst.STATE}');">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.STORG_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="left" >${rst.STORG_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.STORG_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.FLOOR}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.LAY_ADDR}&nbsp;</td>
                     <td nowrap="nowrap" align="center" json='STATE' id="state${rst.STORG_ID}">${rst.STATE}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="11" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
		<input type="hidden" value="${rst[0].STORG_ID}" id="STORG_ID" name="STORG_ID">
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="STORG_IDS" name="STORG_IDS" value=""/>
</form>
</body>
</html>