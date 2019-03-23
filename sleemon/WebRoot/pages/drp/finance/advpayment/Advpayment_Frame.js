/**
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_Frame
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	var isNoEdit = $("#isNoEdit").val();
	if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("advpayment.shtml?action=toList&isNoEdit="+isNoEdit +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	}else{	 
	    framePageInit("advpayment.shtml?action=toList&op=fir&module=" + module+"&isNoEdit="+isNoEdit);
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
		url = "advpayment.shtml?action=childList"
	}else{
		url = "advpayment.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&STATEMENTS_ID="+selRowId);
}