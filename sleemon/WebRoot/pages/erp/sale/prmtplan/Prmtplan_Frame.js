/**
 * @prjName:喜临门营销平台
 * @fileName:Prmtplan_Frame
 * @author zzb
 * @time   2013-08-23 16:04:28 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl = document.getElementById("paramUrl");
	var FLAG = $("#FLAG").val();
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("prmtplan.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("prmtplan.shtml?action=toList&module=" + module+"&FLAG="+FLAG);
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
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "prmtplan.shtml?action=childList";
	}else if("label_2"== showLabelId){
		url = "prmtplan.shtml?action=areaList";
	}else{
		url = "prmtplan.shtml?action=toDetail";
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&PRMT_PLAN_ID="+selRowId);
}