
/**
 *@module 渠道管理-终端管理
 *@func   门店变更申请单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("terminalchange.shtml?action=edit","TERMINAL_CHANGE_ID");
});

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	//详细地址 
	if($("#ADDRESS").val()==null || $("#ADDRESS").val() == ""){
	      parent.showErrorMsg(utf8Decode("请填入详细地址！"));
	      return false;
	}
	return true;
}