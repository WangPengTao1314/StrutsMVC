/**
 *@module 渠道管理-分销商管理
 *@func   加盟商终止合作申请
 *@version 1.1
 *@author gu_hx
 */

$(function() {	
	var module = $("#module").val();
	$("#topcontent").attr("src","distributerEndReq.shtml?action=toEdit&module=" + module+reqParamsEx());
});

