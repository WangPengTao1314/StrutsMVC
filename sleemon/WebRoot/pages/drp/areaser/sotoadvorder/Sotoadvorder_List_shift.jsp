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
	<script type="text/javascript" src="${ctx}/pages/drp/areaser/sotoadvorder/Sotoadvorder_List_shift.js"></script>
</head>
<body >
<table width="99.8%" height="95%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<div align="center"><h2 align="center">销售订单选取</h2></div>
	<td valign="top">
	<div>
		<form id="queryForm"  method="post" action="sotoadvorder.shtml?action=toListShift">
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<input type="hidden" name="selectParams" value=" DEL_FLAG=0 and STATE='启用' and ( CHANN_ID='${params.ORDER_CHANN_ID}' or AREA_SER_ID='${params.ORDER_CHANN_ID}') " />
			<input type="hidden" id="selectAddrParams" name=selectAddrParams value=" DEL_FLAG=0 and STATE='启用' ">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3" >
		               <tr>
		               		<td width="10%" nowrap align="right" class="detail_label">订货方编号:</td>
							<td width="20%" class="detail_content">
								<input type="hidden" id="ORDER_CHANN_ID" json="ORDER_CHANN_ID" name="ORDER_CHANN_ID" value="${params.ORDER_CHANN_ID}"/>
									<input  name="ORDER_CHANN_NO" json="ORDER_CHANN_NO" id="ORDER_CHANN_NO" type="text" autocheck="true" label="订货方编号" inputtype="string" mustinput="true"  READONLY value="${params.ORDER_CHANN_NO}" />
							</td>
							<td width="10%" nowrap align="right" class="detail_label">订货方名称:</td>
							<td width="20%" class="detail_content">
								<input  name="ORDER_CHANN_NAME" json="ORDER_CHANN_NAME" id="ORDER_CHANN_NAME" type="text" autocheck="true" label="订货方名称" inputtype="string" mustinput="true"  READONLY value="${params.ORDER_CHANN_NAME}" />
							</td>
							
							<td width="10%" nowrap align="right" class="detail_label">收货方编号:</td>
							<td width="20%" class="detail_content">
								<input type="hidden" id="RECV_CHANN_ID" json="RECV_CHANN_ID" name="RECV_CHANN_ID" value="${channINfo.CHANN_ID}"/>
									<input  name="RECV_CHANN_NO" json="RECV_CHANN_NO" id="RECV_CHANN_NO" type="text" autocheck="true" label="收货方编号" inputtype="string" mustinput="true"  READONLY value="${channINfo.CHANN_NO}" />
									<img align="absmiddle"  style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selChannInfo()">
							</td>
							<td width="10%" nowrap align="right" class="detail_label">收货方名称:</td>
							<td width="20%" class="detail_content">
								<input  name="RECV_CHANN_NAME" json="RECV_CHANN_NAME" id="RECV_CHANN_NAME" type="text" autocheck="true" label="收货方名称" inputtype="string" mustinput="true"  READONLY value="${channINfo.CHANN_NAME}" />
							</td>
		               </tr>
		               <tr>
		               		<td width="10%" nowrap align="right" class="detail_label">收货地址:</td>
							<td width="80%" colspan="7" class="detail_content">
								<input  name="RECV_ADDR_NO" json="RECV_ADDR_NO" id="RECV_ADDR_NO" type="hidden" autocheck="true" label="收货地址编号" inputtype="string"  />
								<input  name="RECV_ADDR" style="width:40%" json="RECV_ADDR" id="RECV_ADDR" type="text" autocheck="true" label="收货地址" inputtype="string" mustinput="true"  READONLY  />
								<img align="absmiddle" name="selAddr" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onclick="selRevcAddr();">
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
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >销售订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_DATE" >订货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="TOTL_AMOUNT" >订货总额</th>
<!--                     <th  nowrap="nowrap" align="center" dbname="IS_USE_REBATE" >是否使用返利</th>-->
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >订货区域</th>
                    <th  nowrap="nowrap" align="center" dbname="PERSON_CON" >联系人</th>
                    <th  nowrap="nowrap" align="center" dbname="TEL" >联系方式</th>
                    <th  nowrap="nowrap" align="center" dbname="SHIP_POINT_NAME" >生产基地名称</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));" >
					 <td width="1%"align='center' >
						<input type="checkbox" style="height:12px;valign:middle" json="SALE_ORDER_ID" id="eid${rr}" name="SALE_ORDER_ID" value="${rst.SALE_ORDER_ID}" onclick="setEidChecked(this);">
					 </td>
                     <td nowrap="nowrap" align="center">${rst.SALE_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORDER_CHANN_NAME}&nbsp;</td>
                      <td nowrap="nowrap" align="center">${rst.ORDER_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" name="TOTL_AMOUNT_${rst.SALE_ORDER_ID}">${rst.TOTL_AMOUNT}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center">-->
<!--                      <c:if test="${rst.IS_USE_REBATE eq 1}">-->
<!--                                                                       是-->
<!--                      </c:if>-->
<!--                      <c:if test="${rst.IS_USE_REBATE ne 1}">-->
<!--                                                                       否-->
<!--                      </c:if>-->
<!--                      &nbsp;-->
<!--                      </td>-->
                     <td nowrap="nowrap" align="left">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PERSON_CON}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.SHIP_POINT_NAME}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty page}">
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
<tr>
					<td colspan="10" align="center" height="15%">
						<input id="nextStep" type="button" class="btn" value="下一步(S)" title="Alt+S" accesskey="S">&nbsp;&nbsp;
						<input id="close" type="button" class="btn" value="关闭(C)" title="Alt+C" accesskey="C">&nbsp;&nbsp;
					</td>
				</tr>
</table>

</body>
</html>
