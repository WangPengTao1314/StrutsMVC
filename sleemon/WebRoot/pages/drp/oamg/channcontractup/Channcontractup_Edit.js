
/**
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */ 
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	var selId = parent.document.getElementById("selRowId").value;
	var module = parent.document.getElementById("module").value;
	$("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		if(!checkNum()){
			return;
		}
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "channcontractup.shtml?action=edit",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"CHANN_CONTRACT_ID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					var CHANN_CONTRACT_ID = utf8Decode(jsonResult.messages);
					//上帧页面跳转至编辑后的记录
					window.parent.topcontent.location="channcontractup.shtml?action=toList&module="+module+parent.window.reqParamsEx();
 
				}else{
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	//mtbSaveListener("jgxx.shtml?action=edit","JGXXID","jgxx.shtml?action=toList","mainForm");
});
function formCheckedEx(){
	var YEAR=$("#YEAR").val();
  	if(""==YEAR||null==YEAR){
  		parent.showErrorMsg(utf8Decode("请选择年份！"));
  		return false;
  	}
	var CONTRACT_ATT=$("#CONTRACT_ATT").val();
	if(""==CONTRACT_ATT||null==CONTRACT_ATT){
		parent.showErrorMsg(utf8Decode("请上传合同附件！"));
      	return false;
	}
  	if(CONTRACT_ATT.length>1000){
  		parent.showErrorMsg(utf8Decode("合同附件文件名过长！"));
  		return false;
  	}
  	return true;
}
function sumAmount(){
	var FIRST_QUARTER_AMOUNT=changeNum($("#FIRST_QUARTER_AMOUNT").val());
	var SECOND_QUARTER_AMOUNT=changeNum($("#SECOND_QUARTER_AMOUNT").val());
	var THIRD_QUARTER_AMOUNT=changeNum($("#THIRD_QUARTER_AMOUNT").val());
	var FOURTH_QUARTER_AMOUNT=changeNum($("#FOURTH_QUARTER_AMOUNT").val());
	var YEAR_PLAN_AMOUNT=FIRST_QUARTER_AMOUNT+SECOND_QUARTER_AMOUNT+THIRD_QUARTER_AMOUNT+FOURTH_QUARTER_AMOUNT;
	$("#YEAR_PLAN_AMOUNT").val(YEAR_PLAN_AMOUNT);
}
function changeNum(num){
	if(""==num||null==num){
		return 0;
	}else{
		return isNaN(num)?0:parseFloat(num);
	}
}
function checkNum(){
	var map=new Array();
	map["FIRST_QUARTER_AMOUNT"]=$("#FIRST_QUARTER_AMOUNT").val();
	map["SECOND_QUARTER_AMOUNT"]=$("#SECOND_QUARTER_AMOUNT").val();
	map["THIRD_QUARTER_AMOUNT"]=$("#THIRD_QUARTER_AMOUNT").val();
	map["FOURTH_QUARTER_AMOUNT"]=$("#FOURTH_QUARTER_AMOUNT").val();
	var flag=true;
	var re2 = new RegExp(/^\d{1,10}(\.\d{1,2})?$/);
	for(var key in map){
		if(map.hasOwnProperty(key)){
			if(""!=map[key]&&null!=map[key]&&!re2.test(map[key])){
		        parent.showErrorMsg($("#"+key).attr("label")+"最多可输入10位正整数和2位小数");
		        flag=false;
				return false;
		    }
		}
	}
	return flag;
}