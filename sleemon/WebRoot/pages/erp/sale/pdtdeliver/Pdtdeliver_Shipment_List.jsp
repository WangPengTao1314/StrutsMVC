<!--
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 货品发运
 * @author zzb
 * @time   2013-10-12 13:52:19 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/pdtdeliver/Pdtdeliver_Shipment_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>
<body  style="overflow-y:auto;overflow-x:auto;margin:0px">
<table width="99.8%" height="auto" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="50" align="right">
			  <div id="querydiv" class="">
				<form id="queryForm" name="queryForm" method="post" action="#">
				<input type="hidden" id="selParam" name="selParam" value=" STATE='已提交生产' and DEL_FLAG=0 and ORDER_CHANN_ID in ${params.CHANNS_CHARG} "/>
				<input type="hidden" id="selReceiveOrderParam" name="selReceiveOrderParam" value="  STATE ='启用' and CHANN_TYPE !='总部'  "/>
				<input type="hidden" id="selShipParam" name="selShipParam" value="  STATE ='启用'   "/>
				
				<input type="hidden" id="cuur_id" name="cuur_id"  value="" />
				<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
					<tr align="center">
						<td class="detail2">
							<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				               <tr>
				                   <td width="8%" nowrap align="right" class="detail_label">发运单号:</td>
								   <td width="15%" class="detail_content">
								     <input id="DELIVER_ORDER_ID" json="DELIVER_ORDER_ID" name="DELIVER_ORDER_ID" type="hidden" >
								     <input id="DELIVER_ORDER_NO" json="DELIVER_ORDER_NO" name="DELIVER_ORDER_NO" type="text"   value="${param.DELIVER_ORDER_NO}"  >
								     <img align="absmiddle" name="selJGXX" style="cursor: hand"
									  src="${ctx}/styles/${theme}/images/plus/select.gif"
									   onClick="selCommon('BS_68', true, 'DELIVER_ORDER_ID', 'DELIVER_ORDER_ID', 'forms[0]','DELIVER_ORDER_NO', 'DELIVER_ORDER_NO', 'selParam')">
								
								   </td>
								   <td width="8%" nowrap align="right" class="detail_label">单据类型:</td>
									<td width="15%" class="detail_content">
					   					 <select name="BILL_TYPE" id="BILL_TYPE"  style="width: 155"></select>
									</td>
									 
				              <td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>
								<td width="15%" class="detail_content">
							     <input id="RECV_CHANN_ID" name="RECV_CHANN_ID" type="hidden" >
							     <input id="RECV_CHANN_NO" json="RECV_CHANN_NO" name="RECV_CHANN_NO" type="text"   value="${param.RECV_CHANN_NO}"  >
							     <img align="absmiddle" name="selJGXX" style="cursor: hand"
								  src="${ctx}/styles/${theme}/images/plus/select.gif"
								   onClick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[0]','RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selReceiveOrderParam')">
									
							   </td>
							   <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
							   <td width="15%" class="detail_content">
							     <input id="RECV_CHANN_NAME" json="RECV_CHANN_NAME" name="RECV_CHANN_NAME" type="text"   value="${param.RECV_CHANN_NAME}"  >
							   </td> 	
							   
							   <tr>
				                   <td width="8%" nowrap align="right" class="detail_label">预计发货日期从:</td>
								   <td width="15%" class="detail_content">
								     <input type="text"  id="ADVC_SEND_DATE_START" name="ADVC_SEND_DATE_START" value="${params.ADVC_SEND_DATE_START}"  onclick="SelectDate();"  />&nbsp;
								     <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE_START);"/>
								   </td>
				                   <td width="8%" nowrap align="right" class="detail_label">到:</td>
								   <td width="15%" class="detail_content">
								     <input type="text"  id="ADVC_SEND_DATE_END" name="ADVC_SEND_DATE_END" value="${params.ADVC_SEND_DATE_END}"  onclick="SelectDate();"  />&nbsp;
								     <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE_END);"/>
								   </td>
								   <td width="8%" nowrap align="right" class="detail_label">生产基地编号：</td>
								   <td width="15%" class="detail_content"> 
								      <input id="SHIP_POINT_ID" json="SHIP_POINT_ID" name="SHIP_POINT_ID" type="text"   value="${param.SHIP_POINT_ID}"  >
								   </td>
								   <td width="8%" nowrap align="right" class="detail_label">生产基地名称：</td>
								   <td width="15%" class="detail_content"> 
								    <input id="SHIP_POINT_NAME" json="SHIP_POINT_NAME" name="SHIP_POINT_NAME" type="text"   value="${param.SHIP_POINT_NAME}"  >
								    <img align="absmiddle" name="selJGXX" style="cursor: hand"
								  src="${ctx}/styles/${theme}/images/plus/select.gif"
								   onClick="selCommon('BS_22', false, 'SHIP_POINT_ID', 'SHIP_POINT_ID', 'forms[0]','SHIP_POINT_NAME', 'SHIP_POINT_NAME', 'selShipParam')">
								   </td>
				               </tr>
				               
				               <tr>
									<td colspan="10" align="center" valign="middle" class="detail_content" >
										 <input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
									</td>
									 
							   </tr>
							</table>
						</td>
					</tr>
				</table>
				</form> 
				<input type="hidden" id="selRowId" name="selRowId" value=""/>
				<input type="hidden" id="DELIVER_ORDER_NOS" name="DELIVER_ORDER_NOS" value=""/>
				
				</div>
			</td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
		 <form action="">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
                  <td nowrap>
			   		<%--<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">--%>
				</td>
				</tr>
			</table>
			</form>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;" >
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%">
					 <input type="checkbox" style="hvalign:middle" id="allChecked" name="allChecked" />
					</th>
                    <th  nowrap="nowrap" align="center" dbname="DELIVER_ORDER_NO" >发运单号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NO" >收货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NAME" >发货方名称</th><%--
                    <th  nowrap="nowrap" align="center" dbname="AREA_SER_NO" >区域服务中心编号</th>
                    --%><th  nowrap="nowrap" align="center" dbname="AREA_SER_NAME" >区域服务中心名称</th>
                    <th  nowrap="nowrap" align="center" dbname="SHIP_POINT_NAME" >生产基地</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_SEND_DATE" >预计发货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);">
					 <td width="1%"align='center' >
						<input type="checkbox" style="hvalign:middle" name="eid" id="eid${rr}" value="${rst.DELIVER_ORDER_ID}" onclick="setEidChecked(this);"/>
					 </td>
                     <td nowrap="nowrap" align="center"><span CONTENTEDITABLE>${rst.DELIVER_ORDER_NO}&nbsp;</span></td>
                     <td nowrap="nowrap" align="center"><span CONTENTEDITABLE>${rst.BILL_TYPE}</span></td>
                     <td nowrap="nowrap" align="center"><span CONTENTEDITABLE>${rst.RECV_CHANN_NO}&nbsp;</span></td>
                     <td nowrap="nowrap" align="left"><span CONTENTEDITABLE>${rst.RECV_CHANN_NAME}&nbsp;</span></td>
                     <td nowrap="nowrap" align="left"><span CONTENTEDITABLE>${rst.SEND_CHANN_NAME} &nbsp;</span></td>
                     <%--<td nowrap="nowrap" align="center"><span CONTENTEDITABLE>${rst.AREA_SER_NO} &nbsp;</span></td>
                     --%><td nowrap="nowrap" align="center"><span CONTENTEDITABLE>${rst.AREA_SER_NAME} &nbsp;</span></td>
                     <td nowrap="nowrap" align="center"><span CONTENTEDITABLE>${rst.SHIP_POINT_NAME} &nbsp;</span></td>
                     <td nowrap="nowrap" align="center"><span CONTENTEDITABLE>${rst.ADVC_SEND_DATE}&nbsp;</span></td>
                     <td nowrap="nowrap" align="center"><span CONTENTEDITABLE>${rst.STATE}&nbsp;</td>
                     
                     <input id="state${rst.DELIVER_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
                     <input id="BILL_TYPE${rst.DELIVER_ORDER_ID}" type="hidden"  value="${rst.BILL_TYPE}" />
                     <input id="SEND_DATE_DIFF${rst.DELIVER_ORDER_ID}" name="SEND_DATE_DIFF" type="hidden"  value="${rst.SEND_DATE_DIFF}" />
                     <input id="DELIVER_ORDER_NO${rst.DELIVER_ORDER_ID}" name="DELIVER_ORDER_NO" type="hidden"  value="${rst.DELIVER_ORDER_NO}" />
                     <input id="RECV_CHANN_ID${rst.DELIVER_ORDER_ID}" name="RECV_CHANN_ID" type="hidden" label="收货方ID" value="${rst.RECV_CHANN_ID}" />
                     <input id="AREA_SER_ID${rst.DELIVER_ORDER_ID}" name="AREA_SER_ID" type="hidden" label="区域服务中心ID" value="${rst.AREA_SER_ID}" /> 
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
			<table>
			   <c:if test="${not empty page.result}">
				<tr>
					<td height="12" colspan="13" align="center">
			               <table width="100%" height="50%" border="0" cellpadding="0"  cellspacing="0" class="lst_toolbar" >
							<tr>
								<td>
									<form id="pageForm" action="#" method="post" name="listForm">
									    <input type="hidden" id="forWard" name="forWard" value="shipment"/>
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
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
    SelDictShow("BILL_TYPE","BS_77","${params.BILL_TYPE}","");
</script>
</html>
