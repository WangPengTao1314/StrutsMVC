<!-- 
 *@module 渠道管理-分销商管理
 *@function   分销商购货月报
 *@version 1.1
 *@author gu_hx
 *  -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	    <script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>分销商购货月报详情</title>
	</head>
	<body class="bodycss1" >
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="" style="margin-top: -20px;">
			<tr>   
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						<tr>
							<td width="15%" align="right" class="detail_label">分销商数据上报编号：</td>
							<td width="35%" class="detail_content">${rst.DISTRIBUTOR_SALE_RPT_NO}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">提报人：</td>
							<td width="35%" class="detail_content">${rst.CRE_NAME}&nbsp;</td>
					     </tr>
					     <tr>
							<td width="15%" align="right" class="detail_label">提报时间：</td>
							<td width="35%" class="detail_content">${rst.CRE_TIME}&nbsp;</td>
							<td width="15%" align="right" class="detail_label">提报人渠道名称：</td>
							<td width="35%" class="detail_content">${rst.CHANN_NAME}&nbsp;</td>
					    </tr>
						<tr>
						    <td width="15%" align="right" class="detail_label">月报时间：</td>
							<td width="35%" class="detail_content">
								${rst.YEAR}年&nbsp;
				   				${rst.MONTH}月&nbsp;
							</td>
							<td width="15%" align="right" class="detail_label">状态：</td>
							<td width="35%" class="detail_content">${rst.STATE}&nbsp;</td>
						</tr>						
					 </table>
				</td>
			</tr>			
			<tr>
				<td class="detail2">
					<span id="label_1" class="label_down" style="margin-top:2px;">&nbsp;明细信息&nbsp;</span>				
					<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
						<tr class="fixedRow">
			              <th nowrap align="center" >分销商编号</th>
			              <th nowrap align="center">分销商名称</th>
			              <th nowrap align="center">分销类型</th>
			              <th nowrap align="center">货品编号</th>
			              <th nowrap align="center">货品名称</th>
			              <th nowrap align="center">规格型号</th>
			              <th nowrap align="center">提货数量</th>
			              <th nowrap align="center">提货金额</th>
			            </tr>
			            <c:forEach items="${rst.childList}" var="child" varStatus="row">
							<c:set var="r" value="${row.count % 2}"></c:set>
							<c:set var="rr" value="${row.count}"></c:set>
							<tr class="list_row${r}">							 
		                     <td nowrap="nowrap" align="center" >
		                     	${child.DISTRIBUTOR_NO}&nbsp;
		                     </td>
		                     <td nowrap="nowrap" align="left" >
		                     	${child.DISTRIBUTOR_NAME}&nbsp;
		                     </td>
		                     <td nowrap="nowrap" align="center" >
		                     	${child.DISTRIBUTOR_TYPE}&nbsp;
		                     </td>
		                     <td nowrap="nowrap" align="center" >
		                     	${child.PRD_NO}&nbsp;
		                     </td>
		                     <td nowrap="nowrap" align="left" >
		                     	${child.PRD_NAME}&nbsp;
		                     </td>		                     
		                     <td nowrap="nowrap" align="left" >
		                     	${child.PRD_SPEC}&nbsp;
		                     </td>
		                     <td nowrap="nowrap" align="right" >
		                     	${child.STOREOUT_NUM}&nbsp;
		                     </td>
		                     <td nowrap="nowrap" align="right" >
		                     	${child.STOREOUT_AMOUNT}&nbsp;
		                     </td>
						    </tr>
						</c:forEach>
						<c:if test="${empty rst.childList}">
							<tr>
								<td height="25" colspan="15" align="center" class="lst_empty">
					                &nbsp;无相关记录&nbsp;
								</td>
							</tr>
						</c:if>
					</table>
				</td>
			</tr>	
		</table>
	</body>	
</html>
