/**
 * @prjName:喜临门营销平台
 * @fileName:Prmtplan_List
 * @author zzb
 * @time   2013-08-23 16:04:28 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit","issuance","over"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke","issuance","over"]);
	//发布页面隐藏按钮
	issBtnHidden(["add", "modify", "delete", "commit", "revoke","audit","flow"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("prmtplan.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("prmtplan.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("prmtplan.shtml?action=delete", "PRMT_PLAN_ID");
	
	 $("#issuance").click(function () {
		parent.showConfirm("确定发布吗?","topcontent.issOrOver('');");
	 });
	 $("#over").click(function () {
		parent.showConfirm("确定活动终止吗?","topcontent.issOrOver('over');");
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


//发布按钮控制
function issBtnHidden(arrys){
	var flag = getFlag();
	if("PRMT"===flag){
		btnHidden(arrys);
		$("#btntr").css("display","none");
	}
}
//获取模块名称
function getFlag(){
	return parent.$("#FLAG").val();
}
//发布 活动终止按钮事件
function issOrOver(tag){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		
	   $.ajax({
		url: "prmtplan.shtml?action=toIssuance&tag="+tag,
		type:"POST",
		dataType:"text",
		data:{"PRMT_PLAN_ID":selRowId },
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
 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/prmtplan.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
		btnDisable(["revoke","audit","issuance","over"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit","issuance","over"]);
	}
	
	//当状态=撤销
	if (state == "\u64a4\u9500") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "\u5426\u51b3") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete","modify","revoke","commit","audit","over"]);
	}
	
	//当状态=已发布
	if (state == "已发布") {
		btnDisable(["delete","modify","revoke","commit","audit","issuance"]);
	}
	//当状态=已终止
	if (state == "已终止") {
		btnDisable(["delete","modify","revoke","commit","audit","over"]);
	}
}
