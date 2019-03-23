/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10 
 * @version 1.1
 */
$(function(){
    
});


function cardList(){
	$("#listForm").attr("action","wap.shtml?action=cardList");
	$("#listForm").submit();
}

function loginOut(){
	$("#listForm").attr("action","wap.shtml?action=loginOut");
	$("#listForm").submit();
}


function cardEdit(){
	if(!checkform()){
		return;
	}
	var MARKETING_CARD_NO = $("#MARKETING_CARD_NO").val();
	var jsonData = sigleFormJsonData("mainForm");
	$.ajax({
		url: "marketcardsale.shtml?action=mobileCardSaleEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"MARKETING_CARD_NO":MARKETING_CARD_NO},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert(utf8Decode(jsonResult.messages));
			}else{
				alert(utf8Decode(jsonResult.messages));
			}
		}
	});
	
	return false;
}
 
function checkform(){
	var MARKETING_CARD_NO = $("#MARKETING_CARD_NO");
	if(null == MARKETING_CARD_NO.val() || "" == $.trim(MARKETING_CARD_NO.val())){
//		MARKETING_CARD_NO.css("border","1px solid red");
		MARKETING_CARD_NO.parent().css("border","1px solid red");
		MARKETING_CARD_NO.focus();
//		alert("请输入'"+MARKETING_CARD_NO.attr("placeholder")+"'");
		return false;
	}else{
		MARKETING_CARD_NO.parent().css("border","1px solid #CCC");
	}
	
	return true;
}

function loadCard(){
	 var MARKETING_CARD_NO = $("#MARKETING_CARD_NO").val();
	 MARKETING_CARD_NO = $.trim(MARKETING_CARD_NO);
	 if(null == MARKETING_CARD_NO || "" == MARKETING_CARD_NO){
		 return;
	 }
	 $.ajax({
		url: "marketcardsale.shtml?action=loadCard",
		type:"POST",
		dataType:"text",
		data:{"MARKETING_CARD_NO":MARKETING_CARD_NO},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    var data = jsonResult.data;
			     var inputs = $("#mainForm input[json]");
				 inputs.each(function(){
					var id = $(this).attr("id");
					$("#"+id).val(eval("data."+id));
				 });
			    
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
}



/**
 *将单个form封装成json串
 *@param tableid
 *@return json格式字符串
 */
function sigleFormJsonData(formId){
	var jsonData = "{";
	var inputs = $("#"+formId+" :input");
	inputs.each(function(){
		if(null != $(this).attr("json")){
			var key;
			var value; 
			var type = $(this).attr("type");
			if("checkbox" == type){
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
				value= $.trim($(this).attr("value"));
			}
			var inputtype = $(this).attr("inputtype");
			jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
		}
	});
	jsonData = jsonData.substr(0,jsonData.length-1)+"}";
    return "["+jsonData+"]";
}


 