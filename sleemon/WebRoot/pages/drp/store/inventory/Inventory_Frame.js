/**
 * @prjName:喜临门营销平台
 * @fileName:Inventory_Frame
 * @author lyg
 * @time   2013-09-07 09:54:59 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("inventory.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("inventory.shtml?action=toList&module=" + module);
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		if(!"toEdit"==showLabelId){
			$("#showLabel").attr("value",showLabelId);
		}
	}
	var url;
	if("label_1" == showLabelId){
		url = "inventory.shtml?action=childList"
	}else if("toEdit"==showLabelId){
		url = "inventory.shtml?action=toParentEdit"
	}else{
		url = "inventory.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&PRD_INV_ID="+selRowId);
}
