/**
 * @module 系统管理
 * @func 物流供应商
 * @version 1.0
 * @author 王栋斌
 */
$(function() {
	var prvdId = $("#PRVD_ID").val();
	if(prvdId == ""){
		btnDisable(["start", "stop","edit","delete"]);
	}
	//按钮预置
	btnDisable( [ "start", "stop" ]);

	$("#delete").click(function() {
		//查找当前是否有选中的记录
			var checkBox = $("#ordertb tr:gt(0) input:checked");
			if (checkBox.length < 1) {
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			parent.showConfirm("您确认要删除吗", "bottomcontent.multiRecDeletes();");
		});

	$("#edit").click(function() {
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		var ids = "";
		var name = "";
		var state = "";
		var flag = true;
		if (checkBox.length > 0) {
			//获取所有选中的记录
			checkBox.each(function() {
				name = $(this).val();
				state = $.trim($("#state" + name).text());
				if (state == "启用") {
					flag = false;
				} else {
					ids = ids + "'" + $(this).val() + "',";
				}
			});
			if (!flag) {
				parent.showErrorMsg("已启用的记录不能编辑");
				return;
			}
			ids = ids.substr(0, ids.length - 1);
		}
		$("#TRUCK_ID").val(ids);
		$("#form1").attr("action", "logicprvd.shtml?action=toEditTruck");
		$("#form1").submit();
	});

	$("#allChecked").click(function() {
		var flag = document.getElementById("allChecked").checked;
		if (flag) {
			$("#ordertb :checkbox").attr("checked", "true");
		} else {
			$("#ordertb :checkbox").removeAttr("checked");
		}
		setBtStates();
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


//设置Button状态
function setBtStates(){
	var checkboxs = $("#ordertb input[name='eid']:checked");
	var num = checkboxs.length;
	btnReset();
	if(num ==0){
		btnDisable(["delete","start","stop"]);
	}else{
		checkboxs.each(function(){
			var id=$(this).val();
			var dtlState=$.trim($("#state"+id).html());
			if(dtlState=="停用"||dtlState=="制定"){
				btnDisable(["stop"]);
			}else{
				btnDisable(["delete","start","edit"]);
			}
			if(dtlState=="停用"){
				btnDisable(["delete"]);
			}
		});
	}
}



function multiRecDeletes() {
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
	ids = ids.substr(0, ids.length - 1);

	$.ajax( {
		url : "logicprvd.shtml?action=deleteTruck",
		type : "POST",
		dataType : "text",
		data : {
			"TRUCK_ID" : ids
		},
		complete : function(xhr) {
			eval("jsonResult = " + xhr.responseText);
			if (jsonResult.success === true) {
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
			} else {
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//停用记录
function listStopConfirm() {
	closeWindow();
	var PRVD_ID = $("#PRVD_ID").val();
	//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function() {
		ids = ids + "'" + $(this).val() + "',";
	});
	ids = ids.substr(0, ids.length - 1);
	$.ajax( {
				url : "logicprvd.shtml?action=changeTruckState&TRUCK_IDS="
						+ ids + "&STATE=2",
				type : "POST",
				dataType : "text",
				complete : function(xhr) {
					eval("jsonResult = " + xhr.responseText);
					if (jsonResult.success === true) {
						parent.showMsgPanel("状态修改成功");
						window.parent.bottomcontent.location = "logicprvd.shtml?action=toListTruck&PRVD_ID="
								+ PRVD_ID
					} else {
						parent.showErrorMsg(utf8Decode(jsonResult.messages));
					}
				}
			});
}

//启用记录
function listStartConfirm() {
	closeWindow();
	var PRVD_ID = $("#PRVD_ID").val();
	//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function() {
		ids = ids + "'" + $(this).val() + "',";
	});
	ids = ids.substr(0, ids.length - 1);
	$.ajax( {
		url : "logicprvd.shtml?action=changeTruckState&TRUCK_IDS="
				+ ids + "&STATE=1",
		type : "POST",
		dataType : "text",
		complete : function(xhr) {
			eval("jsonResult = " + xhr.responseText);
			if (jsonResult.success === true) {
				parent.showMsgPanel("状态修改成功");
				window.parent.bottomcontent.location = "logicprvd.shtml?action=toListTruck&PRVD_ID="
						+ PRVD_ID
			} else {
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//获取当前行的ID
function getID(truckId) {
	$("#TRUCK_ID").val(truckId);
}