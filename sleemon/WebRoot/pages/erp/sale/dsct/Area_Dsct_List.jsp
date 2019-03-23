<!-- 
 *@module 系统管理
 *@func 区域折扣信息
 *@version 1.0
 *@author 王志格
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>产品价格查询信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/dsct/Area_Dsct_List.js"></script>

</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;价格管理&gt;&gt;产品价格查询</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			
		<form name="form" method="post" action="${ctx}/dsct.shtml?action=toList">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
				 <td nowrap>
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
						<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					    <input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
					    <input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
				   </c:if>
			       <c:if test="${pvg.PVG_BWS eq 1 }">
			        <select id="DECT_TYPE" name="DECT_TYPE" style="width:155px" >
			        </select>
			   		<input id="query" type="submit" class="btn" value="查询(F)" title="Alt+F" accesskey="F"/>	
			      </c:if>
				 </td>
				</tr>
			</table>
		</form>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
				    <th nowrap align="center" ></th>
					<th nowrap align="center" dbname="AREA_NO" >区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME" >区域名称</th>
					<th nowrap align="center" dbname="PRD_NO" >货品编号</th>
					<th nowrap align="center" dbname="PRD_NAME" >货品名称</th>
					<th nowrap align="center" dbname="PRD_SPEC" >规格</th>
					<th nowrap align="center" dbname="DECT_TYPE" >折扣类型</th>
					<th nowrap align="center" dbname="DECT_RATE" >折扣率</th>
					<th nowrap align="center" dbname="BASE_PRICE" >基准价</th>
					<th nowrap align="center" dbname="DECT_PRICE" >折扣价</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
				
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%"align='center' >
						 <input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.AREA_DSCT_ID}"/>
					    </td>
						<td align="center">
						 ${rst.AREA_NO}&nbsp;
						 <input type="hidden" name="AREA_ID" id="AREA_ID_${rst.AREA_DSCT_ID}" value="${rst.AREA_ID}"/>
						</td>
						<td align="left">${rst.AREA_NAME}&nbsp;</td>
						<td align="center">${rst.PRD_NO}&nbsp;</td>
						<td align="left">${rst.PRD_NAME}&nbsp;</td>
						<td align="center">${rst.PRD_SPEC}&nbsp;</td>
						<td align="center">${rst.DECT_TYPE}&nbsp;</td>
						<td align="right">${rst.DECT_RATE}&nbsp;</td>
						<td align="right">${rst.BASE_PRICE}&nbsp;</td>
						<td align="right">${rst.DECT_PRICE}&nbsp;</td>
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

<script language="JavaScript">  
		SelDictShow("DECT_TYPE","BS_7","${params.DECT_TYPE }","");	
</script>
</body>
</html>
