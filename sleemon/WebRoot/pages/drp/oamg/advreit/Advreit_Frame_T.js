/**
 *@module 营销管理-广告投放管理
 *@func   广告投放报销申请单维护
 *@version 1.1
 *@author zcx
 */

$(function() {

	//框架页面初始化
	var module = $("#module").val();
	framePageInit("advreit.shtml?action=toListSH&module=sh");
	var paramUrl = document.getElementById("paramUrl");
	if (paramUrl != null && paramUrl.value != "")
		framePageInit("advreit.shtml?action=toListSH&module=sh"
				+ utf8((paramUrl.value.replaceAll("andflag", "&")).replaceAll(
						"equalsflag", "=")));
	else
		framePageInit("advreit.shtml?action=toListSH&module=sh");
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(action,params1,params2) {

	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var module   = $("#module").val();
	//初始化时下帧页面的action
	if (null == action || "label_1" == action) {
		//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	if("action" == action){
		url = "advreit.shtml?action=toEditSH&REIT_POLICY="+params1+"&ERP_ADV_REIT_ID="+params2;
	}else {
	    var url = "advreit.shtml?action=" + action + "&ERP_ADV_REIT_ID=" + selRowId+"&module="+module;
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
