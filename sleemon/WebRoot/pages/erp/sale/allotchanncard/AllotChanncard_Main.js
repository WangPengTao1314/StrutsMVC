/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time  2014-12-03 10:35:10 
 * @version 1.1
 */
 $(function () {
     $("#querydiv").css("display","block");
     //初始化校验
	InitFormValidator("mainForm");
	
	
	$("#allot").click(function(){
		allot();
	});
	
	$("#query").click(function(){
		queryCard();
	});
	//点击加盟商之间转卡
	$("#totransfer").click(function(){
		totransfer();
	});
	
	$("#transfer").click(function(){
		parent.showConfirm("您确认要转卡吗?","topcontent.transfer();");
	});
	
	$("#calcelTransfer").click(function(){
		calcelTransfer();
	});
	
   $("#querytb input:radio[value='-1']").attr("checked","checked");
	$("#querytb td[name='c-td']").hide();
});

 
  
// 下帧调用查询
function queryCard(url){
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	var CHANN_NO = $("#CHANN_NO").val();
	var CHANN_NAME = $("#CHANN_NAME").val();
	if(null == CHANN_NO || "" == $.trim(CHANN_NO) || null == CHANN_NAME || "" == $.trim(CHANN_NAME)){
		parent.showErrorMsg("请选择'加盟商'");
		return;
	}
	var notallot = $("#querytb input:checked").val();
	parent.bottomcontent.$("#MARKETING_ACT_ID").val($("#MARKETING_ACT_ID").val());
	parent.bottomcontent.$("#MARKETING_ACT_NO").val($("#MARKETING_ACT_NO").val());
	parent.bottomcontent.$("#MARKETING_ACT_NAME").val($("#MARKETING_ACT_NAME").val());
	parent.bottomcontent.$("#CHANN_ID").val($("#CHANN_ID").val());
	parent.bottomcontent.$("#CHANN_NO").val($("#CHANN_NO").val());
	parent.bottomcontent.$("#CHANN_NAME").val($("#CHANN_NAME").val());
	parent.bottomcontent.$("#MARKETING_CARD_NO").val($("#MARKETING_CARD_NO").val());
	parent.bottomcontent.$("#CARD_SEQ_NO_BEGIN").val($("#CARD_SEQ_NO_BEGIN").val());
	parent.bottomcontent.$("#CARD_SEQ_NO_END").val($("#CARD_SEQ_NO_END").val());
	parent.bottomcontent.$("#notallot").val(notallot);
	//转卡标记为1时表示要转卡 查询渠道下未销售的卡券
	var transferFlag = $("#transferFlag").val();
	if(1 == transferFlag){
		url = "allotchanncard.shtml?action=toNoSaleList";
	}
	if(null == url || "" == url){
		url = "allotchanncard.shtml?action=toList";
	}
	parent.bottomcontent.$("#queryForm").attr("action",url);
	parent.bottomcontent.$("#queryForm").submit();
     
 
}



