<%-- 
  *@module 系统管理
  *@func 自定义查询
  *@version 1.1
  *@author zhuxw
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">  
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/pages/sys/custQuery/Custom_Query_Display.js"></script> 
		<title>自定义报表展示</title>
	</head>
	<body>
		<table width="100%" height="100%" border="0" class="panel">
			<tr>
				<td height="20">
					<table width="100%" cellspacing="0" cellpadding="0" >
					<tr>
						<td>当前位置：系统管理&gt;&gt;自定义查询&gt;&gt;自定义查询报表</td>
						<td width="50" align="right"></td>
					</tr>
				  </table>  
				</td>
			</tr> 
			<tr>
				<td valign="top">
					<form name="qryForm" action="customQuery.shtml?action=displayRpt" method="post" id = "qryForm">
					<input type="hidden" name = "hidRptPk" id = "hidRptPk" value="${hidRptPk}"/>
					<input type="hidden" name = "queryType" id = "queryType" value=""/>
					<input type="hidden" name = "currentPageNo" id = "currentPageNo" value="1"/>
					<div class="lst_area">
						<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
							<tr class="fixedRow"> 
								<th nowrap align="center" dbname="RPT_CODE" width="5%">报表编号</th>
								<th nowrap align="center" dbname="RPT_NAME" width="10%">报表名称</th> 
								<th nowrap align="center" width="15%">操作</th>																																
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">  
									<td nowrap align="center" width="10%">${rst.RPT_CODE}&nbsp;</td>
									<td nowrap align="left" width="10%">${rst.RPT_NAME}&nbsp;</td> 
									<td nowrap align="center" width="15%">
											<c:if test="${rst.STATE eq 1 }">
												<a href="#" onclick="displayRpt('${rst.RPTMODELID}')">查看报表</a>
											</c:if>
											<c:if test="${rst.STATE eq 2 }">
												己停用
											</c:if>
											<c:if test="${rst.STATE eq 0 }">
												创建中
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
		</table>
	</body>
</html>
