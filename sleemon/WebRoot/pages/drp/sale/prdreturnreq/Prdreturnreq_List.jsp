<!--
 * @prjName:喜临门营销平台
 * @fileName:prdreturnreq_List
 * @author wzg
 * @time   2013-08-15 10:17:13 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/prdreturnreq/Prdreturnreq_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;退货管理&gt;&gt;<c:out value="${moduleName}"></c:out></td>
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
                   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 or pvg.PVG_BWS_AUDIT0 eq 1 }">
				    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
				   </c:if>
				   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
				    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   <c:if test="${pvg.PVG_AUDIT_AUDIT eq 1 or pvg.PVG_BWS_AUDIT0 eq 1 }">
				    <input id="expdate" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
				   </c:if>
				  <!--  <input id="personal" type="button" class="btn" value="个性化设置" >  -->
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
                    <th  nowrap="nowrap" align="center" dbname="PRD_RETURN_REQ_NO" >退货申请单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="RETURN_CHANN_NO" >退货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RETURN_CHANN_NAME" >退货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NO" >收货方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RETURN_STORE_NAME" >退货出库库房名称</th>
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
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.PRD_RETURN_REQ_ID}"
						           recvChannId="${rst.RECV_CHANN_ID}" returnStoreId="${rst.RETURN_STORE_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.PRD_RETURN_REQ_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RETURN_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RETURN_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECV_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RETURN_STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.PRD_RETURN_REQ_ID}" type="hidden"  value="${rst.STATE}" />
                    <input id="RETURN_CHANN_ID${rst.PRD_RETURN_REQ_ID}" type="hidden"  value="${rst.RETURN_CHANN_ID}" />
                    <input id="AREA_SER_ID${rst.PRD_RETURN_REQ_ID}" type="hidden"  value="${rst.AREA_SER_ID}" />
                    <input id="RETURN_STORE_ID${rst.PRD_RETURN_REQ_ID}" type="hidden"  value="${rst.RETURN_STORE_ID}" />
                    
                    
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="12" align="center" class="lst_empty">
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
<form id="queryForm" name="queryForm" method="post" action="#">
     <input type="hidden" id="queryState" name="queryState" value="queryState${module}"/>
     <input type="hidden" id="ZTXXID" name="ZTXXID" value="${ZTXXID}"/>
     <input type="hidden" id="search" name="search" value="${search}"/>
     <input type="hidden" id="RECV_CHANN_ID_Search" name="RECV_CHANN_ID_Search" value="${RECV_CHANN_ID}"/>
     <!-- 0156829--start--刘曰刚--2014-06-20 -->
     <!-- 修改订货订单维护通用选取查询条件为已处理，修改人员信息查询条件为RYZT in 启用，停用 and RYLB不等于门店 ，修改退货出库库房过滤调终端库房 -->
     <input id="selectSTORECondition" name="selectSTORECondition" type="hidden" value=" STATE in ( '启用','停用' ) and TERM_STROE_FLAG=0 "/>
     <input id="selectREQParam" name="selectREQParam" type="hidden" value=" ${params.addCheck }  and ${params.addQXJB}   " />
     <input id="selectCondition" name="selectCondition" type="hidden" value="  STATE in ( '启用','停用' )  "/>
     <input id="selectReturn" name="selectReturn" type="hidden" value=""/>
     <input id="selGoodorderParams" name="selGoodorderParams" type="hidden" value=" ${params.addCheck } and  STATE ='已处理'  "/>
     <input id="selectRYXX" name="selectRYXX" type="hidden" value=" ${params.empSql } and RYZT in ('启用','停用') and RYLB!='门店' "/>
      <!-- 0156829--End--刘曰刚--2014-06-20 -->
     
  <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">退货申请单编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" id="PRD_RETURN_REQ_ID" name="PRD_RETURN_REQ_ID"/>
	   					<input id="PRD_RETURN_REQ_NO" name="PRD_RETURN_REQ_NO"  style="width:155" value="${params.PRD_RETURN_REQ_NO}"/>
	   					 <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				     		onclick="selCommon('BS_72', false, 'PRD_RETURN_REQ_ID', 'PRD_RETURN_REQ_ID', 'queryForm','PRD_RETURN_REQ_NO', 'PRD_RETURN_REQ_NO','selectREQParam');"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">退货方编号:</td>
					<td width="15%" class="detail_content">
						<input id="RETURN_CHANN_ID" type="hidden" name="RETURN_CHANN_ID"  style="width:155" value="${params.RETURN_CHANN_ID}"/>
	   					<input id="RETURN_CHANN_NO" name="RETURN_CHANN_NO"  style="width:155" value="${params.RETURN_CHANN_NO}"/>
	   					 <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				     		onclick="selCommon('BS_19', false, 'RETURN_CHANN_ID', 'CHANN_ID', 'queryForm','RETURN_CHANN_NO,RETURN_CHANN_NAME', 'CHANN_NO,CHANN_NAME','selectReturn');"/>&nbsp;
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">退货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="RETURN_CHANN_NAME" name="RETURN_CHANN_NAME"  style="width:155" value="${params.RETURN_CHANN_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">制单人:</td>
					<td width="15%" class="detail_content"> 
					 	<input id="CREATOR" type="hidden" name="CREATOR"  style="width:155" value=""/>
	   					<input id="CRE_NAME" name="CRE_NAME"  style="width:155" value="${params.CRE_NAME}"/>
	   					<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				     		onclick="selCommon('BS_97', false, 'CREATOR', 'XTYHID', 'queryForm','CRE_NAME', 'XM','selectRYXX');"/>&nbsp;
					
					</td>				
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">货品编号</td>
					<td width="15%" class="detail_content">
						<input id="PRD_NO" json="PRD_NO"  name="PRD_NO" type="text" inputtype="string"   value="${params.PRD_NO}" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">货品名称</td>
					<td width="15%" class="detail_content">
						<input name="PRD_NAME" type="text" value="${params.PRD_NAME}" id="PRD_NAME" json="PRD_NAME">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">规格型号：</td>
					<td width="15%" class="detail_content">
						<input type="text" value="${params.PRD_SPEC}" id="PRD_SPEC" name="PRD_SPEC"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
               </tr>
               <tr>
<!--           			 <td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--						<input id="RECV_CHANN_ID" type="hidden" name="RECV_CHANN_ID"  style="width:155" value="${params.RECV_CHANN_ID}"/>-->
<!--	   					<input id="RECV_CHANN_NO" name="RECV_CHANN_NO"  style="width:155" value="${params.RECV_CHANN_NO}"/>-->
<!--	   					<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" -->
<!--				     		onclick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'queryForm','RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_NO,CHANN_NAME','selectCondition');"/>&nbsp;-->
<!--					</td>-->
<!--                    <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="RECV_CHANN_NAME" name="RECV_CHANN_NAME"  style="width:155" value="${params.RECV_CHANN_NAME}"/>-->
<!--					</td>					-->
                    <td width="8%" nowrap align="right" class="detail_label">退货出库库房编号:</td>
					<td width="15%" class="detail_content">
						<input id="RETURN_STORE_ID" type="hidden" name="RETURN_STORE_ID"  style="width:155" value="${params.RETURN_STORE_ID}"/>
	   					<input id="RETURN_STORE_NO" name="RETURN_STORE_NO"  style="width:155" value="${params.RETURN_STORE_NO}"/>
	   					<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				         onclick="selCommon('BS_35', false, 'RETURN_STORE_ID', 'STORE_ID', 'queryForm','RETURN_STORE_NO,RETURN_STORE_NAME', 'STORE_NO,STORE_NAME','selectSTORECondition');"/>&nbsp;
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">退货出库库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="RETURN_STORE_NAME" name="RETURN_STORE_NAME"  style="width:155" value="${params.RETURN_STORE_NAME}"/>
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
                   
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE"  style="width:155"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">订货订单编号:</td>
					<td width="15%" class="detail_content">
					  <input type="hidden" id="GOODS_ORDER_ID" name="GOODS_ORDER_ID"  value="">
					  <input type="text" id="GOODS_ORDER_NO" name="GOODS_ORDER_NO"  value="${params.GOODS_ORDER_NO}">
					  <img align="absmiddle" name="dhdd" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				         onclick="selCommon('BS_56', false, 'GOODS_ORDER_ID', 'GOODS_ORDER_ID', 'queryForm','GOODS_ORDER_NO', 'GOODS_ORDER_NO','selGoodorderParams');"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
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

	   SelDictShow("STATE","33","${params.STATE}","");
	   //初始化 审批流程
       spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
</html>
