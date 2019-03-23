

<!--  
/**
 * @module 系统管理
 * @func 渠道信息
 * @version 1.1
 * @author  刘曰刚
 */
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>渠道信息列表</title>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/base/chann/Chann_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;基础信息&gt;&gt;渠道信息</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20px" valign="top" >
	   <div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="margin-left:3px;">
				<tr >
				   <td nowrap >
								<c:if test="${pvg.PVG_EDIT eq 1 }">
									<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
									<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
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
									<input id="expdata" type="button" class="btn" value="导出(X)" title="Alt+X" accesskey="X">
								</c:if>
								<c:if test="${pvg.PVG_BATCH eq 1}">
								    <input id="query" type="button" class="btn" value="批量维护区域经理(M)"
										title="Alt+M" accesskey="F" onclick="orpenWindow()">
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
					<th nowrap align="center" dbname="CHANN_NO">渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME">渠道名称</th>
					<th nowrap align="center" dbname="CHANN_TYPE">渠道类型</th>
					<th nowrap align="center" dbname="CHAA_LVL">渠道级别</th>
					<th nowrap align="center" dbname="CHANN_NAME_P">上级渠道</th>
					<th nowrap align="center" dbname="AREA_NO">区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME">区域名称</th>
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
							<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.CHANN_ID}"/>
						</td>
                        <td nowrap align="center">${rst.CHANN_NO}&nbsp;</td>
                        <td nowrap align="center">${rst.CHANN_NAME}&nbsp;</td>
                        <td nowrap align="center" >${rst.CHANN_TYPE}&nbsp;</td>
                        <td nowrap align="center" >${rst.CHAA_LVL}&nbsp;</td>
                        <td nowrap align="center">${rst.CHANN_NAME_P}&nbsp;</td>
                        <td nowrap align="center">${rst.AREA_NO}&nbsp;</td>
                        <td nowrap align="center">${rst.AREA_NAME}&nbsp;</td>
                        <td nowrap align="center">${rst.CRE_NAME}&nbsp;</td>
                        <td nowrap align="center">${rst.CRE_TIME}&nbsp;</td>
                        <td nowrap align="center">${rst.DEPT_NAME}&nbsp;</td>
                        <td nowrap json='STATE' align="center">${rst.STATE}&nbsp;</td>
                        <input type="hidden" id="state${rst.CHANN_ID}" value="${rst.STATE}"/>
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
	<input type="hidden" name="selectParams" value=" STATE in( '启用')  and DEL_FLAG='0'">
	<input id="selcondition" type="hidden" name="selcondition" value=" 1=1 " />
	<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
	<input type="hidden" id="selectAreaManager" name="selectAreaManager" value=""/>
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="10%" nowrap align="right" class="detail_label" >渠道编号：</td>
					<td nowrap width="15%" class="detail_content">
						<input id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" type="hidden"
							inputtype="string" seltarget="selJGXX" autocheck="true"
							 value="${params.CHANN_ID}">
														
						<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text" inputtype="string"
							seltarget="selJGXX" autocheck="true" 
							value="${params.CHANN_NO}" >
							
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', true, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams')">
					</td>
					<td width="10%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" value="${params.CHANN_NAME }"/>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">渠道类型：</td>
					<td width="15%" class="detail_content">
						<select name="CHANN_TYPE" id="CHANN_TYPE" json="CHANN_TYPE" style="width: 155"></select>
					</td>
					<td width="10%" align="right" class="detail_label">渠道级别：</td>
					<td width="15%" class="detail_content">
						<select label="渠道级别" name="CHAA_LVL"
							valueType="chinese:false" inputtype="string"
							style="width: 155" id="CHAA_LVL" json="CHAA_LVL"
							value="${params.CHAA_LVL}">
						</select>
					</td>
				</tr>
				<tr>
					<td width="10%" nowrap align="right" class="detail_label" >上级渠道编号：</td>
					<td nowrap width="15%" class="detail_content" >
						<input id="CHANN_ID_P" json="CHANN_ID_P" name="CHANN_ID_P" type="hidden"
														inputtype="string"  autocheck="true"
														maxlength="100" value="${params.CHANN_ID_P}">
														
													<input id="CHANN_NO_P" json="CHANN_NO_P"  name="CHANN_NO_P" type="text" inputtype="string"
														seltarget="selJGXX" autocheck="true" maxlength="30"
														value="${params.CHANN_NO_P}">
														
													<img align="absmiddle" name="selJGXX" style="cursor: hand"
														src="${ctx}/styles/${theme}/images/plus/select.gif"       
														onClick="selCommon('BS_19', true, 'CHANN_ID_P', 'CHANN_ID', 'forms[1]','CHANN_ID_P,CHANN_NO_P,CHANN_NAME_P', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams')">
					</td>
					<td width="10%" nowrap align="right" class="detail_label">上级渠道名称：</td>
					<td width="15%" class="detail_content">
						<input id="CHANN_NAME_P" name="CHANN_NAME_P" type="text" inputtype="string"
														 label="上级渠道名称"  json="CHANN_NAME_P" value="${params.CHANN_NAME_P }">
					</td>
					<td width="10%" align="right" class="detail_label" nowrap>区域编号：</td>
					<td width="15%" class="detail_content">
						<input id="AREA_ID" json="AREA_ID" name="AREA_ID" type="hidden"
							inputtype="string" autocheck="true" value="${params.AREA_ID}">
							
						<input id="AREA_NO" json="AREA_NO"  name="AREA_NO" type="text" inputtype="string"
							  value="${params.AREA_NO}"  lable="区域编号">
							
						<img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30"
							onClick="selCommon('BS_18', true, 'AREA_ID', 'AREA_ID', 'forms[1]','AREA_ID,AREA_NO,AREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME', 'selectParams')">
					</td>
					<td width="10%" align="right" class="detail_label">区域名称：</td>
					<td width="15%" class="detail_content">
						<input id="AREA_NAME" name="AREA_NAME" type="text" inputtype="string" maxlength="50"
							 label="区域名称"  json="AREA_NAME" value="${params.AREA_NAME }" >
					</td>
				</tr>
				<tr>
					<td width="10%" nowrap align="right" class="detail_label">省份：</td>
					 <td width="15%" class="detail_content">
	   					<input type="text" id="PROV" name="PROV"    value="${params.PROV}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[1]','PROV,CITY,COUNTY', 'PROV,CITY,COUNTY', '')">
					 </td>
					<td width="10%" nowrap align="right" class="detail_label">城市：</td>
					<td width="15%" class="detail_content">
						<input name="CITY" id="CITY" json="CITY" type="text" value="${params.CITY}">
					</td>
					
					<td width="10%" nowrap align="right" class="detail_label">区县：</td>
					<td width="15%" class="detail_content">
						<input name="COUNTY" id="COUNTY" json="COUNTY" type="text" value="${params.COUNTY}">
					</td>
					<td width="10%" nowrap align="right" class="detail_label">所属战区：</td>
					<td width="15%" class="detail_content">
						<input name="AREA_NAME_P" id="AREA_NAME_P" json="AREA_NAME_P" type="text" value="${params.AREA_NAME_P}">
						<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_26', true, 'AREA_NAME', 'AREA_NAME_P', 'forms[1]',
										'AREA_NAME_P', 
										'AREA_NAME', 
										'selectParamsArea');">
					</td>
				</tr>
				<tr>
				    
				    <td width="10%" nowrap align="right" class="detail_label">制单时间从：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="BEGIN_CRE_TIME" id="BEGIN_CRE_TIME"name="BEGIN_CRE_TIME" value="${params.BEGIN_CRE_TIME}" autocheck="true" inputtype="string" label="制单时间从"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(BEGIN_CRE_TIME);"/>
					</td>
					<td width="10%" nowrap align="righ销售折扣设置t" class="detail_label">制单时间到：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="END_CRE_TIME" id="END_CRE_TIME" value="${params.END_CRE_TIME }" name="END_CRE_TIME" autocheck="true" inputtype="string" label="制单时间到"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(END_CRE_TIME);"/>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">区域经理：</td>
					<td width="15%" class="detail_content">
					    <input name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" type="text"  value="${params.AREA_MANAGE_NAME}">
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_167', true, 'AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'forms[1]','AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'selectAreaManager')">						   
					</td>
				    <td width="10%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select name=STATE id="STATE" json="STATE" style="width: 155"></select>
					</td>
				</tr>
				
				<tr>
				    <td width="10%" nowrap align="right" class="detail_label">加盟日期从：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="BEGIN_JOIN_DATE" id="BEGIN_JOIN_DATE" name="BEGIN_JOIN_DATE" value="${params.BEGIN_JOIN_DATE}" autocheck="true" inputtype="string" label="加盟日期从"  mustinput="true" onclick="SelectDate();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(BEGIN_JOIN_DATE);"/>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">加盟日期到：</td>
					<td width="15%" class="detail_content" colspan="7">
						<input type="text" json="END_JOIN_DATE" id="END_JOIN_DATE" value="${params.END_JOIN_DATE }" name="END_JOIN_DATE" autocheck="true" inputtype="string" label="加盟日期到"  mustinput="true" onclick="SelectDate();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(END_JOIN_DATE);"/>
					</td>
				</tr>
				<tr>
					<td colspan="10" align="center" valign="middle" >
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
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
	//$(function(){
		SelDictShow("STATE","32","${params.STATE}","");
		SelDictShow("CHANN_TYPE", "171", '${params.CHANN_TYPE}', "");
		//SelDictShow("CHAA_LVL", "169", '${params.CHAA_LVL}', "");
		SelDictShow("CHAA_LVL", "BS_100", '${params.CHAA_LVL}', "");
	//});
</script>
</html>
