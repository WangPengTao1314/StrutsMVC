<!--
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List
 * @author lyg
 * @time   2013-08-11 17:17:29 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/storeouta/Storeouta_List_AllPrint.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div>
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<!-- 0157437--start--刘曰刚--2014-06-24 -->
	<!-- 修改终端和人员通用选取过滤条件，只查询本帐套的终端和人员 -->
	<input type="hidden" name=selectParams value="STATE='启用' and DEL_FLAG=0 and CHANN_ID_P='${params.LEDGER_ID}'">
	<input type="hidden" name="selectParam" value="RYZT in ('启用','停用') and DELFLAG=0 and JGXXID in (select JGXXID from T_SYS_JGXX where ZTXXID ='${params.LEDGER_ID}' ) ">
	<input type="hidden" name=selectTERM value="STATE in ('启用','停用') and DEL_FLAG=0 and CHANN_ID_P='${params.LEDGER_ID}'">
	<input type="hidden" name="selectStore"  ID="selectStore" value="  STATE ='启用' and DEL_FLAG=0 and  (BEL_CHANN_ID='${params.LEDGER_ID}' or BEL_CHANN_ID in (${params.TERM_CHARGE}))">
	<input type="hidden" id="search" name="search" value='${search}'/>
	<!-- 0157437--end--刘曰刚--2014-06-24 -->
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">销售出库通知单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREOUT_NO" name="STOREOUT_NO"  style="width:155" value="${params.STOREOUT_NO}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">来源单据编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="FROM_BILL_NO" name="FROM_BILL_NO"  style="width:155" value="${params.FROM_BILL_NO}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">收货方编号:</td>
					<td width="15%" class="detail_content">
	   					<input json="RECV_CHANN_ID" name="RECV_CHANN_ID" autocheck="true" label="收货方ID" type="hidden" inputtype="string"   value="${params.RECV_CHANN_ID}"/>
                       <input json="RECV_CHANN_NO" name="RECV_CHANN_NO" id="RECV_CHANN_NO"   label="收货方编号"  type="text"  value="${params.RECV_CHANN_NO}" />
                       <img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"  
							onClick="selCommon('BS_27', false, 'RECV_CHANN_ID', 'TERM_ID', 'forms[1]','RECV_CHANN_ID,RECV_CHANN_NO,RECV_CHANN_NAME','TERM_ID,TERM_NO,TERM_NAME',  'selectTERM');"> 
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">收货方名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="RECV_CHANN_NAME" name="RECV_CHANN_NAME"   value="${params.RECV_CHANN_NAME}"/>
					</td>
               </tr>
               <tr>
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
                    <td width="8%" nowrap align="right" class="detail_label">制单时间从:</td>
					<td width="15%" class="detail_content">
	   					<input id="BEGIN_CRE_TIME" name="BEGIN_CRE_TIME" readonly="readonly" onclick="SelectTime();" style="width:155" value="${params.BEGIN_CRE_TIME}"    />
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(BEGIN_CRE_TIME);" >
					</td>	
					<td width="8%" nowrap align="right" class="detail_label">制单时间到:</td>
					<td width="15%" class="detail_content">
	   					<input id="END_CRE_TIME" name="END_CRE_TIME" readonly="readonly" style="width:155" onclick="SelectTime();" value="${params.END_CRE_TIME}"/>
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(END_CRE_TIME);" >
					</td>				
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">出库库房编号:</td>
					<td width="15%" class="detail_content">
						<input id="STOREOUT_STORE_ID" name="STOREOUT_STORE_ID" type="hidden" value="${params.STOREOUT_STORE_ID}"/>
	   					<input id="STOREOUT_STORE_NO" name="STOREOUT_STORE_NO"  style="width:155" value="${params.STOREOUT_STORE_NO}"/>
	   					<img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_35',false,'STOREOUT_STORE_ID','STORE_ID','forms[1]','STOREOUT_STORE_ID,STOREOUT_STORE_NO,STOREOUT_STORE_NAME','STORE_ID,STORE_NO,STORE_NAME','selectStore');"> 
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">出库库房名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME"  style="width:155" value="${params.STOREOUT_STORE_NAME}"/>
					</td>	
					<td width="8%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select id="STATE" name="STATE" style="width: 155"></select>
					</td>		
					<td width="8%" nowrap align="right" class="detail_label"></td>
					<td width="15%" class="detail_content">
					</td>			
               </tr>
               <tr>
					<td colspan="10" align="center" valign="middle" >
						<input onclick="queryForm()" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input  type="button" class="btn" value="重置(R)"  onclick="resetQueryInput();" title="Alt+R" accesskey="R">
						<input id="allPrint" type="button" class="btn" value="批量打印(S)" title="Alt+S" accesskey="P">
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" id="allChecked"></th>
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_NO" >销售出库通知单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="FROM_BILL_NO" >来源单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_STORE_NAME" >出库库房</th>
                    <th  nowrap="nowrap" align="center" dbname="RECV_CHANN_NAME" >收货方</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_DATE" >销售日期</th>
                    <th  nowrap="nowrap" align="center" dbname="STOREOUT_AMOUNT" >出库总金额</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="REMARK" >备注</th>-->
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);">
					  <td width="1%"align='center' >
						<input type="checkbox" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STOREOUT_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.STOREOUT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.FROM_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.STOREOUT_STORE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.RECV_CHANN_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SALE_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.STOREOUT_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
<!--                     <td nowrap="nowrap" align="left">${rst.REMARK}&nbsp;</td>-->
                    <td nowrap="nowrap" align="left">${rst.STATE}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="17" align="center" class="lst_empty">
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
</body>
<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
</html>
<script type="text/javascript">
SelDictShow("STATE","BS_166","${params.STATE}","");
</script>