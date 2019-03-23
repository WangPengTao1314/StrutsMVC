<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Trainreq_Edit
 * @author ghx
 * @time   2014-02-28 14:01:04 
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/trainreq/Trainreq_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" class="wz">
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：渠道管理&gt;&gt;渠道培训管理&gt;&gt;渠道培训申请单维护编辑</td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
  	   <input id="selectCHANNCondition" name="selectCHANNCondition" type="hidden" value=" STATE='启用' and CHANN_ID  in (select distinct CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='${XTYH_ID}') " />
  	   <input id="selectTraincourse" name="selectTraincourse" type="hidden" value=" STATE='审核通过' "/>
  	   
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				 <tr>
                   <td width="15%" align="right" class="detail_label">培训申请单编号：</td>
				   <td width="35%" class="detail_content">
					   <c:if test="${empty rst.TRAIN_REQ_NO}">
	                     <input json="TRAIN_REQ_NO" name="TRAIN_REQ_NO" autocheck="true" label="培训申请单编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="自动生成" size="40"/> 
				   	   </c:if>
				   	   <c:if test="${not empty rst.TRAIN_REQ_NO}">
	                     <input json="TRAIN_REQ_NO" name="TRAIN_REQ_NO" autocheck="true" label="培训申请单编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.TRAIN_REQ_NO}" size="40"/> 
				   	   </c:if>
				   </td>
                   <td width="15%" align="right" class="detail_label">申请加盟商编号：</td>
				   <td width="35%" class="detail_content">
					    <input json="CHANN_ID" name="CHANN_ID" autocheck="true" label="申请加盟商ID" type="hidden" inputtype="string"   value="${rst.CHANN_ID}"/> 
	                    <input json="CHANN_NO" name="CHANN_NO" autocheck="true" label="申请加盟商编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.CHANN_NO}" size="40"/> 
					    <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
						     onclick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME','selectCHANNCondition');"/>&nbsp;
					     
				   </td>
                </tr>
                <tr>
               	   <td width="15%" align="right" class="detail_label">申请加盟商名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_NAME" name="CHANN_NAME" autocheck="true" label="申请加盟商名称"  type="text"   inputtype="string"   readonly    maxlength="100"  value="${rst.CHANN_NAME}" size="40"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">培训课程编号：</td>
				   <td width="35%" class="detail_content">
				     <input json="TRAIN_COURSE_ID" name="TRAIN_COURSE_ID" autocheck="true" label="培训课程ID" type="hidden" inputtype="string"   value="${rst.TRAIN_COURSE_ID}"/> 
                     <input json="TRAIN_COURSE_NO" name="TRAIN_COURSE_NO" autocheck="true" label="培训课程编号"  type="text"   inputtype="string"   readonly mustinput="true"  maxlength="30"  value="${rst.TRAIN_COURSE_NO}" size="40"/> 
                     <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
						     onclick="selCommon('BS_105', false, 'TRAIN_COURSE_ID', 'TRAIN_COURSE_ID', 'forms[0]','TRAIN_COURSE_NO,TRAIN_COURSE_NAME,TRAIN_TYPE,TRAIN_TIME_BEG,TRAIN_TIME_END,TRAIN_ADDR,FIXED_PSON_NUM', 'TRAIN_COURSE_NO,TRAIN_COURSE_NAME,TRAIN_TYPE,TRAIN_TIME_BEG,TRAIN_TIME_END,TRAIN_ADDR,FIXED_PSON_NUM','selectTraincourse');"/>&nbsp;
					     
				   </td>                   
               </tr>
               <tr>
               	   <td width="15%" align="right" class="detail_label">培训课程名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="TRAIN_COURSE_NAME" name="TRAIN_COURSE_NAME" autocheck="true" label="培训课程名称"  type="text"   inputtype="string"   readonly    maxlength="50"  value="${rst.TRAIN_COURSE_NAME}" size="40"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">培训类型：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="TRAIN_TYPE" name="TRAIN_TYPE" autocheck="true" label="培训类型"  type="text"   inputtype="string"   readonly  maxlength="30"  value="${rst.TRAIN_TYPE}" size="40"/> 
				   </td>                   
               </tr>
               <tr>
               	   <td width="15%" align="right" class="detail_label">培训时间从：</td>
				   <td width="35%" class="detail_content">
                     <input json="TRAIN_TIME_BEG" name="TRAIN_TIME_BEG" autocheck="true" label="培训时间从"  type="text"   inputtype="string"   readonly   maxlength="100"  value="${rst.TRAIN_TIME_BEG}" size="40"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">培训时间到：</td>
				   <td width="35%" class="detail_content">
                     <input json="TRAIN_TIME_END" name="TRAIN_TIME_END" autocheck="true" label="培训时间到"  type="text"   inputtype="string"   readonly   maxlength="100"  value="${rst.TRAIN_TIME_END}" size="40"/> 
				   </td>                   
               </tr>
               <tr>
               	   <td width="15%" align="right" class="detail_label">培训地点：</td>
				   <td width="35%" class="detail_content">
                     <input json="TRAIN_ADDR" name="TRAIN_ADDR" autocheck="true" label="培训地点"  type="text"   inputtype="string"   readonly   maxlength="250"  value="${rst.TRAIN_ADDR}" size="40"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">申请参加人数：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="FIXED_PSON_NUM" id="FIXED_PSON_NUM" name="FIXED_PSON_NUM" autocheck="true" type="hidden" inputtype="string" value="${rst.FIXED_PSON_NUM}"/>
                     <input json="REQ_JOIN_NUM" id="REQ_JOIN_NUM" name="REQ_JOIN_NUM" autocheck="true" label="申请参加人数"  type="text"   inputtype="int"   mustinput="true"     value="${rst.REQ_JOIN_NUM}" size="40"/> 
				   </td>                   
               </tr>
               <tr>	
					<td width="15%" align="right" class="detail_label" nowrap>申请理由：</td>
					<td width="35%" class="detail_content" colspan="3">
					   <textarea id="REQ_REASON" json="REQ_REASON" name="REQ_REASON"   mustinput="true" autocheck="true" inputtype="string" maxlength="250" value="${rst.REQ_REASON}" size="142" rows="5" cols="32%">${rst.REQ_REASON}</textarea>
					</td>		
			   </tr>
			   <tr>
               	   <td width="15%" align="right" class="detail_label">状态：</td>
				   <td width="35%" class="detail_content">
                     <input json="STATE" name="STATE" autocheck="true" label="状态"  type="text"   inputtype="string"   readonly     maxlength="100"  value="${rst.STATE}" size="40"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">申请人：</td>
				   <td width="35%" class="detail_content">
                     <input json="CRE_NAME" name="CRE_NAME" autocheck="true" label="申请人"  type="text"   inputtype="string"   readonly     maxlength="30"  value="${rst.CRE_NAME}" size="40"/> 
				   </td>                   
               </tr>
               <tr>
               	   <td width="15%" align="right" class="detail_label">申请时间：</td>
				   <td width="35%" class="detail_content">
                     <input json="CRE_TIME" name="CRE_TIME" autocheck="true" label="申请时间"  type="text"   inputtype="string"   readonly    maxlength="100"  value="${rst.CRE_TIME}" size="40"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">&nbsp;</td>
				   <td width="35%" class="detail_content">&nbsp;</td>                   
               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
 