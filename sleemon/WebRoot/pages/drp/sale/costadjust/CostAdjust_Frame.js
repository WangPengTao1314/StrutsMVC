/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_Frame
 * @author lyg
 * @time   2013-08-19 14:35:52 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("costadjust.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("costadjust.shtml?action=toList&module=" + module);
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
		url = "costadjust.shtml?action=childList"
	}else{
		url = "costadjust.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&COST_ADJUST_ID="+selRowId);
}