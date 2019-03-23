/**
 * @module 质检管理
 * @fuc 成品不良通知单
 * @version 1.1
 * @author zhuxw
 */
$(function () {
	whBtnHidden(["audit"]);
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
	listPageInit("newmasterslave.shtml?action=toList&module=" + module);
	addModiToEditFrameInit("newmasterslave.shtml?action=toEditFrame");
	mtbDelListener("newmasterslave.shtml?action=delete", "CPBLTZDID");
	//初始化 审批流程
    spflowInit("ERP_ZJGL_CPBLTZSP", "T_ERP_ZJ_CPBLTZD", "CPBLTZDID", "../sample/newmasterslave.shtml?action=toFrame&module="+module, "com.hoperun.sample.masterslave.service.impl.MasterSlaveFlowInterface", "");
    	
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
    document.affirm.sourceURI.value="../sample/newmasterslave.shtml?action=toList"+document.getElementById("paramUrl").value ;
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
//	var state = $.trim($(obj).find("td").eq(16).text());
	var selRowId = parent.document.getElementById("selRowId").value;
	if(document.getElementById("state"+selRowId)==null)
	return;
	var state =  document.getElementById("state"+selRowId).value;
	btnReset();
	
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit"]);
	}
	
	//当状态=撤销
	if (state == "\u64a4\u9500") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "\u5426\u51b3") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}

}
function setCPZJTZDID(CPZJTZDID) {
	document.getElementById("CPZJTZDID").value = CPZJTZDID;
}
