
/**
 * @module  
 * @func  
 * @version 1.1
 * @author  
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
			url: "budgetitem.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"BUDGET_ITEM_ID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					//上帧页面跳转至编辑后的记录
					window.parent.topcontent.pageForm.submit();
					parent.leftcontent.$("#refresh").click();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
});
 
 
function editRadio(id,val){
	$("#"+id).val(val);
}



//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	if($("#BUDGET_ITEM_TYPE").val()==null || $("#BUDGET_ITEM_TYPE").val() == ""){
      	   parent.showErrorMsg(utf8Decode("请选择'预算科目类型'！"));
           return false;
	}
	return true;
}
