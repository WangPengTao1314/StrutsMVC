/**
 * @prjName:喜临门营销平台
 * @fileName:Store_List_Chld
 * @author wdb
 * @time   2013-08-13 14:01:22 
 * @version 1.1
 */
$(function(){
	//按钮预置
	btnDisable( [ "start", "stop" ]);
	btnStateCheck();
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
		if (state != "制定") {
			parent.showErrorMsg("当前主表状态下，不能删除明细！");
			return;
		}
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
	});

	$("#edit").click(function(){
        var checkBox = $("#ordertb tr:gt(0) input:checked");
		var ids = "";
		var name = "";
		var state = "";
		var flag = true;
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				name = $(this).val();
				state = $.trim($("#state" + name).text());
				if (state == "启用") {
					flag = false;
				} else {
					ids = ids+"'"+$(this).val()+"',";
				}
			});
			if (!flag) {
				parent.showErrorMsg("已启用的记录不能编辑");
				return;
			}
			ids = ids.substr(0,ids.length-1);
		}
		$("#STORG_IDS").val(ids);
		$("#form1").attr("action","store.shtml?action=toChildEdit");
		$("#form1").submit();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
	
	// 停用
	$("#stop").click(function() {
		listStopConfirm();
	});

	// 启用
	$("#start").click(function() {
		listStartConfirm();
	});
});

function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	var name = "";
	var state = "";
	var flag = true;
	checkBox.each(function() {
		name = $(this).val();
		state = $.trim($("#state" + name).text());
		if (state == "启用" || state == "停用") {
			flag = false;
		} else {
			ids = ids + "'" + $(this).val() + "',";
		}
	});
	if (!flag) {
		parent.showErrorMsg("已启用或停用的记录不能删除");
		return;
	}
	ids = ids.substr(0,ids.length-1);
	$.ajax({
		url: "store.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STORG_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//停用记录
function listStopConfirm() {
	closeWindow();
	var storeId = $(window.parent.frames["topcontent"].document).find("#selRowId").val();
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}
	var ids = "";
	checkBox.each(function(){
		if("" != $(this).val()){
			ids = ids+"'"+$(this).val()+"',";
		}
	});
	ids = ids.substr(0,ids.length-1);
	$.ajax( {
		url : "store.shtml?action=changeChildState&STORG_ID=" + ids + "&STATE=2",
		type : "POST",
		dataType : "text",
		complete : function(xhr) {
			eval("jsonResult = " + xhr.responseText);
			if (jsonResult.success === true) {
				parent.showMsgPanel("状态修改成功");
				window.parent.bottomcontent.location = "store.shtml?action=childList&STORE_ID=" + storeId
			} else {
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//启用记录
function listStartConfirm() {
	closeWindow();
	var storeId = $(window.parent.frames["topcontent"].document).find("#selRowId").val();
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}
	var ids = "";
	checkBox.each(function(){
		if("" != $(this).val()){
			ids = ids+"'"+$(this).val()+"',";
		}
	});
	ids = ids.substr(0,ids.length-1);
	$.ajax( {
		url : "store.shtml?action=changeChildState&STORG_ID=" + ids + "&STATE=1",
		type : "POST",
		dataType : "text",
		complete : function(xhr) {
			eval("jsonResult = " + xhr.responseText);
			if (jsonResult.success === true) {
				parent.showMsgPanel("状态修改成功");
				window.parent.bottomcontent.location = "store.shtml?action=childList&STORE_ID=" + storeId
			} else {
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function btnStateChange(state) {
	//按钮状态重置
	btnReset();
	if (state == "启用") {
		btnDisable( [ "start" ]);
	} else if (state == "停用") {
		btnDisable( [ "stop" ]);
	} else if (state == "制定") {
		btnDisable( [ "stop" ]);
	}
	btnStateCheck("1");
}
function btnStateCheck(type){
	if("1"!=type){
		btnReset();
	}
	var state=window.parent.topcontent.getSTATE();
	if("启用"==state){
		btnDisable( [ "start","stop","edit","delete" ]);	
	}
	
}