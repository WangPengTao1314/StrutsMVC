<!--
 * @module 营销管理-销售指标管理
 * @func   渠道销售指标设定维护
 * @version 1.1
 * @author zcx
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>信息详情</title>
</head>
<body class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													渠道销售指标编号：
												</td>
												<td width="35%" class="detail_content">
													${rst.SALE_PLAN_NO} &nbsp;
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道编号：
												</td>
												<td width="35%" class="detail_content">
													${rst.CHANN_NO} &nbsp;
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													渠道名称：
												</td>
												<td width="35%" class="detail_content">
													${rst.CHANN_NAME} &nbsp;												
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道简称：
												</td>
												<td width="35%" class="detail_content">
													${rst.CHANN_ABBR} &nbsp;	
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													渠道类型：
												</td>
												<td width="35%" class="detail_content">
													${rst.CHANN_TYPE} &nbsp;													
												</td>
												<td width="15%" align="right" class="detail_label">
													渠道级别：
												</td>
												<td width="35%" class="detail_content">
													${rst.CHAA_LVL} &nbsp;
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													 战区编号：
												</td>
												<td width="35%" class="detail_content">
													${rst.AREA_NO} &nbsp;
												</td>
												<td width="15%" align="right" class="detail_label">
													 战区名称：
												</td>
												<td width="35%" class="detail_content">
													${rst.AREA_NAME} &nbsp;
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  区域经理：
												</td>
												<td width="35%" class="detail_content">
													${rst.AREA_MANAGE_NAME} &nbsp;
												</td>
												<td width="15%" align="right" class="detail_label">
													计划年份：
												</td>
												<td width="35%" class="detail_content">
													${rst.PLAN_YEAR} &nbsp;
											</tr>
											<tr>
											    <td width="15%" align="right" class="detail_label" nowrap>
													 状态：
												</td>
												<td width="35%" class="detail_content">
													${rst.STATE} &nbsp;
												</td>	
												<td width="15%" align="right" class="detail_label" nowrap>
													 备注：
												</td>
												<td width="35%" class="detail_content">
													${rst.REMARK} &nbsp;
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