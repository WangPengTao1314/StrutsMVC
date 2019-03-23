

/**
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */
$(function (){
//维护页面需要隐藏的按钮
	whBtnHidden(["audit","Paudit","cancelAudit","batchCancelAudit"]);
	$("#creditValdt").hide();
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke","export","Pcommit","copy"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("erprebatechangereq.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("erprebatechangereq.shtml?action=delete", "REBATE_CHANGE_REQ_ID");
	$("#query").click(function(){
		if("sh"==module){
			$("#STATE option[text='未提交']").remove();
		}
	});
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		$.ajax({
			url: "erprebatechangereq.shtml?action=rebateCommit",
			type:"POST",
			dataType:"text",
			data:{"REBATE_CHANGE_REQ_ID":"'"+selRowId+"'"},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					var data = jsonResult.data;
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
		$.ajax({
			url: "erprebatechangereq.shtml?action=rebateRevoke",
			type:"POST",
			dataType:"text",
			data:{"REBATE_CHANGE_REQ_ID":"'"+selRowId+"'"},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					var data = jsonResult.data;
					parent.showMsgPanel("撤销成功");
					$("#pageForm").submit();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
		
	});
	$("#audit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("您确认要审核吗?","topcontent.rebateAudit();");
	});
	
	$("#queryRebate").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		var CHANN_ID=$("#chann"+selRowId).val();
		$.ajax({
			url: "erprebatechangereq.shtml?action=getRebate",
			type:"POST",
			dataType:"text",
			data:{"CHANN_ID":CHANN_ID},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					var data = jsonResult.data;
					parent.showMsgPanel("渠道"+data.CHANN_NAME+"当前返利为："+data.REBATE_CURRT);
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	})
	$("#export").click(function(){
		 $("#queryForm").attr("action","erprebatechangereq.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
	
	$("#Pcommit").click(function(){
		window.showModalDialog("erprebatechangereq.shtml?action=toBatchList",window,"dialogHeight:500px;dialogWidth:1000px;status:no;");
		$("#pageForm").submit();
	});
	
	$("#Paudit").click(function(){
		window.showModalDialog("erprebatechangereq.shtml?action=toBatchAuditList",window,"dialogHeight:500px;dialogWidth:1000px;status:no;");
		$("#pageForm").submit();
	});
	
	$("#cancelAudit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("您确认要弃审吗?","topcontent.cancelAudit();");
	});
	
	$("#batchCancelAudit").click(function(){
		window.showModalDialog("erprebatechangereq.shtml?action=toBatchCancelAuditList",window,"dialogHeight:500px;dialogWidth:1000px;status:no;");
		$("#pageForm").submit();
	});
	$("#copy").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		setRebatePageInfo(true);
	    parent.window.gotoBottomPage("toEdit&IS_COPY=TRUE");
	});
	
	
});

function setRebatePageInfo(flag){
	setCommonPageInfo(flag)
}

//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit","close"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit","cancelAudit"]);
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
	//当状态=关闭
	if (state == "关闭") {
		btnDisable(["close","","upCredit","commit","audit"]);
	}
	
}

function cancelAudit(){
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
	url: "erprebatechangereq.shtml?action=cancelAudit",
	type:"POST",
	dataType:"text",
	data:{"REBATE_CHANGE_REQ_ID":"'"+selRowId+"'"},
	complete: function(xhr){
	eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			var data = jsonResult.data;
			parent.showMsgPanel("弃审成功");
			$("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	}
});
}
//复制记录
function copyRecord(){
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
	url: "erprebatechangereq.shtml?action=copyRecord",
	type:"POST",
	dataType:"text",
	data:{"REBATE_CHANGE_REQ_ID":selRowId},
	complete: function(xhr){
	eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			var data = jsonResult.data;
			parent.showMsgPanel(jsonResult.messages);
			$("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	}
});
}


function rebateAudit(){
	var selRowId = parent.document.getElementById("selRowId").value;
		$.ajax({
			url: "erprebatechangereq.shtml?action=rebateAudit",
			type:"POST",
			dataType:"text",
			data:{"REBATE_CHANGE_REQ_ID":"'"+selRowId+"'"},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					var data = jsonResult.data;
					parent.showMsgPanel("审核成功");
					$("#pageForm").submit();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
}
