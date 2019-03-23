/**
 * @prjName:喜临门营销平台
 * @fileName:Restatements_Frame
 * @author chenj
 * @time   2013-10-12 15:21:43 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	//framePageInit("restatements.shtml?action=toList");
	var paramUrl=document.getElementById("paramUrl");
	var module = document.getElementById("module").value;
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("restatements.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("restatements.shtml?action=toList&module=" + module);
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){
	//"label_1"的判断是为了与主从界面通用
	  action = "toChildList";
	}else if("label_2" == action){
	  action = "toDetail";
	}
	var url = "restatements.shtml?action="+action+"&STATEMENTS_ID="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}