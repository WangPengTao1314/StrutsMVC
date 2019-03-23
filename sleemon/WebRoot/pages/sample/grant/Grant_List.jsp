﻿<!--
 * @prjName:供应链_贵人鸟
 * @fileName:Grant_List
 * @author zhuxw
 * @time   2013-05-15 10:35:31 
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
	<script type="text/javascript" src="${ctx}/pages/sample/grant/Grant_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>库存管理-质检管理-不良通知单</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C">
					<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
				   </c:if>
                   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
				    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
				   </c:if>
				   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
				    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
				   </c:if>
				   <input id="personal" type="button" class="btn" value="个性化设置" >
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
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="CPBLTZDBH" >成品不良通知单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="LCH" >流程号</th>
                    <th  nowrap="nowrap" align="center" dbname="WLDWBH" >往来单位编号</th>
                    <th  nowrap="nowrap" align="center" dbname="WLDWMC" >往来单位名称</th>
                    <th  nowrap="nowrap" align="center" dbname="THSL" >退回数量</th>
                    <th  nowrap="nowrap" align="center" dbname="CRENAME" ></th>
                    <th  nowrap="nowrap" align="center" dbname="CRETIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="BMMC" >部门名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.CPBLTZDID}"/>
					 </td>
                     <td nowrap="nowrap" align="left">${rst.CPBLTZDBH}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.LCH}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.WLDWBH}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.WLDWMC}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.THSL}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRENAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRETIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.BMMC}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.CPBLTZDID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="10" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td height="12px" align="center">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" class="lst_toolbar">
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
				<td align="right">
					 ${page.footerHtml}${page.toolbarHtml}
				</td>
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
                    <td width="8%" nowrap align="right" class="detail_label">成品不良通知单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="CPBLTZDBH" name="CPBLTZDBH"  style="width:155" value="${params.CPBLTZDBH}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">流程号:</td>
					<td width="15%" class="detail_content">
	   					<input id="LCH" name="LCH"  style="width:155" value="${params.LCH}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">退回数量从:</td>
					<td width="15%" class="detail_content">
	   					<input id="THSL_BEG" name="THSL_BEG"  style="width:155" value="${params.THSL_BEG}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">退回数量到:</td>
					<td width="15%" class="detail_content">
	   					<input id="THSL_END" name="THSL_END"  style="width:155" value="${params.THSL_END}"/>
					</td>
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRENAME" name="CRENAME"  style="width:155" value="${params.CRENAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRETIME_BEG" name="CRETIME_BEG" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRETIME_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRETIME_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRETIME_END" name="CRETIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.CRETIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRETIME_END);">
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">部门名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="BMMC" name="BMMC"  style="width:155" value="${params.BMMC}"/>
					</td>					
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">机构名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="JGMC" name="JGMC"  style="width:155" value="${params.JGMC}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<input id="STATE" name="STATE"  style="width:155" value="${params.STATE}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
                    <td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="查询(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
</html>
<script type="text/javascript">
//初始化 审批流程
       spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>