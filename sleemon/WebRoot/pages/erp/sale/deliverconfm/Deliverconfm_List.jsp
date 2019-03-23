<!--
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 发运确认
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/deliverconfm/Deliverconfm_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td>当前位置：销售管理>>发运管理>>发运单确认</td>
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
                   <c:if test="${pvg.PVG_AFFIRM eq 1 }">
                    <input id="modify" type="button" class="btn" value="填写发运信息(E)" title="Alt+E" accesskey="E">
					<input id="affirm" type="button" class="btn" value="发运确认(A)" title="Alt+A" accesskey="A">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="expdate" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
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
		<div class="" style="margin-left:3px;width: 100%;overflow-y: auto; height: 100%;background-color: #fff;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="DELIVER_ORDER_NO" >发运单号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_SEND_DATE" >预计发货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="DELIVER_TYPE" >发货方式</th>
                    <th  nowrap="nowrap" align="center" dbname="TRUCK_TYPE" >车型</th>
                    <th  nowrap="nowrap" align="center" dbname="SHIP_POINT_NAME" >生产基地</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NO" >收货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_ADDR_NO" >收货地址编号</th>
                    <th  nowrap="nowrap" align="center" dbname="DELIVER_DTL_ADDR" >收货地址</th>
                    <th  nowrap="nowrap" align="center" dbname="JOIN_DELIVER_ORDER_NO" >出货计划号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRVD_NAME" >物流公司</th>
                    <th  nowrap="nowrap" align="center" dbname="APPCH_TIME" >进场时间</th>
                    <%--<th  nowrap="nowrap" align="center" dbname="REMARK" >备注说明</th>
                    --%>
                    <th  nowrap="nowrap" align="center" dbname="IS_ALL_FREEZE_FLAG" >是否整单冻结</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源发运单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.DELIVER_ORDER_ID}" />
					 </td>
                     <td nowrap="nowrap" align="center">${rst.DELIVER_ORDER_NO}&nbsp;</td>
                      <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ADVC_SEND_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.DELIVER_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TRUCK_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SHIP_POINT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECV_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_ADDR_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DELIVER_DTL_ADDR}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.JOIN_DELIVER_ORDER_NO} &nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PRVD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.APPCH_TIME}&nbsp;</td>
                     <%--
                     <td nowrap="nowrap" align="left" title="${rst.REMARK}">${rst.SHORT_REMARK}&nbsp;</td>
                     --%>
                     <td nowrap="nowrap" align="center">
                       <c:if test="${empty rst.IS_ALL_FREEZE_FLAG or 0 eq rst.IS_ALL_FREEZE_FLAG}">否</c:if>
                       <c:if test="${1 eq rst.IS_ALL_FREEZE_FLAG}">是</c:if>
                     </td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <input id="state${rst.DELIVER_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
                     <input id="BILL_TYPE${rst.DELIVER_ORDER_ID}" type="hidden"  value="${rst.BILL_TYPE}" />
                     <input id="STOREOUT_TIME${rst.DELIVER_ORDER_ID}" type="hidden"  value="${rst.STOREOUT_TIME}" />
                     <input id="STOREOUT_TIME_DIFF${rst.DELIVER_ORDER_ID}" type="hidden"  value="${rst.STOREOUT_TIME_DIFF}" />
                     <input id="REMARK${rst.DELIVER_ORDER_ID}" type="hidden"  value="${rst.REMARK}" />
                     <input id="DELIVER_ORDER_NO${rst.DELIVER_ORDER_ID}" type="hidden"  value="${rst.DELIVER_ORDER_NO}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="20" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
<c:if test="${not empty page.result}">
<tr>
	<td height="25" colspan="13" align="center"  >
       <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar" >
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
					 ${page.footerHtml}${page.toolbarHtml}&nbsp;&nbsp;&nbsp;&nbsp; 
				</td>
			</tr>
		</table>
	</td>
