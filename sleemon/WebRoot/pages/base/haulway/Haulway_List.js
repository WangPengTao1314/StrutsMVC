/**
 *@module 系统管理
 *@fuc 路线信息一览js
 *@version 1.0
 *@author 王栋斌
 */

$(function() {
	//主从及主从从列表页面通用加载方法
	listPageInit("haulway.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	var selRowId = $("#selRowId").val();
	if (selRowId == null || '' == selRowId) {
		btnDisable( [ "start", "modify", "delete", "stop" ]);
		return;
	}
	$("#delete").click(function() {
		if (null == selRowId || "" == selRowId) {
			parent.showErrorMsg("请选择一条记录");
			return;
		}
		parent.showConfirm("您确认删除该条单位信息吗?", "topcontent.listDelConfirm();");
	});

	// 停用
	$("#stop").click(function() {
		//获取当前选中的记录
			if (null == selRowId || "" == selRowId) {
				parent.showErrorMsg("请选择一条记录");
				return;
			}
			parent.showConfirm("将停用该条单位信息，是否继续?",
					"topcontent.listStopConfirm();");
		});

	// 启用
	$("#start").click(function() {
		//获取当前选中的记录
			if (null == selRowId || "" == selRowId) {
				parent.showErrorMsg("请选择一条记录");
				return;
			}
			parent.showConfirm("将启用该条单位信息，是否继续?",
					"topcontent.listStartConfirm();");

		});
})

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
	if (state == "启用") {
		//启用，删除，修改按钮不可用
		btnDisable( [ "modify" ]);
		btnDisable( [ "delete" ]);
		btnDisable( [ "start" ]);
	} else if (state == "停用") {
		//停用,删除按钮不可用
		btnDisable( [ "stop" ]);
		btnDisable( [ "delete" ]);
	} else if (state == "制定") {
		// 停用按钮不可用
		btnDisable( [ "stop" ]);
	}
}

//删除记录
function listDelConfirm() {
	closeWindow();
	var selRowId = $("#selRowId").val();
	$.ajax( {
		url : "haulway.shtml?action=delete&HAULWAY_ID=" + selRowId,
		type : "POST",
		complete : function(xhr) {
			eval("jsonResult = " + xhr.responseText);
			if (jsonResult.success === true) {
				parent.showMsgPanel("删除成功");
				$("#pageForm").submit();
			} else {
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//停用记录
function listStopConfirm() {
	closeWindow();
	var selRowId = $("#selRowId").val();
	$.ajax( {
		url : "haulway.shtml?action=changeState&HAULWAY_ID=" + selRowId
				+ "&STATE=1",
		type : "POST",
		dataType : "text",
		complete : function(xhr) {
			eval("jsonResult = " + xhr.responseText);
			if (jsonResult.success === true) {
				parent.showMsgPanel("状态修改成功");
				$("#pageForm").submit();
			} else {
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//启用记录
function listStartConfirm() {
	closeWindow();
	var selRowId = $("#selRowId").val();
	$.ajax( {
		url : "haulway.shtml?action=changeState&HAULWAY_ID=" + selRowId
				+ "&STATE=2",
		type : "POST",
		dataType : "text",
		complete : function(xhr) {
			eval("jsonResult = " + xhr.responseText);
			if (jsonResult.success === true) {
				parent.showMsgPanel("状态修改成功");
				$("#pageForm").submit();
			} else {
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
