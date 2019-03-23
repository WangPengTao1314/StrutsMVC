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
	//[STATE][search]add by zzb  分销首页 [待发货预订单]url用到 该参数
	var STATE = $("#STATE").val();
	var search = $("#search").val();
	var isNoEdit = $("#isNoEdit").val();
	var paramUrl=document.getElementById("paramUrl");
	if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("advcinvoice.shtml?action=toList&isNoEdit="+isNoEdit+utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	}else{
		var params = "";
		if(null != search && "" != search){
			params = "&search="+search+"&STATE="+STATE+"&isNoEdit="+isNoEdit;
			params = utf8(params);
		}
		
	    framePageInit("advcinvoice.shtml?action=toList&module=" + module+params);
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
		url = "advcinvoice.shtml?action=childList"
	}else if("label_2" == showLabelId){
		url = "advcinvoice.shtml?action=gchildList"
	}else if("label_4" == showLabelId){
		url = "advcinvoice.shtml?action=toTrace"
	}else{
		url = "advcinvoice.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&ADVC_ORDER_ID="+selRowId);
}