
/**
 *@module 渠道管理-装修管理
 *@func  装修申请单维护
 *@version 1.1
 *@author zcx
 */
$(function () {
	//维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "delete", "commit", "revoke"]);
	
	//主从及主从从列表页面通用加载方法
	listPageInit("decorationapp.shtml?action=toList");
	var module = $("#module").val();
	if (module == "sh") {
		$("#query").click(function () {
			$("#STATE option[text='\u672a\u63d0\u4ea4']").remove();
		});
	}
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
		//toFlow("3");
		parent.showConfirm("\u60a8\u786e\u8ba4\u64a4\u9500\u8be5\u6761\u4fe1\u606f\u5417?", "topcontent.toFlow(3);", "N");
	});
	$("#audit").click(function () {
	    var countT = 0;
	    var aMount = 0;
		var zxdhId = document.getElementById("ZXSQ").value;
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		} else {
			$.ajax({
			    url:"decorationapp.shtml?action=isStoreManage", 
			    type:"POST", 
			    dataType:"text", 
			    data:{"CHANN_RNVTN_ID":selRowId}, 
			    complete:function (xhr) {
				eval("jsonResult = " + xhr.responseText);
				if (jsonResult.success === true) {
					var result = jsonResult.data;
					var REIT_AMOUNT_PS = result.REIT_AMOUNT_PS;
					var count = result.count;
					countT = count;
					aMount = REIT_AMOUNT_PS;
					if(count !=0 && aMount==0){
						   parent.showErrorMsg("请输入报销金额！");
						   return;
					 }else{
					     //toFlow("2");
					      btnDisable(["audit"]);
		                  toAudit(selRowId);
					 }
					//} 
				} else {
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}});
		   }
	 });
	
	$("#flow").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("4");
	});
	$("#add").click(function () {
		setCommonPageInfo(false);
		parent.window.gotoBottomPage("toEdit");
	});
	$("#modifyT").click(function () {
		var selRowId = $("#selRowId").val();
		if (null == selRowId || "" == selRowId) {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u8bb0\u5f55");
		} else {
			setCommonPageInfo(true);
			parent.window.gotoBottomPage("toEditSH");
		}
	});
	$("#modify").click(function () {
		var selRowId = $("#selRowId").val();
		if (null == selRowId || "" == selRowId) {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u8bb0\u5f55");
		} else {
			setCommonPageInfo(true);
			parent.window.gotoBottomPage("toEditSH");
		}
	});
	$("#export").click(function () {
		var search = $("#search").val();
		var CHANN_RNVTN_NO = $("#CHANN_RNVTN_NO").val();
		$("#queryForm").attr("action", "decorationapp.shtml?action=expertExcel&module=" + getModule() + "&search=" + search + "&CHANN_RNVTN_NO=" + CHANN_RNVTN_NO);
		$("#queryForm").submit();
	});
	$("#print").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
	});
	InitFormValidator(0);
	var chiState = $.trim($("#ordertb  :input[type='radio'][checked='true']").parent().parent().find("td").eq(5).text());
	$("#submit").click(function () {
		var selRowId = $("#selRowId").val();
		if (null == selRowId || "" == selRowId) {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u8bb0\u5f55");
		} else {
			setCommonPageInfo(true);
			parent.window.gotoBottomPage("toSubmit");
			parent.showMsgPanel("\u60a8\u5df2\u6210\u529f\u63d0\u4ea4");
			$("#pageForm").submit();
		}
	});
	
	//删除监听.参数1：删除action，参数2：删除主键Id
	var selRowId = $("#selRowId").val();
	if (selRowId == null || "" == selRowId) {
		btnDisable(["start", "modify", "delete", "stop"]);
		return;
	}
	$("#delete").click(function () {
		if (null == selRowId || "" == selRowId) {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u8bb0\u5f55");
			return;
		}
		parent.showConfirm("\u60a8\u786e\u8ba4\u5220\u9664\u8be5\u6761\u4fe1\u606f\u5417?", "topcontent.listDelConfirm();");
	});
	$("#btn").click(function () {
		if (!formChecked("queryForm")) {
			return;
		}
		var freezeDays = $("#MAX_FREEZE_DAYS").val();
		if (freezeDays != 0) {
			var CHANN_ID = $("#CHANN_ID").val();
			var url = "decorationapp.shtml?action=toEditFreezeDays&module=" + getModule() + "&freezeDays=" + freezeDays + "&CHANN_ID=" + CHANN_ID;
			$.ajax({url:url, type:"POST", dataType:"text", complete:function (xhr) {
				eval("jsonResult = " + xhr.responseText);
				if (jsonResult.success === true) {
					//parent.showMsgPanel("保存成功");
				} else {
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}});
			if (freezeDays != "") {
				showMsgPanel("\u4fdd\u5b58\u6210\u529f");
			} else {
				showMsgPanel("\u8bf7\u586b\u5199\u6700\u5927\u51bb\u7ed3\u5929\u6570");
			}
		} else {
			showMsgPanel("\u8bf7\u4e3a\u51bb\u7ed3\u5929\u6570\u586b\u5199\u6b63\u6574\u6570");
		}
	});
	//state();
});
function listRevoke() {
	toFlow("3");
}
function print(selRowId) {
	//跳转扫码打印页面
	window.open("decorationapp.shtml?action=printInfo&CHANN_RNVTN_ID=" + selRowId, "\u88c5\u4fee\u7533\u8bf7\u5355", "height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes");
}
function state(params) {
	var state = params.value;
	var PVG_EDIT_AUDIT = $("#PVG_EDIT_AUDIT").val();
    //按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke", "audit"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		if (1 == PVG_EDIT_AUDIT) {
			btnDisable(["delete", "commit"]);
		} else {
			btnDisable(["delete", "modifyT", "commit"]);
		}
	}
	
	//当状态=撤销
	if (state == "\u64a4\u9500") {
		btnDisable(["revoke", "audit"]);
	}
	
	//当状态=否决
	if (state == "\u5426\u51b3") {
		btnDisable(["revoke", "audit"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete", "modify", "revoke", "commit"]);
	}
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete", "modifyT", "modify", "revoke", "commit", "audit"]);
	}
}
function toCommit(selRowId) {
	$.ajax({url:"decorationapp.shtml?action=toCommit", type:"POST", dataType:"text", data:{"CHANN_RNVTN_ID":selRowId}, complete:function (xhr) {
		eval("jsonResult = " + xhr.responseText);
		if (jsonResult.success === true) {
			parent.showMsgPanel("\u63d0\u4ea4\u6210\u529f");
			pageForm.submit();
                 //toFlow("1");
		} else {
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	}});
}
function audit() {
	var selRowId = $("#CHANN_RNVTN_ID").val();
	$.ajax({url:"decorationapp.shtml?action=toAudit", type:"POST", dataType:"text", data:{"CHANN_RNVTN_ID":selRowId}, complete:function (xhr) {
	}});
}
function toFlow(i) {
	 
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
	document.affirm.sourceURI.value = ctxPath + "/decorationapp.shtml?action=toList" + document.getElementById("paramUrl").value;
	switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		getAuditState(cutid);
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}
function getAuditState(cutid) {
	$.ajax({url:"decorationapp.shtml?action=getAuditState", type:"POST", data:{"CHANN_RNVTN_ID":cutid}, complete:function (xhr) {
	}});
}
function auditT() {
	var cutid = $("#selRowId").val();
	$.ajax({url:"decorationapp.shtml?action=toAudit", type:"POST", dataType:"text", data:{"CHANN_RNVTN_ID":selRowId}, complete:function (xhr) {
		eval("jsonResult = " + xhr.responseText);
		if (jsonResult.success === true) {
			 //parent.showMsgPanel("提交成功");
                //pageForm.submit();
                //toFlow("1");
		} else {
			//parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	}});
}

//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var state = $.trim($(obj).find("td[json='STATE']").text());
	//设置按钮的状态
	setBtnState(state);
}
//设置按钮的状态
function setBtnState(state) {
	//按钮状态重置
	btnReset();
	if (state == "\u542f\u7528") {
		//启用，删除，修改按钮不可用
		btnDisable(["modify"]);
		btnDisable(["delete"]);
		btnDisable(["start"]);
	} else {
		if (state == "\u505c\u7528") {
		//停用,删除按钮不可用
			btnDisable(["sp"]);
			btnDisable(["delete"]);
		} else {
			if (state == "\u5236\u5b9a") {
		// 停用按钮不可用
				btnDisable(["stop"]);
			}
		}
	}
}

 //删除记录
function listDelConfirm() {
	closeWindow();
	var selRowId = $("#selRowId").val();
	$.ajax({url:"decorationapp.shtml?action=delete&CHANN_RNVTN_ID=" + selRowId, type:"POST", complete:function (xhr) {
		eval("jsonResult = " + xhr.responseText);
		if (jsonResult.success === true) {
			parent.showMsgPanel("\u5220\u9664\u6210\u529f");
			$("#pageForm").submit();
		} else {
			showErrorMsg(utf8Decode(jsonResult.messages));
		}
	}});
}

 function toAudit(selRowId){
    //showWaitPanel();
	 $.ajax({
		url: "decorationapp.shtml?action=toAuditT",
		type:"POST",
		dataType:"text",
		data:{"CHANN_RNVTN_ID":selRowId},
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

