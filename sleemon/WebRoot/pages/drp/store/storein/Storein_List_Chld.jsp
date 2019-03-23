﻿<!--
 * @prjName:喜临门营销平台
 * @fileName:Storein_List
 * @author glw
 * @time   2013-08-19 16:55:43 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/storein/Storein_List_Chld.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr id="childedit">
	<td height="20">
		<div class="tablayer" >
		 <form id="addFrom" action="#" method="post" name="addFrom">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
				   		<c:if test="${pvg.PVG_EDIT eq 1 }">
						 	<input id="selectParams" name="selectParams" type="hidden" value=" STATE in ('启用','停用')  and  FINAL_NODE_FLAG='1'  and (COMM_FLAG=1 or ( COMM_FLAG=0 and LEDGER_ID='${LEDGER_ID}')) "/>
						 	<input json="PRD_ID" id="PRD_ID" name="PRD_ID" type="hidden" value=""/>
						 	<input json="" id="PRD_NO" name="PRD_NO" type="hidden" value=""/>
						 	<input json="" id="PRD_NAME" name="PRD_NAME" type="hidden" value=""/>
						 	<input json="" id="PRD_SPEC" name="PRD_SPEC" type="hidden" value=""/>
						 	<input json="" id="PRD_COLOR" name="PRD_COLOR" type="hidden" value=""/>
						 	<input json="" id="BRAND" name="BRAND" type="hidden" value=""/>
						 	<input json="" id="STD_UNIT" name="STD_UNIT" type="hidden" value=""/>
						 	<input json="" id="PRVD_PRICE" name="PRVD_PRICE" type="hidden" value=""/>
					   		<input json="" id="add" type="button" class="btn" value="新增货品(A)"  title="Alt+A" accesskey="A"
					   		 onClick="selCommon('BS_21', false, 'PRD_ID', 'PRD_ID','addFrom',
									'PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRVD_PRICE',
								    'PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRVD_PRICE','selectParams');addStorein()">
				   	   </c:if>
<!--				   	   <c:if test="${pvg.PVG_EDIT eq 1 }">-->
<!--				   			<input id="edit" type="button" class="btn" value="填写实收数量(E)" title="Alt+E" accesskey="E">-->
<!--				   	   </c:if>-->
				   	   <c:if test="${pvg.PVG_EDIT eq 1 }">
				   			<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
				   	   </c:if>
				   	   <input id="scan" type="button" class="btn" value="&nbsp;&nbsp;&nbsp;扫码(S)&nbsp;&nbsp;&nbsp;" title="Alt+S" accesskey="S">
					</td>
					<td align="right">
				 	总数量:<span style="margin-right: 20px;" id="totalNum"></span>
				 	总金额：<span style="margin-right: 20px;" id="totalAmount"></span>
				    </td>
				</tr>
			</table>
		  </form>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
					<th  nowrap="nowrap" align="center" dbname="GOODS_ORDER_NO" >订货订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号&nbsp;-&nbsp;特殊工艺编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="" >特殊规格说明</th>
                    <th  nowrap="nowrap" align="center" dbname="NOTICE_NUM" >通知入库数量</th>
                    <th  nowrap="nowrap" align="center" dbname="REAL_NUM" >实际入库数量</th>
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr id="checked${rr}" class="list_row${r}" onmouseover="mover(this)" value="${rst.STOREIN_DTL_ID}"  onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}'));setSelOperateEx();">
						 <td width="1%"align='center' >
							<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" value="${rst.STOREIN_DTL_ID}" onclick="setEidChecked(this);"/>
						 </td>
						 <td nowrap="nowrap" align="center" >${rst.GOODS_ORDER_NO}&nbsp;</td>
	                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}
	                     <c:if test="${rst.DM_SPCL_TECH_NO ne null}">-${rst.DM_SPCL_TECH_NO}</c:if>
	                     &nbsp;</td>
	                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
	                     <td nowrap="nowrap" align="left" >${rst.PRD_SPEC}&nbsp;</td>
	                     <td nowrap="nowrap" align="left" >${rst.PRD_COLOR}&nbsp;</td>
	                     <td nowrap="nowrap" align="left" >${rst.BRAND}&nbsp;</td>
	                     <td nowrap="nowrap" align="center" >${rst.STD_UNIT}&nbsp;</td>
	                     <td nowrap="nowrap" align="center" >
	                     	 <c:if test="${rst.SPCL_TECH_FLAG ge 1}">
	                     	 	<c:if test="${!empty rst.SPCL_SPEC_REMARK }">
		                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
		                     	</c:if>
	                     	 	<input type="button" class="btn" value="查看" onclick="url('${rst.SPCL_TECH_ID}')" />
	                     	 </c:if>
	                     	 <c:if test="${rst.SPCL_TECH_FLAG eq '0' || rst.SPCL_TECH_FLAG eq null}">
	                     	 	无
	                     	 </c:if>
	                     	&nbsp;
	                     </td>
	                     <td nowrap="nowrap" align="right" name="NOTICE_NUM" >${rst.NOTICE_NUM}&nbsp;</td>
	                     <td nowrap="nowrap" align="right" id="realNum${rr}">${rst.REAL_NUM}</td>
	                     <td nowrap="nowrap" align="left" > ${rst.REMARK}&nbsp; </td>
	                     <input name="delFlag" type="hidden" value="${rst.INS_FLAG}"/>
	                     <input name="DECT_AMOUNT" type="hidden" value="${rst.DECT_AMOUNT}"/>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
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
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="STOREIN_DTL_IDS" name="STOREIN_DTL_IDS" value=""/>
	<input type="hidden" id="STOREIN_ID" name="STOREIN_ID" value=""/>
</form>
</body>
</html>