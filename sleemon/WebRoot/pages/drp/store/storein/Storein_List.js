/**
 * @prjName:喜临门营销平台
 * @fileName:Storein_List
 * @author glw
 * @time   2013-08-19 16:55:43 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("storein.shtml?action=toList&module=" + module);
	// 处理
	$("#done").click(function(){
	  doneChecked();
	});
	$("#return").click(function(){
		parent.showConfirm("您确认要进行退回操作？点击确认则继续","topcontent.returnStorein();");
	});
	
	$("#expdate").click(function(){
		 $("#queryForm").attr("action","storein.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	});
	
	$("#backin").click(function(){
		parent.showConfirm("您确认要进行反入库操作？点击确认则继续","topcontent.backin();");
	});
	setSelOperateEx();
});


//记录按钮根据状态控制
 function setSelOperateEx(){
	var selRowId = parent.document.getElementById("selRowId").value;
	if(null == selRowId || "" == selRowId){
		btnDisable(["done","return","backin"]);
	}else{
		var vstate = parent.topcontent.document.getElementById("state" + selRowId);
		var state = vstate.value;
		//按钮状态重置
		btnReset();
		if(state == "未处理"){
			btnDisable(["backin"]);
		}
		if(state == "已处理"){
			btnDisable(["done","return"]);
		}
	}
 }
  
 function getState(STATE){
	 parent.window.document.getElementById("STATE").value = STATE;
 }
 
//处理
function listReceivedConfirm(){
	parent.showWaitPanel();
//	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "storein.shtml?action=toDone&STOREIN_ID="+utf8(selRowId),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("入库处理成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}

//处理前检查
function doneChecked() {
	showWaitPanel();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "storein.shtml?action=toCommit&STOREIN_ID="+utf8(selRowId),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				closeWindow();
				 parent.showConfirm("您确认要进行入库操作？点击确认则继续","topcontent.listReceivedConfirm();");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				closeWindow();
				return false;
			}
		}
 	});
}

function getBillType(){
	var selRowId = $("#selRowId").val();
	return $("#BILL_TYPE"+selRowId).val();
}
//退回
function returnStorein(){
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "storein.shtml?action=toReturn&STOREIN_ID="+utf8(selRowId),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("退回成功");
            	$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}

function backin(){
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "storein.shtml?action=backin",
		type:"POST",
		dataType:"text",
		data:{"STOREIN_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("反入库成功");
            	$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}