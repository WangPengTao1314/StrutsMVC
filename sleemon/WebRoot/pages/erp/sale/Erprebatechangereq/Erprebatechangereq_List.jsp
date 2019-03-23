
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
	<title>返利额度变更单列表</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/Erprebatechangereq/Erprebatechangereq_List.js"></script>
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
				<td height="20px">&nbsp;&nbsp;当前位置：营销管理&gt;&gt;返利管理&gt;&gt;返利额度变更单申请</td>
			</c:if>
			<c:if test="${params.module=='sh'}">
				<td height="20px">&nbsp;&nbsp;当前位置：营销管理&gt;&gt;返利管理&gt;&gt;返利额度变更单审核</td>
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
						<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+C" accesskey="C">
						<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
					   </c:if>
					   <c:if test="${pvg.PVG_BATCHCOMMIT_CANCLE eq 1 }">
					  	 <input id="Pcommit" type="button" class="btn" value="批量提交(P)" title="Alt+P" accesskey="P">
					   </c:if>
					   <c:if test="${pvg.PVG_COPY eq 1 }">
					  	 <input id="copy" type="button" class="btn" value="复制(P)" title="Alt+Y" accesskey="Y">
					   </c:if>
	                   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
					    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
					   </c:if>
					   <c:if test="${pvg.PVG_BATCHAUDIT_AUDIT eq 1 }">
					  	 <input id="Paudit" type="button" class="btn" value="批量审核(R)" title="Alt+R" accesskey="R">
					   </c:if>	
					   <c:if test="${pvg.PVG_CANCEL_AUDIT eq 1 }">
					   		<input id="cancelAudit" type="button" class="btn" value="弃审(S)" title="Alt+S" accesskey="S">
					   		<input id="batchCancelAudit" type="button" class="btn" value="批量弃审(W)" title="Alt+W" accesskey="W">
					   </c:if>				   
					   <c:if test="${pvg.PVG_EXPORT eq 1 }">
					    <input id="export" type="button" class="btn" value="导出(E)" title="Alt+E" accesskey="E">
					   </c:if>
					   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
					   	<input id="queryRebate" type="button" class="btn" value="当前返利(Q)" title="Alt+F" accesskey="F">
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
					<th nowrap align="center" dbname="REBATE_CHANGE_REQ_NO">返利额度变更单编号</th>
					<th nowrap align="center" dbname="CHANN_NO">渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME">渠道名称</th>
					<th nowrap align="center" dbname="CHANN_TYPE">渠道类型</th>
					<th nowrap align="center" dbname="BILL_TYPE">单据类型</th>
					<th nowrap align="center" dbname="REBATE_TYPE">返利类别</th>
					<th nowrap align="center" dbname="REQ_PSON_NAME">申请人</th>
					<th nowrap align="center" dbname="CHANGE_REBATE">变更额度</th>
					<th nowrap align="center" dbname="CHANGE_REBATE_OLD">变更前返利</th>
					<th nowrap align="center" dbname="CHANGE_REBATE_NEW">变更后返利</th>
					<th nowrap align="center" dbname="CRE_NAME">制单人</th>
					<th nowrap align="center" dbname="CRE_TIME">制单时间</th>
					<th nowrap align="center" dbname="DEPT_NAME">制单部门</th>
					<th nowrap align="center" dbname="STATE">状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.REBATE_CHANGE_REQ_ID}"/>
						</td>
                        <td nowrap align="center">${rst.REBATE_CHANGE_REQ_NO}&nbsp;</td>
                        <td nowrap align="center">${rst.CHANN_NO}&nbsp;</td>
                        <td nowrap align="left" >${rst.CHANN_NAME}&nbsp;</td>
                        <td nowrap align="center" >${rst.CHANN_TYPE}&nbsp;</td>
                        <td nowrap align="center" >${rst.BILL_TYPE}&nbsp;</td>
                        <td nowrap align="center" >${rst.REBATE_TYPE}&nbsp;</td>
                        <td nowrap align="left">${rst.REQ_PSON_NAME}&nbsp;</td>
                        <td nowrap align="right">${rst.CHANGE_REBATE}&nbsp;</td>
                        <td nowrap align="right">${rst.CHANGE_REBATE_OLD}&nbsp;</td>
                        <td nowrap align="right">${rst.CHANGE_REBATE_NEW}&nbsp;</td>
                        <td nowrap align="left">${rst.CRE_NAME}&nbsp;</td>
                        <td nowrap align="center">${rst.CRE_TIME}&nbsp;</td>
                        <td nowrap align="left">${rst.DEPT_NAME}&nbsp;</td>
                        <td nowrap json='STATE' align="center">${rst.STATE}&nbsp;</td>
                        <input id="state${rst.REBATE_CHANGE_REQ_ID}" type="hidden"  value="${rst.STATE}" />
                        <input id="chann${rst.REBATE_CHANGE_REQ_ID}" type="hidden"  value="${rst.CHANN_ID}" />
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="15" align="center" class="lst_empty">
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
	<input type="hidden" name="selectChann" value=" STATE in( '启用') and DEL_FLAG=0 and CHANN_ID in ${CHANNS_CHARG}">	
	<input type="hidden" id="search" name="search" value='${search}'/>
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="10%" align="right" class="detail_label">变更单编号：</td>
					<td width="15%" class="detail_content">
						<input id="REBATE_CHANGE_REQ_NO" name="REBATE_CHANGE_REQ_NO" type="text" inputtype="string" maxlength="32"
							 label="返利额度变更单编号"  json="REBATE_CHANGE_REQ_NO" value="${params.REBATE_CHANGE_REQ_NO }" >
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
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectChann')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" id="CHANN_NAME" json="CHANN_NAME" name="CHANN_NAME" value="${params.CHANN_NAME }"/>
					</td>
					<td width="8%" align="right" class="detail_label" nowrap>单据类型：</td>
					<td width="15%" class="detail_content">
						<select name=BILL_TYPE id="BILL_TYPE" json="BILL_TYPE" style="width: 155"></select>
					</td>
					
				</tr>
				<tr>
					<td width="8%" align="right" class="detail_label">申请人：</td>
					<td width="15%" class="detail_content">
						<input id="REQ_PSON_NAME" name="REQ_PSON_NAME" type="text" inputtype="string" maxlength="50"
							 label="申请人"  json="REQ_PSON_NAME" value="${params.REQ_PSON_NAME }" >
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
	  SelDictShow("STATE","33","${params.STATE}","");
	  SelDictShow("BILL_TYPE","BS_152","${params.BILL_TYPE}","");  
	 //初始化 审批流程
      spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");		
	 //}); 
</script>
</html>
