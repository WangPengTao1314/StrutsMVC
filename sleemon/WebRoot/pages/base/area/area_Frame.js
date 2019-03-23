
/**
 * @module 系统管理
 * @func 区域信息
 * @version 1.1
 * @author 张忠斌
 */
$(function () {
	//框架页面初始化
	framePageInit("area.shtml?action=toList");
	//加载左帧页面
	$("#leftcontent").attr("src", "area.shtml?action=showTree");
	treeShowHide();
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("area.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("area.shtml?action=toList&module=" + module);
});

//bottomcontent页面跳转。
function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	
	if(null == action){
		action = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",action);
	}
	
	var url = "area.shtml?action=";
	if (null == action || "label_1" == action) {//"label_1"的判断是为了与主从界面通用
		url = url + "toDetail";
	}else if("label_2" == action){
		url = "area.shtml?action=childList";
	}else{
		url = url + action;
	}
	
//	alert(url+"&areaID="+selRowId);
	//var url = "area.shtml?action=" + action + "&areaID=" + selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url+"&areaID="+selRowId);
}




