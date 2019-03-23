<!--
 * @prjName:喜临门营销平台
 * @fileName:Saleplan_List
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/saleplan/Saleplan_BatchList.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">		
		  <form method="POST" action="#" id="queryForm" >			
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"><br></th>
					<th nowrap align="center" dbname="CHANN_NO" >渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME" >渠道名称</th>
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
						<td width="1%" align='center' >
							<input type="checkbox" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.SALE_PLAN_ID}" onclick=""/>
							<input type="hidden" name="SALE_PLAN_ID" id="SALE_PLAN_ID${rr}" value="${rst.SALE_PLAN_ID}"/>
							<input type="hidden" id="state${rst.SALE_PLAN_ID}" value="${rst.STATE}"/>
						</td>
					 <td nowrap="nowrap" align="center">${rst.CHANN_NO}&nbsp;<input type="hidden"   id="CHANN_NO${rr}" name="CHANN_NO" json="CHANN_NO"/></td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NAME}&nbsp;<input type="hidden" id="CHANN_NAME${rr}" name="CHANN_NAME" json="CHANN_NAME"/></td>
                     <td nowrap="nowrap" align="center">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
                         <input type='hidden' name='AREA_MANAGE_ID${rr}' id='AREA_MANAGE_ID${rr}' json='AREA_MANAGE_ID' value='${rst.AREA_MANAGE_ID}' />
                         <input type='text'   name='AREA_MANAGE_NAME${rr}' id='AREA_MANAGE_NAME${rr}' json='AREA_MANAGE_NAME' value='${rst.AREA_MANAGE_NAME}' size='5' READONLY style="background-color:#EEEEEE;splid:0px;border:0px;">
                         <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onclick="selCommon('BS_167', true, 'AREA_MANAGE_NAME${rr}', 'AREA_MANAGE_NAME', 'forms[0]','AREA_MANAGE_ID${rr},AREA_MANAGE_NAME${rr}', 'AREA_MANAGE_ID,AREA_MANAGE_NAME', '')">
                     <td nowrap="nowrap" align="center">${rst.PLAN_YEAR}<input type="hidden" id="PLAN_YEAR" name="PLAN_YEAR${rr}" value="${rst.PLAN_YEAR}"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="YEAR_PLAN_AMOUNT" id="YEAR_PLAN_AMOUNT${rr}" json="YEAR_PLAN_AMOUNT" size="4" onblur="getRate(${rr})" onkeyup="checkYearAmount(this)" onchange="checkYearAmount(this)" /></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="JAN_AMOUNT" id="JAN_AMOUNT${rr}" json="JAN_AMOUNT" size="4" onblur="getFirtstAmount(${rr})" onchange="validate(this)"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="FEB_AMOUNT" id="FEB_AMOUNT${rr}" json="FEB_AMOUNT" size="4" onblur="getFirtstAmount(${rr})" onchange="validate(this)"/></td>                     
                     <td nowrap="nowrap" align="center"><input type="text" name="MAR_AMOUNT" id="MAR_AMOUNT${rr}" json="MAR_AMOUNT" size="4" onblur="getFirtstAmount(${rr})" onchange="validate(this)"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="FIRST_QUARTER_AMOUNT" id="FIRST_QUARTER_AMOUNT${rr}" json="FIRST_QUARTER_AMOUNT" size="4" readonly="true" disabled="disabled" style="background-color:#EEEEEE;splid:0px;border:0px;"/></td>
                     
                     <td nowrap="nowrap" align="center"><input type="text" name="APR_AMOUNT" id="APR_AMOUNT${rr}" json="APR_AMOUNT" size="4" onblur="getSecondAmount(${rr})" onchange="validate(this)"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="MAY_AMOUNT" id="MAY_AMOUNT${rr}" json="MAY_AMOUNT" size="4" onblur="getSecondAmount(${rr})" onchange="validate(this)"/></td>                     
                     <td nowrap="nowrap" align="center"><input type="text" name="JUN_AMOUNT" id="JUN_AMOUNT${rr}" json="JUN_AMOUNT" size="4" onblur="getSecondAmount(${rr})" onchange="validate(this)"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="SECOND_QUARTER_AMOUNT" id="SECOND_QUARTER_AMOUNT${rr}" json="SECOND_QUARTER_AMOUNT" size="4" readonly="true" disabled="disabled" style="background-color:#EEEEEE;splid:0px;border:0px;"/></td>
                     
                     <td nowrap="nowrap" align="center"><input type="text" name="JUL_AMOUNT" id="JUL_AMOUNT${rr}" json="JUL_AMOUNT" size="4" onblur="getThirdAmount(${rr})" onchange="validate(this)"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="AUG_AMOUNT" id="AUG_AMOUNT${rr}" json="AUG_AMOUNT" size="4" onblur="getThirdAmount(${rr})" onchange="validate(this)"/></td>                     
                     <td nowrap="nowrap" align="center"><input type="text" name="SEP_AMOUNT" id="SEP_AMOUNT${rr}" json="SEP_AMOUNT" size="4" onblur="getThirdAmount(${rr})" onchange="validate(this)"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="THIRD_QUARTER_AMOUNT" id="THIRD_QUARTER_AMOUNT${rr}" json="THIRD_QUARTER_AMOUNT" size="4" readonly="true" disabled="disabled" style="background-color:#EEEEEE;splid:0px;border:0px;"></td>
                     
                     <td nowrap="nowrap" align="center"><input type="text" name="OCT_AMOUNT" id="OCT_AMOUNT${rr}" json="OCT_AMOUNT" size="4" onblur="getFourthAmount(${rr})" onchange="validate(this)"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="NOV_AMOUNT" id="NOV_AMOUNT${rr}" json="NOV_AMOUNT" size="4" onblur="getFourthAmount(${rr})" onchange="validate(this)"/></td>                     
                     <td nowrap="nowrap" align="center"><input type="text" name="DEC_AMOUNT" id="DEC_AMOUNT${rr}" json="DEC_AMOUNT" size="4" onblur="getFourthAmount(${rr})" onchange="validate(this)"/></td>
                     <td nowrap="nowrap" align="center"><input type="text" name="FOURTH_QUARTER_AMOUNT" id="FOURTH_QUARTER_AMOUNT${rr}" json="FOURTH_QUARTER_AMOUNT" size="4" readonly="true" disabled="disabled" style="background-color:#EEEEEE;splid:0px;border:0px;"/></td>
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
		   </form>
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