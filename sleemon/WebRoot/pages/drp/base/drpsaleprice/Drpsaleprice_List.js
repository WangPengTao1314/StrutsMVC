
/**
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 刘曰刚
 */
$(function () {
    //页面初始化
	listPageInit("drpsaleprice.shtml?action=toList");
	$("#modify").click(function(){
		 var PRD_ID = $("#TEMP_PRD_ID").val();
		 var RETAL_PRICE_ID = $("#TEMP_RETAL_PRICE_ID").val();
	 	 if((null == RETAL_PRICE_ID || "" == RETAL_PRICE_ID)&&(null == PRD_ID || "" == PRD_ID)){
	 		 parent.showErrorMsg("请选择一条记录");
	 	 }else{
			parent.window.gotoBottomPage("toEdit",PRD_ID,RETAL_PRICE_ID);
	 	 }
	});
	$("#tempUp").click(function(){
	window.location="drpsaleprice.shtml?action=ExcelOutput";
})
$("#down").click(function(){
	$("#queryForm").attr("action","drpsaleprice.shtml?action=toExpData");
	$("#queryForm").submit();
})
	$("#up").click(function () {
		$("#tempUp").show()
		$("#upFile").show();
	});
	$("#save").click(function(){
		if(!check()){
			return;
		}
		var jsonData = multiPackJsonData("ordertb");
		$.ajax({
		url: "drpsaleprice.shtml?action=edit",
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
	 	url: "drpsaleprice.shtml?action=delete&RETAL_PRICE_ID="+selRowId,
		type:"POST",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				window.location="drpsaleprice.shtml?action=toList&RETAL_PRICE_ID="+selRowId;
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
		var DECT_PRICE=$(this).parent().parent().find("input[name='DECT_PRICE']").val();//单价
		if(""==DECT_PRICE||null==DECT_PRICE){
				parent.showErrorMsg("请输入折扣价");
	            flag=false;
				return false;
			}
		var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
	        if(!re2.test(DECT_PRICE)){
	            parent.showErrorMsg("折扣价最多可输入8位正整数和2位小数");
	            flag=false;
				return false;
	        }
	})
	return flag;
}
//导入
function uploading(){
	var fileName = $("#upInfo").val();
	$.ajax({
	 url: "drpsaleprice.shtml?action=toImportData",
	 type:"POST",
 	 dataType:"text",
 	 data:{"fileName":fileName},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			alert("上传成功");
            $("#pageForm").submit();
            $("#upFile").hide();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
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
