/**
 *@module 渠道管理-终端管理
 *@func   新开门店申请单维护
 *@version 1.1
 *@author zcx
 */

$(function() {
	//维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add","delete","modify","commit", "revoke"]);
	var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("openterminal.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("openterminal.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("openterminal.shtml?action=delete", "OPEN_TERMINAL_REQ_ID");
	
	$("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}else{
		  $.ajax({
			url: "openterminal.shtml?action=IsCommit",
			type:"POST",
			dataType:"text",
			data:{"OPEN_TERMINAL_REQ_ID":selRowId},
			complete: function (xhr){
			   eval("jsonResult = "+xhr.responseText);
						if(jsonResult.success===true){
						     var result = jsonResult.data;
						     var count = result.count;
						     if(count==0){
						          parent.showErrorMsg("请先完善评估表信息!");
						          return;
						     } else{
						          toFlow("1");
						     }
						}else{
							parent.showErrorMsg(utf8Decode(jsonResult.messages));
						}
			}
		 }); 
		}
	});
	
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		//toFlow("3");
		parent.showConfirm("您确认撤销该条信息吗?","topcontent.toFlow(3);","N");
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
      btnDisable(["modify","delete","commit","revoke","audit","flow"]);
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
		url: "openterminal.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"OPEN_TERMINAL_REQ_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
                 toFlow("1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
 }
 
 function toAudit(){
     var selRowId = $("#selRowId").val();
     $.ajax({
		url: "openterminal.shtml?action=toAudit&OPEN_TERMINAL_REQ_ID="+selRowId,
		type:"POST",
		dataType:"text",
		data:{"OPEN_TERMINAL_REQ_ID":selRowId},
		complete: function (xhr){
		}
	 }); 
 }

 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/openterminal.shtml?action=toList"+document.getElementById("paramUrl").value ;
	switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		toAudit();
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}

 //删除记录
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "openterminal.shtml?action=delete&OPEN_TERMINAL_REQ_ID="+selRowId,
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
