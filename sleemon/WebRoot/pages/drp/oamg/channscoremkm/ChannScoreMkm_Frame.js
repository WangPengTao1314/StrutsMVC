/**
 *@module 渠道管理-上报管理
 *@func   加盟商营销经理评价表维护
 *@version 1.1
 *@author zcx
 */

$(function() {
	//框架页面初始化
	var module = $("#module").val();
	framePageInit("channscoremkm.shtml?action=toList");
	var paramUrl = document.getElementById("paramUrl");
	if (paramUrl != null && paramUrl.value != "")
		framePageInit("channscoremkm.shtml?action=toList"
				+ utf8((paramUrl.value.replaceAll("andflag", "&")).replaceAll(
						"equalsflag", "=")));
	else
		framePageInit("channscoremkm.shtml?action=toList&module=" + module);
});

 
//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var module   = $("#module").val();
	//初始化时下帧页面的action
	if (null == action || "label_1" == action) {
		action = "toDetail";
	}else if("label_2" == action){
		action = "childList";
	}
	var url = "channscoremkm.shtml?action=" + action + "&OPEN_TERMINAL_REQ_ID=" + selRowId+"&module="+module;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
