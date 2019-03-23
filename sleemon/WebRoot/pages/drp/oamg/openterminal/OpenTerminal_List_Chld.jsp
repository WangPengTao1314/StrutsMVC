<!--
 * @prjName:喜临门营销平台
 * @fileName: 
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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/openterminal/OpenTerminal_List_Chld.js"></script>
    <script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                	<td nowrap>
					   <c:if test="${pvg.PVG_EDIT eq 1 }">
				   		<input id="edit" type="button" class="btn" value="编辑(E)" title="Alt+E" accesskey="E">
				   	   </c:if>
				   	   <c:if test="${pvg.PVG_DELETE eq 1 }">
				   		<input id="delete" type="button" class="btn" value="删除(G)" title="Alt+G" accesskey="G">
				   	   </c:if>
					   &nbsp;
					</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
	 <div class="lst_area">
		<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
		 <tr>
			<td width="15%" align="right" class="detail_label"> 城市人口： </td>
			<td width="35%" class="detail_content">  ${rst.CITY_POPULATION} </td>
			<td width="15%" align="right" class="detail_label"> 城市GDP： </td>
			<td width="35%" class="detail_content">  ${rst.CITY_GDP}</td>
	    </tr>
	    <tr>
			<td width="15%" align="right" class="detail_label"> 竞品： </td>
			<td width="35%" class="detail_content">  ${commNames.COMMODITIES_NAME} </td>
			<td width="15%" align="right" class="detail_label"> 城市市场数： </td>
			<td width="35%" class="detail_content">  ${rst.CITY_MARKET_NUM}</td>
	    </tr>
	    <tr>
			<td width="15%" align="right" class="detail_label"> 商场排名： </td>
			<td width="35%" class="detail_content">  ${rst.MALL_RANK} </td>
			<td width="15%" align="right" class="detail_label"> 商场名称： </td>
			<td width="35%" class="detail_content">  ${rst.MALL_NAME}</td>
	    </tr>
	    <tr>
			<td width="15%" align="right" class="detail_label"> 商场总面积： </td>
			<td width="35%" class="detail_content">  ${rst.MALL_ALL_AREA} </td>
			<td width="15%" align="right" class="detail_label"> 已有门店数： </td>
			<td width="35%" class="detail_content">  ${rst.TERMINAL_NUM}</td>
	    </tr>
	    <tr>
			<td width="15%" align="right" class="detail_label"> 门店平均销售额： </td>
			<td width="35%" class="detail_content">  ${rst.TERMINAL_SALE_AMOUNT} </td>
			<td width="15%" align="right" class="detail_label"> 计划开店数量： </td>
			<td width="35%" class="detail_content">  ${rst.PLAN_TERMINAL_NUM}</td>
	    </tr>
	    <tr>
			<td width="15%" align="right" class="detail_label"> 计划开店品牌： </td>
			<td width="35%" class="detail_content">  ${rst.PLAN_TERMINAL_BRAND} </td>
			<td width="15%" align="right" class="detail_label"> 门店位置： </td>
			<td width="35%" class="detail_content">  ${rst.TERMINAL_ADDR}</td>
	    </tr>
	    <tr>
			<td width="15%" align="right" class="detail_label"> 门店面积： </td>
			<td width="35%" class="detail_content">  ${rst.TERMINAL_AREA} </td>
			<td width="15%" align="right" class="detail_label"> 计划年度提货额： </td>
			<td width="35%" class="detail_content">  ${rst.PLAN_YEAR_ORDER_AMOUNT}</td>
	    </tr>
	    <tr>
			<td width="15%" align="right" class="detail_label"> 计划年度零售额： </td>
			<td width="35%" class="detail_content">  ${rst.PLAN_RET_AMOUNT} </td>
			<td width="15%" align="right" class="detail_label"> 单店计划安排导购数： </td>
			<td width="35%" class="detail_content">  ${rst.GUIDE_STAFF_NUM}</td>
	    </tr>
	    
	    
	 </table>
	</div>
	</td>
</tr>
</table>
<form id="form1" action="#" method="post" name="listForm">
	<input type="hidden" id="OPEN_TERMINAL_REQ_DTL_IDS" name="OPEN_TERMINAL_REQ_DTL_IDS" value=""/>
</form>
</body>
</html>