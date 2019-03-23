/**
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 交付计划维护
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	//parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator("mainForm");	
	
//	mtbSaveListener("turnoverhd.shtml?action=edit","DELIVER_ORDER_ID");
	$("#save").click(function(){
		save();
	});
	
});



//返回
function gobacknew(){
   newGoBack("deliveryhd.shtml?action=toFrame");
}


function save(){
	var jsonData = siglePackJsonData();
	$.ajax({
		url: "deliveryhd.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"mainData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				saveSuccess(utf8Decode(jsonResult.messages),"turnoverhd.shtml?action=toFrame");
				saveConfirm(utf8Decode(jsonResult.messages),"goFrame('deliveryhd.shtml?action=toFrame');");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
				 
			}
		}
	});
}