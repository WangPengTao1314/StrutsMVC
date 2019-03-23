<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/deliveryhd/Deliveryhd_Edit_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		.text_underline{
			border-bottom-width:1px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			outline:medium;
			width:70px;
		}
		#freezeDiv{
			margin: 0px auto; 
			width:600px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:160px;
			left:auto;
			text-align:center;
			display: none;
			left: 30%;
			top: 37%;
		}
	</style>
	<title>新增出货计划</title>
</head>
<body class="bodycss1">
<!-- 删除标记:在该页面有删除操作后，返回时刷新页面。否则直接返回上一级，不刷新 -->
<input id="delFlag" type="hidden" value="false"/>
<h2 align="center">新增出货计划</h2>
<form id="queryForm" method="post" action="#">
	<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail" id="tabView">
		<tr>
			<td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3" >
					<input type="hidden" name="selectCHANN" value=" STATE in( '启用','停用') and DEL_FLAG='0' and CHANN_ID in ${params.CHANNS_CHARG} "/>
					<input type="hidden" name="selectParamsPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=1 and COMM_FLAG = 1">
					<input type="hidden" name="selectParamsParPro" value=" STATE in( '启用','停用') and DEL_FLAG='0' and FINAL_NODE_FLAG=0 ">
					<input type="hidden" id="selectAddrParams" name=selectAddrParams value="DEL_FLAG=0 and STATE='启用' and CHANN_ID in ${params.CHANNS_CHARG} ">
					<input type="hidden" name="selectShip" id="selectShip" value="STATE in( '启用','停用') and DEL_FLAG='0' ">
					<tr>
						<td width="8%" nowrap align="right" class="detail_label">发货基地：</td>
						<td width="15%" class="detail_content" >
							<input type="hidden" id="SHIP_POINT_ID" name="SHIP_POINT_ID" value="${params.SHIP_POINT_ID}" />
							<input id="SHIP_POINT_NAME" name="SHIP_POINT_NAME"  json="SHIP_POINT_NAME" type="text" value="${params.SHIP_POINT_NAME}" mustinput="true"  inputtype="string" autocheck="true" label="发货基地" readonly >
							<img align="absmiddle" name="selJGXX" style="cursor:hand" 
			   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
			   						onclick="selCommon('BS_22', false,  'SHIP_POINT_ID', 'SHIP_POINT_ID','forms[0]','SHIP_POINT_ID,SHIP_POINT_NAME','SHIP_POINT_ID,SHIP_POINT_NAME','selectShip');queryList();"/>
						</td>
						<td width="8%" nowrap align="right" class="detail_label">收货地址：</td>
						<td width="15%" class="detail_content" >
							<input id="RECV_ADDR" name="RECV_ADDR"  value="${params.RECV_ADDR}"/>
							<input type="hidden" id="RECV_ADDR_NO" json="RECV_ADDR_NO" name="RECV_ADDR_NO"  value=""/>
			                <img align="absmiddle" name="selAddr" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
			                onclick="selCommon('BS_76', true, 'RECV_ADDR_NO', 'DELIVER_ADDR_NO', 'forms[0]','RECV_ADDR_NO,RECV_ADDR', 'DELIVER_ADDR_NO,DELIVER_DTL_ADDR', 'selectAddrParams');">
						</td>
						
						<td width="8%" nowrap align="right" class="detail_label">客户编号：</td>
						<td width="15%" class="detail_content">
							<input json="ORDER_CHANN_ID" name="ORDER_CHANN_ID" autocheck="true" label="客户ID" type="hidden" inputtype="string"   value="${params.ORDER_CHANN_ID}"/>
							<input json="ORDER_CHANN_NO" name="ORDER_CHANN_NO" id="ORDER_CHANN_NO"  autocheck="true" label="客户编号"  type="text"  inputtype="string"  maxlength="30"  value="${params.ORDER_CHANN_NO}"/>
							<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', true, 'ORDER_CHANN_ID', 'CHANN_ID', 'forms[0]','ORDER_CHANN_ID,ORDER_CHANN_NO,ORDER_CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectCHANN')"> 
						</td>					
						<td width="8%" nowrap align="right" class="detail_label">客户名称：</td>
						<td width="15%" class="detail_content">
							<input id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"   style="width:155"  inputtype="string" autocheck="true" label="客户名称" value="${params.ORDER_CHANN_NAME}"/>
						</td>
					</tr>
					<tr>
						<td width="8%" nowrap align="right" class="detail_label">订货订单编号：</td>
						<td width="15%" class="detail_content">
							<input id="GOODS_ORDER_NO" name="GOODS_ORDER_NO"  style="width:155" value="${params.GOODS_ORDER_NO}" />
						</td>
						<td width="8%" nowrap align="right" class="detail_label">销售订单编号：</td>
						<td width="15%" class="detail_content">
							<input id="SALE_ORDER_NO" name="SALE_ORDER_NO"  style="width:155" value="${params.SALE_ORDER_NO}" />
						</td>           				
						<td width="8%" nowrap align="right" class="detail_label">货品分类编号：</td>
						<td width="15%" class="detail_content">
							<input id="PAR_PRD_ID"    name="PAR_PRD_ID"   json="PAR_PRD_ID" type="hidden"  value="${params.PAR_PRD_ID}">
			   				<input id="PAR_PRD_NO" name="PAR_PRD_NO" type="text"  value="${params.PAR_PRD_NO}"/>
			   				<img align="absmiddle" name="selJGXX" style="cursor:hand" 
		   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
		   						onclick="selCommon('BS_21', true, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]','PAR_PRD_NO,PAR_PRD_NAME','PRD_NO,PRD_NAME','selectParamsParPro');"/>
						</td>
						<td width="8%" nowrap align="right" class="detail_label">货品分类名称：</td>
						<td width="15%" class="detail_content">
							<input id="PAR_PRD_NAME" name="PAR_PRD_NAME" type="text"  value="${params.PAR_PRD_NAME}"/>
						</td>
					</tr>
					<tr>
						<td width="8%" nowrap align="right" class="detail_label">审核时间从：</td>
						<td width="15%" class="detail_content">
							<input id="AUDIT_TIME_BEG" name="AUDIT_TIME_BEG" readonly="readonly"  onclick="SelectTime();"  style="width:155" value="${params.AUDIT_TIME_BEG }">
							<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(AUDIT_TIME_BEG);"  >
						</td>
						<td width="8%" nowrap align="right" class="detail_label">审核时间到：</td>
						<td width="15%" class="detail_content">
							<input id="AUDIT_TIME_END" name="AUDIT_TIME_END" readonly="readonly"  onclick="SelectTime();"   style="width:155" value="${params.AUDIT_TIME_END }">
							<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(AUDIT_TIME_END);" >
						</td>
						<td width="8%" nowrap align="right" class="detail_label">交期从：</td>
						<td width="15%" class="detail_content">
							<input id="ADVC_SEND_DATE_BEG" name="ADVC_SEND_DATE_BEG" readonly="readonly" onclick="SelectDate();" style="width:155" value="${params.ADVC_SEND_DATE_BEG}"    />
							<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE_BEG);" >
						</td>	
						<td width="8%" nowrap align="right" class="detail_label">交期到：</td>
						<td width="15%" class="detail_content">
							<input id="ADVC_SEND_DATE_END" name="ADVC_SEND_DATE_END" readonly="readonly" style="width:155" onclick="SelectDate();" value="${params.ADVC_SEND_DATE_END}"/>
							<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE_END);" >
						</td>				
					</tr>
					<tr>
						<td width="8%" nowrap align="right" class="detail_label">货品编号：</td>
						<td width="15%" class="detail_content">
							<input id="PRD_ID" name="PRD_ID"  json="PRD_ID" type="hidden"  value="${params.PRD_ID}"/>
							<input id="PRD_NO" name="PRD_NO"  json="PRD_NO" type="text"  value="${params.PRD_NO}"/>	   					
		   					<img align="absmiddle" name="selJGXX" style="cursor:hand" 
		   						src="${ctx}/styles/${theme}/images/plus/select.gif" 
		   						onclick="selCommon('BS_21', true, 'PRD_ID', 'PRD_ID', 'forms[0]','PRD_NO,PRD_NAME','PRD_NO,PRD_NAME','selectParamsPro');"/>
						</td>
						<td width="8%" nowrap align="right" class="detail_label">货品名称：</td>
						<td width="15%" class="detail_content">
							<input name="PRD_NAME" id="PRD_NAME" type="text"   value="${params.PRD_NAME}"/>
						</td>
						<td width="8%" nowrap align="right" class="detail_label">省份:</td>
						 <td width="15%" class="detail_content">
		   					<input type="text" id="PROV" name="PROV"    value="${params.PROV}"/>
						    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
								onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[0]','PROV', 'PROV', '')">
						 </td>	
						<td width="8%" nowrap align="right" class="detail_label"></td>
						<td width="15%" class="detail_content">
						</td>
					</tr>
					<tr>
						<td colspan="10" align="center" valign="middle" >
							<input id="q_search" type="button" class="btn" value="查询(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
							<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">&nbsp;&nbsp;
