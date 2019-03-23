<!-- 
 *@module 渠道管理-分销商管理
 *@function   分销商购货月报
 *@version 1.1
 *@author gu_hx
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>装修申请单维护列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/distributer/distributersalerpt/DistributerSalerpt_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 
</head>
<body >
		<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
			   <td height="20px" valign="top">
				<table width="100%" cellspacing="0" cellpadding="0" >
				<tr>			
					<c:if test="${empty module || module eq 'wh'}">
					 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;&gt;分销商管理&gt;&gt;分销商购货月报维护</td>
					</c:if>
					<c:if test="${module eq 'sh'}">
					 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;&gt;分销商管理&gt;&gt;分销商购货月报审核</td>
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
							    <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A">
							   </c:if>
							   <c:if test="${pvg.PVG_TRACE eq 1 or pvg.PVG_TRACE_AUDIT eq 1}">
							    <input id="flow" type="button" class="btn" value="流程跟踪(T)" title="Alt+T" accesskey="T">
							   </c:if>
							   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
						   		<input id="query" type="button" class="btn"  value="查询(F)" title="Alt+F" accesskey="F">
						   		<!--  
						   		<input id="expdata" type="button" class="btn" value="导出(X)" title="Alt+X" accesskey="X">
						   		-->
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
					<th width="1%"></th>
					<th nowrap align="center" dbname="DISTRIBUTOR_SALE_RPT_NO" >提报编号</th>
					<th nowrap align="center" dbname="CRE_NAME" >提报人</th>
					<th  nowrap="nowrap" align="center" dbname="CRE_TIME" >提报时间</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >提报人渠道名称</th>                    	
					<th nowrap align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.DISTRIBUTOR_SALE_RPT_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
							<input type="hidden" id="state${rst.DISTRIBUTOR_SALE_RPT_ID}" value="${rst.STATE}"/>
							<input type="hidden" name="DISTRIBUTOR_SALE_RPT_ID" id="DISTRIBUTOR_SALE_RPT_ID" json="DISTRIBUTOR_SALE_RPT_ID" value="${rst.DISTRIBUTOR_SALE_RPT_ID}"/>
						</td>
						<td align="center">
							<a href="#" onclick="showDetailPage();return false"  title="点击查看详情">
								${rst.DISTRIBUTOR_SALE_RPT_NO}&nbsp;
					   		</a>
						</td>
						<td align="center">${rst.CRE_NAME}&nbsp;</td>
						<td align="center">${rst.CRE_TIME}&nbsp;</td>						
						<td align="center">${rst.CHANN_NAME}&nbsp;</td>						
						<td align="center">${rst.STATE}&nbsp;</td>	
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      state(document.getElementById('state${rst.DISTRIBUTOR_SALE_RPT_ID}'));
						   });
					    </script>											
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="10" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
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
									<input type="hidden" id="pageSize" name="pageSize"
										value='${page.pageSize}' />
									<input type="hidden" id="pageNo" name="pageNo"
										value='${page.currentPageNo}' />
									<input type="hidden" id="orderType" name="orderType"
										value='${orderType}' />
									<input type="hidden" id="orderId" name="orderId"
										value='${orderId}' />
									<input type="hidden" id="selRowId" name="selRowId"
										value="${selRowId}">
									&nbsp;
									<input type="hidden" id="paramUrl" name="paramUrl"
										value="${paramCover.coveredUrl}">
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
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<input type="hidden" name="selectChann" value=" STATE in( '启用') and DEL_FLAG='0' ">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="10%" nowrap align="right" class="detail_label" >提报人渠道：</td>
					<td nowrap width="15%" class="detail_content" >						
						<input id="CHANN_NAME" name="CHANN_NAME" type="text" inputtype="string"
								 label="提报人渠道"  json="CHANN_NAME" value="${params.CHANN_NAME }">
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_187', false, 'CHANN_NAME', 'CHANN_NAME', 'forms[1]',
																'CHANN_NAME', 
																'CHANN_NAME', 
																'selectChann')">
					</td>
					<td width="10%" nowrap align="right" class="detail_label">提报人：</td>
					<td width="15%" class="detail_content">
						<input name="CRE_NAME" id="CRE_NAME" json="CRE_NAME" type="text"  value="${params.CRE_NAME}">
					</td>
					<td width="10%" nowrap align="right" class="detail_label">提报时间从：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="BEGIN_CRE_TIME" id="BEGIN_CRE_TIME"name="BEGIN_CRE_TIME" value="${params.BEGIN_CRE_TIME}" autocheck="true" inputtype="string" label="制单时间从"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(BEGIN_CRE_TIME);"/>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">提报时间到：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="END_CRE_TIME" id="END_CRE_TIME" value="${params.END_CRE_TIME }" name="END_CRE_TIME" autocheck="true" inputtype="string" label="制单时间到"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(END_CRE_TIME);"/>
					</td>					
				</tr>				
				<tr>
					<td width="10%" nowrap align="right" class="detail_label">月报时间：</td>
					<td width="15%" class="detail_content">					
					&nbsp;<select json="YEAR" id="YEAR" name="YEAR" style="width: 80px" ></select>年
				   	&nbsp;<select json="MONTH" id="MONTH" name="MONTH" style="width: 80px" ></select>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select name=STATE id="STATE" json="STATE" style="width: 155"></select>
					</td>
					<td width="10%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">						
					</td>										
					<td width="10%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
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
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script language="JavaScript">
   SelDictShow("STATE","193","${params.STATE}","");
   SelDictShow("YEAR","89","${params.YEAR}","");
   SelDictShow("MONTH","168","${params.MONTH}","");
   spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	
</script>
</html>