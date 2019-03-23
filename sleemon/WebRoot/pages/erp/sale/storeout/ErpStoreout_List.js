/**
 * @prjName:喜临门营销平台
 * @fileName:Storeout_List
 * @author chenj
 * @time   2013-09-15 14:59:50 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
    //主从及主从从列表页面通用加载方法
	listPageInit("erpStoreout.shtml?action=toList&module=" + module);
	 $("#expdate").click(function(){
		 $("#queryForm").attr("action","erpStoreout.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
	 $("#query").click(function(){
		 $("#STATE").append("<option value='关闭'>关闭</option>");
	 });
	 
	 $("#close").click(function(){
	     var selRowId = $("#selRowId").val();
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("您确认关闭该条信息吗?","topcontent.toClose();");
	 	  
	 });
});



 function toClose(){
	 var STOREOUT_ID = $("#selRowId").val();
	 var DELIVER_ORDER_ID = $("#DELIVER_ORDER_ID"+STOREOUT_ID).val();
	 var U9_SM_NO = $("#U9_SM_NO"+STOREOUT_ID).val();
	 $.ajax({
		url: "erpStoreout.shtml?action=toClose",
		type:"POST",
		dataType:"text",
		data:{"STOREOUT_ID":STOREOUT_ID,"DELIVER_ORDER_ID":DELIVER_ORDER_ID,"U9_SM_NO":U9_SM_NO},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 parent.showMsgPanel("关闭成功");
                 $("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
 }
 
 function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	if (state == "关闭") {
		btnDisable(["close"]);
	}
 
}