var setting = ayncTreeSettion();
		
$(function(){
	 treePageInit();
});

//点击跳转
function zTreeOnClick(event, treeId, treeNode) {
    //parent.document.getElementById("topcontent").src="jgxx.shtml?action=toList&JGXXID="+treeNode.id;
};
//异步加载url
function getUrl(treeId, treeNode) {
	return "sample.shtml?action=getTreeAsync&sjjg=" + treeNode.id;
}
 
	