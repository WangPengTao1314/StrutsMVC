/**
 * @prjName:喜临门营销平台
 * @fileName:Changeadvcorder_Frame
 * @author ghx
 * @time   2014-05-20 15:14:52 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	var showFlag=document.getElementById("showFlag").value;
	var ADVC_ORDER_ID=document.getElementById("ADVC_ORDER_ID").value;
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("changeadvcorder.shtml?action=toList"+utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
	{
	    framePageInit("changeadvcorder.shtml?action=toList&module=" + module+"&showFlag="+showFlag+"&ADVC_ORDER_ID="+ADVC_ORDER_ID);
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var showFlag=document.getElementById("showFlag").value;
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "changeadvcorder.shtml?action=childList"
	}else{
		url = "changeadvcorder.shtml?action=toDetail"
	}
	if("label_1" == showLabelId){
		url = "changeadvcorder.shtml?action=childList"
	}else if("label_2" == showLabelId){
		url = "changeadvcorder.shtml?action=gchildList"
	}else if("label_3" == showLabelId){
		url = "changeadvcorder.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&ADVC_ORDER_CHANGE_ID="+selRowId+"&showFlag="+showFlag);
}