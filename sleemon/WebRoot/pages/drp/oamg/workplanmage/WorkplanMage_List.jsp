<!-- 
 *@module 渠道管理-工作计划管理
 *@func   工作计划维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type=text/javascript src="${ctx}/pages/drp/oamg/workplanmage/WorkplanMage_List.js"></script>
	<title>工作计划维护列表</title>
</head>

<body>
<table  width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
	   <table width="100%" cellspacing="0" cellpadding="0" >
		 <tr>			
			<c:if test="${empty module ||module eq 'wh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>工作计划管理>>工作计划维护</td>
			</c:if>
			<c:if test="${module eq 'sh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>工作计划管理>>工作计划审核</td>
			</c:if>			
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20px" valign="top">
	     <div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
		    <table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
			  <tr>
				 <td id="qxBtnTb" nowrap>
				   	    <c:if test="${pvg.PVG_EDIT eq 1 }">
				   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					   </c:if>
					  <c:if test="${pvg.PVG_EDIT eq 1 }">
					    <input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
					   </c:if>
					   <c:if test="${pvg.PVG_DELETE eq 1 }">
				   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
					   </c:if>
					   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
						<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C">
						<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
					   </c:if>
					   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
					    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A" onclick="audit()">
					   </c:if>
					   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
					    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
					   </c:if>
					   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
				   		<input id="query" type="button" class="btn"  value="查询(F)" title="Alt+F" accesskey="F">
					   </c:if>
				    </td>
			 </tr>
		</table>
	  </div>
	</td>
</tr>
<tr>
	     <td valign="middle">
		   <div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"><br></th>
					<th nowrap align="center" dbname="WORK_PLAN_NO" >工作计划编号</th>
					<th nowrap align="center" dbname="WAREA_NO" >战区编号</th>
					<th nowrap align="center" dbname="WAREA_NAME" >战区名称</th>
					<th nowrap align="center" dbname="PLAY_YEAR" >计划年份</th>
					<th nowrap align="center" dbname="PLAY_MONTH" >计划月份</th>
                    <th nowrap align="center" dbname="TOTAL_UP_REPORT_NUM" >总上报份数</th>
                    <th nowrap align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}"  >
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" json="WORK_PLAN_ID" name="eid" id="eid${rr}" value="${rst.WORK_PLAN_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
						    <input type="hidden" id="state${rst.WORK_PLAN_ID}" value="${rst.STATE}"/>
						</td>
						<td align="center">${rst.WORK_PLAN_NO}&nbsp;</td>
						<td align="center">${rst.WAREA_NO}&nbsp;</td>
						<td align="center">${rst.WAREA_NAME}&nbsp;</td>
						<td align="center">${rst.PLAN_YEAR}&nbsp;</td>
						<td align="center">${rst.PLAN_MONTH}&nbsp;</td>
						<td align="center">${rst.TOTAL_UP_REPORT_NUM}&nbsp;</td>
						<td align="center">${rst.STATE}&nbsp;</td>
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      state(document.getElementById('state${rst.WORK_PLAN_ID}'));
						   });
					    </script>
						
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
		<table width="100%" border="0" cellpadding="0"cellspacing="0" class="lst_toolbar">
		<tr>
			<td>
				<form id="pageForm" action="#" method="post" name="listForm">
				<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
					<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
					<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
					<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
					<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
					<input type="hidden" id="brand" name="brand"/>
					<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
					<span id="hidinpDiv" name="hidinpDiv"></span>
					${paramCover.unCoveredForbidInputs } 
				</form>
			</td>
			<td align="right">${page.footerHtml}${page.toolbarHtml}</td>
		</tr>
		</table>
	</td>
</tr>	
</table>
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <input type="hidden" name="selectParamsChann" value="STATE in( '启用') and DEL_FLAG='0' and CHANN_ID in ${QUERY_CHANN_ID}">
			   <input type="hidden" name="selectParamsArea" value="STATE in( '启用') and DEL_FLAG='0'">
               <tr>
					<td nowrap align="right" class="detail_label">工作计划编号：</td>
					<td class="detail_content">
						<input name="WORK_PLAN_NO" id="WORK_PLAN_NO" json="WORK_PLAN_NO"  type="text"  value="${params.WORK_PLAN_NO}" label="工作计划编号"/>
					</td>
					<td nowrap align="right" class="detail_label">战区编号：</td>
		      		<td nowrap class="detail_content">
		                <input type="hidden" name="selectParams"     id="selectParams" />
					    <input id="WAREA_ID"     name="WAREA_ID"     json="WAREA_ID"  type="hidden"/>
                        <input id="WAREA_NO"     name="WAREA_NO"     json="WAREA_NO"  value="${params.WAREA_NO}" label="战区编号"/> 
                        <img align="absmiddle" name="selJGXX" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"       
										onClick="selCommon('BS_147', false, 'WAREA_NO', 'BMBH', 'forms[1]','WAREA_ID,WAREA_NO,WAREA_NAME', 'BMXXID,BMBH,BMMC', 'selectParams');">				
		      		</td>	
		      	   </tr>
				  <tr>
		      		<td nowrap align="right" class="detail_label">战区名称：</td>
		      		<td nowrap class="detail_content">
	   					<input id="WAREA_NAME" name="WAREA_NAME"  json="WAREA_NAME"  label="战区名称" value="${params.WAREA_NAME}"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">计划年份：</td>
		      		<td nowrap class="detail_content">
		                <select  name="PLAN_YEAR" id="PLAN_YEAR" json="PLAN_YEAR" label="计划年份" style="width:155px;"></select>
		      		</td>
		      		</tr>
		      		<tr>
		      		<td nowrap align="right" class="detail_label">计划月份：</td>
		      		<td nowrap class="detail_content">
		                <select name="PLAN_MONTH" id="PLAN_MONTH" json="PLAN_MONTH" label="计划月份" style="width:155px;"></select>
		      		</td>	
				    <td nowrap align="right" class="detail_label">状态：</td>
		      		<td nowrap class="detail_content" colspan="3">
		                 <select id="STATE" name="STATE" json="STATE" style="width:155px;"></select>
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
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script language="JavaScript">
   SelDictShow("STATE","33","${params.STATE}","");  
   SelDictShow("BRAND","BS_88","${params.BRAND}","");
   spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	
</script>
</body>
<input type="hidden" id="actionType" value="list"/>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" name="PVG_EDIT_AUDIT" id="PVG_EDIT_AUDIT" value="${pvg.PVG_EDIT_AUDIT}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
</html>
<script type="text/javascript">
	SelDictShow("PLAN_YEAR","89","${params.PLAN_YEAR}","");
	SelDictShow("PLAN_MONTH","168","${params.PLAN_MONTH}","");
</script>

