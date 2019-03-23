
/**
 * @module 系统管理
 * @func 系统业务表一览
 * @version 1.1
 * @author  zcx
 */
$(function () {
	//框架页面初始化
	framePageInit("table.shtml?action=toView&TABLE_NAME_EN="+$("#TABLE_NAME_EN").val());
});

//bottomcontent页面跳转。
function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
    if(null == action){
    	action = $("#showLabel").attr("value");
		if("toEdit"==action){
			action="label_1";
		}
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",action);
	}
	//初始化时下帧页面的action
	if (null == action || "label_1" == action) {//"label_1"的判断是为了与主从界面通用
		action = "toTableDtl";
		setLabelSelected("label_1");
	}
	var url = "";
	//alert("COL====="+selRowId);
	if(selRowId !=""){
	  url = "table.shtml?action=" + action + "&TABLE_COL_EN=" + selRowId;
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}

