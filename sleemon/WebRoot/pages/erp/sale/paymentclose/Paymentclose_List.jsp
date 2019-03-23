<!--
 * @prjName:喜临门营销平台
 * @fileName:Paymentcmt_List
 * @author lyg
 * @time   2013-08-25 09:50:09 
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/paymentclose/Paymentclose_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;订单中心&gt;&gt;付款凭证关闭</td>
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
				   <c:if test="${pvg.PVG_CLOSE eq 1 }">
				    <input id="close" type="button" class="btn" value="关闭(C)" title="Alt+C" accesskey="C">
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
                    <th  nowrap="nowrap" align="center" dbname="PAYMENT_UPLOAD_NO" >付款凭证编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >上传时间</th>
                    <th  nowrap="nowrap" align="center" dbname="PAYMENT_NO" >凭证号</th>
                    <th  nowrap="nowrap" align="center" dbname="PAYMENT_AMOUNT" >金额</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >渠道编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >区域</th>
                    <th  nowrap="nowrap" align="center" dbname="REQ_PSON_NAME" >申请人</th>
                    <th  nowrap="nowrap" align="center" dbname="TEL" >联系方式</th>
                    <th  nowrap="nowrap" align="center" dbname="AUDIT_NAME" >审核人</th>
                    <th  nowrap="nowrap" align="center" dbname="AUDIT_TIME" >审核时间</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.PAYMENT_UPLOAD_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.PAYMENT_UPLOAD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PAYMENT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.PAYMENT_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.REQ_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.AUDIT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.AUDIT_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STATE}&nbsp;</td>
                      <input id="state${rst.PAYMENT_UPLOAD_ID}" label="状态" type="hidden"  value="${rst.STATE}" />
                      <input id="CHANN_ID${rst.PAYMENT_UPLOAD_ID}" label="渠道ID" type="hidden"  value="${rst.CHANN_ID}" />
                      <input id="PAYMENT_AMOUNT${rst.PAYMENT_UPLOAD_ID}" label="金额" type="hidden"  value="${rst.PAYMENT_AMOUNT}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
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
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			<input type="hidden" name="selectParams" value=" STATE in( '启用','停用')">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">凭证号:</td>
					<td width="15%" class="detail_content">
	   					<input id="PAYMENT_NO" name="PAYMENT_NO"   style="width:155" value="${params.PAYMENT_NO}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
                         <select json="STATE" id="STATE"  name="STATE" style="width:155px" ></select>
					</td>				
					<td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_BEG" name="CRE_TIME_BEG" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.CRE_TIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_END);">
					</td>		
               </tr>
               <tr>
					<td width="8%" nowrap align="right" class="detail_label">区域编号:</td>
					<td width="15%" class="detail_content">
						<input id="AREA_ID" json="AREA_ID" name="AREA_ID" type="hidden" inputtype="string" autocheck="true" value="${params.AREA_ID}">
	   					<input id="AREA_NO" name="AREA_NO"  style="width:155" value="${params.AREA_NO}"/>
	   					<img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30"
							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[1]','AREA_ID,AREA_NO,AREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME','selectParams')">
					</td>	
					<td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_NAME" name="AREA_NAME"  style="width:155" value="${params.AREA_NAME}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道编号:</td>
					<td width="15%" class="detail_content">
						<input id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" type="hidden" inputtype="string"  autocheck="true"  value="${params.CHANN_ID}">
	   					<input id="CHANN_NO" name="CHANN_NO"  style="width:155" value="${params.CHANN_NO}"/>
	   					<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME','selectParams')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">渠道名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="CHANN_NAME" name="CHANN_NAME"  style="width:155" value="${params.CHANN_NAME}"/>
					</td>	
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
</body>
<script type="text/javascript">
	  SelDictShow("STATE","BS_79","${params.STATE}","");
</script>
</html>
