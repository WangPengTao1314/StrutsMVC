/**
 *@module 系统管理
 *@fuc 单位维护框架js
 *@version 1.0
 *@author 王栋斌
 */

$(function() {
	//框架页面初始化
	framePageInit("unit.shtml?action=toList");
	var paramUrl = document.getElementById("paramUrl");
	if (paramUrl != null && paramUrl.value != "")
		framePageInit("unit.shtml?action=toList"
				+ utf8((paramUrl.value.replaceAll("andflag", "&")).replaceAll(
						"equalsflag", "=")));
	else
		framePageInit("unit.shtml?action=toList&module=" + module);

});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();

	//初始化时下帧页面的action
	if (null == action || "label_1" == action) {
		//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "unit.shtml?action=" + action + "&MEAS_UNIT_ID=" + selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
