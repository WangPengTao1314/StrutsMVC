<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:区域折扣
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */
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
			       <td width="15%" align="right" class="detail_label">区域编号：</td>
				   <td width="35%" class="detail_content">
					  ${rst.AREA_NO} 
				   </td>
				   <td width="15%" align="right" class="detail_label">区域名称：</td>
				   <td width="35%" class="detail_content">
					   ${rst.AREA_NAME} 
				   </td>
			   </tr>
			   <tr>
			       <td width="15%" align="right" class="detail_label">货品编号：</td>
				   <td width="35%" class="detail_content">
					   ${rst.PRD_NO} 
				   </td>
				   <td width="15%" align="right" class="detail_label">货品名称：</td>
				   <td width="35%" class="detail_content">
					   ${rst.PRD_NAME} 
				   </td>
			   </tr>
			   <tr>
			       <td width="15%" align="right" class="detail_label">规格型号：</td>
				   <td width="35%" class="detail_content">
					    ${rst.PRD_SPEC} 
				   </td>
				   <td width="15%" align="right" class="detail_label">折扣类型：</td>
				   <td width="35%" class="detail_content">
					   ${rst.DECT_TYPE} 
				   </td>
			   </tr>
			   <tr>
			       <td width="15%" align="right" class="detail_label">折扣率：</td>
				   <td width="35%" class="detail_content">
					   ${rst.DECT_RATE} 
				   </td>
				   <td width="15%" align="right" class="detail_label">基准价格：</td>
				   <td width="35%" class="detail_content">
					   ${rst.BASE_PRICE} 
				   </td>
			   </tr>
			      <tr>
			       <td width="15%" align="right" class="detail_label">折扣价：</td>
				   <td width="35%" class="detail_content">
					   ${rst.DECT_PRICE} 
				   </td>
				   <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content">
				   </td>
			   </tr>
		    </table>
	   </td>
	</tr>
</table>
 
</body>
<script type="text/javascript">
</script>
</html>

 