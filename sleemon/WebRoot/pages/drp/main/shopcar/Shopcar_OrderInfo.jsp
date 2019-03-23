<!-- 
 *@module 分销业务
 *@func 人员信息维护
 *@version 1.1
 *@author lyg
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>购物车</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/main/shopcar/Shopcar_OrderInfo.js"></script>
	<style type="text/css">
		.span_num{
			color: red;
			font-size:12px;
		}
		.topDiv{
			float: left;
			font-size: 12px;
			font-size: 12x;
			height: 30%;
		}
		.text_underline{
			border-bottom-width:0px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
		}
	</style>
</head>
<body onload="selectCarType();" class="bodycss1">
	<div id="topDiv">
		<table width="100%" border="1px" rules="none" id="jsontb">
			<tr>
				<td style="width: 12%;border-bottom:solid 1px #000; padding-bottom: 5px;" >
					<font style="font-size: 12;font-weight: bolder; padding-bottom: 5px;">订货单位信息：</font>
				</td>
				<td style="border-bottom:solid 1px #000;" colspan="4">
					<div class="topDiv" style="width: 30%">
						订货单位信息：${rst.CHANN_NAME}&nbsp;&nbsp;
					</div>
					<c:if test="${sta eq 'show'}">
						<div class="topDiv" style="width: 15%">
							可用信用：
							<span class="span_num">
							<%if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){%>
							 ${rst.INI_CREDIT+userCredit+rst.TEMP_CREDIT_REQ-rst.FREEZE_CREDIT}
							<%}else{%>
							<!-- 等于 当前信用+sum的临时信用+返利额-冻结信用 -->
								${rst.CREDIT_CURRT+rst.TEMP_CREDIT_REQ+rst.REBATE_CURRT-rst.FREEZE_CREDIT}
							<% }%>
							
							</span>&nbsp;&nbsp;
						</div>
						<div class="topDiv" style="width: 15%"><%--
							临时信用：<!-- 临时信用加付款凭证信用 -->
							<span class="span_num">
								${rst.TEMP_CREDIT}
							</span>&nbsp;&nbsp;--%>
							付款凭证信用：<!-- 付款凭证信用 -->
							<span class="span_num">
								${rst.PAYMENT_CREDIT}
							</span>&nbsp;&nbsp;
							
						</div>
						<div class="topDiv" style="width: 40%">
							返利额：
							<span class="span_num">
								${rst.REBATE_CURRT}
							</span>&nbsp;&nbsp;
							(注意:订货可用信用  = 可用信用+付款凭证信用)
						</div>
					</c:if>
				</td>
			</tr>
			<tr>
				<td  colspan="4">
					<font style="font-size: 12;font-weight: bolder; padding-bottom: 5px;width: 10%;">收货方信息：</font>
					<c:if test="${sta eq 'show'}">
						 <span style=" font-weight: bold">
						 	 <input type="checkbox" id="rebate" onclick="rebOp()" >是否使用返利
						 </span>
						 <span style="color: red;width: 50%">(PS:软床和床垫且非特殊工艺的货品可使用返利)</span>
						 <c:if test="${pvg.PVG_BWS_GIFT eq 1 }">
						 <span style=" font-weight: bold">
						 	 <input type="checkbox" id="larRebate" onclick="larOp()" >是否赠品订货
						 </span>
						</c:if>
						 <span style="color: red;"></span>
					 </c:if>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<form id="queryForm" method="post" action="#">
						<table style="border-bottom:solid 1px #000;" id="channInfo">
							<input type="hidden" id="selectAddrParams" name=selectAddrParams value=" DEL_FLAG=0 and STATE='启用' and CHANN_ID='${rst.CHANN_ID}' ">
						    <input type="hidden" name="selectParams" value=" STATE ='启用' and DEL_FLAG='0' and ( CHANN_ID='${CHANN_ID}' or AREA_SER_ID='${CHANN_ID}') ">
						    <input type="hidden" name="selectStore" id="selectStore" value=""/>
							<input type="hidden" name="ORDER_CHANN_ID" json="ORDER_CHANN_ID" id="ORDER_CHANN_ID" value="${rst.CHANN_ID}"/>
							<input type="hidden" name="ORDER_CHANN_NO" json="ORDER_CHANN_NO" id="ORDER_CHANN_NO" value="${rst.CHANN_NO}"/>
							<input type="hidden" name="ORDER_CHANN_NAME" json="ORDER_CHANN_NAME" id="ORDER_CHANN_NAME" value="${rst.CHANN_NAME}"/>
							<input type="hidden" name="AREA_ID" json="AREA_ID" id="AREA_ID" value="${rst.AREA_ID}"/>
							<input type="hidden" name="AREA_NO" json="AREA_NO" id="AREA_NO" value="${rst.AREA_NO}"/>
							<input type="hidden" name="AREA_NAME" json="AREA_NAME" id="AREA_NAME" value="${rst.AREA_NAME}"/>
							<input type="hidden" name="ORDER_RECV_DATE" json="ORDER_RECV_DATE" id="ORDER_RECV_DATE" label="交期" value=""/>
							<tr>
								<td width="8%" nowrap align="right" >收货方编号：</td>
								<td nowrap width="15%" class="detail_content">
									<input type="hidden" id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" value="${params.CHANN_ID}"/>
									<input  name="CHANN_NO" json="CHANN_NO" id="CHANN_NO" type="text" autocheck="true" label="收货方编号" inputtype="string" mustinput="true"  READONLY value="${params.CHANN_NO}" />
									<img align="absmiddle"  style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selRECV()">
								</td>
								<td width="8%" nowrap align="right">收货方名称：</td>
								<td nowrap width="15%" class="detail_content">
									<input type="text" json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME"  autocheck="true" label="收货方名称" inputtype="string" mustinput="true"  value="${params.CHANN_NAME }" READONLY/>
								</td>
								<td width="8%" nowrap align="right">详细地址：</td>
								<td>
								   <input type="hidden" id="RECV_ADDR_NO" json="RECV_ADDR_NO" name="RECV_ADDR_NO"  value=""/>
			                       <input type="text"  id="RECV_ADDR" json="RECV_ADDR" name="RECV_ADDR"  autocheck="true" inputtype="string"  size="250" mustinput="true" style="width: 200px;" maxlength="250" readonly="readonly"  label="详细地址"  value="${rst.RECV_ADDR}" />
			                       <img align="absmiddle" name="selAddr" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onclick="selRevcAddr();">
								</td>
							</tr>
							<tr>
								<td width="8%" nowrap align="right" >联系人：</td>
								<td>
									<input json="PERSON_CON" name="PERSON_CON" autocheck="true" id="PERSON_CON" type="text" mustinput="true" inputtype="String"  maxlength="30" value="${rst.PERSON_CON }">
								</td>
								<td width="8%" nowrap align="right" >联系电话：</td>
								<td>
									<input name="TEL" type="text" json="TEL" autocheck="true" id="TEL" inputtype="string" mustinput="true" maxlength="30" value="${rst.TEL}">
								</td>
								<td width="8%" nowrap align="right">入库库房：</td>
								<td >
									<input id="STORE_ID" json="STORE_ID" name="STORE_ID" type="hidden"  value="${rst.STORE_ID}">
									<input id="STORE_NO" json="STORE_NO"  name="STORE_NO" type="hidden"   value="${rst.STORE_NO}" >
									<input id="STORE_NAME" json="STORE_NAME"  name="STORE_NAME" type="text"   inputtype="string" value="${rst.STORE_NAME}" style="width: 200px;" READONLY>
									<img align="absmiddle"  style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selStore()">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td style="width: 100px; padding-bottom: 5px;">
					<font style="font-size: 12;font-weight: bolder;">整车查看：</font>
				</td>
				<td>
				<table width="100%">
				  <tr>
					<td width="8%" nowrap align="right" >车型：</td>
					<td width="40%">
					 <span >
						<input type="hidden" name="MIN_VOLUME" id="MIN_VOLUME"/>
						<input type="hidden" name="MAX_VOLUME" id="MAX_VOLUME"/>
						<select  name="carType" id="carType" style="width: 100px;" label="车型"  onchange="changeVolumn();"></select>
						 <span style="margin-left: 2px;">
						   <span   name="car_VOLUMN" id="car_VOLUMN" class="text_underline" style="line-height:28px;height:30px;">
						   </span>
						   
					  </span>
					</td>
					<td  align="right"  width="52%">
					 <!-- input type="checkbox" name="notSend" id="notSend" value="总部未排车" <c:if test="${params.notSend eq '总部未发'}">checked="checked"</c:if>  onclick="showBtnLook(this);"/--> 
					   总部未排车<input id="look1" type="button" class="btn" value="查看" onclick="showPage();"  style="margin-left: 5px;">&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				</table>
				</td>
				 
		    </tr>		
		</table>
	</div>
	<div id="bottomdiv" style="height: 70%; width: 100%;z-index:-1;position:absolute;">
		<!-- 下帧 -->
		<iframe id="bottomcontent" name="bottomcontent" src="#" frameBorder=0  width="100%" height="100%" style="z-index:-1;position:absolute;" frameborder="0" scrolling="AUTO"></iframe>
	</div>
</body>
<script language="JavaScript">
    function divShow(id){
    	document.getElementById(id).style.display="";
    }
    
    function selectCarType(){
	  if(null =="${params.carType}" || ""=="${params.carType}"){
	     SelDictShow("carType","BS_109","5吨(49-53)","");
      }else{
    	  SelDictShow("carType","BS_109","${params.carType}","");
      }
  }
</script>
</html>