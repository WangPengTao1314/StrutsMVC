/**
 * @prjName:喜临门营销平台
 * @fileName:Saleplan_List
 * @author zcx
 * @time   2014-12-5 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
	
	//主从及主从从列表页面通用加载方法
	listPageInit("saleplan.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	//mtbDelListener("saleplan.shtml?action=delete", "OPEN_BUSS_PKG_ID");
	$("#commit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("1");
	});
	$("#revoke").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		//toFlow("3");
		parent.showConfirm("您确认撤销该条信息吗?","topcontent.toFlow(3);","N");
	});
	$("#audit").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("2");
	});
	$("#flow").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		toFlow("4");
	});
	
	 $("#modifyT").click(function(){
		 var selRowId = $("#selRowId").val();
	 	 if(null == selRowId || "" == selRowId){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
	 	 	setCommonPageInfo(true);
			parent.window.gotoBottomPage("toEdit");
	 	 }
	});
	
	$("#delete").click(function(){
	   var selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	   } 
	   parent.showConfirm("您确认删除该条信息吗?","topcontent.listDelConfirm();");
	});
	var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
      btnDisable(["modify","delete","commit","revoke","audit","flow"]);
      return;
    }
});

function orpenWindow(){
     var arguemnts = new Object();
     arguemnts.window = window;
	 window.showModalDialog("saleplan.shtml?action=toBatch",arguemnts,"dialogwidth=1400px; dialogheight=500px; status=no");
	 var url="saleplan.shtml?action=toList";
	 $("#topcontent").attr("src",url);
}

function getPlanYear(){
   var PLAN_YEAR = $("#PLAN_YEAR").val();
   $("#YEAR").val(PLAN_YEAR);
   qryformChecked('queryForm');
   parent.document.getElementById("selRowId").value="";
   $("#selRowId").val("");
   var url = "saleplan.shtml?action=toListT";
   $("#queryForm").attr("action",url+"&search=true&module="+getModule()+"&PLAN_YEAR="+PLAN_YEAR);
   $("#queryForm").submit();
   //listPageInit("saleplan.shtml?action=toList&module=" + module+"&PLAN_YEAR="+PLAN_YEAR);
}

 //删除记录
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "saleplan.shtml?action=delete&SALE_PLAN_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				$("#pageForm").submit();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function state(params){
    var state = params.value;
	var PVG_EDIT_AUDIT = $("#PVG_EDIT_AUDIT").val();
    //按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "未提交") {
		btnDisable(["revoke","audit"]);
	}
	//当状态=提交
	if (state == "提交") {
		if(1 == PVG_EDIT_AUDIT){
			btnDisable(["delete","commit"]);
		}else{
			btnDisable(["delete","modify","commit"]);
		}
	}
	//当状态=撤销
	if (state == "撤销") {
		btnDisable(["revoke","audit"]);
	}
	//当状态=否决
	if (state == "否决") {
		btnDisable(["revoke","audit"]);
	}
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "审核通过") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	} 
 }
 
 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/saleplan.shtml?action=toList"+document.getElementById("paramUrl").value ;
	switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}

function getYear(){
  var PLAN_YEAR = $("#PLAN_YEAR").val();
  $("#YEAR").val(PLAN_YEAR);
}