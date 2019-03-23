/**
 * @prjName:喜临门营销平台
 * @fileName:Saleorder_Edit
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
	
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		save();
	});
	
});



function save(){
	var jsonData = siglePackJsonData();
	var SALE_ORDER_ID = $("#SALE_ORDER_ID").val();
	$.ajax({
		url: "saleordermge.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":jsonData,"SALE_ORDER_ID":SALE_ORDER_ID},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 saveConfirm(utf8Decode(jsonResult.messages),"goFrame('saleordermge.shtml?action=toFrame');");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
}


function selAddrInfo(){
	var chann=$("#RECV_CHANN_ID").val();
	if(""==chann||null==""){
		parent.showErrorMsg(utf8Decode("请选择收货方！"));
		return;
	}
	$("#selectAddrParams").val("DEL_FLAG=0 and STATE='启用' and CHANN_ID='"+chann+"'");
	selCommon('BS_76', false, 'RECV_ADDR_NO', 'DELIVER_ADDR_NO', 'forms[0]','RECV_ADDR_NO,RECV_ADDR', 'DELIVER_ADDR_NO,DELIVER_DTL_ADDR', 'selectAddrParams');
}


function selRecvChann(){
	var RECV_CHANN_ID=$("#RECV_CHANN_ID").val();
	selCommon('BS_19', false, 'RECV_CHANN_ID', 'CHANN_ID', 'forms[0]','RECV_CHANN_ID,RECV_CHANN_NO,RECV_CHANN_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selOrderChannParam');
	var newRECV_CHANN_ID=$("#RECV_CHANN_ID").val();
	if(RECV_CHANN_ID!=newRECV_CHANN_ID){
		$("RECV_ADDR_NO").val("");
		$("RECV_ADDR").val("");
	}
}

function selOrderChann(){
	selCommon('BS_19', false, 'ORDER_CHANN_ID', 'CHANN_ID', 'forms[0]','ORDER_CHANN_ID,ORDER_CHANN_NO,ORDER_CHANN_NAME,PERSON_CON,TEL,SHIP_POINT_ID,SHIP_POINT_NAME,AREA_ID,AREA_NO,AREA_NAME', 'CHANN_ID,CHANN_NO,CHANN_NAME,PERSON_CON,TEL,SHIP_POINT_ID,SHIP_POINT_NAME,AREA_ID,AREA_NO,AREA_NAME', 'selOrderChannParam');
}


 
function selStore(){
	 var CHANN_ID = $("#RECV_CHANN_ID").val();
	if(null == CHANN_ID || "" == CHANN_ID){
		parent.showErrorMsg("请先选择收货方信息")
		return;
	}
	 var v = "STATE ='启用' and DEL_FLAG='0' and BEL_CHANN_ID='"+CHANN_ID+"' "
	 $("#selectStore").val(v);
	 selCommon('BS_35', false, 'STORE_ID', 'STORE_ID', 'forms[0]','STORE_NO,STORE_NAME', 'STORE_NO,STORE_NAME', 'selectStore')
 }

 function getCHANN_ID(){
	 return $("#ORDER_CHANN_ID").val();
 }
 
 function getBILL_TYPE(){
	 return $("#BILL_TYPE").val();
 }
 
 function delDtl(){
	 parent.bottomcontent.delDtl();
 }