


/**
 * @module  
 * @func  
 * @version 1.1
 * @author  
 */
$(function(){
	//框架页面初始化
	framePageInit("budgetquota.shtml?action=toList");
	$("#leftcontent").attr("src", "budgetquota.shtml?action=showTree");
	treeShowHide();
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("budgetquota.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }else{
	    framePageInit("budgetquota.shtml?action=toList&module=" + module);
	 }
});


function gotoBottomPage(action){
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "budgetquota.shtml?action="+action+"&BUDGET_QUOTA_ID="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
}

