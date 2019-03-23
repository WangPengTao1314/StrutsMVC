/**
 * @prjName:供应链_贵人
 * @fileName:Dststoreout_List
 * @author zsl
 * @time   2016-01-11 15:05:08 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("dststoreout.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("dststoreout.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("dststoreout.shtml?action=delete", "STOREOUT_ID");
	$("#query").click(function(){
		$("#STATE option[text='已取消']").remove();
	});
	$("#done").click(function(){
	  parent.showConfirm("您确认要进行出库操作？点击确认则继续","topcontent.listCommitConfirm();");
	});
	$("#print").click(function (){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
	})
	setSelOperateEx();
});
function getFROM_BILL_ID(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var FROM_BILL_ID=$("#from"+selRowId).val();
	return FROM_BILL_ID;
}
function getSTOREOUT_STORE_ID(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var STOREOUT_STORE_ID=$("#storeout"+selRowId).val();
	return STOREOUT_STORE_ID;
}

//出库处理按钮的
function listCommitConfirm(){
	parent.showWaitPanel();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "dststoreout.shtml?action=toCommit&STOREOUT_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("处理成功");
            $("#pageForm").submit();
		}else{
			var data = jsonResult.data;
			if(null != data && "" != data){
				var PRD_NO = data.PRD_NO;
				parent.showErrorMsg("货品["+PRD_NO+"]出库数量不能大于订货数量!");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  }
    });
}
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state != "未处理") {
		btnDisable(["modify","delete","done"]);
	}
}
function print(selRowId){
	//跳转扫码打印页面
	window.open("dststoreout.shtml?action=printInfo&STOREOUT_ID="+selRowId,'报表查看','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
}