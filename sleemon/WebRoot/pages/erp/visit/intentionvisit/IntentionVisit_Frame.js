/**
 *@module 渠道管理-拜访管理
 *@func   拓展拜访表维护
 *@version 1.1
 *@author zcx
 */

$(function() {
	//框架页面初始化
	var module = $("#module").val()
	var paramUrl = document.getElementById("paramUrl");
	if (paramUrl != null && paramUrl.value != ""){
		framePageInit("intentionvisit.shtml?action=toList"
				+ utf8((paramUrl.value.replaceAll("andflag", "&")).replaceAll(
						"equalsflag", "=")),null,"40");
	} else{
		framePageInit("intentionvisit.shtml?action=toList&module=" + module,null,"40");
	}
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(action) {
	var selRowId = $("#selRowId").val();
	if (null == action || "label_1" == action) {
		setLabelSelected("label_1");
		action = "toDetail";
	}
	if("label_2" == action){
		setLabelSelected("label_2");
		action = "toChild";
	}
	var url = "intentionvisit.shtml?action=" + action + "&INTE_CHANN_ID=" + selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
