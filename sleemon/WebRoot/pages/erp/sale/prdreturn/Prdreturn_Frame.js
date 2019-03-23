/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_Frame
 * @author wzg
 * @time   2013-08-19 15:33:31 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var flag = document.getElementById("flag").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("prdreturn.shtml?action=toList"+"&flag="+flag +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("prdreturn.shtml?action=toList&module=" + module+"&flag="+flag);
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
	
	var flag = document.getElementById("flag").value;
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
		url = "prdreturn.shtml?action=childList"+"&flag="+flag
	}else{
		url = "prdreturn.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&PRD_RETURN_ID="+selRowId);
}