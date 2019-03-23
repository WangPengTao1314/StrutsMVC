/**
 * @module 质检管理
 * @fuc 成品不良通知单
 * @version 1.1
 * @author zhuxw
 */
$(function () {	//维护和审核调用的是同一页面，只是按钮不同，所以需要设置	//维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);	var module = parent.window.document.getElementById("module").value;	 //主从及主从从列表页面通用加载方法
	listPageInit("newmasterslave.shtml?action=toList&module=" + module);	//新增和修改按钮初始化
	addModiToEditFrameInit("newmasterslave.shtml?action=toEditFrame");	//删除监听.参数1：删除action，参数2：删除主键Id
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
}//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
//	var state = $.trim($(obj).find("td").eq(16).text());
	var selRowId = parent.document.getElementById("selRowId").value;
	if(document.getElementById("state"+selRowId)==null)
	return;
	var state =  document.getElementById("state"+selRowId).value;	//按钮状态重置
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

