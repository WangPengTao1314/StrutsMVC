/**
 * @prjName:喜临门营销平台
 * @fileName:Storediff_Frame
 * @author wzg
 * @time   2013-08-30 14:03:21 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("storediff.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("storediff.shtml?action=toList&module=" + module);
	 }	
//	 $("label_2").click(function(){
//		 
//		 var selRowId = $("#selRowId").val();
//		 
//		 var url = "storediff.shtml?action=dealList";
//		 
//		 $("#bottomcontent").attr("src",url+"&STOREIN_DIFF_ID="+selRowId);
//		 
//	 });
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		if("label_1" == showLabelId || "label_2" == showLabelId || "label_3" == showLabelId){
			$("#showLabel").attr("value",showLabelId);
		}
	}

	var url;
	if("label_1" == showLabelId){
		url = "storediff.shtml?action=childList"
		$("#STOREIN_DIFF_DTL_ID").val("");	
	}else if("label_2" == showLabelId){
		url = dealClick();
		if("" == url)
		    url = "storediff.shtml?action=childList"
	}
	else{
		url = "storediff.shtml?action=toDetail"
	}

	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&STOREIN_DIFF_ID="+selRowId);
}

function dealClick(){
	
	var url ="";
	
	var STOREIN_DIFF_ID = $("#selRowId").val();
	var STOREIN_DIFF_DTL_ID = $("#STOREIN_DIFF_DTL_ID").val();
	if("" == STOREIN_DIFF_DTL_ID){
		showErrorMsg("入库差异明细请至少选择一条记录");
		$("#showLabel").attr("value","label_1");
		$("#label_1").css("background-image","url('"+ctxPath+"/styles/"+theme+"/images/main/divtotext_active.jpg')")
	 	.siblings().css("background-image","url('"+ctxPath+"/styles/"+theme+"/images/main/divtotext_leave.jpg')");
		
		return url;
	}
	
	url = "storediff.shtml?action=dealList&STOREIN_DIFF_ID="+STOREIN_DIFF_ID+"&STOREIN_DIFF_DTL_ID="+STOREIN_DIFF_DTL_ID;
	
	return url;
}



