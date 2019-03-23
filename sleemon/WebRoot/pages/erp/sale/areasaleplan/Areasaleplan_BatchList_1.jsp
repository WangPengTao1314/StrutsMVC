<!--
 * @prjName:喜临门营销平台
 * @fileName:Areasaleplan_BatchList
 * @author zcx
 * @time   2014-12-5 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/areasaleplan/Areasaleplan_BatchList.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<table width="1350px" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">			
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
				    <th width="0.5%"><br></th>
					<th nowrap align="center" dbname="AREA_NAME">战区</th>
					<th nowrap align="center" dbname="AREA_MANAGE_NAME">区域经理</th>
					<th nowrap align="center" dbname="PLAN_YEAR">年份</th>
					<th nowrap align="center" dbname="YEAR_PLAN_AMOUNT" >年度指标</th>
                    <th nowrap align="center" dbname="JAN_AMOUNT" >1月</th>
                    <th nowrap align="center" dbname="FEB_AMOUNT" >2月</th>
                    <th nowrap align="center" dbname="MAR_AMOUNT" >3月</th>
                    <th nowrap align="center" dbname="FIRST_QUARTER_AMOUNT" >第一季度</th>
                    <th nowrap align="center" dbname="APR_AMOUNT" >4月</th>
                    <th nowrap align="center" dbname="MAY_AMOUNT" >5月</th>
                    <th nowrap align="center" dbname="JUN_AMOUNT" >6月</th>
                    <th nowrap align="center" dbname="SECOND_QUARTER_AMOUNT" >第二季度</th>
                    <th nowrap align="center" dbname="JUL_AMOUNT" >7月</th>
                    <th nowrap align="center" dbname="AUG_AMOUNT" >8月</th>
                    <th nowrap align="center" dbname="SEP_AMOUNT" >9月</th>
                    <th nowrap align="center" dbname="THIRD_QUARTER_AMOUNT" >第三季度</th>
                    <th nowrap align="center" dbname="OCT_AMOUNT" >10月</th>
                    <th nowrap align="center" dbname="NOV_AMOUNT" >11月</th>
                    <th nowrap align="center" dbname="DEC_AMOUNT" >12月</th>
                    <th nowrap align="center" dbname="FOURTH_QUARTER_AMOUNT" >第四季度</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
						<td width="0.5%" align='center' >
							<input type="checkbox" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.AREA_SALE_PLAN_ID}" onclick=""/>
							<input type="hidden" name="AREA_SALE_PLAN_ID" id="AREA_SALE_PLAN_ID${rr}" value="${rst.AREA_SALE_PLAN_ID}"/>
							<input type="hidden" id="state${rst.AREA_SALE_PLAN_ID}" value="${rst.STATE}"/>
						</td>
                     <td nowrap="nowrap" align="center">${rst.WAREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_MANAGE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PLAN_YEAR}<input type="hidden" id="PLAN_YEAR" name="PLAN_YEAR${rr}" value="${rst.PLAN_YEAR}"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="YEAR_PLAN_AMOUNT" id="YEAR_PLAN_AMOUNT${rr}" json="YEAR_PLAN_AMOUNT"  onblur="getRate(${rr});" size="5"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="JAN_AMOUNT" id="JAN_AMOUNT${rr}" json="JAN_AMOUNT"  onblur="getFirtstAmount(${rr})" onchange="validate(this)"  size="5"/></td>
					 <td nowrap="nowrap" align="center"><input type="text" name="FEB_AMOUNT" id="FEB_AMOUNT${rr}" json="FEB_AMOUNT"  onblur="getFirtstAmount(${rr})"  onchange="validate(this)" size="5"/></td>  
                    
                     <td nowrap="nowrap" align="center"><input type="text" name="MAR_AMOUNT" id="MAR_AMOUNT${rr}" json="MAR_AMOUNT"  onblur="getFirtstAmount(${rr})" onchange="validate(this)"  size="5"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="FIRST_QUARTER_AMOUNT" id="FIRST_QUARTER_AMOUNT${rr}" json="FIRST_QUARTER_AMOUNT"  readonly="true" disabled="disabled" style="background-color:#EEEEEE;splid:0px;border:0px;"  size="5"/></td>
                     
                     <td nowrap="nowrap" align="center"><input type="text" name="APR_AMOUNT" id="APR_AMOUNT${rr}" json="APR_AMOUNT"  onblur="getSecondAmount(${rr})" onchange="validate(this)"  size="5"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="MAY_AMOUNT" id="MAY_AMOUNT${rr}" json="MAY_AMOUNT"  onblur="getSecondAmount(${rr})" onchange="validate(this)"  size="5"/></td>                     
                     <td nowrap="nowrap" align="center"><input type="text" name="JUN_AMOUNT" id="JUN_AMOUNT${rr}" json="JUN_AMOUNT"  onblur="getSecondAmount(${rr})" onchange="validate(this)"  size="5"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="SECOND_QUARTER_AMOUNT" id="SECOND_QUARTER_AMOUNT${rr}" json="SECOND_QUARTER_AMOUNT"  readonly="true" disabled="disabled" style="background-color:#EEEEEE;splid:0px;border:0px;"  size="5"/></td>
                     
                     <td nowrap="nowrap" align="center"><input type="text" name="JUL_AMOUNT" id="JUL_AMOUNT${rr}" json="JUL_AMOUNT"  onblur="getThirdAmount(${rr})" onchange="validate(this)"  size="5"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="AUG_AMOUNT" id="AUG_AMOUNT${rr}" json="AUG_AMOUNT"  onblur="getThirdAmount(${rr})" onchange="validate(this)"  size="5"/></td>                     
                     <td nowrap="nowrap" align="center"><input type="text" name="SEP_AMOUNT" id="SEP_AMOUNT${rr}" json="SEP_AMOUNT"  onblur="getThirdAmount(${rr})" onchange="validate(this)"  size="5"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="THIRD_QUARTER_AMOUNT" id="THIRD_QUARTER_AMOUNT${rr}" json="THIRD_QUARTER_AMOUNT"   readonly="true" disabled="disabled" style="background-color:#EEEEEE;splid:0px;border:0px;"  size="5"></td>
                     
                     <td nowrap="nowrap" align="center"><input type="text" name="OCT_AMOUNT" id="OCT_AMOUNT${rr}" json="OCT_AMOUNT"  onblur="getFourthAmount(${rr})" onchange="validate(this)"  size="5"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="NOV_AMOUNT" id="NOV_AMOUNT${rr}" json="NOV_AMOUNT"  onblur="getFourthAmount(${rr})" onchange="validate(this)"  size="5"/></td>                     
                     <td nowrap="nowrap" align="center"><input type="text" name="DEC_AMOUNT" id="DEC_AMOUNT${rr}" json="DEC_AMOUNT"  onblur="getFourthAmount(${rr})" onchange="validate(this)"  size="5"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="FOURTH_QUARTER_AMOUNT" id="FOURTH_QUARTER_AMOUNT${rr}" json="FOURTH_QUARTER_AMOUNT"  readonly="true" disabled="disabled" style="background-color:#EEEEEE;splid:0px;border:0px;"  size="5"/></td>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="30" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td height="20px" align="center">
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
</body>
</html>