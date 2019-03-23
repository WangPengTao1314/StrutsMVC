<!--
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List
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
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/annualClosing/AnnualClosing_List.js"></script>
</head>
<body >
<table width="99.8%" height="95%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<div align="center"><span style="font-size: 20px;font-weight: bold">渠道返利年结</span></div>
	<td valign="top">
	<div>
		<form id="queryForm" method="post" action="annualClosing.shtml?action=toList">
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
		               <tr>
		               		<td width="10%" nowrap align="right" class="detail_label">年份:</td>
							<td width="20%" class="detail_content">
			   					<select name="YEAR" style="width: 155px;" json="YEAR" id="YEAR" onchange="selChange();"></select>
							</td>
							<td width="10%" nowrap align="right" class="detail_label">&nbsp;</td>
							<td align="left" width="100%" class="detail_content" colspan="8">
								<input id="yearClosing" type="button" class="btn" value="年结(Y)" title="Alt+Y" accesskey="Y" />&nbsp;&nbsp;
							</td>
							<td width="10%" nowrap align="right" class="detail_label"></td>
							<td width="20%" class="detail_content">
							</td>
							<td width="10%" nowrap align="right" class="detail_label"></td>
							<td width="20%" class="detail_content">
							</td>
		               </tr>
					</table>
				</td>
			</tr>
		</table>
		</form>
		</div>
		
		<div class="lst_area" style="height: 80%">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >渠道编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NO" >区域编号</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >区域名称</th>
                    <th  nowrap="nowrap" align="center" dbname="YEAR" >年份</th>
                    <th  nowrap="nowrap" align="center" dbname="REBATE_TOTAL" >返利总额</th>
				</tr>
				<c:forEach items="${page}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  >
                     <td nowrap="nowrap" align="center">${rst.CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.YEAR}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.REBATE_TOTAL}&nbsp;</td>
                     <input type="hidden" name="REBATE_YEAR_ACCT_ID" value="${rst.REBATE_YEAR_ACCT_ID}"/>
				    </tr>
				</c:forEach>
				<c:if test="${empty page}">
					<tr>
						<td height="25" colspan="6" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
		
	</td>
</tr>
</table>

</body>
<script type="text/javascript">
	SelDictShow("YEAR","89","${params.YEAR}","");   
</script>
</html>