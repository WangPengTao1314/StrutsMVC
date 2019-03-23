<!-- 
 *@module 渠道管理-终端管理
 *@func   门店变更申请单维护
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
	<title>门店变更请单维护列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/terminalchange/TerminalChange_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 
</head>
<body >
		<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
			   <td height="20px" valign="top">
				<table width="100%" cellspacing="0" cellpadding="0" >
				<tr>			
					<c:if test="${empty module || module eq 'wh'}">
					 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>终端管理>>门店变更申请单维护</td>
					</c:if>
					<c:if test="${module eq 'sh'}">
					 <td height="20px">&nbsp;&nbsp;当前位置：渠道管理>>终端管理>>门店变更申请单审核</td>
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
					<th nowrap align="center" dbname="TERM_NO" >终端编号</th>
					<th nowrap align="center" dbname="TERM_NAME" >终端名称</th>
					<th nowrap align="center" dbname="CHANN_NO_P" >上级渠道编号</th>
					<th  nowrap="nowrap" align="center" dbname="CHANN_NAME_P" >上级渠道名称</th>
                    <th  nowrap="nowrap" align="center" dbname="BUSS_NATRUE" >营业性质</th>
                    <th  nowrap="nowrap" align="center" dbname="LOGIC_TYPE" >物流方式</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NO" >区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME" >区域名称</th>		
					<th  nowrap="nowrap" align="center" dbname="PERSON_CON" >联系人</th>
					<th nowrap align="center" dbname="TEL" >联系电话</th>		
					<th nowrap align="center" dbname="STATE" >状态</th>	
					<th nowrap align="center" dbname="caozuo" >操作</th>		
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.TERMINAL_CHANGE_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
							<input type="hidden" name="TERMINAL_CHANGE_ID" id="TERMINAL_CHANGE_ID" value="${rst.TERMINAL_CHANGE_ID}"/>
							<input type="hidden" id="state${rst.TERMINAL_CHANGE_ID}" value="${rst.STATE}"/>
						</td>
						<td align="center">${rst.TERM_NO}&nbsp;</td>
						<td align="center">${rst.TERM_NAME}&nbsp;</td>
						<td align="center">${rst.CHANN_NO_P}&nbsp;</td>
						<td align="center">${rst.CHANN_NAME_P}&nbsp;</td>
						<td align="center">${rst.BUSS_NATRUE}&nbsp;</td>
						<td align="center">${rst.LOGIC_TYPE}&nbsp;</td>
						<td align="center">${rst.AREA_NO}&nbsp;</td>
						<td align="center">${rst.AREA_NAME}&nbsp;</td>
						<td align="center">${rst.PERSON_CON}&nbsp;</td>
						<td align="center">${rst.TEL}&nbsp;</td>
						<td align="center">${rst.STATE}&nbsp;</td>
						<td align="center">
						   <input type="button" name="btn" id="btn" value="查看历史" class="btn" onclick="orpenWindow('${rst.TERM_NO}')"/>  
					    </td>
						<script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      state(document.getElementById('state${rst.TERMINAL_CHANGE_ID}'));
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
<input type="hidden" name="selectParams" value="CHANN_ID_P in ${CHANN_ID} and  STATE in( '启用') and DEL_FLAG='0' ">
<input type="hidden" name="selectParamsT" value=" STATE in( '启用')">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td nowrap align="right" class="detail_label">终端编号：</td>
		      		<td nowrap class="detail_content">
		                <input id="TERM_NO" name="TERM_NO" json="TERM_NO" style="width: 155" value="${params.TERM_NO}" /> 
		                <input json="TERM_ID" name="TERM_ID" id="TERM_ID" type="hidden" label="终端ID">
						<img align="absmiddle" name="selTERM_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_27', false, 'TERM_NO', 'TERM_NO', 'forms[1]','TERM_ID,TERM_NO,TERM_NAME,TERM_TYPE,TERM_LVL', 'TERM_ID,TERM_NO,TERM_NAME,TERM_TYPE,TERM_LVL','selectParams')">
		      		</td>
		      		<td nowrap align="right" class="detail_label">终端名称：</td>
		      		<td nowrap class="detail_content">
		                <input type="text" name="TERM_NAME" id="TERM_NAME" json="TERM_NAME" value="${params.TERM_NAME}" />
		      		</td>
		      		<td nowrap align="right" class="detail_label">终端类型：</td>
		      		<td nowrap class="detail_content">
		                <select type="text" name="TERM_TYPE" id="TERM_TYPE" json="TERM_TYPE" value="${params.TERM_TYPE}" style="width:155px;"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">终端级别：</td>
		      		<td nowrap class="detail_content">
		                <select type="text" name="TERM_LVL" id="TERM_LVL" json="TERM_LVL" value="${params.TERM_LVL}" style="width:155px;"/>
		      		</td>
		      	  </tr>
		      	  <tr>	
		      		<td nowrap align="right" class="detail_label">上级渠道编号：</td>
		      		<td nowrap class="detail_content">
		                <input json="CHANN_NO_P" name="CHANN_NO_P" id="CHANN_NO_P"    value="${params.CHANN_NO_P}" label="上级渠道编号" />
						<input json="CHANN_ID_P" name="CHANN_ID_P" id="CHANN_ID_P"    type="hidden" label="上级渠道ID">
						<img align="absmiddle" name="selCHANN_NO_P" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'CHANN_NO_P', 'CHANN_NO', 'forms[1]','CHANN_ID_P,CHANN_NO_P,CHANN_NAME_P', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParamsT')">
		      		</td>
		      		<td nowrap align="right" class="detail_label">上级渠道名称：</td>
		      		<td nowrap class="detail_content">
		                <input id="CHANN_NAME_P" name="CHANN_NAME_P" json="CHANN_NAME_P" label="上级渠道名称" value="${params.CHANN_NAME_P}"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">区域编号：</td>
		      		<td nowrap class="detail_content">
		                <input json="AREA_NO" name="AREA_NO" id="AREA_NO" label="区域编号" value="${params.AREA_NO}" />
						<input json="AREA_ID" name="AREA_ID" id="AREA_ID" label="区域ID"  type="hidden" />
						<img align="absmiddle" name="selAREA_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_18', false, 'AREA_NO', 'AREA_NO', 'forms[1]','AREA_ID,AREA_NO,AREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME', 'selectParamsT')">
		      		</td>
		      		<td nowrap align="right" class="detail_label">区域名称：</td>
		      		<td nowrap class="detail_content">
		                <input id="AREA_NAME" name="AREA_NAME" json="AREA_NAME"  label="区域名称" value="${params.AREA_NAME}"/>
		      		</td>
		      	  </tr>
				  <tr>
		      		<td nowrap align="right" class="detail_label">状态：</td>
		      		<td nowrap class="detail_content">
		                <select style="width:155px;" name="STATE" id="STATE" json="STATE"></select>
		      		</td>	
		      		<td nowrap align="right" class="detail_label">制单时间从：</td>
		      		<td nowrap class="detail_content">
		                <input id="CRE_TIME_FROM" name="CRE_TIME_FROM" json="CRE_TIME_FROM" label="日期" value="${params.CRE_TIME_FROM}" onclick="SelectTime()" READONLY/>
		                <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(CRE_TIME_FROM);"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">制单时间到：</td>
		      		<td nowrap class="detail_content" colspan="8">
		                <input id="CRE_TIME_TO"   name="CRE_TIME_TO"   json="CRE_TIME_TO"   label="日期" value="${params.CRE_TIME_TO}" onclick="SelectTime()" READONLY/>
		                <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(CRE_TIME_TO);"/>
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
   SelDictShow("TERM_TYPE","175","${params.TERM_TYPE }","");
   SelDictShow("TERM_LVL","176","${params.TERM_LVL }","");	
   spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");	
</script>
</html>