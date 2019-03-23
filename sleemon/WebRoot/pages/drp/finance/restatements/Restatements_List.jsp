<!--
 * @prjName:喜临门营销平台
 * @fileName:Restatements_List
 * @author chenj
 * @time   2013-10-12 15:21:43 
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
	<script type="text/javascript" src="${ctx}/pages/drp/finance/restatements/Restatements_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td height="20px">&nbsp;&nbsp;当前位置：财务管理>>财务结算>>客户退货结算</td>
				<td width="50" align="right"></td>
			</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="30">
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
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C" onclick="opSub();">
					<!--  <input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">-->
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
				   <!--  <input id="personal" type="button" class="btn" value="个性化设置" >-->
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
                    <th  nowrap="nowrap" align="center" dbname="RETURN_STATEMENTS_NO" >客户退货结算编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_ORDER_NO" >终端退货编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NO" >终端编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >终端名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CUST_NAME" >客户名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RETURN_AMOUNT" >退货金额</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_PSON_NAME" >业务员名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STATEMENTS_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="left" >${rst.STATEMENTS_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.ADVC_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.TERM_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.TERM_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.CUST_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.STATEMENTS_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.SALE_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.STATE}&nbsp;</td>
                    <input id="state${rst.STATEMENTS_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="9" align="center" class="lst_empty">
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
                    <td width="8%" nowrap align="right" class="detail_label">客户退货结算编号:</td>
					<td width="15%" class="detail_content">
						<input id="ZTXX_ID" name="ZTXX_ID" json="ZTXX_ID" type='hidden' value="${params.LEDGER_ID}">
						<input type='hidden' id='con' />
	   					<input id="STATEMENTS_NO" name="STATEMENTS_NO"  style="width:155" value="${params.STATEMENTS_NO}"/>
	   					<img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30" onClick="queryReturnStatementNo();">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">终端退货编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ADVC_ORDER_NO" name="ADVC_ORDER_NO"  style="width:155" value="${params.ADVC_ORDER_NO}"/>
	   					<img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30" onClick="queryOrderNo();">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">终端编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_NO" name="TERM_NO"  style="width:155" value="${params.TERM_NO}"/>
	   					<img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30" onClick="queryTeamNo();">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">终端名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_NAME" name="TERM_NAME"  style="width:155" value="${params.TERM_NAME}"/>
					</td>					
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">客户名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="CUST_NAME" name="CUST_NAME"  style="width:155" value="${params.CUST_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_BEG" name="CRE_TIME_BEG" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.CRE_TIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_END);">
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
						<select id="STATE" name="STATE" style="width: 155"></select>
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
//初始化 审批流程
		SelDictShow("STATE","BS_42","${params.STATE}","");
       spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>