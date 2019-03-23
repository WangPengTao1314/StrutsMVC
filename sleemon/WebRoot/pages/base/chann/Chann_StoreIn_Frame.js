
/**
 * @module 系统管理
 * @func 批量维护区域经理
 * @version 1.1
 * @author zcx
 */
$(function () {
    //框架页面初始化
	framePageInit("","33","65");
	//框架页面初始化
	$("#topcontent").attr("src","chann.shtml?action=toTopStoreIn");
	gotoBottomPage();
});

//bottomcontent页面跳转。
 function gotoBottomPage() {
	var url = "chann.shtml?action=toStoreInListT";
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}

