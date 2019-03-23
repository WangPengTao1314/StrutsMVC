/**
 *@module 基础资料
 *@func 维护编码规则
 *@version 1.1
 *@author 孙炜
 */
$(function(){
	//框架页面初始化
	framePageInit("bmgz.shtml?action=toList");

     var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("bmgz.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("bmgz.shtml?action=toList&module=" + module);
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
		url = "bmgz.shtml?action=childList"
	}else{
		url = "bmgz.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&BMGZID="+utf8(selRowId));
}
//是否刷新标记
var refresh = true;
function setRefreshFlag(isRefresh){
	refresh = isRefresh;
}
