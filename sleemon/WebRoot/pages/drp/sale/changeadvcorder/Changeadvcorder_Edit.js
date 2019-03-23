/**
 * @prjName:喜临门营销平台
 * @fileName:Changeadvcorder_Frame
 * @author ghx
 * @time   2014-05-20 15:14:52 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);
	
});
function formCheckedEx(){
	//验证收货地址不为空
		//if($("#RECV_ADDR").val()==null || $("#RECV_ADDR").val() == ""){
	     // 	   parent.showErrorMsg(utf8Decode("请填写收货地址！"));
	     //      return false;
		//}*/
		return true;
}

function btnDisable(arrys){
	if(null==arrys || arrys.length<1){
		return;
	}
	for(var i=0; i<arrys.length; i++){		
		$("#bottomcontent",parent.document.body).contents().find("#"+arrys[i]).attr("disabled","true");
	}
}

function selAdvcorder(){
	var OLD_FROM_BILL_ID=$("#FROM_BILL_ID").val();
	selCommon('BS_84', false, 'FROM_BILL_ID', 'ADVC_ORDER_ID', 'forms[0]',
			'FROM_BILL_ID,FROM_BILL_NO,TERM_NO,TERM_NAME,TERM_ID,CUST_NAME,TEL,SALE_PSON_ID,SALE_PSON_NAME,SALE_DATE,CRE_TIME,RECV_ADDR,DIFF_AMOUNT', 
			'ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_NO,TERM_NAME,TERM_ID,CUST_NAME,TEL,SALE_PSON_ID,SALE_PSON_NAME,SALE_DATE,CRE_TIME,RECV_ADDR,ALL_DIFF_AMOUNT', 'selectParams');
	$("#oldAmount").val($("#DIFF_AMOUNT").val());
	var FROM_BILL_ID=$("#FROM_BILL_ID").val();
	if(OLD_FROM_BILL_ID!=FROM_BILL_ID){
		$.ajax({
			url: "changeadvcorder.shtml?action=queryAdvcOrderInfo",
			type:"POST",
			dataType:"text",
			data:{"ADVC_ORDER_ID":FROM_BILL_ID},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success==true){
					$("#OLD_CUST_NAME").val($("#CUST_NAME").val());
					$("#OLD_TEL").val($("#TEL").val());
					$("#OLD_RECV_ADDR").val($("#RECV_ADDR").val());
				}else{
					showErrorMsg(utf8Decode(jsonResult.messages));
					emptyInfo();
				}
			}
		});
		parent.bottomcontent.delDtl();
	}
}
function setAmoutVal(v){
	var oldAmount=$("#oldAmount").val();
	if(""==oldAmount||null==oldAmount){
		oldAmount=0
	}
	oldAmount=isNaN(oldAmount)?0:parseFloat(oldAmount);
	var NEW_DIFF_AMOUNT=Math.round((isNaN(oldAmount+v)?0:oldAmount+v)*100)/100;
	$("#DIFF_AMOUNT").val(NEW_DIFF_AMOUNT);
}
function emptyInfo(){
	$("#FROM_BILL_ID").val("");
	$("#FROM_BILL_NO").val("");
	$("#TERM_NO").val("");
	$("#TERM_NAME").val("");
	$("#TERM_ID").val("");
	$("#CUST_NAME").val("");
	$("#TEL").val("");
	$("#SALE_PSON_ID").val("");
	$("#SALE_PSON_NAME").val("");
	$("#ADVC_AMOUNT").val("");
	$("#SALE_DATE").val("");
	$("#CRE_TIME").val("");
	$("#RECV_ADDR").val("");
	$("#TOTAL_AMOUNT").val("");
	$("#DISCOUNT_AMOUNT").val("");
	$("#PAYABLE_AMOUNT").val("");
}