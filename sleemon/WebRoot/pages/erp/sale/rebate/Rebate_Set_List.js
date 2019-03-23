$(function() {
	$("#q_add").click(function () {
		setAddRebateAmount();
	});
	
	$("#q_close").click(function () {
		window.close();
	});
});

function setAddRebateAmount(){
		// 校验整型内容
	if (!CheckFloatInput($("#SET_REBATE"))) {
		return false;
	}
					
					
	var SET_REBATE = $("#SET_REBATE").val();
	var CHANN_NOS = $("#CHANN_NOS").val();
	var DESCON = $("#DESCON").val();
	 $.ajax({
	 	url: "rebate.shtml?action=setRebateAmount&SET_REBATE="+SET_REBATE+"&CHANN_NOS="+CHANN_NOS+"&DESCON="+DESCON,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("设置返利成功!");
				window.close();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	
}
