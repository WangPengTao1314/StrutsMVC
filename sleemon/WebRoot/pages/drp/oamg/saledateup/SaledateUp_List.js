/**
 * @prjName:喜临门营销平台
 * @fileName:分销 -订货订单维护
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
$(function(){
	 InitFormValidator("queryForm");	
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	 
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke","export","up"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("saledateup.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("saledateup.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("saledateup.shtml?action=delete", "SALE_DATE_UP_ID");
	 
    $("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		 toFlow("1");
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
	
	$("#retuAudit").click(function(){
    	var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.showConfirm("您确定反审核吗?","topcontent.toRetuAudit();");
    });
	$("#export").click(function(){
		$("#editInfo").show();
	});
	$("#up").click(function () {
		$("#upFile").show();
	});
});

 function toFlow(i) {
	 var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/saledateup.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
	 $.ajax({
		url: "saledateup.shtml?action=toCommit",
		type:"POST",
		dataType:"text",
		data:{"SALE_DATE_UP_ID":selRowId},
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
 
 function getAREA_ID(){
	 var selRowId = parent.document.getElementById("selRowId").value; 
	 return $("#AREA_ID_"+selRowId).val();
 }
 
//选择终端信息
function selectTerm(){
	var CHANN_ID = $("#CHANN_ID").val();
	var selTermParam = "STATE='启用' and DEL_FLAG=0 ";
	if(null == CHANN_ID || "" == CHANN_ID){
		 
	}else{
		CHANN_ID = "'" + CHANN_ID.replaceAll(",","','")+"'";
		selTermParam = selTermParam +" and CHANN_ID_P in("+CHANN_ID+")";
	}
	$("#selTermParam").val(selTermParam);
	
   selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'forms[1]','TERM_NO,TERM_NAME', 'TERM_NO,TERM_NAME', 'selTermParam');

}
 
 
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
  
	//当状态=未提交
	if (state == "未提交") {
		btnDisable(["revoke","audit","retuAudit"]);
	}
	
	//当状态=提交
	if (state == "提交") {
		btnDisable(["delete","modify","commit","retuAudit"]);
	}
	
	//当状态=撤销
	if (state == "撤销") {
		btnDisable(["revoke","audit","retuAudit"]);
	}
	
	//当状态=否决
	if (state == "否决") {
		btnDisable(["revoke","audit","retuAudit"]);
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
}

 function toRetuAudit(){
     var selRowId = $("#selRowId").val();
     $.ajax({
		url: "saledateup.shtml?action=toReAudit&SALE_DATE_UP_ID="+selRowId,
		type:"POST",
		dataType:"text",
		data:{"SALE_DATE_UP_ID":selRowId},
		complete: function (xhr){
		    eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("反审核成功");
	            $("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 }); 
 }
 function modifyImport(){
	 var YEAR=$("#EDIT_YEAR").val();
	 var MONTH=$("#EDIT_MONTH").val();
	 if(""==YEAR||null==YEAR){
		 parent.showErrorMsg("请选择年份");
		 return;
	 }
	 if(""==MONTH||null==MONTH){
		 parent.showErrorMsg("请选择月份");
		 return;
	 }
	 $("#editInfo").hide();
	 var module = parent.window.document.getElementById("module").value;	
	 $("#queryForm").attr("action","saledateup.shtml?action=export&module=" + module+"&YEAR="+YEAR+"&MONTH="+MONTH);
	 $("#queryForm").submit();
 }
 function closePage(){
		$("#editInfo").hide();
	}
 //导入
function uploading(){
	var fileName = $("#upInfo").val();
	$.ajax({
	 url: "saledateup.shtml?action=toImportData",
	 type:"POST",
 	 dataType:"text",
 	 data:{"fileName":fileName},
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
 
 
 