function getCHANN_ID(){
	var selRowId = parent.document.getElementById("selRowId").value;
	return $("#CHANN_ID"+selRowId).val();
}
 
 
 function allot(selRowId){
	 //parent.showWaitPanel();
	 var selRowId = parent.$("#selRowId").val();
	 var CHANN_ID = $("#CHANN_ID").val();
	 var CHANN_NO = $("#CHANN_NO").val();
	 var CHANN_NAME = $("#CHANN_NAME").val();
	 if(null == selRowId || "" == selRowId){
		 parent.showErrorMsg("卡券未选择或者选择的卡券已经被分配");
		 return;
	 }
	 $.ajax({
		url: "allotchanncard.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"MARKETING_CARD_IDS":selRowId,"CHANN_ID":CHANN_ID,"CHANN_NO":CHANN_NO,"CHANN_NAME":CHANN_NAME},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			   parent.showMsgPanel(utf8Decode(jsonResult.messages));
			   queryCard();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
 }
 
 
 function selChann(){
	 var MARKETING_ACT_ID = $("#MARKETING_ACT_ID").val();
	 if(null == MARKETING_ACT_ID || "" == MARKETING_ACT_ID){
		 parent.showErrorMsg("请选择营销活动");
		 return;
	 }
	 
	 $("#selChannParam").val(" MARKETING_ACT_ID='"+MARKETING_ACT_ID+"' and DEL_FLAG=0 ");
	 selCommon('BS_178', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selChannParam');
 }

 //转卡后加盟商
function selTRANChann(){
	 var MARKETING_ACT_ID = $("#MARKETING_ACT_ID").val();
	 if(null == MARKETING_ACT_ID || "" == MARKETING_ACT_ID){
		 parent.showErrorMsg("请选择营销活动");
		 return;
	 }
	 var CHANN_ID = $("#CHANN_ID").val();
	 $("#selChannParam").val(" MARKETING_ACT_ID='"+MARKETING_ACT_ID+"' and DEL_FLAG=0 and CHANN_ID !='"+CHANN_ID+"'");
	 selCommon('BS_178', false, 'TRAN_CHANN_ID', 'CHANN_ID', 'forms[0]','TRAN_CHANN_NO,TRAN_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selChannParam');
}
 
//点击转卡时，将转卡标记设置为1
function totransfer(){
	$("#transferFlag").val("1");
	var MARKETING_ACT_ID = $("#MARKETING_ACT_ID").val();
	if(null == MARKETING_ACT_ID || "" == MARKETING_ACT_ID){
		 parent.showErrorMsg("请选择营销活动");
		 return;
	}
	 
	var CHANN_ID = $("#CHANN_ID").val();
	if(null == CHANN_ID || "" == CHANN_ID){
		parent.showErrorMsg("请选择'加盟商'");
		return;
	}
	$("#notallot_2").attr("checked","checked");
	$("#allot").hide();
	$("#transfer").show();
	$("#calcelTransfer").show();
	$("#querytb td[name='c-td']").show();
	$("#c-td-l").attr("colspan","2");
	queryCard("allotchanncard.shtml?action=toNoSaleList");
	
//	parent.bottomcontent.$("#MARKETING_ACT_ID").val($("#MARKETING_ACT_ID").val());
//    parent.bottomcontent.$("#CHANN_ID").val($("#CHANN_ID").val());
//	parent.bottomcontent.$("#queryForm").attr("action","allotchanncard.shtml?action=toNoSaleList");
//	parent.bottomcontent.$("#queryForm").submit();
}

//取消转卡
function calcelTransfer(){
	$("#transferFlag").val("");
	$("#allot").show();
	$("#transfer").hide();
	$("#calcelTransfer").hide();
	$("#querytb td[name='c-td']").hide();
	$("#c-td-l").attr("colspan","6");
}

//确认转卡
function transfer(){
	 var NO_SALE_ID = parent.$("#NO_SALE_ID").val();
	 var TRAN_CHANN_ID = $("#TRAN_CHANN_ID").val();
     var TRAN_CHANN_NO = $("#TRAN_CHANN_NO").val();
     var TRAN_CHANN_NAME = $("#TRAN_CHANN_NAME").val();
 
     var MARKETING_ACT_ID = $("#MARKETING_ACT_ID").val();
	 if(null == MARKETING_ACT_ID || "" == MARKETING_ACT_ID){
		 parent.showErrorMsg("请选择营销活动");
		 return;
	 }
	 
	 var CHANN_ID = $("#CHANN_ID").val();
	 if(null == CHANN_ID || "" == CHANN_ID){
		parent.showErrorMsg("请选择'加盟商'");
		return;
	 }
	 
	 if(null == TRAN_CHANN_ID || "" == TRAN_CHANN_ID){
		 parent.showErrorMsg("转卡后加盟商未选择");
		 return;
	 }
	 
	 if(null == NO_SALE_ID || "" == NO_SALE_ID){
		 parent.showErrorMsg("卡券未选择");
		 return;
	 }
	 $.ajax({
		url: "allotchanncard.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"MARKETING_CARD_IDS":NO_SALE_ID,"CHANN_ID":TRAN_CHANN_ID,"CHANN_NO":TRAN_CHANN_NO,"CHANN_NAME":TRAN_CHANN_NAME},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			   parent.showMsgPanel(utf8Decode(jsonResult.messages));
			   queryCard();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });

}