/**
 *@module 渠道管理-拜访管理
 *@func   月度工作计划维护
 *@version 1.1
 *@author zcx
 */
 var selRowId = parent.document.getElementById("selRowId").value;
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
	    if(selRowId == null || '' == selRowId){
	        parent.showErrorMsg("主表没有记录");
			return;
	    }
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#MONTH_VISIT_PLAN_DTL_IDS").val(ids);
		$("#MONTH_VISIT_PLAN_ID").val(selRowId);
		$("#Tflag").val("1");
		$("#form1").attr("action","monthVisit.shtml?action=toChildEditT");
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
		ids = ids+""+$(this).val()+",";
	});
 	ids = ids.substr(0,ids.length-1);
	var actionType = getActionType(); 
	$.ajax({
		url: "monthVisit.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"MONTH_VISIT_PLAN_DTL_IDs":ids,"MONTH_VISIT_PLAN_ID":utf8(selRowId)},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				parent.setRefreshFlag(false);
				parent.topcontent.location.reload();
				 
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function getActionType(){
	return parent.document.getElementById("actionType").value;
}
