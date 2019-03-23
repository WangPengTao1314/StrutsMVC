
/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 刘曰刚
 */
$(function () {
    //页面初始化
	listPageInit("drppromoteprice.shtml?action=toList");
	//初始化校验
	InitFormValidator(0);
	InitFormValidator("paramsForm");
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
		var PROMOTE_ID=$("#PROMOTE_ID").val();
		if(""==PROMOTE_ID||null==PROMOTE_ID){
			parent.showErrorMsg("请选择活动信息");
		 	return;
		}
		var jsonData = multiPackJsonData("ordertb");
		$.ajax({
		url: "drppromoteprice.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData,"PROMOTE_ID":PROMOTE_ID},
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
function listDelConfirm(){
	closeWindow();
	var selRowId = $("#selRowId").val();
	 $.ajax({
	 	url: "drppromoteprice.shtml?action=delete&RETAL_PRICE_ID="+selRowId,
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
function check(){
	var checkBox = $("input[name='eid']:checked");
	var flag=true;
	checkBox.each(function(){
		var DECT_PRICE=$(this).parent().parent().find("input[name='DECT_PRICE']").val();//单价
		if(""==DECT_PRICE||null==DECT_PRICE){
				parent.showErrorMsg("请输入折后单价");
	            flag=false;
				return false;
			}
		var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
        if(!re2.test(DECT_PRICE)){
            parent.showErrorMsg("折后单价最多可输入8位正整数和2位小数");
            flag=false;
			return false;
        }
        var DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();//折扣率
        var re1 = new RegExp(/^\d{1,4}(\.\d{1,2})?$/);
	        if(!re1.test(DECT_RATE)){
	            parent.showErrorMsg("折扣率最多可输入4位正整数和2位小数");
	            flag=false;
				return false;
	        }
	})
	return flag;
}
function resetBtn(){
	$("#PRD_NO").val("");
	$("#PRD_NAME").val("");
	$("#PAR_PRD_NAME").val("");
	$("#PAR_PRD_NO").val("");
	$("#PROMOTE_ID").val("");
	$("#PROMOTE_NO").val("");
	$("#PROMOTE_NAME").val("");
	$("#allot").attr("checked","checked");
}
function submitQuery(){
	var PROMOTE_ID=$("#PROMOTE_ID").val();
	if(""==PROMOTE_ID||null==PROMOTE_ID){
		 parent.showErrorMsg("请选择活动信息");
		 return;
	}
	$('#queryForm').submit();
}
function countPrice(id){
	var DECT_PRICE=$("#DECT_PRICE"+id).val();
	var PRICE=$("#PRICE"+id).val();
	var DECT_RATE=Math.round((isNaN(DECT_PRICE/PRICE)?0:DECT_PRICE/PRICE)*100)/100;
	$("#DECT_RATE"+id).val(DECT_RATE);
}