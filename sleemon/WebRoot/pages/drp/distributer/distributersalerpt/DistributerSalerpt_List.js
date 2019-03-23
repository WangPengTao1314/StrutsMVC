/**
 *@module 渠道管理-分销商管理
 *@function   分销商购货月报
 *@version 1.1
 *@author gu_hx
 */

$(function() {
    
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add","modify","delete", "commit", "revoke"]);
	//主从及主从从列表页面通用加载方法
	listPageInit("distributerSalerpt.shtml?action=toList");
	//新增和修改按钮初始化
	//addModiToEditFrameInit("distributerReq.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("distributerSalerpt.shtml?action=delete", "DISTRIBUTOR_SALE_RPT_ID");
	
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
		//toCommit("revoke");		
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
	
	$("#expdata").click(function(){
		 $("#queryForm").attr("action","distributerSalerpt.shtml?action=toExpData");
		 $("#queryForm").submit();
	});
});

function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/distributerSalerpt.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
		btnDisable(["revoke"]);
	}
		
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit"]);
	}
}

//提交/撤销
function toCommit(state){
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "distributerSalerpt.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"DISTRIBUTOR_SALE_RPT_ID":selRowId,"stateFlag":state},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				$("#pageForm").submit();				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});	
}

function showEditPage(selRowId){
	window.showModalDialog("distributerSalerpt.shtml?action=toEditFrame&selRowId="+selRowId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
}

function showDetailPage(){
	var selRowId = $("#selRowId").val();
	window.showModalDialog("distributerSalerpt.shtml?action=toDetailFrame&selRowId="+selRowId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
}
