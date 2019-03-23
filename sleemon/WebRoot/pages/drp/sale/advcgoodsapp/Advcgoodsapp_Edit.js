/**
 * @prjName:喜临门营销平台
 * @fileName:Advcgoodsapp_Edit
 * @author lyg
 * @time   2013-11-02 18:55:53 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	$("#imgSend").hide();
	//初始化校验
	InitFormValidator(0);	
});
function selComm(){
	var SEND_TYPE=$("#SEND_TYPE").val();
	if(""==SEND_TYPE||null==SEND_TYPE||"--请选择--"==SEND_TYPE){
		parent.showErrorMsg("请选择发货方类型");
		return;
	}
	if("终端发货"==SEND_TYPE){
		selCommon('BS_27', false, 'SEND_CHANN_ID', 'TERM_ID', 'forms[0]','SEND_CHANN_ID,SEND_CHANN_NO,SEND_CHANN_NAME', 'TERM_ID,TERM_NO,TERM_NAME', 'selectTERM')
	}else if("渠道发货"==SEND_TYPE){
		selCommon('BS_19', false, 'SEND_CHANN_ID', 'CHANN_ID', 'forms[0]','SEND_CHANN_ID,SEND_CHANN_NO,SEND_CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams');
	}
	
	$("#SEND_CHANN_NAME").focus();
}
function selAdvcorder(){
	var FROM_BILL_ID_TEMP=$("#FROM_BILL_ID").val();
	selCommon('BS_154', false, 
		'FROM_BILL_ID', 'ADVC_ORDER_ID', 'forms[0]','FROM_BILL_ID,FROM_BILL_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_ID,SALE_PSON_NAME,CUST_NAME,TEL,ORDER_RECV_DATE,PAYABLE_AMOUNT,ADVC_AMOUNT,PAYED_TOTAL_AMOUNT,RECV_ADDR,PAYED_TOTAL_AMOUNT,CONTRACT_NO,REMARK', 
		'ADVC_ORDER_ID,ADVC_ORDER_NO,TERM_ID,TERM_NO,TERM_NAME,SALE_DATE,SALE_PSON_ID,SALE_PSON_NAME,CUST_NAME,TEL,ORDER_RECV_DATE,PAYABLE_AMOUNT,ADVC_AMOUNT,PAYED_TOTAL_AMOUNT,RECV_ADDR,PAYED_TOTAL_AMOUNT,CONTRACT_NO,REMARK', "selectADC");
	var FROM_BILL_ID=$("#FROM_BILL_ID").val();
	checkExsitUpAdvcOrder(FROM_BILL_ID);
	if(FROM_BILL_ID!=FROM_BILL_ID_TEMP){
		parent.bottomcontent.delDtl();
	}
}
//check 选择的预订是否存在变更
function checkExsitUpAdvcOrder(FROM_BILL_ID){
	$.ajax({
		url: "advcgoodsapp.shtml?action=checkExsitChangeAdvcOrder",
		type:"POST",
		dataType:"text",
		data:{"ADVC_ORDER_ID":FROM_BILL_ID },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			}else{
				$("#FROM_BILL_ID").val('');
				$("#FROM_BILL_NO").val('');
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function selStoreout(){
	var STOREOUT_STORE_ID_TEMP=$("#STOREOUT_STORE_ID").val();
	var SEND_CHANN_ID =	$("#SEND_CHANN_ID").val();
	if(SEND_CHANN_ID==null || SEND_CHANN_ID ==''){
	    alert('请先选择发货方!');
		return;
	}
	var selectSTORECondition =" STATE ='启用' and  DEL_FLAG=0 and BEL_CHANN_ID='"+SEND_CHANN_ID+"'";
	$("#selectSTORECondition").val(selectSTORECondition);
	selCommon('BS_35', false, 'STOREOUT_STORE_ID', 'STORE_ID', 'forms[0]','STOREOUT_STORE_ID,STOREOUT_STORE_NO,STOREOUT_STORE_NAME', 'STORE_ID,STORE_NO,STORE_NAME','selectSTORECondition')
	var STOREOUT_STORE_ID=$("#STOREOUT_STORE_ID").val();
	if(STOREOUT_STORE_ID!=STOREOUT_STORE_ID_TEMP){
		parent.bottomcontent.delDtl();
	}
}
function acquireTab(){
	   		var STORAGE_FLAG=document.getElementById("STORAGE_FLAG").value;
	   		if(STORAGE_FLAG==1){
	   			document.getElementById("STORAGE_FLAG_YES").checked=true;
	   			SelDictShow("INV_RANGE","BS_33","${rst.INV_RANGE}","");
	   		}else if(STORAGE_FLAG==""||STORAGE_FLAG==null){
	   			document.getElementById("STORAGE_FLAG_YES").checked=false;
	   			document.getElementById("STORAGE_FLAG_NO").checked=false;
	   			SelDictShow("INV_RANGE","BS_33","${rst.INV_RANGE}","");
	   		}else if(STORAGE_FLAG==0){
	   			document.getElementById("STORAGE_FLAG_NO").checked=true;
	   			SelDictShow("INV_RANGE","BS_36","${rst.INV_RANGE}","");
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
function showSendChann(SEND_TYPE){
	var selRowId = parent.document.getElementById("selRowId").value;
	if(""==SEND_TYPE||null==SEND_TYPE){
		SEND_TYPE=$("#SEND_TYPE").val();
	}else{
		if(selRowId!=null||selRowId!=""){
			return;
		}
	}
	$("#SEND_CHANN_ID").val("");
	$("#SEND_CHANN_NO").val("");
	$("#SEND_CHANN_NAME").val("");
	$("#STOREOUT_STORE_ID").val("");
	$("#STOREOUT_STORE_NO").val("");
	$("#STOREOUT_STORE_NAME").val("");
	$("#imgSend").hide();
	if("终端发货"==SEND_TYPE){
		$("#imgSend").show();
	}else if("渠道发货"==SEND_TYPE){
		$("#SEND_CHANN_ID").val($("#channId").val());
		$("#SEND_CHANN_NO").val($("#channNo").val());
		$("#SEND_CHANN_NAME").val($("#channName").val());
	}
		
}