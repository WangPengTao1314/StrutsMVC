/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorderhd_List_Chld
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
$(function(){
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
		parent.showConfirm("您确认要删除评估表吗","bottomcontent.multiRecDeletes();");
	});

	$("#edit").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		window.parent.location="openterminal.shtml?action=toEditFrame&EDIT_FLAG=CHILD_EDIT&selRowId="+selRowId; 
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
    var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "openterminal.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"OPEN_TERMINAL_REQ_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
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
		if (null == state || "" == state || state == "提交"||state == "审核通过"||"未处理"==state||"已处理"==state) {
			//btnDisable(["delete","edit"]);
		}
	} 
 
}
 
