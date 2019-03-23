/**
 *@module 系统管理
 *@func 自定义查询
 *@version 1.1
 *@author zhuxw
 */
$(function(){
	//启用
	$("#open").click(function(){
		var rptPk = $("input[type='radio'][checked='true']").val(); 
		doRptState("1",rptPk);
	});
	 var formId = "pageForm";
	 if(document.body.scrollWidth>document.body.clientWidth)
     {
      $("#"+formId).append("<br>&nbsp;<br>&nbsp;");
     }
}); 