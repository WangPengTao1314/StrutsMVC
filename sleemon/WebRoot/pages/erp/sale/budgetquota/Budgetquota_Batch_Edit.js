
/**
 * @module  
 * @func  
 * @version 1.1
 * @author  
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
		var BUDGET_ITEM_TYPE = $("#BUDGET_ITEM_TYPE").val();
		var BUDGET_QUOTA = "";
		if("按年" == BUDGET_ITEM_TYPE){
			BUDGET_QUOTA = getValue("BUDGET_QUOTA_YEAR");
		}
		if("按季" == BUDGET_ITEM_TYPE){
			BUDGET_QUOTA = getValue("BUDGET_QUOTA_QUARTER");
		}
		if("按月" == BUDGET_ITEM_TYPE){
			BUDGET_QUOTA = getValue("BUDGET_QUOTA_MONTH");
		}
		$("#BUDGET_QUOTA").val(BUDGET_QUOTA);
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "budgetquota.shtml?action=batchEdit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"BUDGET_QUOTA_ID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					//上帧页面跳转至编辑后的记录
					window.parent.topcontent.pageForm.submit();
					parent.leftcontent.$("#refresh").click();
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	
	
//	hideObject();
	
});
 


//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){
	var YEAR = $("#YEAR").val();
	if(null == YEAR || "" == YEAR){
  	   parent.showErrorMsg(utf8Decode("请选择'年份'！"));
       return false;
	}
	 
	
	return true;
}

function changeBatchEdit(v){
	var BUDGET_ITEM_TYPE = $("#BUDGET_ITEM_TYPE").val();
	if(null == BUDGET_ITEM_TYPE){
		 BUDGET_ITEM_TYPE = v;
	}
	if("按年" == BUDGET_ITEM_TYPE){
		$("#jsontb tr[name='quarter_tr']").hide();
		$("#jsontb tr[name='month_tr']").hide();
		$("#jsontb tr[name='year_tr']").show();
	}
	if("按季" == BUDGET_ITEM_TYPE){
		$("#jsontb tr[name='quarter_tr']").show();
		$("#jsontb tr[name='year_tr']").hide();
		$("#jsontb tr[name='month_tr']").hide();
	}
	if("按月" == BUDGET_ITEM_TYPE){
		$("#jsontb tr[name='year_tr']").hide();
		$("#jsontb tr[name='quarter_tr']").hide();
		$("#jsontb tr[name='month_tr']").show();
	}
}
//返回拼接后的字符串  BUDGET_QUOTA(预算额度)-BUDGET_QUOTA_ID(预算ID)
function getValue(obj){
	var value = "";
	$("#jsontb input[name='"+obj+"']").each(function(){
		var v = $(this).val();
		var recardid = $(this).attr("recardid");//该条记录的ID
		if(null == recardid){
			recardid = "";
		}
		value = value+v+"-"+recardid+",";
	});
	value = value.substr(0,value.length-1);
	return value;
}

function hideObject(){
	$("#jsontb tr[name='year_tr']").hide();
	$("#jsontb tr[name='month_tr']").hide();
	$("#jsontb tr[name='quarter_tr']").hide();
}