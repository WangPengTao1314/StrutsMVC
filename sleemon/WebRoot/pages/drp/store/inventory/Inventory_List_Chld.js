/**
 * @prjName:喜临门营销平台
 * @fileName:Inventory_List_Chld
 * @author lyg
 * @time   2013-09-07 09:54:59 
 * @version 1.1
 */
$(function(){
	//审核页面需要隐藏的按钮
	shBtnHidden(["edit","delete","download","uploading"]);
    $("#delete").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == null || "" == selRowId) {
			parent.showErrorMsg("\u4e3b\u8868\u6ca1\u6709\u8bb0\u5f55");
			return;
		}
		var state = parent.topcontent.document.getElementById("state" + selRowId).value;
//		if (state != "未提交" && state != "\u9000\u56de" && state != "\u5426\u51b3" && state != "\u64a4\u9500") {
//			parent.showErrorMsg("当前主表状态下，不能删除明细！");
//			return;
//		}
		if(!checkFlag()){
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
    $("#save").click(function(){
    	var checkBox = $("#ordertb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		save();
    })
    $("#uploading").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		parent.topcontent.$("#type").val("");
		parent.topcontent.$("#tempUp").hide();
		parent.topcontent.$("#upFile").show();
	});
	$("#download").click(function () {
			var selRowId = parent.document.getElementById("selRowId").value;
			if (selRowId == "") {
				parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
				return;
			}
			download();
		});
	$("#edit").click(function(){
//        var checkBox = $("#ordertb tr:gt(0) input:checked");
//		var ids = "";
//		if(checkBox.length>0){
//			//获取所有选中的记录
//			checkBox.each(function(){
//				ids = ids+"'"+$(this).val()+"',";
//			});
//			ids = ids.substr(0,ids.length-1);
//		}
//		$("#PRD_INV_DTL_IDS").val(ids);
		$("#form1").attr("action","inventory.shtml?action=toChildEdit");
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
//		countNum();
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
		url: "inventory.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"PRD_INV_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				$("#pageForm").submit();
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
		//当状态=提交,当状态=回退,当状态=审核通过
		if (state == "\u63d0\u4ea4"||state == "退回"||state == "\u5ba1\u6838\u901a\u8fc7") {
			btnDisable(["delete","edit","save"]);
			$("input[name='INV_NUM']").attr("readonly","readonly");
		}
		if(""==state||null==state){
			btnDisable(["delete","edit","save"]);
		}
		if(state != "开始盘点"&&state!="结束盘点"){
			btnDisable(["download","uploading","edit"]);
		}
	}
}
//获取上贞的盘点范围，只有盘点范围为库房盘点的时候 才不隐藏明细的库位编号和名称
function hideStorg(){
	var INV_RANGE=parent.topcontent.getINV_RANGE();
	var STORG_NO=$("#STORG_NO");
	var STORG_NAME=$("#STORG_NAME");
	if(INV_RANGE!="库位盘点"){
		STORG_NO.hide();
		STORG_NAME.hide();
		$("#ordertb tr td[name='STORG']").each(function(){
			$(this).hide();
		})
	}else{
		STORG_NO.show();
		STORG_NAME.show();
		$("#ordertb tr td[name='STORG']").each(function(){
			$(this).show();
		})
	}
}

function url(SPCL_TECH_ID){
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}
function checkFlag(){
	 var checkBox = $("#ordertb tr:gt(0) input:checked");
	 var flag=true;
	 checkBox.each(function(){
		 var INS_FLAG=$(this).parent().parent().find("input[name='INS_FLAG']").val();
		 if(1==INS_FLAG){
			 parent.showMsgPanel("所选明细存在非手工新增明细，不能删除");
			 flag=false;
			 return;
		 }
	 })
	 return flag;
}
//导出
function download(){
	var selRowId = parent.document.getElementById("selRowId").value;
	window.location="inventory.shtml?action=ExcelOutput&PRD_INV_ID="+utf8(selRowId);
}
function save(){
	if(!checkNum()){
		return;
	}
	parent.showWaitPanel();
	var jsonData = multiPackJsonData("ordertb");
	 $.ajax({
		url: "inventory.shtml?action=childEditToList",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					$("#form1").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	 closeWindow();
}
function countDiffNum(id){
	var INV_NUM=$("#INV_NUM"+id).val();
	var ACCT_NUM=$("#ACCT_NUM"+id).html();
	if(""==ACCT_NUM||null==ACCT_NUM){
		ACCT_NUM=0;
	}
	if(""==INV_NUM||INV_NUM==null){
		INV_NUM=0;
	}
	INV_NUM=isNaN(INV_NUM)?0:parseFloat(INV_NUM);
	ACCT_NUM=isNaN(ACCT_NUM)?0:parseFloat(ACCT_NUM);
	var diffNum=Math.round((isNaN(INV_NUM-ACCT_NUM)?0:INV_NUM-ACCT_NUM),2);
	$("#DIFF_NUM"+id).html(diffNum);
//	countNum();
}
//验证盘点数量为数字
function checkNum(){
	var checkBox = $("#ordertb tr:gt(0) input:checked");
	var flag = true;
	checkBox.each(function(){
		var INV_NUM = $(this).parent().parent().find("input[name='INV_NUM']").val();//盘点数量
		if(""==INV_NUM||null==INV_NUM){
			parent.showErrorMsg("请输入盘点数量");
            flag=false;
			return false;
		}
		var re1 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
        if(!re1.test(INV_NUM)){
            parent.showErrorMsg("盘点数量最多可输入8位正整数");
            flag=false;
			return false;
        }
	});
	return flag;
}
function countNum(){
	var ACCT_NUM=0;//账面数量
	$("td[name='ACCT_NUM']").each(function(){
		var tempNum=$.trim($(this).html());
		if(""==tempNum||null==tempNum){
			tempNum=0;
		}
		tempNum=isNaN(tempNum)?0:parseFloat(tempNum);
		ACCT_NUM=ACCT_NUM+tempNum;
	})
	$("#ACCT_NUM").val(ACCT_NUM);
	
	
	var INV_NUM=0;//盘点数量
	$("input[name='INV_NUM']").each(function(){
		var tempNum=$.trim($(this).val());
		if(""==tempNum||null==tempNum){
			tempNum=0;
		}
		tempNum=isNaN(tempNum)?0:parseFloat(tempNum);
		INV_NUM=INV_NUM+tempNum;
	})
	$("#INV_NUM").val(INV_NUM);
	
	var DIFF_NUM=0;//差异数量
	$("td[name='DIFF_NUM']").each(function(){
		var tempNum=$.trim($(this).html());
		if(""==tempNum||null==tempNum){
			tempNum=0;
		}
		tempNum=isNaN(tempNum)?0:parseFloat(tempNum);
		DIFF_NUM=DIFF_NUM+tempNum;
	})
	$("#DIFF_NUM").val(DIFF_NUM);
}