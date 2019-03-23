/**
 * @prjName:喜临门营销平台
 * @fileName:Drpreturna_List
 * @author lyg
 * @time   2014-10-26 15:41:36 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("drpreturna.shtml?action=toList&module=" + module);
	//新增和修改按钮初始化
	addModiToEditFrameInit("drpreturna.shtml?action=toEditFrame");
	//删除监听.参数1：删除action，参数2：删除主键Id
	mtbDelListener("drpreturna.shtml?action=delete", "PRD_RETURN_ID");
	
	$("#expdate").click(function(){
		 $("#queryForm").attr("action","drpreturna.shtml?action=ExcelOutput&module=" + module);
		 $("#queryForm").submit();
	});
	$("#allPrint").click(function(){
		window.open("drpreturna.shtml?action=toAllPrint","下级退货单批量打印","height=650, width=1200, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no");
	})
});
