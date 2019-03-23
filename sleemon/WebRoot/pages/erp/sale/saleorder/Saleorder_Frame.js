/**
 * @prjName:喜临门营销平台
 * @fileName:Saleorder_Frame
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("saleorder.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("saleorder.shtml?action=toList&module=" + module);
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
		url = "saleorder.shtml?action=childList"
	}else if("label_3" == showLabelId){
		url = "saleorder.shtml?action=toTrace"
	}else{
		url = "saleorder.shtml?action=toDetail"
	}
	
	var module = document.getElementById("module").value;
	
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&SALE_ORDER_ID="+selRowId+"&module="+module);
}