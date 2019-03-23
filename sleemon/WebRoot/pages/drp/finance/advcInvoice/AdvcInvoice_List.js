
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;
	//主从及主从从列表页面通用加载方法
	listPageInit("advcinvoice.shtml?action=toList&module=" + module);
    
    $("#query").click(function(){
		$("#STATE option[text='未提交']").remove();
		$("#STATE option[text='提交']").remove();
		$("#STATE option[text='退回']").remove();
		$("#STATE option[text='待核价']").remove();
		$("#STATE option[text='待结算']").remove();
		$("#STATE option[text='已结算']").remove();
		$("#STATE option[text='关闭']").remove();
		$("#STATE option[text='已收货']").remove();
		$("#STATE option[text='待确认']").remove();
	});
    $("#commit").click(function(){
    	$('#commitDiv').show();
    })
	setSelOperateEx();
});

		
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var NEED_RECEIPT_FLAG = document.getElementById("flag" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if ("1"==NEED_RECEIPT_FLAG) {
		btnDisable(["commit"]);
	}
}

function commitInvoice(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var RECEIPT_REMARK=$("#RECEIPT_REMARK").val();
	var RECEIPT_NO=$("#RECEIPT_NO").val();
	if(""==RECEIPT_NO){
		alert("请输入发票号");
	}
    $.ajax({
	 url: "advcinvoice.shtml?action=commitInvoice",
	 type:"POST",
 	 dataType:"text",
 	 data:{"ADVC_ORDER_ID":selRowId,"RECEIPT_REMARK":RECEIPT_REMARK,"RECEIPT_NO":RECEIPT_NO},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("开票成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
    setSelOperateEx();
}
function closeDiv(){
	$("#commitDiv").hide();
}