<!--							<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">-->
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
<form action="" id="mainForm">
	<input type="hidden" id="selParam" name="selParam" value=" STATE ='启用' and DEL_FLAG=0 and PRVD_TYPE='物流供应商'  "/>
	<table width="100%" style="border-top: dashed 1px #999;" id="mainTab">
		<tr>
			<td width="8%" nowrap align="right" >
				发运方式：
			</td>
			<td width="15%" class="detail_content">
				<select name=DELIVER_TYPE id="DELIVER_TYPE" json="DELIVER_TYPE" label="发运方式" mustinput="true"  autocheck="true" inputtype="string" style="width: 80px"></select>
			</td>
			<td width="8%" nowrap align="right" >
				物流公司：
			</td>
			<td width="15%" class="detail_content">
				<input type="hidden" id="PRVD_ID" name="PRVD_ID" json="PRVD_ID" value="${params.PRVD_ID}"/>
	            <input json="PRVD_NO" name="PRVD_NO" id="PRVD_NO"  label="物流公司编号"  value="${params.PRVD_NO}"  type="hidden" />
				<input json="PRVD_NAME" name="PRVD_NAME" id="PRVD_NAME"  label="物流公司" readonly="readonly" value="${params.PRVD_NAME}"  type="text"  autocheck="true" style="width: 100px;"  maxlength="100" inputtype="string" mustinput="true" />
				<img align="absmiddle" name="selJGXX" style="cursor: hand"
					       src="${ctx}/styles/${theme}/images/plus/select.gif"
					       onClick="selCommon('BS_25', false, 'PRVD_ID', 'PRVD_ID', 'forms[1]','PRVD_NO,PRVD_NAME', 'PRVD_NO,PRVD_NAME', 'selParam')"> 
			</td>
			<td width="8%" nowrap align="right" >
				车型：
			</td>
			<td width="15%" class="detail_content">
				<select  name="TRUCK_TYPE" json="TRUCK_TYPE" id="TRUCK_TYPE" style="width: 100px;" label="车型" autocheck="true"  inputtype="string" ></select>
			</td>
			
			<td width="8%" nowrap align="right" >
				进场时间：
			</td>
			<td width="15%" class="detail_content">
				<input json="APPCH_TIME" name="APPCH_TIME" id="APPCH_TIME"  label="进场时间" style="width: 100px;" readonly="readonly"  type="text"   autocheck="true"  maxlength="32" inputtype="date" mustinput="true" value="${params.APPCH_TIME}" onclick="SelectTime();"/> 
			    <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(APPCH_TIME);"/>
			</td>
			<td width="8%" nowrap align="right" >备注：</td>
			<td width="15%" class="detail_content">
				<input type="text" maxlength="200" id="REMARK" name="REMARK" json="REMARK">
			</td>
			<td><input type="button" value="下单" class="btn" id="save" /></td>
			<td width="3%" align="right">
				<input type="button" value="↑" style="margin-right: 10px;" onclick="hide()" id="showOrHide" class="btn" />
			</td>
		</tr>
		<tr>
			<td width="8%" nowrap align="right" >总数量：<input id="total_num" type="text" class="text_underline" readonly="readonly" style="width:50px;"/></td>
			<td width="8%" nowrap align="right" >总体积：<input id="total_vol" type="text" class="text_underline" readonly="readonly"/></td>
			<td width="8%" nowrap align="right" >总金额：<input id="total_amount" type="text" class="text_underline" readonly="readonly"/></td>
			<td width="8%" nowrap align="right" >总冻结金额：<input id="total_freeAmount" type="text" class="text_underline" readonly="readonly"/></td>
			<td width="8%" nowrap align="right" >参考手动冻结金额：<input id="freeAmount" type="text" class="text_underline" readonly="readonly"/></td>
		</tr>
	</table>
