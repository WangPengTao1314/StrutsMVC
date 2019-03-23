/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Frame
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl = document.getElementById("paramUrl");
	if(null != paramUrl && paramUrl.value != ""){
	    framePageInit("advcgoodslook.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	}else{	 
	    framePageInit("advcgoodslook.shtml?action=toList&module=" + module);
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
		url = "advcgoodslook.shtml?action=childList"
	}else if("label_2" == showLabelId){
		url = "advcgoodslook.shtml?action=toDetail"
	}else{
		url = "advcgoodslook.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&ADVC_ORDER_ID="+selRowId);
}

