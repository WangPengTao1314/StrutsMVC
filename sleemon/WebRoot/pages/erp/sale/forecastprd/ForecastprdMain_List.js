/**
 * @module 预计量上报管理
 * @func 预计量上报货品设置
 * @version 1.1
 * @author 张忠斌
 */ 
$(function () {
     $("#querydiv").css("display","block");
     //初始化校验
	InitFormValidator("mainForm");
	
	
	$("#save").click(function(){
		saveProdSet();
	});
	
	$("#query").click(function(){
		 queryChann();
	});
	
});
 
  
// 以用户维度下帧调用查询
function queryChann(){
    
	parent.bottomcontent.$("#PRD_NO").val($("#PRD_NO").val());
	parent.bottomcontent.$("#PRD_NAME").val($("#PRD_NAME").val());
	parent.bottomcontent.$("#PRD_SPEC").val($("#PRD_SPEC").val());
	parent.bottomcontent.$("#PAR_PRD_NAME").val($("#PAR_PRD_NAME").val());
	parent.bottomcontent.$("#PAR_PRD_NO").val($("#PAR_PRD_NO").val());
	
	
	var notcharg = "0";
	var falg1 = $("#notcharg_1").attr("checked");//显示未分管
	var falg2 = $("#notcharg_2").attr("checked");//显示已分管
	if("checked" == falg1){
		notcharg = "1";
	}
	if("checked" == falg2){
		notcharg = "2";
	}
	parent.bottomcontent.$("#notcharg").val(notcharg);
	parent.bottomcontent.$("#queryForm").attr("action","forecastprd.shtml?action=childList");
	parent.bottomcontent.$("#queryForm").submit();
 
}



function saveProdSet(){
	var PRD_IDS =  parent.document.getElementById("selRowId").value;
	var deleteIds = parent.document.getElementById("deleteIds").value;
    $.ajax({
	 url: "forecastprd.shtml?action=edit",
	 type:"POST",
 	 dataType:"text",
 	 data:{"PRD_IDS":PRD_IDS,"deleteIds":deleteIds},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel(utf8Decode(jsonResult.messages));
			queryChann();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
 