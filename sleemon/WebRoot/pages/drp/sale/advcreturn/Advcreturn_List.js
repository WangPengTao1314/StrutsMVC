/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_List
 * @author lyg
 * @time   2013-08-19 14:35:52 
 * @version 1.1
 */
$(function(){
	//删掉查询状态里的未提交和已入库和已结算状态
	$("#query").click(function(){
		$("#STATE option[text='未提交']").remove();
		$("#STATE option[text='已入库']").remove();
		$("#STATE option[text='已结算']").remove();
	});
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("advcreturn.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("advcreturn.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("advcreturn.shtml?action=delete", "ADVC_ORDER_ID");
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
     $("#print").click(function (){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
		//parent.showErrorMsg("功能开发中。。。");
    })
});
 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
    if(state != "提交"){
		btnDisable(["commit","reverse"]);
	}
 }
 function listRef(){
	 $("#pageForm").submit();
 }
  function reverse(){
	var ADVC_ORDER_ID=$("#ADVC_ORDER_ID").val();
	var REMARK=$("#REMARK").val();
    $.ajax({
	 url: "advcreturn.shtml?action=toReverse&ADVC_ORDER_ID="+utf8(ADVC_ORDER_ID),
	 type:"POST",
	 data:{"REMARK":REMARK},
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
function print(selRowId){
	//跳转扫码打印页面
//	$("#printIframe").attr("src","advcreturn.shtml?action=printInfo&ADVC_ORDER_ID="+selRowId);
	window.open("advcreturn.shtml?action=printInfo&ADVC_ORDER_ID="+selRowId,'报表查看','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
}
function countPrint(){
	$("#queryForm").submit();
}