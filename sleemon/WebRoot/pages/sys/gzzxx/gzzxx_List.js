/*
  *@module 系统管理
  *@func 工作组信息
  *@version 1.1
  *@author 吴亚林
  */
$(function(){
    listPageInit("gzzxx.shtml?action=toList");
	//新增和修改按钮初始化
	addModiToEditFrameInit("gzzxx.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("gzzxx.shtml?action=delete","GZZXXID");
	var selid =$.trim($("#selRowId").val());  
	 if(null == selid || "" == selid){
			  btnDisable(["start","modify","delete","stop","gzzRight"]);
	 	 	  return;
	 	 } 				   
	//工作组权限
	$("#gzzRight").click(function(){
	  
        var edID =$.trim($("#selRowId").val());  
		if(!checkEid(edID))return;
        showDialog("readxml.shtml?action=toGZZQXList&GZZXXID="+edID,580,530,"help:no;scroll:no; status:no; center:yes");
        return;
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
		url: "gzzxx.shtml?action=changeState&GZZXXID="+utf8(selRowId),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    var state = utf8Decode(jsonResult.messages);
			    $("#ordertb  :input[type='radio'][checked='true']").parent().parent().find("td").eq(4).text(state);
				parent.showMsgPanel("状态修改成功");
				//parent.window.gotoBottomPage("label_1");
				//$("#state"+selRowId).html(utf8Decode(jsonResult.messages));
				//$("#state"+selRowId).val(utf8Decode(jsonResult.messages));
				changeButton(utf8Decode(jsonResult.messages));
				$("#pageForm").submit();
				//parent.window.gotoBottomPage("label_1");
				//saveSuccess("状态修改成功","cmxx.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	});
	
	// 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
	   var selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   $.ajax({
		url: "gzzxx.shtml?action=changeState&GZZXXID="+selRowId,
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    var state = utf8Decode(jsonResult.messages);
			    $("#ordertb  :input[type='radio'][checked='true']").parent().parent().find("td").eq(4).text(state);
				parent.showMsgPanel("状态修改成功");
				//$("#state"+selRowId).html(utf8Decode(jsonResult.messages));
				//$("#state"+selRowId).val(utf8Decode(jsonResult.messages));
				changeButton(utf8Decode(jsonResult.messages));
				$("#pageForm").submit();
				//parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	});
});	
		

function checkEid(edID){		
			if(edID==null || ""==edID.trim()){
				showErrorMsg("请选择工作组！");
				return false;
			}
			return true;
}

function changeButton(value){
	 btnReset();
     if ('启用' == value ){
     btnDisable(["start","modify","delete"]);
      //$("#start").attr("disabled","true");
      //$("#stop").attr("disabled","");
      //$("#modify").attr("disabled","true");
      //$("#delete").attr("disabled","true");
   }else if ('制定' == value){
      //$("#start").attr("disabled","");
      //$("#stop").attr("disabled","true");
      //$("#modify").attr("disabled","");
      //$("#delete").attr("disabled","");
      btnDisable(["stop"]);
   } else if('停用' == value ){
   	  //$("#start").attr("disabled","");
      //$("#stop").attr("disabled","true");
      //$("#modify").attr("disabled","");
      //$("#delete").attr("disabled","true");
      btnDisable(["stop","delete"]);
   }else {
      //$("#start").attr("disabled","true");
      //$("#stop").attr("disabled","true");
      //$("#modify").attr("disabled","");
      //$("#delete").attr("disabled","");
      btnDisable(["start","stop"]);
   }
}
	