</tr>
</c:if>
</table>

<input name="REMARK" id="REMARK" type="hidden"/>
<input name="DELAY_TYPE" id="DELAY_TYPE" type="hidden"/>

<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<input type="hidden" id="selParam" name="selParam" value=" STATE in('启用','停用') and DEL_FLAG='0'"/>
<input type="hidden" id="selDevliverParam" name="selDevliverParam" value=" STATE in('已提交库房','已发货','待收货','已收货') and DEL_FLAG=0 and ORDER_CHANN_ID in ${params.CHANNS_CHARG} "/>
<input type="hidden" name="selectParam" value=" STATE ='启用' and DEL_FLAG='0' and CHANN_TYPE='区域服务中心'">
<input type="hidden" name="selectParams" value=" STATE in( '启用','停用') and DEL_FLAG='0' ">
<input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE in('启用','停用')  and CHANN_TYPE !='总部' "/>
<input type="hidden" id="selSendOrderParam" name="selSendOrderParam" value="  CHANN_ID='${ZTXXID}' "/>
<input type="hidden" id="selSaleOrderParam" name="selSaleOrderParam" value=" STATE in('审核通过','退回')"/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="8%" nowrap align="right" class="detail_label">发运单号:</td>
				   <td width="15%" class="detail_content">
				     <input id="DELIVER_ORDER_ID" json="DELIVER_ORDER_ID" name="DELIVER_ORDER_ID" type="hidden" >
				     <input id="DELIVER_ORDER_NO" json="DELIVER_ORDER_NO" name="DELIVER_ORDER_NO" type="text"   value="${param.DELIVER_ORDER_NO}"  >
				     <img align="absmiddle" name="selJGXX" style="cursor: hand"
					  src="${ctx}/styles/${theme}/images/plus/select.gif"
					   onClick="selCommon('BS_68', false, 'DELIVER_ORDER_ID', 'DELIVER_ORDER_ID', 'forms[1]','DELIVER_ORDER_NO', 'DELIVER_ORDER_NO', 'selDevliverParam')">
				
				   </td>
				    <%--<td width="8%" nowrap align="right" class="detail_label">发货类型:</td>
					<td width="15%" class="detail_content">
					   <select name="CHANN_TYPE" id="CHANN_TYPE"  style="width: 155"></select>
					</td>
                    --%><td width="8%" nowrap align="right" class="detail_label">发货方式:</td>
					<td width="15%" class="detail_content">
					 <select name=DELIVER_TYPE id="DELIVER_TYPE"  style="width: 155"></select>
					</td>		
					<td width="8%" nowrap align="right" class="detail_label">生产基地名称:</td>
					<td width="15%" class="detail_content">
					 <input id="SHIP_POINT_NAME" autocheck="true" json="SHIP_POINT_NAME" name="SHIP_POINT_NAME" type="text" label="生产基地名称" value="${params.SHIP_POINT_NAME}" inputtype="string" >
					 <input id="SHIP_POINT_NO" autocheck="true" json="SHIP_POINT_NO" name="SHIP_POINT_NO" type="hidden" label="生产基地编号"  >
					 <img align="absmiddle" name="selZONE" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif" 
						onClick="selCommon('BS_22', false, 'SHIP_POINT_NO','SHIP_POINT_NO', 'forms[1]','SHIP_POINT_NO,SHIP_POINT_NAME', 'SHIP_POINT_NO,SHIP_POINT_NAME', 'selectParams')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">出货计划号:</td>
					 <td width="15%" class="detail_content">
	   					 <input name="JOIN_DELIVER_ORDER_NO" id="JOIN_DELIVER_ORDER_NO" value="${params.JOIN_DELIVER_ORDER_NO}"  ></input>
				  </td>	
               </tr>
              <tr><%--
                <td width="8%" nowrap align="right" class="detail_label">代发区域服务中心编号:</td>
				<td width="15%" class="detail_content">
				  <input id="AREA_SER_ID" json="AREA_SER_ID" name="AREA_SER_ID" type="hidden" >
			      <input id="AREA_SER_NO" json="AREA_SER_NO" name="AREA_SER_NO" type="text"   value="${param.AREA_SER_NO}"  >
			      <img align="absmiddle" name="selJGXX" style="cursor: hand"
				    src="${ctx}/styles/${theme}/images/plus/select.gif"
				    onClick="selCommon('BS_19', false, 'AREA_SER_ID', 'CHANN_ID', 'forms[1]','AREA_SER_ID,AREA_SER_NO,AREA_SER_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParam')">
			    </td>
				<td width="8%" nowrap align="right" class="detail_label">代发区域服务中心名称:</td>
				<td width="15%" class="detail_content">
			      <input id="AREA_SER_NAME" json="AREA_SER_NAME" name="AREA_SER_NAME" type="text"   value="${param.AREA_SER_NAME}"  >
			    </td>
			    	
				--%>
				<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					 <select name=STATE id="STATE"  style="width: 155"></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">单据类型:</td>
					<td width="15%" class="detail_content">
	   					 <select name="BILL_TYPE" id="BILL_TYPE"  style="width: 155"></select>
					</td>	
				<td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>
				<td width="15%" class="detail_content">
   					<input id="RECV_CHANN_NO" name="RECV_CHANN_NO"  style="width:155" value="${params.RECV_CHANN_NO}"/>
				    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[1]','RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selOrderChannParam')">
				
				</td>					
                   <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
				<td width="15%" class="detail_content">
				   <input type="hidden" name="RECV_CHANN_ID" id="RECV_CHANN_ID" value="" />
   				   <input type="text" id="RECV_CHANN_NAME" name="RECV_CHANN_NAME"    value="${params.RECV_CHANN_NAME}"/>
				</td>
			    </tr>
			    <tr>
			    	 <td width="8%" nowrap align="right" class="detail_label">预计发货日期从:</td>
				 <td width="15%" class="detail_content">
				     <input type="text"  id="ADVC_SEND_DATE_START" name="ADVC_SEND_DATE_START" value="${params.ADVC_SEND_DATE_START}"  onclick="SelectDate();"  />&nbsp;
				     <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE_START);"/>
				   </td>
                   <td width="8%" nowrap align="right" class="detail_label">预计发货日期到:</td>
				   <td width="15%" class="detail_content">
				     <input type="text"  id="ADVC_SEND_DATE_END" name="ADVC_SEND_DATE_END" value="${params.ADVC_SEND_DATE_END}"  onclick="SelectDate();"  />&nbsp;
				     <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE_END);"/>
				   </td>
				   <td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
					<td width="15%" class="detail_content">
				      <input id="ORDER_CHANN_ID" name="ORDER_CHANN_ID" type="hidden" >
				      <input id="ORDER_CHANN_NO" json="ORDER_CHANN_NO" name="ORDER_CHANN_NO" type="text"   value="${param.ORDER_CHANN_NO}"  >
				      <img align="absmiddle" name="selJGXX" style="cursor: hand"
					   src="${ctx}/styles/${theme}/images/plus/select.gif"
					   onClick="selCommon('BS_19', false, 'ORDER_CHANN_ID', 'CHANN_ID', 'forms[1]','ORDER_CHANN_NO,ORDER_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selOrderChannParam')">
				   </td>
				   <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
				   <td width="15%" class="detail_content">
				     <input id="ORDER_CHANN_NAME" json="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME" type="text"   value="${param.ORDER_CHANN_NAME}"  >
				   </td> 
			    </tr>
				 <tr> 
                <td width="8%" nowrap align="right" class="detail_label">区域编号:</td>
				<td width="15%" class="detail_content">
   					<input id="AREA_NO" name="AREA_NO"  style="width:155" value="${params.AREA_NO}"/>
   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[1]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selParam')">
				</td>					
                <td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
				<td width="15%" class="detail_content">
				   <input type="hidden" name="AREA_ID" id="AREA_ID" value="" />
   				   <input id="AREA_NAME" name="AREA_NAME"  style="width:155" value="${params.AREA_NAME}"/>
				</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间从：</td>
				  <td width="15%" class="detail_content">
						<input type="text" json="CRE_TIME_BEGIN" id="CRE_TIME_BEGIN"name="CRE_TIME_BEGIN" value="${params.CRE_TIME_BEGIN}" autocheck="true" inputtype="string" label="制单时间从"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_BEGIN);"/>
				  </td>
				  <td width="8%" nowrap align="right" class="detail_label">制单时间到：</td>
				  <td width="15%" class="detail_content">
						<input type="text" json="CRE_TIME_END" id="CRE_TIME_END" value="${params.CRE_TIME_END }" name="CRE_TIME_END" autocheck="true" inputtype="string" label="制单时间到"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_END);"/>
				  </td>
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">货品编号</td>
					<td width="15%" class="detail_content">
						<input id="PRD_NO" json="PRD_NO"  name="PRD_NO" type="text" inputtype="string"   value="${params.PRD_NO}" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">货品名称</td>
					<td width="15%" class="detail_content">
						<input name="PRD_NAME" type="text" value="${params.PRD_NAME}" id="PRD_NAME" json="PRD_NAME">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">规格型号：</td>
					<td width="15%" class="detail_content">
						<input type="text" value="${params.PRD_SPEC}" id="PRD_SPEC" name="PRD_SPEC"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">来源发运单编号：</td>
				    <td width="15%" class="detail_content">
				     <input type="text"  id="FROM_BILL_NO" json="FROM_BILL_NO" name="FROM_BILL_NO" value="${params.FROM_BILL_NO}"/>
				    </td>
					 
               </tr>
                <tr>
                 <td width="8%" nowrap align="right" class="detail_label">省份:</td>
				 <td width="15%" class="detail_content">
				    <input type="hidden" id="ZONE_ID" name="ZONE_ID"    value=""/>
   					<input type="text" id="PROV" name="PROV"    value="${params.PROV}"/>
				    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[1]','PROV', 'PROV', '')">
				 </td>
				 
				  <td width="8%" nowrap align="right" class="detail_label">销售订单编号:</td>
				  <td width="15%" class="detail_content">
			       <input  type="hidden" id="SALE_ORDER_ID" name="SALE_ORDER_ID"    value=""/>
  					<input  type="text" id="SALE_ORDER_NO" name="SALE_ORDER_NO"    value="${params.SALE_ORDER_NO}"/>
  					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
					onClick="selCommon('BS_70', false, 'SALE_ORDER_ID', 'SALE_ORDER_ID', 'forms[1]','SALE_ORDER_NO', 'SALE_ORDER_NO', 'selSaleOrderParam')">
			
			     </td>
				    
				 <td width="8%" nowrap align="right" class="detail_label">制单人：</td>
                  <td width="15%" class="detail_content">
                    <input type="text" id="CRE_NAME" name="CRE_NAME"  value="${params.CRE_NAME}"/>
                  </td>
                  <td width="8%" nowrap align="right" class="detail_label"></td>
                  <td width="15%" class="detail_content">
                  </td>
               </tr>
               
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input id="q_rest" type="reset" class="btn" value="重置(R)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
    SelDictShow("CHANN_TYPE","BS_58","${params.CHANN_TYPE}","");
    SelDictShow("DELIVER_TYPE","BS_54","${params.DELIVER_TYPE}","");
    SelDictShow("STATE","BS_62","${params.STATE}","");
    SelDictShow("BILL_TYPE","BS_77","${params.BILL_TYPE}","");
    
    
</script>
</html>
