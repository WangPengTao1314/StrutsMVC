/**
 * @prjName:喜临门营销平台
 * @fileName:Drpreturnstorein_List
 * @author lyg
 * @time   2014-10-27 14:51:37 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("drpreturnstorein.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("drpreturnstorein.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("drpreturnstorein.shtml?action=delete", "STOREIN_ID");
	
	$("#expdate").click(function(){
		 $("#queryForm").attr("action","drpreturnstorein.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	});
});
