/**
 * @prjName:喜临门营销平台
 * @fileName:Repairapp_Frame
 * @author chenj
 * @time   2013-11-03 16:25:12 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("repairapp.shtml?action=toList&frameTolist=frameTolist" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("repairapp.shtml?action=toList&module=" + module+"&frameTolist=frameTolist");
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
	var module = document.getElementById("module").value;
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "repairapp.shtml?action=childList&module=" + module
	}else{
		url = "repairapp.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&ERP_REPAIR_ORDER_ID="+selRowId);
}