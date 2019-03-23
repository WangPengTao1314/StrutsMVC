<!--
 * @prjName:喜临门营销平台
 * @fileName:Paymentup_Detial
 * @author lyg
 * @time   2013-08-23 10:25:58 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" import="com.hoperun.commons.util.StringUtil" %>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/erpprmtprice/Erpprmtprice_List.js"></script>
	<style type="text/css">
		a {
			cursor: hand ;
		}
		.text_underline{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:100px;
			BACKGROUND-COLOR: transparent;
		}
	</style>
</head>
<body>
	<div style="overflow-x: auto; overflow-y: auto; height: 99.9%; width:100%">
	<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
		<div id="innerdiv" style="position:absolute;">
			<img id="bigimg" style="border:5px solid #fff;" src="" />
		</div>
	</div>
	<table width="99.8%" height="100%" border="0" align="center" cellspacing="0" cellpadding="0" class="panel">
			<tr>
				<td height="30px;">
					折后修改为:<input type="text" id="dect"  class="text_underline" onkeyup="upDect()"/> 
				</td>
				<td align="center">
					<input id="add" type="button" class="btn" style="width: 100px;" value="保存">
					<input id="start" type="button" class="btn" style="width: 100px;" value="启用">
					<input id="stop" type="button" class="btn" style="width: 100px;" value="停用">
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="2">
					<div class="lst_area" style="margin-left:3px;">
						<form id="queryForm" method="post" action="#">
						<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
							<tr class="fixedRow">
								<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked" value=""></th>
			                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
			                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
			                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
			                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
			                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
			                    <th  nowrap="nowrap" align="center" dbname="PRVD_PRICE" >供货价</th>
			                    <th  nowrap="nowrap" align="center" dbname="EFFCT_DATE_BEG" >有效期从</th>
			                    <th  nowrap="nowrap" align="center" dbname="EFFCT_DATE_END" >有效期到</th>
			                    <th  nowrap="nowrap" align="center" dbname="DECT_RATE" >折扣率</th>
			                    <th  nowrap="nowrap" align="center" dbname="PRMT_PRVD_PRICE" >活动供货价</th>
			                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);">
								 <td width="1%"align='center' >
									<input type="checkbox" style="height:12px;valign:middle" json="PRMT_PRICE_ID" id="eid${rr}" name="PRMT_PRICE_ID"  value="${rst.PRMT_PRICE_ID}" >
								 </td>
								 <td nowrap="nowrap" align="center">${rst.PRD_NO}&nbsp;</td>
			                     <td nowrap="nowrap" align="center">${rst.PRD_NAME}&nbsp;</td>
			                     <td nowrap="nowrap" align="center">${rst.PRD_SPEC}&nbsp;</td>
			                     <td nowrap="nowrap" align="left">${rst.BRAND}&nbsp;</td>
			                     <td nowrap="nowrap" align="left">${rst.PRD_COLOR}&nbsp;</td>
			                     <td nowrap="nowrap" align="left">${rst.PRVD_PRICE}&nbsp;</td>
			                     <td nowrap="nowrap" align="center">${rst.EFFCT_DATE_BEG}&nbsp;</td>
			                     <td nowrap="nowrap" align="center">${rst.EFFCT_DATE_END}&nbsp;</td>
			                     <td nowrap="nowrap" align="center">${rst.DECT_RATE}&nbsp;</td>
			                     <td nowrap="nowrap" align="center">
			                     	<input type="text"  value="${rst.PRMT_PRVD_PRICE}" name="PRMT_PRVD_PRICE" id="price${rr}" json="PRMT_PRVD_PRICE" onclick="setCheck('eid${rr}')"   mustinput="true" label="活动供货价"  autocheck="true" inputtype="float" />
			                     	<input type="hidden" value="${rst.PRD_ID}" name="PRD_ID" json="PRD_ID" > 
			                     	<input type="hidden" value="${rst.STATE}" id="state${rst.PRMT_PRICE_ID}"  > 
			                     </td>
			                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
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
						</form>
					</div>
				</td>
			</tr>
			<tr>
			<td height="12px" align="center">
				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
					<tr>
						<td>
							<form id="listForm" action="#" method="post" name="listForm">
								<input id="REBATEFLAG" name="REBATEFLAG" value="${REBATEFLAG}" type="hidden"/>
								<input id="LARGESSFLAG" name="LARGESSFLAG" value="${LARGESSFLAG}" type="hidden"/>
								<input id="SHOP_CART_TYPE" name="SHOP_CART_TYPE" value="${params.SHOP_CART_TYPE}" type="hidden"/>
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
							 ${page.footerHtml}${html}
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
	
</body>
<script type="text/javascript">
</script>
</html>