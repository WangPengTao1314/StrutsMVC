/**
  *@module 系统管理
  *@fuc 数据字典编辑框架
  *@version 1.1
  *@author 张羽
*/

$(function(){
	//框架页面初始化
	var selRowId = $("#selRowId").val();
	var freezeDays = $("#freezeDays").val();
	framePageInit("paramset.shtml?action=toParentEdit&DATA_CONF_ID="+selRowId+"&freezeDays="+freezeDays);
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
		url = "sjzd.shtml?action=modifyToChildEdit";
	}//else if //如果是多个直接定义即可
	//下帧页面跳转
	var chistate=$("#chistate").val();
	$("#bottomcontent").attr("src",url+"&SJZDID="+selRowId+"&chistate="+utf8(chistate));
}
