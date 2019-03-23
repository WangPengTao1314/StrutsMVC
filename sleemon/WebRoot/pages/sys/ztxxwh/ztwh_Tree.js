var setting = treeSetting();

$(function(){
	 treePageInit();
});

function zTreeOnClick(event, treeId, treeNode) {
	//点击后页面跳转
	parent.document.getElementById("topcontent").src="ztxx.shtml?action=toList&ZTXXID="+treeNode.id;
};

