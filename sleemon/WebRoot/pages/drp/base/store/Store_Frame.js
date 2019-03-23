/**
 * @prjName:喜临门营销平台
 * @fileName:Store_Frame
 * @author wdb
 * @time   2013-08-13 14:01:22 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("store.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("store.shtml?action=toList&module=" + module);
	 }	
});
//bottomcontent页面跳转。
function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if (null == action || "label_1" == action) {//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "store.shtml?action=" + action + "&STORE_ID=" + selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
//bottomcontent页面跳转
//function gotoBottomPage(showLabelId){
//    //获取当前选中的记录
//	var selRowId = $("#selRowId").val();
//	if(null == showLabelId){
//		showLabelId = $("#showLabel").attr("value");
//	}else{
//		//设置当前显示的标签页
//		$("#showLabel").attr("value",showLabelId);
//	}
//	var url;
//	if("label_1" == showLabelId){
//		url = "store.shtml?action=childList"
//	}else{
//		url = "store.shtml?action=toDetail"
//	}
//	//下帧页面跳转
//	$("#bottomcontent").attr("src",url+"&STORE_ID="+selRowId);
//}