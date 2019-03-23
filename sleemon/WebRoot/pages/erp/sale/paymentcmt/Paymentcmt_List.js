/**
 * @prjName:喜临门营销平台
 * @fileName:Paymentcmt_List
 * @author lyg
 * @time   2013-08-25 09:50:09 
 * @version 1.1
 */
$(function(){
	//页面初始化
	listPageInit("paymentcmt.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("paymentcmt.shtml?action=delete","PAYMENT_UPLOAD_ID");
	var selRowId = $("#selRowId").val();
	if(null == selRowId || "" == selRowId){
	      btnDisable(["verify","reverse"]);
	      return;
	}
	$("#verify").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		alterState(selRowId,"verify");
	});
	$("#reverse").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		$("#PAYMENT_UPLOAD_ID").val(selRowId);
    	$("#reverseDiv").show();
	});
	$("#close").click(function(){
    	$("#reverseDiv").hide();
    	$("#REMARK").val("");
    	$("#PAYMENT_UPLOAD_ID").val("");
    });
    $("#audit").click(function(){
    	var PAYMENT_UPLOAD_ID=$("#PAYMENT_UPLOAD_ID").val();
    	alterState(PAYMENT_UPLOAD_ID,"reverse");
    })
});
 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
    if(state != "提交"){
		btnDisable(["verify","reverse"]);
	}
 }
function alterState(selRowId,stateTab){
	var REMARK=$("#REMARK").val();
    $.ajax({
	 url: "paymentcmt.shtml?action=toEditState&PAYMENT_UPLOAD_ID="+utf8(selRowId)+"&stateTab="+stateTab+"&REMARK="+utf8(REMARK),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			if(stateTab=="verify"){
				parent.showMsgPanel("确认成功");
			}else{
				parent.showMsgPanel("退回成功");
			}
			
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
