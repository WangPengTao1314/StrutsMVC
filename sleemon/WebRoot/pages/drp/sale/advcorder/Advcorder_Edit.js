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
//应收总额
function setAmoutVal(v){
	$("#TOTAL_AMOUNT").val(v);
	var DISCOUNT_AMOUNT=$("#DISCOUNT_AMOUNT").val();
	if(""==DISCOUNT_AMOUNT||null==DISCOUNT_AMOUNT){
		DISCOUNT_AMOUNT=0;
	}
	v=isNaN(v)?0:parseFloat(v);
	DISCOUNT_AMOUNT=isNaN(DISCOUNT_AMOUNT)?0:parseFloat(DISCOUNT_AMOUNT);
	var PAYABLE_AMOUNT = Math.round((isNaN(v-DISCOUNT_AMOUNT)?0:v-DISCOUNT_AMOUNT)*100)/100;
	$("#PAYABLE_AMOUNT").val(PAYABLE_AMOUNT);
}
//优惠金额
function setDISCOUNT_AMOUNT(DISCOUNT_AMOUNT){
	$("#DISCOUNT_AMOUNT").val(DISCOUNT_AMOUNT);
	var TOTAL_AMOUNT=$("#TOTAL_AMOUNT").val();
	if(""==TOTAL_AMOUNT||null==TOTAL_AMOUNT){
		TOTAL_AMOUNT=0;
	}
	DISCOUNT_AMOUNT=isNaN(DISCOUNT_AMOUNT)?0:parseFloat(DISCOUNT_AMOUNT);
	TOTAL_AMOUNT=isNaN(TOTAL_AMOUNT)?0:parseFloat(TOTAL_AMOUNT);
	var PAYABLE_AMOUNT = Math.round((isNaN(TOTAL_AMOUNT-DISCOUNT_AMOUNT)?0:TOTAL_AMOUNT-DISCOUNT_AMOUNT)*100)/100;
	$("#PAYABLE_AMOUNT").val(PAYABLE_AMOUNT);
}
function formCheckedEx(){
//	if($("#TEL").val()!=null && $("#TEL").val() != ""){
//	        var re1 = new RegExp(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/);//电话匹配
//	        var re2 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
//	        var TEL1 = re1.test($("#TEL").val());
//	        var TEL2 = re2.test($("#TEL").val());
//	        if(!TEL1&&!TEL2 ){
//	      	   parent.showErrorMsg(utf8Decode("电话格式输入不正确！"));
//	           return false;
//	        }
//		}
	//验证是否输入订金
	//if($("#ADVC_AMOUNT").val()==0){
	      	   //parent.showErrorMsg(utf8Decode("请输入订金！"));
	           //return false;
	      //$("#ADVC_AMOUNT").val(0);
	//	}
	if($("#ADVC_AMOUNT").val()==""){
		$("#ADVC_AMOUNT").val(0);
	}
	if($("#PAYABLE_AMOUNT").val()==""){
		$("#PAYABLE_AMOUNT").val(0);
	}
	
	
	//验证订金金额是否大于应收总额
	//if($("#ADVC_AMOUNT").val()*1>$("#PAYABLE_AMOUNT").val()*1){
	//	 parent.showErrorMsg(utf8Decode("订金金额不得大于应收总额！"));
	//          return false;
	//}
	//验证是否输入收货地址
	if($("#RECV_ADDR").val()==""||$("#RECV_ADDR").val()==null){
	      	   parent.showErrorMsg(utf8Decode("请输入收货地址！"));
	           return false;
		}
	//验证到货日期不能在当前日期之前
//	var DateOne=$("#DATE").val();
//	var DateTwo=$("#ORDER_RECV_DATE").val();
//	if(!compareDate(DateOne,DateTwo)){
//		parent.showErrorMsg(utf8Decode("要求到货日期不能在当前日期之前！"));
//	           return false;
//	}
	return true;
}
//验证时间大小
function compareDate(DateOne,DateTwo){    
	var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ("-"));    
	var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ("-")+1);    
	var OneYear = DateOne.substring(0,DateOne.indexOf ("-"));    
	var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ("-"));    
	var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ("-")+1);    
	var TwoYear = DateTwo.substring(0,DateTwo.indexOf ("-"));    
	if (Date.parse(OneMonth+"/"+OneDay+"/"+OneYear) <= Date.parse(TwoMonth+"/"+TwoDay+"/"+TwoYear))    
		return true;    
	else    
		return false;
}
function countAMOUNT(){
	var TOTAL_AMOUNT=$("#TOTAL_AMOUNT").val();//总金额
	if(""==TOTAL_AMOUNT||null==TOTAL_AMOUNT){
		TOTAL_AMOUNT=0;
	}
	var DISCOUNT_AMOUNT=$("#DISCOUNT_AMOUNT").val();//优惠金额
	if(""==DISCOUNT_AMOUNT||null==DISCOUNT_AMOUNT){
		DISCOUNT_AMOUNT=0;
	}
	TOTAL_AMOUNT=isNaN(TOTAL_AMOUNT)?0:parseFloat(TOTAL_AMOUNT);
	DISCOUNT_AMOUNT=isNaN(DISCOUNT_AMOUNT)?0:parseFloat(DISCOUNT_AMOUNT);
	var PAYABLE_AMOUNT=Math.round((isNaN(TOTAL_AMOUNT-DISCOUNT_AMOUNT)?0:TOTAL_AMOUNT-DISCOUNT_AMOUNT)*100)/100;
	$("#PAYABLE_AMOUNT").val(PAYABLE_AMOUNT);
}

//查询加盟商列表
 function queryChannel(){
	selCommon('BS_112', false, 'PROMOTE_ID', 'PROMOTE_ID', 'forms[0]',
		'PROMOTE_ID,PROMOTE_NO,PROMOTE_NAME',
		'PROMOTE_ID,PROMOTE_NO,PROMOTE_NAME','selectMoteParams');
	
 }
 
 
 
 function checkParent(){
	 var DISCOUNT_AMOUNT = $("#DISCOUNT_AMOUNT");
	 if(""==DISCOUNT_AMOUNT.val()||null==DISCOUNT_AMOUNT.val()){
		 return true;
	 }
	 var textType = DISCOUNT_AMOUNT.attr("textType");
	 if("float" == textType){
		 if(!CheckFloatInput(DISCOUNT_AMOUNT)){
			 flag = false;
			return false;
		}
	 }
	 return true;
 }
 function getOldSaleDate(){
	 return $("#oldSaleDate").val();
 }
 function getUpdateFlag(){
	 return $("#updateFlag").val();
 }
 
 