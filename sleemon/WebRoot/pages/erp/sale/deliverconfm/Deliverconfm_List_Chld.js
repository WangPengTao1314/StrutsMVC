/**
 * @prjName:喜临门营销平台
 * @fileName: 发运管理 - 发运确认
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	 headColumnSort("ordertb","ordertbForm");
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		
		 
	});
	
	
	
	$("#edit").click(function(){
        var checkBox = $("#ordertb tr:gt(0) input:checked");
		var ids = "";
		if(checkBox.length>0){
			//获取所有选中的记录
			checkBox.each(function(){
				ids = ids+"'"+$(this).val()+"',";
			});
			ids = ids.substr(0,ids.length-1);
		}
		var paramUrl = parent.topcontent.$("#paramUrl").val();
		if(null != paramUrl && "" != paramUrl) {
		    //paramUrl = (paramUrl.replaceAll("andflag","&")).replaceAll("equalsflag","=");
		    paramUrl = (paramUrl.replaceAll("&","andflag")).replaceAll("=","equalsflag");
		    parent.$("#paramUrl").val(paramUrl);
		}	
		$("#DELIVER_ORDER_DTL_IDS").val(ids);
		$("#form1").attr("action","deliverconfm.shtml?action=toChildEdit");
		$("#form1").submit();
	});
	
	
	setSelOperateEx();
	
	
});
 
 
 

// 选中明细的 
function checkChild(){
	var trs = $("#ordertb tr:gt(0)");
	
	var flag = true;
	trs.each(function(){
		var not_send_num = $(this).parent().parent().find("input[name='NO_SEND_NUM']").val();
		if(parseInt(not_send_num)>0){
			var NO_SEND_DEAL_TYPE = $(this).parent().parent().find("select[name='NO_SEND_DEAL_TYPE']").val();
			if(null == NO_SEND_DEAL_TYPE || "" == NO_SEND_DEAL_TYPE){
				parent.showErrorMsg("请选择'剩余货品处理方式'");
				flag = false;
				return false;
			}
		}
		
	});
	
	return flag;
}
 

function setSelOperateEx() {
	var selRowId = parent.document.getElementById("selRowId").value;
	var vstate = parent.topcontent.document.getElementById("state" + selRowId);
	if(vstate){
		var state = vstate.value;
		if (state != "已发货") {
			 $("#ordertb").find("select[name='NO_SEND_DEAL_TYPE']").attr("disabled","true");
		}
		
		if (state == "已提交库房" || state == "已发货" || state == "部分发货") {
			 
		}else{
			 btnDisable(["edit"]);
		}
		
	}
//	var inputs = $("#ordertb input[name='REAL_SEND_NUM']");
//	inputs.each(function(){
//		var v = $(this).val();
//		if(null != v && "" != v && 0 != v && "0" != v){
//			parent.topcontent.$("#shifa").attr("disabled","disabled");
//			return false;
//		}
//	});
}

//判断是否有选择的明细
function checkHasChild(){
	var checkBoxs = $("#ordertb input[name='mx_checkbox']:checked");
	if(checkBoxs.length == 0){
		parent.showErrorMsg("请至少选择一条明细");
		return false;
	}
	
	return true;
}



//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
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











