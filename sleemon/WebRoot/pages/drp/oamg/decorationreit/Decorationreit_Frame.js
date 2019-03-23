/**
 *@module 渠道管理-装修管理
 *@fuc    装修报销申请单维护
 *@version 1.0
 *@author zzb
 */

$(function() {
	//框架页面初始化
	var module = $("#module").val()
	var paramUrl = document.getElementById("paramUrl");
	if (paramUrl != null && paramUrl.value != ""){
		framePageInit("decorationreit.shtml?action=toList"
				+ utf8((paramUrl.value.replaceAll("andflag", "&")).replaceAll(
						"equalsflag", "=")));
	} else{
		framePageInit("decorationreit.shtml?action=toList&module=" + module);
	}
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
	var url = "decorationreit.shtml?action=" + action + "&RNVTN_REIT_REQ_ID=" + selRowId+"&module="+$("#module").val();
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
