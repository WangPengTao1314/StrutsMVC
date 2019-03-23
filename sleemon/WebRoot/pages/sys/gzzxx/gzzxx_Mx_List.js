 /**
  *@module 系统管理
  *@func 工作组信息
  *@version 1.1
  *@author 吴亚林
*/

$(function(){
	
	
	var selRowId = parent.document.getElementById("selRowId").value;
	if(selRowId == null || '' == selRowId){
		btnDisable(["edit","delete"]);
	}else{
	    var chiState = $.trim(parent.topcontent.$("#ordertb  :input[type='radio'][checked='true']").parent().parent().find("td").eq(4).text());
	    if('启用' == chiState){
	       btnDisable(["edit","delete"]);
		   return;
	    }
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
		$("#GZZCYID").val(ids);
		$("#form1").attr("action","gzzxx.shtml?action=toChildEdit");
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
		url: "gzzxx.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"GZZCYID":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				parent.topcontent.location="gzzxx.shtml?action=toList";
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

