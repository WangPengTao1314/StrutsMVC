<!-- 
 *@module 渠道管理-装修管理
 *@func   装修报销申请单维护
 *@version 1.1
 *@author zzb
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title> </title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/decorationreit/Decorationreit_List.js"></script>
</head>
<body >
	 
    <table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
				<c:if test="${module eq 'wh'}">
			      <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>装修管理>>装修报销申请单维护</td>
			    </c:if>
				<c:if test="${module eq 'sh'}">
			      <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>装修管理>>装修报销申请单审核</td>
			    </c:if>
				<td width="50" align="right"></td>
			</tr>
		    <tr> 
			<td height="20" width="100%">
				<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
					<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
			           <tr>
						   <td id="qxBtnTb" nowrap>
						   	   <c:if test="${pvg.PVG_EDIT eq 1 }">
						   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
							   </c:if>
							   <c:if test="${module eq 'wh'}">
							       <c:if test="${pvg.PVG_EDIT eq 1}">
							            <input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
							       </c:if>
							   </c:if>
							   <c:if test="${module eq 'sh'}">
							       <c:if test="${pvg.PVG_EDIT_AUDIT eq 1}">
							            <input type="hidden" name="PVG_EDIT_AUDIT" id="PVG_EDIT_AUDIT" value="${pvg.PVG_EDIT_AUDIT}"/>
							            <input id="modifyT" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
							       </c:if>
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
							   <input id="print" type="button" class="btn" value="打印(P)" title="Alt+P" accesskey="P">
							   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
							   	<input id="expertExcel" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
						   		<input id="query" type="button" class="btn"  value="查询(F)" title="Alt+F" accesskey="F">
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
					<th nowrap align="center" dbname="RNVTN_REIT_REQ_NO" >装修报销申请单编号</th>
					<th nowrap align="center" dbname="CHANN_RNVTN_NO" >装修申请单号</th>
					<th nowrap align="center" dbname="CHANN_RNVTN_NO" >申请人</th>
					<th nowrap align="center" dbname="CHANN_RNVTN_NO" >申请日期</th>
					<th nowrap align="center" dbname="CHANN_NO" >渠道编号</th>
					<th nowrap align="center" dbname="CHANN_NAME" >渠道名称</th>
					<th nowrap align="center" dbname="TOTAL_REIT_AMOUNT" >总申报金额</th>
					<th nowrap align="center" dbname="REIT_AMOUNT" >本次申报金额</th>
					<th nowrap align="center" dbname="REITED_NUM" >第几次报销</th>
					<th nowrap align="center" dbname="TERM_NO" >终端编号</th>
					<th nowrap align="center" dbname="TERM_NAME" >终端名称</th>
                    <th nowrap align="center" dbname="AREA_NAME" >所属战区名称</th>
                    <th nowrap align="center" dbname="AREA_MANAGE_NAME" >区域经理</th>
                    <th nowrap align="center" dbname="RNVTN_PROP" >装修性质</th>
					<th nowrap align="center" dbname="STATE" >状态</th>		
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%" align='center' >
							<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.RNVTN_REIT_REQ_ID}"/>
						</td>
						<td align="center">${rst.RNVTN_REIT_REQ_NO}&nbsp;</td>
						<td align="center">${rst.CHANN_RNVTN_NO}&nbsp;</td>
						<td align="center">${rst.REQ_NAME}&nbsp;</td>
						<td align="center">${rst.REQ_DATE}&nbsp;</td>
						<td align="center">${rst.CHANN_NO}&nbsp;</td>
						<td align="center">${rst.CHANN_NAME}&nbsp;</td>
						<td align="center">${rst.TOTAL_REIT_AMOUNT}&nbsp;</td>
						<td align="center">${rst.REIT_AMOUNT}&nbsp;</td>
						<td align="center">
					       <c:if test="${rst.REITED_NUM eq 1}">
						     第一次
						   </c:if>
						   <c:if test="${rst.REITED_NUM eq 2}">
						     第二次
						   </c:if>
						   <c:if test="${rst.REITED_NUM eq 3}">
						     第三次
						   </c:if>
						&nbsp;</td>
						<td align="center">${rst.TERM_NO}&nbsp;</td>
						<td align="center">${rst.TERM_NAME}&nbsp;</td>
						<td align="center">${rst.AREA_NAME}&nbsp;</td>
						<td align="center">${rst.AREA_MANAGE_NAME}&nbsp;</td>
						<td align="center">${rst.RNVTN_PROP}&nbsp;</td>
						<td align="center">${rst.STATE}&nbsp;</td>
						<input type="hidden" id="state${rst.RNVTN_REIT_REQ_ID}" value="${rst.STATE}"/>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="18" align="center" class="lst_empty">
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
						<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}' />
						<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}' />
						<input type="hidden" id="orderType" name="orderType" value='${orderType}' />
						<input type="hidden" id="orderId" name="orderId" value='${orderId}' />
						<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}"> &nbsp;
						<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
						<input type="hidden" id="search" name="search" value="${search}" />
						<input type="hidden"   id="module" name="module" value="${module}" json="module"/>
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

