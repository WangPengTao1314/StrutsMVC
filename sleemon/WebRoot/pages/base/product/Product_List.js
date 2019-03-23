/**
 * @module 系统管理
 * @func 货品信息
 * @version 1.1
 * @author 刘曰刚
 */
$(function () {
	//页面初始化
	listPageInit("product.shtml?action=toList");
	//删除监听.参数1：删除action，参数2：删除主键Id
	//mtbDelListener("product.shtml?action=delete", "PRD_ID");
    var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
		  btnDisable(["start","modify","delete","stop"]);
	} 
	$("#delete").click(function(){
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("将删除该货品信息,您确认删除吗?","topcontent.listDelConfirm();");
	 });
	$("#add").click(function(){
		setCommonPageInfo(false);
		parent.window.gotoBottomPage("toEdit");
	});
	$("#up").click(function () {
		$("#tempUp").show()
		$("#upFile").show();
	});
	$("#modify").click(function(){
		 var selRowId = $("#selRowId").val();
	 	 if(null == selRowId || "" == selRowId){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
	 	 	setCommonPageInfo(true);
			parent.window.gotoBottomPage("toEdit");
	 	 }
	});
	$("#close").click(function (){
    	$("#upFile").hide();
    })
	$("#tempUp").click(function(){
		window.location="product.shtml?action=ExcelOutput&DRPFLAG=0";
	})
	// 停用
	$("#stop").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("将停用该货品信息，是否继续?","topcontent.listStopConfirm();");
	});
	
	// 启用
	$("#start").click(function(){
	   //获取当前选中的记录
	   if(null == selRowId || "" == selRowId){
	      parent.showErrorMsg("请选择一条记录");
	      return;
	   }
	   parent.showConfirm("将启用该货品信息，是否继续?","topcontent.listStartConfirm();");

	});
});

 function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state =  $.trim($(obj).find("td[json='STATE']").text());
	//按钮状态重置
	btnReset();
    if(state == "启用"){
		btnDisable(["start","delete"]);
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
	 	url: "product.shtml?action=delete&PRD_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				$("#pageForm").submit();
//				//删除树节点
//				parent.leftcontent.deleteNodes(selRowId);
//				//当前树选中节点
//				var selNodesId = parent.leftcontent.getSelNodesId();
//				window.location="product.shtml?action=toList&PRD_ID="+selNodesId;
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
		url: "product.shtml?action=changeStateStop&PRD_ID="+utf8(selRowId),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("状态修改成功");
				$("#pageForm").submit();
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
	 url: "product.shtml?action=changeStateStart&PRD_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel("状态修改成功");
            $("#pageForm").submit();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}

//点击是否扫码
function judgeScan(obj){
	//0->扫码，1->不扫码
	var v = $(obj).val();
	var PRD_ID = $(obj).parent().parent().find("input:radio[name='eid']").val();
	$.ajax({
        url:"product.shtml?action=updateScan",
        type:"POST",
		dataType:"text",
		data:{"PRD_ID":PRD_ID,"IS_SCAN":v},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
		    if(jsonResult.success===true){
				parent.showMsgPanel("操作成功");
                parent.$("#YT_MSG_BTN_OK").click(function(){
                	$("#queryForm").attr("action", "product.shtml?action=toList&search=true&module="+getModule());
					$("#queryForm").submit();
//					parent.leftcontent.$("#refresh").click();
					//parent.window.gotoBottomPage("label_1");
					
                });
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	    
	});
}
//导入
function uploading(){
	var fileName = $("#upInfo").val();
	$.ajax({
	 url: "product.shtml?action=toImportData",
	 type:"POST",
 	 dataType:"text",
 	 data:{"fileName":fileName,"DRPFLAG":"0"},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			alert("上传成功");
            $("#pageForm").submit();
            $("#upFile").hide();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
 