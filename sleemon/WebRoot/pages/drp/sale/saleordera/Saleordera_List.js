
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("saleordera.shtml?action=toList&module=" + module);
    $("#expdate").click(function(){
		 $("#queryForm").attr("action","saleordera.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
    $("#allPrint").click(function(){
	window.open("saleordera.shtml?action=toAllPrint","销售订单批量打印","height=650, width=1200, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no");
})
});
