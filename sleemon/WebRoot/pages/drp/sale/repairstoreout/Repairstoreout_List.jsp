<!--
 * @prjName:喜临门营销平台
 * @fileName:返修出库
 * @author chenj
 * @time   2013-11-03 16:25:12 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/repairstoreout/Repairstoreout_List.js"></script>
			<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;返修管理&gt;&gt;返修出库</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" >
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
				   
				   <c:if test="${pvg.PVG_COMMIT eq 1 }">
                    <input id="commitRK" type="button" class="btn" value="提交库房出库(C)" title="Alt+C" accesskey="C">
                   </c:if>
                   <c:if test="${pvg.PVG_CLOSE eq 1 }">
                    <input id="auditclose" type="button" class="btn" value="关闭(G)" title="Alt+G" accesskey="G">
				    <!-- <input id="audit" type="button" class="btn" value="审核(A)" title="Alt+A" accesskey="A"> -->
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
		<div class="lst_area">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="ERP_REPAIR_ORDER_NO" >返修单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="REPAIR_CHANN_NO" >返修方编号</th>
                    <th  nowrap="nowrap" align="center" dbname="REPAIR_CHANN_NAME" >返修方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="DELIVER_DTL_ADDR" >返修方收货地址</th>
                    <th  nowrap="nowrap" align="center" dbname="AREA_NAME" >区域名称</th>
                    <th  nowrap="nowrap" align="center" dbname="REQ_FINISH_DATE" >预计返修日期</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_STORE_NAME" >出库库房名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.ERP_REPAIR_ORDER_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.ERP_REPAIR_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.REPAIR_CHANN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.REPAIR_CHANN_NAME}&nbsp;</td>
                      <td nowrap="nowrap" align="left">${rst.DELIVER_DTL_ADDR}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.AREA_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.REQ_FINISH_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STOREOUT_STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.ERP_REPAIR_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
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
                    <td width="8%" nowrap align="right" class="detail_label">返修单编号:</td>
					<td width="15%" class="detail_content">
	   					<input type="hidden" name="selectorder"  value=" DEL_FLAG =0 " >
	   					<input json="ERP_REPAIR_ORDER_ID" type="hidden"  id="ERP_REPAIR_ORDER_ID"  name="ERP_REPAIR_ORDER_ID"  style="width:155" value="${params.ERP_REPAIR_ORDER_ID}"/>
	   					<input json="ERP_REPAIR_ORDER_NO" type="text" id="ERP_REPAIR_ORDER_NO" label="返修单编号" name="ERP_REPAIR_ORDER_NO" 
	   						style="width:155"   mustinput="true" inputtype="string" value="${params.ERP_REPAIR_ORDER_NO}"/>
	   					
	   					 <img align="absmiddle" name="selJGXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"   
							onClick="selCommon('BS_69', false, 'ERP_REPAIR_ORDER_ID', 'ERP_REPAIR_ORDER_ID', 'forms[1]','ERP_REPAIR_ORDER_NO','ERP_REPAIR_ORDER_NO', 'selectorder');"  >
							
	   					
					</td>	
									
                    <td width="8%" nowrap align="right" class="detail_label">返修方编号:</td>
					
					<td width="15%" class="detail_content">
					   	 
					    <input json="REPAIR_CHANN_ID" type="hidden"  id="REPAIR_CHANN_ID"  name="REPAIR_CHANN_ID"  style="width:155" value="${params.REPAIR_CHANN_ID}"/>
	   					<input json="REPAIR_CHANN_NO" type="text" id="REPAIR_CHANN_NO" label="返修方编号" name="REPAIR_CHANN_NO" style="width:155"   mustinput="true" inputtype="string" value="${params.REPAIR_CHANN_NO}"/>
	   					
	   					 <img align="absmiddle" name="selJGXX" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"   
							onClick="selCommon('BS_19', false, 'REPAIR_CHANN_ID', 'CHANN_ID', 'forms[1]','REPAIR_CHANN_NO,REPAIR_CHANN_NAME','CHANN_NO,CHANN_NAME', '');"  >
							
							
				   </td>				
                    <td width="8%" nowrap align="right" class="detail_label">返修方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="REPAIR_CHANN_NAME" name="REPAIR_CHANN_NAME"  style="width:155" value="${params.REPAIR_CHANN_NAME}"/>
					</td>	
					 <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE" style="width:155" value=""/></select>
					</td>					
                    
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">预计返修日期从:</td>
					<td width="15%" class="detail_content">
	   					<input id="FINISH_DATE_BEG" name="FINISH_DATE_BEG" readonly="readonly"   onclick="SelectDate();" style="width:155" value="">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(FINISH_DATE_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">预计返修日期到:</td>
					<td width="15%" class="detail_content">
	   					<input id="FINISH_DATE_END" name="FINISH_DATE_END" readonly="readonly"   onclick="SelectDate();"  style="width:155" value="">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(FINISH_DATE_END);">
					</td>
                   	<td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_BEG" name="CRE_TIME_BEG" readonly="readonly"   onclick="SelectTime();" style="width:155" value="${params.CRE_TIME_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_BEG);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="CRE_TIME_END" name="CRE_TIME_END" readonly="readonly"   onclick="SelectTime();"  style="width:155" value="${params.CRE_TIME_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectTime(CRE_TIME_END);">
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
</html>
<script type="text/javascript">
//SelDictShow("STATE", "BS_13", "${params.STATE}", "");
SelDictShow("STATE","BS_66","${params.STATE}","");

</script>