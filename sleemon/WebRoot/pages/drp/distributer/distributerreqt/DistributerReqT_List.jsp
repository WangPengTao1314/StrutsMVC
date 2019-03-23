<!-- 
 *@module 渠道管理-分销商管理
 *@function   分销渠道信息登记
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
	<title>分销渠道信息登记列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/distributer/distributerreqt/DistributerReqT_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 
</head>
<body >
		<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
			   <td height="20px" valign="top">
				<table width="100%" cellspacing="0" cellpadding="0" >
				<tr>	
					<c:if test="${empty module || module eq 'wh'}">
					 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;&gt;终端管理&gt;&gt;分销渠道信息登记维护</td>
					</c:if>
					<c:if test="${module eq 'sh'}">
					 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理&gt;&gt;终端管理&gt;&gt;分销渠道信息登记审核</td>
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
					<th nowrap align="center" dbname="DISTRIBUTOR_REQ_NO" >申请单编号</th>
					<th nowrap align="center" dbname="REQ_NAME" >申请人</th>
					<th  nowrap="nowrap" align="center" dbname="REQ_DATE" >申请时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DISTRIBUTOR_NAME" >分销渠道名称</th>	
                    <th nowrap align="center" dbname="DISTRIBUTOR_TYPE">分销类型</th>
                    <th nowrap align="center" dbname="CHANN_NAME">所属加盟商渠道名称</th>
                    <th nowrap align="center" dbname="AREA_NAME_P">战区</th>	
                    <th nowrap align="center" dbname="AREA_MANAGE_NAME">营销经理</th>	
					<th nowrap align="center" dbname="STATE" >状态</th>		
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.DISTRIBUTOR_REQ_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
							<input type="hidden" id="state${rst.DISTRIBUTOR_REQ_ID}" value="${rst.STATE}"/>
						</td>
						<td align="center">
							<a href="#" onclick="showDetailPage();return false" style="color: black;font-weight: normal;" title="点击查看详情">
								${rst.DISTRIBUTOR_REQ_NO}&nbsp;
					   		</a>
						</td>
						<td align="center">${rst.REQ_NAME}&nbsp;</td>
						<td align="center">${rst.REQ_DATE}&nbsp;</td>
						<td align="center">${rst.DISTRIBUTOR_NAME}&nbsp;</td>
						<td align="center">${rst.DISTRIBUTOR_TYPE}&nbsp;</td>
						<td align="center">${rst.CHANN_NAME}&nbsp;</td>
						<td align="center">${rst.AREA_NAME_P}&nbsp;</td>
						<td align="center">${rst.AREA_MANAGE_NAME}&nbsp;</td>
						<td align="center">${rst.STATE}&nbsp;</td>												
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
	<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is not null ">
	<input type="hidden" id="selectAreaManager" name="selectAreaManager" value=""/>
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="10%" nowrap align="right" class="detail_label" >申请单编号：</td>
					<td nowrap width="15%" class="detail_content">														
						<input id="DISTRIBUTOR_REQ_NO" json="DISTRIBUTOR_REQ_NO"  name="DISTRIBUTOR_REQ_NO" type="text" inputtype="string"
							  value="${params.DISTRIBUTOR_REQ_NO}" >	
					</td>
					<td width="10%" nowrap align="right" class="detail_label">名称：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="DISTRIBUTOR_NAME" name="DISTRIBUTOR_NAME" id="DISTRIBUTOR_NAME" value="${params.DISTRIBUTOR_NAME }"/>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">类型：</td>
					<td width="15%" class="detail_content">
						<select name="DISTRIBUTOR_TYPE" id="DISTRIBUTOR_TYPE" json="DISTRIBUTOR_TYPE" style="width: 155"></select>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">战区：</td>
					<td width="15%" class="detail_content">
						<input name="AREA_NAME_P" id="AREA_NAME_P" json="AREA_NAME_P" type="text" value="${params.AREA_NAME_P}">
						<img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_26', false, 'AREA_NAME_P', 'AREA_NAME_P', 'forms[1]',
										'AREA_NAME_P', 
										'AREA_NAME_P', 
										'selectParamsArea');">
					</td>
				</tr>
				<tr>
					<td width="10%" nowrap align="right" class="detail_label" >所属加盟商渠道编号：</td>
					<td nowrap width="15%" class="detail_content" >
						<input id="CHANN_NO" json="CHANN_NO"  name="CHANN_NO" type="text" inputtype="string"
								 autocheck="true" maxlength="30" value="${params.CHANN_NO}">
						<img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_187', false, 'CHANN_NO', 'CHANN_NO', 'forms[1]',
																'CHANN_NO,CHANN_NAME,AREA_NAME_P,AREA_MANAGE_NAME,PROV,CITY,COUNTY', 
																'CHANN_NO,CHANN_NAME,AREA_NAME_P,AREA_MANAGE_NAME,PROV,CITY,COUNTY', 
																'selectChann')">
					</td>
					<td width="10%" nowrap align="right" class="detail_label">所属加盟商渠道名称：</td>
					<td width="15%" class="detail_content">
						<input id="CHANN_NAME" name="CHANN_NAME" type="text" inputtype="string"
								 label="所属加盟商渠道名称"  json="CHANN_NAME" value="${params.CHANN_NAME }">
					</td>
					<td width="10%" nowrap align="right" class="detail_label">制单时间从：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="BEGIN_CRE_TIME" id="BEGIN_CRE_TIME"name="BEGIN_CRE_TIME" value="${params.BEGIN_CRE_TIME}" autocheck="true" inputtype="string" label="制单时间从"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(BEGIN_CRE_TIME);"/>
					</td>
					<td width="10%" nowrap align="right" class="detail_label">制单时间到：</td>
					<td width="15%" class="detail_content">
						<input type="text" json="END_CRE_TIME" id="END_CRE_TIME" value="${params.END_CRE_TIME }" name="END_CRE_TIME" autocheck="true" inputtype="string" label="制单时间到"  mustinput="true" onclick="SelectTime();" readonly />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(END_CRE_TIME);"/>
					</td>
				</tr>
				<tr>
					<td width="10%" nowrap align="right" class="detail_label">区域经理：</td>
					<td width="15%" class="detail_content">
					    <input name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" type="text"  value="${params.AREA_MANAGE_NAME}">
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_167', true, 'AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'forms[1]','AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'selectAreaManager')">						   
					</td>
					<td width="10%" nowrap align="right" class="detail_label">省份：</td>
					 <td width="15%" class="detail_content">
	   					<input type="text" id="PROV" name="PROV"    value="${params.PROV}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[1]','PROV,CITY,COUNTY', 'PROV,CITY,COUNTY', '')">
					 </td>
					<td width="10%" nowrap align="right" class="detail_label">城市：</td>
					<td width="15%" class="detail_content">
						<input name="CITY" id="CITY" json="CITY" type="text" value="${params.CITY}">
					</td>
					
					<td width="10%" nowrap align="right" class="detail_label">区县：</td>
					<td width="15%" class="detail_content">
						<input name="COUNTY" id="COUNTY" json="COUNTY" type="text" value="${params.COUNTY}">
					</td>					
				</tr>
				<tr>
				    
				    <td width="10%" nowrap align="right" class="detail_label">联系人：</td>
					<td width="15%" class="detail_content">
						<input name="PERSON_CON" id="PERSON_CON" json="PERSON_CON" type="text"  value="${params.PERSON_CON}">
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
   SelDictShow("DISTRIBUTOR_TYPE", "201", '${params.DISTRIBUTOR_TYPE}', "");
   spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	
</script>
</html>