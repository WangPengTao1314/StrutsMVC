<!--  
/**
 * @module 系统管理
 * @func 供应商信息
 * @version 1.1
 * @author 张涛
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>供应商信息列表</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/provider/provider_List.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;基础信息&gt;&gt;供应商信息</td>
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
										<input id="add" type="button" class="btn" value="新增(N)"
											title="Alt+N" accesskey="N">
										<input id="modify" type="button" class="btn" value="修改(U)"
											title="Alt+U" accesskey="U">
									</c:if>
									<c:if test="${pvg.PVG_DELETE eq 1 }">
										<input id="delete" type="button" class="btn" value="删除(R)"
											title="Alt+R" accesskey="R">
									</c:if>
									<c:if test="${pvg.PVG_START_STOP eq 1 }">
										<input id="start" type="button" class="btn" value="启用(Q)"
											title="Alt+Q" accesskey="Q">
										<input id="stop" type="button" class="btn" value="停用(T)"
											title="Alt+T" accesskey="T">
									</c:if>
									<c:if test="${pvg.PVG_BWS eq 1 }">
										<input id="query" type="button" class="btn" value="查询(F)"
											title="Alt+F" accesskey="F">
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
					<th nowrap align="center" dbname="PRVD_NO">供应商编号</th>
					<th nowrap align="center" dbname="PRVD_NAME">供应商名称</th>
					<th nowrap align="center" dbname="PRVD_TYPE">供应商类别</th>
					<th nowrap align="center" dbname="PROV">省份</th>
					<th nowrap align="center" dbname="CITY">城市</th>
					<th nowrap align="center" dbname="PERSON_BUSS">业务员</th>
					<th nowrap align="center" dbname="CRE_NAME">制单人</th>
					<th nowrap align="center" dbname="CRE_TIME">制单时间</th>
					<th nowrap align="center" dbname="DEPT_NAME">制单部门</th>
					<th nowrap align="center" dbname="STATE">状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.PRVD_ID}"/>
						</td>
                        <td nowrap align="center">${rst.PRVD_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.PRVD_NAME}&nbsp;</td>
                        <td nowrap align="left">${rst.PRVD_TYPE}&nbsp;</td>
                        <td nowrap align="left">${rst.PROV}&nbsp;</td>
                        <td nowrap align="left">${rst.CITY}&nbsp;</td>
                        <td nowrap align="left">${rst.PERSON_BUSS}&nbsp;</td>
                        <td nowrap align="left">${rst.CRE_NAME}&nbsp;</td>
                        <td nowrap align="center">${rst.CRE_TIME}&nbsp;</td>
                        <td nowrap align="left">${rst.DEPT_NAME}&nbsp;</td>
                        <td nowrap  json='STATE' align="center">${rst.STATE}&nbsp;</td>                        
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
					${paramCover.unCoveredForbidInputs}
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
<div id="querydiv" class="query_div" >
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
<input type="hidden" id="selectContion" name="selectContion" value=" STATE in('启用') " />
<input type="hidden" id="selectContionYY" name="selectContionYY" value=" RYZT in('启用') " />
<input type="hidden" id="zoneConditionProv" name="zoneConditionProv" value="" />
<input type="hidden" id="zoneConditionCity" name="zoneConditionCity" value="" />
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="8%" nowrap align="right" class="detail_label" >供应商编号：</td>
					<td nowrap width="15%" class="detail_content">
						<input type="text" name="PRVD_NO" seltarget="selJGXX" value="${params.PRVD_NO }" inputtype="string"  autocheck="true" />
                        <input id="PRVD_ID" name="PRVD_ID" type="hidden" seltarget="selJGXX" value="${params.PRVD_ID }">
						<img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('BS_25', false, 'PRVD_ID', 'PRVD_ID', 'forms[1]','PRVD_NO,PRVD_NAME', 'PRVD_NO,PRVD_NAME', 'selectContion');">  								   		
					</td>
					<td width="8%" nowrap align="right" class="detail_label">供应商名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="PRVD_NAME" name="PRVD_NAME" seltarget="selJGXX" value="${params.PRVD_NAME }"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">供应商类别：</td>
					<td width="15%" class="detail_content">
						<select id="PRVD_TYPE" name="PRVD_TYPE" style="width:155" value="${rst.PRVD_TYPE}" ></select>
					</td>					
				</tr>
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">省份：</td>
					<td width="15%" class="detail_content">
						<input type="text" id="PROV" name="PROV" seltarget="selJGXX" value="${params.PROV }" inputtype="string"  autocheck="true" onChange="setProv()"/>
                        <input id="PRVD_ID" name="PRVD_ID" type="hidden" seltarget="selJGXX" value="${params.PRVD_ID }">
						<img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('BS_31', false, 'PRVD_ID', 'PROV', 'forms[1]','PROV', 'PROV', 'zoneConditionProv');setProv();">						
					</td>
					<td width="8%" nowrap align="right" class="detail_label">城市：</td>
					<td width="15%" class="detail_content">
						<input type="text" id="CITY" name="CITY" seltarget="selJGXX" value="${params.CITY}" inputtype="string"  autocheck="true""/>
                        <input id="PRVD_ID" name="PRVD_ID" type="hidden" seltarget="selJGXX" value="${params.PRVD_ID }">
						<img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('BS_32', false, 'PRVD_ID', 'CITY', 'forms[1]','CITY', 'CITY', 'zoneConditionCity');">						
					</td>
					<td width="8%" nowrap align="right" class="detail_label">业务员：</td>
					<td width="15%" class="detail_content">
						<input type="text" name="PERSON_BUSS" seltarget="selJGXX" value="${params.PERSON_BUSS }" inputtype="string"  autocheck="true" />
                        <input id="PRVD_ID" name="PRVD_ID" type="hidden" seltarget="selJGXX" value="${params.PRVD_ID }">
						<img align="absmiddle" name="selJGXX" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
								   		onClick="selCommon('BS_0', false, 'PRVD_ID', 'RYXXID', 'forms[1]','PERSON_BUSS', 'XM', 'selectContionYY');">
					</td>					
				</tr>
				<tr>					
					<td width="8%" nowrap align="right" class="detail_label">
						制单时间从：
					</td>
					<td width="15%" class="detail_content">
						<input name="CRE_TIME_FROM" type="text"	value="${params.CRE_TIME_FROM}" onclick="SelectTime();">
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
						onclick="SelectTime(CRE_TIME_FROM);"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						到：
					</td>
					<td width="15%" class="detail_content">
						<input name="CRE_TIME_TO" type="text" value="${params.CRE_TIME_TO}" onclick="SelectTime();">
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
						onclick="SelectTime(CRE_TIME_TO);"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">
						状态：
					</td>
					<td width="15%" class="detail_content">
						<select id="STATE" name="STATE" style="width: 155" value="${rst.STATE}}"></select>
					</td>
				</tr>				
				<tr>
					<td colspan="10" align="center" valign="middle" >
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">&nbsp;&nbsp;
						<input type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
<script language="JavaScript">  
		SelDictShow("PRVD_TYPE","173","${params.PRVD_TYPE}","");
		SelDictShow("STATE","32","${params.STATE}","");	
</script>
</html>
