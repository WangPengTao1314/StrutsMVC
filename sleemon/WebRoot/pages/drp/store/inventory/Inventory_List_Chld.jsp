<!--
 * @prjName:喜临门营销平台
 * @fileName:Inventory_List
 * @author lyg
 * @time   2013-09-07 09:54:59 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/inventory/Inventory_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		.text_underline{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:100px;
		}
	</style>		
</head>
<body >
<table width="99.8%" height="90%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="edit" type="button" class="btn" value="新增(E)" title="Alt+E" accesskey="E">
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
			   	   </c:if>
			   	   <c:if test="${pvg.PVG_DOWNLOAD eq 1}">
			   		 <input id="download" type="button" class="btn" value="盘点导出(O)" title="Alt+O" accesskey="O" />
			   		</c:if>
			   		<c:if test="${pvg.PVG_UPLOADING eq 1}">
			   		<input id="uploading" type="button" class="btn" value="盘点导入(U)" title="Alt+U" accesskey="U"/>
			   		</c:if>
				   &nbsp;
				</td>
				<td>
					账面数量：<input class="text_underline" readonly id="ACCT_NUM" value="${invNum.ACCT_NUM}" >盘点数量：<input class="text_underline" readonly id="INV_NUM" value="${invNum.INV_NUM}" >差异数量：<input class="text_underline" readonly value="${invNum.DIFF_NUM}" id="DIFF_NUM" >
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
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号&nbsp;-&nbsp;特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>-->
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="" >特殊规格说明</th>
                    <th  nowrap="nowrap" align="center" dbname="ACCT_NUM" >账面数量</th>
                    <th  nowrap="nowrap" align="center" dbname="ACCT_NUM" >盘点数量</th>
                    <th  nowrap="nowrap" align="center" dbname="DIFF_NUM" >差异数量</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle" json="PRD_INV_DTL_ID" name="PRD_INV_DTL_ID"  id="eid${rr}" value="${rst.PRD_INV_DTL_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}
                     <c:if test="${rst.DM_SPCL_TECH_NO ne null}">-${rst.DM_SPCL_TECH_NO}</c:if>
                     &nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_SPEC}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>-->
                     <td nowrap="nowrap" align="left" >${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                     	 <c:if test="${rst.SPCL_TECH_FLAG ge 1}">
                     	 	<c:if test="${!empty rst.SPCL_SPEC_REMARK }">
	                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
	                     	</c:if>
                     	 	<input type="button" class="btn" value="查看" onclick="url('${rst.SPCL_TECH_ID}')" />
                     	 </c:if>
                     	 <c:if test="${rst.SPCL_TECH_FLAG eq '0' || rst.SPCL_TECH_FLAG eq null}">
                     	 	无
                     	 </c:if>
                     	&nbsp;
                     </td>
                     <td nowrap="nowrap" align="right" id="ACCT_NUM${rr}" name="ACCT_NUM" >${rst.ACCT_NUM}</td>
                     <td nowrap="nowrap" align="center" ><input type="text" onkeyup="countDiffNum('${rr}')"  json="INV_NUM" name="INV_NUM" id="INV_NUM${rr}" value="${rst.INV_NUM}"/></td>
                     <td nowrap="nowrap" align="right" id="DIFF_NUM${rr}" name="DIFF_NUM">${rst.DIFF_NUM}</td>
                     <input type="hidden" name="INS_FLAG" value="${rst.INS_FLAG}"/>
				    </tr>
				</c:forEach>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td height="10px"  >
				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
					<tr>
						<td>
							<form id="pageForm"  action="#" method="post" name="listForm">
							<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
								<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
								<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
								<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
								<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
								<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
								<input type="hidden" id="oldPageNo" name="oldPageNo" value="${page.currentPageNo}" >
								<input type="hidden" id="PRDIDS" name="PRDIDS"> 
								<span id="hidinpDiv" name="hidinpDiv"></span>
								${paramCover.unCoveredForbidInputs }
							</form>
						</td>
						<td align="right">
							 ${page.footerHtml}${html}
						</td>
					</tr>
				</table>
			</td>
</tr>
</table>
<form id="form1" action="#" method="post" >
	<input type="hidden" id="PRD_INV_DTL_IDS" name="PRD_INV_DTL_IDS" value=""/>
</form>
</body>
</html>