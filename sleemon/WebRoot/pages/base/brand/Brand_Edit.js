/**
 *@module 基础数据
 *@func品牌信息
 *@version 1.1
 *@author 郭利伟
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	mtbSaveListener("brand.shtml?action=edit","BRAND_ID");
});

