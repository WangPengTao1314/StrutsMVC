/**
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_List
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
 */
$(function(){
    var module = parent.window.document.getElementById("module").value;	
	//主从及主从从列表页面通用加载方法
	listPageInit("profiiloss.shtml?action=toList&module=" + module);
});
 
 //点击选择某条记录后，需要根据状态判断是否可用
function setSelOperateEx(obj) {
	var selRowId = parent.document.getElementById("selRowId").value;
	var state = document.getElementById("state" + selRowId).value;
	//按钮状态重置
	btnReset();
	//当状态已生效
	if (state == "已结算" || state == "已核销") {
		btnDisable(["delete","modify","commit"]);
	}
}
