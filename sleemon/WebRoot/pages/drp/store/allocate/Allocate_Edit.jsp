<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Allocate_Edit
 * @author lyg
 * @time   2013-09-05 13:29:12 
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
	<script type="text/javascript" src="${ctx}/pages/drp/store/allocate/Allocate_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		window.onload=function(){
			var ALLOC_OUT_CHANN_ID=$("#ALLOC_OUT_CHANN_ID").val();
			if(ALLOC_OUT_CHANN_ID==null){
				return;
			}else{
				$("#selectParam").val(" STATE='启用' and DEL_FLAG='0' and TERM_STROE_FLAG=0 and BEL_CHANN_ID='"+ALLOC_OUT_CHANN_ID+"'");
			}
		}
	</script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：库存管理&gt;&gt;调拨管理&gt;&gt;调拨单维护编辑</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
                     <input type="hidden" id="selectParams" name="selectParams"  value="STATE='启用' and DEL_FLAG='0' and CHANN_ID != '${channId}' and IS_BASE_FLAG=0">
                     <input type="hidden" id="selectParam" name="selectParam" value="">
               <tr>
                   <td width="20%" align="right" class="detail_label">调拨单编号：</td>
				   <td width="35%" class="detail_content">
                     <input json="ALLOCATE_NO" name="ALLOCATE_NO" autocheck="true" label="调拨单编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="32"
                     <c:if test="${rst.ALLOCATE_NO!=null}">value="${rst.ALLOCATE_NO}"</c:if>
					 <c:if test="${rst.ALLOCATE_NO==null}">value="自动生成"</c:if>
                     /> 
				   </td>
                    <td width="20%" align="right" class="detail_label">单据类型：</td>
					<td width="35%" class="detail_content">
                         <select json="BILL_TYPE" id="BILL_TYPE"  name="BILL_TYPE" style="width:150px" inputtype="string"   mustinput="true"  ></select>
					</td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">调出方编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="ALLOC_OUT_CHANN_ID" id="ALLOC_OUT_CHANN_ID" name="ALLOC_OUT_CHANN_ID" autocheck="true" label="调出方ID" type="hidden" inputtype="string"   value="${rst.ALLOC_OUT_CHANN_ID}"/> 
                     <input json="ALLOC_OUT_CHANN_NO" name="ALLOC_OUT_CHANN_NO" autocheck="true" label="调出方编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.ALLOC_OUT_CHANN_NO}"/>
				   </td>
                   <td width="15%" align="right" class="detail_label">调出方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="ALLOC_OUT_CHANN_NAME" name="ALLOC_OUT_CHANN_NAME" autocheck="true" label="调出方名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.ALLOC_OUT_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">调入方编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="ALLOC_IN_CHANN_ID" name="ALLOC_IN_CHANN_ID" autocheck="true" label="调入方ID" type="hidden" inputtype="string"   value="${rst.ALLOC_IN_CHANN_ID}"/> 
                     <input json="ALLOC_IN_CHANN_NO" name="ALLOC_IN_CHANN_NO" autocheck="true" label="调入方编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.ALLOC_IN_CHANN_NO}"/>
                     <img align="absmiddle"  style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" 
                     onClick="selCommon('BS_19', false, 'ALLOC_IN_CHANN_ID', 'CHANN_ID', 'forms[0]','ALLOC_IN_CHANN_ID,ALLOC_IN_CHANN_NO,ALLOC_IN_CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams')"> 
								
				   </td>
                   <td width="15%" align="right" class="detail_label">调入方名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="ALLOC_IN_CHANN_NAME" name="ALLOC_IN_CHANN_NAME" autocheck="true" label="调入方名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.ALLOC_IN_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">调出库房编号：</td>
				   <td width="35%" class="detail_content">
				   	 <input json="ALLOC_OUT_STORE_ID" name="ALLOC_OUT_STORE_ID" id="ALLOC_OUT_STORE_ID" autocheck="true" label="调出方库房ID" type="hidden" inputtype="string"   value="${rst.ALLOC_OUT_STORE_ID}"/>
                     <input json="ALLOC_OUT_STORE_NO" name="ALLOC_OUT_STORE_NO" id="ALLOC_OUT_STORE_NO" autocheck="true" label="调出库房编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="${rst.ALLOC_OUT_STORE_NO}"/>
                     <img align="absmiddle"  style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
								onClick="selOUT_STORE()">  
				   </td>
                   <td width="15%" align="right" class="detail_label">调出库房名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="ALLOC_OUT_STORE_NAME" name="ALLOC_OUT_STORE_NAME" id="ALLOC_OUT_STORE_NAME" autocheck="true" label="调出库房名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="${rst.ALLOC_OUT_STORE_NAME}"/> 
				   </td>
               </tr><%--
               <tr>
                   <td width="15%" align="right" class="detail_label">库位管理标记：</td>
				   <td width="35%" class="detail_content">
				   	 <input id="STORAGE_FLAG" json="STORAGE_FLAG"  name="STORAGE_FLAG" type="hidden" value="${rst.STORAGE_FLAG}" onpropertychange="acquireTab()">
                     <input id="STORAGE_FLAG_YES" name="STORAGE_FLAG_TAB"  type="radio"  disabled="disabled" <c:if test="${rst.STORAGE_FLAG==1 }">checked="checked"</c:if> />是
                     <input id="STORAGE_FLAG_NO" name="STORAGE_FLAG_TAB"  type="radio" disabled="disabled" <c:if test="${rst.STORAGE_FLAG==0 }">checked="checked"</c:if> />否
					 <!-- 0宽度input标签，用来显示单选按钮后的必选星号 -->
					 <input mustinput="true" inputtype="string" name="tab"  style="width: 0px;"  type="text" >  
				   </td>
				   <td width="15%" align="right" class="detail_label"></td>
				   <td width="35%" class="detail_content">
				   </td>
               </tr>
               --%><tr>
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
	   SelDictShow("BILL_TYPE","BS_32","${rst.BILL_TYPE}","");
	   function acquireTab(){
	   		var STORAGE_FLAG=document.getElementById("STORAGE_FLAG").value;
	   		if(STORAGE_FLAG==1){
	   			document.getElementById("STORAGE_FLAG_YES").checked=true;
	   		}else{
	   			document.getElementById("STORAGE_FLAG_NO").checked=true;
	   		}
	   }
</script>
 