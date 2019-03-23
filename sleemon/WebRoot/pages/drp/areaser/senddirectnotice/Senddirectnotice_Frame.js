/**
 * @prjName:喜临门营销平台
 * @fileName:prdreturnreq_Frame
 * @author wzg
 * @time   2013-08-15 10:17:13 
 * @version 1.1
 */
$(function(){
	
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	var firstAudit = document.getElementById("firstAudit").value;
	
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("senddirectnotice.shtml?action=toList&firstAudit="+firstAudit+utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("senddirectnotice.shtml?action=toList&module=" + module+"&firstAudit="+firstAudit);
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
		url = "senddirectnotice.shtml?action=childList"
	}else{
		url = "senddirectnotice.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&BASE_DELIVER_NOTICE_ID="+selRowId);
}