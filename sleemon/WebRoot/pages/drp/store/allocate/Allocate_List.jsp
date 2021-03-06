﻿<!--
 * @prjName:喜临门营销平台
 * @fileName:Allocate_List
 * @author lyg
 * @time   2013-09-05 13:29:12 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/allocate/Allocate_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	//页面加载时判断是审核还是维护，修改通用选取条件
		window.onload=function auditSelect(){
			var module = parent.window.document.getElementById("module").value;
			if(module=="sh"){
				document.getElementById("selectParam").value="STATE !='未提交' and DEL_FLAG='0'";
			}else{
				document.getElementById("selectParam").value="DEL_FLAG='0'";
			}
		}
	</script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<c:if test="${params.module=='wh'}">
				<td height="20px">&nbsp;&nbsp;当前位置：库存管理&gt;&gt;调拨管理&gt;&gt;调拨单维护</td>
			</c:if>
			<c:if test="${params.module=='sh'}">
				<td height="20px">&nbsp;&nbsp;当前位置：库存管理&gt;&gt;调拨管理&gt;&gt;调拨单审核</td>
			</c:if>
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
				   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
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
					    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
					   </c:if>
					   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
					    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
					   </c:if>
					   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
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
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="ALLOCATE_NO" >调拨单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="ALLOC_OUT_CHANN_NO" >调出方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ALLOC_OUT_CHANN_NAME" >调出方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="ALLOC_IN_CHANN_NO" >调入方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ALLOC_IN_CHANN_NAME" >调入方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="ALLOC_OUT_STORE_NO" >调出库房编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ALLOC_OUT_STORE_NAME" >调出库房名称</th>
                    <%--<th  nowrap="nowrap" align="center" dbname="STORAGE_FLAG" >库位管理标记</th>
                    --%><th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.ALLOCATE_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.ALLOCATE_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ALLOC_OUT_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ALLOC_OUT_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ALLOC_IN_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ALLOC_IN_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ALLOC_OUT_STORE_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ALLOC_OUT_STORE_NAME}&nbsp;</td><%--
                     <td nowrap="nowrap" align="center"><c:if test="${rst.STORAGE_FLAG==1}">是</c:if><c:if test="${rst.STORAGE_FLAG==0}">否</c:if>&nbsp;</td>
                     --%><td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.ALLOCATE_ID}" type="hidden"  value="${rst.STATE}" />
                    <input id="ALLOC_OUT_STORE_ID${rst.ALLOCATE_ID}" type="hidden"  value="${rst.ALLOC_OUT_STORE_ID}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="14" align="center" class="lst_empty">
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
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			 <input type="hidden" name=selectParams value="STATE in ('启用','停用') and DEL_FLAG='0'">
			 <input type="hidden" id="selectParam" name=selectParam value="">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">调拨单编号:</td>
					<td width="15%" class="detail_content">
						<input id="ALLOCATE_ID" name="ALLOCATE_ID" type="hidden"  style="width:155" value="${params.ALLOCATE_ID}"/>
	   					<input id="ALLOCATE_NO" name="ALLOCATE_NO"  style="width:155" value="${params.ALLOCATE_NO}"/>
	   					<img align="absmiddle"  style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
								onClick="selCommon('BS_45', false, 'ALLOCATE_ID', 'ALLOCATE_ID', 'forms[1]','ALLOCATE_NO', 'ALLOCATE_NO', 'selectParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">调入方编号:</td>
					<td width="15%" class="detail_content">
						<input json="ALLOC_IN_CHANN_ID" name="ALLOC_IN_CHANN_ID" autocheck="true" label="调入方ID" type="hidden" inputtype="string"   value="${params.ALLOC_IN_CHANN_ID}"/> 
	   					<input id="ALLOC_IN_CHANN_NO" name="ALLOC_IN_CHANN_NO"  style="width:155" value="${params.ALLOC_IN_CHANN_NO}"/>
						<img align="absmiddle"  style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
                     onClick="selCommon('BS_19', false, 'ALLOC_IN_CHANN_ID', 'CHANN_ID', 'forms[1]','ALLOC_IN_CHANN_ID,ALLOC_IN_CHANN_NO,ALLOC_IN_CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams')">	   					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">调入方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="ALLOC_IN_CHANN_NAME" name="ALLOC_IN_CHANN_NAME"  style="width:155" value="${params.ALLOC_IN_CHANN_NAME}"/>
					</td>					
<!--					<td width="8%" nowrap align="right" class="detail_label">库位管理标记:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="STORAGE_FLAG" name="STORAGE_FLAG" json="STORAGE_FLAG" type="radio" value="1" <c:if test="${params.STORAGE_FLAG==1 || params.STORAGE_FLAG eq ''}">checked="checked"</c:if> />是-->
<!--						<input id="STORAGE_FLAG" name="STORAGE_FLAG" json="STORAGE_FLAG" type="radio" value="0" <c:if test="${params.STORAGE_FLAG==0 }">checked="checked"</c:if>/>否-->
<!--					</td>				-->
					<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
						<select id="STATE" name="STATE" style="width: 155"></select>
					</td>
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">调出库房编号:</td>
					<td width="15%" class="detail_content">
						<input json="ALLOC_OUT_STORE_ID" name="ALLOC_OUT_STORE_ID" autocheck="true" label="调出方库房ID" type="hidden" inputtype="string"   value="${params.ALLOC_OUT_STORE_ID}"/>
	   					<input id="ALLOC_OUT_STORE_NO" name="ALLOC_OUT_STORE_NO"  style="width:155" value="${params.ALLOC_OUT_STORE_NO}"/>
	   					<img align="absmiddle"  style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
								onClick="selCommon('BS_35', false, 'ALLOC_OUT_STORE_ID', 'STORE_ID', 'forms[1]','ALLOC_OUT_STORE_ID,ALLOC_OUT_STORE_NO,ALLOC_OUT_STORE_NAME', 'STORE_ID,STORE_NO,STORE_NAME', 'selectParams')">
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">调出库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="ALLOC_OUT_STORE_NAME" name="ALLOC_OUT_STORE_NAME"  style="width:155" value="${params.ALLOC_OUT_STORE_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_BEG" name="CRE_TIME_BEG" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.CRE_TIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_END);">
					</td>
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
	   SelDictShow("STATE","33","${param.STATE}","");
	   //初始化 审批流程
      spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");
</script>