/**
 *@module 基础数据
 *@func终端信息
 *@version 1.1
 *@author 郭利伟
 */
$(function(){
	//页面初始化
	listPageInit("terminal.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("terminal.shtml?action=delete","TERM_ID");
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
	 	 parent.showConfirm("将删除该终端信息,您确认删除吗?","topcontent.listDelConfirm();");
	 });
	// 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
       var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("将停用该终端信息，是否继续?","topcontent.listStopConfirm();");
	});
	
	// 启用
	$("#start").click(function(){
	   //获取当前选中的记录
	   var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("将启用该终端信息，是否继续?","topcontent.listStartConfirm();");
	});
	
	$("#expertExcel").click(function(){
		 $("#queryForm").attr("action","terminal.shtml?action=expertExcel&module=" + getModule());
		 $("#queryForm").submit();
	});
	
	$("#calculator").click(function(){
		 var selRowId = parent.document.getElementById("selRowId").value;
		 var PRICE_EXPRESS_CHS = $("#PRICE_EXPRESS_CHS"+selRowId).val();
		 var PRICE_EXPRESS = $("#PRICE_EXPRESS"+selRowId).val();
         $("#edit_remark").show();
         BtClean();
         $("#result").val(PRICE_EXPRESS_CHS);
         $("#PRICE_EXPRESS").val(PRICE_EXPRESS);
         
	});                   
});	


//记录按钮根据状态控制
 function setSelOperateEx(obj){
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
 //删除记录
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "terminal.shtml?action=delete&TERM_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				$("#pageForm").submit();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//停用记录
function listStopConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "terminal.shtml?action=changeStateStop&TERM_ID="+utf8(selRowId),
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
//启用记录
function listStartConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "terminal.shtml?action=changeStateStart&TERM_ID="+utf8(selRowId),
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

function modifyExper(flag){
	var selRowId = $("#selRowId").val();
	var PRICE_EXPRESS = $("#PRICE_EXPRESS").val();
	var result = $("#result").val();
	 $.ajax({
		url: "terminal.shtml?action=modifyExpress",
		type:"POST",
		dataType:"text",
		data:{"TERM_ID":selRowId,"PRICE_EXPRESS":PRICE_EXPRESS,"flag":flag,"PRICE_EXPRESS_CHS":result},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				if(null == flag || "" == flag){
					parent.$("#YT_MSG_BTN_OK").click(function(){
						 $("#edit_remark").hide();
						 $("#PRICE_EXPRESS").val("");
						 $("#pageForm").submit();
					});
				}
			
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}

function closeDiv(obj){
	$("#edit_remark").hide();
	$("#PRICE_EXPRESS").val("");
}
 
