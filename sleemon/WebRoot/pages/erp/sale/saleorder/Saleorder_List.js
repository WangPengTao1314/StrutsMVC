/**
 * @prjName:喜临门营销平台
 * @fileName:标准订单 一览
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
	listPageInit("saleorder.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("saleorder.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("saleorder.shtml?action=delete", "SALE_ORDER_ID");
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		commit(selRowId);
//		toFlow("1");
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
//		$("#STATE option[text='未提交']").remove();
		$("#BILL_TYPE option[text='非标']").remove();
	});
	
	 //时间差变色 
 //dateDiffColor();
 $("#expdate").click(function(){
		 $("#queryForm").attr("action","saleorder.shtml?action=toExpData&module=" + module);
		 $("#queryForm").submit();
	 });
});

//提交
function commit(selRowId){
	var SALE_ORDER_NO = $("#SALE_ORDER_NO"+selRowId).val();
	$.ajax({
		url: "saleorder.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId,"SALE_ORDER_NO":SALE_ORDER_NO},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


function openReportUrl(obj,SALE_ORDER_NO){
	//showErrorMsg("该报表开发还未完成，敬请期待！");
	var title = $(obj).attr("title");
	var url = "../../drp/report/saleProStatus.shtml?action=toFrame&SALE_ORDER_NO="+SALE_ORDER_NO;
	toShowPage(title,url);
}


function toShowPage(lable,urlAll){
	var mainFrame = window.top.mainFrame;
 	mainFrame.addTab(lable,lable,urlAll);
}


function orpenWindow(SALE_ORDER_ID){
	window.showModalDialog("saleorderview.shtml?action=toGoodsFrame&SALE_ORDER_ID="+SALE_ORDER_ID,window,"dialogwidth=900px; dialogheight=700px; status=no");
}



//时间差变色  
function dateDiffColor(){
	var inputs = $("#ordertb tr:gt(0)").find("input[name='DATE_DIFF']");
	inputs.each(function(){
		var date = $(this).val();
		date = parseInt(date);
		if(date >=1 ){
			$(this).parent().parent().find("td").css("background-color", "#E693A2");//花号还原
			  
		}
	});
}


 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/saleorder.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
	if (state != "未提交") {
		btnDisable(["commit","modify","delete"]);
	}
 
}
 function getCHANN_ID(){
	 var selRowId = parent.document.getElementById("selRowId").value;
	 return $("#RECV_CHANN_ID"+selRowId).val();
 }
 function getBILL_TYPE(){
	 var selRowId = parent.document.getElementById("selRowId").value;
	 return $("#BILL_TYPE"+selRowId).val();
 }