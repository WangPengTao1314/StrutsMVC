/**
 *@module 系统管理
 *@fuc 路线信息js
 *@version 1.0
 *@author 王栋斌
 */
$(function() {
	//form校验设置
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//编号重复性校验
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		var jsonData = siglePackJsonData();
		var HAULWAY_ID = $("#HAULWAY_ID").val();
		$.ajax({
			url: "haulway.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"HAULWAY_ID":HAULWAY_ID},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					window.parent.topcontent.pageForm.submit();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
//	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
//	mtbSaveListener("haulway.shtml?action=edit", "HAULWAY_ID");
});
