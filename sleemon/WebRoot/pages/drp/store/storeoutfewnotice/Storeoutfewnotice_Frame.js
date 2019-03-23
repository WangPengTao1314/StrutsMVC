/**
 * @prjName:喜临门营销平台
 * @fileName:Storeout_Frame
 * @author chenj
 * @time   2013-09-15 14:59:50 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("storeoutfewnotice.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else{	
		 framePageInit("storeoutfewnotice.shtml?action=toList&module=" + module);
	 }	                
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "storeoutfewnotice.shtml?action=childList"
	}else{
		url = "storeoutfewnotice.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&FEW_STOREOUT_REQ_ID="+selRowId);
}