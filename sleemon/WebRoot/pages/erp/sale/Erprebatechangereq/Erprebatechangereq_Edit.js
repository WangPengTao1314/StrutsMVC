
/**
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */ 
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	var selId = parent.document.getElementById("selRowId").value;
	var module = parent.document.getElementById("module").value;
	var IS_COPY =  document.getElementById("IS_COPY").value;
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		if(typeof(IS_COPY)!="undefined" && IS_COPY=='TRUE'){
			selId = '';
	   }
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "erprebatechangereq.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"REBATE_CHANGE_REQ_ID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					//上帧页面跳转至编辑后的记录
					window.parent.topcontent.location="erprebatechangereq.shtml?action=toList&module="+module+parent.window.reqParamsEx();
 
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	//mtbSaveListener("jgxx.shtml?action=edit","JGXXID","jgxx.shtml?action=toList","mainForm");
});
function formCheckedEx(){
	var BILL_TYPE=$("#BILL_TYPE").val();
	if(""==BILL_TYPE||null==BILL_TYPE){
		parent.showErrorMsg(utf8Decode("请选择单据类型！"));
	    return false;
	}
	var REBATE_TYPE=$("#REBATE_TYPE").val();
	if(""==REBATE_TYPE||null==REBATE_TYPE){
		parent.showErrorMsg(utf8Decode("请选择返利类别！"));
	    return false;
	}
	return true;
}