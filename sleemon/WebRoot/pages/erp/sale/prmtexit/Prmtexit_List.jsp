<!--
 * @prjName:喜临门营销平台
 * @fileName:Prmtexit_List
 * @author chenj
 * @time   2013-10-19 16:54:28 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale//prmtexit/Prmtexit_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：营销管理＞＞促销管理＞＞促销品发放</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="30">
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
				   <c:if test="${pvg.PVG_BWS eq 1 }">
				   <input id="commit" type="button" class="btn" value="提交(C)" title="Alt+D" accesskey="C">
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
                    <th  nowrap="nowrap" align="center" dbname="PRMT_GOODS_EXTD_NO" >促销品发放编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRMT_PLAN_NO" >促销方案编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRMT_PLAN_NAME" >促销方案名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NO" >渠道编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CHANN_NAME" >渠道名称</th>
                    <th  nowrap="nowrap" align="center" dbname="COUNT_DATE_BEG" >统计日期从</th>
                    <th  nowrap="nowrap" align="center" dbname="COUNT_DATE_END" >统计日期到</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_AMOUNT" >销售金额</th>
                    <th  nowrap="nowrap" align="center" dbname="EXTD_PSON_NAME" >发放人名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.PRMT_GOODS_EXTD_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.PRMT_GOODS_EXTD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PRMT_PLAN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PRMT_PLAN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.COUNT_DATE_BEG}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.COUNT_DATE_END}&nbsp;</td>  
                     <td nowrap="nowrap" align="right">${rst.SALE_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.EXTD_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.PRMT_GOODS_EXTD_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="11" align="center" class="lst_empty">
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
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">促销品发放编号:</td>
					<td width="15%" class="detail_content">
	   					<!-- <input id="PRMT_GOODS_EXTD_NO" name="PRMT_GOODS_EXTD_NO"  style="width:155" value="${params.PRMT_GOODS_EXTD_NO}"/> -->
	   					
	   					<input type="hidden" name="selectParams1"  value=" DEL_FLAG=0 " >
					    <input type="hidden"  id="PRMT_GOODS_EXTD_ID" name="PRMT_GOODS_EXTD_ID"  style="width:155" value="${params.PRMT_GOODS_EXTD_ID}"/>
	   					<input type="text" id="PRMT_GOODS_EXTD_NO" name="PRMT_GOODS_EXTD_NO"  style="width:155" value="${params.PRMT_GOODS_EXTD_NO}"/>
	   					 <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_63', false, 'PRMT_GOODS_EXTD_ID', 'PRMT_GOODS_EXTD_ID', 'forms[1]','PRMT_GOODS_EXTD_NO','PRMT_GOODS_EXTD_NO', 'selectParams1')">
	   					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">促销方案编号:</td>
	   				<td width="15%" class="detail_content">
					    <input type="hidden" name="selectParams3"  value=" DEL_FLAG=0 and state='已发布' " >
					    <input type="hidden"  id="PRMT_PLAN_ID" name="PRMT_PLAN_ID"  style="width:155" value="${params.PRMT_PLAN_ID}"/>
	   					<input type="text" id="PRMT_PLAN_NO" name="PRMT_PLAN_NO"  style="width:155" value="${params.PRMT_PLAN_NO}"/>
	   					 <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_47', false, 'PRMT_PLAN_ID', 'PRMT_PLAN_ID', 'forms[1]','PRMT_PLAN_NO,PRMT_PLAN_NAME','PRMT_PLAN_NO,PRMT_PLAN_NAME', 'selectParams3')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">促销方案名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="PRMT_PLAN_NAME" name="PRMT_PLAN_NAME"  style="width:155" value="${params.PRMT_PLAN_NAME}"/>
					</td>	
					 <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select name="STATE" id="STATE" style="width:155"></select>
					</td>					
                    
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">渠道编号:</td>
					<td width="15%" class="detail_content">
	   					<!-- <input id="CHANN_NO" name="CHANN_NO"  style="width:155" value="${params.CHANN_NO}"/>-->
	   					
	   					
	   					<input type="hidden" name="selectParams2"  value=" DEL_FLAG=0 and (state='启用' or state='停用')" >
					    <input type="hidden"  id="CHANN_ID" name="CHANN_ID"  style="width:155" value="${params.CHANN_ID}"/>
	   					<input type="text" id="CHANN_NO" name="CHANN_NO"  style="width:155" value="${params.CHANN_NO}"/>
	   					 <img align="absmiddle" name="selJGXX" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_NO','CHANN_NO', 'selectParams2')">
	   					
	   					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">渠道名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="CHANN_NAME" name="CHANN_NAME"  style="width:155" value="${params.CHANN_NAME}"/>
					</td>	
               		<td width="8%" nowrap align="right" class="detail_label">统计日期从:</td>
					<td width="15%" class="detail_content">
	   					<input id="COUNT_DATE_BEG" name="COUNT_DATE_BEG" readonly="readonly"  onclick="SelectDate();"  style="width:155" value="${params.COUNT_DATE_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(COUNT_DATE_BEG);"  >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">统计日期到:</td>
					<td width="15%" class="detail_content">
	   					<input id="COUNT_DATE_END" name="COUNT_DATE_END" readonly="readonly"  onclick="SelectDate();"   style="width:155" value="${params.COUNT_DATE_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(COUNT_DATE_END);" >
					</td>
                    
                   				
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
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
var flag = parent.document.getElementById("flag").value;
//SelDictShow("BILL_TYPE","BS_20","${params.BILL_TYPE}","");
SelDictShow("STATE", "193", "${params.STATE}", "");
</script>