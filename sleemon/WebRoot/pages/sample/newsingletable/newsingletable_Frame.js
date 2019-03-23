/**
 * @module Sample
 * @func 单表示例
 * @version 1.1
 * @author zhuxw
 */
$(function () {
	//框架页面初始化
	framePageInit("newsingletable.shtml?action=toList");
	//加载左帧页面
	//$("#leftcontent").attr("src", "newsingletable.shtml?action=showTree");
	
	
});

//bottomcontent页面跳转。
function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if (null == action || "label_1" == action) {//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "newsingletable.shtml?action=" + action + "&JGXXID=" + selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}

