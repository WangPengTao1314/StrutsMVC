﻿<!--  
/**
 * @module 系统管理
 * @func 货品信息-特殊工艺维护-部件替换
 * @version 1.1
 * @author 黄如
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
	<title>部件替换</title>
</head>
<body class="bodycss1">
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
<td>
	<div>
	    <form id="replaceForm">
	     <input type="hidden" name="selectParam" id="selectParam" value="" />
		<table width="100%" border="0"  >
		<tr>
		   	<td nowrap colspan=4 align="left"><h3>部件替换</h3></td>
		</tr>
		</table>
		<br>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
			<input type="hidden" id="PRD_SPCL_TECH_ID"  name="PRD_SPCL_TECH_ID" value="${rst.PRD_SPCL_TECH_ID}"/>
			<input type="hidden" id="PRD_ID" name ="PRD_ID" value="${PRD_ID}"/>
			<tr >
              <td width="25%" align="right" class="detail_label">工艺名称:</td>
              <td width="75%" colspan=3 class="detail_content"><input json="SPCL_TECH_NAME" label="工艺名称" id="SPCL_TECH_NAME" name ="SPCL_TECH_NAME" value="${rst.SPCL_TECH_NAME}" inputtype="string" maxlength="100"  autocheck="true"/></td>
            </tr> 
            <%--<tr >
              <td width="25%" align="right" class="detail_label">部件替换类型:</td>
              <td width="75%" colspan=3 class="detail_content">
                <select json="TURN_TYPE" label="部件替换类型" id="TURN_TYPE" 
			      name="TURN_TYPE" style="width:152"  inputtype="string" autocheck="true" onchange="changeParams(this);"></select>
              </td>
            </tr>   
            --%><tr> 
              <td width="15%" align="right" class="detail_label">原部件:</td>
              <td width="30%" align="left" class="detail_content"><input json="CURRT_VAL" label="原部件" id="CURRT_VAL" name ="CURRT_VAL" value="${rst.CURRT_VAL}" inputtype="string" maxlength="100"   autocheck="true"/></td>
              <td width="20%" align="right" class="detail_label">替换为:</td>
              <td width="35%" align="left" class="detail_content">
                <input type="hidden" name="PARTS_ID" id="PARTS_ID"/>
                <input json="TUENED_VALS" label="替换为" id="TUENED_VALS" name ="TUENED_VALS" value="${rst.TUENED_VALS}" inputtype="string" maxlength="3000"  autocheck="true"/>
				<img align="absmiddle" name="selBJ" id="selBJ" style="cursor: hand"	src="${ctx}/styles/${theme}/images/plus/select.gif"
					onclick="selCommon('BS_73', false, 'PARTS_ID', 'PARTS_ID', 'forms[0]','TUENED_VALS','PARTS_NAME','selectParam')">
                </td>
            </tr>
             <tr> 
              <td width="15%" align="right" class="detail_label">调价类型:</td>
              <td width="30%" align="left" class="detail_content"><select json="PRICE_TURN_TYPE" label="调价类型" id="PRICE_TURN_TYPE" name="PRICE_TURN_TYPE" style="width:152" inputtype="string"   autocheck="true"></select></td>
              <td width="20%" align="right" class="detail_label">调价值:</td>
              <td width="35%" align="left" class="detail_content"><input json="PRICE_TURN_VAL" label="调价值" id="PRICE_TURN_VAL" name ="PRICE_TURN_VAL" value="${rst.PRICE_TURN_VAL}" inputtype="float" autocheck="true" maxlength="10"/></td>
            </tr>
		</table>
		<br><br><br>
		<table width="100%" border="0">
		<tr>
		   	<td nowrap colspan=4 align="center"> 
			   <input id="saveadd" type="button" class="btn" value="保存" onclick="savePrd();" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   <input type="button" class="btn" value="返回"  onclick="javascript:window.close();">
			</td>
		</tr>
		</table>
		</form>
	</div>
</td>
</tr>
</table>
</body>
<script type="text/javascript">
	 SelDictShow("PRICE_TURN_TYPE", "BS_48", "${rst.PRICE_TURN_TYPE}", "");
	 SelDictShow("TURN_TYPE", "BS_72", "${rst.TURN_TYPE}", "");
	 /**
	 var v = $("#TURN_TYPE").val();
	 if(null == v || "" == v){
		 v = "${rst.TURN_TYPE}";
		 if(null == v || "" == v){
			 $("#selBJ").attr("disabled","true");
		     $("#TUENED_VALS").attr("disabled","true");
		 }
		 
	 }**/
	 
	 function savePrd(){
	 	if(!formChecked("replaceForm")){
			return;
		}
	 	var PRD_ID = $("#PRD_ID").val();
	 	var PRD_SPCL_TECH_ID = $("#PRD_SPCL_TECH_ID").val();
	 	var PRICE_TURN_TYPE = $("#PRICE_TURN_TYPE").val();
		if(PRICE_TURN_TYPE==null || PRICE_TURN_TYPE == ""){
			//parent.showErrorMsg(utf8Decode("请选择单据类型!"));
	        //return false;
		}
	 	var jsonData =siglePackJsonData();
		$.ajax({
			url: "product.shtml?action=toPrdEdit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"SPCL_TECH_TYPE":"部件替换","PRD_ID":PRD_ID,"PRD_SPCL_TECH_ID":PRD_SPCL_TECH_ID},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					window.dialogArguments.parent.showMsgPanel("保存成功");
					window.dialogArguments.parent.window.gotoBottomPage("label_2");
					window.close();
				}else{
					window.dialogArguments.parent.showErrorMsg(utf8Decode(jsonResult.messages));
					window.close();
				}
			}
	 	});
	 }
	 
	 function changeParams(obj){
		 var v = $(obj).val();
		 if(null != v && "" != v){
			 $("#selBJ").removeAttr("disabled");
			 $("#TUENED_VALS").removeAttr("disabled");
		 }else{
			  $("#selBJ").attr("disabled","true");
			  $("#TUENED_VALS").attr("disabled","true");
		 }
		 v = " PARTS_TYPE='"+v+"' ";
		 $("#selectParam").val(v);
		 $("#TUENED_VALS").val("");
		 $("#PARTS_NAME").val("");
	 }
</script>
</html>
