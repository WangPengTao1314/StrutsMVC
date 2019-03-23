/**
 *@module 渠道管理-终端管理
 *@func   新开门店申请单维护
 *@version 1.1
 *@author zcx
 */

$(function() {
	var EDIT_FLAG = $("#EDIT_FLAG").val();
	var selRowId = $("#selRowId").val();
	if("CHILD_EDIT" == EDIT_FLAG){
		framePageInit("openterminal.shtml?action=toChildEdit&OPEN_TERMINAL_REQ_ID="+selRowId);
	}else{
		framePageInit("openterminal.shtml?action=toParentEdit");
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

