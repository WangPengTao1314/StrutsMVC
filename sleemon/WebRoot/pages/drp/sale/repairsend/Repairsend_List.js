/**
 * @prjName:喜临门营销平台
 * @fileName:Repairsend_List
 * @author chenj
 * @time   2013-11-03 16:25:12 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("repairsend.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("repairsend.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("repairsend.shtml?action=delete", "ERP_REPAIR_ORDER_ID");
	
	setSelOperateEx();
	//确认发货货
	 $("#commSend").click(function () {
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == ""){
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		window.showModalDialog('repairsend.shtml?action=toTypeWindow&selRowId='+selRowId,window,'dialogHeight=320px, dialogWidth=490px, dialogTop=500px, dialogLeft=700px, toolbar=no, menubar=no, scroll=yes, resizable=no,location=no, status=no');
	});
});

function setSelOperateEx(){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = parent.document.getElementById("selRowId").value;
	var state=parent.topcontent.document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	if(state == "总部收货确认"){
	}else{
		btnDisable(["commSend"]);
	}
 }