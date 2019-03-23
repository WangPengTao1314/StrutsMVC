/**
 * @prjName:信用额度变更申请
 * @fileName:Grant_Frame
 * @author zhang_zhongbin
 * @version 1.1
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	
	var selId = parent.document.getElementById("selRowId").value;
 
	var module = parent.document.getElementById("module").value;
	 
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "credit_req.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"creditReqID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					//上帧页面跳转至编辑后的记录
					window.parent.topcontent.location="credit_req.shtml?action=toList&module="+module+parent.window.reqParamsEx();
					 
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	//mtbSaveListener("area.shtml?action=edit","quxxID","area.shtml?action=toList","mainForm");
});