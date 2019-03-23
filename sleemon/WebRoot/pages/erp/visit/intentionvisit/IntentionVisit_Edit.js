
/**
 *@module 渠道管理-拜访管理
 *@func   拓展拜访表维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("intentionvisit.shtml?action=edit","INTE_CHANN_ID");
	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
});



  

  function pushArry(arrs,v){
	  if(null == v){
		  v = "";
	  }
	  arrs.push(v);
  }

//单表保存监听
function mtbSaveListener(actionUrl,pkId,successUrl,formId){
	$("#save").click(function(){
		//$(this).attr("disabled","disabled");
		 mtbSave(actionUrl,pkId,successUrl,formId);
	});
}
//单表保存
function mtbSave(actionUrl,pkId,successUrl,formId){
    if(formId==null||formId==''){
      formId="mainForm";
    }
    if(!checkForm()){
    	return;
    }
	if(!formChecked(formId)){
		$("#save").removeAttr("disabled");
		return;
	}
	var selId = parent.document.getElementById("selRowId").value;
	var jsonData = siglePackJsonDatas();
//	parent.showErrorMsg(jsonData);
//	return;
	// zzb
	$.ajax({
		url: actionUrl+"&"+pkId+"="+selId,
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			$("#save").removeAttr("disabled");
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				//modify by zhuxw
				//window.parent.topcontent.location=successUrl+parent.window.reqParamsEx();
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}


 
function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}
 

function checkForm(){
	var CITY = $.trim($("#CITY").val());
	var CITY_TYPE = $("#CITY_TYPE").val();
	if(null == CITY || "" == CITY){
		return true;
	}
	if(null == CITY_TYPE || "" == CITY_TYPE){
		parent.showErrorMsg("请选择'城市类型'!");
		return false;
	}
	return true;
}


 /*
 *将单个form封装成json串
 *
 *@param tableid
 *@return json格式字符串
 */
function siglePackJsonDatas(tableid){
	if(null == tableid){
		tableid = "jsontb";
	}
	var jsonData = "{";
	var inputs = $("#"+tableid+" :input");
	var array = [];
	inputs.each(function(){
		var json = $(this).attr("json");
		if(null != json && "" != json){
			for(var i=0;i<array.length;i++){
				if(array[i] == json){
					return;
				}
			} 
			
			var key = "";
			var value = ""; 
			var type = $(this).attr("type");
			if("radio" != type){
				array.push(json);
			}
			if("radio" == type){
				if($(this).attr("checked")){
					key = $(this).attr("json");
					value= $(this).attr("value");
				}else{
					return;
				}
			}else if("checkbox" == type){
				key = $(this).attr("json");
				if($(this).attr("checked")){
					value= 1;
				}else{
					value= 0;
				}
			}else{
				var jsonInput = $("#"+tableid+" input[json='"+json+"']");
				if(type.indexOf("select")>-1){
					jsonInput = $("#"+tableid+" select[json='"+json+"']");
				}else if("textarea" == type){
					jsonInput = $("#"+tableid+" textarea[json='"+json+"']");
				}
				
				jsonInput.each(function(){
					value = value + $(this).attr("value")+"@@";
				});
				if(value.length>0){
					value = value.substr(0,value.length-2);
				}
			}
			key = json;
			jsonData = jsonData+ "'" + key + "':'" + value +"',";
		}
	});
	jsonData = jsonData.substr(0,jsonData.length-1)+"}";
	return jsonData;
}