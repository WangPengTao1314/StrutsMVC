/**
 *@module 基础资料
 *@func 维护编码规则
 *@version 1.1
 *@author 孙炜
 */
 //var selRowId = parent.document.getElementById("selRowId").value;
$(function(){
	
	 //主从及主从从列表页面通用加载方法
	listPageInit("bmgz.shtml?action=toList");
	//新增和修改按钮初始化
	
	addModiToEditFrameInit("bmgz.shtml?action=toEditFrame&BMGZID="+utf8(selRowId));
	//删除监听.参数1：删除action，参数2：删除主键Id
	//mtbDelListener("bmgz.shtml?action=delete","CMLID");
	
	  var selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      btnDisable(["start","modify","delete","stop"]);
	      return;
	   }
	   
	$("#delete").click(function(){
	 	 deletecheck("bmgz.shtml?action=deletecheck","BMGZID");
	 });
	
	
	
	// 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
	   selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   
	   $.ajax({
		url: "bmgz.shtml?action=changeState&BMGZID="+utf8(selRowId),
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
				$("#pageForm").submit();
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
	   selRowId = $("#selRowId").val();
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请至少选择一条记录");
	      return;
	   }
	   
	   $.ajax({
		url: "bmgz.shtml?action=changeState&BMGZID="+utf8(selRowId),
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
				$("#pageForm").submit();
				//saveSuccess("状态修改成功","cmxx.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	});
	//设置默认日期
	$("#fromCRETIME").val(getBeforData());//前一月
	$("#toCRETIME").val(getCurrentData()); //当月
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
  
   var deleteUrl = "bmgz.shtml?action=delete";
   parent.showConfirm("您确认要删除吗?","topcontent.realDelete('"+deleteUrl+"','"+pkId+"','"+selRowId+"','"+goUrl+"');");

}

function delConfirm(actionUrl,pkId,selRowId,goUrl){
     parent.closeWindow();
	 $.ajax({
	 	url: actionUrl+"&"+pkId+"="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    var deleteUrl = "bmgz.shtml?action=delete";
			    if("confirm" == jsonResult.messages){
			       parent.showConfirm("该编码类数据包含编码明细，是否删除?","topcontent.realDelete('"+deleteUrl+"','"+pkId+"','"+selRowId+"','"+goUrl+"');");
			    } else if("delete" == jsonResult.messages) {
			       realDelete(deleteUrl,pkId,selRowId,goUrl);
			    }
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
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

function changeButton(value){
   //alert(value);
   btnReset();
   if ('启用' == value ){
      btnDisable(["start","modify","delete"]);
   }else if ('停用' == value){
      btnDisable(["stop","delete"]);
   } else if('制定' == value){
      btnDisable(["stop"]);
   }
}
