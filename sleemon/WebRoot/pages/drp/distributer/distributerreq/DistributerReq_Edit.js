
/**
 *@module 渠道管理-分销商管理
 *@func   分销渠道信息登记
 *@version 1.1
 *@author gu_hx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		save();
	});
 
});

//保存
function save(){
	var jsonData = siglePackJsonData();
	$.ajax({
		url: "distributerReq.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 window.dialogArguments.parent.showMsgPanel("保存成功");
				 window.dialogArguments.parent.framePageInit("distributerReq.shtml?action=toList");
			}else{
				window.dialogArguments.parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
			
			window.close();			
		}
	});
}

//返回
function gobacknew(){   
	window.close();
}

//显示附件行
function showTr(obj){
	var selValue = $(obj).val();
	
	var tr_cpatt = $("#tr_cpatt");
	var tr_att = $("#tr_att");
	
	if(selValue.indexOf("1+N") >= 0){
		$(tr_cpatt).attr("style","display: block");
		$(tr_att).attr("style","display: none");
	}else{
		$(tr_cpatt).attr("style","display: none");
		$(tr_att).attr("style","display: block");
	}
}

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	//分销类型
	var DISTRIBUTOR_TYPE = $("#DISTRIBUTOR_TYPE").val();
	if("" == DISTRIBUTOR_TYPE){
		 parent.showErrorMsg(utf8Decode("请选择分销类型！"));
	     return false;
	}
	
	//附件 COOPER_PLAN_ATT ATT
	if(DISTRIBUTOR_TYPE.indexOf("1+N") >= 0){
	   var  COOPER_PLAN_ATT = $("#COOPER_PLAN_ATT").val();
	   if("" == COOPER_PLAN_ATT){
	   	 	parent.showErrorMsg(utf8Decode("请上传合作方案！"));
	    	return false;
	   }
	}else{
	   var  ATT = $("#ATT").val();
	   if("" == ATT){
	   	 	parent.showErrorMsg(utf8Decode("请上传附件！"));
	    	return false;
	   }
	}
	
	return true;
}
