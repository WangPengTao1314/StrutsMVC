/**
 * @prjName:喜临门营销平台
 * @fileName:Paymentcmt_List
 * @author lyg
 * @time   2013-08-25 09:50:09 
 * @version 1.1
 */
$(function(){
	//页面初始化
	listPageInit("paymentclose.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("paymentclose.shtml?action=delete","PAYMENT_UPLOAD_ID");
	$("#close").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (null == selRowId ||  selRowId == "") {
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		closePayment(selRowId);
	});
});

function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
    if(state != "确认"){
		btnDisable(["close"]);
	}
}
 
 
function closePayment(selRowId){
	var PAYMENT_AMOUNT = $("#PAYMENT_AMOUNT"+selRowId).val();
	var CHANN_ID = $("#CHANN_ID"+selRowId).val();
    $.ajax({
		 url: "paymentclose.shtml?action=closePayment",
		 type:"POST",
	 	 dataType:"text",
	 	 data:{"PAYMENT_UPLOAD_ID":selRowId,"CHANN_ID":CHANN_ID,"PAYMENT_AMOUNT":PAYMENT_AMOUNT},
		 complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			  parent.showMsgPanel(utf8Decode(jsonResult.messages));
	          $("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		  }
    });
}
