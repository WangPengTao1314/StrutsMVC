<%--
/**
 * @module 质检管理
 * @fuc 成品不良通知单
 * @version 1.1
 * @author zhuxw
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>成品不良品通知单维护</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
    <script type="text/javascript" src="${ctx}/pages/sample/newmasterslave/NewCpbltzd_Cpbltzdmx_Mx_List.js"></script>
</head>
<body class="bodycss1">
<table width="99.8%" height="100%" border="0" cellSpacing=0 cellPadding=0>
<tr id="btntr">
	<td height="20px" valign="top">
		<div class="tablayer">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
				   <td nowrap>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
			   	   </c:if>
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td>
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
					<th nowrap align="center">成品质检项目编号</th>
					<th nowrap align="center">成品质检项目名称</th>
					<th nowrap align="center">质检项目类别</th>
					<th nowrap align="center">应用标准</th>
					<th nowrap align="center">检验参数</th>
					<th nowrap align="center">合格标准</th>
					<th nowrap align="center">检验值</th>
					<th nowrap align="center">让步接收数量</th>
					<th nowrap align="center">报废数量</th>
					<th nowrap align="center">退回数量</th>
					<th nowrap align="center">修色数量</th>
					<th nowrap align="center">备注</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
						<td nowrap align='center' >
							<input type="checkbox" style="height:12px;valign:middle" id="eid${rr}" value="${rst.CPBLTZDMXID}" onclick="setEidChecked(this);">
						</td>
						<td nowrap align="left">${rst.CPZJXMBH }&nbsp;</td>
						<td nowrap align="left">${rst.CPZJXMMC }&nbsp;</td>
						<td nowrap align="left">${rst.ZJXMLB }&nbsp;</td>
						<td nowrap align="left">${rst.YYBZ }&nbsp;</td>
						<td nowrap align="left">${rst.JYCS }&nbsp;</td>
						<td nowrap align="left">${rst.HGBZ }&nbsp;</td>
						<td nowrap align="left">${rst.JYZ }&nbsp;</td>
						<td nowrap align="right">${rst.RBJSSL }&nbsp;</td>
						<td nowrap align="right">${rst.BFSL }&nbsp;</td>
						<td nowrap align="right">${rst.THSL }&nbsp;</td>
						<td nowrap align="right">${rst.XSSL }&nbsp;</td>
						<td nowrap align="left">${rst.REMARK }&nbsp;</td>
					</tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="18" align="center" class="lst_empty">&nbsp;无相关信息&nbsp;</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="CPBLTZDMXIDS" name="CPBLTZDMXIDS" value=""/>
</form>
</body>

