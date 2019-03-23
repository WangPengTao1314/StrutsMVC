/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorderhd_Edit
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});

function setUser_rebate(){
	var REBATEDSCT = getREBATEDSCT();
	if(null == REBATEDSCT || "" == REBATEDSCT || 0 == REBATEDSCT || "0" == REBATEDSCT){
		parent.showErrorMsg(utf8Decode("总部未设置您的返利折扣，不能使用返利金额，请联系总部！"));
		$("#user_rebate").removeAttr("checked");
		return ;
	}
	var checked = $("#user_rebate").attr("checked");
	if(checked){
		$("#IS_USE_REBATE").val("1");
		parent.bottomcontent.$("input[name='spclTd']").attr("disabled","disabled");
		parent.bottomcontent.delDtl();
	}else{
		$("#IS_USE_REBATE").val("0");
		parent.bottomcontent.delDtl();
		parent.bottomcontent.$("input[name='spclTd']").removeAttr("disabled");
	}
}


 function toFlow(i,cutid) {
//	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/goodsorderhd.shtml?action=toList"+document.getElementById("paramUrl").value ;
    switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}
 
 function changeAddr(){
	 var RECV_CHANN_ID = $("#RECV_CHANN_ID").val();
	 var RECV_CHANN_ID_OLD = $("#RECV_CHANN_ID_OLD").val();
	 if(null != RECV_CHANN_ID_OLD && RECV_CHANN_ID_OLD != RECV_CHANN_ID){
		 $("#DELIVER_ADDR_ID").val("");
		 $("#RECV_ADDR_NO").val("");
		 $("#RECV_ADDR").val("");
		 $("#STORE_ID").val("");
		 $("#STORE_NO").val("");
		 $("#STORE_NAME").val("");
	 }
	 $("#selectAddrParams").val(" DEL_FLAG=0 and STATE='启用' and CHANN_ID='"+RECV_CHANN_ID+"' ");
	 $("#selectAddrParams").val(" DEL_FLAG=0 and STATE='启用' and CHANN_ID='"+RECV_CHANN_ID+"' ");
 }
 function getAREA_ID(){
	 return $("#AREA_ID").val();
 }
 function getREBATEFLAG(){
	 var REBATEFLAG=$("#user_rebate").prop("checked");
	 return REBATEFLAG;
 }
 
 //获取 是否使用返利
function getIS_USE_REBATE(){
	return $("#IS_USE_REBATE").val();
}
//获取返利折扣
function getREBATEDSCT(){
	return $("#REBATEDSCT").val();
}
//获取当前 返利额
function getREBATE_CURRT(){
	return $("#REBATE_CURRT").val();
}

/**
 * 
function formCheckedEx(){
	 if($("#TEL").val()!=null && $("#TEL").val() != ""){
	        var re1 = new RegExp(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/);//电话匹配
	        var re2 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
	        var TEL1 = re1.test($("#TEL").val());
	        var TEL2 = re2.test($("#TEL").val());
	        if(!TEL1&&!TEL2 ){
	      	   parent.showErrorMsg(utf8Decode("电话格式输入不正确！"));
	           return false;
	        }
		}
	 return true;
 }
**/

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
 
 /**
  * 获取主表的单据类型
  */
 function getBILL_TYPE(){
	 return $("#BILL_TYPE").val();
 }
 
 
 