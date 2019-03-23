/**
 *@module 渠道管理-装修管理
 *@func   装修验收单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//框架页面初始化
	framePageInit("rnvtncheck.shtml?action=toParentEdit&RNVTN_CHECK_ID="+$("#RNVTN_CHECK_ID").val());
});

//bottomcontent页面跳转.点击标签，根据选中的记录查询子表信息。
function gotoBottomPage(showLabelId){
	//获取当前选中的记录
	var selRowId = $("#RNVTN_CHECK_ID").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_1" == showLabelId){
		url = "rnvtncheck.shtml?action=modifyToChildEdit";
	}//else if //如果是多个直接定义即可
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&RNVTN_CHECK_ID="+selRowId);
}
