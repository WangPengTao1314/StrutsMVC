/**
 *@module 渠道管理-终端管理
 *@func   加盟商门店指标
 *@version 1.1
 *@author zcx
 */

$(function() {

	//维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add","delete", "commit", "revoke"]);
	//主从及主从从列表页面通用加载方法
	listPageInit("channTermianl.shtml?action=toList");
	
	$("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
	   toFlow("1");
	});
	
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("3");
	});
	$("#audit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("2");
	});
	$("#flow").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("4");
	}); 
	
	
	$("#add").click(function(){
		setCommonPageInfo(false);
		parent.window.gotoBottomPage("toEdit");
	});
	
	 
    $("#modifyT").click(function(){
		 var selRowId = $("#selRowId").val();
	 	 if(null == selRowId || "" == selRowId){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
	 	 	setCommonPageInfo(true);
			parent.window.gotoBottomPage("toEdit1");
	 	 }
	});
	
	$("#modify").click(function(){
		 var selRowId = $("#selRowId").val();
	 	 if(null == selRowId || "" == selRowId){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
	 	 	setCommonPageInfo(true);
			parent.window.gotoBottomPage("toEdit");
	 	 }
	});
	InitFormValidator(0);
	
    var chiState = $.trim($("#ordertb  :input[type='radio'][checked='true']").parent().parent().find("td").eq(5).text());
	
    $("#submit").click(function(){
         var selRowId = $("#selRowId").val();
	 	 if(null == selRowId || "" == selRowId){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
	 	 	setCommonPageInfo(true);
			parent.window.gotoBottomPage("toSubmit");
			parent.showMsgPanel("您已成功提交");
			$("#pageForm").submit();
	 	 } 
    });
	
	$("#delete").click(function(){
	     var selRowId = $("#selRowId").val();
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("您确认删除该条信息吗?","topcontent.listDelConfirm();");
	 });
	//state();
	
	 
    var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
      btnDisable(["modify","modifyT","delete","commit","revoke","audit","flow"]);
      return;
    } 
})
 
 function state(params){
    var state = params.value;
	var PVG_EDIT_AUDIT = $("#PVG_EDIT_AUDIT").val();
    //按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "未提交") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "提交") {
		if(1 == PVG_EDIT_AUDIT){
			btnDisable(["delete","commit"]);
		}else{
			btnDisable(["delete","modifyT","commit"]);
		}
	}
	
	//当状态=撤销
	if (state == "撤销") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "否决") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "审核通过") {
		btnDisable(["delete","modifyT","modify","revoke","commit","audit"]);
	} 
 }

 function toCommit(selRowId){
	 $.ajax({
		url: "decorationapp.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"CHANN_RNVTN_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 parent.showMsgPanel("提交成功");
                 pageForm.submit();
                 //toFlow("1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
 }
 
 function audit(){
    
   var selRowId = $("#CHANN_RNVTN_ID").val();
   $.ajax({
		url: "decorationapp.shtml?action=toAudit",
		type:"POST",
		dataType:"text",
		data:{"CHANN_RNVTN_ID":selRowId},
		complete: function (xhr){
		}
	 });
 }

 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/decorationapp.shtml?action=toList"+document.getElementById("paramUrl").value ;
	switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}
 
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var state = $.trim($(obj).find("td[json='STATE']").text());
	//设置按钮的状态
	setBtnState(state);
}
//设置按钮的状态
function setBtnState(state) {
	//按钮状态重置
	btnReset();
	if (state == "启用") {
		//启用，删除，修改按钮不可用
		btnDisable( [ "modify" ]);
		btnDisable( [ "delete" ]);
		btnDisable( [ "start" ]);
	} else if (state == "停用") {
		//停用,删除按钮不可用
		btnDisable( [ "sp" ]);
		btnDisable( [ "delete" ]);
	} else if (state == "制定") {
		// 停用按钮不可用
		btnDisable( [ "stop" ]);
	}
}

function getYear(){
   var YEAR = $("#YEAR").val();
   qryformChecked('queryForm');
   parent.document.getElementById("selRowId").value="";
   $("#selRowId").val("");
   var url = "channTermianl.shtml?action=toListT";
   $("#queryForm").attr("action",url+"&search=true&module="+getModule()+"&YEAR="+YEAR);
   $("#queryForm").submit();
   //listPageInit("saleplan.shtml?action=toList&module=" + module+"&PLAN_YEAR="+PLAN_YEAR);
}

 //删除记录
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "channTermianl.shtml?action=delete&TERMINAL_QUOTA_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				$("#pageForm").submit();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
