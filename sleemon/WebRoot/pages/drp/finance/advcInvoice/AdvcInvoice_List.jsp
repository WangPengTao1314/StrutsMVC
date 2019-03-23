<!--
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
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
	<script type="text/javascript" src="${ctx}/pages/drp/finance/advcInvoice/AdvcInvoice_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		#commitDiv{
			margin: 0px auto; 
			width:600px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:160px;
			left:100px;
			top:50px;
			text-align:center;
			display: none;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：财务管理&gt;&gt;财务结算&gt;&gt;预订单开票维护</td>
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
				   <c:if test="${pvg.PVG_COMMIT_CANCLE eq 1 }">
					<input id="commit" type="button" class="btn" value="开票(C)" title="Alt+D" accesskey="C">
				   </c:if>
<!--				   <input id="print" type="button" class="btn" value="打印(P)" title="Alt+P" accesskey="P">-->
				   <c:if test="${pvg.PVG_BWS eq 1 or pvg.PVG_BWS_AUDIT eq 1 }">
<!--				   <input id="expertExcel" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">-->
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				   <div id="commitDiv">
				   	<table width="100%" height="100%">
				   		<tr>
				   			<td align="center" colspan="2">
				   				<span style="font-size: 20px;">开票信息：</span>
				   			</td>
				   		</tr>
				   		<tr>
				   			<td align="right" width="15%">发票号：</td>
				   			<td align="left" >
								<input type="text" id="RECEIPT_NO" inputtype="string"  maxlength="32" >  
							</td>
				   		</tr>
						<tr>
							<td align="right" width="15%">开票备注：</td>
							<td align="left" >
								<textarea  id="RECEIPT_REMARK"  cols="60" rows="5" inputtype="string"  maxlength="250"    ></textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="button" value="确定" onclick="commitInvoice()" class="btn" style="margin-right: 10px" />
								<input type="button" value="关闭" onclick="closeDiv()" class="btn" />
							</td>
						</tr>
					</table>
				   </div>
				</td>
				</tr>
			</table>
		</div>
		<div  style="height:0%;width:0%;display: none;">
			<iframe id='printIframe' name='_hiddenIframe' src='#' width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>
		</div>
	</td>
</tr>
<!--startprint1-->
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_ORDER_NO" >预订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NO" >终端编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >终端名称</th>
                    <th  nowrap="nowrap" align="center" dbname="CUST_NAME" >客户姓名</th>
                    <th  nowrap="nowrap" align="center" dbname="CONTRACT_NO" >合同编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_AMOUNT" >订金金额</th>
                    <th  nowrap="nowrap" align="center" dbname="PAYABLE_AMOUNT" >应收总额</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_PSON_NAME" >业务员</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_DATE" >销售日期</th>
                    <th  nowrap="nowrap" align="center" dbname="RECEIPT_NO" >发票号</th>
                    <th  nowrap="nowrap" align="center" dbname="XM" >开票人</th>
                    <th  nowrap="nowrap" align="center" dbname="RECEIPT_TIME" >开票时间</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_NAME" >制单人</th>
                    <th  nowrap="nowrap" align="center" dbname="CRE_TIME" >制单时间</th>
                    <th  nowrap="nowrap" align="center" dbname="DEPT_NAME" >制单部门</th>
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
<!--                    <th  nowrap="nowrap" align="center" dbname="PRINT_NUM" >打印次数</th>-->
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));">
					 <td width="1%"align='center' >
						<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.ADVC_ORDER_ID}"/>
					 </td>
                     <td nowrap="nowrap" align="center">${rst.ADVC_ORDER_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.TERM_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.TERM_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CUST_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CONTRACT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="right">${rst.ADVC_AMOUNT}&nbsp;</td>
                     <td nowrap="nowrap" align="right" id="PAYABLE_AMOUNT${rst.ADVC_ORDER_ID}">${rst.PAYABLE_AMOUNT}</td>
                     <td nowrap="nowrap" align="left">${rst.SALE_PSON_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.SALE_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECEIPT_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.XM}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.RECEIPT_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.CRE_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.CRE_TIME}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.DEPT_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                    <input id="state${rst.ADVC_ORDER_ID}" type="hidden"  value="${rst.STATE}" />
                    <input id="flag${rst.ADVC_ORDER_ID}" type="hidden"  value="${rst.NEED_RECEIPT_FLAG}" />
                    
				    </tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="18" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
