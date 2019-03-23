/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time   2013-09-15 10:35:10 
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
		$("#SALE_DATE_UP_DTL_IDS").val(ids);
		$("#form1").attr("action","saledateup.shtml?action=toChildEdit");
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
	
	countRowNum();
 
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
		url: "saledateup.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"SALE_DATE_UP_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				countAmount();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}



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
function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
//	var type =  parent.topcontent.document.getElementById("type" + selRowId).value;
	if(vstate){
		var state = vstate.value;
		//当状态=提交,当状态=回退,当状态=审核通过
		if (null == state || "" == state || state == "提交"||state == "审核通过"||"未处理"==state||"已处理"==state) {
			btnDisable(["delete","edit"]);
		}
	} 
 
}

 
function countRowNum(){
	var trs = $("#ordertb tr:gt(0)");
	var TOTAL_AMOUNT = 0;
	var TOTAL_NUM = 0;
 
	trs.each(function(){
		var NUM = $(this).find("input[name='TOTAL_NUM']").val();
		var AMOUNT = $(this).find("input[name='TOTAL_AMOUNT']").val();
		NUM = Number(NUM);
		if(isNaN(NUM)){
			NUM = 0;
		}
		TOTAL_NUM = Number(TOTAL_NUM)+NUM;
		AMOUNT = Number(AMOUNT);
		if(isNaN(AMOUNT)){
			AMOUNT = 0;
		}
		 
		TOTAL_AMOUNT = Number(TOTAL_AMOUNT)+AMOUNT;
		
	});

	$("#totalAmount").text(TOTAL_AMOUNT.toFixed(2));
	$("#totalNum").text(TOTAL_NUM.toFixed(2));
}