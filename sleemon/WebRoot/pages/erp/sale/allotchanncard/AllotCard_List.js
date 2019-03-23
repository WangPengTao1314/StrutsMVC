/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time   2014-12-03 10:35:10 
 * @version 1.1
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
	$("#queryForm").attr("action","allotchanncard.shtml?action=childList");
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
    var NO_SALE_ID = "";
    $("#ordertb input[name='eid']:checked").each(function(){
    	//只添加  分管表 没有的渠道
    	var CHANN_ID = $(this).parent().parent().find("input[name='CHANN_ID']").val();
    	if(null == CHANN_ID || "" == CHANN_ID){
    		ids = ids+"'"+$(this).val()+"',";
    	} 
    	NO_SALE_ID = NO_SALE_ID+"'"+$(this).val()+"',";
    });
    ids = ids.substr(0,ids.length-1);
    NO_SALE_ID = NO_SALE_ID.substr(0,NO_SALE_ID.length-1);
    //设置选中的Id
    parent.document.getElementById("selRowId").value = ids;
    parent.document.getElementById("NO_SALE_ID").value = NO_SALE_ID;
    
 
}

function listDelConfirm(obj,id){
	closeWindow();
	var CHANN_IDS =  $("#"+id).val();
	var XTYHID = parent.topcontent.$("#XTYHID").val();
	if(null != CHANN_IDS){
		CHANN_IDS = "'"+CHANN_IDS+"'";
	}
	 $.ajax({
	 	url: "allotchanncard.shtml?action=delete",
		type:"POST",
		data:{"XTYHID":XTYHID,"CHANN_IDS":CHANN_IDS},
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


