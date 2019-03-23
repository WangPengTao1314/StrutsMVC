/**
 *@module 渠道管理-装修管理
 *@func   装修验收单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//下帧跳转
	parent.window.gotoBottomPage();
	//form校验设置
	InitFormValidator(0);
	//alert($("#RNVTN_CHECK_ID").val());
	mtbSaveListener("rnvtncheck.shtml?action=edit&RNVTN_CHECK_ID="+$("#RNVTN_CHECK_ID").val(),"RNVTN_CHECK_ID");
});
