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
			        <td width="15%" align="right" class="detail_label">精致化检查单号：</td>
					<td width="35%" class="detail_content">
                        ${rst.TERM_REFINE_CHECK_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">终端编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.TERM_NO}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">终端名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.TERM_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">品牌类型：</td>
					<td width="35%" class="detail_content">
                        ${rst.BUSS_SCOPE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">渠道名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NAME}&nbsp;
					</td>
					
					<td width="15%" align="right" class="detail_label">渠道电话：</td>
				    <td width="35%" class="detail_content">
				        ${rst.CHANN_TEL}
				    </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">终端版本：</td>
				   <td width="35%" class="detail_content">
                      ${rst.TERM_VERSION}
				   </td>
				   <td width="15%" align="right" class="detail_label">所属战区：</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NAME}&nbsp;
					</td>
               </tr> 
              
               <tr>
			        <td width="15%" align="right" class="detail_label">区域经理：</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_MANAGE_NAME}&nbsp;
					</td>
					<td width="15%" align="right" class="detail_label">精致化得分：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHECK_TOTAL_SCORE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">检查机构：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHECK_ORG_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">检查任务号：</td>
					<td width="35%" class="detail_content">
                        ${rst.TERM_REFINE_TASK_NO}
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">检查日期：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHECK_DATE}&nbsp;
					</td>
				    <td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>
               
               <tr>
                    <td width="15%" align="right" class="detail_label">神秘人评语：</td>
					<td width="35%" class="detail_content">
                       ${rst.MYSTIC_CMNR} 
					</td>
					
					<td width="15%" align="right" class="detail_label">主要扣分问题：</td>
					<td width="35%" class="detail_content">
                         ${rst.MAIN_DEDUCT_SCORE_REMARK}
					</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content">
                        ${rst.REMARK}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">相关资料：</td>
					<td width="35%" class="detail_content">
                       <input id="ATT_PATH" json="ATT_PATH" size="120" name="ATT_PATH"  type="hidden"   value="${rst.ATT_PATH}"/>
					</td>
               </tr>
                <tr>
	                   <td  class="detail_label">
	                                                         流程跟踪：
	                   </td>
	                   <td colspan="3" class="detail2">
	                     <table id="ordertb" width="99.98%" border="0" cellpadding="1" cellspacing="1" class="lst" >
							<tr  >
								<th nowrap align="center">序号</th>
								<th nowrap align="center" >操作者</th>
								<th nowrap align="center" >操作</th>
								<th nowrap align="center" >时间</th>
								<th nowrap align="center" >意见</th>
								<th nowrap align="center" >下一操作者</th>
							</tr>
							<c:forEach items="${flowInfoList}" var="flow" varStatus="row">
							<c:set var="r" value="${row.count % 2}"></c:set>
							<c:set var="rr" value="${row.count}"></c:set>
							<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
								<td nowrap align="center" width="10%">${rr}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATORNAME}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATION}&nbsp;</td>
								<td nowrap align="center" width="10%">${flow.OPERATETIME}&nbsp;</td>
								<td nowrap align="center" width="400px;" style="table-layout:fixed;word-break:break-all; word-wrap:break-word;" >${flow.REMARK}&nbsp;</td>
								<td nowrap align="center" width="20%">${flow.NEXTOPERATORNAME}&nbsp;</td>
							</tr>
							</c:forEach>
							<c:if test="${empty flowInfoList}">
							<tr>
								<td height="25" colspan="13" align="center" class="lst_empty">&nbsp;草拟，尚未进入审核流程!&nbsp;</td>
							</tr>
							</c:if>
						</table>
	                   </td>
	                 </tr>
			</table>
		</td>
	</tr>
</table>
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
</body>
</html>