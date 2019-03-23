<!-- 
 *@module渠道管理-工作计划管理
 *@func  工作计划维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<title>工作计划维护详情</title>
	</head>
	<body class="bodycss1">
		<table width="100%" border="0" cellpadding="4" cellspacing="4"
			class="">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
							<td width="15%" align="right" class="detail_label">&nbsp;  
								工作计划维护编号：  
							</td>
							<td width="35%" class="detail_content">
								 ${rst.WORK_PLAN_NO}
							</td>
							<td width="15%" align="right" class="detail_label">
								战区编号：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.WAREA_NO}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								战区名称：
							</td>
							<td width="35%" class="detail_content">
                                 ${rst.WAREA_NAME} 							 
							</td>
							<td width="15%" align="right" class="detail_label">
								 计划年份：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.PLAN_YEAR}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								计划月份：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.PLAN_MONTH}
							</td>
							<td width="15%" align="right" class="detail_label">
								总上报数：
							</td>
							<td width="35%" class="detail_content">
								${rst.TOTAL_UP_REPORT_NUM}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								状态：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.STATE}
							</td>
							<td width="15%" align="right" class="detail_label">
								备注：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.REMARK}
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="detail_label">
								制单人：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CRE_NAME}
							</td>
							<td width="15%" align="right" class="detail_label">
								制单时间：
							</td>
							<td width="35%" class="detail_content">
								 ${rst.CRE_TIME}
							</td>
						</tr>
						<tr>
	                   <td  class="detail_label">
	                                                         流程跟踪：
	                   </td>
	                   <td colspan="3" class="detail2">
	                     <table id="ordertb" width="99.98%" border="0" cellpadding="1" cellspacing="1" class="lst" >
							<tr  >
								<th nowrap align="center">序号</th>
								<th nowrap align="center" >操作者</th>
								<th nowrap align="center" >操作</th>
								<th nowrap align="center" >时间</th>
								<th nowrap align="center" >意见</th>
								<th nowrap align="center" >下一操作者</th>
							</tr>
							<c:forEach items="${flowInfoList}" var="flow" varStatus="row">
							<c:set var="r" value="${row.count % 2}"></c:set>
							<c:set var="rr" value="${row.count}"></c:set>
							<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
								<td nowrap align="center" width="10%">${rr}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATORNAME}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATION}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATETIME}&nbsp;</td>
								<td nowrap align="center" width="400px;" style="table-layout:fixed;word-break:break-all; word-wrap:break-word;" >${flow.REMARK}&nbsp;</td>
								<td nowrap align="center" width="20%">${flow.NEXTOPERATORNAME}&nbsp;</td>
							</tr>
							</c:forEach>
							<c:if test="${empty flowInfoList}">
							<tr>
								<td height="25" colspan="13" align="center" class="lst_empty">&nbsp;草拟，尚未进入审核流程!&nbsp;</td>
							</tr>
							</c:if>
						</table>
	                   </td>
	                 </tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
