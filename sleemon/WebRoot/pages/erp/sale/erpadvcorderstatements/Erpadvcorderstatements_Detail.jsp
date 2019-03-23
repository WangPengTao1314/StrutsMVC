<!--
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10 
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
<body style="overflow: auto;" class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="" scrolling="AUTO">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
			        <td width="15%" align="right" class="detail_label">结算单编号:</td>
					<td width="35%" class="detail_content"> ${rst.ADVC_STATEMENTS_ORDER_NO}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">结算日期:</td>
					<td width="35%" class="detail_content"> ${rst.STATEMENTS_DATE}&nbsp; </td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">渠道编号:</td>
					<td width="35%" class="detail_content">  ${rst.CHANN_NO}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">渠道名称:</td>
					<td width="35%" class="detail_content"> ${rst.CHANN_NAME}&nbsp; </td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">活动编号:</td>
					<td width="35%" class="detail_content">  ${rst.MARKETING_ACT_NO}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">活动名称:</td>
					<td width="35%" class="detail_content"> ${rst.MARKETING_ACT_NAME}&nbsp; </td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">订金金额金额:</td>
					<td width="35%" class="detail_content"> ${rst.ADVCS_AMOUNT}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">应收总额:</td>
					<td width="35%" class="detail_content"> ${rst.STATEMENTS_AMOUNT}&nbsp;</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">银行扣点比例:</td>
					<td width="35%" class="detail_content"> ${rst.BANK_RATE}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">银行扣点金额:</td>
					<td width="35%" class="detail_content"> ${rst.BANK_AMOUNT}&nbsp;</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">导购员提成比例:</td>
					<td width="35%" class="detail_content"> ${rst.COMMISSION_PERCENTAGE}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">导购员提成金额:</td>
					<td width="35%" class="detail_content"> ${rst.COMMISSION_AMOUNT}&nbsp;</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">礼品费用:</td>
					<td width="35%" class="detail_content"> ${rst.GIFT_AMOUNT}&nbsp; </td>
			        <td width="15%" align="right" class="detail_label">其它费用:</td>
					<td width="35%" class="detail_content"> ${rst.OTHER_AMOUNT}&nbsp;</td>
               </tr>
               <tr>
					<td width="15%" align="right" class="detail_label">实际结算金额:</td>
					<td width="35%" class="detail_content"> ${rst.LAST_STATEMENTS_AMOUNT}&nbsp; </td>
					<td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">  ${rst.STATE}&nbsp; </td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">备注:</td>
					<td width="35%" class="detail_content" colspan="3"> ${rst.REMARK}&nbsp; </td>
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
                    <td width="15%" align="right" class="detail_label">审核人:</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">审核时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_TIME}&nbsp;
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
			       <td width="15%" align="right" class="detail_label"></td>
				 <td width="35%" class="detail_content"> </td>
               </tr> 
			</table>
		</td>
	</tr>
</table>
</body>
</html>