</form>
<table width="99.8%" height="65%" border="0" cellSpacing=0 cellPadding=0 id="btnTab">
<tr >
<td>
	<div class="lst_area" >
	    <form id="pageForm" method="post">
		<table id="jsontb" width="100%"  border="0" cellpadding="1" cellspacing="1" class="lst" >
			<tr class="fixedRow">
              <th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
              <th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >销售订单编号</th>
              <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >订货订单编号</th>
              <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >订单类型</th>
              <th  nowrap="nowrap" align="center" dbname="ADVC_SEND_DATE" >预计发货日期</th>
              <th  nowrap="nowrap" align="center" dbname="RECV_ADDR_NO" >收货地址编号</th>
              <th  nowrap="nowrap" align="center" dbname="RECV_ADDR" >收货地址</th>
              <th  nowrap="nowrap" align="center" dbname="IS_FREE_FLAG" >是否赠品</th>
              <th  nowrap="nowrap" align="center" dbname="IS_BACKUP_FLAG" >是否抵库</th>
              <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
              <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
              <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
              <th  nowrap="nowrap" align="center" dbname="SPCL_DTL_REMARK" >特殊工艺描述</th>
              <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
              <th  nowrap="nowrap" align="center" dbname="SENDED_NUM" >已发数量</th>
              <th  nowrap="nowrap" align="center" dbname="NO_SEND_NUM" >未发数量</th>
