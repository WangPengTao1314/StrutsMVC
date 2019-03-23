<!--
 * @prjName:喜临门营销平台
 * @fileName:Dstrorder_Detial
 * @author glw
 * @time   2013-08-16 10:31:37 
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
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
			        <td width="15%" align="right" class="detail_label">分发指令接收编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.DSTR_ORDER_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">来源单据编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.FROM_BILL_NO}&nbsp;
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
			        <td width="15%" align="right" class="detail_label">订货方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORDER_CHANN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">订货方名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.ORDER_CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">联系人:</td>
					<td width="35%" class="detail_content">
                        ${rst.PERSON_CON}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">电话:</td>
					<td width="35%" class="detail_content">
                        ${rst.TEL}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">分发方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.DSTR_CHANN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">分发方名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.DSTR_CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">收货方编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.RECV_CHANN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">收货方名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.RECV_CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">接收人:</td>
					<td width="35%" class="detail_content">
                        ${rst.RECV_PSON_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">接收时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.RECV_TIME}&nbsp;
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
					<td width="15%" align="right" class="detail_label" >收货地址：</td>
					<td width="50%" class="detail_content" colspan="3" >${rst.RECV_ADDR }&nbsp;</td>
				</tr>
				 
			</table>
		</td>
	</tr>
</table>
</body>
</html>