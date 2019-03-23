<!--
 * @prjName:喜临门营销平台
 * @fileName:Areasaleplan_List
 * @author zcx
 * @time   2014-12-5 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/areasaleplan/Areasaleplan_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body onload="load()">
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		 <table width="100%" cellspacing="0" cellpadding="0" >
		 <tr>			
			<c:if test="${empty module ||module eq 'wh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：营销管理&gt;&gt;销售管理&gt;&gt;区域销售指标设定维护</td>
			</c:if>
			<c:if test="${module eq 'sh'}">
			 <td height="20px">&nbsp;&nbsp;当前位置：营销管理&gt;&gt;销售管理&gt;&gt;区域销售指标设定审核</td>
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
			   		<!-- 
					<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
					-->
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
				   <c:if test="${pvg.PVG_BATCH eq 1 }">
				   <input id="batch" type="button" class="btn" value="批量编辑(B)" title="Alt+B" accesskey="T" onclick="orpenWindow()">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
			   		<input id="query" type="button" class="btn"  value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   &nbsp;
				   <input id="queryByArea" type="button" class="btn"  value="按战区/按区域查看(Q)" title="Alt+Q" accesskey="Q" onclick="setHiddenCol(document.getElementById('ordertb'),1)">
				   &nbsp;&nbsp;&nbsp;
				    年份:
				    <select name="PLAN_YEAR" id="PLAN_YEAR" json="PLAN_YEAR" value="${rst.PLAN_YEAR}" style="width:100px;" autocheck="true" inputtype="string" onchange=getYear()>
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
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">			
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
				    <th width="1%"><br></th>
					<th nowrap align="center" dbname="AREA_NO" style="display:block;" id="AreaNo">
					    区域编号
					</th>
					<th nowrap align="center" dbname="AREA_NAME" style="display:block;" id="AreaName">
					    区域名称
					</th>
					<th nowrap align="center" dbname="WAREA_NO"  style="display:block;" id="WareaNo" >
					    战区编号
					</th>
					<th nowrap align="center" dbname="WAREA_NAME" style="display:block;" id="WareaName">
					    战区名称
					</th>
					<th nowrap align="center" dbname="AREA_MANAGE_NAME">区域经理名称</th>
					<th nowrap align="center" dbname="PLAN_YEAR" >年度指标</th>
                    <th nowrap align="center" dbname="STATE" >1月</th>
                    <th nowrap align="center" dbname="STATE" >2月</th>
                    <th nowrap align="center" dbname="STATE" >3月</th>
                    <th nowrap align="center" dbname="STATE" >第一季度累计</th>
                    <th nowrap align="center" dbname="STATE" >4月</th>
                    <th nowrap align="center" dbname="STATE" >5月</th>
                    <th nowrap align="center" dbname="STATE" >6月</th>
                    <th nowrap align="center" dbname="STATE" >第二季度累计</th>
                    <th nowrap align="center" dbname="STATE" >7月</th>
                    <th nowrap align="center" dbname="STATE" >8月</th>
                    <th nowrap align="center" dbname="STATE" >9月</th>
                    <th nowrap align="center" dbname="STATE" >第三季度累计</th>
                    <th nowrap align="center" dbname="STATE" >10月</th>
                    <th nowrap align="center" dbname="STATE" >11月</th>
                    <th nowrap align="center" dbname="STATE" >12月</th>
                    <th nowrap align="center" dbname="STATE" >第四季度累计</th>
                    <th nowrap align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.AREA_SALE_PLAN_ID}" onclick="setSelEid(document.getElementById('eid${rr}'));"/>
							<input type="hidden" name="AREA_SALE_PLAN_ID" id="AREA_SALE_PLAN_ID" value="${rst.AREA_SALE_PLAN_ID}"/>
							<input type="hidden" id="state${rst.AREA_SALE_PLAN_ID}" value="${rst.STATE}"/>
						</td>
					<td align="center" id="AreaNoT" style="display:block;">
						      ${rst.AREA_NO}&nbsp;
					</td>
					<td align="center" id="AreaNameT" style="display:block;">
					      ${rst.AREA_NAME}&nbsp;
					</td>
					<td align="center" id="WareaNoT${rr}" style="display:block;">
					     ${rst.WAREA_NO}
					</td>
					<td align="center" id="WareaNameT${rr}" style="display:block;">
					      ${rst.WAREA_NAME}
					</td>
                     <td nowrap="nowrap" align="center">${rst.AREA_MANAGE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.YEAR_PLAN_AMOUNT}</td>
                     <td nowrap="nowrap" align="center">${rst.JAN_AMOUNT}</td>
                     <td nowrap="nowrap" align="center">${rst.FEB_AMOUNT}&nbsp;</td>                     
                     <td nowrap="nowrap" align="center">${rst.MAR_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FIRST_QUARTER_AMOUNT}&nbsp;</td>
                     
                     <td nowrap="nowrap" align="center">${rst.APR_AMOUNT}</td>
                     <td nowrap="nowrap" align="center">${rst.MAY_AMOUNT}&nbsp;</td>                     
                     <td nowrap="nowrap" align="center">${rst.JUN_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SECOND_QUARTER_AMOUNT}&nbsp;</td>
                     
                     <td nowrap="nowrap" align="center">${rst.JUL_AMOUNT}</td>
                     <td nowrap="nowrap" align="center">${rst.AUG_AMOUNT}&nbsp;</td>                     
                     <td nowrap="nowrap" align="center">${rst.SEP_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.THIRD_QUARTER_AMOUNT}&nbsp;</td>
                     
                     <td nowrap="nowrap" align="center">${rst.OCT_AMOUNT}</td>
                     <td nowrap="nowrap" align="center">${rst.NOV_AMOUNT}&nbsp;</td>                     
                     <td nowrap="nowrap" align="center">${rst.DEC_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FOURTH_QUARTER_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;<input type="hidden" name="state" id="state${rst.STATE}" value="${rst.STATE}"/></td>
                     <script type="text/javascript">
						   $("#tr${rr}").click(function(){
						      selectThis(this);
						      setSelEid(document.getElementById('eid${rr}'));
						      state(document.getElementById('state${rst.AREA_SALE_PLAN_ID}'));
						   });
					    </script>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="35" align="center" class="lst_empty">
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
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">			
			<input type="hidden" name=selectTERM value="STATE in ('启用') and DEL_FLAG=0 and CHANN_ID_P in ${CHANNS_CHARG}">
			<input type="hidden" name="selectParamsChann" value="STATE in( '启用') and DEL_FLAG='0' and CHANN_ID in ${CHANNS_CHARG} ">
			<input type="hidden" name="selectParamsArea" value="STATE in( '启用') and DEL_FLAG='0'">
			<input type="hidden" name="selectArea" value=" RYZT='启用' and DELFLAG=0 and JGXXID='${JGXXID}' ">
			<input type="hidden" name="selectParams" />
			    <tr>
                    <td width="8%" nowrap align="right" class="detail_label">区域编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="AREA_NO" name="AREA_NO" json="AREA_NO" value="${params.AREA_NO}" type="text"/>
	   					<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
						     onClick="selCommon('BS_138', false, 'AREA_NO', 'AREA_NO', 'forms[1]','AREA_NO,AREA_NAME,WAREA_NAME', 'AREA_NO,AREA_NAME,AREA_NAME_P', 'selectParams')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">区域名称:</td>
				   <td width="15%" class="detail_content">
						<input id="AREA_NAME" name="AREA_NAME" json="AREA_NAMEs"  value="${params.AREA_NAME}" />
	   			   </td>
	   			   <td width="8%" nowrap align="right" class="detail_label">战区名称:</td>
				   <td width="15%" class="detail_content">
						<input id="WAREA_NAME" name="WAREA_NAME" json="WAREA_NAME"  value="${params.WAREA_NAME}" />
	   			   </td>
               </tr>
               <tr>
					<td width="8%" nowrap align="right" class="detail_label">区域经理:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" id="AREA_MANAGE_ID" name="AREA_MANAGE_ID" inputtype="string" autocheck="true" label="区域经理ID" json="AREA_MANAGE_ID"  value="${rst.AREA_MANAGE_ID}"   readonly/>
	   					<input id="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME"  style="width:155" value="${params.AREA_MANAGE_NAME}"/>
					    <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('System_0', false, 'AREA_MANAGE_ID', 'RYXXID', 'forms[1]','AREA_MANAGE_ID,AREA_MANAGE_NAME', 'RYXXID,XM', 'selectArea')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content" colspan="3">
						<select id="STATE" name="STATE" style="width: 155"></select>
						 <input name="STYLE"  id="STYLE" json="STYLE" type="hidden" />
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
<input type="hidden"   name="module" id="module" value="${module}"/>
<input type="hidden" name="PVG_EDIT_AUDIT" id="PVG_EDIT_AUDIT" value="${pvg.PVG_EDIT_AUDIT}"/>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<script type="text/javascript">
    SelDictShow("STATE","33","${params.STATE}","");    
    SelDictShow("BUSS_SCOPE","BS_88","${params.BUSS_SCOPE}","");
	spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");    
</script>
</html>