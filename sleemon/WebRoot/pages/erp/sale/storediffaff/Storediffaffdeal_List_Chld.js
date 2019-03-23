/**
 * @prjName:喜临门营销平台
 * @fileName:Storediffdeal_List_Chld
 * @author wzg
 * @time   2013-08-30 14:17:25 
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
		$("#DIFF_DEAL_DTL_IDS").val(ids);
		var STOREIN_DIFF_DTL_ID = $("#STOREIN_DIFF_DTL_ID",parent.window.document).val();
		
		alert($("#DIFF_DEAL_DTL_IDS").val());
		//$("#form1").attr("action","storediffaff.shtml?action=todealEdit&STOREIN_DIFF_DTL_ID="+STOREIN_DIFF_DTL_ID);
		//$("#form1").submit();
	});
	
	$("#save").click(function(){
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		
		childSave();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
	setSelOperateEx();
});

//子表保存
function childSave(){
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "storediffaff.shtml?action=dealEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
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
		url: "storediffaff.shtml?action=dealDelete",
		type:"POST",
		dataType:"text",
		data:{"DIFF_DEAL_DTL_IDS":ids},
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
	var vstate=parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		if (state == "退回" || state == "完成") {
			btnDisable(["save"]);
		}
	}
}
function changeDealway(rowNum){
	
	var v = $("#DIFF_RSON"+rowNum).val();
	v = $.trim(v);
	if("多发货" == v){
		  
		 $("#DEAL_WAY"+rowNum+" option").remove();
		 $("#DEAL_WAY"+rowNum).append("<option>-请选择-</option>");
		 $("#DEAL_WAY"+rowNum).append("<option value='视同销售' >视同销售</option>");
		 $("#DEAL_WAY"+rowNum).append("<option value='退货' >退货</option>");
	} 
	
	if("少发货" == v){
		
		$("#DEAL_WAY"+rowNum+" option").remove();
		$("#DEAL_WAY"+rowNum).append("<option>-请选择-</option>");
		$("#DEAL_WAY"+rowNum).append("<option value='补单' >补单</option>");
		$("#DEAL_WAY"+rowNum).append("<option value='退货' >退货</option>");
	} 
	if("运输破损" == v){
		 $("#DEAL_WAY"+rowNum+" option").remove();
		 $("#DEAL_WAY"+rowNum).append("<option>-请选择-</option>");
		 $("#DEAL_WAY"+rowNum).append("<option value='退货' >退货</option>");
		 $("#DEAL_WAY"+rowNum).append("<option value='换货' >换货</option>");
		
		 
	} 
	
}
