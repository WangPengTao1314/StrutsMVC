
$(function(){
	$("#allChecked").click(function(){
			var flag = document.getElementById("allChecked").checked;
			if(flag){
				$("#ordertb :checkbox").attr("checked","true");
			}else{
				$("#ordertb :checkbox").removeAttr("checked");
			}
		});

	$("#q_close").click(function () {
		window.close();
	});
	
	$("#q_commit").click(function () {
		submit();
	});
	$("#q_search").click(function () {
		$("#queryForm").submit();
	});
	
	$("#q_reset").click(function () {
		$("#CHANN_ID").val("");
		$("#CHANN_NO").val("");
		$("#CHANN_NAME").val("");
		$("#BILL_TYPE").val("");
		$("#REBATE_TYPE").val("");
	});
});

function submit(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条明细")
		return;
	}
	
	var ids = getAllCheckBox();
	$.ajax({
		url: "erprebatechangereq.shtml?action=batchAudit",
		type:"POST",
		dataType:"text",
		data:{"REBATE_CHANGE_REQ_IDS":ids},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("审核成功");
				window.returnValue = 'SUCC';
				window.close();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function getAllCheckBox(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	return ids;
}

