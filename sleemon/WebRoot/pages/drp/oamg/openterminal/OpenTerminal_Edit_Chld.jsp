<!--  
/**
 *@module 渠道管理-终端管理
 *@func   新开门店申请单维护
 *@version 1.1
 *@author zcx
*/
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css" />
	<script type=text/javascript src="${ctx}/pages/drp/oamg/openterminal/OpenTerminal_Edit_Chld.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<style type="text/css">
	     .meun{position:absolute;width:auto;height:auto;z-index:1;}
		.meun td{text-align:center;}
	 
	</style>	
	<title></title>
	 
</head>
<body class="bodycss1">

<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
					<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">
				</td>
			</tr>
		</table>
	</div>
	<!--浮动按钮层结束-->
	<!--占位用table，以免显示数据被浮动层挡住-->
	<table width="100%" height="25px">
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
	</table>
	<form method="POST" action="#" id="mainForm" name="mainForm" >
	
	   <input type="hidden" name="selectBRANDParams" id="selectBRANDParams" value=" STATE='启用' and DEL_FLAG=0"/>
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
						<tr>
							<td width="17%" align="right" class="detail_label"> 城市人口：</td>
							<td width="33%" class="detail_content">
							<input type="hidden" json="OPEN_TERMINAL_REQ_DTL_ID" id="OPEN_TERMINAL_REQ_DTL_ID" name="OPEN_TERMINAL_REQ_DTL_ID" value="${rst.OPEN_TERMINAL_REQ_DTL_ID}" />
							<input json="CITY_POPULATION" name="CITY_POPULATION" id="CITY_POPULATION"
							        type="text" label="城市人口"  autocheck="true"  inputtype="string" mustinput="true"
									maxlength="100"   value="${rst.CITY_POPULATION}" />
 
							</td>
							<td width="17%" align="right" class="detail_label"> 城市GDP：</td>
							<td width="33%" class="detail_content">
								<input json="CITY_GDP" id="CITY_GDP" name="CITY_GDP" type="text" label="城市GDP" 
								maxlength="100" autocheck="true"  inputtype="string" mustinput="true" value="${rst.CITY_GDP}" />
							</td>
						</tr>
						<tr>
							<td width="17%" align="right" class="detail_label"> 竞品：</td>
							<td width="33%" class="detail_content" colspan="4">
							  <table id="myTable"  width="33%" border="1" cellpadding="1" cellspacing="1" class="detail3" align="left">
								 <tr class="detail_label">
								   <th  align="center" style="width: 153px;"> 竞品名称 </th>
							       <th align="left"  >
							        &nbsp;  &nbsp; <input style="" class="btn"  onclick="addRow()"  type="button" name="Submit2" value="添加" />
							       </th>
								 </tr>
								 <c:forEach items="${commList}" var="commModel">
								    <tr>
									  <td  class="detail_content" colspan="2">
									    <input type="hidden" json="COMMODITIES_ID" value="${commModel.COMMODITIES_ID}" />
									    <input json="COMMODITIES_NAME" id="" name="COMMODITIES_NAME" 
										type="text" label="竞品"  maxLength="100" inputtype="string" 
										autocheck="true"   value="${commModel.COMMODITIES_NAME}"/>
										
										<input class="btn" onclick="deleteRow(this)"  type="button" name="" value="删除" />
									  </td>
								  </tr>
								 </c:forEach>
								</table>
							</td>
						</tr>
						<tr>
						    <td width="17%" align="right" class="detail_label"> 城市市场数：</td>
							<td width="33%" class="detail_content">
								<input json="CITY_MARKET_NUM" id="CITY_MARKET_NUM" name="CITY_MARKET_NUM" type="text" label="城市市场数"   inputtype="float" autocheck="true"  mustinput="true" value="${rst.CITY_MARKET_NUM}" />
							</td>
							<td width="17%" align="right" class="detail_label"> 商场排名：</td>
							<td width="33%" class="detail_content">
								<input json="MALL_RANK" id="MALL_RANK" name="MALL_RANK" type="text" label="商场排名"  maxLength="32" inputtype="string" autocheck="true"  mustinput="true" value="${rst.MALL_RANK}"/>
							</td>
						</tr>
						<tr>
						    <td width="17%" align="right" class="detail_label"> 商场名称：</td>
							<td width="33%" class="detail_content">
								<input json="MALL_NAME" id="MALL_NAME" name="MALL_NAME" type="text" label="商场名称" maxLength="100"  inputtype="string" autocheck="true"  mustinput="true" value="${rst.MALL_NAME}" />
							</td>
							<td width="17%" align="right" class="detail_label"> 商场总面积：</td>
							<td width="33%" class="detail_content">
								<input json="MALL_ALL_AREA" id="MALL_ALL_AREA" name="MALL_ALL_AREA" type="text" label="商场总面积"  maxLength="11" valueType="8,2" inputtype="float"  autocheck="true" mustinput="true" value="${rst.MALL_ALL_AREA}"/>
							</td>
						</tr> 
						<tr>
						   <td width="17%" align="right" class="detail_label"> 已有门店数：</td>
							<td width="33%" class="detail_content">
								<input json="TERMINAL_NUM" id="TERMINAL_NUM" name="TERMINAL_NUM" type="text" label="已有门店数"   inputtype="float" autocheck="true"   mustinput="true" value="${rst.TERMINAL_NUM}" />
							</td>
							<td width="17%" align="right" class="detail_label"> 门店平均销售额：</td>
							<td width="33%" class="detail_content">
								<input json="TERMINAL_SALE_AMOUNT" id="TERMINAL_SALE_AMOUNT" name="TERMINAL_SALE_AMOUNT" type="text" label="门店平均销售额"  maxLength="11" valueType="8,2" inputtype="float" autocheck="true" mustinput="true" value="${rst.TERMINAL_SALE_AMOUNT}"/>
							</td>
						</tr>
						<tr>
						    <td width="17%" align="right" class="detail_label"> 计划开店数：</td>
							<td width="33%" class="detail_content">
								<input json="PLAN_TERMINAL_NUM" id="PLAN_TERMINAL_NUM" name="PLAN_TERMINAL_NUM" type="text" label="计划开店数"   inputtype="float" autocheck="true"  mustinput="true" value="${rst.PLAN_TERMINAL_NUM}" />
							</td>
							<td width="17%" align="right" class="detail_label"> 计划开店品牌：</td>
							<td width="33%" class="detail_content">
							    <input id="PLAN_TERMINAL_BRAND" name="PLAN_TERMINAL_BRAND" json="PLAN_TERMINAL_BRAND"  label="计划开店品牌"
							    inputtype="string" mustinput="true" autocheck="true"  value="${rst.PLAN_TERMINAL_BRAND}" /> 
								<img align="absmiddle" name="selTERM_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_142', true, 'PLAN_TERMINAL_BRAND', 'BRNAME', 'forms[0]','PLAN_TERMINAL_BRAND', 'BRNAME','selectBRANDParams')">
		      		
							</td>
						</tr>  
						<tr>
						   <td width="17%" align="right" class="detail_label"> 门店位置：</td>
							<td width="33%" class="detail_content">
								<select json="TERMINAL_ADDR" id="TERMINAL_ADDR" name="TERMINAL_ADDR" style="width:155px;"  label="门店位置"  autocheck="true" mustinput="true" inputtype="string"></select>
							</td>
							<td width="17%" align="right" class="detail_label"> 门店面积：</td>
							<td width="33%" class="detail_content">
								<input json="TERMINAL_AREA" id="TERMINAL_AREA" name="TERMINAL_AREA" type="text" label="门店面积"  maxLength="11" valueType="8,2" inputtype="float" autocheck="true" mustinput="true" value="${rst.TERMINAL_AREA}"/>
							</td>
						</tr>
						<tr>
						   <td width="17%" align="right" class="detail_label"> 计划年度提货额：</td>
							<td width="33%" class="detail_content">
								<input json="PLAN_YEAR_ORDER_AMOUNT" id="PLAN_YEAR_ORDER_AMOUNT" name="PLAN_YEAR_ORDER_AMOUNT" type="text" label="计划年度提货额"  valueType="8,2" inputtype="float" autocheck="true"  mustinput="true" value="${rst.PLAN_YEAR_ORDER_AMOUNT}" />
							</td>
							<td width="17%" align="right" class="detail_label"> 计划年度零售额：</td>
							<td width="33%" class="detail_content">
								<input json="PLAN_RET_AMOUNT" id="PLAN_RET_AMOUNT" name="PLAN_RET_AMOUNT" type="text" label="计划年度零售额"  maxLength="11" valueType="8,2" inputtype="float" autocheck="true" mustinput="true" value="${rst.PLAN_RET_AMOUNT}"/>
							</td>
						</tr>
						<tr>
						    <td width="17%" align="right" class="detail_label"> 单店计划安排导购数：</td>
							<td width="33%" class="detail_content">
								<input json="GUIDE_STAFF_NUM" id="GUIDE_STAFF_NUM" name="GUIDE_STAFF_NUM" type="text" label="单店计划安排导购数"   inputtype="float" mustinput="true" autocheck="true"  value="${rst.GUIDE_STAFF_NUM}" />
							</td>
							<td width="17%" align="right" class="detail_label"></td>
							<td width="33%" class="detail_content"> </td>
						</tr>
						
						
					</table>
				</td>
			</tr>
		</table>
	</form>
   </div>
</body>
<script type="text/javascript">
 SelDictShow("TERMINAL_ADDR","BS_86","${rst.TERMINAL_ADDR}","");
</script> 


