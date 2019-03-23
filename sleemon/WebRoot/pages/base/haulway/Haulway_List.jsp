
<!-- /**
  *@module 系统管理
  *@fuc 路线信息一览
  *@version 1.1
  *@author 王栋斌
*/ -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>路线信息页面</title>
	<link rel="stylesheet" type="text/css"
		href="${ctx}/styles/${theme}/css/style.css" />
	<script language="JavaScript"
		src="${ctx}/pages/common/select/selCommJs.js">
</script>
	<script type=text/javascript
		src="${ctx}/pages/base/haulway/Haulway_List.js">
</script>
	<script type=text/javascript
		src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js">
</script>

</head>

<body>
	<table width="99.8%" height="100%" border="0" cellspacing="0"
		cellpadding="0" class="panel">
		<tr>
			<td height="20px" valign="top">
				<table width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;物流供应商管理&gt;&gt;路线信息</span></td>
						<td width="50" align="right"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="20px" valign="top">
			<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
				<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0
					width="100%" style="margin-left: 3px">
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
			<td>
				<div class="lst_area" style="margin-left:3px;">
					<table id="ordertb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="lst">
						<tr class="fixedRow">
							<th nowrap align="center" width="1%">
								<br>
							</th>
							<th nowrap align="center" dbname="HAULWAY_NO">
								路线编号
							</th>
							<th nowrap align="center" dbname="HAULWAY_NAME">
								路线名称
							</th>
							<th nowrap align="center" dbname="DELV_CITY">
								发出城市
							</th>
							<th nowrap align="center" dbname="SHIP_POIT_NAME">
								发货点名称
							</th>
							<th nowrap align="center" dbname="ARRV_CITY">
								到达城市
							</th>
							<th nowrap align="center" dbname="CHANN_NAME">
								渠道名称
							</th>
							<th nowrap align="center" dbname="LENGTH">
								全程
							</th>
							<th nowrap align="center" dbname="DAYS">
								天数
							</th>
							<th nowrap align="center" dbname="CRE_NAME">
								制单人
							</th>
							<th nowrap align="center" dbname="CRE_TIME">
								制单时间
							</th>
							<th nowrap align="center" dbname="STATE">
								状态
							</th>
						</tr>
						<c:forEach items="${page.result}" var="rst" varStatus="row">
							<c:set var="r" value="${row.count % 2}"></c:set>
							<c:set var="rr" value="${row.count}"></c:set>
							<tr class="list_row${r}" onmouseover="mover(this)"
								onmouseout="mout(this)"
								onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
								<td nowrap align='center'>
									<input type="radio" style="height: 12px; valign: middle"
										name="eid" id="eid${rr}" value="${rst.HAULWAY_ID}">
								</td>
								<td nowrap align="left">
									${rst.HAULWAY_NO}&nbsp;
								</td>
								<td nowrap align="left">
									${rst.HAULWAY_NAME}&nbsp;
								</td>
								<td nowrap align="left">
									${rst.DELV_CITY}&nbsp;
								</td>
								<td nowrap align="left">
									${rst.SHIP_POIT_NAME}&nbsp;
								</td>
								<td nowrap align="left">
									${rst.ARRV_CITY}&nbsp;
								</td>
								<td nowrap align="left">
									${rst.CHANN_NAME}&nbsp;
								</td>
								<td nowrap align="right">
									${rst.LENGTH}&nbsp;
								</td>
								<td nowrap align="right">
									${rst.DAYS}&nbsp;
								</td>
								<td nowrap align="left">
									${rst.CRE_NAME}&nbsp;
								</td>
								<td nowrap align="center">
									${rst.CRE_TIME}&nbsp;
								</td>
								<td nowrap align="center" id="state${rst.HAULWAY_ID}"
									json="STATE" value="${rst.STATE}">
									${rst.STATE}&nbsp;
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
			</td>
		</tr>
		<tr>
			<td height="8px">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="lst_toolbar">
					<tr>
						<td>
							<form id="pageForm" action="#" method="post" name="listForm">
								<input type="hidden" id="pageSize" name="pageSize"
									value='${page.pageSize}' />
								<input type="hidden" id="pageNo" name="pageNo"
									value='${page.currentPageNo}' />
								<input type="hidden" id="orderType" name="orderType"
									value='${orderType}' />
								<input type="hidden" id="orderId" name="orderId"
									value='${orderId}' />
								<input type="hidden" id="selRowId" name="selRowId"
									value="${selRowId}">
								&nbsp;
								<input type="hidden" id="paramUrl" name="paramUrl"
									value="${paramCover.coveredUrl}">
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
			<table width="100%" border="0" cellpadding="4" cellspacing="4"
				class="detail">
				<input type="hidden" name="selectContion2"
					value=" DELFLAG = 0 and bmzt = '启用'" />
				<input type="hidden" name="selectContion3"
					value=" DELFLAG = 0 and jgzt = '启用'" />

				<tr>
					<td class="detail2">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="detail3">
							<tr>
								<td nowrap align="right" class="detail_label">
									路线编号：
								</td>
								<td class="detail_content">
									<input name="HAULWAY_NO" type="text"
										value="${params.HAULWAY_NO}">
								</td>
								<td nowrap align="right" class="detail_label">
									路线名称：
								</td>
								<td class="detail_content">
									<input name="HAULWAY_NAME" type="text"
										value="${params.HAULWAY_NAME}" />
								</td>
								<td nowrap align="right" class="detail_label">
									发出城市：
								</td>
								<td nowrap class="detail_content">
									<input type="hidden" id="selectParams" name="selectParams" value="STATE in ('启用','停用')"> 
									<input type="hidden" name="ZONE_ID" id="ZONE_ID">
									<input type="text" name="DELV_CITY" value="${params.DELV_CITY}" />
									<img align="absmiddle" name="selAREA" style="cursor: hand" 
									src="/sleemon/styles/newTheme/images/plus/select.gif" 
									onClick="selCommon('BS_20', false, 'ZONE_ID', 'ZONE_ID', 'forms[1]','DELV_CITY', 'CITY','selectParams')" />
								</td>
								<td nowrap align="right" class="detail_label">
									到达城市：
								</td>
								<td nowrap class="detail_content">
									<input type="text" name="ARRV_CITY" value="${params.ARRV_CITY}" />
									<img align="absmiddle" name="selAREA" style="cursor: hand" 
									src="/sleemon/styles/newTheme/images/plus/select.gif" 
									onClick="selCommon('BS_20', false, 'ZONE_ID', 'ZONE_ID', 'forms[1]','ARRV_CITY', 'CITY','selectParams')" />
								</td>
							</tr>
							<tr>
								<td nowrap align="right" class="detail_label">
									发货点编号：
								</td>
								<td nowrap class="detail_content">
									<input type="hidden" name="SHIP_POIT_ID" id="SHIP_POIT_ID">
									<input type="text" name="SHIP_POIT_NO" id="SHIP_POIT_NO" value="${params.SHIP_POIT_NO}" />
									<img align="absmiddle" name="selAREA" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_22', false, 'SHIP_POIT_ID', 'SHIP_POINT_ID', 'forms[1]','SHIP_POIT_NO,SHIP_POIT_NAME', 'SHIP_POINT_NO,SHIP_POINT_NAME', 'selectParams')">
								</td>
								<td nowrap align="right" class="detail_label">
									发货点名称：
								</td>
								<td nowrap class="detail_content">
									<input type="text" name="SHIP_POIT_NAME" id="SHIP_POIT_NAME" value="${params.SHIP_POIT_NAME}" />
								</td>
								<td nowrap align="right" class="detail_label">
									渠道编号：
								</td>
								<td nowrap class="detail_content">
									<input type="hidden" name="CHANN_ID" id="CHANN_ID">
									<input type="text" name="CHANN_NO" id="CHANN_NO" value="${params.CHANN_NO}" />
									<img align="absmiddle" name="selAREA" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selectParams')">
								</td>
								<td nowrap align="right" class="detail_label">
									渠道名称：
								</td>
								<td nowrap class="detail_content">
									<input type="text" name="CHANN_NAME" id="CHANN_NAME" value="${params.CHANN_NAME}" />
								</td>
								
							</tr>
							<tr>
								<td nowrap align="right" class="detail_label">
									制单时间从：
								</td>
								<td class="detail_content">
									<input type="text" json="CRE_TIME_FROM" id="CRE_TIME_FROM"
										name="CRE_TIME_FROM" autocheck="true" inputtype="string"
										label="日期" onclick="SelectTime();" readonly="readonly" value="${params.CRE_TIME_FROM}"/>
									&nbsp;
									<img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectTime(CRE_TIME_FROM);" />
								</td>
								<td nowrap align="right" class="detail_label">
									制单时间到：
								</td>
								<td class="detail_content">
									<input type="text" json="CRE_TIME_TO" id="CRE_TIME_TO"
										name="CRE_TIME_TO" autocheck="true" inputtype="string"
										label="日期" onclick="SelectTime();" readonly="readonly" value="${params.CRE_TIME_TO}"  />
									&nbsp;
									<img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectTime(CRE_TIME_TO);" />
								</td>
								<td nowrap align="right" class="detail_label">
									状态：
								</td>
								<td class="detail_content">
									<select id="STATE" name="STATE" style="width: 155">
									</select>
								</td>
								<td nowrap align="right" class="detail_label">
								</td>
								<td class="detail_content">
								</td>
							</tr>
							<tr>
								<td colspan="10" align="center" valign="middle">
									<input id="q_search" type="button" class="btn" value="确定(O)"
										title="Alt+O" accesskey="O">
									&nbsp;&nbsp;
									<input id="q_close" type="button" class="btn" value="关闭(X)"
										title="Alt+X" accesskey="X">
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
<script type=text/javascript>
SelDictShow("STATE", "32", "${params.STATE}", "");
</script>



