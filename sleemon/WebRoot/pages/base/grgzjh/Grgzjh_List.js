/**
 * @prjName:系统管理——个人工作计划维护
 * @fileName:Grgzjh_List
 * @author wujun
 * @time   2014-12-14 09:42:25 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("grgzjh.shtml?action=toList&module=" + module);
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("grgzjh.shtml?action=delete", "PER_WORK_PLAN_ID");
	 //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit","revoke","new"]);
	
	$("#add").click(function(){
		 var selRowId = $("#selRowId").val();
	 	 window.parent.location="grgzjh.shtml?action=toEditFrame&module="+getModule()+"&selRowId="+selRowId; 
	 });
	 
	 $("#new").click(function(){
	     var selRowId = $("#selRowId").val();
	 	 window.parent.location="grgzjh.shtml?action=toEditFrame&module="+getModule(); 
	 });
	
    $("#simpleSelComm").click(function(){
     		selCommon('System_0', false, 'XM', 'XM', 'queryForm','RYXXID,XM', 'RYXXID,XM', '')
    });

 
    $("#reset").click(function(){
        $.each($("#queryForm input[reset='true']"),function(index,obj){
            $(obj).val("");
        });
    });
     
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
			toFlow("3");
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
		
});

function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/grgzjh.shtml?action=toList"+document.getElementById("paramUrl").value ;
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

 
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
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
				btnDisable(["delete","modify","commit","add"]);
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
			btnDisable(["delete","modify","revoke","commit","audit","add"]);
		} 
 }