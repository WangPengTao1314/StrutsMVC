/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time  2014-12-03 10:35:10 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	 
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("marketact.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("marketact.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("marketact.shtml?action=delete", "MARKETING_ACT_ID");
	
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toCommit(selRowId);
		
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
    document.affirm.sourceURI.value=ctxPath+"/marketact.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
 
 
 function toCommit(selRowId){
	 //parent.showWaitPanel();
	 $.ajax({
		url: "marketact.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"MARKETING_ACT_ID":selRowId},
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
 
 
 
 
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
  
	//当状态=未提交
	if (state == "未提交") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "提交") {
		btnDisable(["delete","modify","commit"]);
	}
	
	//当状态=撤销
	if (state == "撤销") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "否决") {
		btnDisable(["revoke","audit"]);
	}
 
	//当状态=审核通过
	if (state == "审核通过") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
	 
 
}
 
  
