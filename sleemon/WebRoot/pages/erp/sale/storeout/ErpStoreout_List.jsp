﻿<!--
 * @prjName:喜临门营销平台
 * @fileName:Storeout_List
 * @author chenj
 * @time   2013-09-15 14:59:50 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/storeout/ErpStoreout_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<style type="text/css">
	
	   	#edit_remark{
			margin: 0px auto; 
			width:450px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:20px;
			left:230px;
			top:50px;
			text-align:center;
			display: block;
		}
	
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<c:if test="${DRP_FLAG eq 1}">
				<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>发运管理>>出库单</td>
			</c:if>
			<c:if test="${DRP_FLAG eq 0}">
				<td height="20px">&nbsp;&nbsp;当前位置：渠道销售管理>>我的订货订单>>我的总部出库单</td>
			</c:if>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
                  <td nowrap>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_DRP_BWS eq 1}">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
			   		<input id="expdate" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <c:if test="${pvg.PVG_ORDER_CLOSE eq 1}">
				     <input id="close" type="button" class="btn" value="关闭(C)" title="Alt+C" accesskey="C">
				   </c:if>
				</td>
				</tr>
			</table>
		</div>
<!--		<div  style="height:100px;width:1000px;">-->
		<div  style="height:0%;width:0%;display: none;">
			<iframe id='printIframe' name='_hiddenIframe' src='#' width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>
