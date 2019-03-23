/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_List
 * @author glw
 * @time   2013-08-17 17:07:03 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
		//主从及主从从列表页面通用加载方法
	listPageInit("tstoreIn.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("tstoreIn.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("tstoreIn.shtml?action=delete", "STOREIN_NOTICE_ID");
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		showWaitPanel();
		$.ajax({
			url: "tstoreIn.shtml?action=toCommit",
			type:"POST",
			dataType:"text",
			data:{"STOREIN_NOTICE_ID":selRowId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					toFlow("1");
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
					closeWindow();
				}
			}
		});
		
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
});
 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/tstoreIn.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
	//当状态=未提交 	1
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit"]);
	}
	
	//当状态=撤销	2
	if (state == "\u64a4\u9500") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决	3
	if (state == "\u5426\u51b3") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
}

//点击的时候设置 按钮是否置灰
function setUpButtonDis(billtype){
	if (billtype != "手工新增") {
		btnDisable(["delete"]);
	}
}


function getBIll_TYPE(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#billtype"+selRowId).val();
}

function getFROM_BILL_ID(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#FROM_BILL_ID"+selRowId).val();
}
function getFROM_BILL_NO(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#FROM_BILL_NO"+selRowId).val();
}
function getFROM_TYPE(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#FROM_TYPE"+selRowId).val();
}

//默认库房ID
function getDEF_STORE_ID(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#DEF_STORE_ID"+selRowId).val();
}
//默认库房编号
function getDEF_STORE_NO(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#DEF_STORE_NO"+selRowId).val();
}
//默认库房名称
function getDEF_STORE_NAME(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#DEF_STORE_NAME"+selRowId).val();
}
function getRECEIVE_CHANN_ID(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#RECV_CHANN_ID"+selRowId).val();
}
