/**
 * @prjName:喜临门营销平台
 * @fileName:Storeout_List
 * @author chenj
 * @time   2013-09-15 14:59:50 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
    //主从及主从从列表页面通用加载方法
	listPageInit("storeoutfewnotice.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("storeoutfewnotice.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("storeoutfewnotice.shtml?action=delete", "FEW_STOREOUT_REQ_ID");
	
	$("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
//	   toFlow("1");
		toCommit();
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
	
	//维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add","delete","modify", "commit", "revoke"]);
	 
	var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
      btnDisable(["modify","delete","commit","revoke","audit","flow"]);
      return;
    } 
});

 //库房通用选取
function storeSelCommon(){
	var ZTXXID = $("#ZTXXID").val();
	var selectParams = "";
	selectParams = " STATE ='启用' and DEL_FLAG=0 and  (BEL_CHANN_ID='"+ZTXXID+"' or BEL_CHANN_ID = '"+ZTXXID+"')";
	$("#selectStore").val(selectParams);
	selCommon('BS_35',false,'STOREOUT_STORE_ID','STORE_ID','forms[1]','STOREOUT_STORE_NO,STOREOUT_STORE_NAME','STORE_NO,STORE_NAME','selectStore');
	 
}

 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/storeoutfewnotice.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
function setSelOperateEx(obj){
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state != "未提交") {
		btnDisable(["modify","delete","commit"]);
	}
}
 

function realDelete(actionUrl,pkId,selRowId,goUrl){
    parent.closeWindow();
    $.ajax({
	 	url: actionUrl+"&"+pkId+"="+utf8(selRowId),
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			      parent.showMsgPanel("删除成功");
				  $("#pageForm").submit();
			}else{
				  parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function getStoreId(){
	var selRowId = $("#selRowId").val();
	return $("#STOREOUT_STORE_ID"+selRowId).val();
}
function toCommit(){
	parent.showWaitPanel();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "storeoutfewnotice.shtml?action=toCommit&FEW_STOREOUT_REQ_ID="+utf8(selRowId),
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
    closeWindow();
}