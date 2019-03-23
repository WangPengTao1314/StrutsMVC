<%--
/**
 * @author 朱晓文
 * @function 数据引擎
 */
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>库存月结信息列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/acctengi/AcctEngi_List.js"></script>
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>

<body >
<input type="hidden" id="selflag" value="0" />
<table width="100%" height="94%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<div class="tablayer" >
			<form method="post" action="#">
			<input type="hidden" name="selectParamsChann"	value="STATE in( '启用','停用') and DEL_FLAG=0 "	>
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
				   <td nowrap>
				   <!--  
				    &nbsp;渠道编号：&nbsp;
				    <input id="CHANN_ID" name="CHANN_ID"  json="CHANN_ID" type="hidden" <c:if test="${params.IS_DRP_LEDGER eq 1}"> value="${params.LEDGER_ID}"</c:if> />
					<input id="CHANN_NO" name="CHANN_NO"  json="CHANN_NO" type="text" readonly style="width:155" <c:if test="${params.IS_DRP_LEDGER eq 1}"> value="${params.LEDGER_NO}"</c:if> />
				    <c:if test="${params.IS_DRP_LEDGER ne 1}">
				   				<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]',
										'CHANN_ID,CHANN_NO,CHANN_NAME', 
										'CHANN_ID,CHANN_NO,CHANN_NAME',
										'selectParamsChann');">
								</c:if>
				    &nbsp;渠道名称:&nbsp;
				    <input id="CHANN_NAME" name="CHANN_NAME" readonly json="CHANN_NAME" style="width:155" <c:if test="${params.IS_DRP_LEDGER eq 1}"> value="${params.LEDGER_NAME}"</c:if> />
				    -->
				    &nbsp;年份:&nbsp;<select id="YEAR" name="YEAR" style="width: 100px" ></select>
				    &nbsp;月份:&nbsp;<select id="MONTH" name="MONTH" style="width: 100px" ></select>
			   		&nbsp;&nbsp;<input id="act_Btn" type="button" class="btn" value="  生成  " >
				</td>
				</tr>
			</table>
			</form>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center">渠道编号</th>
					<th nowrap align="center">渠道名称</th>
					<th nowrap align="center">年份</th>
					<th nowrap align="center">月份</th>
					<th nowrap align="center">更新人</th>
					<th nowrap align="center">更新时间</th>
					<th nowrap align="center">状态</th>
					<th nowrap align="center">操作</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="setSelEids(document.getElementById('eid${rr}'),'${rst.ZTXXID}');">
					    <td nowrap align="center">${rst.CHANN_NO }&nbsp;</td>
						<td nowrap align="left">${rst.CHANN_NAME}&nbsp;</td>
						<td nowrap align="left">${rst.YEAR }&nbsp;</td>
						<td nowrap align="left">${rst.MONTH }&nbsp;</td>
						<td nowrap align="left">${rst.UPD_NAME }&nbsp;</td>
					    <td nowrap align="left">${rst.UPD_TIME }&nbsp;</td>
						<td nowrap align="left">${rst.STATE }&nbsp;</td>
						<td nowrap align="left">
						<input  type="button" class="btn" value=" 生成 " onclick="chkAccount('${rst.LEDGER_ID}','${rst.YEAR}','${rst.MONTH}')" >&nbsp;
						<input  type="button" class="btn" value="取消生成" onclick="cancleAccount('${rst.LEDGER_ID}','${rst.YEAR}','${rst.MONTH}','${rst.STATE}')">
						</td>
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
						<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
						<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
						<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
						<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
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
</body>
<script type="text/javascript">
		SelDictShow("YEAR","89","${params.CUR_YEAR}","");
        SelDictShow("MONTH","168","${params.CUR_MONTH}","");
</script>
