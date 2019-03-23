﻿<!--  
/**
 * @module 系统管理
 * @func 发运方式
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/repairsend/Repairsend_Type.js"></script>
	<title>发运方式</title>
</head>
<body class="bodycss1">
<table width="99.8%" height="95%" border="0" cellSpacing=0 cellPadding=0>
<tr>
<td>
	<div>
	    <form id="addForm">
	    
		<table width="100%" border="0">
		<tr>
		   	<td nowrap colspan=4 align="left"><h3></h3></td>
		</tr>
		</table>
		<br>
		<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail_lst">
			<input type="hidden" id="ERP_REPAIR_ORDER_ID" name="ERP_REPAIR_ORDER_ID" value="${ERP_REPAIR_ORDER_ID}"/>
             <tr> 
              <td width="15%"  align="right" class="detail_label">发运方式:</td>
              <td width="35%"  align="left" class="detail_content">
              	<select id="DELIVER_TYPE" name="DELIVER_TYPE" style="width:152" inputtype="string" mustinput="true"  autocheck="true" onchange=""></select>
              </td><%--
              <td width="18%" align="right" class="detail_label">发货类型：</td>
			  <td width="35%" class="detail_content">
                     <select id="CHANN_TYPE" name="CHANN_TYPE" style="width: 152px;" mustinput="true" inputtype="string" autocheck="true"></select>
		       </td>
               
               --%></tr>
		</table>
		<br><br><br>
		<table width="100%" border="0">
		<tr>
		   	<td nowrap colspan=4 align="center"> 
			   <input id="saveadd" type="button" class="btn" value="提交" onclick="savePrd();" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
	SelDictShow("CHANN_TYPE","BS_58","${rst.CHANN_TYPE}","");
	SelDictShow("DELIVER_TYPE","BS_54","${rst.DELIVER_TYPE}","");
	function savePrd(){
		var ERP_REPAIR_ORDER_ID = document.getElementById("ERP_REPAIR_ORDER_ID").value;
		if (ERP_REPAIR_ORDER_ID == null || "" == ERP_REPAIR_ORDER_ID) {
			return;
		}
		if(!formChecked("addForm")){
			return;
		}
		var DELIVER_TYPE = $("#DELIVER_TYPE").val();
		var CHANN_TYPE = $("#CHANN_TYPE").val();
		if(DELIVER_TYPE==null || DELIVER_TYPE == ""){
			parent.showErrorMsg(utf8Decode("请选择发运方式!"));
	        return false;
		}
		/**
		if("整车发运" == DELIVER_TYPE){
			if(CHANN_TYPE==null || CHANN_TYPE == ""){
			   parent.showErrorMsg(utf8Decode("请选择发货类型!"));
	           return false;
		    }
		}**/
		
	    $.ajax({
			url: "repairsend.shtml?action=toCommit&ERP_REPAIR_ORDER_ID="+ERP_REPAIR_ORDER_ID,
			type:"POST",
			dataType:"text",
			data:{"DELIVER_TYPE":DELIVER_TYPE,"CHANN_TYPE":CHANN_TYPE,"type":"commSend"},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					window.dialogArguments.saveSuccess("提交排车成功!","repairsend.shtml?action=toFrame");
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
