/**
 *@module 基础数据
 *@func人员信息
 *@version 1.1
 *@author 吴亚林
 */
var setting = treeSetting();

$(function(){
	 treePageInit();
});

function zTreeOnClick(event, treeId, treeNode) {
	//点击后页面跳转
	parent.document.getElementById("topcontent").src="ryxx.shtml?action=toList&BMXXID="+treeNode.id;
};