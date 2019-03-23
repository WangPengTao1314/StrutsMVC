/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time  2014-12-03 10:35:10 
 * @version 1.1
 */
$(function(){
	 //框架页面初始化
	 var module = document.getElementById("module").value;
	 var paramUrl = document.getElementById("paramUrl");
	 if(paramUrl != null && paramUrl.value != ""){
	    framePageInit("erpadvcorderstatement.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }else{	 
	    framePageInit("erpadvcorderstatement.shtml?action=toList&module=" + module);
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
	if("label_2" == showLabelId){
		url = "erpadvcorderstatement.shtml?action=toDetail"
	}else if("label_3" == showLabelId){
		url = "erpadvcorderstatement.shtml?action=paymentList"
	}else{
		url ="erpadvcorderstatement.shtml?action=childList"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&ADVC_STATEMENTS_ORDER_ID="+selRowId);
}
  
 