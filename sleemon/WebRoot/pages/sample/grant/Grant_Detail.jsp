<!--
 * @prjName:供应链_贵人鸟
 * @fileName:Grant_Detial
 * @author zhuxw
 * @time   2013-05-15 10:35:30 
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
			        <td width="15%" align="right" class="detail_label">成品不良通知单编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.CPBLTZDBH}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">流程号:</td>
					<td width="35%" class="detail_content">
                        ${rst.LCH}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">往来单位编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.WLDWBH}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">往来单位名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.WLDWMC}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">退回数量:</td>
					<td width="35%" class="detail_content">
                        ${rst.THSL}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRENAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">制单时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRETIME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">更新人姓名:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPDNAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">:</td>
					<td width="35%" class="detail_content">
                        ${rst.UPDTIME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">部门名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.BMMC}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">机构名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.JGMC}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>