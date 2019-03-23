/**
 *@module 渠道管理-拜访管理
 *@func   加盟商拜访表维护
 *@version 1.1
 *@author zcx
 */

$(function() {
	//框架页面初始化
	var module = $("#module").val();
	framePageInit("channvisit.shtml?action=toList");
	var paramUrl = document.getElementById("paramUrl");
	if (paramUrl != null && paramUrl.value != "")
		framePageInit("channvisit.shtml?action=toList"
				+ utf8((paramUrl.value.replaceAll("andflag", "&")).replaceAll(
						"equalsflag", "=")));
	else
		framePageInit("channvisit.shtml?action=toList&module=" + module);
});

function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var module   = $("#module").val();
	//初始化时下帧页面的action
	if (null == action || "label_1" == action) {
		//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "channvisit.shtml?action=" + action + "&CHANN_VISIT_ID=" + selRowId+"&module="+module;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
