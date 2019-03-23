/**
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_List_Chld
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
 */
$(function(){
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
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
		$("#STATEMENTS_PAYMENT_DTL_IDS").val(ids);
		$("#form1").attr("action","advpayment.shtml?action=toChildEdit");
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
	
	$.ajax({
		url: "advpayment.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STATEMENTS_PAYMENT_DTL_IDS":ids},
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
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	var JSBHS = $("#JSBHS").val();
	if(vstate){
		var state = vstate.value;
		if (state == "已结算" || state=="" || state=="已核销") {
			btnDisable(["delete","edit"]);
		}
		//add by zzb 2015-06-12 渠道财务-角色编号(DRP_F03) 在‘已结算’状态需要修改 付款方式
		if(state == "已结算" && JSBHS.indexOf("DRP_F03")>-1){
			btnArrysReset(["edit"]);
		}
	}
}
