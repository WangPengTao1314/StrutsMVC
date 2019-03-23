
/**
 *@module 渠道管理-分销商管理
 *@func   加盟商终止合作申请
 *@version 1.1
 *@author gu_hx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		save();
	});
 
});

//保存
function save(){
	var jsonData = siglePackJsonData();
	$.ajax({
		url: "distributerEndReq.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 window.returnValue = 2;				 
				 var module = $("#module",parent.document).val();
				 window.dialogArguments.parent.framePageInit("distributerEndReq.shtml?action=toList&module=" + module);
			}else{
				window.returnValue = 0;
				window.dialogArguments.parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
			
			window.close();			
		}
	});
}

//返回
function gobacknew(){
	window.returnValue = 1;
	window.close();
}

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){		
	return true;
}
