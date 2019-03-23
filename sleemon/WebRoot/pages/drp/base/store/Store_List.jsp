<!--
 * @prjName:喜临门营销平台
 * @fileName:Store_List
 * @author wdb
 * @time   2013-08-13 14:01:22 
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
	<script type="text/javascript" src="${ctx}/pages/drp/base/store/Store_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理>>基础信息>>库房库位信息</td>
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
				   <c:if test="${pvg.PVG_START_STOP eq 1 }">
			   		<input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">	
			   		<input id="stop" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">
			       </c:if>
			       <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
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
                    <th  nowrap="nowrap" align="center" dbname="STORE_NO" >库房编号</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_NAME" >库房名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STORE_TYPE" >库房类别</th>
                    <th  nowrap="nowrap" align="center" >上级库房</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_STROE_FLAG" >是否终端库房</th>
                    <th  nowrap="nowrap" align="center" dbname="BEL_CHANN_NAME" >所属单位名称</th>
                    <!--  th  nowrap="nowrap" align="center" dbname="STORAGE_FLAG" >库位管理标记</th-->
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));getStorageFlag('${rst.STORAGE_FLAG}');">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STORE_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="left">${rst.STORE_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STORE_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PAR_STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">
	                     <c:if test="${rst.TERM_STROE_FLAG eq 0}">
	                     	否&nbsp;
	                     </c:if>
	                     <c:if test="${rst.TERM_STROE_FLAG eq 1}" >
	                     	是&nbsp;
	                     </c:if>
	                     <input value="${rst.TERM_STROE_FLAG}" type="hidden" id="STROE_FLAG${rst.STORE_ID}">
                     </td>
                     <td nowrap="nowrap" align="left">${rst.BEL_CHANN_NAME}&nbsp;</td>
                     <!--  
                     <c:if test="${rst.STORAGE_FLAG eq 0}">
                     	<td nowrap="nowrap" align="center">否&nbsp;</td>
                     </c:if>
                     <c:if test="${rst.STORAGE_FLAG eq 1}" >
                     	<td nowrap="nowrap" align="center" >是&nbsp;</td>
                     </c:if>
                     -->
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.STORE_ID}" type="hidden"  value="${rst.STATE}" />
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
		<input type="hidden" id="tableFlag" name="tableFlag" value="${selRowId}">
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
 <input type="hidden" name=selTerm value=" STATE in ('启用','停用') and DEL_FLAG='0' and CHANN_ID_P='${params.LEDGER_ID}' ">
 <input type="hidden" name=selChann value=" STATE in ('启用','停用') and DEL_FLAG='0' and CHANN_ID='${params.LEDGER_ID}' ">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">库房编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="STORE_NO" name="STORE_NO"  style="width:155" value="${params.STORE_NO}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="STORE_NAME" name="STORE_NAME"  style="width:155" value="${params.STORE_NAME}"/>
					</td>	
                    <td width="8%" nowrap align="right" class="detail_label">库房类别:</td>
					<td width="15%" class="detail_content">
                         <select json="STORE_TYPE" id="STORE_TYPE"  name="STORE_TYPE" style="width:155px"></select>
					</td>
                   	<td width="8%" nowrap align="right" class="detail_label">是否终端库房:</td>			
                    <td width="15%" class="detail_content">	
                   		<input type="radio" name="TERM_STROE_FLAG" id="storeFlagYes" value="1" 
                   			<c:if test="${params.TERM_STROE_FLAG eq 1|| empty params.TERM_STROE_FLAG}">checked="checked"</c:if> 
                   		>是&nbsp;&nbsp;
                   		<input type="radio" name="TERM_STROE_FLAG" id="storeFlagNo" value="0"
                   			<c:if test="${params.TERM_STROE_FLAG eq 0}">checked="checked"</c:if>
                   		>否&nbsp;
                    </td>					
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">所属单位编号:</td>
               		<td width="15%" class="detail_content">
               			<input type="hidden" name="BEL_CHANN_ID" id="BEL_CHANN_ID">
               			<input type="text" name="BEL_CHANN_NO" id="BEL_CHANN_NO" value="${params.BEL_CHANN_NO}">
               			<img align="absmiddle" name="selAREA" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="channCheck()">
               		</td> 
               		<td width="8%" nowrap align="right" class="detail_label">所属单位名称:</td>
               		<td width="15%" class="detail_content">
               			<input type="text" name="BEL_CHANN_NAME" id="BEL_CHANN_NAME" value="${params.BEL_CHANN_NAME}">
               		</td>
               		<td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_FROM" name="CRE_TIME_FROM"  style="width:155" value="${params.CRE_TIME_FROM}" onclick="SelectTime();"/>
	   					&nbsp;
						<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_FROM);" />
					</td>
					
               		<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_TO" name="CRE_TIME_TO"  style="width:155" value="${params.CRE_TIME_TO}" onclick="SelectTime();"/>
	   					&nbsp;
						<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(CRE_TIME_TO);" />
					</td>
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
						<select json="STATE" id="STATE"  name="STATE" style="width:155px"></select>
					</td>	
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
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
						<input type="reset" class="btn" value="重置 (R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
<script type="text/javascript">
	   SelDictShow("STORE_TYPE", "BS_39", "${params.STORE_TYPE}", "");
	   SelDictShow("STATE", "32", "${params.STATE}", "");
</script>