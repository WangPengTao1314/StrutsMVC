/**
 *@module 渠道管理-装修管理
 *@func   装修整改验收单维护
 *@version 1.1
 *@author zcx
 */
 //var selRowId = parent.document.getElementById("selRowId").value;
$(function(){
	 //主从及主从从列表页面通用加载方法
	listPageInit("rnvtnreformcheck.shtml?action=toList&module="+document.getElementById("module").value);
	//新增和修改按钮初始化
    $("#modify").click(function(){
        window.parent.location="rnvtnreformcheck.shtml?action=toEdit&RNVTN_REFORM_ID="+$("#selRowId").val(); 
	});
     
	$("#delete").click(function(){
	 	 deletecheck("rnvtnreformcheck.shtml?action=delete","RNVTN_REFORM_ID");
	});
	//确认
	$("#commRecv").click(function(){
	    commRecvcheck("rnvtnreformcheck.shtml?action=commRecv","RNVTN_REFORM_ID");
	});
    //生成整改单
    $("#generate").click(function(){
        generatecheck("rnvtnreformcheck.shtml?action=generatecheck","RNVTN_CHECK_ID");
    })
});

function generatecheck(urk,pkId){
   var selRowId = $("#selRowId").val();
   if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
   } 
   var goUrl = $("#pageForm").attr("action"); 
   var child = parent.bottomcontent.document.getElementById("eid1");
   var url = "rnvtnreformcheck.shtml?action=generatecheck";
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
  
   var deleteUrl = "rnvtnreformcheck.shtml?action=commRecv";
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

function deletecheck(url,pkId){
   var selRowId = $("#selRowId").val();
   if(null == selRowId || "" == selRowId){s
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
   } 
   var goUrl = $("#pageForm").attr("action"); 
   
   var child = parent.bottomcontent.document.getElementById("eid1");
  
   var deleteUrl = "rnvtnreform.shtml?action=delete";
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
			    var deleteUrl = "rnvtncheck.shtml?action=delete";
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

 function state(params){
    var state = params.value;
    //按钮状态重置
	btnReset();
	//当状态=验收完成
	if(state == "重新整改"){
	  btnDisable(["modify"]);
	}
	if (state == "整改通过") {
		btnDisable(["modify"]);
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
				parent.window.topcontent.location=goUrl;
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
