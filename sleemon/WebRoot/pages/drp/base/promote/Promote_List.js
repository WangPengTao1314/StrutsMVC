/**
 * @prjName:喜临门营销平台
 * @fileName:活动
 * @author zzb
 * @time    
 * @version 1.1
 */
$(function(){
	//页面初始化
	listPageInit("promote.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("promote.shtml?action=delete","PROMOTE_ID");
	var selRowId = $("#selRowId").val();
	if(null == selRowId || "" == selRowId){
	      btnDisable(["modify","delete","start","stop"]);
	      return;
	}
    // 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("该操作会停用记录，是否继续?","topcontent.listStopConfirm();");
	});
	
	// 启用
	$("#start").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("该操作会启用记录，是否继续?","topcontent.listStartConfirm();");
   });
});



//记录按钮根据状态控制
 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	if(state == "启用"){
		btnDisable(["start","modify","delete"]);
	}else if(state == "停用"){
		btnDisable(["stop","delete"]);
	}else if (state == "制定"){
		btnDisable(["stop"]);
	}
 }
 
 
 
 
//停用记录
function listStopConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "promote.shtml?action=changeStateTy",
		type:"POST",
		data:{"PROMOTE_ID":selRowId},
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


//启用记录
function listStartConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "promote.shtml?action=changeStateQy",
	 type:"POST",
	 data:{"PROMOTE_ID":selRowId},
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



