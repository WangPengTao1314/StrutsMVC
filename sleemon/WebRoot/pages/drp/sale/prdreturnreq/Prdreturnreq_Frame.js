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
	
	//modify zzb  2014-6-21 [STATE][search]  分销首页 [待入库]url用到 该参数
	var STATE = $("#STATE").val();
	var search = $("#search").val();
	var addUrl = "&search="+search+"&STATE="+utf8(STATE);
	
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("prdreturnreq.shtml?action=toList&firstAudit="+addUrl+firstAudit+utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("prdreturnreq.shtml?action=toList&module=" + module+"&firstAudit="+firstAudit+addUrl);
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
		url = "prdreturnreq.shtml?action=childList"
	}else{
		url = "prdreturnreq.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&PRD_RETURN_REQ_ID="+selRowId);
}