/**
 * @prjName:喜临门营销平台
 * @fileName:销售订单审核
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("saleorderrls.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("saleorderrls.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("saleorderrls.shtml?action=delete", "SALE_ORDER_ID");
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		var BILL_TYPE = $("#BILL_TYPE"+selRowId).val();
		if("非标" == BILL_TYPE){
			checkCommit(selRowId);
		}else{
			toFlow("1");
		}
	});
    
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("3");
	});
	
	$("#audit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
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
	 $("#expdate").click(function(){
		 $("#queryForm").attr("action","saleorderrls.shtml?action=toExpData&module=" + module);
		 $("#queryForm").submit();
	 });
	
//	$("#query").click(function(){
//		$("#STATE option[text='未提交']").remove();
//	});
	
  //dateDiffColor();

});




//查询 对应的 工艺单 的状态 是 “已核价” 才能提交
function checkCommit(selRowId){
	$.ajax({
		url: "saleordersp.shtml?action=queryTechSate",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var sate = utf8Decode(jsonResult.data.STATE);
				var techNo = jsonResult.data.TECH_ORDER_NO;
				//可生产货品的数量
				var NOT_CAN_PRD_COUNT = jsonResult.data.NOT_CAN_PRD_COUNT;
				NOT_CAN_PRD_COUNT = parseInt(NOT_CAN_PRD_COUNT);
				if("已核价" == sate){
					if(!isNaN(NOT_CAN_PRD_COUNT) && NOT_CAN_PRD_COUNT > 0){
						parent.showErrorMsg("该单据下有货品不能生产，请取消后再提交");
					}else{
						toFlow("1");
					}
					
				}else if(null !=techNo && "" !=techNo){
					parent.showErrorMsg("工艺单单号'"+techNo+"'，还未核价");
				}
				
			}else{
				 //表示 工艺单 不生产
				 if("0" == jsonResult.messages){
					 btnDisable(["commit"]); 
				 }
			}
		}
	});
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
	var paramUrl =  document.getElementById("paramUrl").value;
//	paramUrl = utf8Decode(paramUrl);
	 
    document.affirm.sourceURI.value=ctxPath+"/saleorderrls.shtml?action=toList"+paramUrl;
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
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	var TECH_STATE = document.getElementById("TECH_STATE" + selRowId).value;
	var STANDFLAG=document.getElementById("STANDFLAG" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit"]);
	}
	
	//当状态=撤销
	if (state == "\u64a4\u9500") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "\u5426\u51b3") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
	if(state!="未提交" ){
		btnDisable(["commit"]);
	}
	 
}
