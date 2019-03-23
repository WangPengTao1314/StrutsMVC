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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/erpadvcorderstatements/Erpadvcorderstatements_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<c:if test="${module eq 'sh'}">
			  <td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>订单管理>>结算单审核</td>
			</c:if>
			<c:if test="${module eq 'wh' || empty module}">
			  <td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>订单管理>>结算单维护</td>
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
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
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
                    <th  nowrap="nowrap" align="center" dbname="ADVC_STATEMENTS_ORDER_NO" >结算单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >渠道编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
                    <th  nowrap="nowrap" align="center" dbname="MARKETING_ACT_NAME" >营销活动名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STATEMENTS_DATE" >结算日期</th>
                    <th  nowrap="nowrap" align="center" dbname="STATEMENTS_AMOUNT" >结算总额</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVCS_AMOUNT" >订金总额</th>
                    <th  nowrap="nowrap" align="center" dbname="BANK_AMOUNT" >银行扣点</th>
                    <th  nowrap="nowrap" align="center" dbname="COMMISSION_AMOUNT" >导购员提成</th>
                    <th  nowrap="nowrap" align="center" dbname="GIFT_AMOUNT" >礼品费</th>
                    <th  nowrap="nowrap" align="center" dbname="OTHER_AMOUNT" >其它</th>
                    <th  nowrap="nowrap" align="center" dbname="LAST_STATEMENTS_AMOUNT" >实际结算金额</th>
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
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.ADVC_STATEMENTS_ORDER_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.ADVC_STATEMENTS_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.MARKETING_ACT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATEMENTS_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATEMENTS_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.ADVCS_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BANK_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.COMMISSION_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.GIFT_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.OTHER_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.LAST_STATEMENTS_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center"> ${rst.DEPT_NAME}&nbsp; </td>
                     <td nowrap="nowrap" align="center">
                       ${rst.STATE}&nbsp;
                      <input id="state${rst.ADVC_STATEMENTS_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
                      <input id="MARKETING_ACT_ID${rst.ADVC_STATEMENTS_ORDER_ID}" type="hidden"  value="${rst.MARKETING_ACT_ID}" />
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
 <input type="hidden" name="selectActParams" id="selectActParams" value=" STATE='审核通过' and DEL_FLAG=0 ">
 <input type="hidden" id="selChannParam" name="selChannParam" value=" STATE ='启用' and CHANN_ID in(${CHANNS_CHARG}) "/>
 
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">结算单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ADVC_STATEMENTS_ORDER_NO" name="ADVC_STATEMENTS_ORDER_NO" value="${params.ADVC_STATEMENTS_ORDER_NO}"/>
					</td>
					<td width="8%" align="right" class="detail_label">渠道编号：</td>
				    <td width="15%" class="detail_content">
				     <input type="hidden" json="CHANN_ID" id="CHANN_ID" name="CHANN_ID" value="${rst.CHANN_ID}" />
                     <input json="CHANN_NO" name="CHANN_NO"  label="渠道编号"    type="text" value="${params.CHANN_NO}" /> 
				     <img align="absmiddle" name="selJGXX" style="cursor: hand"
					  src="${ctx}/styles/${theme}/images/plus/select.gif"       
					   onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selChannParam')"> 
				    </td>
                    <td width="8%" align="right" class="detail_label">渠道名称：</td>
				    <td width="15%" class="detail_content">
                       <input json="CHANN_NAME" name="CHANN_NAME"  label="渠道名称"    type="text"     value="${params.CHANN_NAME}"/> 
				     </td>
				     <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					 <td width="15%" class="detail_content">
					     <select id="STATE" name="STATE" style="width:155" ></select>
					 </td>
                 
               </tr>
               <tr>
                  <td width="8%" align="right" class="detail_label">活动编号：</td>
				   <td width="15%" class="detail_content">
				     <input type="hidden" id="MARKETING_ACT_ID" name="MARKETING_ACT_ID" json="MARKETING_ACT_ID" value="${rst.MARKETING_ACT_ID}"/>
                     <input json="MARKETING_ACT_NO" name="MARKETING_ACT_NO" id="MARKETING_ACT_NO" label="活动编号" 
                      type="text" value="${params.MARKETING_ACT_NO}"/> 
                      <img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('BS_149', false, 'MARKETING_ACT_ID', 'MARKETING_ACT_ID', 'forms[1]','MARKETING_ACT_NO,MARKETING_ACT_NAME', 'MARKETING_ACT_NO,MARKETING_ACT_NAME', 'selectActParams');">
						
				   </td>
				   <td width="8%" align="right" class="detail_label"> 活动名称： </td>
					<td width="15%" class="detail_content">
					  <input id="MARKETING_ACT_NAME" json="MARKETING_ACT_NAME" name="MARKETING_ACT_NAME" 
					  label="活动名称"  value="${params.MARKETING_ACT_NAME}" />
					</td>
					<td width="8%" nowrap align="right" class="detail_label">结算日期从:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="STATEMENTS_DATE_BEG" name="STATEMENTS_DATE_BEG" onclick="SelectDate();" size="20" value="${params.STATEMENTS_DATE_BEG}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(STATEMENTS_DATE_BEG);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">结算日期到:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="STATEMENTS_DATE_END" name="STATEMENTS_DATE_END" onclick="SelectDate();" size="20" value="${params.STATEMENTS_DATE_END}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(STATEMENTS_DATE_END);">
					</td>
               </tr>
               <tr>
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
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"> </td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content"> </td>
					
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
 SelDictShow("BILL_TYPE","BS_153","${params.BILL_TYPE}","");
	
  //初始化 审批流程
  spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");   
</script>
</html>
