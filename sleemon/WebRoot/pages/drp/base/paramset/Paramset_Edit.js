/**
 *@module 系统管理
 *@fuc 渠道参数设置
 *@version 1.0
 *@author 
 */
$(function() {
	//form校验设置
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	 
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	var DATA_NAME = $("#DATA_NAME").val();
	mtbSaveListener("paramset.shtml?action=edit", "DATA_CONF_ID");
});
