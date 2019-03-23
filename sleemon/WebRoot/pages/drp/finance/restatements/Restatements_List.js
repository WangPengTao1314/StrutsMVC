/**
 * @prjName:喜临门营销平台
 * @fileName:Restatements_List
 * @author chenj
 * @time   2013-10-12 15:21:43 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
	//页面初始化
	listPageInit("restatements.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("restatements.shtml?action=delete","STATEMENTS_ID");
	 
    /*$("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("1");
	});*/
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
});
 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/restatements.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
}

 function queryReturnStatementNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" BILL_TYPE='客户退货结算' and LEDGER_ID = '"+ztxxid+"'");
	selCommon('BS_59', false, 'STATEMENTS_NO', 'STATEMENTS_NO', 'forms[1]','STATEMENTS_NO', 'STATEMENTS_NO','con');
 }

//查询预定单列表
 function queryOrderNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" BILL_TYPE='终端退回' and DEL_FLAG=0 and LEDGER_ID = '"+ztxxid+"'");
	selCommon('BS_55', false, 'ADVC_ORDER_NO', 'ADVC_ORDER_NO', 'forms[1]','ADVC_ORDER_NO', 'ADVC_ORDER_NO','con');
 }
 
 //查询终端列表
 function queryTeamNo(){
 	var ztxxid = $("#ZTXX_ID").val();
 	$("#con").val(" LEDGER_ID = '"+ztxxid+"'");
	selCommon('BS_27', false, 'TERM_NO', 'TERM_NAME','forms[1]','TERM_NO','TERM_NAME','con');
 }
 
 //提交
 function opSub(){
	 var selRowId = $("#selRowId").val();
	  if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
 	 } 
 	 var goUrl = $("#pageForm").attr("action"); 
 	 parent.showConfirm("您确认要提交吗?","topcontent.mainSubmit('"+selRowId+"','sub');");
}
 
 //主体提交方法
 function mainSubmit(selRowId,op){
 	var actionUrl = "";
 	if(op==="sub"){
 		actionUrl = "restatements.shtml?action=opSub";
 	}
 	$.ajax({
        type:"POST", 
        url:actionUrl, 
        data:{"id":selRowId}, 
        complete:function (msg) {
            eval("jsonResult = "+msg.responseText);
            if(jsonResult.success === true) {
                parent.showMsgPanel("操作成功");
                pageForm.submit();
            } else {
                parent.showErrorMsg(utf8Decode(jsonResult.messages));
            }
    }});
 }
 
   //点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态已生效
	if (state == "已结算") {
		btnDisable(["delete","modify","commit"]);
	}
}
