/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List_Gchld
 * @author lyg
 * @time   2013-08-11 17:17:29 
 * @version 1.1
 */
$(function(){

	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
});
