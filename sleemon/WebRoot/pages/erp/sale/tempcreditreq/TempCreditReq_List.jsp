
<!--  
/**
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author  刘曰刚
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>临时额度调整申请列表</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/tempcreditreq/TempCreditReq_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		#creditValdt{
			margin: 0px auto; 
			width:600px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:160px;
			left:230px;
			top:50px;
			text-align:center;
			display: block;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<c:if test="${params.module=='wh'}">
				<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;信用管理&gt;&gt;临时信用申请</td>
			</c:if>
			<c:if test="${params.module=='sh'}">
				<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;信用管理&gt;&gt;临时信用审核</td>
			</c:if>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20px" valign="top">
	<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="margin-left: 3px">
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
					   <c:if test="${pvg.PVG_TEMP_CREDIT_EDIT eq 1}">
					   		<input id="upCredit" type="button" class="btn" value="修改临时信用有效期(U)" title="Alt+U" accesskey="U">
					   </c:if>
					   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
					    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
					   </c:if>
					   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
				   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
					   </c:if>
					   
					   <c:if test="${pvg.PVG_CLOSE eq 1 }">
					    <input id="close" type="button" class="btn" value="关闭(G)" title="Alt+G" accesskey="G">
					   </c:if>
					    <div id="creditValdt" style="display: none;">
					   		<form action="#">
					   		<table width="100%">
					   			<tr>
					   				<td align="center" height="80px;">
					   					临时信用有效期：<input type="text" json="TEMP_CREDIT_VALDT" id="TEMP_CREDIT_VALDT" name="TEMP_CREDIT_VALDT"  autocheck="true" inputtype="string" label="申请临时信用"  mustinput="true" onclick="SelectDate();" readonly />&nbsp;
										<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(TEMP_CREDIT_VALDT);"/>
					   				</td>
					   			</tr>
					   			<tr>
					   				<td align="center" height="80px;">
					   					<input type="button" class="btn" value="确定" onclick="toAudit()" style="margin-right: 50px;" /><input type="button" class="btn" value="关闭" onclick="closeCreditDiv()" />
					   				</td>
					   			</tr>
					   		</table>
					   		</form>
					   </div>
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
					<th nowrap align="center" dbname="TEMP_CREDIT_REQ_ID">临时信用申请单号</th>
					<th nowrap align="center" dbname="CHANN_NO">渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME">渠道名称</th>
					<th nowrap align="center" dbname="AREA_NO">区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME">区域名称</th>
					<th nowrap align="center" dbname="TEMP_CREDIT_REQ">申请临时信用额度</th>
					<th nowrap align="center" dbname="TEMP_CREDIT_VALDT">临时信用有效期</th>
					<c:if test="${params.module eq 'sh'}">
					<th nowrap align="center" dbname="OPERATORNAME">上一审批人</th>
					<th nowrap align="center" dbname="OPERATETIME">上一审批时间</th>
					</c:if>
					<c:if test="${params.module eq 'wh'}">
					<th nowrap align="center" dbname="REQ_PSON_NAME">申请人</th>
					<th nowrap align="center" dbname="CRE_NAME">制单人</th>
					<th nowrap align="center" dbname="CRE_TIME">制单时间</th>
					<th nowrap align="center" dbname="DEPT_NAME">制单部门</th>
				   </c:if>
					<th nowrap align="center" dbname="STATE">状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.TEMP_CREDIT_REQ_ID}"/>
						</td>
                        <td nowrap align="center">${rst.TEMP_CREDIT_REQ_ID}&nbsp;</td>
                        <td nowrap align="center">${rst.CHANN_NO}&nbsp;</td>
                        <td nowrap align="left" >${rst.CHANN_NAME}&nbsp;</td>
                        <td nowrap align="center" >${rst.AREA_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.AREA_NAME}&nbsp;</td>
                        <td nowrap align="right">${rst.TEMP_CREDIT_REQ}&nbsp;</td>
                        <td nowrap align="center">${rst.TEMP_CREDIT_VALDT}&nbsp;</td>
                        <c:if test="${params.module eq 'sh'}">
                         <td nowrap align="center">${rst.OPERATORNAME}&nbsp;</td>
                         <td nowrap align="center">${rst.OPERATETIME}&nbsp;</td>
                        </c:if>
                        <c:if test="${params.module eq 'wh'}">
                        <td nowrap align="left">${rst.REQ_PSON_NAME}&nbsp;</td>
                        <td nowrap align="left">${rst.CRE_NAME}&nbsp;</td>
                        <td nowrap align="center">${rst.CRE_TIME}&nbsp;</td>
                        <td nowrap align="left">${rst.DEPT_NAME}&nbsp;</td>
                        </c:if>
                        <td nowrap json='STATE' align="center">${rst.STATE}&nbsp;</td>
                        <input id="state${rst.TEMP_CREDIT_REQ_ID}" type="hidden"  value="${rst.STATE}" />
                        <input id="data${rst.TEMP_CREDIT_REQ_ID}" type="hidden"  value="${rst.TEMP_CREDIT_VALDT}" />
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
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
					${paramCover.unCoveredForbidInputs}
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
<div id="querydiv" class="query_div" >
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<input type="hidden" name="selectParams" value=" STATE in( '启用') and DEL_FLAG=0">
	<c:if test="${IS_DRP_LEDGER eq 0}">
		<input type="hidden" name="selectChann" value=" STATE in( '启用') and DEL_FLAG=0 and CHANN_ID in ${params.CHANNS_CHARG}">	
	</c:if>
	<c:if test="${IS_DRP_LEDGER ne 0}">
		<input type="hidden" name="selectChann" value=" STATE in( '启用') and DEL_FLAG=0 and CHANN_ID='${params.LEDGER_ID}' ">	
	</c:if>
	
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="10%" align="right" class="detail_label">申请单号：</td>
					<td width="15%" class="detail_content">
						<input id="TEMP_CREDIT_REQ_ID" name="TEMP_CREDIT_REQ_ID" type="text" inputtype="string" maxlength="32"
							 label="申请单号"  json="AREA_NAME" value="${params.TEMP_CREDIT_REQ_ID }" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label" >渠道编号：</td>
					<td nowrap width="15%" class="detail_content">
						<input id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" type="hidden"
							inputtype="string" seltarget="selJGXX" autocheck="true"
							maxlength="100" value="${params.CHANN_ID}">
														
						<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text" inputtype="string"
							seltarget="selJGXX" autocheck="true" maxlength="32"
							value="${params.CHANN_NO}" >
							
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[2]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectChann')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" id="CHANN_NAME" json="CHANN_NAME" name="CHANN_NAME" value="${params.CHANN_NAME }"/>
					</td>
					<td width="8%" align="right" class="detail_label" nowrap>区域编号：</td>
					<td width="15%" class="detail_content">
						<input id="AREA_NO" json="AREA_NO"  name="AREA_NO" type="text" inputtype="string"
							  value="${params.AREA_NO}"  lable="区域编号">
						<img align="absmiddle" name="selAREA" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						onClick="selCommon('BS_18', false, 'AREA_NO', 'AREA_NO', 'forms[2]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selectParams')">
					</td>
					
				</tr>
				<tr>
					<td width="8%" align="right" class="detail_label">区域名称：</td>
					<td width="15%" class="detail_content">
						<input id="AREA_NAME" name="AREA_NAME" type="text" inputtype="string" maxlength="50"
							 label="区域名称"  json="AREA_NAME" value="${params.AREA_NAME }" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间从：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="BEGIN_CRE_TIME" id="BEGIN_CRE_TIME" value="${params.BEGIN_CRE_TIME}" name="BEGIN_CRE_TIME" autocheck="true" inputtype="string" label="制单时间从"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(BEGIN_CRE_TIME);"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="END_CRE_TIME" id="END_CRE_TIME" name="END_CRE_TIME" value="${params.END_CRE_TIME }" autocheck="true" inputtype="string" label="制单时间从"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(END_CRE_TIME);"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select name=STATE id="STATE" json="STATE" style="width: 155"></select>
					</td>
				</tr>
				<tr>
					<td colspan="10" align="center" valign="middle" >
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">&nbsp;&nbsp;
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
<script language="JavaScript">
	 //$(function(){
	  SelDictShow("STATE","33","${params.STATE}","");
	 //初始化 审批流程
      spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");		
	 //}); 
</script>
</html>