<!--endprint1--> 
<tr>
	<td height="12px" align="center">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
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
	<input type="hidden" name=selectTERM value="STATE in ('启用','停用') and DEL_FLAG=0 and CHANN_ID_P='${ZTXXID}'">
	<input type="hidden" name="selectParam" value="RYZT in ('启用','停用') and DELFLAG=0 and JGXXID in (select JGXXID from T_SYS_JGXX where ZTXXID ='${ZTXXID}' ) ">
	<input type="hidden" name="selectMoteParams" value=" DEL_FLAG='0' and STATE='启用' and LEDGER_ID = '${ZTXXID}'">
	<input type="hidden" id="search" name="search" value='${search}'/>
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">预订单编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="ADVC_ORDER_NO" name="ADVC_ORDER_NO"  style="width:155" value="${params.ADVC_ORDER_NO}"/>
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">终端编号:</td>
					<td width="15%" class="detail_content">
	   					<input json="TERM_ID" name="TERM_ID" autocheck="true" label="终端信息ID" type="hidden" inputtype="string"   value="${params.TERM_ID}"/>
                       <input json="TERM_NO" name="TERM_NO" autocheck="true" label="终端编号"  type="text"  inputtype="string"   maxlength="30"  value="${params.TERM_NO}"/>
                       <img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[2]','TERM_ID,TERM_NO,TERM_NAME', 'TERM_ID,TERM_NO,TERM_NAME', 'selectTERM')"> 
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">终端名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_NAME" name="TERM_NAME"  style="width:155" value="${params.TERM_NAME}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">业务员:</td>
					<td width="15%" class="detail_content">
	   					<input id="SALE_PSON_NAME" name="SALE_PSON_NAME"  style="width:155" value="${params.SALE_PSON_NAME}"/>
	   					<input id="SALE_PSON_ID" name="SALE_PSON_ID"  style="width:155" value="${params.SALE_PSON_ID}" type="hidden"/>
	   					<img align="absmiddle" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('System_0', false, 'SALE_PSON_ID','RYXXID', 'forms[2]','SALE_PSON_ID,SALE_PSON_NAME','RYXXID,XM', 'selectParam')"
							>
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
               		<td width="8%" nowrap align="right" class="detail_label">客户姓名:</td>
					<td width="15%" class="detail_content">
	   					<input id="CUST_NAME" name="CUST_NAME"  style="width:155" value="${params.CUST_NAME}" />
					</td>
					<td width="8%" nowrap align="right" class="detail_label">电话:</td>
					<td width="15%" class="detail_content">
						<input id="TEL" name="TEL"  style="width:155" value="${params.TEL}" />
					</td>
                   				
                    <td width="8%" nowrap align="right" class="detail_label">合同编号:</td>
					<td width="15%" class="detail_content">
						<input id="CONTRACT_NO" name="CONTRACT_NO"  style="width:155" value="${params.CONTRACT_NO}" />
					</td>
					<td width="8%" nowrap align="right" class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						<select id="STATE" name="STATE" style="width: 155"></select>
					</td>
               </tr>
               <tr>
               		<td width="8%" nowrap align="right" class="detail_label">活动编号：</td>
					<td width="15%" class="detail_content">
						<input id="PROMOTE_ID" json="PROMOTE_ID" name="PROMOTE_ID" type="hidden" value="${params.PROMOTE_ID}">
                     <input json="PROMOTE_NO" name="PROMOTE_NO" autocheck="true" label="活动编号"  type="text"   inputtype="string"   value="${params.PROMOTE_NO}"/>
                     <img align="absmiddle" style="cursor： hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
                     	onClick="selCommon('BS_112', true, 'PROMOTE_ID', 'PROMOTE_ID', 'forms[2]','PROMOTE_ID,PROMOTE_NO,PROMOTE_NAME','PROMOTE_ID,PROMOTE_NO,PROMOTE_NAME','selectMoteParams')">
					</td>
					<td width="8%" nowrap align="right" class="detail_label">活动名称：</td>
					<td width="15%" class="detail_content">
						<input json="PROMOTE_NAME" name="PROMOTE_NAME"  label="活动名称" type="text"  value="${params.PROMOTE_NAME}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">开票状态：</td>
					<td width="15%" class="detail_content">
						<select id="NEED_RECEIPT_FLAG" name="NEED_RECEIPT_FLAG" style="width: 155"></select>
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

<jsp:include page="/pages/sys/spflow/publicFlow.jsp" />
<%@ include file="/pages/common/uploadfile/importfile.jsp"%>
</html>
<script type="text/javascript">
//初始化 审批流程
	   //uploadFile('up', 'SAMPLE_DIR');
       spflowInit("${pvg.AUD_BUSS_TYPE}", "${pvg.AUD_TAB_NAME}", "${pvg.AUD_TAB_KEY}", "", "${pvg.AUD_FLOW_INS}", "");
       SelDictShow("STATE","BS_9","${params.STATE}","");
       SelDictShow("NEED_RECEIPT_FLAG","BS_141","${params.NEED_RECEIPT_FLAG}","");    
</script>