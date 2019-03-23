
/**
 * @module 系统管理
 * @func 终端信息
 * @version 1.1
 * @author 刘曰刚
 */
$(function () {
	//框架页面初始化
	framePageInit("drpsaleprice.shtml?action=toList");
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("drpsaleprice.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else{
	    framePageInit("drpsaleprice.shtml?action=toList&module=" + module);
	 }
});

//bottomcontent页面跳转。
function gotoBottomPage(action,PRD_ID,RETAL_PRICE_ID) {
	//获取当前选中的记录
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
		action = "toDetail";
		setLabelSelected("label_1");
	}
	var url = "drpsaleprice.shtml?action=" + action + "&RETAL_PRICE_ID=" + RETAL_PRICE_ID+"&PRD_ID="+PRD_ID;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}
function getToDetail(PRD_ID,RETAL_PRICE_ID) {
	action = "toDetail";
	var url = "drpsaleprice.shtml?action=" + action + "&RETAL_PRICE_ID=" + RETAL_PRICE_ID+"&PRD_ID="+PRD_ID;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}

