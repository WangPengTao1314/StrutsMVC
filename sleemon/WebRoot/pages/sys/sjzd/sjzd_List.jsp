<!--  
/**
  *@module 系统管理
  *@fuc 数据字典编辑
  *@version 1.1
  *@author 张羽
*/
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>数据字典列表页面</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/pages/sys/sjzd/sjzd_List.js"></script>
	
</head>

<body>
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td><span id="pageTitle">当前位置：系统管理&gt;&gt;基础信息&gt;&gt;数据字典信息维护</span></td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20px" valign="top">
	<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
		<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
			   <td nowrap>
				  <c:if test="${qxcom.XT0010102 eq 1}">
				    <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
			   		<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				  </c:if>
				  <c:if test="${qxcom.XT0010103 eq 1}">
				    <input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R">
				  </c:if>
				  <c:if test="${qxcom.XT0010101 eq 1}">
				    <input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				  </c:if>
				  <c:if test="${qxcom.XT0010104 eq 1}">
				    <input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">	
				   	<input id="stop" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">	
				  </c:if>
				   <input id="personal" type="button" class="btn" value="个性化设置" >
			 </td>
			</tr>
		</table>
		</div>
	</td>
</tr>
<tr>
	<td>
		<div class="lst_area">
		<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
			<tr class="fixedRow">
				<th nowrap align="center" width="1%" ></th>
				<th nowrap align="center" dbname="SJZDBH">数据字典编号</th>
				<th nowrap align="center" dbname="SJZDMC">数据字典名称</th>
				<th nowrap align="center" dbname="CRENAME">创建人</th>
				<th nowrap align="center" dbname="CRETIME">创建时间</th>
				<th nowrap align="center" dbname="STATE">状态</th>
			</tr>
			<c:forEach items="${page.result}" var="rst" varStatus="row">
			<c:set var="r" value="${row.count % 2}"></c:set>
			<c:set var="rr" value="${row.count}"></c:set>
			<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
				<td nowrap align='center' >
					<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.SJZDID}">
				</td>
				<td nowrap align="left">${rst.SJZDBH}&nbsp;</td>
				<td nowrap align="left">${rst.SJZDMC}&nbsp;</td>
				<td nowrap align="left">${rst.CRENAME}&nbsp;</td>
				<td nowrap align="left">${rst.CRETIME}&nbsp;</td>
				<td nowrap align="center" id="state${rst.SJZDID}" value="${rst.STATE}">
				<input type="hidden" id="STATE" value="${rst.STATE}">${rst.STATE}&nbsp;
				</td>
			</tr>
			</c:forEach>
			<c:if test="${empty page.result}">
			<tr>
				<td height="25" colspan="13" align="center" class="lst_empty">&nbsp;无相关信息&nbsp;</td>
			</tr>
			</c:if>
		</table>
		</div>
	</td>
</tr>
<tr>
	<td height="8px">
		<table width="100%" border="0" cellpadding="0"cellspacing="0" class="lst_toolbar">
		<tr>
			<td>
				<form id="pageForm" action="#" method="post" name="listForm">
				<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
					<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
					<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
					<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
					<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
					<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
					<span id="hidinpDiv" name="hidinpDiv"></span>
					${paramCover.unCoveredForbidInputs } 
				</form>
			</td>
			<td align="right">${page.footerHtml}${page.toolbarHtml}</td>
		</tr>
		</table>
	</td>
</tr>	
</table>
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td nowrap width="10%" align="right" class="detail_label" >字典编号：</td>
					<td nowrap width="15%" class="detail_content">
					    <input type="hidden" id="SJZDZT" value=" delFlag=0 ">
						<input name="SJZDBH" type="text" seltarget="ZDBH" value="${params.SJZDBH }">
						<img align="absmiddle" name="ZDBH" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('System_12', false, 'SJZDBH', 'SJZDID', 'forms[1]','SJZDBH,SJZDMC', 'SJZDBH,SJZDMC', 'SJZDZT')">
					</td>
					<td width="10%" align="right" class="detail_label">字典名称：</td>
					<td width="15%" class="detail_content">
						<input name="SJZDMC" type="text" seltarget="ZDBH" value="${params.SJZDMC }">
					</td>
					<td width="10%" align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select id="STATECON" name="STATECON" ></select>
					</td>
					<td width="10%" align="right" class="detail_label">数据项名称：</td>
					<td width="15%" class="detail_content">
						<input name="SJXMC" type="text" value="${params.SJXMC }">
					</td>			
				</tr>
				<tr>
					<td width="10%" align="right" class="detail_label">数据项值：</td>
					<td width="15%" class="detail_content">
						<input name="SJXZ" type="text" value="${params.SJXZ }">
					</td>
					<td width="10%" align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"></td>
					<td width="10%" align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"></td>
					<td width="10%" align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"></td>
				</tr>
				<tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<script type=text/javascript>
	SelDictShow("STATECON","32","${params.STATECON}","");
</script>



