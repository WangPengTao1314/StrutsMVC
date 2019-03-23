
/**
 * @module 预计量上报管理
 * @func 预计量上报渠道设置
 * @version 1.1
 * @author 张忠斌
 */ 
$(function () {
	//框架页面初始化
	framePageInit("","30","65");
	//框架页面初始化
	$("#topcontent").attr("src","forecastchann.shtml?action=toTopPage");
	gotoBottomPage();
});

//bottomcontent页面跳转。
function gotoBottomPage() {
	var url = "forecastchann.shtml?action=childList";
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}




