/**
 * @prjName:喜临门营销平台
 * @fileName:Termstoreouta_List
 * @author lyg
 * @time   2014-10-26 14:47:09 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("termstoreouta.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("termstoreouta.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("termstoreouta.shtml?action=delete", "STOREOUT_ID");
	
	$("#expdate").click(function(){
		 $("#queryForm").attr("action","termstoreouta.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	 });
});
