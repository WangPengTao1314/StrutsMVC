<!--
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time  2014-12-03 10:35:10 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/marketing/MarketActivitiy_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<c:if test="${module eq 'sh'}">
			  <td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>基础数据>>营销活动审核</td>
			</c:if>
			<c:if test="${module eq 'wh' || empty module}">
			  <td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>基础数据>>营销活动维护</td>
			</c:if>
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
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+C" accesskey="C">
					<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
				   </c:if> 
                   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
				    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
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
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
		   
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="MARKETING_ACT_NO" >营销活动编号</th>
                    <th  nowrap="nowrap" align="center" dbname="MARKETING_ACT_NAME" >营销活动名称</th>
                    <th  nowrap="nowrap" align="center" dbname="START_DATE" >开始日期</th>
                    <th  nowrap="nowrap" align="center" dbname="END_DATE" >结束日期</th>
                    <th  nowrap="nowrap" align="center" dbname="SPONSOR_NAME" >发起人名称</th>
                    <th  nowrap="nowrap" align="center" dbname="COMMISSION_PERCENTAGE" >提成比例</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.MARKETING_ACT_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.MARKETING_ACT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.MARKETING_ACT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.START_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.END_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SPONSOR_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.COMMISSION_PERCENTAGE_P}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center"> ${rst.DEPT_NAME}&nbsp; </td>
                     <td nowrap="nowrap" align="center">
                       ${rst.STATE}&nbsp;
                      <input id="state${rst.MARKETING_ACT_ID}" type="hidden"  value="${rst.STATE}" />
                     </td>
                   
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="11" align="center" class="lst_empty">
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
 <input type="hidden" id="selParam" name="selParam" value=" STATE in('启用','停用')"/>
 <input type="hidden" id="AREA_SER_ID" name="AREA_SER_ID" value="${AREA_SER_ID}"/>
 <input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE in('启用','停用') and CHANN_ID='${ZTXXID}' or AREA_SER_ID='${ZTXXID}' "/>
 <input type="hidden" id="selSendChannParam" name="selSendChannParam" value=" STATE in('启用','停用') and CHANN_ID in('${BASE_CHANN_ID}','${AREA_SER_ID}') "/>
 <input type="hidden" name=selectPrd value="STATE in ('启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 ">
 <input type="hidden" id="flag" name="flag" value=""/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                  <td width="8%" nowrap align="right" class="detail_label">营销活动编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="MARKETING_ACT_NO" name="MARKETING_ACT_NO" value="${params.MARKETING_ACT_NO}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">营销活动名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="MARKETING_ACT_NAME" name="MARKETING_ACT_NAME"   value="${params.MARKETING_ACT_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">发起人名称:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="SPONSOR_NAME" name="SPONSOR_NAME"   value="${params.SPONSOR_NAME}"/>
					</td>					
                     <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					 <td width="15%" class="detail_content">
					     <select id="STATE" name="STATE" style="width:155"  	></select>
					 </td>	
										
               </tr>
               
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">开始日期:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="START_DATE" name="START_DATE" onclick="SelectDate();" size="20" value="${params.START_DATE}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(START_DATE);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">结束日期:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="END_DATE" name="END_DATE" onclick="SelectDate();" size="20" value="${params.END_DATE}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(END_DATE);">
					</td>
					
                    <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN" onclick="SelectTime();" size="20" value="${params.CRE_TIME_BEGIN}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_BEGIN);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="CRE_TIME_END" name="CRE_TIME_END" onclick="SelectTime();" size="20" value="${params.CRE_TIME_END}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_END);">
					</td>
			 
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
				    	<input id="q_reset" type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
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
  //初始化 审批流程
  spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");   
</script>
</html>
