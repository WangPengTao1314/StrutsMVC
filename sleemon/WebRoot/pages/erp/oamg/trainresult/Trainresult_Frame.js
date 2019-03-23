/**
 * @prjName:喜临门营销平台
 * @fileName:Trainresult_Frame
 * @author ghx
 * @time   2014-07-10 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("trainresult.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("trainresult.shtml?action=toList&module=" + module);
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
		url = "trainresult.shtml?action=childList";
	}else if("label_2" == showLabelId){
		url = "trainresult.shtml?action=toChildlistObj";
	}else if("label_3" == showLabelId){
		url = "trainresult.shtml?action=toDetail";
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&TRAIN_REQ_ID="+selRowId);
}