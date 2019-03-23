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

   $("#close").click(function(){
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
		parent.showConfirm("您确认要关闭吗","bottomcontent.multiRecClose();");
	});
    
    $("#open").click(function(){
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
		parent.showConfirm("您确认要开启吗","bottomcontent.multiRecOpen();");
	});
      
    
	$("#edit").click(function(){
        var checkBox = $("#ordertb tr:gt(0) input:checked");
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				var STATE = $(this).parent().parent().find("input[name='STATE']").val();
				if("关闭" != STATE){
					ids = ids+"'"+$(this).val()+"',";
				}
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#CARD_SALE_ORDER_DTL_IDS").val(ids);
		$("#form1").attr("action","marketcardsale.shtml?action=toChildEdit");
		$("#form1").submit();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
	
	setSelOperateEx();
	 
	changeColor();
});



//是否取消过标记 标颜色
function changeColor(){
	var ordertb = $("#ordertb tr:gt(0)");
	ordertb.each(function(){
		var STATE = $(this).find("input[name='STATE']").val();
		if("关闭" == STATE){
		    $(this).find("td").css("background-color", "#FFEC8B");//#F3F3F3花号还原
		    $(this).find("input[type='text']").attr("disabled","disabled").attr("class","readonly");//#F3F3F3花号还原
		}
	});
	
}

 
//删除
function multiRecDeletes(){
	var selRowId = parent.document.getElementById("selRowId").value;
		 
    //查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	$.ajax({
		url: "marketcardsale.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"CARD_SALE_ORDER_DTL_IDS":ids,"CARD_SALE_ORDER_ID":selRowId},
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


//关闭
function multiRecClose(){
	var selRowId = parent.document.getElementById("selRowId").value;
		 
    //查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	$.ajax({
		url: "marketcardsale.shtml?action=childClose",
		type:"POST",
		dataType:"text",
		data:{"CARD_SALE_ORDER_DTL_IDS":ids,"CARD_SALE_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("关闭成功");
				checkBox.parent().parent().remove();
				parent.window.gotoBottomPage("label_3");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


//开启
function multiRecOpen(){
	var selRowId = parent.document.getElementById("selRowId").value;
		 
    //查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	$.ajax({
		url: "marketcardsale.shtml?action=childOpen",
		type:"POST",
		dataType:"text",
		data:{"CARD_SALE_ORDER_DTL_IDS":ids,"CARD_SALE_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("开启成功");
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
			btnDisable(["edit","delete"]);
		}
	}
	
 }
 

function setEidChecked(obj){
	var flag = obj.checked;
	if(flag){
		$(obj).removeAttr("checked");
	}else{
		$(obj).attr("checked","true");
	}
	justMX();
}


function justMX(){
	var checkBox = $("#ordertb tr:gt(0) input[name='mx_checkbox']:checked");
	var size = checkBox.length;
	var leg = 0;
	var cl = 0;
	var ol = 0;
	checkBox.each(function(){
		var STATE = $(this).parent().parent().find("input[name='STATE']").val();
		if("关闭" == STATE){
			 cl = parseInt(cl)+1;
		}
		if("正常" == STATE){
			 ol = parseInt(ol)+1;
		}
		leg = parseInt(leg)+1;
	});
	 btnReset(["edit","delete","close","open"]);
	 
	if(leg == 0){
		 btnReset(["edit","delete","close","open"]);
    	return;
    }
	if(size == cl){
		btnDisable(["close","edit"]);
	}else if(size == ol){
		btnDisable(["open"]);
	}else{
		btnDisable(["edit","delete","close","open"]);
	}
	
}

 
function changeOpenState(){
	var ordertb = $("#ordertb tr:gt(0) input:checked");
	ordertb.each(function(){
		var STATE = $(this).find("input[name='STATE']").val();
		alert(STATE);
		if("关闭" == STATE){
			btnDisable(["edit","delete","close"]);
		}
		if("正常" == STATE){
			btnDisable(["open"]);
		}
	});
}
