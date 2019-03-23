/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_List
 * @author wzg
 * @time   2013-08-19 15:33:31 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit","commitToFactory"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke","commitToFactory"]);
	//鉴定页面需要隐藏的按钮
	var flag = parent.window.document.getElementById("flag").value;	
	if(flag == "decide")
		btnHidden(["add", "modify", "delete", "commit", "revoke","audit","flow"]);
	
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("prdreturn.shtml?action=toList&module=" + module+"&flag="+flag);
	//新增和修改按钮初始化
	addModiToEditFrameInit("prdreturn.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("prdreturn.shtml?action=delete", "PRD_RETURN_ID");
	
	var module = parent.document.getElementById("module").value;
	$("#query").click(function(){
		if("wh" == module){
			//$("#STATE option[text='审核通过']").remove();
			$("#STATE option[text='少发退货']").remove();
		}
		if("sh" == module){
			$("#STATE option[text='未提交']").remove();
			$("#STATE option[text='少发退货']").remove();
			$("#STATE option[text='撤销']").remove();
			$("#STATE option[text='已鉴定']").remove();
		}
	});
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		commit();
	});
    
    $("#commitToFactory").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		$.ajax({
		 url: "prdreturn.shtml?action=toCommit&PRD_RETURN_ID="+utf8(selRowId),
		 type:"POST",
	 	 dataType:"text",
		 complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("提交成功");
           		 $("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
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
	 var flag = parent.window.document.getElementById("flag").value;	
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/prdreturn.shtml?action=toList"+"&flag="+flag+document.getElementById("paramUrl").value ;
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
	if (state == "审核通过" || "已鉴定" == state) {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
	
	if("已鉴定" == state){
		btnDisable(["commitToFactory"]);
	}
}

function setBillType(billType)
{
	$("#BILL_TYPE",window.parent.document).val(billType);
}
//退货单维护点击提交走审批流后，判断来源单据编号，如果来自入库差异确认，则直接改变状态为已鉴定
function upSTATE(PRD_RETURN_ID){
	$.ajax({
		 url: "prdreturn.shtml?action=upState&PRD_RETURN_ID="+utf8(PRD_RETURN_ID),
		 type:"POST",
	 	 dataType:"text",
		 complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		  }
	    });
}
function commit(){
 var selRowId = parent.document.getElementById("selRowId").value;
 $.ajax({
	 url: "prdreturn.shtml?action=toCommitVindicate&PRD_RETURN_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			toFlow("1");
			upSTATE(selRowId);
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
	  	}
	}
 })
	 
}


