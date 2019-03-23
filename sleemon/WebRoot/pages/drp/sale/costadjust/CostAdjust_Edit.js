/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_Edit
 * @author lyg
 * @time   2013-08-19 14:35:52 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);
});
function formCheckedEx(){
	if($("#YEAR").val()==""){
		parent.showErrorMsg(utf8Decode("请选择年份！"));
		return false;
	}
	if($("#MONTH").val()==""){
		parent.showErrorMsg(utf8Decode("请选择月份！"));
		return false;
	}
	return true;
}
function selectStore(){
	var OLD_STORE_ID=$("#STORE_ID").val();
	selCommon('BS_35', false, 'STORE_ID', 'STORE_ID', 'forms[0]','STORE_ID,STORE_NO,STORE_NAME','STORE_ID,STORE_NO,STORE_NAME', 'selectParams');
	var STORE_ID=$("#STORE_ID").val();
	if(OLD_STORE_ID!=STORE_ID){
		parent.bottomcontent.delDtl();
	}
}
function getYEAR(){
	return $("#YEAR").val();
}
function getMONTH(){
	return $("#MONTH").val();
}
function getSTORE_ID(){
	return $("#STORE_ID").val();
}
function delDtl(){
	parent.bottomcontent.delDtl();
}