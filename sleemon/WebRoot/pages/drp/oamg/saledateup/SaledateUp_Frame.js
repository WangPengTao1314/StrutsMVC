/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
$(function(){
	 //框架页面初始化
	 var module = document.getElementById("module").value;
	 var flag = $("#flag").val();
	 var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("saledateup.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }else{	 
	    framePageInit("saledateup.shtml?action=toList&module=" + module+"&flag="+flag);
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
		url = "saledateup.shtml?action=childList"
	}else{
		url = "saledateup.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&SALE_DATE_UP_ID="+selRowId);
}

 


 