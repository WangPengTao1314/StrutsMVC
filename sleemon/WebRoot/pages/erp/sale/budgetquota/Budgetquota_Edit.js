
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
			url: "budgetquota.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"BUDGET_QUOTA_ID":selId},
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
 


//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	var YEAR = $("#YEAR").val();
	if(null == YEAR || "" == YEAR){
  	   parent.showErrorMsg(utf8Decode("请选择'年份'！"));
       return false;
	}
//	var QUARTER = $("#QUARTER").val();
//	var MONTH = $("#MONTH").val();
//	if((null == QUARTER || "" == QUARTER) &&( null == MONTH || "" == MONTH)){
//  	    parent.showErrorMsg(utf8Decode("'季度'和'月份'至少选择一项！"));
//       return false;
//	}
	
	return true;
}
