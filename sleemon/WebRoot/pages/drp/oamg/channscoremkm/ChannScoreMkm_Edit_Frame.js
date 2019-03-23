/**
 *@module 渠道管理-上报管理
 *@func   加盟商营销经理评价表维护
 *@version 1.1
 *@author zcx
 */

$(function() {
	var EDIT_FLAG = $("#EDIT_FLAG").val();
	var selRowId = $("#selRowId").val();
	framePageInit("channscoremkm.shtml?action=toParentEdit&CHANN_SCORE_MKM_ID="+selRowId);
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(showLabelId){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var url = "channscoremkm.shtml?action=modifyToChildEdit"
	  
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&CHANN_SCORE_MKM_ID="+selRowId);
}