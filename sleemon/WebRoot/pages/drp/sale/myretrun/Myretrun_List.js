/**
 * @prjName:喜临门营销平台
 * @fileName:Myretrun_List
 * @author wzg
 * @time   2013-08-25 09:38:48 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("myretrun.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("myretrun.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("myretrun.shtml?action=delete", "PRD_RETURN_ID");
	//删掉查询状态里的少发退货状态
	$("#query").click(function(){
		$("#STATE option[text='少发退货']").remove();
	});
});
