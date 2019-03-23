<!-- 
 *@module 系统管理
 *@func 个人工作计划
 *@version 1.1
 *@author 吴军
 *  -->
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
	<script type="text/javascript" src="${ctx}/pages/base/grgzjh/Grgzjh_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
    <script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>
				<c:if test="${module eq 'wh'}">
				 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>工作计划管理>>个人工作计划维护</td>
				</c:if>
				<c:if test="${module eq 'sh'}">
				 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>工作计划管理>>个人工作计划审核</td>
				</c:if>	
			</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
					<input id="new" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					<input id="add" type="button" class="btn" value="编辑(N)" title="Alt+N" accesskey="N">
					<%--
					<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
					--%>
				   </c:if>
				   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C">
					<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
				   </c:if>
				   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
				    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A" onclick="audit()">
				   </c:if>
				   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
				    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
<!--				   <input id="personal" type="button" class="btn" value="个性化设置" >-->
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
					<th  nowrap="nowrap" align="center" dbname="PER_WORK_PLAN_NO" >计划编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PLAN_YEAR" >年份</th>
                    <th  nowrap="nowrap" align="center" dbname="PLAN_MONTH" >月份</th>
                    <th  nowrap="nowrap" align="center" dbname="PLAN_PSON_NAME" >制定人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制定时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >部门名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.PER_WORK_PLAN_ID}"/>
					 </td>
					 <td nowrap="nowrap" align="center">${rst.PER_WORK_PLAN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PLAN_YEAR}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PLAN_MONTH}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PLAN_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     <input id="state${rst.PER_WORK_PLAN_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="8" align="center" class="lst_empty">
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
<form id="queryForm" name="queryForm" method="post" action="#">
    <input type="hidden" value=" 1=1" name="selcondition" id="selcondition"/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
                <tr>
                  <td width="8%" nowrap align="right" class="detail_label">个人工作计划编号:</td>
				  <td width="15%" class="detail_content">
                       <input  id="PER_WORK_PLAN_NO" name="PER_WORK_PLAN_NO" json="PER_WORK_PLAN_NO" value="${params.PER_WORK_PLAN_NO}">
				  </td>					
                  <td width="8%" nowrap align="right" class="detail_label">部门名称:</td>
				  <td width="15%" class="detail_content">
	   				   <input  id="DEPT_NAME" name="DEPT_NAME" json="DEPT_NAME" value="${params.DEPT_NAME}">
				  </td>
                  <td width="8%" nowrap align="right" class="detail_label">年份:</td>
                  <td width="15%" class="detail_content">
		                <select  name="PLAN_YEAR" id="PLAN_YEAR" json="PLAN_YEAR" label="年份" style="width:155px;"></select>
                  </td>
                  <td width="8%" nowrap align="right" class="detail_label">状态:</td>
                  <td width="15%" class="detail_content">
		                <select  name="STATE" id="STATE" style="width:155px;"></select>
                  </td>
               </tr>
                <tr>
                  <td width="8%" nowrap align="right" class="detail_label">工作计划起:</td>
				  <td width="15%" class="detail_content">
                       <input reset="true" id="XFSJ_BEG" name="XFSJ_BEG" readonly="readonly"   onclick="SelectDate();" style="width:155" value="${params.XFSJ_BEG }">
                       <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(XFSJ_BEG);" >
				  </td>					
                  <td width="8%" nowrap align="right" class="detail_label">工作计划止:</td>
				  <td width="15%" class="detail_content">
	   					<input reset="true" id="XFSJ_END" name="XFSJ_END" readonly="readonly"   onclick="SelectDate();" style="width:155" value="${params.XFSJ_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(XFSJ_END);" >
				   </td>
                   <td width="8%" nowrap align="right" class="detail_label">计划制定人:</td>
                   <td width="15%" class="detail_content">
                       <input reset="true" id="RYXXID" name="RYXXID" type="hidden" style="width:155" value="${params.RYXXID}"/>
                       <input reset="true" json="XM" name="XM" type="text"   value="${params.XM}"/>
                       <img align="absmiddle" name="simpleSelComm" id="simpleSelComm" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"/>
                   </td>
                   <td width="8%" nowrap align="right" class="detail_label"> </td>
                   <td width="15%" class="detail_content"></td>
               </tr>
               
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
                        <input  type="button" id="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
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
<script type="text/javascript">
    SelDictShow("STATE","33","${params.STATE}","");
	spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	 
	SelDictShow("PLAN_YEAR","89","${params.PLAN_YEAR}","");  
</script>
</html>
