/**
 * @prjName:喜临门营销平台
 * @fileName:Paymentcmt_Frame
 * @author lyg
 * @time   2013-08-25 09:50:09 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	//framePageInit("paymentclose.shtml?action=toList");
	var paramUrl=document.getElementById("paramUrl");
	var module = document.getElementById("module").value;
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("paymentclose.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("paymentclose.shtml?action=toList&module=" + module);
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){
	//"label_1"的判断是为了与主从界面通用
	  action = "toDetail";
	}
	var url = "paymentclose.shtml?action="+action+"&PAYMENT_UPLOAD_ID="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}