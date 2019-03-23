/**
 *@module 渠道管理-装修管理
 *@func   装修验收单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	 //主从及主从从列表页面通用加载方法
	listPageInit("rnvtncheck.shtml?action=toList&module="+document.getElementById("module").value);
	//新增和修改按钮初始化
	$("#add").click(function(){
	    window.parent.location="rnvtncheck.shtml?action=toEditFrame"; 
	});
    $("#modify").click(function(){
        window.parent.location="rnvtncheck.shtml?action=toEditFrame&RNVTN_CHECK_ID="+$("#selRowId").val(); 
	});
	//删除监听.参数1：删除action，参数2：删除主键Id
	   
	$("#delete").click(function(){
	 	 deletecheck("rnvtncheck.shtml?action=delete","RNVTN_CHECK_ID");
	 });
	 
	 //确认
	$("#commRecv").click(function(){
	    commRecvcheck("rnvtncheck.shtml?action=commRecv","RNVTN_CHECK_ID");
	});
    //生成整改单
    $("#generate").click(function(){
        generatecheck("rnvtncheck.shtml?action=generatecheck","RNVTN_CHECK_ID");
    })
    
    var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
      btnDisable(["start","modify","delete","commRecv","generate"]);
      return;
    }
});


function generatecheck(urk,pkId){
   var selRowId = $("#selRowId").val();
   if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
   } 
   var goUrl = $("#pageForm").attr("action"); 
   var child = parent.bottomcontent.document.getElementById("eid1");
   var url = "rnvtncheck.shtml?action=generatecheck";
   parent.showConfirm("是否生成整改单?","topcontent.realgenerate('"+url+"','"+pkId+"','"+selRowId+"','"+goUrl+"');");
   
}

function commRecvcheck(urk,pkId){
   var selRowId = $("#selRowId").val();
   if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
   } 
   var goUrl = $("#pageForm").attr("action"); 
   
   var child = parent.bottomcontent.document.getElementById("eid1");
  
   var deleteUrl = "rnvtncheck.shtml?action=commRecv";
   parent.showConfirm("是否要确认?","topcontent.realcommRecv('"+deleteUrl+"','"+pkId+"','"+selRowId+"','"+goUrl+"');");
   
}

function realcommRecv(actionUrl,pkId,selRowId,goUrl){
  parent.closeWindow();
    $.ajax({
	 	url: actionUrl+"&"+pkId+"="+utf8(selRowId),
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("确认成功");
				parent.window.topcontent.location=goUrl;
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function realgenerate(actionUrl,pkId,selRowId,goUrl){
    parent.closeWindow();
    $.ajax({
	 	url: actionUrl+"&"+pkId+"="+utf8(selRowId),
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("整改单生成成功");
				parent.window.topcontent.location=goUrl;
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var state = $.trim($(obj).find("td").eq(10).text());
	//按钮状态重置
	btnReset();
	if(state == "未提交"){
	   //撤销按钮不可用
		btnDisable(["revoke"]);
	}else if(state == "提交"){
		//提交按钮不可用
		btnDisable(["commit"]);
	}
}

 function state(params){
    var state = params.value;
    //按钮状态重置
	btnReset();
	//当状态=验收完成
	if(state == "验收完成"){
	  btnDisable(["delete","modify","commRecv"]);
	}
	if (state == "发起整改") {
		btnDisable(["delete","modify","commRecv","generate"]);
	}
	if (state == "制定") {
		btnDisable(["generate"]);
	}
 }

function deletecheck(url,pkId){
   var selRowId = $("#selRowId").val();
   if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
   } 
   var goUrl = $("#pageForm").attr("action"); 
   
   var child = parent.bottomcontent.document.getElementById("eid1");
  
   var deleteUrl = "rnvtncheck.shtml?action=delete";
   parent.showConfirm("您确认要删除吗?","topcontent.realDelete('"+deleteUrl+"','"+pkId+"','"+selRowId+"','"+goUrl+"');");

}

function realDelete(actionUrl,pkId,selRowId,goUrl){
    parent.closeWindow();
    $.ajax({
	 	url: actionUrl+"&"+pkId+"="+utf8(selRowId),
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				parent.window.topcontent.location=goUrl;
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

