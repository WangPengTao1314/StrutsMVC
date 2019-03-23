/**
 * @module 系统管理
 * @func 物流供应商
 * @version 1.0
 * @author 王栋斌
 */
$(function () {
	//框架页面初始化
	framePageInit("logicprvd.shtml?action=toList");
});

//bottomcontent页面跳转。
function gotoBottomPage(action) {	
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if(null == action){
		action = $("#showLabel").attr("value");
	}else if("toEdit" == action){
		
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",action);
	}
 
	var url;
	if("label_1" == action){
		url = "logicprvd.shtml?action=toDetail&PRVD_ID=" + selRowId;
	}else if("label_2" == action){
		url = "logicprvd.shtml?action=toListTruck&PRVD_ID=" + selRowId;
	}else if("label_3" == action){
		url = "logicprvd.shtml?action=toListWaymerge&PRVD_ID=" + selRowId;
	}else{
		url = "logicprvd.shtml?action="+action+"&PRVD_ID=" + selRowId;
	}
	
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}

