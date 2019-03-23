/**
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_Edit
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){

	if(checkNum("STATEMENTS_AMOUNT","本次收款金额")==false){
		return false;
	}
	else{
		return checkNum("DEDUCT_AMOUNT","本次扣款金额");
	}
}

function checkNum(inputId,inputName){
    
    var STATEMENTS_AMOUNT = $("#"+inputId).val();
    
    if(STATEMENTS_AMOUNT.indexOf(".")>=0){//如果包含小数点
    	var aa = STATEMENTS_AMOUNT.split(".");
    	if(aa[0].length>12){
    		parent.showErrorMsg("'" + inputName +"'必须输入一个数字，最多只能12位整数，两位小数！");
    		return false;
    	}
    	if(aa[1].length>2){
    		parent.showErrorMsg("'" + inputName +"'必须输入一个数字，最多只能12位整数，两位小数！");
    		return false;
    	}
    }else{
    	if(STATEMENTS_AMOUNT.length>12){
    		parent.showErrorMsg("'" + inputName +"'必须输入一个数字，最多只能12位整数，两位小数！");
    		return false;
    	}
    }
   return true;

}

//查询预定单列表
 function queryOrderNo(){
 
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" DEL_FLAG=0 and STATE in ('审核通过','待发货','已发货','待结算','已结算') and LEDGER_ID = '"+ztxxid+"'");
 	
 	//为了不让日期自动添加到查询条件中，先清空
 	$("#SALE_DATE_HIDDEN").val($("#SALE_DATE").val());
 	$("#ORDER_RECV_DATE_HIDDEN").val($("#ORDER_RECV_DATE").val());
 	$("#SALE_DATE").val("");
 	$("#ORDER_RECV_DATE").val("");
 	
	var returnArray = selCommon('BS_55', false, 'ADVC_ORDER_ID', 'ADVC_ORDER_ID', 'forms[0]','ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_NAME,CUST_NAME,TEL,ORDER_RECV_DATE,ADVC_AMOUNT,PAYABLE_AMOUNT,PAYED_TOTAL_AMOUNT,DEDUCTED_TOTAL_AMOUNT', 'ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_NAME,CUST_NAME,TEL,ORDER_RECV_DATE,ADVC_AMOUNT,PAYABLE_AMOUNT,PAYED_TOTAL_AMOUNT,DEDUCTED_TOTAL_AMOUNT','con');
	
	//如果通用选取没有变化，则把以前的日期回填到表格中
	if(returnArray[1]==false){
		$("#SALE_DATE").val($("#SALE_DATE_HIDDEN").val());
		$("#ORDER_RECV_DATE").val($("#ORDER_RECV_DATE_HIDDEN").val());
	}
	
	$("#con").val("");
 }
 
