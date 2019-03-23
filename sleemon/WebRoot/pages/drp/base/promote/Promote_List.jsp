<!--
 * @prjName:喜临门营销平台
 * @fileName:活动
 * @author zzb
 * @time    
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
	<script type="text/javascript" src="${ctx}/pages/drp/base/promote/Promote_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理>>活动管理>>活动信息</td>
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
				 <!--    <input id="personal" type="button" class="btn" value="个性化设置" >-->
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
                    <th  nowrap="nowrap" align="center" dbname="PROMOTE_NO" >渠道活动编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PROMOTE_NAME" >渠道活动名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PROMOTE_DESC" >活动描述</th>
                    <th  nowrap="nowrap" align="center" dbname="BEG_DATE" >活动开始日期</th>
                    <th  nowrap="nowrap" align="center" dbname="END_DATE" >活动结束日期</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="valign:middle" name="eid" id="eid${rr}" value="${rst.PROMOTE_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center" >${rst.PROMOTE_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PROMOTE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PROMOTE_DESC}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.BEG_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.END_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.STATE}&nbsp;</td>
                    <input id="state${rst.PROMOTE_ID}" type="hidden"  value="${rst.STATE}" />
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
<form id="queryForm" method="post" action="#">
<input type="hidden" name="selectParams" id="selectParams" value=" STATE in('启用','停用') and DEL_FLAG=0 "/>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">渠道活动编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" id="PROMOTE_ID" name="PROMOTE_ID" />
	   					<input id="PROMOTE_NO" name="PROMOTE_NO"   value="${params.PROMOTE_NO}"/>
	   					<img align="absmiddle" name="selStore" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_112', false, 'PROMOTE_ID', 'PROMOTE_ID','forms[1]','PROMOTE_NO,PROMOTE_NAME', 'PROMOTE_NO,PROMOTE_NAME', 'selectParams')">
				
					</td>	
                    <td width="8%" nowrap align="right" class="detail_label">渠道活动名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="PROMOTE_NAME" name="PROMOTE_NAME"   value="${params.PROMOTE_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">活动开始日期:</td>
					<td width="15%" class="detail_content">
						<input id="BEG_DATE" json="BEG_DATE"  name="BEG_DATE"  label="活动开始日期"  inputtype="string"      onclick="SelectDate();"   value="${params.BEG_DATE}">
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(BEG_DATE);"  >
					</td>	
					
                    <td width="8%" nowrap align="right" class="detail_label">活动结束日期:</td>
					<td width="15%" class="detail_content">
					  <input id="END_DATE" json="END_DATE"  name="END_DATE"  label="活动开始日期"  inputtype="string"      onclick="SelectDate();"   value="${params.END_DATE}">
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(END_DATE);"  >
					</td>					
               </tr>
                     
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE"  style="width:155" ></select>
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
<script type="text/javascript">
  SelDictShow("STATE", "32", "${params.STATE}", "");
</script>
</html>
