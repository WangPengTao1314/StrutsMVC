<!-- 
 *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *  
 -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/forecasttaskup/ForecastTaskupAudit_List.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;预计量上报管理&gt;&gt;预计量上报</td>
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
				    <c:if test="${pvg.PVG_BWS_AUDIT eq 1 }">
				   		<input id="audit"  type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
				   	    <input id="cancel" type="button" class="btn" value="弃审(C)" title="Alt+C" accesskey="C">
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
					<th nowrap align="center" dbname="ADVC_RPT_JOB_NO" >上报任务编号</th>
					<th nowrap align="center" dbname="YEAR" >年份</th>
					<th nowrap align="center" dbname="MONTH" >月份</th>
					<th nowrap align="center" dbname="CHANN_NO" >渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME" >渠道名称</th>
					<th nowrap align="center" dbname="CRE_NAME" >创建人</th>
					<th nowrap align="center" dbname="CRE_TIME" >创建时间</th>
					<th nowrap align="center" dbname="SENDER_TIME" >发布时间</th>
					<th nowrap align="center" dbname="SUBMIT_TIME">提交时间</th>
					<th nowrap align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.RPT_JOB_CHANN_ID}"/>
						</td>
						
						<td align="center">${rst.ADVC_RPT_JOB_NO}&nbsp;</td>
						<td align="center">${rst.YEAR}&nbsp;</td>
						<td align="center">${rst.MONTH }&nbsp;</td>
						<td align="center">${rst.CHANN_NO}&nbsp;</td>
						<td align="center">${rst.CHANN_NAME}&nbsp;</td>
						<td align="center">${rst.CRE_NAME}&nbsp;</td>
						<td align="center">${rst.CRE_TIME}&nbsp;</td>
				        <td align="center">${rst.SENDER_TIME}&nbsp;</td>
				        <td align="center">${rst.SUBMIT_TIME}</td>
				        <td align="center">${rst.STATE}&nbsp;</td>
				 	    <input type="hidden" id="state${rst.RPT_JOB_CHANN_ID}" name="STATE" value="${rst.STATE}"/> 
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
 <input type="hidden" name="selectPYXXParams" value=" YHZT = '启用' " />
 <input type="hidden" name="selectParams" value=" STATE in('启用','停用') " />

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td nowrap align="right" class="detail_label">上报任务编号：</td>
					<td class="detail_content">
						<input name="ADVC_RPT_JOB_NO"  type="text"   value="${params.ADVC_RPT_JOB_NO}"/>
					</td>
		      	
					<td nowrap align="right" class="detail_label">发布人名称：</td>
		      		<td nowrap class="detail_content">
					 <input type="hidden" id="SENDER_ID" name="SENDER_ID" json="SENDER_ID" value="${param.SENDER_ID}"  />
                     <input type="text"  id="SENDER_NAME" name="SENDER_NAME" json="SENDER_NAME"  value="${rst.SENDER_NAME}" />&nbsp;
                     <img align="absmiddle" name="selJGXX"  
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('System_7', false, 'SENDER_ID', 'XTYHID', 'forms[1]','SENDER_NAME', 'XM', 'selectPYXXParams')">
					</td>
					 <td width="16%" align="right" class="detail_label"> 发布时间从：</td>
					<td width="15%" class="detail_content">
                     <input type="text" id="SENDER_TIME_BEG" name="SENDER_TIME_BEG" json="SENDER_TIME_BEG" onclick="SelectTime();"   value="${params.SENDER_TIME_BEG}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(SENDER_TIME_BEG);">
				   </td>
				   <td width="16%" align="right" class="detail_label"> 发布时间到：</td>
					<td width="35%" class="detail_content">
                     <input type="text" id="SENDER_TIME_END" name="SENDER_TIME_END" json="SENDER_TIME_END" onclick="SelectTime();"   value="${param.SENDER_TIME_END}" >
					 <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(SENDER_TIME_END);">
				   </td>
				</tr>
				<tr>
				   <td width="10%" nowrap align="right" class="detail_label">渠道编号：</td>
				   <td width="15%" class="detail_content">
				     	<input id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" type="hidden"  value="${params.CHANN_ID}">
						<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text"   value="${params.CHANN_NO}" >
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', true, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams')">
					</td>
				    <td width="10%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" value="${params.CHANN_NAME }"/>
					</td>
					
				    <td nowrap align="right" class="detail_label">年份：</td>
					<td class="detail_content">
						 <input type="text"  id="YEAR" name="YEAR" value="${param.YEAR}"  />&nbsp;
	                     <img align="absmiddle" name="selJGXX"  
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_139', false, 'YEAR', 'SJXZ', 'forms[1]','YEAR', 'SJXZ', '')">
					</td>
					<td nowrap align="right" class="detail_label">月份：</td>
					<td class="detail_content">
					 <input type="text"  id="MONTH" name="MONTH" value="${param.MONTH}"  />&nbsp;
                     <img align="absmiddle" name="selJGXX"  
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_140', false, 'MONTH', 'SJXZ', 'forms[1]','MONTH', 'SJXZ', '')">
					</td>
			</tr>
			<tr>
			       <td nowrap align="right" class="detail_label">状态：</td>
					<td class="detail_content">
						<select id="STATE" name="STATE" style="width:145" ></select>
					</td>
					<td nowrap align="right" class="detail_label">制单时间从：</td>
		      		<td nowrap class="detail_content">
		            	<input type="text" json="CRE_TIME_BEGIN" id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN"  value="${params.CRE_TIME_BEGIN}"
							 onclick="SelectTime();" READONLY />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(CRE_TIME_BEGIN);"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">制单时间到：</td>
		      		<td nowrap class="detail_content">
		            	<input type="text" json="CRE_TIME_END" id="CRE_TIME_END" name="CRE_TIME_END"  value="${params.CRE_TIME_END}"  onclick="SelectTime();" READONLY />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(CRE_TIME_END);"/>
		      		</td>	
					<td nowrap align="right" class="detail_label"></td>
					<td class="detail_content"> </td>
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
	SelDictShow("STATE","BS_65","${params.STATE }","");	
</script>
</body>
</html>