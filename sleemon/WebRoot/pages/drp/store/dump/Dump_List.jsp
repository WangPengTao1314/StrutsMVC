<!--
 * @prjName:喜临门营销平台
 * @fileName:转储单
 * @author zzb
 * @time   2013-09-05 14:00:35 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/dump/Dump_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
		    <c:if test="${module eq 'wh'}">
		      <td height="20px">&nbsp;&nbsp;当前位置：库存管理>>转储管理>>转储单维护</td>
		    </c:if>
			<c:if test="${module eq 'sh'}">
		      <td height="20px">&nbsp;&nbsp;当前位置：库存管理>>转储管理>>转储单审核</td>
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
				    <input id="expertExcel" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   <input id="print" type="button" class="btn" value="打印(P)" title="Alt+P" accesskey="P">
				   <!--  <input id="personal" type="button" class="btn" value="个性化设置" >-->
				</td>
				</tr>
			</table>
		</div>
		<div  style="height:0%;width:0%;display: none;">
			<iframe id='printIframe' name='_hiddenIframe' src='#' width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>
<!--			<iframe id="saveiframe" width="0" height="0" ></iframe>-->
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="DUMP_NO" >转储单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="DUMP_OUT_STORE_NO" >转出库房编号</th>
                    <th  nowrap="nowrap" align="center" dbname="DUMP_OUT_STORE_NAME" >转出库房名称</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="DUMP_OUT_STORAGE_FLAG" >转出库位管理标记</th>-->
                    <th  nowrap="nowrap" align="center" dbname="DUMP_IN_STORE_NO" >转入库房编号</th>
                    <th  nowrap="nowrap" align="center" dbname="DUMP_IN_STORE_NAME" >转入库房名称</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="STORAGE_FLAG2" >转入库位管理标记</th>-->
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
                    <th  nowrap="nowrap" align="center" dbname="PRINT_NUM" >打印次数</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.DUMP_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.DUMP_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
                      <input type="hidden" name="DUMP_OUT_STORE_ID" id="DUMP_OUT_STORE_ID${rst.DUMP_ID}" value="${rst.DUMP_OUT_STORE_ID}|${rst.DUMP_OUT_STORAGE_FLAG}"/>
                      ${rst.DUMP_OUT_STORE_NO}&nbsp;
                     </td>
                     <td nowrap="nowrap" align="left">${rst.DUMP_OUT_STORE_NAME}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center">-->
<!--                       <c:if test="${rst.DUMP_OUT_STORAGE_FLAG eq 1}"> 是</c:if>-->
<!--                       <c:if test="${rst.DUMP_OUT_STORAGE_FLAG eq 0}"> 否</c:if>-->
<!--                        &nbsp;-->
<!--                     </td>-->
                     <td nowrap="nowrap" align="center">
                        <input type="hidden" name="DUMP_IN_STORE_ID" id="DUMP_IN_STORE_ID${rst.DUMP_ID}" value="${rst.DUMP_IN_STORE_ID}|${rst.STORAGE_FLAG2}"/>
                       ${rst.DUMP_IN_STORE_NO}&nbsp;
                     </td>
                     <td nowrap="nowrap" align="left">${rst.DUMP_IN_STORE_NAME}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center">-->
<!--                       <c:if test="${rst.STORAGE_FLAG2 eq 1}"> 是</c:if>-->
<!--                       <c:if test="${rst.STORAGE_FLAG2 eq 0}"> 否</c:if>-->
<!--                             &nbsp;-->
<!--                     </td>-->
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PRINT_NUM}&nbsp;</td>
                    <input id="state${rst.DUMP_ID}" type="hidden"  value="${rst.STATE}" />
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
 <input type="hidden" name="selParam" id="selParam" value=" LEDGER_ID='${ZTXXID}' "/>
 <input type="hidden" name="tellOutparams" id="tellOutparams" value=""/>
 <input type="hidden" name="tellInparams" id="tellInparams" value="" />
 <input type="hidden" name="TERM_ID" id="TERM_ID" value="${TERM_ID}" />
 <input type="hidden" name="ZTXXID" id="ZTXXID" value="${ZTXXID}" />
 <input type="hidden" name="TERM_CHARGE" id="TERM_CHARGE" value="${TERM_CHARGE}" />
 <input type="hidden" name="module" id="module" value="${module}" />
 <input type="hidden" name="search" id="search" value="${search}" />
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">转储单编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" name="DUMP_ID" id="DUMP_ID" />
	   					<input id="DUMP_NO" name="DUMP_NO"  style="width:155" value="${params.DUMP_NO}"/>
	   					<img align="absmiddle" name="selStore" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_46', false, 'DUMP_ID', 'DUMP_ID', 'forms[1]','DUMP_NO', 'DUMP_NO', 'selParam');">
						
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">单据类型:</td>
					<td width="15%" class="detail_content">
	   					 <select json="BILL_TYPE" name="BILL_TYPE" id="BILL_TYPE" style="width:155"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">转出库房编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" name="DUMP_OUT_STORE_ID" id="DUMP_OUT_STORE_ID"/>
	   					<input id="DUMP_OUT_STORE_NO" name="DUMP_OUT_STORE_NO"  style="width:155" value="${params.DUMP_OUT_STORE_NO}"/>
	   					<img align="absmiddle" name="selStore" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selTellOutStore();">
				  
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">转出库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="DUMP_OUT_STORE_NAME" name="DUMP_OUT_STORE_NAME"  style="width:155" value="${params.DUMP_OUT_STORE_NAME}"/>
					</td>					
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">转入库房编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" name="DUMP_IN_STORE_ID" id="DUMP_IN_STORE_ID"/>
	   					<input id="DUMP_IN_STORE_NO" name="DUMP_IN_STORE_NO"  style="width:155" value="${params.DUMP_IN_STORE_NO}"/>
	   					<img align="absmiddle" name="selInStore" id="selInStore" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selTellInStore();">
				   
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">转入库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="DUMP_IN_STORE_NAME" name="DUMP_IN_STORE_NAME"  style="width:155" value="${params.DUMP_IN_STORE_NAME}"/>
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
               		 <td width="8%" nowrap align="right" class="detail_label">转储日期从:</td>
					<td width="15%" class="detail_content">
	   					<input id="DUMP_DATE_BEG" name="DUMP_DATE_BEG" readonly="readonly"   onclick="SelectDate();" style="width:155" value="${params.DUMP_DATE_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(DUMP_DATE_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">转储日期到:</td>
					<td width="15%" class="detail_content">
	   					<input id="DUMP_DATE_END" name="DUMP_DATE_END" readonly="readonly"   onclick="SelectDate();"  style="width:155" value="${params.DUMP_DATE_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(DUMP_DATE_END);">
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select name="STATE" id="STATE" style="width:155" ></select>
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
       SelDictShow("BILL_TYPE","64","${params.BILL_TYPE}","");
       SelDictShow("STATE", "33", "${params.STATE}", "");
       //初始化 审批流程
       spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	   
</script>
</html>
