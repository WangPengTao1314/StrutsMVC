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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/saleorderview/Saleorderview_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	  		#mycredit_show{
			margin: 0px auto; 
			width:500px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:140px;
			left:230px;
			top:50px;
			text-align:center;
			display: block;
		}
		.text_underline{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:150px;
		}
		.wenben{
			width: 65px;
			text-align: right;
		}
		.neirong{
			width:150px;
			text-align:left;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>我的订货订单>>我的总部订单</td>
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
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
			   		<input id="expdate" type="button" class="btn" value="导出(X)" title="Alt+X" accesskey="X">
			   		<input id="queryProStatus" type="button" class="btn" value="查看生产状态(Q)" title="Alt+Q" accesskey="Q">
				   </c:if>
				   <c:if test="${havaAreaSerId ne 0}"><!-- 有区域服务中心的加盟商不使用返利 -->
					   <input id="mycredit" type="button" class="btn" value="我的信用(M)" title="Alt+M" accesskey="M" onclick="showPage();"/>
					   <input id="tempCredit" type="button" class="btn" value="临时信用申请(H)" title="Alt+H" accesskey="H" />
				   </c:if>
				   <div id="mycredit_show" style="display: none;">
				    	<h4 align="center" style="margin-top: 10px">我的信用</h4>
				    	<table>
				    		<tr>
				    			<td class="wenben" > 
				    				渠道编号:
				    			</td>
				    			<td class="neirong">
				    				<input class="text_underline" readonly="readonly" value="${rst.CHANN_NO}"/>
				    			</td>
				    			<td class="wenben">
				    				渠道名称:
				    			</td>
				    			<td class="neirong">
				    				<input class="text_underline" readonly="readonly" value="${rst.CHANN_NAME}"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="wenben">
				    				可用信用额:
				    			</td>
				    			<td class="neirong">
				    				<%if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){%>
					    			   <input class="text_underline" readonly="readonly" 
					    			   value="${rst.INI_CREDIT+userCredit+rst.TEMP_CREDIT_REQ-rst.FREEZE_CREDIT}"/>
					    			<%}else{%>
					    			   <input class="text_underline" readonly="readonly" value="${rst.CREDIT_CURRT+rst.TEMP_CREDIT_REQ+rst.REBATE_CURRT-rst.FREEZE_CREDIT}"/>
					    			<%} %>
				    			</td>
				    			<td class="wenben">
				    				临时信用额:
				    			</td>
				    			<td>
				    				<input class="text_underline" readonly="readonly" value="${rst.TEMP_CREDIT + rst.PAYMENT_CREDIT}"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="wenben">
				    				可用返利额:
				    			</td>
				    			<td class="neirong">
				    				<input class="text_underline" readonly="readonly"
				    					<c:if test="${rst.REBATE_CURRT==null||rst.REBATE_CURRT==''}">value="0"</c:if>value="${rst.REBATE_CURRT}" 
				    				/>
				    			</td>
				    			<td>
				    				&nbsp;
				    			</td>
				    			<td>
				    				&nbsp;
				    			</td>
				    		</tr>
				    	</table>
				    	<input style="margin-top: 10px" id="close" class="btn" type="button" value="关闭" onclick="closePage();"/>
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
		<form action="">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >订单编号</th>
					<th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >订单类型</th>
					<th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_DATE" >订货日期</th>
					<th  nowrap="nowrap" align="center" dbname="TOTL_AMOUNT" >订货总额</th>
					<th  nowrap="nowrap" align="center" dbname="AREA_NAME" >订货区域</th>
                    <th  nowrap="nowrap" align="center" dbname="PERSON_CON" >联系人</th>
                    <th  nowrap="nowrap" align="center" dbname="TEL" >联系方式</th>
                    <th  nowrap="nowrap" align="center" dbname="SHIP_POINT_NAME" >生产基地名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.SALE_ORDER_ID}"/>
					 </td>
					 <td nowrap="nowrap" align="center"><a href="#" onclick="orpenWindow('${rst.SALE_ORDER_ID}')">${rst.SALE_ORDER_NO}&nbsp;</a></td>
					 <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORDER_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.TOTL_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PERSON_CON}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.SHIP_POINT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     <input type="hidden" id="saleNo${rst.SALE_ORDER_ID}" value="${rst.SALE_ORDER_NO}"/>
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
<input type="hidden" id="selAREAParam" name="selAREAParam" value=" STATE in('启用','停用')"/>
<input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE in('启用','停用') and CHANN_TYPE !='总部' "/>

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                  	<td width="8%" nowrap align="right" class="detail_label">销售订单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="SALE_ORDER_NO" name="SALE_ORDER_NO"  style="width:155" value="${params.SALE_ORDER_NO}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
					    <select id="STATE" name="STATE" style="width:155"></select>
					</td>
<!--					<td width="8%" nowrap align="right" class="detail_label">区域编号:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="AREA_NO" name="AREA_NO"  style="width:155" value="${params.AREA_NO}"/>-->
<!--	   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       -->
<!--							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[2]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selAREAParam')">-->
<!--					</td>					-->
<!--                    <td width="8%" nowrap align="right" class="detail_label">区域名称:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--					   <input type="hidden" name="AREA_ID" id="AREA_ID" value="" />-->
<!--	   				  <input id="AREA_NAME" name="AREA_NAME"  style="width:155" value="${params.AREA_NAME}"/>-->
<!--					</td>-->
					 <td width="8%" nowrap align="right" class="detail_label">订货日期从:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="ORDER_DATE_BEGIN" name="ORDER_DATE_BEGIN" onclick="SelectDate();" size="20" value="${params.ORDER_DATE_BEGIN}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_DATE_BEGIN);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">订货日期到:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="ORDER_DATE_END" name="ORDER_DATE_END" onclick="SelectDate();" size="20" value="${params.ORDER_DATE_END}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ORDER_DATE_END);">
					</td>
               </tr>
               <td width="8%" nowrap align="right" class="detail_label">
				   <input type="checkbox" value="1" id="IS_CANCELED_FLAG" 
				     <c:if test="${params.IS_CANCELED_FLAG eq 1}">checked="checked"</c:if>
				    name="IS_CANCELED_FLAG" />
				</td>
				
				<td width="15%" class="detail_content">
				  显示已取消
				</td>
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
  SelDictShow("STATE","BS_69","${params.STATE}","");
</script>
</html>
