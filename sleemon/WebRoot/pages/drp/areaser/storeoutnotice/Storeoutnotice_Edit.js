/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});
 //库房通用选取
function storeSelCommon(){
	var chann=$("#chann").val();
	$("#selectParams").val(" STATE ='启用' and DEL_FLAG='0' and BEL_CHANN_ID='"+chann+"' ");
	var temp_STOREOUT_STORE_ID=$("#STOREOUT_STORE_ID").val();
	selCommon('BS_35', false, 'STOREOUT_STORE_ID', 'STORE_ID', 'forms[0]','STOREOUT_STORE_ID,STOREOUT_STORE_NO,STOREOUT_STORE_NAME','STORE_ID,STORE_NO,STORE_NAME', 'selectParams');
	var STOREOUT_STORE_ID=$("#STOREOUT_STORE_ID").val();
	if(STOREOUT_STORE_ID!=temp_STOREOUT_STORE_ID){
		parent.bottomcontent.delDtl();
	}
}
function getFrom(){
	var FROM_BILL_ID=$("#FROM_BILL_ID").val();
	return FROM_BILL_ID
}
function setAmoutVal(v){
	$("#STOREOUT_AMOUNT").val(v);
}
//来源单据通用选取
function selFrom(){
	var FROM_BILL_ID_TEMP=$("#FROM_BILL_ID").val();
	selCommon('BS_80', false, 'FROM_BILL_ID', 'SALE_ORDER_ID', 'forms[0]',
			'FROM_BILL_ID,FROM_BILL_NO,RECV_CHANN_ID,RECV_CHANN_NO,RECV_CHANN_NAME,RECV_ADDR_NO,RECV_ADDR,SALE_DATE', 
			'SALE_ORDER_ID,SALE_ORDER_NO,RECV_CHANN_ID,RECV_CHANN_NO,RECV_CHANN_NAME,RECV_ADDR_NO,RECV_ADDR,ORDER_DATE', 
			'selectFrom')
	var FROM_BILL_ID=$("#FROM_BILL_ID").val();
	if(FROM_BILL_ID!=FROM_BILL_ID_TEMP){
		parent.bottomcontent.delDtl();
	}
}



function getStoreId(){
	return $("#STOREOUT_STORE_ID").val();
}
