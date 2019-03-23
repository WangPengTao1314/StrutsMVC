var setting = treeSetting();

$(function(){
	 treePageInit();
});

function zTreeOnClick(event, treeId, treeNode) {
	//点击后页面跳转
	parent.document.getElementById("topcontent").src="singletree.shtml?action=toList&JGXXID="+treeNode.id;
};

