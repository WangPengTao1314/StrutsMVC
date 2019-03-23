 
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
 
});



 
 

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
			btnDisable(["partAddAdd","PartAddEdit","PartAddDelete","PartReplaceAdd","PartReplaceEdit","PartReplaceDelete","SizeResetAdd","SizeResetEdit","SizeResetDelete","FBSave"]);
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


function changeChannType(obj){
	var v = $(obj).val();
	if("托运" == v){
		$("#CHANN_TYPE option[text='区域代发']").remove();
		$("#CHANN_TYPE option[text='基地发货']").remove();
		$("#CHANN_TYPE").attr("disabled","disabled");
	}else{
		var disabled = $("#CHANN_TYPE").attr("disabled");
		if("disabled" == disabled){
			$("#CHANN_TYPE").removeAttr("disabled");
			$("#CHANN_TYPE").append("<option value='区域代发'>区域代发</option>");
			$("#CHANN_TYPE").append("<option value='基地发货'>基地发货</option>");
		}
		
	}
}
