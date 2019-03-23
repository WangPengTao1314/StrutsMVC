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
	listPageInit("expenseorder.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("expenseorder.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("expenseorder.shtml?action=delete", "EXPENSE_ORDER_ID");
	
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		//toCommit(selRowId);
		 btnDisable(["commit"]);
		 toCommit(selRowId);
	});
    
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		//toFlow("3");
		parent.showConfirm("您确认撤销该条信息吗?","topcontent.toFlow(3);","N");
	});
	$("#audit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		//toFlow("2");
		 btnDisable(["audit"]);
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
	$("#print").click(function (){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
    });
    
    $("#expdate").click(function(){
		 $("#queryForm").attr("action","expenseorder.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	});
});
 
 function print(selRowId){
	//跳转扫码打印页面
	window.open('expenseorder.shtml?action=printInfo&EXPENSE_ORDER_ID='+selRowId,'费用报销单','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
 }

 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/expenseorder.shtml?action=toList"+document.getElementById("paramUrl").value ;
	switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		break;//审核
	  case 3:
		affirmEvent(2);
		Revoke(cutid);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}
 
 function  Revoke(selRowId){
    $.ajax({
		url: "expenseorder.shtml?action=toRevoke",
		type:"POST",
		dataType:"text",
		data:{"EXPENSE_ORDER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			   //toFlow("1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
 }
 
function oppenPromoexpenPage(obj){
	var url = $(obj).attr("url");
	window.open(url,"关联推广费单","dialogwidth=800px; dialogheight=600px; status=no");
}
 
 

//推广费 申请金额
function getRELATE_AMOUNT_REQ(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#RELATE_AMOUNT_REQ"+selRowId).val();
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
 
  function toCommit(selRowId){
	 //parent.showWaitPanel();
	 $.ajax({
		url: "expenseorder.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"EXPENSE_ORDER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			   toFlow("1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				document.getElementById("commit").disabled = false;
			}
		}
	 });
 }
 /*
 function toCommit(selRowId){
	 showWaitPanel();
	 $.ajax({
		url: "expenseorder.shtml?action=toCommitT",
		type:"POST",
		dataType:"text",
		data:{"EXPENSE_ORDER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 toFlow("1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				closeWindow();
			}
		}
	 });
 }*/
 
  function toAudit(selRowId){
    //showWaitPanel();
	 $.ajax({
		url: "expenseorder.shtml?action=toAuditT",
		type:"POST",
		dataType:"text",
		data:{"EXPENSE_ORDER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				toFlow("2");
				//closeWindow();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				//closeWindow();
			}
		}
	 });
 }
 
  
