
/**
 *@module 分销业务
 *@func人员信息维护
 *@version 1.1
 *@author lyg
 */
$(function(){
	//页面初始化
	listPageInit("employee.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	btnState();
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("employee.shtml?action=delete","RYXXID");
	
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
	   $.ajax({
		url: "employee.shtml?action=changeState&RYXXID="+utf8(selRowId)+"&tab=stop",
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				changeButton(utf8Decode(jsonResult.messages));
				$("#pageForm").submit();
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
		url: "employee.shtml?action=changeState&RYXXID="+utf8(selRowId)+"&tab=start",
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				changeButton(utf8Decode(jsonResult.messages));
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	});
	
	 $("#defaultP").click(function(){
	    var ryxxid = $("input:radio[name='eid']:checked").val();
	    var actionUrl = "employee.shtml?action=updatePassword";
	    var goUrl="employee.shtml?action=toList";
	    showConfirm("您确认要设为默认密码吗?","mtbDelConfirm('"+actionUrl+"','"+ryxxid+"','"+ryxxid+"','"+goUrl+"');");
	 });
});	

function mtbDelConfirm(actionUrl,pkId,selRowId,goUrl){
	closeWindow();
	$.ajax({
	        url:actionUrl+"&ryxxid="+selRowId,
            type:"POST",
			dataType:"text",
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
			    if(jsonResult.success===true){
					parent.showMsgPanel("更新成功");
					window.parent.topcontent.location="employee.shtml?action=toList";
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
	    });
}


 function setSelOperateEx(obj){
	var value =  $.trim($(obj).find("td[json='STATE']").text());
	//按钮状态重置
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
     btnState();
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
     btnState();
}
function btnState(){
	var selRowId = $("#selRowId").val();
	var rylb=$("#rylb"+selRowId).val();
	if("加盟商管理员"==rylb){
		btnDisable(["modify","delete","start","stop"]);
		return;
	}
}
function getState(){
	var selRowId = $("#selRowId").val();
	return $("#rylb"+selRowId).val();
}