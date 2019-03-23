<!--
 * @prjName：喜临门营销平台
 * @fileName：Termrefinecheck_Detial
 * @author lyg
 * @time   2014-01-26 14：46：31 
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
<body>
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
			        <td width="15%" align="right" class="detail_label">检查方案编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_CHECK_PLAN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">检查方案名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_CHECK_PLAN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">方案类型：</td>
					<td width="35%" class="detail_content">
                        ${rst.PLAN_TYPE}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">状态：</td>
				    <td width="35%" class="detail_content">
				        ${rst.STATE}
				    </td>
               </tr>
                <tr>
			        <td width="15%" align="right" class="detail_label">制单人名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">制单时间：</td>
					<td width="35%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content">
                      ${rst.REMARK}
				   </td>
				   <td width="15%" align="right" class="detail_label">相关资料：</td>
				   <td width="35%" class="detail_content">
                       <input id="ATT_PATH" json="ATT_PATH" size="120" name="ATT_PATH"  type="hidden"   value="${rst.ATT_PATH}"/>
				   </td>
               </tr> 
			</table>
		</td>
	</tr>
</table>
</body>
</html>
<script type="text/javascript">
       var path = $("#ATT_PATH").val();
       var strs= new Array();   //定义一数组
       strs=path.split(",");    //字符分割      
       var str="";
       if(strs.length>1){
	       for(var i=0;i<strs.length;i++){
	          str+="SAMPLE_DIR,";
	       }
	       var str = str.substring(0, str.lastIndexOf(','));
	    } else {
	          str="SAMPLE_DIR";
	    }
       displayDownFile('ATT_PATH',str,false,false);
</script>