﻿/**
 * @prjName:信用额度变更申请
 * @fileName:Grant_Frame
 * @author zhang_zhongbin
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("credit_req.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("credit_req.shtml?action=toList&module=" + module);
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
	var url = "credit_req.shtml?action=" + action + "&selRowId=" + selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
 