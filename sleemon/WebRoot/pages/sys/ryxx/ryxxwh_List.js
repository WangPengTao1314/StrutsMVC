/**
 *@module 基础数据
 *@func人员信息
 *@version 1.1
 *@author 吴亚林
 */
$(function(){
	//页面初始化
	listPageInit("ryxx.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("ryxx.shtml?action=delete","RYXXID");
	
	var ss = $("#selRowId").val();
	   if(null == ss || "" == ss){
	      btnDisable(["start","modify","delete","stop"]);
	      return;
	   }
	   
	// 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
	   var selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   //alert(selRowId + " : " +utf8(selRowId));
	   $.ajax({
		url: "ryxx.shtml?action=changeState&RYXXID="+selRowId,
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				//parent.window.gotoBottomPage("label_1");
				$("#state"+selRowId).html(utf8Decode(jsonResult.messages));
				$("#state"+selRowId).val(utf8Decode(jsonResult.messages));
				changeButton(utf8Decode(jsonResult.messages));
				parent.window.gotoBottomPage("label_1");
				//saveSuccess("状态修改成功","cmxx.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	});
	
	// 启用
	$("#start").click(function(){
	   //获取当前选中的记录
	   var selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   
	   $.ajax({
		url: "ryxx.shtml?action=changeState&RYXXID="+utf8(selRowId),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				//parent.window.gotoBottomPage("label_1");
				$("#state"+selRowId).html(utf8Decode(jsonResult.messages));
				$("#state"+selRowId).val(utf8Decode(jsonResult.messages));
				changeButton(utf8Decode(jsonResult.messages));
				parent.window.gotoBottomPage("label_1");
				//saveSuccess("状态修改成功","cmxx.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	});
	
});	


 function setSelOperateEx(obj){
	var value =  $.trim($(obj).find("td[json='STATE']").text());
	//按钮状态重置
   btnReset();
     if ('启用' == value ){
     btnDisable(["start","delete"]);
   }else if ('制定' == value){
      btnDisable(["stop"]);
   } else if('停用' == value ){
      btnDisable(["stop","delete"]);
   }else {
      btnDisable(["start","stop"]);
   }
 }
function changeButton(value){
	 btnReset();
     if ('启用' == value ){
     btnDisable(["start","modify","delete"]);
   }else if ('制定' == value){
      btnDisable(["stop"]);
   } else if('停用' == value ){
      btnDisable(["stop","delete"]);
   }else {
      btnDisable(["start","stop"]);
   }
}