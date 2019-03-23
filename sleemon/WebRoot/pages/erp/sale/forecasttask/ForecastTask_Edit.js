/**
  *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	mtbSaveListener("forecasttask.shtml?action=edit","ADVC_RPT_JOB_ID");
});

