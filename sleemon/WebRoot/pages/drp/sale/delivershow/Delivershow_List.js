/**
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 交付计划维护
 * @author zzb
 * @time   2013-10-12 13:52:19 
 * @version 1.1
 */
$(function(){
    //维护页面需要隐藏的按钮
	whBtnHidden(["audit"]);
	//审核页面需要隐藏的按钮
	shBtnHidden(["add", "modify", "delete", "commit", "revoke"]);
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("delivershow.shtml?action=toList&module=" + module);
 
	 $("#expdate").click(function(){
		 $("#queryForm").attr("action","turnoverhd.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
});
  


 
  

  
 