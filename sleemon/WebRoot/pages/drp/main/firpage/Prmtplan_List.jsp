<!--
 * @prjName:喜临门营销平台
 * @fileName:Prmtplan_List
 * @author zzb
 * @time   2013-08-23 16:04:28 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/main/firpage/Prmtplan_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
		 
		    <td>当前位置：推广促销方案信息</td>
		    
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
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
			   		<!--<input id="back" type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="window.history.back(-1)">
				-->
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
                   
                    <th  nowrap="nowrap" align="center" dbname="PRMT_PLAN_NO" >促销方案编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRMT_PLAN_NAME" >促销方案名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PRMT_TYPE" >促销类型</th>
                     <th  nowrap="nowrap" align="center" dbname="EFFCT_DATE_BEG" >生效日期从</th>
                     <th  nowrap="nowrap" align="center" dbname="EFFCT_DATE_END" >生效日期到</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.PRMT_PLAN_ID}"/>
					 </td>
                    
                     <td nowrap="nowrap" align="center">${rst.PRMT_PLAN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PRMT_PLAN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PRMT_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.EFFCT_DATE_BEG}&nbsp;</td>
                      <td nowrap="nowrap" align="center">${rst.EFFCT_DATE_END}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.PRMT_PLAN_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="10" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
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
<input type="hidden" name="FLAG" id="FLAG" value="${FLAG}"/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">促销方案编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" id="selectParams"  value="" >
					    <input type="hidden"  id="PRMT_PLAN_ID" name="PRMT_PLAN_ID"  style="width:155" value="${params.PRMT_PLAN_ID}"/>
	   					<input type="text" id="PRMT_PLAN_NO" name="PRMT_PLAN_NO"  style="width:155" value="${params.PRMT_PLAN_NO}"/>
	   					 <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_47', false, 'PRMT_PLAN_ID', 'PRMT_PLAN_ID', 'forms[1]','PRMT_PLAN_NO,PRMT_PLAN_NAME','PRMT_PLAN_NO,PRMT_PLAN_NAME', '')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">促销方案名称:</td>
					<td width="15%" class="detail_content">
					  <input type="text" id="PRMT_PLAN_NAME" name="PRMT_PLAN_NAME" style="width:155" value="${params.PRMT_PLAN_NAME}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">促销类型:</td>
					<td width="15%" class="detail_content">
					 <select name="PRMT_TYPE" id="PRMT_TYPE" style="width:155"></select>
					</td>
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">生效日期从:</td>
					<td width="15%" class="detail_content">
	   					<input  type="text"  id="EFFCT_DATE_BEG" name="EFFCT_DATE_BEG" readonly="readonly"   onclick="SelectDate();" style="width:155" value="${params.EFFCT_DATE_BEG}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(EFFCT_DATE_BEG);" >
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">生效日期到:</td>
					<td width="15%" class="detail_content">
					 <input  type="text"  id="EFFCT_DATE_END" name="EFFCT_DATE_END" readonly="readonly"   onclick="SelectDate();" style="width:155" value="${params.EFFCT_DATE_END}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(EFFCT_DATE_END);" >
					</td>
					<!--  
                    <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
					 <input  type="text"  id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_BEGIN}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_BEGIN);" >
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
					  <input  type="text"  id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_END}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_END);" >
					</td>-->
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
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
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
</html>
<script type="text/javascript">
    var flag = parent.document.getElementById("flag").value
    SelDictShow("PRMT_TYPE","BS_23","${params.PRMT_TYPE}","");
    if("PRMT" == flag){
    	SelDictShow("STATE", "BS_24", "${params.STATE}", "");
    }else{
    	SelDictShow("STATE", "33", "${params.STATE}", "");
    }
    
    
//初始化 审批流程
       spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>