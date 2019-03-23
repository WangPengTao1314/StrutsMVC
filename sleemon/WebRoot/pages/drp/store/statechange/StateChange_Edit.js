/**
 * @prjName:喜临门营销平台
 * @fileName:形态转换
 * @author zzb
 * @time   2013-09-05 14:00:34 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});


function changeTextAreaLength(obj) {
	var upper = obj.className;
	if("uppercase"==upper){
		obj.value=obj.value.toUpperCase();
	}
	var maxL = obj.maxlength || obj.maxLength;
	var L = stringLength(obj.value);
	if (L > maxL) {
		obj.value = obj.oVal;
	} else {
		obj.oVal = obj.value;
	}
}