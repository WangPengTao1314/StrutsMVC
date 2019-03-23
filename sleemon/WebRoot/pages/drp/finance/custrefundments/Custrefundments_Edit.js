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
   return checkNum("STATEMENTS_AMOUNT","本次退款金额");
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
    var BILL_TYPE = $("#BILL_TYPE").val();
    if(null == BILL_TYPE || "" == BILL_TYPE){
    	parent.showErrorMsg("请选择'退款类型'");
    	return;
    }
    var tempParams = "";
    if("销售退款" == BILL_TYPE){
    	tempParams = " and STATE in ('审核通过','待确认','已发货','关闭') and BILL_TYPE='终端销售' ";
    }else{
    	tempParams = " and STATE in ('待退货','已退货') and BILL_TYPE='终端退货'  ";
    }
    	
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" DEL_FLAG=0 and LEDGER_ID = '"+ztxxid+"' "+tempParams);
 	
 	//为了不让日期自动添加到查询条件中，先清空
 	$("#SALE_DATE_HIDDEN").val($("#SALE_DATE").val());
 	$("#ORDER_RECV_DATE_HIDDEN").val($("#ORDER_RECV_DATE").val());
 	$("#SALE_DATE").val("");
 	$("#ORDER_RECV_DATE").val("");
 	
	var returnArray = selCommon('BS_111', false, 'ADVC_ORDER_ID', 'ADVC_ORDER_ID', 'forms[0]','ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_NAME,CUST_NAME,TEL,ORDER_RECV_DATE,ADVC_AMOUNT,PAYABLE_AMOUNT,PAYED_TOTAL_AMOUNT', 'ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_NAME,CUST_NAME,TEL,ORDER_RECV_DATE,ADVC_AMOUNT,PAYABLE_AMOUNT,PAYED_TOTAL_AMOUNT','con');
	
	//如果通用选取没有变化，则把以前的日期回填到表格中
	if(returnArray[1]==false){
		$("#SALE_DATE").val($("#SALE_DATE_HIDDEN").val());
		$("#ORDER_RECV_DATE").val($("#ORDER_RECV_DATE_HIDDEN").val());
	}
	
	$("#con").val("");
 }
 
 function changeBill(obj){
	  $("#ADVC_ORDER_ID").val("");
	  $("#ADVC_ORDER_NO").val("");
	  $("#TERM_ID").val("");
	  $("#TERM_NO").val("");
	  $("#TERM_NAME").val("");
	  $("#SALE_DATE").val("");
	  
	  $("#SALE_PSON_NAME").val("");
	  $("#SALE_PSON_ID").val("");
	  $("#CUST_NAME").val("");
	  $("#TEL").val("");
	  $("#ORDER_RECV_DATE").val("");
	  $("#ADVC_AMOUNT").val("");
	  $("#PAYED_TOTAL_AMOUNT").val("");
	  $("#PAYABLE_AMOUNT").val("");
	  $("#STATEMENT_DATE").val("");
	      
	  
 }
