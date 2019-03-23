
/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */
$(function () {
	//框架页面初始化
	framePageInit("singletree.shtml?action=toList");
	//加载左帧页面
	$("#leftcontent").attr("src", "singletree.shtml?action=showTree");
	treeShowHide();
});

//bottomcontent页面跳转。
function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if (null == action || "label_1" == action) {//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "singletree.shtml?action=" + action + "&JGXXID=" + selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}

