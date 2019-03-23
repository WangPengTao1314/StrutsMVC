﻿<!--
 * @prjName:喜临门营销平台
 * @fileName:Storediff_List
 * @author wzg
 * @time   2013-08-30 14:03:21 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/storediff/Storediff_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：库存管理&gt;&gt;入库管理&gt;&gt;入库差异确认</td>
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
				   <c:if test="${pvg.PVG_COMMIT eq 1 }">
			   		<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+C" accesskey="C">
				   </c:if>
				   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   <c:if test="${pvg.PVG_OVER eq 1 }">
			   		<input id="over" type="button" class="btn" value="完成(O)" title="Alt+O" accesskey="O">
				   </c:if>
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
                    <th  nowrap="nowrap" align="center" dbname="STOREIN_DIFF_NO" >入库差异单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NAME" >发货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREIN_STORE_NAME" >入库库房名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="ORG_NAME" >制单机构</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STOREIN_DIFF_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.STOREIN_DIFF_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.SEND_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STOREIN_STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORG_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.STOREIN_DIFF_ID}" type="hidden"  value="${rst.STATE}" />
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
<form id="queryForm" name="queryForm" method="post" action="#">
	 <!-- 0157309 --start--刘曰刚--2014-06-23 -->
     <!-- 修改入库库房查询条件，过滤增加只查询渠道库房，按当前登陆人所属渠道过滤库房 -->
	 <input id="selectSTORECondition" name="selectSTORECondition" type="hidden" value=" STATE in ( '启用','停用' ) and TERM_STROE_FLAG=0 and BEL_CHANN_ID='${params.LEDGER_ID}' "/>
   <input id="selectCondition" name="selectCondition" type="hidden" value=" STATE in ( '启用','停用' ) "/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
	
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">入库差异单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREIN_DIFF_NO" name="STOREIN_DIFF_NO"  style="width:155" value="${params.STOREIN_DIFF_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">单据类型:</td>
					<td width="15%" class="detail_content">
	   					<select id="BILL_TYPE" name="BILL_TYPE"  style="width:155"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">来源单据编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="FROM_BILL_NO" name="FROM_BILL_NO"  style="width:155" value="${params.FROM_BILL_NO}"/>
					</td>	
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE"  style="width:155"></select>
					</td>				
               </tr>
               <tr>
               <!-- 0157309 --start--刘曰刚--2014-06-23 -->
               <!-- 入库差异页面，收货方只会是总部，所有收货方信息查询去掉 -->
<!--               		<td width="8%" nowrap align="right" class="detail_label">发货方编号:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--						<input id="SEND_CHANN_ID" type="hidden" name="SEND_CHANN_ID"  style="width:155" value="${params.RSEND_CHANN_ID}"/>-->
<!--	   					<input id="SEND_CHANN_NO" name="SEND_CHANN_NO"  style="width:155" value="${params.SEND_CHANN_NO}"/>-->
<!--	   					<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" -->
<!--				     		onclick="selCommon('BS_19', false, 'SEND_CHANN_ID', 'CHANN_ID', 'queryForm','SEND_CHANN_NO,SEND_CHANN_NAME', 'CHANN_NO,CHANN_NAME','selectCondition');"/>&nbsp;-->
<!--					</td>-->
<!--                    <td width="8%" nowrap align="right" class="detail_label">发货方名称:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  style="width:155" value="${params.SEND_CHANN_NAME}"/>-->
<!--					</td>					-->
					<!-- 0157309 --End--刘曰刚--2014-06-23 -->
                    <td width="8%" nowrap align="right" class="detail_label">入库库房编号:</td>
					<td width="15%" class="detail_content">
						<input id="STOREIN_STORE_ID" type="hidden" name="STOREIN_STORE_ID"  style="width:155" value="${params.STOREIN_STORE_ID}"/>
	   					<input id="STOREIN_STORE_NO" name="STOREIN_STORE_NO"  style="width:155" value="${params.STOREIN_STORE_NO}"/>
	   					<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				         onclick="selCommon('BS_35', false, 'STOREIN_STORE_ID', 'STORE_ID', 'queryForm','STOREIN_STORE_NO,STOREIN_STORE_NAME', 'STORE_NO,STORE_NAME','selectSTORECondition');"/>&nbsp;
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">入库库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREIN_STORE_NAME" name="STOREIN_STORE_NAME"  style="width:155" value="${params.STOREIN_STORE_NAME}"/>
					</td>					
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
	SelDictShow("STATE","BS_28","${params.STATE}","");
	SelDictShow("BILL_TYPE","BS_29","${params.BILL_TYPE}","");
</script>