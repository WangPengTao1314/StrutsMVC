/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_List_Chld
 * @author lyg
 * @time   2013-08-19 14:35:52 
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
		var selRowId = parent.document.getElementById("selRowId").value;
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
		$("#ADVC_ORDER_ID").val(selRowId);
		$("#form1").attr("action","termreturn.shtml?action=toChildEdit");
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
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "termreturn.shtml?action=childDelete",
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
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate=parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		//当状态！=未提交
		if (state != "未提交"&&state != "退回") {
			btnDisable(["delete","edit"]);
		}
	}
}
function url(SPCL_TECH_ID){
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}