var setting = treeSetting();

$(function(){
	 treePageInit();
});

function zTreeOnClick(event, treeId, treeNode) {
	//点击后页面跳转	
	parent.document.getElementById("topcontent").src="pdtmenu.shtml?action=toList&productid="+utf8(treeNode.id);
};

