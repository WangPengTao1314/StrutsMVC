/**
 * @prjName:喜临门营销平台
 * @fileName:Allocate_Edit
 * @author lyg
 * @time   2013-09-05 13:29:12 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});
function formCheckedEx(){
	//验证单据类型不为空
		if($("#BILL_TYPE").val()==null || $("#BILL_TYPE").val() == ""){
	      	   parent.showErrorMsg(utf8Decode("请选择单据类型！"));
	           return false;
		}
	//验证库位管理标记是否被选中
//		if($("#STORAGE_FLAG").val()==null || $("#STORAGE_FLAG").val() == ""){
//			 parent.showErrorMsg(utf8Decode("请选择库位管理标记！"));
//	           return false;
//		}
		return true;
}
//调出库房通用选取
function selOUT_STORE(){
	var ALLOC_OUT_STORE_ID_TEMP=$("#ALLOC_OUT_STORE_ID").val();
	var ALLOC_OUT_CHANN_ID=$("#ALLOC_OUT_CHANN_ID").val();
	if(ALLOC_OUT_CHANN_ID==null||ALLOC_OUT_CHANN_ID==""){
		alert("请先选择调出方编号");
		return;
	}else{
		$("#selectParam").val(" STATE='启用'  and TERM_STROE_FLAG=0 and DEL_FLAG='0' and BEL_CHANN_ID='"+ALLOC_OUT_CHANN_ID+"'");
		selCommon('BS_35', false, 'ALLOC_OUT_STORE_ID', 'STORE_ID', 'forms[0]','ALLOC_OUT_STORE_ID,ALLOC_OUT_STORE_NO,ALLOC_OUT_STORE_NAME', 'STORE_ID,STORE_NO,STORE_NAME', 'selectParam')
		var ALLOC_OUT_STORE_ID=$("#ALLOC_OUT_STORE_ID").val();
		if(ALLOC_OUT_STORE_ID_TEMP!=ALLOC_OUT_STORE_ID){
			parent.bottomcontent.delDtl();
		}
	}
}
//当重新选取调出方时 清空出库信息
function clearALLOC_OUT_STORE(){
	$("#ALLOC_OUT_STORE_ID").val("");
	$("#ALLOC_OUT_STORE_NO").val("");
	$("#ALLOC_OUT_STORE_NAME").val("");
	$("#STORAGE_FLAG_YES").attr("checked",false);
	$("#STORAGE_FLAG_NO").attr("checked",false);
}
function getALLOC_OUT_STORE_ID(){
	return $("#ALLOC_OUT_STORE_ID").val();
}