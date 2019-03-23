
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>列表</title>
		<link rel="stylesheet" type="text/css"	href="${ctx}/styles/${theme}/css/style.css">
		<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>		
		<script type="text/javascript" src="${ctx}/pages/erp/sale/budgetquota/Budgetquota_List.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body>
		<table width="99.8%" height="100%" border="0" cellspacing="0"
			cellpadding="0" class="panel">
			<tr>
				<td height="20px" valign="top">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td height="20px">&nbsp;&nbsp;
								当前位置：营销管理&gt;&gt;预算管理&gt;&gt;预算额度设置
							</td>
							<td width="50" align="right"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
	           <td height="30">
		        <div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			     <table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
						<tr>
							<td nowrap>
								<c:if test="${pvg.PVG_EDIT eq 1}">
									<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">								
									<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
								</c:if>
								<c:if test="${pvg.PVG_DELETE eq 1}">
									<input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R">
								</c:if>
								<c:if test="${pvg.PVG_START_STOP eq 1}">
									<input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">								
									<input id="stop" type="button" class="btn" value="停用(T)"  title="Alt+T" accesskey="T">
								</c:if>
								<c:if test="${pvg.PVG_BATCHEDIT eq 1 }">
								    <input id="batchadd" type="button" class="btn" value="批量新增(B)" title="Alt+B" accesskey="B">
									<input id="batchedit" type="button" class="btn" value="批量维护(P)" title="Alt+P" accesskey="P">
								</c:if>
								<c:if test="${pvg.PVG_BWS eq 1}">
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
						<table id="ordertb" width="100%" border="0" cellpadding="1"
							cellspacing="1" class="lst">
							<tr class="fixedRow">
								<th width="1%"></th>
								<th nowrap align="center" dbname="BUDGET_DEPT_NAME">部门名称</th>
								<th nowrap align="center" dbname="BUDGET_ITEM_NO">科目编号</th>
								<th nowrap align="center" dbname="BUDGET_ITEM_NAME">科目名称</th>
								<th nowrap align="center" dbname="BUDGET_ITEM_TYPE">科目类型</th>
								<th nowrap align="center" dbname="PAR_BUDGET_ITEM_NO">上级科目编号</th>
								<th nowrap align="center" dbname="PAR_BUDGET_ITEM_NAME">上级科目名称</th>
								<th nowrap align="center" dbname="REALATE_AREA_NAME">关联区域</th>
								<th nowrap align="center" dbname="YEAR">年份</th>
								<th nowrap align="center" dbname="QUARTER">季度</th>
								<th nowrap align="center" dbname="MONTH">月份</th>
								<th nowrap align="center" dbname="BUDGET_QUOTA">预算额度</th>
								<th nowrap align="center" dbname="CRE_NAME">制单人</th>
								<th nowrap align="center" dbname="CRE_TIME">制单时间</th>
								<th nowrap align="center" dbname="STATE">状态</th>
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)"
									onmouseout="mout(this)"
									onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
									<td width="1%" align='center'>
										<input type="radio" style="valign: middle" name="eid" id="eid${rr}" value="${rst.BUDGET_QUOTA_ID}" />
									</td>
									<td nowrap align="left">${rst.BUDGET_DEPT_NAME}&nbsp;</td>
									<td nowrap align="center">${rst.BUDGET_ITEM_NO}&nbsp;</td>
									<td nowrap align="left">${rst.BUDGET_ITEM_NAME}&nbsp;</td>
									<td nowrap align="center">${rst.BUDGET_ITEM_TYPE}&nbsp;</td>
									<td nowrap align="center">${rst.PAR_BUDGET_ITEM_NO}&nbsp;</td>
									<td nowrap align="left">${rst.PAR_BUDGET_ITEM_NAME}&nbsp;</td>
									<td nowrap align="left">${rst.RELATE_AREA_NAME}&nbsp;</td>
									<td nowrap align="center">${rst.YEAR}&nbsp;</td>
									<td nowrap align="center">${rst.QUARTER}&nbsp;</td>
									<td nowrap align="center">${rst.MONTH}&nbsp;</td>
									<td nowrap align="center">${rst.BUDGET_QUOTA}&nbsp;</td>
									<td nowrap align="left">${rst.CRE_NAME}&nbsp;</td>
									<td nowrap align="center">${rst.CRE_TIME}&nbsp;</td>
									<td nowrap json='STATE' align="center">${rst.STATE}&nbsp;</td>
									<input id="state${rst.BUDGET_QUOTA_ID}" type="hidden"  value="${rst.STATE}" />
								</tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="16" align="center" class="lst_empty">
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
									<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}' />
									<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}' />
									<input type="hidden" id="orderType" name="orderType" value='${orderType}' />
									<input type="hidden" id="orderId" name="orderId" value='${orderId}' />
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
		<div id="querydiv" class="query_div">
			<form id="queryForm" method="post" action="#">
				<table width="100%" border="0" cellpadding="4" cellspacing="4"
					class="detail">
					<input type="hidden" name="selectParam" value=" STATE='启用' and  DEL_FLAG=0 ">
				    <input type="hidden" name=selectDeptParams id="selectDeptParams" value=" BMZT='启用' and JGXXID='00'">
					<input type="hidden" name=selectAreaParams id="selectAreaParams" value=" STATE='启用' and DEL_FLAG=0 ">
					<tr>
						<td class="detail2">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="detail3">
								<tr>
									<td width="8%" nowrap align="right" class="detail_label">预算科目编号：</td>
									<td width="15%" class="detail_content">
										<input id="BUDGET_ITEM_ID" json="BUDGET_ITEM_ID" name="BUDGET_ITEM_ID" type="hidden" inputtype="string" value="">
										<input id="BUDGET_ITEM_NO" json="BUDGET_ITEM_NO"  name="BUDGET_ITEM_NO" type="text" inputtype="string"   value="${params.BUDGET_ITEM_NO}" >
										<img align="absmiddle" name="" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
											onClick="selCommon('BS_152', false, 'BUDGET_ITEM_ID', 'BUDGET_ITEM_ID', 'forms[1]','BUDGET_ITEM_NO,BUDGET_ITEM_NAME', 'BUDGET_ITEM_NO,BUDGET_ITEM_NAME', 'selectParam')">
									</td>
									<td width="8%" nowrap align="right" class="detail_label">预算科目名称：</td>
									<td width="15%" class="detail_content">
										<input name="BUDGET_ITEM_NAME" type="text" value="${params.BUDGET_ITEM_NAME}" id="BUDGET_ITEM_NAME" json="BUDGET_ITEM_NAME">
									</td>
									<td width="8%" nowrap align="right" class="detail_label">预算科目类型：</td>
									<td width="15%" class="detail_content">
										<select name="BUDGET_ITEM_TYPE"  id="BUDGET_ITEM_TYPE" json="BUDGET_ITEM_TYPE" style="width: 155px;"></select>
									</td>
									<td width="8%" nowrap align="right" class="detail_label">
										状态：
									</td>
									<td width="15%" class="detail_content">
										<select id="STATE" name="STATE" style="width: 155"></select>
									</td>
								</tr>
								<tr>
									 <td width="8%" nowrap align="right" class="detail_label">  预算部门编号： </td>
									 <td width="15%" class="detail_content">
										   <input type="hidden" name="BUDGET_DEPT_ID" id="BUDGET_DEPT_ID" json="BUDGET_DEPT_ID" value="${rst.BUDGET_DEPT_ID}"/>
										   <input id="BUDGET_DEPT_NO" json="BUDGET_DEPT_NO" name="BUDGET_DEPT_NO" type="text"   value="${params.BUDGET_DEPT_NO}"  />
										   <img align="absmiddle" name="selJGXX" style="cursor: hand"
											 src="${ctx}/styles/${theme}/images/plus/select.gif"
											 onClick="selCommon('1', false, 'BUDGET_DEPT_ID', 'BMXXID', 'forms[1]','BUDGET_DEPT_NO,BUDGET_DEPT_NAME', 'BMBH,BMMC', 'selectDeptParams')">
									</td>
									<td width="8%" nowrap align="right" class="detail_label"> 预算部门名称： </td>
									<td width="15%" class="detail_content">
									   <input json="BUDGET_DEPT_NAME" id="BUDGET_DEPT_NAME" name="BUDGET_DEPT_NAME" type="text"   value="${params.BUDGET_DEPT_NAME}" />
									 </td>
									 <td width="8%" nowrap align="right" class="detail_label" >上级预算科目编号：</td>
									<td nowrap width="15%" class="detail_content">
										<input id="PAR_BUDGET_ITEM_ID" json="PAR_BUDGET_ITEM_ID" name="PAR_BUDGET_ITEM_ID" type="hidden" inputtype="string" value="${params.PAR_BUDGET_ITEM_ID}">
										<input id="PAR_BUDGET_ITEM_NO" json="PAR_BUDGET_ITEM_NO"  name="PAR_BUDGET_ITEM_NO" type="text" inputtype="string"  value="${params.PAR_BUDGET_ITEM_NO}" >
										<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
											onClick="selCommon('BS_152', true, 'PAR_BUDGET_ITEM_ID', 'BUDGET_ITEM_ID', 'forms[1]','PAR_BUDGET_ITEM_NO,PAR_BUDGET_ITEM_NAME', 'BUDGET_ITEM_NO,BUDGET_ITEM_NAME', 'selectParam')">
									</td>
									<td width="8%" nowrap align="right" class="detail_label">上级预算科目名称：</td>
									<td width="15%" class="detail_content">
										<input name="PAR_BUDGET_ITEM_NAME" type="text" value="${params.PAR_BUDGET_ITEM_NAME}" id="PAR_BUDGET_ITEM_NAME" json="PAR_BUDGET_ITEM_NAME">
									</td>
								
								</tr>
								<tr>
									<td width="8%" nowrap align="right" class="detail_label">年份：</td>
									<td width="15%" class="detail_content">
									  <select json="YEAR" name="YEAR" id="YEAR" style="width:155px;"  label="年份"  ></select>
									</td>
									<td width="8%" nowrap align="right" class="detail_label">季度：</td>
									<td width="15%" class="detail_content">
										 <select id="QUARTER" json="QUARTER" style="width:155px;"  name="QUARTER"  label="季度"   ></select>
									</td>
									<td width="8%" nowrap align="right" class="detail_label">月份：</td>
									<td width="15%" class="detail_content">
										 <select json="MONTH" name="MONTH" style="width:155px;"  id="MONTH"  label="MONTH"  ></select>
									</td>
									<td width="8%" nowrap align="right" class="detail_label"> </td>
									<td width="15%" class="detail_content"> </td>
								</tr>
								
								<tr>
									<td width="8%" nowrap align="right" class="detail_label">
										制单时间从：
									</td>
									<td width="15%" class="detail_content">
										<input name="BEGIN_CRE_TIME" type="text"	value="${params.BEGIN_CRE_TIME}" onclick="SelectTime();">
										<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
										onclick="SelectTime(BEGIN_CRE_TIME);"/>
									</td>
									<td width="8%" nowrap align="right" class="detail_label">
										制单时间到到：
									</td>
									<td width="15%" class="detail_content">
										<input name="END_CRE_TIME" type="text" value="${params.END_CRE_TIME }" onclick="SelectTime();">
										<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
										onclick="SelectTime(END_CRE_TIME);"/>
									</td>
									<td width="8%" nowrap align="right" class="detail_label"> 区域编号：</td>
									<td width="15%" class="detail_content"> 
									 <input id="RELATE_AREA_ID" json="RELATE_AREA_ID" name="RELATE_AREA_ID" type="hidden" value="${params.RELATE_AREA_ID}">
									 <input id="RELATE_AREA_NO" json="RELATE_AREA_NO"  name="RELATE_AREA_NO" type="text"      value="${params.RELATE_AREA_NO}"  >
									 <img align="absmiddle" name="selJGXX" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"       
										onClick="selCommon('BS_18', false, 'RELATE_AREA_ID', 'AREA_ID', 'forms[1]','RELATE_AREA_NO,RELATE_AREA_NAME', 'AREA_NO,AREA_NAME', 'selectAreaParams')">
									
									</td>
									<td width="8%" nowrap align="right" class="detail_label"> 区域名称： </td>
									<td width="15%" class="detail_content">
									   <input id="RELATE_AREA_NAME" json="RELATE_AREA_NAME" name="RELATE_AREA_NAME" type="text"  value="${params.RELATE_AREA_NAME}"  >
									</td>
								</tr>
								<tr>
									<td colspan="10" align="center" valign="middle"	>
										<input id="q_search" type="button" class="btn" value="确定(O)"
											title="Alt+O" accesskey="O">
										&nbsp;&nbsp;
										<input id="q_close" type="button" class="btn" value="关闭(X)"
											title="Alt+C" accesskey="X">
										&nbsp;&nbsp;
										<input type="reset" class="btn" value="重置(R)" title="Alt+R"
											accesskey="R">
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
		SelDictShow("BUDGET_ITEM_TYPE", "BS_145", '${params.BUDGET_ITEM_TYPE}', "");
		SelDictShow("STATE","32","${params.STATE}","");
		SelDictShow("YEAR", "89", '${params.YEAR}', "");
	    SelDictShow("MONTH", "168", '${params.MONTH}', "");
	    SelDictShow("QUARTER", "BS_148", '${params.QUARTER}', "");
</script>
</html>
