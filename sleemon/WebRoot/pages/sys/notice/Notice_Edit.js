$(function(){	//初始化校验	InitFormValidator(0);	//添加浮动按钮层的监听	addFloatDivListener();	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，//	mtbSaveListener("notice.shtml?action=edit","NOTICEID","notice.shtml?action=toList","mainForm");	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();		$("#save").click(function(){		$("#NOTICE").val(eWebEditor1.getHTML());		$(this).attr("disabled","disabled");//		 mtbSave(actionUrl,pkId,successUrl,formId);		mtbSave("notice.shtml?action=edit","NOTICEID","notice.shtml?action=toList","mainForm");	});	});//单表保存function mtbSave(actionUrl,pkId,successUrl,formId){    if(formId==null||formId=='')    {      formId="mainForm";    }	if(!formCheckedEx()){		$("#save").removeAttr("disabled");		return;	}	var selId = parent.document.getElementById("selRowId").value;	var jsonData = siglePackJsonData();	var NOTICE = $("#NOTICE").val();	$.ajax({		url: actionUrl+"&"+pkId+"="+selId,		type:"POST",		dataType:"text",		data:{"jsonData":jsonData,"NOTICE":NOTICE},		complete: function(xhr){			$("#save").removeAttr("disabled");			eval("jsonResult = "+xhr.responseText);			if(jsonResult.success===true){				parent.showMsgPanel("保存成功");				//modify by zhuxw				//window.parent.topcontent.location=successUrl+parent.window.reqParamsEx();				window.parent.topcontent.pageForm.submit();			}else{				parent.showErrorMsg(utf8Decode(jsonResult.messages));			}		}	});}//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();function formCheckedEx(){	var unitType = $("#NOTICE_TYPE").val();    var startTime = $("#STATIME").val();	var endTime = $("#ENDTIME").val();		if (!InputCheck($("#NOTICE_TITLE")[0])) {			return false;	}		if (unitType == "") {		chkCanErrMsg("", "请选择'公告类型'");		return false;	}			if (!InputCheck($("#ISSUER_NAME")[0])) {			return false;	}    if (!InputCheck($("#STATIME")[0])) {			return false;	}        if (!InputCheck($("#ENDTIME")[0])) {			return false;	}    if (!InputCheck($("#ATT_PATH")[0])) {			return false;	}     	if(endTime<startTime){		parent.showErrorMsg("'结束时间'不得小于'开始时间'");		return false;	}//	var NOTICE = $("#NOTICE").val();	var NOTICE = eWebEditor1.getText();	NOTICE = $.trim(NOTICE);	if(NOTICE==null||NOTICE==''){	  parent.showErrorMsg("请输入'公告内容'");	  return false;	}		return true;} 