
/**
 * @module 系统管理
 * @func 渠道信息
 * @version 1.1
 * @author 刘曰刚
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	var selId = parent.document.getElementById("selRowId").value;
	$("#save").click(function(){
		$(this).attr("disabled","disabled");
		 mtbSave("retalprice.shtml?action=edit","RETAL_PRICE_ID");
	});
});
//单表保存
function mtbSave(actionUrl,pkId,formId){
    if(formId==null||formId=='')
    {
      formId="mainForm";
    }
	if(!formChecked(formId)){
			$("#save").removeAttr("disabled");
			return;
	}
	var RETAL_PRICE_ID=$("#RETAL_PRICE_ID").val();
	var jsonData = siglePackJsonData();
	$.ajax({
		url: actionUrl+"&"+pkId+"="+RETAL_PRICE_ID,
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			$("#save").removeAttr("disabled");
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}