<!--
 * @prjName:喜临门营销平台
 * @fileName:Advreq_List
 * @author ghx
 * @time   2014-07-15 
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
	<script type="text/javascript" src="${ctx}/pages/drp/adv/advreq/Advreq_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" class="wz">
		<tr>
			<c:if test="${module eq 'sh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：营销管理&gt;&gt;广告投放管理&gt;&gt;广告投放申请单审核</td>
			</c:if>
			<c:if test="${empty module || module eq 'wh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：营销管理&gt;&gt;广告投放管理&gt;&gt;广告投放申请单维护</td>			 
			</c:if>			
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
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
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1}">
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
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th  nowrap="nowrap" align="center" dbname="ERP_ADV_REQ_NO" >广告投放申请单号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >渠道编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >所属战区</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_MANAGE_ID" >区域经理</th>
                    <th  nowrap="nowrap" align="center" dbname="ADV_TYPE" >广告投放类型</th>
                    <th  nowrap="nowrap" align="center" dbname="ADV_CONTENT" >广告投放内容</th>
                    <th  nowrap="nowrap" align="center" dbname="ADV_ADDR" >广告投放地点</th>
                    <th  nowrap="nowrap" align="center" dbname="ADV_START_DATE" >投放开始时间</th>
                    <th  nowrap="nowrap" align="center" dbname="ADV_END_DATE" >投放结束时间</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
                    <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.ERP_ADV_REQ_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.ERP_ADV_REQ_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_MANAGE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ADV_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ADV_CONTENT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ADV_ADDR}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ADV_START_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ADV_END_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.REMARK}&nbsp;</td>
                     <input id="state${rst.ERP_ADV_REQ_ID}" type="hidden"  value="${rst.STATE}" />
                     <input id="ADV_TOTAL_AMOUNT${rst.ERP_ADV_REQ_ID}" type="hidden"  value="${rst.ADV_TOTAL_AMOUNT}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
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
			   <tr>
                    <td width="8%" nowrap align="right" class="detail_label">广告投放申请单号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ERP_ADV_REQ_NO" name="ERP_ADV_REQ_NO"  style="width:155" value="${params.ERP_ADV_REQ_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">广告投放类型:</td>
					<td width="15%" class="detail_content">
						<select id="ADV_TYPE" name="ADV_TYPE"  style="width: 155px;" ></select>
					</td>                    
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">广告投放内容:</td>
					<td width="15%" class="detail_content">
						<input id="ADV_CONTENT" name="ADV_CONTENT"  style="width:155" value="${params.ADV_CONTENT}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">广告投放地点:</td>
					<td width="15%" class="detail_content">
						<input id="ADV_ADDR" name="ADV_ADDR"  style="width:155" value="${params.ADV_ADDR}"/>
					</td>
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE" style="width: 155"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">&nbsp;</td>
					<td width="15%" class="detail_content">&nbsp;
					</td>                    
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
<script type="text/javascript">
	//初始化 审批流程
    spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");
    SelDictShow("STATE","33","${params.STATE}","");
    SelDictShow("ADV_TYPE", "BS_111", '${params.ADV_TYPE}', "");	   
</script>
</html>
