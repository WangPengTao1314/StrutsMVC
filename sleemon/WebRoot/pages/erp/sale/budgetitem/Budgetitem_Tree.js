var setting = treeSetting();

$(function(){
	 treePageInit();
});

function zTreeOnClick(event, treeId, treeNode) {
	//点击后页面跳转	
	parent.document.getElementById("topcontent").src="budgetitem.shtml?action=toList&BUDGET_ITEM_ID="+utf8(treeNode.id);
};

