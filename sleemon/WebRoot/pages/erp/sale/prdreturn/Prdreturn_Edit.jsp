<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_Edit
 * @author wzg
 * @time   2013-08-19 15:33:31 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/prdreturn/Prdreturn_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;退货管理&gt;&gt;退货单维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
   	   <input id="selectCHANNCondition" name="selectCHANNCondition" type="hidden" value=" STATE='启用' "/>
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">    
               
               <tr>
                   <td width="15%" align="right" class="detail_label">退货单编号：</td>
				   <td width="35%" class="detail_content">
					   <c:if test="${empty rst.PRD_RETURN_NO}">
	                     <input json="PRD_RETURN_NO" name="PRD_RETURN_NO" autocheck="true" label="退货单编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="系统自动生成"/> 
				   	   </c:if>
				   	   <c:if test="${not empty rst.PRD_RETURN_NO}">
	                     <input json="PRD_RETURN_NO" name="PRD_RETURN_NO" autocheck="true" label="退货单编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.PRD_RETURN_NO}"/> 
				   	   </c:if>
				   </td>
                   <td width="15%" align="right" class="detail_label">单据类型：</td>
				   <td width="35%" class="detail_content">
					   <c:if test="${empty rst.BILL_TYPE}">
	                     <input json="BILL_TYPE" name="BILL_TYPE" autocheck="true" label="单据类型"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="手工新增"/> 
				   	   </c:if>
				   	   <c:if test="${not empty rst.BILL_TYPE}">
	                     <input json="BILL_TYPE" name="BILL_TYPE" autocheck="true" label="单据类型"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.BILL_TYPE}"/> 
				   	   </c:if>
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">来源单据编号：</td>
				   <td width="35%" class="detail_content">
				     <input json="FROM_BILL_ID" name="FROM_BILL_ID" autocheck="true" label="来源单据ID" type="hidden" inputtype="string"   value="${rst.FROM_BILL_ID}"/> 
                     <input json="FROM_BILL_NO" name="FROM_BILL_NO" autocheck="true" label="来源单据编号"  type="text"   inputtype="string"  readonly       maxlength="30"  value="${rst.FROM_BILL_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">预计退货日期：</td>
				   <td width="35%" class="detail_content">
				   <!--  //-- 0156108--Start--刘曰刚--2014-06-16// -->
				   <!-- 把预计退货日期修改为必填字段 -->
                     <input json="EXPECT_RETURNDATE" name="EXPECT_RETURNDATE" autocheck="true" label="预计退货日期"  type="text"  mustinput="true"    inputtype="string"  onclick="SelectDate();"      maxlength="10"  value="${rst.EXPECT_RETURNDATE}"/>
                     <!--  //-- 0156108--End--刘曰刚--2014-06-16// --> 
				     <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"  onclick="SelectDate(EXPECT_RETURNDATE);" >
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">退货方编号：</td>
				   <td width="35%" class="detail_content">
			   		 <input json="RETURN_CHANN_ID" name="RETURN_CHANN_ID" autocheck="true" label="退货方ID" type="hidden" inputtype="string"   value="${rst.RETURN_CHANN_ID}"/> 
                     <input json="RETURN_CHANN_NO" name="RETURN_CHANN_NO" autocheck="true" label="退货方编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.RETURN_CHANN_NO}"/> 
				     <c:if test="${empty rst.BILL_TYPE or '手工新增' eq rst.BILL_TYPE}">
					     <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
					     onclick="selCommon('BS_19', false, 'RETURN_CHANN_ID', 'CHANN_ID', 'forms[0]','RETURN_CHANN_NO,RETURN_CHANN_NAME', 'CHANN_NO,CHANN_NAME','selectCHANNCondition');"/>&nbsp;
				     </c:if>
				   </td>
                   <td width="15%" align="right" class="detail_label">退货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="RETURN_CHANN_NAME" name="RETURN_CHANN_NAME" autocheck="true" label="退货方名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.RETURN_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">收货方编号：</td>
				   <td width="35%" class="detail_content">
				    <input json="RECV_CHANN_ID" name="RECV_CHANN_ID" autocheck="true" label="退货方ID" type="hidden" inputtype="string"   value="${rst.RECV_CHANN_ID}"/> 
                     <input json="RECV_CHANN_NO" name="RECV_CHANN_NO" autocheck="true" label="收货方编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.RECV_CHANN_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">收货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="RECV_CHANN_NAME" name="RECV_CHANN_NAME" autocheck="true" label="收货方名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.RECV_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="3">
                     <textArea json="REMARK" name="REMARK" autocheck="true" label="备注"  inputtype="string"  cols="80%" rows="4"  maxlength="250" /><c:out value="${rst.REMARK}"></c:out></textArea>
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
</script>
 