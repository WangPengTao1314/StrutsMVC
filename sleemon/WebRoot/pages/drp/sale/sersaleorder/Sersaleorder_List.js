/**
 * @prjName:喜临门营销平台
 * @fileName:分销-区域服务中心-销售订单处理
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	//删掉查询状态里的提交，撤销，否决状态
//	$("#query").click(function(){
//		$("#STATE option[text='提交']").remove();
//		$("#STATE option[text='撤销']").remove();
//		$("#STATE option[text='否决']").remove();
//		$("#STATE").append("<option value='取消预订'>取消预订</option>");
//	});
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("sersaleorder.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("sersaleorder.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("sersaleorder.shtml?action=delete", "SALE_ORDER_ID");
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
//		commit(selRowId);
		toFlow("1");
	});
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (null == selRowId ||  selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("3");
	});
	$("#audit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (null == selRowId || selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("2");
	});
	$("#flow").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("4");
	}); 
	
	$("#query").click(function(){
		$("#STATE option[text='提交']").remove();
		$("#STATE option[text='撤销']").remove();
		$("#STATE option[text='否决']").remove();
		
	});
});

//提交
function commit(selRowId){
	$.ajax({
		url: "sersaleorder.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess(utf8Decode(jsonResult.messages),"sersaleorder.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/sersaleorder.shtml?action=toList"+document.getElementById("paramUrl").value ;
	switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}
 //设置 一览页面 ‘订货总额’
 function setTotalAmount(total){
	 var selRowId = parent.document.getElementById("selRowId").value;
	 var name="TOTL_AMOUNT_"+selRowId
	 $("#ordertb").find("td[name='"+name+"']").text(total);
 }
 
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=提交
	if (state == "提交" || state == "审核通过" ) {
		btnDisable(["modify","commit"]);
	}
 
}
