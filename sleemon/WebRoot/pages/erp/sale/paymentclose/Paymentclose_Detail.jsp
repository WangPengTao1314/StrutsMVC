<!--
 * @prjName:喜临门营销平台
 * @fileName:Paymentcmt_Detial
 * @author lyg
 * @time   2013-08-25 09:50:09 
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
<body class="bodycss1">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
			        <td width="15%" align="right" class="detail_label">付款凭证编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.PAYMENT_UPLOAD_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">上传时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">凭证号:</td>
					<td width="35%" class="detail_content">
                        ${rst.PAYMENT_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">金额:</td>
					<td width="35%" class="detail_content">
                        ${rst.PAYMENT_AMOUNT}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">付款凭证路径:</td>
               		<td width="35%" class="detail_content">
						<input type="hidden" id ="PAYMENT_PATH" value='${rst.PAYMENT_PATH }' />
					</td>
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">渠道编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NO}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">渠道名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">区域编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NO}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">区域名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">申请人:</td>
					<td width="35%" class="detail_content">
                        ${rst.REQ_PSON_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">联系方式:</td>
					<td width="35%" class="detail_content">
                        ${rst.TEL}&nbsp;
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
					<td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3" style="word-break:break-all">${rst.REMARK }&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
	displayDownFile('PAYMENT_PATH','SAMPLE_DIR',true,false);
</script>
</html>