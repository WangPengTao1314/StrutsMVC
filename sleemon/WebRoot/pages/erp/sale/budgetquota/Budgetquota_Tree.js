var setting = treeSetting();

$(function(){
	 treePageInit();
});

function zTreeOnClick(event, treeId, treeNode) {
	//点击后页面跳转	
	parent.document.getElementById("topcontent").src="budgetquota.shtml?action=toList&NODEID="+utf8(treeNode.id);
};

