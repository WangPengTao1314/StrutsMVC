/**
 *@module 基础数据
 *@func 生产基地维护
 *@version 1.0
 *@author 王志格
 */

$(function(){
	
	//页面初始化
	listPageInit("shipPoint.shtml?action=toList");
	
	//单表新增和修改按钮初始化
	mtbAddModiInit();
	
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("shipPoint.shtml?action=delete","SHIP_POINT_ID");
	
	var selRowId = $("#selRowId").val();
    if(selRowId == null || '' == selRowId){
        btnDisable(["start","modify","delete","stop"]);
	      return;
    }
	// 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
       var selRowId = parent.document.getElementById("selRowId").value;
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   $.ajax({
		url: "shipPoint.shtml?action=changeState&SHIP_POINT_ID="+selRowId,
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				$("#state"+selRowId).html(utf8Decode(jsonResult.messages));
				$("#state"+selRowId).val(utf8Decode(jsonResult.messages));
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
	   var selRowId = parent.document.getElementById("selRowId").value;
	   
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   $.ajax({
		url: "shipPoint.shtml?action=changeState&SHIP_POINT_ID="+selRowId,
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
	
});	

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
//function load(){
//	//下帧页面跳转
//	$("#bottomcontent").attr("src","shipPoint.shtml?action=toDetail&SHIP_POINT_ID=XCVSA");
//}