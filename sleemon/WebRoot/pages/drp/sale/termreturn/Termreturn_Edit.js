/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_Edit
 * @author lyg
 * @time   2013-08-19 14:35:52 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);
	$("input[type='radio']").click(function(){
		upAttr();
		$("#FROM_BILL_NO").val("");
		$("#FROM_BILL_ID").val("");
		$("#TEL").val("");
		$("#SALE_PSON_NAME").val("");
		$("#RECV_ADDR").val("");
		parent.bottomcontent.delDtl();
	})
	init();
});

function getRadio(){
	return $("#fromFlag").val();
}
function formCheckedEx(){
	//验证收货地址不为空
		if($("#RECV_ADDR").val()==null || $("#RECV_ADDR").val() == ""){
	      	   parent.showErrorMsg(utf8Decode("请填写收货地址！"));
	           return false;
		}
		return true;
}
function selAdvcorder(){
	var FROM_BILL_ID_TEMP=$("#FROM_BILL_ID").val();
	selCommon('BS_38', false, 'FROM_BILL_ID', 'ADVC_ORDER_ID', 'forms[0]','FROM_BILL_ID,FROM_BILL_NO,TERM_NO,TERM_NAME,TERM_ID,CUST_NAME,CONTRACT_NO,TEL,RECV_ADDR,SALE_PSON_ID,SALE_PSON_NAME', 'ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_NO,TERM_NAME,TERM_ID,CUST_NAME,CONTRACT_NO,TEL,RECV_ADDR,SALE_PSON_ID,SALE_PSON_NAME', 'selectParams');
	var FROM_BILL_ID=$("#FROM_BILL_ID").val();
	if(FROM_BILL_ID!=FROM_BILL_ID_TEMP){
		parent.bottomcontent.delDtl();
	}
}
function upAttr(id){
	var radio= $("#fromFlagYes").prop("checked");
	if(!radio){
			$("#selAdvc").hide();
			$("#FROM_BILL_NO").attr("mustinput",false);
			$("#TERM_NO").attr("readonly",false);
			$("#TERM_NAME").attr("readonly",false);
			$("#CUST_NAME").attr("readonly",false);
			$("#CONTRACT_NO").attr("readonly",false);
			$("#TEL").attr("readonly",false);
			$("#SALE_PSON_NAME").attr("readonly",false);
			$("#RECV_ADDR").attr("disabled",false);
			$("#CUST_NAME").css("background","white");
			$("#CONTRACT_NO").css("background","white");
			$("#TEL").css("background","white");
			$("#SALE_PSON_NAME").css("background","white");
			$("#RECV_ADDR").css("background","white");
			$("#fromFlag").val("0");
			if(""==id||null==id){
				$("#TERM_ID").val($("#tempTERM_ID").val());
				$("#TERM_NO").val($("#tempTERM_NO").val());
				$("#TERM_NAME").val($("#tempTERM_NAME").val());
				$("#CONTRACT_NO").val("");
			}
			if(""==$("#tempTERM_ID").val()||null==$("#tempTERM_ID").val()){
				$("#selTerm").show();
			}else{
				$("#selTerm").hide();
			}
		}else{
			$("#selAdvc").show();
			$("#FROM_BILL_NO").attr("mustinput",true);
			$("#TERM_NO").attr("readonly",true);
			$("#TERM_NAME").attr("readonly",true);
			$("#CUST_NAME").attr("readonly",true);
			$("#CONTRACT_NO").attr("readonly",true);
			$("#TEL").attr("readonly",true);
			$("#SALE_PSON_NAME").attr("readonly",true);
			$("#RECV_ADDR").attr("disabled",true);
			$("#TERM_NO").css("background","#EEEEFF");
			$("#TERM_NAME").css("background","#EEEEFF");
			$("#CUST_NAME").css("background","#EEEEFF");
			$("#CONTRACT_NO").css("background","#EEEEFF");
			$("#TEL").css("background","#EEEEFF");
			$("#SALE_PSON_NAME").css("background","#EEEEFF");
			$("#fromFlag").val("1");
			$("#selTerm").hide();
			if(""==id||null==id){
				$("#TERM_ID").val("");
				$("#TERM_NO").val("");
				$("#TERM_NAME").val("");
				$("#CUST_NAME").val("");
				$("#CONTRACT_NO").val("");
			}
		}
}
function init(){
	var fromFlag=$("#fromFlag").val();
	if("0"==fromFlag){
		$("#fromFlagNo").attr("checked",true);
	}else{
		$("#fromFlagYes").attr("checked",true);
	}
	var selRowId = parent.document.getElementById("selRowId").value;
	upAttr(selRowId);
	$("#selTerm").hide();
}