<!-- 
 *@module 渠道管理-上报管理
 *@func   营销经理日报维护
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
	<title>营销经理日报维护列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/mkmdayreport/MkmDayReport_List.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
		<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
		    <tr>
			   <td height="20px" valign="top">
				<table width="100%" cellspacing="0" cellpadding="0" >
				<tr>	
				<c:if test="${empty module || module eq 'wh'}">
				 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>上报管理>>营销经理日报维护</td>
				</c:if>
				<c:if test="${module eq 'sh'}">
				 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>上报管理>>营销经理日报审核</td>
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
						        <!--  
						        <input id="expertExcel" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
						   		-->
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
					<th width="1%"><br></th>
					<th nowrap align="center" >流程编号</th>
                    <th nowrap align="center" >部门名称</th>
					<th nowrap align="center" >拜访人</th>
					<th nowrap align="center" >拜访日期</th>
					<th nowrap align="center" >状态</th>		
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}"  >
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" json="CHANN_VISIT_ID" name="eid" id="eid${rr}" value="${rst.MKM_DAY_RPT_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
						    <input type="hidden" id="state${rst.MKM_DAY_RPT_ID}" value="${rst.STATE}"/>
						    <input type="hidden" value="${rst.MKM_DAY_RPT_ID}" name="MKM_DAY_RPT_ID" json="MKM_DAY_RPT_ID" id="rylb${rst.MKM_DAY_RPT_ID}"/>
						</td>
						<td align="center"><a href="#" onclick="orpenWindow('${rst.MKM_DAY_RPT_ID}');">${rst.MKM_DAY_RPT_NO}</a>&nbsp;</td>
						<td align="center">${rst.WAREA_NAME}&nbsp;</td>
						<td align="center">${rst.MKM_NAME}</td>
						<td align="center">${rst.VST_DATE}&nbsp;</td>
						<td align="center">${rst.STATE}&nbsp;</td>
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      state(document.getElementById('state${rst.MKM_DAY_RPT_ID}'));
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
   <input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
   <input type="hidden" name="selectParamsManage" id="selectParamsManage" />
   <input type="hidden" name=selectDeptParams id="selectDeptParams" value=" BMZT='启用' and JGXXID='00'">
   <input type="hidden" id="selectAreaManager" name="selectAreaManager" value=""/>
 	<tr>
		<td class="detail2">
			 <table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td nowrap align="right" class="detail_label">流程编号：</td>
					<td class="detail_content">
						<input name="MKM_DAY_RPT_NO" id="MKM_DAY_RPT_NO" json="MKM_DAY_RPT_NO"  type="text"  value="${params.MKM_DAY_RPT_NO}" label="流程编号"/>
					</td>
					<td nowrap align="right" class="detail_label">部门名称：</td>
					<td class="detail_content">
					    <input name="WAREA_ID"   id="WAREA_ID"   json="WAREA_ID" type="hidden" value="${params.WAREA_ID}" />
						<input name="WAREA_NAME" id="WAREA_NAME" json="WAREA_NAME" type="text"  value="${params.WAREA_NAME }" READONLY>
		      			<!--   
		      			<img align="absmiddle" name="selJGXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('System_9', false, 'WAREA_ID', 'BMXXID', 'forms[1]','WAREA_ID,WAREA_NAME', 'BMXXID,BMMC', '')">
		      			-->
		      			<img align="absmiddle" name="selJGXX" style="cursor: hand"
							 src="${ctx}/styles/${theme}/images/plus/select.gif"
							 onClick="selCommon('1', false, 'WAREA_ID', 'BMXXID', 'forms[1]','WAREA_ID,WAREA_NAME', 'BMXXID,BMMC', 'selectDeptParams')">
					</td>
					<td nowrap align="right" class="detail_label">营销经理：</td>
					<td class="detail_content">
						<input name="MKM_NAME" id="MKM_NAME" json="MKM_NAME"  type="text"  value="${params.MKM_NAME}" label="营销经理"/>
					    <!--   
					    <img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="getMkmName();">
						-->
						<img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_194', true, 'MKM_NAME', 'AREA_MANAGE_NAME', 'forms[1]','MKM_NAME', 'AREA_MANAGE_NAME', 'selectAreaManager')">						   
					</td>
					<td nowrap align="right" class="detail_label">状态：</td>
					<td class="detail_content">
						<SELECT name="STATE" id="STATE" json="STATE" value="${params.STATE}" style="width:155px;"/>
					</td>
				  </tr>
				  <tr>
					<td nowrap align="right" class="detail_label">上报日期从：</td>
					<td class="detail_content">
						<input name="REPORT_DATE_BEG" id="REPORT_DATE_BEG" json="REPORT_DATE_BEG"  type="text"  value="${params.REPORT_DATE_BEG}" label="上报日期从" onclick="SelectDate()"/>
					    <img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectDate(REPORT_DATE_BEG);">
					</td>
					<td nowrap align="right" class="detail_label">至：</td>
					<td class="detail_content">
					  	<input name="REPORT_DATE_END" id="REPORT_DATE_END" json="REPORT_DATE_END"  type="text"  value="${params.REPORT_DATE_END}" label="上报日期至" onclick="SelectDate()"/>
					    <img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectDate(REPORT_DATE_END);">
					</td>
					<td nowrap align="right" class="detail_label">拜访日期从：</td>
					<td class="detail_content">
						<input name="VST_DATE_BEG" id="VST_DATE_BEG" json="VST_DATE_BEG"  type="text"  value="${params.VST_DATE_BEG}" label="拜访日期从" onclick="SelectDate()"/>
					    <img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectDate(VST_DATE_BEG);">
					</td>
					<td nowrap align="right" class="detail_label">至：</td>
					<td class="detail_content">
						<input name="VST_DATE_END" id="VST_DATE_END" json="VST_DATE_END"  type="text"  value="${params.VST_DATE_END}" label="拜访日期至" onclick="SelectDate()"/>
					    <img align="absmiddle" style="cursor: hand"
										src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
										onclick="SelectDate(VST_DATE_END);">
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
<input type="hidden"   name="module" id="module" value="${module}"/>
<body>&nbsp;</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script language="JavaScript">
   SelDictShow("STATE","33","${params.STATE}","");  
   spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");
</script>
<script type="text/javascript">
    function orpenWindow(MKM_DAY_RPT_ID){
		window.showModalDialog("mkmdayreport.shtml?action=toDetail&MKM_DAY_RPT_ID="+MKM_DAY_RPT_ID,window,"dialogwidth=1000px; dialogheight=800px; status=no");
	 }
</script>
<input type="hidden" id="actionType" value="list"/>
<!-- 模块，页码，排序Id，排序方式，选择记录主键Id -->
<input type="hidden" id="search" value="${search}"/>
<input type="hidden" id="module" value="${module}"/>
<input type="hidden" id="pageNo" value="${pageNo}"/>
<input type="hidden" id="orderId" value="${orderId}"/>
<input type="hidden" id="orderType" value="${orderType}"/>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="paramUrl" value="${paramUrl}"/>
</html>