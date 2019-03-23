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

	var reg = new RegExp("^([0-9]{1,12})+(.[0-9]{0,2})?$", "g");  
    if (!reg.test($("#PAYABLE_AMOUNT").val())){  
        parent.showErrorMsg("'应收总额'必须输入一个数字，最多只能12位整数，两位小数！");  
        return false;
    }
    
    var PAYABLE_AMOUNT = $("#PAYABLE_AMOUNT").val();
    
    if(PAYABLE_AMOUNT.indexOf(".")>=0){//如果包含小数点
    	var aa = PAYABLE_AMOUNT.split(".");
    	if(aa[0].length>12){
    		parent.showErrorMsg("'应收总额'必须输入一个数字，最多只能12位整数，两位小数！");
    		return false;
    	}
    	if(aa[1].length>2){
    		parent.showErrorMsg("'应收总额'必须输入一个数字，最多只能12位整数，两位小数！");
    		return false;
    	}
    }else{
    	if(PAYABLE_AMOUNT.length>12){
    		parent.showErrorMsg("'应收总额'必须输入一个数字，最多只能12位整数，两位小数！");
    		return false;
    	}
    }
    
   return true;
}

//查询预定单列表
 function queryOrderNo(){
 
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" (ADVC_AMOUNT_FLAG <> '1' or ADVC_AMOUNT_FLAG is null) and  DEL_FLAG=0 and STATE in ('已收货') and BILL_TYPE = '终端销售' and LEDGER_ID = '"+ztxxid+"'");
 	
 	//为了不让日期自动添加到查询条件中，先清空
 	$("#SALE_DATE").val("");
 	$("#ORDER_RECV_DATE").val("");
 	
	var returnArray = selCommon('BS_57', false, 'ADVC_ORDER_ID', 'ADVC_ORDER_ID', 'forms[0]','ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_NAME,CUST_NAME,TEL,ORDER_RECV_DATE,ADVC_AMOUNT,PAYABLE_AMOUNT,ADD_ADVC_AMOUNT,STATEMENTS_AMOUNT,SALE_DATE_HIDDEN,ORDER_RECV_DATE_HIDDEN','ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_NAME,CUST_NAME,TEL,ORDER_RECV_DATE,ADVC_AMOUNT,PAYABLE_AMOUNT,ADD_ADVC_AMOUNT,PAYABLE_AMOUNT,SALE_DATE,ORDER_RECV_DATE','con');
	
	//如果通用选取没有变化，则把以前的日期回填到表格中
	if(returnArray[1]==false){
		$("#SALE_DATE").val($("#SALE_DATE_HIDDEN").val());
		$("#ORDER_RECV_DATE").val($("#ORDER_RECV_DATE_HIDDEN").val());
	}
	
	$("#con").val("");
 }
 
