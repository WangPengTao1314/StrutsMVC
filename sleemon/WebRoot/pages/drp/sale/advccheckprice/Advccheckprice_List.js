
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	$("#query").click(function(){
		$("#STATE option[text='未提交']").remove();
		$("#STATE option[text='待发货']").remove();
		$("#STATE option[text='已发货']").remove();
		$("#STATE option[text='已收货']").remove();
		$("#STATE option[text='待确认']").remove();
	});
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("advccheckprice.shtml?action=toList&module=" + module);
	//提交按钮单独写
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("是否提交?","topcontent.commit('commit');");
	});
    $("#reverse").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		$("#audit").show();
	});
    $("#search").click(function(){
    	var RETURN_RSON=$("#RETURN_RSON").val();
		if(""!=RETURN_RSON&&null!=RETURN_RSON){
		if(RETURN_RSON.length>120){
			parent.showErrorMsg(utf8Decode("退回原因超过最大字符数！"));
	           return false;
		}
		}
    	commit("reverse",RETURN_RSON);
    })
	$("#close").click(function(){
		$("#RETURN_RSON").val("");
		$("#audit").hide();
	})
	setSelOperateEx();
});
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	if(state!="待核价"){
		btnDisable(["commit","reverse"]);
	}
}
function commit(type,RETURN_RSON){
	var selRowId = parent.document.getElementById("selRowId").value;
	showWaitPanel();
	$.ajax({
	 url: "advccheckprice.shtml?action=toCommit&ADVC_ORDER_ID="+utf8(selRowId)+"&type="+utf8(type)+"&RETURN_RSON="+utf8(RETURN_RSON),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			if("reverse"==type){
				parent.showMsgPanel("退回成功");
			}else{
				parent.showMsgPanel("提交成功");
			}
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
			closeWindow();
		}
	  }
    });
}