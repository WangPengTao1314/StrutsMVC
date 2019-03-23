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
	listPageInit("techordershow.shtml?action=toList&module=" + module);
 
	$("#print").click(function(){
		parent.showErrorMsg("该功能正在开发中.......");
	})
});
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态!=提交
	if (state !="提交") {
		btnDisable(["audit"]);
	}
	
}
//审核完成
function audit(TECH_ORDER_ID){
	 $.ajax({
	 url: "techordershow.shtml?action=toAudit&TECH_ORDER_ID="+utf8(TECH_ORDER_ID),
	 type:"POST",
 	 dataType:"text",
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