<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<input type="hidden" id="ZTXXID" name="ZTXXID" value="${ZTXXID}" />  
<input type="hidden" id="selectParamsT" name="selectParamsT" value="DEL_FLAG=0  and STATE='审核通过' and CHANN_ID in  ${CHANNS_CHARG}" />
<input type="hidden" id="selectTermParam" name="selectTermParam" value="CHANN_ID_P in ${CHANNS_CHARG} and STATE in('启用','停用')" />
<input type="hidden" name="selectChannParam" value="STATE in( '启用','停用') and DEL_FLAG=0 and CHANN_ID in ${CHANNS_CHARG }">
<input type="hidden" id="selAREAParam" name="selAREAParam" value=""/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
				    <td nowrap align="right" class="detail_label">装修报销申请单号：</td>
					<td class="detail_content">
						<input name="RNVTN_REIT_REQ_NO" id="RNVTN_REIT_REQ_NO" json="RNVTN_REIT_REQ_NO"  type="text"  value="${params.RNVTN_REIT_REQ_NO}"/>
					</td>
		      		<td nowrap align="right" class="detail_label">装修申请单号：</td>
					<td class="detail_content">
						<input name="CHANN_RNVTN_NO" id="CHANN_RNVTN_NO" json="CHANN_RNVTN_NO"  type="text"  value="${params.CHANN_RNVTN_NO}"/>
					</td>
		      		<td nowrap align="right" class="detail_label">终端编号：</td>
		      		<td nowrap class="detail_content">
		      		<input json="TERM_ID" name="TERM_ID" id="TERM_ID"  label="终端信息ID" type="hidden"   />
		                <input type="text" name="TERM_NO" id="TERM_NO" json="TERM_NO" value="${params.TERM_NO}"/>
		                <img align="absmiddle" name="" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"
						onClick="selCommon('BS_27', false, 'TERM_NO', 'TERM_NO', 'forms[1]','TERM_NO,TERM_NAME', 'TERM_NO,TERM_NAME', 'selectTermParam')">
		      		</td>
		      		<td nowrap align="right" class="detail_label">终端名称：</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="TERM_NAME" id="TERM_NAME" json="TERM_NAME" value="${params.TERM_NAME}"/>
		      		</td>	
		      	 </tr>
		      	<tr>
		      		<td nowrap align="right" class="detail_label">所属战区名称：</td>
		      		<td nowrap class="detail_content">
		            <input type="text" name="WAREA_NAME" id="WAREA_NAME" json="AREA_NAME" value="${params.AREA_NAME}"/>
		                 <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_137', false, 'WAREA_NAME', 'WAREA_NAME', 'forms[1]','WAREA_NAME', 'WAREA_NAME', 'selAREAParam')">
				
		      		</td>
		      		<td nowrap align="right" class="detail_label">区域经理：</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" value="${params.AREA_MANAGE_NAME}"/>
		      		</td>	
		      		<td nowrap align="right" class="detail_label">渠道编号</td>
		      		<td nowrap class="detail_content"> 
		      		   <input type="text" id="CHANN_NO" name="CHANN_NO" json="CHANN_NO"  value="${params.CHANN_NO}"/>
						<img align="absmiddle" name="" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/select.gif"
										onClick="selCommon('BS_19', true, 'CHANN_NO', 'CHANN_NO', 'forms[1]',
										'CHANN_NO,CHANN_NAME', 
										'CHANN_NO,CHANN_NAME',
										'selectChannParam');"> 
		      		</td>
		      		<td nowrap align="right" class="detail_label">渠道名称</td>
		      		<td nowrap class="detail_content"> 
		      		   	<input type="text" id="CHANN_NAME" name="CHANN_NAME" json="CHANN_NAME"  value="${params.CHANN_NAME}"/>
		      		</td>
		      	</tr>
		      	<tr> 
		      	    <td nowrap align="right" class="detail_label">状态：</td>
		      		<td nowrap class="detail_content" colspan="7">
		                <select style="width:155px;" name="STATE" id="STATE" json="STATE"></select>
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
<script language="JavaScript">
   SelDictShow("RNVTN_PROP","BS_87","${params.RNVTN_PROP}","");
   SelDictShow("STATE","33","${params.STATE}","");  
   SelDictShow("BUSS_SCOPE","BS_88","${params.BUSS_SCOPE}","");
   spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	
</script>
 
</html>