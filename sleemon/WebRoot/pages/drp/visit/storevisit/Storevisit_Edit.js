
/**
 *@module 渠道管理-拜访管理
 *@func   门店拜访表维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("storevisit.shtml?action=edit","STORE_VISIT_ID");
});

function formCheckedEx() {
	var storeType = $("#VISIT_TYPE").val();
	if (storeType == "") {
		chkCanErrMsg("", "请选择'拜访方式'");
		return false;
	}
	return true;
}