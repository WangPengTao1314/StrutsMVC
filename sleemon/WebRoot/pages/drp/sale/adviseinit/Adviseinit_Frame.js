/**
 * @prjName:喜临门营销平台
 * @fileName:Adviseinit_Frame
 * @author wdb
 * @time   2013-08-13 14:01:22 
 * @version 1.1
 */
$(function(){
	setLabelSelected("label_1");
	gotoBottomPage("label_1");
	$("#label span").click(function(){
		setLabelSelected($(this).attr("id"));
		gotoBottomPage($(this).attr("id"));
	});
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
		url = "adviseinit.shtml?action=prdcmplEdit"
	}else if("label_2" == showLabelId){
		url = "adviseinit.shtml?action=sercmplEdit"
	}else {
		url = "adviseinit.shtml?action=advsEdit"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&STORE_ID="+selRowId);
}