/*
  *@module 系统管理
  *@fuc 系统角色框架页面
  *@version 1.1
  *@author 唐赟
  */
  $(function(){
	//框架页面初始化
	framePageInit("xtjs.shtml?action=toList");	
var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("xtjs.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("xtjs.shtml?action=toList&module=" + module);
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
		url = "xtjs.shtml?action=childList";
	}else{
		url = "xtjs.shtml?action=toDetail";
	}
	
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&XTJSID="+selRowId);
}
  
  
 