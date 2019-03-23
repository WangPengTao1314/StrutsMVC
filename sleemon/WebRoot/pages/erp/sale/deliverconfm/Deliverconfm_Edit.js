/**
 * @prjName:喜临门营销平台
 * @fileName: 发运管理 - 发运确认
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */
$(function(){
    
	//下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator("mainForm");	
	
	
});


function save(){
	var jsonData = siglePackJsonData();
	$.ajax({
		url: "deliverconfm.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess(utf8Decode(jsonResult.messages),"deliverconfm.shtml?action=toFrame");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
}

function restElement(){
	parent.$("#bottomdiv").css("display","block");
	parent.$("#tablayer").css("display","block");
	parent.$("#topdiv").css("height","50%");
}