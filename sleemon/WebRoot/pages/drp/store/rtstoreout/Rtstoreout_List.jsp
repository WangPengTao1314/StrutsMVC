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
	<script type="text/javascript" src="${ctx}/pages/drp/store/rtstoreout/Rtstoreout_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：库存管理>>出库管理>>退货出库处理</td>
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
                   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
			   		<input id="done" type="button" class="btn" value="出库处理(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   <c:if test="${pvg.PVG_TRETURN eq 1 }">
				   		<input id="returnStore" type="button" class="btn" value="反出库(R)" title="Alt+R" accesskey="R">
				   </c:if>
				   <input id="print" type="button" class="btn" value="打印(P)" title="Alt+P" accesskey="P">
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
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
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th  nowrap="nowrap" align="center" dbname="STOREOUT_NO" >出库单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据号</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NAME" >发货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_STORE_NAME" >出库库房名称</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="STORAGE_FLAG" >库位管理标记</th>-->
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);childType('${rst.STORAGE_FLAG}');setSelEid(document.getElementById('eid${rr}'));setStoreId('${rst.STOREOUT_STORE_ID}');">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STOREOUT_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.STOREOUT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.SEND_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STOREOUT_STORE_NAME}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center">-->
<!--                     <c:if test="${rst.STORAGE_FLAG eq 0}">-->
<!--                     	否&nbsp;-->
<!--                     </c:if>-->
<!--                     <c:if test="${rst.STORAGE_FLAG eq 1}" >-->
<!--                     	是&nbsp;-->
<!--                     </c:if>-->
<!--                     </td>-->
                     <td nowrap="nowrap" align="left">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.STOREOUT_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="14" align="center" class="lst_empty">
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
 <input id="selStoreoutParam" name="selStoreoutParam" type="hidden" value=" DEL_FLAG=0 and BILL_TYPE = '${params.BILL_TYPE}' and LEDGER_ID='${ZTXXID}' "/>
  <input id="selectParams" name="selectParams" type="hidden" value=" STATE in( '启用','停用') and DEL_FLAG=0"/>
  <input id="selectParam" name="selectParam" type="hidden" value=" STATE in( '启用','停用') and DEL_FLAG=0 and BEL_CHANN_ID = '${params.LEDGER_ID}'"/>
  <input id="selectOrderChannParam" name="selectOrderChannParam" type="hidden" value=" STATE in( '启用','停用') and DEL_FLAG=0 and CHANN_ID in('${AREA_SER_ID}','${BASE_CHANN_ID}') "/>
  
  
<input type="hidden" id="BILL_TYPE" name="BILL_TYPE" value="${params.BILL_TYPE}">
<input type="hidden" id="TERM_NO" name="TERM_NO" value="${params.TERM_NO}">
<input type="hidden" id="TERM_NAME" name="TERM_NAME" value="${params.TERM_NAME}">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="8%" nowrap align="right" class="detail_label">出库单编号:</td>
					<td width="15%" class="detail_content">
						<input id="STOREOUT_ID" name="STOREOUT_ID" type="hidden" style="width:155" value="${params.STOREOUT_ID}"/>
	   					<input id="STOREOUT_NO" name="STOREOUT_NO"  style="width:155" value="${params.STOREOUT_NO}"/>
	   					<img align="absmiddle" name="selSTOREOUT_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_98', false, 'STOREOUT_ID', 'STOREOUT_ID', 'forms[1]','STOREOUT_ID,STOREOUT_NO,FROM_BILL_NO', 'STOREOUT_ID,STOREOUT_NO,FROM_BILL_NO', 'selStoreoutParam')">

					</td>	
                    <td width="8%" nowrap align="right" class="detail_label">来源单据号:</td>
					<td width="15%" class="detail_content">
	   					<input id="FROM_BILL_NO" name="FROM_BILL_NO"  style="width:155" value="${params.FROM_BILL_NO}"/>
					</td>				
                    <td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>
					<td width="15%" class="detail_content">
						<input id="RECV_CHANN_ID" name="RECV_CHANN_ID" type="hidden" style="width:155" value="${params.RECV_CHANN_ID}"/>
	   					<input id="RECV_CHANN_NO" name="RECV_CHANN_NO"  style="width:155" value="${params.RECV_CHANN_NO}"/>
	   					<img align="absmiddle" name="selCHANN_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[1]','RECV_CHANN_ID,RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectOrderChannParam')">

					</td>	
                    <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  style="width:155" value="${params.RECV_CHANN_NAME}"/>
					</td>					
               </tr>
               <tr>		
                    <td width="8%" nowrap align="right" class="detail_label">出库库房编号:</td>
					<td width="15%" class="detail_content">
						<input id="STOREOUT_STORE_ID" name="STOREOUT_STORE_ID"  type="hidden" value="${params.STOREOUT_STORE_ID}"/>
	   					<input id="STOREOUT_STORE_NO" name="STOREOUT_STORE_NO"  style="width:155" value="${params.STOREOUT_STORE_NO}"/>
	   					<img align="absmiddle" name="selSTOREOUT_STORE_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_35', false, 'STOREOUT_STORE_ID', 'STORE_ID','forms[1]',
												 'STOREOUT_STORE_NO,STOREOUT_STORE_NAME', 'STORE_NO,STORE_NAME','selectParam')">
					</td>	
                    <td width="8%" nowrap align="right" class="detail_label">出库库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME"  style="width:155" value="${params.STOREOUT_STORE_NAME}"/>
					</td>	
<!--					<td width="8%" nowrap align="right" class="detail_label">库位管理标记:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="STORAGE_FLAG_BEG1" name="STORAGE_FLAG"  type="radio" style="width:30" value="1"  -->
<!--	   						<c:if test="${params.STORAGE_FLAG eq 1 or empty params.STORAGE_FLAG}"> checked="checked" </c:if> -->
<!--	   						-->
<!--	   					/>是-->
<!--	   					<input id="STORAGE_FLAG_BEG2" name="STORAGE_FLAG"  type="radio" style="width:30" value="0" <c:if test="${params.STORAGE_FLAG eq 0}"> checked="checked" </c:if>/>否-->
<!--					</td>	-->
					<td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_BEG" name="CRE_TIME_BEG" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.CRE_TIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_END);">
					</td>    			
               </tr>
               <tr>
					      	
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE"  style="width:155"></select>
					</td>	
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>	
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
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
SelDictShow("STATE","76","${params.STATE}","");
</script>