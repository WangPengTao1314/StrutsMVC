﻿/**
 * @prjName:喜临门营销平台
 * @fileName:Trainreq_Frame
 * @author ghx
 * @time   2014-02-28 14:01:04 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	framePageInit("trainreq.shtml?action=toParentEdit");
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
		url = "trainreq.shtml?action=modifyToChildEdit"
	}//else if //如果是多个直接定义即可
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&TRAIN_REQ_ID="+selRowId);
}