
<!--  
/**
 * @module 系统管理
 * @func 未排货品查看
 * @version 1.1
 * @author 张忠斌
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>未排货品查看</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20px" valign="top">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
			       <td nowrap>
					</td>
				</tr>
			</table>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow"><%--
						<th width="1%"></th>
                    --%><th  nowrap="nowrap" align="center" dbname="GOODS_ORDER_NO" >订货订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_TYPE" >发货类型</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_COLOR" >花号</th>
                    <th  nowrap="nowrap" align="center" dbname="BRAND" >品牌</th>
                    <th  nowrap="nowrap" align="center" dbname="STD_UNIT" >标准单位</th>
                    <th  nowrap="nowrap" align="center" dbname="PRICE" >单价</th>
                    <th  nowrap="nowrap" align="center" dbname="HAVE_NUM" >未发数量</th>
                    <th  nowrap="nowrap" align="center" dbname="ADJUST_NUM" >调整</th>
                    <th  nowrap="nowrap" align="center" dbname="VOLUME" >体积</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<%--<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.AREA_ID}"/>
						</td>
                     --%><td nowrap="nowrap" align="center">${rst.GOODS_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SEND_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PRD_COLOR}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BRAND}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STD_UNIT}</td>
                     <td nowrap="nowrap" align="right">${rst.PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.HAVE_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >
                       <c:if test="${rst.SEND_TYPE eq '总部未发'}">
                         <input type="text" name="ADJUST_NUM" style="text-align: right" value="${rst.HAVE_NUM}" style="width: 100px;" onchange="countCar();"  readonly="readonly"/>
                       </c:if>
                       <c:if test="${rst.SEND_TYPE ne '总部未发'}">
                         <input type="text" name="ADJUST_NUM" style="text-align: right" value="${rst.HAVE_NUM}" style="width: 100px;" onchange="countCar();" />
                       </c:if>
                     </td>
                     <td nowrap="nowrap" align="right" name="cuur_VOLUME">${rst.VOLUME}&nbsp;</td>
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
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
					${paramCover.unCoveredForbidInputs}
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


<div id="querydiv" class="query_div" >
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
<input type="hidden" name="selectParams" value=" STATE in('启用','停用','制定') " />
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="8%" nowrap align="right" class="detail_label" >区域编号：</td>
					<td nowrap width="15%" class="detail_content">
					
						<input type="text" name="AREA_NO" id="AREA_NO" seltarget="selAREA" value="${params.AREA_NO}" inputtype="string"  autocheck="true" />
                       
                        <input type="hidden" name="AREA_ID1" id="AREA_ID1"  seltarget="selAREA" value="${params.AREA_ID}">
                        
						 <img align="absmiddle" name="selAREA" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('BS_18', false, 'AREA_ID1', 'AREA_ID', 'forms[1]','AREA_NO,AREA_NAME', 'AREA_NO,AREA_NAME', 'selectParams')"> 								   		
					</td>
					<td width="8%" nowrap align="right" class="detail_label">区域名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="AREA_NAME" name="AREA_NAME" id="AREA_NAME"  seltarget="selAREA" value="${params.AREA_NAME}"/>
					</td>
			 
			   	<td width="8%" nowrap align="right" class="detail_label" >上级区域编号：</td>
					<td nowrap width="15%" class="detail_content" >
						<input type="text" name="AREA_NO_P" seltarget="selAREA_P" value="${params.AREA_NO_P}"/>
                        <input type="hidden" id="AREA_ID_P" name="AREA_ID_P"  seltarget="selAREA_P" value="${params.AREA_ID_P }">
						 <img align="absmiddle" name="selSJJG" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
						onClick="selCommon('BS_18', false, 'AREA_ID_P', 'AREA_ID', 'forms[1]','AREA_NO_P,AREA_NAME_P', 'AREA_NO,AREA_NAME', 'selectParams')"> 								   		  								   		
					</td> 
					<td width="8%" nowrap align="right" class="detail_label">上级区域名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="AREA_NAME_P" name="AREA_NAME_P" seltarget="selAREA_P" value="${params.AREA_NAME_P}"/>
					</td>
					
					
				</tr>
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE" style="width:155" ></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
				</tr>
				<tr><td colspan="10">&nbsp;</td></tr>
				<tr>
					<td colspan="10" align="center" valign="middle" >
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">&nbsp;&nbsp;
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
<script language="JavaScript">
	//$(function(){
		SelDictShow("STATE","32","${params.STATE}","");
	//});
</script>
</html>
