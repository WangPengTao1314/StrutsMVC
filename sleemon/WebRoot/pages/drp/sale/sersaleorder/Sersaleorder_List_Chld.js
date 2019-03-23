/**
 * @prjName:喜临门营销平台
 * @fileName:分销-区域服务中心-销售订单处理
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	//审核页面需要隐藏的按钮
	shBtnHidden(["edit","delete"]);
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
		if (state != "未提交" && state != "\u9000\u56de" && state != "\u5426\u51b3" && state != "\u64a4\u9500") {
			parent.showErrorMsg("当前主表状态下，不能删除明细！");
			return;
		}
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
		$("#SALE_ORDER_DTL_IDS").val(ids);
		$("#form1").attr("action","sersaleorder.shtml?action=toChildEdit");
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
	setSelOperateEx();
	
	//取消预订
	$("#cancel").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		parent.showConfirm("您确认要取消预订吗","bottomcontent.cancel();","N");
	});
	//恢复预订
	$("#recover").click(function(){
		recover();
	});
	
	
});

 

//取消预定
function cancel(){
	var selRowId = parent.document.getElementById("selRowId").value;
	//查找当前是否有选中的记录
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	if(checkBox.length<1){
		parent.showErrorMsg("请至少选择一条记录");
		return;
	}
	
	//获取所有选中的记录
	var ids = "";
	var formIds = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
		var formId = $(this).parent().find("input[name='FROM_BILL_DTL_ID']").val();
		if(null != formId &&　"" != formId){
			formIds = formIds+"'"+formId+"',";
		}
	});
	ids = ids.substr(0,ids.length-1);
	formIds = formIds.substr(0,formIds.length-1);
 
	$.ajax({
		url: "saleorder.shtml?action=cancelOrder",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_DTL_IDS":ids,"SALE_ORDER_ID":selRowId,"FROM_BILL_DTL_IDS":formIds},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				parent.showMsgPanel(utf8Decode(jsonResult.messages));
				checkBox.parent().parent().remove();
				countAmount();
				saveSuccess(utf8Decode(jsonResult.messages),"saleorder.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//恢复 预定
function recover(){
	//selCommon("BS_59", false, "SALE_ORDER_DTL_ID", "SALE_ORDER_DTL_ID","forms[0]","PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,ORDER_NUM,ORDER_AMOUNT", "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,ORDER_NUM,ORDER_AMOUNT", "selectContion")
    
	var selRowId = parent.document.getElementById("selRowId").value;
	$("#selectContion").val(" SALE_ORDER_ID='"+selRowId+"' and CANCEL_FLAG=1 ");
	
	var returnArr = selCommon("BS_61", false, "SALE_ORDER_DTL_ID", "SALE_ORDER_DTL_ID","forms[0]","SALE_ORDER_DTL_ID,FROM_BILL_DTL_ID", "SALE_ORDER_DTL_ID,FROM_BILL_DTL_ID", "selectContion")
    var isSelect = returnArr[1];
	if(!isSelect){
		return;
	}
 
    var SALE_ORDER_DTL_ID = $("#SALE_ORDER_DTL_ID").val();
    var FROM_BILL_DTL_ID = $("#FROM_BILL_DTL_ID").val();
    FROM_BILL_DTL_ID = "'"+FROM_BILL_DTL_ID+"'";
	$.ajax({
		url: "sersaleorder.shtml?action=recoverOrder",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_ID":selRowId,"SALE_ORDER_DTL_ID":SALE_ORDER_DTL_ID,"FROM_BILL_DTL_IDS":FROM_BILL_DTL_ID},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess(utf8Decode(jsonResult.messages),"sersaleorder.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});

}

///设置 一览页面 ‘订货总额’
function countAmount(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var inputs = $("#ordertb").find("input[name='HID_ORDER_AMOUNT']");
	var total = 0;
	inputs.each(function(){
		total = Number(total) + Number($(this).val());
	});
	parent.topcontent.setTotalAmount(total);
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
		url: "sersaleorder.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"SALE_ORDER_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	if(null == selRowId || "" == selRowId){
		btnDisable(["recover","cancel","convert"]);
	}
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		//当状态=提交,当状态=回退,当状态=审核通过
		if (null == state || state == "\u63d0\u4ea4"||state == "\u9000\u56de"||state == "\u5ba1\u6838\u901a\u8fc7") {
			btnDisable(["recover","cancel","convert"]);
		}
	}
}

//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}


