/**
 *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *
 */
$(function(){
	//页面初始化
	listPageInit("forecasttaskupaudit.shtml?action=toList");
	var selRowId = $("#selRowId").val();
	if(null == selRowId || "" == selRowId){
	   btnDisable(["audit"]);
	   return;
    }
	//审核
	$("#audit").click(function(){
	   //获取当前选中的记录
       var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("是否审核","topcontent.auditConfirm();");
	});
	
	//弃审
	$("#cancel").click(function(){
	   //获取当前选中的记录
       var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("是否确认要弃审?","topcontent.cancelAudit();");
	});
});	


function auditConfirm(){
     var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "forecasttaskupaudit.shtml?action=audit",
		type:"POST",
		data:{"RPT_JOB_CHANN_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("提交成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function cancelAudit(){
    var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "forecasttaskupaudit.shtml?action=cancal",
		type:"POST",
		data:{"RPT_JOB_CHANN_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("弃审核成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});

}

function getSTATE(){
	var selRowId = $("#selRowId").val();
	var state =  $("#state"+selRowId).val();
	return state;
}
 

//记录按钮根据状态控制
 function setSelOperateEx(obj){
	var state =  getSTATE();
	//按钮状态重置
	btnReset();
	if(state == "审核通过"){
		btnDisable(["audit"]);
	}
	if(state == "提交"){
	    btnDisable(["cancel"]);
	}
 }
  