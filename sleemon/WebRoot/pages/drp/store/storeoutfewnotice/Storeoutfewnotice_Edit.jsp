<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_Edit
 * @author glw
 * @time   2013-08-17 17:07:03 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/storeoutfewnotice/Storeoutfewnotice_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：库存管理&gt;&gt;出库管理&gt;&gt;零星出库通知单维护编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" name="mainForm" >
   	   <input type="hidden" name="selectParams" value=" STATE in( '启用') and (BEL_CHANN_ID='${ZTXXID}' or LEDGER_ID = '${ZTXXID}')">
   	   <input type="hidden" name="selectParam" value="RYZT ='启用' and JGXXID='${ZTXXID}' and RYLB!='门店' ">
   	   
   	   <input type="hidden" id="state" value="${rst.STATE}">
   	   <input type="hidden" id="selRowId" value=""/> 
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                   <td width="15%" align="right" class="detail_label">出库通知单编号：</td>
				   <td width="35%" class="detail_content">
				   	  <input json="STOREIN_NOTICE_ID" name="STOREIN_NOTICE_ID" label="出库通知单ID" type="hidden" value="${rst.STOREIN_NOTICE_ID}"/> 
                      <input json="STOREIN_NOTICE_NO" name="STOREIN_NOTICE_NO" autocheck="true" label="出库通知单编号"  type="text"  size="35"  inputtype="string" readonly mustinput="true"     maxlength="30"
                      <c:if test="${rst.STOREIN_NOTICE_NO==null}">value="系统自动生成"</c:if>
                      <c:if test="${rst.STOREIN_NOTICE_NO!=null}">value="${rst.STOREIN_NOTICE_NO}"</c:if> 
                       /> 
				   </td>
                   <td width="15%" align="right" class="detail_label">单据类型：</td>
				   <td width="35%" class="detail_content">
                     <input json="BILL_TYPE" id="BILL_TYPE" name="BILL_TYPE" autocheck="true" label="单据类型"  type="text" size="35"   inputtype="string"     mustinput="true"     maxlength="30" readonly value="<c:out value="${rst.BILL_TYPE}">手工新增</c:out>"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">出库库房编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="STOREOUT_STORE_ID" name="STOREOUT_STORE_ID"  id="STOREOUT_STORE_ID" label="出库库房ID"   type="hidden" value="${rst.STOREOUT_STORE_ID}"/> 
                     <input json="STOREOUT_STORE_NO" name="STOREOUT_STORE_NO"  id="STOREOUT_STORE_NO" label="出库库房编号"  type="text"   size="35"  autocheck="true"   inputtype="string"  mustinput="true" readonly maxlength="100"  value="${rst.STOREOUT_STORE_NO}"/> 
				     <img align="absmiddle" name="selDEF_STORE_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_35', false, 'STOREOUT_STORE_ID', 'STORE_ID','forms[0]',
												 'STOREOUT_STORE_ID,STOREOUT_STORE_NO,STOREOUT_STORE_NAME', 'STORE_ID,STORE_NO,STORE_NAME','selectParams');">
				   </td>
                   <td width="15%" align="right" class="detail_label">出库库房名称</td>
				   <td width="35%" class="detail_content">
				     <input json="STOREOUT_STORE_NAME" name="STOREOUT_STORE_NAME"  id="STOREOUT_STORE_NAME" autocheck="true" label="出库库房名称"  type="text"  size="35"   inputtype="string"  mustinput="true" readonly maxlength="50"  value="${rst.STOREOUT_STORE_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">业务员名称：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="BUSS_PSON_ID"   name="BUSS_PSON_ID"   id="BUSS_PSON_ID"   label="业务员ID"    type="hidden" value="${rst.BUSS_PSON_ID}"/> 
                     <input json="BUSS_PSON_NAME" name="BUSS_PSON_NAME" id="BUSS_PSON_NAME" label="业务员名称"   type="text"  size="35"   inputtype="string" autocheck="true"   inputtype="string"  mustinput="true" maxlength="30"  value="${rst.BUSS_PSON_NAME}" readonly />
                      <img align="absmiddle" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
                     onClick="selCommon('System_3', false, 'BUSS_PSON_ID', 'RYXXID', 'forms[0]','BUSS_PSON_ID,BUSS_PSON_NAME,BUSS_DEPT_ID,BUSS_DEPT_NAME', 'RYXXID,XM,BMXXID,BMMC', 'selectParam')">
				   </td>
                   <td width="15%" align="right" class="detail_label">业务部门名称：</td>
				   <td width="35%" class="detail_content">
				     <input json="BUSS_DEPT_ID"   name="BUSS_DEPT_ID"    id="BUSS_DEPT_ID"    label="业务部门ID"   value="${rst.BUSS_DEPT_ID}" type="hidden" />
                     <input json="BUSS_DEPT_NAME" name="BUSS_DEPT_NAME"  id="BUSS_DEPT_NAME"  label="业务部门名称"  type="text"  size="35"  inputtype="string"  autocheck="true"   inputtype="string"  mustinput="true" maxlength="100"  value="${rst.BUSS_DEPT_NAME}" readonly /> 
				   </td>
               </tr>
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
 