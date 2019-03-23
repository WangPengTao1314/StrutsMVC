/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_Frame
 * @author glw
 * @time   2013-08-17 17:07:03 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl = document.getElementById("paramUrl");
	
	//modify zzb  2014-6-21 [STATE][search]  分销首页 [待入库]url用到 该参数
	var STATE = $("#STATE").val();
	var search = $("#search").val();
	var addUrl = "&search="+search+"&STATE="+utf8(STATE);
	
	if(paramUrl != null && paramUrl.value != ""){
	    framePageInit("storeinnotice.shtml?action=toList"+addUrl+utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	}else{	 
	    framePageInit("storeinnotice.shtml?action=toList&module=" + module+addUrl);
	}	
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "storeinnotice.shtml?action=childList"
	}else{
		url = "storeinnotice.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&STOREIN_NOTICE_ID="+selRowId);
}