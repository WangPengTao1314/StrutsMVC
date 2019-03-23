/**
 *@module 渠道管理-装修管理
 *@func   装修整改单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//下帧跳转
	parent.window.gotoBottomPage();
	//form校验设置
	InitFormValidator(0);
	mtbSaveListener("rnvtnreform.shtml?action=edit&RNVTN_REFORM_ID="+$("#RNVTN_REFORM_ID").val(),"RNVTN_REFORM_ID");
});
