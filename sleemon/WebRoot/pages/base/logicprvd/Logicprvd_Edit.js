/**
 * @module 系统管理
 * @func 物流供应商
 * @version 1.0
 * @author 王栋斌
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	mtbSaveListener("logicprvd.shtml?action=edit","PRVD_ID");
});


//单表保存监听
function mtbSaveListener(actionUrl,pkId,successUrl,formId){
	$("#save").click(function(){
		$(this).attr("disabled","disabled");
		 mtbSave(actionUrl,pkId,successUrl,formId);
	});
}
//单表保存
function mtbSave(actionUrl,pkId,successUrl,formId){
    if(formId==null||formId=='')
    {
      formId="mainForm";
    }
	if(!formChecked(formId)){
		$("#save").removeAttr("disabled");
		return;
	}
	var selId = parent.document.getElementById("selRowId").value;
	var jsonData = siglePackJsonData();
	$.ajax({
		url: actionUrl+"&"+pkId+"="+selId,
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			$("#save").removeAttr("disabled");
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
//				parent.showMsgPanel("保存成功");
//				window.parent.topcontent.pageForm.submit();
				saveSuccess("保存成功","logicprvd.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

 

//下拉框必填项验证
function formChecked() {
	 
	if(!InputCheck("#PRVD_NO")){
		return;
	}
	if(!InputCheck("#PRVD_NAME")){
		return;
	}

	var PRVD_TYPE = $("#PRVD_TYPE").val();
	var PRVD_LVL = $("#PRVD_LVL").val();
	var PRVD_NATRUE = $("#PRVD_NATRUE").val();
	if (PRVD_TYPE == "") {
		chkCanErrMsg("", "请选择'供应商类别'");
		return false;
	}
	if (PRVD_LVL == ""){
		chkCanErrMsg("", "请选择'供应商级别'");
		return false;
	}
	if (PRVD_NATRUE == ""){
		chkCanErrMsg("", "请选择'供应商性质'");
		return false;
	}
	
	if(!InputCheck("#PERSON_BUSS")){
		return;
	}
	if(!InputCheck("#PERSON_CON")){
		return;
	}
	
	var TEL = $("#TEL").val();
	if(null != TEL && "" !=TEL){
		var re1 = new RegExp(/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/);//电话匹配
		var r = re1.test(TEL);
        if(!r ){
      	   parent.showErrorMsg(utf8Decode("'电话'格式输入不正确！"));
           return false;
        }
	}
	var MOBILE = $("#MOBILE").val();
	if(null != MOBILE && "" !=MOBILE){
		var re1 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
		var r = re1.test(MOBILE);
        if(!r ){
      	   parent.showErrorMsg(utf8Decode("'手机'格式输入不正确！"));
           return false;
        }
	}
	
	var TAX = $("#TAX").val();
	if(null != TAX && "" !=TAX){
		var re1 = new RegExp(/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/);//电话匹配
		var r = re1.test(TAX);
        if(!r ){
      	   parent.showErrorMsg(utf8Decode("'传真'格式输入不正确！"));
           return false;
        }
	}
	var POST = $("#POST").val();
	if(null != POST && "" != POST){
        var re1 = new RegExp(/^[a-zA-Z0-9 ]{3,12}$/);//邮政编码匹配
        var r = re1.test(POST);
        if(!r ){
      	   parent.showErrorMsg(utf8Decode("'邮编'格式输入不正确！"));
           return false;
        }
	}
	
	var EMAIL = $("#EMAIL").val();
	if(null != EMAIL && "" != EMAIL){
        var re1 = new RegExp(/^\w+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/);//email匹配
        var r = re1.test(EMAIL);
        if(!r ){
      	   parent.showErrorMsg(utf8Decode("'Email'格式输入不正确！"));
           return false;
        }
	}
	
	
	return true;
}