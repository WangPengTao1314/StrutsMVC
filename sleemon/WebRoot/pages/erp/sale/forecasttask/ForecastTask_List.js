/**
 *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *
 */
$(function(){
	//页面初始化
	listPageInit("forecasttask.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("forecasttask.shtml?action=delete","ADVC_RPT_JOB_ID");
	var selRowId = $("#selRowId").val();
	if(null == selRowId || "" == selRowId){
	   btnDisable(["release","modify","delete","cancel","over","open"]);
	   return;
    }
	$("#delete").click(function(){
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("将删除该品牌信息,您确认删除吗?","topcontent.listDelConfirm();");
	 });
	//发布
	$("#release").click(function(){
	   //获取当前选中的记录
       var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("是否发布","topcontent.releaseConfirm();");
	});
	
	// 取消发布
	$("#cancel").click(function(){
	   //获取当前选中的记录
	   var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("是否取消发布","topcontent.cancelConfirm();");
	});
	
	
	 // 结束上报
	$("#over").click(function(){
	   //获取当前选中的记录
	   var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("是否结束上报","topcontent.overConfirm();");
	});
	 // 打开上报
	$("#open").click(function(){
	   //获取当前选中的记录
	   var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("是否打开上报","topcontent.openConfirm();");
	});
});	


//记录按钮根据状态控制
 function setSelOperateEx(obj){
	var selRowId = $("#selRowId").val();
	var state =  $("#state"+selRowId).val();
	//按钮状态重置
	btnReset();
	if(state == "发布"){
		btnDisable(["release","delete","modify","open"]);
	}
	if(state == "取消"){
		btnDisable(["cancel","over","open"]);
	}
	if (state == "制定"){
		btnDisable(["cancel","over","open"]);
	}
	if(state == "结束上报"){
		btnDisable(["release","delete","modify","over","cancel"]);
	}
 }
 //删除记录
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "forecasttask.shtml?action=delete&ADVC_RPT_JOB_ID="+selRowId,
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
//发布
function releaseConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "forecasttask.shtml?action=release",
		type:"POST",
		dataType:"text",
		data:{"ADVC_RPT_JOB_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("发布成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}
//取消发布
function cancelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "forecasttask.shtml?action=cancel",
	 type:"POST",
 	 dataType:"text",
 	 data:{"ADVC_RPT_JOB_ID":selRowId},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("取消发布成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
//结束上报
function overConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "forecasttask.shtml?action=over",
		type:"POST",
		dataType:"text",
		data:{"ADVC_RPT_JOB_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("操作成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}

//打开上报
function openConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "forecasttask.shtml?action=open",
		type:"POST",
		dataType:"text",
		data:{"ADVC_RPT_JOB_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("操作成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}