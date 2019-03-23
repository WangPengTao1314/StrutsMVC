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
	
	$("#save").click(function(){
		save();
	});
	
  
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


function changeCardParams(){
	var MARKETING_ACT_ID = $("#MARKETING_ACT_ID").val();
	var TEL = $("#TEL").val();
	$("#selCardParam").val(" MARKETING_ACT_ID='"+MARKETING_ACT_ID+"' ");
}


 

function selectCard(){
//	var MARKETING_ACT_ID = $("#MARKETING_ACT_ID").val();
//	if(null == MARKETING_ACT_ID || "" == MARKETING_ACT_ID){
//		parent.showErrorMsg("请先选择活动");
//		return;
//	}
//	var TEL = $.trim($("#TEL").val());
//	var MEMBER_NAME = $.trim($("#CUST_NAME").val());
//	var params = " MARKETING_ACT_ID='"+MARKETING_ACT_ID+"'  ";
//	if((null == TEL || "" == TEL) && (null == MEMBER_NAME || "" == MEMBER_NAME)){
//		parent.showErrorMsg("请先填写客户");
//		return;
//	}
//	if(null != TEL && "" != TEL ){
//		params = params +" and MOBILE_PHONE like '%"+TEL+"%' ";
//	}
//	if(null != MEMBER_NAME && "" != MEMBER_NAME ){
//		params = params +" and MEMBER_NAME like '%"+MEMBER_NAME+"%' ";
//	}
//	
////	var sql = " select a.MARKETING_CARD_ID,a.MARKETING_CARD_NO from ERP_MEMBER_CARD_DTL a left join ERP_MEMBER_INFO b"+
////          " on a.MEMBER_ID=b.MEMBER_ID"+
////          "  left join ERP_MARKETING_CARD c on a.MARKETING_CARD_ID=c.MARKETING_CARD_ID "+
////          " where b.MOBILE_PHONE='"+TEL+"' " +
////          " and c.MARKETING_ACT_ID='"+MARKETING_ACT_ID+"' and a.DEL_FLAG=0 and b.DEL_FLAG=0 and c.DEL_FLAG=0";
////	parent.showErrorMsg(sql);
////	$("#selCardParam").val(params);
	selCommon("BS_177", false, "MARKETING_CARD_ID", "MARKETING_CARD_ID", "forms[0]",
		"MARKETING_ACT_ID,MARKETING_ACT_NO,MARKETING_ACT_NAME,MARKETING_CARD_NO,TERM_ID,TERM_NO,TERM_NAME,CUST_NAME,TEL,SALE_DATE,RECV_ADDR",
		"MARKETING_ACT_ID,MARKETING_ACT_NO,MARKETING_ACT_NAME,MARKETING_CARD_NO,TERM_ID,TERM_NO,TERM_NAME,CUST_NAME,MOBILE_PHONE,SALE_DATE,ADDRESS",
        "selCardParam");
	
}

function gobacknew(){
   newGoBack("erpadvcorder.shtml?action=toFrame");
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}

//主子表保存
function save(){
	//主表form校验
	var parentCheckedRst = formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	var parentData = siglePackJsonData();
	var selRowId = getSelRowId();
	$.ajax({
		url: "erpadvcorder.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"ADVC_ORDER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				saveSuccess("保存成功","erpadvcorder.shtml?action=toFrame");
				saveConfirm(utf8Decode(jsonResult.messages),"goFrame('erpadvcorder.shtml?action=toFrame');");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}