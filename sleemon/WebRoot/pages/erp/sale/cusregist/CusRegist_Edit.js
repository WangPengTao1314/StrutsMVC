/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	//初始化校验
	InitFormValidator(0);	
	
	$("#save").click(function(){
		save();
	});
});

 

function loadCard(){
	 var MARKETING_CARD_NO = $("#MARKETING_CARD_NO").val();
	 MARKETING_CARD_NO = $.trim(MARKETING_CARD_NO);
	 if(null == MARKETING_CARD_NO || "" == MARKETING_CARD_NO){
		 return;
	 }
	 $.ajax({
		url: "cusregist.shtml?action=loadCard",
		type:"POST",
		dataType:"text",
		data:{"MARKETING_CARD_NO":MARKETING_CARD_NO},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    var data = jsonResult.data;
			     var inputs = $("#jsontb input[json]");
				 inputs.each(function(){
					var id = $(this).attr("id");
					$("#"+id).val(eval("data."+id));
				 });
			     $("#REMARK").val(data.REMARK);
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
}

 
function selTerm(){
	var CHANN_ID = $("#CHANN_ID").val();
	$("#selTermParam").val(" CHANN_ID_P='"+CHANN_ID+"' and DEL_FLAG=0 and STATE='启用' ");
	selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[0]','TERM_NO,TERM_NAME', 'TERM_NO,TERM_NAME', 'selTermParam');
}
 
function save(){
	if(!formChecked('mainForm')){
		return;
	}
	
	var childJsonData = siglePackJsonData();
	$.ajax({
		url: "cusregist.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":childJsonData},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    parent.showMsgPanel(utf8Decode(jsonResult.messages));
			    parent.$("#YT_MSG_BTN_OK").click(function(){
			    	window.close();
			        window.returnValue='1';
			    });
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
}