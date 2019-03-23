/**
 *@module 预计量上报
 *@func 上报任务发布
 *@version 1.1
 *@author   
 *
 */
$(function(){
	 
	$("#delete").click(function(){
		  if(null == selRowId || "" == selRowId){
			  parent.showErrorMsg("请选择一条记录");
	 	 	  return;
	 	 } 
	 	 parent.showConfirm("将删除该品牌信息,您确认删除吗?","topcontent.listDelConfirm();");
	 });
	 
 
});	


function countAmount(obj,PRICE){
	 var ADVC_RPT_NUM = Number($(obj).val());
	 if(isNaN(ADVC_RPT_NUM)){
		 return;
	 }
	 PRICE = Number(PRICE);
	 var ADVC_RPT_AMOUNT = PRICE*ADVC_RPT_NUM;
	 ADVC_RPT_AMOUNT = ADVC_RPT_AMOUNT.toFixed(2);
	 $(obj).parent().parent().find("input[name='ADVC_RPT_AMOUNT']").val(ADVC_RPT_AMOUNT);
}

function queryPrd(){
	$("#queryForm").attr("action","forecasttaskup.shtml?action=toPrdList");
	$("#queryForm").submit();
}

//打开上报
function save(){
	var RPT_JOB_CHANN_ID = $("#RPT_JOB_CHANN_ID").val();
	var jsonDate = multiPackJsonData();
 
	 $.ajax({
		url: "forecasttaskup.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"RPT_JOB_CHANN_ID":RPT_JOB_CHANN_ID,"jsonDate":jsonDate},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("您已保存成功,不需要再次点击【保存】");
				$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}

function getTotal(){
   var count = $("#count").val();
   var num = 0 ;
   var amount = 0;
   for(var i=0;i<=count;i++){
     var ADVC_RPT_NUM = $("#ADVC_RPT_NUM"+i).val();
     var ADVC_RPT_AMOUNT = $("#ADVC_RPT_AMOUNT"+i).val();
     if(!isNaN(ADVC_RPT_NUM)){
        if(ADVC_RPT_NUM == ""){
          ADVC_RPT_NUM = 0;
        }else{
           num += parseInt(ADVC_RPT_NUM);
        }
     }
     
     if(!isNaN(ADVC_RPT_AMOUNT)){
        if(ADVC_RPT_AMOUNT == ""){
          ADVC_RPT_AMOUNT = 0;
        }else{
          amount += parseFloat(ADVC_RPT_AMOUNT);
        }
     }
   }
   $("#TOTAL_ADVC_RPT_NUM").val(num);
   $("#TOTAL_ADVC_RPT_AMOUNT").val(amount);
}


function load(){
   var count = $("#count").val();
   var num = 0 ;
   var amount = 0;
   for(var i=0;i<=count;i++){
     var ADVC_RPT_NUM = $("#ADVC_RPT_NUM"+i).val();
     var ADVC_RPT_AMOUNT = $("#ADVC_RPT_AMOUNT"+i).val();
     if(!isNaN(ADVC_RPT_NUM)){
        if(ADVC_RPT_NUM == ""){
          ADVC_RPT_NUM = 0;
        }else{
           num += parseInt(ADVC_RPT_NUM);
        }
     }
     
     if(!isNaN(ADVC_RPT_AMOUNT)){
        if(ADVC_RPT_AMOUNT == ""){
          ADVC_RPT_AMOUNT = 0;
        }else{
          amount += parseFloat(ADVC_RPT_AMOUNT);
        }
     }
   }
   $("#TOTAL_ADVC_RPT_NUM").val(num);
   $("#TOTAL_ADVC_RPT_AMOUNT").val(amount);
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