<!--
 * @prjName:喜临门营销平台
 * @fileName:Dstrorder_List
 * @author glw
 * @time   2013-08-16 10:31:37 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/dstrorder/Dstrorder_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		#audit{
			margin: 0px auto; 
			width:600px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:160px;
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
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;区域服务&gt;&gt;分发指令接收</td>
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
				   <c:if test="${pvg.PVG_RICV eq 1 }">
			   			<input id="start" type="button" class="btn" value="接收(Q)" title="Alt+Q" accesskey="Q">	
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   			<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   <div id="audit">
				   		<form action="#">
				   		<table width="100%">
				   			<input type="hidden" name="storeSel" value=" BEL_CHANN_ID='${LEDGER_ID}' and STATE='启用' and DEL_FLAG=0 " />
				   			<tr height="50px;">
				   				<td width="20%" align="right">
				   					<span style="font-size:14px;" >出库库房编号:</span>
				   				</td>
				   				<td width="30%" align="left">
				   					<input type="hidden" id="STOREOUT_STORE_ID" name="STOREOUT_STORE_ID"  style="width: 120px"  readonly/>
				   					<input type="text" id="STOREOUT_STORE_NO" name="STOREOUT_STORE_NO" style="width: 120px"  readonly/>
				   					<input type="hidden" id="STORAGE_FLAG" name="STORAGE_FLAG" />
				   					<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_35', false, 'STOREOUT_STORE_ID','STORE_ID', 'forms[0]','STOREOUT_STORE_ID,STOREOUT_STORE_NO,STOREOUT_STORE_NAME,STORAGE_FLAG','STORE_ID,STORE_NO,STORE_NAME,STORAGE_FLAG', 'storeSel')"
									>
				   				</td>
				   				<td width="20%" align="right">
				   					<span style="font-size: 14px;" >出库库房名称:</span>
				   				</td>
				   				<td width="30%" align="left">
				   					<input type="text" id="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME"  style="width: 120px"  readonly/>
				   				</td>
				   			</tr>
				   			<tr height="50px;">
				   				<td  align="right" colspan="2"><input class="btn" id="moveAdv" type="button" value="确定"/></td>
				   				<td  align="left"><input id="close" class="btn" type="button" value="关闭"/></td>
				   			</tr>
				   		</table>
				   		</form>
				   </div>
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="DSTR_ORDER_NO" >分发指令接收编号</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NO" >发货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NAME" >发货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NO" >收货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_PSON_NAME" >接收人</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_TIME" >接收时间</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.DSTR_ORDER_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.DSTR_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SEND_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.SEND_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORDER_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECV_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECV_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.DSTR_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="12" align="center" class="lst_empty">
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
<form id="queryForm" method="post" action="#" name="queryForm">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<input type="hidden" name="selectParams" value="  DEL_FLAG=0 ">
	<input type="hidden" name="selectParam" value=" STATE in ('启用','停用') and  DEL_FLAG=0 ">
	<input type="hidden" name="FromSel" value="  DEL_FLAG=0 ">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">分发指令接收编号:</td>
					<td width="15%" class="detail_content">
	   					<input json="DSTR_ORDER_NO" id="DSTR_ORDER_NO" name="DSTR_ORDER_NO"  style="width:155" value="${params.DSTR_ORDER_NO}"/>
		      			<input json="DSTR_ORDER_ID" name="DSTR_ORDER_ID" id="DSTR_ORDER_ID" type="hidden" label="分发指令接收ID">
						<img align="absmiddle" name="selDSTR_ORDER_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('WL_295', false, 'DSTR_ORDER_NO', 'DSTR_ORDER_NO','queryForm',
												 'DSTR_ORDER_NO',
												 'DSTR_ORDER_NO','selectParams')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">来源单据编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="FROM_BILL_NO" name="FROM_BILL_NO"  style="width:155" value="${params.FROM_BILL_NO}"/>
	   					<img align="absmiddle" name="selDSTR_ORDER_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_75', false, 'FROM_BILL_NO', 'DELIVER_ORDER_NO','queryForm',
												 'FROM_BILL_NO',
												 'DELIVER_ORDER_NO','FromSel')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">发货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="SEND_CHANN_NO" name="SEND_CHANN_NO"  style="width:155" value="${params.SEND_CHANN_NO}"/>
	   					<img align="absmiddle" name="selDSTR_ORDER_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'SEND_CHANN_NO', 'CHANN_NO','queryForm',
												 'SEND_CHANN_NO,SEND_CHANN_NAME',
												 'CHANN_NO,CHANN_NAME','selectParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">发货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  style="width:155" value="${params.SEND_CHANN_NAME}"/>
					</td>					
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NO" name="ORDER_CHANN_NO"  style="width:155" value="${params.ORDER_CHANN_NO}"/>
	   					<img align="absmiddle" name="selDSTR_ORDER_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'ORDER_CHANN_NO', 'CHANN_NO','queryForm',
												 'ORDER_CHANN_NO,ORDER_CHANN_NAME',
												 'CHANN_NO,CHANN_NAME','selectParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_CHANN_NO" name="RECV_CHANN_NO"  style="width:155" value="${params.RECV_CHANN_NO}"/>
	   					<img align="absmiddle" name="selDSTR_ORDER_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'RECV_CHANN_NO', 'CHANN_NO','queryForm',
												 'RECV_CHANN_NO,RECV_CHANN_NAME',
												 'CHANN_NO,CHANN_NAME','selectParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  style="width:155" value="${params.RECV_CHANN_NAME}"/>
					</td>					
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">接收人名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_PSON_NAME" name="RECV_PSON_NAME"  style="width:155" value="${params.RECV_PSON_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">接收时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_TIME_BEG" name="RECV_TIME_BEG" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.RECV_TIME_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(RECV_TIME_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">接收时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_TIME_END" name="RECV_TIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.RECV_TIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(RECV_TIME_END);">
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select name="STATE" id="STATE" style="width:155" ></select>
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
</html>
<script type="text/javascript">
	SelDictShow("STATE", "190", "${params.STATE}", "");
</script>