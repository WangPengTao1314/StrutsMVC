/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time   2014-12-03 10:35:10 
 * @version 1.1
 */
$(function(){
	//审核页面需要隐藏的按钮
	shBtnHidden(["add","delete","start","stop"]);
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
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
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#MARKETING_CARD_IDS").val(ids);
		$("#form1").attr("action","marketact.shtml?action=toMarketCardEdit");
		$("#form1").submit();
	});
	
	$("#start").click(function(){
		 cardStart();
	});
	$("#stop").click(function(){
		parent.showConfirm("您确认要关闭吗","bottomcontent. cardStop();");
	});
	
	$("#exportExcel").click(function(){
		 var selRowId = parent.document.getElementById("selRowId").value;
		 $("#form1").attr("action","marketact.shtml?action=exportExcel&MARKETING_ACT_ID=" + selRowId);
		 $("#form1").submit();
	});
	
	$("#add").click(function(){
		showPage();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		checkChildState();
	});
	
	setSelOperateEx();
	 
});



 function showPage(){
//	 parent.showWaitPanel();
//	 parent.$("#midden").show();
	 InitFormValidator("selectForm");
	 var v = $("#testDiv").html();
	 var html = '<div style="position:absolute;width:500px;height:300px;filter:alpha(opacity=100);">' 
		 +v+
	 '</div>';
	  parent.showMessageBox_Self(html, 500, 300, "zzb");
//	 
 }
 

function cardStart(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
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
	$.ajax({
		url: "marketact.shtml?action=toCardStart",
		type:"POST",
		dataType:"text",
		data:{"MARKETING_CARD_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("开启成功");
				parent.gotoBottomPage("label_3");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
		
}

function cardStop(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
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
	$.ajax({
		url: "marketact.shtml?action=toCardStop",
		type:"POST",
		dataType:"text",
		data:{"MARKETING_CARD_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("关闭成功");
				parent.gotoBottomPage("label_3");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
		
}

function multiRecDeletes(){
    //查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	var flag = true;
	checkBox.each(function(){
		var state = $(this).parent().parent().find("input[name='state']").val();
		if("开启" == state){
			flag = false;
	    }
		ids = ids+"'"+$(this).val()+"',";
	});
	
	if(!flag){
		parent.showErrorMsg("开启状态不能删除");
		return;
	}
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "marketact.shtml?action=marketCardDelete",
		type:"POST",
		dataType:"text",
		data:{"MARKETING_CARD_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				parent.window.gotoBottomPage("label_3");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

 

function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		if (null == state || "" == state || state == "提交"||state == "审核通过") {
			btnDisable(["add","delete","start","stop"]);
		}
		if(null == state){
			btnDisable(["exportExcel"]);
		}
	}
	
 }
 

//明细表点击后设置选中
function setEidChecked(obj){
	var flag = obj.checked;
	if(flag){
		//$(obj).removeAttr("checked");
	}else{
		//$(obj).attr("checked","true");
	}
	 checkChildState();
}

function checkChildState(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var start_flag = true;
	var stop_flag = true;
	checkBox.each(function(){
		var state = $(this).parent().parent().find("input[name='state']").val();
		if("启用" == state){
			start_flag = false;
			return;
	    }
		if("关闭" == state){
			stop_flag = false;
			return;
	    }
	});
	 
	if(!start_flag){
		btnDisable(["delete","start"]);
	}else{
		btnArrysReset(["delete","start","stop"]);
	}
	 
	if(!stop_flag){
		btnDisable(["stop"]);
	}else{
		btnArrysReset(["stop"]);
	}
	
}
 


