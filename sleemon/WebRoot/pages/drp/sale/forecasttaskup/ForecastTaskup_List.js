/**
 *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *
 */
$(function(){
	//页面初始化
	listPageInit("forecasttaskup.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("forecasttaskup.shtml?action=delete","ADVC_RPT_JOB_ID");
	var selRowId = $("#selRowId").val();
	if(null == selRowId || "" == selRowId){
	   btnDisable(["release","modify","delete","cancel","over","open"]);
	   return;
    }
 
	//提交
	$("#commit").click(function(){
	   //获取当前选中的记录
       var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("是否提交","topcontent.commitConfirm();");
	});
	
	 // 撤销
	$("#revoke").click(function(){
	   //获取当前选中的记录
	   var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("是否撤销","topcontent.revokeConfirm();");
	});
	 
});	


function commitConfirm(){
     var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "forecasttaskup.shtml?action=commit",
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


function revokeConfirm(){
     var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "forecasttaskup.shtml?action=revoke",
		type:"POST",
		data:{"RPT_JOB_CHANN_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("撤销成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function openPrdPage(id,cid,year,month){
//	var retudata = window.showModalDialog("forecasttaskup.shtml?action=toPrdList&ADVC_RPT_JOB_ID="+id,
//				 window,"dialogwidth=1200px; dialogheight=600px; status=no");
	window.open("forecasttaskup.shtml?action=toPrdList&ADVC_RPT_JOB_ID="+id+"&RPT_JOB_CHANN_ID="+cid+"&YEAR="+year+"&MONTH="+month);
}

//记录按钮根据状态控制
 function setSelOperateEx(obj){
	var selRowId = $("#selRowId").val();
	var state =  $("#state"+selRowId).val();
	var JOB_STATE =  $("#JOB_STATE"+selRowId).val();
	//按钮状态重置
	btnReset();
	if(JOB_STATE == "结束上报"){
		btnDisable(["commit","revoke"]);
	}
	if(state == "审核通过"){
		btnDisable(["commit","revoke"]);
	}
	if(state == "提交"){
		btnDisable(["commit"]);
	}
	if(state == "撤销"){
		btnDisable(["revoke"]);
	}
 }