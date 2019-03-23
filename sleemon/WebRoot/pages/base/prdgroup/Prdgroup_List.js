/**
 * @module 基础数据
 * @fuc 货品组
 * @version 1.1
 * @author 张忠斌
 */
$(function () {	//维护和审核调用的是同一页面，只是按钮不同，所以需要设置	//维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);	var module = parent.window.document.getElementById("module").value;	 //主从及主从从列表页面通用加载方法
	listPageInit("prdgroup.shtml?action=toList&module=" + module);	//新增和修改按钮初始化
	addModiToEditFrameInit("prdgroup.shtml?action=toEditFrame");	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("prdgroup.shtml?action=delete", "groupID");
	//初始化 审批流程
//    spflowInit("ERP_ZJGL_CPBLTZSP", "T_ERP_ZJ_CPBLTZD", "groupID", "../sample/prdgroup.shtml?action=toFrame&module="+module, "com.hoperun.sample.masterslave.service.impl.MasterSlaveFlowInterface", "");
    	
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
	
	
		// 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
	   var selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("将停用该货品组，是否继续?","topcontent.listStopConfirm();");
	});
	
	// 启用
	$("#start").click(function(){
	    var selRowId = $("#selRowId").val();
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("将启用该货品组，是否继续?","topcontent.listStartConfirm();");

	});
	
});
function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value="../sample/prdgroup.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
}//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
//	var state = $.trim($(obj).find("td").eq(16).text());
	var selRowId = parent.document.getElementById("selRowId").value;
	if(document.getElementById("state"+selRowId)==null)
	return;
	var state =  document.getElementById("state"+selRowId).value;	//按钮状态重置
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
	//当状态=启用
	if (state == "启用") {
		btnDisable(["start","delete"]);
	}
	//当状态=停用
	if (state == "停用") {
		btnDisable(["stop","delete"]);
	}
	if (state == "制定"){
		btnDisable(["stop"]);
	}

}
function setCPZJTZDID(groupID) {
	document.getElementById("groupID").value = groupID;
}


//停用按钮的url
function listStopConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "prdgroup.shtml?action=changeStateTy&groupID="+utf8(selRowId),
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
//启用按钮的
function listStartConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "prdgroup.shtml?action=changeStateQy&groupID="+utf8(selRowId),
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

