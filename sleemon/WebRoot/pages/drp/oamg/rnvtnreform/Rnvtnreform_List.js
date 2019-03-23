/**
 *@module 渠道管理-装修管理
 *@func   装修整改单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	 //主从及主从从列表页面通用加载方法
	listPageInit("rnvtnreform.shtml?action=toList&module="+document.getElementById("module").value);
	//新增和修改按钮初始化
	
	$("#add").click(function(){
	    window.parent.location="rnvtnreform.shtml?action=toEditFrame"; 
	});
	
    $("#modify").click(function(){
        window.parent.location="rnvtnreform.shtml?action=toEditFrame&RNVTN_REFORM_ID="+$("#selRowId").val(); 
	});
	
	//删除监听.参数1：删除action，参数2：删除主键Id
	//mtbDelListener("bmgz.shtml?action=delete","CMLID");
	   
	$("#delete").click(function(){
	 	 deletecheck("rnvtnreform.shtml?action=delete","RNVTN_REFORM_ID");
	 });
	 
	 //确认
	$("#commRecv").click(function(){
	    commRecvcheck("rnvtnreform.shtml?action=commRecv","RNVTN_REFORM_ID");
	});
    //生成整改单
    $("#generate").click(function(){
        generatecheck("rnvtnreform.shtml?action=generatecheck","RNVTN_REFORM_ID");
    });
    
    var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
      btnDisable(["modify","delete","commRecv"]);
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
   var url = "rnvtnreform.shtml?action=generatecheck";
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
  
   var deleteUrl = "rnvtnreform.shtml?action=commRecv";
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
	//当状态=发起整改
	if (state == "已整改") {
		btnDisable(["delete","modify","commRecv"]);
	}
	//当状态=验收完成
	if (state == "发起整改") {
		btnDisable(["delete","modify"]);
	}
	
 }

function delConfirm(actionUrl,pkId,selRowId,goUrl){
     parent.closeWindow();
	 $.ajax({
	 	url: actionUrl+"&"+pkId+"="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    var deleteUrl = "rnvtnreform.shtml?action=delete";
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

function deletecheck(url,pkId){
   var selRowId = $("#selRowId").val();
   if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
   } 
   var goUrl = $("#pageForm").attr("action"); 
   
   var child = parent.bottomcontent.document.getElementById("eid1");
  
   var deleteUrl = "rnvtnreform.shtml?action=delete";
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
