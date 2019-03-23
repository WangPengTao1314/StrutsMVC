/**
 * @prjName:喜临门营销平台
 * @fileName:prdreturnreq_List
 * @author wzg
 * @time   2013-08-15 10:17:13 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;
    
	//主从及主从从列表页面通用加载方法
	listPageInit("senddirectnotice.shtml?action=toList&module=" + module);
	$("#audit").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		var date=window.showModalDialog("senddirectnotice.shtml?action=toAuditInfo&BASE_DELIVER_NOTICE_ID="+selRowId,window,"dialogwidth=800px; dialogheight=600px; status=no")
		if(date){
			$("#pageForm").submit();
		}
	})
});

function toAdudit(selRowId){
  $.ajax({
	 url: "senddirectnotice.shtml?action=toAudit",
	 type:"POST",
 	 dataType:"text",
 	 data:{"BASE_DELIVER_NOTICE_ID":selRowId},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			 parent.showMsgPanel(utf8Decode(jsonResult.messages));
			 $("#queryForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
			return;
		}
	  }
   });
}
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=已处理
	if (state == "已处理") {
		btnDisable(["audit"]);
	}
}
function listRef(){
	 $("#pageForm").submit();
 }
