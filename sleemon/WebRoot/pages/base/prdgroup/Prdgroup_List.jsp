<%--
/**
 * @module 基础信息
 * @fuc 货品组
 * @version 1.1
 * @author 张忠斌
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>货品组信息列表</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/base/prdgroup/Prdgroup_List.js"></script>
</head>
<body class="bodycss1">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;基础信息&gt;&gt;货品组信息</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
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
					  <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
						<input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
					  </c:if>	
					  <c:if test="${pvg.PVG_START_STOP eq 1 }">
					       <input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">		
			   		       <input id="stop" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">
					  </c:if>
					  <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1}">
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
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th nowrap align="center" dbname="PRD_GROUP_NO" >货品组编号</th>
					<th nowrap align="center" dbname="PRD_GROUP_NAME" >货品组名称</th>
					<th nowrap align="center" dbname="CRE_NAME" >制单人</th>
					<th nowrap align="center" dbname="CRE_TIME" >制单时间</th>
					<th nowrap align="center" dbname="DEPT_NAME" >制单部门</th>
					<th nowrap align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setCPZJTZDID('${rst.PRD_GROUP_ID}');setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.PRD_GROUP_ID}"/>
						</td>
						<td nowrap align="left">${rst.PRD_GROUP_NO}&nbsp;</td>
						<td nowrap align="left">${rst.PRD_GROUP_NAME}&nbsp;</td>
						<td nowrap align="left">${rst.CRE_NAME }&nbsp;</td>
						<td nowrap align="center">${rst.CRE_TIME}&nbsp;</td>
						<td nowrap align="left">${rst.DEPT_NAME}&nbsp;</td>
						<!-- <td nowrap align="left">${rst.SATE}&nbsp;</td>-->
						<td nowrap align="center" id="state${rst.PRD_GROUP_ID}" value="${rst.STATE}">${rst.STATE }&nbsp;</td>
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="19" align="center" class="lst_empty">
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
						<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">
						<input type="hidden" id="groupID" name="groupID">&nbsp;
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
<input type="hidden" name="condition" value=" DEL_FLAG=0">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td class="detail2">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="detail3">
							<tr>
								<td nowrap width="10%" align="right" class="detail_label">
									货品组编号：
								</td>
								<td nowrap width="15%"  class="detail_content">
									<input type="text" json="PRD_GROUP_NO" value="${params.PRD_GROUP_NO}" name="PRD_GROUP_NO" size="20" seltarget="selBH" inputtype="string" class="uppercase"/>
									<input json="PRD_GROUP_ID" name="PRD_GROUP_ID" value="${params.PRD_GROUP_ID}" type="hidden">
									<img align="absmiddle" name="selBH" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
									     onClick="selCommon('BS_29', true, 'PRD_GROUP_ID', 'PRD_GROUP_ID', 'forms[1]','PRD_GROUP_NO,PRD_GROUP_NAME',
                                         'PRD_GROUP_NO,PRD_GROUP_NAME', 'condition')">
								</td>
								<td nowrap  width="10%"  align="right" class="detail_label">
									货品组名称：
								</td>
								<td nowrap width="15%" class="detail_content">
									<input type="text" json="PRD_GROUP_NAME" name="PRD_GROUP_NAME" value="${params.PRD_GROUP_NAME}" size="20" seltarget="selMXXX" inputtype="string" class="uppercase"/>
								</td>
							   <td width="8%" nowrap align="right" class="detail_label">制单时间从：</td>
								<td width="15%" class="detail_content">
									<input type="text" id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN" onclick="SelectTime();" size="20" value="${params.CRE_TIME_BEGIN}" >
									<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_BEGIN);">
								</td>
								<td width="8%" nowrap align="right" class="detail_label">制单时间到：</td>
								<td width="15%" class="detail_content">
									<input type="text" id="CRE_TIME_END" name="CRE_TIME_END" onclick="SelectTime();" value="${params.CRE_TIME_END}"  size="20" >
									<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_END);">
								</td>
							</tr>		
							<tr>
								<td width="8%" nowrap align="right" class="detail_label">
										状态：
								</td>
									<td width="15%" class="detail_content">
									 <select name="STATE" id="STATE" style="width:155" ></select>
								</td>	
								<td width="8%" nowrap align="right" class="detail_label">
								</td>
								<td width="15%" class="detail_content"></td>
								<td width="8%" nowrap align="right" class="detail_label">
								</td>
								<td width="15%" class="detail_content"></td>
								<td width="8%" nowrap align="right" class="detail_label">
								</td>
								<td width="15%" class="detail_content"></td>
							</tr>
				<tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">
					    <input  type="reset" class="btn" value="重置(R)" onclick="clear()"  title="Alt+R" accesskey="R">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</td>
</tr>
</table>
</form>
</div>
</body>
<script language="JavaScript">
	SelDictShow("STATE", "32", "${params.STATE}", "");
</script>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
</html>
