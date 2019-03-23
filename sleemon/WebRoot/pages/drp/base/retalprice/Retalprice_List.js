
/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 刘曰刚
 */
$(function () {
    //页面初始化
	listPageInit("retalprice.shtml?action=toList");
	$("#modify").click(function(){
		 var PRD_ID = $("#TEMP_PRD_ID").val();
		 var RETAL_PRICE_ID = $("#TEMP_RETAL_PRICE_ID").val();
	 	 if((null == RETAL_PRICE_ID || "" == RETAL_PRICE_ID)&&(null == PRD_ID || "" == PRD_ID)){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
			parent.window.gotoBottomPage("toEdit",PRD_ID,RETAL_PRICE_ID);
	 	 }
	});
	$("#save").click(function(){
		if(!check()){
			return;
		}
		var jsonData = multiPackJsonData("ordertb");
		$.ajax({
		url: "retalprice.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData},
		complete: function(xhr){
			$("#save").removeAttr("disabled");
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	})
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
		
	});
});
function init(){
	var RETAL_PRICE_ID=$("#TEMP_RETAL_PRICE_ID").val();
	var PRD_ID = $("#TEMP_PRD_ID").val();
	if((null == RETAL_PRICE_ID || "" == RETAL_PRICE_ID)&&(null == PRD_ID || "" == PRD_ID)){
		$("#eid1").click();
	}
}
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "retalprice.shtml?action=delete&RETAL_PRICE_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				window.location="chann.shtml?action=toList&RETAL_PRICE_ID="+selRowId;
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
function getId(RETAL_PRICE_ID,PRD_ID,id){
	$("#TEMP_RETAL_PRICE_ID").val(RETAL_PRICE_ID);
	$("#TEMP_PRD_ID").val(PRD_ID);
	$("#"+id).attr("checked","true");
	parent.window.getToDetail(PRD_ID,RETAL_PRICE_ID);
}
function check(){
	var checkBox = $("input[name='eid']:checked");
	var flag=true;
	checkBox.each(function(){
		var FACT_PRICE=$(this).parent().parent().find("input[name='FACT_PRICE']").val();//单价
		if(""==FACT_PRICE||null==FACT_PRICE){
				parent.showErrorMsg("请输入零售价");
	            flag=false;
				return false;
			}
		var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
	        if(!re2.test(FACT_PRICE)){
	            parent.showErrorMsg("零售价最多可输入8位正整数和2位小数");
	            flag=false;
				return false;
	        }
	})
	return flag;
}
function resetBtn(){
	$("#PRD_NO").val("");
	$("#PRD_NAME").val("");
	$("#PRD_SPEC").val("");
	$("#BRAND").val("");
	$("#PAR_PRD_NAME").val("");
	$("#BEGIN_CRE_TIME").val("");
	$("#END_CRE_TIME").val("");
}
