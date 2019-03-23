/**
 *@module 系统管理
 *@func 自定义查询
 *@version 1.1
 *@author zhuxw
 */
$(function(){  
}); 

//展示
function displayRpt(hidRptPk){
	$("#hidRptPk").val(hidRptPk);
	$("#queryType").val("0");
	$("#qryForm").submit();
}