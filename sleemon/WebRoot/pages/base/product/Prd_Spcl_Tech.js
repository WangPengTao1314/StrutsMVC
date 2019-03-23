 
/**
 * @module 系统管理
 * @func 货品信息-特殊工艺维护
 * @version 1.1
 * @author 黄如
 */

 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
    setSelOperateEx();
});



//子表保存
function childSave(){
	var selRowId = getSelRowId();
	//alert(selRowId);
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "product.shtml?action=toPrdAddEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"PRD_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
 
 

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	//var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
}

 
 function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate=parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		state = $.trim(state);
		//当状态==未提交
		if (state == "启用") {
			
//			btnDisable(["partAddAdd","PartAddEdit","PartAddDelete","PartReplaceAdd","PartReplaceEdit","PartReplaceDelete","SizeResetAdd","SizeResetEdit","SizeResetDelete","FBSave"]);
		}
	}
}
//按钮不可用
function btnDisable(arrys){
	 
	if(null==arrys || arrys.length<1){
		return;
	}
	for(var i=0; i<arrys.length; i=i+1){
		 
		$("#"+arrys[i]).attr("disabled","true");
	}
}
