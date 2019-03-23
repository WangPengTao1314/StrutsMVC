 /**
  *@module 系统管理
  *@fuc 数据字典明细列表
  *@version 1.0
  *@author 王志格
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
		$("#FACTORY_PRD_IDS").val(ids);
		$("#form1").attr("action","factory.shtml?action=toProductEdit");
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
		ids = ids+$(this).val()+",";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "factory.shtml?action=productDelete",
		type:"POST",
		dataType:"text",
		data:{"FACTORY_PRD_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				parent.gotoBottomPage();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
