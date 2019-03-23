/**
 * @prjName:喜临门营销平台
 * @fileName:Storediff_List
 * @author wzg
 * @time   2013-08-30 14:03:21 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("storediffaff.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("storediffaff.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("storediffaff.shtml?action=delete", "STOREIN_DIFF_ID");
	
	/*$("#commit").click(function(){
		var selRowId = $("#selRowId").val();
		if(null == selRowId || "" == selRowId){
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		$.ajax({
		url: "storediffaff.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"STOREIN_DIFF_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("提交成功");
				parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	});*/
	
	$("#over").click(function(){
		var selRowId = $("#selRowId").val();
		if(null == selRowId || "" == selRowId){
			parent.showErrorMsg("\u8bf7\u9009\u62e9\u4e00\u6761\u6570\u636e");
			return;
		}
		$.ajax({
		url: "storediffaff.shtml?action=over",
		type:"POST",
		dataType:"text",
		data:{"STOREIN_DIFF_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("操作成功");
				parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	});
	
});
function setSelOperateEx(trObj,data){
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态已生效
	if (state == "退回" || state == "完成"||state=="待入库方确认") {
		btnDisable(["commit","reback"]);
	}
	if(state == "完成"||state=="待入库方确认"){
		btnDisable(["commit","reback"]);
	}
	
}

 //退回
 function reback(){
	 var selRowId = $("#selRowId").val();
	  if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
 	 } 
 	 var goUrl = $("#pageForm").attr("action"); 
 	 parent.showConfirm("您确认要退回吗?","topcontent.mainSubmit('"+selRowId+"','reback');");
 }
 
 //确认
 function confirm(){
	 var selRowId = $("#selRowId").val();
	  if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
 	 } 
 	 var goUrl = $("#pageForm").attr("action"); 
 	 parent.showConfirm("您确认要确认吗?","topcontent.mainSubmit('"+selRowId+"','confirm');");
 }
 
 //主体提交方法
 function mainSubmit(selRowId,op){
 	var actionUrl = "";
 	if(op==="reback"){
 		actionUrl = "storediffaff.shtml?action=txReback";
 	}
 	if(op==="confirm"){
 		actionUrl = "storediffaff.shtml?action=txConfirm";
 	}
 	
 	$.ajax({
        type:"POST", 
        url:actionUrl, 
        data:{"id":selRowId}, 
        complete:function (msg) {
            eval("jsonResult = "+msg.responseText);
            if(jsonResult.success === true) {
                parent.showMsgPanel("操作成功");
                pageForm.submit();
            } else {
                parent.showErrorMsg(utf8Decode(jsonResult.messages));
            }
    }});
 }
 
 function my_reset(){
 	$("#STOREIN_DIFF_NO").val('');
 	$("#BILL_TYPE").val('');
 	$("#FROM_BILL_NO").val('');
 	$("#STATE").val('');
 	$("#CRE_TIME_BEG").val('');
 	$("#CRE_TIME_END").val('');
 }