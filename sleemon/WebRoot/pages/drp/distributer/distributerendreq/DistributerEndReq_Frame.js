/**
 *@module 渠道管理-分销商管理
 *@function   加盟商终止合作申请
 *@version 1.1
 *@author gu_hx
 */

$(function() {
	//框架页面初始化
	var module = $("#module").val();	
	var paramUrl = document.getElementById("paramUrl");
	if (paramUrl != null && paramUrl.value != "")
		framePageInit("distributerEndReq.shtml?action=toList"
				+ utf8((paramUrl.value.replaceAll("andflag", "&")).replaceAll(
						"equalsflag", "=")));
	else
		framePageInit("distributerEndReq.shtml?action=toList&module=" + module);
});

function gotoBottomPage(action) {	
}

