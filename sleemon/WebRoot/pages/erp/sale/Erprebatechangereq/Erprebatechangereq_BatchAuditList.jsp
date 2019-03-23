
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/Erprebatechangereq/Erprebatechangereq_BatchAuditList.js"></script>
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
<div align="center">
<h3>返利额度变更单批量操作</h3>
</div>


<form id="queryForm" method="post" action="#">
	<input type="hidden" name="selectChann" value=" STATE in( '启用') and DEL_FLAG=0 and CHANN_ID in ${CHANNS_CHARG}">	
	<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail" id="tabView">
		<tr>
			<td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3" >
					<tr>
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
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectChann')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" id="CHANN_NAME" json="CHANN_NAME" name="CHANN_NAME" value="${params.CHANN_NAME }"/>
					</td>					
						<td width="8%" nowrap align="right" class="detail_label">单据类型：</td>
						<td width="15%" class="detail_content" >
							<select name=BILL_TYPE id="BILL_TYPE" json="BILL_TYPE" style="width: 155"></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">返利类别：</td>
					<td width="15%" class="detail_content" >
					  <select name=REBATE_TYPE id="REBATE_TYPE" json="REBATE_TYPE" style="width: 155"></select>
					</td>
					</tr>
					<tr>
						<td colspan="10" align="center" valign="middle" >
							<input id="q_search" type="button" class="btn" value="查询(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;							
							<input  id="q_reset" type="button" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>


<table width="99.8%" height="70%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
					<th nowrap align="center" dbname="REBATE_CHANGE_REQ_NO">返利额度变更单编号</th>
					<th nowrap align="center" dbname="CHANN_NO">渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME">渠道名称</th>
					<th nowrap align="center" dbname="CHANN_TYPE">渠道类型</th>
					<th nowrap align="center" dbname="BILL_TYPE">单据类型</th>
					<th nowrap align="center" dbname="REBATE_TYPE">返利类别</th>
					<th nowrap align="center" dbname="REQ_PSON_NAME">申请人</th>
					<th nowrap align="center" dbname="CHANGE_REBATE">变更额度</th>
					<th nowrap align="center" dbname="STATE">状态</th>	
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					<td width="1%"align='center' >
						<input type="checkbox" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.REBATE_CHANGE_REQ_ID}"/>
					</td>
			        <td nowrap align="center">${rst.REBATE_CHANGE_REQ_NO}&nbsp;</td>
			        <td nowrap align="center">${rst.CHANN_NO}&nbsp;</td>
			        <td nowrap align="left" >${rst.CHANN_NAME}&nbsp;</td>
			        <td nowrap align="center" >${rst.CHANN_TYPE}&nbsp;</td>
			        <td nowrap align="center" >${rst.BILL_TYPE}&nbsp;</td>
			        <td nowrap align="center" >${rst.REBATE_TYPE}&nbsp;</td>
			        <td nowrap align="left">${rst.REQ_PSON_NAME}&nbsp;</td>
			        <td nowrap align="right">${rst.CHANGE_REBATE}&nbsp;</td>
			        <td nowrap json='STATE' align="center">${rst.STATE}&nbsp;</td>
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
	<tr>
		<td height="12px" align="center" >
				<input id="q_commit" type="button" class="btn" value="审核(O)"
										title="Alt+O" accesskey="O">
				<input id="q_close" type="button" class="btn" value="关闭(X)"
										title="Alt+X" accesskey="X">
		</td>
	</tr>
</table>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script language="JavaScript">
	  SelDictShow("BILL_TYPE","BS_152","${params.BILL_TYPE}","");   
	  SelDictShow("REBATE_TYPE","BS_167","${params.REBATE_TYPE}",""); 
</script>
</html>
