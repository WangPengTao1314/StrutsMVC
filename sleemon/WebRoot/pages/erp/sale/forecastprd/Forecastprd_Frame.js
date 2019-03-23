
/**
 * @module 预计量上报管理
 * @func 预计量上报货品设置
 * @version 1.1
 * @author 张忠斌
 */ 
$(function () {
	//框架页面初始化
	framePageInit("","30","65");
	//框架页面初始化
	$("#topcontent").attr("src","forecastprd.shtml?action=toTopPage");
	gotoBottomPage();
});

//bottomcontent页面跳转。
function gotoBottomPage() {
	var url = "forecastprd.shtml?action=childList";
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}




