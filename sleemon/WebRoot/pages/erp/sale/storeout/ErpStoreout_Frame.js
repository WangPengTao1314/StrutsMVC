﻿/**
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
	var billType=document.getElementById("BILL_TYPE").value;
	if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("erpStoreout.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	}else{	
		 framePageInit("erpStoreout.shtml?action=toList&module=" + module);
    }	
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
	var billType = document.getElementById("BILL_TYPE").value;
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
		url = "erpStoreout.shtml?action=childList"
	}else if("label_2" == showLabelId){
		url = "erpStoreout.shtml?action=toDetail"
	}
	 
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&STOREOUT_ID="+selRowId);
}