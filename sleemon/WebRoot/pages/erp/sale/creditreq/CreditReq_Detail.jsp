<!--
 * @prjName: 信用额度变更申请
 * @fileName:Grant_Frame
 * @author zhang_zhongbin
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
<body class="bodycss1" >
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
			        <td width="15%" align="right" class="detail_label">信用变更申请单号：</td>
					<td width="35%" class="detail_content">
                        ${rst.CREDIT_REQ_ID}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">渠道编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NO}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">渠道名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">区域编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NO}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">区域名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">原信用额度：</td>
					<td width="35%" class="detail_content">
                        ${rst.OLD_CREDIT_TOTAL}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">变更后信用额度：</td>
					<td width="35%" class="detail_content">
                        ${rst.NEW_CREDIT_TOTAL}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">申请人：</td>
					<td width="35%" class="detail_content">
                        ${rst.REQ_PSON_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">制单人：</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
               </tr>     
          
              <tr>
			        <td width="15%" align="right" class="detail_label">更新人：</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">更新时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
               </tr>           
               <tr>
			        <td width="15%" align="right" class="detail_label">审核人：</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">审核时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.AUDIT_TIME}&nbsp;
					</td>
               </tr>     
               
                <tr>
			        <td width="15%" align="right" class="detail_label">制单部门：</td>
					<td width="35%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">制单机构：</td>
					<td width="35%" class="detail_content">
                        ${rst.ORG_NAME}&nbsp;
					</td>
               </tr>   
               
                <tr>
			        <td width="15%" align="right" class="detail_label">帐套名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.LEDGER_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>  
               
               <tr>
			        <td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3" rowspan="3"> 
                        ${rst.REMARK}&nbsp;
					</td>
			        
               </tr>
                
			</table>
		</td>
	</tr>
</table>
</body>
</html>