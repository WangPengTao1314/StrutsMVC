﻿/**
 * @prjName:喜临门营销平台
 * @fileName:Techorder_Frame
 * @author lyg
 * @time   2013-10-12 17:37:51 
 * @version 1.1
 */
$(function(){
//	//框架页面初始化
//	var module = document.getElementById("module").value;
//	var paramUrl=document.getElementById("paramUrl");
//	 if(paramUrl!=null&&paramUrl.value!="")
//	 {
//	    framePageInit("techorder.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
//	 }	
//	 else
//     {	 
//	    framePageInit("techorder.shtml?action=toList&module=" + module);
//	 }	
	url="techorder.shtml?action=modifyToChildEdit"
	$("#bottomcontent").attr("src",url);
});

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
//		url = "techorder.shtml?action=childList"
//	}else if("label_3" == showLabelId){
//		url = "techorder.shtml?action=toTrace"
//	}else{
//		url = "techorder.shtml?action=toDetail"
//	}
//	//下帧页面跳转
//	$("#bottomcontent").attr("src",url+"&TECH_ORDER_ID="+selRowId);
//}