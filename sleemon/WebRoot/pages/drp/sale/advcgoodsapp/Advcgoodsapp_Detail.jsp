<!--
 * @prjName:喜临门营销平台
 * @fileName:Advcgoodsapp_Detial
 * @author lyg
 * @time   2013-11-02 18:55:53 
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
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<title>信息详情</title>
</head>
<body class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class=detail_lst>
               <tr>
			        <td width="15%" align="right" class="detail_label">发货申请单编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADVC_SEND_REQ_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">发货方类型:</td>
					<td width="35%" class="detail_content">
                        ${rst.SEND_TYPE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">发货方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.SEND_CHANN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">发货方名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.SEND_CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">来源单据编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.FROM_BILL_NO}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">单据类型:</td>
					<td width="35%" class="detail_content">
                        ${rst.BILL_TYPE}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">出库库房编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.STOREOUT_STORE_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">出库库房名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.STOREOUT_STORE_NAME}&nbsp;
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
               		<%--<td width="15%" align="right" class="detail_label">库位管理标记:</td>
					<td width="35%" class="detail_content">
                        <c:if test="${rst.STORAGE_FLAG eq 1}">
                     		是
                     	</c:if>
                     	<c:if test="${rst.STORAGE_FLAG eq 0}">
                     		否
                     	</c:if>&nbsp;
					</td>
			        --%>
			        
			        <td width="15%" align="right" class="detail_label">合同编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.CONTRACT_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">销售日期:</td>
					<td width="35%" class="detail_content">
                        ${rst.SALE_DATE}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">业务员:</td>
					<td width="35%" class="detail_content">
                        ${rst.SALE_PSON_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">客户姓名:</td>
					<td width="35%" class="detail_content">
                        ${rst.CUST_NAME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">电话:</td>
					<td width="35%" class="detail_content">
                        ${rst.TEL}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">要求到货日期:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORDER_RECV_DATE}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">订金金额:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADVC_AMOUNT}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">已付款:</td>
					<td width="35%" class="detail_content">
                        ${rst.PAYED_TOTAL_AMOUNT}&nbsp;
					</td>
               </tr>
               <tr>
					<td width="15%" align="right" class="detail_label">应收总额:</td>
					<td width="35%" class="detail_content">
                        ${rst.PAYABLE_AMOUNT}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">出库总金额:</td>
					<td width="35%" class="detail_content">
                        ${rst.STOREOUT_AMOUNT}&nbsp;
					</td>
               </tr>
               
               <tr>
               		<td width="15%" align="right" class="detail_label">制单人:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">更新人:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">更新时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">制单部门:</td>
					<td width="35%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">制单机构:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORG_NAME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">帐套名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.LEDGER_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>
               <tr>
					<td width="15%" align="right" class="detail_label">收货地址：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.RECV_ADDR }&nbsp;</td>
               </tr>
               <tr>
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.REMARK }&nbsp;</td>
               </tr>
               <tr>
					<td width="15%" align="right" class="detail_label">退回原因：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.RETURN_RSON }&nbsp;</td>
               </tr>
               
			</table>
		</td>
	</tr>
</table>
</body>
</html>