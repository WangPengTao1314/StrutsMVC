/**
 *@module 系统管理
 *@fuc 单位维护js
 *@version 1.0
 *@author 王栋斌
 */
$(function() {
	//form校验设置
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//单位类型必填项验证
	//selectCheck();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("unit.shtml?action=edit", "MEAS_UNIT_ID");
});

//单位类型必填项验证
function formCheckedEx() {
	var unitType = $("#UNIT_TYPE").val();
	if (unitType == "") {
		chkCanErrMsg("", "请选择'单位类型'");
		return false;
	}
	return true;
}
