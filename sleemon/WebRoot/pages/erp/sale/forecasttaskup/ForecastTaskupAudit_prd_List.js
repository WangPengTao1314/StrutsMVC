/**
 *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *
 */
$(function(){
	$("#save").click(function(){
	 	 saveChild();
	 });
	disabledButton();
	 
 
});	

function disabledButton(){
	var STATE = parent.topcontent.getSTATE();
	if(null == STATE ||　STATE == "审核通过"){
		btnDisable(["save"]);
		$("#jsontb tr:gt(0) img").hide();
	}
}

function saveChild(){
	var jsonDate = multiPackJsonData();
	 $.ajax({
		url: "forecasttaskupaudit.shtml?action=saveChild",
		type:"POST",
		dataType:"text",
		data:{"jsonDate":jsonDate},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}


/*
 *将多个form封装成json串
 *
 *@param 
 *@return json格式字符串
 */
function multiPackJsonData(tableid){
	if(null == tableid){
		tableid = "jsontb";
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		var isEdit = true;//$(this).find("input[type='checkbox']:eq(0)").attr("checked");
		if(isEdit){
			jsonData = jsonData+"{";
			var isFirst = true;
			var inputs = $(this).find(":input");
			inputs.each(function(){
				if(null != $(this).attr("json")){
					var key;
					var value;
					var type = $(this).attr("type");
					if(!isFirst && "checkbox" == type){
						key = $(this).attr("json");
						if($(this).attr("checked")){
							value= 1;
						}else{
							value= 0;
						}
					}else if("radio" == type){
						if($(this).attr("checked")){
							key = $(this).attr("json");
							value= $(this).attr("value");
						}
					}else{
						key = $(this).attr("json");
						value = $(this).attr("value");
						
						isFirst = false;
					}
					var inputtype = $(this).attr("inputtype");
					jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
				}
			});
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
		}
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}