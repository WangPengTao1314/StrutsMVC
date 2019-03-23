/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturnstoreout_List
 * @author lyg
 * @time   2014-10-31 10:42:20 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("termreturnstoreout.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("termreturnstoreout.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("termreturnstoreout.shtml?action=delete", "STOREOUT_ID");
	
	$("#expdate").click(function(){
		 $("#queryForm").attr("action","termreturnstoreout.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	});
});
