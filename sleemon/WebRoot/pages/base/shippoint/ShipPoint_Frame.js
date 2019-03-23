/**
 *@module 基础数据
 *@func 生产基地维护
 *@version 1.0
 *@author 王志格
 */
$(function(){
	//框架页面初始化
	framePageInit("shipPoint.shtml?action=toList");
});

//bottomcontent页面跳转。
function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();

	//初始化时下帧页面的action
	if(null == action || "label_1" == action){//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "shipPoint.shtml?action="+action+"&SHIP_POINT_ID="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
