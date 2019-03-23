

/**
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */
$(function (){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("channcontractup.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("channcontractup.shtml?action=delete", "CHANN_CONTRACT_ID");
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toCommit(selRowId);
	});
	
});
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit","close"]);
	}
}
function toCommit(selRowId){
	$.ajax({
	 url: "channcontractup.shtml?action=toCommit&CHANN_CONTRACT_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("提交成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
    setSelOperateEx();
}

