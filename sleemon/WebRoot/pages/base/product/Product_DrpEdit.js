
/**
 * @module 系统管理
 * @func 货品信息
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
		if(!formChecked('mainForm')){
			return;
		}
		 
		if(null == selId || "" == selId){
			//var PRD_NO = $("#prefix").val()+$("#suffix").val();
			var PRD_NO =  $("#suffix").val();
		    $("#PRD_NO").val(PRD_NO);
		}
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "product.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"PRD_ID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					var PRD_ID = utf8Decode(jsonResult.messages);
					//上帧页面跳转至编辑后的记录
					//window.parent.topcontent.location="product.shtml?action=toList&PRD_ID="+PRD_ID+parent.window.reqParamsEx();
					window.parent.topcontent.pageForm.submit();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
});

function selParPrdId(){
//	var BRAND=$("#BRAND").val();
//	if(""==BRAND||null==BRAND){
//		parent.showErrorMsg(utf8Decode("请选择品牌！"));
//		return;
//	}else{
		$("#selectFalg").val(" STATE='启用' and FINAL_NODE_FLAG=0  ");
		selCommon('BS_21', false, 'PAR_PRD_ID', 'PRD_ID', 'forms[0]','PAR_PRD_ID,PAR_PRD_NO,PAR_PRD_NAME', 'PRD_ID,PRD_NO,PRD_NAME', 'selectFlag');	
//	}
}

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	
		if($("#PRD_NO").val()!=null && $("#PRD_NO").val() != ""){
	        var re1 = new RegExp(/^[a-zA-Z0-9]*$/);
	        var PRD_NO = re1.test($("#PRD_NO").val());
	        if(!PRD_NO ){
	      	   parent.showErrorMsg(utf8Decode("货品编号格式不正确！"));
	           return false;
	        }
		}
	return true;
}