/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_List_Chld
 * @author glw
 * @time   2013-08-17 17:07:03 
 * @version 1.1
 */
$(function(){
	//审核页面需要隐藏的按钮
	//whBtnHidden(["edit","delete"]);
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
		var billtype = parent.topcontent.document.getElementById("billtype" + selRowId).value;
		//alert("billtype====="+billtype);
		/*
		if ((billtype != "手工新增" && state != "撤销") || (billtype != "手工新增" && state != "未提交")) {
			parent.showErrorMsg("当前主表状态下，不能删除明细！");
			return;
		}
		if (state != "未提交" && state != "\u9000\u56de" && state != "\u5426\u51b3" && state != "\u64a4\u9500") {
			parent.showErrorMsg("当前主表状态下，不能删除明细！");
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
		//查找当前是否有选中的记录
		var checkBox = $("#ordertb tr:gt(0) input:checked");
//		if(checkBox.length<1){
//			parent.showErrorMsg("请至少选择一条记录");
//			return;
//		}
		var selRowId = parent.document.getElementById("selRowId").value;
		//alert("selRowId====="+selRowId);
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
		$("#STOREIN_NOTICE_DTL_IDS").val(ids);
		$("#STOREIN_NOTICE_ID").val(selRowId);
		$("#form1").attr("action","tstoreIn.shtml?action=toChildEdit");
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
		
		countTotalAmount();
});
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
		url: "tstoreIn.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STOREIN_NOTICE_DTL_IDS":ids},
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
	if(vstate) {
		var state = vstate.value;
		//当状态=提交,当状态=回退,当状态=审核通过
		if (state == "\u63d0\u4ea4"||state == "\u9000\u56de"||state == "\u5ba1\u6838\u901a\u8fc7") {
			btnDisable(["delete","edit"]);
		}
		
	}
	 
	
}

function url(SPCL_TECH_ID){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}


function countTotalAmount(){
	var checkBox = $("#ordertb tr:gt(0) input:checkbox");
	var totalNum = 0;
	var totalAmount = 0;
	checkBox.each(function(){
		var NOTICE_NUM = $(this).parent().parent().find("td[name='NOTICE_NUM']").text();
		NOTICE_NUM = $.trim(NOTICE_NUM);
		var DECT_AMOUNT = $(this).parent().parent().find("td[name='DECT_AMOUNT']").text();
		DECT_AMOUNT = $.trim(DECT_AMOUNT);
		NOTICE_NUM = parseInt(NOTICE_NUM);
		DECT_AMOUNT = parseFloat(DECT_AMOUNT);
		if(isNaN(NOTICE_NUM)){
			NOTICE_NUM = 0;
		}
		
		if(isNaN(DECT_AMOUNT)){
			DECT_AMOUNT = 0;
		}
		
		totalNum = totalNum + NOTICE_NUM;
		totalAmount = totalAmount + DECT_AMOUNT;
	});
	$("#totalNum").text(totalNum);
	totalAmount = totalAmount.toFixed(2);
	$("#totalAmount").text(totalAmount);
}