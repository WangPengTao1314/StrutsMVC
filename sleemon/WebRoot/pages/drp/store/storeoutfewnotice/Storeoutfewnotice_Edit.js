/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_Edit
 * @author glw
 * @time   2013-08-17 17:07:03 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);
})
function getStoreId(){
	return $("#STOREOUT_STORE_ID").val();
}