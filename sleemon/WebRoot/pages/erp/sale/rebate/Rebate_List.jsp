

<!--  
/**
 * @module 系统管理
 * @func 返利登记
 * @version 1.1
 * @author  张忠斌
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
	<title>返利登记</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/rebate/Rebate_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>返利管理>>返利登记</td>
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
					<c:if test="${pvg.PVG_DELETE eq 1 }">
						<input id="reset" type="button" class="btn" value="清空返利(R)" title="Alt+R" accesskey="R">
					</c:if>
					<input id="addRebate" type="button" class="btn" value="增加返利" title="Alt+A" accesskey="A">
					<input id="decrRebate" type="button" class="btn" value="减少返利" title="Alt+C" accesskey="C">
			   		<input id="expdate" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
				</td>
				<td>
				<form id="queryForm" method="post" action="#">
				<input type="hidden" name="selectParams" value=" STATE in( '启用')">
				  <select name="CHECK_IN" id="CHECK_IN" style="width:100" ></select>&nbsp;&nbsp;&nbsp;&nbsp;
				    渠道编号：<input type="text" id="CHANN_NO"  name="CHANN_NO"  json="CHANN_NO" value="${params.CHANN_NO}" />
				           <input type="hidden" name="CHANN_ID" />
				           <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selectParams')">
			               &nbsp;&nbsp;
			               渠道名称：<input type="text" id="CHANN_NAME" name="CHANN_NAME"  json="CHANN_NAME"  value="${params.CHANN_NAME}" />
							<c:if test="${pvg.PVG_BWS eq 1 }">
								<input id="q_search" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
							</c:if>
							
				</form>
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
					<th align="center"><input type="checkbox" style="valign:middle" id="allChecked"></th>
					<th nowrap align="center" dbname="CHANN_NO">渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME">渠道名称</th>
					<th nowrap align="center" dbname="CHANN_TYPE">渠道类型</th>
					<th nowrap align="center" dbname="AREA_NO">区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME">区域名称</th>
					<th nowrap align="center" dbname="REBATE_TOTAL">返利总额</th>
					<th nowrap align="center" dbname="CURR_REBATE">当前返利</th>
					<th nowrap align="center" dbname="REBATE_USED">已用返利</th>
					 
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
						<td width="1%"align='center' >
						    <input type="hidden" id="hidcheck" value="" />
							<input type="checkbox" style="height:12px;valign:middle;" name="eid" id="eid${rr}" value="${rst.CHANN_ID}" onclick="setEidChecked(this);"/>
						</td>
                        <td nowrap align="center">${rst.CHANN_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.CHANN_NAME}&nbsp;</td>
                        <td nowrap align="center" >${rst.CHANN_TYPE}&nbsp;</td>
                        <td nowrap align="center">${rst.AREA_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.AREA_NAME}&nbsp;</td>
                        <td nowrap align="center"> ${rst.REBATE_TOTAL}&nbsp;</td>
                        <td nowrap align="left">${rst.REBATE_CURRT}&nbsp;</td>
                        <td nowrap align="right">${rst.REBATE_USED}&nbsp;</td> 
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
<tr >
	<td height="12px" align="center">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" class="lst_toolbar" style="margin-bottom:20px; ">
			<tr>
				<td>
					<form id="listForm" action="#" method="post" name="listForm">
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
 
</body>

<script language="JavaScript">
	 
		SelDictShow("CHECK_IN","BS_8","${params.CHECK_IN}","");
	 
</script>
	 
 
</html>
