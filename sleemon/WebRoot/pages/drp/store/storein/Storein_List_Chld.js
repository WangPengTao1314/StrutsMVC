/**
 * @prjName:喜临门营销平台
 * @fileName:Storein_List_Chld
 * @author glw
 * @time   2013-08-19 16:55:43 
 * @version 1.1
 */
$(function(){
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
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
    $("#scan").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		window.open('storein.shtml?action=toEditScanDtl&STOREIN_ID='+selRowId,'货品扫码明细','height=400, width=1100, top=200, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
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
		} else {
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		$("#STOREIN_DTL_IDS").val(ids);
		$("#form1").attr("action","storein.shtml?action=toChildEdit");
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
	//按钮状态重置
	var state = parent.document.getElementById("STATE").value;
	btnReset();
	if(state == "已处理"){
		btnDisable(["edit","add","delete","scan"]);
	}
	var billType = getBillType();
	if(billType == '终端退货'){
		btnDisable(["edit","add","delete"]);
	}
	
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
		url: "storein.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STOREIN_DTL_IDS":ids},
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


function addStorein(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var PRD_ID = $("#PRD_ID").val();
	var PRD_NO = $("#PRD_NO").val();
	var PRD_NAME = $("#PRD_NAME").val();
	var PRD_COLOR = $("#PRD_COLOR").val();
	var PRD_SPEC = $("#PRD_SPEC").val();
	var BRAND = $("#BRAND").val();
	var STD_UNIT = $("#STD_UNIT").val();
	var PRVD_PRICE = $("#PRVD_PRICE").val();
 
	var INS_FLAG=1;
	var childJsonData = '[{"PRD_ID":"'+PRD_ID+'","PRD_NO":"'+PRD_NO+'","PRD_NAME":"'+PRD_NAME+'","PRD_COLOR":"'+PRD_COLOR+'","PRD_SPEC":"'+PRD_SPEC+'","STD_UNIT":"'+STD_UNIT+'","BRAND":"'+BRAND+'","PRICE":"'+PRVD_PRICE+'","INS_FLAG":"'+INS_FLAG+'"}]';
	if (PRD_ID=="") {
		return;
	}
	$.ajax({
		url: "storein.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"STOREIN_ID":selRowId,
			  "childJsonData":childJsonData
			 },
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("新增成功");
				$("#STOREIN_ID").val(selRowId);
				$("#form1").attr("action","storein.shtml?action=childList");
				$("#form1").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function setSelOperateEx() {
	var state = parent.document.getElementById("STATE").value;
	if(state == "已处理"){
		return;
	}
	var billType = getBillType();
	if(billType == '终端退货'){
		return;
	}
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	var tab="0";
	checkBox.each(function(){
		$(this).parent().parent().find("input[name='delFlag']").each(function (){
			if($(this).val()=="0"){
				tab="1";
			};
		});
	});
	if(tab=="1"){
		//btnDisable(["delete"]);
	}else{
		btnReset();
	}
}
function showStoge(STOREIN_DTL_ID){
	STOREIN_DTL_ID = "'"+STOREIN_DTL_ID+"'";
	var STOREIN_ID = parent.document.getElementById("selRowId").value;
	window.showModalDialog('storein.shtml?action=grandChildList&STOREIN_DTL_ID='+ STOREIN_DTL_ID+"&STOREIN_ID="+STOREIN_ID,window,"dialogWidth=900px;dialogHeight=500px;depended=no");
	window.location.reload();  
}

function url(SPCL_TECH_ID){
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}


//获取单据类型
function getBillType(){
	return parent.topcontent.getBillType();
}
 

function countTotalAmount(){
	var checkBox = $("#ordertb tr:gt(0) input:checkbox");
	var totalNum = 0;
	var totalAmount = 0;
	checkBox.each(function(){
		var NOTICE_NUM = $(this).parent().parent().find("td[name='NOTICE_NUM']").text();
		NOTICE_NUM = $.trim(NOTICE_NUM);
		var DECT_AMOUNT = $(this).parent().parent().find("input[name='DECT_AMOUNT']").val();
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
