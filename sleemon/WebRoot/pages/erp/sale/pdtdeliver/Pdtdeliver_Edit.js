/**
 * @prjName:喜临门营销平台
 * @fileName: 发运管理 - 货品发运
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */
$(function(){
    
	//初始化校验
	InitFormValidator("mainForm");	
	
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		save();
	});
	 
	
	
});


function save(){
	var jsonData = siglePackJsonData();
	$.ajax({
		url: "pdtdeliver.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				  saveSuccess(utf8Decode(jsonResult.messages),"pdtdeliver.shtml?action=toFrame");
//				 parent.showMsgPanel(utf8Decode(jsonResult.messages));
//				 goFrame("pdtdeliver.shtml?action=toFrame");
				 saveConfirm(utf8Decode(jsonResult.messages),"goFrame('pdtdeliver.shtml?action=toFrame');");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
}


//返回
function gobacknew(){
   newGoBack("pdtdeliver.shtml?action=toFrame");
}

function restElement(){
	parent.$("#bottomdiv").css("display","block");
	parent.$("#tablayer").css("display","block");
	parent.$("#topdiv").css("height","50%");
}