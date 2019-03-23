<%--
/**
 * @author zhuxw
 * @function 
 * @version 2011年11月16日 11时23分15秒
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>信息列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/sys/notice/Notice_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td>当前位置：系统管理&gt;&gt;基础资料&gt;&gt;
			<c:if test="${module eq 'sh'}">
				公告信息审核
			</c:if>
			<c:if test="${module eq 'wh'}">
				公告信息维护
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
			<table cellSpacing=0 cellPadding=0 border=0 width="100%" id="qxBtnTb">
				<tr>
				   <td nowrap>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C">
					<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
				   </c:if>
                   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
				    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
				   </c:if>
				   <c:if test="${pvg.PVG_CLOSE eq 1 }">
				    <input id="close" type="button" class="btn" value="关闭(L)" title="Alt+L" accesskey="L">
				   </c:if>
				   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
				    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
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
					<th  nowrap="nowrap" align="center" dbname="NOTICE_TITLE">公告标题</th>
					<th nowrap="nowrap" align="center"  dbname="NOTICE_TYPE">公告类型</th>
					<th nowrap="nowrap" align="center"  dbname="NOTICE_OBJ">公告对象</th>
					<th nowrap="nowrap" align="center"  dbname="CRETIME">制单时间</th>
					<th nowrap="nowrap" align="center"  dbname="STATIME ">开始时间</th>
					<th nowrap="nowrap" align="center"  dbname="ENDTIME">结束时间</th>
					<th nowrap="nowrap" align="center"  dbname="STATE">状态</th>
					
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));setSelOperateEx();">
						<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.NOTICE_ID}"/>
						</td>
						<td nowrap="nowrap" align="left">${rst.NOTICE_TITLE}&nbsp;</td>
						<td nowrap="nowrap" align="left">${rst.NOTICE_TYPE}&nbsp;</td>
						<td nowrap="nowrap" align="center">${rst.NOTICE_OBJ}&nbsp;</td>
						<td nowrap="nowrap" align="center">${rst.CRETIME}&nbsp;</td>
						<td nowrap="nowrap" align="center">${rst.STATIME }&nbsp;</td>
						<td nowrap="nowrap" align="center">${rst.ENDTIME}&nbsp;</td>
						<td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
						 <input id="state${rst.NOTICE_ID}" type="hidden"  value="${rst.STATE}" />
						
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="12" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
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
					<td width="8%" nowrap align="right" class="detail_label">开始时间：</td>
					<td width="15%" class="detail_content">
	   					<input id="STATIME" name="STATIME" readonly="readonly"  onclick="SelectTime();" style="width:155" value="${params.STATIME }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(STATIME);">
					</td>
					<td width="8%" nowrap align="right"readonly="readonly" class="detail_label">结束时间：</td>
					<td width="15%" class="detail_content">
	   					<input id="ENDTIME" name="ENDTIME"  onclick="SelectTime();"style="width:155" value="${params.ENDTIME }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(ENDTIME);">
					</td>
					
					 <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input  type="text"  id="CRETIME_START" name="CRETIME_START" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRETIME_START}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRETIME_START);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input  type="text" id="CRETIME_END" name="CRETIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.CRETIME_END}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRETIME_END);">
					</td>
					
				</tr>
				<tr>
				    <td width="8%" nowrap align="right" class="detail_label">公告类型：</td>
					<td width="15%" class="detail_content">
	   					 <select name="NOTICE_TYPE" id="NOTICE_TYPE" json="NOTICE_TYPE" style="width:155"> </select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
	   					 <select name="STATE" id="STATE" json="STATE" style="width:155"> </select>
					</td>
					<td width="15%" align="right" class="detail_label">公告对象：</td>
					<td width="35%" class="detail_content">
                    <input type="text"  id="NOTICE_OBJ" name="NOTICE_OBJ" json="NOTICE_OBJ" maxlength="30"    autocheck="true" inputtype="string" label="公告对象" value="${params.NOTICE_OBJ}" mustinput="true"  />&nbsp;
                     <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_128', true, 'NOTICE_OBJ', 'SJXMC', 'forms[1]','NOTICE_OBJ', 'SJXMC', '')">
					</td>
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
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
</html>

<script type="text/javascript">

   SelDictShow("NOTICE_TYPE", "192", "${params.NOTICE_TYPE}", "");
   SelDictShow("STATE", "BS_138", "${params.STATE}", "");
	//初始化 审批流程
	spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "UPDTIME");	   
</script>
