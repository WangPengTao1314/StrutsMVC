/**
 * @prjName:喜临门营销平台
 * @fileName: 发运管理 - 交付计划维护
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	 
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox[name='mx_checkbox']").attr("checked","true");
		}else{
			$("#ordertb :checkbox[name='mx_checkbox']").removeAttr("checked");
		}
		
		 
	});
	
 
	
});
 
 



 
 
//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}









