/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_Edit
 * @author wzg
 * @time   2013-08-15 10:17:13 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});


function checkFormSelect(){
	var val = $("#BILL_TYPE").val();
	if(null == val || "" == val){
		parent.showErrorMsg("请选择'单据类型'")
		return false;
	}
	return true;
}

//修改收货方编号的时候 清除下帧的数据
function changChildPrd(){
	var RECV_CHANN_ID_OLD = $("#RECV_CHANN_ID_OLD").val();
	var RECV_CHANN_ID = $("#RECV_CHANN_ID").val();
	if(null != RECV_CHANN_ID_OLD && RECV_CHANN_ID_OLD != RECV_CHANN_ID){
		parent.bottomcontent.clearAllPrd();
	}
	
}
//退货库房ID
function getStoreId(){
	return $("#RETURN_STORE_ID").val();
}