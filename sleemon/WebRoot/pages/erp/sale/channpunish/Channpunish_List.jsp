

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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/channpunish/Channpunish_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		#punishDiv{
			margin: 0px auto; 
			width:600px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:200px;
			left:230px;
			top:50px;
			text-align:center;
			display: none;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;价格管理&gt;&gt;渠道惩罚设定</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20px" valign="top" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="margin-left:3px;">
				<tr >
				   <td nowrap >
					<c:if test="${pvg.PVG_BWS eq 1 }">
						<input id="query" type="button" class="btn" value="查询(F)"
							title="Alt+F" accesskey="F">
					</c:if>
					<c:if test="${pvg.PVG_PUNISH eq 1 }">
						<input id="punish" type="button" class="btn" value="惩罚(P)" title="Alt+P" accesskey="P">
					</c:if>
					<c:if test="${pvg.PVG_CANCEL eq 1 }">
						<input id="cancel" type="button" class="btn" value="取消惩罚(C)" title="Alt+C" accesskey="C">
					</c:if>
				   </td>
				</tr>
			</table>
			<div id="punishDiv">
				<table width="100%" height="100%">
					<tr>
						<td align="right"><span style="font-size: 15px;">渠道信息：</span></td>
						<td align="left">
							<input type="text" value="" id="channInfo" readonly="readonly">
						</td>
					</tr>
					<tr >
						<td align="right"><span style="font-size: 15px;">惩罚说明：</span></td>
						<td align="left">
							<textarea  id="PUNISH_REMARK"  cols="60" rows="5" inputtype="string"  maxlength="250"    ></textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
						<input type="button" id="audit" value="提交" class="btn" style="margin-right: 10px"/>
						<input type="button" id="close" value="关闭" class="btn"/>
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
					<th nowrap align="center" dbname="AREA_NO">区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME">区域名称</th>
					<th nowrap align="center" dbname="PUNISH_FLAG">是否惩罚</th>
					<th nowrap align="center" dbname="CRE_NAME">处理人</th>
					<th nowrap align="center" dbname="CRE_TIME">处理时间</th>
					
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));checkState('${rst.PUNISH_FLAG}')">
						<td width="1%"align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.CHANN_ID}"/>
						</td>
                        <td nowrap align="center">${rst.CHANN_NO}&nbsp;</td>
                        <input type="hidden" id="no${rst.CHANN_ID}" value="${rst.CHANN_NO}"/>
                        <td nowrap align="left">${rst.CHANN_NAME}&nbsp;</td>
                        <input type="hidden" id="name${rst.CHANN_ID}" value="${rst.CHANN_NAME}"/>
                        <td nowrap align="center" >${rst.CHANN_TYPE}&nbsp;</td>
                        <td nowrap align="left">${rst.AREA_NO}&nbsp;</td>
                        <td nowrap align="left">${rst.AREA_NAME}&nbsp;</td>
                        <td nowrap align="center">
                        	<c:if test="${rst.PUNISH_FLAG eq 1}">
                        		是
                        	</c:if>
                        	<c:if test="${rst.PUNISH_FLAG eq 0}">
                        		否
                        	</c:if>
                        &nbsp;</td>
                        <td nowrap align="left">${rst.DEAL_PSON_NAME}&nbsp;</td>
                        <td nowrap align="center">${rst.DEAL_TIME}&nbsp;</td>
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
	<input type="hidden" name="selectParams" value=" STATE in( '启用','停用')  and DEL_FLAG='0'">
	<input type="hidden" name="select" value=" DEL_FLAG='0'">
	<input type="hidden" name="selectParam" value="RYZT in ('启用','停用') and DELFLAG=0 and JGXXID in (select JGXXID from T_SYS_JGXX where ZTXXID ='${ZTXXID}' ) ">
	<input id="selcondition" type="hidden" name="selcondition" value=" 1=1 " />
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="8%" nowrap align="right" class="detail_label" >渠道编号：</td>
					<td nowrap width="15%" class="detail_content">
						<input id="CHANN_ID" json="CHANN_ID" name="CHANN_ID" type="hidden"
							inputtype="string" seltarget="selJGXX" autocheck="true"
							maxlength="100" value="${params.CHANN_ID}">
														
						<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text" inputtype="string"
							seltarget="selJGXX" autocheck="true" maxlength="32"
							value="${params.CHANN_NO}" >
							
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'select')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="CHANN_NAME" name="CHANN_NAME" value="${params.CHANN_NAME }"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道类型：</td>
					<td width="15%" class="detail_content">
						<select name="CHANN_TYPE" id="CHANN_TYPE" json="CHANN_TYPE" style="width: 155"></select>
					</td>
					<td width="8%" align="right" class="detail_label">渠道级别：</td>
					<td width="15%" class="detail_content">
						<select label="渠道级别" name="CHAA_LVL"
							valueType="chinese:false" inputtype="string"
							style="width: 155" id="CHAA_LVL" json="CHAA_LVL"
							value="${params.CHAA_LVL}">
						</select>
					</td>
				</tr>
				<tr>
					<td width="8%" align="right" class="detail_label" nowrap>区域编号：</td>
					<td width="15%" class="detail_content">
						<input id="AREA_ID" json="AREA_ID" name="AREA_ID" type="hidden"
							inputtype="string" autocheck="true" value="${params.AREA_ID}">
							
						<input id="AREA_NO" json="AREA_NO"  name="AREA_NO" type="text" inputtype="string"
							  value="${params.AREA_NO}"  lable="区域编号">
							
						<img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif" maxlength="30"
							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[1]','AREA_ID,AREA_NO,AREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME', 'selectParams')">
					</td>
					<td width="8%" align="right" class="detail_label">区域名称：</td>
					<td width="15%" class="detail_content">
						<input id="AREA_NAME" name="AREA_NAME" type="text" inputtype="string" maxlength="50"
							 label="区域名称"  json="AREA_NAME" value="${params.AREA_NAME }" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">处理时间从：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="BEGIN_DEAL_TIME" id="BEGIN_DEAL_TIME"name="BEGIN_DEAL_TIME" value="${params.BEGIN_DEAL_TIME}" autocheck="true" inputtype="string" label="处理时间从"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(BEGIN_DEAL_TIME);"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">处理时间到：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="END_DEAL_TIME" id="END_DEAL_TIME" value="${params.END_DEAL_TIME }" name="END_DEAL_TIME" autocheck="true" inputtype="string" label="处理时间到"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(END_DEAL_TIME);"/>
					</td>
				</tr>
				<tr>
					<td width="8%" nowrap align="right" class="detail_label">处理人</td>
					<td width="15%" class="detail_content">
						<input id="DEAL_PSON_NAME" name="DEAL_PSON_NAME" type="text"  style="width:155" value="${params.DEAL_PSON_NAME}"/>
						<img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('System_0', false, 'DEAL_PSON_NAME','XM', 'forms[1]','DEAL_PSON_NAME','XM', 'selectParam')"
							>
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
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
<div id="bottomdiv" style="height: 68%; width: 100%;z-index:-1;position:absolute;">
		<!-- 下帧 -->
		<iframe id="bottomcontent" name="bottomcontent" src="#" frameBorder=0 width="100%" height="100%" style="z-index:-1;position:absolute;" frameborder="0" scrolling="AUTO"></iframe>
</div>
</body>

<script language="JavaScript">
	//$(function(){
		SelDictShow("CHANN_TYPE", "171", '${params.CHANN_TYPE}', "");
		SelDictShow("CHAA_LVL", "169", '${params.CHAA_LVL}', "");
	//});
</script>
</html>
