<!-- 
 *@module 渠道管理-装修管理
 *@func   装修整改验收单维护
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
	<title>装修整改验收单维护</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
    <script type="text/javascript" src="${ctx}/pages/drp/oamg/rnvtnreformcheck/Rnvtnreformcheck_MX_List.js"></script>
</head>
<BODY>
<table width="99.9%" border="0" cellSpacing=0 cellPadding=0>
<tr>
	<td>
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center" width="25%">验收项目类型</th>
					<th nowrap align="center" width="25%">验收项目名称</th>
					<th nowrap align="center" width="17%">项目分值</th>	
					<th nowrap align="center" width="17%">验收得分</th>
					<th nowrap align="center" width="17%">验收意见</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
						<td nowrap align="center">${rst.PRJ_TYPE}&nbsp;</td>
						<td nowrap align="center">${rst.PRJ_NAME}&nbsp;</td>
						<td nowrap align="center">${rst.PRJ_SCORE}&nbsp;</td>
						<td nowrap align="center">${rst.CHECK_SCORE}&nbsp;</td>
						<td nowrap align="center">${rst.CHECK_REMARK}&nbsp;</td>
					</tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">&nbsp;无相关信息&nbsp;</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="RNVTN_REFORM_DTL_IDs" name="RNVTN_REFORM_DTL_IDs" value=""/>
</form>
</body>
