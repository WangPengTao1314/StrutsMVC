/**
 * @prjName:喜临门营销平台
 * @fileName:Advise_Frame
 * @author wdb
 * @time   2013-08-17 10:29:35 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var type = $("#CMPL_ADVS_TYPE1").val();
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	if (type != null) {
		framePageInit("advise.shtml?action=toList&CMPL_ADVS_TYPE="+type);
		return;
	}
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("advise.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("advise.shtml?action=toList&module=" + module);
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	var cmplAdvsType = $("#CMPL_ADVS_TYPE").val();
	var url;
	
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}
	
	if("label_4" == showLabelId){
		setLabelSelected("label_4");
		url = "advise.shtml?action=dealProcess";
	}else {
		if (cmplAdvsType == "产品投诉") {
			setLabelSelected("label_1");
			url = "advise.shtml?action=prdcmpl";
		} else if (cmplAdvsType == "服务投诉") {
			setLabelSelected("label_2");
			url = "advise.shtml?action=sercmpl";
		} else if (cmplAdvsType == "建议") {
			setLabelSelected("label_3");
			url = "advise.shtml?action=advs";
		} else {
			return;
		}
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&CMPL_ADVS_ID="+selRowId);
}