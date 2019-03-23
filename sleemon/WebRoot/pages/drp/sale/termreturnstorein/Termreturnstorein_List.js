/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturnstorein_List
 * @author lyg
 * @time   2014-10-27 15:46:33 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("termreturnstorein.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("termreturnstorein.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("termreturnstorein.shtml?action=delete", "STOREIN_ID");
});
