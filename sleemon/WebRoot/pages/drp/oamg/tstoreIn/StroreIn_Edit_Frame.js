/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_Frame
 * @author glw
 * @time   2013-08-17 17:07:03 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	framePageInit("tstoreIn.shtml?action=toParentEdit");
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
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
		url = "tstoreIn.shtml?action=modifyToChildEdit"
	}//else if //如果是多个直接定义即可
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&STOREIN_NOTICE_ID="+selRowId);
}