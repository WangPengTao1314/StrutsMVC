/**
 * @prjName:喜临门营销平台
 * @fileName:Techorder_List
 * @author lyg
 * @time   2013-10-12 17:37:51 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("techorderprice.shtml?action=toList&module=" + module);
	$("#audit").click(function (){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		audit(selRowId);
	});
	$("#query").click(function(){
		$("#STATE option[text='提交']").remove();
	});
});
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态!=提交
	if (state !="待核价") {
		btnDisable(["audit"]);
	}
}
//审核完成
function audit(TECH_ORDER_ID){
	var TECH_ORDER_NO = $("#TECH_ORDER_NO"+TECH_ORDER_ID).val();
	var SALE_ORDER_ID = $("#SALE_ORDER_ID"+TECH_ORDER_ID).val();
	
	 $.ajax({
	 url: "techorderprice.shtml?action=toAudit",
	 type:"POST",
 	 dataType:"text",
 	 data:{"TECH_ORDER_ID":TECH_ORDER_ID,"TECH_ORDER_NO":TECH_ORDER_NO,"SALE_ORDER_ID":SALE_ORDER_ID},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("审核成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
