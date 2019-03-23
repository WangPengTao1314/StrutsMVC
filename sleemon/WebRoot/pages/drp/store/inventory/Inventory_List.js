/**
 * @prjName:喜临门营销平台
 * @fileName:Inventory_List
 * @author lyg
 * @time   2013-09-07 09:54:59 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke","begin","end","iniup"]);
    var module = parent.window.document.getElementById("module").value;	
    $("#query").click(function(){
    	if("sh"==module){
    		$("#STATE option[text='未提交']").remove();
			$("#STATE option[text='开始盘点']").remove();
			$("#STATE option[text='结束盘点']").remove();
    	}
	});
    $("#close").click(function (){
    	$("#upFile").hide();
    })
	//主从及主从从列表页面通用加载方法
	listPageInit("inventory.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
//	addModiToEditFrameInit("inventory.shtml?action=toParentEdit");
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("inventory.shtml?action=delete", "PRD_INV_ID");
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		//-- 0156516--Start--刘曰刚--2014-06-16//
		//增加盘点单提交时校验是否有明细//
		commit(selRowId);
		//-- 0156516--End--刘曰刚--2014-06-16//
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
	$("#begin").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		
		parent.showConfirm("您确认开始盘点吗?","topcontent.begin();");
	});
	$("#end").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("您确认结束盘点吗?","topcontent.end();");
	});
	
	$("#iniup").click(function () {
		$("#type").val("init");
		$("#tempUp").show()
		$("#upFile").show();
	});
	
	$("#tempUp").click(function () {
		tempUpDownload();
	});
//	$("#up").bind("input propertychange" ,function(){
//		if($("#up").val()==null||$("#up").val()==""){
//			return;
//		}else{
//			$("#upFile").hide();
//			uploading();
//		}
//	})
});
 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/inventory.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
 //开始盘点
function begin(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var STORE_ID=$("#store"+selRowId).val();
	var type=$("#type"+selRowId).val();
	$.ajax({
	 url: "inventory.shtml?action=toBegin",
	 type:"POST",
 	 dataType:"text",
 	 data:{"PRD_INV_ID":utf8(selRowId),"STORE_ID":STORE_ID,"INV_TYPE":type},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("开始盘点成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
function end(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var STORE_ID=$("#store"+selRowId).val();
	var type=$("#type"+selRowId).val();
	$.ajax({
	 url: "inventory.shtml?action=toEnd",
	 type:"POST",
 	 dataType:"text",
 	 data:{"PRD_INV_ID":utf8(selRowId),"STORE_ID":STORE_ID,"INV_TYPE":type},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("结束盘点成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var type=$("#type"+selRowId).val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交或者=开始盘点
	if (state == "\u63d0\u4ea4"||state == "开始盘点") {
		btnDisable(["delete","modify","commit"]);
	}
	//当状态！=提交时
	if (state != "\u63d0\u4ea4") {
		btnDisable(["revoke"]);
	}
	//当状态不是开始盘点时
	if(state != "开始盘点"){
		btnDisable(["end"]);
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
	if (state == "退回") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
	//当状态！=未提交的时候 
	if(state!="\u672a\u63d0\u4ea4"){
		btnDisable(["begin"]);
	}
	//当状态==结束盘点的时候 
	if(state=="结束盘点"){
		btnReset();
		btnDisable(["modify","end","revoke","begin"]);
	}
}
function getINV_RANGE(){
	var selRowId=$("#selRowId").val();
	return $("#range"+selRowId).val();
}
//导入
function uploading(){
	var fileName=$("#up").val();
	var type=$("#type").val();
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
	 url: "inventory.shtml?action=parseExecl&fileName="+utf8(fileName)+"&PRD_INV_ID="+utf8(selRowId)+"&type="+utf8(type),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			alert("上传成功");
            $("#pageForm").submit();
            $("#upFile").hide();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
function tempUpDownload(){
	window.location="inventory.shtml?action=tempUp";

}
//获得库房id
function getSTORE_ID(selRow){
	return $("#store"+selRow).val();
}
//获得库位id
function getSTORG_ID(selRow){
	return $("#storg"+selRow).val();
}

//提交
function commit(selRowId){
	showWaitPanel();
    $.ajax({
	 url: "inventory.shtml?action=toCommit&PRD_INV_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			toFlow("1");
		}else{
			//--0157490--start--刘曰刚--2014-06-25--//
			//验证盘点货品是否重复，如果重复给予提示，按货品id或特殊工艺判断
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
			closeWindow();
			//--0157490--End--刘曰刚--2014-06-25--//
		}
	  }
    });
}




    
 //库房通用选取
function storeSelCommon(){
	var BMXXID = $("#BMXXID").val();
	var TERM_ID = $("#TERM_ID").val();
	var ZTXXID = $("#ZTXXID").val();
	var TERM_CHARGE = $("#TERM_CHARGE").val();
	var selectParams = "";
	if(null != TERM_ID && "" != TERM_ID){
		//终端用户选择转出库房只能选自己的
	   selectParams = " STATE ='启用' and DEL_FLAG=0 and  BEL_CHANN_ID='"+TERM_ID+"' ";
	}else{
		//渠道人员只能选本渠道和分管的终端的库房
	    selectParams = " STATE ='启用' and DEL_FLAG=0 and  (BEL_CHANN_ID='"+ZTXXID+"' or BEL_CHANN_ID in "+TERM_CHARGE+")";
	}
	
	$("#selectStore").val(selectParams);
	 
	selCommon('BS_35',false,'STORE_ID','STORE_ID','forms[1]','STORE_ID,STORE_NO,STORE_NAME','STORE_ID,STORE_NO,STORE_NAME','selectStore');
	 
}



