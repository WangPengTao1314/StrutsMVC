/**
 * @prjName:喜临门营销平台
 * @fileName:Advise_Frame
 * @author wdb
 * @time   2013-08-17 10:29:35 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("advisehd.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("advisehd.shtml?action=toList&module=" + module);
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
		url = "advisehd.shtml?action=dealProcess";
	}else{
		if (cmplAdvsType == "产品投诉") {
			setLabelSelected("label_1");
			url = "advisehd.shtml?action=prdcmpl";
		} else if (cmplAdvsType == "服务投诉") {
			setLabelSelected("label_2");
			url = "advisehd.shtml?action=sercmpl";
		} else if (cmplAdvsType == "建议") {
			setLabelSelected("label_3");
			url = "advisehd.shtml?action=advs";
		} else {
			//默认 一览页面没有记录的话 子页面选择label_1
			setLabelSelected("label_1");
			url = "advisehd.shtml?action=prdcmpl";
		}
	}
	
	
	
/**	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		设置当前显示的标签页		$("#showLabel").attr("value",showLabelId);
	}
	if("label_1" == showLabelId){
		setLabelSelected("label_1");
		url = "advisehd.shtml?action=prdcmpl";
	}else if ("label_2" == showLabelId) {
		setLabelSelected("label_2");
		url = "advisehd.shtml?action=sercmpl";
	} else if ("label_3" == showLabelId) {
		setLabelSelected("label_3");
		url = "advisehd.shtml?action=advs";
	} else {
		setLabelSelected("label_4");
		url = "advisehd.shtml?action=dealProcess";
	}*/
	
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&CMPL_ADVS_ID="+selRowId);
}