/**
 * @prjName:喜临门营销平台
 * @fileName:Termrefinecheck_List
 * @author lyg
 * @time   2014-01-26 14:46:31 
 * @version 1.1
 */
$(function(){
     //主从及主从从列表页面通用加载方法
	listPageInit("plancheck.shtml?action=toList&module="+document.getElementById("module").value);
	$("#add").click(function(){
	    window.parent.location="plancheck.shtml?action=toEditFrame"; 
	});
    $("#modify").click(function(){
        window.parent.location="plancheck.shtml?action=toEditFrame&CHANN_CHECK_PLAN_ID="+$("#selRowId").val(); 
	});
	//删除监听.参数1：删除action，参数2：删除主键Id
	//mtbDelListener("bmgz.shtml?action=delete","CMLID");
	var selRowId = $("#selRowId").val();
    if(null == selRowId || "" == selRowId){
      btnDisable(["start","modify","delete","stop"]);
      return;
    }
	   
	$("#delete").click(function(){
	 	 deletecheck("plancheck.shtml?action=delete","CHANN_CHECK_PLAN_ID");
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
		url: "plancheck.shtml?action=changeStateT&CHANN_CHECK_PLAN_ID="+$("#selRowId").val(),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("状态修改成功","plancheck.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  });
	});
	
	// 启用
	$("#start").click(function(){
	   //获取到当前品牌的信息
	   //获取当前选中的记录                     
	   selRowId = $("#selRowId").val();
	   $.ajax({
		url: "plancheck.shtml?action=changeState&CHANN_CHECK_PLAN_ID="+$("#selRowId").val(),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("状态修改成功","plancheck.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	  }); 
	});
});

 function deletecheck(url,pkId){
   var selRowId = $("#selRowId").val();
   if(null == selRowId || "" == selRowId){
		  parent.showErrorMsg("请选择一条记录");
 	 	  return;
   } 
   var goUrl = $("#pageForm").attr("action"); 
   var child = parent.bottomcontent.document.getElementById("eid1");
   var deleteUrl = "plancheck.shtml?action=delete";
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
				  $("#pageForm").submit();
			}else{
				  parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
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
 function toFlow(i) {
	var cutid = $("#selRowId").val();
	document.affirm.id.value = cutid;
    document.affirm.sourceURI.value=ctxPath+"/plancheck.shtml?action=toList"+document.getElementById("paramUrl").value ;
	switch (Number(i)) {
	  case 1:
		affirmEvent(0);
		break;//提交
	  case 2:
		affirmEvent(1);
		break;//审核
	  case 3:
		affirmEvent(2);
		break; //撤销
	  case 4:
		showHistory(cutid);
		break;//撤销
	}
}
//点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态=未提交
	if (state == "\u672a\u63d0\u4ea4") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=提交
	if (state == "\u63d0\u4ea4") {
		btnDisable(["delete","modify","commit"]);
	}
	
	//当状态=撤销
	if (state == "\u64a4\u9500") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=否决
	if (state == "\u5426\u51b3") {
		btnDisable(["revoke","audit"]);
	}
	
	//当状态=回退
	if (state == "\u9000\u56de") {
		btnDisable(["delete","modify","revoke","commit"]);
	}
	//当状态=审核通过
	if (state == "\u5ba1\u6838\u901a\u8fc7") {
		btnDisable(["delete","modify","revoke","commit","audit"]);
	}
}
//提交时验证是否有明细
function commit(selRowId){
	$.ajax({
	 url: "plancheck.shtml?action=toCommit&TERM_REFINE_CHECK_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			toFlow("1");
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
//导入
function uploading(){
	var fileName=$("#up").val();
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
	 url: "plancheck.shtml?action=parseExecl&fileName="+utf8(fileName)+"&TERM_REFINE_CHECK_ID="+utf8(selRowId),
	 type:"POST",
 	 dataType:"text",
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