<!-- 
 *@modul 销售管理
 *@func 信用额度设定
 *@version 1.1
 *@author  郭利伟
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>信用额度列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/credit/Credit_List.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;信用管理&gt;&gt;信用额度设定</td>
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
				   		<c:if test="${pvg.PVG_EDIT eq 1 }">
					   		<input id="modify" type="button" class="btn" value="额度设定(U)" title="Alt+U" accesskey="U">
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
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th nowrap align="center" dbname="CHANN_NO" >渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME" >渠道名称</th>
					<th nowrap align="center" dbname="CHANN_TYPE" >渠道类别</th>
					<th nowrap align="center" dbname="INI_CREDIT" >初始信用额度</th>
<!--					<th nowrap align="center" dbname="CREDIT_CURRT" >当前信用额度</th>-->
					<th nowrap align="center" dbname="AREA_NO" >区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME" >区域名称</th>
					<th nowrap align="center" dbname="CREDIT_CRE_NAME" >信用修改人</th>
					<th nowrap align="center" dbname="CREDIT_CRE_TIME" >信用修改时间</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.CHANN_ID}"/>
						</td>
						<td align="center">${rst.CHANN_NO }&nbsp;</td>
						<td align="left">${rst.CHANN_NAME }&nbsp;</td>
						<td align="left">${rst.CHANN_TYPE}&nbsp;</td>
						<td align="right">${rst.INI_CREDIT}&nbsp;</td>
<!--						<td align="right">${rst.CREDIT_CURRT}&nbsp;</td>-->
						<td align="center">${rst.AREA_NO}&nbsp;</td>
						<td align="left">${rst.AREA_NAME}&nbsp;</td>
						<td align="left">${rst.CREDIT_CRE_NAME}&nbsp;</td>
						<td align="center">${rst.CREDIT_CRE_TIME}&nbsp;</td>
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
						<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">
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
<form id="queryForm" method="post" action="#" name="queryForm">
<input type="hidden" name="selectParams" value=" STATE in( '启用')">
<input type="hidden" id="selectChannParams" name="selectChannParams" value=" STATE in( '启用') ">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td nowrap align="right" class="detail_label">渠道编号：</td>
					<td class="detail_content">
						<input json="CHANN_NO" name="CHANN_NO" id="CHANN_NO" type="text" label="渠道编号" inputtype="string"  autocheck="true" value="${prarms.CHANN_NO}" >
						<input json="CHANN_ID" name="CHANN_ID" id="CHANN_ID" type="hidden" label="渠道ID">
						<img align="absmiddle" name="selCHANN_NO_P" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'queryForm','CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_TYPE,CHAA_LVL', 'CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_TYPE,CHAA_LVL', 'selectChannParams')">
					</td>
					<td nowrap align="right" class="detail_label">渠道名称：</td>
					<td class="detail_content">
						<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" type="text" inputtype="string"  autocheck="true" value="${params.CHANN_NAME }">
					</td>
					<td nowrap align="right" class="detail_label">渠道类别：</td>
					<td class="detail_content">
						<select id="CHANN_TYPE" name="CHANN_TYPE" style="width:155" ></select>
					</td>
					<td nowrap align="right" class="detail_label">渠道级别：</td>
					<td class="detail_content">
						<select id="CHAA_LVL" name="CHAA_LVL" style="width:155" ></select>
					</td>
				</tr>
				<tr>
					<td nowrap align="right" class="detail_label">区域编号：</td>
					<td class="detail_content">
						<input json="AREA_ID" name="AREA_ID" id="AREA_ID" type="hidden" label="区域ID">
						<input json="AREA_NO" name="AREA_NO" id="AREA_NO" type="text" label="区域编号" inputtype="string"  autocheck="true" mustinput="true" value="${params.AREA_NO}" >
						<img align="absmiddle" name="selAREA_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'queryForm','AREA_ID,AREA_NO,AREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME', 'selectParams')">
					</td>
					<td nowrap align="right" class="detail_label">区域名称：</td>
		      		<td nowrap class="detail_content">
						<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" type="text" value="${params.AREA_NAME}">
		      		</td>
					<td nowrap align="right" class="detail_label">信用修改时间从：</td>
		      		<td nowrap class="detail_content">
		            	<input type="text" json="UPD_TIME_BEGIN" id="UPD_TIME_BEGIN" name="UPD_TIME_BEGIN" autocheck="true" inputtype="string" value="${params.UPD_TIME_BEGIN}"
							label="日期" onclick="SelectTime();" READONLY />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(UPD_TIME_BEGIN);"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">信用修改时间到：</td>
		      		<td nowrap class="detail_content">
		            	<input type="text" json="UPD_TIME_END" id="UPD_TIME_END" name="UPD_TIME_END" autocheck="true" inputtype="string" value="${params.UPD_TIME_END}"
							label="日期" onclick="SelectTime();" READONLY />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(UPD_TIME_END);"/>
		      		</td>
				</tr>
				<tr>
					<td nowrap align="right" class="detail_label">制单时间从：</td>
		      		<td nowrap class="detail_content">
		            	<input type="text" json="CREDIT_CRE_TIME_FROM" id="CREDIT_CRE_TIME_FROM" name="CREDIT_CRE_TIME_FROM" autocheck="true" inputtype="string" value="${params.CREDIT_CRE_TIME_FROM}"
							label="日期" onclick="SelectTime();" READONLY />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(CREDIT_CRE_TIME_FROM);"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">制单时间到：</td>
		      		<td nowrap class="detail_content">
		            	<input type="text" json="CREDIT_CRE_TIME_TO" id="CREDIT_CRE_TIME_TO" name="CREDIT_CRE_TIME_TO" autocheck="true" inputtype="string" value="${params.CREDIT_CRE_TIME_TO}"
							label="日期" onclick="SelectTime();" READONLY />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(CREDIT_CRE_TIME_TO);"/>
		      		</td>
					<td nowrap align="right" class="detail_label">信用修改人：</td>
		      		<td nowrap class="detail_content">
		      			<input json="CREDIT_CRE_NAME" name="CREDIT_CRE_NAME" id="CREDIT_CRE_NAME" type="text" label="信用修改人" inputtype="string"  autocheck="true" value="${params.CREDIT_CRE_NAME}">
		      			<input json="RYXXID" name="RYXXID" id="RYXXID" type="hidden" label="信用修改人ID">
						<img align="absmiddle" name="selCHANN_NO_P" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_0', false, 'RYXXID', 'RYXXID', 'queryForm','RYXXID,CREDIT_CRE_NAME', 'RYXXID,XM')">
		      		</td>	
		      		<td nowrap align="right" class="detail_label"></td>
		      		<td nowrap class="detail_content"></td>	
		      		<td nowrap align="right" class="detail_label"></td>
		      		<td nowrap class="detail_content"></td>	
		      		<td nowrap align="right" class="detail_label"></td>
		      		<td nowrap class="detail_content"></td>	
				</tr>
				<tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
<script language="JavaScript">  
	SelDictShow("CHANN_TYPE","187","${params.CHANN_TYPE }","");
	SelDictShow("CHAA_LVL","188","${params.CHAA_LVL }","");
</script>
</body>
</html>