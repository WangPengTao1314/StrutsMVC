<!--
 * @prjName:喜临门营销平台
 * @fileName:Goodsorder_List
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
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type=text/javascript src="${ctx}/pages/erp/sale/goodsorder/Goodsorder_Itself_List.js"></script>
	
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>订单中心>>订货订单处理</td>
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
				 
				   <c:if test="${pvg.PVG_TO_DISPOSED eq 1 }">
			   		<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+C" accesskey="C">
				   </c:if>
				   <c:if test="${pvg.PVG_RETURN eq 1 }">
				    <input id="return" type="button" class="btn" value="退回(R)" title="Alt+R" accesskey="R">
				   </c:if>
				   <c:if test="${pvg.PVG_TO_DISPOSED eq 1 }">
			   		<input id="back" type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">
				   </c:if>
				   
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
		    <input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用' and LEDGER_ID='${LEDGER_ID}' ">
			<input type="hidden" name="XTYHID" id="XTYHID" value="${XTYHID}">
		    <input type="hidden" name="JGXXID" id="JGXXID" value="${JGXXID}">
		    <input type="hidden" name="BMXXID" id="BMXXID" value="${BMXXID}">
		    <input type="hidden" name="msg" id="msg" value="">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th  nowrap="nowrap" align="center" dbname="GOODS_ORDER_NO" >订货订单编号</th>
					<th  nowrap="nowrap" align="center" dbname="ORDER_DATE" >订货日期</th>
					<th  nowrap="nowrap" align="center" dbname="TOTL_AMOUNT" >订货总额</th>
					<th  nowrap="nowrap" align="center" dbname="IS_USE_REBATE" >是否使用返利</th>
					<th  nowrap="nowrap" align="center" dbname="AREA_NAME" >订货区域</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PERSON_CON" >联系人</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_ADDR" >联系方式</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >生产基地名称</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NO" >处理人</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NAME" >处理时间</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
					 
					<tr class="list_row" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid'));">
					 <td width="1%"align='center' >
						 <input type="radio" style="height:12px;valign:middle" name="eid" id="eid" value="${rst.GOODS_ORDER_ID}"/>
					     
					     <input type="hidden" name="GOODS_ORDER_ID" id="GOODS_ORDER_ID" json="GOODS_ORDER_ID" value="${rst.GOODS_ORDER_ID}"/>
					     <input type="hidden" name="GOODS_ORDER_NO" id="GOODS_ORDER_NO" json="GOODS_ORDER_NO" value="${rst.GOODS_ORDER_NO}"/>
						 <input type="hidden" name="ORDER_CHANN_ID" id="ORDER_CHANN_ID" json="ORDER_CHANN_ID" value="${rst.ORDER_CHANN_ID}"/>
						 <input type="hidden" name="ORDER_CHANN_NO" id="ORDER_CHANN_NO" json="ORDER_CHANN_NO" value="${rst.ORDER_CHANN_NO}"/>
						 <input type="hidden" name="ORDER_CHANN_NAME" id="ORDER_CHANN_NAME" json="ORDER_CHANN_NAME" value="${rst.ORDER_CHANN_NAME}"/>
						 <input type="hidden" name="SEND_CHANN_ID" id="SEND_CHANN_ID" json="SEND_CHANN_ID" value="${rst.SEND_CHANN_ID}"/>
						 <input type="hidden" name="SEND_CHANN_NO" id="SEND_CHANN_NO" json="SEND_CHANN_NO" value="${rst.SEND_CHANN_NO}"/>
						 <input type="hidden" name="SEND_CHANN_NAME" id="SEND_CHANN_NAME" json="SEND_CHANN_NAME" value="${rst.SEND_CHANN_NAME}"/>
						 <input type="hidden" name="RECV_CHANN_ID" id="RECV_CHANN_ID" json="RECV_CHANN_ID" value="${rst.RECV_CHANN_ID}"/>
						 <input type="hidden" name="RECV_CHANN_NO" id="RECV_CHANN_NO" json="RECV_CHANN_NO" value="${rst.RECV_CHANN_NO}"/>
						 <input type="hidden" name="RECV_CHANN_NAME" id="RECV_CHANN_NAME" json="RECV_CHANN_NAME" value="${rst.RECV_CHANN_NAME}"/>
						 <input type="hidden" name="RECV_ADDR" id="RECV_ADDR" json="RECV_ADDR" value="${rst.RECV_ADDR}"/>
					     <input type="hidden" name="TEL" id="TEL" json="TEL" value="${rst.TEL}"/>
					     <input type="hidden" name="IS_USE_REBATE" id="IS_USE_REBATE" json="IS_USE_REBATE" value="${rst.IS_USE_REBATE}"/>
					     <input type="hidden" name="PERSON_CON" id="PERSON_CON" json="PERSON_CON" value="${rst.PERSON_CON}"/>
					   
					     <input type="hidden" name="SHIP_POINT_ID" id="SHIP_POINT_ID" json="SHIP_POINT_ID" value="${rst.SHIP_POINT_ID}"/>
					     <input type="hidden" name="SHIP_POINT_NAME" id="SHIP_POINT_NAME" json="SHIP_POINT_NAME" value="${rst.SHIP_POINT_NAME}"/>
					     <input type="hidden" name="ORDER_DATE" id="ORDER_DATE" json="ORDER_DATE" value="${rst.ORDER_DATE}"/>
					     <input type="hidden" name="AREA_ID" id="AREA_ID" json="AREA_ID" value="${rst.AREA_ID}"/>
					     <input type="hidden" name="AREA_NO" id="AREA_NO" json="AREA_NO" value="${rst.AREA_NO}"/>
					     <input type="hidden" name="AREA_NAME" id="AREA_NAME" json="AREA_NAME" value="${rst.AREA_NAME}"/>
					 </td>
					
					 
					 <td nowrap="nowrap" align="center" id="NO_${rst.GOODS_ORDER_ID}" >${rst.GOODS_ORDER_NO}&nbsp;</td>
					 <td nowrap="nowrap" align="center" id="" >${rst.ORDER_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" id="TOTAL_AMOUNT">${rst.TOTAL_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
                      <c:if test="${rst.IS_USE_REBATE eq 1}">
                                                                       是
                      </c:if>
                      <c:if test="${rst.IS_USE_REBATE ne 1}">
                                                                       否
                      </c:if>
                      &nbsp;
                     </td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORDER_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PERSON_CON}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TEL}&nbsp;</td> 
                     <td nowrap="nowrap" align="left">
                     <input id="SHIP_POINT_ID" json="SHIP_POINT_ID" name="SHIP_POINT_ID" type="hidden" seltarget="selSHIP"  value="${rst.SHIP_POINT_ID}">
					<!--   <input id="SHIP_POINT_NAME" json="SHIP_POINT_NAME"  name="SHIP_POINT_NAME" type="text" inputtype="string"
							seltarget="selSHIP" autocheck="true" mustinput="true" maxlength="32" value="${rst.SHIP_POINT_NAME}" READONLY>
					 <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_44', false, 'SHIP_POINT_ID', 'SHIP_POINT_ID', 'forms[0]','SHIP_POINT_NAME', 'SHIP_POINT_NAME', 'selectParams')">
                     -->
                     
                     ${rst.SHIP_POINT_NAME}
                     </td>
                     <td nowrap="nowrap" align="left">&nbsp;</td>
                     <td nowrap="nowrap" align="left">&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     
                    <input id="state${rst.GOODS_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				 
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
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NO" name="ORDER_CHANN_NO"  style="width:155" value="${params.ORDER_CHANN_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货订单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="GOODS_ORDER_NO" name="GOODS_ORDER_NO"  style="width:155" value="${params.GOODS_ORDER_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<input id="STATE" name="STATE"  style="width:155" value="${params.STATE}"/>
					</td>					
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
<script type="text/javascript">
function gobacknew()
{
   newGoBack("goodsorder.shtml?action=toFrame");
}
</script>