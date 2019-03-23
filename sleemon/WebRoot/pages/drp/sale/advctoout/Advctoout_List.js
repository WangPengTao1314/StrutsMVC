
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoout_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	//删掉查询状态里的未提交和回退状态
	$("#query").click(function(){
		$("#STATE option[text='未提交']").remove();
		$("#STATE option[text='退回']").remove();
		$("#STATE option[text='待核价']").remove();
		$("#STATE option[text='待确认']").remove();
	});
	var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("advctoout.shtml?action=toList&module=" + module);
});
	function setSelOperateEx(obj){
	//可根据id或name或所在列数获得值，具体方法开发人员自行选择。
	var selRowId = $("#selRowId").val();
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
   if (state != "提交") {
		btnDisable(["commit"]);
	}
 };
 function listRef(){
	 $("#pageForm").submit();
 }
