/**
 * @prjName:喜临门营销平台
 * @fileName:Restatements_Edit
 * @author chenj
 * @time   2013-10-12 15:21:43 
 * @version 1.1
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("restatements.shtml?action=edit","STATEMENTS_ID");
	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
	
});
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){

	var reg = new RegExp("^([0-9]{1,12})+(.[0-9]{0,2})?$", "g");
    if (!reg.test($("#STATEMENTS_AMOUNT").val())) {  
        parent.showErrorMsg("'退货金额'必须输入一个数字，最多只能12位整数，两位小数！");  
        return false;
    } 
    
    var STATEMENTS_AMOUNT = $("#STATEMENTS_AMOUNT").val();
    
    if(STATEMENTS_AMOUNT.indexOf(".")>=0){//如果包含小数点
    	var aa = STATEMENTS_AMOUNT.split(".");
    	if(aa[0].length>12){
    		parent.showErrorMsg("'退货金额'必须输入一个数字，最多只能12位整数，两位小数！");
    		return false;
    	}
    	if(aa[1].length>2){
    		parent.showErrorMsg("'退货金额'必须输入一个数字，最多只能12位整数，两位小数！");
    		return false;
    	}
    }else{
    	if(STATEMENTS_AMOUNT.length>12){
    		parent.showErrorMsg("'退货金额'必须输入一个数字，最多只能12位整数，两位小数！");
    		return false;
    	}
    }
    
    
   return true;
}

//查询预定单列表
 function queryOrderNo(){
 
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" LEDGER_ID = '"+ztxxid+"'");
 	
 	//为了不让日期自动添加到查询条件中，先清空
 	$("#SALE_DATE").val("");
 	$("#STOREIN_DATE").val("");
 	
	var returnArray = selCommon('BS_62', false, 'ADVC_ORDER_ID', 'ADVC_ORDER_ID', 'forms[0]','STOREIN_ID,ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_NAME,CUST_NAME,TEL,ADVC_AMOUNT,ADD_ADVC_AMOUNT_HIDDEN,LAST_PAYABLE_AMOUNT_HIDDEN,STATEMENTS_AMOUNT,SALE_DATE_HIDDEN','STOREIN_ID,ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_NAME,CUST_NAME,TEL,ADVC_AMOUNT,ADD_ADVC_AMOUNT,LAST_AMOUNT,LAST_AMOUNT,SALE_DATE','con');
	//如果通用选取没有变化，则把以前的日期回填到表格中
	
	if($("#ADD_ADVC_AMOUNT_HIDDEN").val() != "" || $("#LAST_PAYABLE_AMOUNT_HIDDEN").val() != ""){
		$("#RECV_LAST_AMOUNT").val(parseInt($("#ADD_ADVC_AMOUNT_HIDDEN").val())+parseInt($("#LAST_PAYABLE_AMOUNT_HIDDEN").val()));
	}
	
	if(returnArray[1]==false){
		$("#SALE_DATE").val($("#SALE_DATE_HIDDEN").val());
		$("#STOREIN_DATE").val($("#STOREIN_DATE_HIDDEN").val());
	}
	
	$("#con").val("");
 }