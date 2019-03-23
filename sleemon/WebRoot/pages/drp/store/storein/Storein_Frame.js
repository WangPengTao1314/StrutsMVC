/**
 * @prjName:喜临门营销平台
 * @fileName:Storein_Frame
 * @author glw
 * @time   2013-08-19 16:55:43 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("storein.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	 
	    framePageInit("storein.shtml?action=toList&module=" + module);
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
		url = "storein.shtml?action=childList"
	} else if ("label_2" == showLabelId) {
		var ids = "";
		$("#bottomcontent").contents().find("#ordertb tr:gt(0) input:checked").each(function(){
				ids = ids+"'"+$(this).val()+"',";
		});
		ids = ids.substr(0,ids.length-1);
		url = "storein.shtml?action=grandChildList&STOREIN_DTL_ID="+ids;
		
	} else {
		url = "storein.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&STOREIN_ID="+selRowId);
}

