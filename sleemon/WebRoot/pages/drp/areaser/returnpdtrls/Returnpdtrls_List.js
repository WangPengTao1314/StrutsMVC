/**
 * @prjName:喜临门营销平台
 * @fileName:prdreturnreq_List
 * @author wzg
 * @time   2013-08-15 10:17:13 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;
    if(module!="wh"){
    	$("#query").click(function(){
			$("#STATE option[text='未提交']").remove();
		});
    }
    var firstAudit = parent.window.document.getElementById("firstAudit").value;	
    
	//主从及主从从列表页面通用加载方法
	listPageInit("returnpdtrls.shtml?action=toList&module=" + module+"&firstAudit="+firstAudit);
	//新增和修改按钮初始化
	addModiToEditFrameInit("returnpdtrls.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("returnpdtrls.shtml?action=delete", "PRD_RETURN_REQ_ID");
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("请选择一条记录");
			return;
		}
		toFlow("3");
	});
	$("#audit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("请选择一条记录");
			return;
		}
//		toAdudit(selRowId)
		toFlow("2");
	});
	
	$("#flow").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("请选择一条记录");
			return;
		}
		toFlow("4");
	}); 
});

function toAdudit(selRowId){
  $.ajax({
	 url: "returnpdtrls.shtml?action=toAudit",
	 type:"POST",
 	 dataType:"text",
 	 data:{"PRD_RETURN_REQ_ID":selRowId},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			 parent.showMsgPanel(utf8Decode(jsonResult.messages));
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
			return;
		}
	  }
   });
}
 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
	var firstAudit = parent.window.document.getElementById("firstAudit").value;
	
    document.affirm.sourceURI.value=ctxPath+"/returnpdtrls.shtml?action=toList&firstAudit="+firstAudit+document.getElementById("paramUrl").value;
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
	if (state != "未提交") {
		btnDisable(["delete","modify"]);
	}
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit"]);
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
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
}

