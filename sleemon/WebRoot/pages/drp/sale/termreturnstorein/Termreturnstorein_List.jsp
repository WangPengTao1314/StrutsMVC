<!--
 * @prjName:喜临门营销平台
 * @fileName:Termreturnstorein_List
 * @author lyg
 * @time   2014-10-27 15:46:33 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale//termreturnstorein/Termreturnstorein_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;终端入库管理&gt;&gt;终端退货入库单</td>
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
				   <c:if test="${pvg.PVG_EDIT eq 1 }">
			   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				   </c:if>
				   <c:if test="${pvg.PVG_DELETE eq 1 }">
			   		<input id="delete" type="button" class="btn" value="删除(D)" title="Alt+D" accesskey="D">
				   </c:if>
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
<!--				   <input id="personal" type="button" class="btn" value="个性化设置" >-->
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
                    <th  nowrap="nowrap" align="center" dbname="STOREIN_NO" >入库单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >单据类型</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >终端名称</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="SALE_DATE" >销售日期</th>-->
                    <th  nowrap="nowrap" align="center" dbname="SALE_PSON_NAME" >业务员</th>
                    <th  nowrap="nowrap" align="center" dbname="CUST_NAME" >客户名称</th>
                    <th  nowrap="nowrap" align="center" dbname="TEL" >电话</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREIN_STORE_NAME" >入库库房</th>
                    <th  nowrap="nowrap" align="center" dbname="STATEMENUT_DATE" >结算日期</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="ORG_NAME" >制单机构</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STOREIN_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="left">${rst.STOREIN_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.BILL_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.TERM_NAME}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="center">${rst.SALE_DATE}&nbsp;</td>-->
                     <td nowrap="nowrap" align="left">${rst.SALE_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CUST_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.TEL}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STOREIN_STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATEMENUT_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.ORG_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.STOREIN_ID}" type="hidden"  value="${rst.STATE}" />
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="16" align="center" class="lst_empty">
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
<input type="hidden" name=selectTERM value="STATE in ('启用','停用') and DEL_FLAG=0 and CHANN_ID_P='${params.LEDGER_ID}'">
<input type="hidden" name="selectStore"  ID="selectStore" value="  STATE ='启用' and DEL_FLAG=0 and  (BEL_CHANN_ID='${params.LEDGER_ID}' or BEL_CHANN_ID in (${params.TERM_CHARGE}))">
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
	   					<select id="BILL_TYPE" name="BILL_TYPE" style="width: 155"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">来源单据编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="FROM_BILL_NO" name="FROM_BILL_NO"  style="width:155" value="${params.FROM_BILL_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">终端编号:</td>
					<td width="15%" class="detail_content">
	   					<input json="TERM_ID" name="TERM_ID" autocheck="true" label="终端ID" type="hidden" inputtype="string"   value="${params.TERM_ID}"/>
                       <input json="TERM_NO" name="TERM_NO" id="TERM_NO"   label="终端编号"  type="text"  value="${params.TERM_NO}" />
                       <img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"  
							onClick="selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[1]','TERM_ID,TERM_NO,TERM_NAME','TERM_ID,TERM_NO,TERM_NAME',  'selectTERM');">
					</td>					
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">终端名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_NAME" name="TERM_NAME"  style="width:155" value="${params.TERM_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">销售日期从:</td>
					<td width="15%" class="detail_content">
	   					<input id="SALE_DATE_BEG" name="SALE_DATE_BEG" readonly="readonly"  onclick="SelectDate();"  style="width:155" value="${params.SALE_DATE_BEG }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_BEG);"  >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">销售日期到:</td>
					<td width="15%" class="detail_content">
	   					<input id="SALE_DATE_END" name="SALE_DATE_END" readonly="readonly"  onclick="SelectDate();"   style="width:155" value="${params.SALE_DATE_END }">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SALE_DATE_END);" >
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">业务员:</td>
					<td width="15%" class="detail_content">
	   					<input id="SALE_PSON_NAME" name="SALE_PSON_NAME"  style="width:155" value="${params.SALE_PSON_NAME}"/>
					</td>					
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">客户名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="CUST_NAME" name="CUST_NAME"  style="width:155" value="${params.CUST_NAME}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">电话:</td>
					<td width="15%" class="detail_content">
	   					<input id="TEL" name="TEL"  style="width:155" value="${params.TEL}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">入库库房编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREIN_STORE_ID" name="STOREIN_STORE_ID" type="hidden" value="${params.STOREIN_STORE_ID}"/>
	   					<input id="STOREIN_STORE_NO" name="STOREIN_STORE_NO"  style="width:155" value="${params.STOREIN_STORE_NO}"/>
	   					<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_35',false,'STOREIN_STORE_ID','STORE_ID','forms[1]','STOREIN_STORE_ID,STOREIN_STORE_NO,STOREIN_STORE_NAME','STORE_ID,STORE_NO,STORE_NAME','selectStore');">
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">入库库房:</td>
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
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select id="STATE" name="STATE" style="width: 155"></select>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle" >
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+C" accesskey="X">&nbsp;&nbsp;
						<input  type="button" class="btn" value="重置(R)"  onclick="resetQueryInput();" title="Alt+R" accesskey="R">
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
SelDictShow("BILL_TYPE","BS_165","${params.BILL_TYPE}","");
SelDictShow("STATE","BS_166","${params.STATE}","");
</script>