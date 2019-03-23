/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time   2014-12-03 10:35:10 
 * @version 1.1
 */
$(function(){
	//审核页面需要隐藏的按钮
	shBtnHidden(["add","delete","start","stop"]);
	
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
		var selRowId = parent.document.getElementById("selRowId").value;
		$("#MEMBER_CARD_DTL_IDS").val(ids);
		$("#MEMBER_ID").val(selRowId);
		$("#form1").attr("action","member.shtml?action=toMemberCardEdit");
		$("#form1").submit();
	});
	
	$("#start").click(function(){
		 cardStart();
	});
	$("#stop").click(function(){
		 cardStop();
	});
	
	
	$("#add").click(function(){
		showPage();
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
	var flag = true;
	checkBox.each(function(){
		var state = $(this).parent().parent().find("input[name='state']").val();
		if("开启" == state){
			flag = false;
	    }
		ids = ids+"'"+$(this).val()+"',";
	});
	
	if(!flag){
		parent.showErrorMsg("开启状态不能删除");
		return;
	}
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "member.shtml?action=memberCardDelete",
		type:"POST",
		dataType:"text",
		data:{"MEMBER_CARD_DTL_IDS":ids},
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
		if (null == state || "" == state || state == "提交"||state == "审核通过" || state=="启用" ) {
			btnDisable(["edit","delete","start","stop"]);
		}
	} 
 }
 
 

 


