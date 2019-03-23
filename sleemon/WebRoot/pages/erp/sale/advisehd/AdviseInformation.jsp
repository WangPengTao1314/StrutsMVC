﻿<!--  
/**
 * @module  营销管理
 * @func 处理信息填写
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
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	 
	<title>处理信息填写</title>
</head>
<body class="bodycss1">
	<form>
	    <input type="hidden" name="selParams" id="selParams" value=" is_drp_ledger = 0 "/>
		<table width="100%" border="0" cellpadding="4" cellspacing="4" >
		<tr><td align="center" class="detail2" colspan=6><h3>处理信息填写</h3></td></tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" class="">
			<tr>
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
						<tr>
							<td width="20%" align="right"class="detail_label">当前处理人：</td>
							<td width="20%" class="detail_content">
								<input type="hidden" id="DEAL_PSON_ID" name="DEAL_PSON_ID" value="${userId}" readonly class="readonly"/>
								<input type="text" id="DEAL_PSON_NAME" name="DEAL_PSON_NAME" value="${userName}" readonly class="readonly"/>
								 
							</td>
						 
							<td width="10%" align="right" class="detail_label" >处理时间：</td>
							<td width="50%" class="detail_content" colspan="3">
						   	 <input json="DEAL_TIME"  id="DEAL_TIME" name="DEAL_TIME" readonly="readonly" mustinput="true" inputtype="string"  style="width:155" value="${nowTime}">
						   </td>
						 
							 
						</tr>
						<tr>
						 
							<td width="15%" align="right" class="detail_label">处理意见：</td>
							<td width="85%" class="detail_content" colspan="5">
								<textarea onpropertychange="if($.trim(value).length>1000) value=value.substr(0,1000)" rows="5"  json="FEEDBACK_INFO" label="处理意见" id="FEEDBACK_INFO"
									 name ="FEEDBACK_INFO" inputtype="string"    autocheck="true" cols="67%"/></textarea>
							</td>
						</tr>
						
						<c:if test="${pvg.PVG_DEAL eq 1}">
						<tr>
							<td width="15%" align="right"class="detail_label">指派处理人：</td>
							<td width="20%" class="detail_content" colspan="5">
								<input type="hidden" id="APPOINT_PSON_ID" name="APPOINT_PSON_ID" value="" readonly class="readonly"  mustinput="true" inputtype="string"/>
								<input type="text" id="APPOINT_PSON_NAME" name="APPOINT_PSON_NAME" value="" readonly class="readonly"  mustinput="true" inputtype="string"/>
								<img align="absmiddle" name="selAREA" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" 
								onClick="selcomm()">
							</td>
						</tr>
						</c:if>
					</table>
					<input type="hidden" id="CMPL_ADVS_ID" name="CMPL_ADVS_ID" value="${CMPL_ADVS_ID}">
				</td>
			</tr>
		</table>
		<br><br><br>
		<table width="100%" border="0" cellpadding="4" cellspacing="4">
			<tr>
				<td align="center" colspan="6" >
					<input type="button" id="appointSave" name="save" value="指派处理人" class="btn" style="height=23px;width=80px;" onclick="appoint()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 
					<input type="button" id="dealSave" name="save" 
					    value="处理完成" class="btn" style="height=23px;width=80px;"  <c:if test="${pvg.PVG_EDIT ne 1}"> disabled ="disabled"</c:if>
					    onclick="done()" >
					    
					    
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 
					<input type="button" id="cancel" name="cancel" value="关闭" class="btn" onclick="window.close()" style="height=23px;width=80px;" >&nbsp;
					
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	$("#appointSave").attr("disabled","disabled");

 
	function selcomm(){
		selCommon('BS_67',false,'APPOINT_PSON_ID','XTYHID','forms[0]','APPOINT_PSON_NAME','XM','selParams');
		var appValue = $("#APPOINT_PSON_ID").val();
		 
		if($.trim(appValue)!=null && $.trim(appValue)!=""){
			$("#appointSave").removeAttr("disabled");
		}else{
			$("#appointSave").attr("disabled","disabled");
		}
	}
	
	//指派处理人
	function appoint(){
	
		var CMPL_ADVS_ID = $("#CMPL_ADVS_ID").val();
		var DEAL_PSON_ID = $("#DEAL_PSON_ID").val();
	 	var DEAL_PSON_NAME = $("#DEAL_PSON_NAME").val();
		var DEAL_TIME = $("#DEAL_TIME").val();
		var FEEDBACK_INFO = $("#FEEDBACK_INFO").val();
		var APPOINT_PSON_ID = $("#APPOINT_PSON_ID").val();
		var APPOINT_PSON_NAME = $("#APPOINT_PSON_NAME").val();
		//return;
		 $.ajax({
			url: "advisehd.shtml?action=toAppoint&CMPL_ADVS_ID="+CMPL_ADVS_ID+"&DEAL_PSON_ID="+DEAL_PSON_ID,
			type:"POST",
			dataType:"text",
			data:{"DEAL_PSON_NAME":DEAL_PSON_NAME,"APPOINT_PSON_ID":APPOINT_PSON_ID,"APPOINT_PSON_NAME":APPOINT_PSON_NAME,"FEEDBACK_INFO":FEEDBACK_INFO},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					//window.dialogArguments.parent.window.showMsgPanel("指派处理人成功!");
					window.dialogArguments.saveSuccess("指派处理人成功!","advisehd.shtml?action=toFrame");
					//window.dialogArguments.parent.bottomcontent.goFrame('advisehd.shtml?action=toFrame');
					//window.dialogArguments.parent.window.gotoBottomPage("label_4");
					window.close();
				}else{
					window.showErrorMsg(utf8Decode(jsonResult.messages));
					window.close();
				}
			}
	    });
	}
	
	//处理完成
	function done(){
	
		var CMPL_ADVS_ID = $("#CMPL_ADVS_ID").val();
		var DEAL_PSON_ID = $("#DEAL_PSON_ID").val();
	 	var DEAL_PSON_NAME = $("#DEAL_PSON_NAME").val();
		var DEAL_TIME = $("#DEAL_TIME").val();
		var FEEDBACK_INFO = $("#FEEDBACK_INFO").val();
		FEEDBACK_INFO = $.trim(FEEDBACK_INFO);
		if(null == FEEDBACK_INFO || "" == FEEDBACK_INFO){
			parent.showErrorMsg("请填写'处理意见'");
			return;
		}

		//return;
		 $.ajax({
			url: "advisehd.shtml?action=dealDone&CMPL_ADVS_ID="+CMPL_ADVS_ID,
			type:"POST",
			dataType:"text",
			data:{"DEAL_PSON_NAME":DEAL_PSON_NAME,"FEEDBACK_INFO":FEEDBACK_INFO,"DEAL_PSON_ID":DEAL_PSON_ID},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					var data = jsonResult.data;
					window.showMsgPanel("处理成功!");
					$("#YT_MSG_BTN_OK").click(function(){
					     window.dialogArguments.parent.window.gotoBottomPage("label_4");
					     window.dialogArguments.parent.window.topcontent.$("#DEAL_PSON_NAME"+CMPL_ADVS_ID).text(data.DEAL_PSON_NAME);
					     window.dialogArguments.parent.window.topcontent.$("#DEAL_TIME"+CMPL_ADVS_ID).text(data.DEAL_TIME);
					     window.dialogArguments.parent.window.topcontent.$("#"+CMPL_ADVS_ID).text("已反馈");
					     window.dialogArguments.parent.window.topcontent.$("#state"+CMPL_ADVS_ID).val("已反馈");
					     window.dialogArguments.parent.window.topcontent.$("#todeal").attr("disabled","disabled");
					     window.close();
					});
				}else{
					window.showErrorMsg(utf8Decode(jsonResult.messages));
					window.close();
				}
			}
	    });
	}
</script>
</html>
