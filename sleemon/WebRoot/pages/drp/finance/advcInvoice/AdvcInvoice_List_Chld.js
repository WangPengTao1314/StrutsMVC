﻿
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	//审核页面需要隐藏的按钮
	shBtnHidden(["edit","delete","removeFreeze"]);
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
//		if (state != "未提交") {
//			parent.showErrorMsg("当前主表状态下，不能删除明细！");
//			return;
//		}
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
	});
	
    $("#closeAdvc").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确定关闭该条数据吗?","bottomcontent.closeAdvc();","N");
    })
    changeColor();
    
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
		$("#ADVC_ORDER_DTL_IDS").val(ids);
		$("#form1").attr("action","advcinvoice.shtml?action=toChildEdit");
		$("#form1").submit();
	});
	$("#removeFreeze").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要解除冻结吗？","bottomcontent.multiRecRemoveFreeze();");
	});
	$("#cancelAdvc").click(function(){
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条明细记录");
			return;
		}
		parent.showConfirm("您确认要取消预订吗？","bottomcontent.multiCancel();","N");
	});
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		setSelOperateEx();
	});
		setSelOperateEx();
		
		isNoEdit();
		
});
//add by zzb 20143-9-24 预订单核销跳转
function isNoEdit(){
	var isNoEdit = parent.$("#isNoEdit").val();
	if(isNoEdit){
		$("div[class='tablayer']").hide();
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
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "advcinvoice.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"ADVC_ORDER_DTL_IDS":ids,"ADVC_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function multiRecRemoveFreeze(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "advcinvoice.shtml?action=removeFreeze",
		type:"POST",
		dataType:"text",
		data:{"ADVC_ORDER_DTL_IDS":ids,"ADVC_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("解除成功");
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function multiCancel(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "advcinvoice.shtml?action=toCanCel",
		type:"POST",
		dataType:"text",
		data:{"ADVC_ORDER_DTL_IDS":ids,"ADVC_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("解除成功");
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate=parent.topcontent.document.getElementById("state" + selRowId);
	btnReset();
	btnDisable(["removeFreeze"]);
	if(vstate){
		var state = vstate.value;
		//当状态！=未提交
		if (state != "未提交"&&state != "退回") {
			btnDisable(["delete","edit"]);
		}
		if(state!="审核通过"&&state!="已结算"&&state!="待结算"&&state!="待确认"&&state!="已发货"&&state!="审核通过"){
			btnDisable(["closeAdvc"]);
		}
		if(state=="提交"){
			var checkBox = $("#ordertb tr:gt(0) input:checked");
			checkBox.each(function(){
				var IS_FREEZE_FLAG=$(this).parent().parent().find("input[name='IS_FREEZE_FLAG']").val();
				var count=$(this).parent().parent().find("input[name='COUNT']").val();
				if(1==IS_FREEZE_FLAG&&0==count){
					$("#removeFreeze").removeAttr("disabled");
					return;
				}
			})
		}else{
			btnDisable(["cancelAdvc"]);
		}
		
		if(state=="审核通过"){
			var checkBox = $("#ordertb tr:gt(0) input:checked");
			checkBox.each(function(){
				var STATE=$(this).parent().parent().find("input[name='STATE']").val();
				if("关闭"==STATE){
					btnDisable(["closeAdvc"]);
					return;
				}
			})
		}
		
		
	}
}
//关闭
function closeAdvc(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	var selRowId = parent.document.getElementById("selRowId").value;
	var ids = "";
	if(checkBox.length>0){
		//获取所有选中的记录
		checkBox.each(function(){
			ids = ids+"'"+$(this).val()+"',";
		});
		ids = ids.substr(0,ids.length-1);
	}
	$.ajax({
	 url: "advcinvoice.shtml?action=toClose&ADVC_ORDER_ID="+utf8(selRowId)+"&ADVC_ORDER_DTL_IDS="+utf8(ids),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("关闭成功");
            window.parent.topcontent.pageForm.submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
function url(SPCL_TECH_ID){
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}
//总部 销售订单 取消的 明细 特殊颜色
function changeColor(){
	var ordertb = $("#ordertb tr:gt(0)");
	ordertb.each(function(){
		var STATE = $(this).find("input[name='STATE']").val();
		if(STATE == '关闭'){
			var id = $(this).attr("id");
		    $(this).find("td").css("background-color", "#FFFF66");//#F3F3F3花号还原
		}
	});
	
}
