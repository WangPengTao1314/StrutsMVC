/**
 *@module 基础信息
 *@func 系统日志
 *@version 1.1
 *@author  guhongxiu
 */
$(function(){
	//框架页面初始化
	framePageInit("syslog.shtml?action=toList");
});

//bottomcontent页面跳转。
function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();

	//初始化时下帧页面的action
	if(null == action || "label_1" == action){//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "syslog.shtml?action="+action+"&SYSLOG_ID="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
