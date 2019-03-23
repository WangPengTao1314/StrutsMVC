<!--
 * @prjName:喜临门营销平台
 * @fileName:订货订单处理 - 合并提交页面
 * @author zzb
 * @time   2013-08-30 15:55:09 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/goodsorder/Goodsorder_Merge_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body style="overflow-y:auto;overflow-x:auto;margin:0px">
<table width="99.8%" height="auto" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td width="50" align="right">
			 
		<div id="querydiv" class="">
		<form id="queryForm"  name="queryForm"  method="post" action="#">
			<input type="hidden" id="search" name="search" value="true"/>
				<%--<input type="hidden" id="GOODS_ORDER_ID" name="GOODS_ORDER_ID" value="${params.GOODS_ORDER_ID}"/>
				<input type="hidden" id="ORDER_CHANN_ID"    json="ORDER_CHANN_ID"   name="ORDER_CHANN_ID" value="${params.ORDER_CHANN_ID}">
				<input type="hidden" id="ORDER_CHANN_NO"    json="ORDER_CHANN_NO"   name="ORDER_CHANN_NO" value="${params.ORDER_CHANN_NO}">
				<input type="hidden" id="ORDER_CHANN_NAME"  json="ORDER_CHANN_NAME"   name="ORDER_CHANN_NAME" value="${params.ORDER_CHANN_NAME}">
				<input type="hidden" id="BILL_TYPE"         json="BILL_TYPE"   name="BILL_TYPE" value="${params.BILL_TYPE}">
				<input type="hidden" id="RECV_CHANN_ID"     json="RECV_CHANN_ID"   name="RECV_CHANN_ID" value="${params.RECV_CHANN_ID}">
				<input type="hidden" id="RECV_CHANN_NO"     json="RECV_CHANN_NO"   name="RECV_CHANN_NO" value="${params.RECV_CHANN_NO}">
				<input type="hidden" id="RECV_CHANN_NAME"   json="RECV_CHANN_NAME"   name="RECV_CHANN_NAME" value="${params.RECV_CHANN_NAME}">
				<input type="hidden" id="SHIP_POINT_ID"     json="SHIP_POINT_ID" name="SHIP_POINT_ID" value="${params.SHIP_POINT_ID}">
				<input type="hidden" id="AREA_ID"           json="AREA_ID"  name="AREA_ID" value="${params.AREA_ID}">
				
				--%>
				<input type="hidden" id="selReceiveChannParam" name="selReceiveChannParam" value=" STATE in('启用','停用') and CHANN_TYPE !='总部' "/>
					
		     <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			  <tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
		                 <tr>
					        <td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
							<td width="15%" class="detail_content">
			   					<input  type="hidden"  id="ORDER_CHANN_ID" name="ORDER_CHANN_ID"    value="${params.ORDER_CHANN_ID}"/>
			   					<input type="text" id="ORDER_CHANN_NO" name="ORDER_CHANN_NO" label="订货方编号"  autocheck="true" inputtype="string" mustinput="true" value="${params.ORDER_CHANN_NO}"/>
							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_19', false, 'ORDER_CHANN_ID', 'CHANN_ID', 'forms[0]','ORDER_CHANN_NO,ORDER_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selReceiveChannParam')">
							
							</td>					
		                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
							<td width="15%" class="detail_content">
			   				   <input type="text" id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME" label="订货方名称"  autocheck="true" inputtype="string" mustinput="true"   value="${params.ORDER_CHANN_NAME}"/>
							</td>
							
		                    <td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>
							<td width="15%" class="detail_content">
			   					<input  type="hidden"  id="RECV_CHANN_ID" name="RECV_CHANN_ID"    value="${params.RECV_CHANN_ID}"/>
			   					<input type="text" id="RECV_CHANN_NO" name="RECV_CHANN_NO" label="收货方编号"   autocheck="true" inputtype="string" mustinput="true"  value="${params.RECV_CHANN_NO}"/>
							    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[0]','RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selReceiveChannParam')">
							
							</td>					
		                    <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
							<td width="15%" class="detail_content">
			   				   <input type="text" id="RECV_CHANN_NAME" name="RECV_CHANN_NAME" label="收货方名称"   autocheck="true" inputtype="string" mustinput="true"  value="${params.RECV_CHANN_NAME}"/>
							</td>
							</tr>
							
							<tr>
						    <td width="8%" nowrap align="right" class="detail_label">订货日期从:</td>
							<td width="15%" class="detail_content">
			   					<input type="text" id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN" onclick="SelectDate();" size="20" value="${params.CRE_TIME_BEGIN}" >
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CRE_TIME_BEGIN);">
							</td>
							<td width="8%" nowrap align="right" class="detail_label">订货日期到:</td>
							<td width="15%" class="detail_content">
			   					<input type="text" id="CRE_TIME_END" name="CRE_TIME_END" onclick="SelectDate();" size="20" value="${params.CRE_TIME_END}" >
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(CRE_TIME_END);">
							</td>
							<td width="8%" nowrap align="right" class="detail_label"> </td>
							<td width="15%" class="detail_content"> </td>					
		                    <td width="8%" nowrap align="right" class="detail_label"> </td>
							<td width="15%" class="detail_content"> </td>					
		               </tr>
						<tr align="center">
		                  <td nowrap colspan="8" class="detail_content">
						   <c:if test="${pvg.PVG_BWS eq 1 }">
					   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
						   </c:if>
						   <c:if test="${pvg.PVG_RETURN eq 1 }">
						    <input id="return" type="button" class="btn" value="退回(R)" title="Alt+R" accesskey="R">
						   </c:if>
						</td>
						</tr>
					</table>
				</td>
			</tr>
		  </table>
		</form>
		</div>
			</td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
		 
	    <table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
	   </table>
		 
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px; margin-right:13px; moverflow: auto;">
		    <input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用' and LEDGER_ID='${params.LEDGER_ID}' ">
		    <input type="hidden" name="XTYHID" id="XTYHID" value="${XTYHID}">
		    <input type="hidden" name="msg" id="msg" lable="反填的退回原因" value="">
		    <input type="hidden" name="cuur_id" id="cuur_id" lable="当前选择的单据的checkbox的id" value="">
		    
		    <input type="hidden" name="BILL_TYPE_GLOB" id="BILL_TYPE_GLOB" lable="当前选择的单据的单据类型" value="">
		     
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%">
					 	 <input type="checkbox" style="hvalign:middle" id="allChecked" name="allChecked" />
					</th>
					<th  nowrap="nowrap" align="center" dbname="GOODS_ORDER_NO" >订货订单编号</th>
					<th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >订单类型</th>
					<th  nowrap="nowrap" align="center" dbname="ORDER_DATE" >订货日期</th>
					<th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
					<th  nowrap="nowrap" align="center" dbname="TOTL_AMOUNT" >订货总额</th>
					<th  nowrap="nowrap" align="center" dbname="AREA_NAME" >订货所属区域</th>
					<th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NO" >收货方编号</th>
					<th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="SHIP_POINT_NAME" >生产基地名称</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);trClick('${rst.GOODS_ORDER_ID}');">
					 <td width="1%"align='center' >
						<input type="checkbox" style="hvalign:middle" name="eid" id="eid${rr}" value="${rst.GOODS_ORDER_ID}" onclick="setEidChecked(this);"/>
					 </td>
					 <td nowrap="nowrap" align="center">&nbsp;${rst.GOODS_ORDER_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="center">&nbsp;${rst.BILL_TYPE}&nbsp;</td>
					 <td nowrap="nowrap" align="center">&nbsp;${rst.ORDER_DATE}&nbsp;</td>
					 <td nowrap="nowrap" align="center">&nbsp;${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">&nbsp;${rst.ORDER_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="right">&nbsp;${rst.TOTAL_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="left">&nbsp;${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">&nbsp;${rst.RECV_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">&nbsp;${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left"> &nbsp; ${rst.SHIP_POINT_NAME} &nbsp; </td>
                     
                     <input type="hidden" name="" id="GOODS_ORDER_NO${rst.GOODS_ORDER_ID}" value="${rst.GOODS_ORDER_NO}" />
                     
                       <input id="ORDER_CHANN_ID${rst.GOODS_ORDER_ID}"  json="ORDER_CHANN_ID"  name="ORDER_CHANN_ID" label="订货方ID"      type="hidden"  value="${rst.ORDER_CHANN_ID}" />
                       <input id="ORDER_CHANN_NO${rst.GOODS_ORDER_ID}"  json="ORDER_CHANN_NO"  name="ORDER_CHANN_NO" label="订货方编号"      type="hidden"  value="${rst.ORDER_CHANN_NO}" />
                       <input id="ORDER_CHANN_NAME${rst.GOODS_ORDER_ID}"  json="ORDER_CHANN_NAME"  name="ORDER_CHANN_NAME" label="订货方名称"      type="hidden"  value="${rst.ORDER_CHANN_NAME}" />
                       
                       <input id="RECV_CHANN_ID${rst.GOODS_ORDER_ID}"   json="RECV_CHANN_ID"   name="RECV_CHANN_ID"  label="收货方ID"      type="hidden"  value="${rst.RECV_CHANN_ID}" />
                       <input id="RECV_CHANN_NO${rst.GOODS_ORDER_ID}"   json="RECV_CHANN_NO"   name="RECV_CHANN_NO"  label="收货方编号"      type="hidden"  value="${rst.RECV_CHANN_NO}" />
                       <input id="RECV_CHANN_NAME${rst.GOODS_ORDER_ID}"   json="RECV_CHANN_NAME"   name="RECV_CHANN_NAME"  label="收货方名称"      type="hidden"  value="${rst.RECV_CHANN_NAME}" />
                       
                       <input id="BILL_TYPE${rst.GOODS_ORDER_ID}"       json="BILL_TYPE"       name="BILL_TYPE"      label="订单类型"       type="hidden"  value="${rst.BILL_TYPE}" />
                       <input id="AREA_ID${rst.GOODS_ORDER_ID}"         json="AREA_ID"         name="AREA_ID"      label="区域ID"           type="hidden"  value="${rst.AREA_ID}" />
                       <input id="SHIP_POINT_ID${rst.GOODS_ORDER_ID}"   json="SHIP_POINT_ID"   name="SHIP_POINT_ID"  label="生产基地"      type="hidden"  value="${rst.SHIP_POINT_ID}" />
                       <input type="hidden" name="REMARK" id="REMARK${rst.GOODS_ORDER_ID}" value="${rst.REMARK}" /> 
                        
                 
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="15" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		    			<table>
			   <c:if test="${not empty page.result}">
				<tr>
					<td height="12" colspan="13" align="center">
			               <table width="100%" height="50%" border="0" cellpadding="0"  cellspacing="0" class="lst_toolbar" >
							<tr>
								<td>
									<form id="pageForm" action="#" method="post" name="listForm">
									    <input type="hidden" id="forWard" name="forWard" value="select"/>
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
									 ${page.footerHtml}${page.toolbarHtml} &nbsp;&nbsp;&nbsp;&nbsp; 
								</td>
							</tr>
						</table>
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
   
</script>
</html>
