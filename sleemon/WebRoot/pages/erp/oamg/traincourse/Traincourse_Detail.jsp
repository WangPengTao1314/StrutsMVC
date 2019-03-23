<!--
 * @module 渠道培训管理
 * @func 培训课程维护
 * @version 1.1
 * @time   2014-07-04 
 * @author ghx
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
												<td width="15%" align="right" class="detail_label" nowrap>
													培训课程编号：
												</td>
												<td width="35%" class="detail_content">
													${rst.TRAIN_COURSE_NO} &nbsp;
												</td>
												<td width="15%" align="right" class="detail_label">
													培训课程名称：
												</td>
												<td width="35%" class="detail_content">
													${rst.TRAIN_COURSE_NAME} &nbsp;
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													培训类型：
												</td>
												<td width="35%" class="detail_content">
													${rst.TRAIN_TYPE} &nbsp;												
												</td>
												<td width="15%" align="right" class="detail_label">
													额度人数：
												</td>
												<td width="35%" class="detail_content">
													${rst.FIXED_PSON_NUM} &nbsp;	
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													培训时间从：
												</td>
												<td width="35%" class="detail_content">
													${rst.TRAIN_TIME_BEG} &nbsp;													
												</td>
												<td width="15%" align="right" class="detail_label">
													培训时间到：
												</td>
												<td width="35%" class="detail_content">
													${rst.TRAIN_TIME_END} &nbsp;
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													   培训地点：
												</td>
												<td width="35%" class="detail_content">
													${rst.TRAIN_ADDR} &nbsp;
												</td>
												<td width="15%" align="right" class="detail_label">
													状态：
												</td>
												<td width="35%" class="detail_content">
													${rst.STATE} &nbsp;
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  培训目的：
												</td>
												<td width="35%" class="detail_content" colspan="3">
													${rst.TRAIN_GOAL} &nbsp;
												</td>
												
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label" nowrap>
													  培训内容：
												</td>
												<td width="35%" class="detail_content" colspan="3">
													${rst.TRAIN_CONTENT} &nbsp;
												</td>
												
											</tr>
											<tr>
												<td width="15%" align="right" class="detail_label">
													相关附件：
												</td>
												<td width="35%" class="detail_content" colspan="3">
													<input type="hidden" id="ATT_PATH" name="ATT_PATH" value="${rst.ATT_PATH}"/>
													 &nbsp;
												</td>
												
											</tr>
											
			</table>
		</td>
	</tr>
</table>
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
</body>
</html>