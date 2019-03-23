/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorderhd_List_Chld
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
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
		if (state != "制作" && state !="区域服务中心退回" && state !="总部退回") {
			parent.showErrorMsg("当前主表状态下，不能删除明细！");
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
		$("#GOODS_ORDER_DTL_IDS").val(ids);
		$("#form1").attr("action","goodsorderhd.shtml?action=toChildEdit");
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
	
	var BILL_TYPE = parent.topcontent.getBILL_TYPE();
	if("赠品订货" == BILL_TYPE){
		$("#ordertb tr th[name='hideTdByBillType']").css("display","none"); 
		$("#ordertb tr td[name='hideTdByBillType']").css("display","none");
		$("#edit").attr("disabled","disabled");
	}
	
	setSelOperateEx();
	
	countAmount();
	
	changeColor();
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
		url: "goodsorderhd.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"GOODS_ORDER_DTL_IDS":ids},
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

///设置 一览页面 ‘订货总额’
function countAmount(){
	var selRowId = parent.document.getElementById("selRowId").value;
	var inputs = $("#ordertb").find("input[name='HID_ORDER_AMOUNT']");
	var total = 0;
	inputs.each(function(){
		total = Number(total) + Number($(this).val());
	});
	parent.topcontent.setTotalAmount(total);
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
//	if("返利订货"==type){
//		btnDisable(["delete","edit"]);
//	}
}

function url(SPCL_TECH_ID,PRICE){
//	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

function oppenAdvcDtlPage(obj){
	var url = $(obj).attr("url");
	window.open(url,"关联预订单","dialogwidth=800px; dialogheight=600px; status=no");
}


