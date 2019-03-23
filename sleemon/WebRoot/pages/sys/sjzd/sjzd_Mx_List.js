 /**
  *@module 系统管理
  *@fuc 数据字典明细列表
  *@version 1.1
  *@author 张羽
*/

$(function(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = parent.topcontent.document.getElementById("state"+selRowId).value;
	if(state == '启用'){
	   btnDisable(["edit","delete"]);
	}
	if(selRowId == null || '' == selRowId){
	        btnDisable(["edit","delete"]);
			return;
	}
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
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#SJZDMXIDS").val(ids);
		var chiState = $.trim(parent.topcontent.$("#ordertb  :input[type='radio'][checked='true']").parent().parent().find("td").eq(5).text());
		$("#form1").attr("action","sjzd.shtml?action=toChildEdit&chistate="+utf8(chiState));
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
	//setButtonState();
});

function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "sjzd.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"SJZDMXIDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				//checkBox.parent().parent().remove();
				parent.topcontent.location="sjzd.shtml?action=toList";
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


//点击选择某条记录后，需要根据状态判断是否可用
function setButtonState(){
	var child = document.getElementById("eid1");
	var selRowId = parent.document.getElementById("selRowId").value;

	
}

