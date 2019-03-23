<!--
 * @prjName:喜临门营销平台
 * @fileName:销售订单审核
 * @author zzb
 * @time   2013-10-12 13:52:19 
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
	<title>销售订单审核 浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/saleorderrls/Saleorderrls_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>订单中心>>销售订单查询</td>
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
 				    <input id="commit" type="button" class="btn" value="提交(C)" title="Alt+C" accesskey="C"> 
 				   </c:if> 
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
			   		<input id="expdate" type="button" class="btn" value="导出(X)" title="Alt+X" accesskey="X">
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
                    <th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >销售订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_DATE" >订货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="TOTL_AMOUNT" >订货总额</th>
                    <th  nowrap="nowrap" align="center" dbname="WAREA_NAME" >战区</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NO" >订货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_CHANN_NAME" >订货方名称</th>
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
                     <td nowrap="nowrap" align="center">${rst.SALE_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
                       <c:if test="${'合并订单' eq rst.FROM_BILL_NO}" >
                         <a href="#" onclick="orpenWindow('${rst.SALE_ORDER_ID}')"> ${rst.FROM_BILL_NO}</a>
                       </c:if>
                       <c:if test="${'合并订单' ne rst.FROM_BILL_NO}" >
                          ${rst.FROM_BILL_NO}
                       </c:if>
                     </td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.TOTL_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.WAREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ORDER_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORDER_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PERSON_CON}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.SHIP_POINT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.SALE_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
                    <input id="BILL_TYPE${rst.SALE_ORDER_ID}" type="hidden"  value="${rst.BILL_TYPE}" />
                    <input id="TECH_STATE${rst.SALE_ORDER_ID}" type="hidden"  value="${rst.TECH_STATE}" />
                    <input id="STANDFLAG${rst.SALE_ORDER_ID}" type="hidden"  value="${rst.STANDFLAG}" />
                    <input id="ORDER_CHANN_NO${rst.SALE_ORDER_ID}" type="hidden"  value="${rst.ORDER_CHANN_NO}" />
                    <input id="SHIP_POINT_ID${rst.SALE_ORDER_ID}" type="hidden"  value="${rst.SHIP_POINT_ID}" />
                    <input id="" name="DATE_DIFF" type="hidden" value="${rst.DATE_DIFF}" />
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
<input type="hidden" name="selProvParam" value=" STATE in( '启用') and DEL_FLAG='0' ">
<input type="hidden" id="selOrderChannParam" name="selOrderChannParam" value=" STATE in('启用','停用')  and CHANN_TYPE !='总部' "/>
<input type="hidden" id="selGoodOrderParam" name="selGoodOrderParam" value=" STATE='已处理' and DEL_FLAG=0 and ORDER_CHANN_ID in ${params.CHANNS_CHARG} "/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                  <td width="8%" nowrap align="right" class="detail_label">销售订单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="SALE_ORDER_NO" name="SALE_ORDER_NO"  style="width:155" value="${params.SALE_ORDER_NO}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">订货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ORDER_CHANN_NO" name="ORDER_CHANN_NO"  style="width:155" value="${params.ORDER_CHANN_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'ORDER_CHANN_ID', 'CHANN_ID', 'forms[1]','ORDER_CHANN_NO,ORDER_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selOrderChannParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货方名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="ORDER_CHANN_ID" id="ORDER_CHANN_ID" value="" />
	   				   <input type="text" id="ORDER_CHANN_NAME" name="ORDER_CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>					
                   					
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
					    <select id="STATE" name="STATE" style="width:155"></select>
					</td>					
               </tr>
               
                 <tr>
                    <td width="8%" nowrap align="right" class="detail_label">区域编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_NO" name="AREA_NO"  style="width:155" value="${params.AREA_NO}"/>
	   					<img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[1]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selAREAParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="AREA_ID" id="AREA_ID" value="" />
	   				  <input id="AREA_NAME" name="AREA_NAME"  style="width:155" value="${params.AREA_NAME}"/>
					
					</td>					
                   					
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
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">订货订单编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" id="GOODS_ORDER_ID" name="GOODS_ORDER_ID" />
	   					<input type="text" id="GOODS_ORDER_NO" name="GOODS_ORDER_NO" value="${params.GOODS_ORDER_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_56', false, 'GOODS_ORDER_ID', 'GOODS_ORDER_ID', 'forms[1]','GOODS_ORDER_NO', 'GOODS_ORDER_NO', 'selGoodOrderParam')">
					</td>
 
					<td width="8%" nowrap align="right" class="detail_label">单据类型</td>
					<td width="15%" class="detail_content">
					  <select id="BILL_TYPE" name="BILL_TYPE" style="width: 155px;"></select>
                    </td>
					<td width="8%" nowrap align="right" class="detail_label">货品编号：</td>
					<td width="15%" class="detail_content">
						<input type="text" value="${params.PRD_NO}" id="PRD_NO" name="PRD_NO"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">货品名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" value="${params.PRD_NAME}" id="PRD_NAME" name="PRD_NAME"/>
					</td>
               </tr>
               <tr>
                <td width="8%" nowrap align="right" class="detail_label">规格型号：</td>
				<td width="15%" class="detail_content">
					<input type="text" value="${params.PRD_SPEC}" id="PRD_SPEC" name="PRD_SPEC"/>
				</td>
				<td width="8%" nowrap align="right" class="detail_label">省份：</td>
				<td width="15%" class="detail_content">
					<input type="text" value="${params.PROV}" id="PROV" name="PROV"/>
					<img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[1]','PROV', 'PROV', '')">
				</td>
				<td width="8%" nowrap align="right" class="detail_label">
				   <input type="checkbox" value="1" id="IS_CANCELED_FLAG" 
				     <c:if test="${params.IS_CANCELED_FLAG eq 1}">checked="checked"</c:if>
				    name="IS_CANCELED_FLAG" />
				</td>
				
				<td width="15%" class="detail_content">
				  显示已取消
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
    SelDictShow("STATE","BS_126","${params.STATE}","");
    SelDictShow("BILL_TYPE","BS_124","${params.BILL_TYPE}","");
    
    
     //初始化 审批流程
    spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
</html>
