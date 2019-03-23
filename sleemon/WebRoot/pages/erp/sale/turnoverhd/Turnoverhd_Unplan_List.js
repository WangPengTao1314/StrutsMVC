/**
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 交付计划维护
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
	
    queryInit("turnoverhd.shtml?action=toNotPlanList");   
    
    //表头排序
	headColumnSort("ordertb","pageForm");
	$("#pageForm").attr("action","turnoverhd.shtml?action=toNotPlanList");
 
});


//查看特殊工艺
function selectTechPage(SPCL_TECH_ID,PRICE){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID="+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

  

 
