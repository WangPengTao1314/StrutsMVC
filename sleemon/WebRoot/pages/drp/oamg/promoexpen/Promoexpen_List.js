/**
 * @prjName:喜临门营销平台
 * @fileName:Promoexpen_List
 * @author chenj
 * @time   2014-01-24 10:59:55 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
	//页面初始化
	listPageInit("promoexpen.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("promoexpen.shtml?action=delete","PRMT_COST_REQ_ID");
	var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
      btnDisable(["modify","modifyT","delete","commit","revoke","audit","flow"]);
      return;
    }
    // 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("该操作会停用记录，是否继续?","topcontent.listStopConfirm();");
	});
	
	// 启用
	$("#start").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("该操作会启用记录，是否继续?","topcontent.listStartConfirm();");
   });
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}else{
	       $.ajax({
			    url:"promoexpen.shtml?action=isCheckMoney", 
			    type:"POST", 
			    dataType:"text", 
			    data:{"PRMT_COST_REQ_ID":selRowId}, 
			    complete:function (xhr) {
				eval("jsonResult = " + xhr.responseText);
				if (jsonResult.success === true) {
					var result = jsonResult.data;
					var BUDGET_QUOTA = result.BUDGET_QUOTA; //预算额度
					var FREEZE_BUDGET_QUOTA = result.FREEZE_BUDGET_QUOTA; //冻结额度
					var USE_BUDGET_QUOTA = result.USE_BUDGET_QUOTA;       //已使用额度
					var RESIDUE = BUDGET_QUOTA-FREEZE_BUDGET_QUOTA-USE_BUDGET_QUOTA;
					//alert("RESIDUE===="+RESIDUE);
					var BUDGET_AMOUNT = result.BUDGET_AMOUNT;
					//alert("BUDGET_AMOUNT=="+BUDGET_AMOUNT);
					if(eval(BUDGET_AMOUNT)>eval(RESIDUE)){
					     parent.showErrorMsg("预批金额不能大于剩余预算额度");
						 return;
					}else{
					    btnDisable(["commit"]);
					    //toFlow("1");
					    toCommit(selRowId);
					}
				} else {
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}});
		}
		//toFlow("1");
	});
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		//toFlow("3");
		parent.showConfirm("您确认撤销该条信息吗?","topcontent.toFlow(3);","N");
	});
	$("#audit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		//toFlow("2");
		btnDisable(["audit"]);
	    toAudit(selRowId);
	});
	$("#flow").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("4");
	}); 
	
	$("#modifyT").click(function(){
		 var selRowId = $("#selRowId").val();
	 	 if(null == selRowId || "" == selRowId){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
	 	 	
	 	 	setCommonPageInfo(true);
			parent.window.gotoBottomPage("toEdit");
	 	 }
	});
	
	 $("#delete").click(function(){
	     var selRowId = $("#selRowId").val();
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("您确认删除该条信息吗?","topcontent.listDelConfirm();");
	 });
	 
	 $("#expertExcel").click(function(){
	     var search = $("#search").val();
	     var PRMT_COST_REQ_NO = $("#PRMT_COST_REQ_NO").val();
	     var STATE = $("#STATE").val();
		 $("#queryForm").attr("action","promoexpen.shtml?action=expertExcel&module=" + getModule()+"&search="+search+"&PRMT_COST_REQ_NO="+PRMT_COST_REQ_NO);
		 $("#queryForm").submit();
	});
	
	 $("#print").click(function (){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
    });
});

function print(selRowId){
	//跳转扫码打印页面
//	$("#printIframe").attr("src","advcorder.shtml?action=printInfo&ADVC_ORDER_ID="+selRowId);
	window.open('promoexpen.shtml?action=printInfo&PRMT_COST_REQ_ID='+selRowId,'推广费用申请','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
}

//删除记录
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "promoexpen.shtml?action=delete&PRMT_COST_REQ_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				$("#pageForm").submit();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/promoexpen.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
	var audit = document.getElementById("audit1").value;
	
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		if(audit!="1"){//如果不是审核页面，再变灰，审核页面不能变灰
			btnDisable(["delete","modifyT","commit"]);
		}
		
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
		btnDisable(["delete","modifyT","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete","modifyT","revoke","commit","audit"]);
	}
}

function state(params){
    var state = params.value;
    var PVG_EDIT_AUDIT = $("#PVG_EDIT_AUDIT").val();
    //按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "未提交") {
		btnDisable(["revoke","audit"]);
	}
	//当状态=提交
	if (state == "提交") {
		if(1 == PVG_EDIT_AUDIT){
		    
			btnDisable(["delete","commit"]);
		}else{
			btnDisable(["delete","modify","commit"]);
		}
	}
	//当状态=撤销
	if (state == "撤销") {
		btnDisable(["revoke","audit"]);
	}
	//当状态=否决
	if (state == "否决") {
		btnDisable(["revoke","audit"]);
	}
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "审核通过") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	} 
 }

//停用记录
function listStopConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "promoexpen.shtml?action=changeStateStop&PRMT_COST_REQ_ID="+utf8(selRowId),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}
//启用记录
function listStartConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "promoexpen.shtml?action=changeStateStart&PRMT_COST_REQ_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("状态修改成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}

 function toCommit(selRowId){
	 showWaitPanel();
	 $.ajax({
		url: "promoexpen.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"PRMT_COST_REQ_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 toFlow("1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				closeWindow();
			}
		}
	 });
 }
 
  function toAudit(selRowId){
    //showWaitPanel();
	 $.ajax({
		url: "promoexpen.shtml?action=toAuditT",
		type:"POST",
		dataType:"text",
		data:{"PRMT_COST_REQ_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				toFlow("2");
				//closeWindow();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
				//closeWindow();
			}
		}
	 });
 }

