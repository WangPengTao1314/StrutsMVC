
/**
 * @module 系统管理
 * @func 区域分管信息
 * @version 1.1
 * @author 张忠斌
 */
$(function () {
	//框架页面初始化
	framePageInit("areaChrg.shtml?action=toList");
	//加载左帧页面
	$("#leftcontent").attr("src", "areaChrg.shtml?action=showTree");
	treeShowHide();
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("areaChrg.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else	
	    framePageInit("areaChrg.shtml?action=toList&module=" + module);
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
		url = "areaChrg.shtml?action=childList"
	}else{
		url = "areaChrg.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&areaID="+selRowId);
}


/***
 * bottomcontent页面跳转。
function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if (null == action || "label_1" == action) {//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "areaChrg.shtml?action=" + action + "&id=" + selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
*/
