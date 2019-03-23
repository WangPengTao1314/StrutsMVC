<!--
 * @prjName:喜临门营销平台
 * @fileName:Trainresult_Detial
 * @author ghx
 * @time   2014-07-10 
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
<body>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
			        <td width="15%" align="right" class="detail_label">培训申请单编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.TRAIN_REQ_NO}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">申请加盟商编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NO}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">申请加盟商名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NAME}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">培训课程编号:</td>
					<td width="35%" class="detail_content">
                        ${rst.TRAIN_COURSE_NO}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">培训课程名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.TRAIN_COURSE_NAME}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">培训类型:</td>
					<td width="35%" class="detail_content">
                        ${rst.TRAIN_TYPE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">培训时间从:</td>
					<td width="35%" class="detail_content">
                        ${rst.TRAIN_TIME_BEG}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">培训时间到:</td>
					<td width="35%" class="detail_content">
                        ${rst.TRAIN_TIME_END}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">培训地点:</td>
					<td width="35%" class="detail_content">
                        ${rst.TRAIN_ADDR}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">申请参加人数:</td>
					<td width="35%" class="detail_content">
                        ${rst.REQ_JOIN_NUM}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">申请理由:</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.REQ_REASON}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">申请人:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">申请时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">&nbsp;</td>
					<td width="35%" class="detail_content">
                        &nbsp;
					</td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>