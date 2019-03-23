 /**
  *@module 系统管理
  *@fuc 系统角色明细列表
  *@version 1.1
  *@author 唐赟
*/

$(function(){
     
     var selRowId = parent.document.getElementById("selRowId").value; 
        if(selRowId == null || '' == selRowId){
	        btnDisable(["edit","delete"]);
			return;
	    }       
	var state = parent.topcontent.document.getElementById("state"+selRowId).value;
	setBtStats(state);
	//comment by gsy start 2013-7-10
	/*if("启用" == state){
	        btnDisable(["edit","delete"]);
		   return;
	    }*/
	    //comment by gsy end 2013-7-10
	$("#delete").click(function(){
	    //var selRowId = parent.document.getElementById("selRowId").value; 
	    //var state = parent.topcontent.document.getElementById("state"+selRowId).value;
	    
	    if("启用" == state){
	       parent.showErrorMsg("主表为启用状态,不能删除。");
		   return;
	    }
	    
	    /* if('停用' == state){
	       parent.showErrorMsg("主表为停用状态,不能删除。");
		   return;
	    }*/
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
	});

	$("#edit").click(function(){
	    //var selRowId = parent.document.getElementById("selRowId").value; 
	    //var state = parent.topcontent.document.getElementById("state"+selRowId).value;
	    //comment by gsy start 2013-7-10
	   /* if("启用" == state){
	       parent.showErrorMsg("主表为启用状态,不能编辑。");
		   return;
	    }*/
	     //comment by gsy end 2013-7-10
	   
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#XTYHJSIDS").val(ids);
		$("#form1").attr("action","xtjs.shtml?action=toChildEdit");
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

function setBtStats(state){
	btnReset();
	if(state=='启用'){
		btnDisable(["edit","delete"]);
	}
}

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
		url: "xtjs.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"XTJSYHID":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//parent.topcontent.location="xtjs.shtml?action=toList";
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

