/**
 * @prjName:供应链_贵人鸟
 * @fileName:Grant_List_Chld
 * @author zhuxw
 * @time   2013-05-15 10:35:31 
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
				if (state != "未提交" && state != "\u9000\u56de" && state != "\u5426\u51b3" && state != "\u64a4\u9500") {
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
		$("#CPBLTZDMXIDS").val(ids);
		$("#form1").attr("action","grant.shtml?action=toChildEdit");
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
	//选中明细记录
	chooseDtlId();

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
		url: "grant.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"CPBLTZDMXIDS":ids},
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
function chooseDtlId(){
	var selDtlId = parent.document.getElementById("selDtlId").value;
		if(null!= selDtlId && "" != selDtlId){
			var trs = $("#ordertb tr:gt(0)");
			trs.each(function(){
				var selRadio = $(this).find("input[type='checkbox']");
				var pkId = selRadio.attr("value");
				if(pkId == selDtlId){
					$(this).click();
					return;
				}
			});
	}
}