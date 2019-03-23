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
	listPageInit("serstoreouthd.shtml?action=toList&module=" + module+"&BILL_TYPE="+billType);
	$("#done").click(function(){
	  doneCommit();
	});
	setSelOperateEx();
	$("#print").click(function (){
		var selRowId = parent.document.getElementById("selRowId").value;
		if (selRowId == "") {
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		print(selRowId);
	})
	
	$("#cancel").click(function(){
	    cancle_commit();
	});
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
		}else if(state == "已取消"){
			btnDisable(["done","cancel"]);
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
	if(!checkSN()){
		parent.showErrorMsg(utf8Decode('还有未扫码的货品,请进行扫码！'));
		return;
	}
	parent.showConfirm("您确认要进行出库操作？点击确认则继续","topcontent.listCommitConfirm();");
}
 //检查扫码,无扫码不能出库
function checkSN(){
	var flg = true;
	var tabObj = parent.bottomcontent.$("#ordertb tr:gt(0)");
	tabObj.each(function(){
		var sn =  $.trim($(this).find("td[json='SN']").text());
		if(sn == '' || sn == null){
			flg = false;
			return;
		}
	});
	return flg;
}

//启用按钮的
function listCommitConfirm(){
	var billType=parent.window.document.getElementById("BILL_TYPE").value;
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "serstoreouthd.shtml?action=toCommit&STOREOUT_ID="+utf8(selRowId)+"&BILL_TYPE="+billType,
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
function print(selRowId){
	//跳转扫码打印页面
	$("#printIframe").attr("src","serstoreouthd.shtml?action=printInfo&STOREOUT_ID="+selRowId);
}


 
 //处理前检查
function cancle_commit() {
	parent.showConfirm("确定取消吗？","topcontent.cancleConfim();");
}


function cancleConfim(){
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "serstoreouthd.shtml?action=cancleCommit&STOREOUT_ID="+utf8(selRowId),
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