/**
 * @module  
 * @func  
 * @version 1.1
 * @author  
 */
$(function () {
	//页面初始化
	listPageInit("budgetquota.shtml?action=toList");
	 var selRowId = $("#selRowId").val();
	 //新增和修改按钮初始化
	mtbAddModiInit();
    if(null == selRowId || "" == selRowId){
		  btnDisable(["start","modify","delete","stop"]);
	} 
	$("#delete").click(function(){
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("您确认删除吗?","topcontent.listDelConfirm();");
	 });
	
	
	// 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("您确认停用吗?","topcontent.stopConfirm();");
	});
	
	// 启用
	$("#start").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	    startConfirm();

	});
	
	
	$("#batchedit").click(function(){
		parent.window.gotoBottomPage("toBatchEdit");
	});
	$("#batchadd").click(function(){
		setCommonPageInfo(false);
		parent.window.gotoBottomPage("toBatchEdit");
	});
	
});

 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state =  $.trim($(obj).find("td[json='STATE']").text());
	//按钮状态重置
	btnReset();
    if(state == "启用"){
		btnDisable(["start","delete"]);
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
	 	url: "budgetquota.shtml?action=delete",
		type:"POST",
		data:{"BUDGET_QUOTA_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				$("#pageForm").submit();
//				//删除树节点
//				parent.leftcontent.deleteNodes(selRowId);
//				//当前树选中节点
//				var selNodesId = parent.leftcontent.getSelNodesId();
//				window.location="product.shtml?action=toList&PRD_ID="+selNodesId;
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function stopConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "budgetquota.shtml?action=changeStateStop",
		type:"POST",
		dataType:"text",
		data:{"BUDGET_QUOTA_ID":selRowId},
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

function startConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "budgetquota.shtml?action=changeStateStart",
	 type:"POST",
 	 dataType:"text",
 	 data:{"BUDGET_QUOTA_ID":selRowId},
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
