/**
 * @prjName:喜临门营销平台
 * @fileName:Storetoretail_Frame
 * @author ghx
 * @time   2014-05-26 15:14:52 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("storetoretail.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else{	 
	    framePageInit("storetoretail.shtml?action=toList&module=" + module);
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
	var url = "storetoretail.shtml?action=" + action + "&SPCL_STORE_TO_RETAIL_REQ_ID=" + selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
