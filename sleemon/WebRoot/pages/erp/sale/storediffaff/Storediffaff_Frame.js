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
	    framePageInit("storediffaff.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("storediffaff.shtml?action=toList&module=" + module);
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
		//url = "storediffaff.shtml?action=childList"
		//$("#STOREIN_DIFF_DTL_ID").val("");	
		url = "storediffaff.shtml?action=dealList";
	}else if("label_2" == showLabelId){
		url = "storediffaff.shtml?action=childList"
	}
	else{
		url = "storediffaff.shtml?action=toDetail"
	}

	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&STOREIN_DIFF_ID="+selRowId);
}

function dealClick(){
	var url ="";
	var STOREIN_DIFF_ID = $("#selRowId").val();
	url = "storediffaff.shtml?action=dealList&STOREIN_DIFF_ID="+STOREIN_DIFF_ID+"&STOREIN_DIFF_DTL_ID="+STOREIN_DIFF_DTL_ID;
	return url;
}



