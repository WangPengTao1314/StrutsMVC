/**
 *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *
 */
$(function(){
	//框架页面初始化
	 var module = document.getElementById("module").value;
	 var paramUrl = document.getElementById("paramUrl");
	 if(paramUrl != null && paramUrl.value != ""){
	    framePageInit("forecasttaskupaudit.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }else{	 
	    framePageInit("forecasttaskupaudit.shtml?action=toList&module=" + module);
	 }	
});


//bottomcontent页面跳转。
function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
    var flag = 1;
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){//"label_1"的判断是为了与主从界面通用
		action = "childList";
		setLabelSelected("label_1");
	} 
	var url = "forecasttaskupaudit.shtml?action="+action+"&RPT_JOB_CHANN_ID="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}

