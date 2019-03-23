/**
 * @prjName:喜临门营销平台
 * @fileName:Store_Edit
 * @author wdb
 * @time   2013-08-13 14:01:22 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	//parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
	//添加浮动按钮层的监听
	addFloatDivListener();
	var selId = parent.document.getElementById("selRowId").value;
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("store.shtml?action=edit","STORE_ID");
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
		
});

//根据是否终端选择，设置所属单位通用选取
function getBelUnit(){
	var flag = $("#TERM_STROE_FLAG").val();
	if(flag == 1){
		selCommon('BS_27', false, 'BEL_CHANN_NO', 'TERM_ID', 'forms[0]','BEL_UNIT_ID,BEL_CHANN_NO,BEL_UNIT_NAME','TERM_ID,TERM_NO,TERM_NAME', 'TERM_CON');
	} else {
		selCommon('BS_19', false, 'BEL_CHANN_NO', 'CHANN_ID', 'forms[0]','BEL_UNIT_ID,BEL_CHANN_NO,BEL_UNIT_NAME','CHANN_ID,CHANN_NO,CHANN_NAME', 'CHANN_CON');
	}
}

//库房类型必填项验证
function formCheckedEx() {
	var storeType = $("#STORE_TYPE").val();
	if (storeType == "") {
		chkCanErrMsg("", "请选择'库房类型'");
		return false;
	}
	return true;
}
function upStoreFlag(flag){
	$("#TERM_STROE_FLAG").val(flag);
	if("0"==flag){
		$("#BEL_UNIT_ID").val($("#CHANN_ID").val());
		$("#BEL_CHANN_NO").val($("#CHANN_NO").val());
		$("#BEL_UNIT_NAME").val($("#CHANN_NAME").val());
	}else{
		$("#BEL_UNIT_ID").val("");
		$("#BEL_CHANN_NO").val("");
		$("#BEL_UNIT_NAME").val("");
	}
	
}
