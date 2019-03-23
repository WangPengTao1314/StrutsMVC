<!--
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time  2014-12-03 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/expenseorder/Expenseorder_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<c:if test="${module eq 'sh'}">
			  <td height="20px">&nbsp;&nbsp;当前位置：推广费报销管理>>推广费用报销单审核</td>
			</c:if>
			<c:if test="${module eq 'wh' || empty module}">
			  <td height="20px">&nbsp;&nbsp;当前位置：推广费报销管理>>推广费用报销单维护</td>
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
					<input id="commit" type="button" class="btn" value="提交(C)" title="Alt+C" accesskey="C">
					<input id="revoke" type="button" class="btn" value="撤销(R)" title="Alt+R" accesskey="R">
				   </c:if> 
                   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 }">
				    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
				   </c:if>
				   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
				    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
				   	<input id="expdate" type="button" class="btn" value="导出(I)" title="Alt+I" accesskey="I">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				    <input id="print" type="button" class="btn" value="打印(P)" title="Alt+P" accesskey="P">
				</td>
				</tr>
			</table>
		</div>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="EXPENSE_ORDER_NO" >报销单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="EXPENSE_TYPE" >报销单类别</th>
                    <th  nowrap="nowrap" align="center" dbname="BUDGET_ITEM_NAME" >预算科目名称</th>
                    <th  nowrap="nowrap" align="center" dbname="YEAR" >年份</th>
                    <th  nowrap="nowrap" align="center" dbname="MONTH" >月份</th>
                    <th  nowrap="nowrap" align="center" dbname="EXPENSE_PSON_NAME" >报销人</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >渠道编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
                    <th  nowrap="nowrap" align="center" dbname="EXPENSE_AMOUNT" >报销金额</th>
                    <th  nowrap="nowrap" align="center" dbname="EXPENSE_DATE" >报销日期</th>
                    <th  nowrap="nowrap" align="center" dbname="RELATE_ORDER_NOS" >申请单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RELATE_AMOUNT_REQ" >原申请金额</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.EXPENSE_ORDER_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.EXPENSE_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.EXPENSE_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BUDGET_ITEM_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.YEAR}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.MONTH}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.EXPENSE_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.EXPENSE_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.EXPENSE_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RELATE_ORDER_NOS}&nbsp;</td>
                     <td nowrap="nowrap" align="center">  
                     <a href="javascript:void(0);" onclick="oppenPromoexpenPage(this);" 
                        url="expenseorder.shtml?action=toLookPromoexpen&RELATE_ORDER_NOS=${rst.RELATE_ORDER_NOS}">
                       ${rst.RELATE_AMOUNT_REQ}
                     </a>
                     &nbsp;</td>
                     <td nowrap="nowrap" align="center"> ${rst.DEPT_NAME}&nbsp; </td>
                     <td nowrap="nowrap" align="center">
                       ${rst.STATE}&nbsp;
                      <input id="state${rst.EXPENSE_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
                      <input id="RELATE_AMOUNT_REQ${rst.EXPENSE_ORDER_ID}" type="hidden"  value="${rst.RELATE_AMOUNT_REQ}" />
                     </td>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="20" align="center" class="lst_empty">
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
 <input type="hidden" id="flag" name="flag" value=""/>
 <input type="hidden" name="selectParams" id="selectParams" value=" STATE='启用' and DEL_FLAG=0 ">
 <input type="hidden" name=selectDeptParams id="selectDeptParams" value=" BMZT='启用' and JGXXID='00'">
 <input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
 <input type="hidden" name="selectParamsT"     id="selectParamsT" />
 <input type="hidden" name="selectChannParam" value="STATE in( '启用','停用') and DEL_FLAG=0 and CHANN_ID in ${CHANNS_CHARG }">
 <input type="hidden" name=selectDeptParamsT" id="selectDeptParamsT" value=" BMZT='启用' and JGXXID='00'">
  <input type="hidden" id="search" name="search" value='${search}'/>
 <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                  <td width="8%" nowrap align="right" class="detail_label">费用报销单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="EXPENSE_ORDER_NO" name="EXPENSE_ORDER_NO" value="${params.EXPENSE_ORDER_NO}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">费用报销类型:</td>
					<td width="15%" class="detail_content">
	   					 <select id="EXPENSE_TYPE" name="EXPENSE_TYPE" style="width: 155px;"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">预算科目编号:</td>
					<td width="15%" class="detail_content">
					<input type="hidden" id="BUDGET_ITEM_ID" nbame="BUDGET_ITEM_ID" json="BUDGET_ITEM_ID" value="${params.BUDGET_ITEM_ID}" />
	   				   <input type="text" id="BUDGET_ITEM_NO" name="BUDGET_ITEM_NO"  value="${params.BUDGET_ITEM_NO}"/>
  				      <img align="absmiddle" name="selJGXX" style="cursor: hand"
					   src="${ctx}/styles/${theme}/images/plus/select.gif"
					    onClick="selCommon('BS_155', false, 'BUDGET_ITEM_ID', 'BUDGET_ITEM_ID', 'forms[1]','BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_ITEM_TYPE', 'BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_ITEM_TYPE', 'selectParams')">
					</td>			
					<td width="8%" nowrap align="right" class="detail_label">预算科目名称:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="BUDGET_ITEM_NAME" name="BUDGET_ITEM_NAME"   value="${params.BUDGET_ITEM_NAME}"/>
					</td>
               </tr>
               <tr>
                  <td width="8%" nowrap align="right" class="detail_label">预算科目类型:</td>
					<td width="15%" class="detail_content">
					 <select id="BUDGET_ITEM_TYPE" name="BUDGET_ITEM_TYPE" style="width: 155px;"></select>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">年份:</td>
					<td width="15%" class="detail_content">
	   					 <input type="text" id="YEAR" name="YEAR"  value="${params.YEAR}" />
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">季度:</td>
					<td width="15%" class="detail_content">
	   				   <select name="QUARTER" id="QUARTER" style="width: 155px;"></select>
					</td>			
					<td width="8%" nowrap align="right" class="detail_label">月份:</td>
					<td width="15%" class="detail_content">
	   				   <select name="MONTH" id="MONTH" style="width: 155px;"></select>
					</td>
               </tr>
               <tr>
                  <td width="8%" nowrap align="right" class="detail_label">报销人姓名:</td>
					<td width="15%" class="detail_content">
					  <input type="text" id="EXPENSE_PSON_NAME" name="EXPENSE_PSON_NAME"  value="${params.EXPENSE_PSON_NAME}" />
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">报销部门编号:</td>
					<td width="15%" class="detail_content">
					     <input json="EXPENSE_DEPT_ID" name="EXPENSE_DEPT_ID" id="EXPENSE_DEPT_ID"   type="hidden"   value=""/> 
	   					 <input type="text" id="EXPENSE_DEPT_NO" name="EXPENSE_DEPT_NO"  value="${params.EXPENSE_DEPT_NO}" />
	   					  <img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('1', false, 'EXPENSE_DEPT_ID', 'BMXXID', 'forms[1]','EXPENSE_DEPT_NO,EXPENSE_DEPT_NAME', 'BMBH,BMMC', 'selectDeptParams')">
								
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">报销部门名称:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="EXPENSE_DEPT_NAME" name="EXPENSE_DEPT_NAME"  value="${params.EXPENSE_DEPT_NAME}"/>
					</td>			
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
					     <select id="STATE" name="STATE" style="width:155" ></select>
					</td>	
              
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">报销日期从:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="EXPENSE_DATE_BEG" name="EXPENSE_DATE_BEG" onclick="SelectDate();" size="20" value="${params.EXPENSE_DATE_BEG}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(EXPENSE_DATE_BEG);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">报销日期到:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="EXPENSE_DATE_END" name="EXPENSE_DATE_END" onclick="SelectDate();" size="20" value="${params.EXPENSE_DATE_END}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(EXPENSE_DATE_END);">
					</td>
					
                    <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="CRE_TIME_BEGIN" name="CRE_TIME_BEGIN" onclick="SelectTime();" size="20" value="${params.CRE_TIME_BEGIN}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_BEGIN);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="CRE_TIME_END" name="CRE_TIME_END" onclick="SelectTime();" size="20" value="${params.CRE_TIME_END}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_END);">
					</td>
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">申请单编号:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="RELATE_ORDER_NOS" name="RELATE_ORDER_NOS"  value="${params.RELATE_ORDER_NOS}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单部门</td>
					<td width="15%" class="detail_content">
					    <input name="WAREA_ID"   id="WAREA_ID"   json="WAREA_ID" type="hidden" value="${params.WAREA_ID}" />
					    <input type="text" id="DEPT_NAME" name="DEPT_NAME" json="DEPT_NAME" value="${params.DEPT_NAME}" />
		      			<img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('System_1', false, 'WAREA_ID', 'BMXXID', 'forms[1]','WAREA_ID,DEPT_NAME', 'BMXXID,BMMC', 'selectDeptParamsT')" />
					
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道编号</td>
					<td width="15%" class="detail_content">
						<input type="text" id="CHANN_NO" name="CHANN_NO"  value="${params.CHANN_NO}"/>
						<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', true, 'CHANN_NO', 'CHANN_NO', 'forms[1]',
										'CHANN_NO,CHANN_NAME', 
										'CHANN_NO,CHANN_NAME',
										'selectChannParam');"> 
					</td>
					<td width="8%" nowrap align="right" class="detail_label">渠道名称</td>
					<td width="15%" class="detail_content">
						<input type="text" id="CHANN_NAME" name="CHANN_NAME"  value="${params.CHANN_NAME}"/>
					</td>
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
				    	<input id="q_reset" type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
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
 SelDictShow("STATE","33","${params.STATE}","");
 SelDictShow("EXPENSE_TYPE","BS_150","${params.EXPENSE_TYPE}","");
 SelDictShow("BUDGET_ITEM_TYPE", "BS_145", '${params.BUDGET_ITEM_TYPE}', "");
 SelDictShow("MONTH", "168", '${params.MONTH}', "");
 SelDictShow("QUARTER", "BS_148", '${params.QUARTER}', "");
 //初始化 审批流程
 spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");   
</script>
</html>
