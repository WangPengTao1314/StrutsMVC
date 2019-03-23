/**
 * @prjName:喜临门营销平台
 * @fileName:Prmtplan_Edit
 * @author zzb
 * @time   2013-08-23 16:04:28 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});


//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(formId){
	var unitType = $("#PRMT_TYPE").val();
    var startTime = $("#EFFCT_DATE_BEG").val();
	var endTime = $("#EFFCT_DATE_END").val();
	
	if (!InputCheck("#PRMT_PLAN_NAME")) {
			return false;
	}
	
	if (unitType == "") {
		chkCanErrMsg("", "请选择'促销类型'");
		return false;
	}	
	 
	if (!InputCheck("#EFFCT_DATE_BEG")) {
			return false;
	}
	
	if (!InputCheck("#EFFCT_DATE_END")) {
			return false;
	}
	
	if(endTime<startTime){
		parent.showErrorMsg("生效结束日期不得小于开始日期");
		return false;
	}
	
	return true;
}