/**
 * @prjName:喜临门营销平台
 * @fileName:订货订单-合并提交 通用选择
 * @author zzb
 * @time   2014-6-25 13:52:19 
 * @version 1.1
 */
$(function(){
	//主从及主从从列表页面通用加载方法
	InitFormValidator("queryForm");
	headColumnSort("ordertb");
    $("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		setSelRowId();
		trClick();
		checkData();
	});
    	 
    $("#query").click(function(){
    	 if(!formChecked('queryForm')){
			return;
		 }
		 $("#queryForm").attr("action","goodsorder.shtml?action=toMergeList");
		 $("#queryForm").submit();
		 parent.gotoBottomPage("");
    	 
    });
    parent.gotoBottomPage("");
    
    
    $("#ordertb tr:gt(0)").find("td:gt(0)").click(function(){
		checkedThis(this);
	});
    
    
    
    $("#return").click(function(){
		var selRowId = $("#selRowId").val();
		if(null == selRowId || "" == selRowId){
 		   parent.showErrorMsg("请选择一条记录");
 		   return;
 		}else if(selRowId.indexOf(",")>0){
 			 parent.showErrorMsg("不能多选，请勾除不需要退回的单据");
 		     return;
 		}
		parent.showConfirm("您确认要退回吗?","topcontent.returnConfirm();","N");
	});

    
    
    
});

function trClick(id){
	var ids = $("#selRowId").val();
	if(null == ids){
		ids = "";
	}
	parent.gotoBottomPage(ids);
}

//行点击事件
function checkedThis(obj){
	var checkbox = $(obj).parent().find("input:checkbox");
	var flag = checkbox.attr("checked");
	if("checked" == flag){
		checkbox.removeAttr("checked");
	}else{
		checkbox.attr("checked","true");
	}
	
	//设置当前选择的checkbox的Id
	$("#cuur_id").val(checkbox.attr("id"));
	checkData();
	setSelRowId();
}


function rest(){
	$("#selRowId").val("");
}
 

 
//明细表点击后设置选中
function setEidChecked(obj){
	var flag = obj.checked;
	if(flag){
		$(obj).attr("checked","true");
	}else{
		$(obj).removeAttr("checked");
	}
	//设置当前选择的checkbox的Id
	$("#cuur_id").val($(obj).attr("id"));
	checkData();
	setSelRowId();
}



function setSelRowId(){
    var ids = "";
    $("#ordertb input[name='eid']:checked").each(function(){
    	ids = ids+"'"+$(this).val()+"',";
    });
    ids = ids.substr(0,ids.length-1);
    //设置选中的Id
     $("#selRowId").val(ids);
}


//回退
function returnConfirm(){
	closeWindow();
	var return_v = window.showModalDialog('goodsorder.shtml?action=toReason',self,'dialogwidth=510px; dialogheight=400px; status=no');
	if("1" == return_v){
		var reason = $("#msg").val();
		sendMessage();
		//showMsgPanel("退单成功");
		saveConfirm("退单成功","refThispage();");
    	 
    }

}
function refThispage(){
	$("#queryForm").submit();
}

/**
 * 回退成功后 给制单人发消息
 * @return {TypeName} 
 */
function sendMessage(remark){
	var _xtyhids = $("#XTYHID").val();
	var _jgxxids = "";//$("#JGXXID").val();//如果加了部门，就会对同部门的人发 信息
	var _bmxxids = "";// $("#BMXXID").val();//同上
	var selRowId = $("#selRowId").val();
	selRowId = selRowId.replaceAll("'","");
	var NO = $("#GOODS_ORDER_NO"+selRowId).val();
	var reason = $("#msg").val();
	var _fsnr = "单据'"+NO+"',因为'"+reason+"'退单";
	if(null != remark && "" !=remark){
		_fsnr = remark;
	}
	
	$.ajax({
		url: "firstPage.shtml?action=txInsertMessage",
		type:"POST",
		dataType:"text",
		data:{xtyhids:_xtyhids,bmxxids:_bmxxids,jgxxids:_jgxxids,fsnr:_fsnr},
		complete: function(xhr){
		    eval("json = "+xhr.responseText);
		    if(json.success==true){
		       // parent.showErrorMsg(utf8Decode(json.messages));
		    }else{
		       // parent.showErrorMsg(utf8Decode(json.messages));
		    }
		}
	});
}
	


