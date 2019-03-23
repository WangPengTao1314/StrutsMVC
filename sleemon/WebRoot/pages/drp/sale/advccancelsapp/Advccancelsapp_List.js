
/**
 * @prjName:喜临门营销平台
 * @fileName:预订发货取消申请
 * @author zzb
 * @time   2014-05-19  
 * @version 1.1
 */
$(function(){
     //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	 
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("advccancelsapp.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("advccancelsapp.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("advccancelsapp.shtml?action=delete", "ADVC_SEND_CANCEL_ID");
	
	$("#query").click(function(){
		if("wh" == module){
		}
		if("sh" == module){
		     $("#STATE option[text='未提交']").remove();
		} 
	});
		
	$("#commit").click(function(){
		var selRowId = $("#selRowId").val();
		if(null == selRowId || "" == selRowId){
			parent.showErrorMsg("请选择一条记录");
			return;
		}else{
			toCommit();
			 
		}
	});
	
	$("#revoke").click(function(){
		var selRowId = $("#selRowId").val();
		if(null == selRowId || "" == selRowId){
			parent.showErrorMsg("请选择一条记录");
			return;
		}else{
			toFlow(3);
		}
	});
	
	$("#audit").click(function(){
		var selRowId = $("#selRowId").val();
		if(null == selRowId || "" == selRowId){
			parent.showErrorMsg("请选择一条记录");
			return;
		}else{
			toFlow(2);
		}
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


 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/advccancelsapp.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
 
 

//提交
function toCommit(){
	var selRowId = $("#selRowId").val();
	$.ajax({
		url: "advccancelsapp.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"ADVC_SEND_CANCEL_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				parent.showMsgPanel(utf8Decode(jsonResult.messages));
//				$("#pageForm").submit();
				toFlow(1);
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	
}

//审核
function toAudit(){
	var selRowId = $("#selRowId").val();
	var ADVC_SEND_REQ_ID = $("#ADVC_SEND_REQ_ID"+selRowId).val();
	$.ajax({
		url: "advccancelsapp.shtml?action=toAudit",
		type:"POST",
		dataType:"text",
		data:{"ADVC_SEND_CANCEL_ID":selRowId,"ADVC_SEND_REQ_ID":ADVC_SEND_REQ_ID},
		complete: function(xhr){
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


function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	if (state == "未提交") {
		btnDisable(["revoke","audit"]);
	}
	if (state == "提交") {
		btnDisable(["modify","delete","commit"]);
	}
	if (state == "撤销" || state == "否决") {
		btnDisable(["revoke","audit"]);
	}
    if (state == "审核通过") {
		btnDisable(["modify","delete","commit","revoke","audit"]);
	}
}
 
 function getADVC_SEND_REQ_ID(){
	 var selRowId = $("#selRowId").val();
	 return $("#ADVC_SEND_REQ_ID"+selRowId).val();
 }
 
 function toreverse(){
	var selRowId = parent.document.getElementById("selRowId").value;
    $.ajax({
	 url: "advccancelsapp.shtml?action=toReverse&ADVC_ORDER_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("退回成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
 
