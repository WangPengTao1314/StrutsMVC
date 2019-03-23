<!--
 * @prjName：喜临门营销平台
 * @fileName：Promoreim_Detial
 * @author chenj
 * @time   2014-01-25 19：49：05 
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
			        <td width="15%" align="right" class="detail_label">推广费用报销单号：</td>
					<td width="35%" class="detail_content">
                        ${rst.PRMT_COST_REIT_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">关联推广费用申请单号：</td>
					<td width="35%" class="detail_content">
                        ${rst.PRMT_COST_REQ_NO}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">加盟商编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">加盟商名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">加盟商联系人：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_PERSON_CON}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">加盟商电话：</td>
					<td width="35%" class="detail_content">
                        ${rst.CHANN_TEL}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">所属战区编号：</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NO}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">所属战区名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">城市名称：</td>
					<td width="35%" class="detail_content">
                        ${rst.ZONE_ADDR}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">区域经理：</td>
					<td width="35%" class="detail_content">
                        ${rst.AREA_MANAGE_NAME}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">申请人：</td>
					<td width="35%" class="detail_content">
                        ${rst.REQ_NAME}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">申请日期：</td>
					<td width="35%" class="detail_content">
                        ${rst.REQ_DATE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">执行总结：</td>
					<td width="35%" class="detail_content" colspan="3">
                        ${rst.EXCT_SMRZ}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">此次推广费用共计：</td>
					<td width="35%" class="detail_content">
                        ${rst.TOTAL_PRMT_COST}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">申请报销金额：</td>
					<td width="35%" class="detail_content">
                        ${rst.REIT_REQ_AMOUNT}&nbsp;
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">费用类别：</td>
					<td width="35%" class="detail_content">
                        ${rst.COST_TYPE}&nbsp;
					</td>
			        <td width="15%" align="right" class="detail_label">状态：</td>
					<td width="35%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">附件：</td>
					<td width="35%" class="detail_content" colspan="3">
                        <input type="hidden" id ="STATENEBTS_ATT" value='${rst.STATENEBTS_ATT}' />
					</td>
               </tr>
                <tr>
	                   <td colspan="4" class="detail2">
	                     <table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
							<tr  >
								<th nowrap align="center">序号</th>
								<th nowrap align="center" >操作</th>
								<th nowrap align="center" >时间</th>
								<th nowrap align="center" >意见</th>
								<th nowrap align="center" >下一操作者</th>
							</tr>
							<c:forEach items="${flowInfoList}" var="flow" varStatus="row">
							<c:set var="r" value="${row.count % 2}"></c:set>
							<c:set var="rr" value="${row.count}"></c:set>
							<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
								<td nowrap align="center">${rr}&nbsp;</td>
								<td nowrap align="center">${flow.OPERATION}&nbsp;</td>
								<td nowrap align="center">${flow.OPERATETIME}&nbsp;</td>
								<td nowrap align="center">${flow.REMARK}&nbsp;</td>
								<td nowrap align="center">${flow.NEXTOPERATORNAME}&nbsp;</td>
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
</body>
<script type="text/javascript">

	/*var path = $("#STATENEBTS_ATT").val();
    var leg = path.split(",").length ;
    var folder = "";
    if(leg>1){
    	for(var i=0;i<leg;i++){
	    	folder = folder+"SAMPLE_DIR,";
	    }
    	folder = folder.substring(0,folder.length-1);
    }else{
    	folder = "SAMPLE_DIR";
    }*/

	displayDownFile('STATENEBTS_ATT','SAMPLE_DIR',false,false);
</script>
</html>