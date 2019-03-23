<!--
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:17:29 
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/techorder/TechorderManager_History_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<div>
<form action="techorderManager.shtml?action=toHistory" method="post">
	<table>
		<input type="hidden" name="selectADC" value=" DEL_FLAG=0 and DEAL_FLAG=1 and LEDGER_ID='${params.LEDGER_ID}'"/>
		<input type="hidden" name="selectGoods" value=" DEL_FLAG=0  and LEDGER_ID='${params.LEDGER_ID}'"/>
		<input type="hidden" name="PRD_ID" value="${params.PRD_ID}"/>
		<tr>
			<td width="8%" nowrap align="right" class="detail_label">特殊工艺编号:</td>
			<td width="15%" class="detail_content">
				<input id="DM_SPCL_TECH_NO" name="DM_SPCL_TECH_NO"  style="width:155" value="${params.DM_SPCL_TECH_NO}"/>
			</td>
			<td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
			<td width="15%" class="detail_content">
				<input id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN" readonly="readonly"  onclick="SelectDate();" style="width:155" value="${params.CRE_TIME_BEGIN}"/>
				<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CRE_TIME_BEGIN);" >
			</td>
			<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
			<td width="15%" class="detail_content">
				<input id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"  onclick="SelectDate();" style="width:155" value="${params.CRE_TIME_END}"/>
				<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CRE_TIME_END);" >
			</td>
			<td width="8%" nowrap align="right" class="detail_label">详细特殊规格说明:</td>
			<td width="15%" class="detail_content">
				<input id="SPCL_DTL_REMARK" name="SPCL_DTL_REMARK"  style="width:155" value="${params.SPCL_DTL_REMARK}"/>
			</td>
			
		</tr>
		<tr>
			<td width="8%" nowrap align="right" class="detail_label"></td>
			<td width="15%" class="detail_content">
			</td>
			<td width="8%" nowrap align="right" class="detail_label"></td>
			<td width="15%" class="detail_content">
			</td>
			<td width="8%" nowrap align="right" class="detail_label"></td>
			<td width="15%" class="detail_content">
				<input id="search" type="submit" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
				<input  type="button" class="btn" id="reset" value="重置(R)" title="Alt+R" accesskey="R">
			</td>
		</tr>
	</table>
	</form>
</div>
<table width="99.8%" height="100%" style="width: 100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top" height="45%">
		<div class="lst_area" style="margin-left:3px;height: 100%">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="DM_SPCL_TECH_NO" >特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_DTL_REMARK" >详细特殊规格说明</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.SPCL_TECH_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.DM_SPCL_TECH_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
                     	<c:if test="${!empty rst.SPCL_DTL_REMARK }">
                     		<label class="hideNoticeText" title="${rst.SPCL_DTL_REMARK}">${rst.SPCL_DTL_REMARK}</label>
                     	</c:if>
                     	<input type="button" class="btn" value="查看" onclick="url('${rst.SPCL_TECH_ID}')" />
                     </td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
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
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
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
				<td align="left">
					 ${page.footerHtml}${page.toolbarHtml}
				</td>
				<td>
					<input type="button" onclick="getSpcl()" class="btn"  value="确定"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" onclick="window.close()" value="关闭" class="btn">
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
</body>

</html>
<script type="text/javascript">
</script>