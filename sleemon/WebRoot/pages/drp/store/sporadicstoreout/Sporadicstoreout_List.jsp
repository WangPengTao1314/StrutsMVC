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
	<script type="text/javascript" src="${ctx}/pages/drp/store/sporadicstoreout/Sporadicstoreout_List.js"></script>
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
			<td height="20px">&nbsp;&nbsp;当前位置：库存管理>>出库管理>>零星出库处理</td>
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
			   		<input id="modifyRemark" type="button" class="btn" value="修改(M)" title="Alt+M" accesskey="M">
				   </c:if>
				   <c:if test="${pvg.PVG_SRETURN eq 1 }">
				   		<input id="returnStore" type="button" class="btn" value="反出库(R)" title="Alt+R" accesskey="R">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   <c:if test="${pvg.PVG_CLOSE eq 1 }">
			   		<input id="closeDocument" type="button" class="btn" value="关闭(C)" title="Alt+F" accesskey="F">
				   </c:if>
				　　<input id="print" type="button" class="btn" value="打印(P)" title="Alt+P" accesskey="P">
				   <div id="edit_remark" style="display: none;">
				    	<h4 align="center" style="margin-top: 10px">填写备注</h4>
				    	<table style="margin-left: 2px;margin-top: -10px;">
<!--				    		<tr>-->
<!--				    			<td class="" > -->
<!--				    				出库时间:-->
<!--				    			</td>-->
<!--				    			<td class="">-->
<!--				    				<input type="text" json="STOREOUT_TIME" id="STOREOUT_TIME" name="STOREOUT_TIME" autocheck="true" inputtype="string" label="出库时间"  value="${rst.STOREOUT_TIME}" onclick="SelectTime();" readonly />-->
<!--									<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(STOREOUT_TIME);">-->
<!--				    			</td>-->
<!--				    		</tr>-->
				    		<tr>
				    			<td class="" > 
				    				备注:
				    			</td>
				    			<td class="">
				    			  <textarea id="REMARK" name="REMARK" inputtype="string"  oVal="" onkeyup="changeTextAreaLength(this);"
				    			   autocheck="true" maxlength="250" rows="4"
				    			    cols="40"></textarea>
				    			</td>
				    		</tr>
				    		 
				    	</table>
				    	<input style="margin-top: 10px;margin-right: 10px;" id="confirm" class="btn" type="button" value="确定" onclick="modifyRemark();"/>
				    	<input style="margin-top: 10px" id="close" class="btn" type="button" value="关闭" onclick="closePage();"/>
				   </div>
				   
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
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据号</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_STORE_NAME" >出库库房名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
                    <th  nowrap="nowrap" align="center" dbname="PRINT_NUM" >打印次数</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);childType('${rst.STORAGE_FLAG}');setSelEid(document.getElementById('eid${rr}'));setStoreId('${rst.STOREOUT_STORE_ID}')">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STOREOUT_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.STOREOUT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STOREOUT_STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PRINT_NUM}&nbsp;</td>
                    <input id="state${rst.STOREOUT_ID}" type="hidden"  value="${rst.STATE}" />
                    <input id="REMARK${rst.STOREOUT_ID}" type="hidden"  value="${rst.REMARK}" />
                    <input id="Time${rst.STOREOUT_ID}" type="hidden"  value="${rst.STOREOUT_TIME}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="16" align="center" class="lst_empty">
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
  <input id="selStoreoutParam" name="selectParams" type="hidden" value=" DEL_FLAG=0 and BILL_TYPE = '${params.BILL_TYPE}' and LEDGER_ID='${params.LEDGER_ID}' "/>
  <input id="selectParams" name="selectParams" type="hidden" value=" STATE in( '启用','停用') and DEL_FLAG=0"/>
  <input id="selectParam" name="selectParam" type="hidden" value=" STATE in( '启用','停用') and DEL_FLAG=0 and BEL_CHANN_ID = '${params.LEDGER_ID}'"/>
  <input id="termParam" name="termParam" type="hidden" value=" STATE in( '启用','停用') and DEL_FLAG=0 and CHANN_ID_P='${params.LEDGER_ID}'"/>
  <input type="hidden" name=selectPrd value="STATE in ('启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1">
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
	   					<img align="absmiddle" name="selSTOREOUT_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_52', false, 'STOREOUT_ID', 'STOREOUT_ID', 'forms[1]','STOREOUT_ID,STOREOUT_NO,FROM_BILL_NO,TERM_NO,TERM_NAME', 'STOREOUT_ID,STOREOUT_NO,FROM_BILL_NO,TERM_NO,TERM_NAME', 'selStoreoutParam')">

					</td>	
                    <td width="8%" nowrap align="right" class="detail_label">来源单据号:</td>
					<td width="15%" class="detail_content">
	   					<input id="FROM_BILL_NO" name="FROM_BILL_NO"  style="width:155" value="${params.FROM_BILL_NO}"/>
					</td>				
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
					<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE"  style="width:155"></select>
					</td>
               </tr>
               <tr>
                    
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
					<td width="8%" nowrap align="right" class="detail_label">货品编号：</td>
					<td width="15%" class="detail_content"> 
						<input id="PRD_ID" name="PRD_ID"  type="hidden" value="${params.PRD_ID}"/>
	   					<input id="PRD_NO" name="PRD_NO"  style="width:155" value="${params.PRD_NO}"/>
	   					<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_21', true, 'PRD_ID', 'PRD_ID', 'forms[1]','PRD_ID,PRD_NO,PRD_NAME', 'PRD_ID,PRD_NO,PRD_NAME', 'selectPrd')">
					</td>	
					<td width="8%" nowrap align="right" class="detail_label">货品名称：</td>
					<td width="15%" class="detail_content">
						<input id="PRD_NAME" name="PRD_NAME"  style="width:155" value="${params.PRD_NAME}"/> 
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"> </td>						
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
