/**
 *@module 渠道管理-上报管理
 *@func   营销经理上报维护
 *@version 1.1
 *@author zcx
 */

$(function() {
	var EDIT_FLAG = $("#EDIT_FLAG").val();
	var selRowId = $("#selRowId").val();
	if("CHILD_EDIT" == EDIT_FLAG){
		framePageInit("mkmdayreport.shtml?action=toChildEdit&OPEN_TERMINAL_REQ_ID="+selRowId);
	}else{
		framePageInit("mkmdayreport.shtml?action=toParentEdit&MKM_DAY_RPT_ID="+selRowId);
	}
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(showLabelId){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var url = "openterminal.shtml?action=modifyToChildEdit"
	  
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&OPEN_TERMINAL_REQ_ID="+selRowId);
}