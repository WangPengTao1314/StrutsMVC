/**
 *@module 渠道管理-分销商管理
 *@func   分销渠道信息登记
 *@version 1.1
 *@author gu_hx
 */

$(function() {
	//框架页面初始化
	var module = $("#module").val();
	framePageInit("distributerReq.shtml?action=toList");
	var paramUrl = document.getElementById("paramUrl");
	if (paramUrl != null && paramUrl.value != "")
		framePageInit("distributerReq.shtml?action=toList"
				+ utf8((paramUrl.value.replaceAll("andflag", "&")).replaceAll(
						"equalsflag", "=")));
	else
		framePageInit("distributerReq.shtml?action=toList&module=" + module);
});

function gotoBottomPage(action) {	
}

