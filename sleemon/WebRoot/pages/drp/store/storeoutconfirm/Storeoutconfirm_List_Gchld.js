/**
 * @prjName:喜临门营销平台
 * @fileName:Storeingrand_List_Chld
 * @author glw
 * @time   2013-08-30 11:18:12 
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
