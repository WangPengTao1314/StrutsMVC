/**
 *@module 系统管理
 *@func 自定义查询
 *@version 1.1
 *@author zhuxw
 */
$(function(){  
	//页面初始化
	listPageInit("customQuery.shtml?action=toEditTableList"); 
	
	InitFormValidator(0);
	
	//保存按钮
	$("#q_save").click(function(){
		$("#queryForm").submit(); 
	});
	
	$("#q_close").click(function(){
		$("#tableName").val("");
		$("#whereSql").val(""); 
	});
}); 

function query(state){
	var url = "customQuery.shtml?action=toEditTableList";
	window.location=url;  
}

//选择数据表
function selectTable(){  
	var obj=selCommon('System_19', false, 'tableName', 'TABLE_NAME', 'forms[0]','tableName', 'TABLE_NAME', "selPzr");
	$("#lableTable").html($("#tableName").val()); 
}