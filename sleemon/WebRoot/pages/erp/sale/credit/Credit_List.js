/**
 *@module 基础数据
 *@func 信用额度设定
 *@version 1.1
 *@author 郭利伟
 */
$(function(){
	//页面初始化
	listPageInit("credit.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("credit?action=delete","CHANN_ID");
	var selRowId = $("#selRowId").val();
	if(null == selRowId || "" == selRowId){
	   btnDisable(["start","modify","delete","stop"]);
	   return;
    }
});	

