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
	listPageInit("member.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("member.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("member.shtml?action=delete", "MEMBER_ID");
	
    $("#start").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toStart(selRowId);
		
	});
    $("#stop").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toStop(selRowId);
		
	});
 
	
	
});

function getCHANN_ID(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#CHANN_ID"+selRowId).val();
}
 
 
 function toStart(selRowId){
	 //parent.showWaitPanel();
	 $.ajax({
		url: "member.shtml?action=toStart",
		type:"POST",
		dataType:"text",
		data:{"MEMBER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			   parent.showMsgPanel(utf8Decode(jsonResult.messages));
			   $("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
 }
 
function toStop(selRowId){
	$.ajax({
		url: "member.shtml?action=toStop",
		type:"POST",
		dataType:"text",
		data:{"MEMBER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    parent.showMsgPanel(utf8Decode(jsonResult.messages));
			    $("#pageForm").submit();
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
	 
    if (state == "启用") {
		btnDisable(["delete","modify","commit","start"]);
	}
    if (state == "停用") {
		btnDisable(["delete","commit","stop"]);
	}
}
 
  
