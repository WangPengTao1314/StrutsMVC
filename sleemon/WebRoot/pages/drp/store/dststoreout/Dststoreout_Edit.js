/**
 * @prjName:供应链_贵人
 * @fileName:Dststoreout_Edit
 * @author zsl
 * @time   2016-01-11 15:05:08 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});
function selAdvcorder(){
	var FROM_BILL_ID_TEMP=$("#FROM_BILL_ID").val();
	var BMXXID=$("#BMXXID").val();
	selCommon('BS_195', false, 
		'FROM_BILL_ID', 'ADVC_ORDER_ID', 'forms[0]','FROM_BILL_ID,FROM_BILL_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_ID,SALE_PSON_NAME,CUST_NAME,TEL,ORDER_RECV_DATE,RECV_ADDR,REMARK,STOREOUT_AMOUNT', 
		'ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_ID,SALE_PSON_NAME,CUST_NAME,TEL,ORDER_RECV_DATE,RECV_ADDR,REMARK,PAYABLE_AMOUNT', "selectAdvc");
	var FROM_BILL_ID=$("#FROM_BILL_ID").val();
	if(FROM_BILL_ID!=FROM_BILL_ID_TEMP){
		parent.bottomcontent.queryDtl(FROM_BILL_ID);
	}
}
function getFROM_BILL_ID(){
	var FROM_BILL_ID=$("#FROM_BILL_ID").val();
	return FROM_BILL_ID;
}
function getSTOREOUT_STORE_ID(){
	var STOREOUT_STORE_ID=$("#STOREOUT_STORE_ID").val();
	return STOREOUT_STORE_ID;
}
function selStoreout(){
	var FROM_BILL_NO=$("#FROM_BILL_NO").val();
	if(""==FROM_BILL_NO||null==FROM_BILL_NO){
		parent.showErrorMsg("请先选择来源单据");
		return;
	}
	var BMXXID=$("#BMXXID").val();
	var TERM_ID=$("#TERM_ID").val();
	var STOREOUT_STORE_ID_TEMP=$("#STOREOUT_STORE_ID").val();
	var SEND_CHANN_ID =	$("#SEND_CHANN_ID").val();
//	if(SEND_CHANN_ID==null || SEND_CHANN_ID ==''){
//	    alert('请先选择发货方!');
//		return;
//	}
	var selectSTORECondition =" STATE ='启用' and  DEL_FLAG=0 and BEL_CHANN_ID in ('"+TERM_ID+"','"+BMXXID+"') ";
	$("#selectSTORECondition").val(selectSTORECondition);
	selCommon('BS_35', false, 'STOREOUT_STORE_ID', 'STORE_ID', 'forms[0]','STOREOUT_STORE_ID,STOREOUT_STORE_NO,STOREOUT_STORE_NAME', 'STORE_ID,STORE_NO,STORE_NAME','selectSTORECondition')
	var STOREOUT_STORE_ID=$("#STOREOUT_STORE_ID").val();
//	if(STOREOUT_STORE_ID!=STOREOUT_STORE_ID_TEMP){
//		parent.bottomcontent.delDtl();
//	}
}