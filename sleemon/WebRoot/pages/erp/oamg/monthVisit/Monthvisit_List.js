/**
 *@module 渠道管理-拜访管理
 *@func   月度拜访计划维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	 
	 //主从及主从从列表页面通用加载方法
	listPageInit("monthVisit.shtml?action=toList&module="+document.getElementById("module").value);
	
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
	
	//维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add","delete","modify", "commit", "revoke"]);
	
    //新增和修改按钮初始化
	addModiToEditFrameInit("monthVisit.shtml?action=toEditFrame"); 
	//删除监听.参数1：删除action，参数2：删除主键Id
	//mtbDelListener("bmgz.shtml?action=delete","CMLID");
	$("#delete").click(function(){
	 	 deletecheck("monthVisit.shtml?action=delete","MONTH_VISIT_PLAN_ID");
	 });
	 
	var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
      btnDisable(["modify","delete","commit","revoke","audit","flow"]);
      return;
    } 
});

 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/monthVisit.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var state = $.trim($(obj).find("td").eq(10).text());
	//按钮状态重置
	btnReset();
	if(state == "未提交"){
	   //撤销按钮不可用
		btnDisable(["revoke"]);
	}else if(state == "提交"){
		//提交按钮不可用
		btnDisable(["commit"]);
	}
}

function deletecheck(url,pkId){
   var selRowId = $("#selRowId").val();
   if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
   } 
   var goUrl = $("#pageForm").attr("action"); 
   var child = parent.bottomcontent.document.getElementById("eid1");
   var deleteUrl = "monthVisit.shtml?action=delete";
   parent.showConfirm("您确认要删除吗?","topcontent.realDelete('"+deleteUrl+"','"+pkId+"','"+selRowId+"','"+goUrl+"');");

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

function realDelete(actionUrl,pkId,selRowId,goUrl){
    parent.closeWindow();
    $.ajax({
	 	url: actionUrl+"&"+pkId+"="+utf8(selRowId),
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			      parent.showMsgPanel("删除成功");
				  $("#pageForm").submit();
			}else{
				  parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}