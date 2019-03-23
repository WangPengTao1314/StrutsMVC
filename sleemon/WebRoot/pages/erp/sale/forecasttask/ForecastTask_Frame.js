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
	 var flag = $("#flag").val();
	 var paramUrl = document.getElementById("paramUrl");
	 if(paramUrl != null && paramUrl.value != ""){
	    framePageInit("forecasttask.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }else{	 
	    framePageInit("forecasttask.shtml?action=toList&module=" + module+"&flag="+flag);
	 }	
});

//bottomcontent页面跳转。
function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
    var flag = 1;
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){//"label_1"的判断是为了与主从界面通用
		action = "queryJobChann";
		setLabelSelected("label_1");
	}else if("label_2" ==action){
		setLabelSelected("label_2");
		action = "queryJobChann";
		flag = 0;
	}else if("label_3" ==action){
		setLabelSelected("label_3");
		action = "toDetail";
	}
	var url = "forecasttask.shtml?action="+action+"&ADVC_RPT_JOB_ID="+selRowId+"&flag="+flag;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
