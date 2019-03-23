/**
 *@module 渠道管理-装修管理
 *@func   装修验收单维护
 *@version 1.1
 *@author zcx
 */
 var selRowId = parent.document.getElementById("selRowId").value;
$(function(){
	//维护和审核调用的是同一页面，只是按钮不同，所以需要设置
	//审核页面需要隐藏的按钮 TODO: 尺码类页面没有审核页面
	//shBtnHidden(["edit","delete"]);
 
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
	   
	    var selRowId = parent.document.getElementById("selRowId").value;
	    if(selRowId == null || '' == selRowId){
	        parent.showErrorMsg("主表没有记录");
			return;
	    }
	    
	    var state = parent.topcontent.document.getElementById("state"+selRowId).value;
	    if('启用' == state){
	       parent.showErrorMsg("主表启用状态");
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
		$("#RNVTN_CHECK_DTL_IDS").val(ids);
		$("#form1").attr("action","rnvtncheck.shtml?action=toChildEdit");
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
	
	var selRowId = parent.document.getElementById("selRowId").value;
    if(null == selRowId || "" == selRowId){
      btnDisable(["edit","delete"]);
      return;
    }
	
});

function multiRecDeletes(){

    //查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//var selRowId = parent.document.getElementById("selRowId").value;
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	var actionType = getActionType(); 
	$.ajax({
		url: "rnvtncheck.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"RNVTN_CHECK_DTL_IDs":ids,"RNVTN_CHECK_DTL_ID":utf8(selRowId)},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				if(actionType=="list")
				{
				   parent.setRefreshFlag(false);
				   parent.topcontent.location.reload();
				}
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function getActionType(){
	return parent.document.getElementById("actionType").value;
}
