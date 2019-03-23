/**
*@module 领用管理

*@func 材料物资零星领料单

*@version 1.1

*@author 花金石
*/


$(function(){
	//框架页面初始化 
	framePageInit("customQuery.shtml?action=toEditTableList");
});

//bottomcontent页面跳转。
function gotoBottomPage(showLabelId){ 
	var selRowId = $("#selRowId").val();
	//下帧页面跳转
	$("#bottomcontent").attr("src","customQuery.shtml?action=toTableDateDtl"+"&tablePk="+selRowId);
} 