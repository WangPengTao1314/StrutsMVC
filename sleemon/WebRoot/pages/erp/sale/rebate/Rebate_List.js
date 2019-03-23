
/**
 * @module 系统管理
 * @func 返利登记
 * @version 1.1
 * @author张忠斌
 */
$(function () {
	var module =  getModule();
	//页面初始化
//	listPageInit("rebate.shtml?action=toList&module="+module);
	
	//表头排序
	headColumnSort("ordertb","pageForm");
	//防止滚动条当初翻页
	dipalyPageNo();
	
	 
	 
	 $("#q_search").click(function(){
	 	 //修正查询后，下面页面不刷新的问题 09-14
	 	 qryformChecked('queryForm');
		 $("#queryForm").attr("action","rebate.shtml?action=toList&search=true&module="+getModule());
		 $("#queryForm").submit();
	 });
	 
	 
	 $("#addRebate").click(function(){
		 var checkBox = $("#ordertb tr:gt(0) input:checked");
			if(checkBox.length<1){
				showErrorMsg("请至少选择一条记录");
				return;
		}
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
    	window.showModalDialog('rebate.shtml?action=toSetRebate&DESCON=1&CHANN_NOS='+ ids,window,"dialogWidth=500px;dialogHeight=300px;depended=no");
    	$("#q_search").click(); 
	 });
	 $("#expdate").click(function(){
		 $("#queryForm").attr("action","rebate.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
	 $("#decrRebate").click(function(){
		 var checkBox = $("#ordertb tr:gt(0) input:checked");
			if(checkBox.length<1){
				showErrorMsg("请至少选择一条记录");
				return;
		}
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
    	window.showModalDialog('rebate.shtml?action=toSetRebate&DESCON=-1&CHANN_NOS='+ ids,window,"dialogWidth=500px;dialogHeight=300px;depended=no");
    	$("#q_search").click(); 
	 });
   
	$("#save").click(function(){
		   //查找当前是否有选中的记录
		 	var checkBox = $("#ordertb tr:gt(0) input:checked");
			if(checkBox.length<1){
				showErrorMsg("请至少选择一条记录");
				return;
			}
			
			var flag = inpuCheck("ordertb","#C67171");
			if(!flag){
				return ;
			}
			
	 	               
		   var jsonData = multiPackJsonData("ordertb");
		   $.ajax({
			url: "rebate.shtml?action=edit&jsonData="+jsonData,
			type:"POST",
			dataType:"text",
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					showMsgPanel("保存成功");
	                //$("#pageForm").submit();
//					saveSuccess("保存成功","rebate.shtml?action=toList");
				}else{
					showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
	 	});
	 });
	 
	$("#reset").click(function(){
		showConfirm("该操作会清空所有的返利，您确认需要这样操作吗？","resetConfirm();");
	 	  
	});
	 
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
});

function addRebate(){
	
}
 
function inpuCheck(tableid,trColor){
	var flag = true;
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		var isEdit = $(this).find("input[type='checkbox']:eq(0)").attr("checked");
		if(isEdit){
			var inputs = $(this).find(":input");
			inputs.each(function(){
				if(null != $(this).attr("json")){
					var key;
					var value;
					var type = $(this).attr("type");
					if("text" == type){
						value = $(this).val();
						// 校验浮点数内容
						if (!CheckFloatInput(this)) {
							flag = false
							$(this).parent().css("background-color", trColor);
							return flag;
						}
						
					} 
				}
			});
		}
	});
	
	return flag;
}

 
function resetConfirm(){
	$.ajax({
			url: "rebate.shtml?action=rest",
			type:"POST",
			dataType:"text",
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					showMsgPanel(utf8Decode(jsonResult.messages));
					 
				}else{
					showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
	 	});
}

 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state =  $.trim($(obj).find("td[json='STATE']").text());
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
 
 
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "chann.shtml?action=delete&CHANN_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				window.location="chann.shtml?action=toList&CHANN_ID="+selRowId;
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
		url: "chann.shtml?action=changeStateTy&CHANN_ID="+utf8(selRowId),
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
	 url: "chann.shtml?action=changeStateQy&CHANN_ID="+utf8(selRowId),
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
