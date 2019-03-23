/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
	InitFormValidator("mainForm");
	mtbSaveListener("openterminal.shtml?action=childEdit","OPEN_TERMINAL_REQ_ID");
 
});




//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	var PLAN_TERMINAL_BRAND = $.trim($("#PLAN_TERMINAL_BRAND").val());
	if(null == PLAN_TERMINAL_BRAND || "" == PLAN_TERMINAL_BRAND){
	      parent.showErrorMsg(utf8Decode("请选择'计划开店品牌'！"));
	      return false;
	}
	var TERMINAL_ADDR = $.trim($("#TERMINAL_ADDR").val());
	if(null == TERMINAL_ADDR || "" == TERMINAL_ADDR){
	      parent.showErrorMsg(utf8Decode("请选择'门店位置'！"));
	      return false;
	}
	return true;
}


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
	var childJsonData = multiPackJsonData("myTable",true);
	$.ajax({
		url: actionUrl+"&"+pkId+"="+selId,
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData,"childJsonData":childJsonData},
		complete: function(xhr){
			$("#save").removeAttr("disabled");
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.$("#YT_MSG_BTN_OK").click(function(){
					gobacknew();
				});
 
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}



   
//返回
function gobacknew(){
   newGoBack("openterminal.shtml?action=toFrames");
}
  

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}  
  
function addRow(){
	$("#myTable tr:last-child").after('<tr> <td  class="detail_content" colspan="2">' +
	'<input json="COMMODITIES_NAME" id="" name="COMMODITIES_NAME" type="text" label="竞品"  maxLength="100" inputtype="string" autocheck="true"   value=""/>' +
	'<input class="btn" onclick="deleteRow(this)"  type="button" name="" value="删除" />' +
	'</td></tr>');
	//form校验设置
	trCheckInit($("#myTable tr:gt(0) input"));
}


function deleteRow(obj){
	var OPEN_TERMINAL_REQ_DTL_ID = $("#OPEN_TERMINAL_REQ_DTL_ID").val();
	if(null == OPEN_TERMINAL_REQ_DTL_ID || "" == OPEN_TERMINAL_REQ_DTL_ID){
		parent.showMsgPanel("删除成功");
		 $(obj).parent().parent().remove();
		 return;
	}
    var COMMODITIES_ID =  $(obj).parent().parent().find("input[json='COMMODITIES_ID']").val();
	$.ajax({
		url: "openterminal.shtml?action=deleteComm",
		type:"POST",
		dataType:"text",
		data:{"OPEN_TERMINAL_REQ_DTL_ID":OPEN_TERMINAL_REQ_DTL_ID,"COMMODITIES_ID":COMMODITIES_ID},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				$(obj).parent().parent().remove();
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
function multiPackJsonData(tableid,isEdit){
	if(null == tableid){
		tableid = "jsontb";
	}
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
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
