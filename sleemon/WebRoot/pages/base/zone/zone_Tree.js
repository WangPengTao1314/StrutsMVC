var setting = treeSetting();

$(function(){
	 treePageInit();
});

function zTreeOnClick(event, treeId, treeNode) {
	//点击后页面跳转	
	parent.document.getElementById("topcontent").src="zone.shtml?action=toList&TRNOID="+utf8(treeNode.id);
};