/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});

 

function formCheckedEx(){
	var REMARK = $("#REMARK").val();
	if(null == REMARK || "" == $.trim(REMARK)){
		parent.showErrorMsg("请输入'活动内容'");
		return false;
	}
	return true;
}
 
  
 
 
 