<!--			<iframe id="saveiframe" width="0" height="0" ></iframe>-->
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th  nowrap="nowrap" align="center" dbname="STOREOUT_NO" >出库单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="DELIVER_ORDER_NO" >发运单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="U9_SM_NO" >U9出库单编号</th>
                    <%--<th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NO" >收货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    --%><th  nowrap="nowrap" align="center" dbname="SHIP_POINT_NAME" >发货点名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_TIME" >出库时间</th>
                    <th  nowrap="nowrap" align="center" dbname="TRUCK_NO" >车牌号</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STOREOUT_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.STOREOUT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.DELIVER_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.U9_SM_NO}</td>
                     <%--<td nowrap="nowrap" align="left">${rst.RECV_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     --%><td nowrap="nowrap" align="left">${rst.SHIP_POINT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STOREOUT_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.TRUCK_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.STOREOUT_ID}" type="hidden"  value="${rst.STATE}" />
                    <input id="REMARK${rst.STOREOUT_ID}" type="hidden"  value="${rst.REMARK}" />
                    <input id="U9_SM_NO${rst.STOREOUT_ID}" type="hidden"  value="${rst.U9_SM_NO}" />
                    <input id="DELIVER_ORDER_ID${rst.STOREOUT_ID}" type="hidden"  value="${rst.DELIVER_ORDER_ID}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="17" align="center" class="lst_empty">
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
</table>
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<c:if test="${DRP_FLAG eq 1}">
	<input type="hidden" id="selParam" name="selParam" value=" STATE in('已发货','待收货','已收货') and DEL_FLAG=0 and ORDER_CHANN_ID in ${params.CHANNS_CHARG} "/>
	<input type="hidden" id="selStoreoutParam" name="selStoreoutParam" value=" DEL_FLAG=0 and RECV_CHANN_ID in ${params.CHANNS_CHARG} "/>
</c:if>
<c:if test="${DRP_FLAG eq 0}">
	<input type="hidden" id="selParam" name="selParam" value=" STATE in('已发货','待收货','已收货') and DEL_FLAG=0 and ORDER_CHANN_ID = '${params.CHANN_ID}' "/>
	<input type="hidden" id="selStoreoutParam" name="selStoreoutParam" value=" DEL_FLAG=0 and RECV_CHANN_ID= '${params.CHANN_ID}'"/>
</c:if>

  <input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE in('启用','停用')  and CHANN_TYPE !='总部' "/>
  
  <input type="hidden" id="BILL_TYPE" name="BILL_TYPE" value="${params.BILL_TYPE}">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="8%" nowrap align="right" class="detail_label">出库单编号:</td>
					<td width="15%" class="detail_content">
						<input id="STOREOUT_ID" name="STOREOUT_ID" type="hidden" style="width:155" value="${params.STOREOUT_ID}"/>
	   					<input id="STOREOUT_NO" name="STOREOUT_NO"  style="width:155" value="${params.STOREOUT_NO}"/>
	   					<input id="DRP_FLAG" name="DRP_FLAG" value="${DRP_FLAG}" type="hidden">
	   					<img align="absmiddle" name="selSTOREOUT_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_118', false, 'STOREOUT_ID', 'STOREOUT_ID', 'forms[1]','STOREOUT_NO', 'STOREOUT_NO', 'selStoreoutParam')">

					</td>	
                    <td width="8%" nowrap align="right" class="detail_label">发运单号:</td>
				    <td width="15%" class="detail_content">
				      <input id="DELIVER_ORDER_ID" json="DELIVER_ORDER_ID" name="DELIVER_ORDER_ID" type="hidden" >
				      <input id="DELIVER_ORDER_NO" json="DELIVER_ORDER_NO" name="DELIVER_ORDER_NO" type="text"   value="${param.DELIVER_ORDER_NO}"  >
				     <img align="absmiddle" name="selJGXX" style="cursor: hand"
					  src="${ctx}/styles/${theme}/images/plus/select.gif"
					   onClick="selCommon('BS_68', false, 'DELIVER_ORDER_ID', 'DELIVER_ORDER_ID', 'forms[1]','DELIVER_ORDER_NO', 'DELIVER_ORDER_NO', 'selParam')">
				
				    </td>			
                    <td width="8%" nowrap align="right" class="detail_label">U9出库单编号:</td>
				    <td width="15%" class="detail_content">
  				 	<input  type="text" id="U9_SM_NO" name="U9_SM_NO"    value="${params.U9_SM_NO}"/>
			        </td>
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE"  style="width:155"></select>
					</td>			
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_CHANN_NO" name="RECV_CHANN_NO"  style="width:155" <c:if test="${DRP_FLAG eq 0}"> readonly </c:if>  value="${params.RECV_CHANN_NO}"/>
	   					<c:if test="${DRP_FLAG eq 1}">
						    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
								onClick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[1]','RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selOrderChannParam')">
						</c:if>
					</td>					
	                   <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="RECV_CHANN_ID" id="RECV_CHANN_ID" value="" />
	   				   <input type="text" id="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  <c:if test="${DRP_FLAG eq 0}"> readonly </c:if>   value="${params.RECV_CHANN_NAME}"/>
					</td>
                     
                    <td width="8%" nowrap align="right" class="detail_label">发货点名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="SHIP_POINT_NAME" name="SHIP_POINT_NAME"  value="${params.SHIP_POINT_NAME}"/>
					</td>	
					<td width="8%" nowrap align="right" class="detail_label">销售订单编号：</td>
					<td width="15%" class="detail_content">
					   <input id="SALE_ORDER_NO" name="SALE_ORDER_NO"  value="${params.SALE_ORDER_NO}"/>
					</td>				
               </tr>
               <tr>
					<%--<td width="8%" nowrap align="right" class="detail_label">交货日期从:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_RECV_DATE_BEG" name="ORDER_RECV_DATE_BEG" readonly="readonly"   onclick="SelectDate();" style="width:155" value="${params.ORDER_RECV_DATE_BEG}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(ORDER_RECV_DATE_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">交货日期到:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_RECV_DATE_END" name="ORDER_RECV_DATE_END" readonly="readonly"   onclick="SelectDate();"  style="width:155" value="${params.ORDER_RECV_DATE_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(ORDER_RECV_DATE_END);">
					</td>  
                
					--%><td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_BEGIN }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_BEGIN);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.CRE_TIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_END);">
					</td> 
					<td width="8%" nowrap align="right" class="detail_label">出库日期从：</td>
					<td width="15%" class="detail_content">
						<input id="STOREOUT_TIME_BEG" name="STOREOUT_TIME_BEG" readonly="readonly"   onclick="SelectDate();"  style="width:155" value="${params.STOREOUT_TIME_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(STOREOUT_TIME_BEG);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">出库日期到：</td>
					<td width="15%" class="detail_content">
						<input id="STOREOUT_TIME_END" name="STOREOUT_TIME_END" readonly="readonly"   onclick="SelectDate();"  style="width:155" value="${params.STOREOUT_TIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(STOREOUT_TIME_END);">
					</td>		 
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<script type="text/javascript">
SelDictShow("STATE","BS_85","${params.STATE}","");
</script>
</html>
