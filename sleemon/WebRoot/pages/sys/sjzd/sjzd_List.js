/**
  *@module 系统管理
  *@fuc 数据字典主从表一览js
  *@version 1.1
  *@author 张羽
*/
	
$(function(){
	 //主从及主从从列表页面通用加载方法
	listPageInit("sjzd.shtml?action=toList");
	//新增和修改按钮初始化
	var chiState = $.trim($("#ordertb  :input[type='radio'][checked='true']").parent().parent().find("td").eq(5).text());
	addModiToEditFrameInit("sjzd.shtml?action=toEditFrame&chistate="+utf8(chiState));
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("sjzd.shtml?action=delete","SJZDID");
	var selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      btnDisable(["modify","delete","start","stop"]);
	      return;
	   }
	// 停用
	$("#stop").click(function(){
	   
	   //获取当前选中的记录
	   selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   
	   $.ajax({
		url: "sjzd.shtml?action=stateChange&SJZDID="+utf8(selRowId)+"&BUTTON=stop",
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var state = utf8Decode(jsonResult.messages);
				$("#ordertb  :input[type='radio'][checked='true']").parent().parent().find("td").eq(5).text(state);
				btnReset();
				setBtnState(state);
				parent.showMsgPanel("状态修改成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	  // parent.bottomcontent.setButtonState();
	});
	
	// 启用
	$("#start").click(function(){
	   //获取当前选中的记录
	    selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   
	   $.ajax({
		url: "sjzd.shtml?action=stateChange&SJZDID="+utf8(selRowId)+"&BUTTON=start",
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var state = utf8Decode(jsonResult.messages);
				$("#ordertb  :input[type='radio'][checked='true']").parent().parent().find("td").eq(5).text(state);
				btnReset();
				setBtnState(state);
				parent.showMsgPanel("状态修改成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	   // parent.bottomcontent.setButtonState();
	});
})


//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var state = $.trim($(obj).find("input[id='STATE']").val());
	//设置按钮的状态
	setBtnState(state);
}
//设置按钮的状态
function setBtnState(state) {
//按钮状态重置
	btnReset();
	if (state == "启用") {
	    //启用，删除，修改按钮不可用
		btnDisable(["modify"]);
		btnDisable(["delete"]);
		btnDisable(["start"]);
	} else if(state == "停用"){
		//停用,删除按钮不可用
		btnDisable(["stop"]);
		btnDisable(["delete"]);
	} else if(state == "制定"){
		// 停用按钮不可用
		btnDisable(["stop"]);
	} 
}