/**
 *@module 分销业务
 *@func人员信息维护
 *@version 1.1
 *@author lyg
 */
$(function(){
	//框架页面初始化
	framePageInit("employee.shtml?action=toList");
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("employee.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else{
	    framePageInit("employee.shtml?action=toList&module=" + module);
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == action){
		action = $("#showLabel").attr("value");
		if("toEdit"==action){
			action="label_1";
		}
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",action);
	}
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){
	//"label_1"的判断是为了与主从界面通用
	  action = "toDetail";
	}else if("label_2" == action){
		action="childList"
	}
	var url = "employee.shtml?action="+action+"&RYXXID="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}