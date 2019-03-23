


/**
 * @module  
 * @func  
 * @version 1.1
 * @author  
 */
$(function(){
	//框架页面初始化
	framePageInit("budgetitem.shtml?action=toList");
	$("#leftcontent").attr("src", "budgetitem.shtml?action=showTree");
	treeShowHide();
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("budgetitem.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }else{
	    framePageInit("budgetitem.shtml?action=toList&module=" + module);
	 }
});


function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "budgetitem.shtml?action="+action+"&BUDGET_ITEM_ID="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}

