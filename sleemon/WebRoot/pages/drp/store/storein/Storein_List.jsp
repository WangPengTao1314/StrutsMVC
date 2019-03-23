<!--
 * @prjName:喜临门营销平台
 * @fileName:Storein_List
 * @author glw
 * @time   2013-08-19 16:55:43 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/storein/Storein_List.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：库存管理&gt;&gt;入库管理&gt;&gt;入库单处理</td>
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
				   <c:if test="${pvg.PVG_DONE eq 1 }">
			   		<input id="done" type="button" class="btn" value="入库处理(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   <c:if test="${pvg.PVG_RETURN eq 1 }">
			   		<input id="return" type="button" class="btn" value="退回(R)" title="Alt+F" accesskey="F">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
			   		<input id="expdate" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
				   </c:if>
				    <c:if test="${pvg.PVG_BACKIN eq 1 }">
				    <input id="backin" type="button" class="btn" value="反入库(B)" title="Alt+B" accesskey="B">
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
                    <th  nowrap="nowrap" align="center" dbname="STOREIN_NO" >入库单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据号</th>
                    <th  nowrap="nowrap" align="center" dbname="SEND_CHANN_NAME" >发货方/退货门店名称</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方名称</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREIN_STORE_NAME" >入库库房名称</th>
                    <%--<th  nowrap="nowrap" align="center" dbname="STORAGE_FLAG" >库位管理标记</th>--%>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));getState('${rst.STATE}');">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STOREIN_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.STOREIN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.SEND_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STOREIN_STORE_NAME}&nbsp;</td><%--
                     <td nowrap="nowrap" align="center">
	                     <c:if test="${rst.STORAGE_FLAG eq 1 }">是&nbsp;</c:if>
	                     <c:if test="${rst.STORAGE_FLAG eq 0 }">否&nbsp;</c:if>
                     </td>
                     --%><td nowrap="nowrap" align="center">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td json="STATE" nowrap="nowrap" align="center">${rst.STATE}&nbsp;
                    	 <input id="storageFlag${rst.STOREIN_ID}" type="hidden"  value="${rst.STORAGE_FLAG}" />
                    	 <input id="BILL_TYPE${rst.STOREIN_ID}" type="hidden"  value="${rst.BILL_TYPE}" />
                    	 <input id="state${rst.STOREIN_ID}" type="hidden"  value="${rst.STATE}" />
                    	 
                     </td>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<script type="text/javascript">btnReset();btnDisable(["done"]);</script>
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
<form id="queryForm" method="post" action="#" name="queryForm">
 <input id="selectParams" name="selectParams" type="hidden" value=" STATE in( '启用','停用') "/>
 <input type="hidden" id="search" name="search" value='${search}'/>
 <input type="hidden" name="selectParam" id="selectParam" value=" STATE in( '启用','停用') and BEL_CHANN_ID = '${RECV_CHANN_NO}'">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
           		    <td width="8%" nowrap align="right" class="detail_label">入库单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREIN_NO" name="STOREIN_NO"  style="width:155" value="${params.STOREIN_NO}"/>
					</td>	
                    <td width="8%" nowrap align="right" class="detail_label">单据类型:</td>
					<td width="15%" class="detail_content">
						<select id="BILL_TYPE" name="BILL_TYPE"  style="width:155"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">来源单据号:</td>
					<td width="15%" class="detail_content">
	   					<input id="FROM_BILL_NO" name="FROM_BILL_NO"  style="width:155" value="${params.FROM_BILL_NO}"/>
					</td>					
<!--                    <td width="8%" nowrap align="right" class="detail_label">库位管理标记:</td>-->
<!--					<td width="15%" class="detail_content">-->
<!--	   					<input id="STORAGE_FLAG_BEG1" name="STORAGE_FLAG"  type="radio" style="width:30" value="1"  -->
<!--	   						<c:if test="${params.STORAGE_FLAG eq 1 or empty params.STORAGE_FLAG}"> checked="checked" </c:if> -->
<!--	   						-->
<!--	   					/>是-->
<!--	   					<input id="STORAGE_FLAG_BEG2" name="STORAGE_FLAG"  type="radio" style="width:30" value="0" <c:if test="${params.STORAGE_FLAG eq 0}"> checked="checked" </c:if>/>否-->
<!--					</td>-->
					<td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE"  style="width:155"></select>
					</td>	
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">发货方编号:</td>
					<td width="15%" class="detail_content">
						<input id="SEND_CHANN_ID" name="SEND_CHANN_ID" type="hidden" style="width:155" value="${params.SEND_CHANN_ID}"/>
	   					<input id="SEND_CHANN_NO" name="SEND_CHANN_NO"  style="width:155" value="${params.SEND_CHANN_NO}"/>
	   					<img align="absmiddle" name="selCHANN_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'SEND_CHANN_ID', 'CHANN_ID', 'queryForm','SEND_CHANN_ID,SEND_CHANN_NO,SEND_CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">发货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  style="width:155" value="${params.SEND_CHANN_NAME}"/>
					</td>					
                   	<td width="8%" nowrap align="right" class="detail_label">入库库房编号:</td>
					<td width="15%" class="detail_content">
						<input id="STOREIN_STORE_ID" name="STOREIN_STORE_ID"  type="hidden" value="${params.STOREIN_STORE_ID}"/>
	   					<input id="STOREIN_STORE_NO" name="STOREIN_STORE_NO"  style="width:155" value="${params.STOREIN_STORE_NO}"/>
	   					<img align="absmiddle" name="selDEF_STORE_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_35', false, 'STOREIN_STORE_ID', 'STORE_ID','queryForm',
												 'STOREIN_STORE_NO,STOREIN_STORE_NAME', 'STORE_NO,STORE_NAME','selectParam')">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">入库库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREIN_STORE_NAME" name="STOREIN_STORE_NAME"  style="width:155" value="${params.STOREIN_STORE_NAME}"/>
					</td>				
               </tr>
               <tr>
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
                    <td width="8%" nowrap align="right" class="detail_label">合同编号:</td>
					<td width="15%" class="detail_content">
						<input id="CONTRACT_NO" name="CONTRACT_NO"    value="${params.CONTRACT_NO}" />
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
</html>
<script type="text/javascript">
	SelDictShow("STATE","76","${params.selState}","");
	SelDictShow("BILL_TYPE","BS_25","${params.BILL_TYPE}","");
</script>