/**
 * @prjName:喜临门营销平台
 * @fileName:Storeout_List_Chld
 * @author chenj
 * @time   2013-09-15 14:59:50 
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

