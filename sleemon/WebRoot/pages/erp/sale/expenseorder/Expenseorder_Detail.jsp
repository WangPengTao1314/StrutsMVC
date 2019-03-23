<!--
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10 
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
	<script type="text/javascript">
	      function openUrl(lable,url,flag){
	        var urlAll;
			if(""==flag||null==flag){
				flag=1;
			}
			if("0"==flag){
				urlAll="../../"+url+"&PRMT_COST_REQ_NO="+'${rst.RELATE_ORDER_NOS}';
			} 
			toShowPage(lable,urlAll)
          }
         
         function toShowPage(lable,urlAll){
			var mainFrame = window.top.mainFrame;
		 	mainFrame.addTab(lable,lable,urlAll);
		 }
		 
		 function getPath(){
	       var path = $("#ATT_PATH").val();
	       var array = path.split(";");
	       $("#EXPENSE_ATT").val(array[0]);
	       displayDownFile('EXPENSE_ATT','SAMPLE_DIR',false,false);
	       $("#EXPENSE_ATT_PIC").val(array[1]);
	       displayDownFile('EXPENSE_ATT_PIC','SAMPLE_DIR',false,false);
	    }
	</script>
</head>
<body style="overflow: auto;" class="bodycss1" onload="getPath()">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="" scrolling="AUTO">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
               <tr>
			        <td width="10%" align="right" class="detail_label">费用报销单编号:</td>
					<td width="20%" class="detail_content"> ${rst.EXPENSE_ORDER_NO}&nbsp; </td>
			        <td width="10%" align="right" class="detail_label">费用报销类型:</td>
					<td width="20%" class="detail_content">  ${rst.EXPENSE_TYPE}&nbsp;</td>
					<td width="10%" align="right" class="detail_label">预算科目编号:</td>
					<td width="20%" class="detail_content">  ${rst.BUDGET_ITEM_NO}&nbsp; </td>
               </tr>
               <tr>
			        <td width="10%" align="right" class="detail_label">预算科目名称:</td>
					<td width="20%" class="detail_content"> ${rst.BUDGET_ITEM_NAME}&nbsp; </td>
                    <td width="10%" align="right" class="detail_label">预算科目类型:</td>
					<td width="20%" class="detail_content"> ${rst.BUDGET_ITEM_TYPE}&nbsp; </td>
			        <td width="10%" align="right" class="detail_label">年份:</td>
					<td width="20%" class="detail_content"> ${rst.YEAR}&nbsp;</td>
               </tr>
               <tr>
                    <td width="10%" align="right" class="detail_label">季度:</td>
					<td width="20%" class="detail_content"> ${rst.QUARTER}&nbsp; </td>
			        <td width="10%" align="right" class="detail_label">月份:</td>
					<td width="20%" class="detail_content"> ${rst.MONTH}&nbsp;</td>
                    <td width="10%" align="right" class="detail_label">报销人姓名:</td>
					<td width="20%" class="detail_content"> ${rst.EXPENSE_PSON_NAME}&nbsp; </td>
			   </tr>
               <tr>
			        <td width="10%" align="right" class="detail_label">报销金额:</td>
					<td width="20%" class="detail_content"> ${rst.EXPENSE_AMOUNT}&nbsp;</td>
                    <td width="10%" align="right" class="detail_label">报销部门编号:</td>
					<td width="20%" class="detail_content"> ${rst.EXPENSE_DEPT_NO}&nbsp; </td>
			        <td width="10%" align="right" class="detail_label">报销部门名称:</td>
					<td width="20%" class="detail_content"> ${rst.EXPENSE_DEPT_NAME}&nbsp;</td>
               </tr>
               <tr>
                    <td width="10%" align="right" class="detail_label">报销日期:</td>
					<td width="20%" class="detail_content"> ${rst.EXPENSE_DATE}&nbsp; </td>
				    <td width="10%" align="right" class="detail_label">关联单据编号:</td>
					<td width="20%" class="detail_content"><a href='#' onclick="openUrl('推广费用申请单维护','expenseorder.shtml?action=toPromoexpen','0')">${rst.RELATE_ORDER_NOS}&nbsp;</a></td>
                    <td width="10%" align="right" class="detail_label">渠道编号:</td>
				    <td width="20%" class="detail_content">${rst.CHANN_NO}</td>
               </tr>
               <tr>
                    <td width="10%" align="right" class="detail_label">渠道名称:</td>
				    <td width="20%" class="detail_content">${rst.CHANN_NAME}</td>
			        <td width="10%" align="right" class="detail_label">申请金额:</td>
					<td width="20%" class="detail_content"> ${rst.RELATE_AMOUNT_REQ}&nbsp;</td>
					<td width="10%" align="right" class="detail_label">制单人:</td>
					<td width="20%" class="detail_content">
                        ${rst.CRE_NAME}&nbsp;
					</td>
               </tr>
               <tr>
                    <td width="10%" align="right" class="detail_label">制单时间:</td>
					<td width="20%" class="detail_content">
                        ${rst.CRE_TIME}&nbsp;
					</td>
                    <td width="10%" align="right" class="detail_label">状态:</td>
                    <td width="20%" class="detail_content">
                        ${rst.STATE}&nbsp;
					</td>
                    <td width="10%" align="right" class="detail_label">更新人:</td>
					<td width="20%" class="detail_content">
                        ${rst.UPD_NAME}&nbsp;
					</td>
               </tr>
               <tr>
                    <td width="10%" align="right" class="detail_label">更新时间:</td>
					<td width="20%" class="detail_content">
                        ${rst.UPD_TIME}&nbsp;
					</td>
                    <td width="10%" align="right" class="detail_label">审核人:</td>
					<td width="20%" class="detail_content">
                        ${rst.AUDIT_NAME}&nbsp;
					</td>
			        <td width="10%" align="right" class="detail_label">审核时间:</td>
					<td width="20%" class="detail_content">
                        ${rst.AUDIT_TIME}&nbsp;
					</td>
				</tr>
                <tr>
                    <td width="10%" align="right" class="detail_label">制单部门:</td>
					<td width="20%" class="detail_content">
                        ${rst.DEPT_NAME}&nbsp;
					</td>
			        <td width="10%" align="right" class="detail_label">制单机构:</td>
					<td width="20%" class="detail_content">
                        ${rst.ORG_NAME}&nbsp;
					</td>
			        <td width="10%" align="right" class="detail_label">帐套名称:</td>
					<td width="20%" class="detail_content">
                        ${rst.LEDGER_NAME}&nbsp;
					</td>
               </tr> 
               <tr>
                   <td width="10%" align="right" class="detail_label">附件:</td>
				   <td width="20%" class="detail_content" colspan="2"> 
				   	   <input  json="ATT_PATH"    id="ATT_PATH"    name="ATT_PATH"    label="附件"   type="hidden"    value="${rst.ATT_PATH}"/>
					   <input  json="EXPENSE_ATT" id="EXPENSE_ATT" name="EXPENSE_ATT" label="附件"   type="hidden"    value="${rst.EXPENSE_ATT}"/>
				   </td>
				   <td width="10%" align="right" class="detail_label">效果图:</td>
				   <td width="20%" class="detail_content" colspan="2"> 
					   <input  json="EXPENSE_ATT_PIC" id="EXPENSE_ATT_PIC" name="EXPENSE_ATT_PIC" label="效果图"   type="hidden"    value="${rst.EXPENSE_ATT}"/>
				   </td>
               </tr>
               
                <tr>
                   <td width="10%" align="right" class="detail_label">备注:</td>
				   <td width="20%" class="detail_content" colspan="5"> ${rst.REMARK}&nbsp; </td>
               </tr>
                <tr>
	                   <td  class="detail_label">
	                                                         流程跟踪：
	                   </td>
	                   <td colspan="7" class="detail2">
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
</body>
<script type="text/javascript">
//displayDownFile('EXPENSE_ATT','SAMPLE_DIR',true);
</script>
</html>