//check订单的‘订货方’，‘收货方’，‘单据类型’，‘区域’，‘生产基地’
function checkData(){
	var checkBoxs = $("#ordertb tr:gt(0) input:checked");
	var ORDER_CHANN_ID_OLD = "";
	var RECV_CHANN_ID_OLD = "";
	var BILL_TYPE_OLD = "";
	var AREA_ID_OLD = "";
	var SHIP_POINT_ID_OLD = "";
	var flag = true;
	checkBoxs.each(function(){
		var ORDER_CHANN_ID = $(this).parent().parent().find("input[json='ORDER_CHANN_ID']").val();
		var RECV_CHANN_ID = $(this).parent().parent().find("input[json='RECV_CHANN_ID']").val();
		var BILL_TYPE = $(this).parent().parent().find("input[json='BILL_TYPE']").val();
		var AREA_ID = $(this).parent().parent().find("input[json='AREA_ID']").val();
		var SHIP_POINT_ID = $(this).parent().parent().find("input[json='SHIP_POINT_ID']").val();
		//订货方
		if("" == ORDER_CHANN_ID_OLD){
			ORDER_CHANN_ID_OLD = ORDER_CHANN_ID;
		}else if(ORDER_CHANN_ID_OLD != ORDER_CHANN_ID){
			parent.showErrorMsg("'订货方'不同，不能合并");
			flag = false;
			return false;
		}
		
		//收货方
		if("" == RECV_CHANN_ID_OLD){
			RECV_CHANN_ID_OLD = RECV_CHANN_ID;
		}else if(RECV_CHANN_ID_OLD != RECV_CHANN_ID){
			parent.showErrorMsg("'收货方'不同，不能合并");
			flag = false;
			return false;
		}
		//单据类型
		if("" == BILL_TYPE_OLD){
			BILL_TYPE_OLD = BILL_TYPE;
		}else if(BILL_TYPE_OLD != BILL_TYPE){
			parent.showErrorMsg("'单据类型'不同，不能合并");
			flag = false;
			return false;
		}
		 
		//区域
		if("" == AREA_ID_OLD){
			AREA_ID_OLD = AREA_ID;
		}else if(AREA_ID_OLD != AREA_ID){
			parent.showErrorMsg("'区域'不同，不能合并");
			flag = false;
			return false;
		}
		//生产基地
		if("" == SHIP_POINT_ID_OLD){
			SHIP_POINT_ID_OLD = SHIP_POINT_ID;
		}else if(SHIP_POINT_ID_OLD != SHIP_POINT_ID){
			parent.showErrorMsg("'生产基地'不同，不能合并");
			flag = false;
			return false;
		}
	});
	
	if(!flag){
		var cuur_id = $("#cuur_id").val();
	    var flag = $("#"+cuur_id).attr("checked");
	    if("checked" == flag){
	       $("#"+cuur_id).removeAttr("checked");
	    }
	    
	    var allcheck = $("#allChecked").attr("checked");
		if("checked" == allcheck){
			$("#ordertb :checkbox").removeAttr("checked"); 
		}
		return;
	}
	
	
	$("#BILL_TYPE_GLOB").val(BILL_TYPE_OLD);
}


function getChildJson(){
	var childData = parent.bottomcontent.multiRadioJsonData("ordertb",true);
	return childData;
}


//合并主表的备注
function getMergeRemrk(){
	var MERGER_REMARKS = "";
    $("#ordertb input[name='eid']:checked").each(function(){
    	 var REMARK = $(this).parent().parent().find("input[name='REMARK']").val();
    	 if(null != REMARK && "" != REMARK){
    		 MERGER_REMARKS = MERGER_REMARKS + REMARK;
    	 }
    });
     
    return MERGER_REMARKS;
}



function getBILL_TYPE(){
	var BILL_TYPE = $("#BILL_TYPE_GLOB").val();
	return BILL_TYPE;
}





