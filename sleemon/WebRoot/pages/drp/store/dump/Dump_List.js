/**
 * @prjName:喜临门营销平台
 * @fileName:Dump_List
 * @author zzb
 * @time   2013-09-05 14:00:34 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("dump.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("dump.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("dump.shtml?action=delete", "DUMP_ID");
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toCommit(selRowId);
		//toFlow("1");
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
	$("#print").click(function (){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
	});
	
	$("#expertExcel").click(function(){
		 $("#queryForm").attr("action","dump.shtml?action=expertExcel&module=" + module);
		 $("#queryForm").submit();
	});
		
});

 function toCommit(selRowId){
	 showWaitPanel();
	 $.ajax({
		url: "dump.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"DUMP_ID":selRowId},
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

 
 
 
 //转出库房
function selTellOutStore(){
	var TERM_ID = $("#TERM_ID").val();
	var ZTXXID = $("#ZTXXID").val();
	var TERM_CHARGE = $("#TERM_CHARGE").val();
	var tellOutparams = "";
	if(null != TERM_ID  && "" != TERM_ID){
		//终端用户选择转出库房只能选自己的
		tellOutparams = " STATE in('启用','停用') and DEL_FLAG=0 and  BEL_CHANN_ID='"+TERM_ID+"' ";
	}else{
		tellOutparams = " STATE in('启用','停用') and DEL_FLAG=0 and  (BEL_CHANN_ID='"+ZTXXID+"' or BEL_CHANN_ID in "+TERM_CHARGE+")";
	}
	$("#tellOutparams").val(tellOutparams);
	
	selCommon('BS_35', false, 'DUMP_OUT_STORE_ID', 'STORE_ID', 'forms[1]','DUMP_OUT_STORE_NO,DUMP_OUT_STORE_NAME', 'STORE_NO,STORE_NAME', 'tellOutparams')
}

 
 
 
//转入库房
function selTellInStore(){
	var TERM_ID = $("#TERM_ID").val();
	var ZTXXID = $("#ZTXXID").val();
	var TERM_CHARGE = $("#TERM_CHARGE").val();
	var tellInparams = "";
	if(null != TERM_ID  && "" != TERM_ID){
		//终端人员选择转入库房能选整个渠道的
		tellInparams = " STATE in('启用','停用') and DEL_FLAG=0 and  LEDGER_ID='"+ZTXXID+"' ";
	}else{
		//渠道人员只能选本渠道和分管的终端的库房
		tellInparams = " STATE in('启用','停用') and DEL_FLAG=0 and  (BEL_CHANN_ID='"+ZTXXID+"' or BEL_CHANN_ID in "+TERM_CHARGE+")";
	}
	$("#tellInparams").val(tellInparams);
	
	selCommon('BS_35', false, 'DUMP_IN_STORE_ID', 'STORE_ID', 'forms[1]','DUMP_IN_STORE_NO,DUMP_IN_STORE_NAME', 'STORE_NO,STORE_NAME', 'tellInparams')
}





//转出库房ID
function getOutStoreId(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var val = $("#DUMP_OUT_STORE_ID"+selRowId).val();
	val = val.split("|")[0];
	return val;
}
//转出库房管理标记
function getOutFlag(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var val = $("#DUMP_OUT_STORE_ID"+selRowId).val();
	val = val.split("|")[1];
	return val;
}

//转入库房ID
function getInStoreId(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var val = $("#DUMP_IN_STORE_ID"+selRowId).val();
	val = val.split("|")[0];
	return val;
}
//转入库房管理标记
function getInFlag(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var val = $("#DUMP_IN_STORE_ID"+selRowId).val();
	val = val.split("|")[1];
	return val;
}

 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/dump.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
	
//	//当状态！=审核通过时 不允许打印
//	if (state != "审核通过") {
//		btnDisable(["print"]);
//	}

}
function print(selRowId){
	//跳转扫码打印页面
//	$("#printIframe").attr("src","dump.shtml?action=printInfo&DUMP_ID="+selRowId);
	window.open("dump.shtml?action=printInfo&DUMP_ID="+selRowId,'报表查看','height=600, width=800, top=100, left=100, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');
}
function countPrint(){
	$("#queryForm").submit();
}