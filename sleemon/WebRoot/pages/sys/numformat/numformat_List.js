
$(function () {

	
	//页面初始化
	listPageInit("numformat.shtml?action=toList");
	//新增和修改按钮初始化
	mtbAddModiInit();
	//删除监听.参数1：删除action，参数2：删除主键Id
	
    var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
			  btnDisable(["start","modify","delete","stop"]);
	 	 	  return;
	 	 } 
	$("#delete").click(function(){
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("您确认删除吗?","topcontent.listDelConfirm();");
	 });
	
	// 停用
	$("#stop").click(function(){
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("将停用该小数位设置，是否继续?","topcontent.listStopConfirm();");
	});
	
	// 启用
	$("#start").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("将启用该小数位设置，是否继续?","topcontent.listStartConfirm();");

	});
});

 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state =  $.trim($(obj).find("td").eq(9).text());
	//按钮状态重置
	btnReset();

	if(state == "启用"){
		btnDisable(["start","modify","delete"]);
	}else if(state == "停用"){
		btnDisable(["stop","delete"]);
	}else if (state == "制定"){
		btnDisable(["stop"]);
	}
 }
 
 
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "numformat.shtml?action=delete&NUMFORMATID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				//删除树节点
				//parent.leftcontent.deleteNodes(selRowId);
				//当前树选中节点
				//var selNodesId = parent.leftcontent.getSelNodesId();
				window.location="numformat.shtml?action=toList&NUMFORMATID="+selNodesId;
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function listStopConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
		url: "numformat.shtml?action=changeState&NUMFORMATID="+utf8(selRowId)+"&flag=0",
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				window.location.href="numformat.shtml?action=toList&NUMFORMATID="+selRowId;
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}

function listStartConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
    $.ajax({
	 url: "numformat.shtml?action=changeState&NUMFORMATID="+utf8(selRowId)+"&flag=1",
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){

			parent.showMsgPanel("状态修改成功");
			window.location.href="numformat.shtml?action=toList&NUMFORMATID="+selRowId;
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
