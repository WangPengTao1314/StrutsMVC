


/**
 * @module 系统管理
 * @func 货品信息
 * @version 1.1
 * @author 刘曰刚
 */
$(function(){
	//框架页面初始化
	framePageInit("product.shtml?action=toDrpList");
});
function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){//"label_1"的判断是为了与主从界面通用
		action = "toDrpDetail";
	}
	var url = "product.shtml?action="+action+"&PRD_ID="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}
