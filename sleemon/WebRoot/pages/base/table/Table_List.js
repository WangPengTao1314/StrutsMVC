
/**
 * @module 系统管理
 * @func 分销渠道信息
 * @version 1.1
 * @author  gu_hongxiu
 */
$(function () {
	//页面初始化
	var module = parent.window.document.getElementById("module").value;
	listPageInit("table.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	
    var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
			  btnDisable(["start","modify","delete","stop"]);
	 	 	  return;
	} 
	$("#delete").click(function(){
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("将删除该分销渠道,您确认删除吗?","topcontent.listDelConfirm();");
	 });
	$("#expdata").click(function(){
		 $("#queryForm").attr("action","table.shtml?action=toExpData&module=" + module);
		 $("#queryForm").submit();
	 });
	// 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("将停用该分销渠道，是否继续?","topcontent.listStopConfirm();");
	});
	
	// 启用
	$("#start").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("将启用该分销渠道，是否继续?","topcontent.listStartConfirm();");

	});
});

 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state =  $.trim($(obj).find("td[json='STATE']").text());
	//按钮状态重置
	btnReset();
    if(state == "启用"){
		btnDisable(["start","delete","modify"]);
	}else if(state == "停用"){
		btnDisable(["stop","delete"]);
	}else if (state == "制定"){
		btnDisable(["stop"]);
	}
 }
 
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "distributor.shtml?action=delete&DISTRIBUTOR_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				window.location="table.shtml?action=toList&DISTRIBUTOR_ID="+selRowId;
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//停用按钮的url
function listStopConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "table.shtml?action=changeStateTy&DISTRIBUTOR_ID="+utf8(selRowId),
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
	 url: "table.shtml?action=changeStateQy&DISTRIBUTOR_ID="+utf8(selRowId),
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
 


