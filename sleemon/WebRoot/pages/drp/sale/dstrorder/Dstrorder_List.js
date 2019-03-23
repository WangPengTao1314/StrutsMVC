/**
 * @prjName:喜临门营销平台
 * @fileName:Dstrorder_List
 * @author glw
 * @time   2013-08-11 17:28:09 
 * @version 1.1
 */
$(function(){
	$("#audit").hide();
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("dstrorder.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("dstrorder.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("dstrorder.shtml?action=delete", "DSTR_ORDER_ID");
	// 接收
	$("#start").click(function(){
	   //获取当前选中的记录
	   var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   parent.showConfirm("是否确认接收？","topcontent.listReceivedConfirm();","N");
	});
	$("#moveAdv").click(function(){
		var selRowId = parent.document.getElementById("selRowId").value;
		audit(selRowId);
	})
	$("#close").click(function(){
		$("#STOREOUT_STORE_ID").val("");
		$("#STOREOUT_STORE_NO").val("");
		$("#STOREOUT_STORE_NAME").val("");
		$("#audit").hide();
	})
});

//记录按钮根据状态控制
 function setSelOperateEx(obj){
	var selRowId = $("#selRowId").val();
	var state =  $("#state"+selRowId).val();
	//按钮状态重置
	btnReset();
	if(state == "已接收"){
		btnDisable(["start"]);
	}
 }

//接收
function listReceivedConfirm(){
	closeWindow();
	$("#audit").show();
}
function audit(selRowId){
	var STOREOUT_STORE_ID= $("#STOREOUT_STORE_ID").val();
	if(""==STOREOUT_STORE_ID||null==STOREOUT_STORE_ID){
		alert("请选择出库库房");
		return;
	}
	var STOREOUT_STORE_NO=$("#STOREOUT_STORE_NO").val();
	var STOREOUT_STORE_NAME=$("#STOREOUT_STORE_NAME").val();
	var STORAGE_FLAG=$("#STORAGE_FLAG").val();
	
	if(null == STORAGE_FLAG || "" == STORAGE_FLAG){
		STORAGE_FLAG = 0;
	}
	 $.ajax({
		url: "dstrorder.shtml?action=changeStateReceived&DSTR_ORDER_ID="+utf8(selRowId)+"&STOREOUT_STORE_ID="+utf8(STOREOUT_STORE_ID)+"&STOREOUT_STORE_NO="+utf8(STOREOUT_STORE_NO)+"&STOREOUT_STORE_NAME="+utf8(STOREOUT_STORE_NAME)+"&STORAGE_FLAG="+STORAGE_FLAG,
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("接收成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}