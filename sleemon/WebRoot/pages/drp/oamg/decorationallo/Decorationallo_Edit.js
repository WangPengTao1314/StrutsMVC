/**
 *@module 渠道管理-装修管理
 *@func  装修补贴标准维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//下帧跳转
	parent.window.gotoBottomPage();
	//form校验设置
	InitFormValidator(0);
});

function  load(){
  var REMARK = $("#REMARK1").val();
  document.getElementById("REMARK").innerText = REMARK;
}
