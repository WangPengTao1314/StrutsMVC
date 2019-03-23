
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	$("#reverseDiv").hide();
	var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("advctoorder.shtml?action=toList&module=" + module);
	$("#reverse").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		$("#ADVC_ORDER_ID").val(selRowId);
    	$("#reverseDiv").show();
	});
	$("#close").click(function(){
    	$("#reverseDiv").hide();
    	$("#REMARK").val("");
    	$("#ADVC_ORDER_ID").val("");
    });
    $("#audit").click(function(){
    	reverse();
    })
    $("#expdate").click(function(){
		 $("#queryForm").attr("action","advctoorder.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
});
	function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
   if (state != "1") {
		btnDisable(["reverse"]);
	}
 };
 function reverse(){
	var ADVC_ORDER_ID=$("#ADVC_ORDER_ID").val();
	var REMARK=$("#REMARK").val();
    $.ajax({
	 url: "advctoorder.shtml?action=toReverse&ADVC_ORDER_ID="+utf8(ADVC_ORDER_ID)+"&REMARK="+utf8(REMARK),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("退回成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
  function listRef(){
	 $("#pageForm").submit();
 }
