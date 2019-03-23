/**

  *@module 系统管理

  *@fuc 系统用户框架页面

  *@version 1.1

  *@author 唐赟
  */
$(function(){
	//框架页面初始化
	framePageInit("xtyh.shtml?action=toList");
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("xtyh.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("xtyh.shtml?action=toList&module=" + module);
});

//bottomcontent页面跳转。
function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == action){
		action = $("#showLabel").attr("value");;
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",action);
	}
	//初始化时下帧页面的action
	if("label_1" == action){//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}else if("label_2" == action){
		 action = "toJgfgMx";	  
	}else if("label_3" == action){
		action = "toBmfgMx";
	} else if("label_4" == action) {
	    action = "toZtfgMx";
	}else if("toEdit" == action){
		//设置详细信息标签页为选中状态
		setLabelSelected("label_1");
	}else if("label_5"==action){
		action = "toUserList";
	}
	var url = "xtyh.shtml?action="+action+"&XTYHID="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}