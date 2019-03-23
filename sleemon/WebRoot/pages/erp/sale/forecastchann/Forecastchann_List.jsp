<!--  
/**
 * @module 预计量上报管理
 * @func 预计量上报渠道设置
 * @version 1.1
 * @author 张忠斌
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
	<title>渠道列表</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/forecastchann/Forecastchann_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="94%" border="0" cellspacing="0" cellpadding="0" class="panel">
 
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%">
					 <input type="checkbox" id="allChecked"  style="valign:middle" />
					</th>
					<th nowrap align="center" dbname="">序号</th>
					<th nowrap align="center" dbname="CHANN_NO">渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME">渠道名称</th>
					<th nowrap align="center" dbname="CHANN_TYPE">类型</th>
					<th nowrap align="center" dbname="AREA_NAME_P">战区</th>
					<th nowrap align="center" dbname="IS_REPORT_FLAG">是否需要上报</th>
					<c:if test="${pvg.PVG_DELETE eq 1 }">
					 <th nowrap align="center" dbname="">操作</th>
					 </c:if>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));" >
						<td width="1%"align='center' >
							<input type="checkbox" style="valign:middle"  name="eid" id="eid${rr}"  value="${rst.CHANN_ID}"
						   onclick="setEidChecked(this);"/>
						</td>
						<script language="JavaScript">
						  <c:if test="${1 eq rst.IS_REPORT_FLAG}">
						    $("#eid${rr}").attr("checked","checked");
						  </c:if>
						</script>
						 <td nowrap align="center">${rr}</td>
                        <td nowrap align="center">${rst.CHANN_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.CHANN_NAME}&nbsp;</td>
                        <td nowrap align="center" >${rst.CHANN_TYPE}&nbsp;</td>
                        <td nowrap align="center" >${rst.AREA_NAME_P}&nbsp;</td>
                        <td nowrap align="center"> ${rst.IS_REPORT_FLAG_ZN} </td>
                        <c:if test="${pvg.PVG_DELETE eq 1 }">
                          <td nowrap align="center">
						    <input id="cancel" type="button" onclick="listDelConfirm(this,'eid${rr}');" class="btn" value="取消上报" 
						    <c:if test="${empty rst.IS_REPORT_FLAG or 0 eq rst.IS_REPORT_FLAG}"> disabled </c:if> >&nbsp;&nbsp;
                           </td>
                        </c:if>
                        <input type="hidden" id=""  name="IS_REPORT_FLAG" value="${rst.IS_REPORT_FLAG}"/>
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="8" align="center" class="lst_empty">
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
				<td align="right">
					 ${page.footerHtml}${page.toolbarHtml}
				</td>
			</tr>
		</table>
	</td>
</tr>
</table> 
 <form  id="queryForm" name="queryForm" action="" method="post">
       <input type="hidden" name="search" id="search" value="true" />
	   <input type="hidden" name="CHANN_NAME"  id="CHANN_NAME"    />
       <input type="hidden" name="CHANN_NO" id="CHANN_NO"/>
       <input type="hidden" name="CHANN_TYPE" id="CHANN_TYPE" />
       <input type="hidden" name="AREA_NAME_P" id="AREA_NAME_P"  value="">
	   <input type="hidden" name="AREA_NO_P"  id="AREA_NO_P"     value="" >
       <input type="hidden" name="notcharg" id="notcharg"  />
   </form>     
</body>

</html>
