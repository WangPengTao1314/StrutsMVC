 /**
  *@module 系统管理
  *@fuc 送货地址信息
  *@version 1.1
  *@author 张忠斌
*/

$(function(){

	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
	});

	
	$("#edit").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				var id = $(this).val();
				var state = $("#state"+id).text();
				state = $.trim(state);
				if("制定" == state || "停用" == state){
					ids = ids+"'"+id+"',";
				}
				
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#DELIVER_ADDR_IDS").val(ids);
		$("#form1").attr("action","chann.shtml?action=toChildEdit");
		$("#form1").submit();
	});
	
	
	$("#start").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		start();
	});
	
	$("#stop").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		stop();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		setBtStates();
		
	});
	 
	
	setButtonState();
	
});


//启用
function start(){
	//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		var id = $(this).val();
		var state = $("#state"+id).text();
		state = $.trim(state);
		if("停用" == state || "制定" == state){
			ids = ids+"'"+id+"',";
		}
		
	});
	ids = ids.substr(0,ids.length-1);
	
	var selRowId = getSelRowId();
	
	$.ajax({
		url: "chann.shtml?action=childStart",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ADDR_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				parent.document.getElementById("label_2").click();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//停用
function stop(){
	//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		var id = $(this).val();
		var state = $("#state"+id).text();
		state = $.trim(state);
		if("启用" == state){
			ids = ids+"'"+id+"',";
		}
	});
	ids = ids.substr(0,ids.length-1);
	var selRowId = getSelRowId();
	
	$.ajax({
		url: "chann.shtml?action=childStop",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ADDR_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				parent.document.getElementById("label_2").click();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}



function setBtStates(){
	var checkboxs = $("#ordertb input[name='eid']:checked");
	var num = checkboxs.length;
	btnReset();
	if(num ==0){
		btnDisable(["delete","start","stop"]);
	}else{
		checkboxs.each(function(){
			var id  = $(this).val();
			var strStats = $.trim($("#state"+id).html());
			if (strStats=="启用"){
				btnDisable(["start","edit","delete"]);
			} 
			if (strStats=="停用") {
				btnDisable(["stop"]);
			} 
			if (strStats=="制定") {
				btnDisable([ "stop" ]);
			}
		});
	}
	
	setButtonState();
}


//列表页面的删除
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		var id = $(this).val();
		var state = $("#state"+id).text();
		state = $.trim(state);
		if("制定" == state||"停用"==state){
			ids = ids+"'"+id+"',";
		}
		
	});
 
	ids = ids.substr(0,ids.length-1);
	var selRowId = getSelRowId();
	
	$.ajax({
		url: "chann.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_ADDR_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				parent.window.gotoBottomPage("label_2");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}
//点击选择某条记录后，需要根据状态判断是否可用
function setButtonState(){
	var selRowId = getSelRowId();
	var state = parent.topcontent.document.getElementById("state"+selRowId).value;
	
	if(state == '启用'){
//	   btnDisable(["edit","delete","stop","start"]);
	}
	if(selRowId == null || '' == selRowId){
	        btnDisable(["edit","delete","stop","start"]);
	}
	
}


function getActionType(){
	return parent.document.getElementById("actionType").value;
}

