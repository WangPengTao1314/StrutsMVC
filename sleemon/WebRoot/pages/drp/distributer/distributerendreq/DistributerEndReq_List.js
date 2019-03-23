/**
 *@module 渠道管理-分销商管理
 *@function   加盟商终止合作申请
 *@version 1.1
 *@author gu_hx
 */

$(function() {
	//维护页面需要隐藏的按钮
	whBtnHidden(["audit","confirm"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add","delete","modify","commit", "revoke"]);
	var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("distributerEndReq.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	//addModiToEditFrameInit("distributerReq.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("distributerEndReq.shtml?action=delete", "CHANN_END_REQ_ID");
	
	$("#add").click(function(){
		showEditPage("");
	});
	
	$("#modify").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		showEditPage(selRowId);
	});
	
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
		toAudit(selRowId);
	});
	$("#flow").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("4");
	}); 
	
	$("#confirm").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		showEditPage(selRowId);
	});
	
});

function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/distributerEndReq.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit","confirm"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit"]);
	}
	
	//当状态=撤销
	if (state == "\u64a4\u9500") {
		btnDisable(["revoke","audit","confirm"]);
	}
	
	//当状态=否决
	if (state == "\u5426\u51b3") {
		btnDisable(["revoke","audit","confirm"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete","modify","revoke","commit","audit","confirm"]);
	}	
}

//提交审核
function toAudit(selRowId){
	
	$.ajax({
		url: "distributerEndReq.shtml?action=toAudit",
		type:"POST",
		dataType:"text",
		data:{"CHANN_END_REQ_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 toFlow("2");
			}else{			
				parent.showConfirm(utf8Decode(jsonResult.messages),"topcontent.showEditPage('"+selRowId+"');");
			}
		}
	});
}

function showEditPage(selRowId){	
	var module = $("#module",parent.document).val();
	var returnRst = window.showModalDialog("distributerEndReq.shtml?action=toEditFrame&module=" + module+"&selRowId="+selRowId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
	
	if(null != returnRst){
		parent.closeWindow();
		
		if(2==returnRst){
			toFlow("2");
		}		
	}
	
	return true;
}

function showDetailPage(){
	var selRowId = $("#selRowId").val();
	window.showModalDialog("distributerEndReq.shtml?action=toDetailFrame&selRowId="+selRowId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
}
