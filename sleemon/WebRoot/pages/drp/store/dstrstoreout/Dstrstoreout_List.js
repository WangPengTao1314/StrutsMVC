/**
 * @prjName:喜临门营销平台
 * @fileName:Storeout_List
 * @author chenj
 * @time   2013-09-15 14:59:50 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
    var billType=parent.window.document.getElementById("BILL_TYPE").value;
	//主从及主从从列表页面通用加载方法
	listPageInit("dstrstoreout.shtml?action=toList&module=" + module+"&BILL_TYPE="+billType);
	$("#done").click(function(){
	  doneCommit();
	});
	setSelOperateEx();
});


//记录按钮根据状态控制
  function setSelOperateEx(){
	var selRowId = parent.document.getElementById("selRowId").value;
	if(selRowId==''){
		btnDisable(["done"]);
	}else{
		var vstate=parent.topcontent.document.getElementById("state" + selRowId);
		var state = vstate.value;
			//按钮状态重置
		btnReset();
		if(state == "已处理"){
			btnDisable(["done"]);
		}
	}
 }

 //当主表的库位管理标记未‘1’的时候，
 //下面明细的页签显示：入库单明细，入库单库位明细，详细信息；
 function childType(STORAGE_FLAG) {
	 if(1 != STORAGE_FLAG) {
		var obj =  $(window.parent.document).find("#label_2") ;
		$(obj).hide(); 
		var showLabel = parent.$("#showLabel").val();
		if("label_2" == showLabel){
			parent.$("#showLabel").val("label_1");
		}
//		parent.window.gotoBottomPage("label_1");
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
 
 //处理前检查
function doneCommit() {
	checkRealNum();
	parent.showConfirm("您确认要进行出库操作？点击确认则继续","topcontent.listCommitConfirm();");
}
 //如果出库数理为0 不能点出库
function checkRealNum(){
	
}

//处理出库
function listCommitConfirm(){
	var billType=parent.window.document.getElementById("BILL_TYPE").value;
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "dstrstoreout.shtml?action=toCommit&STOREOUT_ID="+utf8(selRowId)+"&BILL_TYPE="+billType,
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("处理成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
