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
	
	$("#EXPENSE_TYPE option[text='推广费']").attr("selected","selected");
 
});

function formCheckedEx(){
	var EXPENSE_TYPE = $.trim($("#EXPENSE_TYPE").val());
	if(null == EXPENSE_TYPE || "" == EXPENSE_TYPE){
		parent.showErrorMsg("请选择'报销单类别'");
		return false;
	}
	return true;
}
 
function setEXPENSE_AMOUNT(amount){
	$("#EXPENSE_AMOUNT").val(amount);
}
  
function countAmount(){
	var REIT_REQ_AMOUNT = $("#REIT_REQ_AMOUNT").val();
	var RELATE_AMOUNT_REQ = $("#RELATE_AMOUNT_REQ").val();
	var arry = REIT_REQ_AMOUNT.split(",");
	if(null == REIT_REQ_AMOUNT || "" == REIT_REQ_AMOUNT){
		$("#RELATE_AMOUNT_REQ").val("");
		return;
	}
	var total = 0;
	for(var i=0;i<arry.length;i++){
		total = Number(total) + Number(arry[i]);
	}
	$("#RELATE_AMOUNT_REQ").val(total);
}
 
 
function changeReqParams(){
	var BUDGET_QUOTA_ID = $("#BUDGET_QUOTA_ID").val();
	var BUDGET_QUOTA_ID_OLD = $("#BUDGET_QUOTA_ID_OLD").val();
	if(null == BUDGET_QUOTA_ID || "" == BUDGET_QUOTA_ID){
		parent.showErrorMsg("请先选择'预算科目'");
		return;
	}
	if(null != BUDGET_QUOTA_ID_OLD && (BUDGET_QUOTA_ID_OLD != BUDGET_QUOTA_ID)){
		$("#RELATE_ORDER_NOS").val("");
	}
	
	var params = " STATE='审核通过' and DEL_FLAG=0 and BUDGET_QUOTA_ID='"+BUDGET_QUOTA_ID+"' ";
	$("#selectReqParams").val(params);
}

function getPrommexpen(){
 var DRP_LEDGER = $("#DRP_LEDGER").val();
 var LEDGER_ID  = $("#LEDGER_ID").val();
 var CHANN_ID   = $("#CHANN_ID_T").val();
 var params     = "";
 if(DRP_LEDGER=="1"){
    $("#selectReqParams").val("temp.CHANN_ID='"+LEDGER_ID+"' and temp.ORG_ID = '"+LEDGER_ID+"' ");
 }else{
    $("#selectReqParams").val("temp.CHANN_ID IN "+CHANN_ID+" ");
 }
 selCommon('BS_183', false, 'RELATE_ORDER_NOS', 'PRMT_COST_REQ_NO', 'forms[0]','RELATE_ORDER_NOS,CHANN_ID,CHANN_NO,CHANN_NAME,BUDGET_QUOTA_ID,BUDGET_ITEM_ID,BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_ITEM_TYPE,EXPENSE_DEPT_ID,EXPENSE_DEPT_NO,EXPENSE_DEPT_NAME,YEAR,MONTH,QUARTER,RELATE_AMOUNT_REQ', 'PRMT_COST_REQ_NO,CHANN_ID,CHANN_NO,CHANN_NAME,BUDGET_QUOTA_ID,BUDGET_ITEM_ID,BUDGET_ITEM_NO,BUDGET_ITEM_NAME,BUDGET_ITEM_TYPE,BUDGET_DEPT_ID,BUDGET_DEPT_NO,BUDGET_DEPT_NAME,YEAR,MONTH,QUARTER,BUDGET_AMOUNT', 'selectReqParams');
 
 var CHANN_ID = $("#CHANN_ID").val();
 $.ajax({
	url: "expenseorder.shtml?action=loadModelT",
	type:"POST",
	dataType:"text",
	data:{"CHANN_ID":CHANN_ID},
	complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
		     var result = jsonResult.data;
		     if(typeof(result.DECT_AMOUNT) == "undefined"){
		        $("#YEAR_GOODS_AMOUNT").val("0");
		     }else{
		        $("#YEAR_GOODS_AMOUNT").val(result.DECT_AMOUNT);
		     }
		     $("#YEAR_CHANN_EXPENSE_AMOUNT").val(result.EXPENSE_AMOUNT);
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	}
});

}

//推广费 申请金额
function getRELATE_AMOUNT_REQ(){
	return $("#RELATE_AMOUNT_REQ").val();
}