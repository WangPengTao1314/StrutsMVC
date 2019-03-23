<!--
 * @prjName:喜临门营销平台
 * @fileName:订货订单处理
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/sale/sergoodsorder/Sergoodsorder_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：区域服务中心管理>>订单中心>>订货订单处理</td>
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
				  
				   <c:if test="${pvg.PVG_RETURN eq 1 }">
				    <input id="return" type="button" class="btn" value="退回(R)" title="Alt+R" accesskey="R">
				   </c:if><%--
				   <c:if test="${pvg.PVG_TO_DISPOSED eq 1 }">
			   		<input id="disposed" type="button" class="btn" value="处理(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   --%><c:if test="${pvg.PVG_TO_DISPOSED eq 1 }">
			   		 <input id="commit" type="button" class="btn" value="提交(C)" title="Alt+C" accesskey="C">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				    <!-- 
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="save" type="button" class="btn" value="保存(S)" title="Alt+F" accesskey="F">
				   </c:if>
				  
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="产能情况(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   
				  <input id="personal" type="button" class="btn" value="个性化设置" >--> 
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
		<form action="">
		    <input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用' and LEDGER_ID='${params.LEDGER_ID}' ">
		    <input type="hidden" name="XTYHID" id="XTYHID" value="${XTYHID}">
		    <input type="hidden" name="JGXXID" id="JGXXID" value="${JGXXID}">
		    <input type="hidden" name="BMXXID" id="BMXXID" value="${BMXXID}">
			<input type="hidden" name="msg" id="msg" lable="反填的退回原因" value="">
			
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th  nowrap="nowrap" align="center" dbname="GOODS_ORDER_NO" >订货订单编号</th>
					<th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
					<th  nowrap="nowrap" align="center" dbname="ORDER_DATE" >订货日期</th>
					<th  nowrap="nowrap" align="center" dbname="TOTAL_AMOUNT" >订货总额</th>
					<%if("true".equals(Consts.IS_OLD_FLOW)){%>
					<th  nowrap="nowrap" align="center" dbname="IS_USE_REBATE" >是否使用返利</th>
					<%} %>
<!--					<th  nowrap="nowrap" align="center" dbname="ORDER_RECV_DATE" >要求到货日期</th>-->
					<th  nowrap="nowrap" align="center" dbname="AREA_NAME" >订货所属区域</th>
                    <th  nowrap="nowrap" align="center" dbname="PERSON_CON" >联系人</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_ADDR" >联系方式</th>
                    <th  nowrap="nowrap" align="center" dbname="SHIP_POINT_NAME" >生产基地名称</th>
                    <th  nowrap="nowrap" align="center" dbname="AUDIT_NAME" >处理人</th>
                    <th  nowrap="nowrap" align="center" dbname="AUDIT_TIME" >处理时间</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.GOODS_ORDER_ID}"/>
					 </td>
					 
					 <td nowrap="nowrap" align="center" id="NO_${rst.GOODS_ORDER_ID}">${rst.GOODS_ORDER_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORDER_CHANN_NAME}&nbsp;</td>
					 <td nowrap="nowrap" align="center">${rst.ORDER_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" id="TOTAL_AMOUNT${rst.GOODS_ORDER_ID}">${rst.TOTAL_AMOUNT}&nbsp;</td>
                     <%if("true".equals(Consts.IS_OLD_FLOW)){%>
                     <td nowrap="nowrap" align="center">
                      <c:if test="${rst.IS_USE_REBATE eq 1}">
                                                                       是
                      </c:if>
                      <c:if test="${rst.IS_USE_REBATE ne 1}">
                                                                       否
                      </c:if>
                     </td>
                     <%} %>
<!--                     <td nowrap="nowrap" align="center" >${rst.ORDER_RECV_DATE}</td>-->
                     <td nowrap="nowrap" align="left">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PERSON_CON}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TEL}&nbsp;</td> 
                     <td nowrap="nowrap" align="left">
                     <input id="SHIP_POINT_ID${rr}" json="SHIP_POINT_ID" name="SHIP_POINT_ID" type="hidden" seltarget="selSHIP"  value="${rst.SHIP_POINT_ID}" onblur="">
                      ${rst.SHIP_POINT_NAME}
                     </td>
                     <td nowrap="nowrap" align="left">${rst.AUDIT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AUDIT_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STATE}&nbsp; 
                       <input id="state${rst.GOODS_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
                       <input id="CREATOR${rst.GOODS_ORDER_ID}" type="hidden"  value="${rst.CREATOR}" />
                       <input id="ORDER_CHANN_ID${rst.GOODS_ORDER_ID}" type="hidden"  value="${rst.ORDER_CHANN_ID}" />
                       <input id="GOODS_ORDER_NO${rst.GOODS_ORDER_ID}" type="hidden"  value="${rst.GOODS_ORDER_NO}" />
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
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<input type="hidden" id="selChannParam" name="selChannParam" value=" STATE in('启用','停用') and AREA_SER_ID='${LEDGER_ID}' "/>
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
							onClick="selCommon('BS_19', false, 'ORDER_CHANN_ID', 'CHANN_ID', 'forms[2]','ORDER_CHANN_NO,ORDER_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selChannParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="ORDER_CHANN_ID" id="ORDER_CHANN_ID" value="" />
	   				   <input type="text" id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>					
                   					
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
					    <select id="STATE" name="STATE" style="width:155"></select>
					</td>					
               </tr>
               
                 <tr>
                    <td width="8%" nowrap align="right" class="detail_label">区域编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_NO" name="AREA_NO"  style="width:155" value="${params.AREA_NO}"/>
	   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[2]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selAREAParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="AREA_ID" id="AREA_ID" value="" />
	   				  <input id="AREA_NAME" name="AREA_NAME"  style="width:155" value="${params.AREA_NAME}"/>
					
					</td>					
                   					
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
               </tr>
               
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input id="q_rest" type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
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
  SelDictShow("STATE","BS_47","${params.STATE}","");
</script>
</html>
