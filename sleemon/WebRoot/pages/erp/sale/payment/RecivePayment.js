/**
 * @prjName:喜临门营销平台
 * @fileName:Payment_List
 * @author glw
 * @time   2013-08-15 09:31:13 
 * @version 1.1
 */
$(function(){
	InitFormValidator(0);
	$("#save").click(function(){
		var CHANN_ID=$("#CHANN_ID").val();
		var REMARK=$("#REMARK").val();
		var PAY_AMONT=$("#PAY_AMONT").val();
		if(""==CHANN_ID||null==CHANN_ID){
			parent.showMsgPanel("请输入渠道id");
			return;
		}
		if(""==PAY_AMONT||null==PAY_AMONT){
			parent.showMsgPanel("请输入付款金额");
			return;
		}
		if(""==REMARK||null==REMARK){
			parent.showMsgPanel("请输入备注");
			return;
		}
		childSave();
	});	
});
function childSave() {
	var jsonData = siglePackJsonData();
	$.ajax({
		url : ctxPath+"/payment.shtml?action=InstPayMentDtl",
		type : "POST",
		dataType : "text",
		data : {"jsonData" : jsonData},
		complete : function(xhr) {
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("保存成功");
				window.close();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
}