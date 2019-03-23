/**
 *@module 渠道管理-装修管理
 *@func   装修整改验收单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	
	$("#commitOk").click(function(){
	  commRecvcheck("rnvtnreformcheck.shtml?action=commRecv","RNVTN_REFORM_ID");
	});
	
	$("#reCheck").click(function(){
	  commcheck("rnvtnreformcheck.shtml?action=reCheck","RNVTN_REFORM_ID");
	});
	
	
	$("#save").click(function(){
		var jsonData = siglePackJsonData();
		var selRowId = $("#RNVTN_REFORM_ID").val();
		$.ajax({
			url: "rnvtnreformcheck.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"RNVTN_REFORM_ID":selRowId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					   window.location="rnvtnreformcheck.shtml?action=toFrames&RNVTN_REFORM_ID="+selRowId;	
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	//mtbSaveListener("jgxx.shtml?action=edit","JGXXID","jgxx.shtml?action=toList","mainForm");
});

function commRecvcheck(urk,pkId){
   var selRowId = $("#RNVTN_REFORM_ID").val();
   var goUrl = $("#pageForm").attr("action"); 
   var child = "";
   var deleteUrl = "rnvtnreformcheck.shtml?action=commRecv&RNVTN_REFORM_ID="+selRowId;
   realcommRecv(deleteUrl,pkId,selRowId,goUrl);
   
}

function commcheck(urk,pkId){
   var selRowId = $("#RNVTN_REFORM_ID").val();
   var goUrl = $("#pageForm").attr("action"); 
   var child = "";
   var deleteUrl = "rnvtnreformcheck.shtml?action=reCheck&RNVTN_REFORM_ID="+selRowId;
   realreCheck(deleteUrl,pkId,selRowId,goUrl);
}

function realreCheck(actionUrl,pkId,selRowId,goUrl){
    $.ajax({
	 	url: actionUrl+"&"+pkId+"="+utf8(selRowId),
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				  //showMsgPanel("已确认为重新整改");
				  window.location="rnvtnreformcheck.shtml?action=toFrames&RNVTN_REFORM_ID="+selRowId;		
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function goFrame(url){
  window.location=url+window.reqParamsEx()+"&module="+getModule(); 
}


function commitPass(){
  history.back();
}

function realcommRecv(actionUrl,pkId,selRowId,goUrl){
    $.ajax({
	 	url: actionUrl+"&"+pkId+"="+utf8(selRowId),
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				//saveConfirm("验收通过",window.location.reload());
				window.location="rnvtnreformcheck.shtml?action=toFrames&RNVTN_REFORM_ID="+selRowId;				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
