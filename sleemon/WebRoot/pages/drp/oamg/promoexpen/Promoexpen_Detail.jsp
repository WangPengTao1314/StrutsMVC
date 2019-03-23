<!--
 * @prjName：喜临门营销平台
 * @fileName：Promoexpen_Detial
 * @author chenj
 * @time   2014-01-24 10：59：55 
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
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	    <script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script  type="text/javascript">
	    function getPath(){
	       var path = $("#ATT_PATH").val();
	       var array = path.split(";");
	       $("#ACTION_PATH").val(array[0]);
	       displayDownFile('ACTION_PATH','SAMPLE_DIR',false,false);
	       $("#BUDGET_PATH").val(array[1]);
	       displayDownFile('BUDGET_PATH','SAMPLE_DIR',false,false);
	       $("#AGREE_PATH").val(array[2]);
	       displayDownFile('AGREE_PATH','SAMPLE_DIR',false,false);
	    }
	</script>
	<title>信息详情</title>
</head>
<body style="overflow: auto;" class="bodycss1" onload="getPath();">
<table width="100%" border="0" cellpadding="4" cellspacing="4"  >
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
			   
			   <tr>
	                   <td width="10%" align="left" class="detail_label">申请人：</td>
					   <td width="20%" class="detail_content">
					       ${rst.REQ_NAME}&nbsp;
					       <input type="hidden" id="ATT_PATH" name="ATT_PATH" value="${rst.ATT_PATH}"/>
 					   </td>
					   <td width="10%" align="left" class="detail_label">申请日期：</td>
					   <td width="20%" class="detail_content">
					       ${rst.REQ_DATE}
 					   </td>
	                   <td width="10%" align="right" class="detail_label">战区名称：</td>
					   <td width="20%" class="detail_content">
					   		${rst.AREA_NAME}
					   </td>
					   </tr>
					   <tr>
	                   <td width="10%" align="right" class="detail_label">城市名称：</td>
					   <td width="20%" class="detail_content">
					        ${rst.CITY_NAME}
 					   </td>
	                   <td width="10%" align="right" class="detail_label">加盟商名称：</td>
					   <td width="20%" class="detail_content">
					        ${rst.CHANN_NAME}
					   </td>
					   <td width="10%" align="right" class="detail_label">城市类型：</td>
					   <td width="20%" class="detail_content">
					        ${rst.CITY_LVL}
 					   </td>
 					 </tr>
	                 <tr>
	                   <td width="10%" align="right" class="detail_label">申请编码：</td>
					   <td width="20%" class="detail_content">
					        ${rst.REQ_MAKE}
 					   </td>
 					   <td width="10%" align="right" class="detail_label">预算科目：</td>
					   <td width="20%" class="detail_content">
					        ${rst.BUDGET_ITEM_NAME}
 					   </td>
 					   <td width="10%" align="right" class="detail_label">促销方案：</td>
					   <td width="20%" class="detail_content">
					        ${rst.PRMT_PLAN_NAME}
 					   </td>
	                 </tr>
	                 <tr> 
	                   <td width="10%" align="right" class="detail_label">申请原因：</td>
					   <td width="20%" class="detail_content" colspan="5">
					        ${rst.REQ_REMARK}
 					   </td>
	                 </tr>
	               <tr>
	                   <td width="10%" align="right" class="detail_label" rowspan="3">申请事项：</td>
					   <td width="10%" align="left" class="detail_content">
					         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此次费用共计&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rst.TOTAL_PRMT_COST}
 					   </td>
					   <td width="10%" align="right" class="detail_label">预批金额：</td>
 					   <td width="20%" class="detail_content">
					       ${rst.BUDGET_AMOUNT}
 					   </td>
 					   <td width="10%" align="right" class="detail_label">附活动方案：</td>
 					   <td width="20%" class="detail_content">
					     <input json="ACTION_PATH" name="ACTION_PATH" id="ACTION_PATH" label="附活动方案"
						        type="hidden"/>
 					   </td>
	               </tr>
	               <tr>
	                   <td width="10%" align="left" class="detail_content">
					         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费用类别&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rst.COST_TYPE}
 					   </td>
	                   <td width="10%" align="right" class="detail_label">附合同：</td>
 					   <td width="20%" class="detail_content" colspan="3">
					     <input json="AGREE_PATH" name="AGREE_PATH" id="AGREE_PATH" label="附合同"
						        type="hidden"/>
 					   </td>
 					 </tr>
 					<tr> 
 					   <td width="10%" align="left" class="detail_content" colspan="6">
					         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;附费用预算&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input id="BUDGET_PATH" json="BUDGET_PATH" name="BUDGET_PATH"  type="hidden"   value="${rst.PIC_PATH}"/>
 					   </td>
 					</tr> 
 					<tr>
	                   <td width="10%" align="right" class="detail_label">计划零售额：</td>
					   <td width="20%" class="detail_content">
					      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					      ${rst.RETAIL_AMOUNT}
  					   </td>
	                   <td width="10%" align="right" class="detail_label">
	                            计划开单数：
	                   </td>
					   <td width="20%" class="detail_content">
					       ${rst.BILL_AMOUNT}
  					   </td>
  					   <td width="10%" align="right" class="detail_label">
	                                                 是否制作VI画面：
	                   </td>
					   <td width="20%" class="detail_content">
					        <c:if test="${rst.PRO_SCREEN eq 1}">
							       是
						    </c:if>
						    <c:if test="${rst.PRO_SCREEN eq 0}">
						               否
						    </c:if>
  					   </td>
	                 </tr> 
	                 <tr>
	                   <td width="10%" align="right" class="detail_label">加盟商版块：</td>
					   <td width="20%" align="left" class="detail_content">
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计实际收入：
					        ${rst.TOTAL_REAL_AMOUNT}
  					   </td>
	                   <td width="10%" align="right" class="detail_label">
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预批费：
	                   </td>
					   <td width="20%" class="detail_content">
					        ${rst.TOTAL_ADVC_AMOUNT}
  					   </td>
  					   <td width="15%" align="left" class="detail_content" colspan="6">
					       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计费效比：
					       &nbsp;&nbsp;&nbsp;
					       ${rst.TOTAL_RATE}
  					   </td>
	                  </tr>
	                   
	                   <tr>
	                   <td width="10%" align="right" class="detail_label">区域版块：</td>
					   <td width="20%" align="left" class="detail_content">
					       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预算费：
					       ${rst.AREA_TOTAL_BUDGET_AMOUNT}
  					   </td>
 					   <td width="10%" align="right" class="detail_label">
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预批费：
	                   </td>
					   <td width="20%" class="detail_content">
					       ${rst.AREA_TOTAL_PRE_AMOUNT}
  					   </td>
					   <td width="15%" align="left" class="detail_content" colspan="6">
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预算可用：
					       ${rst.AREA_TOTAL_AVALIABLE_AMOUNT}
  					   </td>
	                  </tr>
	                  <tr>
	                   <td width="10%" align="right" class="detail_label">战区版块：</td>
					   <td width="20%" align="left" class="detail_content">
					       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预算费：
					       ${rst.WARE_TOTAL_BUDGET_AMOUNT}
  					   </td>
 					   <td width="10%" align="right" class="detail_label">
	                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预批费：
	                   </td>
					   <td width="20%" class="detail_content">
					       ${rst.WARE_TOTAL_PRE_AMOUNT}
  					   </td>
					   <td width="15%" align="left" class="detail_content" colspan="6">
					       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;季度累计预算可用：
					       ${rst.WARE_TOTAL_AVALIABLE_AMOUNT}
  					   </td>
	                 </tr>  
	                 <tr>
	                   <td  class="detail_label">
	                                                         流程跟踪：
	                   </td>
	                   <td colspan="6" class="detail2">
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
	displayDownFile('ACTION_PATH','SAMPLE_DIR',false,false);
</script>
</html>