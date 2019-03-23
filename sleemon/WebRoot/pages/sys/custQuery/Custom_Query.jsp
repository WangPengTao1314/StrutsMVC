<%-- 
  *@module 系统管理
  *@func 自定义查询
  *@version 1.1
  *@author zhuxw
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">  
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/pages/sys/custQuery/Custom_Query.js"></script>
		<title>自定义查询</title>
	</head>
	<body class="bodycss1">	
		<table height="100%" cellspacing="0" cellpadding="0" width="99.9%">
			<tr>
				<td height="20">
					<table width="100%" cellspacing="0" cellpadding="0" >
					<tr>
						<td>当前位置：系统管理&gt;&gt;通用查询&gt;&gt;通用查询</td>
						<td width="50" align="right"></td>
					</tr>
				  </table>  
				</td>
			</tr>
			<tr>
				<td height="20">
					<div class="tablayer" >
						<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
							<tr>
							   <td nowrap> 
					   					<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N"> 
					   				    <input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F"> 
						   				<input id="open" type="button" class="btn" value="启用(R)" title="Alt+R" accesskey="R">
						   				<input id="close" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<form name="qryForm" action="customQuery.shtml?action=displayRpt" method="post" id = "qryForm">
					<input type="hidden" name = "hidRptPk" id = "hidRptPk" value="${hidRptPk}"/>
					<input type="hidden" name = "queryType" id = "queryType" value=""/>
					<div class="lst_area">
						<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
							<tr class="fixedRow">
								<th width="1%"></th>
								<th nowrap align="center" dbname="RPT_CODE" width="5%">报表编号</th>
								<th nowrap align="center" dbname="RPT_NAME" width="10%">报表名称</th>
								<th nowrap align="center" dbname="CRENAME" width="10%">制表人</th>
								<th nowrap align="center" dbname="CRETIME" width="10%">制定时间</th>
								<th nowrap align="center" dbname="RPT_SQL" width="10%">报表SQL</th>
								<th nowrap align="center" dbname="REMARK" width="10%">说明</th>
								<th nowrap align="center" dbname="STATE" width="10%">权限</th>		
								<th nowrap align="center" dbname="STATUS" width="10%">状态</th>
								<th nowrap align="center" width="15%">操作</th>																																
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));"> 
									<td nowrap align="center" width="5%">
										<input type="radio" name="eid" id = "eid${rr}" value="${rst.RPTMODELID}" onclick="setSelOperateEx(this)"/>
									</td>
									<td nowrap align="left" width="10%">${rst.RPT_CODE}&nbsp;</td>
									<td nowrap align="left" width="10%">${rst.RPT_NAME}&nbsp;</td>
									<td nowrap align="left" width="10%">${rst.CRENAME}&nbsp;</td>
									<td nowrap align="center" width="10%">${rst.CRETIME}&nbsp;</td> 	
								    <td nowrap align="center" width="10%">
								    	<a href="#" onclick="doOpen('sql${rr}')";>报表SQL......</a>
								    	<input type="hidden" name = "sql${rr}" id = "sql${rr}" value = "${rst.RPT_SQL}"/>
								    </td>
									<td nowrap align="left" width="10%">${rst.REMARK}&nbsp;</td>
									<td nowrap align="center" width="10%">
										<c:if test="${rst.IS_PRIVILEGE eq 0 }">
											任何人
										</c:if>
										<c:if test="${rst.IS_PRIVILEGE eq 1 }">
											设置权限
										</c:if> 
									</td>
									<td nowrap align="center" width="10%">
										<c:if test="${rst.STATE eq 1 }">
											启用
										</c:if>
										<c:if test="${rst.STATE eq 2 }">
											停用
										</c:if>
										<c:if test="${rst.STATE eq 0 }">
											创建
										</c:if>
									</td>
									<td nowrap align="center" width="15%">
											<input id="edit${rr}" type="button" class="btn" value="修改(E)" title="Alt+E" accesskey="E" onclick = "doEdit('${rst.RPTMODELID}')">
								            <input id="delete${rr}" type="button" class="btn" value="删除(I)" title="Alt+I" accesskey="I" onclick = "doDelete('${rst.RPTMODELID}')"> 
										    <input id="display${rr}" type="button" class="btn" value="展示(D)" title="Alt+D" accesskey="D" onclick = "displayRpt('${rst.RPTMODELID}')"> 
										<c:if test="${rst.STATE eq 2}">
											<script type="text/javascript">
											  	btnDisable(["display${rr}"]); 
											  	//btnDisable(["delete${rr}"]);
											</script>
										</c:if>
										<c:if test="${rst.STATE eq 1 }">
											<script type="text/javascript">
											  	btnDisable(["edit${rr}"]);
											  	btnDisable(["delete${rr}"]);
											</script>
										</c:if>
										<c:if test="${rst.STATE eq 0 }">
											<script type="text/javascript">  
											  	btnDisable(["edit${rr}"]);
											  	btnDisable(["display${rr}"]);
											</script>
										</c:if>
									</td>			
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
					</form>
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
				<input type="hidden" id="search" name="search" value='${search}'/>
					<tr>
						<td class="detail2">
							<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
								<tr>
									<td width="10%" nowrap align="right" class="detail_label">报表编号：</td>
									<td width="23%" class="detail_content"> 
										<input name="selRptCode" size="10" type="text" value="${params.selRptCode }" seltarget="selRptCode" > 
									</td>
									<td width="10%" nowrap align="right"class="detail_label">报表名称：</td>
									<td width="23%" class="detail_content">
										<input name="selRptName" size="10" value="${params.selRptName }" seltarget="selRptName" type="text" > 
									</td>
									<td width="10%" nowrap align="right" class="detail_label" ></td>
									<td width="24%" class="detail_content">  </td>
									<td width="10%" nowrap align="right" class="detail_label" ></td>
									<td width="24%" class="detail_content"> </td>
								</tr>
				
								<tr><td colspan="10">&nbsp;</td></tr>
								<tr>
									<td colspan="10" align="center" valign="middle">
										<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
										<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
