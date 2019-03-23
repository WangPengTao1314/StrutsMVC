/**
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 货品发运
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	headColumnSort("ordertb");
	//主从及主从从列表页面通用加载方法
//	listPageInit("pdtdeliver.shtml?action=toList");
//	queryInit("pdtdeliver.shtml?action=toList&module=&forWard=select");
    $("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		setSelRowId();
	});
    	 
    $("#confirm").click(function(){
		parent.$("#SELECT_DELIVER_ORDER_ID").val($("#selRowId").val());
		parent.$("#SELECT_DELIVER_ORDER_NO").val($("#DELIVER_ORDER_NOS").val());
		parent.$("#SELECT_RECV_CHANN_ID").val($("#RECV_CHANN_ID").val());
		parent.setFatherValue();
		window.close();
    	
    });
    
    $("#rest").click(function(){
    	rest();
    	window.close();
    });
    
    
    $("#ordertb tr:gt(0)").find("td:gt(0)").click(function(){
		checkedThis(this);
	});
    
    $("#query").click(function(){
		 $("#queryForm").attr("action","pdtdeliver.shtml?action=toList&module=&forWard=select");
		 $("#queryForm").submit();
    	 
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


function rest(){
	$("#selRowId").val("");
    $("#DELIVER_ORDER_NOS").val("");
}
function confirm(){
	var checkboxs = $("#ordertb tr:gt(0) input:checked");
	var RECV_CHANN_ID_OLD = "";
	var flag = true;
	checkboxs.each(function(){
		var RECV_CHANN_ID = $(this).parent().parent().find("input[name='RECV_CHANN_ID']").val();
		if("" == RECV_CHANN_ID_OLD){
			RECV_CHANN_ID_OLD = RECV_CHANN_ID;
		}else if(RECV_CHANN_ID_OLD != RECV_CHANN_ID){
			flag = false;
			return flag
		}
	});
	
	if(!flag){
		showErrorMsg("请选择同一个收货方");
	    return false;
	}
	$("#RECV_CHANN_ID").val(RECV_CHANN_ID_OLD);
	return true;
	
}

 
//明细表点击后设置选中
function setEidChecked(obj){
	var flag = obj.checked;
	if(flag){
		$(obj).attr("checked","true");
	}else{
		$(obj).removeAttr("checked");
	}
	setSelRowId();
}




function setSelRowId(){
    var ids = "";
    var DELIVER_ORDER_NOS = "";
    $("#ordertb input[name='eid']:checked").each(function(){
    	ids = ids+"'"+$(this).val()+"',";
    	var DELIVER_ORDER_NO = $(this).parent().parent().find("input[name='DELIVER_ORDER_NO']").val();
    	DELIVER_ORDER_NOS = DELIVER_ORDER_NOS+"'"+DELIVER_ORDER_NO+"',";
    });
    ids = ids.substr(0,ids.length-1);
    DELIVER_ORDER_NOS = DELIVER_ORDER_NOS.substr(0,DELIVER_ORDER_NOS.length-1);
    //设置选中的Id
     $("#selRowId").val(ids);
    $("#DELIVER_ORDER_NOS").val(DELIVER_ORDER_NOS);
}


 

 







