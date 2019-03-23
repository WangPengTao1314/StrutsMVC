/**
 *@module 基础数据
 *@func 生产基地维护
 *@version 1.0
 *@author 王志格
 */
$(function(){
	//框架页面初始化
	framePageInit("factory.shtml?action=toList");
});

//bottomcontent页面跳转。
function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();

	if(null == action){
		action = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		if("label_1" == action || "label_2" == action || "label_3" == action){
			$("#showLabel").attr("value",action);
		}
	}
	
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){
		action = "toDetail";
	}
	else if("label_2" == action){
		action = "toProductList";
	}
		
	var url = "factory.shtml?action="+action+"&FACTORY_ID="+selRowId;
	//下帧页面跳转

	$("#bottomcontent").attr("src",url);
}
