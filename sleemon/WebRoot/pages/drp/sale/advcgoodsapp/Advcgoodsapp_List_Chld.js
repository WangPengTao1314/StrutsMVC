/**
 * @prjName:喜临门营销平台
 * @fileName:Advcgoodsapp_List_Chld
 * @author lyg
 * @time   2013-11-02 18:55:53 
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
		$("#ADVC_SEND_REQ_DTL_IDS").val(ids);
		$("#ADVC_SEND_REQ_ID").val(selRowId);
		$("#form1").attr("action","advcgoodsapp.shtml?action=toChildEdit");
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
	
	changeColor();
	
	setSelOperateEx();
});


//总部 销售订单 取消的 明细 特殊颜色
function changeColor(){
	var ordertb = $("#ordertb tr:gt(0)");
	ordertb.each(function(){
		var CANCEL_FLAG = $(this).find("input[name='CANCEL_FLAG']").val();
		if(CANCEL_FLAG == 1){
			var id = $(this).attr("id");
		    $(this).find("td").css("background-color", "#E6B9B8");//#F3F3F3花号还原
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
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "advcgoodsapp.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"ADVC_SEND_REQ_DTL_IDS":ids,"ADVC_SEND_REQ_ID":selRowId},
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
function url(SPCL_TECH_ID){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}


function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	if(null == selRowId || "" == selRowId){
		btnDisable(["delete","edit"]);
	}
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate) {
		var state = vstate.value;
		if (state == "提交"||state == "已取消"||state == "审核通过"||state == "已发货") {
			btnDisable(["delete","edit"]);
		}
	}
}

