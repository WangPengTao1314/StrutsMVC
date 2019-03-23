/**
 * @prjName:喜临门营销平台
 * @fileName:Repairapp_Edit
 * @author chenj
 * @time   2013-11-03 16:25:12 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});

//清空字表记录
function clearChild(){
	var STOREOUT_STORE_ID_OLD = $("#STOREOUT_STORE_ID_OLD").val();
	var STOREOUT_STORE_ID = $("#STOREOUT_STORE_ID").val();
	if(STOREOUT_STORE_ID_OLD == STOREOUT_STORE_ID){
		return;
	}
	parent.bottomcontent.clearValue();
	 
}


function setAddrParams(){
	var CHANN_ID = $("#REPAIR_CHANN_ID").val();
	if(null == CHANN_ID || "" == CHANN_ID){
		parent.showErrorMsg("请选择'"+$("#REPAIR_CHANN_ID").attr("label")+"'");
		return;
	}
	var v = " DEL_FLAG=0 and STATE='启用' and CHANN_ID='"+CHANN_ID+"'";
	$("#selectAddrParams").val(v);
}



