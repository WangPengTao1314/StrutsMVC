/**
 * @prjName:喜临门营销平台
 * @fileName:分销 -订货订单维护
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
$(function(){
	closePage();
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	 
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("goodsorderhd.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("goodsorderhd.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("goodsorderhd.shtml?action=delete", "GOODS_ORDER_ID");
	
	$("#query").click(function(){
		var AREA_SER_ID = $("#AREA_SER_ID").val();
		if(null == AREA_SER_ID || "" == AREA_SER_ID){
			$("#STATE option[text='区域服务中心退回']").remove();
		}else{
			$("#STATE option[text='总部退回']").remove();
		}
		if("wh" == module){
		     $("#STATE option[text='提交']").remove();
		     $("#STATE option[text='否决']").remove();
		     $("#STATE option[text='撤销']").remove();
		}
		if("sh" == module){
		     $("#STATE option[text='制作']").remove();
		     $("#STATE option[text='撤销']").remove();
		     $("#STATE option[text='审核通过']").remove();
		} 
	});
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		var BILL_TYPE = $("#BILL_TYPE"+selRowId).val();
		var REBATE_CURRT = $("#REBATE_CURRT").val();
		var ORDER_AMOUNT = $("#ORDER_AMOUNT"+selRowId).val();
		REBATE_CURRT = parseFloat(REBATE_CURRT);
		ORDER_AMOUNT = parseFloat(ORDER_AMOUNT);
		if("返利订货" == BILL_TYPE && REBATE_CURRT<ORDER_AMOUNT){
			parent.showErrorMsg("返利金额不足，不能使用返利下单");
			return;
		}
		toCommit(selRowId);
		
	});
    
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
//		toFlow("3");
		doRevoke(selRowId);
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
	
	secondPageCommit();
	
	$("#tempCredit").click(function(){
		tempCredit();
	});
	
	$("#rebackQuery").click(function(){
		 $("#flag").val("reback");
		 document.getElementById("q_search").click();
	});
	
	$("#sendQuery").click(function(){
		 $("#flag").val("isSend");
		 document.getElementById("q_search").click();
	});
	
	
});


//获取 是否使用返利
function getIS_USE_REBATE(){
	var selRowId = parent.document.getElementById("selRowId").value; 
	return $("#IS_USE_REBATE"+selRowId).val();
}

//获取返利折扣
function getREBATEDSCT(){
	var selRowId = parent.document.getElementById("selRowId").value; 
	return $("#REBATEDSCT"+selRowId).val();
}
//获取当前 返利额
function getREBATE_CURRT(){
	var selRowId = parent.document.getElementById("selRowId").value; 
	return $("#REBATE_CURRT"+selRowId).val();
}


//编辑页面 点击[保存并提交]，先跳转到一览页面 然后根据穿过来的参数GOODS_ORDER_ID 自动提交
function secondPageCommit(){
	var GOODS_ORDER_ID =  parent.document.getElementById("GOODS_ORDER_ID").value;
	var doCommitSave = parent.document.getElementById("doCommitSave").value;
	if("true" == doCommitSave){
		if(null != GOODS_ORDER_ID && "" != GOODS_ORDER_ID){
//		   toFlow("1",GOODS_ORDER_ID);
			toCommit(GOODS_ORDER_ID);
	    }
	}
	parent.document.getElementById("doCommitSave").value=false;
	parent.document.getElementById("doCommitSave").value="";
}

//撤销 不走审批
function doRevoke(GOODS_ORDER_ID){
	 var GOODS_ORDER_NO = $("#GOODS_ORDER_NO_"+GOODS_ORDER_ID).val();
	$.ajax({
		url: "goodsorderhd.shtml?action=revoke",
		type:"POST",
		dataType:"text",
		data:{"GOODS_ORDER_ID":GOODS_ORDER_ID,"GOODS_ORDER_NO":GOODS_ORDER_NO},
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

 function toFlow(i,GOODS_ORDER_ID) {
	 var cutid = "";
	if(null != GOODS_ORDER_ID && ""!=GOODS_ORDER_ID){
		cutid = GOODS_ORDER_ID
	}else{
		cutid = $("#selRowId").val();
	}
	
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/goodsorderhd.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
 
 
 function toCommit(selRowId){
	 parent.showWaitPanel();
	 var ORDER_AMOUNT = "";
	 ORDER_AMOUNT = $("#ordertb input:checked").parent().parent().find("td[name='ORDER_AMOUNT']").text();
	 ORDER_AMOUNT = $.trim(ORDER_AMOUNT);
	 var GOODS_ORDER_NO = $("#GOODS_ORDER_NO_"+selRowId).val();
	 var BILL_TYPE = $("#type"+selRowId).val();
	 $.ajax({
		url: "goodsorderhd.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"GOODS_ORDER_ID":selRowId,"ORDER_AMOUNT":ORDER_AMOUNT,"GOODS_ORDER_NO":GOODS_ORDER_NO,"BILL_TYPE":BILL_TYPE},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
			    $("#pageForm").submit();
			}else{
				parent.closeWindow();
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
 }
 
 function showPage(){
	 parent.showWaitPanel();
	 $.ajax({
		url: "goodsorderhd.shtml?action=getUserCredit",
		type:"POST",
		dataType:"text",
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var data = jsonResult.data;
				$("#userCredit").val(data.canUserCredit);
				$("#midden").hide();
				 var v = $("#testDiv").html();
				 var html = '<div style="position:absolute;top:10%;left:10%;width:50%;height:50%;filter:alpha(opacity=100);">' 
					 +v+
				 '</div>';
				 parent.$("#midden").append(html);
				 parent.closeWindow();
			}else{
				parent.closeWindow();
				alert(utf8Decode(jsonResult.messages));
			}
		}
	});
	 parent.closeWindow();
	 //$("#mycredit_show").show();
 }
 function closePage(){
	 $("#mycredit_show").hide();
 }
  //设置 一览页面 ‘订货总额’
 function setTotalAmount(total){
	 var selRowId = parent.document.getElementById("selRowId").value;
	 var name="TOTL_AMOUNT_"+selRowId;
	 total =  total+"&nbsp;";
	 $("#ordertb").find("td[name='"+name+"']").html(total);
 }
 
 
 function getAREA_ID(){
	 var selRowId = parent.document.getElementById("selRowId").value; 
	 return $("#AREA_ID_"+selRowId).val();
 }
 
 //临时信用申请
 function tempCredit(){
	 var mainFrame = window.top.mainFrame;  
	  mainFrame.addTab("w-009","临时信用申请","../../temp_credit_req.shtml?action=toFrame&module=wh");
 }
 
 
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
  
	//当状态=未提交
	if (state == "未提交") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "提交") {
		btnDisable(["delete","modify","commit"]);
	}
	
	//当状态=撤销
	if (state == "撤销") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "否决") {
		btnDisable(["revoke","audit"]);
	}
 
	//当状态=审核通过
	if (state == "审核通过") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
	
	if(state == "制作" || state == "总部退回"|| state == "区域服务中心退回"){
		btnDisable(["revoke"]);
	}
	if(state == "未处理"){
		btnDisable(["delete","modify","commit","audit"]);
	}
	if(state == "已处理"){
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
//	var type = document.getElementById("type" + selRowId).value;
//	if("返利订货"==type){
//		btnDisable(["modify"]);
//	}
}
 

function getREBATEFLAG(){
	 var selRowId = parent.document.getElementById("selRowId").value;
	 var type = document.getElementById("type" + selRowId).value;
	 if("返利订货"==type){
		 return true;
	 }else{
		 return false;
	 }
 }



/**
  * 获取主表的单据类型
  */
 function getBILL_TYPE(){
	 var selRowId = parent.document.getElementById("selRowId").value;
	 return $("#BILL_TYPE"+selRowId).val();
 }
 
 
 
