
/**
 * @module 系统管理
 * @func 渠道分管
 * @version 1.1
 * @author 张忠斌
 */
$(function () {
	//框架页面初始化
	framePageInit("","30","70");
	var XTYHID = $("#XTYHID").val();
	//框架页面初始化
	$("#topcontent").attr("src","xtyh.shtml?action=toTopPage&XTYHID="+XTYHID);
	gotoBottomPage();
});

//bottomcontent页面跳转。
function gotoBottomPage() {
	var url = "xtyh.shtml?action=userList";
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}




