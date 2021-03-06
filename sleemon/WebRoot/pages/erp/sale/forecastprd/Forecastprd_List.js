
/**
 * @module 预计量上报管理
 * @func 预计量上报货品设置
 * @version 1.1
 * @author 张忠斌
 */ 
$(function () {
    parent.document.getElementById("selRowId").value = "";
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		setSelRowId();
		 
	});
	
	$("#ordertb tr:gt(0)").find("td:gt(0)td:lt(7)").click(function(){
		checkedThis(this);
	});
	
});


function checkedThis(obj){
	var checkbox = $(obj).parent().find("input:checkbox");
	var flag = checkbox.attr("checked");
	if("checked" == flag){
		checkbox.removeAttr("checked");
	}else{
		checkbox.attr("checked","true");
	}
	setSelRowId();
}

function autoSubmit(){
	$("#queryForm").attr("action","forecastprd.shtml?action=childList");
	$("#queryForm").submit();
}

function setSelEid(obj){
//	var flag = $(obj).attr("checked");
//	alert(flag);
//	if(null == flag){
//		$(obj).attr("checked","true");
//	}else{
//		$(obj).removeAttr("checked");
//	}
//	setEidChecked(obj);
}

//明细表点击后设置选中
function setEidChecked(obj){
	var flag = $(obj).attr("checked");
	if(flag){
		$(obj).attr("checked","true");
	}else{
		$(obj).removeAttr("checked");
	}
	setSelRowId();
	 
}




function setSelRowId(){
    var ids = "";
    var oldIds = "";
    $("#ordertb input[name='eid']:checked").each(function(){
    	//只添加  分管表 没有的渠道
    	var IS_REPORT_FLAG = $(this).parent().parent().find("input[name='IS_REPORT_FLAG']").val();
    	if(1 == IS_REPORT_FLAG || "1" == IS_REPORT_FLAG){
    	}else{
    		ids = ids+"'"+$(this).val()+"',";
    	}
    	
    });
   
    ids = ids.substr(0,ids.length-1);
    //设置选中的Id
    parent.document.getElementById("selRowId").value = ids;
    
    $("#ordertb input[name='eid']").not("input:checked").each(function(){
    	  var IS_REPORT_FLAG = $(this).parent().parent().find("input[name='IS_REPORT_FLAG']").val();
	      if(1 == IS_REPORT_FLAG || "1" == IS_REPORT_FLAG){
	    		oldIds = oldIds+"'"+$(this).val()+"',";
	      }
    });
    oldIds = oldIds.substr(0,oldIds.length-1);
    parent.document.getElementById("deleteIds").value = oldIds;
}

function listDelConfirm(obj,id){
	closeWindow();
	var PRD_IDS =  $("#"+id).val();
	if(null != PRD_IDS){
		PRD_IDS = "'"+PRD_IDS+"'";
	}
	 $.ajax({
	 	url: "forecastprd.shtml?action=delete",
		type:"POST",
		data:{"PRD_IDS":PRD_IDS},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("操作成功");
				$("#"+id).removeAttr("checked");
				$(obj).attr("disabled","disabled");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


 
   
