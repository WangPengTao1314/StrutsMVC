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
	return true;
}


function changeTerm(){
	var CHANN_ID = $("#CHANN_ID").val();
	$("#selTermParam").val(" CHANN_ID_P='"+CHANN_ID+"' and DEL_FLAG=0 and STATE='启用' ");
}
  
function getMARKETING_ACT_ID(){
   return $("#MARKETING_ACT_ID").val();
}
 
 
function setSALE_CARD_AMOUNT(total){
	$("#SALE_CARD_AMOUNT").val(total);
}

function getCHANN_ID(){
	return $("#CHANN_ID").val();
}
 