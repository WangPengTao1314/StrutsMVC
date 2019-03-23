<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_Edit
 * @author wzg
 * @time   2013-08-15 10:17:13 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/prdreturnreq/Prdreturnreq_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：销售管理&gt;&gt;退货管理&gt;&gt;退货申请单维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input id="selectCHANNCondition" name="selectCHANNCondition" type="hidden" value=" STATE='启用' and DEL_FLAG=0 "/>
       <input id="selectSTORECondition" name="selectSTORECondition" type="hidden" value=" STATE='启用' and LEDGER_ID = '${rst.RETURN_CHANN_ID}' and DEL_FLAG=0 "/>
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="15%" align="right" class="detail_label">退货申请单编号：</td>
				   <td width="35%" class="detail_content">
				   		<c:if test="${empty rst.PRD_RETURN_REQ_NO}">
							<input json="PRD_RETURN_REQ_NO" name="PRD_RETURN_REQ_NO" autocheck="true" label="退货申请单编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="系统自动生成"/> 
						</c:if>
						<c:if test="${not empty rst.PRD_RETURN_REQ_NO}">
							<input json="PRD_RETURN_REQ_NO" name="PRD_RETURN_REQ_NO" autocheck="true" label="退货申请单编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.PRD_RETURN_REQ_NO}"/> 
						</c:if>
				   </td>
                    <td width="15%" align="right" class="detail_label">单据类型：</td>
					<td width="35%" class="detail_content">
                         <select json="BILL_TYPE" id="BILL_TYPE" label="单据类型"  name="BILL_TYPE" style="width:155px" autocheck="true" inputtype="string"   mustinput="true" ></select>  
				    </td>	 
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">退货方编号：</td>
				   <td width="35%" class="detail_content">
				   <input json="RETURN_CHANN_ID" name="RETURN_CHANN_ID" type="hidden" value="${rst.RETURN_CHANN_ID}"/> 
                     <input json="RETURN_CHANN_NO" name="RETURN_CHANN_NO" autocheck="true" label="退货方编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.RETURN_CHANN_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">退货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="RETURN_CHANN_NAME" name="RETURN_CHANN_NAME" autocheck="true" label="退货方名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.RETURN_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">收货方编号：</td>
				   <td width="35%" class="detail_content">
				     <input     type="hidden" id="RECV_CHANN_ID_OLD"  value="${rst.RECV_CHANN_ID}"/>
				     <input json="RECV_CHANN_ID" name="RECV_CHANN_ID" id="RECV_CHANN_ID" type="hidden"  value="${rst.RECV_CHANN_ID}"/> 
                     <input json="RECV_CHANN_NO" name="RECV_CHANN_NO" autocheck="true" label="收货方编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.RECV_CHANN_NO}"/> 
				      <%-- <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				     onclick="selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[0]','RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_NO,CHANN_NAME','selectCHANNCondition');changChildPrd();"/>&nbsp;--%>
				   </td>
                   <td width="15%" align="right" class="detail_label">收货方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="RECV_CHANN_NAME" name="RECV_CHANN_NAME" autocheck="true" label="收货方名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.RECV_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">退货出库库房编号：</td>
				   <td width="35%" class="detail_content">
				     <input json="RETURN_STORE_ID" name="RETURN_STORE_ID" id="RETURN_STORE_ID" label="退货出库库房编号"  type="hidden"  value="${rst.RETURN_STORE_ID}"/> 
                     <input json="RETURN_STORE_NO" name="RETURN_STORE_NO" autocheck="true" label="退货出库库房编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.RETURN_STORE_NO}"/> 
                     <c:if test="${empty rst.RETURN_STORE_NO}">
                        <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
				              onclick="selCommon('BS_35', false, 'RETURN_STORE_ID', 'STORE_ID', 'forms[0]','RETURN_STORE_NO,RETURN_STORE_NAME', 'STORE_NO,STORE_NAME','selectSTORECondition');"/>&nbsp;
                     </c:if>
				     <%--<c:if test="${not empty rst.RETURN_STORE_NO}">
                        <img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif"  disabled="disabled"
				              onclick="selCommon('BS_35', false, 'RETURN_STORE_ID', 'STORE_ID', 'forms[0]','RETURN_STORE_NO,RETURN_STORE_NAME', 'STORE_NO,STORE_NAME','selectSTORECondition');"/>&nbsp;
                     </c:if>
				   --%></td>
                   <td width="15%" align="right" class="detail_label">退货出库库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="RETURN_STORE_NAME" name="RETURN_STORE_NAME" autocheck="true" label="退货出库库房名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="${rst.RETURN_STORE_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content"  colspan="3">
                     <textarea json="REMARK" name="REMARK"  id="REMARK" autocheck="true" label="备注"   inputtype="string"  rows="4" cols="80%"  maxlength="250">${rst.REMARK}</textarea>
				   </td>
               </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
     <c:if test="${empty AREA_SER_ID}">
        //A级渠道
        SelDictShow("BILL_TYPE","BS_105","${rst.BILL_TYPE}","");
     </c:if>
	 <c:if test="${not empty AREA_SER_ID}"> 
	    //B级渠道
        SelDictShow("BILL_TYPE","BS_106","${rst.BILL_TYPE}","");
     </c:if>
</script>
</html>

 