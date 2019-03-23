<!--
 * @prjName:喜临门营销平台
 * @fileName:Advreq_Detial
 * @author ghx
 * @time   2014-07-15 
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
			        <td width="15%" align="right" class="detail_label">广告投放申请单号:</td>
					<td width="35%" class="detail_content">
                        ${rst.ERP_ADV_REQ_NO}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">广告投放类型:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADV_TYPE}&nbsp;
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
			        <td width="15%" align="right" class="detail_label">所属战区:</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NAME}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">区域经理:</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_MANAGE_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">渠道排名:</td>
					<td width="35%" class="detail_content">
                        <span id="CHANN_RANK">${rst.CHANN_RANK}</span>
					</td>
                    <td width="15%" align="right" class="detail_label">广告公司名称:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADV_CO_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">广告公司联系人:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADV_CO_CONTACT}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">广告公司联系电话:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADV_CO_TEL}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">广告投放内容:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADV_CONTENT}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">广告投放地点:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADV_ADDR}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">广告投放开始时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADV_START_DATE}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">广告投放结束时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADV_END_DATE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">广告投放总预算金额:</td>
					<td width="35%" class="detail_content">
                        ${rst.ADV_TOTAL_AMOUNT}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">向总部申请支持费用:</td>
					<td width="35%" class="detail_content">
                        ${rst.HEAD_SUP_AMOUNT}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">广告投放附件:</td>
					<td width="35%" class="detail_content">
                        <input type="hidden" id ="ATT_PATH" value='${rst.ATT_PATH}' />
					</td>
                    <td width="15%" align="right" class="detail_label">状态:</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">申请人:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
                    <td width="15%" align="right" class="detail_label">申请时间:</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">备注:</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.REMARK}&nbsp;
					</td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</body>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
	    var path = $("#ATT_PATH").val();
	    var leg = path.split(",").length ;
	    var folder = "";
	    if(leg>1){
	    	for(var i=0;i<leg;i++){
		    	folder = folder+"SAMPLE_DIR,";
		    }
	    	folder = folder.substring(0,folder.length-1);
	    }else{
	    	folder = "SAMPLE_DIR";
	    }
	    
        //参考pages\common\sample\samplePlus.jsp
        displayDownFile('ATT_PATH',folder,false,false);
       
       
       
   </script>
</html>