﻿<!--  
/**
 * @module 系统管理
 * @func 货品信息-特殊工艺维护-尺寸调整
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
	<title>尺寸调整</title>
</head>
<body class="bodycss1">
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
<td>
	<div>
	    <form id="resetForm">
		<table width="100%" border="0"  >
		<tr>
		   	<td nowrap colspan=4 align="left"><h3>尺寸调整</h3></td>
		</tr>
		</table>
		 
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
			<input type="hidden" id="PRD_SPCL_TECH_ID"  name="PRD_SPCL_TECH_ID" value="${rst.PRD_SPCL_TECH_ID}"/>
			<input type="hidden" id="PRD_ID" name ="PRD_ID" value="${PRD_ID}"/>
			<tr>
              <td width="35%" align="right" class="detail_label">工艺名称:</td>
              <td width="65%" colspan=3 class="detail_content"><input json="SPCL_TECH_NAME" label="工艺名称" id="SPCL_TECH_NAME" name ="SPCL_TECH_NAME" value="${rst.SPCL_TECH_NAME}" mustinput="true" inputtype="string" maxlength="100"  autocheck="true"/></td>
            </tr>
            <tr>
              <td width="20%" align="right" class="detail_label">原尺寸:</td>
              <td width="30%"   class="detail_content">
					<script type="text/javascript">
							document.write(window.dialogArguments.parent.topcontent.document.getElementById("spec"+document.getElementById("PRD_ID").value).value);
					</script>
			 </td>
			 <td width="20%" align="right" class="detail_label" colspan="2"></td>
			 <%--<td width="20%" align="right" class="detail_label">尺寸调整类型:</td>
			 <td width="30%" align="right" class="detail_content">
			   <select json="TURN_TYPE" label="尺寸调整类型" id="TURN_TYPE" 
			   name="TURN_TYPE" style="width:152"  inputtype="string" autocheck="true"></select>
			 </td>
            --%></tr>    
            <tr> 
              <td width="30%" align="right" class="detail_label">调整范围从:</td>
              <td width="20%" align="right" class="detail_content"><input json="CURRT_VAL_TURN_BEG" label="长度调整范围从" id="CURRT_VAL_TURN_BEG" name ="CURRT_VAL_TURN_BEG" value="${rst.CURRT_VAL_TURN_BEG}" type="text" inputtype="int"   maxlength="10" autocheck="true"/></td>
              <td width="20%" align="right" class="detail_label">调整范围到:</td>
              <td width="30%" align="right" class="detail_content"><input json="CURRT_VAL_TURN_END" label="长度调整范围至" id="CURRT_VAL_TURN_END" name ="CURRT_VAL_TURN_END" value="${rst.CURRT_VAL_TURN_END}" type="text" inputtype="int"    maxlength="10" autocheck="true"/></td>
            </tr>
            
            <tr> 
              <td width="30%" align="right" class="detail_label" >调整说明:</td>
              <td width="70%" align="left" class="detail_content" colspan="3">
              	<textarea onpropertychange="changeTextArea(document.getElementById('REMARK'));" rows="2"  json="REMARK" label="高度填充物说明"
              		 id="REMARK " name ="REMARK " inputtype="string"   maxlength="250" autocheck="true" cols="40%"/>${rst.REMARK}</textarea></td>
            </tr>
             <tr> 
              <td width="30%" align="right" class="detail_label">调价类型:</td>
              <td width="20%" align="right" class="detail_content"><select json="PRICE_TURN_TYPE" label="调价类型" id="PRICE_TURN_TYPE" name="PRICE_TURN_TYPE" style="width:152"  inputtype="string" autocheck="true"></select></td>
              <td width="20%" align="right" class="detail_label">调价值:</td>
              <td width="30%" align="right" class="detail_content"><input json="PRICE_TURN_VAL"  label="调价值" id="PRICE_TURN_VAL" name ="PRICE_TURN_VAL" value="${rst.PRICE_TURN_VAL}" inputtype="float" autocheck="true" maxlength="10"/></td>
            </tr>
            
		</table>
		<br>
		<table width="100%" border="0">
		<tr>
		   	<td nowrap colspan=4 align="center"> 
			   <input id="saveadd" type="button" class="btn" value="保存" onclick="savePrd();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   <input type="button" class="btn" value="返回"  onclick="javascript:window.close();"/>
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
	 SelDictShow("PRICE_TURN_TYPE", "BS_71", "${rst.PRICE_TURN_TYPE}", "");
	 
	 SelDictShow("TURN_TYPE", "BS_70", "${rst.TURN_TYPE}", "");
	 function savePrd(){
	 	if(!formChecked("resetForm")){
			return;
		}
	 	var PRD_ID = $("#PRD_ID").val();
	 	var PRD_SPCL_TECH_ID = $("#PRD_SPCL_TECH_ID").val();
	 	var PRICE_TURN_TYPE = $("#PRICE_TURN_TYPE").val();
		if(PRICE_TURN_TYPE==null || PRICE_TURN_TYPE == ""){
			//parent.showErrorMsg(utf8Decode("请选择单据类型!"));
	        //return false;
		}
		var CURRT_VAL_TURN_BEG = $.trim($("#CURRT_VAL_TURN_BEG").val());
		var CURRT_VAL_TURN_END = $.trim($("#CURRT_VAL_TURN_END").val());
		
		if(CURRT_VAL_TURN_BEG!="" && CURRT_VAL_TURN_END!=""){
			if(parseInt(CURRT_VAL_TURN_BEG) >= parseInt(CURRT_VAL_TURN_END)){
				parent.showErrorMsg(utf8Decode("调整范围不正确!"));
		        return false;
			}
		}
		
	 	var jsonData =siglePackJsonData();
	 	//return;
			 $.ajax({
				url: "product.shtml?action=toPrdEdit",
				type:"POST",
				dataType:"text",
				data:{"jsonData":jsonData,"SPCL_TECH_TYPE":"尺寸调整","PRD_ID":PRD_ID,"PRD_SPCL_TECH_ID":PRD_SPCL_TECH_ID},
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
	 
	 
</script>

</html>
