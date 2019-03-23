<!-- 
 /**
 *@module 基础信息
 *@func 系统日志
 *@version 1.1
 *@author  guhongxiu
 */
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>系统日志列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/base/syslog/Syslog_List.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;基础信息&gt;&gt;系统日志</td>
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
				  		 <input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">	
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
					<th nowrap align="center" dbname="SYSLOG_ID" >日志编号</th>
					<th nowrap align="center" dbname="UC_NAME" >模块名称</th>
					<th nowrap align="center" dbname="ACT_NAME" >操作名称</th>
					<th nowrap align="center" dbname="OPRATOR" >调用类型</th>
					<th nowrap align="center" dbname="ACT_TIME" >操作时间</th>
					<th nowrap align="center" dbname="STATE" >状态</th>							
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.SYSLOG_ID}"/>
						</td>
						<td align="center">${rst.SYSLOG_ID }&nbsp;</td>
						<td align="left">${rst.UC_NAME }&nbsp;</td>
						<td align="left">${rst.ACT_NAME}&nbsp;</td>
						<td align="left">${rst.OPRATOR}&nbsp;</td>
						<td align="center">${rst.ACT_TIME}&nbsp;</td>
						<td align="left">${rst.STATE}&nbsp;</td>						
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

<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>					
					<td nowrap align="right" class="detail_label">模块名称：</td>
					<td class="detail_content">
						<input name="UC_NAME" id="UC_NAME" type="text"  value="${params.UC_NAME }">
					</td>
					<td nowrap align="right" class="detail_label">操作名称：</td>
					<td class="detail_content">
						<input name="ACT_NAME" id="ACT_NAME" type="text"  value="${params.ACT_NAME }">
					</td>
					<td nowrap align="right" class="detail_label">状态：</td>
		      		<td nowrap class="detail_content">
						<select id="STATE" name="STATE" style="width:145" ></select>
		      		</td>							      		
				</tr>				
				<tr>
					<td nowrap align="right" class="detail_label">操作时间从：</td>
		      		<td nowrap class="detail_content">
		            	<input type="text" json="ACT_TIME_FROM" id="ACT_TIME_FROM"name="ACT_TIME_FROM" autocheck="true" inputtype="string" value="${params.ACT_TIME_FROM}"
							label="日期" onclick="SelectTime();"  READONLY/>&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(ACT_TIME_FROM);"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">操作时间到：</td>
		      		<td nowrap class="detail_content">
		            	<input type="text" json="ACT_TIME_TO" id="ACT_TIME_TO"name="ACT_TIME_TO" autocheck="true" inputtype="string" value="${params.ACT_TIME_TO}" 
							label="日期" onclick="SelectTime();" READONLY />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(ACT_TIME_TO);"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label"></td>
		      		<td nowrap class="detail_content">
						
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
<script language="JavaScript">  
	SelDictShow("STATE","195","${params.STATE }","");
</script>
</body>
</html>