<!--              <th  nowrap="nowrap" align="center" dbname="" >仓库可发数量</th>-->
              <th  nowrap="nowrap" align="center" dbname="PLAN_NUM" >之前已排数量</th>
              <th  nowrap="nowrap" align="center" dbname="" >本次发货数量</th>
              <th  nowrap="nowrap" align="center" dbname="VOLUME" >单个体积</th>
              <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >单个金额</th>
              <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>
            </tr>
            <c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle"  id="eid${rr}" onclick="checkClick('eid${rr}');" json="SALE_ORDER_DTL_ID" name="SALE_ORDER_DTL_ID" value="${rst.SALE_ORDER_DTL_ID}" />
					 </td>
                     	<td nowrap="nowrap" align="center">${rst.SALE_ORDER_NO}&nbsp;</td>
                     	<td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     	<td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     	<td nowrap="nowrap" align="center">${rst.ADVC_SEND_DATE}&nbsp;</td>
                     	<td nowrap="nowrap" align="center">${rst.RECV_ADDR_NO}&nbsp;</td>
                     	<td nowrap="nowrap" align="center">${rst.RECV_ADDR}&nbsp;</td>
                     	<td nowrap="nowrap" align="center" >
						    <c:if test="${'1' eq rst.IS_FREE_FLAG}"> 是  </c:if>
						    <c:if test="${'1' ne rst.IS_FREE_FLAG}"> 否 </c:if>
						 </td>
						 <td nowrap="nowrap" align="center" >
						    <c:if test="${'1' eq rst.IS_BACKUP_FLAG}"> 是  </c:if>
						    <c:if test="${'1' ne rst.IS_BACKUP_FLAG}"> 否 </c:if>
						 </td>
                     	<td nowrap="nowrap" align="center">${rst.PRD_NO}
                     		<c:if test="${rst.SPCL_TECH_NO ne null}">
					 		-${rst.SPCL_TECH_NO}
					 		</c:if>
                     	&nbsp;</td>
                     	<td nowrap="nowrap" align="center">${rst.PRD_NAME}&nbsp;</td>
                     	<td nowrap="nowrap" align="center">${rst.PRD_SPEC}&nbsp;</td>
                     	<td nowrap="nowrap" align="center">
                     	 <c:if test="${rst.SPCL_TECH_FLAG ge 1}">
                     	 	<c:if test="${!empty rst.SPCL_SPEC_REMARK }">
	                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
	                     	</c:if>
                     	 	<input type="button" class="btn" value="查看" onclick="url('${rst.SPCL_TECH_ID}','${rst.PRICE}')" />
                     	 </c:if>
                     	 <c:if test="${rst.SPCL_TECH_FLAG eq '0' || rst.SPCL_TECH_FLAG eq null}">
                     	 	无
                     	 </c:if>
                     	&nbsp;
                     </td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SENDED_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.NO_SEND_NUM}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center">&nbsp;</td>-->
                     <td nowrap="nowrap" align="center">${rst.PLAN_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
                     	<input type="text" size="4" value="${rst.NO_SEND_NUM}" name="PLAN_NUM" autocheck="true" onkeyup="countTotal();" onclick="$('#eid${rr}').attr('checked','checked');countTotal();"  mustinput="true" label="本次发货数量"  json="PLAN_NUM"/>
                     	<input type="hidden" name="VOLUME"  value="${rst.VOLUME}">
                     	<input type="hidden" name="DECT_PRICE"  value="${rst.DECT_PRICE}">
                     </td>
                     <td nowrap="nowrap" align="center">${rst.VOLUME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.DECT_PRICE}&nbsp;</td>
                     <input type="hidden" value="${rst.ORDER_CHANN_ID}" name="ORDER_CHANN_ID"/>
                     <input type="hidden" value="${rst.ORDER_CHANN_NO}" name="ORDER_CHANN_NO"/>
                     <input type="hidden" value="${rst.ORDER_CHANN_NAME}" name="ORDER_CHANN_NAME"/>
                     <input type="hidden" value="${rst.CREDIT_FREEZE_PRICE}" name="CREDIT_FREEZE_PRICE"/>
                     <input type="hidden" value="${rst.SENDED_NUM}" name="SENDED_NUM"/>
                     <input type="hidden" value="${rst.PLAN_NUM}" name="PLAN_NUM_LAST"/>
                     <input type="hidden" value="${rst.IS_FREE_FLAG}" name="IS_FREE_FLAG"/>
                     
                     
                     <td nowrap="nowrap" align="center"><input type="text" value="${rst.REMARK}" maxlength ="200"   json="REMARK" name="REMARK" onclick="$('#eid${rr}').attr('checked','checked');"/>&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="21" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
		</table>
		</form>
	</div>
</td>
</tr>
<tr width="100%">
			<td height="12px" align="center"  >
				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
					<tr>
						<td>
							<form id="listForm" action="#" method="post" name="listForm">
								<input id="REBATEFLAG" name="REBATEFLAG" value="${REBATEFLAG}" type="hidden"/>
								<input id="LARGESSFLAG" name="LARGESSFLAG" value="${LARGESSFLAG}" type="hidden"/>
								<input id="SHOP_CART_TYPE" name="SHOP_CART_TYPE" value="${params.SHOP_CART_TYPE}" type="hidden"/>
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
							 ${page.footerHtml}${html}
						</td>
					</tr>
				</table>
			</td>
		</tr>
</table>
<div id="freezeDiv">
	<form id="freezeForm" method="post">
		<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
			<tr class="fixedRow">
				  <th nowrap align="center" style="display: none;"></th>
	              <th  nowrap="nowrap" align="center" dbname=" " >订货方编号</th>
	              <th  nowrap="nowrap" align="center" dbname=" " >订货方名称</th>
	              <th  nowrap="nowrap" align="center" dbname=" " >冻结金额</th>
			</tr>
		</table>
	</form>
	<table>
		<tr>
			<td>
				<input type="button" value="确定" onclick="toVerify()" class="btn" style="margin-right: 10px;"/> 
    			<input type="button" value="关闭" onclick="cloce()" class="btn" />
			</td>
		</tr>
	</table>
</div>
</body>
<script type="text/javascript">
	SelDictShow("DELIVER_TYPE","BS_54","整车发运","");
	SelDictShow("TRUCK_TYPE","BS_109","","");
</script>
</html>