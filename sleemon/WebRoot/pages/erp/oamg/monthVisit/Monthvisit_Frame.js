/**
 *@module渠道管理-拜访管理
 *@func  月度拜访计划维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//框架页面初始化
	 var module = document.getElementById("module").value;
     var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("monthVisit.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("monthVisit.shtml?action=toList&module=" + module);
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
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
		url = "monthVisit.shtml?action=childList"
	}else{
		url = "monthVisit.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&MONTH_VISIT_PLAN_ID="+utf8(selRowId));
}

//是否刷新标记
var refresh = true;
function setRefreshFlag(isRefresh){
	refresh = isRefresh;
}
