<!-- 
 *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *  
 -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>货品列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/sale/forecasttaskup/ForecastTaskup_prd_List.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="/sleemon/scripts/core/keyboard_ctrl.js"></script>
		<style type="text/css">
	  .query_div_1{
		display:none;
		width:100%;
		background-color:#e0edf6;
		filter:alpha(opacity=90);
		position:absolute;
		left:0;
		top:20;
		right:100%;
		bottom:100%;
	}
	
	</style>
</head>
<body onload = "load()">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;预计量上报管理&gt;&gt;货品填报</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20">
	    <center><font size="5" color="black">${YEAR}年${MONTH}月预计量</font></center>
	    <br>
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
				<td>
				<form id="queryForm" method="post">
				 <input type="hidden" name="selectParamsT" value=" STATE='启用' and FINAL_NODE_FLAG=1 and DEL_FLAG=0 ">
				 <input type="hidden" id="RPT_JOB_CHANN_ID" name="RPT_JOB_CHANN_ID" value="${RPT_JOB_CHANN_ID}"/>
				 <input type="hidden" id="count" name="count" value="${count}" />
 				 <input type="hidden" id="ADVC_RPT_JOB_ID" name="ADVC_RPT_JOB_ID" value="${ADVC_RPT_JOB_ID}"/>
				货品编号： <input id="PRD_NO" name="PRD_NO" type="text" value="${param.PRD_NO}"/>
				 <img align="absmiddle" name="" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
							  onClick="selCommon('BS_21', false, 'PRD_NO', 'PRD_NO', 'forms[0]','PRD_NO,PRD_NAME', 'PRD_NO,PRD_NAME', 'selectParamsT')">
				货品名称： <input id="PRD_NAME" name="PRD_NAME" type="text" value="${param.PRD_NAME}"/>
				规格： <input id="PRD_SPEC" name="PRD_SPEC" type="text" value="${param.PRD_SPEC}"/>
				<input id="add" type="button" class="btn" value="查询"  onclick="queryPrd();"/>
				</form>
				</td>
				<td>
			   		<input id="add" type="button" class="btn" value="保存" onclick="save();">
			   	</td>
			   	<td></td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center" dbname="PRD_NO" >货品编号</th>
					<th nowrap align="center" dbname="PRD_NAME" >货品名称</th>
					<th nowrap align="center" dbname="PRD_SPEC" >规格</th>
					<th nowrap align="center" dbname="" >现有库存</th>
					<th nowrap align="center" dbname="" >在途库存</th>
					<th nowrap align="center" dbname="" >本年已收货数量</th>
					<th nowrap align="center" dbname="" >上月预计量</th>
					<th nowrap align="center" dbname="" >上月实际订货量</th>
					<th nowrap align="center" dbname="" >本月预计订货量</th>
					<th nowrap align="center" dbname="" >供货价</th>
					<th nowrap align="center" dbname="" >预计订货金额</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" name="list_row${rr}">
						<td align="center">${rst.PRD_NO}<input type="hidden"  name="PRD_ID" json="PRD_ID" value="${rst.PRD_ID}"/> </td>
						<td align="center">${rst.PRD_NAME}</td>
						<td align="center">${rst.PRD_SPEC}</td>
						<td align="center">${rst.STORE_NUM}</td>
						<td align="center">${rst.WAY_NUM} </td>
						<td align="center">${rst.NOTICE_NUM}</td>
						<td align="center">${rst.LAST_ADVC_RPT_NUM}</td>
				        <td align="center" name="333">${rst.LAST_ORDER_NUM}</td>
						<td align="center" name="111">
						 <INPUT type="text" name="ADVC_RPT_NUM" id="ADVC_RPT_NUM${rr}"
						 json="ADVC_RPT_NUM" value="${rst.ADVC_RPT_NUM}" 
						 size="5" onfocus="setRowAndCol('${rr}',0)" 
						 onkeyup="countAmount(this,'${rst.PRVD_PRICE}');" onblur="getTotal()"></INPUT>
						</td>
						<td align="center" name="222">${rst.PRVD_PRICE}</td>
						<td align="center"  >
						 <input type="text" name="ADVC_RPT_AMOUNT" json="ADVC_RPT_AMOUNT" id="ADVC_RPT_AMOUNT${rr}" value="${rst.ADVC_RPT_AMOUNT}" size="5" readonly class="readonly"/>
						 <input type="hidden"   json="PRD_NO" value="${rst.PRD_NO}"/> 
						 <input type="hidden"   json="PRD_NAME" value="${rst.PRD_NAME}"/> 
						 <input type="hidden"   json="PRD_SPEC" value="${rst.PRD_SPEC}"/> 
						  <input type="hidden"   json="PRVD_PRICE" value="${rst.PRVD_PRICE}"/> 
						 <input type="hidden"   json="ADVC_RPT_CHANN_DTL_ID" value="${rst.ADVC_RPT_CHANN_DTL_ID}"/> 
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="12" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
				</c:if>
			 
				<tr>
				   <td colspan="8" align="center">
				        合计
				   </td>
				   <td  align="center">
				      <input id="TOTAL_ADVC_RPT_NUM" name="TOTAL_ADVC_RPT_NUM" json="TOTAL_ADVC_RPT_NUM" READONLY size="5" DISABLED/>
				   </td>
				   <td>
				     &nbsp;
				   </td>
				   <td align="center"> 
				     <input id="TOTAL_ADVC_RPT_AMOUNT" name="TOTAL_ADVC_RPT_AMOUNT" json="TOTAL_ADVC_RPT_AMOUNT" READONLY size="5" DISABLED/>
				   </td>
				</tr> 
			</table>
		</div>
	</td>
</tr>
</table>
</body>
</html>