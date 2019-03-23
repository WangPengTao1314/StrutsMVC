/**
 * @prjName:喜临门营销平台
 * @fileName:Storeout_List_Chld
 * @author chenj
 * @time   2013-09-15 14:59:50 
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
		$("#FEW_STOREOUT_REQ_DTL_IDS").val(ids);
		$("#form1").attr("action","storeoutfewnotice.shtml?action=toChildEdit");
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
		url: "storeoutfewnotice.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"FEW_STOREOUT_REQ_DTL_IDS":ids},
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
	if(vstate){
		var state = vstate.value;
		//当状态=提交,当状态=回退,当状态=审核通过
		if (null == state || "" == state || state == "提交"||state == "审核通过"||"已取消"==state||"已处理"==state) {
			btnDisable(["delete","edit","scan"]);
		}
	} 
}

function showStoge(STOREOUT_DTL_ID){
	var billType=parent.window.document.getElementById("BILL_TYPE").value;
	var STOREOUT_DTL_IDS = "'"+STOREOUT_DTL_ID+"'";
	var STOREOUT_ID = parent.document.getElementById("selRowId").value;
	window.showModalDialog('storeoutfewnotice.shtml?action=storgChildList&STOREOUT_DTL_IDS='+ STOREOUT_DTL_IDS+"&BILL_TYPE="+billType+"&STOREOUT_ID="+STOREOUT_ID,window,"dialogWidth=900px;dialogHeight=500px;depended=no");
	window.location.reload();  
}


function selProduct(rownum){
	selCommon("BS_21", false, "PRD_ID"+rowNum, "PRD_ID", "forms[0]",
			"PRD_NO"+rowNum+",PRD_NAME"+rowNum+",PRD_SPEC"+rowNum+",PRD_COLOR"+rowNum+",BRAND"+rowNum+",STD_UNIT"+rowNum, 
			"PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT","selectCondition");
}

function url(SPCL_TECH_ID){
//	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}
