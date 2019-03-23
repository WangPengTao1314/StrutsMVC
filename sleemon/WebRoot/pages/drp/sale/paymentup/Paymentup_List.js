/**
 * @prjName:喜临门营销平台
 * @fileName:Paymentup_List
 * @author lyg
 * @time   2013-08-23 10:25:58 
 * @version 1.1
 */
$(function(){
	//页面初始化
	listPageInit("paymentup.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("paymentup.shtml?action=delete","PAYMENT_UPLOAD_ID");
	var selRowId = $("#selRowId").val();
	if(null == selRowId || "" == selRowId){
	      btnDisable(["modify","delete","commit"]);
	      return;
	}
	$("#commit").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if(null == selRowId || "" == selRowId){
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		commit(selRowId);
	});
});
 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
    if(state != "未提交"&&state!="退回"){
		btnDisable(["modify","delete","commit"]);
	}
 }
function commit(selRowId){
	parent.showWaitPanel();
    $.ajax({
	 url: "paymentup.shtml?action=toCommit&PAYMENT_UPLOAD_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("提交成功");
            $("#pageForm").submit();
		}else{
			parent.closeWindow();
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
