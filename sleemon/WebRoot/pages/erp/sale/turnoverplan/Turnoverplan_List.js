/**
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 制定交付计划
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("turnoverplan.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("turnoverplan.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("turnoverplan.shtml?action=delete", "SALE_ORDER_ID");
 	$("#expdate").click(function(){
		 $("#queryForm").attr("action","turnoverplan.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
 
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		//设置选择的记录的ID
		setSelRowId();
		//判断 是否属于同一个区域服务中心
	    justOnlyAreaSer();
	    checkStart();
	});
 	$("#return").click(function(){
 		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
 		 parent.showConfirm("您确定要退回吗？","topcontent.toReturn();","N");
 	})
 	
 	//dateDiffColor();
});
  

function getRecvAddr(){
	var cuur_id = $("#cuur_id").val();
	var id = $("#"+cuur_id).val();
	var RECV_ADDR = $("#RECV_ADDR"+id).val();
	return RECV_ADDR;
}

//check选择的订单的收货方
function justOnlyAreaSer(){
	var ids = parent.document.getElementById("selRowId").value;
	if(null == ids || "" == ids){
		//设置选择的记录的ID 并刷新子页面
		setSelRowId();
		return;
	}
	 $.ajax({
		url: "turnoverplan.shtml?action=justOnlyAreaSer",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_IDS":ids},
		complete: function(xhr){
            eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 var msg = jsonResult.messages;
				 if("false" == msg){
					 parent.showErrorMsg("订单的收货方不属于同一个区域服务中心，不能交付。请重新选择");
					 parent.$("#YT_MSG_BTN_OK").click(function(){
						 var cuur_id = $("#cuur_id").val();
						 var flag = $("#"+cuur_id).attr("checked");
						 if("checked" == flag){
						   $("#"+cuur_id).removeAttr("checked");
						 }
						 
						 var allcheck = $("#allChecked").attr("checked");
						 if("checked" == allcheck){
							$("#ordertb :checkbox").removeAttr("checked"); 
							parent.$("#selRowId").val("");
						 }
						 //设置选择的记录的ID 并刷新子页面
						 setSelRowId();
						   
					 });
				 }else{
					 //判断同一个区域服务中心下的 收货方是否相同
					 checkAreaSer();
					 
				 }
				
			} 	 
			 
		}
	});
	  	
}



//判断同一个区域服务中心下的 收货方是否相同
function checkAreaSer(){
	var RECV_CHANN_ID_OLD = "";
	var RECV_ADDR_NO_OLD = "";
	var flag = true;
	var flag1 = true;
	$("#ordertb input[name='eid']:checked").each(function(){
    	 var RECV_CHANN_ID = $(this).parent().parent().find("input[name='RECV_CHANN_ID']").val();
    	 var RECV_ADDR_NO = $(this).parent().parent().find("input[name='RECV_ADDR_NO']").val();
    	 if("" == RECV_CHANN_ID_OLD){
    		 if(null !=RECV_CHANN_ID && "" != RECV_CHANN_ID){
    			  RECV_CHANN_ID_OLD = RECV_CHANN_ID;
    		 }
    		
    	 }else if(RECV_CHANN_ID_OLD != RECV_CHANN_ID){
    		 flag = false;
		     return flag;
    	 }
    	 //判断收货地址是否一样
    	 if("" == RECV_ADDR_NO_OLD){
    		 if(null !=RECV_ADDR_NO && "" != RECV_ADDR_NO){
    			  RECV_ADDR_NO_OLD = RECV_ADDR_NO;
    		 }
    		
    	 }else if(RECV_ADDR_NO_OLD != RECV_ADDR_NO){
    		 flag1 = false;
		     return flag1;
    	 }
    	 
    });
	
	if(!flag){
		parent.showYONConfirm("订单的收货方不同，是否取消该订单?","topcontent.calcelOrder();","N");
	}
	
	if(!flag1){
		parent.showErrorMsg("订单的收货地址不同，不能交付。请重新选择");
		 var cuur_id = $("#cuur_id").val();
		 var flag = $("#"+cuur_id).attr("checked");
		 if("checked" == flag){
		   $("#"+cuur_id).removeAttr("checked");
		}
		var allcheck = $("#allChecked").attr("checked");
		if("checked" == allcheck){
			$("#ordertb :checkbox").removeAttr("checked"); 
			parent.$("#selRowId").val("");
		}
		 //设置选择的记录的ID 并刷新子页面
		setSelRowId();
	}
}


function calcelOrder(){
	var cuur_id = $("#cuur_id").val();
	var flag = $("#"+cuur_id).attr("checked");
	if("checked" == flag){
	   $("#"+cuur_id).removeAttr("checked");
	}
	var allcheck = $("#allChecked").attr("checked");
    if("checked" == allcheck){
	  $("#ordertb :checkbox").removeAttr("checked"); 
	 parent.$("#selRowId").val("");
   }
    //设置选择的记录的ID 并刷新子页面
	 setSelRowId();
	
}


//明细表点击后设置选中
function setEidChecked(obj){
	var flag = obj.checked;
	if(flag){
		$(obj).attr("checked","true");
	}else{
		$(obj).removeAttr("checked");
	}
     
	$("#cuur_id").val($(obj).attr("id"));
	//设置当前选择的记录的收货地址
	getRecvAddr();
	
	//设置
	setSelRowId();
	//判断 是否属于同一个区域服务中心
	justOnlyAreaSer();
    
}




function setSelRowId(){
    var ids = "";
    $("#ordertb input[name='eid']:checked").each(function(){
    	ids = ids+"'"+$(this).val()+"',";
    });
    ids = ids.substr(0,ids.length-1);
    //设置选中的Id
    parent.document.getElementById("selRowId").value = ids;
    //刷新子页面
	parent.window.gotoBottomPage();
}



function showPage(){
	$("#edit_remark").show();
}


function modifyRemark(){
	var REMARK =  $("#REMARK").val();
	if (!mustInputAllCheck($("#REMARK"))) {
		return false;
	}
	closePage();
//	$.ajax({
//	 	url: "goodsorder.shtml?action=modifyRemark",
//		type:"POST",
//		data:{"GOODS_ORDER_ID":selRowId,"REMARK":REMARK},
//		complete: function(xhr){
//			eval("jsonResult = "+xhr.responseText);
//			if(jsonResult.success===true){
//				parent.showMsgPanel("操作成功");
//				closePage();
//				$("#pageForm").submit();
//			}else{
//				 
//			}
//		}
//	});
}




function closePage(){
	$("#edit_remark").hide();
}



 
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit"]);
	}
	
	//当状态=撤销
	if (state == "\u64a4\u9500") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "\u5426\u51b3") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
}



function changeTextAreaLength(obj) {
	var upper = obj.className;
	if("uppercase"==upper){
		obj.value=obj.value.toUpperCase();
	}
	var maxL = obj.maxlength || obj.maxLength;
	var L = stringLength(obj.value);
	if (L > maxL) {
		obj.value = obj.oVal;
	} else {
		obj.oVal = obj.value;
	}
}
function toReturn(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	 $.ajax({
		url: "turnoverplan.shtml?action=toReturn",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				$("#queryForm").submit();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
			closePage();
		}
	});
}
function checkStart(){
	btnReset();
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	checkBox.each(function(){
		var state=$(this).parent().parent().find("input[name='starts']").val();
		if(state!="审核通过"){
			btnDisable(["return"]);
		}
	})
}

//时间差变色  
function dateDiffColor(){
	var inputs = $("#ordertb tr:gt(0)").find("input[name='DATE_DIFF']");
	inputs.each(function(){
		var date = $(this).val();
		date = parseInt(date);
		if(date >=1 ){
			$(this).parent().parent().find("td").css("background-color", "#E693A2");//花号还原
			  
		}
	});
}