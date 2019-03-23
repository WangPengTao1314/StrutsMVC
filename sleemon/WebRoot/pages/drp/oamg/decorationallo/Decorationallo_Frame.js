/**
 *@module 渠道管理-装修管理
 *@func  装修补贴标准维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//框架页面初始化
	//framePageInit("decorationallo.shtml?action=toList&STATE="+state);
	 var module = document.getElementById("module").value;
     var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("decorationallo.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("decorationallo.shtml?action=toList&module=" + module);
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
		url = "decorationallo.shtml?action=childList"
	}else{
		url = "decorationallo.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&RNVTN_SUBST_STD_ID="+utf8(selRowId));
}
//是否刷新标记
var refresh = true;
function setRefreshFlag(isRefresh){
	refresh = isRefresh;
}
