<!-- 
 *@module 系统管理
 *@func 生产工厂维护
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
	<title>生产工厂列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/base/factory/Factory_List.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>

</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;基础信息&gt;&gt;生产工厂信息</td>
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
				    <input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N"/>
			   		<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U"/>
				  </c:if>
			   <c:if test="${pvg.PVG_DELETE eq 1 }">
				    <input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R"/>
				  </c:if>
			   <c:if test="${pvg.PVG_START_STOP eq 1 }">
			   		<input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q"/>	
			   		<input id="stop" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T"/>
			   </c:if>	
			    <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F"/>	
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
					<th nowrap align="center" dbname="FACTORY_NO" >生产工厂编号</th>
					<th nowrap align="center" dbname="FACTORY_NAME" >生产工厂名称</th>
					<th nowrap align="center" dbname="PERSON_CON" >联系人</th>
					<th nowrap align="center" dbname="MOBILE_NO" >手机</th>
					<th nowrap align="center" dbname="CRE_NAME" >制单人</th>
					<th nowrap align="center" dbname="CRE_TIME" >制单时间</th>
					<th nowrap align="center" dbname="DEPT_NAME" >制单部门</th>
					<th nowrap align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
				
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));
					changeButton($.trim($('#state${rst.FACTORY_ID}').text()));">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.FACTORY_ID}"/>
						</td>
						
						<td align="center">${rst.FACTORY_NO }&nbsp;</td>
						<td align="left">${rst.FACTORY_NAME }&nbsp;</td>
						<td align="left">${rst.PERSON_CON}&nbsp;</td>
						<td align="center">${rst.MOBILE_NO }&nbsp;</td>
						<td align="left">${rst.CRE_NAME }&nbsp;</td>
						<td align="center">${rst.CRE_TIME}&nbsp;</td>
						<td align="left">${rst.DEPT_NAME}&nbsp;</td>
						<td nowrap align="center" id="state${rst.FACTORY_ID}" value="${rst.STATE}">${rst.STATE}&nbsp;</td>
						
					    
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
<form id="queryForm" method="post" action="#">
<input name="conJGXX"  type="hidden" value=" JGZT in ('启用','停用')">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">生产工厂编号：</td>
					<td width="15%" class="detail_content">
						<input name="FACTORY_NO" type="text"  value="${params.FACTORY_NO }"></input>
					</td>
					
					<td width="8%" nowrap align="right" class="detail_label">生产工厂名称：</td>
					<td width="15%" class="detail_content">
						<input name="FACTORY_NAME" type="text" value="${params.FACTORY_NAME }"></input>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">联系人：</td>
					<td width="15%" class="detail_content">
						<input name="PERSON_CON" type="text" value="${params.PERSON_CON }"></input>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">手机：</td>
					<td width="15%" class="detail_content">
						<input name="MOBILE_NO" type="text" value="${params.MOBILE_NO }"></input>
				   </td>
				</tr>
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">制单时间从：</td>
					<td width="15%" class="detail_content">
						<input name="CRE_TIME_FROM" type="text" value="${params.CRE_TIME_FROM }" onclick="SelectTime();" readonly="readonly"></input>
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(CRE_TIME_FROM);"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到：</td>
					<td width="15%" class="detail_content">
						<input name="CRE_TIME_TO" type="text" value="${params.CRE_TIME_TO }" onclick="SelectTime();" readonly="readonly"></input>
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(CRE_TIME_TO);"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select id="STATE" name="STATE" style="width:155px" >
					    </select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"></td>
				</tr>
				<tr>
					<td colspan="8" align="center" valign="middle">
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
		SelDictShow("STATE","32","${params.STATE }","");	
</script>
</body>
</html>
