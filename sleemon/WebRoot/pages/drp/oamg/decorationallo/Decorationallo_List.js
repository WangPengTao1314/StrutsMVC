/**
 *@module 渠道管理-装修管理
 *@func   装修补贴标准维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	 
	 //主从及主从从列表页面通用加载方法
	listPageInit("decorationallo.shtml?action=toList&module="+document.getElementById("module").value);
	$("#add").click(function(){
	    window.parent.location="decorationallo.shtml?action=toEditFrame"; 
	});
    $("#modify").click(function(){
        window.parent.location="decorationallo.shtml?action=toEditFrame&RNVTN_SUBST_STD_ID="+$("#selRowId").val(); 
	});
	//删除监听.参数1：删除action，参数2：删除主键Id
	//mtbDelListener("bmgz.shtml?action=delete","CMLID");
	var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
      btnDisable(["start","modify","delete","stop"]);
      return;
    }
	   
	$("#delete").click(function(){
	 	 deletecheck("decorationallo.shtml?action=delete","RNVTN_SUBST_STD_ID");
	 });
	
	// 停用
	$("#stop").click(function(){
	   
	   //获取到当前品牌的信息
	   var brand = $("#brand").val();
	   //获取当前选中的记录
	   selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   $.ajax({
		url: "decorationallo.shtml?action=changeStateT&RNVTN_SUBST_STD_ID="+$("#selRowId").val()+"&BRAND="+utf8(brand),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("状态修改成功","decorationallo.shtml?action=toFrames");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	});
	
	// 启用
	$("#start").click(function(){
	   //获取到当前品牌的信息
	   var brand = $("#brand").val();
	   //获取当前选中的记录                     
	   selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   $.ajax({
		url: "decorationallo.shtml?action=changeState&RNVTN_SUBST_STD_ID="+$("#selRowId").val()+"&BRAND="+utf8(brand),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("状态修改成功","decorationallo.shtml?action=toFrames");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  }); 
	});
});

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

function deletecheck(url,pkId){
   var selRowId = $("#selRowId").val();
   if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
   } 
   var goUrl = $("#pageForm").attr("action"); 
   var child = parent.bottomcontent.document.getElementById("eid1");
   var deleteUrl = "decorationallo.shtml?action=delete";
   parent.showConfirm("您确认要删除吗?","topcontent.realDelete('"+deleteUrl+"','"+pkId+"','"+selRowId+"','"+goUrl+"');");

}

 function state(params){
    var state = params.value;
    var selRowId = $("#selRowId").val();
    if(state == "制定"){
    	btnDisable(["stop"]);
    }
    if(state == '启用'){
	   btnDisable(["modify","delete","start"]);
	}
	if(state == '停用'){
	   btnDisable(["delete","stop"]);
	}
	if(selRowId == null || '' == selRowId){
	        btnDisable(["modify","delete"]);
			return;
    } 
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
				  $("#pageForm").submit();
			}else{
				  parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function deletecheck(url,pkId){
   var selRowId = $("#selRowId").val();
   if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
   } 
   var goUrl = $("#pageForm").attr("action"); 
   var child = parent.bottomcontent.document.getElementById("eid1");
   var deleteUrl = "decorationallo.shtml?action=delete";
   parent.showConfirm("您确认要删除吗?","topcontent.realDelete('"+deleteUrl+"','"+pkId+"','"+selRowId+"','"+goUrl+"');");

}

function changeButton(value){
   btnReset();
   if ('启用' == value ){
      btnDisable(["start","modify","delete"]);
   }else if ('停用' == value){
      btnDisable(["stop","delete"]);
   } else if('制定' == value){
      btnDisable(["stop"]);
   }
}
