<!--
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_Detial
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>信息详情</title>
</head>
<body style="overflow: auto;" class="bodycss1">
<table width="100%" scrolling="AUTO" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
			        <td width="15%" align="right" class="detail_label">收款单编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATEMENTS_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">收款类型:</td>
					<td width="35%" class="detail_content">${rst.BILL_TYPE}</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">预订单编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADVC_ORDER_NO}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">要求到货日期:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORDER_RECV_DATE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">终端编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.TERM_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">终端名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.TERM_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">销售日期:</td>
					<td width="35%" class="detail_content">
                        ${rst.SALE_DATE}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">业务员名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.SALE_PSON_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">客户名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.CUST_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">电话:</td>
					<td width="35%" class="detail_content">
                        ${rst.TEL}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">应收总额:</td>
					<td width="35%" class="detail_content">
                        ${rst.PAYABLE_AMOUNT}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">订金:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADVC_AMOUNT}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">已付款金额:</td>
					<td width="35%" class="detail_content">
                        ${rst.PAYED_TOTAL_AMOUNT}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">已扣款金额:</td>
					<td width="35%" class="detail_content">
                        ${rst.DEDUCTED_TOTAL_AMOUNT}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">本次收款金额:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATEMENTS_AMOUNT}&nbsp;
					</td>
<!--			        <td width="15%" align="right" class="detail_label">本次扣款金额:</td>-->
<!--					<td width="35%" class="detail_content">-->
<!--                        ${rst.DEDUCT_AMOUNT}&nbsp;-->
<!--					</td>-->
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">附件:</td>
					<td width="35%" class="detail_content">
                        <input type="hidden" id ="STATENEBTS_ATT" value='${rst.STATENEBTS_ATT}' />
					</td>
			        <td width="15%" align="right" class="detail_label">结算日期:</td>
					<td width="35%" class="detail_content">${rst.STATEMENT_DATE}</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">制单时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单人:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">更新时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">更新人:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">制单机构:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORG_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单部门:</td>
					<td width="35%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">账套:</td>
					<td width="35%" class="detail_content">
                        ${rst.LEDGER_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">备注:</td>
					<td width="85%" class="detail_content" colspan="3">
                        ${rst.REMARK}&nbsp;
					</td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	displayDownFile('STATENEBTS_ATT','SAMPLE_DIR',true,false);
</script>
</html>