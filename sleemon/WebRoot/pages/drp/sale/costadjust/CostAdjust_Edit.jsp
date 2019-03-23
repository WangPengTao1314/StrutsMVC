<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_Edit
 * @author lyg
 * @time   2013-08-19 14:35:52 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/costadjust/CostAdjust_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>成本调整单申请</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：财务管理&gt;&gt;财务结算&gt;&gt;成本调整编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<input type="hidden" name="selectParams" id="selectParams" value=" STATE ='启用' and DEL_FLAG=0 and  (BEL_CHANN_ID='${rst.LEDGER_ID}' or BEL_CHANN_ID in (${rst.TERM_CHARGE}))">
               <tr>
                    <td width="20%" align="right" class="detail_label">成本调整单编号：</td>
					<td width="35%" class="detail_content">
                       <input json="COST_ADJUST_NO" name="COST_ADJUST_NO" autocheck="true" label="成本调整单编号" mustinput="true"  type="text"  inputtype="string"  
                        readonly    maxlength="30" 
                        <c:if test="${rst.COST_ADJUST_NO==null}"> value="自动生成"</c:if>
                     	<c:if test="${rst.COST_ADJUST_NO!=null}">value="${rst.COST_ADJUST_NO}"</c:if>
                        /> 
					</td>
					<td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content">
				   </td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">年份：</td>
				   <td width="35%" class="detail_content">
                     <select id="YEAR" name="YEAR"  json="YEAR" style="width: 155px;" onchange="delDtl();" ></select> <input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >
				   </td>
               		<td width="15%" align="right" class="detail_label">月份：</td>
				   <td width="35%" class="detail_content">
                     <select id="MONTH" name="MONTH"  json="MONTH"   style="width: 155px;" onchange="delDtl();" ></select> <input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >
				   </td>
               </tr>
<!--               <tr>-->
<!--               		<td width="15%" align="right" class="detail_label">库房编号：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--				   	 <input json="STORE_ID" id="STORE_ID" name="STORE_ID" autocheck="true" label="库房ID" type="hidden" inputtype="string"   value="${rst.STORE_ID}" /> -->
<!--                     <input json="STORE_NO" name="STORE_NO" autocheck="true" label="库房编号"  type="text"   inputtype="string"   readonly    mustinput="true"    maxlength="30"  value="${rst.STORE_NO}"/>-->
<!--                     <img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selectStore();"> -->
<!--				   </td>-->
<!--				    <td width="15%" align="right" class="detail_label">库房名称：</td>-->
<!--				   <td width="35%" class="detail_content">-->
<!--                     <input json="STORE_NAME" name="STORE_NAME" autocheck="true" label="库房名称"  type="text"   inputtype="string"   readonly    mustinput="true"      maxlength="100"  value="${rst.STORE_NAME}"/>-->
<!--				   </td>-->
<!--               </tr>-->
				<tr>
                    <td width="15%" align="right" class="detail_label">备注：</td>
					<td width="35%" class="detail_content" colspan="3">
                         <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
					</td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
SelDictShow("YEAR","89","${rst.YEAR}","");
SelDictShow("MONTH","168","${rst.MONTH}","");
</script>
 