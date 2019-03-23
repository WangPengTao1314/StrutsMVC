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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/marketcardsale/MarketcardSale_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<c:if test="${module eq 'sh'}">
			  <td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>销售管理>>卡券销售审核</td>
			</c:if>
			<c:if test="${module eq 'wh' || empty module}">
			  <td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>销售管理>>卡券销售</td>
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
                    <th  nowrap="nowrap" align="center" dbname="CARD_SALE_ORDER_NO" >卡券销售单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NO" >门店编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >门店名称</th>
                    <th  nowrap="nowrap" align="center" dbname="MARKETING_ACT_NO" >营销活动编号</th>
                    <th  nowrap="nowrap" align="center" dbname="MARKETING_ACT_NAME" >营销活动名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >所属加盟商</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_PSON_NAME" >业务员</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_DATE" >销售日期</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_CARD_AMOUNT" >销售总额</th>
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
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.CARD_SALE_ORDER_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.CARD_SALE_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TERM_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TERM_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.MARKETING_ACT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.MARKETING_ACT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SALE_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SALE_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SALE_CARD_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="center"> ${rst.DEPT_NAME}&nbsp; </td>
                     <td nowrap="nowrap" align="center">
                       ${rst.STATE}&nbsp;
                      <input id="state${rst.CARD_SALE_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
                      <input id="MARKETING_ACT_ID${rst.CARD_SALE_ORDER_ID}" type="hidden"  value="${rst.MARKETING_ACT_ID}" />
                      <input id="CHANN_ID${rst.CARD_SALE_ORDER_ID}" type="hidden"  value="${rst.CHANN_ID}" />
                      
                     </td>
                   
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
  <input type="hidden" id="selChannParam" name="selChannParam" value=" STATE ='启用' and CHANN_ID in(${CHANNS_CHARG}) "/>
  <input type="hidden" id="selectActParams" name="selectActParams" value=" STATE ='审核通过' "/>
  <input type="hidden" id="selTermParam" name="selTermParam" 
  <c:if test="${empty IS_DRP_LEDGER or IS_DRP_LEDGER eq 0}">
    value=" STATE ='启用' and DEL_FLAG=0 "
  </c:if>
  <c:if test="${1 eq IS_DRP_LEDGER}">
     value=" STATE ='启用' and CHANN_ID_P='${CHANN_ID}' and DEL_FLAG=0 "
  </c:if>
 
  />
 
 <input type="hidden" id="flag" name="flag" value=""/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                  <td width="8%" nowrap align="right" class="detail_label">卡券销售单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="CARD_SALE_ORDER_NO" name="CARD_SALE_ORDER_NO" value="${params.CARD_SALE_ORDER_NO}"/>
					</td>
					
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
					
					<td width="8%" nowrap align="right" class="detail_label">业务员:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="SALE_PSON_NAME" name="SALE_PSON_NAME"   value="${params.SALE_PSON_NAME}"/>
					</td>	
               </tr>
               
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">开始日期:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="SALE_DATE_BEGIN" name="SALE_DATE_BEGIN" onclick="SelectDate();" size="20" value="${params.SALE_DATE_BEGIN}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_BEGIN);">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">结束日期:</td>
					<td width="15%" class="detail_content">
	   					<input type="text" id="SALE_DATE_END" name="SALE_DATE_END" onclick="SelectDate();" size="20" value="${params.SALE_DATE_END}" >
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_END);">
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
                    <td width="8%" nowrap align="right" class="detail_label">终端编号:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" id="TERM_ID" name="TERM_ID" value="${params.TERM_ID}"/>
	   				   <input type="text" id="TERM_NO" name="TERM_NO" value="${params.TERM_NO}"/>
	   				   <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[1]','TERM_NO,TERM_NAME', 'TERM_NO,TERM_NAME', 'selTermParam');"> 
					</td>
					<td width="8%" nowrap align="right" class="detail_label">终端名称:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="TERM_NAME" name="TERM_NAME" value="${params.TERM_NAME}"/>
					</td>
				<c:if test="${empty IS_DRP_LEDGER or IS_DRP_LEDGER eq 0}">
                    <td width="8%" nowrap align="right" class="detail_label">所属加盟商编号:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" id="CHANN_ID" name="CHANN_ID" value="${params.CHANN_ID}"/>
	   				   <input type="text" id="CHANN_NO" name="CHANN_NO" value="${params.CHANN_NO}"/>
	   				   <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selChannParam');"> 
					</td>
					<td width="8%" nowrap align="right" class="detail_label">所属加盟商名称:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="CHANN_NAME" name="CHANN_NAME" value="${params.CHANN_NAME}"/>
					</td>
               </tr>
               <tr>
               </c:if>
                     <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					 <td width="15%" class="detail_content">
					     <select id="STATE" name="STATE" style="width:155" ></select>
					 </td>
					 <td width="8%" nowrap align="right" class="detail_label"> </td>
					 <td width="15%" class="detail_content"></td>
					 <c:if test="${empty IS_DRP_LEDGER or IS_DRP_LEDGER eq 0}">
						 <td width="8%" nowrap align="right" class="detail_label"> </td>
						 <td width="15%" class="detail_content"></td>
						 <td width="8%" nowrap align="right" class="detail_label"> </td>
						 <td width="15%" class="detail_content"></td>
					 </c:if>
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
  //初始化 审批流程
  spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");   
</script>
</html>
