/**
 *@module 基础数据
 *@func 部门信息维护
 *@version 1.1
 *@author 吴亚林
 */
var setting = treeSetting();

$(function(){
	 treePageInit();
});

function zTreeOnClick(event, treeId, treeNode) {
	//点击后页面跳转
		parent.document.getElementById("topcontent").src="bmxx.shtml?action=toList&BMXXID="+treeNode.id+"&flag="+treeNode.def1;
};