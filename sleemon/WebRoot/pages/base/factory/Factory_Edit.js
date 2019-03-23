/**
 *@module 基础数据
 *@func 生产基地维护
 *@version 1.0
 *@author 王志格
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	
	var selId = parent.document.getElementById("selRowId").value;

	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "factory.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"FACTORY_ID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					var FACTORY_ID = utf8Decode(jsonResult.messages);
					//上帧页面跳转至编辑后的记录
					window.parent.topcontent.location="factory.shtml?action=toList&FACTORY_ID="+FACTORY_ID+parent.window.reqParamsEx();
					
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	
});


//多行文本框check
function formCheckedEx(){
	var tableId = 'mainForm';
	var flag = true;
	var textAreas = $("#"+tableId).find("textarea");

	textAreas.each(function(){
		if (!TextAreaCheck(this)) {
			flag = false;
			return flag;
		}
	});
	return flag;
}
function TextAreaCheck(textArea){
	
	var tempCheck=$(textArea).attr("autocheck");
	var tempRequir=$(textArea).attr("mustinput");
	var tempVal=$(textArea).val(); 
	var tempLabel=$(textArea).attr("label");
	var tempinputtype=$(textArea).attr("inputtype");
	
	if (tempCheck && (tempCheck == true||tempCheck== "true")) {
		// 必输项校验
		if (tempVal.trim().length == 0) {
			if (tempRequir && (tempRequir == true||tempRequir == "true")) {
				var MsgMustInput = "请输入{0}!";
				var msg = GetSpanMessage(MsgMustInput, tempLabel, textArea.params);
				chkCanErrMsg(textArea, msg);
				
				return false;
			}
			return true;
		}
		// 校验字符串内容
		if (!CheckStringInput(textArea)) {
			return false;
		}
		return true;
	}
}

