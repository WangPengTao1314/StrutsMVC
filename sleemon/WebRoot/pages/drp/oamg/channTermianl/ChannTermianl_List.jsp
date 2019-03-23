<!-- 
 *@module 渠道管理-终端管理
 *@func   加盟商门店指标
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
	<title>加盟商门店指标</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/channTermianl/ChannTermianl_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 
</head>
<body >
		<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
			   <td height="20px" valign="top">
				<table width="100%" cellspacing="0" cellpadding="0" >
				<tr>			
					<c:if test="${module eq 'wh'}">
					 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>终端管理>>加盟商门店指标</td>
					</c:if>
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
						   		<input id="add"    type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
							    <input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
							   </c:if>
							   <c:if test="${pvg.PVG_DELETE eq 1 }">
						   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
							   </c:if>
							   <c:if test="${pvg.PVG_BWS eq 1}">
						   		<input id="query" type="button" class="btn"  value="查询(F)" title="Alt+F" accesskey="F">
							   </c:if>
						     &nbsp;&nbsp;&nbsp;年份:
						    <select name="YEAR" id="YEAR" json="YEAR" value="${rst.YEAR}" style="width:100px;" autocheck="true" inputtype="string" onchange="getYear()">
						           <c:forEach items="${list}" var="list">    							
							       <c:choose>
							          <c:when test="${list eq year}">
							              <option selected="selected" value="${list}">${list}</option>    	
							          </c:when>
							          <c:otherwise>
							              <option value="${list}">${list}</option>    	
							          </c:otherwise>
							        </c:choose>  						
							      </c:forEach>
						     </select>
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
					<th nowrap align="center" dbname="RNVTN_SUBST_STD_NO" >指标编号</th>
					<th nowrap align="center" dbname="BRAND" >战区</th>
					<th nowrap align="center" dbname="MIN_AREA" >年份</th>
					<th  nowrap="nowrap" align="center" dbname="STD_AREA" >月份</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >数量</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
						 <td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" json="TERMINAL_QUOTA_ID" name="eid" id="eid${rr}" value="${rst.TERMINAL_QUOTA_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
						</td>
						<td align="center">${rst.TERMINAL_QUOTA_NO}&nbsp;</td>
						<td align="center">${rst.WAREA_NAME}&nbsp;</td>
						<td align="center">${rst.YEAR}&nbsp;</td>
						<td align="center">${rst.MONTH}&nbsp;</td>
						<td align="center">${rst.QUOTA_NUM}&nbsp;</td>
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      //state(document.getElementById('state${rst.RNVTN_SUBST_STD_ID}'));
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
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr> 
				    <td width="8%" nowrap align="right" class="detail_label">门店指标编号:</td>
					<td width="15%" class="detail_content">
						<input id="TERMINAL_QUOTA_NO" name="TERMINAL_QUOTA_NO"  json="TERMINAL_QUOTA_NO"  value="${params.TERMINAL_QUOTA_NO}"/>
					</td>                    		
					<td width="8%"   align="right" class="detail_label">战区名称：</td>
		      		<td  width="15%" class="detail_content">
		                <input type="text" json="WAREA_NAME" id="WAREA_NAME" name="WAREA_NAME"   value="${params.WAREA_NAME}"/>
		      		</td>
		      	   </tr>
		      	   <tr>
		      		<td nowrap align="right" class="detail_label">年份：</td>
		      		<td nowrap class="detail_content">
		                <input name="YEAR" id="YEAR" json="YEAR" value="${params.YEAR}" /> 
		      		</td>	
		      		<td nowrap align="right" class="detail_label">月份：</td>
		      		<td nowrap class="detail_content" >
		                <input type="text" name="MONTH" id="MONTH" json="MONTH" value="${params.MONTH}" />
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
<input type="hidden" name="ORDER_CHANN_ID" json="ORDER_CHANN_ID" id="ORDER_CHANN_ID" value="${rst.CHANN_ID}"/>
<input type="hidden" name="ORDER_CHANN_NO" json="ORDER_CHANN_NO" id="ORDER_CHANN_NO" value="${rst.CHANN_NO}"/>
<input type="hidden" name="ORDER_CHANN_NAME" json="ORDER_CHANN_NAME" id="ORDER_CHANN_NAME" value="${rst.CHANN_NAME}"/>
<input type="hidden" name="AREA_ID" json="AREA_ID" id="AREA_ID" value="${rst.AREA_ID}"/>
<input type="hidden" name="AREA_NO" json="AREA_NO" id="AREA_NO" value="${rst.AREA_NO}"/>
<input type="hidden" name="AREA_NAME" json="AREA_NAME" id="AREA_NAME" value="${rst.AREA_NAME}"/>
<input type="hidden" name="CHANN_ID" id="CHANN_ID" value="${rst.CHANN_ID}"/>
<input type="hidden"   name="module" id="module" value="${module}"/>
<input type="hidden" name="PVG_EDIT_AUDIT" id="PVG_EDIT_AUDIT" value="${pvg.PVG_EDIT_AUDIT}"/>
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script language="JavaScript">
   SelDictShow("STATE","33","${params.STATE}","");  
   SelDictShow("RNVTN_PROP","BS_87","${params.RNVTN_PROP}","");
   SelDictShow("BUSS_SCOPE","BS_88","${paramsT.BUSS_SCOPE}","");
   spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	
</script>
</html>