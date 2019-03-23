<!--
 * @prjName:喜临门营销平台
 * @fileName:预订单核销
 * @author zzb
 * @time   2013-10-20 18:57:47 
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
	<script type="text/javascript" src="${ctx}/pages/drp/finance/advcverify/Advcverify_List.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style type="text/css">
	 .user_reg_line span{*vertical-align:50%;}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：财务管理>>财务结算>>预订单核销</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
                  <td nowrap>
			 
				   <c:if test="${pvg.PVG_BWS eq 1 }">
			   		<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
				   </c:if>
				  <input id="checkPayMore" type="button" class="btn" value="核销(C)" title="Alt+C" accesskey="C">
				  
				</td>
				<td>
				  <div class="user_reg_line"> 
				  <span style="color:red; ">总金额：</span>
				  <span> <input type="text" name="totalAmount" size="10" id="totalAmount" readonly="readonly"  class="readonly"/></span>&nbsp;&nbsp;
				  <span style="color:red; ">已核销总金额：</span>
				  <span> <input type="text" name="totalAmount" id="totalVerifyAmount" size="10" readonly="readonly"  class="readonly"/></span> &nbsp;&nbsp;
				  <span style="color:red; ">未生效总金额：</span>
				  <span> <input type="text" name="totalAmount" id="totalNoVerifyAmount" size="10" readonly="readonly"  class="readonly"/></span>  &nbsp;&nbsp;
				  </div>
				</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
		<form id="form0" name="form0" method="post">
		    <input type="hidden" id="slectParams" name="slectParams" >
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_ORDER_NO" >预订单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="STATEMENTS_NO" >收款单编号</th>
                    <th  nowrap="nowrap" align="center" dbname="CONTRACT_NO" >合同编号</th>
                    <th  nowrap="nowrap" align="center" dbname="ADVC_BILL_TYPE" >预订单类型</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NO" >终端编号</th>
                    <th  nowrap="nowrap" align="center" dbname="TERM_NAME" >终端名称</th>
                    <th  nowrap="nowrap" align="center" dbname="SALE_DATE" >销售日期</th>
                    <th  nowrap="nowrap" align="center" dbname="CUST_NAME" >客户名称</th>
                    <th  nowrap="nowrap" align="center" dbname="TEL" >联系方式</th>
                    <th  nowrap="nowrap" align="center" dbname="BILL_TYPE" >付款单类型</th>
                    <th  nowrap="nowrap" align="center" dbname="PAY_TYPE" >付款方式</th>
                    <th  nowrap="nowrap" align="center" dbname="PAY_AMONT" >收款金额</th>
                    <th  nowrap="nowrap" align="center" dbname="PAY_BILL_NO" >付款单据编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PAY_INFO" >付款信息</th>
                    <th  nowrap="nowrap" align="center" dbname="" >已核核销金额</th>
                    <th  nowrap="nowrap" align="center" dbname="" >本次核销金额</th>
                    <%--
                    <th  nowrap="nowrap" align="center" dbname="STATE" >状态</th>
                    --%>
                    <th  nowrap="nowrap" align="center"   >操作</th>
                   
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);">
					 <td width="1%"align='center' >
						<input type="checkbox" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.STATEMENTS_PAYMENT_DTL_ID}" onclick=""/>
                     </td>
                     <td nowrap="nowrap" align="center">
                      <span  style="text-decoration:underline;color: #0066CC"  url="advcorder.shtml?action=toFrame&module=wh&paramUrl=andflagADVC_ORDER_NO=${rst.ADVC_ORDER_NO}andflagsearch=true&isNoEdit=true" label="预订单查看">${rst.ADVC_ORDER_NO}</span>
                     </td>
                     <td nowrap="nowrap" align="center">
                      <span  style="text-decoration:underline;color: #0066CC" url="advpayment.shtml?action=toFrame&paramUrl=andflagSTATEMENTS_NO=${rst.STATEMENTS_NO}andflagsearch=true&isNoEdit=true" label="收款单查看">${rst.STATEMENTS_NO}&nbsp;</span>
                     </td>
                     <td nowrap="nowrap" align="center">${rst.CONTRACT_NO}</td>
                     <td nowrap="nowrap" align="center">&nbsp;&nbsp;${rst.ADVC_BILL_TYPE}</td>
                     <td nowrap="nowrap" align="center">${rst.TERM_NO}</td>
                     <td nowrap="nowrap" align="left">${rst.TERM_NAME}</td>
                     <td nowrap="nowrap" align="center">${rst.SALE_DATE}</td>
                     <td nowrap="nowrap" align="center">${rst.CUST_NAME}</td>
                     <td nowrap="nowrap" align="center">${rst.TEL}</td>
                     <td nowrap="nowrap" align="center">${rst.BILL_TYPE}</td>
                     <td nowrap="nowrap" align="center">${rst.PAY_TYPE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" name="PAY_AMONT" >${rst.PAY_AMONT}&nbsp;</td>
                     <td nowrap="nowrap" align="center">${rst.PAY_BILL_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left">${rst.PAY_INFO}&nbsp;</td>
                     <td nowrap="nowrap" align="right" name="WRITE_OFF_AMONT">${rst.WRITE_OFF_AMONT}</td>
                     <td nowrap="nowrap" align="left">
                      <input type="hidden" name="PAY_TYPE" json="PAY_TYPE" value="${rst.PAY_TYPE}"/>
                      <input type="hidden" name="PAY_BILL_NO" json="PAY_BILL_NO" value="${rst.PAY_BILL_NO}"/>
                       <input type="text" id="WRITE_OFF_AMONT" name="WRITE_OFF_AMONT" json="WRITE_OFF_AMONT" label="本次核销金额" style="text-align: right;"
                       size="11" autocheck="true" textType="float" valueType="12,2" maxlength="14" value=""
                       <c:if test="${rst.PAY_AMONT eq rst.WRITE_OFF_AMONT}">disabled="disabled"</c:if>
                        onkeyup="selectParent(this);"/> <span class="validate">*</span>
                     </td>
                     
                     <%--
                     <td nowrap="nowrap" align="center">${rst.STATE}&nbsp;</td>
                     --%>
                     
                     <td nowrap="nowrap" align="center">
                     <input type="hidden" name="STATEMENTS_ID" id="STATEMENTS_ID${rr}" value="${rst.STATEMENTS_ID}"  />
                     <input type="hidden" name="STATEMENTS_PAYMENT_DTL_ID" json="STATEMENTS_PAYMENT_DTL_ID" value="${rst.STATEMENTS_PAYMENT_DTL_ID}"/>
                     
                     <input name="checkPayment" id="checkPayment${rr}" type="button" class="btn" value="核销" 
                     onclick="selectParent(this);" <c:if test="${rst.PAY_AMONT eq rst.WRITE_OFF_AMONT}">disabled="disabled"</c:if>  >
                     
                     <input type="hidden" id="WRITE_OFF_DTL_ID${rr}" name="WRITE_OFF_DTL_ID" json="WRITE_OFF_DTL_ID" value=""/>
                     <input type="hidden" id="WRITE_OFF_PSON_TIME${rr}" name="WRITE_OFF_PSON_TIME" json="WRITE_OFF_PSON_TIME" value=""/>
                     <input name="" id="unCheckPayment${rr}" 
	                     type="button" class="btn" 
	                     <c:if test="${empty rst.WRITE_OFF_AMONT or rst.WRITE_OFF_AMONT eq 0 }"> disabled="disabled"</c:if> 
	                     value="反核销" onclick="beforeUnCheckPaymentFn('${rr}');selectParent(this);">
                     </td>
                    <input id="state${rst.STATEMENTS_ID}" type="hidden"  value="${rst.STATE}" />
                    <input id="WRITE_OFF_FLAG_${rst.STATEMENTS_PAYMENT_DTL_ID}" type="hidden"  value="${rst.WRITE_OFF_FLAG}" />
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
			</form>
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
<input type="hidden" id="selParam" name="selParam" value=" STATE in('启用','停用')"/>
<input type="hidden" id="selTermParam" name="selTermParam" value=" STATE in('启用','停用') and CHANN_ID_P='${LEDGER_ID}' "/>
<input type="hidden" id="selAdvcParam" name="selAdvcParam" value="  LEDGER_ID='${LEDGER_ID}' and DEL_FLAG=0  and   STATE in ('提交','待发货','已发货')  "/>
<input type="hidden" id="LEDGER_ID" name="LEDGER_ID" value="${LEDGER_ID}"/>
<input type="hidden" id="selDateConfParam" name="selDateConfParam" value=" LEDGER_ID='${LEDGER_ID}' and IS_DEAL_FLAG=1 and DEL_FLAG=0 "/>


<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
			       <%--<td width="8%" nowrap align="right" class="detail_label">渠道编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="CHANN_NO" name="CHANN_NO"  style="width:155" value="${params.ORDER_CHANN_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[1]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">渠道名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="CHANN_ID" id="CHANN_ID" value="" />
	   				   <input type="text" id="CHANN_NAME" name="CHANN_NAME"  style="width:155" value="${params.ORDER_CHANN_NAME}"/>
					</td>
						
					--%>
					 <td width="8%" nowrap align="right" class="detail_label">预订单编号:</td>
					<td width="15%" class="detail_content">
					    <input type="hidden" name="ADVC_ORDER_ID" id="ADVC_ORDER_ID" value="" />
	   					<input id="ADVC_ORDER_NO" name="ADVC_ORDER_NO"  style="width:155" value="${params.ADVC_ORDER_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_38', false, 'ADVC_ORDER_ID', 'ADVC_ORDER_ID', 'forms[2]','ADVC_ORDER_NO', 'ADVC_ORDER_NO', 'selAdvcParam')">
					</td>	
					<td width="8%" nowrap align="right" class="detail_label">预订单类型:</td>
					<td width="15%" class="detail_content">
	   				   <select id="ADVC_BILL_TYPE" name="ADVC_BILL_TYPE"  style="width:155" value="${params.ADVC_BILL_TYPE}"/></select>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">销售日期从:</td>
					<td width="15%" class="detail_content">
	   					<input id="SALE_DATE_GEGIN" name="SALE_DATE_GEGIN" readonly="readonly"   onclick="SelectDate();" style="width:155" value="${params.SALE_DATE_GEGIN}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(SALE_DATE_GEGIN);" >
					</td>
					<td width="8%" nowrap align="right" class="detail_label">销售日期到:</td>
					<td width="15%" class="detail_content">
	   					<input id="SALE_DATE_END" name="SALE_DATE_END" readonly="readonly"   onclick="SelectDate();"  style="width:155" value="${params.SALE_DATE_END}">
	   					<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(SALE_DATE_END);">
					</td>
			   </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">付款方式:</td>
					<td width="15%" class="detail_content">
	   					<%--<select json="PAY_TYPE" id="PAY_TYPE"  name="PAY_TYPE" style="width:155px" ></select>
	   					--%>
	   					<input type="hidden" id="DATA_NAME" name="DATA_NAME"    value="${params.DATA_NAME}"/>
					 	<input type="text" id="PAY_TYPE" name="PAY_TYPE"    value="${params.PAY_TYPE}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_123', true, 'DATA_NAME', 'DATA_NAME', 'forms[2]','PAY_TYPE', 'DATA_VAL', 'selDateConfParam')">
					</td>	
                    <td width="8%" nowrap align="right" class="detail_label">付款单据编号:</td>
					<td width="15%" class="detail_content">
	   					 <input id="PAY_BILL_NO" json="PAY_BILL_NO" name="PAY_BILL_NO"  value="${params.PAY_BILL_NO}"/>
	   					 <%--<img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_64', false, 'PAYMENT_ID', 'PAYMENT_ID', 'forms[1]','PAY_BILL_NO', 'PAYMENT_NO', '')">--%>
					
					</td>					
                   	<td width="8%" nowrap align="right" class="detail_label">付款类型:</td>
					<td width="15%" class="detail_content">
					 <select id="BILL_TYPE" name="BILL_TYPE"  style="width:155" value="${params.BILL_TYPE}"/></select>
					</td>			
                    <td width="8%" nowrap align="right" class="detail_label">状态:</td>
					<td width="15%" class="detail_content">
	   					<select json="STATE" id="STATE"  name="STATE" style="width:155px" ></select>
					</td>					
               </tr>
               <tr>
			        <td width="8%" nowrap align="right" class="detail_label">终端编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="TERM_NO" name="TERM_NO"  style="width:155" value="${params.TERM_NO}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[2]','TERM_NO,TERM_NAME', 'TERM_NO,TERM_NAME', 'selTermParam')">
					
					</td>					
                    <td width="8%" nowrap align="right" class="detail_label">终端名称:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" name="TERM_ID" id="TERM_ID" value="" />
	   				   <input type="text" id="TERM_NAME" name="TERM_NAME"  style="width:155" value="${params.TERM_NAME}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">客户名称:</td>
					<td width="15%" class="detail_content">
					   <input type="text" name="CUST_NAME" id="CUST_NAME" value="${params.CUST_NAME}" />
					</td>
					<td width="8%" nowrap align="right" class="detail_label">联系方式:</td>
					<td width="15%" class="detail_content">
					   <input type="text" name="TEL" id="TEL" value="${params.TEL}" />
					</td>
					
			   </tr>
			   
			     <tr>
			        <td width="8%" nowrap align="right" class="detail_label">合同编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="CONTRACT_NO" name="CONTRACT_NO"  style="width:155" value="${params.CONTRACT_NO}"/>
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
					    <input id="q_rest" type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">&nbsp;&nbsp;
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
	SelDictShow("STATE","BS_46","${params.STATE}","");
	var LEDGER_ID = $("#LEDGER_ID").val();
	//SelDictShow("PAY_TYPE","BS_102","${params.PAY_TYPE}","  DEL_FLAG = 0 and LEDGER_ID='"+LEDGER_ID+"' and IS_DEAL_FLAG=1 ");
	SelDictShow("ADVC_BILL_TYPE","BS_74","${params.ADVC_BILL_TYPE}","");
	SelDictShow("BILL_TYPE","BS_75","${params.BILL_TYPE}","");
	
</script>
</html>
