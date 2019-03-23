/**
 * @prjName:喜临门营销平台
 * @fileName:Storeout_List
 * @author chenj
 * @time   2013-09-15 14:59:50 
 * @version 1.1
 */
$(function(){
	init();
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("storeoutconfirm.shtml?action=toList&module=" + module);
	setSelOperateEx();
	$("#verify").click(function(){
		verify();
	})
});

//记录按钮根据状态控制
 function setSelOperateEx(){
	var selRowId = parent.document.getElementById("selRowId").value;
	if(selRowId==''){
		btnDisable(["verify"]);
	}else{
		var vstate=parent.topcontent.document.getElementById("state" + selRowId);
		var state = vstate.value;
			//按钮状态重置
		btnReset();
		if(state == "1"){
			btnDisable(["verify"]);
		}
	}
 }
function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
}
 //当主表的库位管理标记未‘1’的时候，
 //下面明细的页签显示：入库单明细，入库单库位明细，详细信息；
 function childType(STORAGE_FLAG) {
	 if(1 != STORAGE_FLAG) {
		var obj =  $(window.parent.document).find("#label_2") ;
		$(obj).hide(); 
		parent.window.gotoBottomPage("label_1");
	 } else {
		var obj =  $(window.parent.document).find("#label_2") ;
		$(obj).show(); 
	 }
	 parent.window.document.getElementById("STORAGE_FLAG").value = STORAGE_FLAG;
 }
 
 //设置选中记录的库房ＩＤ
 function setStoreId(storeId) {
	 parent.window.document.getElementById("STOREOUT_STORE_ID").value = storeId;
 }
//收货确认
function verify(){
	var selRowId = parent.document.getElementById("selRowId").value;
	if(""==selRowId||null==selRowId){
		parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
		return;
	}
	window.open('storeoutconfirm.shtml?action=getChld&STOREOUT_ID='+utf8(selRowId),'','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
//	window.showModalDialog("storeoutconfirm.shtml?action=getChld&STOREOUT_ID="+utf8(selRowId),window,"dialogwidth=800px; dialogheight=600px; status=no");
}
function listRef(){
	 $("#pageForm").submit();
 }

