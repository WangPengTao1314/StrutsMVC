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
//	var EXPENSE_TYPE = $.trim($("#EXPENSE_TYPE").val());
//	if(null == EXPENSE_TYPE || "" == EXPENSE_TYPE){
//		parent.showErrorMsg("请选择'报销单类别'");
//		return false;
//	}
	return true;
}
  
 
function setPAYABLE_AMOUNT(v){
	$("#PAYABLE_AMOUNT").val(v);
}

//活动ID
function getMARKETING_ACT_ID(){
	return $("#MARKETING_ACT_ID").val();
}

//银行扣点比例
function changeAmount(){
	parent.bottomcontent.paddingADVC_AMOUNT();
}

/**
 * 订金总额
 * @param {Object} a 订金金额
 * @param {Object} b 应收总额/结算总额
 * @param {Object} c 礼品费用
 */
function setADVC_AMOUNT(a,b,c){
	$("#ADVCS_AMOUNT").val(a);
	//银行扣点金额=银行扣点比例*订金总额
	var BANK_AMOUNT = $("#BANK_RATE").val();
	BANK_AMOUNT = Math.round((FloatFomat(BANK_AMOUNT)*a)*100)/100;
	$("#BANK_AMOUNT").val(BANK_AMOUNT);
	
	//导购员提成比例  根据活动上面设置的带入
	var COMMISSION_AMOUNT = $("#COMMISSION_PERCENTAGE").val();
	COMMISSION_AMOUNT = Math.round((FloatFomat(COMMISSION_AMOUNT)*b)*100)/100;
	$("#COMMISSION_AMOUNT").val(COMMISSION_AMOUNT);
	
	//实际结算金额=结算金额-银行扣点金额-导购员提成金额-礼品费用-其它费用
	var OTHER_AMOUNT = $("#OTHER_AMOUNT").val();
	OTHER_AMOUNT = FloatFomat(OTHER_AMOUNT);
	var LAST_STATEMENTS_AMOUNT = b-BANK_AMOUNT-COMMISSION_AMOUNT-c-OTHER_AMOUNT;
	LAST_STATEMENTS_AMOUNT = Math.round(LAST_STATEMENTS_AMOUNT*100)/100;
	setLAST_STATEMENTS_AMOUNT(LAST_STATEMENTS_AMOUNT);
}

//应收总额
function setSTATEMENTS_AMOUNT(amonut){
	$("#STATEMENTS_AMOUNT").val(amonut);
	
}

//礼品费用

function setGIFT_AMOUNT(amonut){
	$("#GIFT_AMOUNT").val(amonut);
}


//实际结算金额
function setLAST_STATEMENTS_AMOUNT(amonut){
	$("#LAST_STATEMENTS_AMOUNT").val(amonut);
}







/**
 * 浮点数的 乘法
 * @param {Object} arg1
 * @param {Object} arg2
 * @return {TypeName} 
 */
function FloatMul(arg1,arg2){   
  var m=0,s1=arg1.toString(),s2=arg2.toString();   
  try{m+=s1.split(".")[1].length}catch(e){}   
  try{m+=s2.split(".")[1].length}catch(e){}   
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
}   


function FloatFomat(arg1){
	var res = Number(arg1);
	if(isNaN(arg1)){
		res = Number(0);
	}
	return res;
}
