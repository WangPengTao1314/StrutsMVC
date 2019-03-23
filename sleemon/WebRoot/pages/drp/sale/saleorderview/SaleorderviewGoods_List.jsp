<!--
 * @prjName:喜临门营销平台
 * @fileName:分销 -订货订单维护
 * @author zzb
 * @time   2013-09-15 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/saleorderview/SaleorderviewGoods_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	  		#mycredit_show{
			margin: 0px auto; 
			width:500px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:140px;
			left:230px;
			top:50px;
			text-align:center;
			display: block;
		}
		.text_underline{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:150px;
		}
		.wenben{
			width: 65px;
			text-align: right;
		}
		.neirong{
			width:150px;
			text-align:left;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
		   
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"> <input type="checkbox" name="allChecked" id="allChecked"/></th>
                    <th  nowrap="nowrap" align="center" dbname="GOODS_ORDER_NO" >订货订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="" >订货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NO" >收货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_AMOUNT" >订货总额</th>
                    <c:if test="${havaAreaSerId ne 0}"><!-- 有区域服务中心的加盟商不使用返利 -->
                     <th  nowrap="nowrap" align="center" dbname="IS_USE_REBATE" >是否使用返利</th>
                    </c:if>
<!--                    <th  nowrap="nowrap" align="center" dbname="ORDER_RECV_DATE" >要求到货日期</th>-->
                    
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
						<input type="checkbox" style="valign:middle" name="eid" id="eid${rr}" value="${rst.GOODS_ORDER_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.GOODS_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORDER_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECV_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="right" name="TOTL_AMOUNT_${rst.GOODS_ORDER_ID}">
                       <c:if test="${empty rst.ORDER_AMOUNT}">0</c:if>
                        <c:if test="${not empty rst.ORDER_AMOUNT}">${rst.ORDER_AMOUNT}</c:if>
                       &nbsp;
                     </td>
                     <c:if test="${havaAreaSerId ne 0}"><!-- 有区域服务中心的加盟商不使用返利 -->
	                     <td nowrap="nowrap" align="center">
	                     <c:if test="${rst.IS_USE_REBATE == 1}">是</c:if>
	                     <c:if test="${rst.IS_USE_REBATE == 0}">否</c:if>
	                       <input type="hidden" id="IS_USE_REBATE${rst.GOODS_ORDER_ID}" name="IS_USE_REBATE"  value="${rst.IS_USE_REBATE}" />
	                       <input type="hidden" id="REBATEDSCT${rst.GOODS_ORDER_ID}" name="REBATEDSCT"  value="${REBATEDSCT}" />
	                       <input type="hidden" id="REBATE_CURRT${rst.GOODS_ORDER_ID}" name="REBATE_CURRT"  value="${REBATE_CURRT-REBATE_FREEZE}" />
	                       
	                       
	                     </td>
	                 </c:if>
<!--                     <td nowrap="nowrap" align="center" id="ORDER_RECV_DATE${rst.GOODS_ORDER_ID}" >${rst.ORDER_RECV_DATE}</td>-->
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center"> ${rst.DEPT_NAME}&nbsp; </td>
                     <td nowrap="nowrap" align="center">
                     ${rst.STATE}&nbsp;
                      <input id="state${rst.GOODS_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
                      <input id="type${rst.GOODS_ORDER_ID}" type="hidden"  value="${rst.BILL_TYPE}" />
                      <input type="hidden"  id="AREA_ID_${rst.GOODS_ORDER_ID}" value="${rst.AREA_ID}"/>
                      <input type="hidden"  id="GOODS_ORDER_NO_${rst.GOODS_ORDER_ID}" value="${rst.GOODS_ORDER_NO}"/>
                     </td>
                   
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
					<input type="hidden" id="SALE_ORDER_ID" value="${params.SALE_ORDER_ID}"/>
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
<input type="hidden" id="selParam" name="selParam" value=" STATE in('启用','停用')"/>
<input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE in('启用','停用') and CHANN_ID='${ZTXXID}' or AREA_SER_ID='${ZTXXID}' "/>
 <input type="hidden" id="selSendChannParam" name="selSendChannParam" value=" STATE in('启用','停用') and CHANN_ID in('${BASE_CHANN_ID}','${AREA_SER_ID}') "/>
<input type="hidden" id="flag" name="flag" value=""/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                  <td width="8%" nowrap align="right" class="detail_label">订货订单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="GOODS_ORDER_NO" name="GOODS_ORDER_NO"  style="width:155" value="${params.GOODS_ORDER_NO}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NO" name="ORDER_CHANN_NO"  style="width:155" value="${params.ORDER_CHANN_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'ORDER_CHANN_ID', 'CHANN_ID', 'forms[1]','ORDER_CHANN_NO,ORDER_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selOrderChannParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="ORDER_CHANN_ID" id="ORDER_CHANN_ID" value="" />
	   				   <input type="text" id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>					
                    			
                     <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					 <td width="15%" class="detail_content">
					     <select id="STATE" name="STATE" style="width:155"  	></select>
					 </td>	
										
               </tr>
               
                <tr>
                    <td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_CHANN_NO" name="RECV_CHANN_NO"  style="width:155" value="${params.RECV_CHANN_NO}"/>
	   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[1]','RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selOrderChannParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="RECV_CHANN_ID" id="RECV_CHANN_ID" value="" />
	   				  <input id="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  style="width:155" value="${params.RECV_CHANN_NAME}"/>
					</td>
					
                    <td width="8%" nowrap align="right" class="detail_label">发货方编号:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="SEND_CHANN_ID" id="SEND_CHANN_ID" value="" />
	   					<input id="SEND_CHANN_NO" name="SEND_CHANN_NO"  style="width:155" value="${params.SEND_CHANN_NO}"/>
	   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'SEND_CHANN_ID', 'CHANN_ID', 'forms[1]','SEND_CHANN_NO,SEND_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selSendChannParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">发货方名称:</td>
					<td width="15%" class="detail_content">
	   				  <input id="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  style="width:155" value="${params.SEND_CHANN_NAME}"/>
					</td>					
								
               </tr>
               <tr>
                <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN" onclick="SelectTime();" size="20" value="${params.CRE_TIME_BEGIN}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_BEGIN);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="CRE_TIME_END" name="CRE_TIME_END" onclick="SelectTime();" size="20" value="${params.CRE_TIME_END}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_END);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">单据类型</td>
					<td width="15%" class="detail_content">
					  <select id="BILL_TYPE" name="BILL_TYPE" style="width: 155px;"></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
				    	<input id="q_reset" type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
 var module = parent.window.document.getElementById("module").value;
 SelDictShow("STATE","BS_44","${params.STATE}","");
 SelDictShow("BILL_TYPE","BS_93","${params.BILL_TYPE}","");
  //初始化 审批流程
 spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
</html>
